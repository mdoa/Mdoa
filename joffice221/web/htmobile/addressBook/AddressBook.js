Ext.define('mobile.addressBook.AddressBook', {
	extend : 'Ext.Panel',
	name : 'addressBook',
	constructor : function(config) {
		config = config || {};
		this.username = config.username;
		var selfItem = [];
		var rowPanel = null;
		var bookTypes = [{
					cls : 'personalAddressBookView',
					title : '个人通讯簿',
					view : 'mobile.addressBook.AddressBookList',
					bookType : "personalAddressBookView"
				}, {
					cls : 'sharedAddressBookView',
					title : '共享通讯录',
					view : 'mobile.addressBook.AddressBookList',
					bookType : "sharedAddressBookView"
				}, {
					cls : 'publicAddressBookView',
					title : '公共通讯薄',
					view : 'mobile.addressBook.AddressBookList',
					bookType : "publicAddressBookView"
				}];

		var bookTypesSize = bookTypes.length;
		if (bookTypes.length != 0 && bookTypes.length % 4 != 0) {
			while (bookTypesSize % 4 != 0) {
				bookTypesSize++;
			}
		}

		for (var idx = 0; idx < bookTypesSize; idx++) {

			if (idx == 0 || idx % 4 == 0) {
				rowPanel = Ext.create('Ext.Panel', {
							layout : {
								type : 'hbox',
								align : 'middle'
							}
						});
			}

			var itemPanel = Ext.create('Ext.Panel', {
						layout : {
							type : 'vbox',
							align : 'middle'
						},
						style : {
							'padding-top' : '15px',
							'padding-bottom' : '15px'
						},
						flex : 1,
						height : 100
					});

			if (idx < bookTypes.length) {
				var item = bookTypes[idx];
				itemPanel.add({
					xtype : 'image',
					name : item.view,
					title : item.title,
					width : 50,
					height : 50,
					bookType : item.bookType,
					cls : item.cls,
					scope : this,
					listeners : {
						tap : function() {
							var isPublic=this.config.bookType=='publicAddressBookView';
							var createName=this.config.name;
							var bookType= this.config.bookType;
							var title=this.config.title;
							var userId=this.config.userId;
							mobileNavi.push(Ext.create(createName, {
								bookType : bookType,
								title : title,
								userId:userId,
								bottomToolbar : Ext.create(
										'mobile.ButtomToolBar', {
											leftButtonName : "添加",
											leftHandler : function(btn) {
												mobileNavi.push(Ext.create('mobile.addressBook.AddressBookDetail'
															,{data:'',isPublic:isPublic,readOnly:false}));
											},
											rhidden : true
										})

							}));
						}
					}
				});

				itemPanel.add({
							xtype : 'label',
							style : {
								'text-align' : 'center',
								'font-size' : '9pt'
							},
							html : item.title
						});
			}

			rowPanel.add(itemPanel);

			if (idx == 0 || idx % 4 == 0) {
				selfItem.push(rowPanel);
			}
		}

		Ext.apply(config, {
					title : config.title,
					items : selfItem
				});
		this.callParent([config]);
	}

});
