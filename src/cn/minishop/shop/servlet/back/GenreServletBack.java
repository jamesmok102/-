package cn.minishop.shop.servlet.back;

import cn.minishop.shop.factory.ServiceBackFactory;
import cn.minishop.shop.vo.Genre;
import cn.minishop.util.validate.ValidateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@WebServlet(name="GenreServletBack", urlPatterns = "/pages/back/admin/genre/GenreServletBack/*")
public class GenreServletBack extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "/pages/errors.jsp";
        String status = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);
        if (status != null) {
            if("insert".equals(status)) {
                try {
                    path = this.insert(request);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if ("update".equals(status)) {
                try {
                    path = this.update(request);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if ("delete".equals(status)) {
                try {
                    path = this.delete(request);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if ("list".equals(status)) {
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

    public String insert(HttpServletRequest request) throws Exception {
        String msg = null;
        String url = null;
        String title = request.getParameter("title");
        if (ValidateUtil.validateEmpty(title)) {
            Genre vo = new Genre();
            vo.setTitle(title);
            if (ServiceBackFactory.getIGenreServiceBackInstance().insert(vo)) {
                msg = "商品分类增加成功！！！";
            } else {
                msg = "商品分类增加成功！！！";
            }
        } else {
            msg = "商品分类增加失败，确认是否输入正确！！！";
        }
        url = "/pages/back/admin/genre/genre_insert.jsp";
        request.setAttribute("msg",msg);
        request.setAttribute("url",url);
        return "/pages/forward.jsp";
    }

    public String update(HttpServletRequest request) throws Exception {
        String msg = null;
        String url = null;
        String gid = request.getParameter("gid");
        String title = request.getParameter("title");
        if (ValidateUtil.validateEmpty(gid) && ValidateUtil.validateEmpty(title))
        {
            Genre vo = new Genre();
            vo.setGid(Integer.parseInt(gid));
            vo.setTitle(title);
            if (ServiceBackFactory.getIGenreServiceBackInstance().update(vo)) {
                msg = "更新商品类型成功！！！";
            } else {
                msg = "更新商品类型失败，请重新确认要更新的数据！！！";
            }
        } else {
            msg = "要更新的数据错误，请重新确认要更新的数据！！！";
        }
        url = "/pages/back/admin/genre/GenreServletBack/list";
        request.setAttribute("msg",msg);
        request.setAttribute("url",url);
        return "/pages/forward.jsp";
    }

    public String delete(HttpServletRequest request) throws Exception {
        String msg = null;
        String url = null;
        String ids = request.getParameter("ids");
        if (ValidateUtil.validateEmpty(ids)) {
            String result [] = ids.split("\\|");
            Set<Integer> all = new HashSet<Integer>();
            for (int x= 0; x < result.length; x++) {
                all.add(Integer.parseInt(result[x]));
            }
            if(ServiceBackFactory.getIGenreServiceBackInstance().delete(all)) {
                msg = "商品类型删除成功！！！";
            } else {
                msg = "商品类型删除失败，请重新确认要删除的数据是否正确或存在！！！";
            }
        } else {
            msg = "要删除的数据错误，请重新确认要删除的数据是否正确或存在！！！";
        }
        url = "/pages/back/admin/genre/GenreServletBack/list";
        request.setAttribute("msg",msg);
        request.setAttribute("url",url);
        return "/pages/forward.jsp";
    }

    public String list(HttpServletRequest request) throws Exception {
        request.setAttribute("allGenres", ServiceBackFactory.getIGenreServiceBackInstance().list());
        return "/pages/back/admin/genre/genre_list.jsp";
    }

}
