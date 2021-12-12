package cn.minishop.shop.service.front;

import cn.minishop.shop.exception.EmptyShopcarException;
import cn.minishop.shop.exception.UnCompleteMemberInformationException;
import cn.minishop.shop.exception.UnEnoughAmountException;
import cn.minishop.shop.vo.Orders;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IOrdersServiceFront {
    /**
     *
     * @param mid
     * @return 如果订单创建成功返回true,否则返回false
     * @throws UnCompleteMemberInformationException 个人信息不完整
     * @throws UnEnoughAmountException 没有足够的库存量时
     * @throws EmptyShopcarException 购物车没有添加任何商碞
     * @throws SQLException
     */
    public boolean insert(String mid) throws UnCompleteMemberInformationException, UnEnoughAmountException, EmptyShopcarException, SQLException;

    /**
     * 查看一个用户所有订单信息
     * @param mid
     * @return
     * @throws Exception
     */
    public Map<String,Object> listByMember(String mid, int currentPage, int lineSize) throws Exception;

    /**
     * 查看一个订单信息以及订单对应的所有详情信息
     * @param mid
     * @param oid
     * @return key = allOrders, value = findAllByMember()
     *         key = ordersCount, value = getAllCountByMember()
     * @throws Exception
     */
    public Orders show(String mid,int oid) throws Exception;
}
