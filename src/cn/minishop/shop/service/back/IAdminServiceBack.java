package cn.minishop.shop.service.back;

import cn.minishop.shop.vo.Admin;

public interface IAdminServiceBack {
    /**
     * 管理员的登入操作，调用管理员dao的findLogin()和doUpdateLastdate()方法
     * @param vo
     * @return
     * @throws Exception
     */
    public boolean login(Admin vo) throws Exception;
}
