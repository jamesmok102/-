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
  <c:if test="${allMembers != null}" var="res">
    <table class="layui-table">
      <thead>
      <tr>
        <th>用户名</th>
        <th>姓名</th>
        <th>电话</th>
        <th>日期</th>
      </thead>
      <tbody>
      <c:forEach items="${allMembers}" var="member">
        <tr>
          <td>
            ${member.mid}
          </td>
          <td>
            ${member.name}
          </td>
          <td>
              ${member.phone}
          </td>
          <td>
              ${member.regDate}
          </td>

        </tr>
      </c:forEach>
      </tbody>
    </table>
  </c:if>
</div>
</body>
</html>

