package com.icss.hr.emp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.icss.hr.emp.service.EmpService;

/**
 * 登陆控制器
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		获得输出流
		PrintWriter out = response.getWriter();
		
		String empLoginName = request.getParameter("empLoginName");
		String empPwd = request.getParameter("empPwd");
		
//		调用业务对象
		EmpService service = new EmpService();
		
		try {
//			得到验证结果
			int result = service.checkLogin(empLoginName, empPwd);
			
			out.print(result);
			
//			如果登陆验证通过，在session中存储登陆标示
			if(result == 3){
				HttpSession session = request.getSession();
				session.setAttribute("empLoginName", empLoginName);
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
