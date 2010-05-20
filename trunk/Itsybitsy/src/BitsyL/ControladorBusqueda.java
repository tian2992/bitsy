package BitsyL;

import arbolB.ArbolB;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import listaEnlazada.*;

public class ControladorBusqueda implements Serializable {
    public ControladorBusqueda() {
        super();
    }
    
    ArbolB<String,ListaEnlazada<Item>> arbolDatos;
    
    /**
     * Busca
     * @param nombre
     * @param filtroArchivo
     * @param filtroFecha solo contiene dos fechas o es null, si la primera va despues de la primera, falla.
     * @return
     * @throws ExcepcionFiltroIncorrecto
     */
    public List<Item> buscar(String nombre, String[] filtroArchivo, Date[] filtroFecha) throws ExcepcionFiltroIncorrecto{
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
    
    public List<Item> buscarElemento(){
        return null;
    }

    //Setters y Getters

    public void setArbolDatos(ArbolB<String, ListaEnlazada<Item>> arbolDatos) {
        this.arbolDatos = arbolDatos;
    }

    public ArbolB<String, ListaEnlazada<Item>> getArbolDatos() {
        return arbolDatos;
    }
}
