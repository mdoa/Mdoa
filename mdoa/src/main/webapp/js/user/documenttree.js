$(function() {  
	$('#ddd').tree({
        url: "../../document/deptTree.do?getMs="+getMS(),
        method:"post",
        animate: false,
        checkbox : false,//是否显示复选框 
        cascadeCheck: false,
        dnd:true,
        onClick: function(node){
        	$("#catalogUrll").val(node.id);
        	console.log("text="+node.text+",url="+node.id+",icon="+node.iconCls,",docFlag="+node.attributes);
        },
        onCollapse:function(node){
        	$("#catalogUrll").val(node.id);
        	$.ajax({
        		data:{url:$("#catalogUrll").val(),state:"onCollapse"},
    			url:"../../document/updateTreeState.do?getMs="+getMS(),
    			method:"post"
    		})
        },
        onExpand:function(node){
        	$("#catalogUrll").val(node.id);
        	$.ajax({
        		data:{url:$("#catalogUrll").val(),state:"onExpand"},
    			url:"../../document/updateTreeState.do?getMs="+getMS(),
    			method:"post"
    		})
        },
        onDrop: function(target,source,point){
        	var targetUrl = $('#ddd').tree('getNode', target).id;
        	var targetName = $('#ddd').tree('getNode', target).text;
        	var attributes = $('#ddd').tree('getNode', target).attributes;
        	if(attributes == '1' && point == "append"){
        		alert("无效操作呦");
        		$.ajax({
        			url:"../../document/deptTree.do?getMs="+getMS(),
        			method:"post",
        			success: function(data){
        				$('#ddd').tree('reload');
        			}
        		})
        	}else{
        		$.ajax({
        			data:{targetUrl:targetUrl,targetName:targetName,sourceUrl:source.id,sourceName:source.text,point:point},
        			url:"../../document/dragDocument.do?getMs="+getMS(),
        			method:"post",
        			success: function(data){
        				$('#ddd').tree('reload');
        			}
        		})
        	}
        },
        onContextMenu : function(e,node){
        	e.preventDefault();
        	var catalogUrll = $('#catalogUrll').val(node.id);
        	$('#tg').tree('select', node.target);
        	if(node.attributes == "0"){
        		$('#mmm').menu('show', {
        			left: e.pageX,
        			top: e.pageY
        		});
        	}else{
        		$('#mmd').menu('show', {
        			left: e.pageX,
        			top: e.pageY
        		});
        	}
		}
       
    });
	//点击确定添加物品类
	$('#documentTree .addWindow .quedingadd').click(function(){
		var catalogName = $('#documentTree .addWindow .catalogName').val();
		var catalogUrll = $("#catalogUrll").val();
		var documentType = $('#documentTree .addWindow .documentType').val();
		if(documentType == "目录"){
			documentType = "0";
		}else{
			documentType = "1";
		}
		if(catalogName == null || catalogName == ""){
			alert("请输入新增文件名");
		}else{
		$.ajax({
			data:{url:catalogUrll,catalogName:catalogName,docFlag:documentType},
			url:"../../document/addDocument.do?getMs="+getMS(),
			method:"post",
			success: function(data){
				$('#ddd').tree('reload');
				$('#documentTree .addWindow').css('display','none');
			}
		})
	}
	})	
	//点击确定删除物品类
	$('#documentTree .removeWindow .quedingremove').click(function(){
		var catalogUrll = $("#catalogUrll").val();
		$.ajax({
			data:{url:catalogUrll},
			url:"../../document/removeDocument.do?getMs="+getMS(),
			method:"post",
			success: function(data){
				$('#ddd').tree('reload');
				$('#documentTree .removeWindow').css('display','none');
			}
		})
	})
	//点击确定修改物品类
	$('#documentTree .editWindow .quedingedit').click(function(){
		var catalogUrll = $("#catalogUrll").val();
		var catalogName = $('#documentTree .editWindow .catalogName').val();
		$.ajax({
			data:{url:catalogUrll,catalogName:catalogName},
			url:"../../document/updateDocument.do?getMs="+getMS(),
			method:"post",
			success: function(data){
				$('#ddd').tree('reload');
				$('#documentTree .editWindow').css('display','none');
			}
		})
	})
	$('#documentTree .addWindow .cancel').click(function(){
		$('#documentTree .addWindow').css('display','none');
	})
	$('#documentTree .removeWindow .cancel').click(function(){
		$('#documentTree .removeWindow').css('display','none');
	})
	$('#documentTree .editWindow .cancel').click(function(){
		$('#documentTree .editWindow').css('display','none');
	})
})

function add(){
	$('#documentTree .addWindow').css('display','block');
}
 
function remove(){
	$('#documentTree .removeWindow').css('display','block');
}

function edit(){
	$('#documentTree .editWindow').css('display','block');
}