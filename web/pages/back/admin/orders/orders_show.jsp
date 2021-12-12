<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://127.0.0.1/c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String showUrl = basePath + "pages/front/commodity/CommodityServletFront/show";
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
    <c:if test="${orders != null}" var="res">
        <table class="layui-table">
            <thead>
            <tr>
                <th>订单编号</th>
                <th>订单金额</th>
                <th>下单时间</th>
                <th>收件人</th>
                <th>联系电话</th>
                <th>收件地址</th>
            </thead>
                <tbody>
                <td>${orders.oid}</td>
                <td>${orders.pay}</td>
                <td>${orders.creDate}</td>
                <td>${orders.name}</td>
                <td>${orders.phone}</td>
                <td>${orders.address}</td>
                </tbody>
        </table>
        <table class="layui-table">
            <thead>
            <tr>
                <th>商品名称</th>
                <th>商品单价</th>
                <th>商品数目</th>
            </thead>
            <c:forEach items="${orders.allDetails}" var="details">
            <tbody>
            <td><a href="<%=showUrl%>?gid=${details.commodity.cid}">${details.title}</a></td>
            <td>${details.price}</td>
            <td>${details.amount}</td>
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

<!--<html>
<head>
    <base href="<%=basePath%>">
    <title>迷你商店</title>
    <link type="text/css" rel="stylesheet" href="css/mldn.css">
    <script type="text/javascript" src="js/mldn.js"></script>
    <script type="text/javascript" src="js/commodity.js"></script>

</head>
<body>

<div id="mainDiv">
    <c:if test="${orders != null}">

        <table border="1" cellpadding="5" cellspacing="0" bgcolor="F2F2F2">
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td>订单编号:</td>
                <td>${orders.oid}</td>
                <td>订单金额:</td>
                <td>${orders.pay}</td>
                <td>下单时间:</td>
                <td>${orders.creDate}</td>
            </tr>
            <tr>
                <td>收件人:</td>
                <td>${orders.name}</td>
                <td>联系电话:</td>
                <td>${orders.phone}</td>
                <td>收件地址:</td>
                <td>${orders.address}</td>
            </tr>
        </table>
        <hr>
        <table border="1" cellpadding="5" cellspacing="0" bgcolor="F2F2F2">
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td>商品名称:</td>
                <td>商品单价:</td>
                <td>购买数量:</td>
            </tr>
            <c:forEach items="${orders.allDetails}" var="details">
                <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                    <td>${details.title}</td>
                    <td>${details.price}</td>
                    <td>${details.amount}</td>
                </tr>
            </c:forEach>
        </table>

    </c:if>
</div>

</body>
</html>-->
