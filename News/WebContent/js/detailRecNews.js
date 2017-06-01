$.get("news/rdetail?cnid=" + cnid,function(data){
	$("#RnewsTitle").html(data.ntitle);
	$("#RnewsCreateDate").html(data.ncreateDate);
	$("#RnewsAuthor").html(data.nauthor);
	$("#RnewsContent").html(data.ncontent);
},"json");