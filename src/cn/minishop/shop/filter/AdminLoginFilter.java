package cn.minishop.shop.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 只要是访问到/pages/back/admin/下的所有页面，就执行filter，
 * 若session中有aid参数，则视为管理员有登入，可以访问该目录下的所有页面
 * 若没有aid参数，则视为没有经过admin登入直接访问该目录，直接转到admin登入页面
 */
@WebFilter(filterName = "AdminLoginFilter", urlPatterns = "/pages/back/admin/*")
public class AdminLoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession ses = request.getSession();
        if (ses.getAttribute("aid") != null) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            request.setAttribute("msg", "您还没有登录，请先登入再进行操作！！！");
            request.setAttribute("url", "/pages/back/login.jsp");
            request.getRequestDispatcher("/pages/forward.jsp").forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
