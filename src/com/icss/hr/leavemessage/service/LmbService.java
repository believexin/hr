package com.icss.hr.leavemessage.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.icss.hr.leavemessage.dao.LmbDao;
import com.icss.hr.leavemessage.pojo.Lmb;

/**
 * 留言板业务逻辑层
 * @author believexin
 *
 */
public class LmbService {
	
	private LmbDao dao = new LmbDao();
	
	/**
	 * 增加一条留言
	 * @param lmb
	 * @throws SQLException
	 */
	public void addLm(Lmb lmb) throws SQLException{
		dao.insert(lmb);
	}
	
	/**
	 * 查询所有主留言记录
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Lmb> QuetyMainLm() throws SQLException{
		
		ArrayList<Lmb> list = dao.query();
		ArrayList<Lmb> resultList = new ArrayList<>();
		
		for(int i = 0;i < list.size();i ++){
			
			if(list.get(i).getCommentEmp() == null){
				resultList.add(list.get(i));
			}
			
		}
		
		return resultList;
	}
	
	/**
	 * 查询所有评论内容
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Lmb> queryComLm() throws SQLException{
		
		ArrayList<Lmb> list = dao.query();
		ArrayList<Lmb> resultList = new ArrayList<>();
		
		for(int i = 0;i < list.size();i ++){
			
			if(list.get(i).getCommentEmp() != null){
				resultList.add(list.get(i));
			}
			
		}
		
		return resultList;
		
	}
	
}
