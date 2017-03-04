<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8" />
<title></title>
<style>
html,body {
	height: 100%;
}

body {
	margin: 0px;
	background: #EDE8D5;
	overflow: hidden;
}

#content {
	width: 1200px;
	height: 100%;
	margin: auto;
	min-height: 500px;
}

#div-read {
	width: 850px;
	margin: auto;
	background: #FAF7ED;
	height: 100%;
}

#text {
	margin: 0px;
	word-wrap: break-word;
	word-break: break-all;
	white-space: pre-wrap;
	width: 650px;
	margin: auto;
	padding: 100px 0px 50px 0px;
	font-size: 20px;
	line-height: 2;
	font-family: "微软雅黑", "Microsoft YaHei";
}

#menu {
	height: 300px;
	width: 150px;
	float: right;
	bottom: 0px;
}

.btn-page {
	width: 35px;
	height: 110px;
	border: 1px solid #BBB;
	background: #E0DCCA;
	display: block;
	margin: 10px;
	cursor: pointer;
	color: #444;
}

.btn-page:hover {
	background: #8C8481;
	color: #FFF;
}

#title {
	width: 1200px;
	position: absolute;
	height: 40px;
}

#title-content {
	width: 650px;
	margin: auto;
	height: 40px;
}

#text-title {
	float: left;
	height: 40px;
	line-height: 40px;
	font-size: 14px;
	color: #999;
}

#text-page {
	float: right;
	height: 40px;
	line-height: 40px;
	font-size: 14px;
	color: #999;
}
</style>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script>
	var maxLine = 0;
	var rows = 32;
	var bookId = ${bookId};
	var page = 1;
	var maxPage = 0;
	$(document).ready(init);
	function init() {
		var hei = window.innerHeight - 150;
		maxLine = Math.floor(hei / 40);
		console.log(maxLine);
		loadPage(1);
		loadMessage();
	}

	function loadMessage() {
		$.ajax({
			url : "currentBookMessageServlet",
			success : function(result) {
				result = eval("(" + result + ")");
				if (result.code == 200) {
					$("#text-title").text(result.name);
					$("#all-page").text(result.maxpage);
					maxPage = result.maxPage;
				}
			}
		});
	}

	function loadPage() {
		if(maxPage==0){
			loadMessage();
		}
		if (page > maxPage) {
			page = maxPage;
		}
		if (page < 1) {
			page = 1;
		}
		$("#text").load(
				"bookTextServlet.do?bookId=" + bookId + "&lines=" + maxLine
						+ "&rows=" + rows + "&page=" + page);
		$("#c-page").text(page);
	}

	function prePage() {
		page = page - 1;
		loadPage();
	}

	function nextPage() {
		page = page + 1;
		loadPage();
	}

	window.onresize = init;
</script>
</head>

<body>
	<div id="content">
		<div id="title">
			<div id="title-content">
				<span id="text-title">这里是书的标题</span> <span id="text-page"><span
					id="c-page">3</span>/<span id="all-page">3</span></span>
			</div>
		</div>
		<div id="menu">
			<button class="btn-page" onclick='nextPage()'>></button>
			<button class="btn-page" onclick='prePage()'><</button>
		</div>
		<div id="div-read">
			<pre id="text">
			</pre>
		</div>
	</div>
</body>

</html>
