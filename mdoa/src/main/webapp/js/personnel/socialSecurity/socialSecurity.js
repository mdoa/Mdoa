$(function(){
	if(existPermission('admin:personnel:socialSecurity:socialSecurity:add'))
		$('#socialSecurity .maintop').append('<div><span class="maintopicon mainicon2"></span><span class="add">添加社保</span></div><div class="defined"><span class="maintopicon mainicon2"></span><span>定义社保类型</span></div>');

	//存储社保类型信息的数组
	var insTypes = [];
	refreshType();
	
	$("#socialSecurity a[name=search]").click(function(){
		var superType = $("#socialSecurity .invitetop select[name=superType] option:selected").val();
		var insuranceTypeId = $("#socialSecurity .invitetop select[name=typeName] option:selected").val();
		var userName = $("#socialSecurity .invitetop input[name=peopleName]").val();
		var idCard = $("#socialSecurity .invitetop input[name=idCard]").val();
		$('#socialSecuritydg').datagrid({
			url:'../../insurance/findInsuranceByCondition.do?getMs='+getMS(),
			queryParams:{
				superType:superType,
				insuranceTypeId:insuranceTypeId,
				userName:userName,
				idCard:idCard
			}
		})
	})
	
		//搜索条件superType下拉框的onchange事件
	$('#socialSecurity .invitetop select[name=superType]').change(function(){
		var superType = $(this).find("option:selected").val();
		if(superType == '' || superType == null){
			$('#socialSecurity .invitetop select[name=typeName]').html("<option value=''></option>");
			return false;
		}else{
			var str = '';
			for(var i = 0;i<insTypes.length;i++){
				var insType = insTypes[i];
				if(insType.superType == superType){
					str += '<option value="'+insType.insuranceTypeId+'">'+insType.typeName+'</option>'
				}else continue;
			}
			$('#socialSecurity .invitetop select[name=typeName]').html("<option value=''></option>");
			$('#socialSecurity .invitetop select[name=typeName]').append(str);
		}
	})
	
	$('#socialSecuritydg').datagrid({
		  // url:'../../insurance/findInsuranceByCondition.do?getMs='+getMS(),
		   rownumbers:"true",
		   singleSelect:true,
		   pagination:true,
		   toolbar:"#toolbar",
		   method:"post",
		   toolbar:"#socialSecurity .invitetop",
		   fit: true, 
		   columns:[[
		     // {field:"ck",checkbox:true },
		       {field:"userName",title:"所属员工姓名",fitColumns:true,resizable:true,align:"center",sortable:true,width:90},
		       {field:"idCard",title:"身份证号",fitColumns:true,sortable:true,align:"center",sortable:true,width:140},
		       {field:"superType",title:"社保大类型",fitColumns:true,sortable:true,align:"center",sortable:true,width:100,formatter:function(value,row,index){
		    	   if(value == '01'){
		    		   return '养老保险';
		    	   }else if(value == '02'){
		    		   return '医疗保险';
		    	   }else if(value == '03'){
		    		   return '失业保险';
		    	   }else if(value == '04'){
		    		   return '工伤保险';
		    	   }else if(value == '05'){
		    		   return '生育保险';
		    	   }else if(value == '06'){
		    		   return '住房公积金';
		    	   }
		       }},
		       {field:"insuranceTypeName",title:"保险类型",fitColumns:true,resizable:true,align:"center",sortable:true,width:100},
		       {field:"text",title:"保险描述",fitColumns:true,resizable:true,align:"center",sortable:true,width:150},
		       {field:"createTimeStr",title:"创建时间",fitColumns:true,resizable:true,align:"center",sortable:true,width:130},
		    ]]
	}); 
	
	$('#socialSecurity .defined').click(function(){
		$('#socialSecurity .defineissue').css('display','block');
	});
	
	$('#socialSecurity .inviteissue .socChoose textarea').css({
		'width':'100%',
		'height':'100%',
	});
	
	//添加社保信息 点击事件
	$('#socialSecurity .maintop .add').click(function(){
		$('.popups .savesocialsecurity').css('display','block');
	});
	
	//添加社保信息 选择人员点击事件
	$('#socialSecurity .savesocialsecurity .chooseUser').click(function(){
		chooseUser();
	    $('#chooseUser .confirm').click(function(){
	    	$('#chooseUser').css('display','none');
	    	selectUser = $('#chooseUser .popuparea .user').datagrid('getSelections');
	    	$('#socialSecurity .savesocialsecurity input[name=peopleName]').val(selectUser[0].userName);
	    	$('#socialSecurity .savesocialsecurity input[name=userId]').val(selectUser[0].userId);
	    })
	})
	
	//superType下拉框的onchange事件
	$('#socialSecurity .savesocialsecurity select[name=superType]').change(function(){
		var superType = $(this).find("option:selected").val();
		if(superType == '' || superType == null){
			$('#socialSecurity .savesocialsecurity select[name=typeName]').html("<option value=''></option>");
			return false;
		}else{
			var str = '';
			for(var i = 0;i<insTypes.length;i++){
				var insType = insTypes[i];
				if(insType.superType == superType){
					str += '<option value="'+insType.insuranceTypeId+'">'+insType.typeName+'</option>'
				}else continue;
			}
			console.log("insType.typeName="+insType.typeName);
			$('#socialSecurity .savesocialsecurity select[name=typeName]').html("<option value=''></option>");
			$('#socialSecurity .savesocialsecurity select[name=typeName]').append(str);
			$('#socialSecurity .savesocialsecurity textarea[name=typeText]').val(null);
		}
	})
	
	//typeName下拉框的onchange事件
	$('#socialSecurity .savesocialsecurity select[name=typeName]').change(function(){
		var typeId = $(this).find("option:selected").val();
		var text;
		if(typeId == '' || typeId == null){
			$('#socialSecurity .savesocialsecurity textarea[name=typeText]').val("");
			return false;
		}else{
			for(var i = 0;i<insTypes.length;i++){
				if(insTypes[i].insuranceTypeId == typeId){
					text = insTypes[i].text;
				}else continue;
			}
			$('#socialSecurity .savesocialsecurity textarea[name=typeText]').val(text);
		}
	})
	
	//添加社保信息 弹窗 确定点击事件
	$('#socialSecurity .savesocialsecurity .confirm').click(function(){
		var userId = $.trim($('#socialSecurity .savesocialsecurity input[name=userId]').val());
		var userName = $.trim($('#socialSecurity .savesocialsecurity input[name=peopleName]').val());
		var typeId = $.trim($('#socialSecurity .savesocialsecurity select[name=typeName]').find("option:selected").val());
		var typeName = $.trim($('#socialSecurity .savesocialsecurity select[name=typeName]').find("option:selected").text());
		if(userName == '' || userName == null|| userId == '' || userId == null){
			windowControl('员工信息有误');
		}else if(typeName == '' || typeName == null|| typeId == '' || typeId == null){
			windowControl('社保类型有误');
		}else{
			$.ajax({
				data:{
					userId:userId,
					insuranceTypeName:typeName,
					insuranceTypeId:typeId,
				},
				url : '../../insurance/insertInsurance.do?getMs='+getMS(),
				type : 'post',
				success : function(data){
					if(data == '200'){
						windowControl('添加员工信息成功');
						$('#socialSecurity .popup').css('display','none');
						$('#socialSecurity .popup input').not('.button').val(null);
						$('#socialSecurity .popup textarea').val(null);
						$('#socialSecurity .popup select').children('option:first-child').attr('selected',true);
						$('#socialSecuritydg').datagrid('reload')
					}else windowControl(data);
				},
				error : function(err) {
					windowControl(err.status);
				}
			});
		}
	});
	
	//设置数据网格窗体的大小
	$('#socialSecuritydg').parent().css('height',$('#loadarea').height()-62);
	
	//定义社保类型 点击事件
	$('#socialSecurity .maintop .defined').click(function(){
		$('.popups .definedsocialsecurity').css('display','block');
	});
	//定义社保类型弹窗 确定按钮点击事件
	$('#socialSecurity .popups .definedsocialsecurity .confirm').click(function(){
		var typeName = $.trim($('#socialSecurity .definedsocialsecurity input[name=typeName]').val());
		var socialS = $.trim($('#socialSecurity .definedsocialsecurity select').find('option:selected').val());
		var socialDis = $.trim($('#socialSecurity textarea[name=socialDis]').val());
		if(typeName == "" || typeName == null){
			windowControl('社保类型名字不能为空');
		}else if(socialDis == "" && socialDis == null){
			windowControl('描述不能为空');
		}else{
			if(socialS == '养老保险'){
				socialS = '01';
			}else if(socialS == '医疗保险'){
				socialS = '02';
			}else if(socialS == '失业保险'){
				socialS = '03';
			}else if(socialS == '工伤保险'){
				socialS = '04';
			}else if(socialS == '生育保险'){
				socialS = '05';
			}else if(socialS == '住房公积金'){
				socialS = '06';
			}
			$.ajax({
				data:{
					typeName:typeName,
					superType:socialS,
					text:socialDis,
				},
				url : '../../insurance/insertInsuranceType.do?getMs='+getMS(),
				type : 'post',
				success : function(data){
					windowControl('保存成功');
					refreshType();
					$('#socialSecurity .popup').css('display','none');
					$('#socialSecurity .popup input').not(".button").val(null);
					$('#socialSecurity .popup textarea').val(null);
					$('#socialSecurity .popup select').children('option:first-child').attr('selected',true);
				},
				error : function(err) {
					windowControl(err.status);
				}
			});
		}
	});
	/*刷新社保类型*/
	function refreshType(){
		$.ajax({
			url:"../../insurance/findTypeByCondition?getMs="+getMS(),
			type:"post",
			success:function(data){
				insTypes = [];
				var content = $.parseJSON(data);
				for(var i=0;i<content.rows.length;i++){
					insTypes.push(content.rows[i]);
				}
			}
		})
	}
	
	/**************superType****************/
	$('#socialSecurity .invitetop select[name=superType]').html(getDataBySelectKeyNo("super_type"));
	/**************superType****************/
	$('#socialSecurity .popups .savesocialsecurity select[name=superType]').html(getDataBySelectKeyNo("super_type"));
	/**************superType****************/
	$('#socialSecurity .popups .definedsocialsecurity select[name=superType]').html(getDataBySelectKey("super_type"));
});