package com.icss.hr.emp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.icss.hr.common.DbUtil;
import com.icss.hr.common.Pager;
import com.icss.hr.dept.pojo.Dept;
import com.icss.hr.emp.pojo.Emp;
import com.icss.hr.job.pojo.Job;

/**
 * 员工数据访问对象
 * @author Administrator
 *
 */
public class EmpDao {
	
	public void insert(Emp emp) throws SQLException{
		
		Connection conn = DbUtil.getConnection();
		
		String sql = "insert into emp values(emp_seq.nextval,?,?,?,?,?,?,?,?,default,?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, emp.getEmpName());
		pstmt.setString(2, emp.getEmpLoginName());
		pstmt.setString(3, emp.getEmpPwd());
		pstmt.setString(4, emp.getEmpEmail());
		pstmt.setString(5,emp.getPhone());
		pstmt.setDouble(6, emp.getEmpSalary());
		pstmt.setInt(7, emp.getDept().getDeptId());
		pstmt.setInt(8, emp.getJob().getJobId());
		pstmt.setString(9, emp.getEmpInfo());
		
		pstmt.executeUpdate();
		pstmt.close();
		
	}
	
	public Emp queryByLoginName(String empLoginName) throws SQLException{
		
		Connection conn = DbUtil.getConnection();
		
		String sql = "select emp_pwd,emp_name,emp_id from emp where emp_login_name=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, empLoginName);
		
		Emp emp = null;
		
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()){
			emp = new Emp();
			emp.setEmpPwd(rs.getString(1));
			emp.setEmpName(rs.getString(2));
			emp.setEmpId(rs.getInt(3));
		}
		rs.close();
		pstmt.close();
		
		return emp;
		
	}
	
	public int getCount() throws SQLException{
		Connection conn = DbUtil.getConnection();
		
		String sql = "select count(*) from emp";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		 rs.next();
		 int result = rs.getInt(1);
		 
		 rs.close();
		pstmt.close();
		 
		 return result;
		
	}
	
	public ArrayList<Emp> queryByPge(Pager pager) throws SQLException{
		
		Connection conn = DbUtil.getConnection();
		
		StringBuffer sb = new StringBuffer();		
		sb.append("SELECT * FROM (SELECT ROWNUM rnum,e.emp_id,e.emp_name,e.emp_email,e.emp_phone,d.dept_name,j.job_name");
		sb.append(" FROM emp e ");
		sb.append("LEFT OUTER JOIN dept d ON e.emp_dept_id = d.dept_id ");
		sb.append("LEFT OUTER JOIN job j ON e.emp_job_id = j.job_id) ");
		sb.append(" WHERE rnum BETWEEN ? AND ?");
		PreparedStatement pstmt = conn.prepareStatement(sb.toString());
		
		pstmt.setInt(1, pager.getStart());
		pstmt.setInt(2, pager.getEnd());
		
		ResultSet rs = pstmt.executeQuery();
		
		ArrayList<Emp> list = new ArrayList<>();
		
		while(rs.next()){
			
			Dept dept = new Dept();
			dept.setDeptName(rs.getString(6));
			Job job = new Job();
			job.setJobName(rs.getString(7));
			
			Emp emp = new Emp();
			emp.setEmpId(rs.getInt(2));
			emp.setEmpName(rs.getString(3));
			emp.setEmpEmail(rs.getString(4));
			emp.setPhone(rs.getString(5));
			emp.setDept(dept);
			emp.setJob(job);
			
			list.add(emp);
			
		}
		
		rs.close();
		pstmt.close();
		
		return list;		
	}
	//orm思想
	public Emp queryById(int empId) throws SQLException{
		Connection conn = DbUtil.getConnection();
		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT e.*,d.dept_name,j.job_name ");
		sb.append("FROM emp e ");
		sb.append("LEFT OUTER JOIN dept d ON e.emp_dept_id=d.dept_id ");
		sb.append("LEFT OUTER JOIN job j ON e.emp_job_id=j.job_id ");
		sb.append("WHERE emp_id=?");
		PreparedStatement pstmt = conn.prepareStatement(sb.toString());
		
		pstmt.setInt(1, empId);
		
		ResultSet rs = pstmt.executeQuery();
		
		Emp emp = null;
		
		if(rs.next()){
			Dept dept = new Dept();
			dept.setDeptId(rs.getInt(8));
			dept.setDeptName(rs.getString("DEPT_NAME"));
			
			Job job = new Job();
			job.setJobId(rs.getInt(9));
			job.setJobName(rs.getString("JOB_NAME"));
			
			emp = new Emp(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getDouble(7),dept,job,rs.getString(10),rs.getString(11));
			
			
		}
		
		rs.close();
		pstmt.close();
		
		return emp;		
	}
	
	public void update(Emp emp) throws SQLException{
		Connection conn = DbUtil.getConnection();
		
		String sql = "update emp set emp_name=?,emp_login_name=?,emp_email=?,emp_phone=?,emp_salary=?,emp_dept_id=?,emp_job_id=?,emp_info=? where emp_id=?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, emp.getEmpName());
		pstmt.setString(2, emp.getEmpLoginName());
		pstmt.setString(3, emp.getEmpEmail());
		pstmt.setString(4, emp.getPhone());
		pstmt.setDouble(5, emp.getEmpSalary());
		pstmt.setInt(6, emp.getDept().getDeptId());
		pstmt.setInt(7, emp.getJob().getJobId());
		pstmt.setString(8, emp.getEmpInfo());
		pstmt.setInt(9, emp.getEmpId());
		
		pstmt.executeUpdate();
		pstmt.close();		
	}
	
	/**
	 * 根据id删除数据
	 * @param empId
	 * @throws SQLException 
	 */
	public void delete(int empId) throws SQLException{
		
		Connection conn = DbUtil.getConnection();
		
		String sql = "delete from emp where emp_id=?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1,empId);
		pstmt.executeUpdate();
		pstmt.close();
		
	}
	
	/**
	 * 更新头像
	 * @param empPic
	 * @throws SQLException 
	 */
	public void updateEmpPic(String empLoginName, String empPic) throws SQLException{
		Connection conn = DbUtil.getConnection();
		
		String sql = "update emp set emp_pic=? where emp_login_name=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, empPic);
		pstmt.setString(2, empLoginName);
		
		pstmt.executeUpdate();
		pstmt.close();
		
	}
	
	/**
	 * 查询头像数据
	 * @param empLoginName
	 * @return
	 * @throws SQLException 
	 */
	public String queryEmpPic(String empLoginName) throws SQLException{
		Connection conn = DbUtil.getConnection();
		
		String sql = "select emp_pic from emp where emp_login_name=?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, empLoginName);
		
		ResultSet rs = pstmt.executeQuery();
		
		String empPic = null;
		
		if(rs.next()){
			empPic = rs.getString(1);
		}
		
		rs.close();
		pstmt.close();
		
		return empPic;	
		
	}
	
	public void updatePwd(Emp emp) throws SQLException{
		
		Connection conn = DbUtil.getConnection();
		
		String sql = "update emp set emp_pwd=? where emp_login_name=?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,emp.getEmpPwd());
		pstmt.setString(2, emp.getEmpLoginName());
		pstmt.executeUpdate();
		pstmt.close();		
	}
	
	/**
	 * 分页显示查询结果
	 * @param emp
	 * @param pager
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Emp> querySearchEmp(Emp emp,Pager pager) throws SQLException{
		Connection conn = DbUtil.getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM ");
		sql.append("(SELECT ROWNUM num ,e.*,d.dept_name,j.job_name FROM emp e  ");
		sql.append("LEFT OUTER JOIN dept d ON d.dept_id=e.emp_dept_id ");
		sql.append("LEFT OUTER JOIN job j ON j.job_id=e.emp_job_id ");
		sql.append("WHERE 1=1 ");
		
		if(emp.getDept() != null){
			sql.append("AND e.emp_dept_id=? ");
		}
		if(emp.getJob() != null){
			sql.append("AND e.emp_job_id=? ");
		}
		if(emp.getEmpName() != null){
			sql.append("AND e.emp_name LIKE ? ");
		}
		sql.append(") emp_sql ");
		sql.append("WHERE emp_sql.num BETWEEN ? AND ?");
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		int index = 1;
		if(emp.getDept() != null){
			pstmt.setInt(index ++, emp.getDept().getDeptId());
		}
		if(emp.getJob() != null){
			pstmt.setInt(index ++, emp.getJob().getJobId());
		}
		if(emp.getEmpName() != null){
			pstmt.setString(index ++, "%"+ emp.getEmpName() +"%");
		}
		
		pstmt.setInt(index ++, pager.getStart());
		pstmt.setInt(index, pager.getEnd());
		ResultSet rs = pstmt.executeQuery();
		
		ArrayList<Emp> list = new ArrayList<>();
		
		while(rs.next()){
			Dept dept = new Dept();
			dept.setDeptId(rs.getInt(9));
			dept.setDeptName(rs.getString(13));
			Job job = new Job();
			job.setJobId(rs.getInt(10));
			job.setJobName(rs.getString(14));
			Emp empResult = new Emp();
			empResult.setEmpId(rs.getInt(2));
			empResult.setEmpName(rs.getString(3));
			empResult.setEmpEmail(rs.getString(6));
			empResult.setPhone(rs.getString(7));
			empResult.setDept(dept);
			empResult.setJob(job);			
			list.add(empResult);
		}
		rs.close();
		pstmt.close();
		
		return list;
	}
	
	public int getSearchEmpCount(Emp emp) throws SQLException{
		
		Connection conn = DbUtil.getConnection();
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(*) FROM emp e  ");
		sql.append("LEFT OUTER JOIN dept d ON d.dept_id=e.emp_dept_id ");
		sql.append("LEFT OUTER JOIN job j ON j.job_id=e.emp_job_id ");
		sql.append("WHERE 1=1 ");
		
		if(emp.getDept() != null){
			sql.append("AND e.emp_dept_id=? ");
		}
		if(emp.getJob() != null){
			sql.append("AND e.emp_job_id=? ");
		}
		if(emp.getEmpName() != null){
			sql.append("AND e.emp_name LIKE ? ");
		}
		
		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		int index = 1;
		if(emp.getDept() != null){
			pstmt.setInt(index ++, emp.getDept().getDeptId());
		}
		if(emp.getJob() != null){
			pstmt.setInt(index ++, emp.getJob().getJobId());
		}
		if(emp.getEmpName() != null){
			pstmt.setString(index ++, "%"+ emp.getEmpName() +"%");
		}
		ResultSet rs = pstmt.executeQuery();
		
		rs.next();
		
		int result = rs.getInt(1);
		
		rs.close();
		pstmt.close();
		
		return result;
		
	}
	
	
	
}
