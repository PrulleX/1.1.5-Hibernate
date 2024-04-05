package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3307/firstbd";
    private static final String USERNAME = "root2";
    private static final String PASSWORD = "rereirf202020";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
           connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("БД подключилась");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("БД не подключилась");
        }
        return connection;
    }
}







//    public static Connection getConnection() {
//        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
//            System.out.println("connected");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return connection;
//    }
