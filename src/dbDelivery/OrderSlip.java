/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbDelivery;

/**
 *
 * @author 17jz0101 Lee Ho Jae
 */
public class OrderSlip {
    private int orderId;
    private int orderSlipId;
    private int paid;

    public OrderSlip() {
    }

    public OrderSlip(int orderId, int orderSlipId, int paid) {
        this.orderId = orderId;
        this.orderSlipId = orderSlipId;
        this.paid = paid;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getOrderSlipId() {
        return orderSlipId;
    }

    public int getPaid() {
        return paid;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setOrderSlipId(int orderSlipId) {
        this.orderSlipId = orderSlipId;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }
}
