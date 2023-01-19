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
import model.User;

/**
 *
 * @author Thalia
 */

public class UserDAO {
    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public List<User> list() throws SQLException {
        List<User> users = new ArrayList<>();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            User user = new User(rs.getString("username"), rs.getString("password"), rs.getString("email"), rs.getInt("role"));
            user.setId(rs.getInt("user_id"));
            users.add(user);
        }
        return users;
    }
    
    public void create(User user) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO users (username, password, email, role) VALUES (?,?,?,?)");
        stmt.setString(1, user.getUsername());
        stmt.setString(2, user.getPassword());
        stmt.setString(3, user.getEmail());
        stmt.setInt(4, user.getRole());
        stmt.execute();
    }

    public User read(int userId) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users WHERE user_id = ?");
        stmt.setInt(1, userId);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new User(rs.getString("username"), rs.getString("password"), rs.getString("email"), rs.getInt("role"));
        }
        return null;
    }

    public void update(User user) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("UPDATE users SET username = ?, password = ?, email = ?, role = ? WHERE user_id = ?");
        stmt.setString(1, user.getUsername());
        stmt.setString(2, user.getPassword());
        stmt.setString(3, user.getEmail());
        stmt.setInt(4, user.getRole());
        stmt.setInt(5, user.getId());
        stmt.execute();
    }

    public void delete(int userId) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM users WHERE user_id = ?");
        stmt.setInt(1, userId);
        stmt.execute();
    }
}


