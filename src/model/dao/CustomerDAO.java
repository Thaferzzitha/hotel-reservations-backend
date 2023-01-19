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
import model.Customer;
/**
 *
 * @author nesch
 */
public class CustomerDAO {
    private Connection connection;

    public CustomerDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Customer> list() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM customers");
        ResultSet rs = stmt.executeQuery();
        this.connection.close();
        while (rs.next()) {
            String identificationNumber = rs.getString("identification_number");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String email = rs.getString("email");
            String phone = rs.getString("phone");
            String address = rs.getString("address");
            int reservationId = rs.getInt("reservation_id");
            int paymentId = rs.getInt("payment_id");
            int customerId = rs.getInt("customer_id");
            Customer customer = new Customer(identificationNumber, firstName, lastName, email, phone, address, reservationId, paymentId);
            customer.setCustomerId(rs.getInt("customer_id"));
            customers.add(customer);
        }
        return customers;
    }
    
    public void create(Customer customer) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO customers (identification_number, first_name, last_name, email, phone, address, reservation_id, payment_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        
        stmt.setString(1, customer.getIdentificationNumber());
        stmt.setString(2, customer.getFirstName());
        stmt.setString(3, customer.getLastName());
        stmt.setString(4, customer.getEmail());
        stmt.setString(5, customer.getPhone());
        stmt.setString(6, customer.getAddress());
        stmt.setInt(7, customer.getReservationId());
        stmt.setInt(8, customer.getPaymentId());
        
        stmt.execute();
        this.connection.close();

    }

    public Customer read(int customerId) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM customers WHERE customer_id = ?");
        stmt.setInt(1, customerId);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            this.connection.close();
            String identificationNumber = rs.getString("identification_number");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            String email = rs.getString("email");
            String phone = rs.getString("phone");
            String address = rs.getString("address");
            int reservationId = rs.getInt("reservation_id");
            int paymentId = rs.getInt("payment_id");
            
            Customer customer = new Customer(identificationNumber, firstName, lastName, email, phone, address, reservationId, paymentId);
            customer.setCustomerId(customerId);
            
            return customer;
        }
        return null;
    }

    public void update(Customer customer) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("UPDATE customers SET identification_number = ?, first_name = ?, last_name = ?, email = ?, phone = ?, address = ?, reservation_id = ?, payment_id = ? WHERE customer_id = ?");
        stmt.setString(1, customer.getIdentificationNumber());
        stmt.setString(2, customer.getFirstName());
        stmt.setString(3, customer.getLastName());
        stmt.setString(4, customer.getEmail());
        stmt.setString(5, customer.getPhone());
        stmt.setString(6, customer.getAddress());
        stmt.setInt(7, customer.getReservationId());
        stmt.setInt(8, customer.getPaymentId());
        stmt.setInt(9, customer.getCustomerId());
        stmt.execute();
        this.connection.close();
    }

    public void delete(int customerId) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM customers WHERE customer_id = ?");
        stmt.setInt(1, customerId);
        stmt.execute();
        this.connection.close();
    }
}
