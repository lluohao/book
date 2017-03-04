<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>添加书籍</title>
</head>
<script type="text/javascript" src="js/Toast.js"></script>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
function jump(path) {
	window.location.href = path;
}
</script>
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
	margin-left: 60px;
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
	margin-left: 55px;
	margin-top: 40px;
	font-size: large;
}

.btn:hover {
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
	width: 200px;
	font-size: large;
	float: left;
}
</style>
<body>
	<div class="total">
		<form id="form0" action="addBookServlet" enctype="multipart/form-data"
			method="post">
			<div style="width: 800px;">
				<div class="left">
					<div class="texts">
						<input type="text" class="inputStyle2" placeholder="书名"
							id="bookName" name="bookName" />
					</div>
					<div class="texts">
						<input type="text" class="inputStyle2" placeholder="作者"
							name="author" />
					</div>
					<div class="texts">
						<input type="text" class="inputStyle2" placeholder="价格"
							name="price" />
					</div>
					<div class="texts">
						<input type="text" class="inputStyle2" placeholder="折扣"
							name="discount" />
					</div>
					<div class="texts">
						<input type="text" class="inputStyle2" placeholder="类型"
							name="type" />
					</div>
					<div class="texts">
						<input type="text" class="inputStyle2" placeholder="库存"
							name="stock" />
					</div>
				</div>
				<div class="right">
					<div class="texts">
						<input type="text" class="inputStyle" placeholder="status"
							name="status" />
					</div>
					<div class="texts">
						<input type="text" class="inputStyle" placeholder="简述"
							name="describe" />
					</div>
					<div class="texts">
						<input type="text" class="inputStyle" placeholder="上架时间"
							name="time" />
					</div>
					<div class="texts">
						<span class="heads">封面</span> <input type="file"
							class="inputStyle3" name="imgPath" />
					</div>
					<div class="texts">
						<div class="heads">文本</div>
						<input type="file" class="inputStyle3" name="bookUrl" />
					</div>
				</div>
				<div class="btns"><input class="btn" type="button" style="margin-left:30px;"
						value="返回菜单" onclick="jump('managerindex.jsp')" /> 
					<input class="btn" type="submit" value="添加书籍" />
				</div>
			</div>
		</form>
	</div>
	<c:if test="${!empty msg }">
		<script type="text/javascript">
			Toast.showMessage("${msg}", 3000);
		</script>
	</c:if>
</body>
</html>
