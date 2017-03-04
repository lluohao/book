<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>全网搜索</title>
</head>
<style type="text/css">
.net_books div {
	height: 20px;
	line-height: 20px;
	width: 900px;
	max-width: 900px;
	margin: auto;
	font-size: 13px;
}

.net_books {
	overflow: hidden;
	border-bottom: 1px solid #F3F3F3;
	width: 900px;
	margin: auto;
	padding-bottom: 20px;
}

#net_book_title {
	font-size: 17px;
	color: #5D00CC;
	height: 40px;
	line-height: 40px;
	width: 900px;
	margin: auto;
}

#keyCode {
	width: 200px;
	border: 2px solid #FA7A20;
	border-radius: 5px;
	background-color: white;
	height: 20px;
}

#net_search {
	margin: auto;
	width: 400px;
	padding-bottom: 30px;
}

#net_button {
	border: none;
	background-color: white;
	background-image: url(img/search.png);
	background-repeat: no-repeat;
}

em {
	color: red;
	font-weight: bold;
	font-style: normal;
}
</style>
<script type="text/javascript">
	function searchBook(_id){
		var dkey = Math.floor(Math.random()*100000);
		$.get("netBookServlet","id="+_id+"&dkey=wait"+dkey);
		window.open("netWait.jsp"+"?dkey=wait"+dkey);
	}
</script>
<body>
	<%@include file="head.jsp"%>
	<div id="content">
		<div id="net_search">
			<form action="netSearchServlet" onsubmit="return netClick()">
				<span style="font-weight:bold;color:#FA7A20">全网搜索</span> <input
					id="keyCode" type="text" placeholder="书名/作者/关键字" name="keyCode" />
				<input id="net_button" type="submit" value="" />
			</form>
		</div>
		<div style="width: 900px;margin: auto;line-height: 30px;">
			共找到${size}条关于"<em>${keyCode }</em>"的书>>>
		</div>
		<c:forEach items="${books }" var="book" varStatus="index">
			<div class="net_books">
				<div id="net_book_title">
					<a href="javascript:searchBook('${index.index }')">${book.name }</a>
				</div>
				<div>${book.author}</div>
				<div style="max-height:40px;">${book.discribe }</div>
			</div>
		</c:forEach>
	</div>
	<%@include file="foot.jsp"%>
</body>
</html>