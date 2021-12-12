package cn.minishop.shop.service.front.impl;

import cn.minishop.shop.dbc.DatabaseConnection;
import cn.minishop.shop.exception.EmptyShopcarException;
import cn.minishop.shop.exception.UnCompleteMemberInformationException;
import cn.minishop.shop.exception.UnEnoughAmountException;
import cn.minishop.shop.factory.DAOFactory;
import cn.minishop.shop.service.front.IOrdersServiceFront;
import cn.minishop.shop.vo.Commodity;
import cn.minishop.shop.vo.Details;
import cn.minishop.shop.vo.Member;
import cn.minishop.shop.vo.Orders;

import java.sql.SQLException;
import java.util.*;

public class OrdersServiceFrontImpl implements IOrdersServiceFront {
    private DatabaseConnection dbc = new DatabaseConnection();
    @Override
    public boolean insert(String mid) throws UnCompleteMemberInformationException, UnEnoughAmountException, EmptyShopcarException, SQLException {
        boolean flag = false;
        dbc.getConnection().setAutoCommit(false);
        try{
            Member member = DAOFactory.getMemberDAOInstance(this.dbc.getConnection()).findById2(mid);
            if (member.getName() == null || member.getPhone() == null || member.getAddress() == null) {
                throw new UnCompleteMemberInformationException("用户个人信不完整，无法进行订单创建");
            }
            Map<Integer,Integer> cars = DAOFactory.getIShopcarDAOInstance(this.dbc.getConnection()).findAllByMember(mid);
            if (cars.size() == 0) {
                throw new EmptyShopcarException("购物车暂无购买商品，无法进行订单创建");
            }
            List<Commodity> allCommodity = DAOFactory.getICommodityDAOInstance(this.dbc.getConnection()).findAllByCid(cars.keySet());
            Iterator<Commodity> iterCommodity = allCommodity.iterator();
            double pay = 0.0;
            while (iterCommodity.hasNext()) {
                Commodity vo = iterCommodity.next();
                if (vo.getAmount() - cars.get(vo.getCid())< 0) {
                    throw new UnEnoughAmountException("库存量不足，无法进行订单创建");
                }
                pay += vo.getPrice() * cars.get(vo.getCid());
            }
            Orders orders = new Orders();
            orders.setMember(member);
            orders.setName(member.getName());
            orders.setPhone(member.getPhone());
            orders.setAddress(member.getAddress());
            orders.setCreDate(new Date());
            orders.setPay(pay);
            flag = DAOFactory.getIOrdersDAOInstance(this.dbc.getConnection()).doCreateOrders(orders);
            Integer oid = DAOFactory.getIOrdersDAOInstance(this.dbc.getConnection()).findLastInsertId();
            orders.setOid(oid);
            iterCommodity = null;
            iterCommodity = allCommodity.iterator();
            List<Details> all = new ArrayList<Details>();

            while (iterCommodity.hasNext()) {
                Details vo = new Details();
                Commodity commodity = iterCommodity.next();
                vo.setCommodity(commodity);
                vo.setOrders(orders);
                int amount = cars.get(commodity.getCid());
                vo.setAmount(amount);
                vo.setTitle(commodity.getTitle());
                vo.setPrice(commodity.getPrice());
                all.add(vo);
                flag = DAOFactory.getICommodityDAOInstance(this.dbc.getConnection()).doUpdateByAmount(commodity.getCid(),0-amount);
            }
            flag =DAOFactory.getIDetailsDAOInstance(this.dbc.getConnection()).doCreateBath(all);
            flag = DAOFactory.getIShopcarDAOInstance(this.dbc.getConnection()).doRemoveByMember(mid);
            dbc.getConnection().commit();
        }catch (SQLException e){
            dbc.getConnection().rollback();
            throw e;
        }finally {
            this.dbc.close();
        }
        return flag;
    }

    @Override
    public Map<String,Object> listByMember(String mid, int currentPage, int lineSize) throws Exception {
        try {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("allOrders",DAOFactory.getIOrdersDAOInstance(this.dbc.getConnection()).findAllByMember(mid,currentPage,lineSize));
            map.put("ordersCount",DAOFactory.getIOrdersDAOInstance(this.dbc.getConnection()).getAllCountByMember(mid));
            return map;
        }catch (Exception e) {
            throw e;
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public Orders show(String mid, int oid) throws Exception {
        try {
            Orders vo = DAOFactory.getIOrdersDAOInstance(this.dbc.getConnection()).findByIdAndMember(mid,oid);
            if (vo != null){
                vo.setAllDetails(DAOFactory.getIDetailsDAOInstance(this.dbc.getConnection()).findAllByOrders(oid));
            }
            return vo;
        }catch (Exception e) {
            throw e;
        }finally {
            this.dbc.close();
        }
    }
}
