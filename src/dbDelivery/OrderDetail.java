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
public class OrderDetail {
    private int orderDetailId;
    private String itemId;
    private int orderId;
    private int orderCount;
    private int orderPrice;

    public OrderDetail() {
    }

    public OrderDetail(int orderDetailId, String itemId, int orderId, int orderCount, int orderPrice) {
        this.orderDetailId = orderDetailId;
        this.itemId = itemId;
        this.orderId = orderId;
        this.orderCount = orderCount;
        this.orderPrice = orderPrice;
    }
    
    @Override
    public String toString() {
        return orderDetailId + ", " + itemId + ", " + orderId + ", " + orderCount + ", " + orderPrice;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public String getItemId() {
        return itemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public int getOrderPrice() {
        return orderPrice;
    }
}
