package controller;

import java.io.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import com.google.gson.Gson;

import model.Item;
import model.ItemDAO;


@WebServlet(name = "UpdateItemServlet", value = "/updateItem")
public class UpdateItemServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the item ID from the request parameter
        int itemId = Integer.parseInt(request.getParameter("itemId"));

        // Fetch item details from the database using the ItemDAO
        ItemDAO itemDAO = new ItemDAO();
        Item item = itemDAO.getItemById(itemId);

        // Convert the item object to JSON
        String json = new Gson().toJson(item);

        // Set the content type and write JSON response
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int itemIdToUpdate = Integer.parseInt(request.getParameter("itemId"));
        String title = request.getParameter("title");
        int categoryId = Integer.parseInt(request.getParameter("category"));
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        String imageUrl = request.getParameter("image_url");

        // Retrieve the item from the database
        ItemDAO itemDAO = (ItemDAO) getServletContext().getAttribute("itemDAO");
        Item item = itemDAO.getItemById(itemIdToUpdate);

        // Update the item with the new values
        item.setProductName(title);
        item.setCategoryId(categoryId);
        item.setDescription(description);
        item.setPrice(price);
        item.setImageUrl(imageUrl);

        // Update the item in the database
        boolean updateSuccess = itemDAO.updateItem(item);

        // Handle success or failure
        if (updateSuccess) {
            System.out.println("Item updated successfully with ID: " + itemIdToUpdate);
        } else {
            System.out.println("Failed to update item with ID: " + itemIdToUpdate);
        }
        // Redirect or forward as needed
    }

}