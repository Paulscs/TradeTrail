package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Item;
import model.ItemDAO;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "FilterItemsServlet", value = "/filtercat")
public class FilterItemsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ItemDAO itemDAO;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        itemDAO = new ItemDAO();
        List<String> categories = itemDAO.getAllCategories();

        // set categories as an attribute in the request scope
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
