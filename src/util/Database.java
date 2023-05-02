/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Map;
import java.util.List;
import java.util.Date;

import com.mysql.cj.jdbc.Driver;

/**
 *
 * @author Hafidz Fadhillah
 */
public class Database {
    private static Connection conn;
    
    static {
        String dbhost = "localhost",
               dbport = "3306",
               dbdata = "perpustakaan",
               dbuser = "root",
               dbpass = "";
        
        String jdbcURL = "jdbc:mysql://" + dbhost + ":" + dbport + "/" + dbdata;
        
        try {
            Driver driver = new Driver();
            DriverManager.registerDriver(driver);
            
            conn = DriverManager.getConnection(jdbcURL, dbuser, dbpass);
            System.out.println("Connection Success");
        } catch (SQLException e) {
            System.out.println("Connection Failure");
            throw new Error(e.getMessage());
        }
    }
    
    public static Connection getConnection() {
        return conn;
    }
    
    public static void prepareStmt(PreparedStatement stmt, Map<String, Object> values) throws SQLException {
        int i = 1;
        
        for(String key: values.keySet()) {
            Object value = values.get(key);
            setValues(stmt, i++, value);
        }
    }
    
    public static void prepareStmt(PreparedStatement stmt, List<Object> values) throws SQLException {
        int i = 1;
        
        for(Object value: values) {
            setValues(stmt, i++, value);
        }
    }
    
    private static void setValues(PreparedStatement stmt, int row, Object value) throws SQLException {
        if(value instanceof String) stmt.setString(row, String.valueOf(value));
        if(value instanceof Integer) stmt.setInt(row, (Integer) value);
        if(value instanceof Date) stmt.setDate(row, new java.sql.Date(((Date) value).getTime()));
    }
}
