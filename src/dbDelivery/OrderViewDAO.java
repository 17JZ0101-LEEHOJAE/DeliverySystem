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
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 17jz0101 Lee Ho Jae
 */
public class OrderViewDAO {
    private static Connection           con;
    private static PreparedStatement    ps;
    
    public OrderViewDAO() {
        DBManagerDelivery dbManager = DBManagerDelivery.getDBManager();
        con                         = dbManager.getConnection();
    }
    
     /**
     * OrderViewテーブル検索処理実行
     * @return 検索結果のリスト
     */
    public List<OrderView> selectOrderViewExecute() {
        List<OrderView> orderViewList = new ArrayList<>();        // 問い合わせ結果 OrderView
        try {            
            // 完成したSQLの実行
            ResultSet rs = ps.executeQuery();
            //　結果の表示            
            while (rs.next()) {     // 検索結果のある間繰り返す
                OrderView orderView = new OrderView();
                setOrderView(orderView, rs);          // 検索結果を1件取り出し                
                orderViewList.add(orderView);         // 検索結果をリストに登録
            }
            // 後片付け
            rs.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }     
        return orderViewList;
    }

   /**
     * 問い合せ結果を OrderViewに設定
     * @param orderView  問い合せ結果を格納
     * @param rs        問い合せ結果
     */
    public void setOrderView(OrderView orderView, ResultSet rs) {
        try {
            //問い合わせ結果の列情報をJavaのデータ型に受け取る
            String  name            = rs.getString("NAME");
            String  tel             = rs.getString("TEL");
            int     point           = rs.getInt("POINT");
            String  itemName        = rs.getString("ITEM_NAME");
            int     orderPrice      = rs.getInt("ORDERPRICE");
            int     orderCount      = rs.getInt("ORDERCOUNT");
            Date    orderDate       = rs.getDate("ORDERDATE");
            Date    deliveryDate    = rs.getDate("DELIVERYDATE");
            int     usedPoint       = rs.getInt("USEDPOINT");
            int     totalPrice      = rs.getInt("TOTALPRICE");
            
            orderView.setName(name);
            orderView.setTel(tel);
            orderView.setPoint(point);
            orderView.setItemName(itemName);
            orderView.setOrderPrice(orderPrice);
            orderView.setOrderCount(orderCount);
            orderView.setOrderDate(orderDate);
            orderView.setDeliveryDate(deliveryDate);
            orderView.setUsedPoint(usedPoint);
            orderView.setTotalPrice(totalPrice);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<OrderView> dbShowOrderViewList() {
        List<OrderView> orderViewList = new ArrayList<>();
        String sql = "SELECT * " +
                     " FROM ORDERS_VIEW";
        
        try {
           ps = con.prepareStatement(sql);
           orderViewList = selectOrderViewExecute();
//           System.out.println(orderViewList);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return orderViewList;
    }
    
    public static void main(String[] args) {
        OrderViewDAO orderViewDAO = new OrderViewDAO();
        orderViewDAO.dbShowOrderViewList();
    }
}
