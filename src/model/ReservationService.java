/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Thalia
 */
public class ReservationService {
    private int reservationServiceId;
    private int reservationId;
    private String serviceName;
    private float servicePrice;

    public ReservationService(int reservationId, String serviceName, float servicePrice) {
        this.reservationId = reservationId;
        this.serviceName = serviceName;
        this.servicePrice = servicePrice;
    }

    public int getReservationServiceId() {
        return reservationServiceId;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public float getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(float servicePrice) {
        this.servicePrice = servicePrice;
    }
}
