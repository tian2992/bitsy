package BitsyL;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import java.net.Socket;

public class GetIndex extends NetWorkControllerThread {
    
    File index;

    /**
     * Crea GI con la string del objetivo
     * @param sSock
     * @param sTring
     */
    public GetIndex(Socket sSock, String sTring){
        super(sSock);
        index = new File(sTring);
    }
    
    /**
     *Crea un GI con string y el archivo donde meter el resultado
     * @param sSock
     * @param f
     */
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
            out.println("dameIndice");
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

    public File getIndex() {
        return index;
    }
}
