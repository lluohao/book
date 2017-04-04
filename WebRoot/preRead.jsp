<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${bookName}</title>
</head>
<link type="text/css" href="css/preRead.css" rel="stylesheet" />
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/jqeury-md5.js"></script>
<script type="text/javascript">
	var userId = "${sessionScope.userId}";
	function toAddCart() {
		if (userId.length == 0) {
			window.location.href = "login.jsp";
		}
		var type = "add";
		if ($("#addCart").text() == "已加入购物车") {
			type = "remove";
		}
		$
				.ajax({
					type : "get",
					url : "updateCartBookServlet?type=" + type + "&bookId="
							+ ${bookId},
					success : function(result) {
						var msg = eval("(" + result + ")");
						if (msg.isAdd) {
							$("#addCart").text("已加入购物车");
						} else {
							$("#addCart").text("加入购物车");
						}
						$("#div-header").load("head.jsp");
					}
				});
	}
	function toAddColl() {
		if (userId.length == 0) {
			window.location.href = "login.jsp";
		}
		var type = "add";
		if ($("#addColl").text() == "已收藏") {
			type = "remove";
		}
		$.ajax({
			type : "get",
			url : type
					+ "CollectionServlet?userId=${sessionScope.userId}&bookId="
					+ ${bookId},
			success : function(result) {
				if (result == "true") {
					if (type == "add") {
						$("#addColl").text("已收藏");
					} else {
						$("#addColl").text("收藏");
					}
				} else {
					Toast.showMessage("操作失败", 2000);
				}
			}
		});
	}
	function toDownBook() {
		window.location.href = ("down?bookId=" + ${bookId});
	}
	function toReadBook() {
		if (userId.length == 0) {
			window.location.href = "login.jsp";
		}
		$.ajax({
			type : "get",
			url : "shelfFindBookServlet?userId=${sessionScope.userId}&bookId="
					+ ${bookId},
			success : function(result) {
				if (result == "true") {
					window.location.href = ("readServlet?bookId=" + ${bookId});
				} else if (result == "false") {
					window.location.href = ("payServlet?bookId=" + ${bookId});
				}
			}
		});
	}
</script>
<body>
	<%@include file="head.jsp"%>
	<div id="content">
		<div class="c_wrap">
			<div class="w_left">
				<div class="bookdata">
					<div class="cover">
						<img style="width:180px; height:240px" alt=""
							src="bookImageServlet?bookId=${bookId}" />
					</div>
					<div class="describ">
						<h3 id="bookName">${bookName }</h3>
						<div class="howgood">
							<span style="color:#003333">评价：</span><span style="color:red">★★★★★</span>
						</div>
						<div class="bookAbout">
							<div class="data">
								<table id="_table">
									<tbody>
										<tr>
											<td>作者：</td>
											<td><a id="t_author" class="superLink">${Author}</a></td>
										</tr>
										<tr>
											<td>类型：</td>
											<td><a id="t_type" class="superLink">${type}</a></td>
										</tr>
										<tr>
											<td>版权：</td>
											<td><a target="">云塘商务印刷馆</a></td>
										</tr>
										<tr>
											<td>上架：</td>
											<td><a id="t_time" target="">${time}</a></td>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="pay">
								<div class="price">
									<em style="font-weight:bold;font-style:normal;color:#CC6600">Ж${price}</em>
									<i class="p_price">原价:Ж${oldPrice}</i>
								</div>
								<div class="act">
									<div class="btns">
										<input class="btn1" type="button" id="buyBtn" value="${isBuy}"
											onclick="toReadBook()" /> <input class="btn1" type="button"
											id="downBtn" value="下载" onclick="toDownBook()"
											style="margin-left: 20px" />
									</div>
									<div class="o_act">
										<ul class="o_list">
											<li class="o_li"><a class="littleLink" id="addCart"
												href="JavaScript:toAddCart()">${isCart}</a><span>&nbsp;&nbsp;&nbsp;|</span></li>
											<li class="o_li"><a class="littleLink" id="addColl"
												href="JavaScript:toAddColl()">${isColl}</a></li>
										</ul>
									</div>
								</div>
							</div>
						</div>
						<div class="data"></div>
					</div>
				</div>
				<div class="bookdetail">
					<div class="b_tab">
						<span class="chooseOne"> <a class="superLink"
							class="superLink" style="color:blue">内容预览</a>
						</span> <span class="charset"><a
							href="preReadServlet?bookId=${bookId}&charset=UTF-8"
							class="superLink">乱码？</a></span>
					</div>
					<div class="b_view">
						<pre id="simple-text">${simpleText}</pre>
					</div>
				</div>
			</div>
			<div class="w_right">
				<div class="appRead">
					<div class="appTitle">
						<span>移动设备阅读</span>
					</div>
					<div class="apps">
						<div class="app" style="margin-left: 25px; ">
							<a><img alt="" src="img/android.jpg" /></a>
						</div>
						<div class="app" style="margin-left: 25px; ">
							<a><img alt="" src="img/ios.jpg" /></a>
						</div>
						<div class="app">
							<a><img alt="" src="img/kindle.jpg" /></a>
						</div>
					</div>

					<div style="font-size: 10px;text-align: center;">
						<img src="img/code.png" style="width:180px;height:180px" />
					</div>
				</div>
				<div class="others">
					<span>喜欢这本书的人还喜欢</span>
					<div class="books">
						<a style="text-decoration: none;color: red"
							href="preReadServlet?bookId=${book0.id }">
							<div class="b_book">
								<img class="images" alt=""
									src="bookImageServlet?bookId=${book0.id }" />
							</div>
							<div class="b_data">
								<table>
									<tr>
										<td>书名：</td>
										<td class="ctrolWidth" style="color:red;font-size: 16">${book0.name}</td>
									</tr>
									<tr>
										<td>作者：</td>
										<td class="ctrolWidth" style="color:#551A8B"><a
											style="text-decoration: none;color: #551A8B">${book0.author}</a></td>
									</tr>
									<tr>
										<td>价格：</td>
										<td class="ctrolWidth" style="color:#A79B8F">Ж${book0.price}</td>
									</tr>
								</table>
							</div>
					</div>
					</a> <a style="text-decoration: none;color: red"
						href="preReadServlet?bookId=${book1.id }">
						<div class="books">
							<div class="b_book">
								<img class="images" alt=""
									src="bookImageServlet?bookId=${book1.id }" />
							</div>
							<div class="b_data">
								<table>
									<tr>
										<td>书名：</td>
										<td class="ctrolWidth" style="color:red;font-size: 16"><a
											style="text-decoration: none;color: red"
											href="preReadServlet?bookId=${book1.id }">${book1.name}</a></td>
									</tr>
									<tr>
										<td>作者：</td>
										<td class="ctrolWidth" style="color:#551A8B"><a
											style="text-decoration: none;color: #551A8B">${book1.author}</a></td>
									</tr>
									<tr>
										<td>价格：</td>
										<td class="ctrolWidth" style="color:#A79B8F">Ж${book1.price}</td>
									</tr>
								</table>
							</div>
						</div>
					</a> <a style="text-decoration: none;color: red"
						href="preReadServlet?bookId=${book2.id }">
						<div class="books">
							<div class="b_book">
								<img class="images" alt=""
									src="bookImageServlet?bookId=${book2.id }" />
							</div>
							<div class="b_data">
								<table>
									<tr>
										<td>书名：</td>
										<td class="ctrolWidth" style="color:red;font-size: 16"><a
											style="text-decoration: none;color: red"
											href="preReadServlet?bookId=${book2.id }">${book2.name}</a></td>
									</tr>
									<tr>
										<td>作者：</td>
										<td class="ctrolWidth" style="color:#551A8B"><a
											style="text-decoration: none;color: #551A8B">${book2.author}</a></td>
									</tr>
									<tr>
										<td>价格：</td>
										<td class="ctrolWidth" style="color:#A79B8F">Ж${book2.price}</td>
									</tr>
								</table>
							</div>
						</div>
					</a>
				</div>
			</div>
		</div>
	</div>
	<%@include file="foot.jsp"%>
</body>
</html>
