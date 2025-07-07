package com.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DBUtil
 */
public class DBUtil {

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException {
        final String URL = "jdbc:mysql://localhost/LibraryManagement";
        final String USER = "root";
        final String PASSWORD = "";
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }


}
