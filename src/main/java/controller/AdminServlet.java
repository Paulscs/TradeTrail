package controller;

import java.io.*;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import model.Item;
import model.User;
import model.ItemDAO;
import model.UserDAO;

@WebServlet(name = "AdminServlet", value = "/admin")
public class AdminServlet extends HttpServlet {

    public void init() {
        ItemDAO itemDAO = new ItemDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ItemDAO itemDAO = new ItemDAO();

        // Retrieve all products from the database
        List<Item> products = ItemDAO.getAllItems2();

        // Set the retrieved products as an attribute in the request object
        request.setAttribute("products", products);

        // Forward the request to the admin.jsp page
        request.getRequestDispatcher("/admin.jsp").forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }
}