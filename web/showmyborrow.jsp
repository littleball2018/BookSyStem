<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%--
  Created by IntelliJ IDEA.
  User: BingqianLee
  Date: 2018/6/1
  Time: 16:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>我的借阅</title>
</head>
<body>
用户名：${sessionScope.username}<br>
我的借阅：<br>

<form id="againBorrowForm" action="againborrowbook" method="post">
    <input type="hidden" name="hiddenBookid" id="hiddenBookidId">

    <table>
        <tr>
            <th>图书编号</th>
            <th>图书名称</th>
            <th>图书作者</th>
            <th>图书价格</th>
            <th>借书时间</th>
            <th>到期时间</th>
            <th>我要续借</th>

        </tr>

        <c:if test="${requestScope.borrowBookList!=null||fn:length(requestScope.borrowBookList)!=0}"><br>
            <c:forEach items="${requestScope.borrowBookList}" var="borrowBook" varStatus="status">

                <tr>
                    <td>${borrowBook.bookid}</td>
                    <td>${borrowBook.bookname}</td>
                    <td>${borrowBook.bookauthor}</td>
                    <td>${borrowBook.bookprice}</td>
                    <td>${borrowBook.borrowtime}</td>
                    <td>${borrowBook.returntime}</td>
                    <td>
                        <input type="button" value="就续借它了！" onclick="againBorrowIt('${borrowBook.bookid}')">
                        
                    </td>
                </tr>
            </c:forEach>
        </c:if>

        <c:if test="${requestScope.borrowedOrNot=='y'}"><br>
            <script type="text/javascript" language="JavaScript">
                alert("你已经借过了。")
            </script>
        </c:if>

    </table>
</form>
<script type="text/javascript" language="JavaScript">
    function againBorrowIt(bookid) {
        console.log(bookid);
        document.getElementById("hiddenBookidId").setAttribute("value",bookid);
        document.getElementById("againBorrowForm").submit();
        
    }
</script>


</body>
</html>
