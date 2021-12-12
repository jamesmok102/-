package cn.minishop.shop.service.back.impl;

import cn.minishop.shop.dbc.DatabaseConnection;
import cn.minishop.shop.factory.DAOFactory;
import cn.minishop.shop.service.back.ICommodityServiceBack;
import cn.minishop.shop.vo.Commodity;
import sun.plugin.security.StripClassFile;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CommodityServiceBackImpl implements ICommodityServiceBack {

    private DatabaseConnection dbc = new DatabaseConnection();

    @Override
    public Map<String, Object> insertPre() throws Exception {
        try{
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("allGenre", DAOFactory.getGenreDAOInstance(this.dbc.getConnection()).findAll());
            return map;
        }catch (Exception e) {
            throw e;
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public boolean insert(Commodity vo) throws Exception {
        try{

            return DAOFactory.getICommodityDAOInstance(this.dbc.getConnection()).doCreate(vo);
        }catch (Exception e) {
            throw e;
        }finally {
            this.dbc.close();
        }

    }

    @Override
    public Map<String, Object> list(int currentPage, int lineSize, String column, String keyword) throws Exception {
        try{
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("allCommodity",DAOFactory.getICommodityDAOInstance(this.dbc.getConnection()).findAllSplit(currentPage,lineSize,column,keyword));
            map.put("commodityCount",DAOFactory.getICommodityDAOInstance(this.dbc.getConnection()).getAllCount(column,keyword));
            return map;
        }catch(Exception e){
            throw e;
        }finally{
            this.dbc.close();
        }

    }

    @Override
    public Map<String, Object> listStatus(int status, int currentPage, int lineSize, String column, String keyword) throws Exception {
        try{
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("allCommodity",DAOFactory.getICommodityDAOInstance(this.dbc.getConnection()).findAllByStatus(status,currentPage,lineSize,column,keyword));
            map.put("commodityCount",DAOFactory.getICommodityDAOInstance(this.dbc.getConnection()).getAllCountByStatus(status,column,keyword));
            return map;
        }catch(Exception e){
            throw e;
        }finally{
            this.dbc.close();
        }
    }

    @Override
    public boolean updateUp(Set<Integer> cid) throws Exception {
        try {
            return DAOFactory.getICommodityDAOInstance(this.dbc.getConnection()).doUpdateStatus(cid,1);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }

    @Override
    public boolean updateDown(Set<Integer> cid) throws Exception {
        try {
            return DAOFactory.getICommodityDAOInstance(this.dbc.getConnection()).doUpdateStatus(cid,0);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }

    @Override
    public boolean updateDelete(Set<Integer> cid) throws Exception {
        try {
            return DAOFactory.getICommodityDAOInstance(this.dbc.getConnection()).doUpdateStatus(cid,2);
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }

    @Override
    public Map<String, Object> updatePre(int cid) throws Exception {
        try{
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("allGenre", DAOFactory.getGenreDAOInstance(this.dbc.getConnection()).findAll());
            map.put("commodity", DAOFactory.getICommodityDAOInstance(this.dbc.getConnection()).findById(cid));
            return map;
        }catch (Exception e) {
            throw e;
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public boolean update(Commodity vo) throws Exception {
        try{
            return DAOFactory.getICommodityDAOInstance(this.dbc.getConnection()).doUpdate(vo);
        }catch (Exception e) {
            throw e;
        }finally {
            this.dbc.close();
        }

    }

    @Override
    public Map<String, Object> deleteAll(Set<Integer> ids) throws Exception {
        try{
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("allPhotos",DAOFactory.getICommodityDAOInstance(this.dbc.getConnection()).findAllByPhoto(ids));
            map.put("flag",DAOFactory.getICommodityDAOInstance(this.dbc.getConnection()).doRemoveBatch(ids));
            return map;
        }catch (Exception e) {
            throw e;
        }finally {
            this.dbc.close();
        }
    }

    @Override
    public boolean delete(Set<Integer> id) throws Exception {
        try{
            return DAOFactory.getICommodityDAOInstance(this.dbc.getConnection()).doRemoveBatch(id);
        }catch (Exception e) {
            throw e;
        }finally {
            this.dbc.close();
        }
    }


}
