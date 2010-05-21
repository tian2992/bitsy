<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ page import="LogicaBaseDeDatos.*"%>
<%@ include file="standardHeader.jspf"%>
<%
response.addHeader("Cache-Control", "no-chache, no-store, must-revalidate, max-age=0, proxy-revalidate, no-transform, pre-check=0, post-check=0, private");
%>
<html xmlns="http://www.w3.org/1999/xhtml">
        <head>
                <meta http-equiv="Content-Type"
                      content="text/html; charset=iso-8859-1"/>
                <title>Iniciar Sesi&oacute;n</title>
        </head>
        <body>
                <script language="javascript">
                  history.go(1);/* undo user navigation (ex: IE Back Button) */
                </script>
                <% 

if( application.getAttribute("Seguridad") == null )
  {
      //Encargado de verificar la autenticidad de un Usuario e inicializar el Sistema
    AdministradorSeguridad admon = new AdministradorSeguridad();    
      
    application.setAttribute("Seguridad", admon);
    
  }
  
%>
                 
<%
//Cambiar la pestaña , si ya inicio sesion, que la pestaña diga cerrar session
    if( session.getAttribute("Logged") != null )
     {    
     out.print("<td><div class= \"navigation\"><a href=\"cerrarSesion.jsp\" class= \"main_link\" >Cerrar Sesion</a></div></td>" );   
     } 
      else //viceversa
     {      
      out.print("<td><div class= \"navigation\"><a href=\"login.jsp\" class= \"main_link\" >Iniciar Sesion</a></div></td>" );    
    %>
                
                 
                <%
if (session.getAttribute("admin")!=null){

%>
                 //Aqui se muestra el menu exclusivo que tendra el administrador 
                <%
} if (session.getAttribute("user")!=null){

%>
                 //Aqui se muestra el menu exclusivo para los usuarios
                (registrados) como el permitir bajar archivo :P 
                <%
}
%>
                 
                <% 
if (session.getAttribute("admin")==null || session.getAttribute("user")== null){

%>
                 //Aqui se muestra el menu para los usuarios que no estan
                &quot;logeados&quot; 
                <%
}
%>
                 HOLA SOY EL INDEX 
                <%
   //Este es el encargado de mostrar el atributo de session mensaje, es decir
   //muestra los mensajes (correspondientes) en la pagina index.
   if( session.getAttribute("Mensaje") != null )
   {          
     out.print(session.getAttribute("Mensaje").toString()); 
     session.removeAttribute("Mensaje"); 
    
   }   
   
   %>
                 
                <%@ include file="standardFooter.jspf"%>
        </body>
</html>
<%
        
      
    if( session.getAttribute("Logged") != null )
        {
      out.print( "<b><font color=\"white\"> "+ "Bienvenido  " + session.getAttribute("User").toString() + "</font></b>");
        
        }else{
        
        }
     
 %>
                                         
                                        <br/>
                                         
                                        <br/>
                                         
                                        <%
   
        if( session.getAttribute("Admin") == null && session.getAttribute("Logged")!=null ) {
        
        out.print( "<b><font color=\"#868A08\"> "+ "Menu Quiniela " + "</font></b>");
         %>
                                         
                                        <br/>
                                         
                                        <br/>
                                         
                                        <b>
                                                <font color="#4b8A08">
                                                        <a href="pronosticos.jsp"
                                                           class='"main_link\'>Pronosticos</a>
                                                </font>
                                                 </b>
                                         
                                        <br/>
                                         
                                        <br/>
                                         
                                        <b>
                                                <font color="#4b8A08">
                                                        <a href="puntuaciones.jsp"
                                                           class='"main_link\'>Puntuaciones</a>
                                                </font>
                                                 </b>
                                         
                                        <br/>
                                         
                                        <br/>
                                         
                                        <%
        
  }
     
 %>
                                </td>
                                <td class="body_content">
                                        <h2>Iniciar Sesion</h2>
                                         
                                        <br/>
                                         
                                        <h4>
                                                <%
   if( session.getAttribute("Mensaje") != null )
   {          
     out.print(session.getAttribute("Mensaje").toString()); 
     session.removeAttribute("Mensaje"); 
    
   }
   %>
                                        </h4>
                                         
                                        <form action="doLogin.jsp"
                                              method="POST">
                                                <table>
                                                        <tr>
                                                                <td>&nbsp;</td>
                                                        </tr>
                                                         
                                                        <tr>
                                                                <td>
                                                                        <h3>Usuario</h3>
                                                                </td>
                                                                <td>
                                                                        <input type="text"
                                                                               name="user"
                                                                               id="user"></input>
                                                                </td>
                                                        </tr>
                                                         
                                                        <tr>
                                                                <td>
                                                                        <h3>Contrase&ntilde;a</h3>
                                                                </td>
                                                                <td>
                                                                        <input type="password"
                                                                               name="pass"
                                                                               id="pass"></input>
                                                                </td>
                                                        </tr>
                                                         
                                                        <tr>
                                                                <td>&nbsp;</td>
                                                        </tr>
                                                         
                                                        <tr>
                                                                <td>&nbsp;</td>
                                                        </tr>
                                                         
                                                        <tr>
                                                                <td>
                                                                        <input class="boton"
                                                                               type="submit"
                                                                               value="Iniciar sesion"></input>
                                                                </td>
                                                                <td>
                                                                        <input class="boton"
                                                                               type="reset"
                                                                               value="Limpiar"></input>
                                                                </td>
                                                        </tr>
                                                         
                                                        <tr>
                                                                <td>&nbsp;</td>
                                                        </tr>
                                                         
                                                        <tr>
                                                                <td>&nbsp;</td>
                                                        </tr>
                                                </table>
                                        </form>
                                </td>
                        </tr>
                </table>
        </td>
        <td class="shadow_right">&nbsp;</td>
</tr>
<tr>
        <td class="shadow_left">&nbsp;</td>
        <td class="middle_spacer">
                <div class="bottom_content">Pendiente</div>
        </td>
        <td class="shadow_right">&nbsp;</td>
</tr>
<tr>
        <td class="shadow_left">&nbsp;</td>
        <td class="bottom_link_container">
                <p>
                        <a href="#" class="bottom_link">Link</a>
                         | 
                        <a href="#" class="bottom_link">Link</a>
                         | 
                        <a href="#" class="bottom_link">Link</a>
                         | 
                        <a href="#" class="bottom_link">Link</a>
                         | 
                        <a href="#" class="bottom_link">Link</a>
                </p>
                <p>
                        All Right Reserved &copy; 2006 by Black Empires<br/>
                         rosewitchy@hotmail.com
                </p>
        </td>
        <td class="shadow_right">&nbsp;</td>
</tr>