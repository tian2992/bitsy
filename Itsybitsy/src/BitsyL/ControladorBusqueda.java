package BitsyL;

import arbolB.ArbolB;
import java.io.Serializable;

import java.net.Socket;

import java.util.Date;
import java.util.List;
import listaEnlazada.*;

public class ControladorBusqueda implements Serializable {

    NetworkController netCon;
    ArbolUpdaterThread aUpdate;
    volatile static ArbolB<String,ListaEnlazada<Item>> arbolDatos; //para evitar que hayan clavos con los thread

    public boolean getIsListo() {
        return arbolDatos == null ;
    }
    
    public ControladorBusqueda() {
        super();
        netCon = NetworkController.getInstance();
        netCon.init();
        aUpdate = new ArbolUpdaterThread(arbolDatos);
        aUpdate.start();
    }
    
    /**
     * Busca
     * @param simple si es true es arbol, si es false no lo es
     * @param filtroArchivo
     * @param filtroFecha solo contiene dos fechas o es null, si la primera va despues de la primera, falla.
     * @return
     * @throws ExcepcionFiltroIncorrecto
     */
    public List<Item> buscar(String nombre, boolean simple, String[] filtroArchivo, Date[] filtroFecha) throws ExcepcionFiltroIncorrecto{
        
        if (!simple){
            return buscarContenido(nombre);
        }
        
        
        if (filtroFecha != null){
            if (filtroFecha.length != 2){
                throw new ExcepcionFiltroIncorrecto("Error en filtros de fecha");
            }
            else{
                if (filtroFecha[0].after(filtroFecha[1])){
                    throw new ExcepcionFiltroIncorrecto("Error en filtros de fecha");
                }
            }
        }
        if (filtroArchivo != null){
            if (filtroArchivo.length==0)
                throw new ExcepcionFiltroIncorrecto("Error en filtros de Archivo");
        }
        
        ListaEnlazada<Item> listaResultados = arbolDatos.get(nombre);

        System.out.println(listaResultados.size());

        ListaEnlazada<Item> lista = new ListaEnlazada<Item>();
        
        //no fue necesiario post procesar
        if (filtroFecha==null && filtroArchivo==null){
            return listaResultados;
        }

        ListaEnlazada<Item> listaFechas = new ListaEnlazada<Item>();
        ListaEnlazada<Item> listaFiltro = new ListaEnlazada<Item>();
        
        
        for (Item i: listaResultados){
          //  if (i.getFechaModificacion().before(filtroFecha[0]) && i.getFechaModificacion().after(filtroFecha[1])){
                
           // }
        }
        
        return lista;
        
    }
    
    public List<Item> buscarContenido(String cadena){
        ListaEnlazada<Item> list = new ListaEnlazada<Item>();
        try {
            for (Socket s: NetworkController.getInstance().getSockets()){
                FindFile fin = new FindFile(s,cadena);
                fin.start();
                fin.join(2000);
                list.addAll(fin.getLis());
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        
        return list;
    }
            
    //Setters y Getters

    public static void setArbolDatos(ArbolB<String, ListaEnlazada<Item>> ab) {
        arbolDatos = ab;
    }

    public static ArbolB<String, ListaEnlazada<Item>> getArbolDatos() {
        return arbolDatos;
    }
    
}
