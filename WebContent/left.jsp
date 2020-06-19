<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>左侧</title>
<link href="jquery-ui.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="jquery-ui.js"></script>
<script type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		$("#accordion").accordion();
	})
</script>
<style type="text/css">
li {
	list-style-type: none;
	padding-top: 9px;
}

a {
	text-decoration: none;
}
h3{
     color: #613A0C;
}

#accordion {
	width: 210px;
	height: 480px;
	margin-left: 0% ;
	margin-top: 10px;
}
 
</style>

</head>
 
<div  class="container-fluid ">
<div class="col col-md-5 col-md-offset-2">
	<div id="accordion"  >
		

         <h3>
			<font size="5" face="楷体" color="#337AB7" ><b>用户详情</b></font>
		</h3>

		<div>

			<li><a href="showAdmin.jsp" target="right"><font size="5" face="楷体"
					color="#337AB7">用户详情</font></a></li>
			<li><a href="changeMm.jsp" target="_top" ><font size="5" face="楷体"
					color="#337AB7">修改密码</font></a></li>
			<li><a href="AdminServlet?action=exit" target="_parent"><font size="5" face="楷体"
					color="#D93E5C">退出系统</font></a></li>

		</div>
		 <h3>
			<font size="5" face="楷体" color="#337AB7" ><b>分类管理</b></font>
		</h3>
		<div>
			
			<li><a href="FenleiServlet?action=showPasgefl" target="right"><font
					size="5" face="楷体" color="#337AB7">分类列表</font></a></li>
			<li> <a href="addfl.jsp" target="right"><font size="5" face="楷体"
					color="#337AB7">添加分类</font></a></li>
			<li><a href="AdminServlet?action=exit" target="_parent"><font size="5" face="楷体"
					color="#D93E5C">退出系统</font></a></li>

		</div>
		<h3>
			<font size="5" face="楷体" color="#337AB7" ><b>图书管理</b></font>
		</h3>

		<div id="book2">
		    <li><a href="BookServlet?action=showPasgeBook" target="right"><font
					face="楷体" size="5" color="#337AB7">图书列表</font></a></li>
		    <li> <a href="addbook.jsp" target="right"><font size="5" face="楷体"
					color="#337AB7">添加图书</font></a></li>
			<li><a href="AdminServlet?action=exit" target="_parent"><font size="5" face="楷体"
					color="#D93E5C">退出系统</font></a></li>
         

		</div>
        
		<h3>
			<font size="5" face="楷体" color="#337AB7" ><b>读者管理</b></font>
		</h3>

		<div id="user2">
            <li><a href="UserServlet?action=showPasgeUser" target="right"><font
					face="楷体" size="5" color="#337AB7">读者列表</font></a></li>
			<li> <a href="adduser.jsp" target="right"><font size="5" face="楷体"
					color="#337AB7">添加读者</font></a></li>
			<li><a href="AdminServlet?action=exit" target="_parent"><font size="5" face="楷体"
					color="#D93E5C">退出系统</font></a></li>
			

		</div>
        <h3>
			<font size="5" face="楷体" color="#337AB7" ><b>借阅管理</b></font>
		</h3>

		<div id="user2">
            <li><a href="LendServlet?action=showPasgeLend" target="right"><font
					face="楷体" size="5" color="#337AB7">借阅列表</font></a></li>
			<li> <a href="addlend.jsp" target="right"><font size="5" face="楷体"
					color="#337AB7">添加记录</font></a></li>
			<li><a href="AdminServlet?action=exit" target="_parent"><font size="5" face="楷体"
					color="#D93E5C">退出系统</font></a></li>
			
			

		</div>
		
        
	</div>
	</div>
	</div>
	
</body>
</html>