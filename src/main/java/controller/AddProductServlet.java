package controller;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.UserDAO; // Import the UserDAO

@WebServlet(name = "SellServlet", value = "/sell")
public class SellServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO; // Declare UserDAO

    public void init() {
        userDAO = new UserDAO(); // Initialize UserDAO
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Fetch categories from the database
        List<String> categories = userDAO.getAllCategories();

        // Set categories as a request attribute
        request.setAttribute("categories", categories);

        // Forward to the sell.jsp
        request.getRequestDispatcher("/sell.jsp").forward(request, response);
    }

}


