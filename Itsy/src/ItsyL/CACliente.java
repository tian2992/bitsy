package ItsyL;

import java.io.File;

import java.io.FileWriter;

import java.net.InetAddress;

import java.text.SimpleDateFormat;

import java.util.Date;

public class CACliente {  
    
   private String pHost = "";   
   private String pIp = "";
   
    public CACliente() {    
 
         try {

            InetAddress addr = InetAddress.getLocalHost();

            pHost = addr.getHostName();

            pIp = addr.getHostAddress();

        } catch (Exception e) {
        
            e.printStackTrace();
        }

    }  
    
    /**
     * Escribe en un archivo de texto el xml generado a partir de la carpeta a compartir seleccionada.
     * @param path de carpeta, path de destino
     */
    public File escribirXML(String pPath, String pathDestino){
        
        File fi = new File (pathDestino);
        
        FileWriter f;
        
        try {
            
            f = new FileWriter(fi);
            
            f.write(escrituraXML(pHost,pIp,pPath));
            
            f.close();
            
        } catch (Exception e) {
          
            e.printStackTrace();
        }
        return fi ;
    }

    public String escrituraXML(String pIdCliente, String pIp,
                               String pCarpetaRaiz) {

        StringBuilder b = new StringBuilder();

        b.append("<CACliente ");
        
        File f = new File( pCarpetaRaiz ) ;
        
        b.append("id=\""+ pIdCliente + "\" ip=\"" + pIp + "\" raiz=\"" + f.getName() + "\"" );

        b.append(">\n\n\n\n");

        if ( f.isDirectory() ) {

            String[] contenido = f.list();

            for (int i = 0; i < contenido.length; i++) { //para cada elemento dentro de esta carpeta

                String actual = f.getAbsolutePath() + f.separator + contenido[i];

                File temp = new File(actual);
                if (temp.canRead()){
                    if (temp.isDirectory()) { //si es directorio, bajar otra vez
                        b.append(getXMLDeDirectorio(actual));
                    } else { 
                        b.append(getXMLDeArchivo(actual)); //si es archivo agregarlo 
                    }
                }
            }
        }


        b.append("</CACliente>");

        return b.toString();
    }

    public String getXMLDeDirectorio(String pPath) {

        StringBuilder b = new StringBuilder();

        b.append("<carpeta ");

        File f = new File(pPath);

        if (f.isDirectory()) {

            String nombre = f.getName();

            b.append("nombre=\"" + nombre + "\"");

        }


        b.append(">\n");

        if ( f.isDirectory() ) {

            String[] contenido = f.list();
            
            if( contenido == null ) {
                return ""; //carpeta vacia
            }

            for (int i = 0; i < contenido.length; i++) { //para cada elemento dentro de esta carpeta

                String actual = f.getAbsolutePath() + f.separator + contenido[i];

                File temp = new File(actual);
                
                if (temp.canRead()){ //Evita que haya un bloqueo al no poder leer
                    if (temp.isDirectory()) { //si es directorio, bajar otra vez
                        b.append(getXMLDeDirectorio(actual));
                    } else { 
                        b.append(getXMLDeArchivo(actual)); //si es archivo agregarlo 
                    }
                }
            }
        }


        b.append("</carpeta>\n\n\n");

        return b.toString();

    }

    public String getXMLDeArchivo(String pPath) {

        StringBuilder b = new StringBuilder();

        b.append("<archivo ");

        File f = new File( pPath ); 
        
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        
        Date fecha = new Date( f.lastModified() );
        
        String fecha_s = formatter.format(fecha);
        
        String nombre = f.getName();
       
        String extension = "";
        
        try {
        
            int posicionDelPunto = nombre.lastIndexOf(".");
        
            extension = nombre.substring(posicionDelPunto+1);
            
        } catch (Exception e) {
         
        }       
        
        
        b.append("nombre=\"" + nombre + "\" size=\"" + f.length() + "\" fecha_modificacion=\"" + fecha_s + "\" extension=\"" + extension + "\""   );                                                       
        
        
        b.append(">\n");

        b.append("</archivo>\n\n");

        return b.toString();
    }

    public void setPHost(String pHost) {
        this.pHost = pHost;
    }

    public String getPHost() {
        return pHost;
    }

    public void setPIp(String pIp) {
        this.pIp = pIp;
    }

    public String getPIp() {
        return pIp;
    }
}
