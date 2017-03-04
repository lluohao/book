<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<tr id="table-title">
	<td class="td1">状态</td>
	<td class="td2">书名</td>
	<td class="td3">单价</td>
	<td class="td4">操作</td>
</tr>
<c:forEach items="${cart.books}" var="bookItem">
	<tr id="table-body${bookItem.book.id}">
		<td class="td1"><input type="checkbox" name="checkbox"
			class="checkbox" id="${bookItem.book.id}" checked="checked"
			value="${bookItem.realPrice}" onclick="toSum()" /></td>
		<td class="td2"><a id="book-title" href="preReadServlet?bookId=${bookItem.book.id}" style="text-decoration: none">${bookItem.book.name}</a>
		</td>
		<td class="td3"><a id="book-price">${bookItem.realPrice}</a>
		</td>
		<td class="td4"><a id="book-remove"> <a
				href='javascript:toRemoveBook(${bookItem.book.id})'>移除</a>
		</a>
		</td>
	</tr>
</c:forEach>