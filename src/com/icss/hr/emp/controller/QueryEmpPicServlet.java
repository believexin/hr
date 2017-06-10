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
 * 查询头像数据控制器
 */
@WebServlet("/QueryEmpPicServlet")
public class QueryEmpPicServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();
//		调用业务对象
		EmpService serivce = new EmpService();

		String empLoginName = (String) session.getAttribute("empLoginName");
		
		try {
//			返回头像数据
			String empPic = serivce.queryEmpPic(empLoginName);
			if(empPic != null){
				out.print(empPic);
			}else{
				out.print("null");
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
