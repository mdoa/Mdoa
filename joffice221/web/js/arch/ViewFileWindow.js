/**
 * @author
 * @createtime
 * @class ViewFileWindow
 * @extends Ext.Window
 * @description ArchBorrow表单
 * @company 宏天软件
 */
ViewFileWindow = Ext.extend(Ext.Window, {
			// 构造函数
			constructor : function(conf) {
				Ext.applyIf(this, conf);
				// 初始化index
				this.index = this.index == null ? (this.startIndex == null
						? 0
						: this.startIndex):  this.index;
				// 必须先初始化组件
				this.initUIComponents();
				ViewFileWindow.superclass.constructor.call(this, {
							region : 'center',
							layout : 'form',
							frame : true,
							border : true,
							modal : true,
							height : 600,
							width : 550,
							maximizable : true,
							title : '附件预览',
							buttonAlign : 'center',
							buttons : [{
										text : '上一条',
										iconCls : 'btn-top',
										scope : this,
										handler : this.up
									}, {
										text : '下一条',
										iconCls : 'btn-down',
										scope : this,
										handler : this.down
									}],
							items : [{
										xtype : 'iframepanel',
										name : 'ViewFileWindow.iframepanel',
										frame : false,
										border : false,
										style : 'margin:0 auto',
										loadMask : true,
										autoLoad : false,
										listeners : {
											scope : this,
											'afterrender' : this.afterrender
										}
									}, {
										xtype : 'panel',
										name : 'ViewFileWindow.panel',
										height : 30,
										frame : false,
										border : false,
										autoLoad : false
									}],
							listeners : {
								scope : this,
								'afterrender' : this.afterrender
							}
						});
			},// end of the constructor
			// 初始化组件
			initUIComponents : function() {
			},
			/**
			 * 调整上下高度及展示
			 */
			afterrender : function() {
				this.updatePanel(this.index);
			},
			/**
			 *  调整上下高度及展示
			 * @param {} index
			 */
			updatePanel : function(index) {
				// 调整上下高度
				var iframe = this.getCmpByName('ViewFileWindow.iframepanel');
				var panel = this.getCmpByName('ViewFileWindow.panel');
				var wh = this.getInnerHeight();
				var ph = panel.getHeight();
				iframe.setHeight(wh - ph);
				// 过滤预览文件
				if (this.viewConfig && this.viewConfig.length > 0) {
					// iframe
					var typeIndex = this.viewConfig[index].fileName
							.lastIndexOf('.');
					var typeLastdex = this.viewConfig[index].fileName.length;
					var fileType = this.viewConfig[index].fileName.substring(
							(typeIndex + 1), typeLastdex);
					if (this.judeFileType(fileType)) {
						iframe.setSrc(__ctxPath + '/attachFiles/'
								+ this.viewConfig[index].filePath);
					} else {
						iframe.resetFrame();	
					}
					
					// panel
					panel.body.update(
							'<div align="center">'
									+ this.viewConfig[index].fileName
									+ '</div>' + '<div align="center">'
									+ '<a href="' + __ctxPath + '/attachFiles/'
									+ this.viewConfig[index].filePath
									+ '" target="_blank">下载</a>' + '</div>');
				}else{
					panel.body.update("该文件没有预览数据");
				}
			},
			/**
			 * 上一条
			 */
			up : function() {
				this.index = this.index - 1;
				if (this.index >= 0) {
						this.updatePanel(this.index);
				} else {
					this.index = 0;
					Ext.ux.Toast.msg('操作信息', '已是第一张！');
				}
			},

			/**
			 * 
			 */
			down : function() {
				this.index = this.index + 1;
				if (this.index < this.viewConfig.length) {
					this.updatePanel(this.index);
				} else {
					this.index = this.viewConfig.length - 1;
					Ext.ux.Toast.msg('操作信息', '已是最后一张！');
				}
			},
			/**
			 * 判断文件类型
			 * 
			 * @param {}
			 *            fileType
			 * @return {Boolean}
			 */
			judeFileType : function(fileType) {
				if (fileType == 'png' || fileType == 'PNG' || fileType == 'gif'
						|| fileType == 'GIF' || fileType == 'jpg'
						|| fileType == 'JPG' || fileType == 'bmp'
						|| fileType == 'BMP' || fileType == 'xml'
						|| fileType == 'XML' || fileType == 'txt'
						|| fileType == 'TXT' || fileType == 'html'
						|| fileType == 'HTML') {
					return true;

				} else {
					return false;
				}
			}

		});