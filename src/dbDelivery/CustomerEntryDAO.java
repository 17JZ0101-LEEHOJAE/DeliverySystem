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
public class CustomerEntryDAO {
    private static Connection           con;
    private static PreparedStatement    ps;
    
    public CustomerEntryDAO() {
        DBManagerDelivery dbManager = DBManagerDelivery.getDBManager();
        con                         = dbManager.getConnection();
    }
    
     /**
     * Customerテーブル検索処理実行
     * @return 検索結果のリスト
     */
    public List<CustomerEntry> selectCustomerEntryExecute() {
        List<CustomerEntry> customerEntryList = new ArrayList<>();        // 問い合わせ結果 CustomerEntry
        try {            
            // 完成したSQLの実行
            ResultSet rs = ps.executeQuery();
            //　結果の表示            
            while (rs.next()) {     // 検索結果のある間繰り返す
                CustomerEntry customerEntry = new CustomerEntry();
                setCustomerEntry(customerEntry, rs);          // 検索結果を1件取り出し                
                customerEntryList.add(customerEntry);         // 検索結果をリストに登録
            }
            // 後片付け
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return customerEntryList;
    }    
    
   /**
     * 問い合せ結果を customerEntryに設定
     * @param customerEntry 問い合せ結果を格納
     * @param rs            問い合せ結果
     */
    public void setCustomerEntry(CustomerEntry customerEntry, ResultSet rs) {
        try {
            //問い合わせ結果の列情報をJavaのデータ型に受け取る
            String  customer_id     = rs.getString("CUSTOMER_ID");
            String  name            = rs.getString("NAME");
            String  tel             = rs.getString("TEL");
            String  address         = rs.getString("ADDRESS");
            int     point           = rs.getInt("POINT");
            
            customerEntry.setCustomer_id(customer_id);
            customerEntry.setName(name);
            customerEntry.setTel(tel);
            customerEntry.setAddress(address);
            customerEntry.setPoint(point);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertCustomerEntryExecute() {
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
    
    public List<CustomerEntry> dbSearchCustomerInformation(String name, String tel) {
        List<CustomerEntry> customerEntryList = new ArrayList<>();
        String sql = "SELECT * FROM CUSTOMER " +
                     " WHERE NAME = ? AND TEL = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, tel);
            customerEntryList = selectCustomerEntryExecute();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        
        return customerEntryList;
    }
    
    public void dbInsertCustomerEntry(String name, String tel, String address) {
        String sql = "INSERT INTO CUSTOMER(CUSTOMER_ID, NAME, TEL, ADDRESS) " +
                     "VALUES(S_CUSTOMER.nextval, ?, ?, ?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, tel);
            ps.setString(3, address);
            insertCustomerEntryExecute();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
