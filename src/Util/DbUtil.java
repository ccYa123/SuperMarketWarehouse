package Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 对数据库相关信息的增、删、查、改
 */
public class DbUtil {

    private Connection conn = null;
    private PreparedStatement stmt = null;


    /**
     * 向数据库增加用户信息
     */
    public void addUser(String username,String password){
        String sql = "insert into warehouse.user(username,password)values(?,?)";
        try{
            DbConnection DbCn = new DbConnection();
            conn = DbCn.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,username);
            stmt.setString(2,password);
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 根据用户名查找用户信息
     */
    public String selectUser(String username){
        String sql = "select password from warehouse.user where username = ?";
        try{
            DbConnection DbCn = new DbConnection();
            conn = DbCn.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,username);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                String password = rs.getString(1);
                return password;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * 向数据库中增加商品信息
     */
    public void addObjects(Objects obj){
        String sql = "insert into warehouse.Objects (num,name,price,kind,sum,sell) values(?,?,?,?,?,?)";
        try{
            DbConnection DbCn = new DbConnection();
            conn = DbCn.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,obj.getNum());
            stmt.setString(2,obj.getName());
            stmt.setDouble(3,obj.getPrice());
            stmt.setString(4,obj.getKind());
            stmt.setInt(5,obj.getSum());
            stmt.setString(6,obj.getSell());
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    /**
     * 删除商品信息
     */
    public void deleteObjects(String num){
        String sql = "delete from warehouse.objects where num = ?";
        try{
            DbConnection DbCn = new DbConnection();
            conn = DbCn.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,num);
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 修改商品信息
     */

    public void editMessages(Objects obj){
        String sql = "update warehouse.Objects set name = ?,price = ?,kind = ?,sales = ?,sum = ?,sell = ? where num =?";
        try{
            DbConnection DbCn = new DbConnection();
            conn = DbCn.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,obj.getName());
            stmt.setDouble(2,obj.getPrice());
            stmt.setString(3,obj.getKind());
            stmt.setInt(4,obj.getSales());
            stmt.setInt(5,obj.getSum());
            stmt.setString(6,obj.getSell());
            stmt.setString(7,obj.getNum());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 查询商品
     */
    public Objects selectObjects(String NUM){
        String sql = "select * from warehouse.objects where num = ?";
        try{
            DbConnection DbCn = new DbConnection();
            conn = DbCn.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,NUM);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                String Num = rs.getString(1);
                String Name = rs.getString(2);
                Double Price = rs.getDouble(3);
                String Kind = rs.getString(4);
                int Sales = rs.getInt(5);
                int Sum = rs.getInt(6);
                String Sell = rs.getString(7);
                return new Objects(Num,Name,Price,Kind,Sales,Sum,Sell);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
    /**
     * 查询商品是否存在
     */
    public boolean judgeObjects(String NUM){
        String sql = "select * from warehouse.objects where num = ?";
        try{
            DbConnection DbCn = new DbConnection();
            conn = DbCn.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,NUM);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                if(rs.getString(1).equals(NUM))
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
    /**
     *增加出货单
     */
    public void addAllOrder(String orderNum,String orderTime,double Money){
        String sql = "insert into warehouse.allorder (ordernum,ordertime,money) values(?,?,?)";
        try{
            DbConnection DbCn = new DbConnection();
            conn = DbCn.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,orderNum);
            stmt.setString(2,orderTime);
            stmt.setDouble(3,Money);
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    /**
     * 增加出货单的出货信息
     */
    public void addOrder(String orderNum,String goodsNum,String goodsName,double goodsPrice,int goodsSum){
        String sql = "insert into warehouse.order (ordernum,goodsnum,goodsname,goodsprice,goodssum) values(?,?,?,?,?)";
        try{
            DbConnection DbCn = new DbConnection();
            conn = DbCn.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,orderNum);
            stmt.setString(2,goodsNum);
            stmt.setString(3,goodsName);
            stmt.setDouble(4,goodsPrice);
            stmt.setInt(5,goodsSum);
            stmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
