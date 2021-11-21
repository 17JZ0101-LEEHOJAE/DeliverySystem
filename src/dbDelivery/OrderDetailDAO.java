/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbDelivery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author 17jz0101 Lee Ho Jae
 */
public class OrderDetailDAO {
        private static Connection           con;
        private static PreparedStatement    ps;

    public OrderDetailDAO() {
        DBManagerDelivery dbManager = DBManagerDelivery.getDBManager();
        con                         = dbManager.getConnection();        
    }
    
    public void insertOrderDetailExecute() {
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

    public void dbInsertOrderDetail(String itemId, int orderCount, int orderPrice) {
        String sql = "INSERT INTO ORDERDETAIL(ORDERDETAIL_ID, ITEM_ID, ORDER_ID, ORDERCOUNT, ORDERPRICE) " +
                     "VALUES(S_ORDER_DETAIL.nextval, ?, S_ORDERS.currval, ?, ?)";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, itemId);
            ps.setInt(2, orderCount);
            ps.setInt(3, orderPrice);
            insertOrderDetailExecute();
        } catch(SQLException e) {
            e.printStackTrace();
        }        
    } 
}
