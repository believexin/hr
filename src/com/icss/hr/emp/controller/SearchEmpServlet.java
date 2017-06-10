package com.icss.hr.emp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.icss.hr.common.Pager;
import com.icss.hr.dept.pojo.Dept;
import com.icss.hr.emp.pojo.Emp;
import com.icss.hr.emp.service.EmpService;
import com.icss.hr.job.pojo.Job;

/**
 * Servlet implementation class SearchEmp
 */
@WebServlet("/SearchEmpServlet")
public class SearchEmpServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		获取请求参数
		String deptId = request.getParameter("deptId");
		String jobId = request.getParameter("jobId");
		String empName = request.getParameter("empName");
//		获取页码
		String pageNumStr = request.getParameter("pageNumStr");
//		获得每页多少条
		String pageSizeStr = request.getParameter("pageSize");
		
//		获取输出流
		PrintWriter out = response.getWriter();
		
		int pageNum = 1;
		
		try {
			pageNum = Integer.parseInt(pageNumStr);

			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
//		调用业务层
		EmpService service = new EmpService();
		try {
			
//			封装pojo
			Dept dept = null;
			if(Integer.parseInt(deptId) != 0){
				dept = new Dept();
				dept.setDeptId(Integer.parseInt(deptId));
			}
			Job job = null;
			if(Integer.parseInt(jobId) != 0){
				job = new Job();
				job.setJobId(Integer.parseInt(jobId));
			}
			
			Emp emp = new Emp();
			emp.setEmpName(empName);
			emp.setDept(dept);
			emp.setJob(job);
			
			
			Pager pager = new Pager(service.getSearchEmpCount(emp), Integer.parseInt(pageSizeStr), pageNum);
			
			ArrayList<Emp> list = service.querySearchEmp(emp, pager);
			
			
			HashMap<String, Object> map = new HashMap<>();
			
			map.put("pager", pager);
			map.put("list", list);
			
			Gson gson = new Gson();
			
			out.print(gson.toJson(map));
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
