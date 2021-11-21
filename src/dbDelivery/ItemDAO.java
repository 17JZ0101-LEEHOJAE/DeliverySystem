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
public class ItemDAO {
    private static Connection           con;
    private static PreparedStatement    ps;
    
    public ItemDAO() {
        DBManagerDelivery dbManager = DBManagerDelivery.getDBManager();
        con                         = dbManager.getConnection();
    }
    
     /**
     * Itemテーブル検索処理実行
     * @return 検索結果のリスト
     */
    public List<Item> selectItemExecute() {
        List<Item> itemList = new ArrayList<>();        // 問い合わせ結果 Item
        try {            
            // 完成したSQLの実行
            ResultSet rs = ps.executeQuery();
            //　結果の表示            
            while (rs.next()) {     // 検索結果のある間繰り返す
                Item item = new Item();
                setItem(item, rs);          // 検索結果を1件取り出し                
                itemList.add(item);         // 検索結果をリストに登録
            }
            // 後片付け
            rs.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }     
        return itemList;
    }

   /**
     * 問い合せ結果を Itemに設定
     * @param itemList  問い合せ結果を格納
     * @param rs         問い合せ結果
     */
    public void setItem(Item item, ResultSet rs) {
        try {
            //問い合わせ結果の列情報をJavaのデータ型に受け取る
            String  itemId          = rs.getString("ITEM_ID");
            String  itemName        = rs.getString("ITEM_NAME");
            int     itemPrice       = rs.getInt("ITEM_PRICE");
            
            item.setItemId(itemId);
            item.setItemName(itemName);
            item.setItemPrice(itemPrice);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Item> dbSearchItemName(String itemName) {
        List<Item> itemList = new ArrayList<>();
        String sql = "SELECT * " +
                     " FROM ITEM " +
                     " WHERE ITEM_NAME = ?";
        
        try {
           ps = con.prepareStatement(sql);
           ps.setString(1, itemName);
           itemList = selectItemExecute();
//           System.out.println(itemList);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return itemList;
    }
    
    public static void main(String[] args) {
        ItemDAO itemDAO = new ItemDAO();
        itemDAO.dbSearchItemName("トリピミックス");
    }
}
