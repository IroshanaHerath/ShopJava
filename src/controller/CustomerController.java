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
import model.Customer;

/**
 *
 * @author IROSHANA
 */
public class CustomerController {
    
    public static ArrayList<String> getAllCustomers() throws ClassNotFoundException, SQLException
    {
        Connection connection = DBConnection.getDBConnection().getConnection();
        Statement stm = connection.createStatement();
        String SQL = "Select id from Customer";
        ResultSet rst = stm.executeQuery(SQL);
        ArrayList<String> ids = new ArrayList<>();
        while(rst.next())
        {
            ids.add(rst.getString("id"));
        }
        return ids;
    }
    
    public static Customer searchCustomer(String id) throws ClassNotFoundException, SQLException
    {
        Connection connection = DBConnection.getDBConnection().getConnection();
        Statement stm = connection.createStatement();
        String SQL = "Select * From Customer where id='" + id + "'";
        ResultSet rst = stm.executeQuery(SQL);
        
        return rst.next() ? new Customer(rst.getString("id"), rst.getString("name"), rst.getString("address"), rst.getDouble("salary")): null;
    }
    
}
