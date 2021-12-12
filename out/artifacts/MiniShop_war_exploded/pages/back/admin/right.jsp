<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<head>
	<meta charset="UTF-8">
	<title>后台管理系统</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
	<link rel="shortcut icon" href="<%=basePath%>/X-admin/favicon.ico" type="image/x-icon" />
	<link rel="stylesheet" href="<%=basePath%>/X-admin/css/font.css">
	<link rel="stylesheet" href="<%=basePath%>/X-admin/css/xadmin.css">
</head>
<body>
<div class="x-body layui-anim layui-anim-up">
	<blockquote class="layui-elem-quote">欢迎管理员
		！上次登入时间:${lastDate}</blockquote>
</div>
</body>
