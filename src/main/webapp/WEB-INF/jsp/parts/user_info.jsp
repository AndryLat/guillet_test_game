<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="dev.andrylat.game.javarush_game.engine.domain.User" %><%--
  Created by IntelliJ IDEA.
  User: andrylat
  Date: 09.09.2022
  Time: 18:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set   var="questRepository"   value="${applicationScope.get('questRepository')}"  />
<c:set   var="itemRepository"   value="${applicationScope.get('itemRepository')}"  />
<div>
  <h1>User info</h1>
  <h4>Username: <c:out value="${sessionScope.user.getUsername()}"/></h4>
  <div>
    <h3>Quests:</h3>
    <ul>
      <c:forEach items="${sessionScope.user.getQuests()}" var="questId">
        <c:set   var="quest"   value="${questRepository.getById(questId)}"  />
        <li>
          <div>
            <p>Quest name: ${quest.getText()}</p>
            <p>Is finished: ${quest.isFinished(sessionScope.user)}</p>
          </div>
        </li>
      </c:forEach>
    </ul>
  </div>

  <div>
    <h3>Items:</h3>
    <ul>
      <c:forEach items="${sessionScope.user.getItems()}" var="itemId">
        <c:set   var="item"   value="${itemRepository.getById(itemId)}"  />
        <li>
          <div>
            <p>item name: ${item.getName()}</p>
          </div>
        </li>
      </c:forEach>
    </ul>
  </div>
</div>
<br>
<hr>