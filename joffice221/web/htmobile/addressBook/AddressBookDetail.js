
/**
 * 公司新闻 by xianggang
 */

Ext.define('mobile.addressBook.AddressBookDetail', {
	extend : 'Ext.form.Panel',
	name : 'addressBookDetail',
	id : 'addressBookDetail',
	constructor : function(config) {
		config = config || {};
		var data = config.data;
		var isPublicAddressBook = !Ext.isEmpty(config.isPublic)
				&& config.isPublic ? true : false;
		var groupOptions = [];
		var groupOptionValue = 0;
		Ext.define('Options', {
					extend : 'Ext.data.Model',
					config : {
						fields : [{
									name : 'groupId',
									type : 'string'
								}, {
									name : 'groupName',
									type : 'string'
								}]
					}
				});

		var store = Ext.create('Ext.data.Store', {
					autoLoad : true,
					model : "Options",
					proxy : {
						type : 'ajax',
						url : __ctxPath
								+ '/htmobile/listAddressGroup.do?method=1&isPublic='
								+ isPublicAddressBook,
						reader : {
							type : 'json',
							rootProperty : 'data'
						}
					}
				});
		groupOptionValue = !Ext.isEmpty(data.phoneGroup)
				? data.phoneGroup.groupId
				: !Ext.isEmpty(groupOptions) ? groupOptions[0].value : 0;
		var itemses = [{
			xtype : 'fieldset',
			title : '基本资料',
			defaults : {
				readOnly : config.readOnly,
				cls :'addressBookDetail'
			},
			items : [{
						xtype : 'textfield',
						name : 'phoneBook.phoneId',
						label : '联系人编号',
						value : data.phoneId,
						hidden : true
					}, {
						xtype : 'formTextfield',
						name : 'phoneBook.fullname',
						value : data.fullname,
						required : true,
						label : '姓名'
					}, {
						xtype : 'selectfield',
						name : 'phoneBook.title',
						value : data.title,
						required : true,
						label : '称谓',
						options : [{
									text : '先生',
									value : '先生'
								}, {
									text : '女士',
									value : '女士'
								}]
					}, {
						xtype : 'datepickerfield',
						name : 'phoneBook.birthday',
						value : new Date(!Ext.isEmpty(data.birthday)
								? data.birthday
								: defaultsValues.defaultsDate),
						dateFormat : "Y-m-d",
						label : '生日',
						picker: {
                            useTitles: true,
                            dayText: '日',
                            monthText: '月',
                            yearFrom: 1970,
                            yearText: '年',
                            yearTo: 2100,
                            doneButton: '确定',
                            cancelButton: '取消'
                        }
						
					}, {
						xtype : 'selectfield',
						hidden : isPublicAddressBook,
						name : 'phoneBook.isShared',
						value : data.isShared,
						label : '共享',
						options : [{
							text:"是",
							value:1
						},{
							text:"否",
							value:0
						}]
					}, {
						xtype : 'selectfield',
						name : 'phoneBook.phoneGroup.groupId',
						value : groupOptionValue,
						label : '分组',
						store:store,
						displayField: 'groupName',
           		 		valueField: 'groupId'
					}]
		}, {
			xtype : 'fieldset',
			title : '联系方式',
			defaults : {
				readOnly : config.readOnly
			},
			items : [{
						xtype : 'formTextfield',
						name : 'phoneBook.mobile',
						label : '手机',
						value : data.mobile,
						maxLength : 11,
						required : true,
						validate : regexes.phone
					}, {
						xtype : 'formTextfield',
						name : 'phoneBook.phone',
						label : '电话',
						value : data.phone,
						maxLength : 11,
						validate : regexes.mobile
					}, {
						xtype : 'formTextfield',
						name : 'phoneBook.qqNumber',
						value : data.qqNumber,
						label : 'QQ',
						maxLength : 12,
						validate : regexes.qq
					}, {
						xtype : 'formTextfield',
						name : 'phoneBook.msn',
						value : data.msn,
						label : 'MSN',
						placeHolder : "abc@hotmail.com",
						validate : regexes.msn
					}, {
						xtype : 'formTextfield',
						name : 'phoneBook.email',
						value : data.email,
						label : 'Email',
						required : true,
						placeHolder : "abc@163.com",
						validate : regexes.email
					}]
		}];
		itemses.push(Ext.create('mobile.ButtomToolBar', {
					leftButtonName : "编辑",
					leftHandler : function() {
						setFormEdit(this.up("formpanel"), true);
					},
					rightButtonName : "保存",
					rightHandler : function() {
						var formpanel = this.up("formpanel");
						var innerItems = formpanel.getInnerItems();
						var canSubmit = true;
						for (var i = 0; i < innerItems.length; i++) {
							var its = innerItems[i];
							if (its.xtype == 'fieldset') {
								if (!isAllFieldRight(its.items)) {
									canSubmit = false;
								}
							}
						}
						if (canSubmit) {
							formpanel.submit({
										url : __ctxPath
												+ '/htmobile/saveAddressBook.do',
										method : 'POST',
										success : function(form, action,
												response) {
											var obj = Ext.util.JSON
													.decode(response);
											if (obj.success) {
												setFormEdit(formpanel, false);
												mobileNavi.pop().getStore()
														.load();
												Ext.Msg.alert("提示", "保存成功");
											}
										}

									})
						}

					}

				}));
		if (!Ext.isEmpty(data.fullname)) {
			var companyName=!Ext.isEmpty(data.companyName)?data.companyName:'';
			config.title = "<span  class='title'>" + data.fullname
					+ "</span><sup class='sup'>" + companyName + "</sup>";
		} else {
			config.title = "新建联系人";
		}
		Ext.apply(config, {
					title : config.title,
					scrollable : {
						direction : 'vertical',
						directionLock : true
					},
					items : itemses
				});
		this.callParent([config]);
	}

});
