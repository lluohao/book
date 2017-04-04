<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://mytlds/pagingtld" prefix="p" %>
<!DOCTYPE html>
<html>
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>我的书架</title>
	<style type="text/css">
		#myBook img{
		width:100px;
		height:150px;
			
		}
		.bookNameClass{
			width:90px;
			height:30px;
			margin-bottom:0px;
			color:#FF3300;
			text-align:center;
			overflow:hidden;	
			line-height:15px;
			font-size:13px;
	
		}
		#myBook{
			float:left;
		}
		.book-image{
			cursor:pointer;
		}	
	</style>
  </head>
  
  <body>
  	<div id="allBook">
  		<c:forEach items="${bookLists}" var="book" varStatus="i">
			<div id="myBook" style="margin-left: 30px;margin-top:20px;">
				<img  src="bookImageServlet?bookId=${book.id }" class="book-image" onclick="location.href='preReadServlet?bookId=${book.id}';" /><br/>
				<div class="bookNameClass"><span id='bookName'>${book.name }</span></div>
			</div>
		</c:forEach>
	</div>
	<p:paging pageNoName="pageNo" pageAllName="pageAll" width="900px" urlName="myShelfServlet" loadDivIdName="htmlDiv"/> 
  </body>
</html>