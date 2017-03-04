<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加用户</title>

</head>
<style type="text/css">
html,body {
	margin: 0px;
	height: 100%;
}

body {
	background-color: #EFEFCD;
}

.total {
	margin: auto;
	width: 600px
}

.left {
	float: left;
}

.right {
	float: left;
	margin-left: 40px;
	margin-top: 25px;
}

.texts {
	width: 260px;
	height: 35px;
	margin-top: 20px;
}

.inputStyle1 {
	width: 260px;
	height: 35px;
	margin-top: 10px;
}

.inputStyle2 {
	width: 260px;
	height: 35px;
	margin-top: 10px;
	margin-left: 30px;
}

.inputStyle {
	width: 260px;
	height: 35px;
	margin-top: 13px;
	margin-left: 20px;
	float: left;
}

.inputStyle3 {
	width: 150px;
	height: 35px;
	margin-top: 10px;
	margin-left: 20px;
	float: left;
}

.textBox {
	width: 260px;
	height: 35px;
	margin-top: 10px;
}

.boxs {
	float: left;
	margin-top: 30px;
	margin-left: 25px;
}

.btn {
	border: 0px;
	background-color: #1A73D6;
	border-radius: 2px;
	color: white;
	width: 220px;
	height: 55px;
	float: left;
	margin-left: 5px;
	margin-top: 40px;
	font-size: large;
}

.btn:hover {
	background: #3B73EE;
	cursor: pointer;
}

.btns {
	width: 100%;
	margin: auto;
	overflow: hidden;
}

.heads {
	margin-top: 10px;
	margin-left: 20px;
	font-size: large;
	color: whitesmoke;
	float: left;
}
</style>
<script type="text/javascript" src="js/Toast.js"></script>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
	function jump(path) {
		window.location.href = path;
	}
	function toAddUser() {
		var _data = $("#form0").serialize();
		$.ajax({
			type : 'get',
			url : "addUserServlet?" + _data,
			dataType : "text",
			success : function(result) {
				var _msg = eval("(" + result + ")");
				Toast.showMessage(_msg.result, 2000);
			}
		});
	}
	function toCenter() {
		var marTop = window.innerHeight / 2 - $(".total").height() / 2;
		$(".total").css({
			"margin-top" : marTop + "px"
		});
	}
</script>
<body onload="toCenter()">
	<div class="total">
		<form id="form0">
			<div style="width: 800px;">
				<div>
					<div class="left">
						<div class="texts">
							<input type="text" class="inputStyle2" placeholder="用户名"
								name="name" />
						</div>
						<div class="texts">
							<input type="text" class="inputStyle2" placeholder="密码"
								name="password" />
						</div>
						<div class="texts">
							<input type="text" class="inputStyle2" placeholder="年龄"
								name="age" />
						</div>
						<div class="texts">
							<input type="text" class="inputStyle2" placeholder="email"
								name="email" />
						</div>

					</div>
					<div class="right">
						<div class="texts">
							<select class="inputStyle" placeholder="用户状态" name="status">
								<option value="1">冻结</option>
								<option value="0">正常</option>
							</select>
						</div>
						<div class="texts">
							<input type="text" class="inputStyle" placeholder="余额"
								name="account" />
						</div>
						<div class="texts">
							<select class="inputStyle" placeholder="性别" name="sex">
								<option value="1">男</option>
								<option value="0">女</option>
							</select>
						</div>
					</div>
				</div>
				<div class="btns">
					<input class="btn" type="button" style="margin-left:30px;"
						value="返回菜单" onclick="jump('managerindex.jsp')" /> <input class="btn"
						type="button" value="添加用户" onclick="toAddUser()" />
				</div>
			</div>
		</form>
	</div>
</body>
</html>

