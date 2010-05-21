package BitsyL;

import java.io.BufferedOutputStream;

import java.net.Socket;

import listaEnlazada.ListaEnlazada;

public class FindFile extends NetWorkControllerThread {
    
    String dataString;
    
    ListaEnlazada<Item> lis = new ListaEnlazada<Item>();
    
    
    public FindFile(Socket sSock, String sTring){
        super(sSock);
        dataString = sTring;
    }
    
    public void run(){
        init();
        fetchList(dataString);
        end();
    }
    
    public ListaEnlazada<Item> fetchList(String s){
        String inputLine;
        try {
            out.println("buscaArchivo");
            System.out.println("buscaArchivo");
            Thread.sleep(DELAY);
            inputLine = in.readLine();
            while (!inputLine.equals("inicioEnvio")){ //consumimos las lineas hasta que inicie envio
                inputLine = in.readLine();
            }
            
            Item i;
            
            do { 
                i = new Item();
                inputLine = in.readLine(); // jalamos el nombre
                i.setNombre(inputLine);
                System.out.println("nombre:"+inputLine);
                inputLine = in.readLine();
                i.setPathCompleto(inputLine);
                System.out.println("path:"+inputLine);
                i.setNombreCliente(socket.getInetAddress().getHostName());
                lis.add(i);
            } while (!(inputLine.equals("listo")));
            
            
        } 
        catch (Exception e){
            e.printStackTrace();
        }
        
        return lis;
    }

    public ListaEnlazada<Item> getLis() {
        return lis;
    }
}
