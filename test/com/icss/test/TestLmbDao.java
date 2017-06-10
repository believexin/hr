package com.icss.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import com.icss.hr.emp.dao.EmpDao;
import com.icss.hr.leavemessage.dao.LmbDao;
import com.icss.hr.leavemessage.pojo.Lmb;

public class TestLmbDao {
	private LmbDao dao = new LmbDao();
	
	@Test
	public void testInsert() throws SQLException{
		
		EmpDao empdao = new EmpDao();
		for(int i=0;i<20;i++){
			Lmb lmb = new Lmb(empdao.queryById(1),"你好tom，我是jack",new Date());
			dao.insert(lmb);
		}
		
		
	}
	
	@Test
	public void testQuery() throws SQLException{
		
//		EmpDao empdao = new EmpDao();		
		ArrayList<Lmb> list = dao.query();
		
		list.forEach(System.out::println);
		
		
	}
}
