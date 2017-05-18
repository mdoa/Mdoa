$(function(){
	/*设置页面高100%*/
	$('#repertorylist').css('height',$('#loadarea').height()-31+'px');	
	/*设置list的宽度*/
	$('#repertorylist .listForm').css('width',$('#loadarea').width()-202+'px');
	/*设置表格的高度*/
	$('#repertorylistdg').css('height',$('#repertorylist .listForm').height()-31+'px');
	$(window).resize(function() {
		$('#repertorylist').css('height',$('#loadarea').height()-31+'px');
		$('#repertorylist .listForm').css('width',$('#loadarea').width()-202+'px');
	});	
	/*表格数据的加载*/
	$('#repertorylistdg').datagrid({
		//url:'../../repertory/selectRepertoryPosition.do?getMs='+getMS(),
		singleSelect:true,
		columns:[[
 	       // {checkbox:true},
			{field:'goodsPositionName',title:'仓位',align:'center',width:100},
	        {field:'goodsName',title:'物品名称',align:'center',width:100},
	        {field:'goodsSize',title:'物品规格',align:'center',width:100},
			{field:'totalNumber',title:'总数量',align:'center',width:80},
			{field:'goodsNumber',title:'当前仓位数量',align:'center',width:80,formatter:function(value,row,index){
				var goodsNumber = row.goodsNumber;
				if(goodsNumber == null){
					return '0';
				}else{
					return value;
				}
				
			}},
			//{field:'warnNumber',title:'警示数量',align:'center',width:80},
			{field:'unit',title:'单位',align:'center',width:50},
			{field:'repertoryName',title:'仓库',align:'center',width:100},
			{field:'_op',title:'操作',width:100,align:'center',
				formatter:function(value,row,index){
					var gpId = "'"+row.goodsPositionId+"'";
					var gpName = "'"+row.goodsPositionName+"'";
					var gName = "'"+row.goodsName+"'";
					var num = "'"+row.totalNumber+"'";
					var rName = "'"+row.repertoryName+"'";
					var gId = "'"+row.goodsId+"'";
					var goodsNumber = "'"+row.goodsNumber+"'";
					if(goodsNumber='undefined'){
						goodsNumber = '0';
					}
					return '<span class="small-button edit" title="修改仓位" onclick="repertorylistupDate('+gpId+','+gpName+','+gName+','+num+','+rName+','+gId+')"></span>'
						+'<span class="small-button delete" title="删除仓位" onclick="repertorylistDel('+gpId+','+goodsNumber+')" delId="'+gpId+'"></span>';
			}}
	    ]],
	    toolbar:'#repertorylist .queryForm',
	    pagination:true
	});
	/*条件查询*/
	$('#repertorylist .query').click(function(){
		Query()
	});
	/*刷新*/
	$('#repertorylist .refresh').click(function(){
		$('#repertorylistdg').datagrid('reload');
		$('#repertorylist .queryForm input').val('');
		$('#repertorylist .query').val('查询');
		$('#repertorylist .queryForm select option:eq(0)').attr('selected',true);
	});
	/*-------------------------------tree---------------------------*/
	var goodsTypeIds=[];
	$('#repertorylisttg').tree({
        url: "../../treeController/queryTree.do?getMs="+getMS(),
        method:"post",
        animate: false,
        checkbox :false,//是否显示复选框  
        cascadeCheck: false,
        dnd:true,
        onClick: function(node){
        	var goodsTypeUrl =node.id;
        	$('#repertorylistdg').datagrid({
        		url:'../../repertory/selectRepertoryPosition.do?getMs='+getMS(),
        		queryParams:{goodsTypeUrl:goodsTypeUrl},
        	});
        },
        onCollapse:function(node){
        	$("#goodsTypeUrl").val(node.id);
        	$.ajax({
        		data:{goodsTypeUrl:$("#goodsTypeUrl").val(),state:"onCollapse"},
    			url:"../../treeController/updateTreeState?getMs="+getMS(),
    			method:"post"
    		})
        },
        onExpand:function(node){
        	$("#goodsTypeUrl").val(node.id);
        	$.ajax({
        		data:{goodsTypeUrl:$("#goodsTypeUrl").val(),state:"onExpand"},
    			url:"../../treeController/updateTreeState?getMs="+getMS(),
    			method:"post"
    		})
        },
        onDrop: function(target,source,point){
        	var targetUrl = $('#repertorylisttg').tree('getNode', target).id;
        	var targetName = $('#repertorylisttg').tree('getNode', target).text;
        	//console.log("targetId="+targetId+"source="+source.text+"point="+point);
        	$.ajax({
    			data:{targetUrl:targetUrl,targetName:targetName,sourceUrl:source.id,sourceName:source.text,point:point},
    			url:"../../treeController/dragRepertoryType?getMs="+getMS(),
    			method:"post",
    			success: function(data){
    				$('#repertorylisttg').tree('reload');
    			}
    		})
        },
        onCheck: function(node,checked){
        	if(checked == true){
        		goodsTypeIds.push(node.id);
        	}else if(checked == false){
        		for(var i=0;i<goodsTypeIds.length;i++){
        			if(goodsTypeIds[i] == node.id){
        				goodsTypeIds.splice(i,1);
        			}
        		}
        	}
        },
        /*onContextMenu : function(e,node){
        	e.preventDefault();
        	var goodsTypeUrl = $('#repertorylist .goodsTypeUrl').val(node.id);
        	$('#repertorylisttg').tree('select', node.target);
    		$('#repertorylistmm').menu('show', {
    			left: e.pageX,
    			top: e.pageY
    		});
		}*/
    });
	
	//点击确定添加物品类
	/*$('#repertorylist .addWindow .quedingadd').click(function(){
		var goodsTypeName = $('#findgoods .addWindow .goodsTypeName').val();
		var goodsTypeUrl = $("#findgoods .goodsTypeUrl").val();
		
		if(goodsTypeName == null || goodsTypeName == ""){
			alert("请输入新增类名");
		}else{
			$.ajax({
				data:{goodsTypeUrl:goodsTypeUrl,goodsTypeName:goodsTypeName},
				url:"../../treeController/addRepertoryType?getMs="+getMS(),
				method:"post",
				success: function(data){
					$('#repertorylisttg').tree('reload');
					$('#repertorylist .addWindow').css('display','none');
					$('#repertorylist .addWindow input[type=text]').val('');
				}
			});
		}
	});
	//点击确定删除物品类
	$('#repertorylist .removeWindow .quedingremove').click(function(){
		var goodsTypeUrl = $("#findgoods .goodsTypeUrl").val();
		$.ajax({
			data:{goodsTypeUrl:goodsTypeUrl},
			url:"../../treeController/removeRepertoryType?getMs="+getMS(),
			method:"post",
			success: function(data){
				$('#repertorylisttg').tree('reload');
				$('#repertorylist .removeWindow').css('display','none');
				$('#repertorylist .removeWindow input[type=text]').val('');
			}
		})
	});
	//点击确定修改物品类
	$('#repertorylist .editWindow .quedingedit').click(function(){
		var goodsTypeUrl = $("#findgoods .goodsTypeUrl").val();
		var goodsTypeName = $('#findgoods .editWindow .goodsTypeName').val();
		$.ajax({
			data:{goodsTypeUrl:goodsTypeUrl,goodsTypeName:goodsTypeName},
			url:"../../treeController/editRepertoryType?getMs="+getMS(),
			method:"post",
			success: function(data){
				$('#repertorylisttg').tree('reload');
				$('#repertorylist .editWindow').css('display','none');
				$('#repertorylist .editWindow input[type=text]').val('');
			}
		})
	});*/
});
/*************************下拉框选择仓库信息********************************/
$.ajax({
	url:'../../repertory/selectRepertoryType.do?getMs='+getMS(),
	type:'post',
	success:function(data){
		var str = "<option value=''></option>";
		var data = eval('(' + data + ')'); 
		for(var i=0;i<data.length;i++){
			str += "<option class='repertoryName' value=" + data[i].repertoryId + ">" + data[i].repertoryName + "</option>";  
		}
	$('#repertorylist .listForm .repertoryName').html(str);
	},
	error:function(error){
		windowControl(error.status);
	}
})
/*---------------------------------tree函数-----------------------------------*/
/*function add(){
	$('#repertorylist .addWindow').css('display','block');
}
function remove(){
	$('#repertorylist .removeWindow').css('display','block');
}
function edit(){
	$('#repertorylist .editWindow').css('display','block');
}*/
/*定义一个全局变量*/
var dataInfo;
/*------操作函数---------*/
/*仓位信息的修改*/
function repertorylistupDate(gpId,gpName,gName,num,rName,gId){
	$('#repertorylist .popups .update').css('display','block');
	$('#repertorylist .popups .update .gpName').val(gpName);
	$('#repertorylist .popups .update .gName').val(gName);
	$('#repertorylist .popups .update .num').val(num);
	$('#repertorylist .popups .update .rName').val(rName);
	$('#repertorylist .popups .update .confirm').unbind('click');
	$('#repertorylist .popups .update .confirm').click(function(){
		var goodsPositionName = $('#repertorylist .popups .update .gpName').val();
		var totalNumber = $('#repertorylist .popups .update .num').val();
		var dataInfo = {
			goodsPositionId:gpId,
			goodsPositionName:goodsPositionName
			//goodsName:gName,
			//repertoryName:rName,
		}
		$.ajax({
			data:dataInfo,
			url:'../../repertory/updateRepertoryPosition.do?getMs='+getMS(),
			type:'post',
			success:function(data){
				console.log(data);
				if(data == 500){
					windowControl('服务器异常');
				}else if(data == 400){
					windowControl('仓位修改信息失败');
				}else{
					windowControl('仓位修改信息成功');
					$('#repertorylist .popups .update').css('display','none');
					$('#repertorylist .popups .update .popuparea input[type=text]').val('');
					$('#repertorylist .popups .update .popuparea textarea').val('');
					$('#repertorylistdg').datagrid('reload');
					
				}
				
			},
			error:function(err){
				windowControl('网络异常');
			}
		});
	});
}
/*仓位信息删除*/
function repertorylistDel(gpId,goodsNumber){
	if(goodsNumber <= 0){
		var dataInfo = {goodsPositionId:gpId};
		ui.confirm('确定删除该仓位吗?',function(z){
			if(z){
				$.ajax({
					data:dataInfo,
					url:'../../repertory/deleteRepertoryPosition.do?getMs='+getMS(),
					type:'post',
					success:function(data){
						if(data == 500){
							windowControl('服务器异常');
						}else if(data == 400){
							windowControl('删除仓位失败');
						}else{
							windowControl('删除仓位成功');
							$('#repertorylistdg').datagrid("reload");
						}
					},
					error:function(err){
						windowControl('网络异常');
					}
				});
			}
		},false);
	}else{
		windowControl('该仓位还有物品不能删除');
	}
	
}
/* 查询*/
function Query(){
	var goodsName = $.trim($('#repertorylist .goodsName').val());
	var gooosSize = $.trim($('#repertorylist .goodsSize').val());
	var goodsNumSm = $.trim($('#repertorylist .goodsNumSm').val());
	var goodsNumBg = $.trim($('#repertorylist .goodsNumBg').val());
	var goodsPositionName = $.trim($('#repertorylist .goodsPosition').val());
	var repertoryId = $('#repertorylist .listForm .repertoryName').val();
	var dataInfo 
	/*判断数量区间是否只有写了一个 写了一个补齐*/
	if(goodsNumSm == ''&&goodsNumBg >= 0){  
		goodsNumSm = '0';
	}else if(goodsNumSm >= 0&&goodsNumBg == ''){
		goodsNumSm = '100000000000000000';
	}
	if(repertoryId == '--请选择--'){
		dataInfo = {
			goodsName:goodsName,
			goodsSize:gooosSize,
			beginNumber:goodsNumSm,
			endNumber:goodsNumBg,
			goodsPositionName:goodsPositionName,
		};
	}else {
		dataInfo = {
			goodsName:goodsName,
			goodsSize:gooosSize,
			beginNumber:goodsNumSm,
			endNumber:goodsNumBg,
			goodsPositionName:goodsPositionName,
			repertoryId:repertoryId
		};
	}
	$('#repertorylistdg').datagrid({
		url:"../../repertory/selectRepertoryPosition?getMs="+getMS(),
		queryParams:dataInfo
	});
}