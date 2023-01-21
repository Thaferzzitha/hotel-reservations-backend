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
import model.PackService;

/**
 *
 * @author THALIA
 */
public class PackServiceDAO {

    private Connection connection;

    public PackServiceDAO(Connection connection) {
        this.connection = connection;
    }
}
