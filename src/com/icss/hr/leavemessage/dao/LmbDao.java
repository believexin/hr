package com.icss.hr.leavemessage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.icss.hr.common.DbUtil;
import com.icss.hr.emp.dao.EmpDao;
import com.icss.hr.leavemessage.pojo.Lmb;

/**
 * 留言板数据访问层
 * @author Administrator
 *
 */
public class LmbDao {
	/**
	 * 插入留言记录
	 * @param lmb
	 * @throws SQLException
	 */
	public void insert(Lmb lmb) throws SQLException{
		
		Connection conn = DbUtil.getConnection();
		
		String sql ="insert into lmb values(lmb_seq.nextval,?,?,sysdate,?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, lmb.getLmbEmp().getEmpId());
		pstmt.setString(2, lmb.getLmbText());
		if(lmb.getCommentEmp() != null){
			pstmt.setInt(3, lmb.getCommentEmp().getEmpId());
		}else{
			pstmt.setInt(3,-1);
		}
		
		pstmt.executeUpdate();
		
		pstmt.close();		
	}
	/**
	 * 删除聊天记录
	 * @param lmbId
	 * @throws SQLException
	 */
	public void delete(int lmbId) throws SQLException{
		Connection conn = DbUtil.getConnection();
		
		String sql = "delete from lmb where lmb_id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, lmbId);
		pstmt.executeUpdate();
		pstmt.close();		
	}
	
	public ArrayList<Lmb> query() throws SQLException{
		
		Connection conn = DbUtil.getConnection();
		String sql = "select * from lmb order by lmb_date";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		
		ArrayList<Lmb> list = new ArrayList<>();
		EmpDao dao = new EmpDao();
		while(rs.next()){
			Lmb lmb = new Lmb(rs.getInt(1),dao.queryById(rs.getInt(2)),rs.getString(3),rs.getDate(4));
			if(rs.getInt(5) != -1){
				lmb.setCommentEmp(dao.queryById(rs.getInt(5)));
			}
			list.add(lmb);
		}
		
		return list;
		
		
	}
	
}
