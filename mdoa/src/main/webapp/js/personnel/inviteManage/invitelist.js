$(function(){
	$('#invitelist .flow').css('height',$('#loadarea').height()-31);
	$('#invitelistdg').parent().css('height',$('#loadarea').height()-62);
	$('#invitelistdg').datagrid({
		  // url:'../../personnel/queryInviteApplyByMy.do?getMs='+getMS(), // list
		   rownumbers:"true",
		   singleSelect:true,
		   pagination:true,
		   toolbar:"#invitelist .invitetop",
		   method:"post",
		   fit: true,
		   columns:[[
		       {field:"planName",title:"招聘计划名",fitColumns:true,resizable:true,align:"center",sortable:true,width:100},
		       {field:"text",title:"招聘描述",fitColumns:true,resizable:true,align:"center",sortable:true,width:150},
		       {field:"reason",title:"招聘原因",fitColumns:true,resizable:true,align:"center",sortable:true,width:150},
		       {field:"planCount",title:"计划人数",fitColumns:true,resizable:true,align:"center",sortable:true,width:60},
		       {field:"inviteStatus",title:"计划状态",fitColumns:true,resizable:true,align:"center",width:60,sortable:true,formatter:function(value,row,index){
		    	   if(row.inviteStatus == '1'){
		    		   return "审批中";
		    	   }else if(row.inviteStatus == '2'){
		    		   return "撤回";
		    	   }else if(row.inviteStatus == '3'){
		    		   return "招聘中";
		    	   }else if(row.inviteStatus == '4'){
		    		   return "驳回";
		    	   }else if(row.inviteStatus == '5'){
		    		   return "招满";
		    	   }
		    	   
		    	   //return getValueFromKey("invite_status",row.inviteStatus);
		    	}},
		    	{field:"examineStatus",title:"审批状态",fitColumns:true,resizable:true,align:"center",width:60,sortable:true,formatter:function(value,row,index){
		    		if(row.examineStatus == '1'){
		    		   return "待审";
		    	   }else if(row.examineStatus == '2'){
		    		   return "撤回";
		    	   }else if(row.examineStatus == '3'){
		    		   return "通过";
		    	   }else if(row.examineStatus == '4'){
		    		   return "驳回";
		    	   }else if(row.examineStatus == '5'){
		    		   return "备案";
		    	   }   
		    		
		    		//return getValueFromKey("examine_status",row.examineStatus);
			    	}},
		       {field:"_op",title:"管理",width:100,resizable:true,align:"center",sortable:true,width:100,formatter:function(value,row,index){
		    		var id = "'"+row.inviteId+"'";
		    		var inviteStreamId ="'"+row.inviteStreamId+"'";
		    		var opera = '';
		    		if(row.examineStatus == '1'){
		    			if(existPermission('admin:personnel:inviteManage:invitelist:examine'))
				    		opera +='<span class="small-button edit" title="审批" onclick="examineInviteStream('+id+','+inviteStreamId+')"></span>';
				    	if(existPermission('admin:personnel:inviteManage:invitelist:select'))
				    		opera +='<span class="small-button lookflow" title="查看流程" onclick="lookInviteStreamList('+id+','+inviteStreamId+')"></span>';
		    		}else{
		    			if(existPermission('admin:personnel:inviteManage:invitelist:select'))
				    		opera +='<span class="small-button lookflow" title="查看流程" onclick="lookInviteStreamList('+id+','+inviteStreamId+')"></span>';
		    		}
		    		return opera;
		    	}},
		  ]] 
	});
	$('#invitelist .invitetop .query').click(function(){//查询
		var planName = $("#invitelist .invitetop .planName").val();
		var applyUserName=$("#invitelist .invitetop .applyUserName").val();
		var createUserName=$("#invitelist .invitetop .createUserName").val();
		var updateUserName=$("#invitelist .invitetop .updateUserName").val();
		var inviteStatus=$("#invitelist .invitetop .inviteStatus").val();
		var examineStatus=$("#invitelist .invitetop .examineStatus").val();
		if(inviteStatus == "审批中")
			inviteStatus = 1;
		if(inviteStatus == "撤回")
			inviteStatus = 2;
		if(inviteStatus == "招聘中")
			inviteStatus = 3;
		if(inviteStatus == "驳回") 
			inviteStatus = 4;
		if(inviteStatus == "招满")
			inviteStatus = 5;
		if(examineStatus == "待审")
			examineStatus =1;
		if(examineStatus == "撤回")
			examineStatus =2;
		if(examineStatus == "通过")
			examineStatus =3;
		if(examineStatus == "驳回")
			examineStatus =4;
		if(examineStatus == "备案")
			examineStatus =5;
		
		var inviteQueryInfo = {
				planName : planName,
				applyUserName : applyUserName,
				createUserName : createUserName,
				updateUserName : updateUserName,
				inviteStatus : inviteStatus,
				examineStatus : examineStatus
		}
		$('#invitelistdg').datagrid({
			url:"../../personnel/queryInviteApplyByMy?getMs="+getMS(),
			queryParams:inviteQueryInfo
		})
	});
	
	//清空
	cleanQuery($("#invitelist .list .invitetop .clean"));
	
	//点击驳回保存按钮
	$('#invitelist .rejectWindow .save').click(function(){
		var examineIdea = $("#invitelist .rejectWindow textarea[name=examineIdea]").val();
		var inviteId = $("#invitelist .flow .showView .inviteId").val();
		var inviteStreamId = $("#invitelist .flow .showView .inviteStreamId").val(); 
		$.ajax({
			data:{inviteId:inviteId,examineIdea:examineIdea,inviteStreamId:inviteStreamId},
			type:"post",
			url:"../../personnel/rejectInviteStream?getMs="+getMS(),
			success : function(data){
				if(data == '200'){
					windowControl("驳回成功");
					$("#invitelist .rejectWindow").css('display','none');
					$('#invitelist .list').css('display','block');
					$('#invitelist .flow').css('display','none');
					$('#invitelistdg').datagrid('reload');
				}else{
					windowControl("驳回失败");
				}
			},
			error:function(){
				windowControl("请求失败");
			}
		})
	});
	//点击备案保存按钮
	$('#invitelist .referenceWindow .save').click(function(){
		var examineIdea = $("#invitelist .referenceWindow textarea[name=examineIdea]").val();
		var inviteStreamId = $("#invitelist .flow .showView .inviteStreamId").val();
		var inviteId = $("#invitelist .flow .showView .inviteId").val();
		$.ajax({
			data:{inviteId:inviteId,examineIdea:examineIdea,inviteStreamId:inviteStreamId},
			type:"post",
			url:"../../personnel/recordAddInviteStream?getMs="+getMS(),
			success : function(data){
				if(data == '200'){
					windowControl("进入备案流程成功");
					$("#invitelist .referenceWindow").css('display','none');
					$('#invitelist .list').css('display','block');
					$('#invitelist .flow').css('display','none');
					$('#invitelistdg').datagrid('reload');
				}else{
					windowControl("进入备案流程失败");
				}
			},
			error:function(){
				windowControl("请求失败");
			}
		})
	});
	//点击通过保存按钮
	$('#invitelist .pastWindow .save').click(function(){
		var examineIdea = $('#invitelist .pastWindow textarea[name=examineIdea]').val();
		var inviteStreamId = $("#invitelist .flow .showView .inviteStreamId").val();
		var inviteId = $("#invitelist .flow .showView .inviteId").val();
		var nextExamineUser=$('#invitelist .pastWindow input[name=nextExamineUser]').val();
		var nextExamineUserName=$('#invitelist .pastWindow input[name=nextExamineUserName]').val();
		$.ajax({
			data:{inviteId:inviteId,examineIdea:examineIdea,inviteStreamId:inviteStreamId,nextExamineUser:nextExamineUser,nextExamineUserName:nextExamineUserName},
			type:"post",
			url:"../../personnel/examineUpdateAddStream?getMs="+getMS(),
			success : function(data){
				if(data == '200'){
					windowControl("通过成功");
					$('#invitelist .list').css('display','block');
					$('#invitelist .flow').css('display','none');
					$("#invitelist .pastWindow").css('display','none');
					$('#invitelistdg').datagrid('reload');
				}else{
					windowControl("通过失败");
				}
			},
			error:function(){
				windowControl("请求失败");
			}
		})
	});
	//选择下一个审批人
	$('#invitelist .pastWindow .chooseUser').click(function(){
		chooseUser();
	    $('#chooseUser .confirm').click(function(){
	    	$('#chooseUser').css('display','none');
	    	selectUser = $('#chooseUser .popuparea .user').datagrid('getSelections');
	    	$('#invitelist .pastWindow input[name=nextExamineUserName]').val(selectUser[0].userName);
	    	$('#invitelist .pastWindow input[name=nextExamineUser]').val(selectUser[0].userId);
	    })
	})
	
	/**************inviteStatus****************/
	//$('#basisInfo .staffRecord .inviteStatus').html(getDataBySelectKey("invite_status"));
	/**************examineStatus****************/
	//$('#basisInfo .staffRecord .examineStatus').html(getDataBySelectKey("examine_status"));
})
function lookInviteStreamList(id,inviteStreamId){//招聘流查看审批后触发
	$('#invitelist .list').css('display','none');
	$('#invitelist .flow').css('display','block');
	$('#invitelist .flow .flowbar .reject').css('display','none');
	$('#invitelist .flow .flowbar .reference').css('display','none');
	$('#invitelist .flow .flowbar .past').css('display','none');
	$.ajax({
		data:{inviteId:id},
		url:'../../personnel/queryStreamInviteApply.do?getMs='+getMS(),
		type:'post',
		success:function(data){
			var obj = eval('(' + data + ')').rows;
			var html = '';
			for(var i = 0; i<obj.length ; i++){
				if(typeof obj[i].nextExamineUserName == 'undefined'){
					obj[i].nextExamineUserName = '';
				}
				if(typeof obj[i].examineIdea == 'undefined'){
					obj[i].examineIdea = '';
				}
				if(typeof obj[i].examineTimeStr == 'undefined'){
					obj[i].examineTimeStr = '';
				}
				if(typeof obj[i].examineUserName == 'undefined'){
					obj[i].examineUserName = '';
				}
				html = html + '<div class="process"><div class="processbox">'
				+ '<p><span class="jiachu expeop" style="margin-left: 2px;">&ensp;&nbsp;审批人：'+obj[i].examineUserName+'</span>'
				+ '<span class="righttime">&ensp;&ensp;&ensp;&nbsp;审批时间：'+obj[i].examineTimeStr+'</span></p>'
				+ '<p><span class="expeop">审批类型：';
				if(obj[i].streamType == '1'){
					html = html + "审批";
				}else if(obj[i].streamType == '2'){
					html = html + "备案";
				}
				html = html +'</span><span class="righttime">下一级审批人：'+obj[i].nextExamineUserName+'</span></p>'
				+ '<div class="opinionbox"><div class="opiniontit">审批意见：'+obj[i].examineIdea+'</div>'
				+ '<div class="opinionarea"></div></div>'
				+ '</div></div><div class="arrdown"><span></span></div>'
			}
			var str = '<input type="text" style="display:none;" class="inviteId" value="'+id+'"/>'+
			'<input type="text" style="display:none;" class="inviteStreamId" value="'+inviteStreamId+'"/>';
			html += str;
			$('#invitelist .flow .showView').html(html);
		}
	})
	
	$.ajax({
		data:{inviteId:id},
		url:'../../personnel/queryInviteApply.do?getMs='+getMS(),
		type:'post',
		success:function(data){
			var obj = eval('(' + data + ')').rows;
			$('#invitelist .flow .flowplan .planName').val(obj[0].planName);
			$('#invitelist .flow .flowplan .createUserName').val(obj[0].createUserName);
			$('#invitelist .flow .flowplan .createTimeStr').val(obj[0].createTimeStr);
			$('#invitelist .flow .flowplan .planCount').val(obj[0].planCount);
			$('#invitelist .flow .flowplan .reallyCount').val(obj[0].reallyCount);
			$('#invitelist .flow .flowplan .reallyCount').val(obj[0].reallyCount);
			if(obj[0].inviteStatus == 1)
				$('#invitelist .flow .flowplan .inviteStatus').val('审批中');
			else if(obj[0].inviteStatus == 2)
				$('#invitelist .flow .flowplan .inviteStatus').val('撤回');
			else if(obj[0].inviteStatus == 3)
				$('#invitelist .flow .flowplan .inviteStatus').val('招聘中');
			else if(obj[0].inviteStatus == 4)
				$('#invitelist .flow .flowplan .inviteStatus').val('驳回');
			else if(obj[0].inviteStatus == 5)
				$('#invitelist .flow .flowplan .inviteStatus').val('招满');
			$('#invitelist .flow .flowplan .text').html(obj[0].text);
			$('#invitelist .flow .flowplan .reason').html(obj[0].reason);
		},
		error:function(err){
			windowControl(err.status);
		}
	})
	
}
function examineInviteStream(id,inviteStreamId){//招聘流点击审批后触发
	$('#invitelist .list').css('display','none');
	$('#invitelist .flow').css('display','block');
	$('#invitelist .flow .flowbar .reject').css('display','inline-block');
	$('#invitelist .flow .flowbar .reference').css('display','inline-block');
	$('#invitelist .flow .flowbar .past').css('display','inline-block');
	$.ajax({
		data:{inviteId:id},
		url:'../../personnel/queryStreamInviteApply.do?getMs='+getMS(),
		type:'post',
		success:function(data){
			var obj = eval('(' + data + ')').rows;
			var html = '';
			for(var i = 0; i<obj.length ; i++){
				html = html + '<div class="process"><div class="processbox">'
				+ '<p><span class="jiachu expeop" style="margin-left: 2px;">&ensp;&nbsp;审批人：'+obj[i].examineUserName+'</span>'
				+ '<span class="righttime">&ensp;&ensp;&ensp;&nbsp;审批时间：'+obj[i].examineTimeStr+'</span></p>'
				+ '<p><span class="expeop">审批类型：';
				if(obj[i].streamType == '1'){
					html = html + "审批";
				}else if(obj[i].streamType == '2'){
					html = html + "备案";
				}
				html = html +'</span><span class="righttime">下一级审批人：'+obj[i].nextExamineUserName+'</span></p>'
				+ '<div class="opinionbox"><div class="opiniontit">审批意见：'+obj[i].examineIdea+'</div>'
				+ '<div class="opinionarea"></div></div>'
				+ '</div></div><div class="arrdown"><span></span></div>'
			}
			var str = '<input type="text" style="display:none;" class="inviteId" value="'+id+'"/>'+
			'<input type="text" style="display:none;" class="inviteStreamId" value="'+inviteStreamId+'"/>';
			html += str;
			$('#invitelist .flow .showView').html(html);
		}
	})
	
	$.ajax({
		data:{inviteId:id},
		url:'../../personnel/queryInviteApply.do?getMs='+getMS(),
		type:'post',
		success:function(data){
			var obj = eval('(' + data + ')').rows;
			$('#invitelist .flow .flowplan .planName').val(obj[0].planName);
			$('#invitelist .flow .flowplan .createUserName').val(obj[0].createUserName);
			$('#invitelist .flow .flowplan .createTimeStr').val(obj[0].createTimeStr);
			$('#invitelist .flow .flowplan .planCount').val(obj[0].planCount);
			$('#invitelist .flow .flowplan .reallyCount').val(obj[0].reallyCount);
			$('#invitelist .flow .flowplan .reallyCount').val(obj[0].reallyCount);
			if(obj[0].inviteStatus == 1)
				$('#invitelist .flow .flowplan .inviteStatus').val('审批中');
			else if(obj[0].inviteStatus == 2)
				$('#invitelist .flow .flowplan .inviteStatus').val('撤回');
			else if(obj[0].inviteStatus == 3)
				$('#invitelist .flow .flowplan .inviteStatus').val('招聘中');
			else if(obj[0].inviteStatus == 4)
				$('#invitelist .flow .flowplan .inviteStatus').val('驳回');
			else if(obj[0].inviteStatus == 5)
				$('#invitelist .flow .flowplan .inviteStatus').val('招满');
			$('#invitelist .flow .flowplan .text').html(obj[0].text);
			$('#invitelist .flow .flowplan .reason').html(obj[0].reason);
		},
		error:function(err){
			windowControl(err.status);
		}
	})
}
/*********如果点击了返回按钮，则返回到列表********/
$('#invitelist .flow .flowbar .back').click(function(){
	$('#invitelist .list').css('display','block');
	$('#invitelist .flow').css('display','none');
});
/******通过，驳回，备案按钮相对应的取消事件*********/
$("#invitelist .rejectWindow .cannel").click(function(){
	$("#invitelist .rejectWindow").css('display','none');	
})
$("#invitelist .referenceWindow .cannel").click(function(){
	$("#invitelist .referenceWindow").css('display','none');	
})
$("#invitelist .pastWindow .cannel").click(function(){
	$("#invitelist .pastWindow").css('display','none');	
})
/******通过，驳回，备案按钮相对应的监听事件*******/
$("#invitelist .reject").click(function(){
	$("#invitelist .rejectWindow").css('display','block');						
})
$("#invitelist .reference").click(function(){
	$("#invitelist .referenceWindow").css('display','block');
})
$("#invitelist .past").click(function(){
	$("#invitelist .pastWindow").css('display','block');
})