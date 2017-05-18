$(function(){
	//公文树
	$('#ddd').tree({
        url: "../../document/deptTree.do?getMs="+getMS(),
        method:"post",
        animate: false,
        checkbox : false,//是否显示复选框 
        cascadeCheck: false,
        dnd:true,
        onCollapse:function(node){
        	$("#catalogUrl").val(node.id);
        	$.ajax({
        		data:{url:$("#catalogUrl").val(),state:"onCollapse"},
    			url:"../../document/updateTreeState.do?getMs="+getMS(),
    			method:"post"
    		})
        },
        onExpand:function(node){
        	$("#catalogUrl").val(node.id);
        	$.ajax({
        		data:{url:$("#catalogUrl").val(),state:"onExpand"},
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
        	var catalogUrl = $('#catalogUrl').val(node.id);
        	$("#catalogUrl").attr("docFlag",node.attributes);
        	$('#ddd').tree('select', node.target);
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
		
	//点击确定删除物品类
	$('#documentManage .removeWindow .quedingremove').click(function(){
		var catalogUrl = $("#catalogUrl").val();
		$.ajax({
			data:{url:catalogUrl},
			url:"../../document/removeDocument.do?getMs="+getMS(),
			method:"post",
			success: function(data){
				$('#ddd').tree('reload');
				$('#documentManage .removeWindow .popuparea input[type=text]').val('');
				$('#documentManage .removeWindow').css('display','none');
			}
		})
	})
	//点击确定修改物品类
	$('#documentManage .editWindow .quedingedit').click(function(){
		var catalogUrl = $("#catalogUrl").val();
		var catalogName = $('#documentManage .editWindow .catalogName').val();
		$.ajax({
			data:{url:catalogUrl,catalogName:catalogName},
			url:"../../document/updateDocument.do?getMs="+getMS(),
			method:"post",
			success: function(data){
				$('#ddd').tree('reload');
				$('#documentManage .editWindow .popuparea input[type=text]').val('');
				$('#documentManage .editWindow').css('display','none');
			}
		})
	})
	/*-----------设置公文编辑区-----------*/
	$('#documentManage').css('height',$('#loadarea').height()-31);
	$('#documentManage .listForm').css('width',$('#loadarea').width()-202);
	/*--------------编辑公文---------------*/
	$('#documentManage .listForm .editDocument').click(function(){
		$('#documentManage .editorarea').css('display','block');
	});
});
/*文件添加*/
function add(){
	$('#documentManage .addWindow').css('display','block');
}
/*目录*/
function addcatalogue(){
	$('#documentManage .addcatalogueWindow').css('display','block');
}
/*文件删除*/
function remove(){
	$('#documentManage .removeWindow').css('display','block');
}
/*文件修改*/
function edit(){
	$('#documentManage .editWindow').css('display','block');
}