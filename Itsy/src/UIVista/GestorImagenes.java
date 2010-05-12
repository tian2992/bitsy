package UIVista;

import javax.swing.ImageIcon;

import java.net.URL;

import javax.swing.ImageIcon;

public class GestorImagenes {
    public GestorImagenes() {

    }


    public static ImageIcon cargarIcono(String pPath, int pAncho, int pAlto) {

        try {

            URL iconoURL = ItsyVista.class.getResource(pPath);

            ImageIcon iconoOriginal = new ImageIcon(iconoURL);

            ImageIcon iconoEscala =
                new ImageIcon(iconoOriginal.getImage().getScaledInstance(pAncho,
                                                                         pAlto,
                                                                         java.awt.Image.SCALE_DEFAULT));

            return iconoEscala;

        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }

        return new ImageIcon(""); //si falla, cargar un imageIcon sin nada

    }


}

