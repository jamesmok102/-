package cn.minishop.shop.dao.impl;

import cn.minishop.shop.dao.IGenreDAO;
import cn.minishop.shop.vo.Genre;
import cn.minishop.util.dao.AbstractDAOImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GenreDAOImpl extends AbstractDAOImpl implements IGenreDAO {

    public GenreDAOImpl(Connection conn) {
        super(conn);
    }

    @Override
    public boolean doCreate(Genre vo) throws Exception {
        String sql = "INSERT INTO genre(title) VALUES (?)";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, vo.getTitle());
        return super.pstmt.executeUpdate() > 0;
    }

    @Override
    public boolean doUpdate(Genre vo) throws Exception {
        String sql = "UPDATE genre SET title=? WHERE gid=?" ;
        super.pstmt = super.conn.prepareStatement(sql) ;
        super.pstmt.setString(1,vo.getTitle());
        super.pstmt.setInt(2,vo.getGid());
        return super.pstmt.executeUpdate() > 0 ;
    }

    @Override
    public boolean doRemoveBatch(Set<Integer> ids) throws Exception {
        return super.removeHandle("genre","gid",ids);
    }

    @Override
    public Genre findById(Integer id) throws Exception {
        Genre vo = null;
        String sql = "SELECT gid, title FROM genre WHERE gid=?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setInt(1, id);
        ResultSet rs = super.pstmt.executeQuery();
        if (rs.next()) {
            vo = new Genre();
            vo.setGid(rs.getInt(1));
            vo.setTitle(rs.getString(2));
        }
        return vo;
    }

    @Override
    public List<Genre> findAll() throws Exception {
        List<Genre> all = new ArrayList<Genre>() ;
        String sql = "SELECT gid,title FROM genre" ;
        super.pstmt = super.conn.prepareStatement(sql) ;
        ResultSet rs = super.pstmt.executeQuery() ;
        while(rs.next()) {
            Genre vo = new Genre() ;
            vo.setGid(rs.getInt(1));
            vo.setTitle(rs.getString(2));
            all.add(vo);
        }
        return all;
    }

    @Override
    public List<Genre> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord) throws Exception {
        return null;
    }

    @Override
    public Integer getAllCount(String column, String keyWord) throws Exception {
        return null;
    }
}
