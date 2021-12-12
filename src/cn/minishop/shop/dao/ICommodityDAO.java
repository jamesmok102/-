package cn.minishop.shop.dao;

import cn.minishop.shop.vo.Commodity;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface ICommodityDAO extends IDAO<Integer, Commodity> {
    /**
     * 根据商品状态来返回商品列表
     * @param status 商品状态
     * @param currentPage
     * @param lineSize
     * @param column
     * @param keyWord
     * @return
     * @throws Exception
     */
    public List<Commodity> findAllByStatus(Integer status, Integer currentPage, Integer lineSize, String column, String keyWord) throws Exception;

    /**
     * 根据商品状态返回符合该状态的商品个数
     * @param status
     * @param column
     * @param keyWord
     * @return
     * @throws Exception
     */
    public Integer getAllCountByStatus(Integer status, String column, String keyWord) throws Exception;

    /**
     * 批量更新商品状态
     * @param id 商品id
     * @param status 商品状态
     * @return
     * @throws Exception
     */
    public boolean doUpdateStatus(Set<Integer> id, Integer status) throws Exception;

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    public Set<String> findAllByPhoto(Set<Integer> id) throws Exception;

    /**
     * 根据商品的分类与状态进行商品的列表显示
     * @param gid
     * @param status
     * @param currentPage
     * @param lineSize
     * @param column
     * @param keyWord
     * @return
     * @throws Exception
     */
    public List<Commodity> findAllByGenre(Integer gid, Integer status, Integer currentPage, Integer lineSize, String column, String keyWord) throws Exception;

    /**
     * 根据分类返回商品个数
     * @param gid
     * @param status
     * @param column
     * @param keyWord
     * @return
     * @throws Exception
     */
    public Integer getAllCountByGenre(Integer gid, Integer status, String column, String keyWord) throws Exception;

    /**
     * 根据多个商品id返回所有商品信息
     * @param ids
     * @return
     * @throws SQLException
     */
    public List<Commodity> findAllByCid(Set<Integer> ids) throws SQLException;

    /**
     * 变更商品库存量
     * @param cid
     * @param num
     * @return
     * @throws Exception
     */
    public boolean doUpdateByAmount(Integer cid, Integer num) throws SQLException;
}
