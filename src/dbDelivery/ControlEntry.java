/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbDelivery;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import system.ControlSystem;

/**
 *
 * @author 17jz0101 Lee Ho Jae
 */
public class ControlEntry {
    private BoundaryEntry               boundary;
    private List<CustomerEntry>         customerEntryList;
    private List<Customer>              customerList;
    private CustomerEntryDAO            customerEntryDAO;
    private CustomerDAO                 customerDAO;    
    private ControlOrder                controlOrder;
    
    public ControlEntry() {
        boundary = new BoundaryEntry();
        customerEntryList = new ArrayList<>();
        customerEntryDAO = new CustomerEntryDAO();
    }
    
    public void setControlOrder(ControlOrder controlOrder) {
        this.controlOrder = controlOrder;
    }    

    public void start() {
        boundary.setControl(this);   // バウンダリにコントローラを設定
        boundary.setVisible(true);      // バウンダリを表示
    }
    
    public void cancelEntryCustomer() {
        boundary.setVisible(false);
        controlOrder.setVisibleCancel();
    }    
    
    public void showEntryCustomerInformation(String name, String tel) {
        boundary.showEntryCustomer(name, tel);
    }
    
    public void insertEntryCustomer(String name, String tel, String address) {
        customerEntryDAO.dbInsertCustomerEntry(name, tel, address);
        JOptionPane.showMessageDialog(boundary , name + "様の情報が登録されました", "登録完了", JOptionPane.INFORMATION_MESSAGE);
        
        JOptionPane.showMessageDialog(boundary, "注文受付画面に移動します", "通知メッセージ", JOptionPane.INFORMATION_MESSAGE);
        
        boundary.setVisible(false);
        controlOrder.searchEntryCustomerInformation(name, tel);
        controlOrder.setVisible();
        controlOrder.notCheckCustomer();
    }
    
    /**
     * メインメソッド　このメインメソッドから起動する
     * @param args 
     */
    public static void main(String[] args) {
        new ControlOrder().start();  // コントローラをnewし、start()メソッドを呼び出す
    }    
}
