<%@  page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<base href="/News/">
<meta charset="UTF-8">
<title>后台管理界面</title>
</head>

<body>
		<form id="aUser" onsubmit="return addUser()" style="width: 85%; margin: 0px auto;">
				<br><br>
				<label> 用户名 ：</label> 
				<input name="uname" id="uname" type="text"  placeholder="请输入用户名称"  onblur="checkUname(this)"/>
				<label id="unameInfo"></label><br><br>
				<label> 密&nbsp;&nbsp;&nbsp;码 ：</label> 
				<input name="upwd" type="password" id="upwd" /><br><br>
				<input type="submit" value="提交" class="opt_sub" /> 
				<input type="reset" value="重置" class="opt_sub" />
			</form>
	<script type="text/javascript" src="js/addUser.js"></script>
</body>
</html>
