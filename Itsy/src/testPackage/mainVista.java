package testPackage;

import UIVista.*;

public class mainVista {
    public mainVista() {

    }
    
    public static void main(String[] args) {
        
        ItsyVista app = new ItsyVista();
        
        System.out.println("Ultimo Commit Bueno, porfin!");
        
        try {
            app.setVisible(true);
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
        
    }
}
