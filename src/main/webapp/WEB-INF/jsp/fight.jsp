<%--
  Created by IntelliJ IDEA.
  User: andrylat
  Date: 09.09.2022
  Time: 19:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Fighting</title>
</head>
<body>
<h1> fighting</h1>

<hr>
<h3>user's health: ${sessionScope.user.getCurrentHealth()} \ ${sessionScope.user.getMaxHealth()}</h3>
<form method="post" action="${pageContext.request.contextPath}/fight">
    <h3>Block:</h3>
    <ul>
        <li><input type="radio" name="block" value="head" checked> Head</li>
        <li><input type="radio" name="block" value="body"> Body</li>
        <li><input type="radio" name="block" value="legs"> Legs</li>
    </ul>
    <h3>Attack:</h3>
    <ul>
        <li><input type="radio" name="attack" value="head" checked> Head</li>
        <li><input type="radio" name="attack" value="body"> Body</li>
        <li><input type="radio" name="attack" value="legs"> Legs</li>
    </ul>

    <input type="submit" value="fight">
</form>

<hr>
<h3>Enemy's health: </h3>
</body>
</html>
