<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: andrylat
  Date: 06.09.2022
  Time: 19:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Dialog</title>
</head>
<body>

<jsp:include page="parts/user_info.jsp" />

<h1>Dialog</h1>
<h3>Text: ${message.getText()}</h3>
<ul>
    <c:forEach items="${message.getAnswers()}" var="answer">
        <li>
            <c:choose>
                <c:when test="${answer.getNextMessageId() == null && answer.getQuestId() == null}">
                    <a href="${pageContext.request.contextPath}/dialog?finish=true">${answer.getText()}</a>
                </c:when>
                <c:when test="${answer.getNextMessageId() != null}">
                    <a href="${pageContext.request.contextPath}/dialog?message=${answer.getNextMessageId()}">${answer.getText()}</a>
                </c:when>
<%--                <c:when test="${answer.isFighting()}">
                    <a href="${pageContext.request.contextPath}/fight?message=${answer.getNextMessageId()}">${answer.getText()}</a>
                </c:when>--%>
                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/dialog?quest=${answer.getQuestId()}">${answer.getText()}</a>
                </c:otherwise>
            </c:choose>
        </li>
    </c:forEach>
</ul>
</body>
</html>
