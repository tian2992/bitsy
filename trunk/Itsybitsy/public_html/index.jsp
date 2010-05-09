<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="LogicaBaseDeDatos.*" %>
<%@ page contentType="text/html;charset=windows-1252"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Inicio</title>

<%

if( application.getAttribute("Seguridad") == null )
  {
      //Encargado de verificar la autenticidad de un Usuario e inicializar el Sistema
    AdministradorSeguridad admon = new AdministradorSeguridad();    
      
    application.setAttribute("Seguridad", admon);
    
  }
  
%>
  HOLA SOY EL INDEX
   <%
   if( session.getAttribute("Mensaje") != null )
   {          
     out.print(session.getAttribute("Mensaje").toString()); 
     session.removeAttribute("Mensaje"); 
    
   }
   %>
   </head>
   </html>