package cn.minishop.shop.service.back.impl;

import cn.minishop.shop.dbc.DatabaseConnection;
import cn.minishop.shop.factory.DAOFactory;
import cn.minishop.shop.service.back.IMemberServiceBack;
import sun.plugin.security.StripClassFile;

import java.util.HashMap;
import java.util.Map;

public class MemberServiceBackImpl implements IMemberServiceBack {
    private DatabaseConnection dbc = new DatabaseConnection();

    @Override
    public Map<String, Object> list(int currentPage, int lineSize, String column, String keyWord) throws Exception {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("allMembers", DAOFactory.getMemberDAOInstance(this.dbc.getConnection()).findAllSplit(currentPage, lineSize, column, keyWord));
            map.put("memberCount", DAOFactory.getMemberDAOInstance(this.dbc.getConnection()).getAllCount(column, keyWord));
            return map;
        } catch (Exception e) {
            throw e;
        } finally {
            this.dbc.close();
        }

    }
}
