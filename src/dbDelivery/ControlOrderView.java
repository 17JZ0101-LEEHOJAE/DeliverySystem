/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbDelivery;

import java.util.ArrayList;
import java.util.List;
import system.ControlSystem;

/**
 *
 * @author 17jz0101 Lee Ho Jae
 */
public class ControlOrderView {
    private BoundaryOrderView   boundary;
    private ControlSystem       controlSystem;
    private List<OrderView>     orderViewList;
    private OrderViewDAO        orderViewDAO;
    
    
    public ControlOrderView() {
        boundary = new BoundaryOrderView();
        orderViewList = new ArrayList<>();
        orderViewDAO = new OrderViewDAO();
    }
    
    public void setControlSystem(ControlSystem controlSystem) {
        this.controlSystem = controlSystem;
    }  
    
    public void start() {
        boundary.setControl(this);      // バウンダリにコントローラを設定
        boundary.setVisible(true);      // バウンダリを表示
    }
    
    public void showSystem() {
        boundary.setVisible(false);
        controlSystem.setVisible();
    }
    
    public void showOrderView() {
        orderViewList = orderViewDAO.dbShowOrderViewList();
        if(orderViewList.size() > 0) {
            for(OrderView orderView : orderViewList) {
                String[] rowData = orderView.toString().split(", ");
                for(int i = 0; i < rowData.length; i++) {
                    System.out.print("[" + rowData[i] + "]");
                }
                System.out.println("");
                boundary.addOrderViewTableModel(rowData);
            }
        }
    }
    
    /**
     * メインメソッド　このメインメソッドから起動する
     * @param args 
     */
    public static void main(String[] args) {
        new ControlOrderView().start();  // コントロールをnewし、start()メソッドを呼び出す
    }         
}
