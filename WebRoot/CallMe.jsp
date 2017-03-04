<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>About Us</title>
	</head>
	<style type="text/css">
		.attention{
			font-size: 14px;
			text-align: center;
			margin: auto;
		}
		.company{
			margin-left: 30px;
		}
		.CallUs{
			margin-left: 30px;
			margin-top: 30px;
		}
		.c_name{
			font-size: 18px;
			color: red;
			font-weight:bold; 
		}
		.link{
			text-decoration: none;
			color:teal;
		}
		.person{
			margin-top: 20px;
		}
		.call_me{
			margin-left: 30px;
			margin-top: 30px;
		}
	</style>
	<body>
	<%@include file="head.jsp" %>
		<div id="contentDiv"  style="width: 1200px;margin: auto;">
			<div class="attention" ><p>安卓版我们将于2016.8.3号正式发布</p></div>
			<div class="company">
				<span style="font-size: 25px;">商务合作</span><br />
				<div style="margin-top: 10px;">
				<span>
					1<br />
					2<br />
					3<br />
					4
				</span>
				</div>
			</div>
			<div class="CallUs">
				<div class="person">
					<span class="c_name">周俊林（秘书)：</span><br />
					<a class="link" href="">1002587665@qq.com</a>
				</div>
				<div class="person">
					<span class="c_name">彭小蔗(清洁工)：</span><br />
					<a class="link" href="">1002587665@qq.com</a>
				</div>
				<div class="person">
					<span class="c_name">赖明勇(保安)：</span><br />
					<a class="link" href="">1002587665@qq.com</a>
				</div>
			</div>
			<div class="call_me">
				<div style="font-size: 25px;">联系我们</div>
				<div style="margin-top: 10px;">
					<span style="font-size: 14px;color:#663333;">
						电话：<br />
						地址：<br />
						邮编：
					</span>
				</div>
				<div>
					微信：<br />
					<img src="" />
				</div>
			</div>
		</div>
	</body>
</html>