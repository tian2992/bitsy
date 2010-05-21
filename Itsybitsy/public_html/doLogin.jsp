<jsp:useBean id="admon" class="LogicaBaseDeDatos.AdministradorSeguridad" scope="page" />
<jsp:useBean id="usuario" class="LogicaBaseDeDatos.Usuario" scope="session"/>
<%
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    
    if (username == null || password == null){ %>
        <jsp:forward page="index.jsp">
            <jsp:param name="error" value="noUsernamePass" />
        </jsp:forward>
<%  }
    else {
        if (admon.existe(username, password)){
            usuario.setUsername(username);
            usuario.setValido(true);
            %>
            <jsp:forward page="index.jsp">
                <jsp:param name="autorizado" value="yeah" />
            </jsp:forward>
            <%
        } else { %>
            <jsp:forward page="index.jsp">
                <jsp:param name="autorizado" value="nopes" />
            </jsp:forward>
    <% }
    
    }





%>