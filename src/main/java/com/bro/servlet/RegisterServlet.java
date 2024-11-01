package com.bro.servlet;

import com.bro.dao.UserDao;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name="RegisterServlet", urlPatterns={"/register.do"})
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Map<String, Object> map = new HashMap<>();
        map.put("status", "0");
        map.put("data", "注册失败");

        UserDao userDao = new UserDao();
        try {
            if (userDao.register(username, password)) {
                map.put("status", "1");
                map.put("data", "注册成功！");
            } else {
                map.put("data", "用户名已存在！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JSONObject msg = JSONObject.fromObject(map);
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println(msg);
    }
}
