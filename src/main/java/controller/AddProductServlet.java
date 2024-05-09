package controller;

import java.io.*;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.MultipartConfig;
import model.Item;
import model.ItemDAO;
import model.UserDAO;

@WebServlet(name = "AddProductServlet", value = "/addProductServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50)  // 50MB
public class AddProductServlet extends HttpServlet {

    private UserDAO userDAO;
    private ItemDAO itemDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        userDAO = new UserDAO();
        itemDAO = new ItemDAO();
    }

    //method to correctly store image
    protected String saveImageAndGetUrl(HttpServletRequest request) throws IOException, ServletException {
        Part filePart = request.getPart("image");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String uniqueFileName = UUID.randomUUID().toString() + "_" + fileName;
        String uploadDir = getServletContext().getRealPath("resources/img");
        File uploadDirPath = new File(uploadDir);
        if (!uploadDirPath.exists()) {
            uploadDirPath.mkdirs();
        }
        String filePath = uploadDir + File.separator + uniqueFileName;
        try (InputStream fileContent = filePart.getInputStream();
             OutputStream outputStream = new FileOutputStream(filePath)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fileContent.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
        return request.getContextPath() + "/resources/img/" + uniqueFileName;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // get the data from the sell.jsp form
            String productName = request.getParameter("productName");
            String categoryName = request.getParameter("category");
            String description = request.getParameter("description");
            double price = Double.parseDouble(request.getParameter("price"));
            String imageUrl = saveImageAndGetUrl(request);

            // get userid from session
            HttpSession session = request.getSession();
            int userId = (int) session.getAttribute("userId");

            int categoryId = itemDAO.getCategoryIdByName(categoryName);

            Item item = new Item(userId, productName, categoryId, description, price, imageUrl);

            boolean success = itemDAO.insertItem(item);

            // Redirect with success or error message
            if (success) {
                response.sendRedirect(request.getContextPath() + "/products?status=success");
            } else {
                response.sendRedirect(request.getContextPath() + "/sell.jsp?status=error");
            }
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }
}
