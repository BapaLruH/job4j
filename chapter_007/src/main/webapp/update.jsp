<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <form>
        <button formaction="<%=request.getContextPath()%>/" formmethod="get">back</button>
    </form>
</div>
<div>
    <form action="<%=request.getContextPath()%>/users" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="<%=request.getParameter("id")%>">
        <input type="text" name="name" value="<%=request.getParameter("name")%>">
        <input type="text" name="login" value="<%=request.getParameter("login")%>">
        <input type="email" name="email" value="<%=request.getParameter("email")%>">
        <input type="submit">
    </form>
</div>
</body>
</html>
