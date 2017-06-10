package com.icss.hr.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 通用过滤器
 * 
 * @author Administrator
 *
 */
@WebFilter("/*")
public class CommonFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		// 转换子接口类型
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		// 设置响应报头允许当前应用被跨域请求
		response.setHeader("Access-Control-Allow-Origin", "*");

		// 同意设置编码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		// 登陆验证
//		String uri = request.getRequestURI();
//		String app = request.getContextPath();
//		HttpSession session = request.getSession();
//		if (!uri.equals(app + "/")
//				&& !uri.equals(app + "/login.html")
//				&& !uri.equals(app + "/logout.html")
//				&& !uri.equals(app + "/LoginServlet")
//				&& !uri.equals(app + "/404.jsp")
//				&& !uri.equals(app + "/error.jsp")
//				&& !uri.startsWith(app + "/css")
//				&& !uri.startsWith(app + "/js")
//				&& !uri.startsWith(app + "/images")) {
//
//			
//			String empLoginName = (String) session.getAttribute("empLoginName");
//			if (empLoginName == null) {
//				DbUtil.close();
//
//				// 判断是否是ajax请求 ，在响应报头中设置一个标识
//				if (request.getHeader("x-requested-with") != null
//						&& request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
//					response.setHeader("sessionStatus", "timeout");
//				} else {
//					response.sendRedirect(app + "/logout.html");
//				}
//
//				response.sendRedirect(app + "/logout.html");
//				return;
//			}
//		}
//		String empLoginName1 = (String) session.getAttribute("empLoginName");
//		
//		
//		if(empLoginName1 != null && (uri.equals(app + "/login.html") || uri.equals(app + "/"))){
//			response.sendRedirect(app + "/main.html");
//			DbUtil.close();
//			return;
//		}
		// 继续向下执行
		chain.doFilter(request, response);

		// 调用同意关闭连接方法
		DbUtil.close();

	}

	@Override
	public void destroy() {

	}

}
