$(function(){
	if(existPermission('admin:personnel:inviteManage:invite:add'))
		$('#invite .maintop').append('<div><span class="maintopicon mainicon2"></span> <span class="add">添加</span></div>');

	//招聘信息数据网格生成
	$('#invitedg').datagrid({
		   //url:'../../personnel/queryInviteApply.do?getMs='+getMS(),
		   rownumbers:"true",
		   singleSelect:true,
		   pagination:true,
		   toolbar:"#invite .invitetop",
		   method:"post",
		   fit: true, 
		   columns:[[
		       {field:"planName",title:"招聘计划名",resizable:true,fitColumns:true,align:"center",sortable:true,width:100},
		       {field:"text",title:"招聘描述",resizable:true,fitColumns:true,align:"center",sortable:true,width:150},
		       {field:"reason",title:"招聘原因",resizable:true,fitColumns:true,align:"center",sortable:true,width:150},
		       {field:"planCount",title:"计划人数",resizable:true,fitColumns:true,align:"center",sortable:true,width:60},
		       {field:"inviteStatus",title:"计划状态",resizable:true,align:"center",sortable:true,width:60,formatter:function(value,row,index){
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
		       {field:"_op",title:"管理",width:100,resizable:true,align:"center",fitColumns:true,width:100,sortable:true,formatter:function(value,row,index){
		    		var id = "'"+row.inviteId+"'";
		    		var opera = ''; 
		    		if(row.inviteStatus == '3'){
		    			if(existPermission('admin:personnel:inviteManage:invite:recall'))
			    			opera +='<span class="small-button recall" title="撤回" onclick="backInvite('+id+')"></span>';
			    		if(existPermission('admin:personnel:inviteManage:invite:select'))
			    			opera +='<span class="small-button lookflow" title="查看流程" onclick="popupInvite('+id+')"></span>';
			    		if(existPermission('admin:personnel:inviteManage:invite:delete'))
			    			opera +='<span class="small-button delete" title="删除" onclick="deleteInvite('+id+')"></span>';
			    		if(existPermission('admin:personnel:inviteManage:invite:select'))
			    			opera +='<span class="small-button look" title="查看招聘详情" onclick="lookInviteDetail('+id+')"></span>';
		    		}else if(row.inviteStatus == '2'){
		    			if(existPermission('admin:personnel:inviteManage:invite:select'))
			    			opera +='<span class="small-button lookflow" title="查看流程" onclick="popupInvite('+id+')"></span>';
			    		if(existPermission('admin:personnel:inviteManage:invite:delete'))
			    			opera +='<span class="small-button delete" title="删除" onclick="deleteInvite('+id+')"></span>';
			    		if(existPermission('admin:personnel:inviteManage:invite:select'))
			    			opera +='<span class="small-button look" title="查看招聘详情" onclick="lookInviteDetail('+id+')"></span>';
		    		}else{
		    			if(existPermission('admin:personnel:inviteManage:invite:select'))
			    			opera +='<span class="small-button lookflow" title="查看流程" onclick="popupInvite('+id+')"></span>';
		    			if(existPermission('admin:personnel:inviteManage:invite:select'))
			    			opera +='<span class="small-button look" title="查看招聘详情" onclick="lookInviteDetail('+id+')"></span>';
		    		}
		    		return opera;
		    	}},
		  ]]
	});
	//添加招聘计划点击事件
	$('#invite .maintop .add').click(function(){
		$('#invite .popups .saveInvite').css('display','block');
	});
	
	$('#invite .popups .saveInvite .chooseUser').click(function(){
		chooseUser();
	    $('#chooseUser .confirm').click(function(){
	    	$('#chooseUser').css('display','none');
	    	selectUser = $('#chooseUser .popuparea .user').datagrid('getSelections');
	    	$('#invite .popups .saveInvite input[name=examineUserName]').val(selectUser[0].userName);
	    	$('#invite .popups .saveInvite input[name=examineUserId]').val(selectUser[0].userId);
	    })
	})
	//清空
	cleanQuery($("#invite .list .invitetop .clean"));
	
	//保存招聘计划按钮
	$('#invite .popups .saveInvite .confirm').click(function(){
		if(!$(this).attr('clicked')){
			$(this).attr('clicked',0);
			$('#invite .popups .saveInvite .confirm')
			var planName = $.trim($('#invite .popups .saveInvite input[name=invitePlan]').val());
			var text = $.trim($('#invite .popups .saveInvite textarea[name=condition]').val());
			var reason = $.trim($('#invite .popups .saveInvite textarea[name=reason]').val());
			var planCount = $.trim($('#invite .popups .saveInvite input[name=planNumber]').val());
			var reallyCount = $.trim($('#invite .popups .saveInvite input[name=alreadyNumber]').val());
			var examineUserName = $.trim($('#invite .popups .saveInvite input[name=examineUserName]').val());
			var examineUserId = $.trim($('#invite .popups .saveInvite input[name=examineUserId]').val());
			if(planName==''||planName==null){
				windowControl("招聘计划名不能为空");
			}
			else if(text==''||text==null){
				windowControl("招聘条件不能为空");
			}
			else if(reason==''||reason==null){
				windowControl("招聘理由不能为空");
			}
			else if(examineUserId==''||examineUserId==null){
				windowControl("审批人不能为空");
			}
			else if(planCount==''||planCount==null){
				windowControl("招聘计划人数不能为空");
			}
			else if(examineUserName==''||examineUserName==null){
				windowControl("审批人不能为空");
			}else{
				 inviteInfo = {
					planName : planName,
					text : text,
					reason : reason,
					planCount : planCount,
					reallyCount : reallyCount,
					examineUserName : examineUserName,
					examineUserId : examineUserId
				}
				$.ajax({
					data : inviteInfo,
					url : '../../personnel/startInviteApply.do?getMs='+getMS(),
					type : 'post',
					success : function(data) {
						if(data != 200){
							windowControl('添加招聘计划失败');
							retrun ;
						}
						$('#invite .popups .saveInvite .confirm').removeAttr('clicked');
						$('.popup').css('display', 'none');
						$('.popup input').not('.button').val(null);
						$('.popup textarea').val(null);
						$('#invitedg').datagrid({
							url:'../../personnel/queryInviteApply.do?getMs='+getMS(),
						});
					},
					error : function(err) {
						$('#invite .popups .saveInvite .confirm').removeAttr('clicked');
						windowControl('服务器未响应');
					}
				})
			}
		}
	});
	
	$('#invite .invitetop .query').click(function(){
		var planName = $("#invite .invitetop .planName").val();
		var applyUserName=$("#invite .invitetop .applyUserName").val();
		var createUserName=$("#invite .invitetop .createUserName").val();
		var updateUserName=$("#invite .invitetop .updateUserName").val();
		var inviteStatus=$("#invite .invitetop .inviteStatus").val();
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
		var inviteQueryInfo = {
				planName : planName,
				applyUserName : applyUserName,
				createUserName : createUserName,
				updateUserName : updateUserName,
				inviteStatus : inviteStatus
		}
		$('#invitedg').datagrid({
			url:"../../personnel/queryInviteApply?getMs="+getMS(),
			queryParams:inviteQueryInfo
		})
		
	});
	
	/**************招聘状态****************/
	//$('#invite .invitetop .inviteStatus').html(getDataBySelectKeyNo("invite_status"));
	
})
function popupInvite(id){//招聘计划点击查看后触发
	$('#invite .list').css('display','none');
	$('#invite .flow').css('display','block');
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
			$('#invite .flow .showView').html(html);
		}
	})
	
	$.ajax({
		data:{inviteId:id},
		url:'../../personnel/queryInviteApply.do?getMs='+getMS(),
		type:'post',
		success:function(data){
			var obj = eval('(' + data + ')').rows;
			$('#invite .flow .flowplan .planName').val(obj[0].planName);
			$('#invite .flow .flowplan .createUserName').val(obj[0].createUserName);
			$('#invite .flow .flowplan .createTimeStr').val(obj[0].createTimeStr);
			$('#invite .flow .flowplan .planCount').val(obj[0].planCount);
			$('#invite .flow .flowplan .reallyCount').val(obj[0].reallyCount);
			if(obj[0].inviteStatus == '1'){
				obj[0].inviteStatus = "审批中";
    	   }else if(obj[0].inviteStatus == '2'){
    		   obj[0].inviteStatus = "撤回";
    	   }else if(obj[0].inviteStatus == '3'){
    		   obj[0].inviteStatus = "招聘中";
    	   }else if(obj[0].inviteStatus == '4'){
    		   obj[0].inviteStatus = "驳回";
    	   }else if(obj[0].inviteStatus == '5'){
    		   obj[0].inviteStatus = "招满";
    	   }
			//getValueFromKey("invite_status",obj[0].inviteStatus)
			$('#invite .flow .flowplan .inviteStatus').val(obj[0].inviteStatus);
			$('#invite .flow .flowplan .text').html(obj[0].text);
			$('#invite .flow .flowplan .reason').html(obj[0].reason);
		},
		error:function(err){
			windowControl(err.status);
		}
	})
	
	
}

function backInvite(id){//撤回招聘信息
	ui.confirm('确定要撤回招聘计划？',function(z){
		if(z){
			$.ajax({
				data:{inviteId:id},
				type:"post",
				url:"../../personnel/backInviteApply?getMs="+getMS(),
				success:function(data){
					windowControl('撤回成功');
					$('#invitedg').datagrid('reload');
				}
			});
		}
	},false);
}
function deleteInvite(id){//删除招聘计划
	ui.confirm('确定要删除招聘计划？',function(z){
		if(z){
			$.ajax({
				data:{inviteId:id}, 
				url:'../../personnel/deleteInviteApply.do?getMs='+getMS(),
				type:'post',
				success:function(){
					$('#invitedg').datagrid('reload');
				},
				error:function(err){
					windowControl(err.status);
				}
			});
		}
	},false);
}
function lookInviteDetail(id){//查看招聘详情
	$.ajax({
		data:{inviteId:id},
		url:'../../personnel/queryInviteApply.do?getMs='+getMS(),
		type:'post',
		success:function(data){
			var invite = eval('('+data+')').rows[0];
			if(invite.inviteStatus == '1'){
				invite.inviteStatus = "审批中";
    	   }else if(invite.inviteStatus == '2'){
    		   invite.inviteStatus = "撤回";
    	   }else if(invite.inviteStatus == '3'){
    		   invite.inviteStatus = "招聘中";
    	   }else if(invite.inviteStatus == '4'){
    		   invite.inviteStatus = "驳回";
    	   }else if(invite.inviteStatus == '5'){
    		   invite.inviteStatus = "招满";
    	   }
			getValueFromKey("invite_status",invite.inviteStatus);
			$('#invite .selectInvitePlan input[name=inviteStatus]').val(invite.inviteStatus);
			$('#invite .selectInvitePlan').css('display','block');
			$('#invite .selectInvitePlan input').attr('readonly','readonly');
			$('#invite .selectInvitePlan textarea').attr('readonly','readonly');
			$('#invite .selectInvitePlan input[name=planCount]').val(invite.planCount);
			$('#invite .selectInvitePlan input[name=planName]').val(invite.planName);
			$('#invite .selectInvitePlan textarea[name=reason]').val(invite.reason);
			$('#invite .selectInvitePlan textarea[name=text]').val(invite.text);
			
		}
		
	})
	
	
	
}
$('#invite .flow .flowbar .back').click(function(){
	$('#invite .list').css('display','block');
	$('#invite .flow').css('display','none');
});

