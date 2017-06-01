//做异步用户名校验
		 function checkUname(obj){
			 var uname =$("#aUser").serialize();
			 $.post("user/check",uname ,function(data) {//data是个对象
					if(data){
						$("#unameInfo").html(" 用户名称已经被占用，请重新输入！");
						$("#unameInfo").css("color","red");
					}else{
						$("#unameInfo").html(" 用户名称可以使用！");
						$("#unameInfo").css("color","green");
					}
				}, "json");
				return false;
		 } 
		 
		
		 function addUser() {
				var formData = new FormData($("#aUser")[0]);
				$.ajax({
					url : ' user/add',
					type : 'POST',
					data : formData,
					async : false,
					cache : false,
					contentType : false,
					processData : false,
					success : function(returndata) {
						 if(returndata){
								$('#userInfo').datagrid("reload");
								$("#aUser").get(0).reset();
							}
						$.messager.show({
							title : '添加用户',
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