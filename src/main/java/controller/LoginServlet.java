package controller;

import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.User;
import model.UserDAO;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        userDAO = new UserDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String uname = request.getParameter("username");
        String password = request.getParameter("password");


        User user = userDAO.getUserByUsernameAndPassword(uname, password);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("userId", user.getId());
            session.setAttribute("name", user.getUsername());
            session.setAttribute("admin", user.isAdmin()); // Store admin status in session
            if (user.isAdmin()) {
                response.sendRedirect(request.getContextPath() + "/admin"); // Redirect to admin dashboard
            } else {
                response.sendRedirect(request.getContextPath() + "/products");
            }
        } else {
            request.setAttribute("status", "failed");
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

}
