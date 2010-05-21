package BitsyL;

import arbolB.ArbolB;

import java.io.File;

import java.net.Socket;

import java.util.List;

import listaEnlazada.ListaEnlazada;

public class ArbolUpdaterThread extends Thread {

    ArbolB<String, ListaEnlazada<Item>> arbolDatos;
    boolean funcionando = true;

    public ArbolUpdaterThread(ArbolB<String, ListaEnlazada<Item>> a) {
        super();
        arbolDatos = a;
    }

    public void run() {
        try {
            while (funcionando) {
                System.out.println("Actualizando");
                //TODO: hacer operacion de actualizar

                NetworkController n = NetworkController.getInstance();

                List<Socket> sockets = n.getSockets();

                /* actualizacion #n */
                // crear el manejador de xml, para que la tabla este limpia, estado vacio, reconstruccion del arbol desde 0
                ManejoXML manejo = new ManejoXML();

                /*manejo*/
                //procesar todos los xml,de los clientes, ( los xml actualizados )


                File directorio = new File("/tmp/BitsyXML");

                String[] nombresDeArchivos = directorio.list();

                for (int i = 0; i < nombresDeArchivos.length; i++) {

                    String nombre = nombresDeArchivos[i];
                    manejo.procesarXML(nombre);
                }


                //crear un arbol nuevo y vacio
                ArbolB<String, ListaEnlazada<Item>> arbolDatos =
                    new ArbolB<String, ListaEnlazada<Item>>();


                //hacer que el arbol, sea rellenado por la clase ManejoXML con los datos extraidos de todos los XML procesados
                manejo.rellenarArbol(arbolDatos);

                //Arbol rellenado con exito (espero )

                for (Socket s : sockets) {
                    //TODO: cambiar el archivo correcto
                    File f = new File("/tmp/bleg");

                    GetIndex gi = new GetIndex(s, f);

                    gi.start();
                    gi.join();

                    f = gi.getIndex();

                    Thread.sleep(250);

                    gi.stop(); //mejor prevenir que lamentar
                }

                Thread.sleep(1000 * 60 * 3); //3 mins
            }
        } catch (Exception e) {

        }
    }


    public void setFuncionando(boolean funcionando) {
        this.funcionando = funcionando;
    }

    public boolean isFuncionando() {
        return funcionando;
    }
}
