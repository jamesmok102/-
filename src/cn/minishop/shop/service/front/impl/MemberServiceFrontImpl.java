package cn.minishop.shop.service.front.impl;

import cn.minishop.shop.dbc.DatabaseConnection;
import cn.minishop.shop.factory.DAOFactory;
import cn.minishop.shop.service.front.IMemberServiceFront;
import cn.minishop.shop.vo.Member;

public class MemberServiceFrontImpl implements IMemberServiceFront {
    private DatabaseConnection dbc = new DatabaseConnection();
    @Override
    public boolean regist(Member vo) throws Exception {
        try {
            if (DAOFactory.getMemberDAOInstance(this.dbc.getConnection()).findById(vo.getMid()) == null) {
                return DAOFactory.getMemberDAOInstance(this.dbc.getConnection()).doCreate(vo);
            }
            return false;
        } catch (Exception e){
            throw e;
        } finally {
            this.dbc.close();
        }
    }

    @Override
    public boolean login(Member vo) throws Exception {
        try {
            return DAOFactory.getMemberDAOInstance(this.dbc.getConnection()).findLogin(vo);
        } catch (Exception e){
            throw e;
        } finally {
            this.dbc.close();
        }
    }

    @Override
    public Member updatePre(String mid) throws Exception {
        try {
            return DAOFactory.getMemberDAOInstance(this.dbc.getConnection()).findById(mid);
        } catch (Exception e){
            throw e;
        } finally {
            this.dbc.close();
        }
    }

    @Override
    public boolean update(Member vo) throws Exception {
        try {
            return DAOFactory.getMemberDAOInstance(this.dbc.getConnection()).doUpdateMember(vo);
        } catch (Exception e){
            throw e;
        } finally {
            this.dbc.close();
        }
    }
}
