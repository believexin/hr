package com.icss.test;

import java.sql.SQLException;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.junit.Test;

import com.icss.hr.analysis.dao.AnaDao;

public class TestAnanDao {
	private AnaDao dao = new AnaDao();
	
	@Test
	public void testQueryEmpCount() throws SQLException{		
		dao.queryEmpCount().forEach(System.out::println);
		
	}
	
	@Test
	public void testQueryEmpAvg() throws SQLException{		
		dao.queryEmpAvg().forEach(System.out::println);
		
	}
	
	
}
