<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://127.0.0.1/c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String updateUrl = basePath + "pages/front/member/MemberInfoServletFront/update";
%>

<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>$Title$</title>
    <!-- Place favicon.ico in the root directory -->
    <link rel="shortcut icon" type="image/x-icon" href="assets/img/favicon.png">
    <!-- CSS here -->
    <link rel="stylesheet" href="<%=basePath%>/assets/css/preloader.css">
    <link rel="stylesheet" href="<%=basePath%>/assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="<%=basePath%>/assets/css/slick.css">
    <link rel="stylesheet" href="<%=basePath%>/assets/css/metisMenu.css">
    <link rel="stylesheet" href="<%=basePath%>/assets/css/owl.carousel.min.css">
    <link rel="stylesheet" href="<%=basePath%>/assets/css/animate.min.css">
    <link rel="stylesheet" href="<%=basePath%>/assets/css/jquery.fancybox.min.css">
    <link rel="stylesheet" href="<%=basePath%>/assets/css/fontAwesome5Pro.css">
    <link rel="stylesheet" href="<%=basePath%>/assets/css/ionicons.min.css">
    <link rel="stylesheet" href="<%=basePath%>/assets/css/default.css">
    <link rel="stylesheet" href="<%=basePath%>/assets/css/style.css">
</head>
<body>
<div id="loading">
    <div id="loading-center">
        <div id="loading-center-absolute">
            <div class="object" id="first_object"></div>
            <div class="object" id="second_object"></div>
            <div class="object" id="third_object"></div>
        </div>
    </div>
</div>
<header>
    <div id="header-sticky" class="header__area grey-bg">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-xl-3 col-lg-3 col-md-4 col-sm-4">
                    <div class="logo">
                        <a href="index.html"><img src="<%=basePath%>/assets/img/logo/logo.png" alt="logo"></a>
                    </div>
                </div>
                <div class="col-xl-9 col-lg-9 col-md-8 col-sm-8">
                    <div class="header__right p-relative d-flex justify-content-between align-items-center">
                        <div class="main-menu d-none d-lg-block">
                            <nav>
                                <ul>
                                    <li><a href="<%=basePath%>/index.jsp">商城首页</a></li>
                                    <li><a href="<%=basePath%>/pages/front/commodity/CommodityServletFront/list?gid=0">商品列表</a></li>
                                    <li><a href="<%=basePath%>/pages/front/shopcar/ShopcarServletFront/list">我的购物车</a></li>
                                    <c:if test="${mid == null}">
                                        <li><a href="<%=basePath%>/pages/member_login.jsp">登入</a></li>
                                        <li><a href="<%=basePath%>/pages/member_regist.jsp">注册</a></li>
                                    </c:if>
                                    <c:if test="${mid != null}">
                                        <li><a href="<%=basePath%>/pages/front/member/MemberInfoServletFront/updatePre">个人信息</a></li>
                                        <li><a href="<%=basePath%>/pages/front/orders/OrdersServletFront/list">全部订单</a></li>
                                        <li><a href="<%=basePath%>/pages/MemberServletFront/logout">登出</a></li>
                                    </c:if>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>

<main>
    <section class="login-area pt-100 pb-100">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 offset-lg-2">
                    <div class="basic-login">
                        <h3 class="text-center mb-60">用户个人信息</h3>
                        <form action="<%=updateUrl%>" method="post" enctype="multipart/form-data">
                            <label>帐号名称：${member.mid}</label>
                            <label for="pass">真实姓名 <span>**</span></label>
                            <input id="pass" type="text" name="name" placeholder="真实姓名" value="${member.name}"/>
                            <label for="pass">联系电话 <span>**</span></label>
                            <input id="pass" type="text" name="phone" placeholder="真实姓名" value="${member.phone}"/>
                            <label for="pass">用户地址 <span>**</span></label>
                            <input id="pass" type="text" name="address" placeholder="用户地址" value="${member.address}"/>
                            <button class="os-btn w-100" type="submit">更新</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>
</main>

<script src="<%=basePath%>/assets/js/vendor/modernizr-3.5.0.min.js"></script>
<script src="<%=basePath%>/assets/js/vendor/jquery-3.5.1.min.js"></script>
<script src="<%=basePath%>/assets/js/vendor/waypoints.min.js"></script>
<script src="<%=basePath%>/assets/js/bootstrap.bundle.min.js"></script>
<script src="<%=basePath%>/assets/js/metisMenu.min.js"></script>
<script src="<%=basePath%>/assets/js/slick.min.js"></script>
<script src="<%=basePath%>/assets/js/jquery.fancybox.min.js"></script>
<script src="<%=basePath%>/assets/js/isotope.pkgd.min.js"></script>
<script src="<%=basePath%>/assets/js/owl.carousel.min.js"></script>
<script src="<%=basePath%>/assets/js/jquery-ui-slider-range.js"></script>
<script src="<%=basePath%>/assets/js/ajax-form.js"></script>
<script src="<%=basePath%>/assets/js/wow.min.js"></script>
<script src="<%=basePath%>/assets/js/imagesloaded.pkgd.min.js"></script>
<script src="<%=basePath%>/assets/js/main.js"></script>
</body>
</html>

