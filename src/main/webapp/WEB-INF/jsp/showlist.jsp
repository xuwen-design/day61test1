<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 雯雯
  Date: 2019/7/5
  Time: 11:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <tr>
        <c:forEach var="user" items="${users}">
            <td>${user.uid}</td>
            <td>${user.uname}</td>
            <td>${user.pwd}</td>
            <td>${user.birthday}</td>
        </c:forEach>
    </tr>
</table>
</body>
</html>
