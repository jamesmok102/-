<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://127.0.0.1/c" %>
<div id="headerDiv">
    迷你商店
    <a href="index.jsp">商城首页</a>
    <a href="pages/front/commodity/CommodityServletFront/list">商品列表</a>
    <a href="pages/front/shopcar/ShopcarServletFront/list">我的购物车</a>
    <c:if test="${mid == null}">
        <a href="pages/member_login.jsp">登录</a>
        <a href="pages/member_regist.jsp">注册</a>
    </c:if>
    <c:if test="${mid != null}">
        <a href="pages/front/member/MemberInfoServletFront/updatePre">个人信息</a>
        <a href="pages/front/orders/OrdersServletFront/list">全部订单</a>
        <a href="pages/MemberServletFront/logout">登出</a>
        用户ID:${mid}
    </c:if>
</div>
