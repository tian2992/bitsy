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
            System.out.println("buscaArchivo :"+s);
            out.println(s);
            inputLine = in.readLine();
            Thread.sleep(DELAY);
            while (!inputLine.equals("inicioEnvio")){ //consumimos las lineas hasta que inicie envio
                System.out.println(inputLine);
                inputLine = in.readLine();
                
            }
            System.out.println("transfiriendo");
            Item i;
            
            do { 
                i = new Item();
                inputLine = in.readLine(); // jalamos el nombre
                i.setPathCompleto(inputLine);
                System.out.println("path:"+inputLine);
                lis.add(i);
            } while (!(inputLine.equals("listo")));
            
            System.out.println("se resulto en "+lis);
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
