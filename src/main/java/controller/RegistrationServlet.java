package servlets;

import java.io.*;
import java.net.ConnectException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "RegistrationServlet", value = "/register")
public class RegistrationServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Forward the request to the JSP page
        request.getRequestDispatcher("/registration.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String uname = request.getParameter("name");
        String uemail = request.getParameter("email");
        String upwd = request.getParameter("pass");
        String umobile = request.getParameter("contact");
        RequestDispatcher dispatcher = null;
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/company?useSSL=false", "root", "paul0409!");
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try{


            PreparedStatement pst = con.prepareStatement("insert into users(username, uemail, password, umobile) values (?,?,?,?)");
            pst.setString(1, uname);
            pst.setString(2, uemail);
            pst.setString(3, upwd);
            pst.setString(4, umobile);

            int rowCount = pst.executeUpdate();
            dispatcher = request.getRequestDispatcher("/registration.jsp");

            if (rowCount > 0) {
                request.setAttribute("status", "success");
            } else {
                request.setAttribute("status", "failed");
            }
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}