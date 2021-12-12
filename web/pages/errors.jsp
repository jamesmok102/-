<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://127.0.0.1/c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>迷你商店</title>
    <link type="text/css" rel="stylesheet" href="css/jamesmok.css">
    <script type="text/javascript" src="js/jamesmok.js"></script>

</head>
<body>
<h1>程序发生错误，如有问题，请与管理员联系！！！！</h1>
</body>
</html>
