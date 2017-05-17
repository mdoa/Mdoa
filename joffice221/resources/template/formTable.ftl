/*css*/
<#assign formTable='border-collapse :collapse;width:100%;text-align:center;font-size: 12px;'>
<#assign formHead='border:1px solid #366092;text-align: center;font-size: 16px;font-weight: bold;
	background-color: #366092;height: 32px;color: #fff;'>
<#assign formTitle='background-color:#EDF6FC;text-align: right;text-align: center;height: 23px;'>
<#assign formInput='padding:2px;background-color:white;text-align: left;'>

<#macro sign elm ctcls>
<#if elm.tag='label' || elm.tag='strong' || elm.tag='em' 
|| elm.tag='u' || elm.tag='sub' || elm.tag='sup'>
	<@label elm=elm ctcls=ctcls />

<#elseif elm.class?exists && elm.class='textfield'>
	<#if elm.type='varchar'>
		<@textfield elm=elm />
	<#elseif elm.type='number'>
		<@numberfield elm=elm />
	</#if>

<#elseif elm.class?exists && elm.class='textarea'>
	<@textarea elm=elm />

<#elseif elm.class?exists && elm.class='dictionary'>
	<@diccombo elm=elm />

<#elseif elm.class?exists && elm.class='userselector'>
	<@userdialog elm=elm />

<#elseif elm.class?exists && elm.class='depselector'>
	<@depdialog elm=elm />

<#elseif elm.class?exists && elm.class='positionselector'>
	<@posdialog elm=elm />
	
<#elseif elm.class?exists && elm.class='roleselector'>
	<@roledialog elm=elm />

<#elseif elm.class?exists && elm.class='selectinput'>
	<@select elm=elm />

<#elseif elm.class?exists && elm.class='checkbox'>
	<@checkbox elm=elm />

<#elseif elm.class?exists && elm.class='radioinput'>
	<@radio elm=elm />

<#elseif elm.class?exists && elm.class='datepicker'>
	<@datefield elm=elm />

<#elseif elm.class?exists && elm.class='ckeditor'>
	<@ckeditor elm=elm />

<#elseif elm.class?exists && elm.class='officecontrol'>
	<@officeeditor elm=elm />

<#elseif elm.class?exists && elm.class='attachement'>
	<@fileattach elm=elm />

<#elseif elm.tag='table' && elm.class='subtable'>
	<@formDetailGrid elm=elm />

<#elseif elm.tag='br'>
	<@br elm=elm />
	
<#elseif elm.tag='p'>
	<@p elm=elm />
	
<#elseif elm.tag='ol'>
	<@ol elm=elm />
	
<#elseif elm.tag='ul'>
	<@ul elm=elm />
	
<#elseif elm.tag='li'>
	<@li elm=elm />
	
</#if>

</#macro>

<#macro label elm ctcls>
{
	<#assign style=''>
	<#assign text=''>
	xtype:'label',
	<@font elm=elm />
	<#if ctcls='formHead'>
		style:'${formHead}${style}',
	<#elseif ctcls='formTitle'>
		style:'${formTitle}<#if style!=''>${style}</#if>',
	<#elseif ctcls='formInput'>
		style:'${formInput}<#if style!=''>${style}</#if>',
	<#else>
		style:'<#if style!=''>${style}</#if>',
		ctCls:'${ctcls}',
	</#if>
	text:'${elm.text}'
}
</#macro>

<#macro font elm>
<#if elm.tag != 'label'>
	<#if elm.tag='strong'>
		<#assign style=style+'font-weight:bold;'>

	<#elseif elm.tag='em'>
		<#assign style=style+'font-style:italic;'>
		
	<#elseif elm.tag='u'>
		<#assign style=style+'text-decoration:underline;'>
		
	<#elseif elm.tag='sub'>
		<#assign style=style+'vertical-align:sub;'>
		
	<#elseif elm.tag='sup'>
		<#assign style=style+'vertical-align:super;'>
		
	</#if>
	<@font elm=elm.chd0 />
<#else>
	<#if (elm.style!'0')!='0'>
		<#assign style=style+elm.style>
	</#if>
	<#if (elm.text!'0')!='0'><#assign text=elm.text></#if>
</#if>
</#macro>

<#macro textfield elm>
{
	xtype:'textfield',
	name:'${elm.name}',
	allowBlank:<#if elm.isRequired='1'>true<#else>false</#if>
}
</#macro>

<#macro numberfield elm>

	<#assign de=2>
	<#assign minValue=0.01>
	
	<#if elm.format?exists>
		<#assign format=elm.format>
		<#assign de=format?last_index_of('.',0)>
		<#if de!=-1&&de<format?length>
			<#assign de=format?length-de-1>
		<#elseif de=-1>
			<#assign de=0>
			<#assign minValue=0>
		</#if>
	</#if>

{
	xtype:'numberfield',
	name:'${elm.name}',
	decimalPrecision:${de},
	minValue:${minValue},
	maxValue:9999999999999.999999,
	allowBlank:<#if elm.isRequired='1'>true<#else>false</#if>
}
</#macro>

<#macro textarea elm>
{
	xtype:'textarea',
	name:'${elm.name}',
	allowBlank:<#if elm.isRequired='1'>true<#else>false</#if>,
	width:<#if elm.width?exists>${elm.length}<#else>100</#if>
}
</#macro>

<#macro diccombo elm>
{
	xtype:'diccombo',
	name:'${elm.name}',
	allowBlank:<#if elm.isRequired='1'>true<#else>false</#if>,
	nodeKey:'${elm.dictType}'
}
</#macro>

<#macro userdialog elm>
{
	xtype:'userdialog',
	name:'${elm.name}',
	width:${elm.length},
	allowBlank:<#if elm.isRequired='1'>true<#else>false</#if>,
	isSingle:${elm.isSingle}
}
</#macro>

<#macro depdialog elm>
{
	xtype:'depdialog',
	name:'${elm.name}',
	width:${elm.length},
	allowBlank:<#if elm.isRequired='1'>true<#else>false</#if>,
	isSingle:${elm.isSingle}
}
</#macro>

<#macro posdialog elm>
{
	xtype:'posdialog',
	name:'${elm.name}',
	width:${elm.length},
	allowBlank:<#if elm.isRequired='1'>true<#else>false</#if>,
	isSingle:${elm.isSingle}
}
</#macro>

<#macro roledialog elm>
{
	xtype:'roledialog',
	name:'${elm.name}',
	width:${elm.length},
	allowBlank:<#if elm.isRequired='1'>true<#else>false</#if>,
	isSingle:${elm.isSingle}
}
</#macro>

<#macro select elm>

<#if elm.isList?exists && elm.isList='1' >
{
	xtype:'multiselect',
	name:'${elm.name}',
	allowBlank:<#if elm.isRequired='1'>true<#else>false</#if>,
	<#if elm.size?exists>rowSize:${elm.size},</#if>
	store:[
		<#assign selidx=0>
		<#assign options = elm.options?split('#newline#')>
		<#list options as option>
			<#if selidx!=0>,</#if>
			['${option}','${option}']
			<#assign selidx=selidx+1>
		</#list>
	]
}
<#else>
{
	xtype:'combo',
	name:'${elm.name}',
	typeAhead:true,
	triggerAction:'all',
	lazyRender:true,
	mode:'local',
	editable:false,
	allowBlank:<#if elm.isRequired='1'>true<#else>false</#if>,
	store:new Ext.data.ArrayStore({
		id:0,
		fields:[
			'value',
			'text'
		],
		data:
		[
			<#assign selidx=0>
			<#assign options = elm.options?split('#newline#')>
			<#list options as option>
				<#if selidx!=0>,</#if>
				['${option}','${option}']
				<#assign selidx=selidx+1>
			</#list>
		]
	}),
	valueField:'value',
	displayField:'text',
	listeners:{'afterrender':function(obj){
		var store = obj.store;
		if(store.getCount()>0){
			obj.setValue(store.getAt(0).data.value);
		}
	}}
}
</#if>

</#macro>

<#macro checkbox elm>
{
	xtype:'checkbox',
	name:'${elm.name}',
	boxLabel:'${elm.boxLabel}',
	checked:<#if elm.checked?exists && elm.checked='checked'>true<#else>false</#if>
}
</#macro>

<#macro radio elm>
{
	xtype:'radio',
	name:'${elm.name}',
	boxLabel:'${elm.boxLabel}',
	checked:<#if elm.checked?exists && elm.checked='checked'>true<#else>false</#if>
}
</#macro>

<#macro datefield elm>
{
	xtype:'datetimefield',
	name:'${elm.name}',
	editable:false,
	allowBlank:<#if elm.isRequired='1'>true<#else>false</#if>,
	format:<#if elm.format='yyyy-MM-dd'>
			'Y-m-d'
			<#elseif elm.format='yyyy-MM-dd HH:mm:ss'>
			'Y-m-d H:i:s'
			<#else>
			'Y-m-d H:i'
			</#if>,
	value:<#if elm.displayDate?exists && elm.displayDate='1'>new Date()<#else>''</#if>,
	listeners:{'afterrender':function(obj){
		obj.setWidth(obj.getWidth()*<#if elm.format='yyyy-MM-dd'>1<#elseif elm.format='yyyy-MM-dd HH:mm:ss'>1.5<#else>1.3</#if>)
	}}
}
</#macro>

<#macro fileattach elm>
{
	xtype:'attachpanel',
	name:'${elm.name}'
}
</#macro>

<#macro ckeditor elm>
{
	xtype:'ckdesigner',
	name:'${elm.name}',
	allowBlank:<#if elm.isRequired='1'>true<#else>false</#if>
}
</#macro>

<#macro officeeditor elm>
new NtkOfficePanel({
	name:'${elm.name}',
	showToolbar:true,
	doctype:'doc',
	unshowMenuBar:false
}).panel
</#macro>

<#macro br elm>
{
	xtype:'panel',
	border:false,
	html:'<br>'
}
</#macro>

<#macro formDetailGrid elm>
{
	xtype:'formDetailGrid',
	name:'${elm.tablekey}',
	gridName:'${elm.tablekey}',
	headers:[<@table tbMap=elm flag='header' gridName='${elm.tablekey}' />],
	fields:[<@table tbMap=elm flag='fields' gridName='${elm.tablekey}' />],
	columns:[<@table tbMap=elm flag='columns' gridName='${elm.tablekey}' />]
}
</#macro>

<#macro formDetailPanel elm>
{
	xtype:'formDetailPanel',
	name:'${elm.tablekey}',
	item:[<@table tbMap=elm flag='table' gridName='' />],
	tablerows:${elm.tablerows}
}
</#macro>

<#macro table tbMap flag gridName>
	<#assign tdidx=0>
	<#assign rowidx=0>
	<#list tbMap?keys as tbKey>
		<#if (tbKey?index_of("chd")>=0)>
			
			<#assign tableMap=tbMap[tbKey] >
			
			<#assign isdetail='false'>
			<#assign isgrid='false'>
			<#if tbMap.class?exists>
				
				<#if tbMap.class='subtable'>
					<#assign isdetail='true'>
					<#if tbMap.inlinemodel?exists && tbMap.inlinemodel='1'>
						<#assign isgrid='true'>
					</#if>
					<#if tbMap.blockmodel?exists && tbMap.blockmodel='1'>
						<#assign isgrid='false'>
					</#if>
				</#if>
			</#if>
			
			<@tableSign elm=tableMap isdetail=isdetail flag=flag isgrid=isgrid gridName=gridName />
		</#if>
	</#list>
</#macro>

<#macro tablePanel tbMap>
{
	layout:'table',
	border:false,
	layoutConfig: {
		columns:${tbMap.tablerows},
		tableAttrs:{
			bordercolor:'#99BBFF',
			border:1
		}
	},
	items:[<@table tbMap=tbMap flag='table' gridName='commontable' />]
}
</#macro>

<#macro tableSign elm isdetail flag isgrid gridName>
	<#if elm.tag='tbody'>
		<@tbody tbodyMap=elm flag=flag isdetail=isdetail isgrid=isgrid gridName=gridName />
	</#if>
	<#if elm.tag='thead'>
		<@thead theadMap=elm />
	</#if>
</#macro>

<#macro thead theadMap>
	<#list theadMap?keys as theadKey>
	
		<#if (theadKey?index_of("chd")>=0)>
			<#assign trMap=theadMap[theadKey]>
			
			<#assign colidx=0>
			<#list trMap?keys as trKey>
				<#if (trKey?index_of("chd")>=0)>
					<#assign thMap=trMap[trKey]>
					<#if tdidx!=0>,</#if>
					
					<@th thMap=thMap />
					
					<#assign tdidx=tdidx+1>
					<#assign colidx=colidx+1>
				</#if>
			</#list>
			
		</#if>
		
	</#list>
</#macro>

<#macro th thMap>
{
	border:false,
	bodyStyle:'padding:5px;',
	<#if thMap.rowspan?exists>rowspan:${thMap.rowspan},</#if>
	<#if thMap.colspan?exists>colspan:${thMap.colspan},</#if>
	items:[
		<#assign elidx=0>
		<#list thMap?keys as elkey>
			<#if (elkey?index_of("chd")>=0)>
				<#if elidx!=0>,</#if>
				<@sign elm=thMap[elkey]  ctcls='<#if thMap.class?exists>${thMap.class}</#if>' />
				<#assign elidx=elidx+1>
			</#if>
		</#list>
	]
}
</#macro>

<#macro tbody tbodyMap flag isdetail isgrid gridName>

	<#list tbodyMap?keys as tbodyKey>
	
		<#if (tbodyKey?index_of("chd")>=0)>
			<#assign trMap=tbodyMap[tbodyKey]>
			
			<#assign headeridx=0>
			<#assign colidx=0>
			<#list trMap?keys as trKey>
				<#if (trKey?index_of("chd")>=0)>
					<#assign tdMap=trMap[trKey]>
					
					<#if isdetail='true'>
						
						<#if isgrid='true'>
							
							<#if rowidx=1>
								<#if flag='header'>
									<@headers elm=tdMap headeridx=headeridx />
								</#if>
							</#if>
							
							<#if rowidx=2>
								<#if flag='fields'>
									<@fields elm=tdMap fieidx=colidx />
								</#if>
								
								<#if flag='columns'>
									<@columns elm=tdMap colidx=colidx gridName=gridName />
								</#if>
							</#if>
							
						<#else>
							
							<#if rowidx!=0>
							<#if tdidx!=0>,</#if>
							<@td tdMap=tdMap />
							<#assign tdidx=tdidx+1>
							</#if>
						</#if>
					
					<#else>
						
						<#if tdidx!=0>,</#if>
						<@td tdMap=tdMap />
						<#assign tdidx=tdidx+1>
					</#if>
					
					<#assign headeridx=headeridx+1>
					<#assign colidx=colidx+1>

				</#if>
			</#list>
			
			<#assign rowidx=rowidx+1>
		</#if>
		
	</#list>
</#macro>

<#macro td tdMap>
{
	border:false,
	layout:'table',
	<#if tdMap.class?exists>
		<#if tdMap.class='formHead'>
			bodyStyle:'${formHead}',
		<#else>
			ctCls:'${tdMap.class}',
		</#if>
	</#if>
	<#if tdMap.rowspan?exists>rowspan:${tdMap.rowspan},</#if>
	<#if tdMap.colspan?exists>colspan:${tdMap.colspan},</#if>
	items:[
		<#assign elidx=0>
		<#list tdMap?keys as elkey>
			<#if (elkey?index_of("chd")>=0)>
				<#if elidx!=0>,</#if>
				<#assign ctcls=''>
				<#if tdMap.class?exists><#assign ctcls=tdMap.class ></#if>
				<@sign elm=tdMap[elkey] ctcls=ctcls />
				<#assign elidx=elidx+1>
			</#if>
		</#list>
	]
}
</#macro>

<#macro headers elm headeridx>
	<#list elm?keys as headerkey>
		<#if (headerkey?index_of("chd")>=0)>
			<#assign header=elm[headerkey]>
			<#if headeridx!=0>,</#if>
			'${header.text}'
		</#if>
	</#list>
</#macro>

<#macro fields elm fieidx>
	<#list elm?keys as fiekey>
		<#if (fiekey?index_of("chd")>=0)>
			<#assign field=elm[fiekey]>
			<#if field.name?exists>
				<#if fieidx!=0>,</#if>
				'${field.name}'
			</#if>
		</#if>
	</#list>
</#macro>

<#macro columns elm colidx gridName>
	<#list elm?keys as colkey>
		<#if (colkey?index_of("chd")>=0)>
			<#assign column=elm[colkey]>
			<#if column.name?exists>
				<#if colidx!=0>,</#if>
				{
					header:'',
					dataIndex:'${column.name}',
					editor:<@detialTableElmSign elm=column gridName=gridName />
					<#if column.class='datepicker' >
						,renderer: Ext.util.Format.dateRenderer(
							<#if column.format='yyyy-MM-dd'>
							'Y-m-d'
							<#elseif column.format='yyyy-MM-dd HH:mm:ss'>
							'Y-m-d H:i:s'
							<#else>
							'Y-m-d H:i'
							</#if>
						)
					</#if>
				}
			</#if>
		</#if>
	</#list>
</#macro>

<#macro detialTableElmSign elm gridName>

<#if elm.class?exists && elm.class='textfield'>
	<#if elm.type='varchar'>
		<@textfield elm=elm />
	<#elseif elm.type='number'>
		<@numberfield elm=elm />
	</#if>

<#elseif elm.class?exists && elm.class='dictionary'>
	<@diccombo elm=elm />

<#elseif elm.class?exists && elm.class='userselector'>
	<@duserdialog elm=elm gridName=gridName />

<#elseif elm.class?exists && elm.class='depselector'>
	<@ddepdialog elm=elm gridName=gridName />

<#elseif elm.class?exists && elm.class='positionselector'>
	<@dposdialog elm=elm gridName=gridName />

<#elseif elm.class?exists && elm.class='roleselector'>
	<@droledialog elm=elm gridName=gridName />

<#elseif elm.class?exists && elm.class='selectinput'>
	<@select elm=elm />

<#elseif elm.class?exists && elm.class='datepicker'>
	<@datefield elm=elm />

<#elseif elm.class?exists && elm.class='fileattach'>
	<@dfileattach elm=elm gridName=gridName />

</#if>

</#macro>

<#macro dfileattach elm gridName>
new Ext.form.TriggerField({
	onTriggerClick: function(e) {
		FileAttachDetail.dupload('fdg-${gridName}','${elm.name}');
	}
})
</#macro>

<#macro duserdialog elm gridName>
	new Ext.form.TriggerField({
		triggerClass: 'x-form-browse-trigger',
		gridName:'${gridName}',
		isSingle:${elm.isSingle},
		dataIndexName:'${elm.name}',
		editable:false,
		value:<#if column.iscurrent?exists><#if column.iscurrent='1'>App.auth.fullName<#else>''</#if><#else>''</#if>,
		allowBlank:<#if elm.isRequired='1'>true<#else>false</#if>,
		onTriggerClick: function(e) {
			var grid=Ext.getCmp('fdg-'+this.gridName);
			var modifyName=this.dataIndexName;
			UserSelector.getView(function(id,name){
				var record = grid.getSelectionModel().getSelected();
				record.set(modifyName, name);
            },this.isSingle).show();
		}
   })
</#macro>

<#macro ddepdialog elm gridName>
	new Ext.form.TriggerField({
		triggerClass: 'x-form-browse-trigger',
		gridName:'${gridName}',
		isSingle:${elm.isSingle},
		dataIndexName:'${elm.name}',
		editable:false,
		value:<#if column.iscurrent?exists><#if column.iscurrent='1'>App.auth.fullName<#else>''</#if><#else>''</#if>,
		allowBlank:<#if elm.isRequired='1'>true<#else>false</#if>,
		onTriggerClick: function(e) {
			var grid=Ext.getCmp('fdg-'+this.gridName);
			var modifyName=this.dataIndexName;
			DepSelector.getView(function(id, name){
				var record = grid.getSelectionModel().getSelected();
				record.set(modifyName, name);
			},this.isSingle).show();
		}
   })
</#macro>

<#macro dposselector elm gridName>
	new Ext.form.TriggerField({
		triggerClass: 'x-form-browse-trigger',
		gridName:'${gridName}',
		isSingle:${elm.isSingle},
		dataIndexName:'${elm.name}',
		editable:false,
		value:<#if column.iscurrent?exists><#if column.iscurrent='1'>App.auth.fullName<#else>''</#if><#else>''</#if>,
		allowBlank:<#if elm.isRequired='1'>true<#else>false</#if>,
		onTriggerClick: function(e) {
			var grid=Ext.getCmp('fdg-'+this.gridName);
			var modifyName=this.dataIndexName;
	        new PositionDialog({
				scope:this,
				single:this.isSingle,
				callback:function(ids,names){
					var record = grid.getSelectionModel().getSelected();
					record.set(modifyName, names);
				}
			}).show();
		}
   })
</#macro>

<#macro droledialog elm gridName>
	new Ext.form.TriggerField({
		triggerClass: 'x-form-browse-trigger',
		gridName:'${gridName}',
		isSingle:${elm.isSingle},
		dataIndexName:'${elm.name}',
		editable:false,
		value:<#if column.iscurrent?exists><#if column.iscurrent='1'>App.auth.fullName<#else>''</#if><#else>''</#if>,
		allowBlank:<#if elm.isRequired='1'>true<#else>false</#if>,
		onTriggerClick: function(e) {
			var grid=Ext.getCmp('fdg-'+this.gridName);
			var modifyName=this.dataIndexName;
	        new RoleDialog({
				scope:this,
				single:this.isSingle,
				callback:function(ids,names){
					var record = grid.getSelectionModel().getSelected();
					record.set(modifyName, names);
				}
			}).show();
		}
   })
</#macro>

<#macro ol elm>
<#assign elmMap=elm>
{
	border:false,
	items:[
		<#assign elidx=0>
		<#list elm?keys as elkey>
			<#if (elkey?index_of("chd")>=0)>
				<#if elidx!=0>,</#if>
				<#assign ulelm=elm[elkey]>
				<@li elm=ulelm />
				<#assign elidx=elidx+1>
			</#if>
		</#list>
	]
}
</#macro>

<#macro ul elm>
{
	border:false,
	items:[
		<#assign elidx=0>
		<#list elm?keys as elkey>
			<#if (elkey?index_of("chd")>=0)>
				<#if elidx!=0>,</#if>
				<#assign ulelm=elm[elkey]>
				<@li elm=ulelm />
				<#assign elidx=elidx+1>
			</#if>
		</#list>
	]
}
</#macro>

<#macro li elm>
{
	border:false,
	items:[
		<#assign elidx=0>
		<#list elm?keys as elkey>
			<#if (elkey?index_of("chd")>=0)>
				<#if elidx!=0>,</#if>
				<#assign lielm=elm[elkey]>
				<@sign elm=lielm ctcls='' />
				<#assign elidx=elidx+1>
			</#if>
		</#list>
	]
}
</#macro>

<#macro p elm>
{
	border:false,
	items:[
		<#assign elidx=0>
		<#list elm?keys as elkey>
			<#if (elkey?index_of("chd")>=0)>
				<#if elidx!=0>,</#if>
				<#assign pelm=elm[elkey]>
				<@sign elm=pelm ctcls='' />
				<#assign elidx=elidx+1>
			</#if>
		</#list>
	]
}
</#macro>

Ext.ns('FormTable');
FormTable = Ext.extend(Ext.Panel, {
	constructor : function(_cfg) {
		Ext.applyIf(this, _cfg);
		this.initUIComponents();
		FormTable.superclass.constructor.call(this, {
			id: 'FormTable',
			border: false,
			items: [this.form]
		});
	},
	initUIComponents : function() {
		this.form = new Array();
		<#assign idx0=0>
		<#list ft as ctMap>
			<#list ctMap?keys as ctKey>
			
				<#if (ctKey?index_of("p")>=0)>
					this.p${idx0} = new Ext.Panel({
						<#assign idx=0>
						<#assign chdMap=ctMap[ctKey] >
						layout:'table',
						border: false,
						items: [
							<#list chdMap?keys as chdKey>
								<#if (chdKey?index_of("chd")>=0)>
									<#assign elm=chdMap[chdKey] >
									<#if idx!=0>,</#if>
									<@sign elm=elm ctcls='' />
									<#assign idx=idx+1>
								</#if>
							</#list>
						]
					});
					this.form.push(this.p${idx0});
					<#assign idx0=idx0+1>
				</#if>
				
				<#if (ctKey?index_of("table")>=0)>
					
					<#assign tridx=0>
					<#assign chdMap=ctMap[ctKey] >
					
					<#if (chdMap.class!'0')!='0' && chdMap.class='formTable'>
					
						this.table${idx0} = new Ext.Panel({
							layout:'table',
							border:false,
							layoutConfig: {
								columns:${chdMap.tablerows}
							},
							bodyStyle:'${formTable}',
							items:[<@table tbMap=chdMap flag='table' gridName='maintable'+idx0 />]
						});
					<#elseif (chdMap.class!'0')!='0' && chdMap.class='subtable'>
						
						this.table${idx0} = new Ext.Panel({
							border:false,
							items:[
								<#if (chdMap.inlinemodel!'0')!='0' && chdMap.inlinemodel='1'>
									<@formDetailGrid elm=chdMap />
								<#else>
									<@formDetailPanel elm=chdMap />
								</#if>
							]
						});
						
					<#else>
					
						this.table${idx0} = new Ext.Panel({
							layout:'table',
							border:false,
							layoutConfig: {
								columns:${chdMap.tablerows},
								tableAttrs:{
									bordercolor:'#99BBFF',
									border:1
								}
							},
							items:[<@table tbMap=chdMap flag='table' gridName='commontable'+idx0 />]
						});
					</#if>
					
					this.form.push(this.table${idx0});
					
					<#assign idx0=idx0+1>
					
				</#if>
				
			</#list>
		</#list>
	}
});