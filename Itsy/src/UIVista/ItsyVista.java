package UIVista;

import ItsyL.CACliente;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import java.awt.Font;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;

import java.net.URL;

import javax.imageio.ImageIO;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

public class ItsyVista extends JFrame {
    private JPanel FPrincipal = new JPanel();
    private JButton btnCompartir = new JButton();
    private JButton btnCerrar = new JButton();
    private JButton btnAyuda = new JButton();
    private JButton btnCopiar = new JButton();
    private JButton btnPegar = new JButton();
    private JButton btnCortar = new JButton();
    private JButton btnTransferir = new JButton();
    private JLabel lbIp = new JLabel();
    private JLabel lbHost = new JLabel();
    private JPanel toolBar = new JPanel();
    private JPanel FIngreso = new JPanel();
    private JPanel Animacion = new JPanel();
    private JLabel instrucciones = new JLabel();
    private JTextArea ingresoDireccion = new JTextArea();
    private JButton btnBusquedaCarpeta = new JButton();
    private JLabel vacio = new JLabel();


    public ItsyVista() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {

        this.getContentPane().setLayout(null);
        this.setSize(new Dimension(743, 463));

        //ingresoDireccion.setEditable(true);
        ingresoDireccion.setAlignmentX(ingresoDireccion.LEFT_ALIGNMENT);
        
        btnTransferir.setVisible(false);
                        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setTitle("Itsy Application Desktop");
        FPrincipal.setBounds(new Rectangle(0, 0, 745, 440));

        FIngreso.setSize(new Dimension(1042, 65));

        instrucciones.setText("Ingrese el path de la carpeta que desea compartir:  ");
        instrucciones.setFont(new Font("Gill Sans MT", 0, 14));

        ingresoDireccion.setBackground(new Color(255, 239, 214));
        ingresoDireccion.setBounds(new Rectangle(516, 40, 5, 23));
        ingresoDireccion.setFont(new Font("Calibri", 0, 14));
        ingresoDireccion.setMinimumSize(new Dimension(40, 40));
        ingresoDireccion.setSelectionColor(new Color(165, 165, 0));
        ingresoDireccion.setSize(new Dimension(50, 50));
        ingresoDireccion.setMargin(new Insets(5, 5, 5, 5));

        ingresoDireccion.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        ingresoDireccion.setPreferredSize(new Dimension(300, 30));
        ingresoDireccion.setText("");

        btnBusquedaCarpeta.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnBusquedaCarpeta_actionPerformed(e);
                }
            });
        this.add(toolBar, BorderLayout.NORTH);
        this.add(FIngreso, BorderLayout.CENTER);
        this.add(Animacion,BorderLayout.SOUTH);

        btnCompartir.setText("Compartir");
        btnCompartir.setFont(new Font("Tahoma", 0, 10));
        btnCompartir.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnCompartir_actionPerformed(e);
                }
            });
        btnCortar.setText("Cortar");
        btnCortar.setFont(new Font("Tahoma", 0, 10));
        btnCortar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnCortar_actionPerformed(e);
                }
            });
        btnCopiar.setText("Copiar");
        btnCopiar.setFont(new Font("Tahoma", 0, 10));
        btnCopiar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnCopiar_actionPerformed(e);
                }
            });
        btnPegar.setText("Pegar");
        btnPegar.setFont(new Font("Tahoma", 0, 10));
        btnPegar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnPegar_actionPerformed(e);
                }
            });
        btnCerrar.setText("Cerrar");
        btnCerrar.setFont(new Font("Tahoma", 0, 10));
        btnCerrar.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnCerrar_actionPerformed(e);
                }
            });
        btnAyuda.setText("Ayuda");

        btnAyuda.setFont(new Font("Tahoma", 0, 10));
        try {

            URL imagen = ItsyVista.class.getResource("imagenes/imgFondo.jpg");
            InputStream stream = imagen.openStream();
            BufferedImage tmpImagen = ImageIO.read(stream);

            BgBorder fondo = new BgBorder(tmpImagen);

            FPrincipal.setBorder(fondo);

            btnCompartir.setIcon(GestorImagenes.cargarIcono("imagenes/imgCompartir.png",
                                                            45, 45));
            btnCerrar.setIcon(GestorImagenes.cargarIcono("imagenes/imgCerrar.png",
                                                         45, 45));
            btnAyuda.setIcon(GestorImagenes.cargarIcono("imagenes/imgAyuda.png",
                                                        45, 45));
            
            btnCopiar.setIcon(GestorImagenes.cargarIcono("imagenes/imgCopy.png",
                                                            45, 45));
            btnPegar.setIcon(GestorImagenes.cargarIcono("imagenes/imgPaste.png",
                                                         45, 45));
            btnCortar.setIcon(GestorImagenes.cargarIcono("imagenes/imgCut.png",
                                                        45, 45));
            
            btnTransferir.setIcon(GestorImagenes.cargarIcono("imagenes/send.gif", 200, 100));
            
            btnBusquedaCarpeta.setIcon(GestorImagenes.cargarIcono("imagenes/imgDirectorio.png", 20, 20));

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
        }

        JLabel vacio = new JLabel();

        vacio.setPreferredSize(new Dimension(20, 1));

        FIngreso.add(vacio, BorderLayout.EAST);

        vacio = new JLabel();

        vacio.setPreferredSize(new Dimension(5, 1));

        FIngreso.add(vacio, BorderLayout.WEST);

        vacio = new JLabel();

        vacio.setPreferredSize(new Dimension(1, 100));

        FIngreso.add(vacio, BorderLayout.NORTH);

        vacio = new JLabel();

        vacio.setPreferredSize(new Dimension(1, 50));

        FIngreso.add(vacio, BorderLayout.SOUTH);


        toolBar.add(btnCompartir, null);
        toolBar.add(btnCortar, null);
        toolBar.add(btnCopiar, null);
        toolBar.add(btnPegar, null);
        toolBar.add(btnCerrar, null);
        toolBar.add(btnAyuda, null);

        FIngreso.add(instrucciones, null);
        FIngreso.add(ingresoDireccion, null);
        FIngreso.add(btnBusquedaCarpeta,null);
        
        Animacion.add(lbIp,null);
        Animacion.add(vacio,null);
        Animacion.add(lbHost,null);
        Animacion.add(btnTransferir,null);
        
    
        this.getContentPane().add(FPrincipal, null);
        FPrincipal.add(toolBar, null);
        FPrincipal.add(FIngreso, null);
        FPrincipal.add(Animacion,null);
        
        this.setResizable(false);
    }

    private void btnCerrar_actionPerformed(ActionEvent e) {

        System.exit(-1);

    }

    private void btnCortar_actionPerformed(ActionEvent e) {
        
        ingresoDireccion.cut();
    }

    private void btnCompartir_actionPerformed(ActionEvent e) {     
        
        if (ingresoDireccion.getText()!=null && ingresoDireccion.getText().length() != 0) {
       
        btnTransferir.setVisible(true);
        
        String pPath = ingresoDireccion.getText();
        
        CACliente cliente = new CACliente ();
        
        cliente.escribirXML(pPath, "cliente1.xml");
        
        //Metodo para transferir por socket pendiente 
        
        JOptionPane.showMessageDialog(null, "Archivo Transferido Correctamente");
        
        lbIp.setText("Nombre del Equipo: "+cliente.getPHost());
        vacio.setText("");
        lbHost.setText("Direccion IP: "+cliente.getPIp());
        
        btnTransferir.setVisible(false);
        
        }else{
            
            JOptionPane.showMessageDialog(null, "Ingrese una direcci�n en el cuadro de texto");
        }
        
    }

    private void btnCopiar_actionPerformed(ActionEvent e) {
        
        ingresoDireccion.copy();
    }

    private void btnPegar_actionPerformed(ActionEvent e) {
        
        ingresoDireccion.paste();
    }

    private void btnBusquedaCarpeta_actionPerformed(ActionEvent e) {
        
        try {
            
            JFileChooser f = new JFileChooser();
            
            f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            
            if( f.showOpenDialog(this) == JFileChooser.APPROVE_OPTION ) {
                
                File seleccionado = f.getSelectedFile();
                
                ingresoDireccion.setText( seleccionado.getAbsolutePath() );
                
            }
                
            
        } catch (Exception e1) {
            // TODO: Add catch code
            e1.printStackTrace();
        }
    }
}
