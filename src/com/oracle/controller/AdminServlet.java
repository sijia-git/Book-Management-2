package com.oracle.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.oracle.domain.Admin;

import com.oracle.factory.AdminServiceFactory;
import com.oracle.util.DBUtilsPlus;

import net.sf.json.JSONObject;

public class AdminServlet extends HttpServlet {//推荐的创建servlet方式
	private static final long serialVersionUID = 1L;

	// 以不知道请求方式（post/get）拿到参数,处理所有的增删改查（单查和全查）操作
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//拿到提交的参数
		String action = req.getParameter("action");		
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		// 创建一个session对象，获取用户输入的数据（全局参数）
		HttpSession session = req.getSession();
		
	 
		if ("login".equals(action)) {// 1.登录
			
		    session.setAttribute("password",password);//若登录成功，把username和password存放在session中			 
			session.setAttribute("username", username);
			System.out.println(username);
			 
			resp.sendRedirect("index.jsp");//重定向时传递数据，可以用seesion不能用request
			return;
			}
			
		 
	    if("yanzhenglg".equals(action)){ //1.登录校验
	    	Admin a = new Admin();//创建admin对象
		    a.setUsername(username);
		    a.setPassword(password);

		    Admin admin = AdminServiceFactory.getAdminServiceImpl().login(a);//把a传进去
		    if (!admin.getUsername().equals(a.getUsername())) {//用户不存在
		    	resp.getWriter().write("{\"valid\":\"false\"}");
		    	return;
		    	}
		    if (!admin.getPassword().equals(a.getPassword())) {//数据库中存放的密码和输入的用户密码不一致，密码错误
		    	resp.getWriter().write("{\"valid\":\"false\"}");//返回一个validator，值为false
		    	return;
		    	} else {//登陆成功
		    		resp.getWriter().write("{\"valid\":\"true\"}");
		    		return; 
		    	}
		    }
	    
	    
	    if("addadmin1".equals(action)){// 2.注册
	    	//1.创建1个部件工厂
	    	DiskFileItemFactory factory = new DiskFileItemFactory();
	    	//2.创建一个解析器
		    ServletFileUpload upload = new ServletFileUpload(factory);
		    try {//3.解析请求对象，返回一个list(部件）
		    	List<FileItem> list = upload.parseRequest(req);
		    	//4.通过下标拿到普通部件（name和value）
			    FileItem fileItem = list.get(0);//姓名
			    String name1 = fileItem.getString("utf-8");//返回input的value值
			    
			    FileItem fileItem1 = list.get(1);//电话
			    String phone1 = fileItem1.getString("utf-8");

			    FileItem fileItem2 = list.get(2);//昵称
			    String username1 = fileItem2.getString("utf-8");

			    FileItem fileItem4 = list.get(4);//密码
			    String password1 = fileItem4.getString("utf-8");
                //文件部件
			    FileItem fileItem3 = list.get(3);//头像
			    String filename = fileItem3.getName();//拿到需要上传的文件名
			    
			    // 处理文件名（IE：全名，含盘符；非IE：只是文件名）
			    int index = filename.lastIndexOf("\\");//转义，找到\最后出现的位置
			    if (index != -1) {//index=-1:非IE(没有\),index!=-1:IE,需要截取
			    	filename = filename.substring(index + 1);//从index的下一个开始截取（不要\,要a.png)
			    	}
			    
			    
			    // 拿到要上传的目录
			    String path = new File("E:\\JAVAWEB\\upload").getAbsolutePath();
			    
			    // 目录打散,方便查找，按照哈希算法打散
			    int hashCode = filename.hashCode();//把文件名转换成hash码 
			    //System.out.println(hashCode);
			    String hex = Integer.toHexString(hashCode);//把hash码转换成16进制
			    //System.out.println(hex);
			    char c1 = hex.charAt(0);//切割前两个字符生成目录
			    char c2 = hex.charAt(1);
			    path = path + "/" + c1 + "/" + c2;
			    File file = new File(path);//创建文件夹
			    file.mkdirs();//创建多级目录
			    //上传同样的文件名，则出现覆盖
			    //UUID.randomUUID().toString()：随机产生一个32位的字符串，且永远不会重复
			    String realName = UUID.randomUUID().toString() + "_" + filename;//拼接
			    String savepath = "/" + c1 + "/" + c2 + "/" + realName;//存放到数据库（目录+文件名）
			    File file1 = new File(file, realName);
			    fileItem3.write(file1);//上传
			    
			    
			   Admin a = new Admin(name1, username1, password1, phone1, savepath);
			   int i = AdminServiceFactory.getAdminServiceImpl().addAdmin(a);
			   if (i > 0) {//注册成功，跳转到登录页面
				   session.setAttribute("mag", "注册成功");
				   resp.sendRedirect("login.jsp");
				   return;}
			   else {//注册失败，跳转到注册界面
				   session.setAttribute("mag", "注册失败");
				   resp.sendRedirect("addAdmin1.jsp");
				   }
			   } 
		    catch (FileUploadException e) {
		    	// TODO Auto-generated catch block
				e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					}
		    }
	    if("yanzheng".equals(action)){// 2.注册用户名校验：管理员用户注册，用户名是否存在
	    	int a = AdminServiceFactory.getAdminServiceImpl().yanzheng(username);
		    if (a == 0) {
		    	resp.getWriter().write("{\"valid\":\"true\"}");
			    return;
			    } else {
			    	resp.getWriter().write("{\"valid\":\"false\"}");
			    	return;
			    	}
		    }
	   
	    if("showAdmin".equals(action)){//3.显示管理员信息
	    	//指定响应的编码
	    	resp.setContentType("text/html;charset=utf-8");	                                                                                                                                                                                                                                                                                                                                                   
	    	
	    	//取出session里的username,查询这个人的信息
		    String un = (String) session.getAttribute("username");
		    
		    //查出来的Adimn为JavaBean
		    Admin s = AdminServiceFactory.getAdminServiceImpl().showAdmin(un);
		    
		    //把Admin转换成json，json不能直接发
		    JSONObject jsonObject = JSONObject.fromObject(s);
		    
		    //把json转换成字符串，以输出流的形式发送出去
		    resp.getWriter().write(jsonObject.toString());
		    return;
		    }

	   

	    if("yanzhengmm".equals(action)){// 6.密码校验：验证原密码是否正确,按照用户名来查
	 
	    	
	    	String pw = (String) session.getAttribute("password");

		    String password2 = req.getParameter("password2");

		    if (pw.equals(password2)) {
		    	resp.getWriter().write("{\"valid\":\"true\"}");
			    return;
			    } else {
			    	resp.getWriter().write("{\"valid\":\"false\"}");
			        return;
			        }
		    }
	    /**
	     * @Description 修改密码
	     */
	    if("updatemm".equals(action)){// 7.修改密码
	    	String newpassword = req.getParameter("newpassword");
	    	String un = (String) session.getAttribute("username");
	    	int a = AdminServiceFactory.getAdminServiceImpl().updatemm(newpassword, un);
	    	if (a > 0) {
	    		session.setAttribute("mag", "密码修改成功,需重新登录！");
	    		resp.sendRedirect("exit.jsp");//重定向到退出界面
	    		return;
	    		} else {
	    			session.setAttribute("mag", "非常抱歉->修改失败了！");
	    			resp.sendRedirect("changeMm.jsp");//重定向到修改密码界面
	    			return;
	    			}
	    	}
	    if("exit".equals(action)){// 退出登录
	    	//1.清空session
	    	session.invalidate();
	    	//2.跳转到登录页面
	    	resp.sendRedirect("login.jsp");
	    }
	}
}