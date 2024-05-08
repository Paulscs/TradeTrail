package model;

import java.util.Date;

public class Item {
    private int itemId; // set to incremental in mysql
    private User user; // Reference to the User who posted the item
    private int userId;
    private int categoryId;
    private String productName;
    private String category;
    private String description;
    private double price;
    private String imageUrl; // path to image
    private String categoryName;

    public Item(int userId, String productName, int categoryId, String description, double price, String imageUrl) {
        this.userId = userId;
        this.productName = productName;
        this.categoryId = categoryId;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    //constructor for GetAllItems2()
    public Item(int itemId, int userId, String productName, int categoryId, String description, double price, String imageUrl) {
        this.itemId = itemId;
        this.userId = userId;
        this.productName = productName;
        this.categoryId = categoryId;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    //Constructor for getAllItemsWithCategoryName()
    public Item(int itemId, int userId, String productName, int categoryId, String description, double price, String imageUrl, String categoryName) {
        this.itemId = itemId;
        this.userId = userId;
        this.productName = productName;
        this.categoryId = categoryId;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.categoryName = categoryName;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getProductName() {
        return productName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
