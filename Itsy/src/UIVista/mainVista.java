package UIVista;

public class mainVista
{
  public mainVista()
  {
    
  }
  public static void main(String[] args) {
         
         ItsyVista app = new ItsyVista();
         
         
         /**
          * Aplicacion de escritorio este es el main de Itsy :P el papa yeah!
          */
         try {
             app.setVisible(true);
         } catch (Exception e) {
             // TODO: Add catch code
             e.printStackTrace();
         }
         
     }
  }
