<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>查询书籍</title>
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

.up {
	border: 0px;
	background-color: #1A73D6;
	border-radius: 2px;
	color: white;
	width: 60px;
	height: 25px;
	float: left;
	margin-left: 15px;
	margin-top: 14px;
}

.btn:hover {
	cursor: pointer;
}
</style>
<script type="text/javascript" src="js/Toast.js"></script>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
	function toFindBook() {
		var bookName = $("#name").val();
		var _data = "bookName=" + bookName;
		$.ajax({
			type : 'post',
			url : 'findBooksByKeyServlet',
			data : _data,
			success : function(result) {
				var msg = eval("(" + result + ")");
				$("#bookName").val(msg.name);
				$("#author").val(msg.author);
				$("#price").val(msg.price);
				$("#discount").val(msg.discount);
				$("#type").val(msg.type);
				$("#stock").val(msg.stock);
				$("#status").val(msg.status);
				$("#describe").val(msg.discribe);
				/* alert(msg.discribe); */
				$("#time").val(msg.time);
			}
		});
	}
	function jump(path) {
		window.location.href = path;
	}
</script>
<body>

	<div class="total">
		<form id="form0">
			<div style="margin-left: 20px;">
				<input type="text" class="text01" id="name" /> <input type="button"
					class="btn01" onclick="toFindBook()" value="查询" />
			</div>
		</form>
		<form id="form1"
			action="${pageContext.request.contextPath}/updateBookServlet"
			enctype="multipart/form-data" method="post">
			<div style="width: 800px;">
				<div class="left">
					<div class="texts">
						<input type="text" class="inputStyle2" placeholder="书名"
							id="bookName" name="bookName" />
					</div>
					<div class="texts">
						<input type="text" class="inputStyle2" placeholder="作者"
							id="author" name="author" />
					</div>
					<div class="texts">
						<input type="text" class="inputStyle2" placeholder="价格" id="price"
							name="price" />
					</div>
					<div class="texts">
						<input type="text" class="inputStyle2" placeholder="折扣"
							id="discount" name="discount" />
					</div>
					<div class="texts">
						<input type="text" class="inputStyle2" placeholder="类型" id="type"
							name="type" />
					</div>
					<div class="texts">
						<input type="text" class="inputStyle2" placeholder="库存" id="stock"
							name="stock" />
					</div>
				</div>
				<div class="right">
					<div class="texts">
						<input type="text" class="inputStyle" placeholder="status"
							id="status" name="status" />
					</div>
					<div class="texts">
						<input type="text" class="inputStyle" placeholder="简述"
							id="describe" name="describe" />
					</div>
					<div class="texts">
						<input type="text" class="inputStyle" placeholder="上架时间" id="time"
							id="time" />
					</div>
					<div class="texts">
						<span class="heads">封面</span> <input type="file"
							class="inputStyle3" id="imgPath" name="imgPath" />
					</div>
					<div class="texts">
						<div class="heads">文本</div>
						<input type="file" class="inputStyle3" id="bookUrl" name="bookUrl" />
					</div>
				</div>
				<div class="btns">
					<input class="btn" type="button" style="margin-left:30px;"
						value="返回菜单" onclick="jump('managerindex.jsp')" /> <input
						class="btn" type="submit" value="修改书籍" />
				</div>
			</div>
		</form>
	</div>
</body>
<c:if test="${!empty msg }">
	<script type="text/javascript">
		Toast.showMessage("${msg}", 3000);
	</script>
</c:if>
</html>
