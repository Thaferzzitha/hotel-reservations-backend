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
import model.ReservationPackage;

/**
 *
 * @author THALIA
 */
public class ReservationPackageDAO {

    private Connection connection;

    public ReservationPackageDAO(Connection connection) {
        this.connection = connection;
    }

    public List<ReservationPackage> list() throws SQLException {
        List<ReservationPackage> reservationPackages = new ArrayList<>();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM reservation_package");
        ResultSet rs = stmt.executeQuery();
        this.connection.close();
        while (rs.next()) {
            int reservationId = rs.getInt("reservation_id");
            int packageId = rs.getInt("package_id");
            ReservationPackage reservationPackage = new ReservationPackage(reservationId, packageId);
            reservationPackages.add(reservationPackage);
        }
        return reservationPackages;
    }

    public void create(ReservationPackage reservationPackage) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO reservation_package (reservation_id, package_id) VALUES (?, ?)");

        stmt.setInt(1, reservationPackage.getReservationId());
        stmt.setInt(2, reservationPackage.getPackageId());

        stmt.execute();
        this.connection.close();

    }

    public ReservationPackage read(int reservationPackageId) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("SSELECT * FROM reservation_package WHERE reservation_id = ? and package_id = ?");
        stmt.setInt(1, reservationPackageId);
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

    /*public void update(ReservationService reservationService) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("UPDATE reservation_services SET reservation_id = ?, service_name = ?, service_price = ? WHERE reservation_service_id = ?");
        stmt.setInt(1, reservationService.getReservationId());
        stmt.setString(2, reservationService.getServiceName());
        stmt.setFloat(3, reservationService.getServicePrice());
        stmt.setInt(4, reservationService.getReservationServiceId());
        
        stmt.execute();
        this.connection.close();
    }*/
    public void delete(int reservationId, int packageId) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM reservation_package WHERE reservation_id = ? and package_id = ?");
        stmt.setInt(1, reservationId);
        stmt.setInt(2, packageId);
        stmt.execute();
        this.connection.close();
    }
}
