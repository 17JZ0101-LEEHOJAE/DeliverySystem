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
 * 顧客確認用DAO
 * @author 17jz0101 Lee Ho Jae
 */
public class CustomerDAO {
    private static Connection           con;
    private static PreparedStatement    ps;
    
    public CustomerDAO() {
        DBManagerDelivery dbManager = DBManagerDelivery.getDBManager();
        con                         = dbManager.getConnection();
    }
    
     /**
     * Customerテーブル検索処理実行
     * @return 検索結果のリスト
     */
    public List<Customer> selectCustomerExecute() {
        List<Customer> customerList = new ArrayList<>();        // 問い合わせ結果 Customer
        try {            
            // 完成したSQLの実行
            ResultSet rs = ps.executeQuery();
            //　結果の表示            
            while (rs.next()) {     // 検索結果のある間繰り返す
                Customer customer = new Customer();
                setCustomer(customer, rs);          // 検索結果を1件取り出し                
                customerList.add(customer);         // 検索結果をリストに登録
            }
            // 後片付け
            rs.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }     
        return customerList;
    }
    
   /**
     * 問い合せ結果を Customerに設定
     * @param customer  問い合せ結果を格納
     * @param rs        問い合せ結果
     */
    public void setCustomer(Customer customer, ResultSet rs) {
        try {
            //問い合わせ結果の列情報をJavaのデータ型に受け取る
            int customerId          = rs.getInt("CUSTOMER_ID");
            String  name            = rs.getString("NAME");
            String  tel             = rs.getString("TEL");
            String address          = rs.getString("ADDRESS");
            int point               = rs.getInt("POINT");
            
            customer.setCustomerId(customerId);
            customer.setName(name);
            customer.setTel(tel);
            customer.setAddress(address);
            customer.setPoint(point);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<Customer> dbSearchCustomer(String name, String tel) {
        List<Customer> customerList = new ArrayList<>();
        String sql = "SELECT * " +
                     " FROM CUSTOMER " +
                     " WHERE NAME = ? AND TEL = ?";
        
        try {
//           System.out.println("name = [" + name + "] tel = [" + tel + "]");
           ps = con.prepareStatement(sql);
           ps.setString(1, name);
           ps.setString(2, tel);
           customerList = selectCustomerExecute();
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return customerList;
    }
    
    public static void main(String[] args) {
        CustomerDAO customerDAO = new CustomerDAO();
        customerDAO.dbSearchCustomer("イシイ", "07077355541");
    }    
}
