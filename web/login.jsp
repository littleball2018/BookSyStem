<%--
  Created by IntelliJ IDEA.
  User: BingqianLee
  Date: 2018/5/31
  Time: 11:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>请登录</title>
    <link rel="stylesheet" type="text/css" href="login.css">
</head>
<body>
<div id="container">
    <div id="bigTitle">小丸子图书馆欢迎您！</div>
    <div id="toLogin">请先登录</div>
    <div id="loginForm">
        <form action="login" method="post">
            <div id="formUsername">
                <input type="text" name="username" placeholder="用户名">
            </div>
            <div id="formPwd">
                <input type="password" name="password" placeholder="密码">
            </div>
            <div id="submitOrReset">
                <input type="submit" name="loginSubmit" value="登录">
                <input type="reset" value="重置">
            </div>
            <div id="goToRegister">
                新用户？去<a href="register.jsp">注册</a>
            </div>
        </form>
    </div>
</div>
</body>
</html>
