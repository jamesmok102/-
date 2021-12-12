package cn.minishop.shop.dao;

import cn.minishop.shop.vo.Orders;

import java.sql.SQLException;
import java.util.List;

public interface IOrdersDAO extends IDAO<Integer, Orders>{
    /**
     * 调用Last_insert_id()取得当前增长后的订单编号for订单详情
     * @return 返回自动增长编号
     * @throws Exception
     */
    public Integer findLastInsertId() throws SQLException;
    public boolean doCreateOrders(Orders vo) throws SQLException;

    /**
     * 根据用户的编号，列出所有的订单信息
     * @param mid
     * @return
     * @throws Exception
     */
    public List<Orders> findAllByMember(String mid,Integer currentPage, Integer lineSize) throws Exception;
    public Integer getAllCountByMember(String mid) throws Exception;

    /**
     * 查询一个用户的一个订单信息
     * @param mid
     * @return
     * @throws Exception
     */
    public Orders findByIdAndMember(String mid, Integer oid) throws Exception;
}
