
/**
 * 公司新闻 by xianggang
 */

Ext.define('mobile.addressBook.AddressBookList', {
	extend : 'mobile.List',
	name : 'addressBookList',
	id : "mobileList",
	constructor : function(config) {
		config = config || {};
		var bookType = config.bookType;
		var url = '';
		if (bookType == "personalAddressBookView") {
			url = __ctxPath + '/htmobile/listAddressBook.do?Q_phoneGroup.isPublic_SN_EQ = "0"&Q_appUser.userId_L_EQ='+config.userId;
		} else if (bookType == "sharedAddressBookView") {
			url = __ctxPath + '/htmobile/sharedAddressBook.do';
		} else if (bookType == "publicAddressBookView") {
			url = __ctxPath + '/htmobile/listAddressBook.do?Q_phoneGroup.isPublic_SN_EQ=1';
		}
		Ext.apply(config, {
			title : config.title,
			buttonText: '删除',
			cls : 'addressBookList',
			fields : [{
						name : 'phoneId',
						type : 'string'
					}, {
						name : "fullname",
						type : "string"
					}, {
						name : "title",
						type : "string"
					}, {
						name : "mobile",
						type : "string"
					}, {
						name : "phone",
						type : "string"
					}, {
						name : "note",
						type : "string"
					},{
						name : "phoneGroup",
						type : "auto"
					}],
			url : url,
			root : 'result',
			searchCol : "Q_fullname_S_LK",
			sorters : 'fullname',
			grouped : true,
			groupedFiled : 'fullname',
			loadingText : '正在加载',
			hideOnMaskTap: false,
			indexBar : true,
			plugins : false,
			itemTpl : new Ext.XTemplate(
				"<span class='itemTpl'>{fullname}<sub>",
					'<tpl switch="title">',
						'<tpl case="先生">',
							'<span class="sub" style="color:#f15a22;">♀</span>',
						'<tpl case="女士">',
							'<span class="sub">♂</span>',
					'</tpl>',
					"</sub>({mobile})</span>"),
			useSelect : true,
			itemsingletap : function(obj, index, target, record) {
				if (!defaultsValues.isTapHold) {
					Ext.Ajax.request({
								url : __ctxPath + '/htmobile/getAddressBook.do?phoneId='+record.get('phoneId'),
								success : function(response) {
									var result = Ext.util.JSON .decode(response.responseText);
									mobileNavi.push(Ext.create('mobile.addressBook.AddressBookDetail',
															{data : result.data,
															 readOnly : true,
															 callback : function() {
															 obj.store.load();
															}
													}));
								}
							});
				}
			},
			doneSuccess : function(ids) {
				Ext.Ajax.request({
							url : __ctxPath+ '/htmobile/delAddressBook.do?phoneId='+ ids,
							success : function(response) {
								var result = Ext.util.JSON.decode(response.responseText);
								if (result.success) {
									Ext.Msg.alert("提示", "删除数据成功");
									Ext.ComponentManager.get("mobileList").getStore().load();
								}
							}

						});

			}
		});
		this.callParent([config]);

	}
	
});
