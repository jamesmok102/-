package cn.minishop.shop.dao;

import cn.minishop.shop.vo.Member;

import java.sql.SQLException;

public interface IMemberDAO extends IDAO<String, Member>{
    public Member findById2(String mid) throws SQLException;
    /**
     * 用户的登录检查操作，登录正确后返回用户名
     * @param vo 传参
     * @return 登录成功返回true,否则返回false
     * @throws Exception
     */
    public boolean findLogin(Member vo) throws Exception;

    /**
     * 更新用户信息
     * @param vo
     * @return
     * @throws Exception
     */
    public boolean doUpdateMember(Member vo) throws Exception;
}
