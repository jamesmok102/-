<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String loginUrl = basePath + "pages/back/AdminLoginServletBack/login" ;
%>
<title>网站管理员登陆</title>
<base href="<%=basePath%>">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
<meta http-equiv="Cache-Control" content="no-siteapp" />

<link rel="shortcut icon" href="/favicon.ico" type="<%=basePath%>/X-admin/image/x-icon" />
<link rel="stylesheet" href="<%=basePath%>/X-admin/css/font.css">
<link rel="stylesheet" href="<%=basePath%>/X-admin/css/xadmin.css">
<script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script src="<%=basePath%>/X-admin/lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="<%=basePath%>/X-admin/js/xadmin.js"></script>

<body class="login-bg">

<div class="login layui-anim layui-anim-up">
	<div class="message">电子商务系统——后台管理登录</div>
	<div id="darkbannerwrap"></div>

	<form method="post" class="layui-form" action="<%=loginUrl%>">
		<input name="aid" placeholder="用户名"  type="text" lay-verify="required" class="layui-input" >
		<hr class="hr15">
		<input name="password" lay-verify="required" placeholder="密码"  type="password" class="layui-input">
		<hr class="hr15">
		<input value="登录" lay-submit lay-filter="login" style="width:100%;" type="submit">
		<hr class="hr20" >
	</form>
</div>