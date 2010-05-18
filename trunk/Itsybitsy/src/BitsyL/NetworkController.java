package BitsyL;

import LogicaBaseDeDatos.*;
import java.io.*;
import java.net.*;

import listaEnlazada.ListaEnlazada;

public class NetworkController{

    ServerSocket serverSocket = null;
    PrintWriter out = null;
    BufferedReader in = null;

    boolean listening = true;
    
    ListaEnlazada<NetWorkControllerThread> netControllers = new ListaEnlazada<NetWorkControllerThread>();
    

    public NetworkController() {
        super();
        
        boolean listening = true;
        try {
            serverSocket = new ServerSocket(1992);
        } catch (IOException e) {
            System.err.println("Error en el puerto: 1992.");
            return;
        }
        try {
            while (listening) {
                NetWorkControllerThread t = new NetWorkControllerThread(serverSocket.accept());
                t.start();
                netControllers.add(t);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }



    private static class NetworkControllerHolder{
        private static final NetworkController INSTANCE = new NetworkController();
    }
}
