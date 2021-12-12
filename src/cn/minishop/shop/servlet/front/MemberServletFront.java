package cn.minishop.shop.servlet.front;

import cn.minishop.shop.factory.ServiceFrontFactory;
import cn.minishop.shop.vo.Member;
import cn.minishop.util.CookieUtil;
import cn.minishop.util.MD5Code;
import cn.minishop.util.validate.ValidateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(name="MemberServletFront", urlPatterns = "/pages/MemberServletFront/*")
public class MemberServletFront extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "/pages/errors.jps";
        String status = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);
        if (status != null) {
            if ("regist".equals(status)) {
                path = this.regist(request) ;
            } else if ("login".equals(status)) {
                try {
                    path = this.login(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if ("logout".equals(status)) {
                try {
                    path = this.logout(request, response) ;
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
    public String regist(HttpServletRequest request) {
        String msg = null;
        String url = null;
        String mid = request.getParameter("mid");
        String password = request.getParameter("password");
        if (ValidateUtil.validateEmpty(mid) && ValidateUtil.validateEmpty(password)) {
            Member vo = new Member();
            vo.setMid(mid);
            vo.setPassword(new MD5Code().getMD5ofStr(password));
            vo.setRegDate(new Date());
            try{
                if (ServiceFrontFactory.getIMemberServiceFrontInstance().regist(vo)) {
                    msg = "用户注册成功";
                    url = "/index.jsp";
                } else {
                    msg = "用户注册失败，请填写新的用户信息！";
                    url = "/pages/member_regist.jsp";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            msg = "输入的用户注册信息不正确，请重新注册！";
            url = "/pages/member_regist.jsp";
        }
        request.setAttribute("msg",msg);
        request.setAttribute("url",url);
        return "/pages/forward.jsp";
    }

    public String login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String msg = null;
        String url = null;
        String mid = request.getParameter("mid");
        String password = request.getParameter("password");
        if (ValidateUtil.validateEmpty(mid) && ValidateUtil.validateEmpty(password)) {
            Member vo = new Member();
            vo.setMid(mid);
            vo.setPassword(new MD5Code().getMD5ofStr(password));
            if (ServiceFrontFactory.getIMemberServiceFrontInstance().login(vo)) {
                request.getSession().setAttribute("mid",mid);
                msg = "登录成功！！！！";
                url = "/index.jsp";
                if (request.getParameter("rememberMe") != null) {
                    int expiry = Integer.parseInt(request.getParameter("rememberMe"));
                    CookieUtil.save(response, request, "mid", vo.getMid(), expiry);
                    CookieUtil.save(response, request, "password", vo.getPassword(), expiry);
                }
            } else {
                msg = "错误的用户名或密码！！！！";
                url = "/pages/member_login.jsp";
            }
        } else {
            msg = "信息错误，请重新登录！";
            url = "pages/member_login.jsp";
        }
        request.setAttribute("msg",msg);
        request.setAttribute("url",url);
        return "/pages/forward.jsp";
    }

    public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CookieUtil.clear(request,response);
        request.getSession().invalidate();
        request.setAttribute("msg","已登出");
        request.setAttribute("url","/index.jsp");
        return "/pages/forward.jsp";
    }
}
