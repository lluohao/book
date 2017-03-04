<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改密码</title>
<style type="text/css">
		#ok{
			border-radius:3px;
			border-style:solid;
			border-width:thin;
			border-color:#EF5B00;
			width:60px;
			height:25px;
		}
		#pwdTable input,select{
			border-radius:3px;
			border-style:solid;
			border-width:thin;
			border-color:#EF5B00;
		}
		.tdLbl{
			color:#A58CB7;
		}
		#pwdDiv{
			margin-top: 80px;
		}
</style>
<script type="text/javascript" src="js/Toast.js"></script>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
	var key="";
	function getCode(_userId){
		var _data="userId="+_userId;
		
		Toast.showMessage("验证码获取中。。。", 5000);
		$.ajax({
			type:'post',
			data:_data,
			url:'requestEmailCodeServlet',
			success:function(result){
				var r=eval("("+result+")");
				if(r.code==200){
					key=r.key;
					Toast.showMessage("发送成功，请查看", 3000);
				}else if(r.code==404){
					Toast.showMessage("您还没有注册邮箱", 3000);
				}else{
					Toast.showMessage("发生未知错误", 3000);
				}
			}
		});
	}
	function update(){
		var newPwd=$("#newPwd").val();
		var queryPwd=$("#queryPwd").val();
		var code=$("#code").val();
		if(newPwd!=queryPwd){
			Toast.showMessage("两次密码输入不一致", 3000);

		}
		if(code==""){
			Toast.showMessage("验证码不能为空", 3000);

		}
		var _data="key="+key+"&value="+code;
		$.ajax({
			type:'post',
			data:_data,
			url:'verifyMailCodeServlet',
			success:function(result){
				var r=eval("("+result+")");
				if(r.code==404){
					Toast.showMessage("验证码出错", 3000);
					
				}else if(newPwd==queryPwd && r.code==200){
					setPwd();
					location.href="login.jsp";
				}
			}
		});
		
	}
	function setPwd(){
		var newPwd=$("#newPwd").val();
		_data="newPwd="+newPwd;
		$.ajax({
			type:'post',
			data:_data,
			url:'updatePwdServlet',
			success:function(result){
				Toast.showMessage(result+"请重新登录！", 3000);
			}
		});
	}
</script>
</head>
<body>
	<div id="pwdDiv">
    <form id="pwdFrom" name="pwdFrom" method="post" action="">
      <table width="350" border="0" align="center" cellspacing="10" id="pwdTable">
        <tr>
          <td class='tdLbl' align="right">新密码：</td>
          <td><input name="newPwd" type="password" id="newPwd" /></td>
        </tr>
        <tr>
          <td class='tdLbl' align="right">确认密码：</td>
          <td><input name="queryPwd" type="password" id="queryPwd" /></td>
        </tr>
        <tr>
          <td class='tdLbl' align="right">验证码：</td>
          <td><input name="code" type="text" id="code" />&nbsp;<a href="javascript:getCode('${sessionScope.userId}')"><span id='code	Msg'>获取验证码</span></a></td>
        </tr>
        <tr>
          <td>&nbsp;</td>
          <td align="center"><input name="ok" type="button" id="ok" value="确认修改" onclick="update()" /></td>
        </tr>
      </table>
    </form>
  </div>
</body>
</html>