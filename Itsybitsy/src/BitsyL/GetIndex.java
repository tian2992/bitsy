package BitsyL;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import java.net.Socket;

public class GetIndex extends NetWorkControllerThread {
    
    File index;
    
    public GetIndex(Socket sSock, String sTring){
        super(sSock);
        index = new File(sTring);
    }
    public GetIndex(Socket sSock, File f){
        super(sSock);
        index = f;
    }
    
    public void run(){
        init();
        index = pedirIndice(index);
        end();
    }

    public File pedirIndice(File f) {
        String inputLine;

        try {
            BufferedOutputStream bos;
            inputLine = in.readLine();
            out.println("dameIndice");
            Thread.sleep(DELAY);
            inputLine = in.readLine();
            while (!inputLine.equals("inicioEnvio")){ //consumimos las lineas hasta que inicie envio
                inputLine = in.readLine();
            }
        
            final long tamaArchivo = Long.parseLong(in.readLine());
            byte[] receivedData = new byte[tamaArchivo];
            FileOutputStream filo = new FileOutputStream(f);
            bos = new BufferedOutputStream(filo);

            int bitEntrada;

            long recibido = 0;

            while (input.available() < 0 && recibido < tamaArchivo + 2) {
                bitEntrada = input.read();
                if (bitEntrada != -1) {
                    bos.write(bitEntrada);
                }
            }

            bos.close();
            filo.close();

            Thread.sleep(DELAY);
        
            inputLine = in.readLine();
            while (!(inputLine.equals("listo"))) {
                inputLine = in.readLine();
            }
            
            //termino la lectura
        } catch (Exception e) {
            return f;
        }
        return f;
    }

    public File getIndex() {
        return index;
    }
}
