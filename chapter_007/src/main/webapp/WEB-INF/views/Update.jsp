<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Update user page</title>
</head>
<body>
    <div>
        <form>
            <button formaction="${pageContext.servletContext.contextPath}/" formmethod="get">back</button>
        </form>
    </div>
    <div>
        <c:if test="${result != null}">
            <h4><c:out value="${result}"/></h4>
        </c:if>
    </div>
    <div>
        <form action="${pageContext.servletContext.contextPath}/" method="post">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="id" value="${param.id}">
            <div>
                Name:<input type="text" name="name" value="${user.name}">
                Login:<input type="text" name="login" value="${user.login}">
            </div>
            <div>
                Email:<input type="email" name="email" value="${user.email}">
                Password:<input type="password" name="password" value="${user.password}">
            </div>
                <c:choose>
                    <c:when test="${isAdmin}">
                        <c:forEach items="${roles}" var="role">
                            <c:choose>
                                <c:when test="${user.roles.contains(role)}">
                                    ${role.name}<input type="checkbox" name="${role.id}" value="${role.name}" checked>
                                </c:when>
                                <c:otherwise>
                                    ${role.name}<input type="checkbox" name="${role.id}" value="${role.name}">
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${roles}" var="role">
                            <c:if test="${user.roles.contains(role)}">
                                <input type="hidden" name="${role.id}" value="${role.name}">
                            </c:if>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            <div>
                <input type="submit">
            </div>
        </form>
    </div>
</body>
</html>
