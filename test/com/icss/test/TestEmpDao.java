package com.icss.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import com.icss.hr.common.DbUtil;
import com.icss.hr.common.Pager;
import com.icss.hr.dept.pojo.Dept;
import com.icss.hr.emp.dao.EmpDao;
import com.icss.hr.emp.pojo.Emp;
import com.icss.hr.job.pojo.Job;

public class TestEmpDao {
	
	private EmpDao dao = new EmpDao();
	
	@Test
	public void testinsert() throws SQLException{
		Dept dept = new Dept();
		dept.setDeptId(30);
		
		Job job = new Job();
		job.setJobId(31);
		
		Emp emp = new Emp("tom","tom","123456","tom@163.com","113012587",4500.00,dept,job,null,"我叫汤姆");
		
		dao.insert(emp);
		
	}
	@Test
	public void testQueryByLoginName() throws SQLException{
		
		Emp emp = dao.queryByLoginName("tom");
		
		System.out.println(emp);
		
	}
	@Test
	public void testinsertMany() throws SQLException{
		Dept dept = new Dept();
		dept.setDeptId(20);
		
		Job job = new Job();
		job.setJobId(32);
		for(int i = 1;i < 35 ;i ++){
			Emp emp = new Emp("john" + i,"john" + i,"123456","tom@163.com","113012587",4500.00,dept,job,null,"我叫汤姆");
			
			dao.insert(emp);
		}
		
		
	}
	
	@Test
	public void testgetCount() throws SQLException{
		
		System.out.println(dao.getCount());
		
	}
	
	@Test
	public void testQueryByPage() throws SQLException{
		
		Pager pager = new Pager(dao.getCount(),20,1);
		
		ArrayList<Emp> list = dao.queryByPge(pager);
		
		list.forEach(System.out::println);
		
	}
	@Test
	public void testQueryById() throws SQLException{

		System.out.println(dao.queryById(1));
	}
	
	@Test
	public void testUpdateEmp() throws SQLException{
		Dept dept = new Dept();
		dept.setDeptId(30);
		
		Job job = new Job();
		job.setJobId(31);
		
		Emp emp = new Emp(1,"tom","tom","123456","alter@163.com","113012587",4500.00,dept,job,null,"我叫alter");
		
		dao.update(emp);
		
	}
	
	
	@Test
	public void testDeleteEmp() throws SQLException{
		
		dao.delete(34);
		
	}
	
	@Test
	public void testUpdateEmpPic() throws SQLException{
		
		String dataUrl = "data:image/jpeg;base64,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";
		
		dao.updateEmpPic("tom", dataUrl);
		
	}
	
	@Test
	public void testQueryEmpPic() throws SQLException{
		
		System.out.println(dao.queryEmpPic("believexin"));
		
	}
	
	
	@Test
	public void testUpdatePwd() throws SQLException{
		
		Emp emp = new Emp();
		emp.setEmpLoginName("tom");
		emp.setEmpPwd("666666");
		dao.updatePwd(emp);
		
	}
	
	@Test
	public void testQuerySearchEmp() throws SQLException{
		
		Emp emp = new Emp();
		Dept dept = new Dept();
		dept.setDeptId(30);
		
		Job job = new Job(); 
		job.setJobId(31);
		
		emp.setDept(dept);
		emp.setJob(job);
		emp.setEmpName("t");
		System.out.println();
		Pager pager = new Pager(8, 5, 1);
		System.out.println(pager.getStart() + "," + pager.getEnd());
		
		dao.querySearchEmp(emp, pager).forEach(System.out::println);
		
	}
	
	@Test
	public void testSearchEmpCount() throws SQLException{
		
		Emp emp = new Emp();
		Dept dept = new Dept();
		dept.setDeptId(30);
		Job job = new Job(); 
		job.setJobId(31);
		emp.setDept(dept);
		emp.setJob(job);
		
		System.out.println(dao.getSearchEmpCount(emp));
		
	}
	
	
	
}
