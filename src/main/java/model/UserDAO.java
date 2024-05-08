package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UserDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/company?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "paul0409!";

    private static final String INSERT_USERS_SQL = "INSERT INTO users (username, uemail, password, umobile) VALUES (?, ?, ?, ?)";
    private static final String SELECT_USER_BY_USERNAME_AND_PASSWORD = "SELECT * FROM users WHERE username = ? AND password = ?";

    public UserDAO() {
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

    public boolean insertUser(User user) throws SQLException {
        boolean rowInserted = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getMobile());

            rowInserted = preparedStatement.executeUpdate() > 0;
        }
        return rowInserted;
    }


    public User getUserByUsernameAndPassword(String username, String password) {
        User user = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_USERNAME_AND_PASSWORD)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String email = rs.getString("uemail");
                String mobile = rs.getString("umobile");
                boolean admin = rs.getBoolean("admin"); // Retrieve the admin status
                user = new User(id, username, email, password, mobile, admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


    // To retrieve username by user ID
    public String getUsernameById(int userId) {
        String username = null;
        String query = "SELECT username FROM users WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                username = rs.getString("username");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return username;
    }

    // get all users for the admin dashboard
    public Map<Integer, String> getAllUsers() {
        Map<Integer, String> users = new HashMap<>();
        String query = "SELECT id, username FROM users";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                users.put(id, username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


}
