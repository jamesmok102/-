package cn.minishop.shop.dao;

import cn.minishop.shop.vo.Shopcar;

import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

public interface IShopcarDAO extends IDAO<Integer, Shopcar>{
    /**
     * 若购物车上已有商品，再次增加商品时，修改商品数量即可
     * @param mid
     * @param cid
     * @return
     * @throws SQLException
     */
    public boolean doUpdateAmount(String mid, Integer cid) throws SQLException;

    /**
     * 根据用户id和商品id查询购物车信息
     * @param mid
     * @param cid
     * @return
     * @throws SQLException
     */
    public Shopcar findByMemberAndCommodity(String mid,Integer cid) throws SQLException;

    /**
     * 清除一个用户的所有的购物车信息
     * @param mid
     * @return
     * @throws SQLException
     */
    public boolean doRemoveByMember(String mid) throws SQLException;

    /**
     * 批量保存新的购物车信息
     * @param vos
     * @return
     * @throws SQLException
     */
    public boolean doCreateBatch(Set<Shopcar> vos) throws SQLException;

    /**
     * delete一个用户一种商品的购物车记录
     * @param mid
     * @param cid
     * @return
     * @throws SQLException
     */
    public boolean doRemoveByMemberAndCommodity(String mid,Set<Integer> cid) throws SQLException;

    /**
     * 一个用户购买的所有商品信息
     * @param mid
     * @return
     * @throws SQLException
     */
    public Map<Integer,Integer> findAllByMember(String mid) throws SQLException;
}
