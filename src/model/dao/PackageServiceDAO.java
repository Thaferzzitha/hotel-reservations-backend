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
import model.PackageService;

/**
 *
 * @author THALIA
 */
public class PackageServiceDAO {

    private Connection connection;

    public PackageServiceDAO(Connection connection) {
        this.connection = connection;
    }

    public List<PackageService> list() throws SQLException {
        List<PackageService> packageServices = new ArrayList<>();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM reservation_services");
        ResultSet rs = stmt.executeQuery();
        this.connection.close();
        while (rs.next()) {
            int packageId = rs.getInt("package_id");
                int reservationServiceId = rs.getInt("reservation_service_id");
            PackageService packageService = new PackageService(packageId, reservationServiceId);
            packageServices.add(packageService);
        }
        return packageServices;
    }

    public void create(PackageService packageService) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO package_service (package_id, reservation_service_id) VALUES (?, ?)");

        stmt.setInt(1, packageService.getPackageId());
        stmt.setInt(2, packageService.getReservationServiceId());

        stmt.execute();
        this.connection.close();

    }

    public PackageService read(int packageId, int reservationServiceId) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("SSELECT * FROM package_service WHERE package_id = ? and reservation_service_id = ?");
        stmt.setInt(1, packageId);
        stmt.setInt(2, reservationServiceId);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            this.connection.close();
            int reservationId = rs.getInt("reservation_id");
            String serviceName = rs.getString("service_name");
            float servicePrice = rs.getFloat("service_price");
            ReservationService reservationService = new ReservationService(reservationId, serviceName, servicePrice);
            reservationService.setReservationServiceId(reservationServiceId);

            return packageService;
        }
        return null;
    }

    public void update(PackageService packageService) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("UPDATE package_service SET package_id = ?, reservation_service_id = ? WHERE package_id = ? and reservation_service_id = ?");
        stmt.setInt(1, packageService.getPackageId());
        stmt.setInt(2, packageService.getReservationServiceId());
        stmt.setInt(3, packageService.getPackageId());
        stmt.setInt(4, packageService.getReservationServiceId());

        stmt.execute();
        this.connection.close();
    }

    public void delete(int packageId, int reservationServiceId) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM package_service WHERE package_id = ? and reservation_service_id = ?");
        stmt.setInt(1, packageId);
        stmt.setInt(2, reservationServiceId);
        stmt.execute();
        this.connection.close();
    }
}
