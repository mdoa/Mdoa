/**
 *  通讯分组详细信息
 * @author YungLocke
 * @class PhoneGroupForm
 * @extends Ext.Window
 */
Ext.ns("PublicPhoneGroupForm");
PhoneGroupForm = Ext.extend(Ext.Window,{
	//构造方法
	constructor : function(_cfg){
	    Ext.applyIf(this,_cfg);
	    //必须先初始化
	    this.initUI();
	    //调用父类构造方法
	    PhoneGroupForm.superclass.constructor.call(this,{
	        id : 'PhoneGroupFormWin',
			title : '通讯分组详细信息',
			iconCls : 'menu-phonebook',
			width : 300,
			height : 150,
			modal : true,
			layout : 'fit',
			border : false,
			buttonAlign : 'center',
			items : this.formPanel,
			buttons : this.buttons,
			keys : {
	    		key : Ext.EventObject.ENTER,
	    		fn : this.saveRecord,
	    		scope : this
	    	}
	    });
	},
	//初始化面板
	initUI:function(){
		//分组信息面板
		this.formPanel = new Ext.FormPanel( {
			layout : 'form',
			defaultType : 'textfield',
			bodyStyle : 'padding:5px;',
			items : [ {
				name : 'phoneGroup.groupId',
				id : 'groupId',
				xtype : 'hidden',
				value : this.groupId == null ? '' : this.groupId
			}, {
				fieldLabel : '分组名称',
				name : 'phoneGroup.groupName',
				id : 'groupName',
				width:140,
				allowBlank :false
			}, {
	           	 xtype : 'combo',
	           	 fieldLabel: '是否共享*',
	             hiddenName: 'phoneGroup.isShared',
	             value:'0',
	             mode : 'local',
				 editable : false,
				 allowBlank :false,
				 triggerAction : 'all',
				 store : [['1','是'],['0','否']],
				 width:80
	        }, {
	            xtype:'hidden',
				name : 'phoneGroup.sn',
				id : 'sn',
				width:80
			}, {
	            xtype:'hidden',
				name : 'phoneGroup.isPublic',
				value : this.isPublic
			}]
		});
	
		if (this.groupId != null && this.groupId != 'undefined') {
			this.formPanel.getForm().load({
				deferredRender : false,
				url : __ctxPath + '/communicate/getPhoneGroup.do?groupId=' + this.groupId,
				method : 'post',
				waitMsg : '正在载入数据...',
				success : function(form, action){
				},
				failure : function(form, action) {
					Ext.ux.Toast.msg('编辑', '载入失败');
				}
			});
		}
		
		this.buttons=[ {
			text : '保存',
			iconCls : 'btn-save',
			scope : this,
			handler : this.saveRecord
		}, {
			text : '取消',
			iconCls : 'btn-cancel',
			scope : this,
			handler : function() {
				this.close();
			}
		} ];
	},
	saveRecord : function(){
		$postForm({
			formPanel : this.formPanel,
			scope : this,
			url : __ctxPath + '/communicate/savePhoneGroup.do',
			callback : function(fp, action) {
				if(this.callback){
					this.callback.call(this.scope);
				}
				this.close();
			}
		});
	}

});
