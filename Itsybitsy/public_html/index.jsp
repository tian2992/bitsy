<%@ include file="standardHeader.jspf" %>
<%@ page import="LogicaBaseDeDatos.*"%>


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

 out.print( "<b><font color=\"white\"> "+ "Bienvenido  " + session.getAttribute("user").toString() + "</font></b>");
%>

//Aqui se muestra el menu exclusivo para los usuarios (registrados) como el permitir bajar archivo :P

<%
}
%>

<% 
if (session.getAttribute("admin")==null || session.getAttribute("user")== null){

%>
//Aqui se muestra el menu para los usuarios que no estan "logeados"
 
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
   <%@ include file="standardFooter.jspf" %>