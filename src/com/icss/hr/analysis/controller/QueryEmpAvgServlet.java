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

/**
 * 部门平均工资数据接口
 */
@WebServlet("/QueryEmpAvgServlet")
public class QueryEmpAvgServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
//		获得输出流
		PrintWriter out = response.getWriter();
		
//		调用业务层
		
		AnaService service = new AnaService();
		
		try {
			ArrayList<Map<String, Object>> list = service.queryEmpAvg();			
			Gson gson = new Gson();
			out.write(gson.toJson(list));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
