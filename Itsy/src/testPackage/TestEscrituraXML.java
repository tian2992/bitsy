package testPackage;

import ItsyL.CACliente;

import java.io.FileWriter;

import java.net.InetAddress;

public class TestEscrituraXML {
    public TestEscrituraXML() {

    }

    public static void main(String[] args) {

        CACliente cliente = new CACliente();

        String host = "";

        String ip = "";

        try {

            InetAddress addr = InetAddress.getLocalHost();

            host = addr.getHostName();

            ip = addr.getHostAddress();

        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }

        try {
            
            FileWriter f = new FileWriter("C:\\Users\\Black Empires\\Downloads\\prueba.xml");
            
            f.write(cliente.escrituraXML( host , ip ,
                                                "C:\\Users\\Black Empires\\Downloads"));
            
            f.close();
            
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
        
    }
}
