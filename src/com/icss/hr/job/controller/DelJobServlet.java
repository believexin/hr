package com.icss.hr.job.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icss.hr.job.pojo.Job;
import com.icss.hr.job.service.JobService;

/**
 *	删除职务的控制器
 */
@WebServlet("/DelJobServlet")
public class DelJobServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		获得请求参数
		String jobId = request.getParameter("jobId");
		System.out.println(jobId);
	
//		调用业务对象
		JobService service = new JobService();
		
		
		try {
			service.deleteJOb(Integer.parseInt(jobId));
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);

		
	}

}
