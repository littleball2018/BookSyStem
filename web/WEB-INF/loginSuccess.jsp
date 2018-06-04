<%--
  Created by IntelliJ IDEA.
  User: BingqianLee
  Date: 2018/5/31
  Time: 21:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>登陆成功</title>
</head>
<body>
你好，${sessionScope.username}！
欢迎登录小丸子图书馆。<br>

<form action="showbooks" method="post">
    <input type="hidden" name="userrequest">
    <%--<input type="hidden" name="username" value=<%=request.getParameter("username")%>>--%>
    <input type="submit" value="我要借书" onclick="this.form.userrequest.value='showAllBooks'">
    <input type="submit" value="我的借阅" onclick="this.form.userrequest.value='showMyBorrow'">

</form>


<%--<a href="ShowBooks?userrequest=showAllBooks">借书</a><br>--%>
<%--<a href="ShowBooks?userrequest=showMyBorrow">我的借阅</a>--%>



</body>
</html>
