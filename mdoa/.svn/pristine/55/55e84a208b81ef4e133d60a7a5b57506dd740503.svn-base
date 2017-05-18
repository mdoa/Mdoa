$(function(){
	/**设置内部网格内容的高度**/
	$("#leaveRecord .traindg").css('height',$('#loadarea').height()-62);
	$("#leaveRecord .flow").css('height',$('#loadarea').height()-62);
	
	/******创建数据培训信息网格******/
	$('#leaveRecord .traindg').datagrid({
	   // url:'../../leave/findStreamByCondition.do?getMs='+getMS(),
	    method:"post",
	    columns:[[
			{field:'userName',align:"center",title:'请假人姓名',width:75},
			{field:'leaveType',align:"center",title:'请假类型',width:100,formatter:function(value,row,index){
		    	   return getValueFromKey('leave_type',value);
		       }},
			{field:'startTimeStr',align:"center",title:'请假开始时间',width:130},
			{field:'overTimeStr',align:"center",title:'请假结束时间',width:130,align:'right'},
			{field:'applyTimeStr',align:"center",title:'请假申请时间',width:130,align:'right'},
			{field:'reason',align:"center",title:'请假原因',width:150,align:'center'},
			{field:'examineStatus',title:'审批状态',width:60,align:'center',formatter:function(value,row,index){
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
			}},
			{field:'examineTimeStr',title:'审批时间',width:130,align:'center'},
			{field:'_operate',title:'操作',align:'center',width:100,formatter:function(value,row,index){
				var id = "'"+row.leaveApplyId+"'";
				var leaveStreamId = "'"+row.leaveStreamId+"'";
				if(row.examineStatus == '1'){
					return '<span class="small-button edit" onclick="updateLeaveFlowMessage('+id+','+leaveStreamId+')"></span><span class="small-button look" onclick="selectLeaveFlowMessage('+id+','+leaveStreamId+')"></span>'					
				}else{
					return '<span class="small-button look" onclick="selectLeaveFlowMessage('+id+','+leaveStreamId+')"></span>'
				}
			}}
	    ]],
	    toolbar:'#leaveRecord .invitetop',
	    striped:true,
	    loadMsg:'数据加载中',
	    checkbox:false,
	    singleSelect:true,
	    pagination:true,
    	queryParams: {
    		streamType:'2'
    	},
    	rownumbers:true
	});
	//条件查询
	$('#leaveRecord .invitetop .query').click(function(){
		var userName = $("#leaveRecord .invitetop .userName").val();
		var examineStatus=$("#leaveRecord .invitetop .examineStatus").val();
		var leaveType = $("#leaveExamine .invitetop select[name=leaveType]").val();
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
				userName : userName,
				examineStatus : examineStatus,
				leaveType:leaveType,
				streamType:'2'
		}
		$('#leaveRecord .traindg').datagrid({
			url:'../../leave/findStreamByCondition.do?getMs='+getMS(),
			queryParams:inviteQueryInfo
		})
	})
	//清空
	cleanQuery($("#leaveRecord .list .invitetop .clean"));
	
	/*********如果点击了返回按钮，则返回到列表********/
	$('#leaveRecord .flow .flowbar .back').click(function(){
		$('#leaveRecord .list').css('display','block');
		$('#leaveRecord .flow').css('display','none');
	});
	
	/******通过，驳回，备案按钮相对应的监听事件*******/
	$("#leaveRecord .reject").click(function(){
		$("#leaveRecord .rejectWindow").css('display','block');
	})
	$("#leaveRecord .reference").click(function(){
		$("#leaveRecord .referenceWindow").css('display','block');
	})
	$("#leaveRecord .past").click(function(){
		$("#leaveRecord .pastWindow .nextpeople").html('下级审批人：'+$('#leaveRecord input[name=nextExamineName]').val());
		$("#leaveRecord .pastWindow").css('display','block');
	})
	
	/********通过，驳回，备案操作事件********/
	//驳回
	$("#leaveRecord .rejectWindow .save").click(function(){
		var text = $('#leaveRecord .rejectWindow textarea').val();
		var leaveApplyId = $('#leaveRecord .flow .flowplan .leaveApplyId').val();
		var leaveStreamId = $('#leaveRecord .flow .flowplan .leaveStreamId').val();
		windowControl(leaveApplyId);
		windowControl(leaveStreamId);
		$.ajax({
			url:'../../leave/rejectLeaveApply.do?getMs='+getMS(),
			type:'post',
			data:{
				examineIdea:text,
				leaveApplyId:leaveApplyId,
				leaveStreamId:leaveStreamId,
				examineStatus:'4'
			},
			success:function(data){
				if(data == '200'){
					windowControl("驳回成功");
					$('#leaveRecord .list').css('display','block');
					$('#leaveRecord .flow').css('display','none');
					$("#leaveRecord .rejectWindow").css('display','none');
					$('#leaveRecord .traindg').datagrid('reload');
				}else{
					windowControl("驳回失败");
				}
			}
		})
	});
	//备案
	$("#leaveRecord .referenceWindow .save").click(function(){
		var text = $('#leaveRecord .referenceWindow textarea[name=examineIdea]').val();
		var leaveApplyId = $('#leaveRecord .flow .flowplan .leaveApplyId').val();
		var leaveStreamId = $('#leaveRecord .flow .flowplan .leaveStreamId').val();
		$.ajax({
			url:'../../leave/recordLeaveApply.do?getMs='+getMS(),
			type:'post',
			data:{
				recordNote : text,
				examineIdea:text,
				leaveApplyId:leaveApplyId,
				leaveStreamId:leaveStreamId,
				examineStatus:'3'
			},
			success:function(data){
				if(data == '200'){
					windowControl("备案成功");
					$('#leaveRecord .list').css('display','block');
					$('#leaveRecord .flow').css('display','none');
					$("#leaveRecord .referenceWindow").css('display','none');
					$('#leaveRecord .traindg').datagrid('reload');
				}else{
					windowControl("备案失败");
				}
			}
		});
	
	});
	//重新审批
	$("#leaveRecord .pastWindow .save").click(function(){
		var text = $('#leaveRecord .pastWindow textarea').val();
		var leaveApplyId = $('#leaveRecord .flow .flowplan .leaveApplyId').val();
		var leaveStreamId = $('#leaveRecord .flow .flowplan .leaveStreamId').val();
		var nextExamineUserName = $('#leaveRecord .pastWindow input[name=nextExamineUserName]').val();
		var nextExamineUserId = $('#leaveRecord .pastWindow input[name=nextExamineUserId]').val();
		var leaveType = getKeyFromValue('leave_type',$('#leaveRecord .flow .flowplan .leaveType').val());
		$.ajax({
			url:'../../leave/rejectRecordApply.do?getMs='+getMS(),
			type:'post',
			data:{
				examineIdea:text,
				leaveApplyId:leaveApplyId,
				leaveStreamId:leaveStreamId,
				nextExamineUserId:nextExamineUserId,
				nextExamineUserName:nextExamineUserName,
				leaveType:leaveType,
				examineStatus:'3'
			},
			success:function(data){
				if(data == '200'){
					windowControl("重新审批成功");
					$('#leaveRecord .list').css('display','block');
					$('#leaveRecord .flow').css('display','none');
					$("#leaveRecord .pastWindow").css('display','none');
					$('#leaveRecord .traindg').datagrid('reload');
				}else{
					windowControl("重新审批失败");
				}
			}
		});
	});
	//选择下一个审批人
	$('#leaveRecord .pastWindow .chooseUser').click(function(){
		chooseUser();
	    $('#chooseUser .confirm').click(function(){
	    	$('#chooseUser').css('display','none');
	    	selectUser = $('#chooseUser .popuparea .user').datagrid('getSelections');
	    	$('#leaveRecord .pastWindow input[name=nextExamineUserName]').val(selectUser[0].userName);
	    	$('#leaveRecord .pastWindow input[name=nextExamineUserId]').val(selectUser[0].userId);
	    })
	})
})
//
$('#leaveRecord .invitetop select[name=leaveType]').html(getDataBySelectKeyNo("leave_type"));
//
function updateLeaveFlowMessage(id){
	$('#leaveRecord .list').css('display','none');
	$('#leaveRecord .flow').css('display','block');
	/*******从服务器获取培训的描述原因等信息*******/
	$.ajax({
		url:'../../leave/findStreamByCondition.do?getMs='+getMS(),
		data:{
			leaveApplyId:id
		},
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
				 }else if(obj.streamType == '2'){
					 html = html + "备案";
				 }
				html = html +'</span><span class="righttime">下一级审批人：'+obj[i].nextExamineUserName+'</span></p>'
					 + '<div class="opinionbox"><div class="opiniontit">审批意见：'+obj[i].examineIdea+'</div>'
					 + '<div class="opinionarea"></div></div>'
					 + '</div></div><div class="arrdown"><span></span></div>'
			}
			$('#leaveRecord .flow .flowplan .leaveStreamId').val(obj[obj.length-1].leaveStreamId);
			$('#leaveRecord .flow .showView').html(html);
		}
	});
	
	/*******从服务器获取培训的流程审批信息*******/
	$.ajax({
		url:'../../leave/findApplyByCondition.do?getMs='+getMS(),
		data:{
			leaveApplyId:id
		},
		type:'post',
		success:function(data){
			var obj = eval('(' + data + ')').rows;
			$('#leaveRecord .flow .flowplan .leaveApplyId').val(obj[0].leaveApplyId);
			$('#leaveRecord .flow .flowplan .userName').val(obj[0].userName);
			$('#leaveRecord .flow .flowplan .leaveType').val(getValueFromKey('leave_type'),obj[0].leaveType);
			$('#leaveRecord .flow .flowplan .applyTime').val(obj[0].applyTimeStr);
			$('#leaveRecord .flow .flowplan .startTime').val(obj[0].startTimeStr);
			$('#leaveRecord .flow .flowplan .overTime').val(obj[0].overTimeStr);
			if(obj[0].examineStatus == 1)
				$('#leaveRecord .flow .flowplan .examineStatus').val('待审');
			else if(obj[0].examineStatus == 2)
				$('#leaveRecord .flow .flowplan .examineStatus').val('撤回');
			else if(obj[0].examineStatus == 3)
				$('#leaveRecord .flow .flowplan .examineStatus').val('通过');
			else if(obj[0].examineStatus == 4)
				$('#leaveRecord .flow .flowplan .examineStatus').val('驳回');
			else if(obj[0].examineStatus == 5)
				$('#leaveRecord .flow .flowplan .examineStatus').val('完成');
			$('#leaveRecord .flow .flowplan .reason').html(obj[0].reason);
		}
	})
}
/******从服务器查询相关的培训流程信息，然后显示在前端页面******/
function selectLeaveFlowMessage(id){
	$('#leaveRecord .list').css('display','none');
	$('#leaveRecord .flow').css('display','block');
	$('#leaveRecord .flow .flowbar .reject').css('display','none');
	$('#leaveRecord .flow .flowbar .reference').css('display','none');
	$('#leaveRecord .flow .flowbar .past').css('display','none');
	/*******从服务器获取培训的描述原因等信息*******/
	$.ajax({
		url:'../../leave/findStreamByCondition.do?getMs='+getMS(),
		data:{
			leaveApplyId:id
		},
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
				 }else if(obj.streamType == '2'){
					 html = html + "备案";
				 }
				html = html +'</span><span class="righttime">下一级审批人：'+obj[i].nextExamineUserName+'</span></p>'
					 + '<div class="opinionbox"><div class="opiniontit">审批意见：'+obj[i].examineIdea+'</div>'
					 + '<div class="opinionarea"></div></div>'
					 + '</div></div><div class="arrdown"><span></span></div>'
			}
			$('#leaveRecord .flow .flowplan .leaveStreamId').val(obj[obj.length-1].leaveStreamId);
			$('#leaveRecord .flow .showView').html(html);
		}
	});
	
	/*******从服务器获取培训的流程审批信息*******/
	$.ajax({
		url:'../../leave/findApplyByCondition.do?getMs='+getMS(),
		data:{
			leaveApplyId:id
		},
		type:'post',
		success:function(data){
			var obj = eval('(' + data + ')').rows;
			$('#leaveRecord .flow .flowplan .leaveApplyId').val(obj[0].leaveApplyId);
			$('#leaveRecord .flow .flowplan .userName').val(obj[0].userName);
			$('#leaveRecord .flow .flowplan .applyTime').val(obj[0].applyTimeStr);
			$('#leaveRecord .flow .flowplan .startTime').val(obj[0].startTimeStr);
			$('#leaveRecord .flow .flowplan .overTime').val(obj[0].overTimeStr);
			if(obj[0].examineStatus == 1)
				$('#leaveRecord .flow .flowplan .examineStatus').val('待审');
			else if(obj[0].examineStatus == 2)
				$('#leaveRecord .flow .flowplan .examineStatus').val('撤回');
			else if(obj[0].examineStatus == 3)
				$('#leaveRecord .flow .flowplan .examineStatus').val('通过');
			else if(obj[0].examineStatus == 4)
				$('#leaveRecord .flow .flowplan .examineStatus').val('驳回');
			else if(obj[0].examineStatus == 5)
				$('#leaveRecord .flow .flowplan .examineStatus').val('完成');
			$('#leaveRecord .flow .flowplan .reason').html(obj[0].reason);
		}
	})
}