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
            <c:if test="${result != null}">
                <h4><c:out value="${result}"/></h4>
            </c:if>
        </div>
        <div>
            <form action="${pageContext.servletContext.contextPath}/" method="post">
                <input type="hidden" name="action" value="add">
                <div>
                    Name:<input type="text" placeholder="name" name="name" value="<c:if test="${param.name != null}">${param.name}</c:if>">
                    Login:<input type="text" placeholder="login" name="login" value="<c:if test="${!result.contains('Login')}">${param.login}</c:if>">
                </div>
                <div>
                    Email:<input type="email" placeholder="email" name="email" value="<c:if test="${!result.contains('Email')}">${param.email}</c:if>">
                    Password:<input type="password" placeholder="password" name="password" value="<c:if test="${param.password != null}">${param.password}</c:if>">
                </div>
                <c:forEach items="${roles}" var="role">
                    <c:choose>
                        <c:when test="${isAdmin}">
                            <input type="checkbox" name="${role.id}" value="${role.name}">${role.name}
                        </c:when>
                        <c:otherwise>
                            <c:if test="${role.defaultRole}">
                                <input type="hidden" name="${role.id}" value="${role.name}"><%--${role.name}--%>
                            </c:if>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <div>
                    <input type="submit">
                </div>
            </form>
        </div>
    </body>
</html>
