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
public class Item {
    private String  itemId;
    private String  itemName;
    private int     itemPrice;

    public Item() {
    }

    public Item(String itemId, String itemName, int itemPrice) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }
    
    @Override
    public String toString() {
        return itemId + ", " + itemName + ", " + itemPrice;
    }
    
    public void print() {
        System.out.print(toString());
    }
    
    public String getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }
}
