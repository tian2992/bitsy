package BitsyL;

import arbolB.ArbolB;

import java.io.File;

import java.net.Socket;

import java.util.List;

import listaEnlazada.ListaEnlazada;

public class ArbolUpdaterThread extends Thread {
    
    ArbolB<String,ListaEnlazada<Item>> arbolDatos;
    boolean funcionando = true;
    
    public ArbolUpdaterThread(ArbolB<String,ListaEnlazada<Item>> a) {
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

                Thread.sleep(1000*60*3); //3 mins
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
