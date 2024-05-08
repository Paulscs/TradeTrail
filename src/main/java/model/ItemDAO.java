package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/company?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "paul0409!";

    private static final String INSERT_ITEM_SQL = "INSERT INTO items (user_id, title, category_id, description, price, image_url) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_ITEMS_SQL = "SELECT * FROM items";
    private static final String SELECT_CATEGORY_ID_BY_NAME_SQL = "SELECT category_id FROM categories WHERE name = ?";
    private static final String SELECT_ALL_CATEGORIES = "SELECT name FROM categories";

    public ItemDAO() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public boolean insertItem(Item item) throws SQLException {
        boolean rowInserted = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ITEM_SQL)) {
            preparedStatement.setInt(1, item.getUserId());
            preparedStatement.setString(2, item.getProductName());
            preparedStatement.setInt(3, item.getCategoryId());
            preparedStatement.setString(4, item.getDescription());
            preparedStatement.setDouble(5, item.getPrice());
            preparedStatement.setString(6, item.getImageUrl());

            rowInserted = preparedStatement.executeUpdate() > 0;
        }
        return rowInserted;
    }

    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ITEMS_SQL);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                int userId = rs.getInt("user_id");
                int categoryId = rs.getInt("category_id");
                String productName = rs.getString("title");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                String imageUrl = rs.getString("image_url");

                Item item = new Item(userId, productName, categoryId, description, price, imageUrl);
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public List<Item> getAllItems2() {
        List<Item> items = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ITEMS_SQL);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                int itemId = rs.getInt("item_id");
                int userId = rs.getInt("user_id");
                int categoryId = rs.getInt("category_id");
                String productName = rs.getString("title");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                String imageUrl = rs.getString("image_url");

                Item item = new Item(itemId, userId, productName, categoryId, description, price, imageUrl);
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public int getCategoryIdByName(String categoryName) throws SQLException {
        int categoryId = -1;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORY_ID_BY_NAME_SQL)) {
            preparedStatement.setString(1, categoryName);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                categoryId = rs.getInt("category_id");
            }
        }
        return categoryId;
    }

    // retrieves all the categories from the table categories
    public List<String> getAllCategories() {
        List<String> categories = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CATEGORIES)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String category = rs.getString("name");
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }
}
