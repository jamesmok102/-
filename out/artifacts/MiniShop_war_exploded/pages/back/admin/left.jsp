<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta charset="UTF-8">
  <title>后台管理系统</title>
  <meta name="renderer" content="webkit|ie-comp|ie-stand">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
  <meta http-equiv="Cache-Control" content="no-siteapp" />

  <link rel="shortcut icon" href="<%=basePath%>/X-admin/favicon.ico" type="image/x-icon" />
  <link rel="stylesheet" href="<%=basePath%>/X-admin/css/font.css">
  <link rel="stylesheet" href="<%=basePath%>/X-admin/css/xadmin.css">
  <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
  <script src="<%=basePath%>/X-admin/lib/layui/layui.js" charset="utf-8"></script>
  <script type="text/javascript" src="<%=basePath%>/X-admin/js/xadmin.js"></script>
</head>
<body>
<div class="left-nav">
  <div id="side-nav">
    <ul id="nav">
      <li>
        <a href="javascript:;">
          <i class="iconfont">&#xe6b8;</i>
          <cite>用户管理</cite>
          <i class="iconfont nav_right">&#xe697;</i>
        </a>
        <ul class="sub-menu">
          <li>
            <a _href="<%=basePath%>/pages/back/admin/member/MemberServletBack/list">
              <i class="iconfont">&#xe6a7;</i>
              <cite>用户列表</cite>
            </a>
          </li >
      </li>
      <li>
        <a href="javascript:;">
          <i class="iconfont">&#xe723;</i>
          <cite>订单管理</cite>
          <i class="iconfont nav_right">&#xe697;</i>
        </a>
        <ul class="sub-menu">
          <li>
            <a _href="<%=basePath%>pages/back/admin/orders/OrdersServletBack/list">
              <i class="iconfont">&#xe6a7;</i>
              <cite>全部订单</cite>
            </a>
          </li >
        </ul>
      </li>
      <li>
        <a href="javascript:;">
          <i class="iconfont">&#xe726;</i>
          <cite>商品管理</cite>
          <i class="iconfont nav_right">&#xe697;</i>
        </a>
        <ul class="sub-menu">
          <li>
            <a _href="<%=basePath%>pages/back/admin/genre/genre_insert.jsp">
              <i class="iconfont">&#xe6a7;</i>
              <cite>增加分类</cite>
            </a>
          </li >
          <li>
            <a _href="<%=basePath%>pages/back/admin/genre/GenreServletBack/list">
              <i class="iconfont">&#xe6a7;</i>
              <cite>分类列表</cite>
            </a>
          </li >
          <li>
            <a _href="<%=basePath%>pages/back/admin/commodity/CommodityServletBack/insertPre">
              <i class="iconfont">&#xe6a7;</i>
              <cite>增加商品</cite>
            </a>
          </li >
          <li>
            <a _href="<%=basePath%>pages/back/admin/commodity/CommodityServletBack/list">
              <i class="iconfont">&#xe6a7;</i>
              <cite>商品列表</cite>
            </a>
          </li >
          <li>
            <a _href="<%=basePath%>pages/back/admin/commodity/CommodityServletBack/listStatus?status=1">
              <i class="iconfont">&#xe6a7;</i>
              <cite>上架商品</cite>
            </a>
          </li >
          <li>
            <a _href="<%=basePath%>pages/back/admin/commodity/CommodityServletBack/listStatus?status=0">
              <i class="iconfont">&#xe6a7;</i>
              <cite>下架商品</cite>
            </a>
          </li >
          <li>
            <a _href="<%=basePath%>pages/back/admin/commodity/CommodityServletBack/listStatus?status=2">
              <i class="iconfont">&#xe6a7;</i>
              <cite>删除商品</cite>
            </a>
          </li >
        </ul>
      </li>
    </ul>
  </div>
</div>
</body>
</html>
