<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html">
<html>

<head>
<link rel='icon' href='img/cart.png' type='image/x-ico' />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>购物车</title>
<script type="text/javascript" src="js/Toast.js"></script>
<style type="text/css">
#cart-head {
	height: 45px;
	line-height: 45px;
	border-bottom: 1px dashed #999;
}

#content {
	width: 1200px;
	margin: auto;
	overflow: hidden;
}

#cart-menu {
	list-style: none;
	padding: 0px;
}

#cart-menu ol {
	display: inline-block;
	padding: 0px 30px 0px 2px;
}

#cart-status {
	float: right;
	color: #555;
	font-size: 14px;
}

#cart-pay {
	height: 30px;
	padding: 0 15px;
	border-radius: 3px;
	line-height: 30px;
	font-size: 14px;
	color: #FFF;
	background: #e45a3f;
	text-align: center;
	cursor: pointer;
	border: 0px;
	display: inline-block;
	text-decoration: none;
}

#cart-delete {
	height: 30px;
	padding: 0 15px;
	border-radius: 3px;
	line-height: 30px;
	font-size: 14px;
	color: #FFF;
	background: #e45a3f;
	text-align: center;
	cursor: pointer;
	border: 0px;
	display: inline-block;
}

#cart-pay:hover {
	text-decoration: underline;
}

#cart-delete:hover {
	text-decoration: underline;
}

.td1 {
	width: 100px;
}

.td2 {
	width: 500px;
}

.td3 {
	width: 150px;
}

.td4 {
	width: 250px;
}

#table-data {
	width: 1000px;
}

#table-title {
	background: #AAA;
}

#table-data td {
	padding: 10px;
}

#table-body {
	background: #D1E4E9;
}

#cart-data {
	margin: auto;
	margin: 30px auto;
	width: 1000px;
}

#cart-delete {
	text-decoration: none;
}

#pay {
	text-align: right;
}
</style>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
	//求和
	function toSum() {
		var bookNum = $("table input:checkbox").length;
		if (bookNum != 0) {
			if (isCheckAll()) {
				$("#allCheck").prop("checked", true);
			} else {
				$("#allCheck").prop("checked", false);
			}
		}
		var sum = 0;

		var checkboxs = $("table input:checked");
		for (var i = 0; i < checkboxs.length; i++) {
			var price = parseInt(checkboxs[i].value);
			sum = sum + price;
		}
		$("#book-num").text(checkboxs.length);
		$("#sum_top").text(sum + " 码币");
		$("#sum_bottom").text(sum + " 码币");

	}

	//是否全选
	function isCheckAll() {
		var i = 0;
		$("table input:checkbox").each(function() {
			if ($(this).prop("checked") == false) {
				i++;
			}
		});
		if (i == 0) {//没有没选的，返回true
			return true;
		} else {
			return false;
		}
	}

	//全选
	function toCheckAll() {
		if ($("#allCheck").prop("checked") == true) {
			$(".checkbox").prop("checked", true);
		} else {
			$(".checkbox").prop("checked", false);
		}
		toSum();
	}

	//移除
	function toRemoveBook(_bookId) {
		var _url = "updateCartBookServlet?type=remove";
		if (_bookId.length > 1) {
			for (var i = 0; i < _bookId.length; i++) {
				_url += "&bookId=" + _bookId[i];
			}
		} else {
			_url += "&bookId=" + _bookId;
		}
		if (window.confirm("确认删除？")) {
			$.ajax({
				type : 'get',
				url : _url,
				success : function(result) {
					var _msg = eval("(" + result + ")");
					if (_msg.isDelete) {
						$("#head-cart").text(_msg.cartNum);
						if (_bookId.length > 1) {
							for (var i = 0; i < _bookId.length; i++) {
								$("#table-body" + _bookId[i]).remove();
							}
						} else {
							$("#table-body" + _bookId).remove();
						}
						$("#table-body" + _bookId).remove();
						toSum();
					} else {
						Toast.showMessage("删除失败", 1000);
					}
				}
			});
		}
	}
	function toRemoveChoosen() {
		var ids = getBookIds();
		toRemoveBook(ids);
	}

	//查询购物车
	function toSearchCart() {
		$.ajax({
			type : 'get',
			url : 'searchCartServlet',
			success : function(result) {
				$("#table-data").html(result);
				toSum();
			}
		});
	}

	function getBookIds() {
		var ids = new Array();
		var i = 0;
		$("table input:checked").each(function() {
			ids[i] = $(this).prop("id");
			i++;
		});
		return ids;
	}
	function toPay() {
		var ids = getBookIds();
		if (ids.length > 0) {
			//跳转订单页面
			var _url = "payServlet?";
			for (var i = 0; i < ids.length; i++) {
				_url += "bookId=" + ids[i] + "&";
			}
			_url += "cc=sb";
			window.location.href = _url;
		} else {
			alert("请选择需要购买的书本");
		}
	}
</script>
<body onload="toSearchCart()">
	<%@include file="head.jsp"%>
	<div class="content" id="content">
		<div id="cart-head">
			<span>购物车状态</span>
			<div id="cart-status">
				<span>共选中 </span><span id="book-num">0</span><span> 本 应付金额: </span><span
					id="sum_top" style="color: #E45A3F;padding-right: 20px;"> 0
					码币</span> <a id="cart-pay" href="javascript:toPay()">去结算</a>
			</div>
		</div>
		<div id="cart-data">
			<input type="checkbox" id="allCheck" checked="checked"
				onclick="toCheckAll()" /> 全选 <a id="cart-delete"
				href="javascript:toRemoveChoosen()">删除选中</a>
			<table id="table-data">
				<tr id="table-title">
					<td class="td1">状态</td>
					<td class="td2">书名</td>
					<td class="td3">单价</td>
					<td class="td4">操作</td>
				</tr>

			</table>
			<div id="pay">
				<p>
					总计： <span id="sum_bottom"> 0 码币</span>
				</p>
				<a id="cart-pay" href="javascript:toPay()">去结算</a>
			</div>
		</div>
	</div>
	<%@include file="foot.jsp"%>
</body>

</html>