/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbDelivery;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 17jz0101 Lee Ho Jae
 */
public class Customer {
    private int customerId;
    private String name;
    private String tel;
    private String address;
    private int point;

    public Customer() {
    }

    public Customer(int customerId, String name, String tel, String address, int point) {
        this.customerId = customerId;
        this.name = name;
        this.tel = tel;
        this.address = address;
        this.point = point;
    }

    @Override
    public String toString() {
        return customerId + ", " + name + ", " + tel + ", " + address + ", " + point;
    }    

    public int getCustomerId() {
        return customerId;
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

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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
    
    public void print() {
        System.out.print(toString());
    }
    
    public void println() {
        print();
        System.out.println("");
    }
}
