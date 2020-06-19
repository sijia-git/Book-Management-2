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
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="bootstrap/css/bootstrap.css" />
<script type="text/javascript" src="bootstrap/js/jquery.js"></script>
<script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>

<title>图书列表</title>
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="js/ajax.js"></script>
<script>
 
	window.onload = function() {//加载页面
		var chek = document.getElementsByName("ids");

		var selectAll = document.getElementById("selectAll");

		selectAll.onclick = function() {
			//为其绑定一个单击事件，全选

			for (i = 0; i < chek.length; i++) {

				chek[i].checked = true;

			}

		}
		//全不选
		var selectNot = document.getElementById("selectNot");

		selectNot.onclick = function() {

			for (i = 0; i < chek.length; i++) {

				chek[i].checked = false;

			}

		}

		//反选
		var fanxuan = document.getElementById("fanxuan");

		fanxuan.onclick = function() {

			for (i = 0; i < chek.length; i++) {
				if (chek[i].checked == true) {
					chek[i].checked = false;

				} else {
					chek[i].checked = true;

				}

			}

		}

		//删除	  
		var df = document.getElementById("dfd");

		df.onclick = function() {

			var flag = false;

			for (i = 0; i < chek.length; i++) {

				if (chek[i].checked == true) {
					flag = true;
					break;
				}
			}

			if (flag == false) {
				alert("请至少选一项");
				return;

			} else {

				var str = "";

				for (var i = 0; i < chek.length; i++) {

					if (chek[i].checked == true) {

						str += chek[i].value + ",";

					}
				}
				str = str.slice(0, str.length - 1);
				//alert(str);

				var flage = confirm("你确定删除所勾选的记录吗？");
				if (flage == true) {//确定
					window.location.href = "<%=base%>LendServlet?action=delete&ids="+ str + "&pageNew=${pb.pageNew}";

				} else {//取消

					window.location.href = "<%=base%>LendServlet?action=showPasgeLend&pageNew=${pb.pageNew}";

				}
			}
		}

		
		});
	
		
	};
	
	

</script>
<style>
.col {
	width: 1000px;
	height: 500px;
}

#div3 {
	margin-top: 10px;
	width: 1000px;
	height: 520px;
	margin-left: 10px;
}

#div2 {
	margin-left: 10px;
}

#div1 {
	height: 520px;
	background-image: url("tu/t6.jpg");
	background-size: cover;
}

#li {
	color: #337AB7;
	font-size: 26px;
}

#tb2 {
	width: 800px;
}

.r2 {
	margin-top: 3px;
}

#tb2 {
	margin-top: 5px;
	width: 860px;
}

#f1 {
	color: #337AB7;
	width: 400px;
}

.ss {
	margin-left: 40%;
}
</style>
</head>
<body>

	<div class="container-fluid" id="div1">
		<c:if test="${!empty mag }">
			<script>
			   alert("${mag }");
		    </script>
		    <c:remove var="mag" scope="session"/>
		</c:if>
		
		<div class="col col-md-5 " id="div2">
			<ul class="nav nav-tabs">
				
				<li><a href="<%=base%>addlend.jsp">添加借阅记录&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				</a></li></ul>
			<div class="container-fluid " id="div3">
				<table id="t">
					<tr height="4%">
						<td align="center" colspan=2><br> <font size="5"
							color="#337AB7" face="宋体"><strong>借阅记录列表</strong></font> 
					</tr>

					<tr align="center">
						<td>
							<table class="table table-bordered table-hover " cellspacing="0"
								cellpadding="20" id="tb2">
								<tr align="center">
									<td>图书编号</td>
									<td>ID</td>
									<td>分类名称</td>
									<td>图书名称</td>
									<td>图书价格</td>
									<td>图书出版社</td>
									<td>读者</td>
									<td>借书时间</td>
									<td>还书时间</td>
									<td><button id="dfd">删除</button></td>
									<td>修改</td>
								</tr>
								<c:forEach items="${pb.beanList }" var="s" varStatus="ss">
									<tr align='center'>
										<td>${ss.index+1}</td>
										<td>${s.id}</td>
										<td>${s.flname}</td>
										<td>${s.name}</td>
										<td>${s.money}</td>
										<td>${s.press}</td>
										<td>${s.reader}</td>
										<td>${s.lend_date}</td>
										<td>${s.back_date}</td>
										<td><input type="checkbox" name="ids" value="${s.id}" /></td>
										<td><a
											href='<%=base %>LendServlet?action=showOne&id=${s.id }&pageNew=${pb.pageNew }'>
												<input type="button" value="修改" class="btn btn-info btn-sm" />
										</a></td>
									</tr>
								</c:forEach>
							</table>

               
				<c:if  test="${showPesge=='gao'}" > 
							<p>第${pb.pageNew }页/共${pb.pages}页 
							<ul class="pagination ">

								<li><a
									href="${pb.url }&pageNew=1">首页</a>
									</li>
								<c:if test="${pb.pageNew>1 }">
									<li><a aria-label="Previous"
										href="${pb.url }&pageNew=${pb.pageNew-1 }"><span
											aria-hidden="ture">&laquo;</span></a></li>
								</c:if>

								<!-- 分页2种情况
			               1.页数小于10
			                   2.页数大于10
			                         -->

								<c:choose>
									<c:when test="${pb.pages<=10 }">
										<c:set var="begin" value="1"></c:set>
										<c:set var="end" value="${pb.pages }"></c:set>
									</c:when>
									<c:otherwise>
										<c:set var="begin" value="${pb.pageNew-5 }"></c:set>
										<c:set var="end" value="${pb.pageNew+4 }"></c:set>
										<c:if test="${begin<=1 }">
											<c:set var="begin" value="1"></c:set>
											<c:set var="end" value="10"></c:set>
										</c:if>
										<c:if test="${end>=pb.pages }">
											<c:set var="begin" value="${pb.pages-9 }"></c:set>
											<c:set var="end" value="${pb.pages}"></c:set>
										</c:if>

									</c:otherwise>
								</c:choose>
								<!-- 每页面显示10页数 -->

								<c:forEach begin="${begin }" end="${end }" var="i">
									<c:choose>
										<c:when test="${pb.pageNew==i }">
											<li class="active"><span>${i }</span></li>
										</c:when>
										<c:otherwise>
											<li><a
												href="${pb.url }&pageNew=${i}">${i }</a>
											</li>
										</c:otherwise>
									</c:choose>
								</c:forEach>


								<c:if test="${pb.pageNew<pb.pages }">
									<li><a
										href="${pb.url }&pageNew=${pb.pageNew+1 }"
										aria-label="Previous"><span aria-hidden="ture">下一页</span></a>
									</li>
								</c:if>

								 
								<li><a
									href="${pb.url }&pageNew=${pb.pages}">尾页
								</a>
								</li>
								</p>
				
							</ul>
					  </c:if>
					  <c:if test="${showPesge=='lend'}"> 
								<p>第${pb.pageNew }页/共${pb.pages } 页
							<ul class="pagination ">

								<li><a
									href="<%=base%>LendServlet?action=showPasgeLend&pageNew=1">首页</a>
									</li>
								<c:if test="${pb.pageNew>1 }">
									<li><a aria-label="Previous"
										href="<%=base %>LendServlet?action=showPasgeLend&pageNew=${pb.pageNew-1 }"><span
											aria-hidden="ture">&laquo;</span></a></li>
								</c:if>

								<!-- 分页2种情况
			               1.页数小于10
			                   2.页数大于10
			                         -->

								<c:choose>
									<c:when test="${pb.pages<=10 }">
										<c:set var="begin" value="1"></c:set>
										<c:set var="end" value="${pb.pages }"></c:set>
									</c:when>
									<c:otherwise>
										<c:set var="begin" value="${pb.pageNew-5 }"></c:set>
										<c:set var="end" value="${pb.pageNew+4 }"></c:set>
										<c:if test="${begin<=1 }">
											<c:set var="begin" value="1"></c:set>
											<c:set var="end" value="10"></c:set>
										</c:if>
										<c:if test="${end>=pb.pages }">
											<c:set var="begin" value="${pb.pages-9 }"></c:set>
											<c:set var="end" value="${pb.pages}"></c:set>
										</c:if>

									</c:otherwise>
								</c:choose>
								<!-- 每页面显示10页数 -->

								<c:forEach begin="${begin }" end="${end }" var="i">
									<c:choose>
										<c:when test="${pb.pageNew==i }">
											<li class="active"><span>${i }</span></li>
										</c:when>
										<c:otherwise>
											<li><a
												href="<%=base %>LendServlet?action=showPasgeLend&pageNew=${i}">${i }</a>
											</li>
										</c:otherwise>
									</c:choose>
								</c:forEach>


								<c:if test="${pb.pageNew<pb.pages }">
									<li><a
										href="<%=base %>LendServlet?action=showPasgeLend&pageNew=${pb.pageNew+1 }"
										aria-label="Next"><span aria-hidden="ture">&raquo;</span></a>
									</li>
								</c:if>

								 
								<li><a
									href="<%=base %>LendServlet?action=showPasgeLend&pageNew=${pb.pages}">尾页
								</a>
								</li>
								</p>
				
							</ul> 
					  </c:if>
				 
					  <br>

						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
</body>

</html>