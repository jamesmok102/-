package cn.minishop.shop.service.front;

import cn.minishop.shop.vo.Commodity;
import cn.minishop.shop.vo.Shopcar;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IShopcarServiceFront {
    /**
     * 增加商品到购物车
     * @param vo
     * @return
     * @throws Exception
     */
    public boolean addCar(Shopcar vo) throws Exception;

    /**
     * 根据当前用户列出该用户的购物车
     * @param mid
     * @return key = allShopcars, value = cars
     *         key = allCommodity, value = findAllByCid
     * @throws Exception
     */
    public Map<String,Object> listCar(String mid) throws Exception;

    /**
     * 删除该用户购物车中的某一商品
     * @param mid
     * @param cid
     * @return
     * @throws Exception
     */
    public boolean deleteCar(String mid, Set<Integer> cid) throws Exception;

    /**
     * 更新购物车，要先删除购物车再保存新的购物车
     * @param mid
     * @param vos
     * @return
     * @throws Exception
     */
    public boolean update(String mid,Set<Shopcar> vos) throws Exception;
}
