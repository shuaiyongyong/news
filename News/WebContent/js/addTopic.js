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
			 
		
		 function addTopic() {
				var formData = new FormData($("#aTopic")[0]);
				//alert(JSON.stringify(formData));
				$.ajax({
					url : ' topic/add',
					type : 'POST',
					data : formData,
					async : false,
					cache : false,
					contentType : false,
					processData : false,
					success : function(returndata) {
						 if(returndata){
								$('#topicInfo').datagrid("reload");
								 $("#aTopic").get(0).reset();
							}
						$.messager.show({
							title : '添加主题',
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