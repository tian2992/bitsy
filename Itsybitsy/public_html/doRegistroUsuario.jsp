<%@ page import="LogicaBaseDeDatos.*" %>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>

<%          

    //Validaciones
    boolean huboError = false;
    if (request.getParameter("user") == null || request.getParameter("user").toString().length() == 0) {

        session.setAttribute("Mensaje", "Error el correo electronico es un campo requerido");

        huboError = true;

    }


    if (request.getParameter("pass") == null || request.getParameter("pass").toString().length() == 0) {

        session.setAttribute("Mensaje", "Error el password es un campo requerido");

        huboError = true;

    }

    if (request.getParameter("nombre") == null || request.getParameter("nombre").toString().length() == 0) {

        session.setAttribute("Mensaje", "Error el nombre es un campo requerido");

        huboError = true;

    }

    if (request.getParameter("apellido") == null || request.getParameter("apellido").toString().length() == 0) {

        session.setAttribute("Mensaje", "Error el apellido es un campo requerido");

        huboError = true;

    }


    if (request.getParameter("nacimiento") == null || request.getParameter("nacimiento").toString().length() == 0) {

        session.setAttribute("Mensaje", "Error la fecha de nacimiento es un campo requerido");

        huboError = true;

    }

    String user = request.getParameter("user").toString();
    String pass = request.getParameter("pass").toString();
    String nombres = request.getParameter("nombre").toString();
    String apellidos = request.getParameter("apellido").toString();
    String fechanac = request.getParameter("nacimiento").toString();

    Herramientas t = new Herramientas();

    String rawPass = EncriptadorMD5.MD5(pass);

    //Verificar que la direccion de correo electronico, tenga una estructura v�lida
    if (!t.isEmail(user)) {
        session.setAttribute("Mensaje", "Error la direccion de correo electronica no es valida");

        huboError = true;
        
    }

    if (huboError) {
        response.sendRedirect("registroUsuario.jsp");
        return;
    }

    try {

        if (application.getAttribute("Seguridad") != null) {

            AdministradorSeguridad admon = (AdministradorSeguridad) application.getAttribute("Seguridad");

            boolean yaSeRegistro = admon.yaSeRegistro(user);

            if (yaSeRegistro) {

                session.setAttribute("Mensaje", "Usuario " + user + " ya existe");
                
                response.sendRedirect("registroUsuario.jsp");
            
            }

            admon.agregarUsuario(user, rawPass, nombres, apellidos, fechanac);

            session.setAttribute("Mensaje", "Usuario " + user + " registrado con exito");

            response.sendRedirect("index.jsp");
            
        }

    } catch (Exception e) {

        e.printStackTrace();

    }

 %>