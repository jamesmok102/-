package cn.minishop.shop.servlet.front;

import cn.minishop.shop.factory.ServiceFrontFactory;
import cn.minishop.shop.util.ShopcarCookieUtil;
import cn.minishop.shop.vo.Commodity;
import cn.minishop.shop.vo.Member;
import cn.minishop.shop.vo.Shopcar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet(name="ShopcarServletFront", urlPatterns = "/pages/front/shopcar/ShopcarServletFront/*")
public class ShopcarServletFront extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "/pages/errors.jps";
        String status = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);
        if (status != null) {
            if("insert".equals(status)) {
                try {
                    path = this.insert(request,response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if ("list".equals(status)) {
                try {
                    path = this.list(request);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if ("update".equals(status)) {
                try {
                    path = this.update(request,response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if ("delete".equals(status)) {
                try {
                    path = this.delete(request,response);
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

    public String insert(HttpServletRequest request, HttpServletResponse response) throws Exception {

        int cid = Integer.parseInt(request.getParameter("cid"));
        String mid = (String) request.getSession().getAttribute("mid");
        Shopcar vo = new Shopcar();
        Member member = new Member();
        member.setMid(mid);
        vo.setMember(member);
        Commodity commodity = new Commodity();
        commodity.setCid(cid);
        vo.setCommodity(commodity);
        String msg = null;

        if (ServiceFrontFactory.getIShopcarServiceFrontInstance().addCar(vo)) {
            msg = "购物车添加成功！！！";
        } else {
            msg = "购物车添加失败！！！";
        }
        //String url = "/pages/front/commodity/CommodityServletFront/list";
        String referer = request.getHeader("referer");
        String url = "/pages/front/commodity/CommodityServletFront" + referer.substring(referer.lastIndexOf("/"));
        request.setAttribute("msg",msg);
        request.setAttribute("url",url);
        return "/pages/forward.jsp";
    }

    public String list(HttpServletRequest request) throws Exception {
        String mid = (String) request.getSession().getAttribute("mid");
        Map<String,Object> map = ServiceFrontFactory.getIShopcarServiceFrontInstance().listCar(mid);
        request.setAttribute("allCommodity", map.get("allCommodity"));
        request.setAttribute("allCars", map.get("allShopcars"));
        return "/pages/front/shopcar/car_list.jsp";
    }

    public String update(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String mid = (String) request.getSession().getAttribute("mid");
        ShopcarCookieUtil.clearCar(request,response);
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        Enumeration<String> enu = request.getParameterNames();
        Set<Shopcar> all = new HashSet<Shopcar>();
        while (enu.hasMoreElements()) {
            String temp = enu.nextElement();
            int cid = Integer.parseInt(temp);
            int count = Integer.parseInt(request.getParameter(temp));
            Shopcar vo = new Shopcar();
            Member member = new Member();
            member.setMid(mid);
            vo.setMember(member);
            Commodity commodity = new Commodity();
            commodity.setCid(cid);
            vo.setCommodity(commodity);
            vo.setAmount(count);
            all.add(vo);
        }
        String msg = null;
        String url = null;
        if (ServiceFrontFactory.getIShopcarServiceFrontInstance().update(mid,all)) {
            msg = "购物车信息更新成功！";
        } else {
            msg = "购物车信息更新失败！";
        }
        url = "/pages/front/shopcar/ShopcarServletFront/list";
        request.setAttribute("msg",msg);
        request.setAttribute("url",url);
        return "/pages/forward.jsp";
    }

    public String delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String mid = (String) request.getSession().getAttribute("mid");
        String ids = request.getParameter("ids");
        String result [] = ids.split("\\|");
        Set<Integer> set = new HashSet<Integer>();
        for (int x=0; x< result.length; x++){
            set.add(Integer.parseInt(result[x]));
        }
        String msg = null;
        String url = null;
        if (ServiceFrontFactory.getIShopcarServiceFrontInstance().deleteCar(mid,set)) {
            msg = "购物车商品删除成功！！！";
        } else {
            msg = "购物车商品删除失败！！！";
        }
        url = "/pages/front/shopcar/ShopcarServletFront/list";
        request.setAttribute("msg",msg);
        request.setAttribute("url",url);
        return "/pages/forward.jsp";
    }
}