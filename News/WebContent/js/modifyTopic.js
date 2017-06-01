$.get("topic/detail?ctid=" + ctid, function(data) {
	$("#mtname").val(data.tname);
	$("#mtid").val(ctid);

}, "json");

//做异步用户名校验
function checkTname(obj){
	 var tname =$("#aTopic").serialize();
	 $.post("topic/check",tname ,function(data) {//data是个对象
			if(data){
				$("#tnameInfo").html(" 主题名称已经被占用，请重新选择一个！");
				$("#tnameInfo").css("color","red");
			}else{
				$("#tnameInfo").html(" 主题名称可以使用！");
				$("#tnameInfo").css("color","green");
			}
		}, "json");
		return false;
			
	 };

function mdTopic() {
	var params=$("#mTopic").serialize();
	$.post("topic/modify",params ,function(data) {//data是个对象
		if(data){
			alert("修改成功！！")	
			$('#topicInfo').datagrid("reload");
			parent.closeMT();
		}else{
			alert("修改失败！！")	
		}
	}, "json");
	return false;
}