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
import model.Pack;

/**
 *
 * @author THALIA
 */
public class PackDAO {

    private Connection connection;

    public PackDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Pack> list() throws SQLException {
        List<Pack> packs = new ArrayList<>();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM packs");
        ResultSet rs = stmt.executeQuery();
        this.connection.close();
        while (rs.next()) {
            String packName = rs.getString("pack_name");
            float packPrice = rs.getFloat("pack_price");
            Pack pack = new Pack(packName, packPrice);
            pack.setPackId(rs.getInt("pack_id"));
            packs.add(pack);
        }
        return packs;
    }

    public void create(Pack pack) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO packs (pack_name, pack_price) VALUES (?, ?)");

            stmt.setString(1, pack.getPackName());
            stmt.setFloat(2, pack.getPackPrice());
        
            stmt.execute();
        this.connection.close();

    }

    public Pack read(int packId) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM packs WHERE pack_id = ?");
        stmt.setInt(1, packId);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            this.connection.close();
            String packName = rs.getString("pack_name");
            float packPrice = rs.getFloat("pack_price");
            Pack pack = new Pack(packName, packPrice);
            pack.setPackId(packId);

            return pack;
        }
        return null;
    }

    public void update(Pack pack) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("UPDATE packs SET pack_name = ?, pack_price = ? WHERE pack_id = ?");
        stmt.setString(1, pack.getPackName());
        stmt.setFloat(2, pack.getPackPrice());
        stmt.setInt(3, pack.getPackId());

        stmt.execute();
        this.connection.close();
    }

    public void delete(int packId) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM packs WHERE pack_id = ?");
        stmt.setInt(1, packId);
        stmt.execute();
        this.connection.close();
    }
}
