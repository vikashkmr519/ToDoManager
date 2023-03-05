package config;

import java.sql.*;

public class DatabaseConfig {


    public static Connection getConnection(){
        Connection conn = null;

        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            String url = "jdbc:mysql://localhost/todomanager";
            String username = "root";
            String password = "Sysofni@1021";
            conn  = DriverManager.getConnection(url,username,password);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return conn;
    }



}
