/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.RoomCategory;

/**
 *
 * @author THALIA
 */
public class RoomCategoryDAO {

    private Connection connection;

    public RoomCategoryDAO(Connection connection) {
        this.connection = connection;
    }

    public List<RoomCategory> list() throws SQLException {
        List<RoomCategory> roomCategories = new ArrayList<>();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM room_categories");
        ResultSet rs = stmt.executeQuery();
        this.connection.close();
        while (rs.next()) {
            String categoryName = rs.getString("category_name");
            String categoryDescription = rs.getString("category_description");
            int capacity = rs.getInt("capacity");
            int beds = rs.getInt("beds");
            float price = rs.getFloat("price");
            RoomCategory roomCategory = new RoomCategory(categoryName, categoryDescription, capacity, beds, price);
            roomCategory.setCategoryId(rs.getInt("category_id"));
            roomCategories.add(roomCategory);
        }
        return roomCategories;
    }

    public void create(RoomCategory roomCategory) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO room_categories (category_name, category_description, capacity, beds, price) VALUES (?, ?, ?, ?, ?)");

        stmt.setString(1, roomCategory.getCategoryName());
        stmt.setString(2, roomCategory.getCategoryDescription());
        stmt.setInt(3, roomCategory.getCapacity());
        stmt.setInt(4, roomCategory.getBeds());
        stmt.setFloat(5, roomCategory.getPrice());

        stmt.execute();
        this.connection.close();

    }

    public RoomCategory read(int categoryId) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM room_categories WHERE category_id = ?");
        stmt.setInt(1, categoryId);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            this.connection.close();
            String categoryName = rs.getString("category_name");
            String categoryDescription = rs.getString("category_description");
            int capacity = rs.getInt("capacity");
            int beds = rs.getInt("beds");
            float price = rs.getFloat("price");

            RoomCategory roomCategory = new RoomCategory(categoryName, categoryDescription, capacity, beds, price);
            roomCategory.setCategoryId(categoryId);

            return roomCategory;
        }
        return null;
    }

    public void update(RoomCategory roomCategory) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("UPDATE room_categories SET category_name = ?, category_description = ?, capacity = ?, beds = ?, price = ? WHERE category_id = ?");
        stmt.setString(1, roomCategory.getCategoryName());
        stmt.setString(2, roomCategory.getCategoryDescription());
        stmt.setInt(3, roomCategory.getCapacity());
        stmt.setInt(4, roomCategory.getBeds());
        stmt.setFloat(5, roomCategory.getPrice());
        stmt.setInt(6, roomCategory.getCategoryId());
        
        stmt.execute();
        this.connection.close();
    }

    public void delete(int categoryId) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM room_categories WHERE category_id = ?");
        stmt.setInt(1, categoryId);
        stmt.execute();
        this.connection.close();
    }
}
