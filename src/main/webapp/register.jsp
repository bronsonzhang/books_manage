<%@ page language="java" pageEncoding="utf-8" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户注册</title>
    <link href="assets/css/admin_login.css" rel="stylesheet" type="text/css" />
    <script src="assets/js/jquery-3.2.1.js"></script>
    <script src="assets/layer/layer.js"></script>
</head>
<body>
<%
    if (session.getAttribute("username") != null) {
        out.print("<h2>您已经登录，无需注册。</h2>");
        return; // 如果已登录，则停止后续处理
    }
%>
<div class="admin_login_wrap">
    <h1>用户注册</h1>
    <div class="adming_login_border">
        <div class="admin_input">
            <form id="form_for_register">
                <ul class="admin_items">
                    <li>
                        <label for="username">用户名：</label>
                        <input type="text" name="username" id="username" size="35" class="admin_input_style" />
                    </li>
                    <li>
                        <label for="password">密码：</label>
                        <input type="password" name="password" id="password" size="35" class="admin_input_style" />
                    </li>
                    <li>
                        <label for="confirmPassword">确认密码：</label>
                        <input type="password" name="confirmPassword" id="confirmPassword" size="35" class="admin_input_style" />
                    </li>
                    <li>
                        <button tabindex="3" type="button" class="btn btn-primary" id="btn-submit">注册</button>
                    </li>
                </ul>
            </form>
            <!-- 回到登录页 -->
            <div class="login_prompt" style="margin-top: 15px;">
                <span>已有账号？</span>
                <a href="login.jsp" class="login_link">立即登录</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>

<script>
    $(document).ready(function () {
        $("#btn-submit").click(function () {
            if (!$('#username').val()) {
                alert("请输入用户名！");
                return;
            }
            if (!$('#password').val()) {
                alert("请输入密码！");
                return;
            }
            if ($('#password').val() !== $('#confirmPassword').val()) {
                alert("两次输入的密码不一致！");
                return;
            }

            $.ajax({
                type: "post",
                url: "/register.do",
                data: $('#form_for_register').serialize(),
                dataType: "json",
                success: function (msg) {
                    if (msg.status == 1) {
                        layer.msg(msg.data);
                        window.setTimeout("window.location.href='/login.jsp'", 1000);
                    } else {
                        layer.msg(msg.data);
                    }
                }
            });
        });
    });
</script>
