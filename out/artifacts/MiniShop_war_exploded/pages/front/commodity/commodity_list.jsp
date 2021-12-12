<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://127.0.0.1/c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String showUrl = basePath + "pages/front/commodity/CommodityServletFront/show";
    String listUrl = basePath + "pages/front/commodity/CommodityServletFront/list";
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
                        <h1>商品列表</h1>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <section class="shop__area pt-100 pb-100">
        <div class="container">
            <select onchange="goList('<%=listUrl%>',this.value)">
                <option value="0">====== 查看全部商品 ======</option>
                <c:forEach items="${allGenre}" var="genre">
                    <option value="${genre.gid}" ${genre.gid==paramValue?"selected":""}>${genre.title}</option>
                </c:forEach>
            </select>
            <c:if test="${allCommodity != null}" var="res">
            <div class="row">
                <div class="col-xl-12">
                    <div class="shop__content-area">
                        <div class="tab-content" id="pills-tabContent">
                            <div class="tab-pane fade show active" id="pills-grid" role="tabpanel" aria-labelledby="pills-grid-tab">
                                <div class="row custom-row-10">
                                    <c:forEach items="${allCommodity}" var="commodity">
                                    <div class="col-xl-3 col-lg-3 col-md-6 col-sm-6 custom-col-10">
                                        <div class="product__wrapper mb-60">
                                            <div class="product__thumb">
                                                <a href="<%=showUrl%>?cid=${commodity.cid}" class="w-img">
                                                    <img src="<%=basePath%>/upload/commodity/${commodity.photo}" alt="product-img">
                                                </a>
                                            </div>
                                            <div class="product__content p-relative">
                                                <div class="product__content-inner">
                                                    <h4><a href="<%=showUrl%>?cid=${commodity.cid}">${commodity.title}</a></h4>
                                                    <div class="product__price transition-3">
                                                        <span>${commodity.price}</span>
                                                    </div>
                                                </div>
                                                <div class="add-cart p-absolute transition-3">
                                                    <a href="<%=addCarUrl%>?cid=${commodity.cid}">+ Add to Cart</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                        <div class="row mt-40">
                            <div class="col-xl-12">
                                <div class="shop-pagination-wrapper d-md-flex justify-content-between align-items-center">
                                    <div id="splitBarDiv" style="float:right">
                                        <jsp:include page="/pages/split_page_plugin_bars.jsp"/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            </c:if>
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
