<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page import="LogicaBaseDeDatos.*" %>
<%@ page contentType="text/html;charset=utf-8"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Registro Usuario</title>
<link href="calendario/CalendarControl.css"  rel="stylesheet" type="text/css"/>
</head>

<body>

<script src="calendario/CalendarControl.js" language="javascript"></script>
  

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
</body>
</html>
