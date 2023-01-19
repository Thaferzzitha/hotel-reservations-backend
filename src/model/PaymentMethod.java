/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author THALIA
 */
public class PaymentMethod {
    private int paymentMethodId;
    private String methodName;

    public PaymentMethod(String methodName) {
        this.methodName = methodName;
    }

    public int getPaymentMethodId() {
        return paymentMethodId;
    }


    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
    
}
