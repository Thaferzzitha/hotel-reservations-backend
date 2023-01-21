/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Thalia
 */
public class PackService {
    private int packId;
    private int reservationServiceId;

    public PackService(int packId, int reservationServiceId) {
        this.packId = packId;
        this.reservationServiceId = reservationServiceId;
    }

    public int getPackId() {
        return packId;
    }

    public void setPackId(int packId) {
        this.packId = packId;
    }

    public int getReservationServiceId() {
        return reservationServiceId;
    }

    public void setReservationServiceId(int reservationServiceId) {
        this.reservationServiceId = reservationServiceId;
    }
    
    
}
