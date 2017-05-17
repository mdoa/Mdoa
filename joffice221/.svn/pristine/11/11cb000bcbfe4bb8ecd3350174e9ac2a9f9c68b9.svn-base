/**
 * 下拉树
 * @author zxh
 * @class ComboTree
 * @extends Ext.form.TriggerField
 * @use
 *	{
 *		fieldLabel : '图书类别',
 *		hiddenId：'bookTypeId',//隐藏域的id
 *		hiddenName: 'book.bookType.typeId', //如果不需要赋值就写 hiddenName 就行
 *		name : 'book.bookType.typeName',
 *		xtype:'combotree',	//自定义类型 combotree
 *		allowBlank : false,// 不允许为空
 *		url: __ctxPath + '/admin/treeBookType.do?method=1'
 *	}
 *	<br>
 *	
 */
HT.ComboTree = Ext.extend(Ext.form.TriggerField, {
	triggerClass : 'x-form-arrow-trigger',
	shadow : 'sides',
	lazyInit : true,
	currNode:null,		//当前选中的节点
	
	/**
	 * 覆盖initComponent
	 */
	initComponent : function() {
		HT.ComboTree.superclass.initComponent.call(this);
		this.addEvents(
		     /**
             * @event expand
             * 当下拉列表展开的时候触发。
             * Fires when the dropdown list is expanded
             * @param {HT.ComboTree} combo 组合框本身。This combo box
             */
            'expand',
             /**
             * @event collapse
             * 当下拉列表收起的时候触发。
             * Fires when the dropdown list is collapsed
             * @param {HT.ComboTree} combo 组合框本身。This combo box
             */
            'collapse',
             /**
             * @event beforeselect
             * 列表项被选中前触发。返回 false 可以取消选择。
             * Fires before a list item is selected. Return false to cancel the selection.
             * @param {HT.ComboTree} combo 组合框本身。This combo box
             * @param {Ext.data.Record}  record 从数据仓库中返回的数据记录。The data record returned from the underlying store
             * @param {Number} index 选中项在下拉列表中的索引位置。The index of the selected item in the dropdown list
             */
            'beforeselect',
             /**
             * @event select
             * 当列表项被选中时触发。
             * Fires when a list item is selected
             * @param {HT.ComboTree} combo 组合框本身。This combo box
             * @param {Ext.data.Record} record 从数据仓库中返回的数据记录。The data record returned from the underlying store
             * @param {Number} index 选中项在下拉列表中的索引位置。The index of the selected item in the dropdown list
             */
            'select'
        );
	},
	
	/**
	 * 覆盖onRender
	 * @param {} ct
	 * @param {} position
	 */
	onRender : function(ct, position) {
		HT.ComboTree.superclass.onRender.call(this, ct, position);
		this.name =  this.name?this.name:this.hiddenName;	
       if(this.hiddenName){
            this.hiddenField = this.el.insertSibling({tag:'input', type:'hidden', name: this.hiddenName,
                    id: (this.hiddenId||this.hiddenName)}, 'before', true);

            // prevent input submission
            this.el.dom.removeAttribute('name');
        }
        if(Ext.isGecko){
            this.el.dom.setAttribute('autocomplete', 'off');
        }

		if (!this.lazyInit) {
			this.initList();
		} else {
			this.on('focus', this.initList, this, {
				single : true
			});
		}
		
		if(!this.editable){
            this.editable = true;
            this.setEditable(false);
        }
	},
	
    // private
    initValue : function(){
        HT.ComboTree.superclass.initValue.call(this);
        if(this.hiddenField){
            this.hiddenField.value =
                this.hiddenValue !== undefined ? this.hiddenValue :
                this.value !== undefined ? this.value : '';
        }
    },
	/**
	 * private：初始化下拉列表
	 */
	initList : function() {
		//构建容纳TreePanel的层
		if (this.list) {
			return;
		}
		this.list = new Ext.Layer({
			cls : 'x-combo-list',
			constrain : false
		});
		this.list.setWidth(Math.max(this.wrap.getWidth(), 70));
		this.mon(this.list,'mouseover',this.onViewOver,this);
		
		//构建TreePanel，并渲染到list中
		if(!this.tree){
			this.root = new Ext.tree.AsyncTreeNode({
				expanded : true
			});	
			this.loader = this.loader?this.loader:new Ext.tree.TreeLoader({
					url : this.url
			});
			this.tree = new Ext.tree.TreePanel({
				autoScroll : true,
				height : 200,
				border : false,
				root : this.root,
				rootVisible : false,
				loader : this.loader
			});
			delete this.loader;
		}
	
		this.tree.on({
			scope:this,
			click:this.onTreeClick

		});
		this.tree.render(this.list);
		
		this.el.dom.setAttribute('readOnly', true);
		this.el.addClass('x-combo-noedit');
	},
	
	expandNode : function(n, v) {
		var cs = n.childNodes;
		for (var i = 0, len = cs.length; i < len; i++) {
			if (cs[i].id == v) {
				return true;
			}
			if (expandNode(cs[i], v)) {
				cs[i].expand();
				return true;
			}
		}
		return false;
	},
//不要覆盖父类验证的方法
//	validateValue : function(value) {
//		return true;
//	},
	
	/**
	 * 覆盖onDestory
	 */
	onDestroy : function() {
		if (this.wrap) {
			this.wrap.remove();
		}
		if(this.tree){
			this.tree.destroy();
		}
		if (this.list) {
			this.list.destroy();
		}
		HT.ComboTree.superclass.onDestroy.call(this);
	},
	
	isExpanded : function() {
		return this.list && this.list.isVisible();
	},

	collapse : function() {
		if (this.isExpanded()) {
			this.list.hide();
		}
		this.fireEvent('collapse', this);
	},

	expand : function(){
		this.list.alignTo(this.wrap, 'tl-bl?');
		this.list.show();
		this.mon(Ext.getDoc(), {
            scope: this,
            mousewheel: this.collapseIf,
            mousedown: this.collapseIf
        });
        this.fireEvent('expand', this);
	},
	
	collapseIf : function(e){
	//	console.log(e.within);
        if(!this.isDestroyed && !e.within(this.wrap) && !e.within(this.list)){
            this.collapse();
        }
    },
    
	onSelect : function(node){
        if(this.fireEvent('beforeselect', this, node) !== false){
            this.setValue(node);
            this.collapse();
            this.fireEvent('select', this, node);
        }
    },
    
	onTreeClick : function(node) {
		if(node){
			this.onSelect(node);
		}else{
			this.collapse();
		}
	},
	
	assertValue:function(){
		if(this.currNode){
			this.setValue(this.currNode);
		}
	},
	
    // private
    beforeBlur:function(){
    	this.assertValue();
    },
    
    postBlur  : function(){
        HT.ComboTree.superclass.postBlur.call(this);
        this.collapse();
    },
    
    mimicBlur : function(e){
        if(!this.isDestroyed && !this.wrap.contains(e.target) && this.validateBlur(e)){
            this.triggerBlur();
        }
    },
    
    validateBlur : function(e){
        return !this.list || !this.list.isVisible();
    },
    
    onViewOver:function(e,t){
    	t=Ext.get(t);
    	if(t.hasClass("x-tree-node-el")){
    		var id=t.getAttribute("ext:tree-node-id");
    		if(id){
    			this.currNode=this.tree.getNodeById(id);
    		}
    	}
    },
    
    setValue : function(v) {
		var text = v;
		if (typeof v === 'object') {
			this.hiddenField.value = ((this.root && v.id == this.root.id)
					? ''
					: v.id);
			text = v.text;
		}
		HT.ComboTree.superclass.setValue.call(this, text);
		this.currNode=v;
	},
	
	getValue:function(){
		return this.currNode?this.currNode.id:'';
	},
	
	getSelected:function(){
		return this.currNode;
	},
	
	clearValue : function() {  
	        if (this.hiddenField) {  
	        	this.hiddenField.value = '';  
	        }  
	        this.setValue('');  
	 },  

	
	initEvents : function() {
		HT.ComboTree.superclass.initEvents.call(this);
		this.el.on('mousedown', this.onTriggerClick, this);
	},
		
	onTriggerClick : function() {
		if (this.disabled) {
			return;
		}
		if(this.isExpanded()) {
			this.collapse();
			this.el.focus();
		} else {
			this.onFocus({});
			this.expand();
			this.el.focus();
		}
	}
});
Ext.reg('combotree', HT.ComboTree);