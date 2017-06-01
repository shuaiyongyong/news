$("#leftSider").accordion({
	fit:true,
});

var urlJson = {"编辑新闻":"editNews.jsp","添加新闻":"addNews.jsp","添加标题":"addTopic.jsp","编辑标题":"editTopic.jsp","编辑用户":"editUser.jsp","添加用户":"addUser.jsp","密码修改":"updateUser.jsp","新闻恢复":"editNewsRec.jsp"}

$(".menutree").tree({
	onClick: function(node){
		//alert(node.text);  // 在用户点击的时候提示
		// add a new tab panel    
		var nt = node.text;
		if($('#main').tabs('exists',nt )){//判断面板是否存在
			//打开显示为当前面板
			$('#main').tabs('select',nt )
		}else{
			//添加面板
			if(urlJson[nt]){
				$('#main').tabs('add',{    
				    title:nt,    
				    href:"back/" + urlJson[nt],    
				    closable:true,
				});  
			}else{
				$('#main').tabs('add',{    
				    title:nt,    
				    content:'<h1>'+ nt +'</h1>',    
				    closable:true,
				});  
			}
			
		}		
	}
});
$("#main").tabs({
	fit:true,
});

var cnid;
function openDN(id){
	cnid = id;
	$("#detailNews").dialog({
		title:'detailNews',
		width:600,
		height:300,
		left:10,
		href: 'back/detailNews.jsp',    
	    modal: true 
	});
};

function openDRN(id){
	cnid = id;
	$("#detailRecNews").dialog({
		title:'detailRecNews',
		width:600,
		height:300,
		left:10,
		href: 'back/detailRecNews.jsp',    
	    modal: true 
	});
};

function openMN(id){
	cnid = id;
	$("#modifyNews").dialog({
		title:'modifyNews',
		width:800,
		height:400,
		left:10,
		href: 'back/modifyNews.jsp'
	});
};

function openMT(id){
	ctid = id;
	$("#modifyTopic").dialog({
		title:'modifyTopic',
		width:300,
		height:200,
		left:10,
		href: 'back/modifyTopic.jsp'
	});
};

function openMU(id){
	usid = id;
	$("#modifyUser").dialog({
		title:'modifyUser',
		width:450,
		height:300,
		left:10,
		href: 'back/modifyUser.jsp'
	});
};

function closeMN(){
	$("#modifyNews").dialog("close",true);
};

function closeMT(){
	$("#modifyTopic").dialog("close",true);
};

function closeMU(){
	$("#modifyUser").dialog("close",true);
};
