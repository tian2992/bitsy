package BitsyL;

public class DatosCliente {
    public DatosCliente() {

    }

    private String id = "";
    private String ip = "";
    private String enLinea = "";

    private boolean estaEnLinea = false;

    public String toString() {
        StringBuilder b = new StringBuilder();

        b.append("<CACliente id = \" " + this.getId() + " \" ip= \" " +
                 this.getIp() + " \" archivo= \" " + this.getId() + ".clt" +
                 " \" enlinea= \" " + " \" ");

        if (estaEnLinea == false) {

            b.append("no" + " \" ");
        } else {

            b.append("si" + " \" ");
        }

        return b.toString();
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIp() {
        return ip;
    }

    public void setEnLinea(String enLinea) {
        this.enLinea = enLinea;
    }

    public String getEnLinea() {
        return enLinea;
    }

    public void setEstaEnLinea(boolean estaEnLinea) {
        this.estaEnLinea = estaEnLinea;
    }

    public boolean isEstaEnLinea() {
        return estaEnLinea;
    }
}
