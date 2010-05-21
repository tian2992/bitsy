package BitsyL;


import java.io.Serializable;

import java.util.Date;

public class Item implements Serializable {

    private String nombre;
    private Date fechaModificacion;
    private String size;
    private String extension;
    private String pathCompleto;
    boolean carpeta;
    private String nombreCliente;


    public Item() {
        super();
    }

  public String toString()
  {
    StringBuilder b = new StringBuilder();
    b.append("ITEM[");
    b.append("Nombre:" + nombre);
    b.append(", Cliente:" + nombreCliente );
    b.append(", pathCompleto:" + pathCompleto);
    b.append("]");
    return b.toString();
  }

    //Setters y Getters Automatico

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
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

  public void setFechaModificacion(Date fechaModificacion)
  {
    this.fechaModificacion = fechaModificacion;
  }

  public Date getFechaModificacion()
  {
    return fechaModificacion;
  }

  public void setSize(String size)
  {
    this.size = size;
  }

  public String getSize()
  {
    return size;
  }

  public void setPathCompleto(String pathCompleto)
  {
    this.pathCompleto = pathCompleto;
  }

  public String getPathCompleto()
  {
    return pathCompleto;
  }

  public void setNombreCliente(String nombreCliente)
  {
    this.nombreCliente = nombreCliente;
  }

  public String getNombreCliente()
  {
    return nombreCliente;
  }
}
