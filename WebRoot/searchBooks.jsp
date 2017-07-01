<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>查询0.0</title>
</head>
<link rel="stylesheet" href="css/searchBook.css" />
<script type="text/javascript">
	$("#document").ready(function() {
		$("#search_booklist").load("searchPageNoServlet");
	});
	function bookClick(_id) {
		document.location.href = "preReadServlet?bookId=" + _id;
	}
</script>
<body>
	<%@include file="head.jsp"%>
	<div id="content">
		<div id="left_div">
			<a style="float:right;padding-right: 20px;"
				href="netSearchServlet?keyCode=${keyCode }">没有找到想要的？试试全网搜索吧！</a>
			<c:choose>
				<c:when test="${pageNoAll==0 }">
					<div id="no_found">
						没有查询到与"<em>${keyCode }</em>"的相关书籍>_<
					</div>
				</c:when>
				<c:otherwise>
					<div style="float:left">
						共搜索到${size }本关于"<em>${keyCode }</em>"的书>>>
					</div>
					<div id="search_booklist"></div>
				</c:otherwise>
			</c:choose>
		</div>
		<div id="right_div">
			<div style="font-size: 15px;">大家都在搜>>></div>
			<c:forEach items="${hotBooks }" var="hotBook">
				<div class="recommend_book" onclick="bookClick('${hotBook.id}')">
					<div class="recommend_item">
						<img src="bookImageServlet?bookId=${hotBook.id }">
					</div>
					<div class="recommend_item">
						<div>${hotBook.name }</div>
						<div>${hotBook.author }</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<%@include file="foot.jsp"%>
</body>
</html>