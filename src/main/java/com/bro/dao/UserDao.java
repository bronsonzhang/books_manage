package com.bro.dao;

import com.bro.utils.JdbcUtil;
import com.bro.utils.Md5;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    private Connection con;
    public boolean isRegistered(String username,String password) throws SQLException {
        boolean IsRegistered=false;
        password= Md5.md5Password(password);
        con= JdbcUtil.getConnecttion();
        String sql="SELECT * FROM user WHERE username=? and password=?";
        try{
            PreparedStatement psmt=con.prepareStatement(sql);
            psmt.setString(1,username);
            psmt.setString(2,password);
            ResultSet rs=psmt.executeQuery();
            System.out.println(rs);
            while(rs.next()){
                System.out.println(rs.getString(2)+"\t"+rs.getString(3));
                IsRegistered=true;
            }

        }catch (SQLException e){
            con.rollback();
            e.printStackTrace();
        }finally {
            con.close();
        }
        return IsRegistered;
    }

    // 方法用于检查用户是否已注册并注册新用户
    public boolean register(String username, String password) throws SQLException {
        boolean isRegistered = false;
        password = Md5.md5Password(password); // 对密码进行加密
        con = JdbcUtil.getConnecttion();

        // 检查用户名是否已存在
        String checkSql = "SELECT COUNT(*) FROM user WHERE username=?";
        try {
            PreparedStatement checkPsmt = con.prepareStatement(checkSql);
            checkPsmt.setString(1, username);
            ResultSet rs = checkPsmt.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
                return false; // 用户名已存在
            }

            // 如果用户名可用，插入新用户
            String insertSql = "INSERT INTO user (username, password) VALUES (?, ?)";
            PreparedStatement insertPsmt = con.prepareStatement(insertSql);
            insertPsmt.setString(1, username);
            insertPsmt.setString(2, password);
            int rowsAffected = insertPsmt.executeUpdate();

            if (rowsAffected > 0) {
                isRegistered = true; // 注册成功
            }
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        } finally {
            con.close();
        }
        return isRegistered;
    }

}
