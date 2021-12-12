package cn.minishop.shop.service.back;

import cn.minishop.shop.vo.Orders;

import java.util.Map;

public interface IOrdersServiceBack {
    /**
     * 分类显示订单列表
     * @param currentPage
     * @param lineSize
     * @param column
     * @param keyWord
     * @return key = allOrders, value = findAllSplit()
     *         key = ordersCount, value = getAllCount()
     * @throws Exception
     */
    public Map<String,Object> list(int currentPage, int lineSize, String column, String keyWord) throws Exception;
    public Orders show(int oid) throws Exception;
}
