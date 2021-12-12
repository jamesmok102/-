package cn.minishop.shop.factory;

import cn.minishop.shop.dao.*;
import cn.minishop.shop.dao.impl.*;

import java.sql.Connection;

public class DAOFactory {
    public static IMemberDAO getMemberDAOInstance(Connection conn) {
        return new MemberDAOImpl(conn);
    }
    public static IAdminDAO getIAdminDAOInstance(Connection conn) {
        return new AdminDAOImpl(conn);
    }
    public static IGenreDAO getGenreDAOInstance(Connection conn) {
        return new GenreDAOImpl(conn);
    }
    public static ICommodityDAO getICommodityDAOInstance(Connection conn) {
        return new CommodityDAOImpl(conn);
    }
    public static IShopcarDAO getIShopcarDAOInstance(Connection conn) {
        return new ShopcarDAOImpl(conn);
    }
    public static IOrdersDAO getIOrdersDAOInstance(Connection conn) {
        return new OrdersDAOImpl(conn);
    }
    public static IDetailsDAO getIDetailsDAOInstance(Connection conn) {
        return new DetailsDAOImpl(conn);
    }
}
