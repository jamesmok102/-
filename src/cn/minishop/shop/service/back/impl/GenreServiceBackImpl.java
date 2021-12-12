package cn.minishop.shop.service.back.impl;

import cn.minishop.shop.dbc.DatabaseConnection;
import cn.minishop.shop.factory.DAOFactory;
import cn.minishop.shop.service.back.IGenreServiceBack;
import cn.minishop.shop.vo.Genre;
import sun.plugin.security.StripClassFile;

import java.util.List;
import java.util.Set;

public class GenreServiceBackImpl implements IGenreServiceBack {
    private DatabaseConnection dbc = new DatabaseConnection();

    @Override
    public boolean insert(Genre vo) throws Exception {
        try {
            return DAOFactory.getGenreDAOInstance(this.dbc.getConnection()).doCreate(vo);
        }catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }

    @Override
    public boolean update(Genre vo) throws Exception {
        try {
            return DAOFactory.getGenreDAOInstance(this.dbc.getConnection()).doUpdate(vo);
        }catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }

    @Override
    public boolean delete(Set<Integer> ids) throws Exception {
        try {
            return DAOFactory.getGenreDAOInstance(this.dbc.getConnection()).doRemoveBatch(ids);
        }catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }

    @Override
    public List<Genre> list() throws Exception {
        try {
            return DAOFactory.getGenreDAOInstance(this.dbc.getConnection()).findAll();
        }catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }
    }
}
