package cn.minishop.shop.factory;

import cn.minishop.shop.service.back.*;
import cn.minishop.shop.service.back.impl.*;

public class ServiceBackFactory {
    public static IAdminServiceBack getIAdminServiceBackInstance() {
        return new AdminServiceBackImpl();
    }
    public static IMemberServiceBack getIMemberServiceBackInstance() {
        return new MemberServiceBackImpl();
    }
    public static IGenreServiceBack getIGenreServiceBackInstance() {
        return new GenreServiceBackImpl();
    }
    public static ICommodityServiceBack getICommodityServiceBackInstance() {
        return new CommodityServiceBackImpl();
    }
    public static IOrdersServiceBack getIOrdersServiceBackInstance() {
        return new OrdersServiceBackImpl();
    }
}
