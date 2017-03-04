<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>跳转界面</title>
</head>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
	function reflush() {
		$.ajax({
			type : "get",
			url : "netDownloadStatus?dkey="+dkey,
			success : function(result) {
				console.log(result);
				var _msg = eval("(" + result + ")");
				if (_msg.code == 200) {
					if (_msg.bookId > 0) {
						$("#showWait").text("已完成！点击");
						$("#down").attr("href","down?bookId="+ _msg.bookId);
						$("#down").css("display","inline-block");
					} else {
						showWaitTime(_msg.size);
					}
				}
			}
		});
	}
	
	function showWaitTime(a) {
		var str = "字节";
		var size = a;
		if(a>1024){
			size = a/1024;
			str="KB";
		}
		if(a>1024*1024){
			size=size/1024;
			str="MB";
		}
		$("#showWait").text(size.toFixed(2)+str);
	}
	var dkey = "";
	$(document).ready(function() {
		var str = window.location.href.split("?")[1];
		var pa = str.split("&");
		for ( var i in pa) {
			var t = pa[i].split("=")[0];
			if(t=="dkey"){
				dkey = pa[i].split("=")[1];
			}
		}
		console.log(dkey);
		setInterval(reflush, 500);
	});
</script>
<style>
	#down{
		display: none;
	}
</style>
<body>
	<%@include file="head.jsp"%>
	<div id="content">
		<h2 style="text-align: center;">此书源于网络，服务器正在抓取内容,具体耗时取决于书本大小，请耐心等待</h2>
		<div
			style="width: 900px;margin: auto;padding-top: 100px;font-weight: Bold;font-size:16px;">
			服务器正在从网络中解析数据<span id="showWait"></span>
			<a href="#" id="down">下载</a>
		</div>
	</div>
	<%@include file="foot.jsp"%>
</body>
</html>