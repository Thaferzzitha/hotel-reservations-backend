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
import model.Package;

/**
 *
 * @author THALIA
 */
public class PackageDAO {

    private Connection connection;

    public PackageDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Package> list() throws SQLException {
        List<Package> packages = new ArrayList<>();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM packages");
        ResultSet rs = stmt.executeQuery();
        this.connection.close();
        while (rs.next()) {
            String packageName = rs.getString("package_name");
            float packagePrice = rs.getFloat("package_price");
            Package package = new Package(packageName, packagePrice);
            package.setPackageId(rs.getInt("package_id"));
            packages.add(package);
        }
        return packages;
    }

    public void create(Package package) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO packages (package_name, package_price) VALUES (?, ?)");

            stmt.setString(1, package.getPackageName());
            stmt.setFloat(2, package.getPackagePrice());
        
            stmt.execute();
        this.connection.close();

    }

    public Package read(int packageId) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM payments WHERE payment_id = ?");
        stmt.setInt(1, packageId);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            this.connection.close();
            String packageName = rs.getString("package_name");
            float packagePrice = rs.getFloat("package_price");
            Package package = new Package(packageName, packagePrice);
            package.setPackageId(packageId);

            return package;
        }
        return null;
    }

    public void update(Package package) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("UPDATE package SET package_name = ?, package_price = ? WHERE package_id = ?");
        stmt.setString(1, package.getPackageName());
        stmt.setFloat(2, package.getPackagePrice());
        stmt.setInt(3, package.getPackageId());

        stmt.execute();
        this.connection.close();
    }

    public void delete(int packageId) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM package WHERE package_id = ?");
        stmt.setInt(1, packageId);
        stmt.execute();
        this.connection.close();
    }
}
