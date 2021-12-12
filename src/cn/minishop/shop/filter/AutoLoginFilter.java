package cn.minishop.shop.filter;

import cn.minishop.shop.factory.ServiceFrontFactory;
import cn.minishop.shop.vo.Member;
import cn.minishop.util.CookieUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * 若session中没有mid参数，没有进行登入，这时可以根据cookie来看是否进行自动登入
 * 若cookie中有mid和password，则帮助用户进行自动登入
 */

@WebFilter(filterName = "AutoLoginFilter", urlPatterns = {"/index.jsp", "/pages/front/*"})
public class AutoLoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession ses = request.getSession();
        if (ses.getAttribute("mid") == null) {
            Map<String, String> map = CookieUtil.load(request);
            if (map.containsKey("mid") && map.containsKey("password")) {
                Member vo = new Member();
                vo.setMid(map.get("mid"));
                vo.setPassword(map.get("password"));
                try {
                    if (ServiceFrontFactory.getIMemberServiceFrontInstance().login(vo)) {
                        ses.setAttribute("mid",vo.getMid());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        //Filter.super.destroy();
    }
}
