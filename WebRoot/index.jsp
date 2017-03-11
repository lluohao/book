<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html">
<html>

<head>
<link rel="stylesheet" href="css/frame.css" />
<title>皓叶电子书——首页</title>
<style>
.index-item {
	margin-bottom: 50px;
}

.index-title {
	font-size: 22px;
	color: #777;
	overflow: hidden;
}

.book-item {
	float: left;
	width: 160px;
	padding: 10px 20px;
	text-align: center;
	cursor: pointer;
}

.book-title {
	margin: auto;
	width: 90%;
	font-size: 14px;
	color: #D25000;
}

.book-title p {
	height: 42px;
	overflow: hidden;
	text-align: left;
	line-height: 20px;
	margin: 0px 0px;
	text-align: center;
}

.book-price {
	
}

.index-content {
	overflow: hidden;
}
</style>
</head>

<body>
	<%@include file="head.jsp"%>
	<div id="content">


		<!--
            	作者：840230057@qq.com
            	时间：2016-07-26
            	描述：热销榜块开始
            -->
		<div class="index-item">
			<div class="index-title">
				<p>热销榜</p>
			</div>
			<div class="index-content">
				<c:forEach items="${hotBooks }" var="book" varStatus="index">
					<a href="preReadServlet?bookId=${book.id}" target="_blank"><div
							class="book-item" id="book${book.id}">
							<div class="book-image">
								<img src="bookImageServlet?bookId=${book.id}" width="160px" height="213px"/>
							</div>
							<div class="book-title">
								<p>${book.name }</p>
							</div>
							<div class="book-price">
								<b>码币 ${book.price }</b>
							</div>
						</div> </a>
				</c:forEach>
			</div>
		</div>
		
		<!--
            	作者：840230057@qq.com
            	时间：2016-07-26
            	描述：新书榜块开始
            -->
		<div class="index-item">
			<div class="index-title">
				<p>新书上架</p>
			</div>
			<div class="index-content">
				<c:forEach items="${newBooks }" var="book" varStatus="index">
					<a href="preReadServlet?bookId=${book.id}"  target="_blank"><div
							class="book-item" id="book${book.id}">
							<div class="book-image">
								<img src="bookImageServlet?bookId=${book.id}" width="160px"  height="213px"/>
							</div>
							<div class="book-title">
								<p>${book.name }</p>
							</div>
							<div class="book-price">
								<b>码币 ${book.price }</b>
							</div>
						</div> </a>
				</c:forEach>
			</div>
		</div>
		<!--
            	作者：840230057@qq.com
            	时间：2016-07-26
            	描述：随机推荐块开始
            -->
		<div class="index-item">
			<div class="index-title">
				<p>随机推荐</p>
			</div>
			<div class="index-content">
				<c:forEach items="${randomBooks }" var="book" varStatus="index">
					<a href="preReadServlet?bookId=${book.id}"  target="_blank"><div
							class="book-item" id="book${book.id}">
							<div class="book-image">
								<img src="bookImageServlet?bookId=${book.id}" width="160px" height="213px" />
							</div>
							<div class="book-title">
								<p>${book.name }</p>
							</div>
							<div class="book-price">
								<b>码币${book.price }</b>
							</div>
						</div> </a>
				</c:forEach>
			</div>
		</div>

	</div>
	<%@include file="foot.jsp"%>
</body>

</html>