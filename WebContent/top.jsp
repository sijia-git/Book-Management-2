<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String base = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/"
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <!-- 1.移动设备优先 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 2.引入核心的css文件-->
<link rel="stylesheet" href="bootstrap/css/bootstrap.css" />
<!-- 3.引入jQuery文件-->
<script type="text/javascript" src="bootstrap/js/jquery.js"></script>
 <!-- 4.引入BootStrap的核心JS文件-->
<script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>
<link rel="stylesheet" href="bootstrap/css/bootstrapValidator.css" />
<script type="text/javascript" src="iconfont/iconfont.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrapValidator.js"></script>

<title>顶层</title>
<style type="text/css">

 h4{
 margin-top: 30px;
 }
 </style>
</head>
<body> 
	<!-- -->
	 <div class="container-fluid">
	<div class="row header" id="divt">
			<!-- 上 -->		 
				<div class="col col-md-7 ">
				  <font size="7" color=pink face=华文新魏><b>HBBの</b></font> <font size="7" color="#337AB7" face=华文新魏><b>图书管理系统</b></font>
				</div>
					<div  class="col col-md-3 ">
                 <h4 id="userlogin">
                  <c:if test="${empty username}"> 
						<a href='<%=base %>login.jsp' target="_top"><font  size="5px;">请登录  </font></a>
				 </c:if>
                    <c:if test="${!empty username}"> 
						<font class="text-warning" size="5px;"> 欢迎${username} </font>|<a href='<%=base %>exit.jsp' target="_top"><font  size="5px;">退出  </font></a>
				 </c:if>		 
                 </h4>
				</div>
			</div>
 	</div>
</body>
</html>