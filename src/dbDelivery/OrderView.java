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
public class OrderView {
    private String  name;
    private String  tel;
    private int     point;
    private String  itemName;
    private int     orderPrice;
    private int     orderCount;
    private Date    orderDate;
    private Date    deliveryDate;
    private int     usedPoint;
    private int     totalPrice;
    
    public OrderView() {
    }

    public OrderView(String name, String tel, int point, String itemName, int orderPrice, int orderCount, Date orderDate, Date deliveryDate, int usedPoint, int totalPrice) {
        this.name = name;
        this.tel = tel;
        this.point = point;
        this.itemName = itemName;
        this.orderPrice = orderPrice;
        this.orderCount = orderCount;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.usedPoint = usedPoint;
        this.totalPrice = totalPrice;
    }
    
    @Override
    public String toString() {
        return name + ", " + tel + ", " + point + ", " + itemName + ", " + orderPrice + ", " + orderCount + ", " + 
               orderDate + ", " + deliveryDate + ", " + usedPoint + ", " + totalPrice;              
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public void setUsedPoint(int usedPoint) {
        this.usedPoint = usedPoint;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getName() {
        return name;
    }

    public String getTel() {
        return tel;
    }

    public int getPoint() {
        return point;
    }

    public String getItemName() {
        return itemName;
    }

    public int getOrderPrice() {
        return orderPrice;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public int getUsedPoint() {
        return usedPoint;
    }

    public int getTotalPrice() {
        return totalPrice;
    }
}
