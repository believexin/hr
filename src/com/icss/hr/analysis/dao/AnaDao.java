package com.icss.hr.analysis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.icss.hr.common.DbUtil;

/**
 * 数据分析
 * @author Administrator
 *
 */
public class AnaDao {
	
	public ArrayList<Map<String,Object>> queryEmpCount() throws SQLException{
		Connection conn = DbUtil.getConnection();
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("SELECT d.dept_name,COUNT(e.emp_id) ");
		sb.append("FROM emp e ");
		sb.append("right outer JOIN dept d ON e.emp_dept_id=d.dept_id ");
		sb.append("GROUP BY d.dept_name");
		
		PreparedStatement pstmt = conn.prepareStatement(sb.toString());
		
		ResultSet rs = pstmt.executeQuery();
		
		ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		while(rs.next()){
			
			HashMap<String, Object> map = new HashMap<>();
			map.put("deptName", rs.getString(1));
			map.put("empCount", rs.getInt(2));
			list.add(map);
		}
		rs.close();
		pstmt.close();
		
		return list;
	}
	
	public ArrayList<Map<String,Object>> queryEmpAvg() throws SQLException{
		
		Connection conn = DbUtil.getConnection();
		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT d.dept_name,AVG(e.emp_salary) ");
		sb.append("FROM emp e ");
		sb.append("LEFT OUTER JOIN dept d ON e.emp_dept_id=d.dept_id ");
		sb.append("GROUP BY d.dept_name");
		
		PreparedStatement pstmt = conn.prepareStatement(sb.toString());
		
		ResultSet rs = pstmt.executeQuery();
		
		ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		while(rs.next()){
			HashMap<String, Object> map = new HashMap<>();
			map.put("deptName", rs.getString(1));
			map.put("deptAvg", rs.getDouble(2));
			
			list.add(map);
		}
		
		
		rs.close();
		pstmt.close();
		
		return list;
		
	}
	
	public ArrayList<Map<String,Object>> queryDeptSalary() throws SQLException{
		
		Connection conn = DbUtil.getConnection();
		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT d.dept_name,MAX(e.emp_salary),MIN(e.emp_salary) ");
		sb.append("FROM emp e ");
		sb.append("LEFT OUTER JOIN dept d ON e.emp_dept_id=d.dept_id ");
		sb.append("GROUP BY d.dept_name");
		
		PreparedStatement pstmt = conn.prepareStatement(sb.toString());
		
		ResultSet rs = pstmt.executeQuery();
		
		ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		ArrayList<Double> salarys = null;
		
		while(rs.next()){
			salarys = new ArrayList<Double>();
			HashMap<String, Object> map = new HashMap<>();
			map.put("deptName", rs.getString(1));
			salarys.add(rs.getDouble(2));
			salarys.add(rs.getDouble(3));
			map.put("deptSalary", salarys);
			
			list.add(map);
		}
		rs.close();
		pstmt.close();		
		return list;
	}
	
	
	
}
