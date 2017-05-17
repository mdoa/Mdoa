/**
 * @description 输入框（按钮、单选按钮、复选框、单行文本、多行文本、下拉框等）
 * @name baidu.editor.execCommand
 * @param {String}
 *            cmdName 输入框
 * @author heyifan
 */
UE.plugins['input'] = function() {
	var me = this;
	// /commands 隐藏域
	// /commandsTitle 隐藏域
	me.commands['hidedomain'] = {
		/**
		 * @param {}
		 *            cmdName 命令名称 ，如：此处为hidedomian
		 * @param {}
		 *            data 编辑界面点击确定时的内容
		 * @param {}
		 *            old 编辑的input对象
		 */
		execCommand : function(cmdName, data, old) {
			if (data) {
				if (old) {
					old.setAttribute("external", getExternal(data));
				} else {
					insertControl(cmdName, data, '<input type="text"/>');
				}
			}else{
				openDialog(cmdName);
			}
		},
		queryCommandState : function() {
			return this.highlight ? -1 : 0;
		}
	};
	// /commands 单行文本框
	// /commandsTitle 单行文本框
	me.commands['textfield'] = {
		/**
		 * @param {}
		 *            cmdName 命令名称 ，如：此处为textinput
		 * @param {}
		 *            data 编辑界面点击确定时的内容
		 * @param {}
		 *            old 编辑的input对象
		 */
		execCommand : function(cmdName, data, old) {
			if (data) {
				if (old) {
					old.setAttribute("external", getExternal(data));
				} else {
					insertControl(cmdName, data, '<input type="text"/>');
				}
			}else{
				openDialog(cmdName);
			}
		},
		queryCommandState : function() {
			return this.highlight ? -1 : 0;
		}
	};
	// /commands 多行文本框
	// /commandsTitle 多行文本框
	me.commands['textarea'] = {
		/**
		 * @param {}
		 *            cmdName 命令名称 ，如：此处为textarea
		 * @param {}
		 *            data 编辑界面点击确定时的内容
		 * @param {}
		 *            old 编辑的input对象
		 */
		execCommand : function(cmdName, data, old) {
			if (data) {
				if (old) {
					old.setAttribute("external", getExternal(data));
				} else {
					insertControl(cmdName, data,
							'<textarea cols="15" rows="3"></textarea>');
				}
			}else{
				openDialog(cmdName);
			}
		},
		queryCommandState : function() {
			return this.highlight ? -1 : 0;
		}
	};
	// /commands 复选框
	// /commandsTitle 复选框
	me.commands['checkbox'] = {
		execCommand : function(cmdName, data, old) {
			if (data) {
				var text = [''];
				for (var i = 0, c; c = data[i++];) {
					if (c.id == 'options') {
						var options = c.val.split(/\n/g);
						for (var j = 0, option; option = options[j++];) {
							text.push('<label><input type="checkbox" value="'
									+ option + '"/>' + option + '</label>');
						}
					}
				}
				if (old) {
					old.setAttribute("external", getExternal(data));
					old.innerHTML = text.join('');
				} else {
					insertControl(cmdName, data, text.join(''));
				}
			}else{
				openDialog(cmdName);
			}
		},
		queryCommandState : function() {
			return this.highlight ? -1 : 0;
		}
	};
	// /commands 单选按钮
	// /commandsTitle 单选按钮
	me.commands['radioinput'] = {
		execCommand : function(cmdName, data, old) {
			if (data) {
				var text = [''];
				for (var i = 0, c; c = data[i++];) {
					if (c.id == 'options') {
						var options = c.val.split(/\n/g);
						for (var j = 0, option; option = options[j++];) {
							text.push('<label><input type="radio" value="'
									+ option + '"/>' + option + '</label>');
						}
					}
				}
				if (old) {
					old.setAttribute("external", getExternal(data));
					old.innerHTML = text.join('');
				} else {
					insertControl(cmdName, data, text.join(''));
				}
			}else{
				openDialog(cmdName);
			}
		},
		queryCommandState : function() {
			return this.highlight ? -1 : 0;
		}
	};
	// 下拉框
	me.commands['selectinput'] = {
		execCommand : function(cmdName, data, old) {
			if (data) {
				var text = ['<select>'];
				for (var i = 0, c; c = data[i++];) {
					if (c.id == 'options') {
						var options = c.val.split(/\n/g);
						for (var j = 0, option; option = options[j++];) {
							text.push('<option value="' + option + '">'
									+ option + '</option>');
						}
					}
				}
				text.push('</select>');
				if (old) {
					old.setAttribute("external", getExternal(data));
					old.innerHTML = text.join('');
				} else {
					insertControl(cmdName, data, text.join(''));
				}
			}else{
				openDialog(cmdName);
			}
		},
		queryCommandState : function() {
			return this.highlight ? -1 : 0;
		}
	};
	// 数据字典
	me.commands['dictionary'] = {
		execCommand : function(cmdName, data, old) {
			if (data) {
				if (old) {
					old.setAttribute("external", getExternal(data));
				} else {
					var text = '<input type="text" />';
					insertControl(cmdName, data, text);
				}
			}else{
				openDialog(cmdName);
			}
		},
		queryCommandState : function() {
			return this.highlight ? -1 : 0;
		}
	};
	// 人员选择器
	me.commands['userselector'] = {
		execCommand : function(cmdName, data, old) {
			if (data) {
				var text = setText(data, cmdName);
				if (old) {
					old.setAttribute("external", getExternal(data));
					old.innerHTML = text;
				} else {
					insertControl(cmdName, data, text);
				}
			}else{
				openDialog(cmdName);
			}
		},
		queryCommandState : function() {
			return this.highlight ? -1 : 0;
		}
	};
	// 角色选择器
	me.commands['roleselector'] = {
		execCommand : function(cmdName, data, old) {
			if (data) {
				var text = setText(data, cmdName);
				if (old) {
					old.setAttribute("external", getExternal(data));
					old.innerHTML = text;
				} else {
					insertControl(cmdName, data, text);
				}
			}else{
				openDialog(cmdName);
			}
		},
		queryCommandState : function() {
			return this.highlight ? -1 : 0;
		}
	};
	// 岗位选择器
	me.commands['positionselector'] = {
		execCommand : function(cmdName, data, old) {
			if (data) {
				var text = setText(data, cmdName);
				if (old) {
					old.setAttribute("external", getExternal(data));
					old.innerHTML = text;
				} else {
					insertControl(cmdName, data, text);
				}
			}else{
				openDialog(cmdName);
			}
		},
		queryCommandState : function() {
			return this.highlight ? -1 : 0;
		}
	};

	// 部门选择器
	me.commands['depselector'] = {
		execCommand : function(cmdName, data, old) {
			if (data) {
				var text = setText(data, cmdName);
				if (old) {
					old.setAttribute("external", getExternal(data));
					old.innerHTML = text;
				} else {
					insertControl(cmdName, data, text);
				}
			}else{
				openDialog(cmdName);
			}
		},
		queryCommandState : function() {
			return this.highlight ? -1 : 0;
		}
	};
	// 附件上传工具
	me.commands['attachement'] = {
		execCommand : function(cmdName, data, old) {
			if (data) {
				var text = setText(data, cmdName);
				if (old) {
					old.setAttribute("external", getExternal(data));
					old.innerHTML = text;
				} else {
					insertControl(cmdName, data, text);
				}
			}else{
				openDialog(cmdName);
			}
		},
		queryCommandState : function() {
			return this.highlight ? -1 : 0;
		}
	};
	// 日期选择器
	me.commands['datepicker'] = {
		execCommand : function(cmdName, data, old) {
			if (data) {
				if (old) {
					old.setAttribute("external", getExternal(data));
				} else {
					var text = '<input type="text" />';
					insertControl(cmdName, data, text);
				}
			}else{
				openDialog(cmdName);
			}
		},
		queryCommandState : function() {
			return this.highlight ? -1 : 0;
		}
	};
	// office控件
	me.commands['officecontrol'] = {
		execCommand : function(cmdName, data, old) {
			if (data) {
				if (old) {
					old.setAttribute("external", getExternal(data));
				} else {
					insertControl(cmdName, data, '<input type="text" />');
				}
			}else{
				openDialog(cmdName);
			}
		},
		queryCommandState : function() {
			return this.highlight ? -1 : 0;
		}
	};
	// CK编辑器
	me.commands['ckeditor'] = {
		execCommand : function(cmdName, data, old) {
			if (data) {
				if (old) {
					old.setAttribute("external", getExternal(data));
				} else {
					insertControl(cmdName, data, '<input type="text" />');
				}
			}else{
				openDialog(cmdName);
			}
		},
		queryCommandState : function() {
			return this.highlight ? -1 : 0;
		}
	};
	//明细表
	me.commands['subtable'] = {
		execCommand : function(cmdName, data, old) {
			if (data) {
				var num=0,tablename='',tablekey='',model=0;
					for(var i=0,c;c=data[i++];){
						if(c.id=="tablerows"){
							num=c.val;
						}else if(c.id=='tablename'){
							tablename=c.val;
						}else if(c.id=='tablekey'){
							tablekey=c.val;
						}else if(c.val==1){
							if(c.id=='inlinemodel')
								model=0;
							else if(c.id=='blockmodel')
								model=1;
							else if(c.id=='windowmodel')
								model=2;
						}							
					}
					var external= getExternal(data);
					var html=['<div tablename="'+tablename+'" tablekey="'+tablekey+'" type="subtable" name="editable-input" class="'+cmdName+'" external="'+external+'">'];
					html.push(getSubtableContent(num,model));
					html.push('</div>');
					//对已有进行修改
					if(old){
						var externalStr = old.getAttribute("external").replace(/\&quot\;/g, '\"').replace(/\&\#39\;/g, '\'');						
						var externalObj=eval("("+externalStr+")");
						if(externalObj){
							var tablerows=externalObj.tablerows,
								oldModel;
							if(externalObj.inlinemodel==1){
								oldModel=0;
							}else if(externalObj.blockmodel==1){
								oldModel=1;
							}else if(externalObj.windowmodel==1){
								oldModel=2;
							}
							
							if(tablerows==num&&oldModel==model){//不修改明细表模式和列数时，不会清空明细表中的字段
								old.setAttribute("external",external);
								old.setAttribute("tablename",tablename);
								old.setAttribute("tablekey",tablekey);
							}else
								old.outerHTML = html.join('');
						}else
							old.outerHTML = html.join('');
				//新添加
				}else{
					me.execCommand('insertHtml',html.join(''));
				}
			}else{
				openDialog(cmdName);
			}
		},
		queryCommandState : function() {
			return this.highlight ? -1 : 0;
		}
	};
	//获取对应模式子表内容
	function getSubtableContent(num,modelIndex){
		var text=[''];
		switch(modelIndex){
			case 0:
				//行模式
				text.push('<table class="listTable" border="0" cellpadding="2" cellspacing="0"><tbody>');
				text.push('<tr class="toolBar"><td colspan="'+num+'"><a class="link add" href="javascript:;">添加</a></td></tr>');
				text.push('<tr class="headRow">');
				for(var i=0;i<num;i++){
					text.push('<th>列'+(i+1)+'</th>');
				}
				text.push('</tr><tr class="listRow" formtype="edit">');
				for(var i=0;i<num;i++){
					text.push('<td></td>');
				}
				text.push('</tr></table>');
				break;
			case 1:
				//块模式
				text.push('<div><a class="link add">添加</a></div><div formtype="edit"><table class="blocktable"><tr>');
				for(var i=0;i<num;i++){
					text.push('<th style="width:'+(100*0.3)/num+'%;">列'+(i+1)+'</th><td style="width:'+(100*0.7)/num+'%;"></td>');
				}
				text.push('</tr></table></div>');
				break;
			case 2:
				//窗口模式
				text.push('<table class="listTable" border="0" cellpadding="2" cellspacing="0"><tbody>');
				text.push('<tr class="toolBar"><td colspan="'+num+'"><a class="link add" href="javascript:;">添加</a></td></tr>');
				text.push('<tr class="headRow">');
				for(var i=0;i<num;i++){
					text.push('<th>列'+(i+1)+'</th>');
				}
				text.push('</tr><tr class="listRow" formtype="form">');
				for(var i=0;i<num;i++){
					text.push('<td></td>');
				}
				text.push('</tr></table><div formtype="window"><table class="window-table">');
				for(var i=0;i<num;i++){
					text.push('<tr><th>列'+(i+1)+'</th><td></td></tr>');
				}
				text.push('</table></div>');
				break;
		}
		return text.join('');		
	};
	
	/**
	 * 设置text
	 * 
	 * @param data
	 *            数据 
	 * @param cmdName
	 *            命令名称
	 */
	var setText = function(data, cmdName) {
		var content = "选择";
		for (var i = 0, c; c = data[i++];) {
			if (c.id == "buttoncontent") {
				content = c.val;
				break;
			}
		}
		return '<input type="text" /><button type="button"  class="btn-text btn-'
				+ cmdName + '">' + content + '</button>';
	};
	/**
	 * 打开窗口
	 * @param cmdName
	 *            命令名称
	 */
	var openDialog = function(cmdName){	
		me.curInputType = cmdName;
		var title = me.options.labelMap[cmdName]|| me.getLang("labelMap." + cmdName) || '';
        var className= "edui-for-" + cmdName;
        var dialogUI = me.ui._dialogs[cmdName+'dialog'];
		if(!dialogUI){
			iframeUrl ="~/dialogs/input/"+cmdName+".html";
			var dialog = new baidu.editor.ui.Dialog( utils.extend({
               iframeUrl: me.ui.mapUrl(iframeUrl),
               editor: me,
               className: 'edui-for-'+cmdName+'dialog',
               title: title
           },{
               buttons: [{
                   className: 'edui-okbutton',
                   label: me.getLang("ok"),
                   onclick: function (){
                       dialog.close(true);
                   }
               }, {
                   className: 'edui-cancelbutton',
                   label: me.getLang("cancel"),
                   onclick: function (){
                       dialog.close(false);
                   }
               }]
           }));
			dialogUI = dialog;
			dialog.render();			
		}				
		dialogUI.title = title;
		dialogUI.className = className;
		dialogUI.clearContent();
		dialogUI.open();
	};
	/**
	 * 添加标签
	 * 
	 * @param cmdName
	 *            命令名称
	 * @param data
	 *            编辑界面提交的数据
	 * @param text
	 *            span标签所包含的内容
	 */
	var insertControl = function(cmdName, data, text) {
		var html = ['<span name="editable-input" style="display:inline-block;padding:2px;" class="'
				+ cmdName + '" external="'];
		html.push(getExternal(data));
		html.push('">');
		html.push(text);
		html.push('</span>');
		editor.execCommand('insertHtml', html.join(''));
	};
	/**
	 * 构建external json字符
	 * 
	 * @param {}
	 *            d
	 * @return {}
	 */
	var getExternal = function(d) {
		var external = {};
		for (var i = 0, c; c = d[i++];) {
			if (c.prenode) {
				if (typeof external[c.prenode] == 'undefined')
					external[c.prenode] = {};
				(external[c.prenode])[c.id] = c.val;
			} else {
				external[c.id] = c.val;
			}
		}
		external = utils.json2str(external);
		return utils.htmlEncode(external);
	};
};