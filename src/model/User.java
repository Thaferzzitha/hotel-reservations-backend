/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author nesch
 */
public class User {
    private int userId;
    private String username;
    private String password;
    private String email;
    private int role;

    public User(String username, String password, String email, int role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public int getId() {
        return userId;
    }

    public void setId(int user_id) {
        this.userId = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
    
}
