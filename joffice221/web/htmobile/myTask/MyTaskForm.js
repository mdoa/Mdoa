
/**
 * 我的待办表单
 * by cjj
 */

Ext.define('mobile.myTask.MyTaskForm', {
    extend: 'mobile.myTask.TableForm',
    
    name: 'myTaskForm',

    constructor: function (config) {
		
		config = config || {};
    	
    	
        this.callParent([config]);
	},

	formSubmit:function(){
		var tabpanel = this.up('tabpanel');
		var tabpanelconfig = tabpanel.config;
		var formpanel = tabpanel.down('formpanel');
		// 会签
		var signVoteType = null;
		if(tabpanelconfig.isSignTask){
			var signField = formpanel.getCmpByName('signField');
			var signItems = signField.getInnerItems();
			for(var idx=0;idx<signItems.length;idx++){
				var vote = signItems[idx];
				if(vote.getChecked()){
					var voteVal = vote.getValue();
					signVoteType = voteVal!=null?voteVal:0;
					break;
				}
			}
		}
		// 短讯提醒
		var sendMsg = formpanel.getCmpByName('sms');
		// 邮件提醒
		var sendMail = formpanel.getCmpByName('mail');
		// 是否驳回
		var back = this.config.active==2?'true':'false';
		// 选择流程路径
		var dest = formpanel.getCmpByName("dest");

		if(tabpanelconfig.extForm)
		{
			formpanel.formSubmit({
				voteAgree: voteAgree,
				formpanel: formpanel
			});
		}
		else
		{
			if(tabpanel.validate(tabpanel,signVoteType)){
				formpanel.submit({
					url: __ctxPath+'/htmobile/doNextTask.do',
			        params: {
			        	'comments': formpanel.getCmpByName('comments').getValue(),
			        	'taskId':tabpanelconfig.taskId,
						'defId':tabpanelconfig.defId,
						'activityName':tabpanelconfig.activityName,
						'destName':dest.getValue(),
			        	'signVoteType':signVoteType,
			        	'back':back,
			        	'sendMsg': sendMsg.getChecked()?'true':'false',
			        	'sendMail': sendMail.getChecked()?'true':'false',
			        	'preHandler':tabpanelconfig.preHandler,
			        	'afterHandler':tabpanelconfig.afterHandler,
					},
			        method: 'POST',
			        success: function(form,action,response) {
			        	tabpanelconfig.callback.call();
			        	Ext.Msg.alert('保存成功');
			        	mobileNavi.pop();
			        },
			        failure: function(form,action,response){
						var obj = Ext.util.JSON.decode(response);
						Ext.Msg.alert('', obj.msg);
			        }
				});
			}
		}
	},
	
	validate:function(tabpanel,signVoteType){
		var tabpanelconfig = tabpanel.config;
		if(tabpanelconfig.isSignTask){
			if(signVoteType==null){
				Ext.Msg.alert('', '请进行会签设置');
				return false;
			}
		}
		return true;
	}

});
