package BitsyL;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import java.net.Socket;

import java.util.Random;

import javax.servlet.*;
import javax.servlet.http.*;

public class downloader extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException,
                                                           IOException {
        String filename = "";
        Integer pcNumber = 0;
        try {
            filename = request.getParameter("filename");
            pcNumber = Integer.parseInt(request.getParameter("pc"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        
        Random r = new Random();
        
        NetworkController n = NetworkController.getInstance();
        
        File f = File.createTempFile("bitSy", ".tamp");
        
        Socket s = n.getSockets().get(pcNumber);
        
        if (s == null){
            out.close();
            return;
        }
        
        GetIndex gi = new GetIndex(s ,f);
        
        gi.start();
        
        try {gi.join(); } catch (Exception e) {e.printStackTrace();}
        
        f = gi.getIndex();
        
        FileInputStream fo = new FileInputStream(f);
        int i;
        while ((i=fo.read())!=-1){
            out.write(i);
        }
        
        
        /*
        out.println("<html>");
        out.println("<head><title>downloader</title></head>");
        out.println("<body>");
        out.println("<p>The servlet has received a GET. This is the reply.</p>");
        out.println("</body></html>");
        */
        out.close();
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException,
                                                            IOException {
        String filename = "";
        try {
            filename = request.getParameter("filename");
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.setContentType(CONTENT_TYPE);
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>downloader</title></head>");
        out.println("<body>");
        out.println("<p>The servlet has received a POST. This is the reply.</p>");
        out.println("</body></html>");
        out.close();
    }
}
