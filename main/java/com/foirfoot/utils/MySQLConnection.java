package com.foirfoot.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {

    private static Connection con = null;

    static
    {
        String url = "jdbc:mysql://mysql-leo-ig.alwaysdata.net:3306/leo-ig_foir_foot?enabledTLSProtocols=TLSv1.2";
        String user = "leo-ig";
        String pass = "ftyx-mloi-fhci";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection()
    {
        return con;
    }
}
