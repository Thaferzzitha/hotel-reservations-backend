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
import model.PaymentMethod;

/**
 *
 * @author THALIA
 */
public class PaymentMethodDAO {

    private Connection connection;

    public PaymentMethodDAO(Connection connection) {
        this.connection = connection;
    }

    public List<PaymentMethod> list() throws SQLException {
        List<PaymentMethod> paymentMethods = new ArrayList<>();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM payment_methods");
        ResultSet rs = stmt.executeQuery();
        this.connection.close();
        while (rs.next()) {
            String methodName = rs.getString("method_name");
            PaymentMethod paymentMethod = new PaymentMethod(methodName);
            paymentMethod.setPaymentMethodId(rs.getInt("payment_method_id"));
            paymentMethods.add(paymentMethod);
        }
        return paymentMethods;
    }

    public void create(PaymentMethod paymentMethod) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO payment_method (method_name) VALUES (?)");

        stmt.setString(1, paymentMethod.getMethodName());

        stmt.execute();
        this.connection.close();

    }

    public PaymentMethod read(int paymentMethodId) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM payment_methods WHERE payment_method_id = ?");
        stmt.setInt(1, paymentMethodId);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            this.connection.close();
            String methodName = rs.getString("method_name");
            PaymentMethod paymentMethod = new PaymentMethod(methodName);
            paymentMethod.setPaymentMethodId(paymentMethodId);

            return paymentMethod;
        }
        return null;
    }

    public void update(PaymentMethod paymentMethod) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("UPDATE payment_methods SET method_name = ? WHERE payment_method_id = ?");
        stmt.setString(1, paymentMethod.getMethodName());

        stmt.execute();
        this.connection.close();
    }

    public void delete(int paymentMethodId) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM payment_method WHERE payment_method_id = ?");
        stmt.setInt(1, paymentMethodId);
        stmt.execute();
        this.connection.close();
    }
}
