package com.icss.test;

import java.sql.SQLException;

import org.junit.Test;

import com.icss.hr.emp.service.EmpService;

public class TestEmpService {
	
	private EmpService service = new EmpService();
	
	@Test
	public void testEmpService() throws SQLException{
		
		int result = service.checkLogin("tom", "123456");
		
		System.out.println(result);
		
	}
	
}
