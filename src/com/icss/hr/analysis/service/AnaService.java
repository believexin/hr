package com.icss.hr.analysis.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import com.icss.hr.analysis.dao.AnaDao;

/**
 * 数据分析业务逻辑
 * @author Administrator
 *
 */
public class AnaService {
	
	private AnaDao dao = new AnaDao();
	/**
	 * 查询部门员工人数
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Map<String, Object>> queryEmpCount() throws SQLException{
		
		return dao.queryEmpCount();
	}
	
	
	/**
	 * 查询每个部门的平均工资
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Map<String, Object>> queryEmpAvg() throws SQLException{
		return dao.queryEmpAvg();
	}
	
	/**
	 * 查询每个部门的最大、最小工资
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Map<String, Object>> queryEmpSalary() throws SQLException{
		return dao.queryDeptSalary();
	}
	
	
}
