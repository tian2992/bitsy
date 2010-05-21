package LogicaBaseDeDatos;

import java.io.Serializable;

public class Usuario implements Serializable {
    public Usuario() {
        super();
    }
    
    String username;
    String password;
    boolean valido = false;
    
    


    public void setUsername(String username) {
        this.username = username;
        setValido(true);
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
        setValido(true);
    }

    public String getPassword() {
        return password;
    }

    public void setValido(boolean valido) {
        this.valido = valido;
    }

    public boolean isValido() {
        return valido;
    }
}
