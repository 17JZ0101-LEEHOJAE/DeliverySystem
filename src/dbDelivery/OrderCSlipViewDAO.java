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
import java.util.List;

/**
 *
 * @author 17jz0101 Lee Ho Jae
 */
public class OrderCSlipViewDAO {
        private static Connection           con;
        private static PreparedStatement    ps;

    public OrderCSlipViewDAO() {
        DBManagerDelivery dbManager = DBManagerDelivery.getDBManager();
        con                         = dbManager.getConnection();           
    }
        
     /**
     * OrderCSlipViewテーブル検索処理実行
     * @return 検索結果のリスト
     */
    public List<OrderCSlipView> selectOrderCSlipViewExecute() {
        List<OrderCSlipView> orderCSlipList = new ArrayList<>();        // 問い合わせ結果 OrderView
        try {            
            // 完成したSQLの実行
            ResultSet rs = ps.executeQuery();
            //　結果の表示            
            while (rs.next()) {     // 検索結果のある間繰り返す
                OrderCSlipView orderCSlipView = new OrderCSlipView();
                setOrderCSlipView(orderCSlipView, rs);          // 検索結果を1件取り出し                
                orderCSlipList.add(orderCSlipView);         // 検索結果をリストに登録
            }
            // 後片付け
            rs.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }     
        return orderCSlipList;
    }
    
   /**
     * 問い合せ結果を OrderViewに設定
     * @param orderCSlipView  問い合せ結果を格納
     * @param rs        問い合せ結果
     */
    public void setOrderCSlipView(OrderCSlipView orderCSlipView, ResultSet rs) {
        try {
            //問い合わせ結果の列情報をJavaのデータ型に受け取る
            int     orderId         = rs.getInt("ORDER_ID");
            String  itemName        = rs.getString("ITEM_NAME");
            int     orderCount      = rs.getInt("ORDERCOUNT");
            int     orderPrice      = rs.getInt("ORDERPRICE");

            orderCSlipView.setOrderId(orderId);
            orderCSlipView.setItemName(itemName);
            orderCSlipView.setOrderCount(orderCount);
            orderCSlipView.setOrderPrice(orderPrice);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<OrderCSlipView> dbSearchOrderCSlipView(int orderId) {
        List<OrderCSlipView> orderCSlipViewList = new ArrayList<>();
        String sql = "SELECT * " +
                     "FROM ORDERCSLIP_VIEW " +
                     "WHERE order_id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, orderId);
            orderCSlipViewList = selectOrderCSlipViewExecute();
//            System.out.println(orderCSlipViewList);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return orderCSlipViewList;
    }
    
    public static void main(String[] args) {
        OrderCSlipViewDAO orderCSlipViewDAO = new OrderCSlipViewDAO();
        orderCSlipViewDAO.dbSearchOrderCSlipView(10001);
    }
}
