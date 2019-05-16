<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Index page</title>
</head>
<body>
    <div>
        <form>
            <button formaction="${pageContext.servletContext.contextPath}/create" formmethod="get" value="">create user</button>
            <c:if test="${param.id != null}">
                <button formaction="${pageContext.servletContext.contextPath}/" formmethod="get" value="">all users</button>
            </c:if>
        </form>
    </div>
    <div>
        <c:if test="${result != null}">
            <h5><c:out value="${result}"></c:out></h5>
        </c:if>
        <c:if test="${users.isEmpty() == false}">
            <table border="1">
                <tr>
                    <th>id</th>
                    <th>name</th>
                    <th>login</th>
                    <th>email</th>
                    <th>update</th>
                    <th>delete</th>
                </tr>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td>
                            <a href="${pageContext.servletContext.contextPath}/?id=<c:out value="${user.id}"></c:out>">
                                <c:out value="${user.id}"></c:out>
                            </a>
                        </td>
                        <td><c:out value="${user.name}"></c:out></td>
                        <td><c:out value="${user.login}"></c:out></td>
                        <td><c:out value="${user.email}"></c:out></td>
                        <td>
                            <form action="${pageContext.servletContext.contextPath}/update" method="get">
                                <input type="hidden" name="action" value="update">
                                <input type="hidden" name="id" value="<c:out value="${user.id}"></c:out>">
                                <input type="hidden" name="name" value="<c:out value="${user.name}"></c:out>">
                                <input type="hidden" name="login" value="<c:out value="${user.login}"></c:out>">
                                <input type="hidden" name="email" value="<c:out value="${user.email}"></c:out>">
                                <input type="submit" value="update">
                            </form>
                        </td>
                        <td>
                            <form action="${pageContext.servletContext.contextPath}/" method="post">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="id" value="<c:out value="${user.id}"></c:out>">
                                <input type="submit" value="delete">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </div>
</body>
</html>
