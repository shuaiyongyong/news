$('#topicInfo').datagrid({    
	fitColumns : true,
    url:'topic/list',    
    singleSelect:true,
    pagination : true,
    columns:[[    
        {
        	field:'tid',
        	title:'编号',
        	width:100,
        	align:'center'
        },  {
        	field:'tname',
        	title:'标题名称',
        	width:200,
        	align:'center'
        }, {
        	field:'nsummary',
        	title:'操作',
        	width:100,
        	align:'center',
        	formatter: function(value,row,index){
        		var str ='<a class="operatorBtn" href="javascript:void(0)" onClick="operatorFun(2,'
        			+row.tid+')">修改</a>&nbsp;'
        			+'<script>$(".operatorBtn").linkbutton();</script>';
        		return str;
        	}
        } ]]    
});  

function operatorFun(operType,id){
	switch (operType) {
	case 1:	
		parent.stopTopic(id);
		break;
	case 2:	
		parent.openMT(id);
		break;
	}
};

function stopTopic(id){
	$.messager.confirm('停用标题', '是否确定停用此标题？', function(r){
		if (r){		
		   $.get("topic/archive?tid=" + id ,function(data){
			   if(data){
					$('#topicInfo').datagrid("reload");
				}
			   $.messager.show({
					title : '归档新闻',
					msg : data ? "归档成功..." : "归档失败！！！",
					showType : 'show',
					style : {
						top : document.body.scrollTop
								+ document.documentElement.scrollTop + 40,
					}
				});
		   },"json");
		}
	});


};
