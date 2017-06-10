package com.icss.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import com.icss.hr.pic.dao.PicDao;
import com.icss.hr.pic.pojo.Pic;

public class TestPicDao {
	
	private PicDao dao = new PicDao();
	@Test
	public void testInsert() throws FileNotFoundException, SQLException{
		
		File file = new File("e:\\pic\\17.jpg");
		FileInputStream fis = new FileInputStream(file);
		
		Pic pic = new Pic("17.jpg","大爷常来玩啊",file.length(),"tom",fis,null);
		
		
		
		dao.insert(pic);
		
	}
	
	@Test
	public void testQuery() throws SQLException{
		ArrayList<Pic> list = dao.query();
		
		list.forEach(System.out::println);
	}
	
}
