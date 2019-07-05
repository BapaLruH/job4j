<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Login page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</head>
<body>
    <div>
        <c:choose>
            <c:when test="${access != null}">
                <div class="alert alert-danger text-center" role="alert">
                    <h4 class="text-danger">${access}</h4>
                </div>
            </c:when>
            <c:otherwise>
                <div class="alert alert-light text-center" role="alert">
                    <h4>Enter a valid username or email and password</h4>
                </div>
            </c:otherwise>
        </c:choose>
    </div>

    <div class="container p-2">
        <form action="${pageContext.servletContext.contextPath}/signIn" method="post">
            <div class="form-group col-md-6 offset-md-3">
                <label for="Login">Login || Email</label>
                <input type="text" id="login" class="form-control" placeholder="login or email" name="login" value="<c:if test="${param.login != null}">${param.login}</c:if>">
            </div>
            <div class="form-group col-md-6 offset-md-3">
                <label for="password">Password</label>
                <input type="password" id="password" class="form-control" placeholder="password" name="password">
            </div>

            <div class="form-row pl-4">
                <div class="form-col offset-md-3">
                    <button type="submit" class="btn btn-primary">Login</button>
                </div>
                <div class="form-col-ml">
                    <button class="btn btn-default border" formaction="${pageContext.servletContext.contextPath}/create" formmethod="get">Registration</button>
                </div>
            </div>
        </form>
    </div>
</body>
</html>
