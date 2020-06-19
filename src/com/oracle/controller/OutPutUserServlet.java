package com.oracle.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.oracle.domain.User;
 
import com.oracle.factory.UserServiceFactory;
import com.oracle.util.DownUtils;
 
 

/**
 * Servlet implementation class OutAllUserServlet
 */
public class OutPutUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String action=request.getParameter("action");//拿到action
		List<User> list=null;
		String key="";
		if("all".equals(action)){//若全部导出
			//从数据库中查询出list
		    list=UserServiceFactory.getUserServiceImpl().showUser();	
			key="全部";
		}
		if("outids".equals(action)){
			String ids1=request.getParameter("ids");
			String[] ids=ids1.split(",");
			  list=UserServiceFactory.getUserServiceImpl().showUserByIds(ids);	
			  key="勾选";
		}
		
		    //1.创建一个工作蒲
			HSSFWorkbook workbook=new HSSFWorkbook();
			 
			//2.在工作蒲中创建工作表
			HSSFSheet sheet=workbook.createSheet(key+"用户信息表");
			
			//设置单元格宽度
			 sheet.setColumnWidth(7,20*256);
			 //创建单元格样式对象
			 HSSFCellStyle style=workbook.createCellStyle();
			 style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
			//设置字体样式
		     HSSFFont font=workbook.createFont();
			 font.setBold(true);
			 font.setColor(HSSFColor.DARK_RED.index);  
			 style.setFont(font);
			 
			//3.创建行，并在行中写入数据
			 //第一行是表头
			 String[] title={"编号","姓名","用户名","密码","电话","注册时间","头像"}; //表头要和数据库里的对应
			 HSSFRow row=sheet.createRow(0);//创建第一行		
			 for(int i=0 ;i<title.length;i++){   //遍历数组                                                                                                                                                                                                                 
				 HSSFCell cell=row.createCell(i);//创建单元格
				 cell.setCellStyle(style);
				 cell.setCellValue(title[i]);//写入数据
			 }
			
			 HSSFCellStyle style1=workbook.createCellStyle();
			 style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中
			//设置字体样式
			 
			 //4.把list中的数据放进去
	        for(int i=0;i<list.size();i++){//循环几次创建几行
				 
			HSSFRow row1=sheet.createRow(i+1);//从第2行开始，下标1开始
			User user =list.get(i);//取出一个user对象（一行数据）
			 			
			HSSFCell cell1=row1.createCell(0);//第一列：编号
			cell1.setCellValue(user.getId());
			
			HSSFCell cell2=row1.createCell(1);//第二列：姓名
			cell2.setCellValue(user.getName());
			
			HSSFCell cell3=row1.createCell(2);
			cell3.setCellValue(user.getUsername());
			
			HSSFCell cell4=row1.createCell(3);
			cell4.setCellValue(user.getPassword());
			
			HSSFCell cell5=row1.createCell(4);
			cell5.setCellValue(user.getPhone());
			
			HSSFCell cell6=row1.createCell(5);
			cell6.setCellValue(user.getRegtime());
			
			HSSFCell cell7=row1.createCell(6);
			cell7.setCellValue(user.getTouxiang());
			 
			cell1.setCellStyle(style1);
			cell2.setCellStyle(style1);
			cell3.setCellStyle(style1);
			cell4.setCellStyle(style1);
			cell5.setCellStyle(style1);
			cell6.setCellStyle(style1);
			cell7.setCellStyle(style1);
		 
			 }
	         //此时工作蒲还在内存中，需要将其输出到硬盘中
	        
			 //数据还在内存 把数据输出到文件
			 File f=new File("用户信息表.xls");//把工作蒲的内容输出到excel中			 
			 OutputStream outputStream=new FileOutputStream(f);//此时工作蒲和excel无关，此时输出excel为空
			 //把工作蒲的内容输出到文件
			 workbook.write(outputStream);
			 
			//下载
			//响应浏览器
			 String file=f.getName();//拿到file的名字 
			
			//拿到文件的MIME类型，头1
			String mime=request.getServletContext().getMimeType(file);	
			System.out.print(mime);
			String filename=DownUtils.filenameEncoding(key+file, request);
			 
		    //头2，默认inline,如果要下载需要改成attachment;filename=文件名
			String disposition="attachment;filename="+filename;
			
			
			//把两个头发送给浏览器，设置两个响应头信息，从而告诉浏览器，这个东西需要下载
			response.setHeader("Content-Type", mime);
			response.setHeader("Content-DisPosition",disposition);
			
			//两个流
			//文件加载到内存
			InputStream inputStream=new FileInputStream(file);
			//流输给浏览器
			//拷贝文件，拿到一个输入流、一个输出流，读一个字节写一个字节
			//commons-io中有一个IOUtils,封装了一个方法copy,可以很方便的完成文件的复制
			ServletOutputStream output=response.getOutputStream();//浏览器的out
			IOUtils.copy(inputStream, output);//把文件加载进来再output
			
	 
		
		
	}

}
