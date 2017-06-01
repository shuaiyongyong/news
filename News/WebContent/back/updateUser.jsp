<%@  page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<base href="/News/">
<meta charset="UTF-8">
<title>后台管理界面</title>
</head>

<body>
		<form  id="mUser" style="width: 85%; margin: 0px auto;">
			<input type="hidden" name="usid" id="musid" /><br>
			<label> 用 户 名 ：</label> 
			<input name="muname"  id="muname" type="text" value="${ sessionScope.loginUser }"  />
			<br/><br/>
			<label> 原始密码：</label>
			<input name="oldpwd"  id="oldpwd" type="password" value=""  onblur="checkOupwd(this)" />
			<label id="opwd"></label><br><br>
			<label> 新 密 码 ：</label>
			<input name="newpwd"  id="newpwd" type="password" value=""  onblur="checkNupwd(this)" />
			<label id="upwd"></label><br><br>
			<label> 确认密码：</label>
			<input name="mnewpwd"  id="mnewpwd" type="password" value=""  onblur="checkUpwd(this)" />
			<label id="upwd2"></label><br><br>
			<input type="button"  value="修改" onclick="mdUser()" /> 
			<input type="reset" value="重置" class="opt_sub" />
		</form>
	<script type="text/javascript" src="js/updateUser.js"></script>
</body>
</html>
