<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="css/bookListShow.css" />
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/Toast.js"></script>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta charset="UTF-8">
<title>${parentType.typeName }</title>
</head>
<script type="text/javascript">
	$("#document").ready(function() {
		$("#${selectType.typeId}").css("border-right", "3px solid #fa7a20");
		$("#${selectType.typeId}").css("background-color", "#E2DCD7");
		$("#${orderType}_div").css("border-bottom", "3px solid #fa7a20");
		$("#${orderType}_div").css("font-weight", "bold");
		pageNo(1);
	});

	function typeClick(_id) {
		var id = $("#" + _id).attr("id");
		document.location.href = "bookListShowServlet?typeId=" + id;
	}
	function pageNo(_id) {
		var typeId = $("#${selectType.typeId}").attr("id");
		var pageNo = _id;
		var orderType = "${orderType}";
		var _data = "typeId=" + typeId + "&pageNo=" + pageNo + "&orderType="
				+ orderType;
		if ("count" == orderType) {
			var values = $("#count_div>input");
			var numa = values[0].value;
			var numb = values[1].value;
			_data += "&numa=" + numa + "&numb=" + numb;
		}

		$("#right_up").load("pageNoServlet?" + _data);
	}
	function orderClick(_id) {
		var _data = "orderType=" + _id;
		var typeId = ${selectType.typeId};
		_data += "&typeId=" + typeId;
		if (_id == "count") {
			var values = $("#count_div>input");
			var numa = values[0].value;
			var numb = values[1].value;
			if (isNaN(numa) || isNaN(numb)) {
				Toast.showMessage("请输入数字！！！", 1000);
				return;
			}
			_data += "&numa=" + numa + "&numb=" + numb;
		}
		document.location.href = "bookListShowServlet?" + _data;
	}
	function shopClick(_bookId) {
		var bookIds="${bookIds}";
		if(bookIds!=undefined){
			for(var i=0;i<bookIds.length;i++){
				//alert(bookIds[i]);
				if(bookIds[i]==_bookId){
					Toast.showMessage("您已经加入购物车了,>_<再点我受不了了", 1000);
					return;
				}
			}
		}
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
				if (result=="true") {
					Toast.showMessage("收藏成功！您可以在我的收藏里查看。。。", 1000);
				} else {
					Toast.showMessage("收藏失败！！！", 1000);
				}
			}
		});
	}
	function bookClick(_bookId) {
		window.open("preReadServlet?bookId=" + _bookId);
	}
</script>
<body>
	<jsp:include page="head.jsp"></jsp:include>
	<div id="content">
		<div id="left_div">
			<div id="${parentType.typeId}" style="text-indent: 0px;"
				class="books_item" onclick="typeClick('${parentType.typeId}')">
				${parentType.typeName}</div>
			<c:forEach items="${types }" var="type" varStatus="item">
				<div class="books_item" id="${type.typeId }"
					onclick="typeClick('${type.typeId }')">${type.typeName}</div>
			</c:forEach>
		</div>
		<div id="right_div">
			<div id="right_top">
				<div>
					<a href="javascript:typeClick('${parentType.typeId}')">${parentType.typeName }</a>
				</div>
				<c:if test="${selectType.parentTypeId!=-1 }">
					<div>&nbsp;>&nbsp;</div>
					<div>${selectType.typeName }</div>
				</c:if>
			</div>
			<div id="right_center">
				<div id="hot_div">
					<a href="javascript:orderClick('hot')">畅销</a>
				</div>
				<div id="new_div">
					<a href="javascript:orderClick('new')">最新</a>
				</div>
				<div id="count_div" style="border-right:none">
					价格 &nbsp; <input type="text" placeholder="0" /> &nbsp;-&nbsp; <input
						type="text" placeholder="不限" /> <input type="button"
						id="count_btn" value="确定" onclick="orderClick('count')"
						style="width: 40px;height:25px;" />
					<c:if test="${!empty numa }">
						<script type="text/javascript">
							var values = $("#count_div>input");
							values[0].value = ${numa};
							values[1].vaule = ${numb};
						</script>
					</c:if>
				</div>
			</div>
			<div id="right_up"></div>

		</div>
	</div>
	<%@include file="foot.jsp"%>
</body>

</html>