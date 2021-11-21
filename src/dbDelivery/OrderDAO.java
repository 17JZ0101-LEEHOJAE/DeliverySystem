/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbDelivery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 17jz0101 Lee Ho Jae
 */
public class OrderDAO {
        private static Connection           con;
        private static PreparedStatement    ps;

    public OrderDAO() {
        DBManagerDelivery dbManager = DBManagerDelivery.getDBManager();
        con                         = dbManager.getConnection();        
    }
    
     /**
     * Orderテーブル検索処理実行
     * @return 検索結果のリスト
     */
    public int selectOrderIdExecute() {
       // 問い合わせ結果 Customer
        int count = 0;
        try {            
            // 完成したSQLの実行
            ResultSet rs = ps.executeQuery();
            //　結果の表示            
            while (rs.next()) {     // 検索結果のある間繰り返す
                System.out.println(count + "件見つかりました");
                count = rs.getInt("ORDER_ID");
            }
            // 後片付け
            rs.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public int dbSearchOrderId() {
        String sql = "SELECT S_ORDERS.currval as order_Id " +
                     "FROM dual";
        int num = 0;
        try {
            ps = con.prepareStatement(sql);
            num = selectOrderIdExecute();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return num; 
    }
    
    public void insertOrderExecute() {
        try {
            int count = ps.executeUpdate();
            if(count > 0) {
                System.out.println(count + "件更新されました（登録）");
            }
            ps.close();
        } catch(SQLException e) {
             e.printStackTrace();
        }
    }

    public void dbInsertNewOrder(int customerId, int totalPrice, Timestamp deliveryDate, int usedPoint) {
        String sql = "INSERT INTO ORDERS(ORDER_ID, CUSTOMER_ID, ORDERDATE, TOTALPRICE, DELIVERYDATE, USEDPOINT) " +
                     "VALUES(S_ORDERS.nextval, ?, SYSDATE, ?, ?, ?)";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, customerId);
            ps.setInt(2, totalPrice);
            ps.setTimestamp(3, deliveryDate);
            ps.setInt(4, usedPoint);
            insertOrderExecute();
        } catch(SQLException e) {
            e.printStackTrace();
        }        
    }
}
