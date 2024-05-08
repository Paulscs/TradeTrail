package controller;

import java.io.*;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import model.Item;
import model.ItemDAO;


@WebServlet(name = "AdminServlet", value = "/admin")
public class AdminServlet extends HttpServlet {

    public void init() {
        ItemDAO itemDAO = new ItemDAO();
        getServletContext().setAttribute("itemDAO", itemDAO);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ItemDAO itemDAO = (ItemDAO) getServletContext().getAttribute("itemDAO");

        // Retrieve all products from db
        List<Item> products2 = itemDAO.getAllItems2();
        System.out.println("Products retrieved in admin servlet: " + products2);


        request.setAttribute("products2", products2);
        request.getRequestDispatcher("/admin.jsp").forward(request, response);
    }

    //delete post method
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // get the item ID to delete
        int itemIdToDelete = Integer.parseInt(request.getParameter("itemId"));

        // delete the item
        ItemDAO itemDAO = (ItemDAO) getServletContext().getAttribute("itemDAO");
        boolean deletionSuccess = itemDAO.deleteItem(itemIdToDelete);

        //debugging purposes
        if (deletionSuccess) {
            System.out.println("Item deleted successfully with ID: " + itemIdToDelete);
        } else {
            System.out.println("Failed to delete item with ID: " + itemIdToDelete);
        }

        response.sendRedirect(request.getContextPath() + "/admin");
    }


}