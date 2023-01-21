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
import model.ReservationService;

/**
 *
 * @author THALIA
 */
public class ReservationServiceDAO {

    private Connection connection;

    public ReservationServiceDAO(Connection connection) {
        this.connection = connection;
    }

    public List<ReservationService> list() throws SQLException {
        List<ReservationService> reservationServices = new ArrayList<>();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM reservation_services");
        ResultSet rs = stmt.executeQuery();
        this.connection.close();
        while (rs.next()) {
            int reservationId = rs.getInt("reservation_id");
            String serviceName = rs.getString("service_name");
            float servicePrice = rs.getFloat("service_price");
            ReservationService reservationService = new ReservationService(reservationId, serviceName, servicePrice);
            reservationService.setReservationServiceId(rs.getInt("reservation_service_id"));
            reservationServices.add(reservationService);
        }
        return reservationServices;
    }

    public void create(ReservationService reservationService) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO reservation_services (reservation_id, service_name, service_price) VALUES (?, ?, ?)");

        stmt.setInt(1, reservationService.getReservationId());
        stmt.setString(2, reservationService.getServiceName());
        stmt.setFloat(3, reservationService.getServicePrice());

        stmt.execute();
        this.connection.close();

    }

    public ReservationService read(int reservationServiceId) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM reservation_services WHERE reservation_service_id = ?");
        stmt.setInt(1, reservationServiceId);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            this.connection.close();
            int reservationId = rs.getInt("reservation_id");
            String serviceName = rs.getString("service_name");
            float servicePrice = rs.getFloat("service_price");
            ReservationService reservationService = new ReservationService(reservationId, serviceName, servicePrice);
            reservationService.setReservationServiceId(reservationServiceId);

            return reservationService;
        }
        return null;
    }

    public void update(ReservationService reservationService) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("UPDATE reservation_services SET reservation_id = ?, service_name = ?, service_price = ? WHERE reservation_service_id = ?");
        stmt.setInt(1, reservationService.getReservationId());
        stmt.setString(2, reservationService.getServiceName());
        stmt.setFloat(3, reservationService.getServicePrice());
        stmt.setInt(4, reservationService.getReservationServiceId());
        
        stmt.execute();
        this.connection.close();
    }

    public void delete(int reservationServiceId) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM reservation_services WHERE reservation_service_id = ?");
        stmt.setInt(1, reservationServiceId);
        stmt.execute();
        this.connection.close();
    }
}
