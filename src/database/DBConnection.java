/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author IROSHANA
 */
public class DBConnection {
    
    private static DBConnection dbConnection;
    private final Connection connection;
    
    private DBConnection() throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost/ThogaKade","root","root");
    }
    
    public static DBConnection getDBConnection() throws ClassNotFoundException, SQLException
    {
        if(dbConnection == null)
        {    
            dbConnection = new DBConnection();
            return dbConnection;
        }
        else 
            return dbConnection;
    }
    
    public Connection getConnection()
    {
        return connection;
    }
        
    
}
