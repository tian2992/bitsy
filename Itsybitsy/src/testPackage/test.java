package testPackage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;

public class test extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException,
                                                           IOException {
        String var0 = "";
        boolean var1;
        int var2;
        short var3;
        try {
            var0 = request.getParameter("param0");
            var1 = Boolean.valueOf(request.getParameter("param1")).booleanValue();
            var2 = Integer.parseInt(request.getParameter("param2"));
            var3 = (short) Integer.parseInt(request.getParameter("param3"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>test</title></head>");
        out.println("<body>");
        out.println("<p>The servlet has received a GET. This is the reply.</p>");
        out.println("</body></html>");
        out.close();
    }
}
