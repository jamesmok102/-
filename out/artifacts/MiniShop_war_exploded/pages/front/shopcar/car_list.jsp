<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://127.0.0.1/c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String deleteUrl = basePath + "pages/front/shopcar/ShopcarServletFront/delete?p=p";
    String updateUrl = basePath + "pages/front/shopcar/ShopcarServletFront/update";
    String ordersUrl = basePath + "pages/front/orders/OrdersServletFront/insert";
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
                        <h1>我的购物车</h1>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <section class="cart-area pt-100 pb-100">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <c:if test="${allCommodity != null}" var="res">
                        <form action="<%=updateUrl%>" method="post">
                            <div class="table-content table-responsive">
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th class="product-remove"><input type="checkbox" onclick="checkboxSelect(this,'cid')"></th>
                                        <th class="product-thumbnail">图片</th>
                                        <th class="cart-product-name">名称</th>
                                        <th class="product-price">价格</th>
                                        <th class="product-quantity">数量</th>
                                        <th class="product-subtotal">总价格</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${allCommodity}" var="commodity">
                                        <tr>
                                            <td class="product-remove"><input type="checkbox" id="cid" name="cid" value="${commodity.cid}"></td>
                                            <td class="product-thumbnail"><a href="#"><img src="<%=basePath%>/upload/commodity/${commodity.photo}" alt=""></a></td>
                                            <td class="product-name"><a href="#">${commodity.title}</a></td>
                                            <td class="product-price"><span class="amount"><span id="price-${commodity.cid}">${commodity.price}</span></span></td>
                                            <td class="product-quantity">
                                                <input type="button" value="-" onclick="subBut(${commodity.cid})">
                                                <input type="text" value="${allCars[commodity.cid]}" size="5" name="${commodity.cid}" id="${commodity.cid}">
                                                <input type="button" value="+" onclick="addBut(${commodity.cid})">
                                            </td>
                                            <td class="product-subtotal"><span class="amount">
                                        <span id="cal-${commodity.cid}"></span>
                                            <script type="text/javascript">
                                                calCommodity(${commodity.cid});
                                        </script>
                                    </span></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                            <div class="row">
                                <div class="col-12">
                                    <div class="coupon-all">
                                        <div class="coupon2">
                                            <input type="button" value="删除购物车商品" onclick="deleteAll('<%=deleteUrl%>','ids','cid')">
                                            <button class="os-btn os-btn-black" type="submit">Update cart</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-5 ml-auto">
                                    <div class="cart-page-total">
                                        <h2>Cart totals</h2>
                                        <ul class="mb-20">
                                            <li>Total <span id="result"></span></li>
                                        </ul>
                                        <a class="os-btn" href="<%=ordersUrl%>">Proceed to checkout</a>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </c:if>
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
