<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Create user page</title>
    </head>
    <body>
        <div>
            <form>
                <button formaction="${pageContext.servletContext.contextPath}/" formmethod="get">back</button>
            </form>
        </div>
        <div>
            <form action="${pageContext.servletContext.contextPath}/" method="post">
                <input type="hidden" name="action" value="add">
                <input type="text" placeholder="name" name="name">
                <input type="text" placeholder="login" name="login">
                <input type="email" placeholder="email" name="email">
                <input type="submit">
            </form>
        </div>
    </body>
</html>
