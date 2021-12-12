package cn.minishop.shop.servlet.back;

import cn.minishop.shop.factory.ServiceBackFactory;
import cn.minishop.shop.vo.Admin;
import cn.minishop.util.MD5Code;
import cn.minishop.util.validate.ValidateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="AdminLoginServletBack", urlPatterns = "/pages/back/AdminLoginServletBack/*")
public class AdminLoginServletBack extends HttpServlet{
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "/pages/errors.jsp";
        String status = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);
        if (status != null) {
            if("login".equals(status)) {
                try {
                    path = this.login(request);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if ("logout".equals(status)) {
                path = this.logout(request);
            }
        }
        request.getRequestDispatcher(path).forward(request, response);
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    public String login(HttpServletRequest request) throws Exception {
        String msg = null;
        String url = null;
        String aid = request.getParameter("aid");
        String password = request.getParameter("password");
        if(ValidateUtil.validateEmpty(aid) && ValidateUtil.validateEmpty(password)) {
            Admin vo = new Admin();
            vo.setAid(aid);
            vo.setPassword(new MD5Code().getMD5ofStr(password));
            if (ServiceBackFactory.getIAdminServiceBackInstance().login(vo)) {
                request.getSession().setAttribute("aid",aid);
                request.getSession().setAttribute("lastDate", vo.getLastDate());
                msg = "管理员登录成功！！！";
                url = "/pages/back/admin/index.jsp";
            } else {
                msg = "管理员登录信息错误，用户名或密码不正确！！！";
                url = "/pages/back/login.jsp";
            }
        } else {
            msg = "管理员登录信息错误，请重新输入！！！";
            url = "/pages/back/login.jsp";
        }
        request.setAttribute("msg",msg);
        request.setAttribute("url",url);
        return "/pages/forward.jsp";
    }
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        request.setAttribute("msg","管理员登出成功！！！");
        request.setAttribute("url","/pages/back/login.jsp");
        return "/pages/forward.jsp";
    }
}
