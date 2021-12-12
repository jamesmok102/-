<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://127.0.0.1/c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String showUrl = basePath + "pages/back/admin/orders/OrdersServletBack/show";
%>

<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <title>欢迎页面-X-admin2.0</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="shortcut icon" href="<%=basePath%>/X-admin/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="<%=basePath%>/X-admin//css/font.css">
    <link rel="stylesheet" href="<%=basePath%>/X-admin//css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/X-admin//lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="<%=basePath%>/X-admin//js/xadmin.js"></script>
    <script type="text/javascript" src="js/jamesmok.js"></script>
</head>
<body>

<div class="x-body">
    <c:if test="${allOrders != null}" var="res">
        <table class="layui-table">
            <thead>
            <tr>
                <th>订单编号</th>
                <th>接收人</th>
                <th>联系电话</th>
                <th>地址</th>
                <th>下单日期</th>
            </thead>
            <c:forEach items="${allOrders}" var="orders">
            <tbody>
                <td><a href="<%=showUrl%>?oid=${orders.oid}">${orders.oid}</a></td>
                <td>${orders.name}</td>
                <td>${orders.phone}</td>
                <td>${orders.address}</td>
                <td>${orders.creDate}</td>
            </tbody>
            </c:forEach>
        </table>
    </c:if>
    <div id="splitBarDiv" style="float:right">
        <jsp:include page="/pages/split_page_plugin_bars.jsp"/>
    </div>
</div>
</body>
</html>


