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
        // get itemID from the request parameter
        int itemId = Integer.parseInt(request.getParameter("itemId"));

        // fetch item details from db
        ItemDAO itemDAO = new ItemDAO();
        Item item = itemDAO.getItemById(itemId);

        // Convert object to json
        String json = new Gson().toJson(item);

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


        // get specific item from db
        ItemDAO itemDAO = (ItemDAO) getServletContext().getAttribute("itemDAO");
        Item item = itemDAO.getItemById(itemIdToUpdate);

        // update the item with the new values
        item.setProductName(title);
        item.setCategoryId(categoryId);
        item.setDescription(description);
        item.setPrice(price);
        item.setImageUrl(imageUrl);


        boolean updateSuccess = itemDAO.updateItem(item);

        if (updateSuccess) {
            System.out.println("Item updated successfully with ID: " + itemIdToUpdate);
        } else {
            System.out.println("Failed to update item with ID: " + itemIdToUpdate);
        }
    }

}