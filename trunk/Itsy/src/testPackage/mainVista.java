package testPackage;

import UIVista.*;

public class mainVista {
    public mainVista() {

    }
    
    public static void main(String[] args) {
        
        ItsyVista app = new ItsyVista();
        
        try {
            app.setVisible(true);
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }
        
    }
}
