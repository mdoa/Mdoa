//=================================================奖惩类型页面============================================================================
$(function(){
	if(existPermission('admin:personnel:performanceManage:rewardPunishType:add'))
		$('#rewardPunishType .maintop').append('<div><span class="maintopicon mainicon2"></span> <span class="add">添加</span></div>');

$('#rewardPunishTypedg').datagrid({
	  // url:'../../personnel/selectAwardPunishTypeByTime.do?getMs='+getMS(), 
	   rownumbers:"true",
	   singleSelect:true,
	   pagination:true,
	   toolbar:"#rewardPunishType .invitetop",
	   method:"post",
	   fit: true, 
	   columns:[[
	      // {field:"ck",checkbox:true },
	       {field:"typeName",title:"类型名称",fitColumns:true,resizable:true,align:"center",sortable:true,width:80},
	       {field:"solution",title:"奖惩办法",fitColumns:true,resizable:true,align:"center",sortable:true,width:120},
	       {field:"strCreateTime",title:"创建时间",fitColumns:true,resizable:true,align:"center",sortable:true,width:150},
	       {field:"strUpdateTime",title:"更新时间",fitColumns:true,resizable:true,align:"center",sortable:true,width:150},
	       {field:"_op",title:"管理",width:100,resizable:true,align:"center",sortable:true,formatter:function(value,row,index){
	    	   var id = "'"+row.awardPunishTypeId+"'";
	    	   var opera = '';
	    		  if(existPermission('admin:personnel:performanceManage:rewardPunishType:update'))
	    				opera +='<span class="small-button edit" title="修改" onclick="updatePunishType('+id+')"></span>';
	    		  if(existPermission('admin:personnel:performanceManage:rewardPunishType:delete'))
	    			  opera +='<span class="small-button delete" title="删除" onclick="deletePunishType('+id+')"></span>';
	    		 return opera;
	    	   //return '<span class="small-button edit" title="修改" onclick="updatePunishType('+id+')"></span><span class="small-button delete" title="删除" onclick="deletePunishType('+id+')"></span>';
	       }},
	  ]]		
	});	
});
/*********************条件查询奖惩类型信息******************************/
$(function(){
	$('#rewardPunishType .selectByName').click(function(){
		var typename1 = $('#rewardPunishType .invitetop .typeName').val();
		windowControl(typename1);
		$('#rewardPunishTypedg').datagrid({
			url:'../../personnel/selectAwardPunishTypeByTime.do?getMs='+getMS(),
			method:"post",
			queryParams: {
				typeName:typename1,
			},
		});	
	})
/*********************添加奖惩类型信息******************************/	
	$('#rewardPunishType .maintop .add').click(function(){
		$("#rewardPunishType .saveRewardPunishType").css("display","block");
	})
	$('#rewardPunishType .saveRewardPunishType .confirm').click(function(){
		var typeName = $('#rewardPunishType .saveRewardPunishType .typeName').val();
		var solution = $('#rewardPunishType .saveRewardPunishType .solution').val();
		if(typeName ==''|| typeName == null){			
			windowControl("类型名字不能为空");
		}else if(solution ==''|| solution == null){			
			windowControl("奖惩办法不能为空");
		}else{
			$.ajax({
				data:{typeName:typeName,solution:solution,},
				method:"post",
				url:"../../personnel/insertAwardPunishType?getMs="+getMS(),
				success: function(data){
					if(data == 200){
						$('#rewardPunishType .popups').css('display','none');
						$('#rewardPunishType .saveRewardPunishType .popuparea input').val(null);
						$('#rewardPunishType .saveRewardPunishType .popuparea textarea').val(null);
						$('#rewardPunishTypedg').datagrid({
							url:'../../personnel/selectAwardPunishTypeByTime.do?getMs='+getMS(),
						});
						windowControl('保存奖惩类型信息成功');
					}else{
						windowControl('保存奖惩类型信息失败')
					}
				},
				error:function(){
			    	windowControl("服务器未响应!");
			    }
			})		
		}	
	});	
	
})
/*********************删除奖惩类型信息******************************/	
function deletePunishType(id){
	ui.confirm('确定要删除KPI打分记录？',function(z){
		if(z){
			$.ajax({
				data:{awardPunishTypeId:id},
				url:'../../personnel/deleteAwardPunishType.do?getMs='+getMS(),
				type:'post',
				success:function(data){
					if(data == '200'){
						$("#rewardPunishType #rewardPunishTypedg").datagrid("reload");
						windowControl("删除奖惩类型成功");
					}else{
						windowControl("删除奖惩类型失败");
					}
				},
				error:function(err){
					windowControl(err.status);
				}
			});
		}
	},false);
}
/*********************修改奖惩类型信息******************************/	
function updatePunishType(id){
	$.ajax({
		data:{awardPunishTypeId:id},
		url:'../../personnel/selectAwardPunishTypeByTime.do?getMs='+getMS(),
		type:'post',
		success:function(data){
			var obj = eval('('+data+')').rows[0];
			$("#rewardPunishType .updateRewardPunishType .alreadyNumber").removeAttr('readonly');
			$("#rewardPunishType .updateRewardPunishType").css("display","block");
			$("#rewardPunishType .updateRewardPunishType .typeName").val(obj.typeName);
			$("#rewardPunishType .updateRewardPunishType .solution").val(obj.solution);
		},
		error:function(err){
			windowControl(err.status);
		}  
	})
	//把click绑定的时间解除
	$('#rewardPunishType .updateRewardPunishType .confirm').unbind('click');
	$('#rewardPunishType .updateRewardPunishType .confirm').click(function(){
		var typeName = $("#rewardPunishType .updateRewardPunishType .typeName").val();
		var solution = $("#rewardPunishType .updateRewardPunishType .solution").val();
		if(typeName ==''|| typeName == null){			
			windowControl("类型名字不能为空");
		}else if(solution ==''|| solution == null){			
			windowControl("奖惩办法不能为空");
		}else{
			$.ajax({
				data:{awardPunishTypeId:id,typeName:typeName,solution:solution,
				},
				method:"post",
				url:"../../personnel/updateAwardPunishType?getMs="+getMS(),
				success: function(data){
					if(data == 200){
						$('#rewardPunishType .popups').css('display','none');
						$('#rewardPunishType .updateRewardPunishType .popuparea input').val(null);
						$('#rewardPunishType .updateRewardPunishType .popuparea textarea').val(null);
						$('#rewardPunishTypedg').datagrid({
							url:'../../personnel/selectAwardPunishTypeByTime.do?getMs='+getMS(),
						});
						windowControl('修改奖惩类型信息成功');
					}else{
						windowControl('修改奖惩类型信息失败');
					}
					
				},
				error:function(){
			    	windowControl("请求失败!");
			    }
			})		
		}
	});	
}