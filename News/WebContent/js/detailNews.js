$.get("news/detail?cnid=" + cnid,function(data){
	$("#newsTitle").html(data.ntitle);
	$("#newsCreateDate").html(data.ncreateDate);
	$("#newsAuthor").html(data.nauthor);
	$("#newsContent").html(data.ncontent);
},"json");