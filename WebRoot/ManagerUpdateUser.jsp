<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>编辑用户</title>
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

.btns {
	width: 800px;
	margin-left: 0px;
	overflow: hidden;
}

.heads {
	margin-top: 10px;
	margin-left: 20px;
	font-size: large;
	color: whitesmoke;
	float: left;
}

.btn01 {
	border: 0px;
	background-color: #1A73D6;
	border-radius: 2px;
	color: white;
	width: 120px;
	height: 40px;
	float: left;
	margin-left: 25px;
	margin-top: 10px;
	font-size: large;
}

.text01 {
	width: 260px;
	height: 35px;
	margin-top: 10px;
}
</style>
<script type="text/javascript" src="js/Toast.js"></script>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
	function jump(path) {
		window.location.href = path;
	}
	function toSearchUser() {
		var userName = $("#find-userName").val();
		$.ajax({
			type : 'get',
			url : "findUserServlet?userName=" + userName,
			dataType : "text",
			success : function(result) {
				var _msg = eval("(" + result + ")");

				if (_msg.user.id == "") {
					Toast.showMessage("找不到用户", 2000);
				} else {
					$("#name").val(_msg.user.name);
					$("#id").val(_msg.user.id);
					$("#age").val(_msg.user.age);
					$("#email").val(_msg.user.email);
					$("#status").val(_msg.user.status);
					$("#account").val(_msg.user.account);
					$("#sex").val(_msg.user.sex);
				}
			}
		});
	}

	function toUpdateUser() {
		var id = $("#id").val();
		if (id == "") {
			Toast.showMessage("请先查询用户", 2000);
		} else {
			var _data = $("#form0").serialize();
			$.ajax({
				type : 'get',
				url : "updateUserServlet?" + _data + "&id=" + id,
				dataType : "text",
				success : function(result) {
					var _msg = eval("(" + result + ")");
					Toast.showMessage(_msg.result, 2000);
				}
			});
		}
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
		<form>
			<div style="margin-left: 10px;">
				<input type="text" class="text01" id="find-userName" /> <input
					type="button" class="btn01" value="查询" onclick="toSearchUser()" />
			</div>
		</form>
		<form id="form0">
			<div style="width: 800px;">
				<div>
					<div class="left">
						<div class="texts">
							<input type="text" class="inputStyle2" placeholder="ID" id="id"
								name="id" disabled="disabled" />
						</div>
						<div class="texts">
							<input type="text" class="inputStyle2" placeholder="用户名"
								name="name" id="name" />
						</div>
						<div class="texts">
							<input type="text" class="inputStyle2" placeholder="年龄"
								name="age" id="age" />
						</div>
						<div class="texts">
							<input type="text" class="inputStyle2" placeholder="email"
								name="email" id="email" />
						</div>
					</div>
					<div class="right">
						<div class="texts">
							<select class="inputStyle" placeholder="用户状态" name="status"
								id="status">
								<option value="1">冻结</option>
								<option value="0">正常</option>
							</select>
						</div>
						<div class="texts">
							<input type="text" class="inputStyle" placeholder="余额"
								name="account" id="account" />
						</div>
						<div class="texts">
							<select class="inputStyle" placeholder="性别" name="sex" id="sex">
								<option value="1">男</option>
								<option value="0">女</option>
							</select>
						</div>
					</div>
				</div>
				<div class="btns">
					<input class="btn" type="button" style="margin-left:30px;"
						value="返回菜单" onclick="jump('managerindex.jsp')" /> <input
						class="btn" type="button" value="编辑用户" onclick="toUpdateUser()" />
				</div>
			</div>
		</form>
	</div>
</body>
</html>

