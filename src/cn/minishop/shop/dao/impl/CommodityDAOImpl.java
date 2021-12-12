package cn.minishop.shop.dao.impl;

import cn.minishop.shop.dao.ICommodityDAO;
import cn.minishop.shop.vo.Admin;
import cn.minishop.shop.vo.Commodity;
import cn.minishop.shop.vo.Genre;
import cn.minishop.util.dao.AbstractDAOImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class CommodityDAOImpl extends AbstractDAOImpl implements ICommodityDAO {

    public CommodityDAOImpl(Connection conn) {
        super(conn);
    }

    @Override
    public boolean doCreate(Commodity vo) throws Exception {
        String sql = "INSERT INTO commodity(gid,aid,title,pubDate,price,amount,note,photo,status) VALUES (?,?,?,?,?,?,?,?,?)";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setInt(1, vo.getGenre().getGid());
        super.pstmt.setString(2, vo.getAdmin().getAid());
        super.pstmt.setString(3, vo.getTitle());
        super.pstmt.setTimestamp(4, new java.sql.Timestamp(vo.getPubDate().getTime()));
        super.pstmt.setDouble(5, vo.getPrice());
        super.pstmt.setInt(6, vo.getAmount());
        super.pstmt.setString(7, vo.getNote());
        super.pstmt.setString(8, vo.getPhoto());
        super.pstmt.setInt(9, vo.getStatus());
        return super.pstmt.executeUpdate() > 0;
    }

    @Override
    public boolean doUpdate(Commodity vo) throws Exception {
        String sql = "UPDATE commodity SET gid=?,title=?,price=?,amount=?,note=?,photo=?,status=? WHERE cid=?" ;
        super.pstmt = super.conn.prepareStatement(sql) ;
        super.pstmt.setInt(1,vo.getGenre().getGid());
        super.pstmt.setString(2,vo.getTitle());
        super.pstmt.setDouble(3,vo.getPrice());
        super.pstmt.setInt(4,vo.getAmount());
        super.pstmt.setString(5,vo.getNote());
        super.pstmt.setString(6,vo.getPhoto());
        super.pstmt.setInt(7,vo.getStatus());
        super.pstmt.setInt(8,vo.getCid());
        return super.pstmt.executeUpdate() > 0 ;
    }

    @Override
    public boolean doRemoveBatch(Set<Integer> ids) throws Exception {
        return super.removeHandle("commodity","cid",ids);
    }

    @Override
    public Commodity findById(Integer id) throws Exception {
        Commodity vo = new Commodity();
        String sql = "SELECT cid,gid,aid,title,pubDate,price,amount,note,photo,status FROM commodity WHERE cid=?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setInt(1,id);
        ResultSet rs = super.pstmt.executeQuery();
        if(rs.next()){
            vo.setCid(rs.getInt(1));
            Genre genre = new Genre();
            genre.setGid(rs.getInt(2));
            vo.setGenre(genre);
            Admin admin = new Admin();
            admin.setAid(rs.getString(3));
            vo.setAdmin(admin);
            vo.setTitle(rs.getString(4));
            vo.setPubDate(rs.getTimestamp(5));
            vo.setPrice(rs.getDouble(6));
            vo.setAmount(rs.getInt(7));
            vo.setNote(rs.getString(8));
            vo.setPhoto(rs.getString(9));
            vo.setStatus(rs.getInt(10));
        }
        return vo;
    }

    @Override
    public List<Commodity> findAll() throws Exception {
        return null;
    }

    @Override
    public List<Commodity> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord) throws Exception {
        List<Commodity> all = new ArrayList<Commodity>();
        String sql = "SELECT cid,gid,aid,title,pubDate,price,amount,note,photo,status FROM commodity WHERE " + column + " LIKE ? AND status<>2 LIMIT ? , ?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, "%"+keyWord+"%");
        super.pstmt.setInt(2, (currentPage - 1) * lineSize);
        super.pstmt.setInt(3, lineSize);
        ResultSet rs = super.pstmt.executeQuery();
        while (rs.next()) {
            Commodity vo = new Commodity();
            vo.setCid(rs.getInt(1));
            Genre genre = new Genre();
            genre.setGid(rs.getInt(2));
            vo.setGenre(genre);
            Admin admin = new Admin();
            admin.setAid(rs.getString(3));
            vo.setAdmin(admin);
            vo.setTitle(rs.getString(4));
            vo.setPubDate(rs.getTimestamp(5));
            vo.setPrice(rs.getDouble(6));
            vo.setAmount(rs.getInt(7));
            vo.setNote(rs.getString(8));
            vo.setPhoto(rs.getString(9));
            vo.setStatus(rs.getInt(10));
            all.add(vo);
        }
        return all;
    }

    @Override
    public Integer getAllCount(String column, String keyWord) throws Exception {
        String sql = "SELECT COUNT(*) FROM commodity WHERE " + column + " LIKE ? AND status<>2";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, "%"+keyWord+"%");
        ResultSet rs = super.pstmt.executeQuery();
        if(rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    @Override
    public List<Commodity> findAllByStatus(Integer status, Integer currentPage, Integer lineSize, String column, String keyWord) throws Exception {
        List<Commodity> all = new ArrayList<Commodity>();
        String sql = "SELECT cid,gid,aid,title,pubDate,price,amount,note,photo,status FROM commodity WHERE " + column + " LIKE ? AND status=? LIMIT ? , ?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, "%"+keyWord+"%");
        super.pstmt.setInt(2, status);
        super.pstmt.setInt(3, (currentPage - 1) * lineSize);
        super.pstmt.setInt(4, lineSize);
        ResultSet rs = super.pstmt.executeQuery();
        while (rs.next()) {
            Commodity vo = new Commodity();
            vo.setCid(rs.getInt(1));
            Genre genre = new Genre();
            genre.setGid(rs.getInt(2));
            vo.setGenre(genre);
            Admin admin = new Admin();
            admin.setAid(rs.getString(3));
            vo.setAdmin(admin);
            vo.setTitle(rs.getString(4));
            vo.setPubDate(rs.getTimestamp(5));
            vo.setPrice(rs.getDouble(6));
            vo.setAmount(rs.getInt(7));
            vo.setNote(rs.getString(8));
            vo.setPhoto(rs.getString(9));
            vo.setStatus(rs.getInt(10));
            all.add(vo);
        }
        return all;
    }

    @Override
    public Integer getAllCountByStatus(Integer status, String column, String keyWord) throws Exception {
        String sql = "SELECT COUNT(*) FROM commodity WHERE " + column + " LIKE ? AND status=?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, "%"+keyWord+"%");
        super.pstmt.setInt(2, status);
        ResultSet rs = super.pstmt.executeQuery();
        if(rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    @Override
    public boolean doUpdateStatus(Set<Integer> id, Integer status) throws Exception {
        String sql = "UPDATE commodity SET status=? WHERE cid=?";
        Iterator<Integer> iter = id.iterator();
        super.pstmt = super.conn.prepareStatement(sql);
        while(iter.hasNext()){
            super.pstmt.setInt(1, status);
            super.pstmt.setInt(2, iter.next());
            super.pstmt.addBatch();
        }
        boolean flag = true;
        int result [] = super.pstmt.executeBatch();
        for(int x=0; x<result.length; x++) {
            if (result[x]==0) {
                flag = false;
            }
        }
        return true;
    }

    @Override
    public Set<String> findAllByPhoto(Set<Integer> id) throws Exception {
        if (id.size() > 0) {
            return super.photoHandle("commodity","photo","cid",id);
        }
        return null;
    }

    @Override
    public List<Commodity> findAllByGenre(Integer gid, Integer status, Integer currentPage, Integer lineSize, String column, String keyWord) throws Exception {
        List<Commodity> all = new ArrayList<Commodity>();
        String sql = "SELECT cid,gid,aid,title,pubDate,price,amount,note,photo,status FROM commodity WHERE " + column + " LIKE ? AND status=? AND gid=? LIMIT ? , ?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, "%"+keyWord+"%");
        super.pstmt.setInt(2, status);
        super.pstmt.setInt(3, gid);
        super.pstmt.setInt(4, (currentPage - 1) * lineSize);
        super.pstmt.setInt(5, lineSize);
        ResultSet rs = super.pstmt.executeQuery();
        while (rs.next()) {
            Commodity vo = new Commodity();
            vo.setCid(rs.getInt(1));
            Genre genre = new Genre();
            genre.setGid(rs.getInt(2));
            vo.setGenre(genre);
            Admin admin = new Admin();
            admin.setAid(rs.getString(3));
            vo.setAdmin(admin);
            vo.setTitle(rs.getString(4));
            vo.setPubDate(rs.getTimestamp(5));
            vo.setPrice(rs.getDouble(6));
            vo.setAmount(rs.getInt(7));
            vo.setNote(rs.getString(8));
            vo.setPhoto(rs.getString(9));
            vo.setStatus(rs.getInt(10));
            all.add(vo);
        }
        return all;
    }

    @Override
    public Integer getAllCountByGenre(Integer gid, Integer status, String column, String keyWord) throws Exception {
        String sql = "SELECT COUNT(*) FROM commodity WHERE " + column + " LIKE ? AND status=? AND gid=?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, "%"+keyWord+"%");
        super.pstmt.setInt(2, status);
        super.pstmt.setInt(3, gid);
        ResultSet rs = super.pstmt.executeQuery();
        if(rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    @Override
    public List<Commodity> findAllByCid(Set<Integer> ids) throws SQLException {
        List<Commodity> all = new ArrayList<Commodity>();
        if (ids.size() == 0){
            return null;
        }
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT cid,gid,aid,title,pubDate,price,amount,note,photo,status FROM commodity WHERE status=1 AND cid IN (");
        Iterator<Integer> iter = ids.iterator();
        while(iter.hasNext()) {
            sql.append(iter.next()).append(",");
        }
        sql.delete(sql.length()-1,sql.length()).append(")");
        super.pstmt = super.conn.prepareStatement(sql.toString());
        System.out.println(sql.toString());
        ResultSet rs = super.pstmt.executeQuery();
        while (rs.next()) {
            Commodity vo = new Commodity();
            vo.setCid(rs.getInt(1));
            Genre genre = new Genre();
            genre.setGid(rs.getInt(2));
            vo.setGenre(genre);
            Admin admin = new Admin();
            admin.setAid(rs.getString(3));
            vo.setAdmin(admin);
            vo.setTitle(rs.getString(4));
            vo.setPubDate(rs.getTimestamp(5));
            vo.setPrice(rs.getDouble(6));
            vo.setAmount(rs.getInt(7));
            vo.setNote(rs.getString(8));
            vo.setPhoto(rs.getString(9));
            vo.setStatus(rs.getInt(10));
            all.add(vo);
        }
        return all;
    }

    @Override
    public boolean doUpdateByAmount(Integer cid, Integer num) throws SQLException {
        String sql = "UPDATE commodity SET amount=amount+" + num + " WHERE cid=?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setInt(1,cid);
        return super.pstmt.executeUpdate() > 0;
    }
}
