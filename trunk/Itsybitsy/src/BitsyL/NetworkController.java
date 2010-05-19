package BitsyL;

import LogicaBaseDeDatos.*;
import java.io.*;
import java.net.*;

import listaEnlazada.ListaEnlazada;


/**
 * Primero se obtiene la instancia del Network Controller, con NetworkController.getInstance
 * luego se le da init();
 * luego se pide el socket (con getSocket(InetAddress) o con List &lt;Socket&gt; getSockets)
 * luego se crea un objeto de tipo GetIndex o cualquiera de las acciones Thread
 * 
 * se da un {nombreDelObjetoThread}.join(200) o una cantidad de milisegundos para que responda
 * y se obtiene del objeto el resultado depende del objeto
 * 
 */
public class NetworkController implements Runnable{

    ServerSocket serverSocket = null;
    PrintWriter out = null;
    BufferedReader in = null;

    boolean listening = true;
    static boolean iniciado = false;
    
    ListaEnlazada<Socket> sockets = new ListaEnlazada<Socket>();


    /**
     * @return
     */
    public static boolean init(){
        if (iniciado){
            return true;
        }
        else{
            NetworkController.getInstance().run();
            iniciado = true;
            return false;
        }
    }

    public NetworkController() {
        super();
    }
    
    public void run(){
        try {
            serverSocket = new ServerSocket(1992);
        } catch (IOException e) {
            System.err.println("Error en el puerto: 1992.");
            return;
        }
        try {
            while (listening) {
                sockets.add(serverSocket.accept());
            }
            
            serverSocket.close();
            
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public Socket getSocket(InetAddress ia){
        for (Socket s: sockets){
            if (s.getInetAddress().equals(ia))
                return s;
        }
        return null;
    }
    
    
    
    public static NetworkController getInstance() {
        return NetworkControllerHolder.INSTANCE;
    }

    public void setSockets(ListaEnlazada<Socket> sockets) {
        this.sockets = sockets;
    }

    public ListaEnlazada<Socket> getSockets() {
        return sockets;
    }

    private static class NetworkControllerHolder{
        private static final NetworkController INSTANCE = new NetworkController();
    }
}
