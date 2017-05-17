/**
 * 取到某项目的数据字典
 * 
 * @class DicCombo
 * @extends Ext.form.ComboBox
 *          @example
 * 
 * <pre>
 * new DicCombo({
 *  	'xtype':'diccombo',
 *  	'fieldLabel&quot;:'缺省值',
 *  	'name':'reportParam.defaultVal',
 *  	'id':'defaultVal',
 *  	'proTypeId':'2005'//或者 nodeKey='major'
 * 		}	
 * 	);
 * </pre>
 * 
 * 或者
 * 
 * 
 */
DicCombo = Ext.extend(Ext.form.ComboBox, {
			constructor : function(conf) {
				Ext.apply(this, conf);
				DicCombo.superclass.constructor.call(this, {
							// emptyText : this.emptyText
							// ? this.emptyText
							// : '请选择...',
							editable : this.editable ? this.editable : true,
							forceSelection : this.forceSelection
									? this.forceSelection
									: true,
							selectOnFocus : true,
							typeAhead : true,
							mode : 'local',
							triggerAction : 'all',
							store : new Ext.data.ArrayStore({
										autoLoad : this.autoLoad?this.autoLoad:true,
										baseParams : {
											nodeKey : this.nodeKey,
											proTypeId : this.proTypeId
										},
										url : __ctxPath
												+ '/system/loadItemDictionary.do',
										fields : ['itemId', 'itemName']
									}),
							displayField : 'itemName',
							valueField : (this.returnName)?'itemName':'itemId'
						});
			}
		});

Ext.reg('diccombo', DicCombo);