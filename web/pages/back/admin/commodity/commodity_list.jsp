<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://127.0.0.1/c" %>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://"
          + request.getServerName() + ":" + request.getServerPort()
          + path + "/";
  String updateUpUrl = basePath + "pages/back/admin/commodity/CommodityServletBack/updateStatus?type=up";
  String updateDownUrl = basePath + "pages/back/admin/commodity/CommodityServletBack/updateStatus?type=down";
  String updateDeleteUrl = basePath + "pages/back/admin/commodity/CommodityServletBack/updateStatus?type=delete";
  String updatePreUrl = basePath + "pages/back/admin/commodity/CommodityServletBack/updatePre";
  String deleteUrl = basePath + "pages/back/admin/commodity/CommodityServletBack/delete?p=p" ;

%>

<html>
<head>
  <base href="<%=basePath%>">
  <meta charset="UTF-8">
  <title>欢迎页面-X-admin2.0</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
  <link rel="shortcut icon" href="<%=basePath%>/X-admin/favicon.ico" type="image/x-icon" />
  <link rel="stylesheet" href="<%=basePath%>/X-admin//css/font.css">
  <link rel="stylesheet" href="<%=basePath%>/X-admin//css/xadmin.css">
  <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
  <script type="text/javascript" src="<%=basePath%>/X-admin//lib/layui/layui.js" charset="utf-8"></script>
  <script type="text/javascript" src="<%=basePath%>/X-admin//js/xadmin.js"></script>
  <script type="text/javascript" src="js/jamesmok.js"></script>
</head>
<body>

<div class="x-body">
  <c:if test="${allCommodity != null}" var="res">
    <table class="layui-table">
      <thead>
      <tr>
        <td><input type="checkbox" onclick="checkboxSelect(this,'cid')"></td>
        <td>图片</td>
        <td>名称</td>
        <td>价格</td>
        <td>发布日期</td>
        <td>库存量</td>
        <td>状态</td>
        <td>操作</td>
      </thead>
      <c:forEach items="${allCommodity}" var="commodity">
        <tbody>
        <td><input type="checkbox" id="cid" name="cid" value="${commodity.cid}:${commodity.photo}"></td>
        <td><img src="upload/commodity/${commodity.photo}" style="width:50px;height:50px;"></td>
        <td>${commodity.title}</td>
        <td>${commodity.price}</td>
        <td>${commodity.pubDate}</td>
        <td>${commodity.amount}</td>
        <td>
          <c:if test="${commodity.status == 0}">
            下架
          </c:if>
          <c:if test="${commodity.status == 1}">
            上架
          </c:if>
        </td>
        <td>
          <a href="<%=updatePreUrl%>?cid=${commodity.cid}">修改</a>
        </td>
        </tbody>
      </c:forEach>
    </table>
  </c:if>
  <c:if test="${param.status != 1}">
    <input type="button" value="批量上架" onclick="updateAll('<%=updateUpUrl%>','ids','cid')">
  </c:if>
  <c:if test="${param.status != 0}">
    <input type="button" value="批量下架" onclick="updateAll('<%=updateDownUrl%>','ids','cid')">
  </c:if>
  <c:if test="${param.status != 2}">
    <input type="button" value="移到回收站" onclick="deleteAll('<%=updateDeleteUrl%>','ids','cid')">
  </c:if>
  <c:if test="${param.status == 2}">
    <input type="button" value="完全删除" onclick="deleteAll('<%=deleteUrl%>','ids','cid')">
  </c:if>
  <div id="splitBarDiv" style="float:right">
    <jsp:include page="/pages/split_page_plugin_bars.jsp"/>
  </div>
</div>
</body>
</html>

<!--<html>
<head>
  <base href="<%=basePath%>">
  <title>迷你商店</title>
  <link type="text/css" rel="stylesheet" href="css/jamesmok.css">
  <script type="text/javascript" src="js/jamesmok.js"></script>

</head>
<body>
<div id="mainDiv">
  <h1>商品列表</h1>
  <c:if test="${allCommodity != null}" var="res">
    <div id="splitSearchDiv">
      <jsp:include page="/pages/split_page_plugin_search.jsp"/>
      <br>
    </div>
    <table border="1" cellpadding="5" cellspacing="0" bgcolor="F2F2F2", width="100%">
      <tr onmouseover="changeCode(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
        <td><input type="checkbox" onclick="checkboxSelect(this,'cid')"></td>
        <td>图片</td>
        <td>名称</td>
        <td>价格</td>
        <td>发布日期</td>
        <td>库存量</td>
        <td>状态</td>
        <td>操作</td>
      </tr>
      <c:forEach items="${allCommodity}" var="commodity">
        <tr onmouseover="changeCode(this,'white')" onmouseout="changeColor(this,'F2F2F2')">
          <td><input type="checkbox" id="cid" name="cid" value="${commodity.cid}:${commodity.photo}"></td>
          <td><img src="upload/commodity/${commodity.photo}" style="width:50px;height:50px;"></td>
          <td>${commodity.title}</td>
          <td>${commodity.price}</td>
          <td>${commodity.pubDate}</td>
          <td>${commodity.amount}</td>
          <td>
            <c:if test="${commodity.status == 0}">
              下架
            </c:if>
            <c:if test="${commodity.status == 1}">
              上架
            </c:if>
          </td>
          <td>
            <a href="<%=updatePreUrl%>?cid=${commodity.cid}">修改</a>
          </td>
        </tr>
      </c:forEach>
    </table>
    <c:if test="${param.status != 1}">
      <input type="button" value="批量上架" onclick="updateAll('<%=updateUpUrl%>','ids','cid')">
    </c:if>
    <c:if test="${param.status != 0}">
      <input type="button" value="批量下架" onclick="updateAll('<%=updateDownUrl%>','ids','cid')">
    </c:if>
    <c:if test="${param.status != 2}">
      <input type="button" value="移到回收站" onclick="deleteAll('<%=updateDeleteUrl%>','ids','cid')">
    </c:if>
    <c:if test="${param.status == 2}">
      <input type="button" value="完全删除" onclick="deleteAll('<%=deleteUrl%>','ids','cid')">
    </c:if>
    <div id="splitBarDiv" style="float:right">
      <jsp:include page="/pages/split_page_plugin_bars.jsp"/>
    </div>
  </c:if>
</div>
</body>
</html>-->
