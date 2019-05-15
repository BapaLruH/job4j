<%@ page import="ru.job4j.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.job4j.service.ValidateService" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index page</title>
</head>
<body>
    <div>
        <form>
            <button formaction="<%=request.getContextPath()%>/create" formmethod="get" value="">create user</button>
        </form>
    </div>
    <div>
        <% List<User> users = ValidateService.getInstance().findAll();
        String resultOperation = (String) request.getAttribute("result");
        if (resultOperation != null) { %>
            <h5><%=resultOperation%></h5>
        <% } %>
        <% if (users != null && !users.isEmpty()) { %>
        <table border="1">
            <tr>
                <th>id</th>
                <th>name</th>
                <th>login</th>
                <th>email</th>
                <th>update</th>
                <th>delete</th>
            </tr>
            <% for (User user : users) { %>
                <tr>
                    <td><%=user.getId()%></td>
                    <td><%=user.getName()%></td>
                    <td><%=user.getLogin()%></td>
                    <td><%=user.getEmail()%></td>
                    <td>
                        <form action="<%=request.getContextPath()%>/update.jsp" method="get">
                            <input type="hidden" name="action" value="update">
                            <input type="hidden" name="id" value="<%=user.getId()%>">
                            <input type="hidden" name="name" value="<%=user.getName()%>">
                            <input type="hidden" name="login" value="<%=user.getLogin()%>">
                            <input type="hidden" name="email" value="<%=user.getEmail()%>">
                            <input type="submit" value="update">
                        </form>
                    </td>
                    <td>
                        <form action="<%=request.getContextPath()%>/users" method="post">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="id" value="<%=user.getId()%>">
                            <input type="submit" value="delete">
                        </form>
                    </td>
                </tr>
            <% } %>
        </table>
        <% } %>
    </div>
</body>
</html>
