<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Role page</title>
</head>
    <body>
        <div>
            <form>
                <button formaction="${pageContext.servletContext.contextPath}/" formmethod="get">back</button>
                <c:if test="${param.id != null}">
                    <button formaction="${pageContext.servletContext.contextPath}/roles" formmethod="get" value="">all roles</button>
                </c:if>
                <button formaction="${pageContext.servletContext.contextPath}/signIn" formmethod="get" name="logout" value="true">logout</button>
            </form>
        </div>
        <div>
            <c:if test="${result != null}">
                <h4><c:out value="${result}"/></h4>
            </c:if>
        </div>
        <div>
            <form action="${pageContext.servletContext.contextPath}/roles" method="post">
                <input type="hidden" name="action" value="add">
                Name: <input type="text" placeholder="name" name="name">
                <input type="submit" value="Создать">
            </form>
        </div>
        <div>
            <c:if test="${!roles.isEmpty()}">
                <table border="1">
                    <tr>
                       <th>id</th>
                       <th>name</th>
                       <th>default_role</th>
                       <th>update</th>
                       <th>delete</th>
                    </tr>
                    <c:forEach items="${roles}" var="role">
                        <tr>
                            <td>
                                <a href="${pageContext.servletContext.contextPath}/roles?id=<c:out value="${role.id}"/>">
                                    <c:out value="${role.id}"/>
                                </a>
                            </td>
                            <c:choose>
                                <c:when test="${param.id != null}">
                                    <c:choose>
                                        <c:when test="${!role.name.equals('Admin')}">
                                            <form id="role_update" action="${pageContext.servletContext.contextPath}/roles" method="post">
                                                <input type="hidden" name="action" value="update">
                                                <input type="hidden" name="id" value="<c:out value="${role.id}"/>">
                                                <td><input type="text" name="name" value="<c:out value="${role.name}"/>"></td>
                                                <c:choose>
                                                    <c:when test="${role.defaultRole}">
                                                        <td><input type="checkbox" name="defaultRole" value="<c:out value="${role.defaultRole}"/>" checked></td>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <td><input type="checkbox" name="defaultRole" value="<c:out value="${role.defaultRole}"/>"></td>
                                                    </c:otherwise>
                                                </c:choose>
                                                <td><input type="submit" value="update"></td>
                                            </form>
                                            <td></td>
                                        </c:when>
                                        <c:otherwise>
                                            <td>${role.name}</td>
                                            <td></td>
                                            <td></td>
                                            <td></td>
                                        </c:otherwise>
                                    </c:choose>
                                </c:when>
                                <c:otherwise>
                                    <td>${role.name}</td>
                                    <c:choose>
                                        <c:when test="${role.defaultRole}">
                                            <td><input type="checkbox" name="defaultRole" value="<c:out value="${role.defaultRole}"/>" checked disabled></td>
                                        </c:when>
                                        <c:otherwise>
                                            <td><input type="checkbox" name="defaultRole" value="<c:out value="${role.defaultRole}"/>" disabled></td>
                                        </c:otherwise>
                                    </c:choose>
                                    <td></td>
                                    <c:choose>
                                        <c:when test="${!role.name.equals('Admin')}">
                                            <td>
                                                <form action="${pageContext.servletContext.contextPath}/roles" method="post">
                                                    <input type="hidden" name="action" value="delete">
                                                    <input type="hidden" name="id" value="<c:out value="${role.id}"/>">
                                                    <input type="submit" value="delete">
                                                </form>
                                            </td>
                                        </c:when>
                                        <c:otherwise>
                                            <td></td>
                                        </c:otherwise>
                                    </c:choose>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
        </div>
    </body>
</html>
