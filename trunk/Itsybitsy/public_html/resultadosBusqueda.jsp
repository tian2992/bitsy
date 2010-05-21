<%@ include file="standardHeader.jspf" %>
<!--

    -->
    
    <%
    
        java.util.List<BitsyL.Item> lista = new java.util.LinkedList<BitsyL.Item>();
    
        String texto = request.getParameter("busc");
        //boolean plano = Boolean.valueOf(request.getParameter("textoCompleto")).booleanValue();
        //boolean plano = request.getParameter("textoCompleto").toString();
        //out.print("plano : " + plano ); 
        
        String texCom = request.getParameter("textoCompleto");
        
        out.println();
        
        if( !controlador.getIsListo() ) 
        {
            if (texCom==null)
                lista = controlador.buscar(texto,  true, null, null);
            else
                lista = controlador.buscar(texto, false, null, null);
        } else
        {
            out.print("indice aun no esta listo");
        }
        
        out.print(controlador.getIsListo());
    
    %>

    <h1>Resultados de la Busqueda</h1>
    <div class="clear span-18">
        <div class="clear span-16 prepend-1 append-1 last" id="contenido">
            <%
            
                
                for (BitsyL.Item i: lista){
                    out.println(i.getFullPath());
                }
            %>
            
        </div>
    </div>

<%@ include file="standardFooter.jspf" %>