/*var muname = $("#muname").val();
$.get("user/namedetail?uname=" + muname, function(data) {
	$("#musid").val(data.usid);
}, "json");
*/

/*function checkOupwd(obj){
	var params=$("#mUser").serialize();
	$.post("user/upcheck",params ,function(data) {//data是个对象
		if(data){
			$("#opwd").html(" ");
			$("#opwd").css("color","green");
		}else{
			 $("#opwd").html("密码输入错误！！！");
			 $("#opwd").css("color","red");
		}
	}, "json");
	return false;
};*/
//做异步用户名校验
function checkNupwd(obj){
	 var oldpwd =$("#oldpwd").val();
	 var newpwd =$("#newpwd").val();
	 if(oldpwd == newpwd){
		 $("#upwd").html(" 原始密码与新密码相同！请重新输入！！");
		 $("#upwd").css("color","red");
	 }else{
		 $("#upwd").html("");
		$("#upwd").css("color","green");
	 }		
 };
	 
	 function checkUpwd(obj){
		 var newpwd =$("#newpwd").val();
		 var mnewpwd =$("#mnewpwd").val();
		 if(newpwd != mnewpwd){
			 $("#upwd2").html(" 两次密码不相同！请重新输入！！");
			 $("#upwd2").css("color","red");
		 }else{
			 $("#upwd2").html("");
			$("#upwd2").css("color","green");
		 }		
 };

function mdUser() {
	var params=$("#mUser").serialize();

	$.post("user/modify",params ,function(data) {//data是个对象
		if(data){
			alert("修改成功！！")	
			$('#userInfo').datagrid("reload");
			parent.closeMU();
			window.location.href="/News/index.jsp";		
		}else{
			alert("修改失败！！")	
		}
	}, "json");
	return false;
}