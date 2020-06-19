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

<title>添加图书</title>

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

							message : '图书名不能为空'
						},
						stringLength : {
							min : 1,
							max : 10,
							message : '书名长度小于20'
						},

						
						remote : {//ajax验证。
							url : "BookServlet",//验证地址
							delay : 2000,
							type : 'POST',//请求方式

							//自定义提交数据，默认值提交当前input value
							data : function(validator) {
								return {
									action : "yanzheng",
									name : $("#name").val(),
									flname : $("#flname").val()
								}
							}

						}

					}

				},
				money : {

					validators : {

						notEmpty : {

							message : '价格不能为空'
						}
					}

				},
				flname : {

					validators : {

						callback : {

							callback : function(value, validator) {

								if (flname.value == "-请选择分类-") {
									return {
										valid : false,

										message : '必须选择分类',
									}

								}
								return true;

							}
						}
					}

				},
				press : {
					validators : {

						notEmpty : {

							message : '出版社不能为空'
						}
					}
				},
				state : {
					validators : {
						
						notEmpty : {

							message : '请选择是否借出'
						}
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
hr{
border: 1px solid #337AB7;
}
#div9{
	background-image: url("tu/ta.png");
	background-size:cover;
	height: 520px;
}
 
</style>
<body  >
	<div class="container-fluid"  id="div9">
		<!--  <marquee align="texttop" behavior="slide" scrollamount="60"
			direction="up">-->
			<div class="col col-md-5 col-md-offset-2" id="div1">

				<h1 class="text-center text-info">添加图书</h1>
             <hr >
				<form action="BookServlet?action=add" method="post" name="register"
					id="fm" class="form-horizontal">

					<div class="form-group">
						<label class="col-sm-3 col-sm-offset-2 control-label text-info">选择分类:</label>
						<div class="col-sm-5">
						<!-- 下拉列表 -->
							<select name="flname" id="flname"
								style="color: #265C88; font-size: 14px;" class="form-control input-sm">
								<option>-请选择-</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label col-sm-offset-2 text-info">图书名称:</label>
						<div class="col-sm-5">
							<input type="text" name="name" class="form-control input-sm" id="name" />
						</div>

					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label col-sm-offset-2 text-info">图书价格:</label>
						<div class="col-sm-5">
							<input type="text" name="money" class="form-control input-sm" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label col-sm-offset-2 text-info">出版社:</label>
						<div class="col-sm-5">
							<input type="text" name="press" class="form-control input-sm" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label col-sm-offset-2 text-info">借阅状态:</label>
						<div class="col-sm-2 radio-inline">
							<label><input type="radio" name="state" value="未借" />未借
							</label>
						</div>
						<div class="col-sm-2  radio-inline">
							<label><input type="radio" name="state" value="已借" />已借</label>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-3 control-label col-sm-offset-2 text-info">借阅者:</label>
						<div class="col-sm-5">
							<input type="text" value="无" name="reader" class="form-control input-sm" />
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-2  col-sm-offset-4 ">
							<button type="submit" class="btn btn-success">
								添加 <span class="glyphicon glyphicon-ok"></span>
							</button>
						</div>
						<div class="col-sm-2  col-sm-offset-1">
							<button type="reset" class="btn btn-info">
								重置 <span class="glyphicon glyphicon-repeat"></span>
							</button>
						</div>
					</div>
                  <br>
				</form>

			</div>

		<!-- </marquee>-->
	</div>
</body>
</html>