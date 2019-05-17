<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Index page</title>
    <style>
        select {
            width: 200px;
        }
    </style>
</head>
<body>
    <div>
        <form>
            <c:if test="${isAdmin}">
                <button formaction="${pageContext.servletContext.contextPath}/create" formmethod="get" value="">create user</button>  
                <button formaction="${pageContext.servletContext.contextPath}/roles" formmethod="get" value="">roles page</button>
            </c:if>
            <c:if test="${param.id != null}">
                <button formaction="${pageContext.servletContext.contextPath}/" formmethod="get" value="">all users</button>
            </c:if>
            <button formaction="${pageContext.servletContext.contextPath}/signIn" formmethod="get" name="logout" value="true">logout</button>
        </form>
    </div>
    <div>
        <c:if test="${access != null}">
            <h1><c:out value="${access}"/></h1>
        </c:if>
        <c:if test="${result != null}">
            <h5><c:out value="${result}"/></h5>
        </c:if>
        <c:if test="${!users.isEmpty()}">
            <table border="1">
                <tr>
                    <th>id</th>
                    <th>name</th>
                    <th>login</th>
                    <th>email</th>
                    <th>roles</th>
                    <th>update</th>
                    <th>delete</th>
                </tr>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td>
                            <a href="${pageContext.servletContext.contextPath}/?id=<c:out value="${user.id}"/>">
                                <c:out value="${user.id}"/>
                            </a>
                        </td>
                        <td><c:out value="${user.name}"/></td>
                        <td><c:out value="${user.login}"/></td>
                        <td><c:out value="${user.email}"/></td>
                        <td>
                            <c:if test="${!user.roles.isEmpty()}">
                                <select id="roles">
                                    <c:forEach items="${user.roles}" var="role">
                                        <option value="<c:out value="${role.id}"/>"><c:out value="${role.name}"/></option>
                                    </c:forEach>
                                </select>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${isAdmin || user.login.equals(login)}">
                                <form action="${pageContext.servletContext.contextPath}/update" method="get">
                                    <input type="hidden" name="action" value="update">
                                    <input type="hidden" name="id" value="<c:out value="${user.id}"/>">
                                    <input type="submit" value="update">
                                </form>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${isAdmin}">
                                <form action="${pageContext.servletContext.contextPath}/" method="post">
                                    <input type="hidden" name="action" value="delete">
                                    <input type="hidden" name="id" value="<c:out value="${user.id}"/>">
                                    <input type="submit" value="delete">
                                </form>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </div>
</body>
</html>
