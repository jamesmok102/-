package cn.minishop.shop.servlet.back;

import cn.minishop.shop.factory.ServiceBackFactory;
import cn.minishop.shop.vo.Admin;
import cn.minishop.shop.vo.Commodity;
import cn.minishop.shop.vo.Genre;
import cn.minishop.util.validate.ValidateUtil;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

@WebServlet(name="CommodityServletBack", urlPatterns = "/pages/back/admin/commodity/CommodityServletBack/*")
public class CommodityServletBack extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "/pages/errors.jsp";
        String status = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);
        if (status != null) {
            if("insertPre".equals(status)) {
                try {
                    path = this.insertPre(request);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if ("insert".equals(status)) {
                try {
                    path = this.insert(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if ("list".equals(status)) {
                path = this.list(request);
            } else if ("listStatus".equals(status)) {
                path = this.listStatus(request);
            } else if ("updateStatus".equals(status)) {
                path = this.updateStatus(request);
            } else if ("updatePre".equals(status)) {
                try {
                    path = this.updatePre(request);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if ("update".equals(status)) {
                try {
                    path = this.update(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if("delete".equals(status)) {
                try {
                    path = this.delete(request);
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

    public String insertPre(HttpServletRequest request) throws Exception {
        Map<String, Object> map = ServiceBackFactory.getICommodityServiceBackInstance().insertPre();
        request.setAttribute("allGenre", map.get("allGenre"));
        return "/pages/back/admin/commodity/commodity_insert.jsp";
    }

    public String updatePre(HttpServletRequest request) throws Exception {
        String cid = request.getParameter("cid");
        String referer = request.getHeader("referer");
        if(ValidateUtil.validateEmpty("cid")){
            Map<String, Object> map = ServiceBackFactory.getICommodityServiceBackInstance().updatePre(Integer.parseInt(cid));
            request.setAttribute("allGenre", map.get("allGenre"));
            request.setAttribute("commodity", map.get("commodity"));
            request.setAttribute("back", "/pages/back/admin/commodity/CommodityServletBack" + referer.substring(referer.lastIndexOf("/")));
            return "/pages/back/admin/commodity/commodity_update.jsp";
        } else {
            request.setAttribute("msg","还未选择要更新的数据，请重新确认！！！");
            request.setAttribute("url","/pages/back/admin/commodity/CommodityServletBack" + referer.substring(referer.lastIndexOf("/")));
            return "/pages/forward.jsp";
        }

    }

    public String list(HttpServletRequest request) {
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
            Map<String,Object> map = ServiceBackFactory.getICommodityServiceBackInstance().list(currentPage,lineSize,column,keyWord) ;
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
        request.setAttribute("url", "/pages/back/admin/commodity/CommodityServletBack/list");
        return "/pages/back/admin/commodity/commodity_list.jsp";
    }

    public String listStatus(HttpServletRequest request) {
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
        int status = Integer.parseInt(request.getParameter("status"));
        try {
            Map<String,Object> map = ServiceBackFactory.getICommodityServiceBackInstance().listStatus(status,currentPage,lineSize,column,keyWord) ;
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
        request.setAttribute("url", "/pages/back/admin/commodity/CommodityServletBack/listStatus");
        request.setAttribute("paramName", "status");
        request.setAttribute("paramValue", String.valueOf(status));
        return "/pages/back/admin/commodity/commodity_list.jsp";
    }

    public String updateStatus(HttpServletRequest request) {
        String msg = null;
        String url = null;
        String referer = request.getHeader("referer");
        String type = request.getParameter("type");
        String ids = request.getParameter("ids");
        if (ValidateUtil.validateEmpty(ids)) {
            String result [] = ids.split("\\|");
            Set<Integer> all = new HashSet<Integer>();
            for (int x = 0; x < result.length; x++) {
                String temp [] = result[x].split(":");
                all.add(Integer.parseInt(temp[0]));
            }
            boolean flag = false;
            try {
                if ("up".equals(type)) {
                    flag = ServiceBackFactory.getICommodityServiceBackInstance().updateUp(all);
                }
                if ("down".equals(type)) {
                    flag = ServiceBackFactory.getICommodityServiceBackInstance().updateDown(all);
                }
                if ("delete".equals(type)) {
                    flag = ServiceBackFactory.getICommodityServiceBackInstance().updateDelete(all);
                }
                if (flag) {
                    msg = "商品状态更新成功！！！";
                } else {
                    msg = "商品状态更新失败！！";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            msg = "要更新的数据有错误，请重新操作！！！";
        }
        url = "/pages/back/admin/commodity/CommodityServletBack" + referer.substring(referer.lastIndexOf("/"));
        request.setAttribute("msg",msg);
        request.setAttribute("url",url);
        return "/pages/forward.jsp";
    }

    public String insert(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String msg = null;
        String url = null;
        SmartUpload smart = new SmartUpload();
        try {
            smart.initialize(super.getServletConfig(),request,response);
            smart.upload();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String gid = smart.getRequest().getParameter("gid");
        String title = smart.getRequest().getParameter("title");
        String price = smart.getRequest().getParameter("price");
        String amount = smart.getRequest().getParameter("amount");
        String note = smart.getRequest().getParameter("note");
        String status = smart.getRequest().getParameter("status");
        if (ValidateUtil.validateEmpty(title) &&
                ValidateUtil.validateRegex(price,"\\d+(\\.\\d{1,2})?") &&
                ValidateUtil.validateRegex(amount, "\\d+") &&
                ValidateUtil.validateEmpty(note) &&
                ValidateUtil.validateRegex(status, "\\d") &&
                ValidateUtil.validateRegex(gid, "\\d+")) {
            Commodity vo = new Commodity();
            vo.setTitle(title);
            vo.setPrice(Double.parseDouble(price));
            vo.setAmount(Integer.parseInt(amount));
            vo.setNote(note);
            vo.setStatus(Integer.parseInt(status));
            vo.setPubDate(new Date());
            Genre genre = new Genre();
            genre.setGid(Integer.parseInt(gid));
            vo.setGenre(genre);
            String aid = (String) request.getSession().getAttribute("aid");
            Admin admin = new Admin();
            admin.setAid(aid);
            vo.setAdmin(admin);
            if (smart.getFiles().getSize() > 0) {
                if (smart.getFiles().getFile(0).getContentType().contains("image")) {
                    vo.setPhoto(UUID.randomUUID()+"."+smart.getFiles().getFile(0).getFileExt());
                } else {
                    vo.setPhoto("nophoto.jpg");
                }
            } else {
                vo.setPhoto("nophoto.jpg");
            }
            if (ServiceBackFactory.getICommodityServiceBackInstance().insert(vo)) {
                String filePath = super.getServletContext().getRealPath("/upload/commodity/") + vo.getPhoto();
                if (smart.getFiles().getSize() > 0) {
                    if (smart.getFiles().getFile(0).getContentType().contains("image")) {
                        smart.getFiles().getFile(0).saveAs(filePath);
                    }
                }
                msg = "商品信息发布成功！！！";
            } else {
                msg = "商品信息发布失败！！！";
            }
            url = "/pages/back/admin/commodity/CommodityServletBack/insertPre";
        } else {
            msg = "商品增加数据出错，无法进行商品的信息发布！！！";
            url = "/pages/back/admin/commodity/CommodityServletBack/insertPre";
        }
        request.setAttribute("msg", msg);
        request.setAttribute("url", url);
        return "/pages/forward.jsp";
    }

    public String update(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String msg = null;
        String url = null;
        SmartUpload smart = new SmartUpload();
        try {
            smart.initialize(super.getServletConfig(), request, response);
            smart.upload();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String oldpic = smart.getRequest().getParameter("oldpic");
        String cid = smart.getRequest().getParameter("cid");
        String gid = smart.getRequest().getParameter("gid");
        String title = smart.getRequest().getParameter("title");
        String price = smart.getRequest().getParameter("price");
        String amount = smart.getRequest().getParameter("amount");
        String note = smart.getRequest().getParameter("note");
        String status = smart.getRequest().getParameter("status");
        if (ValidateUtil.validateEmpty(cid) &&
                ValidateUtil.validateEmpty(title) &&
                ValidateUtil.validateRegex(price, "\\d+(\\.\\d{1,2})?") &&
                ValidateUtil.validateRegex(amount, "\\d+") &&
                ValidateUtil.validateEmpty(note) &&
                ValidateUtil.validateRegex(status, "\\d") &&
                ValidateUtil.validateRegex(gid, "\\d+")) {
            Commodity vo = new Commodity();
            vo.setCid(Integer.parseInt(cid));
            vo.setTitle(title);
            vo.setPrice(Double.parseDouble(price));
            vo.setAmount(Integer.parseInt(amount));
            vo.setNote(note);
            vo.setStatus(Integer.parseInt(status));
            Genre item = new Genre();
            item.setGid(Integer.parseInt(gid));
            vo.setGenre(item);
            try {
                if (smart.getFiles().getSize() > 0) {
                    if (smart.getFiles().getFile(0).getContentType().contains("image")) {
                        if ("nophoto.jpg".equals(oldpic)) { // 之前没有上传图片，需要重新生成名称
                            vo.setPhoto(UUID.randomUUID() + "." + smart.getFiles().getFile(0).getFileExt());
                        } else {
                            vo.setPhoto(oldpic);
                        }
                    } else {
                        vo.setPhoto(oldpic);
                    }
                } else {
                    vo.setPhoto(oldpic);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (ServiceBackFactory.getICommodityServiceBackInstance().update(vo)) {
                    String filePath = super.getServletContext().getRealPath("/upload/commodity/") + vo.getPhoto();
                    if (smart.getFiles().getSize() > 0) {
                        if (smart.getFiles().getFile(0).getContentType().contains("image")) {
                            smart.getFiles().getFile(0).saveAs(filePath);
                        }
                    }
                    msg = "商品信息修改成功！";
                } else {
                    msg = "商品信息修改失败！";
                }
                url = smart.getRequest().getParameter("back");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            msg = "商品修改数据出错，无法进行商品的信息修改！";
            url = smart.getRequest().getParameter("back");
        }
        request.setAttribute("msg", msg);
        request.setAttribute("url", url);
        return "/pages/forward.jsp";
    }

    public String delete(HttpServletRequest request) throws Exception {
        String msg = null;
        String url = null;
        String referer = request.getHeader("referer");
        String ids = request.getParameter("ids");
        if (ValidateUtil.validateEmpty(ids)) {
            Set<Integer> allIds = new HashSet<Integer>();
            Set<String> allPhotos = new HashSet<String>();
            String result [] = ids.split("\\|");
            for (int x=0; x<result.length; x++) {
                String temp [] = result[x].split(":");
                allIds.add(Integer.parseInt(temp[0]));
                if (!"nophoto.jpg".equals(temp[1])) {
                    allPhotos.add(temp[1]);
                }
            }
            if (ServiceBackFactory.getICommodityServiceBackInstance().delete(allIds)) {
                if (allPhotos.size() > 0) {
                    Iterator<String> iter = allPhotos.iterator();
                    while (iter.hasNext()) {
                        String filePath = super.getServletContext().getRealPath("/upload/commodity/") + iter.next();
                        File file = new File(filePath);
                        if(file.exists()){
                            file.delete();
                        }
                    }
                }
                msg = "商品信息删除成功！！！";
            } else {
                msg = "商品信息删除失败！！！";
            }
        } else {
            msg = "删除的数据有误，请重新操作！！！";
        }
        url = "/pages/back/admin/commodity/CommodityServletBack" + referer.substring(referer.lastIndexOf("/"));
        request.setAttribute("msg",msg);
        request.setAttribute("url",url);
        return "/pages/forward.jsp";
    }

}