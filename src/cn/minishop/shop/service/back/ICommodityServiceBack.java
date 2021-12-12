package cn.minishop.shop.service.back;

import cn.minishop.shop.vo.Commodity;

import java.util.Map;
import java.util.Set;

public interface ICommodityServiceBack {
    /**
     * 商品增加前的数据查询操作，要查询出所有的栏目信息
     * @return key = allGenre, value = findAll()
     * @throws Exception
     */
    public Map<String, Object> insertPre() throws Exception;

    /**
     * 商品增加操作
     * @param vo
     * @return
     * @throws Exception
     */
    public boolean insert(Commodity vo) throws Exception;

    /**
     * 商品信息的基本列表
     * key = allCommodity, value = ICommodityDAO.findAll()
     * key = CommodityCount, value = ICommodityDAO.getAllCount()
     * @param currentPage
     * @param lineSize
     * @param column
     * @param keyword
     * @return key = allCommodity, value = findAllSplit()
     *         key = commodityCount, value = getAllCount()
     * @throws Exception
     */
    public Map<String,Object> list(int currentPage, int lineSize, String column, String keyword) throws Exception;

    /**
     * 根据商品状态返回符合该状态的商品列表
     * @param status
     * @param currentPage
     * @param lineSize
     * @param column
     * @param keyword
     * @return key = allCommodity, value = findAllByStatus()
     *         key = commodityCount, value = getAllCountByStatus()
     * @throws Exception
     */
    public Map<String,Object> listStatus(int status, int currentPage, int lineSize, String column, String keyword) throws Exception;

    /**
     * 商品上架
     * @param cid
     * @return
     * @throws Exception
     */
    public boolean updateUp(Set<Integer> cid) throws Exception;

    /**
     * 商品下架
     * @param cid
     * @return
     * @throws Exception
     */
    public boolean updateDown(Set<Integer> cid) throws Exception;

    /**
     * 把商品移到回收站
     * @param cid
     * @return
     * @throws Exception
     */
    public boolean updateDelete(Set<Integer> cid) throws Exception;

    /**
     * 商品修改前的数据查询操作
     * key = allCommodity, value=ICommodityDAO.findAll()
     * key = commodity, value=ICommodity.findById()
     * @param cid
     * @return
     * @throws Exception
     */
    public Map<String,Object> updatePre(int cid) throws Exception;
    public boolean update(Commodity vo) throws Exception;

    /**
     * 执行数据的批量删除操作，但是在删除之后要清除对应的商品图片信息
     * @param ids
     * @return
     * key = flag, value = doRemoveBatch()
     * key = allPhotos, value = findALLByPhoto()
     * @throws Exception
     */
    public Map<String,Object> deleteAll(Set<Integer> ids) throws Exception;

    /**
     * 根据编号删除商品信息
     * @param id
     * @return
     * @throws Exception
     */
    public boolean delete(Set<Integer> id) throws Exception;
}
