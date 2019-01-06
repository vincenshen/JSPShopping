package dao;

import entity.Items;
import util.DBHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * 商品的业务逻辑类
 */
public class ItemsDAO {

    // 获得所有的商品信息
    public ArrayList<Items> getAllItems(){

        // 定义三个执行SQL语句所用到的对象
        Connection conn = null; // SQL连接对象
        PreparedStatement stmt = null;  // SQL语句执行对象
        ResultSet rs = null;    // SQL返回结果集合

        ArrayList<Items> list = new ArrayList<>();  // 商品集合
        try {
            conn = DBHelper.getConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql = "select * from items;";    // SQL语句
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()){
                Items item = new Items();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setCity(rs.getString("city"));
                item.setNumber(rs.getInt("number"));
                item.setPrice(rs.getInt("price"));
                item.setPicture(rs.getString("picture"));
                list.add(item);     // 把一个商品加入集合
            }
            return list;    // 返回商品集合
        } catch (SQLException e) {
            e.printStackTrace();
            return null;

        } finally {
            if(rs != null){
                try {
                    rs.close();
                    rs = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(stmt != null){
                try {
                    stmt.close();
                    stmt = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 根据商品编号获取商品资料
    public Items getItemsById(int id){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            conn = DBHelper.getConn();
            String sql = "select * from items where id=?;";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if(rs.next()){
                Items item = new Items();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setCity(rs.getString("city"));
                item.setNumber(rs.getInt("number"));
                item.setPrice(rs.getInt("price"));
                item.setPicture(rs.getString("picture"));
                return item;
            } else {
                return null;
            }

        } catch (Exception ex){
            ex.printStackTrace();
            return null;

        } finally {
            if(rs != null){
                try {
                    rs.close();
                    rs = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(stmt != null) {
                try {
                    stmt.close();
                    stmt = null;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
