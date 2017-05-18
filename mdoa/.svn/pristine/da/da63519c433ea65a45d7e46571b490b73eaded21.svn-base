//==========================================奖惩页面========================================================================
$(function(){	
	if(existPermission('admin:personnel:performanceManage:rewardPenalties:add'))
		$('#rewardPenalties .maintop').append('<div><span class="maintopicon mainicon2"></span> <span class="add">添加</span></div>');

	$('#rewardPenaltiesdg').datagrid({
	//	url:'../../personnel/selectAwardPunishByTime.do?getMs='+getMS(), 
		rownumbers:"true",
		   singleSelect:true,
		   pagination:true,
		   toolbar:"#rewardPenalties .invitetop",
		   method:"post",
		   fit: true, 
		   columns:[[
		     // {field:"ck",checkbox:true },
		       {field:"userName",title:"员工名称",fitColumns:true,resizable:true,align:"center",sortable:true,width:80},
		       {field:"awardPunishReason",title:"奖惩原因",fitColumns:true,resizable:true,align:"center",sortable:true,width:120},
		       {field:"solution",title:"奖惩办法",fitColumns:true,resizable:true,align:"center",sortable:true,width:100},
		       {field:"typeName",title:"奖惩类型",fitColumns:true,resizable:true,align:"center",sortable:true,width:100},
		       {field:"performUserName",title:"执行人",fitColumns:true,resizable:true,align:"center",sortable:true,width:80},
		       {field:"performState",title:"执行状态",fitColumns:true,resizable:true,align:"center",width:100,sortable:true,formatter:function(value,row,index){
		    	   if(value ==1){
		    		   return "已执行";
		    	   }else if(value ==0){
		    		  return "未执行";
		    	   }
		    	}},
		       {field:"_op",title:"管理",width:100,resizable:true,align:"center",sortable:true,formatter:function(value,row,index){
		    	   var id = "'"+row.awardPunishId+"'";
		    	   
		    	   var opera = '';
/*		    		  if(existPermission('admin:personnel:performanceManage:rewardPenalties:update'))
		    				opera +='<span class="small-button edit" title="执行" onclick="performPunish('+id+')"></span>';
		    		  if(existPermission('admin:personnel:performanceManage:rewardPenalties:update'))
		    			  opera +='<span class="small-button edit" title="修改" onclick="updatePunish('+id+')"></span>';
		    		  if(existPermission('admin:personnel:performanceManage:rewardPenalties:delete'))
		    			  opera +='<span class="small-button delete" title="删除" onclick="deletePunish('+id+')"></span>';*/
		    		
		    	  if(row.performState == '0'){
		    		  if(existPermission('admin:personnel:performanceManage:rewardPenalties:update'))
		    				opera +='<span class="small-button edit" title="执行" onclick="performPunish('+id+')"></span>';
		    		  if(existPermission('admin:personnel:performanceManage:rewardPenalties:update'))
		    			  opera +='<span class="small-button edit" title="修改" onclick="updatePunish('+id+')"></span>';
		    		  if(existPermission('admin:personnel:performanceManage:rewardPenalties:delete'))
		    			  opera +='<span class="small-button delete" title="删除" onclick="deletePunish('+id+')"></span>';	  
		    	  }else{
		    		  if(existPermission('admin:personnel:performanceManage:rewardPenalties:update'))
		    			  opera +='<span class="small-button edit" title="修改" onclick="updatePunish('+id+')"></span>';
		    		  if(existPermission('admin:personnel:performanceManage:rewardPenalties:delete'))
		    			  opera +='<span class="small-button delete" title="删除" onclick="deletePunish('+id+')"></span>';
		    	  }
		    	  return opera;
		       }},
		  ]]	
	})	
});
$(function(){
/*********************条件查询奖惩信息******************************/
	$('#rewardPenalties .selectByName').click(function(){
		var username1 = $('#rewardPenalties .userName').val();
		var performUserName = $('#rewardPenalties .performUserName').val();
		$('#rewardPenaltiesdg').datagrid({
			url:'../../personnel/selectAwardPunishByTime.do?getMs='+getMS(),
			method:"post",
			queryParams: {
				userName:username1,performUserName:performUserName,
			},
		});	
	})
/*********************添加奖惩信息******************************/
	$('#rewardPenalties .maintop .add').click(function(){
		$("#rewardPenalties .savepunishment").css("display","block");
	})
	$('#rewardPenalties .popups .savepunishment .chooseUser').click(function(){
		chooseUser();
	    $('#chooseUser .confirm').click(function(){
	    	$('#chooseUser').css('display','none');
	    	selectUser = $('#chooseUser .popuparea .user').datagrid('getSelections');
	    	$('#rewardPenalties .popups .savepunishment input[name=peopleName]').val(selectUser[0].userName);
	    	$('#rewardPenalties .popups .savepunishment input[name=userId]').val(selectUser[0].userId);
	    })
	})
	//下拉框异步请求奖惩类型
	$.ajax({
		url:'../../personnel/selectAwardPunishTypeByTime.do?getMs='+getMS(),
		type:'post',
		success:function(data){
			var str = "<option>--请选择--</option>";
			var data = eval('(' + data + ')'); 
			for(var i=0;i<data.rows.length;i++){
				str += "<option class='awardPunishTypeId' value=" + data.rows[i].awardPunishTypeId + ">" + data.rows[i].typeName + "</option>";  
			}
		$('#rewardPenalties .savepunishment .awardPunishType').html(str);
		},
		error:function(error){
			windowControl(error.status);
		}
	})
	$('#rewardPenalties .savepunishment .confirm').click(function(){
		var userName = $('#rewardPenalties .savepunishment input[name=peopleName]').val();
		var userId = $('#rewardPenalties .savepunishment input[name=userId]').val();
		var reason = $('#rewardPenalties .savepunishment .awardPunishReason').val();
		var solution = $('#rewardPenalties .savepunishment .solution').val();
		var awardPunishTypeId =  $('#rewardPenalties .savepunishment .awardPunishType').val();
		var performUserName = $('#rewardPenalties .savepunishment .performUserName').val();
		if(userName ==''|| userName == null){			
			windowControl("员工名称不能为空");
		}else if(reason ==''|| reason == null){			
			windowControl("奖惩原因不能为空");
		}else if(solution ==''||solution == null){
			windowControl("奖惩办法不能为空");
		}else if(awardPunishTypeId == '--请选择--' || awardPunishTypeId == null){
			windowControl("奖惩类型不能为空");
		}else if(performUserName == '' ||performUserName == null ){
			windowControl("执行人不能为空");
		}else{
			$.ajax({
				data:{userName:userName,userId:userId,awardPunishReason:reason,
					solution:solution,awardPunishTypeId:awardPunishTypeId,
					performUserName:performUserName
				},
				method:"post",
				url:"../../personnel/insertAwardPunish?getMs="+getMS(),
				success: function(data){
					if(data == 200){
						$('#rewardPenalties .popups').css('display','none');
						$('#rewardPenalties .savepunishment .popuparea input').val(null);
						$('#rewardPenalties .savepunishment .popuparea select').val(null);
						$('#rewardPenalties .savepunishment .popuparea textarea').val(null);
						$('#rewardPenaltiesdg').datagrid({
							url:'../../personnel/selectAwardPunishByTime.do?getMs='+getMS(),
						});
						windowControl('保存奖惩信息成功');
					}else{
						windowControl('保存奖惩信息失败');
					}
					
				},
				error:function(){
			    	windowControl("请求失败!");
			    }
			})	
			
		}
		
	});	
	
})
/*********************更改执行状态为已执行*********************/
function performPunish(id){
	$.ajax({
		data:{awardPunishId:id},
		url:'../../personnel/updateAwardPunishPerform.do?getMs='+getMS(),
		type:'post',
		success:function(data){
			if(data == '200'){
				windowControl('执行成功');
				$("#rewardPenalties #rewardPenaltiesdg").datagrid("reload");
			}else{
				windowControl('执行失败');
			}
		},
		error:function(err){
			windowControl(err.status);
		}
		
	})
}
/*********************查看奖惩信息*********************/
function lookPunish(id){
	$("#rewardPenalties .updatepunishment").css("display","block");
	$.ajax({
		data:{awardPunishId:id},
		url:'../../personnel/selectAwardPunishByTime.do?getMs='+getMS(),
		type:'post',
		success:function(data){
				var obj = eval('('+data+')').rows[0];
				$("#rewardPenalties .updatepunishment input").attr('readonly','true');
				$("#rewardPenalties .updatepunishment textarea").attr('readonly','true');
				$("#rewardPenalties .updatepunishment .alreadyNumber").removeAttr('readonly');
				$("#rewardPenalties .updatepunishment").css("display","block");
				$("#rewardPenalties .updatepunishment .userName").val(obj.userName);
				$("#rewardPenalties .updatepunishment .awardPunishReason").val(obj.awardPunishReason);
				$("#rewardPenalties .updatepunishment .solution").val(obj.solution);
				$("#rewardPenalties .updatepunishment .awardPunishType").val(obj.awardPunishType);
				$("#rewardPenalties .updatepunishment .performUserName").val(obj.performUserName);
		},
		error:function(){
			windowControl("服务器未响应!");
		}
	})	
}
/*********************删除奖惩信息*********************/
function deletePunish(id){
	ui.confirm('确定要删除这条奖惩信息？',function(z){
		if(z){
			$.ajax({
				data:{awardPunishId:id},
				url:'../../personnel/deleteAwardPunish.do?getMs='+getMS(),
				type:'post',
				success:function(data){
					if(data == '200'){
						windowControl('删除奖惩信息成功');
						$("#rewardPenalties #rewardPenaltiesdg").datagrid("reload");
					}else{
						windowControl('删除奖惩信息失败');
					}
				},
				error:function(){
					windowControl("服务器未响应!");
				}
			});
		}
	},false);
}
/*********************修改奖惩信息*********************/
function updatePunish(id){
	$("#rewardPenalties .updatepunishment").css("display","block");
	//下拉框异步请求奖惩类型
	$.ajax({
		url:'../../personnel/selectAwardPunishTypeByTime.do?getMs='+getMS(),
		type:'post',
		success:function(data){
			var str = "<option>--请选择--</option>";
			var data = eval('(' + data + ')'); 
			for(var i=0;i<data.rows.length;i++){
				str += "<option class='awardPunishTypeId' value=" + data.rows[i].awardPunishTypeId + ">" + data.rows[i].typeName + "</option>";  
			}
		$('#rewardPenalties .updatepunishment .awardPunishType').html(str);
		},
		error:function(error){
			windowControl(error.status);
		}
	})
	//查询奖惩信息
	$.ajax({
		data:{awardPunishId:id},
		url:'../../personnel/selectAwardPunishByTime.do?getMs='+getMS(),
		type:'post',
		success:function(data){
				var obj = eval('('+data+')').rows[0];
				$("#rewardPenalties .updatepunishment .alreadyNumber").removeAttr('readonly');
				$("#rewardPenalties .updatepunishment").css("display","block");
				$("#rewardPenalties .updatepunishment .userName").val(obj.userName);
				$("#rewardPenalties .updatepunishment .awardPunishReason").val(obj.awardPunishReason);
				$("#rewardPenalties .updatepunishment .solution").val(obj.solution);
				$("#rewardPenalties .updatepunishment .performUserName").val(obj.performUserName);
		},
		error:function(err){
			windowControl(err.status); 
		}
	})
	$('#rewardPenalties .updatepunishment .confirm').unbind('click');
	$('#rewardPenalties .updatepunishment .confirm').click(function(){
		var userName = $("#rewardPenalties .updatepunishment .userName").val();
		var awardPunishTypeId =  $('#rewardPenalties .updatepunishment .awardPunishTypeId').val();
		var awardPunishReason = $("#rewardPenalties .updatepunishment .awardPunishReason").val();
		var solution = $("#rewardPenalties .updatepunishment .solution").val();
		var awardPunishtype =  $("#rewardPenalties .updatepunishment .awardPunishType").val();
		var performUserName = $("#rewardPenalties .updatepunishment .performUserName").val();
		if(userName ==''|| userName == null){			
			windowControl("员工名称不能为空");
		}else if(awardPunishReason ==''|| awardPunishReason == null){			
			windowControl("奖惩原因不能为空");
		}else if(solution ==''||solution == null){
			windowControl("奖惩办法不能为空");
		}else if(awardPunishTypeId == '--请选择--' || awardPunishTypeId == null){
			windowControl("奖惩类型不能为空");
		}else if(performUserName == '' ||performUserName == null ){
			windowControl("执行人不能为空");
		}else{
			$.ajax({
				data:{awardPunishId:id,userName:userName,awardPunishReason:awardPunishReason,
					solution:solution,awardPunishTypeId:awardPunishTypeId,
					performUserName:performUserName
				},
				method:"post",
				url:"../../personnel/updateAwardPunish?getMs="+getMS(),
				success: function(data){
					if(data == 200){
						$('#rewardPenalties .popups').css('display','none');
						$('#rewardPenalties .updatepunishment .popuparea input').val(null);
						$('#rewardPenalties .updatepunishment .popuparea select').val(null);
						$('#rewardPenalties .updatepunishment .popuparea textarea').val(null);
						$('#rewardPenaltiesdg').datagrid({
							url:'../../personnel/selectAwardPunishByTime.do?getMs='+getMS(),
						});
						windowControl('修改奖惩信息成功');
					}else{
						windowControl('修改奖惩信息失败');
					}
				},
				error:function(){
			    	windowControl("服务器未响应!");
			    }
			})		
		}		
	});		
}
