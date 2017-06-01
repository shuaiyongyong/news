<%@  page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<base href="/News/">
<meta charset="UTF-8">
<title>后台管理界面</title>
</head>

<body>
		<form id="aTopic" onsubmit="return addTopic()" style="width: 85%; margin: 0px auto;">
				<label> 主题名称 </label> 
				<input id="tname" name="tname" type="text"  placeholder="请输入主题名称"  onblur="checkTname(this)"/>
				<label id="tnameInfo"></label><br><br>
				<input type="submit" value="提交" class="opt_sub" /> 
				<input type="reset" value="重置" class="opt_sub" />
			</form>
	<script type="text/javascript" src="js/addTopic.js"></script>
</body>
</html>
