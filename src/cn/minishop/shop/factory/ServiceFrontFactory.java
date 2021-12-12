package cn.minishop.shop.factory;

import cn.minishop.shop.service.front.ICommodityServiceFront;
import cn.minishop.shop.service.front.IMemberServiceFront;
import cn.minishop.shop.service.front.IOrdersServiceFront;
import cn.minishop.shop.service.front.IShopcarServiceFront;
import cn.minishop.shop.service.front.impl.CommodityServiceFrontImpl;
import cn.minishop.shop.service.front.impl.MemberServiceFrontImpl;
import cn.minishop.shop.service.front.impl.OrdersServiceFrontImpl;
import cn.minishop.shop.service.front.impl.ShopcarServiceFrontImpl;

public class ServiceFrontFactory {
    public static IMemberServiceFront getIMemberServiceFrontInstance() {
        return new MemberServiceFrontImpl();
    }
    public static ICommodityServiceFront getICommodityServiceFrontInstance(){
        return new CommodityServiceFrontImpl();
    }
    public static IShopcarServiceFront getIShopcarServiceFrontInstance() {
        return new ShopcarServiceFrontImpl();
    }
    public static IOrdersServiceFront getIOrdersServiceFrontInstance() {
        return new OrdersServiceFrontImpl();
    }
}
