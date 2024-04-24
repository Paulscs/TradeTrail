package controller;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "ServletExample", value = "/servlet-example")
public class ServletExample extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Handle GET request
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        // Write response
        response.getWriter().println("<html><body>");
        response.getWriter().println("<h1>GET Request Received</h1>");
        response.getWriter().println("</body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Handle POST request
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        // Read POST parameters
        String param1 = request.getParameter("param1");
        String param2 = request.getParameter("param2");

        // Write response
        response.getWriter().println("<html><body>");
        response.getWriter().println("<h1>POST Request Received</h1>");
        response.getWriter().println("<p>Param1: " + param1 + "</p>");
        response.getWriter().println("<p>Param2: " + param2 + "</p>");
        response.getWriter().println("</body></html>");
    }
}