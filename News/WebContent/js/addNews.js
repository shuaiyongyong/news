$.get("topic/list", function(d) {
		//alert(JSON.stringify(d));//把js的json对象转换成json字符串
		for (var i = 0; i < d.rows.length; i++) {
			$("#antid").append(
					"<option value='" + d.rows[i].tid + "'  >"
							+ d.rows[i].tname + "</option>");
		}
	}, "json");

function addPic(obj) {
	var picURI = window.URL.createObjectURL(obj.files[0]);// 根据图片资源创建一个图片路径
	$("#acurrPic").attr("src", picURI);
}

UE.getEditor("anewsContent");

function addNews() {
	// jquery对象转dom对象 jqueryObj[0] ==> $("#dd")[0]
	// dom对象转jquery对象$(domObj) ==>$(document....)
	var formData = new FormData($("#aNews")[0]);
	$.ajax({
		url : 'news/add',
		type : 'POST',
		data : formData,
		async : false,
		cache : false,
		contentType : false,
		processData : false,
		success : function(returndata) {
			 if(returndata){
					/*$('#newsInfo').datagrid("reload");
					$("#anewsTitle").val("");
					$("#anewsAuthor").val("");
					$("#anewsSummary").val("");
					$("#anewsContent").html("");	*/	
				 $("#aNews").get(0).reset();
				 $("#anewsContent").html("");
				}
			$.messager.show({
				title : '添加新闻',
				msg :returndata.trim() == "true" ? "添加成功..." : "添加失败！！！",
				showType : 'show',
				style : {
					top : document.body.scrollTop
							+ document.documentElement.scrollTop + 40,
				}
			});
		}
	});
	return false;
}