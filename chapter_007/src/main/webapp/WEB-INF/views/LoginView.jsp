<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Login page</title>
</head>
<body>
    <div>
        <c:if test="${access != null}">
            <h4>${access}</h4>
        </c:if>
    </div>
    <form action="${pageContext.servletContext.contextPath}/signIn" method="post">
        <input type="text" placeholder="login" name="login" value="<c:if test="${param.login != null}">${param.login}</c:if>">
        <input type="password" placeholder="password" name="password">
        <input type="submit" name="login">
    </form>
    <form>
        <button formaction="${pageContext.servletContext.contextPath}/create" formmethod="get">registration</button>
    </form>
</body>
</html>
