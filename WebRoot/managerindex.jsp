<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>后台管理</title>
<script type="text/javascript" src="js/Toast.js"></script>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<style>
body {
	background-color: #FFE7DE;
}

.le-item {
	transition: background-color 0.4s, width .4s;
	width: 300px;
	height: 200px;
	display: inline-block;
	background: #44B4C2;
	color: #FFF;
	border: 0px;
	cursor: pointer;
	font-size: 22px;
	margin: 10px;
}

.le-item:hover {
	width: 320px;
	box-shadow: 0px 0px 13px #999;
	background: #FFF;
	color: #44B4C2;
}

.menu {
	width: 670px;
	height: 500px;
	left: 300px;
	top: 100px;
	margin: auto;
}
</style>
<script>
	function jump(path) {
		window.location.href = path;
	}
	$(document).ready(function(){
		var hei = window.innerHeight/2-$(".menu").height()/2;
		$(".menu").css("padding-top",hei+"px");
	});
</script>
</head>
<body>
	<div class="menu">
		<button class="le-item" onclick="jump('ManagerAddUser.jsp')">添加用户</button>
		<button class="le-item" onclick="jump('ManagerUpdateUser.jsp')">更新用户</button>
		<button class="le-item" onclick="jump('ManagerAddBook.jsp')">添加书籍</button>
		<button class="le-item" onclick="jump('ManagerUpdateBook.jsp')">管理书籍</button>
	</div>
</body>
</html>

