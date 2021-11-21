/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbDelivery;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author 17jz0101 Lee Ho Jae
 */
public class ControlSearchCustomer {
    private BoundarySearchCustomer  boundary;
    private ControlOrder            controlOrder;
    private Customer                customer;
    private List<CustomerView>      customerViewList;
    private CustomerViewDAO         customerViewDAO;
    
    public ControlSearchCustomer() {
        boundary = new BoundarySearchCustomer();
        customerViewList = new ArrayList<>();
        customerViewDAO = new CustomerViewDAO();
    }
    
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    public void setControlOrder(ControlOrder controlOrder) {
        this.controlOrder = controlOrder;
    }
    
    public void start() {
        boundary.setControl(this);   // バウンダリにコントローラを設定
        boundary.setVisible(true);      // バウンダリを表示
        showCustomerInformation();
    }
    
    public void showCustomerInformation() {
        customerViewList = customerViewDAO.dbSearchCustomerInformation(customer.getName(), customer.getTel());
        if(customerViewList.size() > 0) {
            String  customerName    = customerViewList.get(0).getName();
            String  customerTel     = customerViewList.get(0).getTel();
            String  customerAddress = customerViewList.get(0).getAddress();
            int     customerPoint   = customerViewList.get(0).getPoint();
            boundary.showCustomer(customerName, customerTel, customerAddress, customerPoint);
            controlOrder.setPoint(customerPoint);
            controlOrder.setCustomerInformationSlip(customerName, customerTel, customerAddress, customerPoint);
        } else {
            boundary.showCustomer(customer.getName(), customer.getTel(), customer.getAddress(), customer.getPoint());            
            controlOrder.searchEntryCustomerInformation(customer.getName(), customer.getTel());
            controlOrder.notCheckCustomer();
        }
    }
    
    public void updateDeliveryDate(Timestamp deliveryDate) {
        customerViewDAO.dbUpdateDeliveryDate(deliveryDate, customerViewList.get(0).getOrderId());
        JOptionPane.showMessageDialog(boundary, "配達希望日時が変更されました", "更新メッセージ", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void showSearchTable() {
        for(CustomerView customerView : customerViewList) {
            String[] rowData = customerView.toString().split(",");
            for(int i = 0; i < rowData.length; i++) {
//                System.out.print("[" + rowData[i] + "]");
            }
            System.out.println("");
            String[] temp    = {rowData[6], rowData[7]};
            boundary.addSearchTable(temp);
        }
    }
    
    public void okSearchCustomer() {
        boundary.setVisible(false);
        showCustomerInformation();
        controlOrder.setVisible();
    }
    
    /**
     * メインメソッド　このメインメソッドから起動する
     * @param args 
     */
    public static void main(String[] args) {
        new ControlSearchCustomer().start();  // コントローラをnewし、start()メソッドを呼び出す
    }   
}
