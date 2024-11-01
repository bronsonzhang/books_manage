package com.bro.servlet;



import com.bro.dao.UserDao;
import com.bro.utils.VerifyCodeUtils;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name="LoginServlet", urlPatterns={"/login.do"})
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String  username= request.getParameter("username");
        String  password= request.getParameter("password");
        String checkcode=request.getParameter("checkcode");
        HttpSession session=request.getSession();
        String code= (String) session.getAttribute("verCode");
        Map map=new HashMap();
        map.put("status","0");
        map.put("data","登录失败");
        if (checkcode.length() == 4) { // 验证码设定为4位
            checkcode = VerifyCodeUtils.codeToLowerCase(checkcode); // 前台输入的验证码转为小写字母
            if (code.equals(checkcode)) {
                UserDao userDao = new UserDao();
                try {
                    // 检查用户是否注册并验证密码
                    if (userDao.isRegistered(username, password)) {
                        map.put("status", "1");
                        map.put("data", "登录成功！");
                        session.setAttribute("username", username);
                    } else {
                        map.put("data", "用户名或密码错误");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    map.put("data", "数据库错误");
                }
            } else {
                map.put("data", "验证码错误");
            }
        } else {
            map.put("data", "验证码格式错误");
        }
        JSONObject msg=JSONObject.fromObject(map);
        System.out.println(msg);
        response.setCharacterEncoding("UTF-8");
        PrintWriter out=response.getWriter();
        out.println(msg);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type=request.getParameter("type");
        if(type.equals("logout")){
            HttpSession session=request.getSession();
            session.removeAttribute("username");
            response.sendRedirect("/login.jsp");
        }
    }
}
