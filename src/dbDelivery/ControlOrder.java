/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dbDelivery;

import system.ControlSystem;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * 
 * @author 17jz0101 Lee Ho Jae
 */
public class ControlOrder {
    private BoundaryOrder           boundary;
    private ControlSystem           controlSystem;
    private ControlSearchCustomer   controlSearch;
    private ControlEntry            controlEntry;
    private ControlOrderCSlip       controlOrderCSlip;
    private List<OrderView>         orderViewList;    
    private List<Customer>          customerList;
    private List<CustomerView>      customerViewList;
    private List<Item>              itemList;
    private OrderDAO                orderDAO;
    private OrderViewDAO            orderViewDAO;
    private OrderDetailDAO          orderDetailDAO;
    private OrderSlipDAO            orderSlipDAO;
    private CustomerDAO             customerDAO;
    private CustomerViewDAO         customerViewDAO;
    private ItemDAO                 itemDAO;
    private Customer                customer;    
    
    public ControlOrder() {
        boundary = new BoundaryOrder();
        orderViewList = new ArrayList<>();
        customerList = new ArrayList<>();
        customerViewList = new ArrayList<>();
        itemList = new ArrayList<>();
        orderDAO = new OrderDAO();
        orderViewDAO = new OrderViewDAO();
        orderDetailDAO = new OrderDetailDAO();
        orderSlipDAO = new OrderSlipDAO();
        customerDAO = new CustomerDAO();
        customerViewDAO = new CustomerViewDAO();
        itemDAO = new ItemDAO();
        controlSearch = new ControlSearchCustomer();
        controlEntry = new ControlEntry();
        controlOrderCSlip = new ControlOrderCSlip();
    }
    
    public void setControlSystem(ControlSystem controlSystem) {
        this.controlSystem = controlSystem;
    }    
    
    public void setVisible() {
        boundary.setVisible(true);
        boundary.setControl(this);
        boundary.setEnabled();
        boundary.showDeliveryDate();
    }
    
    public void setVisibleCancel() {
        boundary.setVisible(true);
        boundary.setControl(this);
    }
    
    public void start() {
        boundary.setControl(this);
        boundary.setVisible(true);
        controlSearch.setControlOrder(this);
        controlEntry.setControlOrder(this);
        controlOrderCSlip.setControlOrder(this);
    }
    
    public void allNew() {
        orderViewList = new ArrayList<>();
        customerList = new ArrayList<>();
        customerViewList = new ArrayList<>();
        itemList = new ArrayList<>();
        orderDAO = new OrderDAO();
        orderViewDAO = new OrderViewDAO();
        orderDetailDAO = new OrderDetailDAO();
        orderSlipDAO = new OrderSlipDAO();
        customerDAO = new CustomerDAO();
        customerViewDAO = new CustomerViewDAO();
        itemDAO = new ItemDAO();
        controlSearch = new ControlSearchCustomer();
        controlEntry = new ControlEntry();
        controlOrderCSlip = new ControlOrderCSlip();
        boundary.setControl(this);        
        controlSearch.setControlOrder(this);
        controlEntry.setControlOrder(this);
        controlOrderCSlip.setControlOrder(this);
    }
    
    public void customerNew() {
        customerList = new ArrayList<>();
        customerViewList = new ArrayList<>();
        customerDAO = new CustomerDAO();
        customerViewDAO = new CustomerViewDAO();        
    }
    
    public void showSystem() {
        boundary.setVisible(false);
        controlSystem.setVisible();
    }
    
    public void showSearchCustomer(String name, String tel) {
        customerViewList = customerViewDAO.dbSearchCustomerInformation(name, tel);
        customerList     = customerDAO.dbSearchCustomer(name, tel);
        if(customerViewList.size() >= 1) {
            int value = JOptionPane.showConfirmDialog(boundary, name + " 様の情報が確認できました \n" +
                                          "顧客確認画面に移しますか？", "確認メッセージ", JOptionPane.YES_NO_OPTION);
            if(value == (JOptionPane.YES_OPTION)) {
                boundary.setVisible(false);
                controlSearch.setCustomer(customerList.get(0));
                controlSearch.start();
            } else if(value == JOptionPane.NO_OPTION) {
                searchOrderCustomerInformation(name, tel);
                boundary.setEnabled();
                boundary.showDeliveryDate();
            }
        } else if(customerList.size() > 0) { 
            int value = JOptionPane.showConfirmDialog(boundary, name + " 様の情報が確認できました \n" +
                                          "顧客確認画面に移しますか？", "確認メッセージ", JOptionPane.YES_NO_OPTION);
            if(value == (JOptionPane.YES_OPTION)) {
                boundary.setVisible(false);
                controlSearch.setCustomer(customerList.get(0));
                controlSearch.start();
            } else if(value == JOptionPane.NO_OPTION) {
                searchEntryCustomerInformation(name, tel);
                boundary.setEnabled();
                notCheckCustomer();                
                boundary.showDeliveryDate();
            }
        }
    }
    
    public void showEntryCustomer(String name, String tel) {
        customerList = customerDAO.dbSearchCustomer(name, tel);
        if(customerList.size() < 1) {
            JOptionPane.showMessageDialog(boundary, name + " 様の情報が見つかりません", "エラーメッセージ",
                                          JOptionPane.ERROR_MESSAGE);
            int value = JOptionPane.showConfirmDialog(boundary, "顧客登録画面に移しますか？", "確認メッセージ", JOptionPane.YES_NO_OPTION);
            if(value == (JOptionPane.YES_OPTION)) {
                boundary.setVisible(false);
                controlEntry.showEntryCustomerInformation(name, tel);
                controlEntry.start();
            }            
        }
    }
    
    public void showOrderCSlip() {
        boundary.setVisible(false);
        controlOrderCSlip.start();
    }
    
    public void insertNewOrder(String name, String tel, int totalPrice, Timestamp deliveryDate, int usedPoint) {
        customerViewList = customerViewDAO.dbSearchCustomerInformation(name, tel);
        if(customerViewList.size() > 0) {
            int customerId = customerViewList.get(0).getCustomerId();
            orderDAO.dbInsertNewOrder(customerId, totalPrice, deliveryDate, usedPoint);
        }
    }
    
    public void insertNewCustomerOrder(String name, String tel, int totalPrice, Timestamp deliveryDate, int usedPoint) {
        customerList = customerDAO.dbSearchCustomer(name, tel);
        if(customerList.size() > 0) {
            int newCustomerId = customerList.get(0).getCustomerId();
            orderDAO.dbInsertNewOrder(newCustomerId, totalPrice, deliveryDate, usedPoint);
        }
    }
    
    public void insertOrderDetail(String itemId ,int orderCount, int orderPrice) {
        orderDetailDAO.dbInsertOrderDetail(itemId, orderCount, orderPrice);
    }
    
    public void insertOrderSlip() {
        orderSlipDAO.dbInsertOrderSlip();
    }
    
    public void updatePointAfterUsing(String name, String tel) {
        customerViewList = customerViewDAO.dbSearchCustomerInformation(name, tel);
        if(customerViewList.size() > 0) {
            int customerId = customerViewList.get(0).getCustomerId();
            int orderId = customerViewList.get(0).getOrderId();
            int currentPoint = boundary.getPointAfterUsing();
            customerViewDAO.dbUpdateCustomerPoint(currentPoint, customerId, orderId);            
        }
    }
    
    public void updateCustomerPointGrant(String name, String tel, int point) {
        customerViewList = customerViewDAO.dbSearchCustomerInformation(name, tel);
        if(customerViewList.size() > 0) {
            int possessionPoint = customerViewList.get(0).getPoint();
            int grantPoint = point;
            possessionPoint += grantPoint;
            int orderId = customerViewList.get(0).getOrderId();
            int customerId = customerViewList.get(0).getCustomerId();
            customerViewDAO.dbUpdateGrantPoint(possessionPoint, customerId, orderId);
        }
    }
    
    public void searchOrderCustomerInformation(String name, String tel) {
        customerNew();
        customerViewList = customerViewDAO.dbSearchCustomerInformation(name, tel);
        if(customerViewList.size() > 0) {
            String  customerName    = customerViewList.get(0).getName();
            String  customerTel     = customerViewList.get(0).getTel();
            String  customerAddress = customerViewList.get(0).getAddress();
            int     customerPoint   = customerViewList.get(0).getPoint();            
            setPoint(customerPoint);
            setCustomerInformationSlip(customerName, customerTel, customerAddress, customerPoint);
        } 
    }
    
    public void searchEntryCustomerInformation(String name, String tel) {
        customerNew();
        customerList = customerDAO.dbSearchCustomer(name, tel);
        if(customerList.size() > 0) {
            String  entryName       = customerList.get(0).getName();
            String  entryTel        = customerList.get(0).getTel();
            String  entryAddress    = customerList.get(0).getAddress();
            int     entryPoint      = customerList.get(0).getPoint();
            setPoint(entryPoint);
            setCustomerInformationSlip(entryName, entryTel, entryAddress, entryPoint);
        }
    }

    public void notCheckCustomer() {
        boundary.CheckCustomer();
    }
            
    public void setPoint(int point) {
        boundary.showPoint(point);
    }

    public int setItemPrice(String itemName) {
        int itemPrice = 0;
        itemList = itemDAO.dbSearchItemName(itemName);
        if(itemList.size() >= 0) {
            if(itemName.equals("10%割引")) {
                itemPrice = (int) Math.floor(((boundary.getTotalPrice() * 0.9) - boundary.getTotalPrice()));
            } else {
                itemPrice = itemList.get(0).getItemPrice();                
            }
        }
        return itemPrice;
    }
    
    public String getItemId(String itemName) {
        itemList = itemDAO.dbSearchItemName(itemName);
        String itemId = "";
        if(itemList.size() >= 0) {
            itemId = itemList.get(0).getItemId();
        }
        return itemId;
    }
    
    public void addOrderItemData(String inputData) {
        String[] rowData = inputData.split(", ", 4);
        
        int i;
        for(i = 0; i < rowData.length - 1; i++) {
//            System.out.print(rowData[i] + " : ");
        }
//        System.out.println(rowData[i]);
        boundary.addOrderTableModel(rowData);
    }
    
    public void setCustomerInformationSlip(String name, String tel, String address, int point) {
        Timestamp orderDates = boundary.getOrderDates();
        controlOrderCSlip.setCustomerSlip(name, tel, address, point, orderDates);
    }
    
    public void setOrderPriceInformationSlip(int usingPoint, int subTotalPrice, int totalPrice, int grantPoint) {
        controlOrderCSlip.setOrderPriceSlip(usingPoint, subTotalPrice, totalPrice, grantPoint);
    }
    
    public int getOrderId() {
        int orderId = orderDAO.dbSearchOrderId();
        return orderId;
    }
    
     /**
     * メインメソッド　このメインメソッドから起動する
     * @param args 
     */
    public static void main(String[] args) {
        new ControlOrder().start();  // コントローラをnewし、start()メソッドを呼び出す
    }   
}
