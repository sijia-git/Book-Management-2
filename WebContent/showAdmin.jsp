<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="bootstrap/css/bootstrap.css" />
<script type="text/javascript" src="bootstrap/js/jquery.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>
<link rel="stylesheet" href="bootstrap/css/bootstrapValidator.css" />
<script type="text/javascript" src="bootstrap/js/bootstrapValidator.js"></script>

<title>管理员详情</title>
<script type="text/javascript" src="js/ajax.js"></script>
<script type="text/javascript">
	window.onload = function() {//遍历管理员信息
		ajax({
			method : "POST",
			url : "AdminServlet",
			params : "action=showAdmin",
			type : "json",//传的数据为json
			success : function(content) {
				var admin = content;
				 
				var id = document.getElementById("id");
				id.value = admin.id;

				var name = document.getElementById("name");
				name.value = admin.name;

				var password = document.getElementById("password1");
				password.value = admin.password;

				var phone = document.getElementById("phone");
				phone.value = admin.phone;

				var username = document.getElementById("username");
				username.value = admin.username;
				
				var touxiang= document.getElementById("touxiang");
				var img=document.createElement("img");
				img.src="/img"+admin.touxiang;
				img.width=180;
				img.height=180;
				touxiang.appendChild(img);
				 
			}
		});
	};
</script>
<style>

body {
	margin: 0;
}

.icon {
	width: 1em;
	height: 1em;
	vertical-align: -0.15em;
	fill: currentColor;
	overflow: hidden;
	margin-top: 10px;
}

.footer {
	color: #777;
	border-top: 1px solid #e5e5e5;
	text-align: center;
	padding: 30px 0;
}

#div1 {
	height: 80px;
}

.bg {
	background: url("tu/ta.png") ;
	background-size: cover;
	height: 520px;
	padding-top: 90px;
}

#fr{
	border: 2px solid #E9445C;
	border-style: groove;
	 
}

h2 {
	font-size: 34px;
}

label {
 
	font-size: 16px;
}

.btn-danger {
	background-color: #E9445C;
}

a {
	text-decoration: none;
	color: #FFFFFF;
}
 
 
 
</style>
</head>
<body  >
<div class="container-fluid"  id="div9"   >
	
<div class="col col-md-5 col-md-offset-3" id="div1">
    <!-- 表格 -->
    <h1 class="text-center text-info"><font color="#337AB7" size="6" face="宋体" align="center"><b>用户详情</b></font></h1>
	<table bgcolor="#993300" border="4" align="center" width="500px" class="table table-bordered table-hover"
		height="300px" cellspacing="0">
		<tr align="center" >
			<td ><br>头像：</td>
			<td id="touxiang">
			</td>
		</tr>
		<tr align="center">
			<td>编号：</td>
			<td><input type="text" id="id" /></td>
		</tr>
		<tr align="center">
			<td>姓名：</td>
			<td><input type="text" id="name" /></td>
		</tr>
		<tr align="center">
			<td>账号：</td>
			<td><input type="text" id="username" /></td>
		</tr>

		<tr align="center">
			<td>密码：</td> 
			<td><input type="password" disabled="disabled" id="password1" /></td>
		</tr>
		<tr align="center">
			<td>手机：</td>
			<td><input type="text" id="phone" /></td>
		</tr>
	</table>
	</div>
	</div>
</body>
</html>