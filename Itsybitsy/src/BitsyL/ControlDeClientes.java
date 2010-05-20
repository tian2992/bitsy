package BitsyL;


import java.io.File;
import java.io.FileWriter;

import java.util.LinkedList;

public class ControlDeClientes
{
  public ControlDeClientes()
  {

    //datos.LecturaXML("C:\\Users\\Black Empires\\Desktop\\edd.txt");

    System.out.println("Lectura exitosa");

  }
  ManejoXML datos = new ManejoXML();
  Item item = new Item();
  DatosCliente usuario = new DatosCliente();


  private LinkedList<DatosCliente> usuarios = new LinkedList<DatosCliente> ();
    
    
    /**
   * Rellenar la Linked List desde la clase "Server" que será la encargada de decir quien
   * esta online y quien offline y dira cuando hay un usuario nuevo :P
   * @param pUsuario
   */
  public void agregarUsuario( DatosCliente pUsuario )
  {
    usuarios.add(pUsuario);
  }
  
  
  /**
   * Escritura del archivo .cas en la carpeta de la aplicacion web
   * donde se lleva el control de los usuarios
   */  
  public File escribirArchivoEnCarpetaServidor(String pPath)
  {
    File fi = new File(pPath);

    FileWriter f;
    StringBuilder b = new StringBuilder();

    b.append("<CAClientes>");
    
    for( int i = 0 ; i < usuarios.size() ; i++ )
    {
      DatosCliente  actual = usuarios.get(i);
      b.append( actual.toString() ) ;  
    }
    

    b.append(" />");


    try
    {

      f = new FileWriter(fi);


      f.close();

    }
    catch (Exception e)
    {

      e.printStackTrace();
    }
    return fi;

  }
}
