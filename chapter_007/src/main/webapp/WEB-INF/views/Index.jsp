<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Index page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script src="${pageContext.servletContext.contextPath}/scripts/main.js"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="${pageContext.servletContext.contextPath}/users">Users</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <c:if test="${param.id != null}">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.servletContext.contextPath}/users">All users</a>
                </li>
            </c:if>
            <c:if test="${isAdmin}">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.servletContext.contextPath}/create">Create user</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.servletContext.contextPath}/roles">Roles</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.servletContext.contextPath}/places">Places</a>
                </li>
            </c:if>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.servletContext.contextPath}/persons">Persons</a>
            </li>
        </ul>
        <c:if test="${login != null}">
            <span class="navbar-text mr-2">${login}</span>
        </c:if>
        <form class="form-inline my-2 my-lg-0" action="${pageContext.servletContext.contextPath}/signIn" method="get">
            <input type="hidden" name="logout" value="true">
            <button class="btn btn-primary" type="submit">Logout</button>
        </form>
    </div>
</nav>
<div>
    <div class="container">
        <c:choose>
            <c:when test="${access != null}">
                <div class="alert alert-light text-center" role="alert">
                    <h1><c:out value="${access}"/></h1>
                </div>
            </c:when>
            <c:when test="${result != null}">
                <div class="alert alert-light text-center" role="alert">
                    <h5><c:out value="${result}"/></h5>
                </div>
            </c:when>
            <c:otherwise>
                <div class="alert alert-light text-center" role="alert">
                    <h1><c:out value="Users list"/></h1>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
    <div class="container">
        <c:if test="${!users.isEmpty()}">
            <table class="table table-striped" id="table" border="3">
                <tr>
                    <th>id</th>
                    <th>name</th>
                    <th>login</th>
                    <th>email</th>
                    <th>roles</th>
                    <th>city</th>
                    <th>update</th>
                    <th>delete</th>
                </tr>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td>
                            <a href="${pageContext.servletContext.contextPath}/users?id=<c:out value="${user.id}"/>">
                                <c:out value="${user.id}"/>
                            </a>
                        </td>
                        <td><c:out value="${user.name}"/></td>
                        <td><c:out value="${user.login}"/></td>
                        <td><c:out value="${user.email}"/></td>
                        <td>
                            <c:if test="${!user.roles.isEmpty()}">
                                <select class="custom-select" id="roles">
                                    <c:forEach items="${user.roles}" var="role">
                                        <option value="<c:out value="${role.id}"/>"><c:out value="${role.name}"/></option>
                                    </c:forEach>
                                </select>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${user.city != null}">
                                <c:out value="${user.city.name}"/>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${isAdmin || user.login.equals(login)}">
                                <div class="text-center">
                                    <a class="badge badge-dark" href="${pageContext.servletContext.contextPath}/update?action=update&id=<c:out value="${user.id}"/>">update</a>
                                </div>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${isAdmin}">
                                <div class="text-center">
                                    <a class="badge badge-dark" href="${pageContext.servletContext.contextPath}/users" onclick="deleteElement('././users', ${user.id})">delete</a>
                                </div>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </div>
</div>
</body>
</html>
