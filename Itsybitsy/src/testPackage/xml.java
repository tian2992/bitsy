package testPackage;

import BitsyL.*;

import arbolB.ArbolB;

import listaEnlazada.ListaEnlazada;

public class xml
{
  public xml()
  {

  }

  public static void main(String[] args)
  {

    System.out.println("main");

    /* actualizacion #n */ 
    // crear el manejador de xml, para que la tabla este limpia, estado vacio, reconstruccion del arbol desde 0 
    ManejoXML manejo = new ManejoXML();

    /*manejo*/
    //procesar todos los xml,de los clientes, ( los xml actualizados )
    
    
    
    manejo.procesarXML("/tmp/BitsyXML/clienteprueba.clt");
    
    ControladorBusqueda control = new ControladorBusqueda();
    
    
 
    /*
     
      for( archivos en una carpeta )
      {
         manejo.procesarXML(archivoI);
       }

       Arbol a = new Arbol ... 

      manejo.rellenar( a ) ;
     */

    //validar que la tabla este correcta, debug only 
    System.out.println(manejo.getTablaArbol().toString());

    //crear un arbol nuevo y vacio
    ArbolB<String, ListaEnlazada<Item>> arbolDatos =
      new ArbolB<String, ListaEnlazada<Item>>();


    //hacer que el arbol, sea rellenado por la clase ManejoXML con los datos extraidos de todos los XML procesados
    //manejo.rellenarArbol(arbolDatos);


    //control.setArbolDatos(arbolDatos);
    
  //  arbolDatos = control.getArbolDatos();
    
  //  System.out.println(arbolDatos.toDot());
    
   // arbolDatos.generarGrafoJPG("/tmp/","grafo");
      
      boolean repetir = true; 
      while(  repetir  ) { 
          
    
              try {
                  
                      ListaEnlazada<Item> res = (ListaEnlazada<Item>) control.buscar("Keyword.java", true, null, null);
                      
                      Item result = res.get(0);
                  
                      System.out.println(" numero de resultados " + result.toString() );
                  } catch (Exception e) {
                      // TODO: Add catch code
                      e.printStackTrace();
                  }
              
          
          repetir = true;  }
    //debug only, not working due to poor toString method in class BTreee :( 
    System.out.println(arbolDatos);
    
    
    

  }

}
