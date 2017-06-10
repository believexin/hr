package com.icss.hr.job.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.icss.hr.job.pojo.Job;
import com.icss.hr.job.service.JobService;

/**
 * 获得职务控制器
 */
@WebServlet("/GetJobServlet")
public class GetJobServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
//		获得输出流
		PrintWriter out = response.getWriter();
//		 获得职务id
		String jobId = request.getParameter("jobId");
		
//		调用业务对象
		JobService service = new JobService();
		
		try {
			Job job = service.queryJobById(Integer.parseInt(jobId));
			
//			转换为json数据
			
			Gson json = new Gson();
			out.write(json.toJson(job));
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
