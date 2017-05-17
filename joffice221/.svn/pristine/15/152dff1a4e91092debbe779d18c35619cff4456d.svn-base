/**
 *  公共通讯分类详细信息
 * @author YungLocke
 * @class PublicPhoneGroupForm
 * @extends Ext.Window
 */
Ext.ns("PublicPhoneGroupForm");
PublicPhoneGroupForm=Ext.extend(Ext.Window,{
	//构造方法
	constructor:function(_cfg){
	    Ext.applyIf(this,_cfg);
	    //必须先初始化
	    this.initUI();
	    //调用父类构造方法
	    PublicPhoneGroupForm.superclass.constructor.call(this,{
	        id : 'PublicPhoneGroupFormWin',
			title : '公共通讯分类详细信息',
			iconCls:'menu-phonebook',
			width : 300,
			height : 150,
			modal : true,
			layout : 'fit',
			border:false,
			buttonAlign : 'center',
			items : this.formPanel,
			buttons : this.buttons
	    
	    });
	},
	//初始化面板
	initUI:function(){
		//分类信息面板
		this.formPanel = new Ext.FormPanel( {
			layout : 'form',
			defaultType : 'textfield',
			bodyStyle : 'padding:5px;',
			items : [ {
				name : 'phoneGroup.groupId',
				xtype : 'hidden',
				value : this.groupId == null ? '' : this.groupId
			}, {
				fieldLabel : '分类名称',
				name : 'phoneGroup.groupName',
				width:140,
				allowBlank :false
			}, {
	           	 xtype : 'hidden',
	           	 fieldLabel: '是否共享*',
	             name: 'phoneGroup.isShared',
	             value:0
	        }, {
	            xtype:'hidden',
				name : 'phoneGroup.sn',
				width:80
			}, {
	            xtype:'hidden',
				name : 'phoneGroup.isPublic',
				value:1
			}]
		});
		//加载数据
		if (this.groupId!=null && this.groupId != 'undefined') {
			this.formPanel.loadData({
						deferredRender : false,
						url : __ctxPath + '/communicate/getPhoneGroup.do?groupId='+ this.groupId,
						method:'post',
						preName : 'phoneGroup',
						root : 'data',
						waitMsg : '正在载入数据...',
						success : function(form, action){
						},
						failure : function(form, action) {
							Ext.ux.Toast.msg('编辑', '载入失败');
						}
			});
		}
		//底部菜单面板
		this.buttons=[ {
			text : '保存',
			iconCls:'btn-save',
			scope:this,
			handler :this.saveRecord
		}, {
			text : '取消',
			iconCls:'btn-cancel',
			scope:this,
			handler : function() {
				this.close();
			}
		} ];
	},
	//保存
	saveRecord:function(){
	    $postForm({
			formPanel : this.formPanel,
			waitMsg : '正在提交数据...',
			scope : this,
			url : __ctxPath + '/communicate/savePhoneGroup.do',
			callback : function(fp, action) {
				if (this.callback) {
					this.callback.call(this.scope);
				}
				this.close();
			}
		});
	}

});
