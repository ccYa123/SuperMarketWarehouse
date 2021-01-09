package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 连接数据库
 */
public class DbConnection {
    /**
     * 定义mysql的数据库驱动程序
     */
    private static final String DBDRIVER = "com.mysql.cj.jdbc.Driver";
    /**
     * 定义mysql数据库的连接地址
     */
    private static final String DBURL = "jdbc:mysql://localhost:3306/warehouse";
    /**
     * mysql数据库的连接用户名
     */
    private static final String DBUSER = "root";
    /**
     * mysql数据库连接的密码
     */
    private static final String DBPASS = "123456789.cc";

    public Connection conn = null;

    /**
     * 获得mysql数据库连接
     * getConn()方法返回一个Connection对象
     * @return
     */
    public Connection getConnection(){
        try{
            Class.forName(DBDRIVER);
            conn = DriverManager.getConnection(DBURL,DBUSER,DBPASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }
}