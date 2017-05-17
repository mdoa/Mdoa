insert into sys_config(CONFIGID, CONFIGKEY, CONFIGNAME, CONFIGDESC, TYPENAME, DATATYPE, DATAVALUE,TYPEKEY)
values (1,'goodsStockUser','用户帐号','当库存产生警报时，接收消息的人员','行政管理配置',1,'admin','adminConfig');

insert into sys_config (CONFIGID, CONFIGKEY, CONFIGNAME, CONFIGDESC, TYPENAME, DATATYPE, DATAVALUE,TYPEKEY)
values (2,'codeConfig','验证码','登录时是否需要验证码','验证码配置',2,'1','codeConfig');

insert into sys_config (CONFIGID, CONFIGKEY, CONFIGNAME, CONFIGDESC, TYPENAME, DATATYPE, DATAVALUE,TYPEKEY)
values (3,'smsMobile','手机短信','流程执行时是否需要短信提醒','手机短信提醒',2,'1','smsConfig');

insert into sys_config (CONFIGID, CONFIGKEY, CONFIGNAME, CONFIGDESC, TYPENAME, DATATYPE, DATAVALUE,TYPEKEY)
values (4,'deviceName','设备名称','GMS设备名称','手机短信提醒',1,'COM4','smsConfig');

insert into sys_config (CONFIGID, CONFIGKEY, CONFIGNAME, CONFIGDESC, TYPENAME, DATATYPE, DATAVALUE,TYPEKEY)
values (5,'baudRate','设备波特率','设备波特率','手机短信提醒',1,'9600','smsConfig');

insert into sys_config (CONFIGID, CONFIGKEY, CONFIGNAME, CONFIGDESC, TYPENAME, DATATYPE, DATAVALUE,TYPEKEY)
values (6,'suggestId','意见接收人ID','意见接收人ID','意见箱配置',1,'1','suggestConfig');

insert into sys_config (CONFIGID, CONFIGKEY, CONFIGNAME, CONFIGDESC, TYPENAME, DATATYPE, DATAVALUE,TYPEKEY)
values (7,'suggestName','意见接收人','意见接收人','意见箱配置',1,'管理员','suggestConfig');

insert into sys_config (CONFIGID, CONFIGKEY, CONFIGNAME, CONFIGDESC, TYPENAME, DATATYPE, DATAVALUE,TYPEKEY)
values (8,'dynamicPwd','动态密码','动态密码','动态密码配置',2,'2','dynamicPwdConfig');

insert into sys_config (CONFIGID, CONFIGKEY, CONFIGNAME, CONFIGDESC, TYPENAME, DATATYPE, DATAVALUE,TYPEKEY)
values (9,'dynamicUri','验证路径','验证路径','动态密码配置',1,'http://www.yoo-e.com/cbsite/authsys/api/','dynamicPwdConfig');

insert into sys_config (CONFIGID, CONFIGKEY, CONFIGNAME, CONFIGDESC, TYPENAME, DATATYPE, DATAVALUE,TYPEKEY)
values (10,'smsPortUri','端口路径','商品路径','短信端口配置',1,'http://58.63.224.34:8000/smsweb/services/sms','smsPortConfig');

insert into sys_config (CONFIGID, CONFIGKEY, CONFIGNAME, CONFIGDESC, TYPENAME, DATATYPE, DATAVALUE,TYPEKEY)
values (11,'smsPortUserID','用户ID','用户ID','短信端口配置',1,'','smsPortConfig');

insert into sys_config (CONFIGID, CONFIGKEY, CONFIGNAME, CONFIGDESC, TYPENAME, DATATYPE, DATAVALUE,TYPEKEY)
values (12,'smsPortAccount','用户账号','用户账号','短信端口配置',1,'','smsPortConfig');

insert into sys_config (CONFIGID, CONFIGKEY, CONFIGNAME, CONFIGDESC, TYPENAME, DATATYPE, DATAVALUE,TYPEKEY)
values (13,'smsPortPwd','用户密码','用户密码','短信端口配置',1,'','smsPortConfig');

INSERT INTO app_user (USERID,USERNAME,TITLE,PASSWORD,EMAIL,DEPID,JOBID,PHONE,MOBILE,FAX,ADDRESS,ZIP,PHOTO,ACCESSIONTIME,STATUS,EDUCATION,FULLNAME,DELFLAG)  
VALUES (1,'admin',1,'a4ayc/80/OGda4BO/1o/V0etpOqiLx1JwB5S3beHW0s=','csx@jee-soft.cn',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2009-12-18 00:00:00',1,NULL,'超级管理员',0);

INSERT INTO app_user (USERID,USERNAME,TITLE,PASSWORD,EMAIL,DEPID,JOBID,PHONE,MOBILE,FAX,ADDRESS,ZIP,PHOTO,ACCESSIONTIME,STATUS,EDUCATION,FULLNAME,DELFLAG) 
VALUES (-1,'system',1,'0','152@163.com',1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2009-12-18 00:00:00',0,NULL,'系统',1);

insert into mail_folder (FOLDERID, USERID, FOLDERNAME, PARENTID, DEPLEVEL, PATH, ISPUBLIC, FOLDERTYPE)
values (1, null, '收件箱', 0, 1, '0.1.', 1, 1);

insert into mail_folder (FOLDERID, USERID, FOLDERNAME, PARENTID, DEPLEVEL, PATH, ISPUBLIC, FOLDERTYPE)
values (2, null, '发件箱', 0, 1, '0.2.', 1, 2);

insert into mail_folder (FOLDERID, USERID, FOLDERNAME, PARENTID, DEPLEVEL, PATH, ISPUBLIC, FOLDERTYPE)
values (3, null, '草稿箱', 0, 1, '0.3.', 1, 3);

insert into mail_folder (FOLDERID, USERID, FOLDERNAME, PARENTID, DEPLEVEL, PATH, ISPUBLIC, FOLDERTYPE)
values (4, null, '垃圾箱', 0, 1, '0.4.', 1, 4);

insert into app_role (ROLEID, ROLENAME, ROLEDESC, STATUS, RIGHTS, ISDEFAULTIN)
values (-1, '超级管理员', '超级管理员,具有所有权限', 1, '__ALL', 0);

insert into app_role (ROLEID, ROLENAME, ROLEDESC, STATUS, RIGHTS, ISDEFAULTIN)
values (1, '[人事经理]', '管理人事的经理', 1, 'SystemSetting,AppUserView,_AppUserQuery,_AppUserAdd,_AppUserEdit,_AppUserDel,DepartmentView,_DepartmentQuery,_DepartmentAdd,_DepartmentEdit,_DepartmentDel,PublicDocument,NewPublicDocumentForm,Task,NewWorkPlanForm,_NewDepPlan,Personal,PersonalDuty,SignInOffView,ErrandsRegisterView,ErrandsRegisterOutView,DutyManager,Duty,HolidayRecordView,_HolidayRecordQuery,_HolidayRecordAdd,_HolidayRecordEdit,_HolidayRecordDel,DutySectionView,_DutySectonQuery,_DutySectonAdd,_DutySectonEdit,_DutySectonDel,DutySystemView,_DutySystemQuery,_DutySystemAdd,_DutySystemEdit,_DutySystemDel,DutyView,_DutyQuery,_DutyAdd,_DutyEdit,_DutyDel,DutyRegisterView,_DutyRegisterQuery,_DutyRegisterAdd,_DutyRegisterDel', 0);

insert into app_role (ROLEID, ROLENAME, ROLEDESC, STATUS, RIGHTS, ISDEFAULTIN)
values (2, '[行政经理]', '管理行政', 1, 'SystemSetting,PublicDocument,NewPublicDocumentForm,Task,NewWorkPlanForm,_NewDepPlan,Personal,PersonalDuty,SignInOffView,ErrandsRegisterView,ErrandsRegisterOutView,Administrator,GoodManeger,OfficeGoodsManageView,_OfficeGoodsQuery,_OfficeGoodsTypeManage,_OfficeGoodsAdd,_OfficeGoodsEdit,_OfficeGoodsDel,InStockView,_InStockQuery,_InStockAdd,_InStockEdit,_InStockDel,GoodsApplyView,_GoodsApplyQuery,_GoodsApplyAdd,_GoodsApplyEdit,_GoodsApplyDel,CarManeger,CarView,_CarQuery,_CarAdd,_CarEdit,_CarDel,CartRepairView,_CarRepairQuery,_CarRepairAdd,_CarRepairEdit,_CarRepairDel,CarApplyView,_CarApplyQuery,_CarApplyAdd,_CarApplyEdit,_CarApplyDel,FixedAssetsManage,DepreTypeView,_DepreTypeQuery,_DepreTypeAdd,_DepreTypeEdit,_DepreTypeDel,FixedAssetsManageView,_FixedAssetsQuery,_AssetsTypeManage,_FixedAssetsAdd,_FixedAssetsEdit,_FixedAssetsDel,_Depreciate,DepreRecordView,_DepreRecordQuery,BookManager,BookTypeView,_BookTypeQuery,_BookTypeAdd,_BookTypeEdit,_BookTypeDel,BookManageView,_BookQuery,_BookAdd,_BookEdit,_BookDel,BookBorrowView,_BookBorrowQuery,_BookBorrowAdd,_BookBorrowEdit,_BookReturn,_BookBorrowDel,BookReturnView,_BookReturnQuery,_BookReturnAdd,_BookReturnEdit,_BookReturnDel', 0);

insert into app_role (ROLEID, ROLENAME, ROLEDESC, STATUS, RIGHTS, ISDEFAULTIN)
values (3, '[文档管理员]', '管理文档', 1, 'SystemSetting,PublicDocument,NewPublicDocumentForm,DocFolderSharedView,_DocFolderSharedManage,_DocPrivilegeQuery,_DocPrivilegeAdd,_DocPrivilegeEdit,_DocPrivilegeDel,Personal,PersonalDuty,SignInOffView,ErrandsRegisterView,ErrandsRegisterOutView', 0);

insert into app_role (ROLEID, ROLENAME, ROLEDESC, STATUS, RIGHTS, ISDEFAULTIN)
values (4, '[信息管理员]', '管理新闻公告等信息', 1, 'SystemSetting,Task,PlanTypeView,_PlanTypeQuery,_PlanTypeAdd,_PlanTypeEdit,_PlanTypeDel,NewWorkPlanForm,_NewDepPlan,Info,NewsView,_NewsQuery,_NewsAdd,_NewsEdit,_NewsDel,NewsCommentView,_NewsCommentQuery,_NewsCommentDel,NewsTypeView,_NewsTypeQuery,_NewsTypeAdd,_NewsTypeEdit,_NewsTypeDel,NoticeView,_NoticeQuery,_NoticeAdd,_NoticeEdit,_NoticeDel,Personal,PersonalDuty,SignInOffView,ErrandsRegisterView,ErrandsRegisterOutView', 0);

insert into app_role (ROLEID, ROLENAME, ROLEDESC, STATUS, RIGHTS, ISDEFAULTIN)
values (5, '[客户经理]', '管理客户信息', 1, 'SystemSetting,PublicDocument,NewPublicDocumentForm,Task,NewWorkPlanForm,_NewDepPlan,Personal,PersonalDuty,SignInOffView,ErrandsRegisterView,ErrandsRegisterOutView,customer,CustomerView,_CustomerQuery,_CustomerAdd,_CustomerEdit,_CustomerDel,CusLinkmanView,_CusLinkmanQuery,_CusLinkmanAdd,_CusLinkmanEdit,_CusLinkmanDel,CusConnectionView,_CusConnectionQuery,_CusConnectionAdd,_CusConnectionEdit,_CusConnectionDel,ProjectView,_ProjectQuery,_ProjectAdd,_ProjectEdit,_ProjectDel,ContractView,_ContractQuery,_ContractAdd,_ContractEdit,_ContractDel,ProductView,_ProductQuery,_ProductAdd,_ProductEdit,_ProductDel,ProviderView,_ProviderQuery,_ProviderAdd,_ProviderEdit,_ProviderDel', 0);

insert into app_role (ROLEID, ROLENAME, ROLEDESC, STATUS, RIGHTS, ISDEFAULTIN)
values (6, '公文管理', '公文管理', 1, 'Archive,ArchFlowConfView,_ArchFlowConfEdit,ArchiveIssue,ArchiveTypeTempView,_ArchiveTypeTempQuery,_ArchivesTypeAdd,_ArchivesTypeEdit,_ArchivesTypeDel,_ArchivesTempAdd,_ArchivesTempEdit,_ArchviesTempDel,ArchivesDraftView,_AchivesDrafAdd,ArchivesDraftManage,_ArchivesDrafmQuery,_ArchivesDrafmEdit,_ArchivesDrafmDel,ArchivesIssueAudit,_ArchivesIssueQuery,_ArchivesIssueEdit,ArchivesIssueLead,_ArchivesIssueLeadQuery,_ArchivesIssueLeadEdit,ArchivesIssueCharge,_ArchivesIssueChargeQuery,_ArchivesIssueChargeEdit,ArchivesIssueProof,_ArchivesIssueProofQuery,_ArchivesIssueProofEdit,ArchivesDocument,_ArchivesDocumentQuery,ArchivesIssueMonitor,_ArchivesIssueMonitorQuery,_ArchivesIssueHasten,ArchivesIssueManage,_ArchivesIssueManageQuery,ArchivesIssueSearch,_ArchivesIssueSearchQuery,DocHistoryView,_DocHistoryQuery,_DocHistoryDel,ArchiveReceive,ArchivesSignView,_ArchivesSignQuery,_ArchivesSignUp,ArchivesRecView,_ArchivesRecQuery,_ArchivesRecAdd,_ArchivesRecEdit,_ArchivesRecDel,ArchivesHandleView,_ArchivesHandleQuery,LeaderReadView,_LeaderReadQuery,ArchDispatchView,_ArchDispatchQuery,ArchUndertakeView,_ArchUndertakeQuery,ArchivesRecCtrlView,_ArchivesRecCtrlQuery,_ArchivesRecHasten,ArchReadView,_ArchReadQuery,ArchRecTypeView,_ArchRecTypeQuery,_ArchRecTypeAdd,_ArchRecTypeEdit,_ArchRecTypeDel', 0);

insert into demension values(1,'行政维度','行政维度',1);
insert into demension values(2,'项目维度','项目维度',2);

insert into form_def (FORMDEFID, FORMTITLE, FORMDESP, DEFHTML, STATUS, FORMTYPE, ISDEFAULT, ISGEN) 
values (1, '通用流程表单', '通用流程表单', '<table class="form-info" cellspacing="1" cellpadding="0" style="width: 878px; height: 478px"><tbody><tr class="tr-info"><td class="td-info" colspan="4" style="text-align: center"><h2>申请事项</h2></td></tr><tr class="tr-info"><td class="td-info" width="20%"><strong>事项标题</strong></td><td class="td-info"><input class="x-form-text x-form-field" width="350" xtype="textfield" isnew="isnew" txtlabel="事项标题" txtvaluetype="varchar" txtisnotnull="1" txtisprimary="0" txtsize="256" style="width: 350px" name="itemSubject" type="text" /></td><td class="td-info" width="10%"><strong>创建时间</strong></td><td class="td-info" width="10%"><input xtype="datefield" txtlabel="创建时间" txtvaluetype="date" txtisnotnull="0" __cfckdate="__cfckdate" txtistoday="1" dateformat="yyyy-MM-dd" name="createtime" type="text" /></td></tr><tr class="tr-info"><td class="td-info"><strong>事项描述</strong></td><td class="td-info" colspan="3"><textarea class="x-form-textarea x-form-field x-column" xtype="fckeditor" txtlabel="事项描述" txtvaluetype="text" txtwidth="600" txtheight="350" isfck="true" style="width: 600px; height: 350px" name="itemDescp"></textarea></td></tr></tbody></table>', 1, 0, 1, 1);

insert into form_table (TABLEID, FORMDEFID, TABLENAME, TABLEKEY, ISMAIN) 
values (1, 1, '通用表单', 'general', 1);

insert into form_field (FIELDID, TABLEID, FIELDNAME, FIELDLABEL, FIELDTYPE, ISREQUIRED, FIELDSIZE, FIELDDSCP, ISPRIMARY, FOREIGNKEY, FOREIGNTABLE, ISLIST, ISQUERY, SHOWFORMAT, ISFLOWTITLE, ISDESIGNSHOW)
values (1, 1, 'entityId', 'ID', 'bigint', 0, null, '', 1, '', '', 1, 1, '', 0, 3);

insert into form_field (FIELDID, TABLEID, FIELDNAME, FIELDLABEL, FIELDTYPE, ISREQUIRED, FIELDSIZE, FIELDDSCP, ISPRIMARY, FOREIGNKEY, FOREIGNTABLE, ISLIST, ISQUERY, SHOWFORMAT, ISFLOWTITLE, ISDESIGNSHOW)
values (2, 1, 'itemDescp', '事项描述', 'text', 0, 4000, '', 0, '', '', 1, 1, '', 0, 1);

insert into form_field (FIELDID, TABLEID, FIELDNAME, FIELDLABEL, FIELDTYPE, ISREQUIRED, FIELDSIZE, FIELDDSCP, ISPRIMARY, FOREIGNKEY, FOREIGNTABLE, ISLIST, ISQUERY, SHOWFORMAT, ISFLOWTITLE, ISDESIGNSHOW)
values (3, 1, 'createtime', '创建时间', 'date', 0, null, '', 0, '', '', 1, 1, 'yyyy-MM-dd', 0, 1);

insert into form_field (FIELDID, TABLEID, FIELDNAME, FIELDLABEL, FIELDTYPE, ISREQUIRED, FIELDSIZE, FIELDDSCP, ISPRIMARY, FOREIGNKEY, FOREIGNTABLE, ISLIST, ISQUERY, SHOWFORMAT, ISFLOWTITLE, ISDESIGNSHOW)
values (4, 1, 'itemSubject', '事项标题', 'varchar', 1, 256, '', 0, '', '', 1, 1, '', 1, 1);

insert into user_role (roleId,userId) values(-1,1);

insert into organization (ORG_ID,DEM_ID,ORG_NAME,ORG_DESC,ORG_SUP_ID,PATH,DEPTH,ORG_TYPE,CREATOR_ID,CREATETIME,UPDATE_ID,UPDATETIME) values (1,1,'宏天软件','宏天软件',0,'0.1.',1,1,1,'2009-12-18 00:00:00',1,'2009-12-18 00:00:00');

insert into type_key (typekeyId,typekey,typename,sn)values(1,'REGULATION','规章制度分类',1);
insert into type_key (typekeyId,typekey,typename,sn)values(2,'AR_FD','全宗分类',2);
insert into type_key (typekeyId,typekey,typename,sn)values(3,'AR_RL','案卷分类',3);
insert into type_key (typekeyId,typekey,typename,sn)values(4,'DIC','数据字典分类',4);
insert into type_key (typekeyId,typekey,typename,sn)values(5,'AR_RLF','文件分类',5);
insert into type_key (typekeyId,typekey,typename,sn)values(6,'FLOW','流程分类',6);
insert into type_key (typekeyId,typekey,typename,sn)values(7,'DEPWORKPLAN','部门计划分类',7);

insert into type_key (typekeyId,typekey,typename,sn)values(8,'PWP','个人计划分类',8);
insert into type_key (typekeyId,typekey,typename,sn)values(9,'ARCHIVES_TYPE','发文分类',9);
insert into type_key (typekeyId,typekey,typename,sn)values(10,'ARC_TEM_TYPE','公文模板分类',10);
insert into type_key (typekeyId,typekey,typename,sn)values(11,'ARCHIVES_REC_TYPE','收文分类',11);
insert into type_key (typekeyId,typekey,typename,sn)values(12,'ATTACHFILE_TYPE','附件分类',12);

insert into global_type(protypeid, typename,path,depth, parentid, nodekey, catkey,sn, userid,depid)
values(1007,'我的桌面','0.1007.',1,0,'myDesktop','ATTACHFILE_TYPE',1,null,null);

insert into global_type(protypeid, typename,path, depth, parentid, nodekey, catkey,sn, userid,depid)
values(1008,'修改个人资料','0.1007.1008.',2,1007,'myDesktop/profile','ATTACHFILE_TYPE',1,null,null);

insert into global_type(protypeid, typename,path,depth, parentid, nodekey, catkey,sn, userid,depid)
values(1009,'我的计划','0.1007.1009.',2,1007,'myDesktop/personalWorkPlan','ATTACHFILE_TYPE',2,null,null);

insert into global_type(protypeid, typename,path,depth, parentid, nodekey, catkey,sn, userid,depid)
values(1010,'公文管理','0.1010.',1,0,'archive','ATTACHFILE_TYPE',1,null,null);

insert into global_type(protypeid, typename,path,depth, parentid, nodekey, catkey,sn, userid,depid)
values(1011,'发文管理','0.1010.1011.',2,1010,'archive/archiveIssue','ATTACHFILE_TYPE',1,null,null);

insert into global_type(protypeid, typename,path,depth, parentid, nodekey, catkey,sn, userid,depid)
values(1012,'公文类别、模板管理','0.1010.1011.1012.',3,1011,'archive/archiveIssue/archiveTypeTemp','ATTACHFILE_TYPE',1,null,null);

insert into global_type(protypeid, typename,path,depth, parentid, nodekey, catkey,sn, userid,depid)
values(1013,'发文拟稿','0.1010.1011.1013.',3,1011,'archive/archiveIssue/archivesDraft','ATTACHFILE_TYPE',2,null,null);

insert into global_type(protypeid, typename,path,depth, parentid, nodekey, catkey,sn, userid,depid)
values(1014,'收文管理','0.1010.1014.',2,1010,'archive/archiveReceive','ATTACHFILE_TYPE',2,null,null);

insert into global_type(protypeid, typename,path,depth, parentid, nodekey, catkey,sn, userid,depid)
values(1015,'收文登记','0.1010.1014.1015.',3,1014,'archive/archiveReceive/archivesRec','ATTACHFILE_TYPE',1,null,null);


insert into global_type(protypeid, typename,path,depth, parentid, nodekey, catkey,sn, userid,depid)
values(1016,'系统设置','0.1016.',1,0,'system','ATTACHFILE_TYPE',1,null,null);

insert into global_type(protypeid, typename,path,depth, parentid, nodekey, catkey,sn, userid,depid)
values(1017,'员工管理','0.1016.1017.',2,1016,'system/appUser','ATTACHFILE_TYPE',1,null,null);

insert into global_type(protypeid, typename,path,depth, parentid, nodekey, catkey,sn, userid,depid)
values(1018,'公司信息管理','0.1016.1018.',2,1016,'system/company','ATTACHFILE_TYPE',2,null,null);

insert into global_type(protypeid, typename,path,depth, parentid, nodekey, catkey,sn, userid,depid)
values(1019,'报表管理','0.1016.1019.',2,1016,'system/reportTemplate','ATTACHFILE_TYPE',3,null,null);


insert into global_type(protypeid, typename,path,depth, parentid, nodekey, catkey,sn, userid,depid)
values(1020,'信息管理','0.1020.',1,0,'info','ATTACHFILE_TYPE',1,null,null);

insert into global_type(protypeid, typename,path,depth, parentid, nodekey, catkey,sn, userid,depid)
values(1021,'新闻管理','0.1020.1021.',2,1020,'info/news','ATTACHFILE_TYPE',1,null,null);

insert into global_type(protypeid, typename,path,depth, parentid, nodekey, catkey,sn, userid,depid)
values(1022,'公告管理','0.1020.1022.',2,1020,'info/notice','ATTACHFILE_TYPE',2,null,null);


insert into global_type(protypeid, typename,path,depth, parentid, nodekey, catkey,sn, userid,depid)
values(1023,'通讯管理','0.1023.',1,0,'communication','ATTACHFILE_TYPE',1,null,null);

insert into global_type(protypeid, typename,path,depth, parentid, nodekey, catkey,sn, userid,depid)
values(1024,'内部邮件','0.1023.1024.',2,1023,'communication/innerMail','ATTACHFILE_TYPE',1,null,null);

insert into global_type(protypeid, typename,path,depth, parentid, nodekey, catkey,sn, userid,depid)
values(1025,'外部邮件','0.1023.1025.',2,1023,'communication/outMail','ATTACHFILE_TYPE',2,null,null);


insert into global_type(protypeid, typename,path,depth, parentid, nodekey, catkey,sn, userid,depid)
values(1026,'文档管理','0.1026.',1,0,'document','ATTACHFILE_TYPE',1,null,null);

insert into global_type(protypeid, typename,path,depth, parentid, nodekey, catkey,sn, userid,depid)
values(1027,'个人文档','0.1026.1027.',2,1026,'document/privateDocument','ATTACHFILE_TYPE',1,null,null);

insert into global_type(protypeid, typename,path,depth, parentid, nodekey, catkey,sn, userid,depid)
values(1028,'知识管理','0.1026.1028.',2,1026,'document/knowledgeManage','ATTACHFILE_TYPE',1,null,null);

insert into global_type(protypeid, typename,path,depth, parentid, nodekey, catkey,sn, userid,depid)
values(1031,'在线文档管理','0.1026.1031.',2,1026,'document/saveOnlineManage','ATTACHFILE_TYPE',1,null,null);

insert into global_type(protypeid, typename,path,depth, parentid, nodekey, catkey,sn, userid,depid)
values(1032,'上传文档','0.1032.',1,0,'uploadDocument','ATTACHFILE_TYPE',1,null,null);

insert into global_type(protypeid, typename,path,depth, parentid, nodekey, catkey,sn, userid,depid)
values(1029,'流程管理','0.1029.',1,0,'flow','ATTACHFILE_TYPE',1,null,null);

insert into global_type(protypeid, typename,path,depth, parentid, nodekey, catkey,sn, userid,depid)
values(1030,'报表管理','0.1030.',1,0,'report','ATTACHFILE_TYPE',1,null,null);

insert into form_rule (ruleid, name, rule, memo, tipinfo)
values (1, '手机号码', '^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$', '手机号码输入有误', '验证手机号码');

insert into form_rule (ruleid, name, rule, memo, tipinfo)
values (2, 'email', '^\w+([-+.]\w+)*@\w+([-.]\w+)*.\w+([-.]\w+)*$', 'email格式输入有误', 'email规则验证');

insert into form_rule (ruleid, name, rule, memo, tipinfo)
values (3, 'QQ号码', '^[1-9]*[1-9][0-9]*$', '请输入有效的QQ号码', '验证QQ号码');

insert into form_rule (ruleid, name, rule, memo, tipinfo)
values (4, '汉字', '^[\u4E00-\u9FA5]+$', '验证汉字', '请输入汉字');

insert into form_rule (ruleid, name, rule, memo, tipinfo)
values (5, '整数', '^-?\d+$', '验证整数', '请输入整数');

insert into form_rule (ruleid, name, rule, memo, tipinfo)
values (6, '正整数', '^[1-9]+\d*$', '验证正整数', '请输入正整数');

insert into form_rule (ruleid, name, rule, memo, tipinfo)
values (7, '负整数', '^-{1}\d+$', '验证负整数', '请输入负整数');

insert into form_rule (ruleid, name, rule, memo, tipinfo)
values (8, '英数字', '^[a-zA-Z0-9]+$', '验证输入英文与数字', '请输入英文字母和数字');

insert into form_rule (ruleid, name, rule, memo, tipinfo)
values (9, '非负整数', '^\d+$', '验证非负整数', '请输入非负整数');

insert into form_rule (ruleid, name, rule, memo, tipinfo)
values (10, '英文字母', '^[a-zA-Z]+$', '验证英文字母', '请输入英文字母');
