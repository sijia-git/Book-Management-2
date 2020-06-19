package com.oracle.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	public void destroy() {//销毁
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		//强转，把父类型强转成子类型
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		//登录会走servlet，传入username
		//创建一个session ，取出其中的username
		HttpSession session = req.getSession();
		String username = (String) session.getAttribute("username");
		
		
		//如果没有登录，只能访问登录和注册
		if (username == null || username.isEmpty()) {
			//返回URL（字符串）
			String url = req.getRequestURI();
			System.out.println(url);//打印出访问的是哪一个jsp
			//  /Book2/index.jsp

			String path = url.substring(url.lastIndexOf("/") + 1);//截取字符串（从最后一个/截取到最后）

			if ("login.jsp".equals(path) || "addAdmin1.jsp".equals(path)) {
				chain.doFilter(request, response);//如果是登录或注册页面就放行
			} else {
				resp.sendRedirect("login.jsp");//重定向到登录页面
			}
		} else {//若登录，则访问所有页面

			chain.doFilter(request, response);//若要执行目标资源，调用chain.doFilter放行
		}

		
	}

	public void init(FilterConfig fConfig) throws ServletException {//创建filter对象
		// TODO Auto-generated method stub

	}

}
