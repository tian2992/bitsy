<%@ include file="standardHeader.jspf" %>

  <h1>Bienvenido a Bitsy</h1>
  <div class="clear span-18">
  
  <%
    String error      = request.getParameter("error");
    String autorizado = request.getParameter("autorizado");
  %>
  
    <% if (error!=null) {  %>
        <div class="span-16 prepend-1 append-1 last" id="mensajes">
            <div class="error">
            Ocurrio un <strong>error</strong> en la operacion anterior.
            </div>
        </div>
    <% } %>
    
    <div class="clear span-16 prepend-1 append-1 last" id="contenido">
        <form id="searchF" name="searchF" method="POST" action="resultadosBusqueda.jsp">
            <label for="busc"> Usuario:</label> <br /> 
            <input class="text" type="text" name="busc" size="10" /><br/>
            <input type="checkbox" name="textoCompleto"/>
            <input type="submit" value="Search" />
        </form>
    </div>
  </div>
  <div class="span-6 last" id="sidebar">
    <div class="span-4 prepend-1 append-1 last" id="sidebar-contenido">
    <% if (!(usuario.isValido())) { %>
      <h2>Login</h2>
        <form id="loginForm" name="loginForm" method="POST" action="doLogin.jsp">
            <label for="username">Usuario:</label> <br /> <input class="text" type="text" name="username" size="10" /><br/>
            <label for="password">Password:</label> <br /> <input class="text" type="password" name="password" size="10" /><br/>
            <input type="submit" value="Login" />
        </form>
        
        <a href="registroUsuario.jsp">Si no tienes cuenta, registrate</a>
        
    <% } else { %>    
    <h2>Bienvenido <%= usuario.getUsername() %></h2>
    <% } %>
    </div>
  </div>

<%@ include file="standardFooter.jspf" %>