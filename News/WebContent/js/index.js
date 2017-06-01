//异步取到新闻分页数据
function getNews(pageNum) {
	/*关注:
			 	1.请求路径和请求参数  :news/list?page=" + pageNum

			   2.响应数据:function(data)中的data
	 */
	$.get("news/list?page=" + pageNum,function(data) {
		$("#newsList").empty();

		var newses = data.rows;
		for (var i = 0; i < newses.length; i++) {
			if (i % 5 == 0 && i != 0) {
				$("#newsList").append("<li class='space'></li>");
			}
			$("#newsList").append("<li><a href='news/read?cnid="+newses[i].nid+"'>"+ 
					newses[i].ntitle+ "</a> <span>"+ newses[i].ncreateDate+ "</span></li>");
			$("#pagation").empty();
			$("#pagation").append("当前页数:["+ data.currPage+ "/"+ data.totalPage+ "]&nbsp;&nbsp; "
					+ "<a href='javascript:void(0)' onclick='getNews(1)'>首页</a>&nbsp;&nbsp;"
					+ "<a href='javascript:void(0)' onclick='getNews("+ (data.currPage - 1)+ ")'>上一页</a>&nbsp;&nbsp; "
					+ "<a href='javascript:void(0)' onclick='getNews("+ (data.currPage + 1)+ ")'>下一页</a>&nbsp;&nbsp;"
					+ "<a href='javascript:void(0)' onclick='getNews("+ data.totalPage+ ")'>末页</a>&nbsp;&nbsp;");
		}
	}, "json");
}
getNews(1);//默认第一次触发

//2.异步取到所有主题信息
$.post("topic/list", function(data) {//data是个对象
	$("#class_month").empty();
	//alert(data.rows.length);
	//alert(JSON.stringify(data.rows));
	var pageNum = 1;
	for (var i = 0; i < data.rows.length; i++) {
		$("#class_month").append("<a href='javascript:void(0)' onclick='getNew("+data.rows[i].tid+","+pageNum+")' ><b >" + data.rows[i].tname + " </b></a>");
	}
}, "json");

//异步取到根据新闻主题类型的所有新闻信息
function getNew(tid,pageNum) {
	
	$.get("news/tlist?tid="+tid+"&page=" + pageNum,function(data) {
		$("#newsList").empty();

		var newses = data.rows;
		//alert(newses+ "----" );
		for (var i = 0; i < newses.length; i++) {
			if (i % 5 == 0 && i != 0) {
				$("#newsList").append("<li class='space'></li>");
			}
			$("#newsList").append("<li><a href='news/read?cnid="+newses[i].nid+"'>"+ 
					newses[i].ntitle+ "</a> <span>"+ newses[i].ncreateDate+ "</span></li>");
			$("#pagation").empty();
			$("#pagation").append("当前页数:["+ data.currPage+ "/"+ data.totalPage+ "]&nbsp;&nbsp; "
					+ "<a href='javascript:void(0)' onclick='getNew(1)'>首页</a>&nbsp;&nbsp;"
					+ "<a href='javascript:void(0)' onclick='getNew("+ (data.currPage - 1)+ ")'>上一页</a>&nbsp;&nbsp; "
					+ "<a href='javascript:void(0)' onclick='getNew("+ (data.currPage + 1)+ ")'>下一页</a>&nbsp;&nbsp;"
					+ "<a href='javascript:void(0)' onclick='getNew("+ data.totalPage+ ")'>末页</a>&nbsp;&nbsp;");
		}
	}, "json");
}

//3.异步取到根据新闻类型的新闻信息	
$("#china").empty();
$("#inter").empty();
$("#happy").empty();
$.get("news/typeNews", function(data) {
	for (var i = 0; i < data.length; i++) {
		if (data[i].ntid == 1) {
			$("#china").append("<li><a href='news/read?cnid="+data[i].nid+"'><b>" + data[i].ntitle+ "</b></a></li>");
		} else if (data[i].ntid == 2) {
			$("#inter").append("<li><a href='news/read?cnid="+data[i].nid+"'><b>" + data[i].ntitle+ "</b></a></li>");
		}
		if (data[i].ntid == 5) {
			$("#happy").append("<li><a href='news/read?cnid="+data[i].nid+"'><b>" + data[i].ntitle+ "</b></a></li>");
		}
	}
}, "json");

//异步登陆操作
function login(){
	var params=$("#loginForm").serialize();//取到表单要提交的请求数据(用户名和密码)
	$.post("user/login",params,function(data){//写上参数后，才可传递数据
		if(data){//因为是用json,所以当为字符串ture(false)时,会自动转换成boolean
			$("#loginForm").html(" <label> 欢迎"+$("#loginForm")[0].uname.value+"登陆成功，"+
					"<a href='javascript:void(0)' onclick='logout()' >注销用户 </a> "+
			"<a href='admin.jsp' >后台管理 </a></label>");
		}else{
			$("#error").html("错误");
			$("#error").css("color","red");
		}
	},"json");
	return false;
}

//注销登陆
function logout(){
	if(confirm("是否注销？")){
		$.post("user/login",function(data){
			if(data){
				$("#loginForm").html('<label> 登录名 </label>'+
						'<input required placeholder="请输入用户名" type="text" name="uname" id="uname" class="login_input" />'+
						'<label>密&#160;&#160;码 </label>'+
						'<input type="password" required placeholder="请输入用户名" name="upwd" id="upwd" class="login_input" />'+
						'<input type="submit" class="login_sub" value="登录" />'+
				'<label id="error"></label>');
			}
		},"json");
	}
}