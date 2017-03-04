<%@page import="com.code.service.fact.ServiceFactory"%>
<%@page import="com.code.service.IUserService"%>
<%@page import="com.code.entity.User"%>
<%@page import="com.code.entity.Cart"%>
<%@page import="com.code.service.ICartService"%>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	IUserService service = ServiceFactory.getInstance()
			.newUserService();
	ICartService cartService = ServiceFactory.getInstance()
			.newCartService();
	Integer userId = (Integer) session.getAttribute("userId");
	User user = null;
	int cartBooks = 0;
	if (userId != null) {
		try {
			Cart cart = cartService.findCart(userId);
			cartBooks = cart.getBooks().size();
		} catch (Exception e) {
		}
		user = service.findUser(userId);
	} else {
		user = new User();
		user.setName("登录");
		user.setId(-1);
	}
	session.setAttribute("cartBooks", cartBooks);
	request.setAttribute("user", user);
%>
<link rel="stylesheet" href="css/frame.css" />
<link rel="shortcut icon" href="img/favicon.ico" type="image/x-icon" />
<div id="div-header">
	<div id="header-top">
		<div id="div-top-content">
			<div id="header-left">
				<a class="header-top-a" href="hy.apk"> 皓叶移动版 </a>
			</div>
			<script type="text/javascript">
				function toUserMsg() {
					var userName="${user.name}";
					if (userName=="登录") {
						window.location.href = "login.jsp";
					} else {
						window.location.href = "userMsg.jsp";
					}
				}
			</script>
			<div id="header-right">
				<div id="header-user">
					<a class="header-top-a" href="javascript:toUserMsg()" id="a-user">
						${user.name}</a>
				</div>
				| <a class="header-top-a" href="cartServlet"> 购物车(<span
					id="head-cart">${cartBooks}</span>)
				</a>
			</div>
		</div>
	</div>
	<div id="header-nexttop">
		<div id="header-icon">
			<img src="img/icon.png" width="" height="50px" class="img-icon" /> <span
				style="font-size: 28px;position: relative;top: -14px;padding-left: 5px;color: #555;">皓叶电子书</span>
		</div>
		<div id="header-search">
			<form id="search-form" action="searchBooksServlet"
				onsubmit="return checkForm()">
				<input type="text" id="search-input" placeholder="书名/作者/关键字"
					name="keyCode" /> <input type="submit" id="search-do" value=""></input>
			</form>
			<script type="text/javascript">
				function checkForm() {
					var value = $("#search-input").val();
					value = value.replace(/\s+/g, "");
					if (value == null || value == '' || value == undefined) {
						return false;
					}
					return true;
				}
			</script>
		</div>
	</div>
	<div id="header-menu">
		<a href="indexServlet" class="menu-a">首页</a> <a
			href="bookListShowServlet?typeId=4" class="menu-a">经典文学</a> <a
			href="bookListShowServlet?typeId=1" class="menu-a">网络小说</a> <a
			href="bookListShowServlet?typeId=6" class="menu-a">教育教材</a> <a
			href="bookListShowServlet?typeId=20" class="menu-a">计算机/IT</a> <a
			href="hy.apk" class="menu-a">客户端</a> <a class="menu-a" href="userMsg.jsp">个人中心</a>
	</div>

</div>