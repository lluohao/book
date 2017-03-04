<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://mytlds/pagingtld" prefix="p" %>
<!DOCTYPE html">
<html>
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>我的收藏</title>
	<style type="text/css">
		#favorDiv img{
		width:100px;
		height:150px;
			
		}
		#favorDiv{
			float:left;
			margin-top:20px;
			overflow:hidden;
			
		}
		.aboutBook{
			width:80px;
			float:left;
		}
		.bookName{
			width:90px;
			height:30px;
			margin-bottom:0px;
			color:#FF3300;
			text-align:center;
			overflow:hidden;	
			line-height:15px;
			font-size:13px;
		}
		.bookPrice{
			text-align: center;
			cursor: pointer;
			font-weight:bold;
		}
		.btnClass{
			border-radius:3px;
			border-style:solid;
			border-width:thin;
			border-color:#00CCFF;
			width:80px;
			height:25px;
			
		}
		#allCheckId{
			margin-left:50px;
		}
		#removeBtn{
			border-radius:3px;
			border-style:solid;
			border-width:thin;
			border-color:#EF5B00;
			width:60px;
			height:25px;
		}
		.bookImg{
			cursor:pointer;
		}	
	</style>
	<script type="text/javascript" src="js/Toast.js"></script>
	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript">
		function check(){
			/*var check=$("#allCheckId").attr("checked");
			alert(check);
			$(".checkId").attr("checked",false);*/
			var oChecks = document.getElementsByName("checkName");
			for(var i=0;i<oChecks.length;i++){
				oChecks[i].checked = document.getElementById("allCheckId").checked;
			}
		
		}
		function deleteBook(_userId){
			
			var ids="";
			var oChecks = document.getElementsByName("checkName");
			for(var i=0;i<oChecks.length;i++){
				/* alert(oChecks[i].checked); */
				if(oChecks[i].checked==true){
					var bookId=$("#checkId"+i).attr("value");
					ids=ids+","+bookId;
				}
					
				
			}
			
			var _data="userId="+_userId+"&bookIds="+ids;
			$.ajax({
				type:'post',
				data:_data,
				url:'deleteCollectionServlet',
				success:function(result){
					Toast.showMessage(result, 3000);
					loadHtml('titleId2','myCollectionServlet','${sessionScope.userId}');
				}
				
			});
			
			
			
		}
	</script>
  </head>
  
  <body>
  	<input type='checkbox' id='allCheckId' onclick='check()' style='margin-left: 40px;'/>&nbsp;&nbsp;全选
  	&nbsp;&nbsp;<input type='button' id='removeBtn' value='移除选中' onclick="deleteBook('${sessionScope.userId}')"/>
  	<div id="allFavorDiv">
  		<c:forEach items="${bookLists}" var="book" varStatus="i">
  			<input type='checkbox' value='${book.id }' id='checkId${i.index }' name="checkName" class='checkId' style='float:left;margin-top: 20px;margin-left: 40px'/>
			<div id='favorDiv'>
				<img class="bookImg"  src="bookImageServlet?bookId=${book.id }" onclick="location.href='preReadServlet?bookId=${book.id}';" /><br/>
				<div class="aboutBook"></div>
					<div class='bookName' id='bookName'>${book.name}</div>
					<div class='bookPrice' id='bookPrice'>${book.price}Ж</div>
				</div>
			</div>
		</c:forEach>
	</div>
  </body>
  <p:paging pageNoName="pageNo" pageAllName="pageAll" width="900px" urlName="myCollectionServlet" loadDivIdName="htmlDiv"/> 
</html>