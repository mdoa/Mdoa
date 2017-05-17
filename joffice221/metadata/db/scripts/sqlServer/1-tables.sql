/*==============================================================*/
/* DBMS name:      Microsoft SQL Server 2005                    */
/* Created on:     2012/4/1 16:57:11                            */
/*==============================================================*/


/*==============================================================*/
/* Table: app_function                                          */
/*==============================================================*/
create table app_function (
   functionId           bigint          identity,
   funKey               varchar(64)          not null,
   funName              varchar(128)         not null,
   constraint PK_APP_FUNCTION primary key nonclustered (functionId),
   constraint AK_UQ_RSKEY_APP_FUNC unique (funKey)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '权限Key',
   'user', @CurrentUser, 'table', 'app_function', 'column', 'funKey'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '权限名称',
   'user', @CurrentUser, 'table', 'app_function', 'column', 'funName'
go

/*==============================================================*/
/* Table: app_role                                              */
/*==============================================================*/
create table app_role (
   roleId               bigint          identity,
   roleName             varchar(128)         not null,
   roleDesc             varchar(128)         null,
   status               smallint             not null,
   rights               text                 null,
   isDefaultIn          smallint             not null,
   constraint PK_APP_ROLE primary key nonclustered (roleId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '角色表',
   'user', @CurrentUser, 'table', 'app_role'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '角色名称',
   'user', @CurrentUser, 'table', 'app_role', 'column', 'roleName'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '角色描述',
   'user', @CurrentUser, 'table', 'app_role', 'column', 'roleDesc'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '状态',
   'user', @CurrentUser, 'table', 'app_role', 'column', 'status'
go

/*==============================================================*/
/* Table: app_tips                                              */
/*==============================================================*/
create table app_tips (
   tipsId               bigint          identity,
   userId               bigint          not null,
   tipsName             varchar(128)         null,
   content              varchar(2048)        null,
   disheight            bigint          null,
   diswidth             bigint          null,
   disleft              bigint          null,
   distop               bigint          null,
   dislevel             bigint          null,
   createtime           datetime             not null,
   constraint PK_APP_TIPS primary key nonclustered (tipsId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '用户便签',
   'user', @CurrentUser, 'table', 'app_tips'
go

/*==============================================================*/
/* Table: app_user                                              */
/*==============================================================*/
create table app_user (
   userId               bigint          identity,
   username             varchar(128)         not null,
   title                smallint             not null,
   depId                bigint          null,
   password             varchar(128)         not null,
   email                varchar(128)         not null,
   jobId                bigint          null,
   phone                varchar(32)          null,
   mobile               varchar(32)          null,
   fax                  varchar(32)          null,
   address              varchar(64)          null,
   zip                  varchar(32)          null,
   photo                varchar(128)         null,
   accessionTime        datetime             not null,
   status               smallint             not null,
   education            varchar(64)          null,
   fullname             varchar(50)          not null,
   delFlag              smallint             not null,
   constraint PK_APP_USER primary key nonclustered (userId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'app_user
   用户表',
   'user', @CurrentUser, 'table', 'app_user'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '主键',
   'user', @CurrentUser, 'table', 'app_user', 'column', 'userId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '用户名',
   'user', @CurrentUser, 'table', 'app_user', 'column', 'username'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '1=先生
   0=女士
   小姐',
   'user', @CurrentUser, 'table', 'app_user', 'column', 'title'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '密码',
   'user', @CurrentUser, 'table', 'app_user', 'column', 'password'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '邮件',
   'user', @CurrentUser, 'table', 'app_user', 'column', 'email'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '职位',
   'user', @CurrentUser, 'table', 'app_user', 'column', 'jobId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '电话',
   'user', @CurrentUser, 'table', 'app_user', 'column', 'phone'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '手机',
   'user', @CurrentUser, 'table', 'app_user', 'column', 'mobile'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '传真',
   'user', @CurrentUser, 'table', 'app_user', 'column', 'fax'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '地址',
   'user', @CurrentUser, 'table', 'app_user', 'column', 'address'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '邮编',
   'user', @CurrentUser, 'table', 'app_user', 'column', 'zip'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '相片',
   'user', @CurrentUser, 'table', 'app_user', 'column', 'photo'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '入职时间',
   'user', @CurrentUser, 'table', 'app_user', 'column', 'accessionTime'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '状态
   1=激活
   0=禁用
   2=离职
   ',
   'user', @CurrentUser, 'table', 'app_user', 'column', 'status'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '0=未删除
   1=删除',
   'user', @CurrentUser, 'table', 'app_user', 'column', 'delFlag'
go

/*==============================================================*/
/* Table: appointment                                           */
/*==============================================================*/
create table appointment (
   appointId            bigint          identity,
   userId               bigint          null,
   subject              varchar(128)         not null,
   startTime            datetime             not null,
   endTime              datetime             not null,
   content              text                 not null,
   notes                varchar(1000)        null,
   location             varchar(150)         not null,
   inviteEmails         varchar(1000)        null,
   sendMessage          smallint             null,
   sendMail             smallint             null,
   constraint PK_APPOINTMENT primary key nonclustered (appointId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '约会管理',
   'user', @CurrentUser, 'table', 'appointment'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '主键',
   'user', @CurrentUser, 'table', 'appointment', 'column', 'userId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '主题',
   'user', @CurrentUser, 'table', 'appointment', 'column', 'subject'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '开始时间',
   'user', @CurrentUser, 'table', 'appointment', 'column', 'startTime'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '结束时间',
   'user', @CurrentUser, 'table', 'appointment', 'column', 'endTime'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '约会内容',
   'user', @CurrentUser, 'table', 'appointment', 'column', 'content'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '备注',
   'user', @CurrentUser, 'table', 'appointment', 'column', 'notes'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '地点',
   'user', @CurrentUser, 'table', 'appointment', 'column', 'location'
go

/*==============================================================*/
/* Table: arch_dispatch                                         */
/*==============================================================*/
create table arch_dispatch (
   dispatchId           bigint          identity,
   archivesId           bigint          null,
   dispatchTime         datetime             not null,
   userId               bigint          not null,
   fullname             varchar(128)         null,
   isRead               smallint             null,
   subject              varchar(256)         null,
   readFeedback         varchar(1024)        null,
   archUserType         smallint             not null default 0,
   disRoleId            bigint          null,
   disRoleName          varchar(64)          null,
   constraint PK_ARCH_DISPATCH primary key nonclustered (dispatchId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '0=阅读人员
   1=承办人
   2=分发负责人
   ',
   'user', @CurrentUser, 'table', 'arch_dispatch', 'column', 'archUserType'
go

/*==============================================================*/
/* Table: arch_flow_conf                                        */
/*==============================================================*/
create table arch_flow_conf (
   configId             bigint          identity,
   defId                bigint          null,
   processName          varchar(128)         not null,
   archType             smallint             not null,
   depId                bigint          null,
   constraint PK_ARCH_FLOW_CONF primary key nonclustered (configId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '公文流程设置',
   'user', @CurrentUser, 'table', 'arch_flow_conf'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '0=发文
   1=收文',
   'user', @CurrentUser, 'table', 'arch_flow_conf', 'column', 'archType'
go

/*==============================================================*/
/* Table: arch_fond                                             */
/*==============================================================*/
create table arch_fond (
   archFondId           bigint          identity,
   afNo                 varchar(64)          not null,
   afName               varchar(128)         not null,
   shortDesc            varchar(4000)        null,
   descp                text                 null,
   clearupDesc          varchar(4000)        null,
   createTime           datetime             null,
   updateTime           datetime             null,
   creatorName          varchar(32)          null,
   creatorId            bigint          null,
   caseNums             bigint          null,
   status               smallint             null,
   proTypeId            bigint          null,
   typeName             varchar(128)         null,
   openStyle            varchar(64)          null,
   constraint PK_ARCH_FOND primary key nonclustered (archFondId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '全宗号',
   'user', @CurrentUser, 'table', 'arch_fond', 'column', 'afNo'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '全宗名',
   'user', @CurrentUser, 'table', 'arch_fond', 'column', 'afName'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '全宗概述',
   'user', @CurrentUser, 'table', 'arch_fond', 'column', 'shortDesc'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '全宗描述',
   'user', @CurrentUser, 'table', 'arch_fond', 'column', 'descp'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '全宗整理描述',
   'user', @CurrentUser, 'table', 'arch_fond', 'column', 'clearupDesc'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '创建时间',
   'user', @CurrentUser, 'table', 'arch_fond', 'column', 'createTime'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '最后更新时间',
   'user', @CurrentUser, 'table', 'arch_fond', 'column', 'updateTime'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '创建人',
   'user', @CurrentUser, 'table', 'arch_fond', 'column', 'creatorName'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '创建人ID',
   'user', @CurrentUser, 'table', 'arch_fond', 'column', 'creatorId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '案件数',
   'user', @CurrentUser, 'table', 'arch_fond', 'column', 'caseNums'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '0草稿
   1启用
   -1禁用
   ',
   'user', @CurrentUser, 'table', 'arch_fond', 'column', 'status'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '全宗分类名称',
   'user', @CurrentUser, 'table', 'arch_fond', 'column', 'typeName'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '开放形式(来自数字字典)',
   'user', @CurrentUser, 'table', 'arch_fond', 'column', 'openStyle'
go

/*==============================================================*/
/* Table: arch_hasten                                           */
/*==============================================================*/
create table arch_hasten (
   recordId             bigint          identity,
   archivesId           bigint          null,
   content              varchar(1024)        null,
   createtime           datetime             null,
   hastenFullname       varchar(64)          null,
   handlerFullname      varchar(64)          null,
   handlerUserId        bigint          null,
   constraint PK_ARCH_HASTEN primary key nonclustered (recordId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '催办内容',
   'user', @CurrentUser, 'table', 'arch_hasten', 'column', 'content'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '催办时间',
   'user', @CurrentUser, 'table', 'arch_hasten', 'column', 'createtime'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '催办人',
   'user', @CurrentUser, 'table', 'arch_hasten', 'column', 'hastenFullname'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '承办人',
   'user', @CurrentUser, 'table', 'arch_hasten', 'column', 'handlerFullname'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '承办人ID',
   'user', @CurrentUser, 'table', 'arch_hasten', 'column', 'handlerUserId'
go

/*==============================================================*/
/* Table: arch_roll                                             */
/*==============================================================*/
create table arch_roll (
   rollId               bigint          identity,
   archFondId           bigint          null,
   proTypeId            bigint          null,
   typeName             varchar(32)          null,
   rolllName            varchar(128)         not null,
   afNo                 varchar(64)          not null,
   rollNo               varchar(64)          not null,
   catNo                varchar(64)          null,
   timeLimit            varchar(64)          null,
   startTime            datetime             null,
   endTime              datetime             null,
   openStyle            varchar(64)          null,
   author               varchar(32)          null,
   setupTime            datetime             null,
   checker              varchar(32)          null,
   creatorName          varchar(32)          null,
   createTime           datetime             null,
   keyWords             varchar(512)         null,
   editCompany          varchar(128)         null,
   editDep              varchar(128)         null,
   decp                 text                 null,
   status               smallint             null,
   constraint PK_ARCH_ROLL primary key nonclustered (rollId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '保管期限
   长久
   长期
   短期
   10年
   15年
   20年
   
   ',
   'user', @CurrentUser, 'table', 'arch_roll', 'column', 'timeLimit'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '起始日期',
   'user', @CurrentUser, 'table', 'arch_roll', 'column', 'startTime'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '结束日期',
   'user', @CurrentUser, 'table', 'arch_roll', 'column', 'endTime'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '开放形式',
   'user', @CurrentUser, 'table', 'arch_roll', 'column', 'openStyle'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '立卷人',
   'user', @CurrentUser, 'table', 'arch_roll', 'column', 'author'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '立卷时间',
   'user', @CurrentUser, 'table', 'arch_roll', 'column', 'setupTime'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '检查人',
   'user', @CurrentUser, 'table', 'arch_roll', 'column', 'checker'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '录入人',
   'user', @CurrentUser, 'table', 'arch_roll', 'column', 'creatorName'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '录入时间',
   'user', @CurrentUser, 'table', 'arch_roll', 'column', 'createTime'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '1=正常
   0=销毁 ',
   'user', @CurrentUser, 'table', 'arch_roll', 'column', 'status'
go

/*==============================================================*/
/* Table: arch_template                                         */
/*==============================================================*/
create table arch_template (
   templateId           bigint          identity,
   fileId               bigint          null,
   proTypeId            bigint          null,
   tempName             varchar(128)         not null,
   tempPath             varchar(256)         not null,
   constraint PK_ARCH_TEMPLATE primary key nonclustered (templateId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '公文模板',
   'user', @CurrentUser, 'table', 'arch_template'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '模板名称',
   'user', @CurrentUser, 'table', 'arch_template', 'column', 'tempName'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '路径',
   'user', @CurrentUser, 'table', 'arch_template', 'column', 'tempPath'
go

/*==============================================================*/
/* Table: archives                                              */
/*==============================================================*/
create table archives (
   archivesId           bigint          identity,
   typeName             varchar(128)         null,
   archivesNo           varchar(100)         not null,
   issueDep             varchar(128)         null,
   proTypeId            bigint          null,
   glo_proTypeId        bigint          null,
   subject              varchar(256)         not null,
   createtime           datetime             not null,
   issueDate            datetime             not null,
   status               varchar(256)         not null,
   shortContent         varchar(1024)        null,
   fileCounts           bigint          null default 0,
   privacyLevel         varchar(50)          null default '普通',
   urgentLevel          varchar(50)          null default '普通',
   issuer               varchar(50)          null,
   issuerId             bigint          null,
   keywords             varchar(256)         null,
   sources              varchar(50)          null,
   archType             smallint             not null default 0,
   recDepIds            varchar(2000)        null,
   recDepNames          varchar(2000)        null,
   handlerUids          varchar(256)         null,
   handlerUnames        varchar(256)         null,
   orgArchivesId        bigint          null,
   depSignNo            varchar(100)         null,
   runId                bigint          null,
   archStatus           smallint             null,
   depId                bigint          null,
   constraint PK_ARCHIVES primary key nonclustered (archivesId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '收发公文',
   'user', @CurrentUser, 'table', 'archives'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '公文类型名称',
   'user', @CurrentUser, 'table', 'archives', 'column', 'typeName'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '发文字号',
   'user', @CurrentUser, 'table', 'archives', 'column', 'archivesNo'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '发文机关或部门',
   'user', @CurrentUser, 'table', 'archives', 'column', 'issueDep'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '文件标题',
   'user', @CurrentUser, 'table', 'archives', 'column', 'subject'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '发布日期',
   'user', @CurrentUser, 'table', 'archives', 'column', 'issueDate'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '公文状态
   0=拟稿、修改状态
   1=发文状态
   2=归档状态',
   'user', @CurrentUser, 'table', 'archives', 'column', 'status'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '内容简介',
   'user', @CurrentUser, 'table', 'archives', 'column', 'shortContent'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '文件数',
   'user', @CurrentUser, 'table', 'archives', 'column', 'fileCounts'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '秘密等级
   普通
   秘密
   机密
   绝密',
   'user', @CurrentUser, 'table', 'archives', 'column', 'privacyLevel'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '紧急程度
   普通
   紧急
   特急
   特提',
   'user', @CurrentUser, 'table', 'archives', 'column', 'urgentLevel'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '发文人',
   'user', @CurrentUser, 'table', 'archives', 'column', 'issuer'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '发文人ID',
   'user', @CurrentUser, 'table', 'archives', 'column', 'issuerId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '主题词',
   'user', @CurrentUser, 'table', 'archives', 'column', 'keywords'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '公文来源
   仅在收文中指定，发公文不需要指定
   上级公文
   下级公文',
   'user', @CurrentUser, 'table', 'archives', 'column', 'sources'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '0=发文
   1=收文',
   'user', @CurrentUser, 'table', 'archives', 'column', 'archType'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '用于存储接收公文的部门ID,使用,进行分开',
   'user', @CurrentUser, 'table', 'archives', 'column', 'recDepIds'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '用于存储接收公文的部门的名称，使用,进行分开',
   'user', @CurrentUser, 'table', 'archives', 'column', 'recDepNames'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '在收文中使用，多个用户ID用'',''分割',
   'user', @CurrentUser, 'table', 'archives', 'column', 'handlerUids'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '用于收文，存储多个拟办用户名，用‘，’分割',
   'user', @CurrentUser, 'table', 'archives', 'column', 'handlerUnames'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '用于收文时使用，指向原公文ID',
   'user', @CurrentUser, 'table', 'archives', 'column', 'orgArchivesId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '用于收文时，部门对自身的公文自编号',
   'user', @CurrentUser, 'table', 'archives', 'column', 'depSignNo'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '流程运行id
   通过该id可以查看该公文的审批历史',
   'user', @CurrentUser, 'table', 'archives', 'column', 'runId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '归档状态
   1=已经归档
   0=尚未归档',
   'user', @CurrentUser, 'table', 'archives', 'column', 'archStatus'
go

/*==============================================================*/
/* Table: archives_dep                                          */
/*==============================================================*/
create table archives_dep (
   archDepId            bigint          identity,
   signNo               varchar(128)         null,
   archivesId           bigint          not null,
   subject              varchar(256)         not null,
   status               smallint             not null,
   signTime             datetime             null,
   signFullname         varchar(64)          null,
   signUserID           bigint          null,
   handleFeedback       varchar(4000)        null,
   isMain               smallint             not null default 1,
   depId                bigint          null,
   constraint PK_ARCHIVES_DEP primary key nonclustered (archDepId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '主键',
   'user', @CurrentUser, 'table', 'archives_dep', 'column', 'archDepId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '自编号',
   'user', @CurrentUser, 'table', 'archives_dep', 'column', 'signNo'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '所属公文',
   'user', @CurrentUser, 'table', 'archives_dep', 'column', 'archivesId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '公文标题',
   'user', @CurrentUser, 'table', 'archives_dep', 'column', 'subject'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '签收状态
   0=未签收
   1=已签收',
   'user', @CurrentUser, 'table', 'archives_dep', 'column', 'status'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '签收日期',
   'user', @CurrentUser, 'table', 'archives_dep', 'column', 'signTime'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '签收人',
   'user', @CurrentUser, 'table', 'archives_dep', 'column', 'signFullname'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '办理结果反馈',
   'user', @CurrentUser, 'table', 'archives_dep', 'column', 'handleFeedback'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '主送、抄送
   1=主送
   0=抄送',
   'user', @CurrentUser, 'table', 'archives_dep', 'column', 'isMain'
go

/*==============================================================*/
/* Table: archives_doc                                          */
/*==============================================================*/
create table archives_doc (
   docId                bigint          identity,
   archivesId           bigint          null,
   fileId               bigint          null,
   creator              varchar(64)          null,
   creatorId            bigint          null,
   menderId             bigint          null,
   mender               varchar(64)          null,
   docName              varchar(128)         not null,
   docStatus            smallint             not null,
   curVersion           bigint          not null,
   docPath              varchar(128)         not null,
   updatetime           datetime             not null,
   createtime           datetime             not null,
   constraint PK_ARCHIVES_DOC primary key nonclustered (docId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '拟稿人',
   'user', @CurrentUser, 'table', 'archives_doc', 'column', 'creator'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '拟稿人ID',
   'user', @CurrentUser, 'table', 'archives_doc', 'column', 'creatorId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '修改人',
   'user', @CurrentUser, 'table', 'archives_doc', 'column', 'mender'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '文档名称',
   'user', @CurrentUser, 'table', 'archives_doc', 'column', 'docName'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '文档状态
   0=修改中
   1=修改完成',
   'user', @CurrentUser, 'table', 'archives_doc', 'column', 'docStatus'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '当前版本
   取当前最新的版本',
   'user', @CurrentUser, 'table', 'archives_doc', 'column', 'curVersion'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '文档路径',
   'user', @CurrentUser, 'table', 'archives_doc', 'column', 'docPath'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '更新时间',
   'user', @CurrentUser, 'table', 'archives_doc', 'column', 'updatetime'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '创建时间',
   'user', @CurrentUser, 'table', 'archives_doc', 'column', 'createtime'
go

/*==============================================================*/
/* Table: assets_type                                           */
/*==============================================================*/
create table assets_type (
   assetsTypeId         bigint          identity,
   typeName             varchar(128)         not null,
   constraint PK_ASSETS_TYPE primary key nonclustered (assetsTypeId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '分类名称',
   'user', @CurrentUser, 'table', 'assets_type', 'column', 'typeName'
go

/*==============================================================*/
/* Table: board_type                                            */
/*==============================================================*/
create table board_type (
   typeId               bigint          identity,
   typeName             varchar(128)         not null,
   typeDesc             varchar(4000)        not null,
   constraint PK_BOARD_TYPE primary key nonclustered (typeId)
)
go

/*==============================================================*/
/* Table: boardroo                                              */
/*==============================================================*/
create table boardroo (
   roomId               bigint          identity,
   roomName             varchar(128)         not null,
   roomDesc             varchar(4000)        null,
   containNum           bigint          null default 0,
   constraint PK_BOARDROO primary key nonclustered (roomId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '会议室名称',
   'user', @CurrentUser, 'table', 'boardroo', 'column', 'roomName'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '会议室描述',
   'user', @CurrentUser, 'table', 'boardroo', 'column', 'roomDesc'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '容纳人数',
   'user', @CurrentUser, 'table', 'boardroo', 'column', 'containNum'
go

/*==============================================================*/
/* Table: book                                                  */
/*==============================================================*/
create table book (
   bookId               bigint          identity,
   typeId               bigint          null,
   bookName             varchar(128)         not null,
   author               varchar(128)         not null,
   isbn                 varchar(64)          not null,
   publisher            varchar(128)         null,
   price                numeric              not null,
   location             varchar(128)         not null,
   department           varchar(64)          not null,
   amount               bigint          not null,
   leftAmount           bigint          not null,
   constraint PK_BOOK primary key nonclustered (bookId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '图书',
   'user', @CurrentUser, 'table', 'book'
go

/*==============================================================*/
/* Table: book_bor_ret                                          */
/*==============================================================*/
create table book_bor_ret (
   recordId             bigint          identity,
   bookSnId             bigint          null,
   borrowTime           datetime             not null,
   returnTime           datetime             not null,
   lastReturnTime       datetime             null,
   borrowIsbn           varchar(128)         not null,
   bookName             varchar(128)         not null,
   registerName         varchar(32)          not null,
   fullname             varchar(32)          not null,
   constraint PK_BOOK_BOR_RET primary key nonclustered (recordId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '图书借还表',
   'user', @CurrentUser, 'table', 'book_bor_ret'
go

/*==============================================================*/
/* Table: book_sn                                               */
/*==============================================================*/
create table book_sn (
   bookSnId             bigint          identity,
   bookId               bigint          not null,
   bookSN               varchar(128)         not null,
   status               smallint             not null,
   constraint PK_BOOK_SN primary key nonclustered (bookSnId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '借阅状态
   0=未借出
   1=借出
   2=预订
   3=注销',
   'user', @CurrentUser, 'table', 'book_sn', 'column', 'status'
go

/*==============================================================*/
/* Table: book_type                                             */
/*==============================================================*/
create table book_type (
   typeId               bigint          identity,
   typeName             varchar(128)         not null,
   constraint PK_BOOK_TYPE primary key nonclustered (typeId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '图书类别',
   'user', @CurrentUser, 'table', 'book_type'
go

/*==============================================================*/
/* Table: borrow_file_list                                      */
/*==============================================================*/
create table borrow_file_list (
   listId               bigint          identity,
   recordId             bigint          not null,
   listType             varchar(64)          null,
   archFondId           bigint          null,
   afNo                 varchar(64)          null,
   afName               varchar(128)         null,
   rollId               bigint          null,
   rollNo               varchar(64)          null,
   rolllName            varchar(128)         null,
   rollFileId           bigint          null,
   fileNo               varchar(64)          null,
   fileName             varchar(128)         null,
   constraint PK_BORROW_FILE_LIST primary key nonclustered (listId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '文件借阅清单',
   'user', @CurrentUser, 'table', 'borrow_file_list'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '0:全宗
   1:案卷
   2:文件',
   'user', @CurrentUser, 'table', 'borrow_file_list', 'column', 'listType'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '全宗号',
   'user', @CurrentUser, 'table', 'borrow_file_list', 'column', 'afNo'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '全宗名',
   'user', @CurrentUser, 'table', 'borrow_file_list', 'column', 'afName'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '文件编号',
   'user', @CurrentUser, 'table', 'borrow_file_list', 'column', 'fileNo'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '文件题名',
   'user', @CurrentUser, 'table', 'borrow_file_list', 'column', 'fileName'
go

/*==============================================================*/
/* Table: borrow_record                                         */
/*==============================================================*/
create table borrow_record (
   recordId             bigint          identity,
   borrowDate           datetime             null,
   borrowType           varchar(64)          null,
   borrowReason         varchar(64)          null,
   checkUserId          bigint          null,
   checkUserName        varchar(64)          null,
   checkDate            datetime             null,
   returnDate           datetime             null,
   borrowNum            varchar(128)         null,
   borrowRemark         varchar(128)         null,
   returnStatus         smallint             null,
   checkId              bigint          null,
   checkName            varchar(64)          null,
   checkContent         varchar(128)         null,
   constraint PK_BORROW_RECORD primary key nonclustered (recordId)
)
go

/*==============================================================*/
/* Table: cal_file                                              */
/*==============================================================*/
create table cal_file (
   fileId               bigint          not null,
   planId               bigint          not null,
   constraint PK_CAL_FILE primary key nonclustered (fileId, planId)
)
go

/*==============================================================*/
/* Table: calendar_plan                                         */
/*==============================================================*/
create table calendar_plan (
   planId               bigint          identity,
   startTime            datetime             null,
   endTime              datetime             null,
   urgent               smallint             not null,
   summary              varchar(200)         null,
   content              varchar(1200)        not null,
   status               smallint             not null,
   userId               bigint          not null,
   fullname             varchar(32)          null,
   assignerId           bigint          not null,
   assignerName         varchar(32)          null,
   feedback             varchar(500)         null,
   showStyle            smallint             not null,
   taskType             smallint             not null,
   constraint PK_CALENDAR_PLAN primary key nonclustered (planId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '日程安排',
   'user', @CurrentUser, 'table', 'calendar_plan'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '开始时间',
   'user', @CurrentUser, 'table', 'calendar_plan', 'column', 'startTime'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '结束时间',
   'user', @CurrentUser, 'table', 'calendar_plan', 'column', 'endTime'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '紧急程度
   0=一般
   1=重要
   2=紧急',
   'user', @CurrentUser, 'table', 'calendar_plan', 'column', 'urgent'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '内容',
   'user', @CurrentUser, 'table', 'calendar_plan', 'column', 'content'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '状态
   0=未完成
   1=完成',
   'user', @CurrentUser, 'table', 'calendar_plan', 'column', 'status'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '员工ID',
   'user', @CurrentUser, 'table', 'calendar_plan', 'column', 'userId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '员工名',
   'user', @CurrentUser, 'table', 'calendar_plan', 'column', 'fullname'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '分配人',
   'user', @CurrentUser, 'table', 'calendar_plan', 'column', 'assignerId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '分配人名',
   'user', @CurrentUser, 'table', 'calendar_plan', 'column', 'assignerName'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '反馈意见',
   'user', @CurrentUser, 'table', 'calendar_plan', 'column', 'feedback'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '显示方式
   1=仅在任务中显示
   2=在日程与任务中显示',
   'user', @CurrentUser, 'table', 'calendar_plan', 'column', 'showStyle'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '任务类型
   1=限期任务
   2=非限期任务',
   'user', @CurrentUser, 'table', 'calendar_plan', 'column', 'taskType'
go

/*==============================================================*/
/* Table: car                                                   */
/*==============================================================*/
create table car (
   carId                bigint          identity,
   carNo                varchar(128)         not null,
   carType              varchar(64)          not null,
   engineNo             varchar(128)         null,
   buyInsureTime        datetime             null,
   auditTime            datetime             null,
   notes                varchar(500)         null,
   factoryModel         varchar(64)          not null,
   driver               varchar(32)          not null,
   buyDate              datetime             not null,
   status               smallint             not null,
   cartImage            varchar(128)         null,
   constraint PK_CAR primary key nonclustered (carId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '车辆信息',
   'user', @CurrentUser, 'table', 'car'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '轿车
   货车
   商务车
   ',
   'user', @CurrentUser, 'table', 'car', 'column', 'carType'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '购买保险时间',
   'user', @CurrentUser, 'table', 'car', 'column', 'buyInsureTime'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '年审时间',
   'user', @CurrentUser, 'table', 'car', 'column', 'auditTime'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '购置日期',
   'user', @CurrentUser, 'table', 'car', 'column', 'buyDate'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '当前状态
   1=可用
   2=维修中
   0=报废',
   'user', @CurrentUser, 'table', 'car', 'column', 'status'
go

/*==============================================================*/
/* Table: car_apply                                             */
/*==============================================================*/
create table car_apply (
   applyId              bigint          identity,
   carId                bigint          not null,
   department           varchar(64)          not null,
   userFullname         varchar(32)          not null,
   applyDate            datetime             not null,
   reason               varchar(512)         not null,
   startTime            datetime             not null,
   endTime              datetime             null,
   userId               bigint          not null,
   proposer             varchar(32)          not null,
   mileage              numeric(18,2)        null,
   oilUse               numeric(18,2)        null,
   notes                varchar(128)         null,
   approvalStatus       smallint             not null,
   constraint PK_CAR_APPLY primary key nonclustered (applyId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '车辆申请',
   'user', @CurrentUser, 'table', 'car_apply'
go

/*==============================================================*/
/* Table: cart_repair                                           */
/*==============================================================*/
create table cart_repair (
   repairId             bigint          identity,
   carId                bigint          null,
   repairDate           datetime             not null,
   reason               varchar(128)         not null,
   executant            varchar(128)         not null,
   notes                varchar(128)         null,
   repairType           varchar(128)         not null,
   fee                  numeric(18,2)        null,
   constraint PK_CART_REPAIR primary key nonclustered (repairId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '维护日期',
   'user', @CurrentUser, 'table', 'cart_repair', 'column', 'repairDate'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '维护原因',
   'user', @CurrentUser, 'table', 'cart_repair', 'column', 'reason'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '经办人',
   'user', @CurrentUser, 'table', 'cart_repair', 'column', 'executant'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '备注',
   'user', @CurrentUser, 'table', 'cart_repair', 'column', 'notes'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '维修类型
   保养
   维修',
   'user', @CurrentUser, 'table', 'cart_repair', 'column', 'repairType'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '费用',
   'user', @CurrentUser, 'table', 'cart_repair', 'column', 'fee'
go

/*==============================================================*/
/* Table: company                                               */
/*==============================================================*/
create table company (
   companyId            bigint          identity,
   companyNo            varchar(128)         null,
   companyName          varchar(128)         not null,
   companyDesc          varchar(4000)        null,
   legalPerson          varchar(32)          null,
   setup                datetime             null,
   phone                varchar(32)          null,
   fax                  varchar(32)          null,
   site                 varchar(128)         null,
   logo                 varchar(128)         null,
   constraint PK_COMPANY primary key nonclustered (companyId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '公司信息',
   'user', @CurrentUser, 'table', 'company'
go

/*==============================================================*/
/* Table: conf_attach                                           */
/*==============================================================*/
create table conf_attach (
   confId               bigint          null,
   fileId               bigint          null
)
go

/*==============================================================*/
/* Table: conf_attend                                           */
/*==============================================================*/
create table conf_attend (
   attendId             bigint          identity,
   confId               bigint          null,
   userId               bigint          null,
   userType             smallint             null default 3,
   fullname             varchar(50)          null,
   constraint PK_CONF_ATTEND primary key nonclustered (attendId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '会议ID',
   'user', @CurrentUser, 'table', 'conf_attend', 'column', 'confId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '1=主持人
   2=记录人
   3=参与会议人',
   'user', @CurrentUser, 'table', 'conf_attend', 'column', 'userType'
go

/*==============================================================*/
/* Table: conf_privilege                                        */
/*==============================================================*/
create table conf_privilege (
   privilegeId          bigint          identity,
   confId               bigint          null,
   userId               bigint          not null,
   fullname             varchar(64)          not null,
   rights               smallint             not null,
   constraint PK_CONF_PRIVILEGE primary key nonclustered (privilegeId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '权限
   1=查看
   2=修改
   3=建立纪要',
   'user', @CurrentUser, 'table', 'conf_privilege', 'column', 'rights'
go

/*==============================================================*/
/* Table: conf_sum_attach                                       */
/*==============================================================*/
create table conf_sum_attach (
   sumId                bigint          null,
   fileId               bigint          null
)
go

/*==============================================================*/
/* Table: conf_summary                                          */
/*==============================================================*/
create table conf_summary (
   sumId                bigint          identity,
   confId               bigint          null,
   createtime           datetime             not null,
   creator              varchar(32)          not null,
   sumContent           text                 not null,
   status               smallint             null,
   constraint PK_CONF_SUMMARY primary key nonclustered (sumId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '状态
   0=待发送
   1=发送',
   'user', @CurrentUser, 'table', 'conf_summary', 'column', 'status'
go

/*==============================================================*/
/* Table: conference                                            */
/*==============================================================*/
create table conference (
   confId               bigint          identity,
   confTopic            varchar(128)         not null,
   confProperty         varchar(64)          null,
   importLevel          smallint             not null,
   feeBudget            numeric(18,2)        null,
   compereName          varchar(256)         null,
   compere              varchar(128)         null,
   recorder             varchar(128)         null,
   recorderName         varchar(256)         null,
   attendUsers          varchar(500)         null,
   attendUsersName      varchar(4000)        null,
   status               smallint             not null,
   isEmail              smallint             null,
   isMobile             smallint             null,
   startTime            datetime             not null,
   endTime              datetime             not null,
   roomId               bigint          null,
   typeId               bigint          null,
   roomName             varchar(64)          null,
   roomLocation         varchar(128)         null,
   confContent          text                 null,
   createtime           datetime             null,
   sendtime             datetime             null,
   checkReason          varchar(512)         null,
   checkUserId          bigint          null,
   checkName            varchar(64)          null,
   constraint PK_CONFERENCE primary key nonclustered (confId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '会议议题',
   'user', @CurrentUser, 'table', 'conference', 'column', 'confTopic'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '会议性质',
   'user', @CurrentUser, 'table', 'conference', 'column', 'confProperty'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '重要级别
   1=普通
   2=重要
   3=非常重要',
   'user', @CurrentUser, 'table', 'conference', 'column', 'importLevel'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '费用预算',
   'user', @CurrentUser, 'table', 'conference', 'column', 'feeBudget'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '主持人
   (允许多人，用,分开)',
   'user', @CurrentUser, 'table', 'conference', 'column', 'compere'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '记录人
   (允许多人，用,分开)',
   'user', @CurrentUser, 'table', 'conference', 'column', 'recorder'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '参加人员列表',
   'user', @CurrentUser, 'table', 'conference', 'column', 'attendUsers'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '状态
   0=暂存
   1=发布',
   'user', @CurrentUser, 'table', 'conference', 'column', 'status'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '邮件通知',
   'user', @CurrentUser, 'table', 'conference', 'column', 'isEmail'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '短信通知',
   'user', @CurrentUser, 'table', 'conference', 'column', 'isMobile'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '开始时间',
   'user', @CurrentUser, 'table', 'conference', 'column', 'startTime'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '结束时间',
   'user', @CurrentUser, 'table', 'conference', 'column', 'endTime'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '会议室ID',
   'user', @CurrentUser, 'table', 'conference', 'column', 'roomId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '会议室',
   'user', @CurrentUser, 'table', 'conference', 'column', 'roomName'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '会议地点',
   'user', @CurrentUser, 'table', 'conference', 'column', 'roomLocation'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '会议内容',
   'user', @CurrentUser, 'table', 'conference', 'column', 'confContent'
go

/*==============================================================*/
/* Table: contract                                              */
/*==============================================================*/
create table contract (
   contractId           bigint          identity,
   contractNo           varchar(64)          not null,
   subject              varchar(128)         not null,
   contractAmount       numeric              not null,
   mainItem             varchar(4000)        null,
   salesAfterItem       varchar(4000)        null,
   validDate            datetime             not null,
   expireDate           datetime             not null,
   serviceDep           varchar(64)          null,
   serviceMan           varchar(64)          null,
   signupUser           varchar(64)          not null,
   signupTime           datetime             not null,
   creator              varchar(32)          not null,
   createtime           datetime             not null,
   projectId            bigint          null,
   consignAddress       varchar(128)         null,
   consignee            varchar(32)          null,
   constraint PK_CONTRACT primary key nonclustered (contractId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '合同编号',
   'user', @CurrentUser, 'table', 'contract', 'column', 'contractNo'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '合同标题',
   'user', @CurrentUser, 'table', 'contract', 'column', 'subject'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '合同金额',
   'user', @CurrentUser, 'table', 'contract', 'column', 'contractAmount'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '主要条款',
   'user', @CurrentUser, 'table', 'contract', 'column', 'mainItem'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '售后条款',
   'user', @CurrentUser, 'table', 'contract', 'column', 'salesAfterItem'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '生效日期',
   'user', @CurrentUser, 'table', 'contract', 'column', 'validDate'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '有效期',
   'user', @CurrentUser, 'table', 'contract', 'column', 'expireDate'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '维修部门',
   'user', @CurrentUser, 'table', 'contract', 'column', 'serviceDep'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '维修负责人',
   'user', @CurrentUser, 'table', 'contract', 'column', 'serviceMan'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '签约人',
   'user', @CurrentUser, 'table', 'contract', 'column', 'signupUser'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '签约时间',
   'user', @CurrentUser, 'table', 'contract', 'column', 'signupTime'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '录入人',
   'user', @CurrentUser, 'table', 'contract', 'column', 'creator'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '录入时间',
   'user', @CurrentUser, 'table', 'contract', 'column', 'createtime'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '所属项目',
   'user', @CurrentUser, 'table', 'contract', 'column', 'projectId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '收货地址',
   'user', @CurrentUser, 'table', 'contract', 'column', 'consignAddress'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '收货人',
   'user', @CurrentUser, 'table', 'contract', 'column', 'consignee'
go

/*==============================================================*/
/* Table: contract_config                                       */
/*==============================================================*/
create table contract_config (
   configId             bigint          identity,
   itemName             varchar(128)         not null,
   contractId           bigint          null,
   itemSpec             varchar(128)         not null,
   amount               numeric(18,2)        not null,
   notes                varchar(200)         null,
   constraint PK_CONTRACT_CONFIG primary key nonclustered (configId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '合同配置单',
   'user', @CurrentUser, 'table', 'contract_config'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '设备名称',
   'user', @CurrentUser, 'table', 'contract_config', 'column', 'itemName'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '设置规格',
   'user', @CurrentUser, 'table', 'contract_config', 'column', 'itemSpec'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '数量',
   'user', @CurrentUser, 'table', 'contract_config', 'column', 'amount'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '备注',
   'user', @CurrentUser, 'table', 'contract_config', 'column', 'notes'
go

/*==============================================================*/
/* Table: contract_file                                         */
/*==============================================================*/
create table contract_file (
   fileId               bigint          not null,
   contractId           bigint          not null,
   constraint PK_CONTRACT_FILE primary key nonclustered (fileId, contractId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '合同附件',
   'user', @CurrentUser, 'table', 'contract_file'
go

/*==============================================================*/
/* Table: cus_connection                                        */
/*==============================================================*/
create table cus_connection (
   connId               bigint          identity,
   customerId           bigint          not null,
   contactor            varchar(32)          not null,
   startDate            datetime             not null,
   endDate              datetime             not null,
   content              varchar(512)         not null,
   notes                varchar(1000)        null,
   creator              varchar(32)          null,
   constraint PK_CUS_CONNECTION primary key nonclustered (connId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'business connection ',
   'user', @CurrentUser, 'table', 'cus_connection'
go

/*==============================================================*/
/* Table: cus_linkman                                           */
/*==============================================================*/
create table cus_linkman (
   linkmanId            bigint          identity,
   customerId           bigint          not null,
   fullname             varchar(32)          not null,
   sex                  smallint             not null,
   position             varchar(32)          null,
   phone                varchar(32)          null,
   mobile               varchar(32)          not null,
   fax                  varchar(32)          null,
   email                varchar(100)         null,
   msn                  varchar(100)         null,
   qq                   varchar(64)          null,
   birthday             datetime             null,
   homeAddress          varchar(128)         null,
   homeZip              varchar(32)          null,
   homePhone            varchar(32)          null,
   hobby                varchar(100)         null,
   isPrimary            smallint             not null,
   notes                varchar(500)         null,
   constraint PK_CUS_LINKMAN primary key nonclustered (linkmanId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '客户联系人',
   'user', @CurrentUser, 'table', 'cus_linkman'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '所属客户',
   'user', @CurrentUser, 'table', 'cus_linkman', 'column', 'customerId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '姓名',
   'user', @CurrentUser, 'table', 'cus_linkman', 'column', 'fullname'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '性别',
   'user', @CurrentUser, 'table', 'cus_linkman', 'column', 'sex'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '职位',
   'user', @CurrentUser, 'table', 'cus_linkman', 'column', 'position'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '电话',
   'user', @CurrentUser, 'table', 'cus_linkman', 'column', 'phone'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '手机',
   'user', @CurrentUser, 'table', 'cus_linkman', 'column', 'mobile'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Email',
   'user', @CurrentUser, 'table', 'cus_linkman', 'column', 'email'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'MSN',
   'user', @CurrentUser, 'table', 'cus_linkman', 'column', 'msn'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'QQ',
   'user', @CurrentUser, 'table', 'cus_linkman', 'column', 'qq'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '生日',
   'user', @CurrentUser, 'table', 'cus_linkman', 'column', 'birthday'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '家庭住址',
   'user', @CurrentUser, 'table', 'cus_linkman', 'column', 'homeAddress'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '邮编',
   'user', @CurrentUser, 'table', 'cus_linkman', 'column', 'homeZip'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '家庭电话',
   'user', @CurrentUser, 'table', 'cus_linkman', 'column', 'homePhone'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '爱好',
   'user', @CurrentUser, 'table', 'cus_linkman', 'column', 'hobby'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '是否为主要联系人',
   'user', @CurrentUser, 'table', 'cus_linkman', 'column', 'isPrimary'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '备注',
   'user', @CurrentUser, 'table', 'cus_linkman', 'column', 'notes'
go

/*==============================================================*/
/* Table: customer                                              */
/*==============================================================*/
create table customer (
   customerId           bigint          identity,
   customerNo           varchar(64)          not null,
   industryType         varchar(64)          not null,
   customerSource       varchar(64)          null,
   customerType         smallint             not null,
   companyScale         bigint          null,
   customerName         varchar(128)         not null,
   customerManager      varchar(32)          not null,
   phone                varchar(32)          not null,
   fax                  varchar(32)          null,
   site                 varchar(128)         null,
   email                varchar(128)         null,
   state                varchar(32)          null,
   city                 varchar(32)          null,
   zip                  varchar(32)          null,
   address              varchar(100)         null,
   registerFun          numeric              null,
   turnOver             numeric              null,
   currencyUnit         varchar(32)          null,
   otherDesc            varchar(800)         null,
   principal            varchar(32)          null,
   openBank             varchar(64)          null,
   accountsNo           varchar(64)          null,
   taxNo                varchar(64)          null,
   notes                varchar(500)         null,
   rights               smallint             not null,
   constraint PK_CUSTOMER primary key nonclustered (customerId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '客户信息',
   'user', @CurrentUser, 'table', 'customer'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '客户号
   自动生成',
   'user', @CurrentUser, 'table', 'customer', 'column', 'customerNo'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '所属行业
   有缺省的选择，也可以输入',
   'user', @CurrentUser, 'table', 'customer', 'column', 'industryType'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '客户来源
   可编辑，可添加
   
   电话访问
   网络
   客户或朋友介绍',
   'user', @CurrentUser, 'table', 'customer', 'column', 'customerSource'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '1=正式客户
   2=重要客户
   3＝潜在客户
   4＝无效客户',
   'user', @CurrentUser, 'table', 'customer', 'column', 'customerType'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '1=1-20人
   2=20-50人
   3=50-100人
   4=100-200人
   5=200-500人
   6=500-1000 人
   7=1000人以上
   ',
   'user', @CurrentUser, 'table', 'customer', 'column', 'companyScale'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '客户名称
   一般为公司名称',
   'user', @CurrentUser, 'table', 'customer', 'column', 'customerName'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '负责该客户的经理',
   'user', @CurrentUser, 'table', 'customer', 'column', 'customerManager'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '电话',
   'user', @CurrentUser, 'table', 'customer', 'column', 'phone'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '注册资金及年营业额的货币单位
   可选可编辑
   人民币（默认）
   美元
   ',
   'user', @CurrentUser, 'table', 'customer', 'column', 'currencyUnit'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '1=客户经理及上级经理有权查看
   2=公开
   3=共享人员有权查看',
   'user', @CurrentUser, 'table', 'customer', 'column', 'rights'
go

/*==============================================================*/
/* Table: demension                                             */
/*==============================================================*/
create table demension (
   dem_id               bigint          identity,
   dem_name             varchar(128)         not null,
   dem_desc             varchar(1024)        null,
   dem_type             smallint             not null,
   constraint PK_DEMENSION primary key nonclustered (dem_id)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '维度名称',
   'user', @CurrentUser, 'table', 'demension', 'column', 'dem_name'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '维度描述',
   'user', @CurrentUser, 'table', 'demension', 'column', 'dem_desc'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '类型
   1=行政(为公司的整个组织结构，用户所属的部门即隶属于该维度)
   2=其他
   ',
   'user', @CurrentUser, 'table', 'demension', 'column', 'dem_type'
go

/*==============================================================*/
/* Table: dep_pos                                               */
/*==============================================================*/
create table dep_pos (
   org_id               bigint          not null,
   pos_id               bigint          not null,
   constraint PK_DEP_POS primary key nonclustered (org_id, pos_id)
)
go

/*==============================================================*/
/* Table: depre_record                                          */
/*==============================================================*/
create table depre_record (
   recordId             bigint          identity,
   assetsId             bigint          not null,
   workCapacity         numeric(18,2)        null,
   workGrossUnit        varchar(128)         null,
   depreAmount          numeric(18,4)        not null,
   calTime              datetime             not null,
   constraint PK_DEPRE_RECORD primary key nonclustered (recordId)
)
go

/*==============================================================*/
/* Table: depre_type                                            */
/*==============================================================*/
create table depre_type (
   depreTypeId          bigint          identity,
   typeName             varchar(64)          not null,
   deprePeriod          bigint          not null,
   typeDesc             varchar(500)         null,
   calMethod            smallint             not null,
   constraint PK_DEPRE_TYPE primary key nonclustered (depreTypeId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'depreciation type',
   'user', @CurrentUser, 'table', 'depre_type'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '单位为月',
   'user', @CurrentUser, 'table', 'depre_type', 'column', 'deprePeriod'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '1=平均年限法
   2=工作量法
   加速折旧法
   3=双倍余额递减法
   4=年数总和法',
   'user', @CurrentUser, 'table', 'depre_type', 'column', 'calMethod'
go

/*==============================================================*/
/* Table: diary                                                 */
/*==============================================================*/
create table diary (
   diaryId              bigint          identity,
   userId               bigint          null,
   dayTime              datetime             not null,
   content              text                 not null,
   diaryType            smallint             not null,
   constraint PK_DIARY primary key nonclustered (diaryId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '主键',
   'user', @CurrentUser, 'table', 'diary', 'column', 'userId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '1=工作日志
   0=个人日志',
   'user', @CurrentUser, 'table', 'diary', 'column', 'diaryType'
go

/*==============================================================*/
/* Table: dictionary                                            */
/*==============================================================*/
create table dictionary (
   dicId                bigint          identity,
   proTypeId            bigint          null,
   itemName             varchar(64)          not null,
   itemValue            varchar(128)         not null,
   descp                varchar(256)         null,
   sn                   bigint          null,
   constraint PK_DICTIONARY primary key nonclustered (dicId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '数据字典',
   'user', @CurrentUser, 'table', 'dictionary'
go

/*==============================================================*/
/* Table: doc_file                                              */
/*==============================================================*/
create table doc_file (
   fileId               bigint          not null,
   docId                bigint          not null,
   constraint PK_DOC_FILE primary key nonclustered (fileId, docId)
)
go

/*==============================================================*/
/* Table: doc_folder                                            */
/*==============================================================*/
create table doc_folder (
   folderId             bigint          identity,
   userId               bigint          null,
   folderName           varchar(128)         not null,
   parentId             bigint          null,
   path                 varchar(128)         null,
   isShared             smallint             not null,
   descp                varchar(256)         null,
   constraint PK_DOC_FOLDER primary key nonclustered (folderId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '主键',
   'user', @CurrentUser, 'table', 'doc_folder', 'column', 'userId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '目录名称',
   'user', @CurrentUser, 'table', 'doc_folder', 'column', 'folderName'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '父目录',
   'user', @CurrentUser, 'table', 'doc_folder', 'column', 'parentId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '路径
   为当前路径的＋上级路径
   如当前ID为3，上级目录的路径为1.2，
   则当前的路径为1.2.3.',
   'user', @CurrentUser, 'table', 'doc_folder', 'column', 'path'
go

/*==============================================================*/
/* Table: doc_history                                           */
/*==============================================================*/
create table doc_history (
   historyId            bigint          identity,
   docId                bigint          not null,
   fileId               bigint          null,
   docName              varchar(128)         not null,
   path                 varchar(128)         not null,
   version              bigint          not null,
   updatetime           datetime             not null,
   mender               varchar(64)          not null,
   constraint PK_DOC_HISTORY primary key nonclustered (historyId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '文档名称',
   'user', @CurrentUser, 'table', 'doc_history', 'column', 'docName'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '路径',
   'user', @CurrentUser, 'table', 'doc_history', 'column', 'path'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '版本',
   'user', @CurrentUser, 'table', 'doc_history', 'column', 'version'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '更新时间',
   'user', @CurrentUser, 'table', 'doc_history', 'column', 'updatetime'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '修改人',
   'user', @CurrentUser, 'table', 'doc_history', 'column', 'mender'
go

/*==============================================================*/
/* Table: doc_privilege                                         */
/*==============================================================*/
create table doc_privilege (
   privilegeId          bigint          identity,
   folderId             bigint          null,
   docId                bigint          null,
   rights               bigint          not null,
   udrId                bigint          null,
   udrName              varchar(128)         null,
   flag                 smallint             not null,
   fdFlag               smallint             not null,
   constraint PK_DOC_PRIVILEGE primary key nonclustered (privilegeId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '文档或目录的权限，只要是针对公共目录下的文档，个人的文档则不需要在这里进行权限的设置
   
   某个目录或文档若没有指定某部门或某个人，即在本表中没有记录，
   则表示可以进行所有的操作',
   'user', @CurrentUser, 'table', 'doc_privilege'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '权限
   文档或目录的读写修改权限
   1=读
   2=修改
   4=删除
   
   权限值可以为上面的值之和
   如：3则代表进行读，修改的操作
   
   
   ',
   'user', @CurrentUser, 'table', 'doc_privilege', 'column', 'rights'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '1=user
   2=deparment
   3=role',
   'user', @CurrentUser, 'table', 'doc_privilege', 'column', 'flag'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '缺省为文件夹权限
   1=文档权限
   0=文件夹权限',
   'user', @CurrentUser, 'table', 'doc_privilege', 'column', 'fdFlag'
go

/*==============================================================*/
/* Table: document                                              */
/*==============================================================*/
create table document (
   docId                bigint          identity,
   docName              varchar(100)         not null,
   content              text                 null,
   createtime           datetime             not null,
   updatetime           datetime             null,
   folderId             bigint          null,
   userId               bigint          null,
   fullname             varchar(32)          not null,
   haveAttach           smallint             null,
   sharedUserIds        varchar(1000)        null,
   sharedUserNames      varchar(1000)        null,
   sharedDepIds         varchar(1000)        null,
   sharedDepNames       varchar(1000)        null,
   sharedRoleIds        varchar(1000)        null,
   sharedRoleNames      varchar(1000)        null,
   isShared             smallint             not null,
   author               varchar(64)          null,
   keywords             varchar(256)         null,
   docType              varchar(64)          null,
   swfPath              varchar(256)         null,
   constraint PK_DOCUMENT primary key nonclustered (docId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '文档',
   'user', @CurrentUser, 'table', 'document'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '内容',
   'user', @CurrentUser, 'table', 'document', 'column', 'content'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '创建时间',
   'user', @CurrentUser, 'table', 'document', 'column', 'createtime'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '主键',
   'user', @CurrentUser, 'table', 'document', 'column', 'userId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '共享员工ID',
   'user', @CurrentUser, 'table', 'document', 'column', 'sharedUserIds'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '共享部门ID',
   'user', @CurrentUser, 'table', 'document', 'column', 'sharedDepIds'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '共享角色ID',
   'user', @CurrentUser, 'table', 'document', 'column', 'sharedRoleIds'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '是否共享',
   'user', @CurrentUser, 'table', 'document', 'column', 'isShared'
go

/*==============================================================*/
/* Table: download_log                                          */
/*==============================================================*/
create table download_log (
   logId                bigint          identity,
   username             varchar(64)          not null,
   userId               bigint          not null,
   fileId               bigint          not null,
   downloadTIme         datetime             not null,
   notes                varchar(1024)        null,
   constraint PK_DOWNLOAD_LOG primary key nonclustered (logId)
)
go

/*==============================================================*/
/* Table: duty                                                  */
/*==============================================================*/
create table duty (
   dutyId               bigint          identity,
   userId               bigint          not null,
   fullname             varchar(32)          not null,
   systemId             bigint          not null,
   startTime            datetime             not null,
   endTime              datetime             null,
   constraint PK_DUTY primary key nonclustered (dutyId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '排班',
   'user', @CurrentUser, 'table', 'duty'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '员工ID',
   'user', @CurrentUser, 'table', 'duty', 'column', 'userId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '员工姓名',
   'user', @CurrentUser, 'table', 'duty', 'column', 'fullname'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '班制ID',
   'user', @CurrentUser, 'table', 'duty', 'column', 'systemId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '开始时间',
   'user', @CurrentUser, 'table', 'duty', 'column', 'startTime'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '结束时间',
   'user', @CurrentUser, 'table', 'duty', 'column', 'endTime'
go

/*==============================================================*/
/* Table: duty_register                                         */
/*==============================================================*/
create table duty_register (
   registerId           bigint          identity,
   registerDate         datetime             not null,
   userId               bigint          not null,
   fullname             varchar(32)          not null,
   regFlag              smallint             not null,
   regMins              bigint          not null,
   reason               varchar(128)         null,
   dayOfWeek            bigint          not null,
   inOffFlag            smallint             not null,
   sectionId            bigint          not null,
   constraint PK_DUTY_REGISTER primary key nonclustered (registerId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '登记时间',
   'user', @CurrentUser, 'table', 'duty_register', 'column', 'registerDate'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '登记人',
   'user', @CurrentUser, 'table', 'duty_register', 'column', 'userId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '登记标识
   1=正常登记（上班，下班）
   2＝迟到
   3=早退
   4＝休息
   5＝旷工
   6=放假
   
   ',
   'user', @CurrentUser, 'table', 'duty_register', 'column', 'regFlag'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '迟到或早退分钟
   正常上班时为0',
   'user', @CurrentUser, 'table', 'duty_register', 'column', 'regMins'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '迟到原因',
   'user', @CurrentUser, 'table', 'duty_register', 'column', 'reason'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '周几
   1=星期日
   ..
   7=日期六',
   'user', @CurrentUser, 'table', 'duty_register', 'column', 'dayOfWeek'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '上下班标识
   1=签到
   2=签退',
   'user', @CurrentUser, 'table', 'duty_register', 'column', 'inOffFlag'
go

/*==============================================================*/
/* Table: duty_section                                          */
/*==============================================================*/
create table duty_section (
   sectionId            bigint          identity,
   sectionName          varchar(64)          not null,
   startSignin          datetime             not null,
   dutyStartTime        datetime             not null,
   endSignin            datetime             not null,
   earlyOffTime         datetime             not null,
   dutyEndTime          datetime             not null,
   signOutTime          datetime             not null,
   constraint PK_DUTY_SECTION primary key nonclustered (sectionId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '班次
   也称为班段，一天可以分为两个或三个班段
   ',
   'user', @CurrentUser, 'table', 'duty_section'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '开始签到',
   'user', @CurrentUser, 'table', 'duty_section', 'column', 'startSignin'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '上班时间',
   'user', @CurrentUser, 'table', 'duty_section', 'column', 'dutyStartTime'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '签到结束时间',
   'user', @CurrentUser, 'table', 'duty_section', 'column', 'endSignin'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '早退计时',
   'user', @CurrentUser, 'table', 'duty_section', 'column', 'earlyOffTime'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '下班时间',
   'user', @CurrentUser, 'table', 'duty_section', 'column', 'dutyEndTime'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '签退结束',
   'user', @CurrentUser, 'table', 'duty_section', 'column', 'signOutTime'
go

/*==============================================================*/
/* Table: duty_system                                           */
/*==============================================================*/
create table duty_system (
   systemId             bigint          identity,
   systemName           varchar(128)         not null,
   systemSetting        varchar(128)         not null,
   systemDesc           varchar(256)         not null,
   isDefault            smallint             not null,
   constraint PK_DUTY_SYSTEM primary key nonclustered (systemId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '轮班制
   为公司设置上班的时间规定
   如规定周一至周五上班，周六周日休息',
   'user', @CurrentUser, 'table', 'duty_system'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '班制名称',
   'user', @CurrentUser, 'table', 'duty_system', 'column', 'systemName'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '班次
   班次的数据结构为：
   如一员工周一至周五上班，上午9：00- 12：00 ，下午 13：30 -18:00
   
   基数据结构为：
   1|2,1|2,1|2,1|2,1|2,-,-
   -代表正常休息日
   1|2代表为一天上两个班次，1代表为班次表1的记录
   ',
   'user', @CurrentUser, 'table', 'duty_system', 'column', 'systemSetting'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '班次描述',
   'user', @CurrentUser, 'table', 'duty_system', 'column', 'systemDesc'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '是否缺省
   1＝缺省
   0＝非缺省',
   'user', @CurrentUser, 'table', 'duty_system', 'column', 'isDefault'
go

/*==============================================================*/
/* Table: emp_profile                                           */
/*==============================================================*/
create table emp_profile (
   profileId            bigint          identity,
   profileNo            varchar(100)         not null,
   userId               bigint          not null,
   fullname             varchar(64)          not null,
   address              varchar(128)         null,
   birthday             datetime             null,
   homeZip              varchar(32)          null,
   sex                  varchar(32)          null,
   marriage             varchar(32)          null,
   designation          varchar(64)          null,
   position             varchar(128)         not null,
   phone                varchar(64)          null,
   mobile               varchar(64)          null,
   openBank             varchar(100)         null,
   bankNo               varchar(100)         null,
   qq                   varchar(64)          null,
   email                varchar(128)         null,
   hobby                varchar(300)         null,
   religion             varchar(100)         null,
   party                varchar(100)         null,
   nationality          varchar(100)         null,
   race                 varchar(100)         null,
   birthPlace           varchar(128)         null,
   eduDegree            varchar(100)         null,
   eduMajor             varchar(100)         null,
   eduCollege           varchar(128)         null,
   startWorkDate        datetime             null,
   eduCase              varchar(2048)        null,
   awardPunishCase      varchar(2048)        null,
   trainingCase         varchar(2048)        null,
   workCase             varchar(2048)        null,
   idCard               varchar(64)          null,
   photo                varchar(128)         null,
   standardMiNo         varchar(100)         null,
   standardMoney        numeric(18,2)        null,
   standardName         varchar(128)         null,
   standardId           bigint          null,
   jobId                bigint          null,
   creator              varchar(64)          null,
   createtime           datetime             null,
   checkName            varchar(128)         null,
   checktime            datetime             null,
   opprovalOpinion      varchar(1024)        null,
   approvalStatus       smallint             null default 0,
   memo                 varchar(1024)        null,
   depName              varchar(100)         null,
   depId                bigint          null,
   delFlag              smallint             not null default 0,
   constraint PK_EMP_PROFILE primary key nonclustered (profileId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '员工档案',
   'user', @CurrentUser, 'table', 'emp_profile'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '档案编号',
   'user', @CurrentUser, 'table', 'emp_profile', 'column', 'profileNo'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '员工姓名',
   'user', @CurrentUser, 'table', 'emp_profile', 'column', 'fullname'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '家庭地址',
   'user', @CurrentUser, 'table', 'emp_profile', 'column', 'address'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '出生日期',
   'user', @CurrentUser, 'table', 'emp_profile', 'column', 'birthday'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '家庭邮编',
   'user', @CurrentUser, 'table', 'emp_profile', 'column', 'homeZip'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '婚姻状况
   已婚
   未婚',
   'user', @CurrentUser, 'table', 'emp_profile', 'column', 'marriage'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '电话号码',
   'user', @CurrentUser, 'table', 'emp_profile', 'column', 'phone'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '手机号码',
   'user', @CurrentUser, 'table', 'emp_profile', 'column', 'mobile'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '开户银行',
   'user', @CurrentUser, 'table', 'emp_profile', 'column', 'openBank'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '银行账号',
   'user', @CurrentUser, 'table', 'emp_profile', 'column', 'bankNo'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'QQ号码',
   'user', @CurrentUser, 'table', 'emp_profile', 'column', 'qq'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '电子邮箱',
   'user', @CurrentUser, 'table', 'emp_profile', 'column', 'email'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '爱好',
   'user', @CurrentUser, 'table', 'emp_profile', 'column', 'hobby'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '宗教信仰',
   'user', @CurrentUser, 'table', 'emp_profile', 'column', 'religion'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '政治面貌',
   'user', @CurrentUser, 'table', 'emp_profile', 'column', 'party'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '国籍',
   'user', @CurrentUser, 'table', 'emp_profile', 'column', 'nationality'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '民族',
   'user', @CurrentUser, 'table', 'emp_profile', 'column', 'race'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '出生地',
   'user', @CurrentUser, 'table', 'emp_profile', 'column', 'birthPlace'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '学历',
   'user', @CurrentUser, 'table', 'emp_profile', 'column', 'eduDegree'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '专业',
   'user', @CurrentUser, 'table', 'emp_profile', 'column', 'eduMajor'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '毕业院校',
   'user', @CurrentUser, 'table', 'emp_profile', 'column', 'eduCollege'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '参加工作时间',
   'user', @CurrentUser, 'table', 'emp_profile', 'column', 'startWorkDate'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '教育背景',
   'user', @CurrentUser, 'table', 'emp_profile', 'column', 'eduCase'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '奖惩情况',
   'user', @CurrentUser, 'table', 'emp_profile', 'column', 'awardPunishCase'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '培训情况',
   'user', @CurrentUser, 'table', 'emp_profile', 'column', 'trainingCase'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '工作经历',
   'user', @CurrentUser, 'table', 'emp_profile', 'column', 'workCase'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '身份证号',
   'user', @CurrentUser, 'table', 'emp_profile', 'column', 'idCard'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '照片',
   'user', @CurrentUser, 'table', 'emp_profile', 'column', 'photo'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '薪酬标准编号',
   'user', @CurrentUser, 'table', 'emp_profile', 'column', 'standardMiNo'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '薪酬标准金额',
   'user', @CurrentUser, 'table', 'emp_profile', 'column', 'standardMoney'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '薪酬标准单名称',
   'user', @CurrentUser, 'table', 'emp_profile', 'column', 'standardName'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '薪酬标准单编号',
   'user', @CurrentUser, 'table', 'emp_profile', 'column', 'standardId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '建档人',
   'user', @CurrentUser, 'table', 'emp_profile', 'column', 'creator'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '建档时间',
   'user', @CurrentUser, 'table', 'emp_profile', 'column', 'createtime'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '审核人',
   'user', @CurrentUser, 'table', 'emp_profile', 'column', 'checkName'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '审核时间',
   'user', @CurrentUser, 'table', 'emp_profile', 'column', 'checktime'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '核审状态
   0=未审批
   1=通过审核
   2=未通过审核',
   'user', @CurrentUser, 'table', 'emp_profile', 'column', 'approvalStatus'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '备注',
   'user', @CurrentUser, 'table', 'emp_profile', 'column', 'memo'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '所属部门或公司',
   'user', @CurrentUser, 'table', 'emp_profile', 'column', 'depName'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '所属部门Id',
   'user', @CurrentUser, 'table', 'emp_profile', 'column', 'depId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '删除状态
   0=未删除
   1=删除',
   'user', @CurrentUser, 'table', 'emp_profile', 'column', 'delFlag'
go

/*==============================================================*/
/* Table: errands_register                                      */
/*==============================================================*/
create table errands_register (
   dateId               bigint          identity,
   userId               bigint          not null,
   descp                varchar(500)         not null,
   startTime            datetime             not null,
   endTime              datetime             not null,
   approvalId           bigint          null,
   approvalName         varchar(128)         null,
   status               smallint             not null,
   approvalOption       varchar(500)         null,
   flag                 smallint             null,
   runId                bigint          null,
   constraint PK_ERRANDS_REGISTER primary key nonclustered (dateId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '请假、加班、外出登记',
   'user', @CurrentUser, 'table', 'errands_register'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '外出/加班人员',
   'user', @CurrentUser, 'table', 'errands_register', 'column', 'userId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '描述',
   'user', @CurrentUser, 'table', 'errands_register', 'column', 'descp'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '开始日期',
   'user', @CurrentUser, 'table', 'errands_register', 'column', 'startTime'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '结束日期',
   'user', @CurrentUser, 'table', 'errands_register', 'column', 'endTime'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '审批人ID',
   'user', @CurrentUser, 'table', 'errands_register', 'column', 'approvalId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '审批人',
   'user', @CurrentUser, 'table', 'errands_register', 'column', 'approvalName'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '审批状态
   0=未审批
   1=通过审批
   2=不通过审批',
   'user', @CurrentUser, 'table', 'errands_register', 'column', 'status'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '审批意见',
   'user', @CurrentUser, 'table', 'errands_register', 'column', 'approvalOption'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '标识
   0=加班
   1=请假
   2=外出',
   'user', @CurrentUser, 'table', 'errands_register', 'column', 'flag'
go

/*==============================================================*/
/* Table: field_rights                                          */
/*==============================================================*/
create table field_rights (
   rightId              bigint          identity,
   mappingId            bigint          not null,
   fieldId              bigint          not null,
   taskName             varchar(128)         not null,
   readWrite            smallint             not null default 0,
   constraint PK_FIELD_RIGHTS primary key nonclustered (rightId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '隐藏读写权限
   0=隐藏
   1=读
   2=写',
   'user', @CurrentUser, 'table', 'field_rights', 'column', 'readWrite'
go

/*==============================================================*/
/* Table: file_attach                                           */
/*==============================================================*/
create table file_attach (
   fileId               bigint          identity,
   fileName             varchar(128)         not null,
   filePath             varchar(128)         not null,
   createtime           datetime             not null,
   ext                  varchar(32)          null,
   fileType             varchar(128)         not null,
   note                 varchar(1024)        null,
   creatorId            bigint          null,
   creator              varchar(32)          not null,
   totalBytes           bigint          null default 0,
   delFlag              smallint             null,
   proTypeId            bigint          null,
   constraint PK_FILE_ATTACH primary key nonclustered (fileId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '附件',
   'user', @CurrentUser, 'table', 'file_attach'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '文件名',
   'user', @CurrentUser, 'table', 'file_attach', 'column', 'fileName'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '文件路径',
   'user', @CurrentUser, 'table', 'file_attach', 'column', 'filePath'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '创建时间',
   'user', @CurrentUser, 'table', 'file_attach', 'column', 'createtime'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '扩展名',
   'user', @CurrentUser, 'table', 'file_attach', 'column', 'ext'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '附件类型
   如：邮件附件',
   'user', @CurrentUser, 'table', 'file_attach', 'column', 'fileType'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '说明',
   'user', @CurrentUser, 'table', 'file_attach', 'column', 'note'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '上传者',
   'user', @CurrentUser, 'table', 'file_attach', 'column', 'creator'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '1=已删除
   0=删除',
   'user', @CurrentUser, 'table', 'file_attach', 'column', 'delFlag'
go

/*==============================================================*/
/* Table: fixed_assets                                          */
/*==============================================================*/
create table fixed_assets (
   assetsId             bigint          identity,
   assetsNo             varchar(128)         null,
   assetsName           varchar(128)         not null,
   model                varchar(64)          null,
   assetsTypeId         bigint          not null,
   manufacturer         varchar(64)          null,
   manuDate             datetime             null,
   buyDate              datetime             not null,
   beDep                varchar(64)          not null,
   custodian            varchar(32)          null,
   notes                varchar(500)         null,
   remainValRate        numeric(18,6)        not null,
   depreTypeId          bigint          not null,
   startDepre           datetime             null,
   intendTerm           numeric(18,2)        null,
   intendWorkGross      numeric(18,2)        null,
   workGrossUnit        varchar(128)         null,
   assetValue           numeric(18,4)        not null,
   assetCurValue        numeric(18,4)        not null,
   depreRate            numeric(18,2)        null,
   defPerWorkGross      numeric(18,2)        null,
   constraint PK_FIXED_ASSETS primary key nonclustered (assetsId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '当折旧的方法选择用工作量法进行计算时，才需要填写',
   'user', @CurrentUser, 'table', 'fixed_assets', 'column', 'intendWorkGross'
go

/*==============================================================*/
/* Table: form_def                                              */
/*==============================================================*/
create table form_def (
   formDefId            bigint          identity,
   formTitle            varchar(128)         not null,
   formDesp             text                 null,
   defHtml              text                 null,
   status               smallint             not null,
   formType             smallint             null,
   isDefault            smallint             null,
   isGen                smallint             null default 0,
   constraint PK_FORM_DEF primary key nonclustered (formDefId),
   constraint AK_FD_FORMNAME_FORM_DEF unique (formTitle)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '表单ID',
   'user', @CurrentUser, 'table', 'form_def', 'column', 'formDefId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '表单标题',
   'user', @CurrentUser, 'table', 'form_def', 'column', 'formTitle'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '0=草稿状态
   1=正式状态',
   'user', @CurrentUser, 'table', 'form_def', 'column', 'status'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '表单类型
   1=单表
   2=主从表
   3=多表',
   'user', @CurrentUser, 'table', 'form_def', 'column', 'formType'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '是否缺省',
   'user', @CurrentUser, 'table', 'form_def', 'column', 'isDefault'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '1=已生成
   0=未生成',
   'user', @CurrentUser, 'table', 'form_def', 'column', 'isGen'
go

/*==============================================================*/
/* Table: form_def_mapping                                      */
/*==============================================================*/
create table form_def_mapping (
   mappingId            bigint          identity,
   formDefId            bigint          null,
   defId                bigint          null,
   versionNo            bigint          not null default 0,
   deployId             varchar(128)         not null,
   useTemplate          smallint             null default 0,
   constraint PK_FORM_DEF_MAPPING primary key nonclustered (mappingId),
   constraint AK_UK_DEPLOYID_FORM_DEF unique (deployId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '表单ID',
   'user', @CurrentUser, 'table', 'form_def_mapping', 'column', 'formDefId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '发布ID',
   'user', @CurrentUser, 'table', 'form_def_mapping', 'column', 'deployId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '1=使用模板表单
   0=使用普通在线表单',
   'user', @CurrentUser, 'table', 'form_def_mapping', 'column', 'useTemplate'
go

/*==============================================================*/
/* Table: form_field                                            */
/*==============================================================*/
create table form_field (
   fieldId              bigint          identity,
   tableId              bigint          null,
   fieldName            varchar(128)         not null,
   fieldLabel           varchar(128)         null,
   fieldType            varchar(128)         not null,
   isRequired           smallint             null,
   fieldSize            bigint          null,
   fieldDscp            varchar(1024)        null,
   isPrimary            smallint             null,
   foreignKey           varchar(64)          null,
   foreignTable         varchar(64)          null,
   isList               smallint             null default 1,
   isQuery              smallint             null default 1,
   showFormat           varchar(256)         null,
   isFlowTitle          smallint             null,
   isDesignShow         smallint             null,
   status               smallint             null,
   decimalLen           bigint,
   controlType          bigint,
   constraint PK_FORM_FIELD primary key nonclustered (fieldId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '显示格式
   如日期显示yyyy-MM-dd
   数字 如 000.00',
   'user', @CurrentUser, 'table', 'form_field', 'column', 'showFormat'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '1=是
   0=否',
   'user', @CurrentUser, 'table', 'form_field', 'column', 'isFlowTitle'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '1=设计的可视化
   2=设计的不可视化
   3=手工加上',
   'user', @CurrentUser, 'table', 'form_field', 'column', 'isDesignShow'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '1=删除
   0=未删除',
   'user', @CurrentUser, 'table', 'form_field', 'column', 'status'
go

/*==============================================================*/
/* Table: form_table                                            */
/*==============================================================*/
create table form_table (
   tableId              bigint          identity,
   formDefId            bigint          null,
   tableName            varchar(128)         not null,
   tableKey             varchar(128)         not null,
   isMain               smallint             null,
   constraint PK_FORM_TABLE primary key nonclustered (tableId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '表单ID',
   'user', @CurrentUser, 'table', 'form_table', 'column', 'formDefId'
go

/*==============================================================*/
/* Table: form_template                                         */
/*==============================================================*/
create table form_template (
   templateId           bigint          identity,
   mappingId            bigint          null,
   nodeName             varchar(128)         not null,
   tempContent          text                 null,
   extDef               text                 null,
   formUrl              varchar(256)         null,
   tempType             smallint             null,
   constraint PK_FORM_TEMPLATE primary key nonclustered (templateId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '映射ID',
   'user', @CurrentUser, 'table', 'form_template', 'column', 'mappingId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '节点名称',
   'user', @CurrentUser, 'table', 'form_template', 'column', 'nodeName'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '模板内容',
   'user', @CurrentUser, 'table', 'form_template', 'column', 'tempContent'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '1=EXT模板
   2=URL模板',
   'user', @CurrentUser, 'table', 'form_template', 'column', 'tempType'
go

/*==============================================================*/
/* Table: fun_url                                               */
/*==============================================================*/
create table fun_url (
   urlId                bigint          identity,
   functionId           bigint          not null,
   urlPath              varchar(128)         not null,
   constraint PK_FUN_URL primary key nonclustered (urlId)
)
go

/*==============================================================*/
/* Table: global_type                                           */
/*==============================================================*/
create table global_type (
   proTypeId            bigint          identity,
   typeName             varchar(128)         not null,
   path                 varchar(64)          null,
   depth                bigint          not null,
   parentId             bigint          null,
   nodeKey              varchar(64)          not null,
   catKey               varchar(64)          not null,
   sn                   bigint          not null,
   userId               bigint          null,
   depId                bigint          null,
   constraint PK_GLOBAL_TYPE primary key nonclustered (proTypeId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '总分类表
   
   用于显示树层次结构的分类
   可以允许任何层次结构',
   'user', @CurrentUser, 'table', 'global_type'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '名称',
   'user', @CurrentUser, 'table', 'global_type', 'column', 'typeName'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '路径',
   'user', @CurrentUser, 'table', 'global_type', 'column', 'path'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '层次',
   'user', @CurrentUser, 'table', 'global_type', 'column', 'depth'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '父节点',
   'user', @CurrentUser, 'table', 'global_type', 'column', 'parentId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '节点的分类Key',
   'user', @CurrentUser, 'table', 'global_type', 'column', 'nodeKey'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '节点分类的Key，如产品分类Key为PT',
   'user', @CurrentUser, 'table', 'global_type', 'column', 'catKey'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '序号',
   'user', @CurrentUser, 'table', 'global_type', 'column', 'sn'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '所属用户
   当为空则代表为公共分类',
   'user', @CurrentUser, 'table', 'global_type', 'column', 'userId'
go

/*==============================================================*/
/* Table: goods_apply                                           */
/*==============================================================*/
create table goods_apply (
   applyId              bigint          identity,
   goodsId              bigint          not null,
   applyDate            datetime             not null,
   applyNo              varchar(128)         not null,
   useCounts            bigint          not null,
   userId               bigint          not null,
   proposer             varchar(32)          not null,
   notes                varchar(500)         null,
   approvalStatus       smallint             not null,
   constraint PK_GOODS_APPLY primary key nonclustered (applyId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '办公用品出库',
   'user', @CurrentUser, 'table', 'goods_apply'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '申请号,按系统时间产生，如GA20091002-0001',
   'user', @CurrentUser, 'table', 'goods_apply', 'column', 'applyNo'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '审批状态
   1=通过审批
   0=未审批
   ',
   'user', @CurrentUser, 'table', 'goods_apply', 'column', 'approvalStatus'
go

/*==============================================================*/
/* Table: hire_issue                                            */
/*==============================================================*/
create table hire_issue (
   hireId               bigint          identity,
   title                varchar(128)         not null,
   startDate            datetime             not null,
   endDate              datetime             not null,
   hireCount            bigint          not null,
   jobName              varchar(128)         not null,
   jobCondition         varchar(1024)        null,
   regFullname          varchar(128)         not null,
   regDate              datetime             not null,
   modifyFullname       varchar(32)          null,
   modifyDate           datetime             null,
   checkFullname        varchar(32)          null,
   checkOpinion         varchar(512)         null,
   checkDate            datetime             null,
   status               smallint             not null,
   memo                 varchar(1024)        null,
   jobId                bigint          null,
   constraint PK_HIRE_ISSUE primary key nonclustered (hireId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '招聘发布',
   'user', @CurrentUser, 'table', 'hire_issue'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '招聘信息标题',
   'user', @CurrentUser, 'table', 'hire_issue', 'column', 'title'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '开始时间',
   'user', @CurrentUser, 'table', 'hire_issue', 'column', 'startDate'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '结束时间',
   'user', @CurrentUser, 'table', 'hire_issue', 'column', 'endDate'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '招聘人数',
   'user', @CurrentUser, 'table', 'hire_issue', 'column', 'hireCount'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '职位名称',
   'user', @CurrentUser, 'table', 'hire_issue', 'column', 'jobName'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '招聘要求(条件)',
   'user', @CurrentUser, 'table', 'hire_issue', 'column', 'jobCondition'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '登记人姓名',
   'user', @CurrentUser, 'table', 'hire_issue', 'column', 'regFullname'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '登记时间',
   'user', @CurrentUser, 'table', 'hire_issue', 'column', 'regDate'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '变更人姓名',
   'user', @CurrentUser, 'table', 'hire_issue', 'column', 'modifyFullname'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '变更时间',
   'user', @CurrentUser, 'table', 'hire_issue', 'column', 'modifyDate'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '审核人姓名',
   'user', @CurrentUser, 'table', 'hire_issue', 'column', 'checkFullname'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '审核意见',
   'user', @CurrentUser, 'table', 'hire_issue', 'column', 'checkOpinion'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '审批时间',
   'user', @CurrentUser, 'table', 'hire_issue', 'column', 'checkDate'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '状态
   1=通过审核
   0=未审核
   2=审核不通过',
   'user', @CurrentUser, 'table', 'hire_issue', 'column', 'status'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '备注',
   'user', @CurrentUser, 'table', 'hire_issue', 'column', 'memo'
go

/*==============================================================*/
/* Table: holiday_record                                        */
/*==============================================================*/
create table holiday_record (
   recordId             bigint          identity,
   startTime            datetime             not null,
   endTime              datetime             not null,
   descp                varchar(512)         null,
   fullname             varchar(32)          null,
   userId               bigint          null,
   isAll                smallint             not null,
   constraint PK_HOLIDAY_RECORD primary key nonclustered (recordId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '放假记录',
   'user', @CurrentUser, 'table', 'holiday_record'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '开始日期',
   'user', @CurrentUser, 'table', 'holiday_record', 'column', 'startTime'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '结束日期',
   'user', @CurrentUser, 'table', 'holiday_record', 'column', 'endTime'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '假期描述',
   'user', @CurrentUser, 'table', 'holiday_record', 'column', 'descp'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '用户名',
   'user', @CurrentUser, 'table', 'holiday_record', 'column', 'fullname'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '所属用户
   若为某员工的假期，则为员工自己的id',
   'user', @CurrentUser, 'table', 'holiday_record', 'column', 'userId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '是否为全公司假期
   1=是
   0=否',
   'user', @CurrentUser, 'table', 'holiday_record', 'column', 'isAll'
go

/*==============================================================*/
/* Table: in_message                                            */
/*==============================================================*/
create table in_message (
   receiveId            bigint          identity,
   messageId            bigint          null,
   userId               bigint          null,
   readFlag             smallint             not null,
   delFlag              smallint             not null,
   userFullname         varchar(32)          not null,
   constraint PK_IN_MESSAGE primary key nonclustered (receiveId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '主键',
   'user', @CurrentUser, 'table', 'in_message', 'column', 'userId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '1=has red
   0=unread',
   'user', @CurrentUser, 'table', 'in_message', 'column', 'readFlag'
go

/*==============================================================*/
/* Table: in_stock                                              */
/*==============================================================*/
create table in_stock (
   buyId                bigint          identity,
   goodsId              bigint          not null,
   providerName         varchar(128)         null,
   stockNo              varchar(128)         not null,
   price                numeric(18,2)        null,
   inCounts             bigint          null,
   amount               numeric(18,2)        not null,
   inDate               datetime             not null,
   buyer                varchar(128)         null,
   constraint PK_IN_STOCK primary key nonclustered (buyId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '办公用品入库需要同时更新办公用品表的库存',
   'user', @CurrentUser, 'table', 'in_stock'
go

/*==============================================================*/
/* Table: index_display                                         */
/*==============================================================*/
create table index_display (
   indexId              bigint          identity,
   portalId             varchar(64)          not null,
   userId               bigint          not null,
   colNum               bigint          not null,
   rowNo                bigint          not null,
   constraint PK_INDEX_DISPLAY primary key nonclustered (indexId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '每个员工可以设置自己的显示方式',
   'user', @CurrentUser, 'table', 'index_display'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Portal ID',
   'user', @CurrentUser, 'table', 'index_display', 'column', 'portalId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '用户ID',
   'user', @CurrentUser, 'table', 'index_display', 'column', 'userId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '列号',
   'user', @CurrentUser, 'table', 'index_display', 'column', 'colNum'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '行号',
   'user', @CurrentUser, 'table', 'index_display', 'column', 'rowNo'
go

/*==============================================================*/
/* Table: job_change                                            */
/*==============================================================*/
create table job_change (
   changeId             bigint          identity,
   profileId            bigint          not null,
   profileNo            varchar(64)          not null,
   userName             varchar(64)          null,
   orgJobName           varchar(64)          not null,
   newJobName           varchar(64)          not null,
   orgStandardId        bigint          null,
   orgStandardNo        varchar(64)          null,
   orgStandardName      varchar(64)          null,
   orgDepId             bigint          null,
   orgDepName           varchar(128)         null,
   orgTotalMoney        numeric(18,2)        null,
   newStandardId        bigint          null,
   newStandardNo        varchar(64)          null,
   newStandardName      varchar(64)          null,
   newDepId             bigint          null,
   newDepName           varchar(128)         null,
   newTotalMoney        numeric(18,2)        null,
   changeReason         varchar(1024)        null,
   regTime              datetime             null,
   regName              varchar(64)          null,
   checkName            varchar(64)          null,
   checkTime            datetime             null,
   checkOpinion         varchar(512)         null,
   status               smallint             null,
   memo                 varchar(1024)        null,
   orgJobId             bigint          null,
   newJobId             bigint          null,
   constraint PK_JOB_CHANGE primary key nonclustered (changeId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '原薪酬标准单ID',
   'user', @CurrentUser, 'table', 'job_change', 'column', 'orgStandardId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '状态
   
   -1=草稿
   0=提交审批
   1=通过审批
   2=未通过审批
   ',
   'user', @CurrentUser, 'table', 'job_change', 'column', 'status'
go

/*==============================================================*/
/* Table: mail                                                  */
/*==============================================================*/
create table mail (
   mailId               bigint          identity,
   sender               varchar(32)          not null,
   senderId             bigint          not null,
   importantFlag        smallint             not null,
   sendTime             datetime             not null,
   content              text                 not null,
   subject              varchar(256)         not null,
   copyToNames          varchar(2000)        null,
   copyToIDs            varchar(2000)        null,
   recipientNames       varchar(2000)        not null,
   recipientIDs         varchar(2000)        not null,
   mailStatus           smallint             not null,
   fileIds              varchar(500)         null,
   filenames            varchar(500)         null,
   constraint PK_MAIL primary key nonclustered (mailId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '邮件',
   'user', @CurrentUser, 'table', 'mail'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '1=一般
   2=重要
   3=非常重要',
   'user', @CurrentUser, 'table', 'mail', 'column', 'importantFlag'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '邮件内容',
   'user', @CurrentUser, 'table', 'mail', 'column', 'content'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '邮件标题',
   'user', @CurrentUser, 'table', 'mail', 'column', 'subject'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '抄送人姓名列表',
   'user', @CurrentUser, 'table', 'mail', 'column', 'copyToNames'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '抄送人ID列表
   用'',''分开',
   'user', @CurrentUser, 'table', 'mail', 'column', 'copyToIDs'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '收件人姓名列表',
   'user', @CurrentUser, 'table', 'mail', 'column', 'recipientNames'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '收件人ID列表
   用'',''分隔',
   'user', @CurrentUser, 'table', 'mail', 'column', 'recipientIDs'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '邮件状态
   1=正式邮件
   0=草稿邮件',
   'user', @CurrentUser, 'table', 'mail', 'column', 'mailStatus'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '附件Ids，多个附件的ID，通过,分割',
   'user', @CurrentUser, 'table', 'mail', 'column', 'fileIds'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '附件名称列表，通过,进行分割',
   'user', @CurrentUser, 'table', 'mail', 'column', 'filenames'
go

/*==============================================================*/
/* Table: mail_attach                                           */
/*==============================================================*/
create table mail_attach (
   mailId               bigint          not null,
   fileId               bigint          not null,
   constraint PK_MAIL_ATTACH primary key nonclustered (mailId, fileId)
)
go

/*==============================================================*/
/* Table: mail_box                                              */
/*==============================================================*/
create table mail_box (
   boxId                bigint          identity,
   mailId               bigint          not null,
   folderId             bigint          not null,
   userId               bigint          null,
   sendTime             datetime             not null,
   delFlag              smallint             not null,
   readFlag             smallint             not null,
   replyFlag            smallint             not null,
   note                 varchar(256)         null,
   constraint PK_MAIL_BOX primary key nonclustered (boxId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '收件箱',
   'user', @CurrentUser, 'table', 'mail_box'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '主键',
   'user', @CurrentUser, 'table', 'mail_box', 'column', 'userId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'del=1则代表删除',
   'user', @CurrentUser, 'table', 'mail_box', 'column', 'delFlag'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'note',
   'user', @CurrentUser, 'table', 'mail_box', 'column', 'note'
go

/*==============================================================*/
/* Table: mail_folder                                           */
/*==============================================================*/
create table mail_folder (
   folderId             bigint          identity,
   userId               bigint          null,
   folderName           varchar(128)         not null,
   parentId             bigint          null,
   depLevel             bigint          not null,
   path                 varchar(256)         null,
   isPublic             smallint             not null,
   folderType           smallint             not null,
   constraint PK_MAIL_FOLDER primary key nonclustered (folderId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '文件夹编号',
   'user', @CurrentUser, 'table', 'mail_folder', 'column', 'folderId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '主键',
   'user', @CurrentUser, 'table', 'mail_folder', 'column', 'userId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '文件夹名称',
   'user', @CurrentUser, 'table', 'mail_folder', 'column', 'folderName'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '父目录',
   'user', @CurrentUser, 'table', 'mail_folder', 'column', 'parentId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '目录层',
   'user', @CurrentUser, 'table', 'mail_folder', 'column', 'depLevel'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '1=表示共享，则所有的员工均可以使用该文件夹
   0=私人文件夹',
   'user', @CurrentUser, 'table', 'mail_folder', 'column', 'isPublic'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '文件夹类型
   1=收信箱
   2=发信箱
   3=草稿箱
   4=删除箱
   10=其他',
   'user', @CurrentUser, 'table', 'mail_folder', 'column', 'folderType'
go

/*==============================================================*/
/* Table: meeting                                               */
/*==============================================================*/
create table meeting (
   mettingId            bigint          identity,
   holdTime             datetime             null,
   holdLocation         varchar(128)         null,
   meetingName          varchar(128)         null,
   attendUsers          varchar(128)         null,
   holdDep              varchar(128)         null,
   holdDepId            bigint          null,
   shortDesc            varchar(256)         null,
   isFeedback           smallint             not null,
   summary              text                 null,
   constraint PK_MEETING primary key nonclustered (mettingId)
)
go

/*==============================================================*/
/* Table: meeting_attend                                        */
/*==============================================================*/
create table meeting_attend (
   attendId             bigint          identity,
   mettingId            bigint          not null,
   userName             varchar(64)          null,
   userId               bigint          null,
   depName              varchar(100)         null,
   depId                bigint          null,
   attendType           smallint             not null default 0,
   feedback             varchar(1024)        null,
   signTime             datetime             null,
   signName             varchar(32)          not null,
   constraint PK_MEETING_ATTEND primary key nonclustered (attendId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '会议参与部门或人员',
   'user', @CurrentUser, 'table', 'meeting_attend'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '参与类型
   0=user
   1=department',
   'user', @CurrentUser, 'table', 'meeting_attend', 'column', 'attendType'
go

/*==============================================================*/
/* Table: meeting_file                                          */
/*==============================================================*/
create table meeting_file (
   mettingId            bigint          not null,
   fileId               bigint          not null,
   constraint PK_MEETING_FILE primary key nonclustered (mettingId, fileId)
)
go

/*==============================================================*/
/* Table: news                                                  */
/*==============================================================*/
create table news (
   newsId               bigint          identity,
   sectionId            bigint          null,
   subjectIcon          varchar(128)         null,
   subject              varchar(128)         not null,
   author               varchar(32)          not null,
   createtime           datetime             not null,
   expTime              datetime             null,
   replyCounts          bigint          null,
   viewCounts           bigint          null,
   issuer               varchar(32)          not null,
   content              text                 not null,
   updateTime           datetime             null,
   status               smallint             not null,
   isDeskImage          smallint             null,
   isNotice             smallint             null,
   sn                   bigint          null,
   orgIds               varchar(200),
   orgNames             varchar(1000),
   constraint PK_NEWS primary key nonclustered (newsId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '新闻',
   'user', @CurrentUser, 'table', 'news'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'ID',
   'user', @CurrentUser, 'table', 'news', 'column', 'newsId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '新闻标题',
   'user', @CurrentUser, 'table', 'news', 'column', 'subject'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '作者',
   'user', @CurrentUser, 'table', 'news', 'column', 'author'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '创建时间',
   'user', @CurrentUser, 'table', 'news', 'column', 'createtime'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '浏览数',
   'user', @CurrentUser, 'table', 'news', 'column', 'viewCounts'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '内容',
   'user', @CurrentUser, 'table', 'news', 'column', 'content'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '
   0=待审核
   1=审核通过',
   'user', @CurrentUser, 'table', 'news', 'column', 'status'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '是否为桌面新闻',
   'user', @CurrentUser, 'table', 'news', 'column', 'isDeskImage'
go

/*==============================================================*/
/* Table: news_comment                                          */
/*==============================================================*/
create table news_comment (
   commentId            bigint          identity,
   content              varchar(500)         not null,
   createtime           datetime             not null,
   fullname             varchar(32)          not null,
   userId               bigint          not null,
   newsId               bigint          null,
   constraint PK_NEWS_COMMENT primary key nonclustered (commentId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'ID',
   'user', @CurrentUser, 'table', 'news_comment', 'column', 'newsId'
go

/*==============================================================*/
/* Table: office_goods                                          */
/*==============================================================*/
create table office_goods (
   goodsId              bigint          identity,
   typeId               bigint          not null,
   goodsName            varchar(128)         not null,
   goodsNo              varchar(128)         not null,
   specifications       varchar(256)         not null,
   unit                 varchar(64)          not null,
   isWarning            smallint             not null,
   notes                varchar(500)         null,
   stockCounts          bigint          not null,
   warnCounts           bigint          not null,
   constraint PK_OFFICE_GOODS primary key nonclustered (goodsId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '办公用品',
   'user', @CurrentUser, 'table', 'office_goods'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '所属分类',
   'user', @CurrentUser, 'table', 'office_goods', 'column', 'typeId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '物品名称',
   'user', @CurrentUser, 'table', 'office_goods', 'column', 'goodsName'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '编号',
   'user', @CurrentUser, 'table', 'office_goods', 'column', 'goodsNo'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '规格',
   'user', @CurrentUser, 'table', 'office_goods', 'column', 'specifications'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '计量单位',
   'user', @CurrentUser, 'table', 'office_goods', 'column', 'unit'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '是否启用库存警示',
   'user', @CurrentUser, 'table', 'office_goods', 'column', 'isWarning'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '备注',
   'user', @CurrentUser, 'table', 'office_goods', 'column', 'notes'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '库存总数',
   'user', @CurrentUser, 'table', 'office_goods', 'column', 'stockCounts'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '最低库存数',
   'user', @CurrentUser, 'table', 'office_goods', 'column', 'warnCounts'
go

/*==============================================================*/
/* Table: office_goods_type                                     */
/*==============================================================*/
create table office_goods_type (
   typeId               bigint          identity,
   typeName             varchar(128)         not null,
   constraint PK_OFFICE_GOODS_TYPE primary key nonclustered (typeId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '办公用品类型',
   'user', @CurrentUser, 'table', 'office_goods_type'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '分类名称',
   'user', @CurrentUser, 'table', 'office_goods_type', 'column', 'typeName'
go

/*==============================================================*/
/* Table: organization                                          */
/*==============================================================*/
create table organization (
   org_id               bigint          identity,
   dem_id               bigint          null,
   org_name             varchar(128)         not null,
   org_desc             varchar(500)         null,
   org_sup_id           bigint          null,
   path                 varchar(128)         null,
   depth                int                  null,
   org_type             smallint             null,
   creator_id           bigint          null,
   createtime           datetime             null,
   update_id            bigint          null,
   updatetime           datetime             null,
   sn                   int                  null,
   constraint PK_ORGANIZATION primary key nonclustered (org_id)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '1=公司
   2=部门
   3=其他组织',
   'user', @CurrentUser, 'table', 'organization', 'column', 'org_type'
go

/*==============================================================*/
/* Table: out_mail                                              */
/*==============================================================*/
create table out_mail (
   mailId               bigint          identity,
   uidNo                varchar(512)         null,
   setId                bigint          not null,
   userId               bigint          null,
   folderId             bigint          null,
   title                varchar(512)         null,
   content              text                 null,
   senderAddresses      varchar(128)         not null,
   senderName           varchar(128)         null,
   receiverAddresses    text                 not null,
   receiverNames        text                 null,
   cCAddresses          text                 null,
   cCNames              text                 null,
   bCCAddresses         text                 null,
   bCCAnames            text                 null,
   mailDate             datetime             not null,
   fileIds              varchar(512)         null,
   fileNames            varchar(512)         null,
   readFlag             smallint             not null default 0,
   replyFlag            smallint             not null default 0,
   constraint PK_OUT_MAIL primary key nonclustered (mailId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '文件夹编号',
   'user', @CurrentUser, 'table', 'out_mail', 'column', 'folderId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '主题',
   'user', @CurrentUser, 'table', 'out_mail', 'column', 'title'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '内容',
   'user', @CurrentUser, 'table', 'out_mail', 'column', 'content'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '发件人地址',
   'user', @CurrentUser, 'table', 'out_mail', 'column', 'senderAddresses'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '发件人地址别名',
   'user', @CurrentUser, 'table', 'out_mail', 'column', 'senderName'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '收件人地址',
   'user', @CurrentUser, 'table', 'out_mail', 'column', 'receiverAddresses'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '收件人地址别名',
   'user', @CurrentUser, 'table', 'out_mail', 'column', 'receiverNames'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '抄送人地址',
   'user', @CurrentUser, 'table', 'out_mail', 'column', 'cCAddresses'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '抄送人地址别名',
   'user', @CurrentUser, 'table', 'out_mail', 'column', 'cCNames'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '暗送人地址',
   'user', @CurrentUser, 'table', 'out_mail', 'column', 'bCCAddresses'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '暗送人地址别名',
   'user', @CurrentUser, 'table', 'out_mail', 'column', 'bCCAnames'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '0:未阅
   1:已阅',
   'user', @CurrentUser, 'table', 'out_mail', 'column', 'readFlag'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '0:未回复
   1;已回复',
   'user', @CurrentUser, 'table', 'out_mail', 'column', 'replyFlag'
go

/*==============================================================*/
/* Table: out_mail_file                                         */
/*==============================================================*/
create table out_mail_file (
   mailId               bigint          not null,
   fileId               bigint          not null,
   constraint PK_OUT_MAIL_FILE primary key nonclustered (mailId, fileId)
)
go

/*==============================================================*/
/* Table: out_mail_folder                                       */
/*==============================================================*/
create table out_mail_folder (
   folderId             bigint          identity,
   setId 				bigint      not null,
   userId               bigint          null,
   folderName           varchar(128)         not null,
   parentId             bigint          null,
   depLevel             bigint          not null,
   path                 varchar(256)         null,
   folderType           smallint             not null,
   constraint PK_OUT_MAIL_FOLDER primary key nonclustered (folderId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '文件夹编号',
   'user', @CurrentUser, 'table', 'out_mail_folder', 'column', 'folderId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '主键',
   'user', @CurrentUser, 'table', 'out_mail_folder', 'column', 'userId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '文件夹名称',
   'user', @CurrentUser, 'table', 'out_mail_folder', 'column', 'folderName'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '父目录',
   'user', @CurrentUser, 'table', 'out_mail_folder', 'column', 'parentId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '目录层',
   'user', @CurrentUser, 'table', 'out_mail_folder', 'column', 'depLevel'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '文件夹类型
   1=收信箱
   2=发信箱
   3=草稿箱
   4=删除箱
   10=其他',
   'user', @CurrentUser, 'table', 'out_mail_folder', 'column', 'folderType'
go

/*==============================================================*/
/* Table: out_mail_user_seting                                  */
/*==============================================================*/
create table out_mail_user_seting (
   setId                bigint          identity,
   userId               bigint          null,
   userName             varchar(128)         null,
   accountname          varchar(128)         not null,
   mailAddress          varchar(128)         not null,
   mailPass             varchar(128)         not null,
   smtpHost             varchar(128)         not null,
   smtpPort             varchar(64)          not null,
   popHost              varchar(128)         not null,
   popPort              varchar(64)          not null,
   isDefault            smallint             null default 1,
   constraint PK_OUT_MAIL_USER_SETING primary key nonclustered (setId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '用户ID',
   'user', @CurrentUser, 'table', 'out_mail_user_seting', 'column', 'userId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '用户名称',
   'user', @CurrentUser, 'table', 'out_mail_user_seting', 'column', 'userName'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '外部邮件地址',
   'user', @CurrentUser, 'table', 'out_mail_user_seting', 'column', 'mailAddress'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '外部邮件密码',
   'user', @CurrentUser, 'table', 'out_mail_user_seting', 'column', 'mailPass'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'smt主机',
   'user', @CurrentUser, 'table', 'out_mail_user_seting', 'column', 'smtpHost'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'smt端口',
   'user', @CurrentUser, 'table', 'out_mail_user_seting', 'column', 'smtpPort'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'pop主机',
   'user', @CurrentUser, 'table', 'out_mail_user_seting', 'column', 'popHost'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'pop端口',
   'user', @CurrentUser, 'table', 'out_mail_user_seting', 'column', 'popPort'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '是否默认：1表示默认，0表示非默认',
   'user', @CurrentUser, 'table', 'out_mail_user_seting', 'column', 'isDefault'
go

/*==============================================================*/
/* Table: paint_template                                        */
/*==============================================================*/
create table paint_template (
   ptemplateId          bigint          identity,
   fileId               bigint          null,
   templateKey          varchar(64)          null,
   templateName         varchar(64)          not null,
   path                 varchar(128)         null,
   isActivate           smallint             not null,
   constraint PK_PAINT_TEMPLATE primary key nonclustered (ptemplateId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '主键',
   'user', @CurrentUser, 'table', 'paint_template', 'column', 'ptemplateId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '模板名称',
   'user', @CurrentUser, 'table', 'paint_template', 'column', 'templateName'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '路径',
   'user', @CurrentUser, 'table', 'paint_template', 'column', 'path'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '是否激活
   1=是
   0=否',
   'user', @CurrentUser, 'table', 'paint_template', 'column', 'isActivate'
go

/*==============================================================*/
/* Table: phone_book                                            */
/*==============================================================*/
create table phone_book (
   phoneId              bigint          identity,
   fullname             varchar(128)         not null,
   title                varchar(32)          not null,
   birthday             datetime             null,
   nickName             varchar(32)          null,
   duty                 varchar(50)          null,
   spouse               varchar(32)          null,
   childs               varchar(40)          null,
   companyName          varchar(100)         null,
   companyAddress       varchar(128)         null,
   companyPhone         varchar(32)          null,
   companyFax           varchar(32)          null,
   homeAddress          varchar(128)         null,
   homeZip              varchar(12)          null,
   mobile               varchar(32)          null,
   phone                varchar(32)          null,
   email                varchar(32)          null,
   QQ                   varchar(64)          null,
   MSN                  varchar(128)         null,
   note                 varchar(500)         null,
   userId               bigint          not null,
   groupId              bigint          null,
   isShared             smallint             not null,
   constraint PK_PHONE_BOOK primary key nonclustered (phoneId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '通讯簿',
   'user', @CurrentUser, 'table', 'phone_book'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '先生
   女士
   小姐',
   'user', @CurrentUser, 'table', 'phone_book', 'column', 'title'
go

/*==============================================================*/
/* Table: phone_group                                           */
/*==============================================================*/
create table phone_group (
   groupId              bigint          identity,
   groupName            varchar(128)         not null,
   isShared             smallint             not null,
   SN                   bigint          not null,
   userId               bigint          not null,
   isPublic             smallint             null default 0,
   constraint PK_PHONE_GROUP primary key nonclustered (groupId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '分组名称',
   'user', @CurrentUser, 'table', 'phone_group', 'column', 'groupName'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '1=共享
   0=私有',
   'user', @CurrentUser, 'table', 'phone_group', 'column', 'isShared'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '是否公共
   0=私有
   1=公共',
   'user', @CurrentUser, 'table', 'phone_group', 'column', 'isPublic'
go

/*==============================================================*/
/* Table: plan_attend                                           */
/*==============================================================*/
create table plan_attend (
   attendId             bigint          identity,
   userId               bigint          null,
   depId                bigint          null,
   planId               bigint          not null,
   isDep                smallint             not null,
   isPrimary            smallint             null,
   constraint PK_PLAN_ATTEND primary key nonclustered (attendId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '部门ID',
   'user', @CurrentUser, 'table', 'plan_attend', 'column', 'depId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '是否为部门',
   'user', @CurrentUser, 'table', 'plan_attend', 'column', 'isDep'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '是否负责人',
   'user', @CurrentUser, 'table', 'plan_attend', 'column', 'isPrimary'
go

/*==============================================================*/
/* Table: plan_file                                             */
/*==============================================================*/
create table plan_file (
   fileId               bigint          not null,
   planId               bigint          not null,
   constraint PK_PLAN_FILE primary key nonclustered (fileId, planId)
)
go

/*==============================================================*/
/* Table: position                                              */
/*==============================================================*/
create table position (
   pos_id               bigint          identity,
   org_id               numeric(18,0)        null,
   pos_name             varchar(128)         not null,
   pos_desc             varchar(1024)        null,
   pos_sup_id           bigint          null,
   path                 varchar(256)         null,
   depth                int                  null,
   sn                int                  null,
   constraint PK_POSITION primary key nonclustered (pos_id)
)
go

/*==============================================================*/
/* Table: position_sub                                          */
/*==============================================================*/
create table position_sub (
   mainPositionId       bigint          not null,
   subPositionId        bigint          not null,
   constraint PK_POSITION_SUB primary key nonclustered (mainPositionId, subPositionId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '同级岗位是用于管理同级用户的下级，如一经理岗位同时有正与副，正副也同时管理下面的职位人员的话，
   可以把这两职位在这里设置了映射关系，则清楚他们是同时管理下面的岗位',
   'user', @CurrentUser, 'table', 'position_sub'
go

/*==============================================================*/
/* Table: pro_def_rights                                        */
/*==============================================================*/
create table pro_def_rights (
   rightsId             bigint          identity,
   proTypeId            bigint          null,
   defId                bigint          null,
   roleNames            varchar(2000)        null,
   depNames             varchar(2000)        null,
   userNames            varchar(2000)        null,
   userIds              varchar(2000)        null,
   roleIds              varchar(2000)        null,
   depIds               varchar(2000)        null,
   constraint PK_PRO_DEF_RIGHTS primary key nonclustered (rightsId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '用户IDS
   格式如下，以方便使用like操作
   ,1,2,',
   'user', @CurrentUser, 'table', 'pro_def_rights', 'column', 'userIds'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '角色IDS
   格式如下，以方便使用like操作
   ,1,2,',
   'user', @CurrentUser, 'table', 'pro_def_rights', 'column', 'roleIds'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '部门IDS
   格式如下，以方便使用like操作
   ,1,2,',
   'user', @CurrentUser, 'table', 'pro_def_rights', 'column', 'depIds'
go

/*==============================================================*/
/* Table: pro_definition                                        */
/*==============================================================*/
create table pro_definition (
   defId                bigint          identity,
   proTypeId            bigint          null,
   name                 varchar(256)         not null,
   description          varchar(1024)        null,
   createtime           datetime                null,
   deployId             varchar(64)          null,
   pdId                 varchar(64)          null,
   defKey               varchar(64)          null,
   defXml               text                 null,
   drawDefXml           text                 null,
   isDefault            smallint             not null default 0,
   processName          varchar(128)         null,
   newVersion           bigint          null,
   status               smallint             null,
   parentId             bigint,
   isMain               smallint,
   updatetime 			datetime,
   skipFirstNode 		smallint,
   constraint PK_PRO_DEFINITION primary key nonclustered (defId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '流程定义',
   'user', @CurrentUser, 'table', 'pro_definition'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '流程的名称',
   'user', @CurrentUser, 'table', 'pro_definition', 'column', 'name'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '描述',
   'user', @CurrentUser, 'table', 'pro_definition', 'column', 'description'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '创建时间',
   'user', @CurrentUser, 'table', 'pro_definition', 'column', 'createtime'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Jbpm 工作流id',
   'user', @CurrentUser, 'table', 'pro_definition', 'column', 'deployId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '流程定义XML',
   'user', @CurrentUser, 'table', 'pro_definition', 'column', 'defXml'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '是否缺省
   1=是
   0=否',
   'user', @CurrentUser, 'table', 'pro_definition', 'column', 'isDefault'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '来自jbpm的流程定义jpdl中的name值',
   'user', @CurrentUser, 'table', 'pro_definition', 'column', 'processName'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '1=激活
   0=禁用',
   'user', @CurrentUser, 'table', 'pro_definition', 'column', 'status'
go

/*==============================================================*/
/* Table: pro_handle_comp                                       */
/*==============================================================*/
create table pro_handle_comp (
   handleId             bigint          identity,
   deployId             varchar(128)         not null,
   activityName         varchar(128)         null,
   tranName             varchar(128)         null,
   eventName            varchar(128)         null,
   eventLevel           smallint             null,
   exeCode              varchar(4000)        null,
   handleType           smallint             null,
   constraint PK_PRO_HANDLE_COMP primary key nonclustered (handleId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'JBPM流程DeployId',
   'user', @CurrentUser, 'table', 'pro_handle_comp', 'column', 'deployId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '节点名称',
   'user', @CurrentUser, 'table', 'pro_handle_comp', 'column', 'activityName'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '若事件为某个transition中的事件的话，则该字段存储该值',
   'user', @CurrentUser, 'table', 'pro_handle_comp', 'column', 'tranName'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '事件名，有值为：
   start
   end',
   'user', @CurrentUser, 'table', 'pro_handle_comp', 'column', 'eventName'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '事件级别为三值：
   1=process  代表为流程的事件
   2=node     代表为流程节点的事件
   3=transition 代表为跳转的事件',
   'user', @CurrentUser, 'table', 'pro_handle_comp', 'column', 'eventLevel'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '事件中动态执行的代码',
   'user', @CurrentUser, 'table', 'pro_handle_comp', 'column', 'exeCode'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '1=监听类 实现listener之类的接口
   2=处理类  实现handler之类的接口',
   'user', @CurrentUser, 'table', 'pro_handle_comp', 'column', 'handleType'
go

/*==============================================================*/
/* Table: pro_node_set                                          */
/*==============================================================*/
create table pro_node_set (
   setId                bigint          identity,
   defId                bigint          null,
   deployId             varchar(64)          not null,
   jbpmDefId            varchar(64)          not null,
   nodeName             varchar(256)         null,
   nodeType             smallint             null,
   joinNodeName         varchar(256)         null,
   isAllowBack          smallint             null,
   constraint PK_PRO_NODE_SET primary key nonclustered (setId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '1=普通任务节点;2=会签任务节点;3=分发任务节点',
   'user', @CurrentUser, 'table', 'pro_node_set', 'column', 'nodeType'
go

/*==============================================================*/
/* Table: pro_user_assign                                       */
/*==============================================================*/
create table pro_user_assign (
   assignId             bigint          identity,
   deployId             varchar(128)         not null,
   activityName         varchar(128)         not null,
   roleId               varchar(128)         null,
   roleName             varchar(256)         null,
   userId               varchar(128)         null,
   username             varchar(256)         null,
   isSigned             smallint             null default 0,
   jobId                varchar(128)         null,
   jobName              varchar(128)         null,
   reJobId              varchar(128)         null,
   reJobName            varchar(128)         null,
   depIds               varchar(512)         null,
   depNames             varchar(512)         null,
   posUserFlag          smallint             null,
   depPosIds            varchar(128)         null,
   depPosNames          varchar(128)         null,
   constraint PK_PRO_USER_ASSIGN primary key nonclustered (assignId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '流程过程中各个任务节点及启动流程时的角色及用户',
   'user', @CurrentUser, 'table', 'pro_user_assign'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '授权ID',
   'user', @CurrentUser, 'table', 'pro_user_assign', 'column', 'assignId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'jbpm流程定义的id',
   'user', @CurrentUser, 'table', 'pro_user_assign', 'column', 'deployId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '流程节点名称',
   'user', @CurrentUser, 'table', 'pro_user_assign', 'column', 'activityName'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '角色Id',
   'user', @CurrentUser, 'table', 'pro_user_assign', 'column', 'roleId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '用户ID',
   'user', @CurrentUser, 'table', 'pro_user_assign', 'column', 'userId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '1=是会签任务
   0=非会签任务
   
   若为会签任务，则需要为该会签添加会签的决策方式的设置
   ',
   'user', @CurrentUser, 'table', 'pro_user_assign', 'column', 'isSigned'
go

/*==============================================================*/
/* Table: pro_user_set                                          */
/*==============================================================*/
create table pro_user_set (
   id                   bigint          identity,
   defId                bigint          null,
   deployId             varchar(64)          not null,
   jbpmDefId            varchar(64)          not null,
   nodeName             varchar(256)         not null,
   userType             smallint             not null,
   uids                 text                 null,
   unames               text                 null,
   compType             smallint             null,
   demId                bigint          null,
   sn                   bigint          null,
   strategy             smallint        null,
   constraint PK_PRO_USER_SET primary key nonclustered (id)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '1=发起人;2=user;3=role;4=岗位;5=部门、组织;6=部门、组织负责人;7=上下级''',
   'user', @CurrentUser, 'table', 'pro_user_set', 'column', 'userType'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '1=or;2=and;3=exclude',
   'user', @CurrentUser, 'table', 'pro_user_set', 'column', 'compType'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '若选择上下级时对应的维度',
   'user', @CurrentUser, 'table', 'pro_user_set', 'column', 'demId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '级数',
   'user', @CurrentUser, 'table', 'pro_user_set', 'column', 'strategy'
go

/*==============================================================*/
/* Table: process_form                                          */
/*==============================================================*/
create table process_form (
   formId               bigint          identity,
   runId                bigint          not null,
   activityName         varchar(256)         not null,
   createtime           datetime             not null,
   endtime              datetime             null,
   durtimes             bigint          null,
   creatorId            bigint          null,
   creatorName          varchar(64)          null,
   fromTaskId           varchar(64)          null,
   fromTask             varchar(256)         null,
   taskId               varchar(64)          null,
   transTo              varchar(256)         null,
   status               smallint             null default 0,
   preFormId            bigint          null,
   comments             varchar(2000)        null,
   constraint PK_PROCESS_FORM primary key nonclustered (formId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '流程表单
   存储保存在运行中的流程表单数据',
   'user', @CurrentUser, 'table', 'process_form'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '所属运行流程',
   'user', @CurrentUser, 'table', 'process_form', 'column', 'runId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '活动或任务名称',
   'user', @CurrentUser, 'table', 'process_form', 'column', 'activityName'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '该任务来自由哪一任务跳转过来，目的是为了查到该任务的上一任务，方便任务驳回。存储Jbpm 的任务ID',
   'user', @CurrentUser, 'table', 'process_form', 'column', 'fromTaskId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '该任务来自由哪一任务跳转过来，目的是为了查到该任务的上一任务，方便任务驳回。',
   'user', @CurrentUser, 'table', 'process_form', 'column', 'fromTask'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '当前任务ID',
   'user', @CurrentUser, 'table', 'process_form', 'column', 'taskId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '跳转节点
   跳转至下一任务',
   'user', @CurrentUser, 'table', 'process_form', 'column', 'transTo'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '0=进入任务
   1=完成
   2=取消',
   'user', @CurrentUser, 'table', 'process_form', 'column', 'status'
go

/*==============================================================*/
/* Table: process_module                                        */
/*==============================================================*/
create table process_module (
   moduleId             bigint          identity,
   moduleName           varchar(256)         not null,
   moduleKey            varchar(128)         not null,
   descp                varchar(4000)        null,
   defId                bigint          null,
   processKey           varchar(256)         null,
   creator              varchar(64)          null,
   createtime           datetime             null,
   constraint PK_PROCESS_MODULE primary key nonclustered (moduleId)
)
go

/*==============================================================*/
/* Table: process_run                                           */
/*==============================================================*/
create table process_run (
   runId                bigint          identity,
   subject              varchar(256)         not null,
   creator              varchar(128)         null,
   userId               bigint          not null,
   defId                bigint          not null,
   piId                 varchar(64)          null,
   piDbid               bigint          null,
   pdId                 varchar(64)          null,
   processName          varchar(128)         null,
   createtime           datetime             not null,
   runStatus            smallint             not null,
   busDesc              varchar(1024)        null,
   entityName           varchar(128)         null,
   entityId             bigint          null,
   formDefId            bigint          null,
   defHtml              text                 null,
   constraint PK_PROCESS_RUN primary key nonclustered (runId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '运行中的流程',
   'user', @CurrentUser, 'table', 'process_run'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '标题
   一般为流程名称＋格式化的时间',
   'user', @CurrentUser, 'table', 'process_run', 'column', 'subject'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '创建人',
   'user', @CurrentUser, 'table', 'process_run', 'column', 'creator'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '所属用户',
   'user', @CurrentUser, 'table', 'process_run', 'column', 'userId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '所属流程定义',
   'user', @CurrentUser, 'table', 'process_run', 'column', 'defId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '流程实例ID',
   'user', @CurrentUser, 'table', 'process_run', 'column', 'piId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '创建时间',
   'user', @CurrentUser, 'table', 'process_run', 'column', 'createtime'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '0=尚未启动
   1=已经启动流程
   2=运行结束',
   'user', @CurrentUser, 'table', 'process_run', 'column', 'runStatus'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '存储正在运行的表单定义id',
   'user', @CurrentUser, 'table', 'process_run', 'column', 'formDefId'
go

/*==============================================================*/
/* Table: product                                               */
/*==============================================================*/
create table product (
   productId            bigint          identity,
   productName          varchar(128)         not null,
   productModel         varchar(128)         null,
   unit                 varchar(128)         null,
   costPrice            numeric(18,2)        null,
   salesPrice           numeric(18,2)        null,
   productDesc          varchar(512)         null,
   providerId           bigint          not null,
   createtime           datetime             not null,
   updatetime           datetime             not null,
   constraint PK_PRODUCT primary key nonclustered (productId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '供应商产品',
   'user', @CurrentUser, 'table', 'product'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '产品名称',
   'user', @CurrentUser, 'table', 'product', 'column', 'productName'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '产品型号',
   'user', @CurrentUser, 'table', 'product', 'column', 'productModel'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '计量单位',
   'user', @CurrentUser, 'table', 'product', 'column', 'unit'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '成本价',
   'user', @CurrentUser, 'table', 'product', 'column', 'costPrice'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '出售价',
   'user', @CurrentUser, 'table', 'product', 'column', 'salesPrice'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '产品描述',
   'user', @CurrentUser, 'table', 'product', 'column', 'productDesc'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '所属供应商',
   'user', @CurrentUser, 'table', 'product', 'column', 'providerId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '收录时间',
   'user', @CurrentUser, 'table', 'product', 'column', 'createtime'
go

/*==============================================================*/
/* Table: project                                               */
/*==============================================================*/
create table project (
   projectId            bigint          identity,
   projectName          varchar(128)         not null,
   projectNo            varchar(64)          not null,
   reqDesc              text                 null,
   isContract           smallint             not null,
   fullname             varchar(32)          not null,
   mobile               varchar(32)          null,
   phone                varchar(32)          null,
   fax                  varchar(32)          null,
   otherContacts        varchar(128)         null,
   customerId           bigint          not null,
   userId               bigint          not null,
   constraint PK_PROJECT primary key nonclustered (projectId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '项目信息',
   'user', @CurrentUser, 'table', 'project'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '项目名称',
   'user', @CurrentUser, 'table', 'project', 'column', 'projectName'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '项目编号',
   'user', @CurrentUser, 'table', 'project', 'column', 'projectNo'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '需求描述',
   'user', @CurrentUser, 'table', 'project', 'column', 'reqDesc'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '是否签订合同',
   'user', @CurrentUser, 'table', 'project', 'column', 'isContract'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '联系人姓名',
   'user', @CurrentUser, 'table', 'project', 'column', 'fullname'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '手机',
   'user', @CurrentUser, 'table', 'project', 'column', 'mobile'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '电话',
   'user', @CurrentUser, 'table', 'project', 'column', 'phone'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '传真',
   'user', @CurrentUser, 'table', 'project', 'column', 'fax'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '其他联系方式',
   'user', @CurrentUser, 'table', 'project', 'column', 'otherContacts'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '所属客户',
   'user', @CurrentUser, 'table', 'project', 'column', 'customerId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '业务人员',
   'user', @CurrentUser, 'table', 'project', 'column', 'userId'
go

/*==============================================================*/
/* Table: project_file                                          */
/*==============================================================*/
create table project_file (
   fileId               bigint          not null,
   projectId            bigint          not null,
   constraint PK_PROJECT_FILE primary key nonclustered (fileId, projectId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '项目附件',
   'user', @CurrentUser, 'table', 'project_file'
go

/*==============================================================*/
/* Table: provider                                              */
/*==============================================================*/
create table provider (
   providerId           bigint          identity,
   providerName         varchar(128)         not null,
   contactor            varchar(128)         not null,
   phone                varchar(32)          not null,
   fax                  varchar(32)          null,
   site                 varchar(128)         null,
   email                varchar(128)         null,
   address              varchar(128)         not null,
   zip                  varchar(32)          null,
   openBank             varchar(128)         null,
   account              varchar(64)          null,
   notes                varchar(500)         null,
   rank                 bigint          null,
   constraint PK_PROVIDER primary key nonclustered (providerId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '供应商',
   'user', @CurrentUser, 'table', 'provider'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '供应商名称',
   'user', @CurrentUser, 'table', 'provider', 'column', 'providerName'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '联系人',
   'user', @CurrentUser, 'table', 'provider', 'column', 'contactor'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '电话',
   'user', @CurrentUser, 'table', 'provider', 'column', 'phone'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '传真',
   'user', @CurrentUser, 'table', 'provider', 'column', 'fax'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '网址',
   'user', @CurrentUser, 'table', 'provider', 'column', 'site'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '邮件',
   'user', @CurrentUser, 'table', 'provider', 'column', 'email'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '地址',
   'user', @CurrentUser, 'table', 'provider', 'column', 'address'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '邮编',
   'user', @CurrentUser, 'table', 'provider', 'column', 'zip'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '开户行',
   'user', @CurrentUser, 'table', 'provider', 'column', 'openBank'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '帐号',
   'user', @CurrentUser, 'table', 'provider', 'column', 'account'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '备注',
   'user', @CurrentUser, 'table', 'provider', 'column', 'notes'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '供应商等级
   1=一级供应商
   2＝二级供应商
   3＝三级供应商
   4＝四级供应商
   ',
   'user', @CurrentUser, 'table', 'provider', 'column', 'rank'
go

/*==============================================================*/
/* Table: reg_attach                                            */
/*==============================================================*/
create table reg_attach (
   fileId               bigint          not null,
   regId                bigint          not null,
   constraint PK_REG_ATTACH primary key nonclustered (fileId, regId)
)
go

/*==============================================================*/
/* Table: region                                                */
/*==============================================================*/
create table region (
   regionId             bigint          identity,
   regionName           varchar(128)         not null,
   regionType           smallint             not null,
   parentId             bigint          null,
   constraint PK_REGION primary key nonclustered (regionId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '地区管理',
   'user', @CurrentUser, 'table', 'region'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '地区名称',
   'user', @CurrentUser, 'table', 'region', 'column', 'regionName'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '地区类型
   1=省份
   2=市',
   'user', @CurrentUser, 'table', 'region', 'column', 'regionType'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '上级地区',
   'user', @CurrentUser, 'table', 'region', 'column', 'parentId'
go

/*==============================================================*/
/* Table: regulation                                            */
/*==============================================================*/
create table regulation (
   regId                bigint          identity,
   proTypeId            bigint          null,
   subject              varchar(256)         not null,
   issueDate            datetime             null,
   issueUserId          bigint          null,
   issueFullname        varchar(64)          null,
   issueDepId           bigint          null,
   issueDep             varchar(64)          null,
   recDeps              varchar(1024)        null,
   recDepIds            varchar(1024)        null,
   recUsers             varchar(1024)        null,
   recUserIds           varchar(1024)        null,
   content              text                 null,
   keywords             varchar(256)         null,
   status               smallint             null,
   constraint PK_REGULATION primary key nonclustered (regId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '标题',
   'user', @CurrentUser, 'table', 'regulation', 'column', 'subject'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '发布日期',
   'user', @CurrentUser, 'table', 'regulation', 'column', 'issueDate'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '发布人ID',
   'user', @CurrentUser, 'table', 'regulation', 'column', 'issueUserId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '发布人',
   'user', @CurrentUser, 'table', 'regulation', 'column', 'issueFullname'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '发布部门ID',
   'user', @CurrentUser, 'table', 'regulation', 'column', 'issueDepId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '发布部门',
   'user', @CurrentUser, 'table', 'regulation', 'column', 'issueDep'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '接收部门范围',
   'user', @CurrentUser, 'table', 'regulation', 'column', 'recDeps'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '接收部门范围ID',
   'user', @CurrentUser, 'table', 'regulation', 'column', 'recDepIds'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '接收人范围',
   'user', @CurrentUser, 'table', 'regulation', 'column', 'recUsers'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '接收人范围ID',
   'user', @CurrentUser, 'table', 'regulation', 'column', 'recUserIds'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '内容',
   'user', @CurrentUser, 'table', 'regulation', 'column', 'content'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '关键字',
   'user', @CurrentUser, 'table', 'regulation', 'column', 'keywords'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '状态',
   'user', @CurrentUser, 'table', 'regulation', 'column', 'status'
go

/*==============================================================*/
/* Table: relative_job                                          */
/*==============================================================*/
create table relative_job (
   reJobId              bigint          identity,
   jobName              varchar(128)         not null,
   jobCode              varchar(256)         null,
   parent               bigint          null,
   path                 varchar(128)         null,
   depath               bigint          null default 0,
   constraint PK_RELATIVE_JOB primary key nonclustered (reJobId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '岗位名称',
   'user', @CurrentUser, 'table', 'relative_job', 'column', 'jobName'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '编码',
   'user', @CurrentUser, 'table', 'relative_job', 'column', 'jobCode'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '父岗位',
   'user', @CurrentUser, 'table', 'relative_job', 'column', 'parent'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '路径',
   'user', @CurrentUser, 'table', 'relative_job', 'column', 'path'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '深度',
   'user', @CurrentUser, 'table', 'relative_job', 'column', 'depath'
go

/*==============================================================*/
/* Table: relative_user                                         */
/*==============================================================*/
create table relative_user (
   relativeUserId       bigint          identity,
   reJobId              bigint          null,
   userId               bigint          null,
   jobUserId            bigint          null,
   isSuper              smallint             null,
   constraint PK_RELATIVE_USER primary key nonclustered (relativeUserId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'ID',
   'user', @CurrentUser, 'table', 'relative_user', 'column', 'relativeUserId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '所属相对岗位',
   'user', @CurrentUser, 'table', 'relative_user', 'column', 'reJobId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '所属员工',
   'user', @CurrentUser, 'table', 'relative_user', 'column', 'userId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '上下级标识
   1=上级
   0=下级',
   'user', @CurrentUser, 'table', 'relative_user', 'column', 'isSuper'
go

/*==============================================================*/
/* Table: report_param                                          */
/*==============================================================*/
create table report_param (
   paramId              bigint          identity,
   reportId             bigint          not null,
   paramName            varchar(64)          not null,
   paramKey             varchar(64)          not null,
   defaultVal           varchar(128)         null,
   paramType            varchar(32)          not null,
   sn                   bigint          not null,
   paramTypeStr         varchar(1024)        null,
   constraint PK_REPORT_PARAM primary key nonclustered (paramId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '报表参数',
   'user', @CurrentUser, 'table', 'report_param'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '所属报表',
   'user', @CurrentUser, 'table', 'report_param', 'column', 'reportId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '参数名称',
   'user', @CurrentUser, 'table', 'report_param', 'column', 'paramName'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '参数Key',
   'user', @CurrentUser, 'table', 'report_param', 'column', 'paramKey'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '缺省值',
   'user', @CurrentUser, 'table', 'report_param', 'column', 'defaultVal'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '类型
   字符类型--varchar
   整型--int
   精度型--decimal
   日期型--date
   日期时间型--datetime
   ',
   'user', @CurrentUser, 'table', 'report_param', 'column', 'paramType'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '系列号',
   'user', @CurrentUser, 'table', 'report_param', 'column', 'sn'
go

/*==============================================================*/
/* Table: report_template                                       */
/*==============================================================*/
create table report_template (
   reportId             bigint          identity,
   title                varchar(128)         not null,
   descp                varchar(500)         not null,
   reportLocation       varchar(128)         not null,
   createtime           datetime             not null,
   updatetime           datetime             not null,
   reportKey            varchar(128)         null,
   isDefaultIn          smallint             null,
   constraint PK_REPORT_TEMPLATE primary key nonclustered (reportId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '报表模板
   report_template',
   'user', @CurrentUser, 'table', 'report_template'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '标题',
   'user', @CurrentUser, 'table', 'report_template', 'column', 'title'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '描述',
   'user', @CurrentUser, 'table', 'report_template', 'column', 'descp'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '报表模块的jasper文件的路径',
   'user', @CurrentUser, 'table', 'report_template', 'column', 'reportLocation'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '创建时间',
   'user', @CurrentUser, 'table', 'report_template', 'column', 'createtime'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '修改时间',
   'user', @CurrentUser, 'table', 'report_template', 'column', 'updatetime'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '标识key',
   'user', @CurrentUser, 'table', 'report_template', 'column', 'reportKey'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '是否缺省
   1=缺省
   0=非缺省',
   'user', @CurrentUser, 'table', 'report_template', 'column', 'isDefaultIn'
go

/*==============================================================*/
/* Table: resume                                                */
/*==============================================================*/
create table resume (
   resumeId             bigint          identity,
   fullname             varchar(64)          not null,
   age                  bigint          null,
   birthday             datetime             null,
   address              varchar(128)         null,
   zip                  varchar(32)          null,
   sex                  varchar(32)          null,
   position             varchar(64)          null,
   phone                varchar(64)          null,
   mobile               varchar(64)          null,
   email                varchar(128)         null,
   hobby                varchar(256)         null,
   religion             varchar(128)         null,
   party                varchar(128)         null,
   nationality          varchar(32)          null,
   race                 varchar(32)          null,
   birthPlace           varchar(128)         null,
   eduCollege           varchar(128)         null,
   eduDegree            varchar(128)         null,
   eduMajor             varchar(128)         null,
   startWorkDate        datetime             null,
   idNo                 varchar(64)          null,
   photo                varchar(128)         null,
   status               varchar(64)          null,
   memo                 varchar(1024)        null,
   registor             varchar(64)          null,
   regTime              datetime             null,
   workCase             text                 null,
   trainCase            text                 null,
   projectCase          text                 null,
   constraint PK_RESUME primary key nonclustered (resumeId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '简历管理',
   'user', @CurrentUser, 'table', 'resume'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '状态
   
   通过
   未通过
   准备安排面试
   面试通过
   
   ',
   'user', @CurrentUser, 'table', 'resume', 'column', 'status'
go

/*==============================================================*/
/* Table: resume_file                                           */
/*==============================================================*/
create table resume_file (
   fileId               bigint          not null,
   resumeId             bigint          not null,
   constraint PK_RESUME_FILE primary key nonclustered (fileId, resumeId)
)
go

/*==============================================================*/
/* Table: role_fun                                              */
/*==============================================================*/
create table role_fun (
   roleId               bigint          not null,
   functionId           bigint          not null,
   constraint PK_ROLE_FUN primary key nonclustered (roleId, functionId)
)
go

/*==============================================================*/
/* Table: role_position                                         */
/*==============================================================*/
create table role_position (
   pos_id               bigint          not null,
   roleId               bigint          not null,
   constraint PK_ROLE_POSITION primary key nonclustered (pos_id, roleId)
)
go

/*==============================================================*/
/* Table: roll_file                                             */
/*==============================================================*/
create table roll_file (
   rollFileId           bigint          identity,
   typeName             varchar(128)         null,
   rollId               bigint          null,
   proTypeId            bigint          null,
   fileName             varchar(128)         not null,
   fileNo               varchar(64)          not null,
   dutyPerson           varchar(32)          null,
   afNo                 varchar(64)          null,
   catNo                varchar(64)          null,
   rollNo               varchar(64)          null,
   seqNo                bigint          null,
   pageNo               bigint          null,
   pageNums             bigint          null,
   secretLevel          varchar(64)          null,
   timeLimit            varchar(64)          null,
   openStyle            varchar(64)          null,
   keyWords             varchar(512)         null,
   notes                varchar(4000)        null,
   content              text                 null,
   fileTime             datetime             null,
   creatorName          varchar(128)         null,
   createTime           datetime             null default 'SYSDATE',
   archStatus           smallint             null default 0,
   tidyTime             datetime             null,
   tidyName             varchar(128)         null,
   constraint PK_ROLL_FILE primary key nonclustered (rollFileId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '分类名称',
   'user', @CurrentUser, 'table', 'roll_file', 'column', 'typeName'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '案卷ID',
   'user', @CurrentUser, 'table', 'roll_file', 'column', 'rollId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '文件题名',
   'user', @CurrentUser, 'table', 'roll_file', 'column', 'fileName'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '文件编号',
   'user', @CurrentUser, 'table', 'roll_file', 'column', 'fileNo'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '责任者',
   'user', @CurrentUser, 'table', 'roll_file', 'column', 'dutyPerson'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '全宗号',
   'user', @CurrentUser, 'table', 'roll_file', 'column', 'afNo'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '目录号',
   'user', @CurrentUser, 'table', 'roll_file', 'column', 'catNo'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '案卷号',
   'user', @CurrentUser, 'table', 'roll_file', 'column', 'rollNo'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '顺序号',
   'user', @CurrentUser, 'table', 'roll_file', 'column', 'seqNo'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '页号',
   'user', @CurrentUser, 'table', 'roll_file', 'column', 'pageNo'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '页数',
   'user', @CurrentUser, 'table', 'roll_file', 'column', 'pageNums'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '密级
   
   普通
   秘密
   机密
   绝密',
   'user', @CurrentUser, 'table', 'roll_file', 'column', 'secretLevel'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '保管期限
   长久
   长期
   短期
   10年
   15年
   20年
   ',
   'user', @CurrentUser, 'table', 'roll_file', 'column', 'timeLimit'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '开放形式
   开放
   待定
   私密',
   'user', @CurrentUser, 'table', 'roll_file', 'column', 'openStyle'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '主题词',
   'user', @CurrentUser, 'table', 'roll_file', 'column', 'keyWords'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '附注',
   'user', @CurrentUser, 'table', 'roll_file', 'column', 'notes'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '内容',
   'user', @CurrentUser, 'table', 'roll_file', 'column', 'content'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '文件时间',
   'user', @CurrentUser, 'table', 'roll_file', 'column', 'fileTime'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '录入人',
   'user', @CurrentUser, 'table', 'roll_file', 'column', 'creatorName'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '录入时间',
   'user', @CurrentUser, 'table', 'roll_file', 'column', 'createTime'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '归档状态
   1=归档
   0=未归档',
   'user', @CurrentUser, 'table', 'roll_file', 'column', 'archStatus'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '归档时间',
   'user', @CurrentUser, 'table', 'roll_file', 'column', 'tidyTime'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '归档人',
   'user', @CurrentUser, 'table', 'roll_file', 'column', 'tidyName'
go

/*==============================================================*/
/* Table: roll_file_list                                        */
/*==============================================================*/
create table roll_file_list (
   listId               bigint          identity,
   rollFileId           bigint          not null,
   fileId               bigint          null,
   downLoads            bigint          null,
   sn                   int                  null,
   shortDesc            varchar(500)         null,
   constraint PK_ROLL_FILE_LIST primary key nonclustered (listId)
)
go

/*==============================================================*/
/* Table: run_data                                              */
/*==============================================================*/
create table run_data (
   dataId               bigint          identity,
   runId                bigint          null,
   fieldLabel           varchar(128)         null,
   fieldName            varchar(64)          not null,
   intValue             bigint          null,
   longValue            bigint          null,
   decValue             numeric(18,4)        null,
   dateValue            datetime             null,
   strValue             varchar(4000)        null,
   boolValue            smallint             null,
   blobValue            ntext                null,
   isShowed             smallint             null,
   textValue            text                 null,
   fieldType            varchar(32)          null,
   constraint PK_RUN_DATA primary key nonclustered (dataId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '字段标签',
   'user', @CurrentUser, 'table', 'run_data', 'column', 'fieldLabel'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '字段名称',
   'user', @CurrentUser, 'table', 'run_data', 'column', 'fieldName'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '整数值',
   'user', @CurrentUser, 'table', 'run_data', 'column', 'intValue'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '长整值',
   'user', @CurrentUser, 'table', 'run_data', 'column', 'longValue'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '精度值',
   'user', @CurrentUser, 'table', 'run_data', 'column', 'decValue'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '日期值',
   'user', @CurrentUser, 'table', 'run_data', 'column', 'dateValue'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '字符值',
   'user', @CurrentUser, 'table', 'run_data', 'column', 'strValue'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '布尔值',
   'user', @CurrentUser, 'table', 'run_data', 'column', 'boolValue'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '对象值',
   'user', @CurrentUser, 'table', 'run_data', 'column', 'blobValue'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '是否显示
   1=显示
   0=不显示',
   'user', @CurrentUser, 'table', 'run_data', 'column', 'isShowed'
go

/*==============================================================*/
/* Table: salary_item                                           */
/*==============================================================*/
create table salary_item (
   salaryItemId         bigint          identity,
   itemName             varchar(128)         not null,
   defaultVal           numeric(18,2)        not null,
   constraint PK_SALARY_ITEM primary key nonclustered (salaryItemId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '薪酬组成项目',
   'user', @CurrentUser, 'table', 'salary_item'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '项目名称',
   'user', @CurrentUser, 'table', 'salary_item', 'column', 'itemName'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '缺省值',
   'user', @CurrentUser, 'table', 'salary_item', 'column', 'defaultVal'
go

/*==============================================================*/
/* Table: salary_payoff                                         */
/*==============================================================*/
create table salary_payoff (
   recordId             bigint          identity,
   fullname             varchar(64)          not null,
   userId               bigint          not null,
   profileNo            varchar(128)         null,
   standardId           bigint          not null,
   idNo                 varchar(128)         null,
   standAmount          numeric(18,2)        not null default 0,
   encourageAmount      numeric(18,2)        not null default 0,
   deductAmount         numeric(18,2)        not null default 0,
   achieveAmount        numeric(18,2)        null default 0,
   encourageDesc        varchar(512)         null,
   deductDesc           varchar(512)         null,
   memo                 varchar(512)         null,
   acutalAmount         numeric(18,2)        null,
   regTime              datetime             not null,
   register             varchar(64)          null,
   checkOpinion         varchar(1024)        null,
   checkName            varchar(64)          null,
   checkTime            datetime             null,
   checkStatus          smallint             null,
   startTime            datetime             not null,
   endTime              datetime             not null,
   constraint PK_SALARY_PAYOFF primary key nonclustered (recordId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '员工姓名',
   'user', @CurrentUser, 'table', 'salary_payoff', 'column', 'fullname'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '所属员工',
   'user', @CurrentUser, 'table', 'salary_payoff', 'column', 'userId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '档案编号',
   'user', @CurrentUser, 'table', 'salary_payoff', 'column', 'profileNo'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '身份证号',
   'user', @CurrentUser, 'table', 'salary_payoff', 'column', 'idNo'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '薪标金额',
   'user', @CurrentUser, 'table', 'salary_payoff', 'column', 'standAmount'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '奖励金额',
   'user', @CurrentUser, 'table', 'salary_payoff', 'column', 'encourageAmount'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '扣除工资',
   'user', @CurrentUser, 'table', 'salary_payoff', 'column', 'deductAmount'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '效绩工资',
   'user', @CurrentUser, 'table', 'salary_payoff', 'column', 'achieveAmount'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '奖励描述',
   'user', @CurrentUser, 'table', 'salary_payoff', 'column', 'encourageDesc'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '扣除描述',
   'user', @CurrentUser, 'table', 'salary_payoff', 'column', 'deductDesc'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '备注描述',
   'user', @CurrentUser, 'table', 'salary_payoff', 'column', 'memo'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '实发金额',
   'user', @CurrentUser, 'table', 'salary_payoff', 'column', 'acutalAmount'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '登记时间',
   'user', @CurrentUser, 'table', 'salary_payoff', 'column', 'regTime'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '登记人',
   'user', @CurrentUser, 'table', 'salary_payoff', 'column', 'register'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '审批人',
   'user', @CurrentUser, 'table', 'salary_payoff', 'column', 'checkName'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '审批时间',
   'user', @CurrentUser, 'table', 'salary_payoff', 'column', 'checkTime'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '审批状态
   0=草稿
   1=通过审批
   2=未通过审批
   ',
   'user', @CurrentUser, 'table', 'salary_payoff', 'column', 'checkStatus'
go

/*==============================================================*/
/* Table: seal                                                  */
/*==============================================================*/
create table seal (
   sealId               bigint          identity,
   fileId               bigint          null,
   sealName             varchar(64)          not null,
   sealPath             varchar(128)         null,
   belongId             bigint          not null,
   belongName           varchar(64)          not null,
   constraint PK_SEAL primary key nonclustered (sealId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '印章名称',
   'user', @CurrentUser, 'table', 'seal', 'column', 'sealName'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '印章文件路径',
   'user', @CurrentUser, 'table', 'seal', 'column', 'sealPath'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '所属人ID',
   'user', @CurrentUser, 'table', 'seal', 'column', 'belongId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '所属人',
   'user', @CurrentUser, 'table', 'seal', 'column', 'belongName'
go

/*==============================================================*/
/* Table: section                                               */
/*==============================================================*/
create table section (
   sectionId            bigint          identity,
   sectionName          varchar(256)         not null,
   sectionDesc          varchar(1024)        null,
   createtime           datetime             not null,
   sectionType          smallint             not null,
   username             varchar(256)         null,
   userId               bigint          null,
   colNumber            bigint          null,
   rowNumber            bigint          null,
   status               smallint             not null,
   constraint PK_SECTION primary key nonclustered (sectionId)
)
go

/*==============================================================*/
/* Table: serial_number                                         */
/*==============================================================*/
create table serial_number (
   numberId             bigint          identity,
   name                 varchar(50)          null,
   alias                varchar(20)          null,
   regulation           varchar(100)         null,
   genType              smallint             null,
   noLength             int                  null,
   curDate              varchar(10)          null,
   initValue            int                  null,
   curValue             int                  null,
   step                 smallint             null,
   constraint PK_SERIAL_NUMBER primary key nonclustered (numberId)
)
go

/*==============================================================*/
/* Table: short_message                                         */
/*==============================================================*/
create table short_message (
   messageId            bigint          identity,
   senderId             bigint          null,
   content              varchar(256)         not null,
   sender               varchar(64)          not null,
   msgType              smallint             not null,
   sendTime             datetime             not null,
   constraint PK_SHORT_MESSAGE primary key nonclustered (messageId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '短信消息',
   'user', @CurrentUser, 'table', 'short_message'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '主键',
   'user', @CurrentUser, 'table', 'short_message', 'column', 'senderId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '1=个人信息
   2=日程安排
   3=计划任务
   ',
   'user', @CurrentUser, 'table', 'short_message', 'column', 'msgType'
go

/*==============================================================*/
/* Table: sms_history                                           */
/*==============================================================*/
create table sms_history (
   smsId                bigint          identity,
   sendTime             datetime             not null,
   recipients           varchar(128)         null,
   phoneNumber          varchar(128)         not null,
   userId               bigint          null,
   userName             varchar(128)         null,
   smsContent           varchar(1024)        not null,
   status               smallint             not null,
   constraint PK_SMS_HISTORY primary key nonclustered (smsId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '0=未发送
   1=发送失败
   
   发送成功后，该记录会直接存在另一张发送历史的表中
   ',
   'user', @CurrentUser, 'table', 'sms_history', 'column', 'status'
go

/*==============================================================*/
/* Table: sms_mobile                                            */
/*==============================================================*/
create table sms_mobile (
   smsId                bigint          identity,
   sendTime             datetime             not null,
   recipients           varchar(128)         null,
   phoneNumber          varchar(128)         not null,
   userId               bigint          null,
   userName             varchar(128)         null,
   smsContent           varchar(1024)        not null,
   status               smallint             not null,
   constraint PK_SMS_MOBILE primary key nonclustered (smsId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '0=未发送
   1=发送失败
   
   发送成功后，该记录会直接存在另一张发送历史的表中
   ',
   'user', @CurrentUser, 'table', 'sms_mobile', 'column', 'status'
go

/*==============================================================*/
/* Table: stand_salary                                          */
/*==============================================================*/
create table stand_salary (
   standardId           bigint          identity,
   standardNo           varchar(128)         not null,
   standardName         varchar(128)         not null,
   totalMoney           numeric(18,2)        not null default 0.00,
   framer               varchar(64)          null,
   setdownTime          datetime             null,
   checkName            varchar(64)          null,
   checkTime            datetime             null,
   modifyName           varchar(64)          null,
   modifyTime           datetime             null,
   checkOpinion         varchar(512)         null,
   status               smallint             not null,
   memo                 varchar(512)         null,
   constraint PK_STAND_SALARY primary key nonclustered (standardId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '薪酬标准编号
   惟一',
   'user', @CurrentUser, 'table', 'stand_salary', 'column', 'standardNo'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '标准名称',
   'user', @CurrentUser, 'table', 'stand_salary', 'column', 'standardName'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '0=草稿
   1=审批
   2=未通过审批',
   'user', @CurrentUser, 'table', 'stand_salary', 'column', 'status'
go

/*==============================================================*/
/* Table: stand_salary_item                                     */
/*==============================================================*/
create table stand_salary_item (
   itemId               bigint          identity,
   standardId           bigint          not null,
   itemName             varchar(64)          not null,
   amount               numeric(18,2)        not null,
   salaryItemId         bigint          null,
   constraint PK_STAND_SALARY_ITEM primary key nonclustered (itemId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '薪酬标准明细',
   'user', @CurrentUser, 'table', 'stand_salary_item'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '所属工资组成ID
   外键，但不需要在数据库层建立外键',
   'user', @CurrentUser, 'table', 'stand_salary_item', 'column', 'salaryItemId'
go

/*==============================================================*/
/* Table: subordinate                                           */
/*==============================================================*/
create table subordinate (
   subordinateId        bigint          identity,
   dem_id               bigint          not null,
   userId               bigint          not null,
   jobuserId            bigint          not null,
   relative             numeric(2)           not null,
   constraint PK_SUBORDINATE primary key nonclustered (subordinateId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '用户上下级',
   'user', @CurrentUser, 'table', 'subordinate'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '关系所属维度',
   'user', @CurrentUser, 'table', 'subordinate', 'column', 'dem_id'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'jobuserid',
   'user', @CurrentUser, 'table', 'subordinate', 'column', 'jobuserId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '上下级标识 1=上级 ，2同级，0下级''',
   'user', @CurrentUser, 'table', 'subordinate', 'column', 'relative'
go


/*==============================================================*/
/* Table: suggest_box                                           */
/*==============================================================*/
create table suggest_box (
   boxId                bigint          identity,
   subject              varchar(256)         not null,
   content              varchar(4000)        not null,
   createtime           datetime             null,
   recUid               bigint          null,
   recFullname          varchar(32)          null,
   senderId             bigint          null,
   senderFullname       varchar(32)          null,
   senderIp             varchar(64)          null,
   phone                varchar(64)          null,
   email                varchar(100)         null,
   isOpen               smallint             null,
   replyContent         varchar(4000)        null,
   replyTime            datetime             null,
   replyId              bigint          null,
   replyFullname        varchar(32)          null,
   status               smallint             null,
   queryPwd             varchar(128)         null,
   constraint PK_SUGGEST_BOX primary key nonclustered (boxId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '意见标题',
   'user', @CurrentUser, 'table', 'suggest_box', 'column', 'subject'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '意见内容',
   'user', @CurrentUser, 'table', 'suggest_box', 'column', 'content'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '创建日期',
   'user', @CurrentUser, 'table', 'suggest_box', 'column', 'createtime'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '接收人ID',
   'user', @CurrentUser, 'table', 'suggest_box', 'column', 'recUid'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '接收人名',
   'user', @CurrentUser, 'table', 'suggest_box', 'column', 'recFullname'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '发送人ID',
   'user', @CurrentUser, 'table', 'suggest_box', 'column', 'senderId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '发送人名',
   'user', @CurrentUser, 'table', 'suggest_box', 'column', 'senderFullname'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '发送人IP',
   'user', @CurrentUser, 'table', 'suggest_box', 'column', 'senderIp'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '联系电话',
   'user', @CurrentUser, 'table', 'suggest_box', 'column', 'phone'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Email',
   'user', @CurrentUser, 'table', 'suggest_box', 'column', 'email'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '是否公开',
   'user', @CurrentUser, 'table', 'suggest_box', 'column', 'isOpen'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '回复内容',
   'user', @CurrentUser, 'table', 'suggest_box', 'column', 'replyContent'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '回复时间',
   'user', @CurrentUser, 'table', 'suggest_box', 'column', 'replyTime'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '回复人ID',
   'user', @CurrentUser, 'table', 'suggest_box', 'column', 'replyId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '回复人名',
   'user', @CurrentUser, 'table', 'suggest_box', 'column', 'replyFullname'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '状态',
   'user', @CurrentUser, 'table', 'suggest_box', 'column', 'status'
go

/*==============================================================*/
/* Table: sys_config                                            */
/*==============================================================*/
create table sys_config (
   configId             bigint          identity,
   configKey            varchar(64)          not null,
   configName           varchar(64)          not null,
   configDesc           varchar(256)         null,
   typeName             varchar(32)          not null,
   dataType             smallint             not null,
   dataValue            varchar(64)          null,
   typeKey              varchar(64)          null,
   constraint PK_SYS_CONFIG primary key nonclustered (configId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '系统配置
   
   用于系统的全局配置
   如邮件服务器的配置',
   'user', @CurrentUser, 'table', 'sys_config'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'Key',
   'user', @CurrentUser, 'table', 'sys_config', 'column', 'configKey'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '配置名称',
   'user', @CurrentUser, 'table', 'sys_config', 'column', 'configName'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '配置描述',
   'user', @CurrentUser, 'table', 'sys_config', 'column', 'configDesc'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '所属分类名称',
   'user', @CurrentUser, 'table', 'sys_config', 'column', 'typeName'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '数据类型
   1=varchar
   2=intger
   3=decimal
   4=datetime
   5=time
   ',
   'user', @CurrentUser, 'table', 'sys_config', 'column', 'dataType'
go

/*==============================================================*/
/* Table: task_sign                                             */
/*==============================================================*/
create table task_sign (
   signId               bigint          identity,
   setId                bigint          not null,
   voteCounts           bigint          null,
   votePercents         bigint          null,
   decideType           smallint             not null,
   signType             int                  null,
   constraint PK_TASK_SIGN primary key nonclustered (signId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '绝对票数',
   'user', @CurrentUser, 'table', 'task_sign', 'column', 'voteCounts'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '百分比票数',
   'user', @CurrentUser, 'table', 'task_sign', 'column', 'votePercents'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '1=pass 通过
   2=reject 拒绝',
   'user', @CurrentUser, 'table', 'task_sign', 'column', 'decideType'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '1=绝对票数 2=百分比票数',
   'user', @CurrentUser, 'table', 'task_sign', 'column', 'signType'
go

/*==============================================================*/
/* Table: task_sign_data                                        */
/*==============================================================*/
create table task_sign_data (
   dataId               bigint          identity,
   voteId               bigint          not null,
   voteName             varchar(64)          null,
   voteTime             datetime             not null,
   taskId               varchar(64)          not null,
   isAgree              smallint             not null,
   constraint PK_TASK_SIGN_DATA primary key nonclustered (dataId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '投票人',
   'user', @CurrentUser, 'table', 'task_sign_data', 'column', 'voteId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '投票人名',
   'user', @CurrentUser, 'table', 'task_sign_data', 'column', 'voteName'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '投票时间',
   'user', @CurrentUser, 'table', 'task_sign_data', 'column', 'voteTime'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '任务Id',
   'user', @CurrentUser, 'table', 'task_sign_data', 'column', 'taskId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '是否同意
   1=同意
   2=拒绝
   
   跟task_sign中的decideType是一样',
   'user', @CurrentUser, 'table', 'task_sign_data', 'column', 'isAgree'
go

/*==============================================================*/
/* Table: type_key                                              */
/*==============================================================*/
create table type_key (
   typeKeyId            bigint          identity,
   typeKey              varchar(64)          not null,
   typeName             varchar(64)          not null,
   sn                   bigint          null,
   constraint PK_TYPE_KEY primary key nonclustered (typeKeyId)
)
go

/*==============================================================*/
/* Table: user_org                                              */
/*==============================================================*/
create table user_org (
   user_org_id          bigint          identity,
   userId               bigint          null,
   org_id               bigint          null,
   is_primary           smallint             not null,
   is_charge            bigint          null,
   constraint PK_USER_ORG primary key nonclustered (user_org_id)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '主键',
   'user', @CurrentUser, 'table', 'user_org', 'column', 'userId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '1=主要
   0=非主要',
   'user', @CurrentUser, 'table', 'user_org', 'column', 'is_primary'
go

/*==============================================================*/
/* Table: user_position                                         */
/*==============================================================*/
create table user_position (
   user_pos_id          bigint          identity,
   pos_id               bigint          null,
   userId               bigint          null,
   isPrimary            smallint             null,
   constraint PK_USER_POSITION primary key nonclustered (user_pos_id)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '主键',
   'user', @CurrentUser, 'table', 'user_position', 'column', 'userId'
go

/*==============================================================*/
/* Table: user_role                                             */
/*==============================================================*/
create table user_role (
   userId               bigint          not null,
   roleId               bigint          not null,
   constraint PK_USER_ROLE primary key nonclustered (userId, roleId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '主键',
   'user', @CurrentUser, 'table', 'user_role', 'column', 'userId'
go

/*==============================================================*/
/* Table: wf_general                                            */
/*==============================================================*/
create table wf_general (
   entityId             bigint          identity,
   itemSubject          varchar(128)         not null,
   itemDescp            text                 not null,
   runId                bigint          null,
   createtime           datetime             null,
   constraint PK_WF_GENERAL primary key nonclustered (entityId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'ID',
   'user', @CurrentUser, 'table', 'wf_general', 'column', 'entityId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '申请描述',
   'user', @CurrentUser, 'table', 'wf_general', 'column', 'itemDescp'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   'process_run表的主键，通过它可以取到相关的流程运行及审批信息',
   'user', @CurrentUser, 'table', 'wf_general', 'column', 'runId'
go

/*==============================================================*/
/* Table: work_plan                                             */
/*==============================================================*/
create table work_plan (
   planId               bigint          identity,
   planName             varchar(128)         not null,
   planContent          text                 null,
   startTime            datetime             not null,
   endTime              datetime             not null,
   typeName             varchar(64)          null,
   userId               bigint          null,
   proTypeId            bigint          null,
   issueScope           varchar(2000)        null,
   participants         varchar(2000)        null,
   principal            varchar(256)         not null,
   note                 varchar(500)         null,
   status               smallint             not null,
   isPersonal           smallint             not null,
   icon                 varchar(128)         null,
   constraint PK_WORK_PLAN primary key nonclustered (planId)
)
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '工作计划',
   'user', @CurrentUser, 'table', 'work_plan'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '计划名称',
   'user', @CurrentUser, 'table', 'work_plan', 'column', 'planName'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '计划内容',
   'user', @CurrentUser, 'table', 'work_plan', 'column', 'planContent'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '开始日期',
   'user', @CurrentUser, 'table', 'work_plan', 'column', 'startTime'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '结束日期',
   'user', @CurrentUser, 'table', 'work_plan', 'column', 'endTime'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '类型名称',
   'user', @CurrentUser, 'table', 'work_plan', 'column', 'typeName'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '员工ID',
   'user', @CurrentUser, 'table', 'work_plan', 'column', 'userId'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '发布范围
   0则代表全部部门
   存放所有的参与部门ID
   ',
   'user', @CurrentUser, 'table', 'work_plan', 'column', 'issueScope'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '参与人
   0则代表全部参与
   参与人,即员工ID列表',
   'user', @CurrentUser, 'table', 'work_plan', 'column', 'participants'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '负责人',
   'user', @CurrentUser, 'table', 'work_plan', 'column', 'principal'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '备注',
   'user', @CurrentUser, 'table', 'work_plan', 'column', 'note'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '状态
   1=激活
   0=禁用',
   'user', @CurrentUser, 'table', 'work_plan', 'column', 'status'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '是否为个人计划
   1=则为个人工作计划，这时发布范围，参与人均为空，负责人为当前用户
   0=则代表为其他任务',
   'user', @CurrentUser, 'table', 'work_plan', 'column', 'isPersonal'
go

declare @CurrentUser sysname
select @CurrentUser = user_name()
execute sp_addextendedproperty 'MS_Description', 
   '图标',
   'user', @CurrentUser, 'table', 'work_plan', 'column', 'icon'
go

create table form_button_right (
	buttonid  		bigint 			identity,
	mappingid  		bigint			not null,
	tableid  		bigint 			not null,
	tablename  		varchar(256) 	not null,
	buttonright 	smallint		not null,
	usertype  		smallint 		null,
	uids  			text,
	unames  		text,
	taskname 		varchar(128) 	not null,
	buttontype  	smallint 		not null,
	constraint PK_FORM_BUTTON_RIGHT primary key nonclustered (buttonid)
)
go


alter table form_button_right 
	add constraint FORM_BUTTON_RIGHT_IBFK_1 foreign key (mappingid) 
		references form_def_mapping (mappingid) on delete cascade
go


alter table app_tips
   add constraint FK_APP_TIPS_AT_R_AP_APP_USER foreign key (userId)
      references app_user (userId)
go

alter table appointment
   add constraint FK_APPOINTM_AP_R_AU_APP_USER foreign key (userId)
      references app_user (userId)
go

alter table arch_dispatch
   add constraint FK_AVDH_R_ARV foreign key (archivesId)
      references archives (archivesId)
         on delete cascade
go

alter table arch_flow_conf
   add constraint FK_AFC_R_PDN foreign key (defId)
      references pro_definition (defId)
         on delete cascade
go

alter table arch_fond
   add constraint FK_AF_R_GT foreign key (proTypeId)
      references global_type (proTypeId)
         on delete set null
go

alter table arch_hasten
   add constraint FK_ARHN_R_ARV foreign key (archivesId)
      references archives (archivesId)
go

alter table arch_roll
   add constraint FK_AR_R_AF foreign key (archFondId)
      references arch_fond (archFondId)
         on delete set null
go

alter table arch_roll
   add constraint FK_AR_R_GT foreign key (proTypeId)
      references global_type (proTypeId)
         on delete set null
go

alter table arch_template
   add constraint FK_ARCH_TEM_AHT_R_FA_FILE_ATT foreign key (fileId)
      references file_attach (fileId)
go

alter table arch_template
   add constraint FK_ARCH_TEM_ART_R_ARV_GLOBAL_T foreign key (proTypeId)
      references global_type (proTypeId)
go

alter table archives
   add constraint FK_ARCHIVES_ARV_R_ART_GLOBAL_T foreign key (glo_proTypeId)
      references global_type (proTypeId)
go

alter table archives
   add constraint FK_ARCHIVES_ARV_R_ARV_GLOBAL_T foreign key (proTypeId)
      references global_type (proTypeId)
go

alter table archives_dep
   add constraint FK_ARCHIVES_AVD_R_ARV_ARCHIVES foreign key (archivesId)
      references archives (archivesId)
         on delete cascade
go

alter table archives_doc
   add constraint FK_ARHD_R_FA foreign key (fileId)
      references file_attach (fileId)
         on delete set null
go

alter table archives_doc
   add constraint FK_ARCHIVES_ARVD_R_AR_ARCHIVES foreign key (archivesId)
      references archives (archivesId)
go

alter table book
   add constraint FK_BOOK_BK_R_BT_BOOK_TYP foreign key (typeId)
      references book_type (typeId)
go

alter table book_bor_ret
   add constraint FK_BOOK_BOR_BBR_R_BS_BOOK_SN foreign key (bookSnId)
      references book_sn (bookSnId)
go

alter table book_sn
   add constraint FK_BOOK_SN_BS_R_BK_BOOK foreign key (bookId)
      references book (bookId)
go

alter table borrow_file_list
   add constraint FK_BORROW_F_BFL_AR_ARCH_ROL foreign key (rollId)
      references arch_roll (rollId)
go

alter table borrow_file_list
   add constraint FK_BORROW_F_BFL_R_AF_ARCH_FON foreign key (archFondId)
      references arch_fond (archFondId)
go

alter table borrow_file_list
   add constraint FK_BORROW_F_BFL_R_BR_BORROW_R foreign key (recordId)
      references borrow_record (recordId)
go

alter table borrow_file_list
   add constraint FK_BORROW_F_BFL_R_RF_ROLL_FIL foreign key (rollFileId)
      references roll_file (rollFileId)
go

alter table borrow_record
   add constraint FK_BORROW_R_BR_R_AU1_APP_USER foreign key (checkUserId)
      references app_user (userId)
         on delete set null
go

alter table cal_file
   add constraint FK_CAL_FILE_CF_R_CP_CALENDAR foreign key (planId)
      references calendar_plan (planId)
go

alter table cal_file
   add constraint FK_CAL_FILE_CF_R_FA_FILE_ATT foreign key (fileId)
      references file_attach (fileId)
go

alter table calendar_plan
   add constraint FK_CALENDAR_CA_R_AU_APP_USER foreign key (userId)
      references app_user (userId)
go

alter table calendar_plan
   add constraint FK_CALENDAR_CP_R_AUAS_APP_USER foreign key (assignerId)
      references app_user (userId)
go

alter table car_apply
   add constraint FK_CAR_APPL_CRA_R_CR_CAR foreign key (carId)
      references car (carId)
go

alter table cart_repair
   add constraint FK_CART_REP_CRR_R_CR_CAR foreign key (carId)
      references car (carId)
go

alter table conf_attach
   add constraint FK_CFA_R_CFC foreign key (confId)
      references conference (confId)
         on delete cascade
go

alter table conf_attach
   add constraint FK_CCFA_R_FA foreign key (fileId)
      references file_attach (fileId)
         on delete cascade
go

alter table conf_attend
   add constraint FK_CA_R_CFC foreign key (confId)
      references conference (confId)
         on delete cascade
go

alter table conf_privilege
   add constraint FK_CP_R_CFC foreign key (confId)
      references conference (confId)
         on delete cascade
go

alter table conf_sum_attach
   add constraint FK_CSA_R_CS foreign key (sumId)
      references conf_summary (sumId)
         on delete cascade
go

alter table conf_sum_attach
   add constraint FK_CSA_R_FA foreign key (fileId)
      references file_attach (fileId)
go

alter table conf_summary
   add constraint FK_CS_R_CFC foreign key (confId)
      references conference (confId)
go

alter table conference
   add constraint FK_CF_R_BT_BD foreign key (typeId)
      references board_type (typeId)
         on delete set null
go

alter table conference
   add constraint FK_CFC_R_BDM foreign key (roomId)
      references boardroo (roomId)
         on delete set null
go

alter table contract
   add constraint FK_CONTRACT_CT_R_PT_PROJECT foreign key (projectId)
      references project (projectId)
go

alter table contract_config
   add constraint FK_CONTRACT_CC_R_CT_CONTRACT foreign key (contractId)
      references contract (contractId)
go

alter table contract_file
   add constraint FK_CONTRACT_CTF_R_CT_CONTRACT foreign key (contractId)
      references contract (contractId)
go

alter table contract_file
   add constraint FK_CONTRACT_CTF_R_FA_FILE_ATT foreign key (fileId)
      references file_attach (fileId)
go

alter table cus_connection
   add constraint FK_CUS_CONN_CC_R_CS_CUSTOMER foreign key (customerId)
      references customer (customerId)
go

alter table cus_linkman
   add constraint FK_CUS_LINK_CLM_R_CS_CUSTOMER foreign key (customerId)
      references customer (customerId)
go

alter table dep_pos
   add constraint FK_DEP_POS_REFERENCE_ORGANIZA foreign key (org_id)
      references organization (org_id)
go

alter table dep_pos
   add constraint FK_DEP_POS_REFERENCE_POSITION foreign key (pos_id)
      references position (pos_id)
go

alter table depre_record
   add constraint FK_DEPRE_RE_DR_R_FA_FIXED_AS foreign key (assetsId)
      references fixed_assets (assetsId)
go

alter table diary
   add constraint FK_DIARY_DY_R_AU_APP_USER foreign key (userId)
      references app_user (userId)
go

alter table dictionary
   add constraint DTY_R_GT foreign key (proTypeId)
      references global_type (proTypeId)
         on delete set null
go

alter table doc_file
   add constraint FK_DOC_FILE_DF_F_DT_DOCUMENT foreign key (docId)
      references document (docId)
go

alter table doc_file
   add constraint FK_DF_R_FA foreign key (fileId)
      references file_attach (fileId)
go

alter table doc_folder
   add constraint FK_DOC_FOLD_DF_R_AU_APP_USER foreign key (userId)
      references app_user (userId)
go

alter table doc_history
   add constraint FK_DOC_HIST_DHY_R_ARV_ARCHIVES foreign key (docId)
      references archives_doc (docId)
go

alter table doc_history
   add constraint FK_DHY_R_FA foreign key (fileId)
      references file_attach (fileId)
         on delete set null
go

alter table doc_privilege
   add constraint FK_DOC_PRIV_DP_R_DF_DOC_FOLD foreign key (folderId)
      references doc_folder (folderId)
         on delete cascade
go

alter table doc_privilege
   add constraint FK_DOC_PRIV_DP_R_DT_DOCUMENT foreign key (docId)
      references document (docId)
         on delete cascade
go

alter table document
   add constraint FK_DOCUMENT_DT_R_AU_APP_USER foreign key (userId)
      references app_user (userId)
go

alter table document
   add constraint FK_DOCUMENT_DT_R_DF_DOC_FOLD foreign key (folderId)
      references doc_folder (folderId)
         on delete set null
go

alter table duty
   add constraint FK_DUTY_DUY_R_AU_APP_USER foreign key (userId)
      references app_user (userId)
go

alter table duty
   add constraint FK_DUTY_DUY_R_DS_DUTY_SYS foreign key (systemId)
      references duty_system (systemId)
go

alter table duty_register
   add constraint FK_DUTY_REG_DR_R_AU_APP_USER foreign key (userId)
      references app_user (userId)
go

alter table duty_register
   add constraint FK_DUTY_REG_DR_R_DS_DUTY_SEC foreign key (sectionId)
      references duty_section (sectionId)
go

alter table emp_profile
   add constraint FK_EMP_PROF_EPF_R_AU_APP_USER foreign key (userId)
      references app_user (userId)
go

alter table emp_profile
   add constraint FK_EMP_PROF_PT_R_EPF_POSITION foreign key (jobId)
      references position (pos_id)
go

alter table emp_profile
   add constraint FK_EMP_PROF_SD_R_SY_STAND_SA foreign key (standardId)
      references stand_salary (standardId)
go

alter table errands_register
   add constraint FK_ERRANDS__ERP_R_AU_APP_USER foreign key (approvalId)
      references app_user (userId)
go

alter table errands_register
   add constraint FK_ERRANDS__ER_R_AU_APP_USER foreign key (userId)
      references app_user (userId)
go

alter table field_rights
   add constraint FK_FIELD_RI_FR_R_FDM_FORM_DEF foreign key (mappingId)
      references form_def_mapping (mappingId)
         on delete cascade
go

alter table field_rights
   add constraint FK_FIELD_RI_FR_R_FF_FORM_FIE foreign key (fieldId)
      references form_field (fieldId)
         on delete cascade
go

alter table file_attach
   add constraint FK_FLE_AT_R_GT foreign key (proTypeId)
      references global_type (proTypeId)
         on delete set null
go

alter table fixed_assets
   add constraint FK_FIXED_AS_FA_R_AT_ASSETS_T foreign key (assetsTypeId)
      references assets_type (assetsTypeId)
go

alter table fixed_assets
   add constraint FK_FIXED_AS_FA_R_DT_DEPRE_TY foreign key (depreTypeId)
      references depre_type (depreTypeId)
go

alter table form_def_mapping
   add constraint FK_FORM_DEF_FDM_R_FD_FORM_DEF foreign key (formDefId)
      references form_def (formDefId)
         on delete cascade
go

alter table form_def_mapping
   add constraint FK_FORM_DEF_FDM_R_PD_PRO_DEFI foreign key (defId)
      references pro_definition (defId)
         on delete cascade
go

alter table form_field
   add constraint FK_FORM_FIE_FF_R_FD_FORM_TAB foreign key (tableId)
      references form_table (tableId)
         on delete cascade
go

alter table form_table
   add constraint FK_FORM_TAB_FT_R_FD_FORM_DEF foreign key (formDefId)
      references form_def (formDefId)
         on update no action
go

alter table form_template
   add constraint FK_FORM_TEM_FT_R_FDM_FORM_DEF foreign key (mappingId)
      references form_def_mapping (mappingId)
         on delete set null
go

alter table fun_url
   add constraint FK_FUN_URL_FU_R_AFN_APP_FUNC foreign key (functionId)
      references app_function (functionId)
go

alter table goods_apply
   add constraint FK_GOODS_AP_GA_R_OG_OFFICE_G foreign key (goodsId)
      references office_goods (goodsId)
go

alter table in_message
   add constraint FK_IN_MESSA_IM_R_AU_APP_USER foreign key (userId)
      references app_user (userId)
go

alter table in_message
   add constraint FK_IN_MESSA_IM_R_SM_SHORT_ME foreign key (messageId)
      references short_message (messageId)
go

alter table in_stock
   add constraint FK_IN_STOCK_IS_R_OG_OFFICE_G foreign key (goodsId)
      references office_goods (goodsId)
go

alter table index_display
   add constraint FK_INDEX_DI_ID_R_AU_APP_USER foreign key (userId)
      references app_user (userId)
go

alter table job_change
   add constraint FK_JOB_CH_R_POS_NEW foreign key (newJobId)
      references position (pos_id)
         on delete cascade
go

alter table job_change
   add constraint FK_JOB_CH_R_POS foreign key (orgJobId)
      references position (pos_id)
         on update no action
go

alter table mail
   add constraint FK_MAIL_ML_R_AU_APP_USER foreign key (senderId)
      references app_user (userId)
go

alter table mail_attach
   add constraint FK_MAIL_ATT_MA_R_FA_FILE_ATT foreign key (fileId)
      references file_attach (fileId)
go

alter table mail_attach
   add constraint FK_MAIL_ATT_MA_R_ML_MAIL foreign key (mailId)
      references mail (mailId)
go

alter table mail_box
   add constraint FK_MAIL_BOX_MB_R_AU_APP_USER foreign key (userId)
      references app_user (userId)
go

alter table mail_box
   add constraint FK_MAIL_BOX_MB_R_FD_MAIL_FOL foreign key (folderId)
      references mail_folder (folderId)
go

alter table mail_box
   add constraint FK_MAIL_BOX_MB_R_ML_MAIL foreign key (mailId)
      references mail (mailId)
go

alter table mail_folder
   add constraint FK_MAIL_FOL_FD_R_AU_APP_USER foreign key (userId)
      references app_user (userId)
go

alter table meeting_attend
   add constraint FK_MEETING__MTA_R_MT_MEETING foreign key (mettingId)
      references meeting (mettingId)
go

alter table meeting_file
   add constraint FK_MEETING__MF_R_FA_FILE_ATT foreign key (fileId)
      references file_attach (fileId)
go

alter table meeting_file
   add constraint FK_MEETING__MF_R_MT_MEETING foreign key (mettingId)
      references meeting (mettingId)
go

alter table news
   add constraint FK_NEWS_NEWS_R_SC_SECTION foreign key (sectionId)
      references section (sectionId)
         on delete set null
go

alter table news_comment
   add constraint FK_NEWS_COM_NC_R_AU_APP_USER foreign key (userId)
      references app_user (userId)
go

alter table news_comment
   add constraint FK_NEWS_COM_NC_R_NS_NEWS foreign key (newsId)
      references news (newsId)
go

alter table office_goods
   add constraint FK_OFFICE_G_OG_R_OGT_OFFICE_G foreign key (typeId)
      references office_goods_type (typeId)
go

alter table organization
   add constraint FK_ORG_R_DMS foreign key (dem_id)
      references demension (dem_id)
         on delete cascade
go

alter table out_mail
   add constraint FK_OUT_MAIL_OM_R_OMF_OUT_MAIL foreign key (folderId)
      references out_mail_folder (folderId)
go

alter table out_mail
   add constraint FK_O_M_R_O_M_S foreign key (setId)
      references out_mail_user_seting (setId)
go

alter table out_mail_file
   add constraint FK_OUT_MAIL_OMF_R_FA_FILE_ATT foreign key (fileId)
      references file_attach (fileId)
go

alter table out_mail_file
   add constraint FK_OUT_MAIL_OMF_R_OM_OUT_MAIL foreign key (mailId)
      references out_mail (mailId)
go

alter table out_mail_folder
   add constraint FK_OUT_MAIL_OMF_RAU_APP_USER foreign key (userId)
      references app_user (userId)
go

alter table out_mail_folder
   add constraint FK_O_M_F_R_O_M_S foreign key (setId)
      references out_mail_user_seting (setId)
go

alter table out_mail_user_seting
   add constraint FK_OUT_MAIL_OMU_R_AU_APP_USER foreign key (userId)
      references app_user (userId)
         on delete set null
go

alter table paint_template
   add constraint FK_PTE_R_FA foreign key (fileId)
      references file_attach (fileId)
         on delete cascade
go

alter table phone_book
   add constraint FK_PHONE_BO_PB_R_AU_APP_USER foreign key (userId)
      references app_user (userId)
go

alter table phone_book
   add constraint FK_PHONE_BO_PB_R_PG_PHONE_GR foreign key (groupId)
      references phone_group (groupId)
go

alter table phone_group
   add constraint FK_PHONE_GR_PG_R_AU_APP_USER foreign key (userId)
      references app_user (userId)
go

alter table plan_attend
   add constraint FK_PLAN_ATT_PAD_R_UA_APP_USER foreign key (userId)
      references app_user (userId)
go

alter table plan_attend
   add constraint FK_PLAN_ATT_PAD_R_WP_WORK_PLA foreign key (planId)
      references work_plan (planId)
go

alter table plan_file
   add constraint FK_PLAN_FIL_PA_R_FA_FILE_ATT foreign key (fileId)
      references file_attach (fileId)
go

alter table plan_file
   add constraint FK_PLAN_FIL_PA_R_WP_WORK_PLA foreign key (planId)
      references work_plan (planId)
go

alter table position_sub
   add constraint FK_POS_SUB_R_POS foreign key (mainPositionId)
      references position (pos_id)
         on delete cascade
go

alter table position_sub
   add constraint FK_POS_SUB_SUB_R_POS foreign key (subPositionId)
      references position (pos_id)
         on update no action
go

alter table pro_def_rights
   add constraint FK_PRO_DEF__PDR_R_GT_GLOBAL_T foreign key (proTypeId)
      references global_type (proTypeId)
         on delete set null
go

alter table pro_def_rights
   add constraint FK_PRO_DEF__PDR_R_PD_PRO_DEFI foreign key (defId)
      references pro_definition (defId)
         on delete cascade
go

alter table pro_definition
   add constraint FK_PRO_DEFI_PD_R_GT_GLOBAL_T foreign key (proTypeId)
      references global_type (proTypeId)
         on delete set null
go

alter table pro_node_set
   add constraint FK_PRO_NODE_SET_PRO_DEF foreign key (defId)
      references pro_definition (defId)
        on delete cascade
go

alter table pro_user_set
   add constraint FK_PRO_USER_SET_DEMENSIO foreign key (demId)
      references demension (dem_id)
go

alter table process_form
   add constraint FK_PROCESS__PF_R_PR_PROCESS_ foreign key (runId)
      references process_run (runId)
go

alter table process_module
   add constraint FK_PM_R_PDI foreign key (defId)
      references pro_definition (defId)
         on delete cascade
go

alter table process_run
   add constraint FK_PRORN__R_FORM_DEF foreign key (formDefId)
      references form_def (formDefId)
         on delete set null
go

alter table process_run
   add constraint FK_PROCESS__PR_R_AU_APP_USER foreign key (userId)
      references app_user (userId)
go

alter table process_run
   add constraint FK_PROCESS__PR_R_PD_PRO_DEFI foreign key (defId)
      references pro_definition (defId)
go

alter table product
   add constraint FK_PRODUCT_PD_R_PUT_PROVIDER foreign key (providerId)
      references provider (providerId)
go

alter table project
   add constraint FK_PROJECT_PR_R_CS_CUSTOMER foreign key (customerId)
      references customer (customerId)
go

alter table project
   add constraint FK_PROJECT_PT_R_AU_APP_USER foreign key (userId)
      references app_user (userId)
go

alter table project_file
   add constraint FK_PROJECT__PF_R_FA_FILE_ATT foreign key (fileId)
      references file_attach (fileId)
go

alter table project_file
   add constraint FK_PROJECT__PF_R_PT_PROJECT foreign key (projectId)
      references project (projectId)
go

alter table reg_attach
   add constraint FK_REG_ATTA_RA_R_FA_FILE_ATT foreign key (fileId)
      references file_attach (fileId)
         on delete cascade
go

alter table reg_attach
   add constraint FK_REG_ATTA_RA_R_GT_REGULATI foreign key (regId)
      references regulation (regId)
         on delete cascade
go

alter table regulation
   add constraint FK_RG_R_GT foreign key (proTypeId)
      references global_type (proTypeId)
         on delete set null
go

alter table relative_user
   add constraint FK_RELATIVE_RU_R_AU_APP_USER foreign key (userId)
      references app_user (userId)
         on delete cascade
go

alter table relative_user
   add constraint FK_RELATIVE_RU_R_RJ_RELATIVE foreign key (reJobId)
      references relative_job (reJobId)
         on delete cascade
go

alter table report_param
   add constraint FK_REPORT_P_RP_R_RPT_REPORT_T foreign key (reportId)
      references report_template (reportId)
go

alter table resume_file
   add constraint FK_RESUME_F_RMF_R_FA_FILE_ATT foreign key (fileId)
      references file_attach (fileId)
go

alter table resume_file
   add constraint FK_RESUME_F_RMF_R_RM_RESUME foreign key (resumeId)
      references resume (resumeId)
go

alter table role_fun
   add constraint FK_ROLE_FUN_RF_R_AFN_APP_FUNC foreign key (functionId)
      references app_function (functionId)
go

alter table role_fun
   add constraint FK_ROLE_FUN_RF_R_AR_APP_ROLE foreign key (roleId)
      references app_role (roleId)
go

alter table role_position
   add constraint FK_ROL_POS_R_POS foreign key (pos_id)
      references position (pos_id)
         on delete cascade
go

alter table role_position
   add constraint FK_ROLE_POS_RPOS_R_AP_APP_ROLE foreign key (roleId)
      references app_role (roleId)
go

alter table roll_file
   add constraint FK_RF_R_AR foreign key (rollId)
      references arch_roll (rollId)
         on delete set null
go

alter table roll_file
   add constraint FK_RF_R_GT foreign key (proTypeId)
      references global_type (proTypeId)
         on delete set null
go

alter table roll_file_list
   add constraint FK_ROLL_FIL_RFL_R_F_A_FILE_ATT foreign key (fileId)
      references file_attach (fileId)
         on delete cascade
go

alter table roll_file_list
   add constraint FK_RFL_R_RF foreign key (rollFileId)
      references roll_file (rollFileId)
         on delete cascade
go

alter table run_data
   add constraint FK_FD_R_PR foreign key (runId)
      references process_run (runId)
         on delete cascade
go

alter table seal
   add constraint FK_SE_R_FA foreign key (fileId)
      references file_attach (fileId)
         on delete cascade
go

alter table short_message
   add constraint FK_SHORT_ME_SM_R_AU_APP_USER foreign key (senderId)
      references app_user (userId)
go

alter table stand_salary_item
   add constraint FK_STAND_SA_SSI_R_SSY_STAND_SA foreign key (standardId)
      references stand_salary (standardId)
go

alter table subordinate
   add constraint FK_SUBORDINATE_D_DEM foreign key (dem_id)
      references demension (dem_id)
go

alter table subordinate
   add constraint FK_SUBORDINATE_J_APPUSER foreign key (jobuserId)
      references app_user (userId)
go

alter table subordinate
   add constraint FK_SUBORDINATE_U_APPUSER foreign key (userId)
      references app_user (userId)
go

alter table task_sign
   add constraint FK_TASK_SIG_TS_R_PRO_NODE_SET foreign key (setId)
      references pro_node_set (setId)
go
	
alter table user_org
   add constraint FK_USER_ORG_USER_ORG__APP_USER foreign key (userId)
      references app_user (userId)
go

alter table user_org
   add constraint FK_USER_ORG_R_ORG foreign key (org_id)
      references organization (org_id)
         on delete cascade
go

alter table user_position
   add constraint FK_USER_POS_R_POSITION foreign key (pos_id)
      references position (pos_id)
         on delete cascade
go

alter table user_position
   add constraint FK_USER_POS_USER_POS__APP_USER foreign key (userId)
      references app_user (userId)
go

alter table user_role
   add constraint FK_UR_R_AR foreign key (roleId)
      references app_role (roleId)
go

alter table user_role
   add constraint FK_UR_R_AU foreign key (userId)
      references app_user (userId)
go

alter table work_plan
   add constraint FK_WP_R_GT foreign key (proTypeId)
      references global_type (proTypeId)
go

alter table work_plan
   add constraint FK_WORK_PLA_WP_R_AU_APP_USER foreign key (userId)
      references app_user (userId)
go

