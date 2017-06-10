package com.icss.hr.emp.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.hr.dept.pojo.Dept;
import com.icss.hr.emp.pojo.Emp;
import com.icss.hr.emp.service.EmpService;
import com.icss.hr.job.pojo.Job;

/**
 * 增加员工的控制器
 */
@WebServlet("/AddEmpServlet")
public class AddEmpServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String empName = request.getParameter("empName");
		String empLoginName = request.getParameter("empLoginName");
		String empPwd = request.getParameter("empPwd");
		String empEmail = request.getParameter("empEmail");
		String phone = request.getParameter("phone");
		String empSalary = request.getParameter("empSalary");
		String deptId = request.getParameter("deptId");
		String jobId = request.getParameter("jobId");
		String empInfo = request.getParameter("empInfo");
		
//		封装pojo
		Dept dept = new Dept();
		dept.setDeptId(Integer.parseInt(deptId));
		
		Job job = new Job();
		job.setJobId(Integer.parseInt(jobId));
		
		Emp emp = new Emp(empName, empLoginName, empPwd, empEmail, phone, Double.parseDouble(empSalary), dept, job,
				null, empInfo);
		
		EmpService service = new EmpService();
		try {
			service.addEmp(emp);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
