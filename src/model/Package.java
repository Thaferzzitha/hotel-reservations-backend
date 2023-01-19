/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Thalia
 */
public class Package {
    private int packageId;
    private String packageName;
    private float packagePrice;

    public Package(String packageName, float packagePrice) {
        this.packageName = packageName;
        this.packagePrice = packagePrice;
    }

    public int getPackageId() {
        return packageId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public float getPackagePrice() {
        return packagePrice;
    }

    public void setPackagePrice(float packagePrice) {
        this.packagePrice = packagePrice;
    }
    
    
}
