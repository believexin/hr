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
@WebServlet("/UpdateEmpServlet")
public class UpdateEmpServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String empName = req.getParameter("empName");
		String empLoginName = req.getParameter("empLoginName");
		String empPwd = req.getParameter("empPwd");
		String empEmail = req.getParameter("empEmail");
		String phone = req.getParameter("phone");
		String empSalary = req.getParameter("empSalary");
		String deptId = req.getParameter("deptId");
		String jobId = req.getParameter("jobId");
		String empInfo = req.getParameter("empInfo");
		String empId = req.getParameter("empId");
		
		//封装pojo
		Dept dept = new Dept();
		dept.setDeptId(Integer.parseInt(deptId));
		
		Job job = new Job();
		job.setJobId(Integer.parseInt(jobId));
		
		Emp emp = new Emp(Integer.parseInt(empId),empName, empLoginName, empPwd, empEmail, phone, Double.parseDouble(empSalary), dept, job,
				null, empInfo);
		

		
		EmpService service = new EmpService();
		try {
			service.updateEmp(emp);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doGet(req, resp);
	}

}
