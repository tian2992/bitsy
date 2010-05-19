package testPackage;

import BitsyL.GetIndex;
import BitsyL.NetworkController;

import java.io.File;

import java.net.Socket;

import java.util.List;

public class IndexRecieveTest {
    public IndexRecieveTest() {
        super();
    }

    public static void main(String[] args) throws Exception {
        //================ Esto solo se hace una vez
        
        //Se llama la instancia
        NetworkController n = NetworkController.getInstance();
        n.init(); //se puede llamar init muchas veces, no hay clavo
        System.out.println("Iniciado");
        
        
        //================ Aqui se escoje el cliente a llamar
        
        //esto es parte del demo, se toma un socket por indice
        Thread.sleep(5000);
        Socket coneccion;
        List<Socket> lis = n.getSockets(); //toma el listado entero de sockets
        coneccion = lis.get(0); //toma el primer socket
        //eso no se haria en la vida real, se tomaria el socket en base a la IP o
        //se tomaria el listado y se efectuaria la accion en todos
        System.out.println(coneccion); 
        
        
        // ======================= cuando se quiere usar
        //crea el GetIndex, con el archivo objetivo
        //
        
        GetIndex gi = new GetIndex(coneccion, new File("/tmp/timpi.txt"));
        gi.start(); //Inicia el thread
        
        gi.join(); //espera hasta el final de la ejecucion del thread para obtener resultados
        
        File filo = gi.getIndex(); //archivo resultante
        
        System.out.println(filo.length());
        
        //fuerza la parada de todo
        gi.stop();
        n.stop();        
        
        // ===================se usan los resultados
        
    }
}
