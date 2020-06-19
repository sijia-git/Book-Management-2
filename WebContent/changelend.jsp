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
<title>修改借阅记录</title>
 
<script type="text/javascript" src="js/ajax.js"></script>
<script>
 
$(function() {
	
	var flname = document.register.flname;

	ajax({
		method : "POST",
		url : "FenleiServlet",
		params : "action=showOne2",
		type : "json",
		success : function(content) {

			for (var i = 0; i < content.length; i++) {

				var name = content[i];

				var opt = document.createElement("option");
				opt.value = name.name;
				opt.innerHTML = name.name;
				flname.appendChild(opt);

			}

		}
	});

		$("#fm").bootstrapValidator({

			feedbackIcons : {
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'

			},
			fields : {
				name : {
					validators : {

						notEmpty : {

							message : '书名不能为空'
						},
						
					}
				},
				back_date: {
			        validators: {
			        	notEmpty : {

							message : '日期不能为空'
						},
					 
						date : {
							format : 'YYYY-MM-DD',
							message : '日期格式不正确'
						}
			        }
			    }

		})
	});
</script>
<style>
#div1 {
 
	margin-top: 20px;
 
}

form {
	margin-top: 20px;
}


.btn {
	margin-top: 30px;
}
h3{
margin-top: 30px;
}
label{
   font-size: 16px;
}
.zt{
 font-size: 10px;
}
hr{
border: 1px solid #337AB7;
}
#div9{
	background-image: url("tu/t7.jpg");
	background-size: cover;
	height: 600px;
}
</style>
<body  >

<div class="container-fluid"  id="div9">
		<c:if test="${!empty mag }">
		<script>
			alert("${mag}");
		</script>
	</c:if>
	<c:remove var="mag" />
	 	 
	<div class="col col-md-5 col-md-offset-2" id="div1">

		<h1 class="text-center text-info">修改还书时间</h1>
		<hr>

		<form action="LendServlet?action=update2" method="post" id="fm"
			name="register"class="form-horizontal">
		
                       <div class="form-group">
								<label class="col-sm-3 control-label col-sm-offset-2 text-info">书名:</label>
								<div class="col-sm-6">
									<input type="text" name="back_date"   value="${fl.name }"class="form-control input-sm" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label col-sm-offset-2 text-info">还书时间:</label>
								<div class="col-sm-6">
									<input type="date" name="back_date"   value="${fl.back_date }"class="form-control input-sm" />
								</div>
							</div>
						<div class="form-group">
						<div class="col-sm-2 col-sm-offset-5">
							<button type="submit" class="btn   btn-success">
								修改 <span class="glyphicon glyphicon-cog"></span>
							</button>
						</div>

						<div class="col-sm-2  col-sm-offset-1">

							<a href="LendServlet?action=showPasgeLend&pageNew=${pageNew }"
								class="btn btn-info ">返回 <span
								class="glyphicon glyphicon-repeat"></span></a>

						</div>
					</div>
		</form>
					</div>
			</div>
</body>

</html>