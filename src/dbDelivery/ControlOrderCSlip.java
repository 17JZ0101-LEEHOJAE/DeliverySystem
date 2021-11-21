/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbDelivery;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 17jz0101 Lee Ho Jae
 */
public class ControlOrderCSlip {
    private BoundaryOrderCSlip      boundary;
    private ControlOrder            controlOrder;
    private List<OrderCSlipView>    orderCSlipViewList;
    private OrderCSlipViewDAO       orderCSlipViewDAO;

    public ControlOrderCSlip() {
        boundary = new BoundaryOrderCSlip();
        orderCSlipViewList = new ArrayList<>();
        orderCSlipViewDAO = new OrderCSlipViewDAO();
    }
    
     public void setControlOrder(ControlOrder controlOrder) {
        this.controlOrder = controlOrder;
    }

    public void setVisible() {
        boundary.setVisible(true);
        boundary.setControl(this);
    }    
    
    public void start() {
        boundary.setControl(this);
        boundary.setVisible(true);
    }
    
    public void setCustomerSlip(String name, String tel, String address, int point, Timestamp orderDates) {
        boundary.setInformationCustomerSlip(name, tel, address, point, orderDates);
    }
    
    public void setOrderPriceSlip(int usingPoint, int subTotalPrice, int totalPrice, int grantPoint) {
        boundary.setInformationOrderPriceSlip(usingPoint, subTotalPrice, totalPrice, grantPoint);
    }
    
    public void showOrderCSlipView() {
        int orderId = controlOrder.getOrderId();
        orderCSlipViewList = orderCSlipViewDAO.dbSearchOrderCSlipView(orderId);
        if(orderCSlipViewList.size() > 0) {
            for(OrderCSlipView orderCSlipView : orderCSlipViewList) {
                String[] rowData = orderCSlipView.toString().split(", ");
                for(int i = 0; i < rowData.length; i++) {
                    System.out.print("[" + rowData[i] + "]");
                }
                String[] tempName       = {rowData[1]};
                String[] tempCount      = {rowData[2]};
                String[] tempPrice      = {rowData[3]};
                boundary.addListOrderCSlipName(tempName);
                boundary.addListOrderCSlipCount(tempCount);
                boundary.addListOrderCSlipPrice(tempPrice);
            }
        }
    }
    
    public void showOrderReception() {
        boundary.setVisible(false);
        controlOrder.setVisibleCancel();
    }
    
    /**
     * メインメソッド　このメインメソッドから起動する
     * @param args 
     */
    public static void main(String[] args) {
        new ControlOrderCSlip().start();  // コントロールをnewし、start()メソッドを呼び出す
    }          
}
