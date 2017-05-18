$(function(){
	var leftCellWidth = $('#dictionary .dictionaryType').width()-3;
	var rightCellWidth = $('#dictionary .dictionaryDetail').width()-3;
	$('#dictionary .dictionaryMain #dictionaryTypeDg').parent().css('height',$('#loadarea').height()-63+'px');
	$("#dictionaryTypeDg").datagrid({
		url:"../../dictionary/queryDictionaryType.do?getMs="+getMS(),
		singleSelect:true,
		method:"post",
		fit: true,
		pagination:false,
		onLoadSuccess:function(){
			$('#dictionary .dictionaryType .datagrid-cell:eq(0)').css({'height':'32px','line-height':'32px','font-size':'14px'});
			$('#dictionary .dictionaryMain #dictionaryDetailDg').parent().css('height',$('#loadarea').height()-63+'px');
		},
		onDblClickRow:function(rowIndex, rowData){
			var selectedRow = $('#dictionaryTypeDg').datagrid('getSelected');
			var selectedKey = selectedRow.selectKey;
			$('#dictionaryDetailDg').datagrid({
				url:"../../dictionary/queryDictionary.do?getMs="+getMS(),
				queryParams:{
					selectKey:selectedKey
				},
				singleSelect:true,
				method:"post",
				fit: true,
				pagination:false,
				onLoadSuccess:function(data){
					$('#dictionary .dictionaryDetail .datagrid-cell:eq(0)').css({'height':'32px','line-height':'32px','font-size':'14px'});
					$('#dictionary .dictionaryDetail .downsetDictionary:last').remove();
					$('#dictionary .dictionaryDetail .upsetDictionary:last').before('<span class="last-button"></span>');
				},
				columns:[[
				    {field:"optionKey",title:"编号",fitColumns:true,resizable:true,align:"center",sortable:true,width:100},
				    {field:"optionValue",title:"名称",fitColumns:true,resizable:true,align:"center",sortable:true,width:100,formatter:function(value,row,index){
				    	if(row.isDefault == 1){
				    		return '<span style="color:green;">'+value+'</span>';
				    	}else{
				    		return '<span>'+value+'</span>';
				    	}
				    }},
				    {field:"createUserName",title:"创建人",fitColumns:true,resizable:true,align:"center",sortable:true,width:175},
				    {field:"createTimeStr",title:"创建时间",fitColumns:true,resizable:true,align:"center",sortable:true,width:175},
				    {field:"updateUserName",title:"修改人",fitColumns:true,resizable:true,align:"center",sortable:true,width:175},
				    {field:"updateTimeStr",title:"修改时间",fitColumns:true,resizable:true,align:"center",sortable:true,width:175},
				    {field:"aliveFlag",title:"是否有效",fitColumns:true,resizable:true,align:"center",sortable:true,width:100,formatter:function(value,row,index){
				    	if(row.aliveFlag == 1){
				    		return "有效";
				    	}else{
				    		return "无效";
				    	}
				    }},
				    {field:"_op",title:"管理",width:100,resizable:true,align:"center",sortable:true,formatter:function(value,row,index){
				    	   var id ="'"+row.dictionaryId+"'";
				    	   var selectKey = "'"+row.selectKey+"'";
				    	   var optionKey = "'"+row.optionKey+"'";
				    	   var optionValue = "'"+row.optionValue+"'";
				    	   var operation = '';
				    	   operation +='<span class="small-button edit" onclick="editDictionary('+id+','+selectKey+','+optionKey+','+optionValue+')" title="编辑"></span>';
				    	   operation +='<span class="small-button delete" onclick="deleteDictionary('+id+','+selectKey+','+optionKey+','+index+')" title="弃用"></span>';
				    	   //operation +='<span class="empty-button"></span>';
				    	   operation +='<span class="downset downsetDictionary" onclick="downsetDictionary('+index+')" title="下移"></span>';
				    	   if(row.isDefault == 1){
				    		   operation +='<span class="empty-button"></span>';
				    	   }else{
				    		   operation +='<span class="small-button defaultSet" onclick="setDefault('+id+')"></span>';
				    	   }
				    	   if(index != 0){
				    		   operation +='<span class="upset upsetDictionary" onclick="upsetDictionary('+index+')" title="上移"></span>';
				    	   }else{
				    		   operation +='<span class="empty-button"></span>';
				    	   }
				    	   return operation;
				     }},
				    {field:"dictionaryId",hidden:true},
				    {field:"orderCode",hidden:true},
				    {field:"isDefault",hidden:true}
				]]
			});
			$('#dictionary .maintop input[name=addDictionary]').css('display','inline');
		},
		columns:[[
		     {field:"selectName",title:"数据类别",fitColumns:true,resizable:true,align:"center",sortable:true,width:leftCellWidth},
		]]
	});
	
	/*********************************确认修改数据项点击事件******************************************/
	$('#dictionary .popups .editDictionary input[type=button][name=ensure]').click(function(){
		var optionKey = $('#dictionary .popups input[type=text][name=optionKey]').val();
		var optionValue = $('#dictionary .popups input[type=text][name=optionValue]').val();
		var selectKey = $('#dictionary .popups input[name=selectKey]').val();
		var id = $('#dictionary .popups .hidden[name=id]').val();
		$('#dictionary .popups input[type=text]').val(null);
		$('#dictionary .popups .editDictionary').css('display','none');
		$.ajax({
			data:{
				dictionaryId:id,
				selectKey:selectKey,
				optionKey:optionKey,
				optionValue:optionValue
			},
			url:"../../dictionary/updateDictionary.do?getMs="+getMS(),
			success:function(data){
				if(data == '200'){
					windowControl("修改成功");
				}else{
					windowControl("修改失败");
				}
				$('#dictionaryDetailDg').datagrid('reload');
			},
			fail:function(){
				windowControl("服务器未响应");
			}
		})
	})
	/*-----------------------添加数据项按钮-------------------------------*/
	$('#dictionary .maintop input[name=addDictionary]').click(function(){
		$('#dictionary .popups .addDictionary').css('display','block');
	});
	/*-----------------------添加数据项----------------------------------*/
	$('#dictionary .popups .addDictionary .confirm').click(function(){
		var optionKey = $.trim($('#dictionary .addDictionary .popuparea input[name=optionKey]').val());
 		var optionValue = $.trim($('#dictionary .addDictionary .popuparea input[name=optionValue]').val());
 		var selectKey = $('#dictionaryDetailDg').datagrid('getRows')[0].selectKey;
 		var selectName = $('#dictionaryDetailDg').datagrid('getRows')[0].selectName;
 		if(optionKey == ''||optionKey == null){
 			windowControl('数据项编号不能为空');
 			return false;
 		}else if(optionValue == ''||optionValue == null){
 			windowControl('数据项名称不能为空');
 			return false;
 		}else{
 			var info = {
 				optionKey:optionKey,
 				selectKey:selectKey,
 				selectName:selectName,
 				optionValue:optionValue,
 				isDefault:'0',
 			}
 			$.ajax({
 	 			url:'../../dictionary/addDictionary.do?getMs='+getMS(),
 	 			data:info,
 	 			success:function(data){
 	 				if(data == 200){
 	 					windowControl('添加数据项成功');
 	 					$('#dictionary .popups .addDictionary').css('display','none');
 	 					$('#dictionary .popups .addDictionary .popuparea input[type=text]').val(null);
 	 					$('#dictionaryDetailDg').datagrid('reload');
 	 				}else{
 	 					windowControl('添加数据项失败');
 	 				}
 	 			},
 	 			error:function(err){
 	 				windowControl('网络异常');
 	 			}
 	 		});
 		}
	});
	/*********************************取消修改数据项点击事件******************************************/
	$('#dictionary .popups .editDictionary input[type=button][name=cancel]').click(function(){
		$('#dictionary .popups input[type=text]').val(null);
		$('#dictionary .popups .editDictionary').css('display','none');
	})
	
	/*******************************确认修改数据字典顺序的方法************************************/
	$('#dictionary .maintop input[name=sortDictionary]').click(function(){
		ui.confirm('确认是否修改数据字典顺序',function(z){
			var rows = $('#dictionaryDetailDg').datagrid('getRows');
			var dictionaries = [];
			for(var i = 0;i < rows.length;i++){
				var dictionary = {
					dictionaryId:rows[i].dictionaryId,
					orderCode:rows[i].orderCode
				};
				dictionaries.push(dictionary);
			}
			var params = JSON.stringify(dictionaries);
			$.ajax({
				url:'../../dictionary/updateDictionaryOrder.do?getMs='+getMS(),
				data:{jsonString:params},
				success:function(data){
					if(data=='200'){
						windowControl("修改顺序成功");
						$('#dictionary .maintop input[name=sortDictionary]').css('display','none');
					}else{
						windowControl("修改顺序失败");
					}
				},
				fail:function(){
					windowControl("服务器未响应");
				}
			})
		});
	});
})

/*********************************编辑数据字典行的方法******************************************/
function editDictionary(id,selectKey,optionKey,optionValue){
	$('#dictionary .popups .editDictionary').css('display','block');
	$('#dictionary .popups .editDictionary input[type=text][name=optionKey]').val(optionKey);
	$('#dictionary .popups .editDictionary input[type=text][name=optionValue]').val(optionValue);
	$('#dictionary .popups .editDictionary input[name=selectKey]').val(selectKey);
	$('#dictionary .popups .hidden[name=id]').val(id);
}

/*********************************弃用数据字典行的方法******************************************/
function deleteDictionary(id,selectKey,optionKey,index){
	ui.confirm('是否确认弃用该数据项？',function(z){ 
		if(z){
			$.ajax({
				data:{dictionaryId:id,selectKey:selectKey,optionKey:optionKey},
				url:"../../dictionary/deleteDictionary.do?getMs="+getMS(),
				success:function(data){
					if(data == '200'){
						windowControl("弃用成功");
					}else{
						windowControl("弃用失败");
					}
					$('#dictionaryDetailDg').datagrid('reload');
				},
				fail:function(){
					windowControl("服务器未响应");
				}
			})
		}
	},false)
}

/*********************************下移数据字典行的方法******************************************/
function downsetDictionary(index,row){
	var rows = $('#dictionaryDetailDg').datagrid('getRows');
	var total = rows.length;
	var curRow = rows[index];
	var nextRow = rows[index+1];
	var curParam = {
			optionKey:nextRow.optionKey||"",
			optionValue:nextRow.optionValue||"",
			createTimeStr:nextRow.createTimeStr||"",
			createUserName:nextRow.createUserName||"",
			updateTimeStr:nextRow.updateTimeStr||"",
			updateUserName:nextRow.updateUserName||"",
			dictionaryId:nextRow.dictionaryId||"",
			isDefault:nextRow.isDefault||""
	};
	nextParam = {
			optionKey:curRow.optionKey||"",
			optionValue:curRow.optionValue||"",
			createTimeStr:curRow.createTimeStr||"",
			createUserName:curRow.createUserName||"",
			updateTimeStr:curRow.updateTimeStr||"",
			updateUserName:curRow.updateUserName||"",
			dictionaryId:curRow.dictionaryId||"",
			isDefault:curRow.isDefault||""
	};
	$('#dictionaryDetailDg').datagrid('updateRow',{
		index:index,
		row:curParam
	});
	$('#dictionaryDetailDg').datagrid('updateRow',{
		index:index+1,
		row:nextParam
	});
	var rows2 = $('#dictionaryDetailDg').datagrid('getRows');
	if(index == total-1 || index+1 == total-1){
		$('#dictionary .dictionaryDetail .downsetDictionary:last').remove();
		$('#dictionary .dictionaryDetail .upsetDictionary:last').before('<span class="last-button"></span>');
	}
	var display = $('#dictionary .maintop input[name=sortDictionary]').css('display');
	if(display == 'none'){
		$('#dictionary .maintop input[name=sortDictionary]').css('display','inline');
	}
}

/*********************************上移数据字典行的方法******************************************/
function upsetDictionary(index,row){
	var rows = $('#dictionaryDetailDg').datagrid('getRows');
	var total = rows.length;
	var curRow = rows[index];
	var lastRow = rows[index-1];
	var curParam = {
			optionKey:lastRow.optionKey||"",
			optionValue:lastRow.optionValue||"",
			createTimeStr:lastRow.createTimeStr||"",
			createUserName:lastRow.createUserName||"",
			updateTimeStr:lastRow.updateTimeStr||"",
			updateUserName:lastRow.updateUserName||"",
			dictionaryId:lastRow.dictionaryId||"",
			isDefault:lastRow.isDefault||""
	};
	lastParam = {
			optionKey:curRow.optionKey||"",
			optionValue:curRow.optionValue||"",
			createTimeStr:curRow.createTimeStr||"",
			createUserName:curRow.createUserName||"",
			updateTimeStr:curRow.updateTimeStr||"",
			updateUserName:curRow.updateUserName||"",
			dictionaryId:curRow.dictionaryId||"",
			isDefault:curRow.isDefault||""
	};
	$('#dictionaryDetailDg').datagrid('updateRow',{
		index:index,
		row:curParam
	});
	$('#dictionaryDetailDg').datagrid('updateRow',{
		index:index-1,
		row:lastParam
	});
	if(index == total-1 || index-1 == total-1){
		$('#dictionary .dictionaryDetail .downsetDictionary:last').remove();
		$('#dictionary .dictionaryDetail .upsetDictionary:last').before('<span class="last-button"></span>');
	}
	var display = $('#dictionary .maintop input[name=sortDictionary]').css('display');
	if(display == 'none'){
		$('#dictionary .maintop input[name=sortDictionary]').css('display','inline');
	}
}
/*-------------------------设置默认-----------------------*/
function setDefault(id){
	var selectKey = $('#dictionaryDetailDg').datagrid('getRows')[0].selectKey;
	ui.confirm('是否将这项设置为默认项',function(z){
		if(z){
			$.ajax({
				data:{dictionaryId:id,isDefault:'1',selectKey:selectKey},
				url:"../../dictionary/updateDictionary.do?getMs="+getMS(),
				success:function(data){
					if(data == '200'){
						windowControl("设置默认成功");
					}else{
						windowControl("设置默认失败");
					}
					$('#dictionaryDetailDg').datagrid('reload');
				},
				fail:function(){
					windowControl("服务器未响应");
				}
			});
		}
	});
}

