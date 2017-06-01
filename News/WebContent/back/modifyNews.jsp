<%@  page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<base href="/News/">
<meta charset="UTF-8">
<title>后台管理界面</title>
</head>

<body>
		<form id="mNews" enctype="multipart/form-data" style="width: 85%; margin: 0px auto;">
			<input type="hidden" name="nid" id="mnid" />
			<p>
				<label> 主题 </label> <select name="ntid" id="ntid"></select>
			</p>
			<p>
				<label> 标题 </label> <input name="ntitle"  id="mnewsTitle" type="text" value="" class="opt_input" />
			</p>
			<p>
				<label> 作者 </label> <input name="nauthor"  id="mnewsAuthor"  type="text" value="" class="opt_input" />
			</p>
			<p>
				<label> 摘要 </label>
				<textarea name="nsummary"  id="newsSummary"  cols="40" rows="3"></textarea>
			</p>
			<p>
				<label> 内容 </label>
				<textarea name="ncontent"  id="mnewsContent"  cols="70" rows="10"></textarea>
			</p>
			<p>
				<label> 上传图片 </label> <input name="file" type="file" onchange="addPic(this)" multiple="multiple" /><br> 
				<img id="currPic" alt="图片" src="images/94337_205.jpg"  width="100">
			</p>
			<p id="pics"></p>

			<input type="button"  value="修改" onclick="mdNews()" /> 
			<input type="reset" value="重置" class="opt_sub" />
		</form>
	<script type="text/javascript" src="ueditor/ueditor.config.js"></script>
	<script type="text/javascript" src="ueditor/ueditor.all.min.js"></script>
	<script type="text/javascript" src="js/modifyNews.js"></script>
</body>
</html>
