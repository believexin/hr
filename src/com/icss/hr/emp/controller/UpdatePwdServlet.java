package com.icss.hr.emp.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icss.hr.emp.pojo.Emp;
import com.icss.hr.emp.service.EmpService;

/**
 * 修改密码控制器
 */
@WebServlet("/UpdatePwdServlet")
public class UpdatePwdServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//		获得密码
		String empPwd = request.getParameter("empPwd");
		
		
		HttpSession session = request.getSession();
		
		String empLoginName = (String) session.getAttribute("empLoginName");
	
//		封装pojo对象
		Emp emp = new Emp();
		
		emp.setEmpLoginName(empLoginName);
		emp.setEmpPwd(empPwd);
		
//		调用业务
		EmpService service = new EmpService();
		
		try {
			service.UpdatePwd(emp);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
