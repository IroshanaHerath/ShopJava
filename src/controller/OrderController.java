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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Order;

/**
 *
 * @author IROSHANA
 */
public class OrderController {
    
    public static boolean placeOrder(Order order) throws ClassNotFoundException, SQLException
    {
        Connection connection = DBConnection.getDBConnection().getConnection();
        try {
            
            Statement stm = connection.createStatement();
            connection.setAutoCommit(false);
            String SQL = "insert into orders values('" + order.getId() + "','" + order.getDate() + "','" + order.getCustomerId() + "')" ;
            
            if(stm.executeUpdate(SQL) > 0)
            {
                if(OrderDetailController.addOrderDetail(order.getDetails()))
                {
                    if(ItemController.updateStock(order.getDetails()))
                    {
                        connection.commit();
                        return true;
                    }
                }
            }
            
            connection.rollback();
            return false;
            
        }finally
        {
            connection.setAutoCommit(true);
        }
        
    }
    
    public static ArrayList<String> getAllOrderIds() throws SQLException, ClassNotFoundException
    {
        Connection connection = DBConnection.getDBConnection().getConnection();
        Statement stm = connection.createStatement();
        String SQL = "Select id from orders";
        ResultSet rst = stm.executeQuery(SQL);
        ArrayList<String> ids = new ArrayList<>();
        
        while(rst.next())  ids.add(rst.getString("id"));
        
        return ids;
    
    }
    
}
