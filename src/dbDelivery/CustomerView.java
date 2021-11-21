/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbDelivery;

import java.sql.Timestamp;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author 17jz0101 Lee Ho Jae
 */
public class CustomerView {
    private int     customerId;
    private int     orderId;
    private String  name;
    private String  tel;
    private String  address;
    private int     point;
    private Timestamp    orderDate;
    private Timestamp    deliveryDate;

    public CustomerView() {
    }

    public CustomerView(int customerId, int orderId, String name, String tel, String address, int point, Timestamp orderDate, Timestamp deliveryDate) {
        this.customerId = customerId;
        this.orderId = orderId;
        this.name = name;
        this.tel = tel;
        this.address = address;
        this.point = point;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
    }

    @Override
    public String toString() {
        return customerId + ", " + orderId + ", " + name + ", " + tel + "," + address + ", " + point + ", "
               + orderDate + ", " + deliveryDate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getName() {
        return name;
    }

    public String getTel() {
        return tel;
    }

    public String getAddress() {
        return address;
    }

    public int getPoint() {
        return point;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public Timestamp getDeliveryDate() {
        return deliveryDate;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public void setDeliveryDate(Timestamp deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}
