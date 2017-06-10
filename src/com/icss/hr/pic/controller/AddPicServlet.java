package com.icss.hr.pic.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.icss.hr.pic.pojo.Pic;
import com.icss.hr.pic.service.PicService;

/**
 * 上传照片
 */
@WebServlet("/AddPicServlet")
public class AddPicServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 设置编码
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");

			PrintWriter out = response.getWriter();

			// 获得Servlet上下文对象
			ServletContext context = this.getServletContext();

			// 磁盘文件列表工厂对象
			DiskFileItemFactory factory = new DiskFileItemFactory();

			// 设置内存区块大小4KB
			factory.setSizeThreshold(4 * 1024);

			// 设置暂存容器临时目录，当上传文件大于设置的内存块大小时，用暂存容器做中转（非常重要）
			factory.setRepository(new File(context.getRealPath("/temp")));

			// Servlet文件上传对象
			ServletFileUpload upload = new ServletFileUpload(factory);
			
//			获得登陆名
			HttpSession session = request.getSession();
			String EmpLoginName = (String)session.getAttribute("empLoginName");

			// 获得上传列表
			try {
				// 设置最大上传文件大小为1000M，超出大小会发生异常
				upload.setSizeMax(1024 * 1024 * 1000);

				// 获得所有上传文件对象集合
				List<FileItem> list = upload.parseRequest(request);

				// 获得第一个文件对象
				FileItem picData = list.get(0);
				
				//获得普通表单元素				
				String picInfo = list.get(1).getString();
				
				picInfo = new String(picInfo.getBytes("iso-8859-1"),"utf-8");
				
				// 文件大小
				long fileSize = picData.getSize();
//				

				

				// MIME类型
				String contentType = picData.getContentType();

				// 客户端文件路径
				String fullName = picData.getName();

				// 原始文件名称
				String oldFileName = fullName.substring(fullName.lastIndexOf("\\") + 1);

				// 扩展名
				String extName = oldFileName.substring(oldFileName.lastIndexOf("."));

				// 生成新文件名称(当前毫秒数连接1~1000随机数)
				String newFileName = System.currentTimeMillis() + "" + (int) ((1000 - 1 + 1) * Math.random() + 1) + extName;
				
				//封装pojo对象				
				Pic pic = new Pic(newFileName,picInfo,fileSize,EmpLoginName,picData.getInputStream(),new Date());
				
				PicService service = new PicService();
				service.addPic(pic);

				// 删除临时文件
				picData.delete();
				

			} catch (Exception e) {
				e.printStackTrace();
			}
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
