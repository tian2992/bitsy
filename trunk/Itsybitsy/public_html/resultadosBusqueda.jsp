<%@ include file="standardHeader.jspf" %>
<!--

    -->
    
    <%
        java.util.List<BitsyL.Item> lista = controlador.buscar("txt", false, null, null);
    
    %>

    <h1>Resultados de la Busqueda</h1>
    <div class="clear span-18">
        <div class="clear span-16 prepend-1 append-1 last" id="contenido">
            <%
                for (BitsyL.Item i: lista){
                    out.println(i.getNombre());
                }
            %>
            
        </div>
    </div>

<%@ include file="standardFooter.jspf" %>