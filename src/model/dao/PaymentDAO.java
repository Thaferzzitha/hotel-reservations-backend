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
import model.Payment;

/**
 *
 * @author THALIA
 */
public class PaymentDAO {

    private Connection connection;

    public PaymentDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Payment> list() throws SQLException {
        List<Payment> payments = new ArrayList<>();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM payments");
        ResultSet rs = stmt.executeQuery();
        this.connection.close();
        while (rs.next()) {
            int reservationId = rs.getInt("reservation_id");
            float amount = rs.getFloat("amount");
            String paymentDate = rs.getString("payment_date");
            int paymentMethod = rs.getInt("payment_method");
            Payment payment = new Payment(reservationId, amount, paymentDate, paymentMethod);
            payment.setPaymentId(rs.getInt("pyment_id"));
            payments.add(payment);
        }
        return payments;
    }

    public void create(Payment payment) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO payments (reservation_id, amount, payment_date, payment_method) VALUES (?, ?, ?, ?)");

        stmt.setInt(1, payment.getReservationId());
        stmt.setFloat(2, payment.getAmount());
        stmt.setString(3, payment.getPaymentDate());
        stmt.setInt(4, payment.getPaymentMethod());

        stmt.execute();
        this.connection.close();

    }

    public Payment read(int paymentId) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM payments WHERE payment_id = ?");
        stmt.setInt(1, paymentId);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            this.connection.close();
           int reservationId = rs.getInt("reservation_id");
            float amount = rs.getFloat("amount");
            String paymentDate = rs.getString("payment_date");
            int paymentMethod = rs.getInt("payment_method");
            Payment payment = new Payment(reservationId, amount, paymentDate, paymentMethod);
            payment.setPaymentId(paymentId);

            return payment;
        }
        return null;
    }

    public void update(Payment payment) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("UPDATE payment SET reservation_id = ?, amount = ?, payment_date = ?, payment_method = ? WHERE payment_id = ?");
        stmt.setInt(1, payment.getReservationId());
        stmt.setFloat(2, payment.getAmount());
        stmt.setString(3, payment.getPaymentDate());
        stmt.setInt(4, payment.getPaymentMethod());
        stmt.setInt(5, payment.getPaymentId());

        stmt.execute();
        this.connection.close();
    }

    public void delete(int paymentId) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM reservation_services WHERE reservation_service_id = ?");
        stmt.setInt(1, paymentId);
        stmt.execute();
        this.connection.close();
    }
}
