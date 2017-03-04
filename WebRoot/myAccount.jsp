<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>用户账号设置</title>
	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<style type="text/css">
		#updateBtn{
			border-radius:3px;
			border-style:solid;
			border-width:thin;
			border-color:#EF5B00;
			width:60px;
			height:32px;
		}
		#userTable input,select{
			border-radius:3px;
			border-style:solid;
			border-width:thin;
			border-color:#EF5B00;
		}
		.tdLbl{
			color:#A58CB7;
		}
		
	</style>
	<script type="text/javascript" src="js/Toast.js"></script>
	<script type="text/javascript">
		function toVerifyInt(event){
			var obj=event;
			var code;
			if(window.event){
				code=obj.keyCode;
			}else{
				code=obj.which;
			}
			//alert(code);
			if(code==8 || (code>=48 && code<=57)){
				return true;
			}else{
				return false;
			}		
		}
		function getMsg(_userId){
			var _data="userId="+_userId;
			$.ajax({
				type:'post',
				data:_data,
				url:'getUserMsgServlet',
				success:function(result){

					var user=eval("("+result+")");

					$("#urUserName").val(user.name);
					if(user.sex=="男"){
						$("#sex").val("1");	
					}else{
						$("#sex").val("0");
					}
					$("#email").val(user.email);
					$("#age").val(user.age);
					$("#status").val(user.status);
					$("#account").val(user.account);
				}
			});	
		}
		function updateMsg(_userId){
			var _data="userId="+_userId+"&"+$("#userForm").serialize();
			if(judgeInt('age',0,150)){
				$.ajax({
					type:'post',
					data:_data,
					url:'updateUserMsgServlet',
					success:function(result){
						Toast.showMessage(result, 3000);
					}	
				});
			}else{
				Toast.showMessage('年龄请输入0到150整数', 3000);
			}
		}
		function judgeInt(_id,min,max){
			var val=$("#"+_id).val();
			if(val>=min&&val<=max){
				return true;
			}else{
				return false;
			}
			
		}
	</script>
  </head>
  
  <body>
  	<div style="margin-top:50px;">
	  <form id="userForm" name="userForm" method="post" action="">
	    <table width="307" border="0" align="center" id="userTable" cellspacing="15">
          <tr>
            <td class="tdLbl" align="right">用户名：</td>
            <td><input name="urUserName" type="text" id="urUserName" disabled="true"/></td>
          </tr>
          <tr>
            <td class="tdLbl" align="right" >邮箱：</td>
            <td><input name="email" type="text" id="email" disabled="true"/></td>
          </tr>
          <tr>
            <td class="tdLbl" align="right" >账户余额：</td>
            <td><input name="account" type="text" id="account" disabled="true"/></td>
          </tr>
          <tr>
            <td class="tdLbl" align="right">状态：</td>
            <td><input name="status" type="text" id="status" disabled="true"/></td>
          </tr>
          <tr>
            <td class="tdLbl" align="right">性别：</td>
            <td><label>
              <select name="sex" id="sex">
                <option value="1">男</option>
                <option value="0">女</option>
               </select>
            </label></td>
          </tr>
          <tr>
            <td class="tdLbl" align="right">年龄：</td>
            <td><input name="age" type="text" id="age" onkeypress="return toVerifyInt(event)" /></td>
          </tr>
          <tr>
            <td colspan="2" align="center"><input name="updateBtn" type="button" id="updateBtn" value="修改资料" onclick="updateMsg('${sessionScope.userId}')" /></td>
          </tr>
        </table>
      </form>
    </div>
  </body>
  <script type="text/javascript">
  	getMsg('${sessionScope.userId}');
  </script>
</html>
