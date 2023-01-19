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
import model.Room;

/**
 *
 * @author nesch
 */
public class RoomDAO {
    private Connection connection;

    public RoomDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Room> list() throws SQLException {
        List<Room> rooms = new ArrayList<>();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM rooms");
        ResultSet rs = stmt.executeQuery();
        this.connection.close();
        while (rs.next()) {
            int number = rs.getInt("room_number");
            boolean available = rs.getBoolean("available");
            int floor = rs.getInt("floor");
            int categoryId = rs.getInt("category_id");
            int reservationId = rs.getInt("reservation_id");
            Room room = new Room (number, available, floor, categoryId, reservationId);
            room.setRoomId(rs.getInt("room_id"));
            rooms.add(room);
        }
        return rooms;
    }
    
    public void create(Room room) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO room (room_id, room_number, available, floor, category_id, reservation_id) VALUES (?, ?, ?, ?, ?, ?)");
        
        stmt.setInt(1, room.getRoomId());
        stmt.setInt(2, room.getRoomNumber());
        stmt.setBoolean(3, room.isAvailable());
        stmt.setInt(4, room.getFloor());
        stmt.setInt(5, room.getCategoryId());
        stmt.setInt(6, room.getReservationId());
        
        stmt.execute();
        this.connection.close();

    }

    public Room read(int roomId) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM rooms WHERE room_id = ?");
        stmt.setInt(1, roomId);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            this.connection.close();
            int number = rs.getInt("room_number");
            boolean available = rs.getBoolean("available");
            int floor = rs.getInt("floor");
            int categoryId = rs.getInt("category_id");
            int reservationId = rs.getInt("reservation_id");
            
            Room room = new Room(number, available, floor, categoryId, reservationId);
            room.setRoomId(roomId);
            
            return room;
        }
        return null;
    }

    public void update(Room room) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("UPDATE room SET room_number = ?, available = ?, floor = ?, category_id = ?, reservation_id = ? WHERE room_id = ?");
        stmt.setInt(1, room.getRoomNumber());
        stmt.setBoolean(2, room.isAvailable());
        stmt.setInt(3, room.getFloor());
        stmt.setInt(4, room.getCategoryId());
        stmt.setInt(5, room.getReservationId());
        stmt.setInt(6, room.getRoomId());
        stmt.execute();
        this.connection.close();
    }

    public void delete(int roomId) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM rooms WHERE room_id = ?");
        stmt.setInt(1, roomId);
        stmt.execute();
        this.connection.close();
    }
}
