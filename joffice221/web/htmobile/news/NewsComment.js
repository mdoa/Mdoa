Ext.define('mobile.news.NewsComment', {
			extend : 'mobile.List',
			name : 'newsComment',
			constructor : function(config) {
				config = config || {};
				
		    	Ext.apply(config,{
		    		title:config.title,
		    		fields:[
			            {name: "fullname", type: "string"},
			            {name: "createtime",  type: "string"},
			            {name: "content",  type: "string"},
			            {name: "newsId", type: "string"}
		    		],
		    		url:__ctxPath + '/htmobile/listNewsComment.do?newsId=' + config.newsId,
		    		root:'result',
				    itemTpl: new Ext.XTemplate(
				    	"<div><span class='name'>{fullname}</span>&nbsp;&nbsp;&nbsp;<span class='time'>{createtime}</span></div>"
							+ "<div style='margin-top:10px;'><span class='comment'>",
								'{[this.replaceBlank(values.content)]}' ,
							"</span><br></div>",{
								replaceBlank : function(content){
									return content.replaceAll("B_L_A_N_K","<br/>",false);
								}
							}
					),
					grouped: false,
					totalProperty: 'totalCounts',
				    pullRefresh: true,
				    listPaging: true
		    	});
				
				this.callParent([config]);
			}
});