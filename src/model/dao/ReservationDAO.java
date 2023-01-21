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
import model.Reservation;

/**
 *
 * @author THALIA
 */
public class ReservationDAO {

    private Connection connection;

    public ReservationDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Reservation> list() throws SQLException {
        List<Reservation> reservations = new ArrayList<>();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM reservations");
        ResultSet rs = stmt.executeQuery();
        this.connection.close();
        while (rs.next()) {
            int roomId = rs.getInt("room_id");
            String checkIn = rs.getString("check_in");
            String checkOut = rs.getString("check_out");
            String state = rs.getString("state");
            Reservation reservation = new Reservation(roomId, checkIn, checkOut, state);
            reservation.setReservationId(rs.getInt("reservation_id"));
            reservations.add(reservation);
        }
        return reservations;
    }

    public void create(Reservation reservation) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO reservations (room_id, check_in, check_out, state) VALUES (?, ?, ?, ?)");

        stmt.setInt(1, reservation.getRoomId());
        stmt.setString(2, reservation.getCheckIn());
        stmt.setString(3, reservation.getCheckOut());
        stmt.setString(4, reservation.getState());

        stmt.execute();
        this.connection.close();

    }

    public Reservation read(int reservationId) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("ELECT * FROM reservations WHERE reservation_id = ?");
        stmt.setInt(1, reservationId);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            this.connection.close();
            int roomId = rs.getInt("room_id");
            String checkIn = rs.getString("check_in");
            String checkOut = rs.getString("check_out");
            String state = rs.getString("state");
            Reservation reservation = new Reservation(reservationId, roomId, checkIn, checkOut, state);
            reservation.setReservationId(reservationId);

            return reservation;
        }
        return null;
    }

    public void update(Reservation reservation) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("UPDATE reservations SET room_id = ?, check_in = ?, check_out = ?, state = ? WHERE reservation_id = ?");
        stmt.setInt(1, reservation.getRoomId());
        stmt.setString(2, reservation.getCheckIn());
        stmt.setString(3, reservation.getCheckOut());
        stmt.setString(4, reservation.getState());
        stmt.setInt(5, reservation.getReservationId());

        stmt.execute();
        this.connection.close();
    }

    public void delete(int reservationId) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM reservations WHERE reservation_id = ?");
        stmt.setInt(1, reservationId);
        stmt.execute();
        this.connection.close();
    }
}
