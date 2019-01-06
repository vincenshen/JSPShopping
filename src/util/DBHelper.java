package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {

    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://172.16.65.200:3306/shopping";
    private static final String username = "root";
    private static final String password = "123456";

    private static Connection conn = null;
    // 静态代码块，负责加载驱动
    static{
        try {
            Class.forName(driver);
            System.out.println("Success loading Mysql Driver!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 单例模式，返回数据库连接对象
    public static Connection getConn() throws SQLException {
        if(conn == null){
            conn = DriverManager.getConnection(url, username, password);
            return conn;
        }
        return conn;
    }

    public static void main(String[] args){
        try {
            Connection conn = DBHelper.getConn();
            if(conn != null){
                System.out.println("Mysql Connect success!");
            }else {
                System.out.println("Mysql Connect failed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
