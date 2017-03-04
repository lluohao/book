<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>码币充值</title>
<script type="text/javascript" src="js/Toast.js"></script>
<style type="text/css">
<!--
.STYLE7 {
	font-size: 24px;
	font-style: italic;
	font-weight: bold;
}

-->
#count {
	width: 60px;
	height: 25px;
}

#count:hover {
	width: 60px;
	height: 25px;
	background-color: #EF5B00;
}

#rechargeTable input,select {
	border-radius: 3px;
	border-style: solid;
	border-width: thin;
	border-color: #EF5B00;
}

.tdLbl {
	color: #A58CB7;
}

.rechargeDiv {
	margin-top: 80px;
}
</style>
<script type="text/javascript">
	function toVerifyInt(event) {
		var obj = event;
		var code;
		if (window.event) {
			code = obj.keyCode;
		} else {
			code = obj.which;
		}
		//alert(code);
		if (code == 8 || (code >= 48 && code <= 57)) {
			return true;
		} else {
			return false;
		}
	}
	function back() {
		loadDiv('titleId3', 'myMoney.jsp');
	}
	function addMoney(){
		var money=$("#money_div").val();
		$.ajax({
			type:"post",
			data:"money="+money,
			url:"addMoneyServlet",
			success:function(result){
				var _msg=eval("("+result+")");
				if(_msg.code==200){
					Toast.showMessage("充值成功！！！", 1000);
					loadDiv('titleId3', 'myMoney.jsp');
				}else{
					Toast.showMessage("充值失败！！！", 1000);
				}
			}
		});
	}
</script>
</head>
<body>
	<%-- <%@ include file="head.jsp" %><br/><br/> --%>
	<a href="javascript:void(0)" onclick="back()"
		style="margin-left: 40px;">&lt;--返回</a>
	<div class="rechargeDiv">
		<table id="rechargeTable" width="345" border="0" align="center"
			cellspacing="10">
			<tr>
				<td colspan="2" align="center"><span class="STYLE7">码币充值中心</span></td>
			</tr>
			<tr>
				<td width="75">&nbsp;</td>
				<td width="236">&nbsp;</td>
			</tr>
			<tr>
				<td class='tdLbl' align="right">充值：</td>
				<td><input type="text" name="recharge"
					onkeypress="return toVerifyInt(event)" id="money_div"/> Ж</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td align="center"><input name="count" type="button" id="count"
					value="结算" onclick="addMoney()" /><span id="msgSpan"></span></td>
			</tr>
		</table>
	</div>
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<%--   <%@ include file="foot.jsp" %> --%>
</body>
</html>