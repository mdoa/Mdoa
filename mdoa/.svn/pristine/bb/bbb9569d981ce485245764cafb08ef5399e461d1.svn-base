/*-------------------------------invite-top------------------------------------------*/
$(function(){
	$('.invitetop').children("input:last-child").click(function(){
		$(this).parent().find('input[type="text"]').val(null);
		$(this).parent().find('select option:eq(0)').attr('selected',true);
	});
	$('.maintop .add').click(function(){
		var CPM = $(this).parents('.maintop').siblings('.inviteissue').eq(0);
		CPM.css('display','block');
	})
	$('.list .maintop .add').click(function(){
		var CPM = $(this).parents('.list').siblings('.inviteissue').eq(0);
		CPM.css('display','block');
	})
	$('.inviteissue p textarea').width($('.inviteissue p input').width()-2);
	$('.invitemain table tr th input').click(function(){
		if($(this).attr('checked') == 'checked'){
			$('.invitemain table tr td:eq(1) input').attr('checked',true);
		}else{
			$('.invitemain table tr td:eq(1) input').attr('checked',false);
		}	
	});
	$('.inviteissue .cancel').click(function(){
		$(this).parents('.inviteissue').css('display','none');
	});
	$('.inviteissue .turnoff').click(function(){
		$(this).parents('.inviteissue').css('display','none');
	});
	$('.inviteissue .reset').click(function(){
		$(this).parents('.inviteissue').find('input').val(null);
		$(this).parents('.inviteissue').find('textarea').val(null);
	});
})

