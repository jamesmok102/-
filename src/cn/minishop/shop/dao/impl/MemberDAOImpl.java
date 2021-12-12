package cn.minishop.shop.dao.impl;

import cn.minishop.shop.dao.IMemberDAO;
import cn.minishop.shop.vo.Member;
import cn.minishop.util.dao.AbstractDAOImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MemberDAOImpl extends AbstractDAOImpl implements IMemberDAO {
    public MemberDAOImpl(Connection conn) {
        super(conn);
    }
    @Override
    public boolean doCreate(Member vo) throws Exception {
        String sql = "INSERT INTO member(mid,password,regDate) VALUES (?,?,?)";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, vo.getMid());
        super.pstmt.setString(2, vo.getPassword());
        super.pstmt.setTimestamp(3, new Timestamp(vo.getRegDate().getTime()));
        return super.pstmt.executeUpdate() > 0;
    }

    @Override
    public boolean doUpdate(Member vo) throws Exception {
        return false;
    }

    @Override
    public boolean doRemoveBatch(Set<String> ids) throws Exception {
        return false;
    }

    @Override
    public Member findById(String id) throws Exception {
        Member vo = null;
        String sql = "SELECT mid,password,name,phone,address,regDate FROM member WHERE mid=?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, id);
        ResultSet rs = super.pstmt.executeQuery();
        if(rs.next()) {
            vo = new Member();
            vo.setMid(rs.getString(1));
            vo.setPassword(rs.getString(2));
            vo.setName(rs.getString(3));
            vo.setPhone(rs.getString(4));
            vo.setAddress(rs.getString(5));
            vo.setRegDate(rs.getTimestamp(6));
        }
        return vo;
    }

    @Override
    public List<Member> findAll() throws Exception {
        return null;
    }

    @Override
    public List<Member> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord) throws Exception {
        List<Member> all = new ArrayList<Member>();
        String sql = "SELECT mid,password,name,phone,address,regdate FROM member WHERE " + column + " LIKE ? LIMIT ? ,? ";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, "%" + keyWord + "%");
        super.pstmt.setInt(2, (currentPage - 1) * lineSize);
        super.pstmt.setInt(3, lineSize);
        ResultSet rs = super.pstmt.executeQuery();
        while (rs.next()) {
            Member vo = new Member();
            vo.setMid(rs.getString(1));
            vo.setPassword(rs.getString(2));
            vo.setName(rs.getString(3));
            vo.setPhone(rs.getString(4));
            vo.setAddress(rs.getString(5));
            vo.setRegDate(rs.getTimestamp(6));
            all.add(vo);
        }
        return all;
    }

    @Override
    public Integer getAllCount(String column, String keyWord) throws Exception {
        return super.countHandle("member", column, keyWord);
    }

    @Override
    public Member findById2(String mid) throws SQLException {
        Member vo = null;
        String sql = "SELECT mid,password,name,phone,address,regDate FROM member WHERE mid=?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, mid);
        ResultSet rs = super.pstmt.executeQuery();
        if(rs.next()) {
            vo = new Member();
            vo.setMid(rs.getString(1));
            vo.setPassword(rs.getString(2));
            vo.setName(rs.getString(3));
            vo.setPhone(rs.getString(4));
            vo.setAddress(rs.getString(5));
            vo.setRegDate(rs.getTimestamp(6));
        }
        return vo;
    }

    @Override
    public boolean findLogin(Member vo) throws Exception {
        boolean flag = false;
        String sql = "SELECT mid FROM member WHERE mid=? AND password=?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1, vo.getMid());
        super.pstmt.setString(2, vo.getPassword());
        ResultSet rs = super.pstmt.executeQuery();
        if(rs.next()) {
            flag = true;
            vo.setMid(rs.getString(1));
        }
        return flag;
    }

    @Override
    public boolean doUpdateMember(Member vo) throws Exception {
        String sql = "UPDATE member SET name=?,phone=?,address=? WHERE mid=?";
        super.pstmt = super.conn.prepareStatement(sql);
        super.pstmt.setString(1,vo.getName());
        super.pstmt.setString(2, vo.getPhone());
        super.pstmt.setString(3,vo.getAddress());
        super.pstmt.setString(4, vo.getMid());
        return super.pstmt.executeUpdate() > 0;
    }
}
