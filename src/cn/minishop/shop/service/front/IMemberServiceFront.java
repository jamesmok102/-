package cn.minishop.shop.service.front;

import cn.minishop.shop.vo.Member;

public interface IMemberServiceFront {
    /**
     * 实现用户注册操作
     *
     * @param vo
     * @return 注册成功返回true,否则返回false
     * @throws Exception
     */
    public boolean regist(Member vo) throws Exception;

    /**
     *  用户登录操作
     * @param vo
     * @return 登录成功返回true,否则返回false
     * @throws Exception
     */
    public boolean login(Member vo) throws Exception;

    /**
     * 读取用户信息到用户信息页面中
     * @param mid
     * @return
     * @throws Exception
     */
    public Member updatePre(String mid) throws Exception;

    /**
     * 用户信息更新操作
     * @param vo
     * @return
     * @throws Exception
     */
    public boolean update(Member vo) throws Exception;

}
