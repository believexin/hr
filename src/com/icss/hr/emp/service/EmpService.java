package com.icss.hr.emp.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.icss.hr.common.Pager;
import com.icss.hr.dept.pojo.Dept;
import com.icss.hr.emp.dao.EmpDao;
import com.icss.hr.emp.pojo.Emp;
import com.icss.hr.job.pojo.Job;

/**
 * 员工的业务对象
 * @author Administrator
 *
 */
public class EmpService {
	
	private EmpDao empDao = new EmpDao();
	
	/**
	 * 用户名密码验证
	 * @param empLoginName
	 * @param empPwd
	 * @return 1用户名不存在 2密码错误 3验证成功
	 * @throws SQLException 
	 */
	public int checkLogin(String empLoginName,String empPwd) throws SQLException{
		
		Emp emp = empDao.queryByLoginName(empLoginName);
		
		if(emp == null)
			return 1;
		else if(!empPwd.equals(emp.getEmpPwd()))
			return 2;
		return 3;
		
		
	}
	
	/**
	 * 检查登陆名是否存在
	 * @return
	 * @throws SQLException 
	 */
	public boolean checkLoginName(String empLoginName) throws SQLException{
		
		Emp emp = empDao.queryByLoginName(empLoginName);
		
		if(emp == null)
			return false;
		
		return true;
		
	}
	
	public Emp queryEmpByLoginName(String empLoginName) throws SQLException{
		
		return empDao.queryByLoginName(empLoginName);
		
	}
	
	/**
	 * 增加员工
	 * @param emp
	 * @throws SQLException
	 */
	public void addEmp(Emp emp) throws SQLException{
		empDao.insert(emp);
	}
	
	/**
	 * 返回员工总数
	 * @return
	 * @throws SQLException
	 */
	public int getEmpCount() throws SQLException{
		return empDao.getCount();
	}
	
	/**
	 * 分页查询员工数据
	 * @param pager
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Emp> queryEmpByPage(Pager pager) throws SQLException{
		return empDao.queryByPge(pager);
	}
	
	/**
	 * 根据id查询员工数据
	 * @param empId
	 * @return
	 * @throws SQLException 
	 */
	public Emp queryEmpById(int empId) throws SQLException{
		
		return empDao.queryById(empId);
		
	}
	
	/**
	 * 修改该员工信息
	 * @param emp
	 * @throws SQLException
	 */
	public void updateEmp(Emp emp) throws SQLException{
		empDao.update(emp);
	}
	
	/**
	 * 删除员工信息
	 * @param empId
	 * @throws SQLException
	 */
	public void deleteEmp(int empId) throws SQLException{
		empDao.delete(empId);
	}
	
	/**
	 * 根据登陆名查询头像信息
	 * @param empLoginName
	 * @return
	 * @throws SQLException
	 */
	public String queryEmpPic(String empLoginName) throws SQLException{
		if(empLoginName == null){
			return null;
		}
		return empDao.queryEmpPic(empLoginName);
	}
	
	/**
	 * 上传头像
	 * @param empLoginName
	 * @param empPic
	 * @throws SQLException
	 */
	public void updateEmpPic (String empLoginName,String empPic) throws SQLException{
		empDao.updateEmpPic(empLoginName, empPic);
	}
	
	/**
	 * 查询用户名
	 * @return
	 * @throws SQLException 
	 */
	public Emp queryPwdLoginName(String empLoginName) throws SQLException{
		
		return empDao.queryByLoginName(empLoginName);
	}
	
	/**
	 * 修改密码
	 * @param emp
	 * @throws SQLException
	 */
	public void UpdatePwd(Emp emp) throws SQLException{
		empDao.updatePwd(emp);
	}
	
	/**
	 * 查询所有员工的数据
	 * @param emp
	 * @param pager
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Emp> querySearchEmp(Emp emp,Pager pager) throws SQLException{
		return  empDao.querySearchEmp(emp, pager);
	}
	
	/**
	 * 返回搜索结果的总记录数
	 * @param emp
	 * @return
	 * @throws SQLException
	 */
	public int getSearchEmpCount(Emp emp) throws SQLException{
		return empDao.getSearchEmpCount(emp);
	}
	
}

