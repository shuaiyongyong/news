$('#userInfo')
		.datagrid(
				{
					fitColumns : true,
					url : 'user/list',
					singleSelect : true,
					pagination : true,
					columns : [ [
							{
								field : 'usid',
								title : '编号',
								width : 100,
								align : 'center'
							},
							{
								field : 'uname',
								title : '用户名',
								width : 200,
								align : 'center'
							},
							{
								field : 'nsummary',
								title : '操作',
								width : 100,
								align : 'center',
								formatter : function(value, row, index) {
									var str = '<a class="operatorBtn" href="javascript:void(0)" onClick="operatorFun(1,'
											+ row.usid
											+ ')">归档</a>&nbsp;'
											+ '<script>$(".operatorBtn").linkbutton();</script>';
									return str;
								}
							} ] ]
				});

function operatorFun(operType, id) {
	switch (operType) {
	case 1:
		parent.stopUser(id);
		break;
	case 2:
		parent.openMU(id);
		break;
	}
};

function stopUser(id) {
	/*$.get("user/limits?usid=" + id, function(data) {
		if (data) {*/
			$.messager.confirm('停用用户', '是否确定停用此用户？', function(r) {
				if (r) {
					$.get("user/archive?usid=" + id, function(data) {
						if (data) {
							$('#userInfo').datagrid("reload");
						}
						$.messager.show({
							title : '归档新闻',
							msg : data ? "归档成功..." : "归档失败！！！",
							showType : 'show',
							style : {
								top : document.body.scrollTop
										+ document.documentElement.scrollTop
										+ 40,
							}
						});
					}, "json");
				}
			});
		/*} else {
			alert("对不起！您的权限不够!!");
			
		}
	}, "json");*/

};
