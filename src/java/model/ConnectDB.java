/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class ConnectDB {
    Connection conn = null;

    public ConnectDB(String URL, String userName, String passWord) {
        try {
//        URL : string connection: address, port, databaseName
//        userName, passWord: account of SQLserver
//        call Diver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
//            Class.forName(com.oracle.webservices.api.EnvelopeStyle);
            conn = DriverManager.getConnection(URL, userName, passWord);
            System.out.println("connected");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
    }
    public ConnectDB(){
        this("jdbc:sqlserver://localhost:1433;databaseName=DBSE1605", "sa", "123456");
    }
    
    public ResultSet getData(String sql){
        ResultSet rs = null;
        Statement state;
        try {
            state = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs=state.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDB.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return rs;
    }
    
    
    public static void main(String[] args) {
        new ConnectDB();
    }
}
