package BitsyL;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import java.net.Socket;

public class GetFile  extends NetWorkControllerThread {
    
    File file;
    String fileName;
    
    public GetFile(Socket sSock, File f, String name){
        super(sSock);
        file = f;
        fileName = name;
    }
    
    public void run(){
        init();
        pedirIndice(file);
        end();
    }

    public File pedirIndice(File f) {
        String inputLine;

        try {
            BufferedOutputStream bos;
            out.println("dameArchivo");
            System.out.println("dameArchivo");
            out.println(fileName);
            System.out.println(fileName);
            Thread.sleep(DELAY);
            inputLine = in.readLine();
            while (!inputLine.equals("inicioEnvio")&&!inputLine.equals("error")){ //consumimos las lineas hasta que inicie envio
                inputLine = in.readLine();
            }
            //algo fallo
            if (inputLine.equals("error")){
                return null;
            }
            
            //final long tamaArchivo = Long.parseLong(in.readLine());
            //byte[] receivedData = new byte[tamaArchivo];
            FileOutputStream filo = new FileOutputStream(f);
            bos = new BufferedOutputStream(filo);

            int bitEntrada = 0;

            long recibido = 0;
            System.out.print("recibiendo data");
            
            while (bitEntrada < 128) {
                
                bitEntrada = in.read();
                //System.out.println(bitEntrada);
                if ((bitEntrada < 128)) {
                    bos.write(bitEntrada);
                }
                recibido++;
            }
            
            System.out.print("data recibida");
            
            bos.close();
            filo.close();

            Thread.sleep(DELAY);
        
            inputLine = in.readLine();
            while (!(inputLine.equals("listo"))) {
                inputLine = in.readLine();
            }
            
            //termino la lectura
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    public File getFile() {
        return file;
    }
}
