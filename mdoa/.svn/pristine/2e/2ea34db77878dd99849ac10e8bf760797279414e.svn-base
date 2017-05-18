$(function(){
	/*设置页面高100%*/
	$('#repertoryControl').css('height',$('#loadarea').height()-31+'px');
	/*设置list的宽度*/
	$('#repertoryControl .listForm').css('width',$('#loadarea').width()+'px');
	/*设置表格的高度*/
	$('#repertoryControldg').css('height',$('#repertoryControl .listForm').height()-30+'px');
	$(window).resize(function() {
		$('#repertoryControl').css('height',$('#loadarea').height()-31+'px');
		$('#repertoryControl .listForm').css('width',$('#loadarea').width()+'px');
	});
	/*----表格化----*/
	$('#repertoryControldg').datagrid({
		//url:'',
		columns:[[
			{field:'物品名',title:'物品名',width:100},
			{field:'规格',title:'规格',width:100},
			{field:'数量',title:'数量',width:50},
			{field:'单位',title:'单位',width:50},
			{field:'警示数量',title:'警示数量',width:80},
			{field:'最新价格',title:'最新价格',width:80},
			{field:'更新日期',title:'更新日期',width:80},
			{field:'是否缺货',title:'是否缺货',width:80,align:'center',
				formatter:function(value,row,index){
					var i= row.警示数量;
					var j= row.数量;
					if(i>j){
						return "缺货";
					}else{
						return "不缺货";					
					}
				}
			},
			{field:'备注',title:'备注',width:100,align:'center',width:100,
				formatter:function(value,row,index){
					return '备注信息XXXXXXXXXX';
			}},
			{field:'_op',title:'操作',width:100,align:'center',width:100,
				formatter:function(value,row,index){
					return '<span class="small-button edit" title="修改" onclick="repertoryControlupDate('+id+')"></span>'
					+'<span class="small-button delete" title="删除" onclick="repertoryControlDel('+id+')"></span>';
			}}
	    ]],
	    singleSelect:true,
	    toolbar:'#repertoryControl .queryForm',
	    pagination:true,
	});
	/*添加部门*/
	$('#repertoryControl .addDpbtn').click(function(){
		$('#repertoryControl .popups .addDepartment').css('display','block');
	});
	$('#repertoryControl .addDepartment .confirm').click(function(){
		var departmentName = $.trim($('#repertoryControl .addDepartment .departmentName').val());
		if(departmentName != ''){
			$.ajax({
				data:{departmentName:departmentName},
				url:'../../repertory/insertGetDepartment.do?getMs='+getMS(),
				type:'post',
				success:function(data){
					windowControl('保存成功');
					$('#repertoryControl .popups .addDepartment').css('display','none');
					$('#repertoryControl .popups .addDepartment .popuparea input').val('');
				},
				error:function(err){
					windowControl(err.status);
				}
			});
		}else{
			windonControl('部门名不能为空');
		}
	});
})