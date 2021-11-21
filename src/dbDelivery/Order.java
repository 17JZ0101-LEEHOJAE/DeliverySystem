/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbDelivery;

import java.util.Date;

/**
 *
 * @author 17jz0101 Lee Ho Jae
 */
public class Order {
    private int orderId;
    private int customerId;
    private Date orderDate;
    private int totalPrice;
    private Date deliveryDate;
    private int usedPoint;

    public Order() {
    }

    public Order(int orderId, int customerId, Date orderDate, int totalPrice, Date deliveryDate, int usedPoint) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.deliveryDate = deliveryDate;
        this.usedPoint = usedPoint;
    }
    
    @Override
    public String toString() {
        return orderId + ", " + customerId + ", " + orderDate + ", " + totalPrice + ", " +
               deliveryDate + ", " + usedPoint;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public void setUsedPoint(int usedPoint) {
        this.usedPoint = usedPoint;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public int getUsedPoint() {
        return usedPoint;
    }
}
