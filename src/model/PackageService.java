/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Thalia
 */
public class PackageService {
    private int packageId;
    private int reservationServiceId;

    public PackageService(int packageId, int reservationServiceId) {
        this.packageId = packageId;
        this.reservationServiceId = reservationServiceId;
    }

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public int getReservationServiceId() {
        return reservationServiceId;
    }

    public void setReservationServiceId(int reservationServiceId) {
        this.reservationServiceId = reservationServiceId;
    }
    
    
}
