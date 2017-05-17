var DocFolderSelector = Ext.extend(Ext.Window, {
			// 构造方法
			constructor : function(conf) {
				Ext.applyIf(this, conf);
				this.initUI();
				DocFolderSelector.superclass.constructor.call(this, {
							title : '请选择目录',
							width : 440,
							iconCls : 'menu-mail_folder',
							height : 420,
							layout : 'fit',
							items : [this.treePanel],
							modal : true,
							buttonAlign : 'center',
							buttons : [{
										text : '确认',
										iconCls : 'btn-ok',
										scope : this,
										handler : this.confirm
									}, {
										text : '关闭',
										iconCls : 'btn-cancel',
										scope : this,
										handler : function() {
											this.close();
										}
									}]
						});

			},
			initUI : function() {
				var _url = __ctxPath + '/document/knowledgeTreeDocFolder.do'
				if (this.isOnline) {
					_url = __ctxPath + '/document/onlineTreeDocFolder.do';
				}
				this.treePanel = new Ext.tree.TreePanel({
							id : 'docFolderTreePanel',
							title : '目录树',
							loader : new Ext.tree.TreeLoader({
										url : _url
									}),
							root : new Ext.tree.AsyncTreeNode({
										expanded : true
									}),
							rootVisible : false,
							autoScroll : true,
							listeners : {
								scope : this,
								'dblclick' : this.dblclickNode,
								'click' : this.clickNode
							}
						});

			},
			/**
			 * 单击
			 * 
			 * @param {}
			 *            node
			 */
			clickNode : function(node) {
				this.selectNode = node;
			},
			/**
			 * 双击节点
			 */
			dblclickNode : function(node) {
				if (node.id > 0) {
					this.selectNode = node;
					this.confirm();
				}
			},
			/**
			 * 确定
			 */
			confirm : function() {
				if (Ext.isEmpty(this.selectNode)) {
					Ext.ux.Toast.msg('提示', '请选择目录！');
					return;
				}
				if (this.selectNode.id > 0) {
					if (this.callback) {
						this.callback.call(this.scope, this.selectNode.id,
								this.selectNode.text);
					}
					this.close();
				} else {
					Ext.ux.Toast.msg('提示', '请选择目录！');
				}
			}
		});