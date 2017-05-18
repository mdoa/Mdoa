$(function(){
	$('#systemlogdg').parent().css('height',$('#loadarea').height()-64);
	$('#systemlogdg').datagrid({
		   url:'../../systemLog/querySystemLog.do?getMs='+getMS(),
		   rownumbers:"true",
		   pagination:true,
		   checkOnSelect:true,
		   toolbar:"#systemLog .list .invitetop",
		   method:"post",
		   fit: true,
		   columns:[[
		       {field:"ck",checkbox:true },
		       {field:"operaTimeStr",title:"操作时间",fitColumns:true,resizable:true,align:"center",sortable:true,width:130},
		       {field:"operaUserName",title:"操作员",fitColumns:true,resizable:true,align:"center",sortable:true,width:80},
		       {field:"operaPanel",title:"操作模块",fitColumns:true,resizable:true,align:"center",sortable:true,width:80},
		       {field:"operaTab",title:"操作标记",fitColumns:true,resizable:true,align:"center",sortable:true,width:80},
		       {field:"_op",title:"管理",width:100,resizable:true,align:"center",width:100,sortable:true,formatter:function(value,row,index){
		    	   var id ="'"+row.logId+"'";
		    	   var opera = '';
		    	   if(existPermission('admin:system:systemLog:delete'))
		    		   opera +='<span class="small-button delete" onclick="deleteLog('+id+')" title="删除日志"></span>';
		    	   return opera;
		       }},
		    ]]
	});
	
	//查询的点击事件
	$('#systemLog .list a[name=querySystemLog]').click(function(){
		
		var operaTimeStartStr = $('#systemLog .list input[name=operaTimeStartStr]').val();
		var operaTimeEndStr = $('#systemLog .list input[name=operaTimeEndStr]').val();
		var operaUserName = $('#systemLog .list input[name=operaUserName]').val();
		var operaTab = $('#systemLog .list input[name=operaTab]').val();
		var operaPanel = $('#systemLog .list input[name=operaPanel]').val();
		if(operaTimeStartStr != null && operaTimeStartStr != '')
			operaTimeStartStr +=" 00:00:00";
		if(operaTimeEndStr != null && operaTimeEndStr != '')
			operaTimeEndStr +=" 23:59:59";
		var info = {
			operaTimeStartStr:operaTimeStartStr,
			operaTimeEndStr:operaTimeEndStr,
			operaUserName:operaUserName,
			operaTab:operaTab,
			operaPanel:operaPanel
		};
		$('#systemlogdg').datagrid({
			queryParams:info
		});
	})
	//批量删除
	$('#systemLog .list .maintop .add').click(function(){
		var data = $('#systemlogdg').datagrid('getSelections');
		var logId = "";
		for(var i=0;i<data.length;i++){
			logId += data[i].logId+",";
		}
		if(logId.length >0)
			logId = logId.substring(0,logId.length-1);
		ui.confirm('确认批量删除选中的日志吗',function(z){
			if(z){
				$.ajax({
					data:{logId:logId},
					url:'../../systemLog/deleteSystemLog.do?getMs='+getMS(),
					success:function(data){
						if(data == 200){
							windowControl("删除成功");
							$('#systemlogdg').datagrid('reload');
						}else if(data == 400){
							windowControl("数据异常");
						}else{
							windowControl("服务器异常");
						}
					}
				});
			}
		},false);
	});
});

//单个删除
function deleteLog(id){
	ui.confirm('确认删除该日志吗',function(z){
		if(z){
			$.ajax({
				data:{logId:id},
				url:'../../systemLog/deleteSystemLog.do?getMs='+getMS(),
				success:function(data){
					if(data == 200){
						windowControl("删除成功");
						$('#systemlogdg').datagrid('reload');
					}else if(data == 400){
						windowControl("数据异常");
					}else{
						windowControl("服务器异常");
					}
				}
			});
		}
	},false);
}