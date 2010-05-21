<%@ include file="standardHeader.jspf" %>

    <%
        String s = request.getParameter("");
        
        if (s == null){ %>
            <jsp:forward page="index.jsp">
                <jsp:param name="error" value="noUsernamePass" />
            </jsp:forward>
        <% }
    
    %>

    <h1>Resultados de la Busqueda</h1>
    <div class="clear span-18">
        <div class="clear span-16 prepend-1 append-1 last" id="contenido">
            
            
        </div>
    </div>

<%@ include file="standardFooter.jspf" %>