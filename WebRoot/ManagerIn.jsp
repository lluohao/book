<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理员界面</title>
</head>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<style type="text/css">
html,body {
	margin: 0px;
	height: 100%;
}

body {
	background: url("img/Library.jpg");
	background-size: 100% 100%;
}

.btn0 {
	border: 0px;
	background-color: #070506;
	border-radius: 2px;
	color: white;
	width: 120px;
	height: 45px;
	float: left;
	font-size: large;
}

.btn1 {
	border: 0px;
	background-color: #47656E;
	border-radius: 2px;
	color: #FCA87C;
	width: 120px;
	height: 45px;
	float: left;
	margin-left: 25px;
	font-size: large;
}

.btn1:hover {
	cursor: pointer;
}

.btn0:hover {
	cursor: pointer;
}

.btns {
	position: absolute;
	left: 295px;
	top: 470px;
}

.btns2 {
	position: absolute;
	left: 295px;
	top: 530px;
}

.btns3 {
	position: absolute;
	left: 295px;
	top: 465px;
}
</style>
<body>
	<div>

		<div class="btns2">
			<a href="ManagerAddBook.jsp"><input type="button"
				value="AddBook" style="background-color:#47656E;" class="btn0" /></a> <a
				href="ManagerUpdateBook.jsp"><input type="button"
				value="UpdateBook" style="background-color:#070506;" class="btn1" /></a>
		</div>
		<div class="btns3">
			<a href="ManagerAddUser.jsp"><input type="button"
				value="AddUser" style="background-color:#070506;" class="btn0" /></a> <a
				href="ManagerUpdateUser.jsp"><input type="button"
				value="UpdateUser" style="background-color:#47656E;" class="btn1" /></a>
		</div>
	</div>
</body>
</html>
