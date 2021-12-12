<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://127.0.0.1/c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String updateUrl = basePath + "/pages/back/admin/genre/GenreServletBack/update";
    String deleteUrl = basePath + "/pages/back/admin/genre/GenreServletBack/delete?p=p";
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
    <script type="text/javascript" src="js/genre.js"></script>
</head>
<body>

<div class="x-body">
    <xblock>
        <button class="layui-btn layui-btn-danger" onclick="deleteAll('<%=deleteUrl%>','ids','tgid')"><i class="layui-icon"></i>批量删除</button>
    </xblock>
    <c:if test="${allGenres != null}" var="res">
    <table class="layui-table">
        <thead>
        <tr>
            <th>
                <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i class="layui-icon">&#xe605;</i></div>
            </th>
            <th>商品分类</th>
            <th>操作</th>
        </thead>
        <tbody>
        <c:forEach items="${allGenres}" var="genre">
        <tr>
            <td>
                <input type="checkbox" name="tgid" id="tgid" value="${genre.gid}">
            </td>
            <td>
                <input type="text" name="title-${genre.gid}" id="title-${genre.gid}" value="${genre.title}">
                <span id="title-${genre.gid}Msg"></span>
            </td>
            <td><input type="button" value="修改" onclick="goUpdate(${genre.gid})"></td>

        </tr>
        </c:forEach>
        </tbody>
    </table>
    </c:if>
    <form id="itemForm" method="post" action="<%=updateUrl%>">
        <input type="hidden" name="gid" id="gid">
        <input type="hidden" name="title" id="title">
    </form>
</div>

<!--<div id="mainDiv">
    <c:if test="${allGenres != null}" var="res">
        <table border="1" cellpadding="5" cellspacing="0" bgcolor="F2F2F2">
            <tr onmouseover="changeCode(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td colspan="3">商品类型列表</td>
            </tr>
            <tr onmouseover="changeCode(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td><input type="checkbox" onclick="checkboxSelect(this,'tgid')"></td>
                <td>类型</td>
                <td>操作</td>
            </tr>
            <c:forEach items="${allGenres}" var="genre">
                <tr onmouseover="changeCode(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                    <td><input type="checkbox" name="tgid" id="tgid" value="${genre.gid}"></td>
                    <td>
                        <input type="text" name="title-${genre.gid}" id="title-${genre.gid}" value="${genre.title}">
                        <span id="title-${genre.gid}Msg"></span>
                    </td>
                    <td><input type="button" value="修改" onclick="goUpdate(${genre.gid})"></td>
                </tr>
            </c:forEach>
        </table>
        <input type="button" value="删除商品分类信息" onclick="deleteAll('<%=deleteUrl%>','ids','tgid')">
    </c:if>
</div>
<form id="itemForm" method="post" action="<%=updateUrl%>">
    <input type="hidden" name="gid" id="gid">
    <input type="hidden" name="title" id="title">
</form>-->
</body>
</html>
