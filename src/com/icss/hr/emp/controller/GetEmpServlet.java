package com.icss.hr.emp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.icss.hr.emp.pojo.Emp;
import com.icss.hr.emp.service.EmpService;

import sun.net.www.content.image.gif;

/**
 * 查询指定Id员工控制器
 */
@WebServlet("/GetEmpServlet")
public class GetEmpServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
//		获得员工id
		String empId = request.getParameter("empId");
		
//		调用业务对象
		EmpService serive = new EmpService();
		
		try {
//			返回员工对象
			Emp emp = serive.queryEmpById(Integer.parseInt(empId));
			
//			转换json数据
			Gson gson = new Gson();			
			out.write(gson.toJson(emp));
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
