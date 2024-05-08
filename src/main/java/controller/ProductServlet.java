package controller;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Item;
import model.ItemDAO;
import model.UserDAO;

@WebServlet(name = "ProductServlet", value = "/products")
public class ProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ItemDAO itemDAO;
    private UserDAO userDAO;

    public void init() {
        itemDAO = new ItemDAO();
        userDAO = new UserDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Entering ProductServlet doGet method");

        // Retrieve all items
        List<Item> items = itemDAO.getAllItems();
        System.out.println("Retrieved items: " + items);

        request.setAttribute("items", items);
        request.setAttribute("userDAO", userDAO);

        request.getRequestDispatcher("/index.jsp").forward(request, response);

        System.out.println("Exiting ProductServlet doGet method"); // Log exit point
    }
}
