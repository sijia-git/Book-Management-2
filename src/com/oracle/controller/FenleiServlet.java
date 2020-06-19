package com.oracle.controller;

import java.io.IOException;
 
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oracle.domain.Fenlei;
import com.oracle.domain.PageBean;
 
import com.oracle.factory.FenleiServiceFactory;
 

import net.sf.json.JSONArray;

 
public class FenleiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
		
		HttpSession session=req.getSession();
		//拿到提交的参数
		String action=req.getParameter("action");
		String id=req.getParameter("id");
		String name=req.getParameter("name");//拿到新名
		String oldname=req.getParameter("oldname");//拿到旧名
		String ids1=req.getParameter("ids");
		 int pageNew=this.getPageNew(req);
		//String back=req.getParameter("back");
		 
		if("add".equals(action)){//1.添加分类
			
			Fenlei fl=new Fenlei(name);
	 
				int s1=FenleiServiceFactory.getFenleiServiceImpl().addfl(name);
				if(s1>0){
					session.setAttribute("mag", "分类添加成功!");
					resp.sendRedirect("FenleiServlet?action=showPasgefl");
				}else{
					session.setAttribute("mag","分类添加失败!");
					resp.sendRedirect("addfl.jsp");
				}
 
			
		}
           if("yanzheng".equals(action)){//1.添加分类/修改分类的验证
			
			int a=FenleiServiceFactory.getFenleiServiceImpl().yanzheng(name);//验证分类是否存在，不存在才能继续添加
			  
		    if(a==0){
	        	  
	        	  resp.getWriter().write("{\"valid\":\"true\"}");
	        	  
					 return;
				 
	          }else{
	        	  resp.getWriter().write("{\"valid\":\"false\",\"message\":\"该分类名已存在\"}");
					 return;
				 
	          }
				  
			 
		}
		
		if("delete".equals(action)){//2.删除
			
			String[] ids=ids1.split(",");
			//System.out.println(ids[0]+" "+ids[1]);
			int[] arr=FenleiServiceFactory.getFenleiServiceImpl().delete(ids);
			
			if (arr == null) {
				session.setAttribute("mag","警告->删除异常！");
				resp.sendRedirect("FenleiServlet?action=showPasgefl&pageNew="+pageNew);
				return;
			}
			
			resp.sendRedirect("FenleiServlet?action=showPasgefl&pageNew="+pageNew);
			return;
			 
			
		}
		
			
		if ("showOne".equals(action)) {// 3.单项修改的单查
            
			int id1 = Integer.parseInt(id);
			Fenlei fl=FenleiServiceFactory.getFenleiServiceImpl().dancha(id1);
			System.out.println(fl);
			if(fl!=null){
				session.setAttribute("fl", fl);
				session.setAttribute("pageNew", pageNew);
				resp.sendRedirect("changeFenlei.jsp");
				return;
				
			} else {

				session.setAttribute("mag", name + "书库中没有这个分类！");
				resp.sendRedirect("FenleiServlet?action=showPasgefl&pageNew="+pageNew);
				return;

			}
			
		}

 	if ("update".equals(action)) {// 3.单项修改
 		
 		int id1 = Integer.parseInt(id);
 		Fenlei fl1=new Fenlei(id1,name);
 		int i = FenleiServiceFactory.getFenleiServiceImpl().update(fl1);
 		
 	 
		if (i > 0) {
			session.setAttribute("mag", "修改成功！");
			resp.sendRedirect("FenleiServlet?action=showPasgefl&pageNew="+pageNew);
			return;

		} else {
			session.setAttribute("mag", "修改失败！");
			resp.sendRedirect("FenleiServlet?action=update&pageNew="+pageNew);
			return;
		}
 	 	 
 	}
 	

if ("showOne2".equals(action)) {// 4.多项修改单查
	resp.setContentType("text/html;charset=utf-8");
	 List<Fenlei> list=FenleiServiceFactory.getFenleiServiceImpl().findfl();//拿到分类列表list
	 JSONArray jsonArray=JSONArray.fromObject(list);	 
	 resp.getWriter().write(jsonArray.toString());
		 return;
	
	
	}

if ("update2".equals(action)) {// 4.多项修改
  
 		int i = FenleiServiceFactory.getFenleiServiceImpl().update2(oldname,name);
 		
 	 
		if (i > 0) {
			session.setAttribute("mag","修改成功！");
			resp.sendRedirect("FenleiServlet?action=showPasgefl");
			return;

		} else {
			session.setAttribute("mag","修改失败！");
			resp.sendRedirect("FenleiServlet?action=showOne2");
			return;
		}
 	}
  



    if("showPasgefl".equals(action)){//5.全查， 以分页的形式显示分类列表
 
	 int pageSize=5;//一页显示几条记录
	 int PageNew=this.getPageNew(req);//当前页
	 
	 PageBean<Fenlei> pb=FenleiServiceFactory.getFenleiServiceImpl().showPesgefl(pageNew,pageSize);
	   
		session.setAttribute("pb", pb);//把pb放在session中发给页面
		
		//resp.sendRedirect("showFenlei.jsp");//不能用存储转发
		req.getRequestDispatcher("showFenlei.jsp").forward(req, resp);//页面静态化后用转发器
		return;
	
}

	}
	
	public int getPageNew(HttpServletRequest req){//5.得到当前页
		
		String pageNew=req.getParameter("pageNew");
		
		 if(pageNew==null||pageNew.trim().isEmpty()){//pagenew无或pagenew去空格后为空
			 return 1;//取第1页
		 }
		return Integer.parseInt(pageNew);//转换成int返回
	}

}
