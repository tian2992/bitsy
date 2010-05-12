package ItsyL;

import java.io.File;

import java.text.SimpleDateFormat;

import java.util.Date;

public class CACliente {
    public CACliente() {

    }

    public String escrituraXML(String pIdCliente, String pIp,
                               String pCarpetaRaiz) {

        StringBuilder b = new StringBuilder();

        b.append("<CACliente ");
        
        File f = new File( pCarpetaRaiz ) ;
        
        b.append("id=\""+ pIdCliente + "\" ip=\"" + pIp + "\" raiz=\"" + f.getName() + "\"" );

        b.append(">\n");

        if ( f.isDirectory() ) {

            String[] contenido = f.list();

            for (int i = 0; i < contenido.length; i++) { //para cada elemento dentro de esta carpeta

                String actual = f.getAbsolutePath() + f.separator + contenido[i];

                File temp = new File(actual);

                if (temp.isDirectory()) { //si es directorio, bajar otra vez
                    b.append(getXMLDeDirectorio(actual));
                } else { 
                    b.append(getXMLDeArchivo(actual)); //si es archivo agregarlo 
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

            for (int i = 0; i < contenido.length; i++) { //para cada elemento dentro de esta carpeta

                String actual = f.getAbsolutePath() + f.separator + contenido[i];

                File temp = new File(actual);

                if (temp.isDirectory()) { //si es directorio, bajar otra vez
                    b.append(getXMLDeDirectorio(actual));
                } else { 
                    b.append(getXMLDeArchivo(actual)); //si es archivo agregarlo 
                }
            }
        }


        b.append("</carpeta>\n");

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
            // TODO: Add catch code
            //e.printStackTrace();
        } 
        
        
        
        
        b.append("nombre=\"" + nombre + "\" size=\"" + f.length() + "\" fecha_modificacion=\"" + fecha_s + "\" extension=\"" + extension + "\""   );                                                       
        
        
        b.append(">\n");

        b.append("</archivo>\n");

        return b.toString();
    }
}
