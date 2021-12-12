package cn.minishop.shop.service.front.impl;

import cn.minishop.shop.dbc.DatabaseConnection;
import cn.minishop.shop.factory.DAOFactory;
import cn.minishop.shop.service.front.ICommodityServiceFront;
import cn.minishop.shop.vo.Commodity;

import java.util.HashMap;
import java.util.Map;

public class CommodityServiceFrontImpl implements ICommodityServiceFront {
    private DatabaseConnection dbc = new DatabaseConnection();

    @Override
    public Map<String, Object> list(int currentPage, int lineSize, String column, String keyWord) throws Exception {
        try {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("allGenre", DAOFactory.getGenreDAOInstance(this.dbc.getConnection()).findAll());
            map.put("allCommodity", DAOFactory.getICommodityDAOInstance(this.dbc.getConnection()).findAllByStatus(1,currentPage,lineSize,column,keyWord));
            map.put("commodityCount", DAOFactory.getICommodityDAOInstance(this.dbc.getConnection()).getAllCountByStatus(1,column,keyWord));
            return map;
        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public Map<String, Object> listByGenre(int gid, int currentPage, int lineSize, String column, String keyWord) throws Exception {
        try {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("allGenre", DAOFactory.getGenreDAOInstance(this.dbc.getConnection()).findAll());
            map.put("allCommodity", DAOFactory.getICommodityDAOInstance(this.dbc.getConnection()).findAllByGenre(gid,1,currentPage,lineSize,column,keyWord));
            map.put("commodityCount", DAOFactory.getICommodityDAOInstance(this.dbc.getConnection()).getAllCountByGenre(gid,1,column,keyWord));
            return map;
        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public Commodity show(int cid) throws Exception {
        try {
            Commodity vo = DAOFactory.getICommodityDAOInstance(this.dbc.getConnection()).findById(cid);
            if (vo != null) {
                vo.setGenre(DAOFactory.getGenreDAOInstance(this.dbc.getConnection()).findById(vo.getGenre().getGid()));
            }
            return vo;
        }catch (Exception e){
            throw e;
        }finally {
            this.dbc.close();
        }
    }
}
