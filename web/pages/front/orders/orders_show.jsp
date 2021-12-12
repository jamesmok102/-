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
    <script type="text/javascript" src="<%=basePath%>/js/jamesmok.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/shopcar.js"></script>
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
    <section class="page__title p-relative d-flex align-items-center" data-background="<%=basePath%>/assets/img/page-title/page-title-1.jpg">
        <div class="container">
            <div class="row">
                <div class="col-xl-12">
                    <div class="page__title-inner text-center">
                        <h1>${mid}的订单</h1>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <c:if test="${orders != null}" var="res">
        <section class="cart-area pt-100 pb-100">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <form action="#">
                            <div class="table-content table-responsive">
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th class="cart-product-name">订单编号</th>
                                        <th class="cart-product-name">订单金额</th>
                                        <th class="cart-product-name">下单时间</th>
                                        <th class="cart-product-name">收件人</th>
                                        <th class="cart-product-name">联系电话</th>
                                        <th class="cart-product-name">收件地址</th>
                                    </tr>
                                    </thead>
                                        <tbody>
                                        <tr>
                                            <td class="product-name">${orders.oid}</td>
                                            <td class="product-name">${orders.pay}</td>
                                            <td class="product-name">${orders.creDate}</td>
                                            <td class="product-name">${orders.name}</td>
                                            <td class="product-name">${orders.phone}</td>
                                            <td class="product-name">${orders.address}</td>
                                        </tr>
                                        </tbody>
                                </table>

                            </div>
                        </form>
                    </div>
                </div>
                <div class="row">
                    <div class="col-12">
                        <form action="#">
                            <div class="table-content table-responsive">
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th class="cart-product-name">商品名称</th>
                                        <th class="cart-product-name">商品单价</th>
                                        <th class="cart-product-name">购买数目</th>
                                    </tr>
                                    </thead>
                                    <c:forEach items="${orders.allDetails}" var="details">
                                    <tbody>
                                    <tr>
                                        <td class="product-name"><a href="<%=showUrl%>?gid=${details.commodity.cid}">${details.title}</a></td>
                                        <td class="product-name">${details.price}</td>
                                        <td class="product-name">${details.amount}</td>
                                    </tr>
                                    </tbody>
                                    </c:forEach>
                                </table>

                            </div>
                        </form>
                        <div id="splitBarDiv" style="float:right">
                            <jsp:include page="/pages/split_page_plugin_bars.jsp"/>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </c:if>
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

<!--<html>
<head>
    <base href="<%=basePath%>">
    <title>迷你商店</title>
    <link type="text/css" rel="stylesheet" href="css/jamesmok.css">
    <script type="text/javascript" src="js/jamesmok.js"></script>
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
                    <td><a href="<%=showUrl%>?gid=${details.commodity.cid}">${details.title}</a></td>
                    <td>${details.price}</td>
                    <td>${details.amount}</td>
                </tr>
            </c:forEach>
        </table>

    </c:if>
</div>

</body>
</html>-->
