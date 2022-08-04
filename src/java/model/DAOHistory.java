/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.HistoryManage;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dell
 */
public class DAOHistory extends ConnectDB {

    public int addHistoryManage(HistoryManage hm) {
        int n = 0;
        String sql = "INSERT INTO [dbo].[HistoryManager]\n"
                + "           ([time]\n"
                + "           ,[staff_name]\n"
                + "           ,[manager_content])\n"
                + "     VALUES\n"
                + "           (GETDATE(),?,?)";
        try {
            PreparedStatement pre = conn.prepareStatement(sql);
            //set value for ?
            // index start 1
            pre.setString(1, hm.getName()); // 2 ~ firstname
            pre.setString(2, hm.getContent());
            // run
            n = pre.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }
    
    public Vector<HistoryManage> ListAll(String sql) {
        Vector<HistoryManage> vector = new Vector();
        try {
            ResultSet rs = getData(sql);
            while (rs.next()) {
                String time = rs.getString(1);
                String name = rs.getString(2);
                String content = rs.getString(3);

                vector.add(new HistoryManage(time, name, content));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
}
