$(function(){
	var flowindex = $('.flowplan').length-1;
	var flowcontent = $.parseJSON(toflow);
	var len = flowcontent.rows.length-1;
	var content = null;
	var Status = null;
	var dom = null;
	console.log(toflow);
	$.ajax({
		data:{welfareId:flowcontent.rows[0].welfareId,},
		url:'../../welfare/findWelfareByCondition.do?getMs='+getMS(),
		type:'post',
		success:function(data){
			content = $.parseJSON(data);
			if(content.rows[len].examineStatus == 1){
				Status = "申请";
			}else if(content.rows[len].examineStatus == 2){
				Status = "撤回";
			}else if(content.rows[len].examineStatus == 3){
				Status = "通过";
			}else if(content.rows[len].examineStatus == 4){
				Status = "驳回";
			}
			//<p class="jiachu">流程名：</p><p><input type="text"  class="flowName" value="'+content.rows[0].planName+'"/></p><p class="jiachu">发起人：</p><p><input type="text" class="applyName" value="'+content.rows[0].applyUserName+'"/></p>
			var str = '<p class="jiachu">发起时间：</p><p><input type="text" class="startTime" value="'+content.rows[0].createTimeStr+'"/></p><p class="jiachu">审批状态：</p><p><input type="text" class="examineStatus" value="'+Status+'"/></p><p class="jiachu">发放理由：</p><div class="applyDescribe">'+content.rows[0].reason+'</div>';
			$('#welfarelookflow .flowplan').append(str);
		},
		error:function(err){
			windowControl(err.status);
		}
	});
	for(var i=0;i <= len;i++){
	   /*if(flowcontent.rows[i].examineStatus == 1){
				Status = "申请";
			}else if(flowcontent.rows[i].examineStatus == 2){
				Status = "撤回";
			}else if(flowcontent.rows[i].examineStatus == 3){
				Status = "通过";
			}else if(flowcontent.rows[i].examineStatus == 4){
				Status = "驳回";
			}*/
	    Status = getValueFromKey("examine_status",flowcontent.rows[i].examineStatus);
		var str = '<div class="process"><div class="processbox"><p><span class="jiachu expeop" style="margin-left: 2px;">&ensp;审批人：'
		+flowcontent.rows[i].examineUserName+'</span><span class="righttime">&ensp;&ensp;&ensp;&ensp;审批时间：'
		+flowcontent.rows[i].updateTimeStr+'</span></p><p><span class="expeop">审批类型：'+Status+'</span><span class="righttime"> 下一级审批人：'
		+flowcontent.rows[i].nextExamineUserName+'</span></p><div class="opinionbox"><div class="opiniontit">审批意见：</div><div class="opinionarea">'
		+flowcontent.rows[i].examineIdea+'</div></div></div></div><div class="arrdown"><span></span></div>'
	}
	$('#welfarelookflow .showView').append(str);
});