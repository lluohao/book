<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html">
<html>
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>我的码币</title>
	<style type="text/css">
		#rechargeMoneyId{
			background-color:#3399FF;
			width:60px;
			height:25px;
			font-size:18px;
			padding-left:8px;
			padding-top:10px;
		}
		#chargeId{
			border-radius:3px;
			border-style:solid;
			border-width:thin;
			border-color:#00CCFF;
			width:100px;
			height:32px;
	
		}
	</style>
	<script type="text/javascript">
		function getBalance(_userId){
			var _data="userId="+_userId;
			$.ajax({
				type:'post',
				data:_data,
				url:'getBalanceServlet',
				success:function(result){
					$("#moneyId").html(result);
				}
			});
		}
		function toCharge(){
			loadDiv('titleId3','recharge.jsp');
			//location.href='recharge.jsp';
		}
	</script>
  </head>
  
  <body>
  	<div style="margin-left: 30px;margin-top:30px;">
		码币余额为:<span id='moneyId'>66</span>Ж<br/>
		<br/><br/>
		<input type="button" name="charge" id="chargeId" value="充值" onclick="toCharge()" />
			
	</div>
  </body>
  <script type="text/javascript">getBalance('${sessionScope.userId}')</script>
</html>