package LogicaBaseDeDatos;

import java.sql.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Herramientas {
    public Herramientas() {

    }


    private static Connection mConexion = null;
    private static final String USERNAME = "root";
    private static final String PASS = "witchy";
    private static final String ROUTE = "jdbc:mysql://localhost/Pro3Edd";
    
    
    /**
     * Crea una nueva conexion al ser llamado
     * @return una nueva conexion
     */
    public static Connection connectionFactory(){
        try {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        //creamos la conexion
        Connection conn =
            DriverManager.getConnection(ROUTE,
                                        //nombre base de datos
                USERNAME, //usuario
                PASS); //contrase�a
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Retorna una conexión unica compartida
     * @return conexion
     */
    public static Connection getConnection() {

        if (mConexion != null) { //si la conexion ya existe, NO crear una nueva
            return mConexion;
        }

        //si la conexion no existe, crearla
        return connectionFactory();
    }

    /**
     * Verificar si el correo ingresado es correcto 
     */
    public static boolean isEmail(String correo) {

        Pattern pat = null;
        Matcher mat = null;
        pat = Pattern.compile("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$");
        mat = pat.matcher(correo);
        if (mat.find()) {
            return true;
        } else {
            return false;
        }
    }
}
