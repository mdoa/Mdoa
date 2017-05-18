$(function(){
	var flowindex = $('.flowplan').length-1;
	var flowcontent = $.parseJSON(toflow);
	var len = flowcontent.rows.length-1;
	var y = $('.welfarerecordflow').length-1;
	var content = null;
	var Status = null;
	var dom = null;
	$('.welfarerecordflow .inviteissue textarea').css({
		'width':'458px',
		'height':'158px',
		'outline':'none',
	})
	if(flowcontent.rows[len].examineStatus == 1){
		Status = "申请";
	}else if(flowcontent.rows[len].examineStatus == 2){
		Status = "撤回";
	}else if(flowcontent.rows[len].examineStatus == 3){
		Status = "通过";
	}else if(flowcontent.rows[len].examineStatus == 4){
		Status = "驳回";
	}
	//<p class="jiachu">流程名：</p><p><input type="text"  class="flowName" value="'+content.rows[0].planName+'"/></p><p class="jiachu">发起人：</p><p><input type="text" class="applyName" value="'+content.rows[0].applyUserName+'"/></p>
	var str = '<p class="jiachu">发起时间：</p><p><input type="text" class="startTime" value="'
		+flowcontent.rows[0].createTimeStr+'"/></p><p class="jiachu">审批状态：</p><p><input type="text" class="examineStatus" value="'
		+Status+'"/></p><p class="jiachu">发放理由：</p><div class="applyDescribe">'
		+flowcontent.rows[0].reason+'</div><p class="jiachu">福利内容：</p><div class="applyDescribe">'
		+flowcontent.rows[0].text+'</div>';
	$('.welfarerecordflow:eq('+y+') .flowplan').append(str);
	for(var i=0;i <= len;i++){
	   if(flowcontent.rows[i].examineStatus == 1){
				Status = "申请";
			}else if(flowcontent.rows[i].examineStatus == 2){
				Status = "撤回";
			}else if(flowcontent.rows[i].examineStatus == 3){
				Status = "通过";
			}else if(flowcontent.rows[i].examineStatus == 4){
				Status = "驳回";
			}
		var str = '<div class="process"><div class="processbox"><p><span class="jiachu expeop" style="margin-left: 2px;">&ensp;审批人：'
		+flowcontent.rows[i].examineUserName+'</span><span class="righttime">&ensp;&ensp;&ensp;&ensp;审批时间：'
		+flowcontent.rows[i].updateTimeStr+'</span></p><p><span class="expeop">审批类型：'+Status+'</span><span class="righttime"> 下一级审批人：'
		+flowcontent.rows[i].nextExamineUserName+'</span></p><div class="opinionbox"><div class="opiniontit">审批意见：</div><div class="opinionarea">'
		+flowcontent.rows[i].examineIdea+'</div></div></div></div><div class="arrdown"><span></span></div>'
	}
	$('.welfarerecordflow:eq('+y+') .showView').append(str);
	var flag;
	var dom1 ;
	$('.welfarerecordflow .reject').click(function (){
		flag = 'reject';
		dom1 = $(this).parents('.welfarerecordflow').find('.inviteissue');
		dom1.find('.nextpeople').css('display','block');
		dom1.find('span:eq(0)').text('驳回信息框');
		dom1.css('display','block');
	});
	$('.welfarerecordflow .reference').click(function (){
		flag = 'reference';
		dom1 = $(this).parents('.welfarerecordflow').find('.inviteissue');
		dom1.find('.nextpeople').css('display','block');
		dom1.find('span:eq(0)').text('备案信息框');
		dom1.css('display','block');
	});
	$('.welfarerecordflow .back').click(function (){
		flag = 'back';
		dom1 = $(this).parents('.welfarerecordflow').find('.inviteissue');
		dom1.find('.nextpeople').css('display','block');
		dom1.find('span:eq(0)').text('通过提示框');
		dom1.css('display','block');
	});
	$('.welfarerecordflow .turnoff').click(function (){
		dom1 = $(this).parents('.inviteissue');
		dom1.css('display','none');
		dom1.find('textarea').val(null);
	});
	$('.welfarerecordflow .cannel').click(function (){
		dom1 = $(this).parents('.inviteissue');
		dom1.css('display','none');
		dom1.find('textarea').val(null);
	});
	$('.welfarerecordflow .save').click(function (){
		dom1 = $(this).parents('.inviteissue');
		var idea = dom1.find('textarea').val();
		if(flag == 'reject'){
			$.ajax({
				data:{examineIdea:idea,
					welfareStreamId:flowcontent.rows[0].welfareStreamId,
					welfareId:flowcontent.rows[0].welfareId,
					examineStatus:'4',
				},
				url:'../../welfare/rejectWelfareApply.do?getMs='+getMS(),
				success:function(data){
					windowControl('驳回成功');
				},
				error:function(err){
					windowControl(err.status);
				}
			});
		}else if(flag == 'back'){ 
			var nextUserName = $('.welfareflow .gather').find('option:selected').val();
			$.ajax({
				data:{examineIdea:idea,
					welfareStreamId:flowcontent.rows[0].welfareStreamId,
					welfareId:flowcontent.rows[0].welfareId,
					examineStatus:'3',
					nextExamineUserName:nextUserName,
				},
				url:'../../welfare/rejectRecordApply.do?getMs='+getMS(),
				success:function(data){
					windowControl('提交成功');
				},
				error:function(err){
					windowControl(err.status);
				}
			});
		}else if(flag == 'reference'){
			$.ajax({
				data:{examineIdea:idea,
					welfareStreamId:flowcontent.rows[0].welfareStreamId,
					welfareId:flowcontent.rows[0].welfareId,
					examineStatus:'3',
				},
				url:'../../welfare/recordWelfareApply.do?getMs='+getMS(),
				success:function(data){
					windowControl('已进入备案流程');
				},
				error:function(err){
					windowControl(err.status);
				}
			});
		}
		dom1.css('display','none');
		dom1.find('textarea').val(null);
	});
});