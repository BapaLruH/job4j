<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <form>
        <button formaction="${pageContext.servletContext.contextPath}/" formmethod="get">back</button>
    </form>
</div>
<div>
    <form action="${pageContext.servletContext.contextPath}/" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="${param.id}">
        <input type="text" name="name" value="${param.name}">
        <input type="text" name="login" value="${param.login}">
        <input type="email" name="email" value="${param.email}">
        <input type="submit">
    </form>
</div>
</body>
</html>
