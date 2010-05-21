package BitsyL;

import arbolB.ArbolB;

import java.io.*;

import java.util.*;

import listaEnlazada.ListaEnlazada;

import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.*;

public class ManejoXML {

    private LinkedList<String> mPath = new LinkedList<String>();
    
    //private ListaEnlazada<Item> mListaDeItems = new ListaEnlazada<Item>();

    private DatosCliente user = new DatosCliente();    
    
    private Item item = null;
    
    private Hashtable<String, ListaEnlazada<Item>> TablaArbol = new Hashtable<String, ListaEnlazada<Item>> ();
    
    public void rellenarArbol( ArbolB<String,ListaEnlazada<Item>> pArbol )
    {
     
      Iterator<String> llaves = TablaArbol.keySet().iterator();
      
      while( llaves.hasNext() )
      {
        
        String llave = llaves.next();
        
        ListaEnlazada<Item> lista = TablaArbol.get(llave);
        
        pArbol.insert(llave, lista);
        
      }
      
    }
    
    private void agregarATabla( String pNombreDeArchivo, Item pItem )
    {
      if( TablaArbol.containsKey(pNombreDeArchivo))
      {
        ListaEnlazada<Item> items = TablaArbol.get(pNombreDeArchivo);
        items.add(pItem);
      } else        
      {
        ListaEnlazada<Item> items = new ListaEnlazada<Item>();
        items.add(pItem);
        TablaArbol.put(pNombreDeArchivo, items);
      }
    }
    
    private String getPath() {
        StringBuilder b = new StringBuilder();
        for( int i = 0; i < mPath.size(); i++ ) {
            b.append(mPath.get(i) + "/");         
            
        }
        return b.toString();
    }
        
    private void quitarUltimo() {
        if( mPath.size() > 0 ) {
            mPath.remove( mPath.size() - 1 );
        }
    }
    
    /**
     * carga los xml en el arbol
     *
     * @param pPath es la path de la carpeta donde estan los indices XML de los clientes
     */
    public void procesarXML( String pPath ) {
     
        
        StringBuilder b = new StringBuilder();
        
        try {
            SAXBuilder builder = new SAXBuilder(false);
            
            Document doc = builder.build(pPath);

            Element raiz = doc.getRootElement();
            
            b.append("El id de usuario es : ");
            b.append(raiz.getAttributeValue("id") + "\n");
            user.setId(raiz.getAttributeValue("id"));

            b.append("La ip es: ");
            b.append(raiz.getAttributeValue("ip") + "\n");
            user.setIp(raiz.getAttributeValue("ip"));

            b.append("La carpeta compartida es: ");
            b.append(raiz.getAttributeValue("raiz") + "\n");
            
            mPath.add(raiz.getAttributeValue("raiz"));
            
            System.out.println(b.toString());
            
            
            //Lista de carpetas
            List carpetas = raiz.getChildren("carpeta");
            //System.out.println("Numero de carpetas es: " + carpetas.size());
            
            for( int i = 0; i < carpetas.size(); i++ ) {
                procesarDirectorio( (Element)carpetas.get(i) );
            }

            //Lista de Archivos
            List archivos = raiz.getChildren("archivo");
            //System.out.println("Numero de archivos es: " + archivos.size());
            
            for( int i = 0; i < archivos.size(); i++ ) {
                procesarArchivo( (Element) archivos.get(i) );
            }
            
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
        
    }
    
    private void procesarDirectorio( Element pDirectorio ) {
                
        List carpetas = pDirectorio.getChildren("carpeta");
        
        String nombreCarpeta = pDirectorio.getAttributeValue("nombre");

        mPath.add(nombreCarpeta);

        //System.out.println("Numero de carpetas es: " + carpetas.size());
        
        for( int i = 0; i < carpetas.size(); i++ ) {
            procesarDirectorio( (Element)carpetas.get(i) );
        }
        
        //Lista de Archivos
        List archivos = pDirectorio.getChildren("archivo");
        //System.out.println("Numero de archivos es: " + archivos.size());
        
        for( int i = 0; i < archivos.size(); i++ ) {
            procesarArchivo( (Element) archivos.get(i) );
        }
        
        this.quitarUltimo();
        
    }
    
    private void procesarArchivo( Element pArchivo ) {
        
        Element za = pArchivo;

        //System.out.println("Nombre archivo: " +  za.getAttributeValue("nombre"));
        //System.out.println("Path " + getPath() );
    
        System.out.println(getPath() + za.getAttributeValue("nombre") );

        item = new Item();
        item.setNombre(za.getAttributeValue("nombre"));
        item.setSize(za.getAttributeValue("size"));
        item.setFechaModificacion(za.getAttributeValue("fecha_modificacion"));
        item.setExtension(za.getAttributeValue("extension"));
        item.setPathCompleto( getPath() ) ;
        item.setNombreCliente( user.getId() );
        //mListaDeItems.add(item);
         
        this.agregarATabla(za.getAttributeValue("nombre"), item);         
         
        
    }
    
    public ManejoXML() {
        
        
        
    }

/*
    StringBuilder b = new StringBuilder();

    Item item = null;
    DatosCliente user = new DatosCliente();

    private String nombreDelXML = "";
    private StringBuilder manejarPath1 = new StringBuilder();
    private StringBuilder manejarPath2 = new StringBuilder();
    private StringBuilder manejarPath3 = new StringBuilder();

    private LinkedList<StringBuilder> listaManejarPath1 =
        new LinkedList<StringBuilder>();
    private LinkedList<StringBuilder> listaManejarPath2 =
        new LinkedList<StringBuilder>();
    private LinkedList<StringBuilder> listaManejarPath3 =
        new LinkedList<StringBuilder>();

    public String getDirectorio(StringBuilder pManejar) {

        return pManejar.toString();

    }

    public void remover(StringBuilder algo) {

        for (int i = 0; i < algo.length(); i++) {

        }
    }

    public String LecturaXML(String pPath) {
        StringBuilder b = new StringBuilder();

        File archivo = new File(pPath);

        nombreDelXML = archivo.getName();

        try {
            item = new Item();
            SAXBuilder builder = new SAXBuilder(false);
            Document doc = builder.build(pPath);

            Element raiz = doc.getRootElement();

            //Datos del CACliente
            b.append("El id de usuario es : ");
            b.append(raiz.getAttributeValue("id") + "\n");
            user.setId(raiz.getAttributeValue("id"));

            b.append("La ip es: ");
            b.append(raiz.getAttributeValue("ip") + "\n");
            user.setIp(raiz.getAttributeValue("ip"));

            b.append("La carpeta compartida es: ");
            b.append(raiz.getAttributeValue("raiz") + "\n");
            item.setNombreCarpetaCompartida(raiz.getAttributeValue("raiz"));

            manejarPath1.append(raiz.getAttributeValue("raiz") +
                                "\\"); //Compartida + Carpeta + archivo
            manejarPath2.append(raiz.getAttributeValue("raiz") +
                                "\\"); //Compartida + Carpeta + subcarpetas + archivo
            manejarPath3.append(raiz.getAttributeValue("raiz") +
                                "\\"); //Compartida + Archivo

            List listaHijos = raiz.getChildren();
            System.out.println("Numero de hijos " + listaHijos.size());

            //Lista de carpetas
            List carpetas = raiz.getChildren("carpeta");
            System.out.println("Numero de carpetas es: " + carpetas.size());

            //Lista de Archivos
            List archivos = raiz.getChildren("archivo");
            System.out.println("Numero de archivos es: " + archivos.size());


            Iterator a = carpetas.iterator();
            Iterator z = archivos.iterator();

            Element aa = null;

            //Recorrer las carpetas
            while (a.hasNext()) {

                aa = (Element)a.next();


                System.out.println("Nombre carpeta : " +
                                   aa.getAttributeValue("nombre"));

                manejarPath1.append(aa.getAttributeValue("nombre") + "\\");
                manejarPath2.append(aa.getAttributeValue("nombre") + "\\");

                //Lista de carpetas contenidas en las carpetas
                List subCarpetas = aa.getChildren("carpeta");
                System.out.println("Numero de subCarpetas es: " +
                                   subCarpetas.size());

                Iterator as = subCarpetas.iterator();

                //Recorrer la lista de subcarpetas
                while (as.hasNext()) {


                    Element at = (Element)as.next();
                    System.out.println("Nombre de la carpeta contenida en la carpeta es: " +
                                       at.getAttributeValue("nombre"));

                    manejarPath2.append(at.getAttributeValue("nombre") + "\\");

                    //Crear lista de archivos que estan dentro de la subCarpeta
                    List subArchivos = at.getChildren("archivo");
                    System.out.println("El numero de subArchivos es: " +
                                       subArchivos.size());

                    Iterator ac = subArchivos.iterator();

                    //Recorrer lista de archivos que estan dentro de la subCarpeta
                    while (ac.hasNext()) {
                        item = new Item();
                        Element ad = (Element)ac.next();

                        System.out.println("Nombre del archivo : " +
                                           ad.getAttributeValue("nombre"));

                        manejarPath2.append(ad.getAttributeValue("nombre") +
                                            "." +
                                            ad.getAttributeValue("extension"));
                        this.listaManejarPath2.add(manejarPath2);


                        String no =
                            ad.getAttributeValue("nombre") + "." + ad.getAttributeValue("extension");

                        int ultimoElemento = no.length();
                        int cadena = manejarPath2.length();

                        int resta = cadena - ultimoElemento;

                        String temporal = manejarPath2.toString();

                        temporal = temporal.substring(0, resta);

                        manejarPath2 = new StringBuilder(temporal);

                        item.setNombre(ad.getAttributeValue("nombre"));
                        item.setSize(ad.getAttributeValue("size"));
                        item.setFechaModificacion(ad.getAttributeValue("fecha_modificacion"));
                        item.setExtension(ad.getAttributeValue("extension"));
                    }

                }

                //Crear lista de archivos que estan dentro de la fuckin' carpeta
                List subArchivos = aa.getChildren("archivo");
                System.out.println("El numero de subArchivos es: " +
                                   subArchivos.size());

                Iterator ac = subArchivos.iterator();

                //Recorrer lista de archivos que estan dentro de la carpeta
                while (ac.hasNext()) {
                    item = new Item();
                    Element ad = (Element)ac.next();

                    System.out.println("Nombre del archivo : " +
                                       ad.getAttributeValue("nombre"));

                    manejarPath1.append(ad.getAttributeValue("nombre") + "." +
                                        ad.getAttributeValue("extension"));
                    listaManejarPath1.add(manejarPath1);


                    String no =
                        ad.getAttributeValue("nombre") + "." + ad.getAttributeValue("extension");

                    int ultimoElemento = no.length();
                    int cadena = manejarPath1.length();

                    int resta = cadena - ultimoElemento;

                    String temporal = manejarPath1.toString();

                    temporal = temporal.substring(0, resta);

                    manejarPath1 = new StringBuilder(temporal);

                    item.setNombre(ad.getAttributeValue("nombre"));
                    item.setSize(ad.getAttributeValue("size"));
                    item.setFechaModificacion(ad.getAttributeValue("fecha_modificacion"));
                    item.setExtension(ad.getAttributeValue("extension"));
                }

            }

            //Recorrer lista de archivos
            while (z.hasNext()) {
                item = new Item();
                Element za = (Element)z.next();

                System.out.println("Nombre archivo: " +
                                   za.getAttributeValue("nombre"));

                manejarPath3.append(za.getAttributeValue("nombre") + "." +
                                    za.getAttributeValue("extension"));
                this.listaManejarPath3.add(manejarPath3);

                item.setNombre(za.getAttributeValue("nombre"));
                item.setSize(za.getAttributeValue("size"));
                item.setFechaModificacion(za.getAttributeValue("fecha_modificacion"));
                item.setExtension(za.getAttributeValue("extension"));

            }


            for (int i = 0; i < this.listaManejarPath1.size(); i++) {
                StringBuilder resultado = listaManejarPath1.get(i);

                System.out.println(resultado.toString());
            }

            for (int i = 0; i < this.listaManejarPath2.size(); i++) {
                StringBuilder resultado = listaManejarPath2.get(i);

                System.out.println(resultado.toString());
            }


            for (int i = 0; i < this.listaManejarPath3.size(); i++) {
                StringBuilder resultado = listaManejarPath3.get(i);

                System.out.println(resultado.toString());
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    public void setNombreDelXML(String nombreDelXML) {
        this.nombreDelXML = nombreDelXML;
    }

    public String getNombreDelXML() {
        return nombreDelXML;
    }*/


  public void setTablaArbol(Hashtable<String, ListaEnlazada<Item>> TablaArbol)
  {
    this.TablaArbol = TablaArbol;
  }

  public Hashtable<String, ListaEnlazada<Item>> getTablaArbol()
  {
    return TablaArbol;
  }
}


