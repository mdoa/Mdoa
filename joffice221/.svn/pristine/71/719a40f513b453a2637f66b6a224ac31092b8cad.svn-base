//报表菜单id:ReportTemplateMenu
ReportTemplateMenu = function(_cfg) {
	Ext.applyIf(this, _cfg);
	this.reportId = getReportIdByKey(this.reportKey);
	var preview =  new ReportTemplatePreview({
		reportId : this.reportId,
		title : this.title
	});
	return preview;
};
//根据报表key 取得报表ID
var getReportIdByKey = function(reportKey) {

	var reportId = '';
	Ext.Ajax.request( {
		url : __ctxPath + '/system/listReportTemplate.do',
		method : 'POST',
		async : false,
		success : function(response, opts) {
			var obj = Ext.decode(response.responseText);
			reportId = obj.result[0].reportId;
		},
		failure : function(response, opts) {
		},
		params : {
			Q_reportKey_S_EQ : reportKey
		}
	});
	return reportId;

};