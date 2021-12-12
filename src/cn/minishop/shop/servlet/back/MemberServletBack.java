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
import java.util.Map;

@WebServlet(name="MemberServletBack", urlPatterns = "/pages/back/admin/member/MemberServletBack/*")
public class MemberServletBack extends HttpServlet{
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "/pages/errors.jsp";
        String status = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);
        if (status != null) {
            if("list".equals(status)) {
                try {
                    path = this.list(request);
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
        int currentPage = 1;
        int lineSize = 10;
        String column = null;
        String keyWord = null;
        String columnData = "用户名:mid|真实姓名:name|联系电话:phone|地址:address";
        try {
            currentPage = Integer.parseInt(request.getParameter("cp")) ;
        }catch (Exception e) {}
        try {
            lineSize = Integer.parseInt(request.getParameter("ls")) ;
        }catch (Exception e) {}
        column = request.getParameter("col");
        keyWord = request.getParameter("kw");
        if (column == null) {
            column = "mid";
        }
        if (keyWord == null) {
            keyWord = "";
        }
        try {
            Map<String,Object> map = ServiceBackFactory.getIMemberServiceBackInstance().list(currentPage,lineSize,column,keyWord) ;
            request.setAttribute("allMembers",map.get("allMembers"));
            request.setAttribute("allRecorders",map.get("memberCount"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("lineSize", lineSize);
        request.setAttribute("column", column);
        request.setAttribute("keyWord", keyWord);
        request.setAttribute("columnData", columnData);
        request.setAttribute("url", "/pages/back/admin/member/MemberServletBack/list");
        return "/pages/back/admin/member/member_list.jsp";
    }
}
