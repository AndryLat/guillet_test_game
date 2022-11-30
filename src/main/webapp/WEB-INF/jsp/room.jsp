<%--
  Created by IntelliJ IDEA.
  User: andrylat
  Date: 06.09.2022
  Time: 17:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Room: ${roomInfo.getName()}</title>
</head>
<body>
<jsp:include page="parts/user_info.jsp" />

<h1>Room: ${roomInfo.getName()}</h1>
<br>
<h3>Go to:</h3>
<ul>
    <c:forEach items="${doors}" var="door">
        <li>
            <form action="${pageContext.request.contextPath}/room" method="post">
                <input type="hidden" name="nextRoomId" value="${door.getRoomInfo().getId()}">
                <button type="submit">${door.getRoomInfo().getName()} : is opened = ${door.isOpened()}</button>
            </form>
        </li>
    </c:forEach>
</ul>
<br>
<H3>NPC:</H3>
<ul>
    <c:forEach items="${npcs}" var="npc">
        <li>
            <form action="${pageContext.request.contextPath}/dialog" method="post">
                <input type="hidden" name="message" value="${npc.getStartMessageId()}">
                <button type="submit">${npc.getName()}</button>
            </form>
        </li>
    </c:forEach>
</ul>
<br>
<H3>Items:</H3>
<ul>
    <c:forEach items="${items}" var="item">
        <li>
            <form action="${pageContext.request.contextPath}/room" method="post">
                <input type="hidden" name="itemId" value="${item.getId()}">
                <button type="submit">${item.getName()}</button>
            </form>
        </li>
    </c:forEach>
</ul>
</body>
</html>
