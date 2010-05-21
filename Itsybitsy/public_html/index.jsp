<%@ include file="standardHeader.jspf" %>

<% 

/*

if( application.getAttribute("Seguridad") == null )
  {
      //Encargado de verificar la autenticidad de un Usuario e inicializar el Sistema
    AdministradorSeguridad admon = new AdministradorSeguridad();    
      
    application.setAttribute("Seguridad", admon);
    
  }
  */
%>
  HOLA SOY EL INDEX
   <%
   /*
   if( session.getAttribute("Mensaje") != null )
   {          
     out.print(session.getAttribute("Mensaje").toString()); 
     session.removeAttribute("Mensaje"); 
    
   }
   */
   %>
   
   <%@ include file="standardFooter.jspf" %>