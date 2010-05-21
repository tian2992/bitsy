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
            out.println("dameIndice");
            Thread.sleep(DELAY);
            inputLine = in.readLine();
            while (!inputLine.equals("inicioEnvio")&&!inputLine.equals("error")){ //consumimos las lineas hasta que inicie envio
                inputLine = in.readLine();
            }
            //algo fallo
            if (inputLine.equals("error")){
                return lis;
            }
            
            Item i;
            
            while (!inputLine.equals("listo")){ 
                i = new Item();
                inputLine = in.readLine(); // jalamos el nombre
                i.setNombre(inputLine);
                inputLine = in.readLine();
                i.setPathCompleto(inputLine);
                i.setNombreCliente(socket.getInetAddress().getHostName());
                lis.add(i);
            }
            
            
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
