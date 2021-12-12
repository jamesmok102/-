<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://127.0.0.1/c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String addCarUrl = basePath + "pages/front/shopcar/ShopcarServletFront/insert";
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
                        <h1>商品详情</h1>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <c:if test="${commodity != null}">
    <section class="shop__area pb-65">
        <div class="shop__top grey-bg-6 pt-100 pb-90">
            <div class="container">
                <div class="row">
                    <div class="col-xl-6 col-lg-6">
                        <div class="product__modal-box d-flex">
                            <div class="tab-content mb-20" id="product-detailsContent">
                                <div class="tab-pane fade show active" id="pro-one" role="tabpanel" aria-labelledby="pro-one-tab">
                                    <div class="product__modal-img product__thumb w-img">
                                        <img src="<%=basePath%>/upload/commodity/${commodity.photo}" alt="">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-xl-6 col-lg-6">
                        <div class="product__modal-content product__modal-content-2">
                            <h4><a href="product-details.html">${commodity.title}</a></h4>
                            <div class="product__price-2 mb-25">
                                <span>${commodity.price}</span>
                            </div>
                            <div class="product__price-2 mb-25">
                                <span>商品数量：${commodity.amount}</span>
                            </div>
                            <div class="product__modal-des mb-30">
                                <p>${commodity.note}</p>
                            </div>
                            <div class="product__modal-form mb-30">
                                <form action="#">
                                    <div class="pro-quan-area d-sm-flex align-items-center">
                                        <div class="pro-cart-btn">
                                            <a href="<%=addCarUrl%>?cid=${commodity.cid}" class="add-cart-btn mb-20">+ Add to Cart</a>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="product__tag mb-25">
                                <span>商品分类:</span>
                                <span>${commodity.genre.title}</span>
                            </div>
                        </div>
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
    <c:if test="${commodity != null}">

        <table border="1" cellpadding="5" cellspacing="0" bgcolor="F2F2F2">
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td rowspan="7"><div id="preview"><img src="upload/commodity/${commodity.photo}"></div></td>
                <td>商品名称：</td>
                <td>${commodity.title}</td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td>商品类型：</td>
                <td>${commodity.genre.title}</td>

            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td>商品价格：</td>
                <td>${commodity.price}</td>

            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td>发布日期：</td>
                <td>${commodity.pubDate}</td>

            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td>库存数量：</td>
                <td>${commodity.amount}</td>

            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td colspan="3">
                    ${commodity.note}
                </td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td colspan="3">
                        <a href="<%=addCarUrl%>?cid=${commodity.cid}">加入购物车</a>
                </td>
            </tr>
        </table>

    </c:if>
</div>

</body>
</html>-->
