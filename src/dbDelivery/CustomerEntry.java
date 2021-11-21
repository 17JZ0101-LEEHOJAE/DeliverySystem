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
public class CustomerEntry {
    private String  customer_id;
    private String  name;
    private String  tel;
    private String  address;
    private int     point;

    public CustomerEntry() {
    }

    public CustomerEntry(String customer_id, String name, String tel, String address, int point) {
        this.customer_id = customer_id;
        this.name = name;
        this.tel = tel;
        this.address = address;
        this.point = point;
    }

    public String getCustomer_id() {
        return customer_id;
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

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
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
}
