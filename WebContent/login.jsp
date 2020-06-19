<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
      <!-- 引入BootstrapValidator、iconfont的核心css和js文件 -->
      <link rel="stylesheet" href="bootstrap/css/bootstrapValidator.css" />
      <script type="text/javascript" src="iconfont/iconfont.js"></script>
      <script type="text/javascript" src="bootstrap/js/bootstrapValidator.js"></script>

     
     
      <title>登录界面</title>
      <script type="text/javascript">

	    $(function() {
		  $('#login').bootstrapValidator({//选中表单

			feedbackIcons : {//验证图标提示
				valid : 'glyphicon glyphicon-ok',
				invalid : 'glyphicon glyphicon-remove',
				validating : 'glyphicon glyphicon-refresh'

			},
			fields : {//需要验证的字段
				username : {//用户名校验
					validators : {
						notEmpty : {

							message : '用户名不能为空'
						},
						stringLength : {//长度
							min : 2,
							max : 6,
							message : '用户名必须由2-6个汉字组成'
						}
					}
				},
				password : {//密码校验
					validators : {

						notEmpty : {

							message : '密码不能为空'
						},
						
						
						remote : {//ajax验证。server result:{"valid",true or false} 向服务发送当前input name值，获得一个json数据。例表示正确：{"valid",true}  
						url : "AdminServlet",//验证地址
						message : '用户名或密码错误',//提示消息
						delay : 2000,//设置每2秒发送一次ajax请求（默认输入一个字符，提交一次，服务器压力太大）
						type : 'POST',//请求方式，默认为get

						//自定义提交数据，默认值提交当前input value
						data : function(validator) {
							return {
							action : "yanzhenglg",
							username : $("input[name=username]").val(),//带的input中的username数据
							password : $("input[name=password]").val()
						         	}
						        }

	        				}

        				}

        				}
	         		}

	        	});
        	});
      </script>
      <style>
         td {
	           color: #663300;
           	   font-size: 20px;
            }

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
	           height: 0px;
                }

          .bg {
	           background: url("tu/ta.png") no-repeat center;
	           background-size: cover;
	           height: 620px;
	           padding-top: 120px;
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

   <body>
  
	<div class="container-fluid">
		<c:if test="${!empty mag }">
			<script>
				alert("${mag}");
			</script>
			<c:remove var="mag" scope="session"/>
		</c:if>
		
	<div class="row header" id="div1">
			<!-- 上 -->
			<div class="col col-md-10 col-sm-10">
				<div class="col col-md-8  ">
				    <font size="7" color="#EB1B3F" face=华文新魏 ><b>图书管理系统-登录</b></font>
					


				</div>
			</div>
		</div>
		<div class="container-fluid bg">
			<!-- 中 -->
			<div class="col-md-4  col-md-offset-3" id="form">
			<!-- form请求为post -->
				<form id="login" action="AdminServlet?action=login" method="post" class="form-horizontal" >
					<div class="form-group">
					<div class="gl">
						<h2 class="col-sm-8 col-sm-offset-3">
								<font color="#EB1B3F" size="8" face="华文新魏"><b>管理员登录</b></font>
						</h2>
						</div>
					</div>
					<div class="form-group form-group-lg">
						<label for="username" class="col-md-4 control-label"><font
							size="6" color="#EB1B3F" face="华文新魏"><b>用户名:</b></font></label>
						<div class="col-md-8">
							<input type="text"  name="username" placeholder="用户名"
								class="form-control" />
						</div>
					</div>
					<div class="form-group form-group-lg">
						<label for="password" class="col-md-4 control-label"> <font
							size="6" color="#EB1B3F" face="华文新魏"><b>密码:</b></font></label>
						<div class="col-md-8">
							<input type="password"  name="password" placeholder="密码"
								class="form-control" />
						</div>
					</div>

					<div class="form-group">
						<div class="col-md-11 col-md-offset-1">
							<button class="btn btn-danger  btn-md btn-block" type="submit"><font size="4" 
									>登录</font></button>
						</div>
					</div>

					<div class="form-group">
					    <!-- 页面和页面之间的连接用超链接即可，需要带数据时要发给servelt -->
						<a href="addAdmin1.jsp?"><font size="4" color="#EB1B3F" ><center>无法登录？立即注册 </center></font></a>
					</div>
				</form>

			</div>
		</div>

		<footer class="footer">
			<!-- 下 -->

			<h4>
				<font size="3" color="#b1b1b1">HBB的图书管理系统&copy;2025-2030</font>
			</h4>
		</footer>
	</div>
</body>
</html>