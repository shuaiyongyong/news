$.get("news/detail?cnid=" + cnid, function(data) {
	//alert(JSON.stringify(data));//把js的json对象转换成json字符串
	$("#mnewsTitle").val(data.ntitle);
	$("#mnewsAuthor").val(data.nauthor);
	$("#mnewsContent").html(data.ncontent);
	$("#newsSummary").html(data.nsummary);
	$("#mnid").val(cnid);

	$.get("topic/list", function(d) {
		// alert(JSON.stringify(d));//把js的json对象转换成json字符串
		for (var i = 0; i < d.rows.length; i++) {
			$("#ntid").append(
					"<option value='" + d.rows[i].tid + "'  "
							+ (d.rows[i].tid == data.ntid ? "selected" : " ") + ">"
							+ d.rows[i].tname + "</option>");
		}
	}, "json");

}, "json");

function addPic(obj) {
	var picURI = window.URL.createObjectURL(obj.files[0]);// 根据图片资源创建一个图片路径
	$("#currPic").attr("src", picURI);
}

UE.getEditor("mnewsContent");

function mdNews() {
	// jquery对象转dom对象 jqueryObj[0] ==> $("#dd")[0]
	// dom对象转jquery对象$(domObj) ==>$(document....)
	var formData = new FormData($("#mNews")[0]);
	$.ajax({
		url : 'news/modify',
		type : 'POST',
		data : formData,
		async : false,
		cache : false,
		contentType : false,
		processData : false,
		success : function(returndata) {
			if (returndata) {
				parent.closeMN();
				$('#newsInfo').datagrid("reload");
			}
			$.messager.show({
				title : '修改新闻',
				msg : returndata ? "修改成功..." : "修改失败！！！",
				showType : 'show',
				style : {
					top : document.body.scrollTop
							+ document.documentElement.scrollTop + 40,
				}
			});
		}
	});
}