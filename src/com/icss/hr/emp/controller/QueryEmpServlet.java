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
import com.icss.hr.emp.pojo.Emp;
import com.icss.hr.emp.service.EmpService;
@WebServlet("/QueryEmpServlet")
public class QueryEmpServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		获取页码
		String pageNumStr = req.getParameter("pageNumStr");
//		获得每页多少条
		String pageSizeStr = req.getParameter("pageSize");
		
		int pageNum = 1;
		
		try {
			pageNum = Integer.parseInt(pageNumStr);
		} catch (NumberFormatException e) {
			
			e.printStackTrace();
		}
		int pageSize = 10;
		try {
			pageSize = Integer.parseInt(pageSizeStr);
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

				
		PrintWriter out = resp.getWriter();
		
		EmpService service = new EmpService();
		try {
//			分页对象
			Pager pager = new Pager(service.getEmpCount(),pageSize, pageNum);
//			获得当前页的数据集合
			ArrayList<Emp> list = service.queryEmpByPage(pager);
			
			HashMap<String, Object> map = new HashMap<>();
			
			map.put("pager", pager);
			map.put("list", list);
			
//			转换json数据
			
			Gson gson = new Gson();
			out.print(gson.toJson(map));
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		super.doPost(req, resp);
	}
	
}
