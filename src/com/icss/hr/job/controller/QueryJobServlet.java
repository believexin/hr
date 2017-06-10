package com.icss.hr.job.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.icss.hr.job.pojo.Job;
import com.icss.hr.job.service.JobService;

/**
 * 查询职务控制器
 */
@WebServlet("/QueryJobServlet")
public class QueryJobServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
//		调用业务对象
		JobService service = new JobService();
		PrintWriter out = response.getWriter();
		
		try {
			ArrayList<Job> list = service.queryJob();
			
//			转换为json数据
			Gson gson = new Gson();
			
			out.println(gson.toJson(list));
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
