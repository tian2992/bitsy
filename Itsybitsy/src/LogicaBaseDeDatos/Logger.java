package LogicaBaseDeDatos;

import java.sql.*;

/**
 * Logger de los eventos
 * <code>
 *  Logger log = Logger.getInstance();
 *  log.logData("algo paso");
 * </code>
 */
public class Logger {

    Connection conexion;
    /**
     * Asi el constructor solo puede ser llamado de su padre
     */
    private Logger() {
        conexion = Herramientas.connectionFactory(); //pido una conexion nueva para mantener threadsafe el logger
        //TODO: otras inicializaciones
    }
    
    public void logData(String message){
        
        //TODO: hacer el log
    }


    public static Logger getInstance() {
        return LoggerHolder.INSTANCE;
    }
    
    /**
     * asi solo carga el LoggerHolder cuando es necesitado y lo utiliza unicamente una vez
     * inicializando hasta que es necesario
     */
    private static class LoggerHolder {
        private static final Logger INSTANCE = new Logger();
    }
}


