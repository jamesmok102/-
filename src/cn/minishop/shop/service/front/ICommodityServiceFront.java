package cn.minishop.shop.service.front;

import cn.minishop.shop.vo.Commodity;

import java.util.Map;

public interface ICommodityServiceFront {
    /**
     * 返回商品列表
     * @param currentPage
     * @param lineSize
     * @param column
     * @param keyWord
     * @return
     * @throws Exception
     */
    public Map<String,Object> list(int currentPage, int lineSize, String column, String keyWord) throws Exception;

    /**
     * 根据分类返回符合该分类的商品列表
     * @param gid
     * @param currentPage
     * @param lineSize
     * @param column
     * @param keyWord
     * @return key = allGenre, value = findAll()
     *         key = allCommodity, value = findAllByStatus()
     *         key = commodityCount, value = getAllCountByStatus()
     * @throws Exception
     */
    public Map<String,Object> listByGenre(int gid, int currentPage, int lineSize, String column, String keyWord) throws Exception;

    /**
     * 显示某一商品的详细商品信息
     * @param cid
     * @return
     * @throws Exception
     */
    public Commodity show(int cid) throws Exception;
}
