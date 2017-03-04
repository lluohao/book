<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>皓叶登录</title>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/Toast.js"></script>
<script type="text/javascript" src="js/jqeury-md5.js"></script>
<style>
body {
	height: 100%;
	background: #F9f9f9;
	margin: 0px;
}

#content {
	width: 850px;
	margin: auto;
	background: #FFF;
}

#div-login {
	padding: 80px 0px;
}

#div-register {
	padding: 30px 0px;
	display: none;
}

#logo {
	text-align: center;
}

#mesg-login {
	font-size: 30px;
	color: #424242;
	font-weight: normal;
}

.center {
	text-align: center;
}

.input-primay {
	width: 350px;
	height: 40px;
	border: 1px solid #AAA;
	padding: 5px 10px;
	margin-bottom: 20px;
}

.btn-primay {
	width: 370px;
	height: 50px;
	background: #EF5B00;
	border: 0px;
	color: #FFF;
	margin-top: 15px;
	cursor: pointer;
}

.line {
	width: 400px;
	margin: auto;
	background: #999;
	height: 1px;
	margin-top: 50px;
}

#div-login {
	display: block;
}

#mesg-register {
	font-size: 35px;
	color: #424242;
	font-weight: normal;
	position: relative;
	top: -17px;
}
}
</style>
<script>
			var preURL = "indexServlet";
			
			$(document).ready(toCenter);
			function toCenter() {
				var marTop = window.innerHeight / 2 - $("#content").height() / 2;
				$("#content").css({
					"margin-top": marTop + "px"
				});
			}
			function showDiv() {
				console.log($("#div-register").text().trim().length);
				if($("#div-register").text().trim().length <= 0) {
					$.ajax({
						type: "get",
						url: "register.html?d=" + Math.random(),
						async: true,
						success: function(e) {
							$("#div-register").html(e);
							showDiv();
						}
					});
					return;
				}
				$("#content").fadeOut(200, function() {
					$("#div-login").toggle();
					$("#div-register").toggle();
					toCenter();
					$("#content").fadeIn(200);
				});
			}
			function login() {
				var name = $("#login-name").val();
				if(name.length <= 2 || name.length > 15) {
					Toast.showMessage("请输入正确的用户名", 2000);
					return;
				}
				var pwd = $("#login-pwd").val();
				if(pwd.length <= 5 || pwd.length > 16) {
					Toast.showMessage("请输入密码", 2000);
					return;
				}
				$("#btn-login").val("正在登录...");
				$.ajax({
					type: "post",
					url: "./login",
					data: "name=" + name + "&pwd=" + $.md5(pwd),
					async: true,
					success: function(result) {
						result = eval("(" + result + ")");
						if(result.code == 200 && result.id > 0) {
							window.location.href=preURL;
						} else {
							Toast.showMessage("用户名密码不匹配", 2000);
						}
						$("#btn-login").val("立即登录");
					},
					error: function() {
						Toast.showMessage("网络连接失败！", 3000);
						$("#btn-login").val("立即登录");
					}
				});
			}
		</script>
</head>

<body>
	<div id="content">
		<div id="div-login">
			<div id="logo">
				<img src="img/icon.png" width="60px" />
				<p id="mesg-login">皓叶账号登录</p>
			</div>
			<div class="center">
				<input type="text" placeholder="用户名" class="input-primay"
					id="login-name" />
			</div>
			<div class="center">
				<input type="password" placeholder="密码" class="input-primay"
					id="login-pwd" />
			</div>
			<div class="center">
				<input type="submit" class="btn-primay" value="立即登录" id="btn-login" onclick="login()" />
			</div>
			<div class="line"></div>
			<div class="center" style="margin-top: 40px;">
				<a href="javascript:showDiv()"
					style="margin-right: 20px;color: #777;text-decoration: none;">注册皓叶账号</a>
				<a href="forgetPwd.html" style="text-decoration: none;color: #777;">忘记密码？</a>
			</div>
		</div>
		<div id="div-register"></div>
	</div>
</body>
</html>