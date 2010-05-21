<%@ include file="standardHeader.jspf" %>
<%@ page import="listaEnlazada.*" %>
<%@ page import="BitsyL.*" %>
<%@ page import="java.net.*" %>
<%@ page import="java.util.*" %>


  <h1>Opciones Administrativas</h1>
  <div class="clear span-18">
    <div class="clear span-16 prepend-1 append-1 last" id="contenido">
    <h2>ClientesConectados</h2>
    <ul>
        <%
            BitsyL.NetworkController n = BitsyL.NetworkController.getInstance();
            List<Socket> socks = n.getSockets();
            for (Socket s: socks) {
            %>
                <li><%= s.getInetAddress() %></li>
            <%
            }
        
        %>
        </ul>
    
    </div>

</div>


<%@ include file="standardFooter.jspf" %>