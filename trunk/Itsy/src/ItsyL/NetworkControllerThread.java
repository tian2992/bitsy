package ItsyL;

import UIVista.ItsyVista;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.net.Socket;

public class NetworkControllerThread extends Thread {

    public NetworkControllerThread(Socket s) throws Exception {
        super();
        socket = s;
        out = new PrintWriter(socket.getOutputStream());
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()),1);
        
    }
    
    Socket socket;
    PrintWriter out;
    BufferedReader in;
    String inputLine;
    
    
    public void sendFile(File file) throws IOException, InterruptedException{
        out.println("inicioEnvio");
        
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));   
        int in = 0;
        
        while (in != -1){
            
            in = bis.read();
            out.write(in);  
            //System.out.println(in);
        }
        out.write(-1);        
        out.flush();
    }
    
    public void run(){
        try {
            while (!(inputLine = in.readLine()).equals("bye")) {
                System.out.println(inputLine);
                if (inputLine.equals("dameIndice")){
                    //File f = new File("/tmp/blagg.txt");
                    File f = ItsyVista.file; //aca debe ir el archivo xml a enviar
                    sendFile(f);
                    out.println("listo");
                } //fin dameIndice
                //Terrible error de seguridad, pero que se le hace...
                if (inputLine.equals("dameArchivo")){ //pide archivos a gusto
                    inputLine = in.readLine();
                    File f;
                    try {
                        f = new File(inputLine);
                        if (!f.canRead()){
                            out.println("error");
                            continue;
                        }
                    } catch (Exception e){
                        out.println("error");
                        continue;
                    }
                    sendFile(f);
                    out.println("listo");
                    
                } // fin dameArchivo

            }
            
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
