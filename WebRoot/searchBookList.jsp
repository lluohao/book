<%@page import="com.code.entity.Book"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://mytlds/pagingtld" prefix="my"%>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/Toast.js"></script>
<script type="text/javascript">
function shopClick(_bookId) {
	var _data = "bookId=" + _bookId + "&type=add";
	$.ajax({
		type : "get",
		url : "updateCartBookServlet?" + _data,
		success : function(result) {
			var _msg = eval("(" + result + ")");
			if (_msg.isAdd) {
				Toast.showMessage("加入购物车成功！您可以在我的购物车中查看。。。", 1000);
				$("#div-header").load("head.jsp");
			} else {
				Toast.showMessage("加入购物车失败！！！", 1000);
			}
		}
	});
}
function collectionClick(_bookId) {
	var _data = "bookId=" + _bookId;
	$.ajax({
		type : "get",
		url : "addCollectionServlet?" + _data,
		success : function(result) {
			if (result) {
				Toast.showMessage("收藏成功！您可以在我的收藏里查看。。。", 1000);
			} else {
				Toast.showMessage("收藏失败！！！", 1000);
			}
		}
	});
}
</script>
<c:forEach items="${books}" var="book">
	<div class="search_book">
		<div>
			<a href="preReadServlet?bookId=${book.id }" target="_blank"><img src="bookImageServlet?bookId=${book.id }"
				class="search_left" /></a>
		</div>
		<div class="search_bottom">
			<div style="font-size: 15px;font-weight: bold;">
				<a href="preReadServlet?bookId=${book.id }" target="_blank">${book.name }</a>
			</div>
			<div>${book.author }</div>
			<div style="max-height:60px;height: 60px;">${book.discribe }</div>
			<div>现价:${book.price-book.discount } 原价:${book.price }</div>
		</div>
		<div class="search_right">
			<div>
				<a href="javascript:shopClick('${book.id }')">加入购物车</a>
			</div>
			<div>
				<a href="javascript:collectionClick('${book.id }')">加入收藏</a>
			</div>
		</div>
	</div>
</c:forEach>
<my:paging pageNoName="pageNo" pageAllName="pageNoAll" width="800px"
	urlName="searchPageNoServlet" loadDivIdName="search_booklist" />
