package com.icss.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.junit.Test;

import com.icss.hr.common.DbUtil;
import com.icss.hr.dept.dao.impl.DeptDaoImpl;
import com.icss.hr.dept.pojo.Dept;

public class TestDeptDao {
	private DeptDaoImpl dao = new  DeptDaoImpl();
	
	@Test
	public void testInsert (){
		
		Dept dept  = new Dept(" 第二开发部","大连");
		
		
		try {
			dao.insert(dept);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
	@Test
	public void testUpdate () throws Exception{
		
		Dept dept  = new Dept(" 第一开发部","北京");
		
		dao.update(dept);
		
	}
	@Test
	public void testDelete () throws Exception{
		
		dao.delete(10);
		
	}
	@Test
	public void testqueryById () throws Exception{
		
		System.out.println(dao.queryById(10).toString());
		
	}
	@Test
	public void testquery() throws Exception {
		
		ArrayList<Dept> list = dao.query();
		
		for(int i = 0;i < list.size();i ++){
			
			System.out.println(list.get(i));
			
		}
		
		
	}
}
