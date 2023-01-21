/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Thalia
 */
public class Pack {
    private int packId;
    private String packName;
    private float packPrice;

    public Pack(String packName, float packPrice) {
        this.packName = packName;
        this.packPrice = packPrice;
    }

    public int getPackId() {
        return packId;
    }

    public void setPackId(int packId) {
        this.packId = packId;
    }

    public String getPackName() {
        return packName;
    }

    public void setPackName(String packName) {
        this.packName = packName;
    }

    public float getPackPrice() {
        return packPrice;
    }

    public void setPackPrice(float packPrice) {
        this.packPrice = packPrice;
    }
    
    
}
