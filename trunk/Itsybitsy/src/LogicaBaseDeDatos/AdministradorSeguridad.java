package LogicaBaseDeDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AdministradorSeguridad {
    public AdministradorSeguridad() {

    }

    /** Obtener el id del usuario, a partir del correo (usuario)
     *
     * @param pCorreo
     * @return int
     */
    public int getIdUsuario(String pCorreo) {

        int codigo = 0;

        try {
            
            //Establecer conexion
            Connection conn = Herramientas.getConnection();
            Statement st = conn.createStatement();
            
            //Consultar a mysql
            ResultSet rs =
                st.executeQuery("SELECT idUsuario FROM usuario " + "WHERE correo = '" +
                                pCorreo + "'");


            if (rs.next()) {

                codigo = rs.getInt(1);

            }

        } catch (Exception e) {

            e.printStackTrace();
        }


        return codigo;

    }

     /**
     *Validar si el usuario (por medio del correo) ya se registro (registroUsuario.jsp)
     * @param pCorreo
     * @return
     */
 public boolean yaSeRegistro(String pCorreo) {

        boolean resultado = false;

        try {
            
            //Establecer conexion
            Connection conn = Herramientas.getConnection();

            if (conn != null) { 

                System.out.println("Conexi�n satisfactoria");

                Statement st = conn.createStatement();

                //Consultar a mysql
                ResultSet rs =
                    st.executeQuery("SELECT * FROM usuario WHERE correo = '" +
                                    pCorreo + "'");

                if (rs.next()) {
                    resultado = true;
                } else
                    resultado = false;

            }
        } catch (Exception ex) {

            ex.printStackTrace();

        }


        return resultado;

    }
     
     /**
     * Verificar si el usuario existe por medio de los parametros correo  y contrase�a (login.jsp)
     * @param pCorreo
     * @param pPass
     * @return existe
     */

    public boolean existe(String pCorreo, String pPass) {

        boolean existe = false;

        try {

            //Establecer conexion
            Connection conn = Herramientas.getConnection();

            if (conn != null) {

                System.out.println("Conexi�n satisfactoria");

                Statement st = conn.createStatement();

                //Consultar a mysql
                ResultSet rs =
                    st.executeQuery("SELECT * FROM usuario WHERE correo = '" +
                                    pCorreo + "'" + "AND pass = '" + pPass +
                                    "'" );

                if (rs.next()) {
                    existe = true;
                } else
                    existe = false;

            }
        } catch (Exception ex) {

            ex.printStackTrace();
        }

        System.out.println(existe);

        return existe;
    }

    public void agregarUsuario(String pCorreo, String pPassword,
                               String pNombres, String pApellidos,
                               String pFechaNacimiento) {

        try {

            //Establecer conexion
            Connection conn = Herramientas.getConnection();

            if (conn != null) { 

                System.out.println("Conexi�n satisfactoria");

                int rows_updated = 0;
                
                //Consultar a mysql
                PreparedStatement ps =
                    conn.prepareStatement("INSERT INTO usuario VALUES (null,?,?,?,?,?)");

                ps.setString(1, pCorreo);
                ps.setString(2, pPassword);
                ps.setString(3, pNombres);
                ps.setString(4, pApellidos);
                ps.setString(5, pFechaNacimiento);

                rows_updated = ps.executeUpdate();

                System.out.println("Datos insertados correctamente");

            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
