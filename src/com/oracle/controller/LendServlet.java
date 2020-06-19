package com.oracle.controller;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oracle.domain.Book;
import com.oracle.domain.Fenlei;
import com.oracle.domain.Lend;
import com.oracle.domain.PageBean;
import com.oracle.factory.AdminServiceFactory;
import com.oracle.factory.BookServiceFactory;
import com.oracle.factory.LendServiceFactory;
 
 
public class LendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		
		HttpSession session=req.getSession();
		String action=req.getParameter("action");
		
		 String id=req.getParameter("id");
	     String flname=req.getParameter("flname");
		 String  name=req.getParameter("name");
		 String  money=req.getParameter("money");
		 String press=req.getParameter("press");
		 String  reader=req.getParameter("reader"); 
		 String lend_date=req.getParameter("lend_date");
		 String  back_date=req.getParameter("back_date");
		 String ids1=req.getParameter("ids");
		 int pageNew=this.getPageNew(req);

	
		
		
		if("add".equals(action)){//添加	
			 //double money=Integer.parseInt(money1);
			Lend lend=new Lend(flname, name, money, press, reader,lend_date,back_date);
	 
				int s1=LendServiceFactory.getLendServiceImpl().addlend(lend);
				if(s1>0){
					session.setAttribute("mag","借阅添加成功!");
					resp.sendRedirect("LendServlet?action=showPasgeLend");
				}else{
					session.setAttribute("mag","借阅添加失败!");
					resp.sendRedirect("addlend.jsp");
				}
 
			
		}
		
		

		if("delete".equals(action)){//删除
			
			
			 String[] ids=ids1.split(",");
			 
			int[] arr=LendServiceFactory.getLendServiceImpl().delete(ids);
			
			if (arr == null) {
				session.setAttribute("mag","警告->删除异常！");
				resp.sendRedirect("LendServlet?action=showPasgeLend&pageNew="+pageNew);
				return;
			}
			
			resp.sendRedirect("LendServlet?action=showPasgeLend&pageNew="+pageNew);
			
			return;
			 
			
		}	
		
	if ("showOne".equals(action)) {// 单查
            
			int id1 = Integer.parseInt(id);
			//System.out.println(id1);
			Lend fl=LendServiceFactory.getLendServiceImpl().dancha(id1);
			if(fl!=null){
				 
				 List<Fenlei> list=LendServiceFactory.getLendServiceImpl().findfl();	
			    session.setAttribute("list", list);
				session.setAttribute("fl", fl);
				session.setAttribute("pageNew", pageNew);
				resp.sendRedirect("changelend.jsp");
				return;
				
			} else {

				session.setAttribute("mag", name +"没有借阅记录");
				resp.sendRedirect("LendServlet?action=showPasgeLend");
				return;

			}
	}
	  if("update2".equals(action)){// 3.修改还书日期
	    	String date = req.getParameter("back_date");
	    	String un = (String) session.getAttribute("name");
	    	int a = LendServiceFactory.getLendServiceImpl().update2(date, un);
	    	if (a > 0) {
	    		session.setAttribute("mag", "修改成功,需重新登录！");
	    		resp.sendRedirect("exit.jsp");//重定向到退出界面
	    		return;
	    		} else {
	    			session.setAttribute("mag", "非常抱歉->修改失败了！");
	    			resp.sendRedirect("changelend.jsp");//重定向到修改密码界面
	    			return;
	    			}
	    	}	

		
		if("showPasgeLend".equals(action)){//查看
			
			// int pageNew=this.getPageNew(req);
			 int pageSize=5;
            
			 PageBean<Lend> pb=LendServiceFactory.getLendServiceImpl().showPesgefl(pageNew,pageSize);
			 List<Fenlei> list=LendServiceFactory.getLendServiceImpl().findfl();	

			 session.setAttribute("list", list);
				session.setAttribute("pb", pb);
				session.setAttribute("showPesge","lend");
				//resp.sendRedirect("showBook.jsp");
				req.getRequestDispatcher("showLend.jsp").forward(req, resp);
				return;
			
		}
}
			
	
			public int getPageNew(HttpServletRequest req){
				
				String pageNew=req.getParameter("pageNew");
				
				 if(pageNew==null||pageNew.trim().isEmpty()){
					 return 1;
				 }
				return Integer.parseInt(pageNew);
			}
		
			
}		
