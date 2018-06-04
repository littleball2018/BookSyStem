<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


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
    <title>所有图书</title>
</head>
<body>
用户名：${sessionScope.username}<br>
所有图书：<br>

<form id="borrowForm" action="borrowbook" method="post">
    <input type="hidden" name="hiddenBookid" id="hiddenBookidId">
    <table>
        <tr>
            <th>图书编号</th>
            <th>图书名称</th>
            <th>图书作者</th>
            <th>图书价格</th>
            <th>是否借阅</th>
        </tr>

        <c:if test="${requestScope.allBookList!=null || fn:length(requestScope.allBookList)!=0 }">
            <c:forEach items="${requestScope.allBookList}" var="book" varStatus="status">
                <tr>
                    <td>${book.bookid}</td>
                    <td>${book.bookname}</td>
                    <td>${book.bookauthor}</td>
                    <td>${book.bookprice}</td>
                    <td>
                        <input type="button" value="就借它了！" onclick="borrowIt('${book.bookid}')">
                        <%--<a href="borrowbook?bookid=${book.bookid}">就借它了！</a>--%>
                    </td>
                </tr>
            </c:forEach>
        </c:if>

    </table>
</form>

<script type="text/javascript" language="JavaScript">
    function borrowIt(bookid){
        console.log(bookid);
        document.getElementById("hiddenBookidId").setAttribute("value",bookid);
        // document.borrowForm.hiddenBookid.value=bookid;
        document.getElementById('borrowForm').submit();
    }

</script>


</body>
</html>
