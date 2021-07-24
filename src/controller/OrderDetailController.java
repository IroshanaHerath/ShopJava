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
import model.OrderDetail;

/**
 *
 * @author IROSHANA
 */
public class OrderDetailController {

    public static boolean addOrderDetail(OrderDetail detail) throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getDBConnection().getConnection();
        Statement stm = connection.createStatement();
        String SQL = "Insert into orderdetail values('" + detail.getOrderId() + "','" + detail.getItemCode() + "','" + detail.getQty() + "','" + detail.getUnitPrice() + "')";
        return  stm.executeUpdate(SQL) > 0;
        
    }
    
    public static boolean addOrderDetail(ArrayList<OrderDetail> detail) throws ClassNotFoundException, SQLException
    {
        for(OrderDetail orderDetail : detail)
        {
            if(!addOrderDetail(orderDetail)) return false;
        }
        return  true;
    }
    
    
}
