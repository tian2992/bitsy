package testPackage;

import BitsyL.*;
public class xml {
    public xml() {

    }
    
    public static void main(String[] args) {
        
        System.out.println("main");
        
        ManejoXML manejo = new ManejoXML();
        
        manejo.procesarXML("C:\\JDeveloper\\mywork\\pro\\Archivos de Prueba\\cliente1.txt");
        
    }
    
}
