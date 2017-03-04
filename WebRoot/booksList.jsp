<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://mytlds/pagingtld" prefix="my"%>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<c:forEach items="${books }" var="book">
	<div class="book_div">
		<div class="book_left">
			<a href="javaScript:bookClick('${book.id }')"><img src="bookImageServlet?bookId=${book.id }" /></a>
			<div style="font-size:13px;"><span>原价:Ж${book.price }</span><span style="color:red">现价:Ж${book.price-book.discount }</span></div>
		</div>
		<div class="book_right">
			<div >${book.name }</div>
			<div style="color:#C4BAB2">${book.author }</div>
			<div style="height:80px;font-size:13px;line-height:20px;">${book.discribe}</div>
			<div class="book_last">
				<a href="javaScript:shopClick('${book.id }')"><img
					src="img/shopcar.png" /></a> <a
					href="javaScript:collectionClick('${book.id }')"><img
					src="img/collection.png" /></a>
			</div>
		</div>
	</div>
</c:forEach>
<c:if test="${empty msg}">
<my:paging pageNoName="pageNo" pageAllName="pageAll" width="900px" urlName="pageNoServlet" loadDivIdName="right_up"/>
</c:if>
<div style="font-size:30px;font-style:bold;margin-left:100px;margin-top:100px;">${msg }</div>
