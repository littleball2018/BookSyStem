<%--
  Created by IntelliJ IDEA.
  User: BingqianLee
  Date: 2018/5/31
  Time: 21:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>注册成功</title>
</head>
<body>
欢迎来到小丸子图书馆，<%=request.getParameter("username")%>！
刚刚你注册成功了，注册的手机号为：<%=request.getParameter("telephone")%>。

</body>
</html>
