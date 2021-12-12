package cn.minishop.shop.servlet.front;

import cn.minishop.shop.factory.ServiceFrontFactory;
import cn.minishop.shop.vo.Member;
import cn.minishop.util.validate.ValidateUtil;
import com.jspsmart.upload.SmartUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="MemberInfoServletFront", urlPatterns = "/pages/front/member/MemberInfoServletFront/*")
public class MemberInfoServletFront extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "/pages/errors.jps";
        String status = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);
        if (status != null) {
            if("updatePre".equals(status)) {
                try {
                    path = this.updatePre(request);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if ("update".equals(status)) {
                try {
                    path = this.update(request,response);
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

    public String updatePre(HttpServletRequest request) throws Exception {
        String mid = (String) request.getSession().getAttribute("mid");
        request.setAttribute("member", ServiceFrontFactory.getIMemberServiceFrontInstance().updatePre(mid));

        return "/pages/front/member/member_update.jsp";
    }

    public String update(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String mid = (String) request.getSession().getAttribute("mid");
        SmartUpload smart = new SmartUpload();
        try {
            smart.initialize(super.getServletConfig(),request,response);
            smart.upload();
        } catch (Exception e) {
        }
        String name = smart.getRequest().getParameter("name");
        String phone = smart.getRequest().getParameter("phone");
        String address = smart.getRequest().getParameter("address");
        String msg = null;
        String url = null;
        if (ValidateUtil.validateEmpty(name) &&
                ValidateUtil.validateEmpty(phone) &&
                ValidateUtil.validateEmpty(address)) {
            Member vo = new Member();
            vo.setMid(mid);
            vo.setName(name);
            vo.setPhone(phone);
            vo.setAddress(address);
            if (ServiceFrontFactory.getIMemberServiceFrontInstance().update(vo)) {
                msg = "用户信息更新成功！！！";

            } else {
                msg = "用户信息更新失败！！！";
            }
        } else {
            msg = "要更新的数据不完整！！！";
            url = "/pages/front/member/MemberInfoServletFront/updatePre";
        }
        request.setAttribute("msg",msg);
        request.setAttribute("url",url);
        return "/pages/forward.jsp";
    }
}