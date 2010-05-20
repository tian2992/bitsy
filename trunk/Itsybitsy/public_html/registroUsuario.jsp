<%@ include file="standardHeader.jspf" %>
<%
//Formulario para el registro
%>
<form action="doRegistroUsuario.jsp" method="POST">

  <h3>Correo Electronico</h3>
  <input type="text" name="user" id="user" size="30"></input>
  <h3>Constraseña</h3>
  <input type="password" name="pass" id="pass" size="15" ></input>

  <h3>Nombres</h3>
  <input type= "text" name="nombre" id="nombre" size="30" ></input>

  <h3>Apellidos</h3>
  <input type="text" name="apellido" id="apellido" size="30" ></input>

  <h3>Fecha Nacimiento</h3>
  <input type= "text" name="nacimiento" id="nacimiento" size="30" onfocus="showCalendarControl(this);" ></input> *
  <input type="submit" value="Crear"
         class="boton"></input>
  <input type="reset" value="Limpiar"
  class="boton"></input>

  <br />
  Todos los campos son obligatorios


</form> 
<%@ include file="standardFooter.jspf" %>