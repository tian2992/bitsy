package ItsyL;

import java.io.*;
import java.net.*;

public class NetworkController{
    
    Socket socket;
    NetworkControllerThread n;
    
    int p;
    boolean ini = false;
    
    public boolean init(String in,int pt){
        if (ini==true){
            return true;
        }
        try {
            socket = new Socket(in,pt);
            n = new NetworkControllerThread(socket);
            n.start();
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    
    private NetworkController(){

        
    }
    
    
    public static NetworkController getInstance() {
        return NetworkControllerHolder.INSTANCE;
    }
    
    private static class NetworkControllerHolder{
        private static final NetworkController INSTANCE = new NetworkController();
    }
    
}
