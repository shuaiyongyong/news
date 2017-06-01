$('#newsRecInfo').datagrid({    
	fitColumns : true,
    url:'news/reclist',    
    singleSelect:true,
    pagination : true,
    columns:[[    
        {
        	field:'nid',
        	title:'编号',
        	width:50,
        	align:'center'
        },  {
        	field:'ntitle',
        	title:'标题',
        	width:155,
        	align:'center'
        }, {
        	field:'nauthor',
        	title:'作者',
        	width:50,
        	align:'center'
        }, {
        	field:'ncreateDate',
        	title:'创建日期',
        	width:80,
        	align:'center'
        },{
        	field:'nsummary',
        	title:'操作',
        	width:60,
        	align:'center',
        	formatter: function(value,row,index){
        		var str ='<a class="operatorBtn" href="javascript:void(0)" onClick="operatorFun(1,'
        			+row.nid+')">恢复</a>&nbsp;'
        			+'<a class="operatorBtn" href="javascript:void(0)" onClick="operatorFun(2,'
        			+row.nid+')">详情</a>&nbsp;'
        			+'<script>$(".operatorBtn").linkbutton();</script>';
        		return str;
        	}
        } ]]    
});  

function operatorFun(operType,id){
	switch (operType) {
	case 1:	
		parent.recoverNews(id);
		break;
	case 2:	
		parent.openDRN(id);
		break;
	}
};


function recoverNews(id){
	$.messager.confirm('停用新闻', '是否确定恢复此新闻？', function(r){
		if (r){		
		   $.get("news/recover?cnid=" + id ,function(data){
			   if(data){
					$('#newsRecInfo').datagrid("reload");
					$('#newsInfo').datagrid("reload");
				}
			   $.messager.show({
					title : '恢复新闻',
					msg : data ? "恢复成功..." : "恢复失败！！！",
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
