<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Role page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script src="${pageContext.servletContext.contextPath}/scripts/main.js"></script>
</head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="${pageContext.servletContext.contextPath}/users">Home</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <c:if test="${param.id != null}">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.servletContext.contextPath}/roles">All roles</a>
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
            <c:if test="${result != null}">
                <h4><c:out value="${result}"/></h4>
            </c:if>
        </div>
        <div class="container p-2">
            <div>
                <form class="form-inline" action="${pageContext.servletContext.contextPath}/roles" method="post">
                    <div class="row">
                        <div class="col">
                            <input type="hidden" name="action" value="add">
                            <label class="mr-2" for="name">Name:</label>
                        </div>
                        <div class="col">
                            <input id="name" type="text" name="name">
                        </div>
                        <div class="form-col pl-2">
                            <button type="submit" class="btn btn-primary btn-sm">Create</button>
                        </div>
                    </div>
                </form>
            </div>
            <div>
                <c:if test="${!roles.isEmpty()}">
                    <table class="table table-striped" id="table" border="3">
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
                                                    <td><input class="form-control" type="text" name="name" value="<c:out value="${role.name}"/>"></td>
                                                    <c:choose>
                                                        <c:when test="${role.defaultRole}">
                                                            <td>
                                                                <label class="form-check-inline">
                                                                    <input class="form-check-input" type="checkbox" name="defaultRole" value="<c:out value="${role.defaultRole}"/>" checked>
                                                                </label>
                                                            </td>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td>
                                                                <label class="form-check-inline">
                                                                    <input class="form-check-input" type="checkbox" name="defaultRole" value="<c:out value="${role.defaultRole}"/>">
                                                                </label>
                                                            </td>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <td>
                                                        <div class="form-col pt-2">
                                                            <button type="submit" class="btn btn-dark">Update</button>
                                                        </div>
                                                    </td>
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
                                                <td>
                                                    <label class="form-check-inline">
                                                        <input class="form-check-input" type="checkbox" name="defaultRole" value="<c:out value="${role.defaultRole}"/>" checked disabled>
                                                    </label>
                                                </td>
                                            </c:when>
                                            <c:otherwise>
                                                <td>
                                                    <label class="form-check-inline">
                                                        <input class="form-check-input" type="checkbox" name="defaultRole" value="<c:out value="${role.defaultRole}"/>" disabled>
                                                    </label>
                                                </td>
                                            </c:otherwise>
                                        </c:choose>
                                        <td></td>
                                        <c:choose>
                                            <c:when test="${!role.name.equals('Admin')}">
                                                <td>
                                                    <div class="text-center">
                                                        <a class="badge badge-dark" href="${pageContext.servletContext.contextPath}/roles" onclick="deleteElement('././roles', ${role.id})">delete</a>
                                                    </div>
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
        </div>
    </body>
</html>
