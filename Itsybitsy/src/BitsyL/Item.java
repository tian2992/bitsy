package BitsyL;

import java.io.Serializable;

import java.util.Date;

public class Item implements Serializable {

    private String nombre;
    private Date fechaModificacion;
    private int tama�o;
    private String extension;
    boolean carpeta;


    public Item() {
        super();
    }


    //Setters y Getters Automatico

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setTama�o(int tama�o) {
        this.tama�o = tama�o;
    }

    public int getTama�o() {
        return tama�o;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }

    public void setCarpeta(boolean carpeta) {
        this.carpeta = carpeta;
    }

    public boolean isCarpeta() {
        return carpeta;
    }
}
