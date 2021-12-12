package cn.minishop.shop.servlet.front;

import cn.minishop.shop.factory.ServiceBackFactory;
import cn.minishop.shop.factory.ServiceFrontFactory;
import cn.minishop.util.validate.ValidateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(name="CommodityServletFront", urlPatterns = "/pages/front/commodity/CommodityServletFront/*")
public class CommodityServletFront extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "/pages/errors.jps";
        String status = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);
        if (status != null) {
            if("list".equals(status)) {
                path = this.list(request);
            } else if ("show".equals(status)) {
                try {
                    path = this.show(request);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        request.getRequestDispatcher(path).forward(request, response);
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    public String list(HttpServletRequest request) {
        String gid = request.getParameter("gid");
        int currentPage = 1;
        int lineSize = 10;
        String column = null;
        String keyWord = null;
        String columnData = "商品名称:title|发布管理员:aid|";
        try {
            currentPage = Integer.parseInt(request.getParameter("cp")) ;
        }catch (Exception e) {}
        try {
            lineSize = Integer.parseInt(request.getParameter("ls")) ;
        }catch (Exception e) {}
        column = request.getParameter("col");
        keyWord = request.getParameter("kw");
        if (column == null) {
            column = "title";
        }
        if (keyWord == null) {
            keyWord = "";
        }
        try {
            Map<String,Object> map = null;
            if (gid == null || "0".equals(gid)) {
                map = ServiceFrontFactory.getICommodityServiceFrontInstance().list(currentPage,lineSize,column,keyWord) ;
            } else {
                map = ServiceFrontFactory.getICommodityServiceFrontInstance().listByGenre(Integer.parseInt(gid),currentPage,lineSize,column,keyWord) ;
            }
            request.setAttribute("allGenre",map.get("allGenre"));
            request.setAttribute("allCommodity",map.get("allCommodity"));
            request.setAttribute("allRecorders",map.get("CommodityCount"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("lineSize", lineSize);
        request.setAttribute("column", column);
        request.setAttribute("keyWord", keyWord);
        request.setAttribute("columnData", columnData);
        request.setAttribute("url", "/pages/front/commodity/CommodityServletFront/list");
        request.setAttribute("paramName", "gid");
        request.setAttribute("paramValue", gid);
        return "/pages/front/commodity/commodity_list.jsp";
    }

    public String show(HttpServletRequest request) throws Exception {
        String msg = null;
        String url = null;
        String cid = request.getParameter("cid");
        if(ValidateUtil.validateRegex(cid,"\\d+")){
            request.setAttribute("commodity",ServiceFrontFactory.getICommodityServiceFrontInstance().show(Integer.parseInt(cid)));
            return "/pages/front/commodity/commodity_show.jsp";
        } else {
            msg = "您所选择的商品信息有问题，请重新选择！！！";
            url = request.getHeader("referer");
            return "/pages/forward.jsp";
        }


    }

}