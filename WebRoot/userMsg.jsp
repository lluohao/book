<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>浩叶个人中心</title>
	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript">
		function loadDiv(_titleId,_url,_id){
			var title=$("#"+_titleId).html();
			$("#title").html(title);
			$("#htmlDiv").load(_url);
		} 
		function loadHtml(_titleId,_servlet,_userId){
			var title=$("#"+_titleId).html();
			$("#title").html(title);
			var _data="userId="+_userId;
			$.ajax({
				type:'post',
				data:_data,
				url:_servlet,
				success:function(result){
					$("#htmlDiv").html(result);	
				}
			});	
		}
	</script>
	<style type="text/css">
		.childDiv{
		   float:left;
		}
		.hrClass{
		}
		li{
			list-style-type:none;
			font-size:18px;
			height:50px;
			text-align:center;
			line-height:50px;
		}
		a{
			text-decoration:none;
			color:#000000;
		}
		#content{
			margin-left:auto;
			margin-right:auto;
		}
		li:hover a{
			color:#FF3300;
		}
		li:hover{
		background-color:#FFCCCC}
	</style>
  </head>
  
  <body onload="loadHtml('titleId1','myShelfServlet','${sessionScope.userId}')">
   <%@ include file="head.jsp" %>
  	<div id="content" style="width:1200px">
	  <div id='personCenter' class='childDiv' style="width:20%">
	      <h2 style="padding-left:39px;color:#FF3300">个人中心</h2>
		  <div id='personDiv'>
		  	<ul id='personItem'>
				<li><a id="titleId1" href="javascript:loadHtml('titleId1','myShelfServlet','${sessionScope.userId}')">我的书架</a></li>
				<li><a id="titleId2" href="javascript:loadHtml('titleId2','myCollectionServlet','${sessionScope.userId}')">我的收藏</a></li>
				<li><a id="titleId3" href="javascript:loadDiv('titleId3','myMoney.jsp','${sessionScope.userId}')">码币中心</a></li>
				<li><a id="titleId4" href="javascript:loadDiv('titleId4','myAccount.jsp','${sessionScope.userId}')">账号设置</a></li>
				<li><a id="titleId5" href="javascript:loadDiv('titleId5','updatePwd.jsp','${sessionScope.userId}')">修改密码</a>
			</ul>
		  </div>  
	  </div>
	
	  <div id='msgCenter' class='childDiv' style="width:80%">
	  		<br/><br/>
	  		<span id='title' style="margin-left: 30px;"></span>
			<hr width="900px" color="#FFCCCC"/>
			<div id='htmlDiv'>
				
			</div>
	  </div>
	</div>
  <br/><br/><br/><br/><br/><br/><br/><br/>
  <%@ include file="foot.jsp" %>
  </body>
</html>