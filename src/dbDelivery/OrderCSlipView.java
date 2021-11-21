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
public class OrderCSlipView {
    private int orderId;
    private String itemName;
    private int orderCount;
    private int orderPrice;

    public OrderCSlipView() {
    }

    public OrderCSlipView(int orderId, String itemName, int orderCount, int orderPrice) {
        this.orderId = orderId;
        this.itemName = itemName;
        this.orderCount = orderCount;
        this.orderPrice = orderPrice;
    }
    
    @Override
    public String toString() {
        return orderId + ", " + itemName + ", " + orderCount + ", " + orderPrice;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public void setOrderPrice(int orderPrice) {
        this.orderPrice = orderPrice;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getItemName() {
        return itemName;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public int getOrderPrice() {
        return orderPrice;
    }
}
