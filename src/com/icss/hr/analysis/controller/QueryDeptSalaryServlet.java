package com.icss.hr.analysis.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.icss.hr.analysis.service.AnaService;
@WebServlet("/QueryDeptSalaryServlet")
public class QueryDeptSalaryServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		
		AnaService service = new AnaService();
		
		try {
			ArrayList<Map<String, Object>> list = service.queryEmpSalary();
			
			Gson gson = new Gson();
			out.print(gson.toJson(list));
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

}
