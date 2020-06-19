package com.oracle.filter;

import java.io.File;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
public class StaticFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//1.将请求和响应向下转型
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		
		// 2.拿到htmls目录
		String path = req.getServletContext().getRealPath("htmls");
		 
		
		// 3.拿到页码，（根据页码生成htmls目录）

		String action = req.getParameter("action"); 		
		 if("showPasgefl".equals(action)||"showPasgeBook".equals(action)||"showPasgeUser".equals(action)){		 
		 String pageNew = req.getParameter("pageNew");  //拿到pagenow,当前页
		 if(pageNew==null||pageNew.trim().isEmpty()){   //第一次点pagenow=1
			 
			 pageNew="1";
			 
		      }
		String fileName = action+pageNew + ".html";  //生成html
		
		// 4.创建这个文件
		File file = new File(path, fileName); //在htmls目录下创建html
		
		// 5.判断文件是否存在
		if(file.exists()){
			// 如果存在就跳转到这个页面
			// 存在       拿到项目名称
			String url= req.getServletContext().getContextPath();			 
			resp.sendRedirect(url + "/htmls/" + fileName);
			return;//跳完之后，没有执行的必要，直接return
			//此时，可以生成，但是里面是没有数据的
		} else {
			//如果不存在，先生成文件再跳转
			//对响应对象进行重写
			StaticResponse staticResponse = new StaticResponse(resp, file.getAbsolutePath());//拿到file的绝对路径，把数据保存在指定的HTML文件中
			chain.doFilter(request, staticResponse);
			String url = req.getServletContext().getContextPath();
			resp.sendRedirect(url + "/htmls/" + fileName);
		     }
		 }else{
			      File file=new File(path);
			      File[] arr=file.listFiles();
			      for(File f:arr){
			    	  f.delete();
			      }
			      chain.doFilter(request, response);
		 }

	}

	 
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
