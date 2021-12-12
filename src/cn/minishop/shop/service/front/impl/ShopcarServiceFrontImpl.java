package cn.minishop.shop.service.front.impl;

import cn.minishop.shop.dbc.DatabaseConnection;
import cn.minishop.shop.factory.DAOFactory;
import cn.minishop.shop.service.front.IShopcarServiceFront;
import cn.minishop.shop.vo.Commodity;
import cn.minishop.shop.vo.Shopcar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ShopcarServiceFrontImpl implements IShopcarServiceFront {

    private DatabaseConnection dbc = new DatabaseConnection();

    @Override
    public boolean addCar(Shopcar vo) throws Exception {
        try{
           if (DAOFactory.getIShopcarDAOInstance(this.dbc.getConnection()).findByMemberAndCommodity(vo.getMember().getMid(),vo.getCommodity().getCid()) != null) {
               return DAOFactory.getIShopcarDAOInstance(this.dbc.getConnection()).doUpdateAmount(vo.getMember().getMid(),vo.getCommodity().getCid());
           } else {
               vo.setAmount(1);
               return DAOFactory.getIShopcarDAOInstance(this.dbc.getConnection()).doCreate(vo);
           }

        }catch(Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public Map<String,Object> listCar(String mid) throws Exception {
        Map<String,Object> map = new HashMap<String,Object>();
        try{
            Map<Integer,Integer> cars = DAOFactory.getIShopcarDAOInstance(this.dbc.getConnection()).findAllByMember(mid);
            map.put("allShopcars",cars);
            map.put("allCommodity",DAOFactory.getICommodityDAOInstance(this.dbc.getConnection()).findAllByCid(cars.keySet()));
            return map;
        }catch(Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public boolean deleteCar(String mid, Set<Integer> cid) throws Exception {
        try{
            return DAOFactory.getIShopcarDAOInstance(this.dbc.getConnection()).doRemoveByMemberAndCommodity(mid,cid);
        }catch(Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public boolean update(String mid, Set<Shopcar> vos) throws Exception {
        try{
            if (vos.size() == 0) {
                return false;
            }
            if (DAOFactory.getIShopcarDAOInstance(this.dbc.getConnection()).doRemoveByMember(mid)){
                return DAOFactory.getIShopcarDAOInstance(this.dbc.getConnection()).doCreateBatch(vos);
            }
            return false;
        }catch(Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }
    }
}
