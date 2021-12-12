package cn.minishop.shop.dao;

import cn.minishop.shop.vo.Admin;

public interface IAdminDAO  extends IDAO<String, Admin>{
    /**
     * 管理员登入功能，传入一个管理员vo对象，查询数据库
     * @param vo 管理员vo对象
     * @return 成功登录返回true，否则返回false
     * @throws Exception
     */
    public boolean findLogin(Admin vo) throws Exception;

    /**
     * 更新最后一次登入的日期，传入一个管理员id
     * @param aid 管理员id
     * @return 更新成功返回true，否则返回false
     * @throws Exception
     */
    public boolean doUpdateLastdate(String aid) throws Exception;
}
