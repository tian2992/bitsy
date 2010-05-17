package ItsyL;

import java.io.File;
import java.io.Serializable;

import java.util.Date;

public class Item implements Serializable {

    private String ruta;
    private File archivo;

    public Item() {
        super();
    }
    
    public File getArchivo(){
        if (archivo == null){
            archivo = new File(ruta);
            return archivo;
        }
        else {
            return archivo;
        }
    }

    //Setters y Getters Automatico


    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getRuta() {
        return ruta;
    }
}
