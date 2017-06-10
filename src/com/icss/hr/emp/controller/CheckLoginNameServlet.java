package com.icss.hr.emp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.hr.emp.service.EmpService;

/**
 * 判断用户名是否存在
 */
@WebServlet("/CheckLoginNameServlet")
public class CheckLoginNameServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
//		获得登陆名称
		String empLoginName = request.getParameter("empLoginName");
		
		PrintWriter out = response.getWriter();
//		调用业务对象
		EmpService service  =new EmpService();
		
		try {
			boolean result = service.checkLoginName(empLoginName);
			out.write(String.valueOf(result));
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
