<%@  page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<base href="/News/">
<meta charset="UTF-8">
<title>后台管理界面</title>
</head>

<body>
		<form  id="mTopic" style="width: 85%; margin: 0px auto;">
			<input type="hidden" name="tid" id="mtid" />
			<label> 标题名称 </label> 
			<input name="mtname"  id="mtname" type="text" value=""   placeholder="请输入主题名称"  onblur="checkTname(this)"/>
			<label id="tnameInfo"></label><br/><br/>
			<input type="button"  value="修改" onclick="mdTopic()" /> 
			<input type="reset" value="重置" class="opt_sub" />
		</form>
	<script type="text/javascript" src="js/modifyTopic.js"></script>
</body>
</html>
