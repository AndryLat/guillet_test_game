<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
</head>
<body>
<h1>Start page</h1>
<h3>some interesting story</h3>
<form action="${pageContext.request.contextPath}/bad" method="post">
    <div class="button">
        <button type="submit" class="btn btn-success">Go</button>
    </div>
</form>


<br>

<h1>Number of Games: ${numberOfGames}</h1>
</body>
</html>