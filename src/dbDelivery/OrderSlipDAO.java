/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbDelivery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author 17jz0101 Lee Ho Jae
 */
public class OrderSlipDAO {
    private static Connection           con;
    private static PreparedStatement    ps;

    public OrderSlipDAO() {
        DBManagerDelivery dbManager = DBManagerDelivery.getDBManager();
        con                         = dbManager.getConnection();        
    }

     /**
     * OrderSlipテーブル検索処理実行
     * @return 検索結果のリスト
     */
    public List<OrderSlip> selectOrderViewExecute() {
        List<OrderSlip> orderSlipList = new ArrayList<>();        // 問い合わせ結果 OrderSlip
        try {            
            // 完成したSQLの実行
            ResultSet rs = ps.executeQuery();
            //　結果の表示            
            while (rs.next()) {     // 検索結果のある間繰り返す
                OrderSlip orderSlip = new OrderSlip();
                setOrderSlip(orderSlip, rs);          // 検索結果を1件取り出し                
                orderSlipList.add(orderSlip);         // 検索結果をリストに登録
            }
            // 後片付け
            rs.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }     
        return orderSlipList;
    }

   /**
     * 問い合せ結果を OrderSlipに設定
     * @param orderView  問い合せ結果を格納
     * @param rs        問い合せ結果
     */
    public void setOrderSlip(OrderSlip orderSlip, ResultSet rs) {
        try {
            //問い合わせ結果の列情報をJavaのデータ型に受け取る
            int orderId = rs.getInt("ORDER_ID");
            int orderSlipId = rs.getInt("ORDERSLIP_ID");
            int paid = rs.getInt("PAID");
           
            orderSlip.setOrderId(orderId);
            orderSlip.setOrderSlipId(orderSlipId);
            orderSlip.setPaid(paid);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void insertOrderSlipExecute() {
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
    
    public List<OrderSlip> dbShowOrderSlip() {
        List<OrderSlip> orderSlipList = new ArrayList<>();
        String sql = "SELECT * " +
                     " FROM ORDERSLIP";

        try {
           ps = con.prepareStatement(sql);
           orderSlipList = selectOrderViewExecute();
           System.out.println(orderSlipList);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return orderSlipList;
    }
    
    public void dbInsertOrderSlip() {
        String sql = "INSERT INTO ORDERSLIP(ORDER_ID, ORDERSLIP_ID, PAID) " +
                     "VALUES(S_ORDERS.currval, S_ORDER_SLIP.nextval, 0)";
        try {
            ps = con.prepareStatement(sql);
            insertOrderSlipExecute();
        } catch(SQLException e) {
            e.printStackTrace();
        }         
    }
}
