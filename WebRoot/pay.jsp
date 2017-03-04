<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>支付</title>
<link rel="stylesheet" href="css/pay.css" />
</head>
<script type="text/javascript">
	function select(_id) {
		$("#" + _id + ">input")[0].checked = true;
		if (_id == "pay1") {
			$("#accounts").text("${mab}码币");
			$("#real_price").text("实际支付:Ж${mab}");
		} else {
			$("#accounts").text("${rmb}元");
			$("#real_price").text("实际支付:￥${rmb}");
		}
	}
	function payMoney() {
		var _data = "orderId="+${orderId};
		if ($("#pay1>input")[0].checked == true) {
			_data += "&type=mab";
		} else {
			_data += "&type=rmb";
		}
		$.ajax({
					type : "post",
					url : "payMoneyServlet",
					data : _data,
					success : function(result) {
						var _msg = eval("(" + result + ")");
						if (_msg.code == 200) {
							$("#pay_div").html(
									"<div id='pay_result' style='background-color: #FBFAF8;width: 800px;'>支付成功，"
											+ "你可以在<a href='javascript:payOk()'>我的书架</a>里查看</div>");
							$("#btn_div").html(
									"<input type='button' value='确定' id='pay_btn' style='margin-left:350px'"
											+ "onclick='payOk()' /> ");
						} else {
							$("#pay_div")
									.html(
											"<div id='pay_result' style='background-color: #FBFAF8;width: 800px;'>支付失败${msg}</div>");
							$("#btn_div").html(
									"<input type='button' value='返回' id='pay_btn' style='margin-left:350px'"
											+ "onclick='payReturn()' /> ");
						}
						
					}
				});
	}
	function payReturn() {
		window.history.back(-1);
	}
	function  payOk() {
		document.location.href="userMsg.jsp";
	}
</script>
<body>
	<%@include file="head.jsp"%>
	<div id="content">
		<h1 id="pay_top">支付</h1>
		<div id="pay_div">
			<h3>
				共${bookNums }本书，总计<span id="accounts" style="color:#FA7A20">${mab }码币</span>
			</h3>
			<div style="margin-left: 200px" id="pay1" onclick="select('pay1')">
				<input type="radio" name="pay_check" checked="checked" /><img
					src="img/icon.png">
				<div id="pay_count">码币</div>
			</div>
			<div id="pay2" onclick="select('pay2')">
				<input type="radio" name="pay_check" /><img src="img/alipay.png">
			</div>
		</div>
		<div id="btn_div">
			<input type="button" value="确认支付" id="pay_btn" style="float:right"
				onclick="payMoney()" /> <span
				style="float: right;font-size:12px; line-height: 60px;"
				id="real_price">实际支付:Ж${mab }</span>
		</div>
	</div>
	<%@include file="foot.jsp"%>
</body>

</html>