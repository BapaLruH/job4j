<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Create user page</title>
    </head>
    <body>
        <div>
            <form>
                <button formaction="<%=request.getContextPath()%>/" formmethod="get">back</button>
            </form>
        </div>
        <div>
            <form action="<%=request.getContextPath()%>/users" method="post">
                <input type="hidden" name="action" value="add">
                <input type="text" placeholder="name" name="name">
                <input type="text" placeholder="login" name="login">
                <input type="email" placeholder="email" name="email">
                <input type="submit">
            </form>
        </div>
    </body>
</html>
