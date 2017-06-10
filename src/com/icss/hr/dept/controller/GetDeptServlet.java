package com.icss.hr.dept.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.hr.dept.pojo.Dept;
import com.icss.hr.dept.service.impl.DeptServiceImpl;

/**
 * 获取修改数据
 */
@WebServlet("/GetDeptServlet")
public class GetDeptServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//		获得部门编号
		String deptId = request.getParameter("deptId");
		
//		调用业务对象
		DeptServiceImpl serice  = new DeptServiceImpl();
		
		try {
			Dept dept = serice.queryDeptById(Integer.parseInt(deptId));
			
			request.setAttribute("dept", dept);
			request.getRequestDispatcher("UpdateDept.jsp").forward(request, response);
			
			
		} catch (Exception e) {
			
			
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
