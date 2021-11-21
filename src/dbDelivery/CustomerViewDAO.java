/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbDelivery;

import dbDelivery.DBManagerDelivery;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author 17jz0101 Lee Ho Jae
 */
public class CustomerViewDAO {
    private static Connection           con;
    private static PreparedStatement    ps;
    
    public CustomerViewDAO() {
        DBManagerDelivery dbManager = DBManagerDelivery.getDBManager();
        con                         = dbManager.getConnection();
    }
    
     /**
     * CustomerViewテーブル検索処理実行
     * @return 検索結果のリスト
     */
    public List<CustomerView> selectCustomerViewExecute() {
        List<CustomerView> customerViewList = new ArrayList<>();        // 問い合わせ結果 CustomerView
        try {            
            // 完成したSQLの実行
            ResultSet rs = ps.executeQuery();
            //　結果の表示            
            while (rs.next()) {     // 検索結果のある間繰り返す
                CustomerView customerView = new CustomerView();
                setCustomerView(customerView, rs);          // 検索結果を1件取り出し                
                customerViewList.add(customerView);         // 検索結果をリストに登録
            }
            // 後片付け
            rs.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }     
        return customerViewList;
    }
    
    public void updateCustomerViewExecute() {
        try {
            int count = ps.executeUpdate();
            if(count > 0) {
                System.out.println(count + "件更新されました（変更）");
            }
            ps.close();
        } catch(SQLException e) {
             e.printStackTrace();
        }
    }
    
    
   /**
     * 問い合せ結果を CustomerViewに設定
     * @param customerView  問い合せ結果を格納
     * @param rs            問い合せ結果
     */
    public void setCustomerView(CustomerView customerView, ResultSet rs) {
        try {
            //問い合わせ結果の列情報をJavaのデータ型に受け取る
            int     customerId          = rs.getInt("CUSTOMER_ID");
            int     orderId             = rs.getInt("ORDER_ID");
            String  name                = rs.getString("NAME");
            String  tel                 = rs.getString("TEL");
            String  address             = rs.getString("ADDRESS");
            int     point               = rs.getInt("POINT");
            Timestamp    orderDate      = rs.getTimestamp("ORDERDATE");
            Timestamp    deliveryDate   = rs.getTimestamp("DELIVERYDATE");
            
            customerView.setCustomerId(customerId);
            customerView.setOrderId(orderId);
            customerView.setName(name);
            customerView.setTel(tel);
            customerView.setAddress(address);
            customerView.setPoint(point);
            customerView.setOrderDate(orderDate);
            customerView.setDeliveryDate(deliveryDate);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<CustomerView> dbSearchCustomerInformation(String name, String tel) {
        List<CustomerView> customerViewList = new ArrayList<>();
        String sql= "SELECT * " +
                    " FROM CUSTOMER_VIEW " +
                    " WHERE NAME = ? AND TEL = ?";
        
        try {
           ps = con.prepareStatement(sql);
           ps.setString(1, name);
           ps.setString(2, tel);
           customerViewList = selectCustomerViewExecute();
//           System.out.println(customerViewList);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return customerViewList;
    }
    
    public void dbUpdateDeliveryDate(Timestamp deliveryDate, int orderId) {
        String sql = "UPDATE ORDERS " + 
                     " SET DELIVERYDATE = ? " +
                     " WHERE ORDER_ID = (SELECT ORDER_ID FROM CUSTOMER_VIEW WHERE ORDER_ID = ?)";
        try {
            ps = con.prepareStatement(sql);
            System.out.println("date = [" + deliveryDate + "] orderId=[" + orderId + "]");
            ps.setTimestamp(1, deliveryDate);
            ps.setInt(2, orderId);
            updateCustomerViewExecute();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void dbUpdateCustomerPoint(int point, int customerId, int orderId) {
        String sql = "UPDATE CUSTOMER " +
                     " SET POINT = ? " +
                     " WHERE CUSTOMER_ID = (SELECT CUSTOMER_ID FROM CUSTOMER_VIEW WHERE CUSTOMER_ID = ? AND ORDER_ID = ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, point);
            ps.setInt(2, customerId);
            ps.setInt(3, orderId);
            updateCustomerViewExecute();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public void dbUpdateGrantPoint(int point, int customerId, int orderId) {
        String sql = "UPDATE CUSTOMER " +
                     " SET POINT = ? " +
                     " WHERE CUSTOMER_ID = (SELECT CUSTOMER_ID FROM CUSTOMER_VIEW WHERE CUSTOMER_ID = ? AND ORDER_ID = ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, point);
            ps.setInt(2, customerId);
            ps.setInt(3, orderId);
            updateCustomerViewExecute();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }    
    
    public static void main(String[] args) {
        CustomerViewDAO customerViewDAO = new CustomerViewDAO();
        customerViewDAO.dbSearchCustomerInformation("イシイ", "07077355541");
//        customerViewDAO.dbUpdateDeliveryDate(Date.valueOf("2018-12-08"), 10008);
    }
}
