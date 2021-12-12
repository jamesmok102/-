<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://127.0.0.1/c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    String updateUrl = basePath + "/pages/back/admin/commodity/CommodityServletBack/update";
%>
<html>
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
    <form action="<%=updateUrl%>" method="post" onsubmit="return validateInsert()" enctype="multipart/form-data">
        <table border="1" cellpadding="5" cellspacing="0" bgcolor="F2F2F2">
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td colspan="4"><span class="title">修改商品信息</span></td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td>商品名称：</td>
                <td><input type="text" name="title" id="title" class="init" onblur="validateTitle()" value="${commodity.title}"></td>
                <td><span id="titleMsg"></span></td>
                <td rowspan="7"><div id="preview"><img src="upload/commodity/${commodity.photo}"></div></td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td>商品类型：</td>
                <td>
                    <c:if test="${allGenre != null}" var="res">
                        <select name="gid" id="gid">
                            <c:forEach items="${allGenre}" var="genre">
                                <option value="${genre.gid}" ${commodity.genre.gid==genre.gid?"selected":""}>${genre.title}</option>
                            </c:forEach>
                        </select>
                    </c:if>
                </td>
                <td><span id="gidMsg"></span></td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td>商品价格：</td>
                <td><input type="text" name="price" id="price" class="init" onblur="validatePrice()" value="${commodity.price}"></td>
                <td><span id="priceMsg"></span></td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td>库存数量：</td>
                <td><input type="text" name="amount" id="amount" class="init" onblur="validateAmount()" value="${commodity.amount}"></td>
                <td><span id="amountMsg"></span></td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td>商品相片：</td>
                <td><input type="file" name="photo" id="photo" class="init" onchange="preview(this)"></td>
                <td><span id="photoMsg"></span></td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td>发布状态：</td>
                <td>
                    <input type="radio" name="status" id="status" class="init" value="0" ${commodity.status==0?"checked":""}>下架
                    <input type="radio" name="status" id="status" class="init" value="1" ${commodity.status==1?"checked":""}>上架
                    <input type="radio" name="status" id="status" class="init" value="2" ${commodity.status==2?"checked":""}>删除
                </td>
                <td><span id="statusMsg"></span></td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td>商品描述:</td>
                <td colspan="3">&nbsp;</td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td colspan="4">
                    <textarea id="note" name="note" cols="80" rows="5">${commodity.note}</textarea>
                </td>
            </tr>
            <tr onmouseover="changeColor(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
                <td colspan="4">
                    <input type="hidden" name="oldpic" id="oldpic" value="${commodity.photo}">
                    <input type="hidden" name="cid" id="cid" value="${commodity.cid}">
                    <input type="hidden" name="back" id="back" value="${back}">
                    <input type="submit" value="修改">
                    <input type="reset" value="重置">
                </td>
            </tr>
        </table>
    </form>
    </c:if>
</div>

</body>
</html>
