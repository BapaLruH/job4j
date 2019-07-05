<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Update user page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
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
                <c:if test="${isAdmin}">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.servletContext.contextPath}/roles">Roles</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.servletContext.contextPath}/places">Places</a>
                    </li>
                </c:if>
                <c:if test="${login != null}">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.servletContext.contextPath}/persons">Persons</a>
                    </li>
                </c:if>
            </ul>
            <c:if test="${login != null}">
                <span class="navbar-text mr-2">${login}</span>
                <form class="form-inline my-2 my-lg-0" action="${pageContext.servletContext.contextPath}/signIn" method="get">
                    <input type="hidden" name="logout" value="true">
                    <button class="btn btn-primary" type="submit">Logout</button>
                </form>
            </c:if>
        </div>
    </nav>
    <div>
        <c:choose>
            <c:when test="${result != null}">
                <div class="alert alert-danger text-center" role="alert">
                    <h4 class="text-danger">${result}</h4>
                </div>
            </c:when>
            <c:otherwise>
                <div class="alert alert-light text-center" role="alert">
                    <h4>Update user: ${user.name}</h4>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
    <div class="container">
        <form class="p-2" action="${pageContext.servletContext.contextPath}/users" method="post">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="id" value="${param.id}">
            <div class="row">
                <div class="col">
                    <label class="mr-2" for="name">Name:</label>
                    <input class="form-control" id="name" type="text" name="name" value="${user.name}">
                </div>
                <div class="col">
                    <label class="mr-2" for="login">Login:</label>
                    <input class="form-control" id="login" type="text" name="login" value="${user.login}">
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <label class="mr-2" for="email">Email:</label>
                    <input class="form-control" id="email" type="email" name="email" value="${user.email}">
                </div>
                <div class="col">
                    <label class="mr-2" for="password">Password:</label>
                    <input class="form-control" id="password" type="password" name="password" value="${user.password}">
                </div>
            </div>
            <div class="row">
                <div class="col-3">
                    <label class="mr-2" for="cities">City:</label>
                    <input type="hidden" id="city_id" name="city_id" value="">
                    <select class="form-control" id="cities">
                        <c:forEach items="${cities}" var="cityDb">
                            <c:choose>
                                <c:when test="${cityDb.id == user.city.id}">
                                    <option value="${cityDb.id}" selected>${cityDb.name}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${cityDb.id}">${cityDb.name}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div>
            </div>
                <c:choose>
                    <c:when test="${isAdmin}">
                        <c:forEach items="${roles}" var="role">
                            <c:choose>
                                <c:when test="${user.roles.contains(role)}">
                                <label class="form-check-inline">
                                        ${role.name} <input class="form-check-input" type="checkbox" name="${role.id}" value="${role.name}" checked>
                                </label>
                                </c:when>
                                <c:otherwise>
                                    <label class="form-check-inline">
                                        ${role.name} <input class="form-check-input" type="checkbox" name="${role.id}" value="${role.name}">
                                    </label>
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
                <div class="form-col pt-2">
                    <button type="submit" class="btn btn-primary">Submit</button>
                </div>
            </div>
        </form>
    </div>
    <script>
        var iCity_id = document.getElementById('city_id'),
            select = document.getElementById('cities');
        select.addEventListener("input", setCity, false);
        function setCity() {
            iCity_id.value = select[select.selectedIndex].value;
        }
        $(setCity());
    </script>
</body>
</html>
