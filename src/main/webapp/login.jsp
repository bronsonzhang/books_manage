<%@ page language="java" pageEncoding="utf-8" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>后台登录</title>
    <link href="assets/css/admin_login.css" rel="stylesheet" type="text/css" />
    <script src="assets/js/jquery-3.2.1.js"></script>
    <script src="assets/layer/layer.js"></script>
</head>
<body>
<%
    //防止重复登录
if (session.getAttribute("username")!=null)
    response.sendRedirect("/book.do?type=pageList");
%>
<div class="admin_login_wrap">
    <h1>后台管理</h1>
    <div class="adming_login_border">
        <div class="admin_input">
            <form id="form_for_login">
                <ul class="admin_items">
                    <li>
                        <label for="username">用户名：</label>
                        <input type="text" name="username" id="username" size="35" class="admin_input_style" />
                    </li>
                    <li>
                        <label for="password">密码：</label>
                        <input type="password" name="password"  id="password" size="35" class="admin_input_style" />
                    </li>
                    <li>
                        <label for="checkcode">验证码:</label>
                        <input type="text" name="checkcode" id="checkcode" size="18" class="admin_input_style" />
                        <img id="img" src="${pageContext.request.contextPath}/authImage" onclick="javascript:changeImg()" style="position: relative;top: 10px"/>
                    </li>
                    <li>
                        <button tabindex="3" type="button" class="btn btn-primary" id="btn-submit">登录</button>
                    </li>
                </ul>
            </form>
            <!-- 注册提示 -->
            <div class="register_prompt" style="margin-top: 15px;">
                <span>还没有账号？</span>
                <a href="register.jsp" class="register_link">立即注册</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>
<!--ajax提交表单-->
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
            if(!$('#checkcode').val()){
                alert("请输入验证码！");
            } else {
                $.ajax({
                    type: "post",
                    url: "/login.do",
                    data: $('#form_for_login').serialize(),
                    dataType: "json",
                    success: function (msg) {
                        if (msg.status == 1) {
                            layer.msg(msg.data);
                            window.setTimeout("window.location.href='/book.do?type=pageList'", 1000);
                        } else {
                            layer.msg(msg.data);
                        }
                    }
                });
            }
        });

        //回车提交表单
        $("#form_for_login").keydown(function(e){
            var e = e || event,
                keycode = e.which || e.keyCode;
            if (keycode==13) {
                $("#btn-submit").trigger("click");
            }
        });
    });
</script>

<!-- 触发JS刷新验证码-->
<script type="text/javascript">
    function changeImg(){
        var img = document.getElementById("img");
        var contextPath = '${pageContext.request.contextPath}'; // 获取上下文路径
        var newSrc = contextPath + "/authImage?date=" + new Date().getTime();
        img.src = newSrc;
    }
</script>