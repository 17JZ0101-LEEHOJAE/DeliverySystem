/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbDelivery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * シングルトンパターンを適用した
 * データベース接続管理クラス
 * @author 17jz0101 Lee Ho Jae
 */
public class DBManagerDelivery {
    private static DBManagerDelivery    dbManager = null;
    private static Connection           con = null;
    private static final String driverUrl = "jdbc:oracle:thin:@//10.40.112.11:1521/dbsys";
    private static final String dbUserName = "G1701";
    private static final String dbUserPassword = "g1701";
//    private static final String driverUrl = "jdbc:derby://localhost:1527/DeliveryDB";
//    private static final String dbUserName = "user01";
//    private static final String dbUserPassword = "user01";
//    private static final String driverUrl = "jdbc:oracle:thin:@localhost:1521:XE";
//    private static final String dbUserName = "HR";
//    private static final String dbUserPassword = "pass";
    
    /**
     * コンストラクタ
     * ドライバマネージャにJDBCドライバを登録し、URLで指定された場所に存在するデータベースに接続する
     * アクセス修飾子がprivateなので、内部メソッドからしか生成することができない
     */
    private DBManagerDelivery() {
        try {
            // JavaDB接続用設定
            con = DriverManager.getConnection(driverUrl, dbUserName, dbUserPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * DB接続管理インスタンスの生成を兼ねたゲッター
     * @return DB接続管理インスタンス
     */
    public static DBManagerDelivery getDBManager() {
        if(DBManagerDelivery.dbManager == null) {
            DBManagerDelivery.dbManager = new DBManagerDelivery();
        }
        
        return DBManagerDelivery.dbManager;
    }
    
    /**
     * コネクションのゲッター
     * @return コネクション
     */
    public Connection getConnection() {
        return con;
    }
}
