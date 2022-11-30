<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css">
</head>
<body>
<h1>Start page</h1>
<h3>some interesting story</h3>
<form action="${pageContext.request.contextPath}/entrance" method="post">
    <div>
        <label for="name">Name:</label>
        <input type="text" id="name" name="username">
    </div>
    <div class="button">
        <button type="submit" class="btn btn-success">Go</button>
    </div>
</form>
</body>
</html>