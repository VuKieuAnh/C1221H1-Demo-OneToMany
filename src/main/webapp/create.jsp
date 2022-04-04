<%--
  Created by IntelliJ IDEA.
  User: kieuanh
  Date: 04/04/2022
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="" method="post">
    <input name="name">
    <select name="classes">
        <c:forEach items="${classes}" var="cl" >
            <option value="${cl.id}">${cl.name}</option>
        </c:forEach>
    </select>
    <input value="tao moi" type="submit">
</form>
</body>
</html>
