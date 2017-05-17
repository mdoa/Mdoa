Ext.define('mobile.news.NewsDetail', {
	extend : 'Ext.Panel',
	name : 'newsDetail',
	constructor : function(config) {
		config = config || {};	
		currentData=config.result.data;
		commentCounts = config.result.total;
		comment=config.result.comment;
		replyCount = 1;
		htmls = "";
		getHtml = function() {
			var html = "";
			if(commentCounts==0) {
				html += "<div><span class='tip'>暂无评论!</span><div><hr>";
			}
			else {
				var tempComment;
				for(var i = 0; i<commentCounts && i<3; i++) {
					tempComment=comment[i];
					html += "<div><span class='name'>" + tempComment.fullname + "</span>&nbsp;&nbsp;&nbsp;<span class='time'>" + tempComment.createtime.substring(0, tempComment.createtime.length - 2) 
					+ "</span></div><div style='margin-top:10px;'><span class='comment'>" + tempComment.content.replaceAll("B_L_A_N_K","<br/>",false) + "</span><br></div><hr>";
				}
			}
			return html;
		};
						
		contentList = function(){
			mobileNavi.push(
					Ext.create('mobile.news.NewsComment', {
						newsId: currentData.newsId,
						title : config.itemRecord.get("subject"),
					})
			);
		};
		getContent = function(content) {
			var tempContent = content.replaceAll("B_L_A_N_K", "<br/>", false);
			imgStyle=tempContent.replaceAll("<img","<img class='newsImg' onClick='javascript:showImg(this)'");
			var hideHTML=document.getElementById("hideHTML");
			hideHTML.innerHTML="<div id='tempHideHTML' style='display:none'>"+imgStyle+"</div>";
			var imgs=Ext.DomQuery.select("img[class='newsImg']");
			for(var i=0;i<imgs.length;i++){
				imgs[i].style.width=(document.body.clientWidth/4)+"px";
				imgs[i].style.height=(document.body.clientWidth/4)+"px";
			}
			imgStyle=document.getElementById("tempHideHTML").innerHTML;
			return imgStyle.replaceAll("localhost",defaultsValues.IP);
		};
		Ext.apply(config, {
			title: currentData.section.sectionName,
			scrollable: 'vertical',
			items: [
		            {
		                xtype: 'panel',
		                margin: '10 8 0 8',
		                styleHtmlContent: true,
		                html: "<h3 align='center' style='margin-bottom:8px;font-weight:bold;'>" + currentData.subject + "</h3><div align='center' class='size'><span>发布时间：</span>" +
		                		"<span class='createtime'>" + currentData.createtime.substring(0,10) + "</span>" +
		                		"&nbsp;&nbsp<span>浏览次数：</span><span class='viewCounts'>" + currentData.viewCounts +
				    	        "</span>&nbsp;&nbsp<span>回复次数：</span><span class='replyCounts'>" + currentData.replyCounts + "</span></div><hr><p>" + 
				    	        getContent(currentData.content) + "</p><div class='size' align='right'>( 作者：<span class='authors'>" + currentData.author + 
				    	        "</span>&nbsp;&nbsp发布人：<span class='issuer'>" + currentData.issuer + "</span> )</div>"
		            },
		            {
		                xtype: 'panel',
		                id: 'commentpanel',
		                margin: '10 20 0 20',
		                html: "<div><img src='htmobile/resources/images/comment.jpg' /><span class='head'>评论</span></div><hr>" + getHtml()
		            },
		            {
		                xtype: 'formpanel',
		                margin: '10 20 70 20',
		                items: [
		                    {
		                        xtype: 'fieldset',
		                        docked: 'top',
		                        name: 'myFieldset',
		                        title : '我要评论<span class="newsComtent" onclick="javascript:contentList();"><span>'
										+ currentData.replyCounts
										+ '</span><img class="newsComtentImg" src="htmobile/resources/images/news_coment.png"/><span>',
								items: [
									{
		                                xtype: 'textareafield',
		                                placeHolder : '说两句......',
		                                hight: 40,
		                                name: 'content',
		                                maxLength: 200,
		                                maxRows: 3
		                            },{
	                                    xtype: 'button',
	                                    docked: 'bottom',
	                                    right: 1,
	                                    top: 120,
	                                    width: 100,
	                                    text: '发表评论',
	                                    handler: function() {
	                                    	var textareaField = Ext.ComponentQuery.query('textareafield')[0];
	                                    	if(textareaField.getValue()!=""){
	                                    		Ext.Ajax.request({
		                        					url : __ctxPath + '/htmobile/saveNewsComment.do',
		                        					params : {
		                        						"newsComment.newsId": currentData.newsId,
		                        						"newsComment.content": textareaField.getValue(),
		                        						"newsComment.fullname": config.result.currentusername
		                        					},
		                        					success : function(response) {
		                        						var commentpanel = Ext.getCmp('commentpanel');
		                        						var fieldset = Ext.ComponentQuery.query('fieldset')[0];
		                        						var fieldsetTitle = "我要评论<span class='newsComtent' onclick='javascript:contentList();'><span>" +
		            										+ (currentData.replyCounts + replyCount)
		            										+ "</span><img class='newsComtentImg' src='htmobile/resources/images/news_coment.png'/><span>";
		                        						fieldset.setTitle(fieldsetTitle);
		                        						var headHtml = "<div><img src='htmobile/resources/images/comment.jpg' /><span class='head'>评论</span></div><hr>";
		                        						var newComment = "<div><span class='name'>" + config.result.currentusername + "</span>&nbsp;&nbsp;&nbsp;<span class='time'>" + (new Date()).format('yyyy-MM-dd hh:mm:ss') 
		                    							+ "</span></div><div style='margin-top:10px;'><span class='comment'>" + textareaField.getValue().replaceAll("\n","<br/>",false) + "</span><br></div><hr>";
		                        						commentpanel.setHtml("");
		                        						htmls += newComment;
		                        						if(commentCounts==0){
		                        							commentpanel.setHtml(headHtml + htmls);
		                        						}
		                        						else {
			                        						commentpanel.setHtml(headHtml + getHtml() + htmls);
		                        						}
		                        						textareaField.setValue("");
		                        						replyCount++;
		                        					}
		                        				});
	                                    	}
	                                    	else {
	                                    		Ext.Msg.alert("提示", "评论内容不能为空!");
	                                    	}
	                                    }
		                            }
		                        ]
		                    }
		                ]
		            }
		        ]
		});
		this.callParent([config]);
	}
});
