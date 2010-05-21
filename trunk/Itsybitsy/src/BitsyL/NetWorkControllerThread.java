package BitsyL;

import java.io.*;
import java.net.*;

import java.util.Date;

public abstract class NetWorkControllerThread extends Thread {
    
    Socket socket;
    public static final int DELAY = 50;
    
    protected NetWorkControllerThread(Socket servSocket) {
        super();
        socket = servSocket;
    }
    
    PrintWriter out;
    InputStream input;
    BufferedReader in;
    
    public void init(){
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            input = socket.getInputStream();
            in = new BufferedReader(new InputStreamReader(input),1); //eso hace abstrae el inputreader, sin hacer sufrir los /n
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void end(){
        try {
            in.close();
            input.close();
            out.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public abstract void run();
    
    public InetAddress getCliente(){
        return socket.getInetAddress();
    }
    
}
