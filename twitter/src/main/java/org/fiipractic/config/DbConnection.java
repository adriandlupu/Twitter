package org.fiipractic.config;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DbConnection {
    private static Connection con=null;
    static {
        String jdbcURL = "jdbc:mysql://localhost:3306/twitter";
        String usernameConnection = "adrian.lupu";
        String passwordConnection = "password";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(jdbcURL, usernameConnection, passwordConnection);
        }
        catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

    }
    public static Connection getConnection()
    {
        return con;
    }
}
