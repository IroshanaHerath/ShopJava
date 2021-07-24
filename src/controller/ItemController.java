/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import database.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Item;
import model.OrderDetail;

/**
 *
 * @author IROSHANA
 */
public class ItemController {
    
    public static ArrayList<String> getAllItemCodes() throws ClassNotFoundException, SQLException
    {
    
        Connection connection = DBConnection.getDBConnection().getConnection();
        Statement stm = connection.createStatement();
        String SQL = "Select code from Item";
        ResultSet rst = stm.executeQuery(SQL);
        ArrayList<String> itemCodes = new ArrayList<>();
        
        while(rst.next())   itemCodes.add(rst.getString("code"));
        
        return itemCodes;
    
    }
    
    public static Item searchItem(String code) throws ClassNotFoundException, SQLException
    {
        Connection connection = DBConnection.getDBConnection().getConnection();
        Statement stm = connection.createStatement();
        String SQL = "Select * From Item where code='" + code + "'";
        ResultSet rst = stm.executeQuery(SQL);
        
        return rst.next() ? new Item(rst.getString("code"), rst.getString("description"), rst.getDouble("unitPrice"), rst.getDouble("qtyOnHand")): null;
    }

    public static boolean updateStock(ArrayList<OrderDetail> detail) throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getDBConnection().getConnection();
        Statement stm = connection.createStatement();
        for(OrderDetail orderDetail : detail)
        {
            String SQL = "Update Item set qtyOnHand = qtyOnHand - " + orderDetail.getQty() + "where id= '" + orderDetail.getItemCode() + "'";
            return stm.executeUpdate(SQL) > 0;
        }
        
        return true;
    }
    
}
