/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2012-3-7 17:53:15                            */
/*==============================================================*/


/*==============================================================*/
/* Table: app_function                                          */
/*==============================================================*/
create table app_function
(
   functionId           bigint not null auto_increment,
   funKey               varchar(64) not null comment '权限Key',
   funName              varchar(128) not null comment '权限名称',
   primary key (functionId),
   unique key AK_UQ_RSKEY (funKey)
);

/*==============================================================*/
/* Table: app_role                                              */
/*==============================================================*/
create table app_role
(
   roleId               bigint not null auto_increment,
   roleName             varchar(128) not null comment '角色名称',
   roleDesc             varchar(128) comment '角色描述',
   status               smallint not null comment '状态',
   rights               text,
   isDefaultIn          smallint not null,
   primary key (roleId)
);

alter table app_role comment '角色表';

/*==============================================================*/
/* Table: app_tips                                              */
/*==============================================================*/
create table app_tips
(
   tipsId               bigint not null auto_increment,
   userId               bigint not null,
   tipsName             varchar(128),
   content              varchar(2048),
   disheight            bigint,
   diswidth             bigint,
   disleft              bigint,
   distop               bigint,
   dislevel             bigint,
   createtime           datetime not null,
   primary key (tipsId)
);

alter table app_tips comment '用户便签';

/*==============================================================*/
/* Table: app_user                                              */
/*==============================================================*/
create table app_user
(
   userId               bigint not null auto_increment comment '主键',
   username             varchar(128) not null comment '用户名',
   title                smallint not null comment '1=先生
            0=女士
            小姐',
   depId                bigint,
   password             varchar(128) not null comment '密码',
   email                varchar(128) not null comment '邮件',
   jobId                bigint comment '职位',
   phone                varchar(32) comment '电话',
   mobile               varchar(32) comment '手机',
   fax                  varchar(32) comment '传真',
   address              varchar(64) comment '地址',
   zip                  varchar(32) comment '邮编',
   photo                varchar(128) comment '相片',
   accessionTime        datetime not null comment '入职时间',
   status               smallint not null comment '状态
            1=激活
            0=禁用
            2=离职
            ',
   education            varchar(64),
   fullname             varchar(50) not null,
   delFlag              smallint not null comment '0=未删除
            1=删除',
   primary key (userId)
);

alter table app_user comment 'app_user
用户表';

/*==============================================================*/
/* Table: appointment                                           */
/*==============================================================*/
create table appointment
(
   appointId            bigint not null auto_increment,
   userId               bigint comment '主键',
   subject              varchar(128) not null comment '主题',
   startTime            datetime not null comment '开始时间',
   endTime              datetime not null comment '结束时间',
   content              text not null comment '约会内容',
   notes                varchar(1000) comment '备注',
   location             varchar(150) not null comment '地点',
   inviteEmails         varchar(1000),
   sendMessage          smallint,
   sendMail             smallint,
   primary key (appointId)
);

alter table appointment comment '约会管理';

/*==============================================================*/
/* Table: arch_dispatch                                         */
/*==============================================================*/
create table arch_dispatch
(
   dispatchId           bigint not null auto_increment,
   archivesId           bigint,
   dispatchTime         datetime not null,
   userId               bigint not null,
   fullname             varchar(128),
   isRead               smallint,
   subject              varchar(256),
   readFeedback         varchar(1024),
   archUserType         smallint not null default 0 comment '0=阅读人员
            1=承办人
            2=分发负责人
            ',
   disRoleId            bigint,
   disRoleName          varchar(64),
   primary key (dispatchId)
);

/*==============================================================*/
/* Table: arch_flow_conf                                        */
/*==============================================================*/
create table arch_flow_conf
(
   configId             bigint not null auto_increment,
   defId                bigint,
   processName          varchar(128) not null,
   archType             smallint not null comment '0=发文
            1=收文',
   depId                bigint,
   primary key (configId)
);

alter table arch_flow_conf comment '公文流程设置';

/*==============================================================*/
/* Table: arch_fond                                             */
/*==============================================================*/
create table arch_fond
(
   archFondId           bigint not null auto_increment,
   afNo                 varchar(64) not null comment '全宗号',
   afName               varchar(128) not null comment '全宗名',
   shortDesc            varchar(4000) comment '全宗概述',
   descp                text comment '全宗描述',
   clearupDesc          varchar(4000) comment '全宗整理描述',
   createTime           datetime comment '创建时间',
   updateTime           datetime comment '最后更新时间',
   creatorName          varchar(32) comment '创建人',
   creatorId            bigint comment '创建人ID',
   caseNums             bigint comment '案件数',
   status               smallint comment '0草稿
            1启用
            -1禁用
            ',
   proTypeId            bigint,
   typeName             varchar(128) comment '全宗分类名称',
   openStyle            varchar(64) comment '开放形式(来自数字字典)',
   primary key (archFondId)
);

/*==============================================================*/
/* Table: arch_hasten                                           */
/*==============================================================*/
create table arch_hasten
(
   recordId             bigint not null auto_increment,
   archivesId           bigint,
   content              varchar(1024) comment '催办内容',
   createtime           datetime comment '催办时间',
   hastenFullname       varchar(64) comment '催办人',
   handlerFullname      varchar(64) comment '承办人',
   handlerUserId        bigint comment '承办人ID',
   primary key (recordId)
);

/*==============================================================*/
/* Table: arch_roll                                             */
/*==============================================================*/
create table arch_roll
(
   rollId               bigint not null auto_increment,
   archFondId           bigint,
   proTypeId            bigint,
   typeName             varchar(32),
   rolllName            varchar(128) not null,
   afNo                 varchar(64) not null,
   rollNo               varchar(64) not null,
   catNo                varchar(64),
   timeLimit            varchar(64) comment '保管期限
            长久
            长期
            短期
            10年
            15年
            20年
            
            ',
   startTime            datetime comment '起始日期',
   endTime              datetime comment '结束日期',
   openStyle            varchar(64) comment '开放形式',
   author               varchar(32) comment '立卷人',
   setupTime            datetime comment '立卷时间',
   checker              varchar(32) comment '检查人',
   creatorName          varchar(32) comment '录入人',
   createTime           datetime comment '录入时间',
   keyWords             varchar(512),
   editCompany          varchar(128),
   editDep              varchar(128),
   decp                 text,
   status               smallint comment '1=正常
            0=销毁 ',
   primary key (rollId)
);

/*==============================================================*/
/* Table: arch_template                                         */
/*==============================================================*/
create table arch_template
(
   templateId           bigint not null auto_increment,
   fileId               bigint,
   proTypeId            bigint,
   tempName             varchar(128) not null comment '模板名称',
   tempPath             varchar(256) not null comment '路径',
   primary key (templateId)
);

alter table arch_template comment '公文模板';

/*==============================================================*/
/* Table: archives                                              */
/*==============================================================*/
create table archives
(
   archivesId           bigint not null auto_increment,
   typeName             varchar(128) comment '公文类型名称',
   archivesNo           varchar(100) not null comment '发文字号',
   issueDep             varchar(128) comment '发文机关或部门',
   proTypeId            bigint,
   glo_proTypeId        bigint,
   subject              varchar(256) not null comment '文件标题',
   createtime           datetime not null,
   issueDate            datetime not null comment '发布日期',
   status               varchar(256) not null comment '公文状态
            0=拟稿、修改状态
            1=发文状态
            2=归档状态',
   shortContent         varchar(1024) comment '内容简介',
   fileCounts           bigint default 0 comment '文件数',
   privacyLevel         varchar(50) default '普通' comment '秘密等级
            普通
            秘密
            机密
            绝密',
   urgentLevel          varchar(50) default '普通' comment '紧急程度
            普通
            紧急
            特急
            特提',
   issuer               varchar(50) comment '发文人',
   issuerId             bigint comment '发文人ID',
   keywords             varchar(256) comment '主题词',
   sources              varchar(50) comment '公文来源
            仅在收文中指定，发公文不需要指定
            上级公文
            下级公文',
   archType             smallint not null default 0 comment '0=发文
            1=收文',
   recDepIds            varchar(2000) comment '用于存储接收公文的部门ID,使用,进行分开',
   recDepNames          varchar(2000) comment '用于存储接收公文的部门的名称，使用,进行分开',
   handlerUids          varchar(256) comment '在收文中使用，多个用户ID用'',''分割',
   handlerUnames        varchar(256) comment '用于收文，存储多个拟办用户名，用‘，’分割',
   orgArchivesId        bigint comment '用于收文时使用，指向原公文ID',
   depSignNo            varchar(100) comment '用于收文时，部门对自身的公文自编号',
   runId                bigint comment '流程运行id
            通过该id可以查看该公文的审批历史',
   archStatus           smallint comment '归档状态
            1=已经归档
            0=尚未归档',
   depId                bigint,
   primary key (archivesId)
);

alter table archives comment '收发公文';

/*==============================================================*/
/* Table: archives_dep                                          */
/*==============================================================*/
create table archives_dep
(
   archDepId            bigint not null auto_increment comment '主键',
   signNo               varchar(128) comment '自编号',
   archivesId           bigint not null comment '所属公文',
   subject              varchar(256) not null comment '公文标题',
   status               smallint not null comment '签收状态
            0=未签收
            1=已签收',
   signTime             datetime comment '签收日期',
   signFullname         varchar(64) comment '签收人',
   signUserID           bigint,
   handleFeedback       varchar(4000) comment '办理结果反馈',
   isMain               smallint not null default 1 comment '主送、抄送
            1=主送
            0=抄送',
   depId                bigint,
   primary key (archDepId)
);

/*==============================================================*/
/* Table: archives_doc                                          */
/*==============================================================*/
create table archives_doc
(
   docId                bigint not null auto_increment,
   archivesId           bigint,
   fileId               bigint,
   creator              varchar(64) comment '拟稿人',
   creatorId            bigint comment '拟稿人ID',
   menderId             bigint,
   mender               varchar(64) comment '修改人',
   docName              varchar(128) not null comment '文档名称',
   docStatus            smallint not null comment '文档状态
            0=修改中
            1=修改完成',
   curVersion           bigint not null comment '当前版本
            取当前最新的版本',
   docPath              varchar(128) not null comment '文档路径',
   updatetime           datetime not null comment '更新时间',
   createtime           datetime not null comment '创建时间',
   primary key (docId)
);

/*==============================================================*/
/* Table: assets_type                                           */
/*==============================================================*/
create table assets_type
(
   assetsTypeId         bigint not null auto_increment,
   typeName             varchar(128) not null comment '分类名称',
   primary key (assetsTypeId)
);

/*==============================================================*/
/* Table: board_type                                            */
/*==============================================================*/
create table board_type
(
   typeId               bigint not null auto_increment,
   typeName             varchar(128) not null,
   typeDesc             varchar(4000) not null,
   primary key (typeId)
);

/*==============================================================*/
/* Table: boardroo                                              */
/*==============================================================*/
create table boardroo
(
   roomId               bigint not null auto_increment,
   roomName             varchar(128) not null comment '会议室名称',
   roomDesc             varchar(4000) comment '会议室描述',
   containNum           bigint default 0 comment '容纳人数',
   primary key (roomId)
);

/*==============================================================*/
/* Table: book                                                  */
/*==============================================================*/
create table book
(
   bookId               bigint not null auto_increment,
   typeId               bigint,
   bookName             varchar(128) not null,
   author               varchar(128) not null,
   isbn                 varchar(64) not null,
   publisher            varchar(128),
   price                numeric(8,0) not null,
   location             varchar(128) not null,
   department           varchar(64) not null,
   amount               bigint not null,
   leftAmount           bigint not null,
   primary key (bookId)
);

alter table book comment '图书';

/*==============================================================*/
/* Table: book_bor_ret                                          */
/*==============================================================*/
create table book_bor_ret
(
   recordId             bigint not null auto_increment,
   bookSnId             bigint,
   borrowTime           datetime not null,
   returnTime           datetime not null,
   lastReturnTime       datetime,
   borrowIsbn           varchar(128) not null,
   bookName             varchar(128) not null,
   registerName         varchar(32) not null,
   fullname             varchar(32) not null,
   primary key (recordId)
);

alter table book_bor_ret comment '图书借还表';

/*==============================================================*/
/* Table: book_sn                                               */
/*==============================================================*/
create table book_sn
(
   bookSnId             bigint not null auto_increment,
   bookId               bigint not null,
   bookSN               varchar(128) not null,
   status               smallint not null comment '借阅状态
            0=未借出
            1=借出
            2=预订
            3=注销',
   primary key (bookSnId)
);

/*==============================================================*/
/* Table: book_type                                             */
/*==============================================================*/
create table book_type
(
   typeId               bigint not null auto_increment,
   typeName             varchar(128) not null,
   primary key (typeId)
);

alter table book_type comment '图书类别';

/*==============================================================*/
/* Table: borrow_file_list                                      */
/*==============================================================*/
create table borrow_file_list
(
   listId               bigint not null auto_increment,
   recordId             bigint not null,
   listType             varchar(64) comment '0:全宗
            1:案卷
            2:文件',
   archFondId           bigint,
   afNo                 varchar(64) comment '全宗号',
   afName               varchar(128) comment '全宗名',
   rollId               bigint,
   rollNo               varchar(64),
   rolllName            varchar(128),
   rollFileId           bigint,
   fileNo               varchar(64) comment '文件编号',
   fileName             varchar(128) comment '文件题名',
   primary key (listId)
);

alter table borrow_file_list comment '文件借阅清单';

/*==============================================================*/
/* Table: borrow_record                                         */
/*==============================================================*/
create table borrow_record
(
   recordId             bigint not null auto_increment,
   borrowDate           datetime,
   borrowType           varchar(64),
   borrowReason         varchar(64),
   checkUserId          bigint,
   checkUserName        varchar(64),
   checkDate            datetime,
   returnDate           datetime,
   borrowNum            varchar(128),
   borrowRemark         varchar(128),
   returnStatus         smallint,
   checkId              bigint,
   checkName            varchar(64),
   checkContent         varchar(128),
   primary key (recordId)
);

/*==============================================================*/
/* Table: cal_file                                              */
/*==============================================================*/
create table cal_file
(
   fileId               bigint not null,
   planId               bigint not null,
   primary key (fileId, planId)
);

/*==============================================================*/
/* Table: calendar_plan                                         */
/*==============================================================*/
create table calendar_plan
(
   planId               bigint not null auto_increment,
   startTime            datetime comment '开始时间',
   endTime              datetime comment '结束时间',
   urgent               smallint not null comment '紧急程度
            0=一般
            1=重要
            2=紧急',
   summary              varchar(200),
   content              varchar(1200) not null comment '内容',
   status               smallint not null comment '状态
            0=未完成
            1=完成',
   userId               bigint not null comment '员工ID',
   fullname             varchar(32) comment '员工名',
   assignerId           bigint not null comment '分配人',
   assignerName         varchar(32) comment '分配人名',
   feedback             varchar(500) comment '反馈意见',
   showStyle            smallint not null comment '显示方式
            1=仅在任务中显示
            2=在日程与任务中显示',
   taskType             smallint not null comment '任务类型
            1=限期任务
            2=非限期任务',
   primary key (planId)
);

alter table calendar_plan comment '日程安排';

/*==============================================================*/
/* Table: car                                                   */
/*==============================================================*/
create table car
(
   carId                bigint not null auto_increment,
   carNo                varchar(128) not null,
   carType              varchar(64) not null comment '轿车
            货车
            商务车
            ',
   engineNo             varchar(128),
   buyInsureTime        datetime comment '购买保险时间',
   auditTime            datetime comment '年审时间',
   notes                varchar(500),
   factoryModel         varchar(64) not null,
   driver               varchar(32) not null,
   buyDate              datetime not null comment '购置日期',
   status               smallint not null comment '当前状态
            1=可用
            2=维修中
            0=报废',
   cartImage            varchar(128),
   primary key (carId)
);

alter table car comment '车辆信息';

/*==============================================================*/
/* Table: car_apply                                             */
/*==============================================================*/
create table car_apply
(
   applyId              bigint not null auto_increment,
   carId                bigint not null,
   department           varchar(64) not null,
   userFullname         varchar(32) not null,
   applyDate            datetime not null,
   reason               varchar(512) not null,
   startTime            datetime not null,
   endTime              datetime,
   userId               bigint not null,
   proposer             varchar(32) not null,
   mileage              numeric(18,2),
   oilUse               numeric(18,2),
   notes                varchar(128),
   approvalStatus       smallint not null,
   primary key (applyId)
);

alter table car_apply comment '车辆申请';

/*==============================================================*/
/* Table: cart_repair                                           */
/*==============================================================*/
create table cart_repair
(
   repairId             bigint not null auto_increment,
   carId                bigint,
   repairDate           datetime not null comment '维护日期',
   reason               varchar(128) not null comment '维护原因',
   executant            varchar(128) not null comment '经办人',
   notes                varchar(128) comment '备注',
   repairType           varchar(128) not null comment '维修类型
            保养
            维修',
   fee                  numeric(18,2) comment '费用',
   primary key (repairId)
);

/*==============================================================*/
/* Table: company                                               */
/*==============================================================*/
create table company
(
   companyId            bigint not null auto_increment,
   companyNo            varchar(128),
   companyName          varchar(128) not null,
   companyDesc          varchar(4000),
   legalPerson          varchar(32),
   setup                datetime,
   phone                varchar(32),
   fax                  varchar(32),
   site                 varchar(128),
   logo                 varchar(128),
   primary key (companyId)
);

alter table company comment '公司信息';

/*==============================================================*/
/* Table: conf_attach                                           */
/*==============================================================*/
create table conf_attach
(
   confId               bigint,
   fileId               bigint,
   primary key (confId,fileId)
);

/*==============================================================*/
/* Table: conf_attend                                           */
/*==============================================================*/
create table conf_attend
(
   attendId             bigint not null auto_increment,
   confId               bigint comment '会议ID',
   userId               bigint,
   userType             smallint default 3 comment '1=主持人
            2=记录人
            3=参与会议人',
   fullname             varchar(50),
   primary key (attendId)
);

/*==============================================================*/
/* Table: conf_privilege                                        */
/*==============================================================*/
create table conf_privilege
(
   privilegeId          bigint not null auto_increment,
   confId               bigint,
   userId               bigint not null,
   fullname             varchar(64) not null,
   rights               smallint not null comment '权限
            1=查看
            2=修改
            3=建立纪要',
   primary key (privilegeId)
);

/*==============================================================*/
/* Table: conf_sum_attach                                       */
/*==============================================================*/
create table conf_sum_attach
(
   sumId                bigint,
   fileId               bigint,
   primary key(sumId,fileId)
);

/*==============================================================*/
/* Table: conf_summary                                          */
/*==============================================================*/
create table conf_summary
(
   sumId                bigint not null auto_increment,
   confId               bigint,
   createtime           datetime not null,
   creator              varchar(32) not null,
   sumContent           text not null,
   status               smallint comment '状态
            0=待发送
            1=发送',
   primary key (sumId)
);

/*==============================================================*/
/* Table: conference                                            */
/*==============================================================*/
create table conference
(
   confId               bigint not null auto_increment,
   confTopic            varchar(128) not null comment '会议议题',
   confProperty         varchar(64) comment '会议性质',
   importLevel          smallint not null comment '重要级别
            1=普通
            2=重要
            3=非常重要',
   feeBudget            numeric(18,2) comment '费用预算',
   compereName          varchar(256),
   compere              varchar(128) comment '主持人
            (允许多人，用,分开)',
   recorder             varchar(128) comment '记录人
            (允许多人，用,分开)',
   recorderName         varchar(256),
   attendUsers          varchar(500) comment '参加人员列表',
   attendUsersName      varchar(4000),
   status               smallint not null comment '状态
            0=暂存
            1=发布',
   isEmail              smallint comment '邮件通知',
   isMobile             smallint comment '短信通知',
   startTime            datetime not null comment '开始时间',
   endTime              datetime not null comment '结束时间',
   roomId               bigint comment '会议室ID',
   typeId               bigint,
   roomName             varchar(64) comment '会议室',
   roomLocation         varchar(128) comment '会议地点',
   confContent          text comment '会议内容',
   createtime           datetime,
   sendtime             datetime,
   checkReason          varchar(512),
   checkUserId          bigint,
   checkName            varchar(64),
   primary key (confId)
);

/*==============================================================*/
/* Table: contract                                              */
/*==============================================================*/
create table contract
(
   contractId           bigint not null auto_increment,
   contractNo           varchar(64) not null comment '合同编号',
   subject              varchar(128) not null comment '合同标题',
   contractAmount       numeric(8,0) not null comment '合同金额',
   mainItem             varchar(4000) comment '主要条款',
   salesAfterItem       varchar(4000) comment '售后条款',
   validDate            datetime not null comment '生效日期',
   expireDate           datetime not null comment '有效期',
   serviceDep           varchar(64) comment '维修部门',
   serviceMan           varchar(64) comment '维修负责人',
   signupUser           varchar(64) not null comment '签约人',
   signupTime           datetime not null comment '签约时间',
   creator              varchar(32) not null comment '录入人',
   createtime           datetime not null comment '录入时间',
   projectId            bigint comment '所属项目',
   consignAddress       varchar(128) comment '收货地址',
   consignee            varchar(32) comment '收货人',
   primary key (contractId)
);

/*==============================================================*/
/* Table: contract_config                                       */
/*==============================================================*/
create table contract_config
(
   configId             bigint not null auto_increment,
   itemName             varchar(128) not null comment '设备名称',
   contractId           bigint,
   itemSpec             varchar(128) not null comment '设置规格',
   amount               numeric(18,2) not null comment '数量',
   notes                varchar(200) comment '备注',
   primary key (configId)
);

alter table contract_config comment '合同配置单';

/*==============================================================*/
/* Table: contract_file                                         */
/*==============================================================*/
create table contract_file
(
   fileId               bigint not null,
   contractId           bigint not null,
   primary key (fileId, contractId)
);

alter table contract_file comment '合同附件';

/*==============================================================*/
/* Table: cus_connection                                        */
/*==============================================================*/
create table cus_connection
(
   connId               bigint not null auto_increment,
   customerId           bigint not null,
   contactor            varchar(32) not null,
   startDate            datetime not null,
   endDate              datetime not null,
   content              varchar(512) not null,
   notes                varchar(1000),
   creator              varchar(32),
   primary key (connId)
);

alter table cus_connection comment 'business connection ';

/*==============================================================*/
/* Table: cus_linkman                                           */
/*==============================================================*/
create table cus_linkman
(
   linkmanId            bigint not null auto_increment,
   customerId           bigint not null comment '所属客户',
   fullname             varchar(32) not null comment '姓名',
   sex                  smallint not null comment '性别',
   position             varchar(32) comment '职位',
   phone                varchar(32) comment '电话',
   mobile               varchar(32) not null comment '手机',
   fax                  varchar(32),
   email                varchar(100) comment 'Email',
   msn                  varchar(100) comment 'MSN',
   qq                   varchar(64) comment 'QQ',
   birthday             datetime comment '生日',
   homeAddress          varchar(128) comment '家庭住址',
   homeZip              varchar(32) comment '邮编',
   homePhone            varchar(32) comment '家庭电话',
   hobby                varchar(100) comment '爱好',
   isPrimary            smallint not null comment '是否为主要联系人',
   notes                varchar(500) comment '备注',
   primary key (linkmanId)
);

alter table cus_linkman comment '客户联系人';

/*==============================================================*/
/* Table: customer                                              */
/*==============================================================*/
create table customer
(
   customerId           bigint not null auto_increment,
   customerNo           varchar(64) not null comment '客户号
            自动生成',
   industryType         varchar(64) not null comment '所属行业
            有缺省的选择，也可以输入',
   customerSource       varchar(64) comment '客户来源
            可编辑，可添加
            
            电话访问
            网络
            客户或朋友介绍',
   customerType         smallint not null comment '1=正式客户
            2=重要客户
            3＝潜在客户
            4＝无效客户',
   companyScale         bigint comment '1=1-20人
            2=20-50人
            3=50-100人
            4=100-200人
            5=200-500人
            6=500-1000 人
            7=1000人以上
            ',
   customerName         varchar(128) not null comment '客户名称
            一般为公司名称',
   customerManager      varchar(32) not null comment '负责该客户的经理',
   phone                varchar(32) not null comment '电话',
   fax                  varchar(32),
   site                 varchar(128),
   email                varchar(128),
   state                varchar(32),
   city                 varchar(32),
   zip                  varchar(32),
   address              varchar(100),
   registerFun          numeric(8,0),
   turnOver             numeric(8,0),
   currencyUnit         varchar(32) comment '注册资金及年营业额的货币单位
            可选可编辑
            人民币（默认）
            美元
            ',
   otherDesc            varchar(800),
   principal            varchar(32),
   openBank             varchar(64),
   accountsNo           varchar(64),
   taxNo                varchar(64),
   notes                varchar(500),
   rights               smallint not null comment '1=客户经理及上级经理有权查看
            2=公开
            3=共享人员有权查看',
   primary key (customerId)
);

alter table customer comment '客户信息';

/*==============================================================*/
/* Table: demension                                             */
/*==============================================================*/
create table demension
(
   dem_id               bigint not null auto_increment,
   dem_name             varchar(128) not null comment '维度名称',
   dem_desc             varchar(1024) comment '维度描述',
   dem_type             smallint not null comment '类型
            1=行政(为公司的整个组织结构，用户所属的部门即隶属于该维度)
            2=其他
            ',
   primary key (dem_id)
);

/*==============================================================*/
/* Table: SUBORDINATE    用户组织关系改造相关修改						*/
/*==============================================================*/
create table subordinate 
(
	subordinateId  bigint not null auto_increment,
	dem_id 			bigint not null comment '关系所属维度',
	userId 			bigint not null comment '主用户标示',
	jobUserId 	bigint not null comment '相对用户标示',
	relative smallint not null comment '上下级标识 1=上级 ，2同级，0下级',
	primary key (subordinateId)
);

/*==============================================================*/
/* Table: dep_pos                                               */
/*==============================================================*/
create table dep_pos
(
   org_id               bigint not null,
   pos_id               bigint not null,
   primary key (org_id, pos_id)
);

/*==============================================================*/
/* Table: depre_record                                          */
/*==============================================================*/
create table depre_record
(
   recordId             bigint not null auto_increment,
   assetsId             bigint not null,
   workCapacity         numeric(18,2),
   workGrossUnit        varchar(128),
   depreAmount          numeric(18,4) not null,
   calTime              datetime not null,
   primary key (recordId)
);

/*==============================================================*/
/* Table: depre_type                                            */
/*==============================================================*/
create table depre_type
(
   depreTypeId          bigint not null auto_increment,
   typeName             varchar(64) not null,
   deprePeriod          bigint not null comment '单位为月',
   typeDesc             varchar(500),
   calMethod            smallint not null comment '1=平均年限法
            2=工作量法
            加速折旧法
            3=双倍余额递减法
            4=年数总和法',
   primary key (depreTypeId)
);

alter table depre_type comment 'depreciation type';

/*==============================================================*/
/* Table: diary                                                 */
/*==============================================================*/
create table diary
(
   diaryId              bigint not null auto_increment,
   userId               bigint comment '主键',
   dayTime              datetime not null,
   content              text not null,
   diaryType            smallint not null comment '1=工作日志
            0=个人日志',
   primary key (diaryId)
);

/*==============================================================*/
/* Table: dictionary                                            */
/*==============================================================*/
create table dictionary
(
   dicId                bigint not null auto_increment,
   proTypeId            bigint,
   itemName             varchar(64) not null,
   itemValue            varchar(128) not null,
   descp                varchar(256),
   sn                   bigint,
   primary key (dicId)
);

alter table dictionary comment '数据字典';

/*==============================================================*/
/* Table: doc_file                                              */
/*==============================================================*/
create table doc_file
(
   fileId               bigint not null,
   docId                bigint not null,
   primary key (fileId, docId)
);

/*==============================================================*/
/* Table: doc_folder                                            */
/*==============================================================*/
create table doc_folder
(
   folderId             bigint not null auto_increment,
   userId               bigint comment '主键',
   folderName           varchar(128) not null comment '目录名称',
   parentId             bigint comment '父目录',
   path                 varchar(128) comment '路径
            为当前路径的＋上级路径
            如当前ID为3，上级目录的路径为1.2，
            则当前的路径为1.2.3.',
   isShared             smallint not null,
   descp                varchar(256),
   primary key (folderId)
);

/*==============================================================*/
/* Table: doc_history                                           */
/*==============================================================*/
create table doc_history
(
   historyId            bigint not null auto_increment,
   docId                bigint not null,
   fileId               bigint,
   docName              varchar(128) not null comment '文档名称',
   path                 varchar(128) not null comment '路径',
   version              bigint not null comment '版本',
   updatetime           datetime not null comment '更新时间',
   mender               varchar(64) not null comment '修改人',
   primary key (historyId)
);

/*==============================================================*/
/* Table: doc_privilege                                         */
/*==============================================================*/
create table doc_privilege
(
   privilegeId          bigint not null auto_increment,
   folderId             bigint,
   docId                bigint,
   rights               bigint not null comment '权限
            文档或目录的读写修改权限
            1=读
            2=修改
            4=删除
            
            权限值可以为上面的值之和
            如：3则代表进行读，修改的操作
            
            
            ',
   udrId                bigint,
   udrName              varchar(128),
   flag                 smallint not null comment '1=user
            2=deparment
            3=role',
   fdFlag               smallint not null comment '缺省为文件夹权限
            1=文档权限
            0=文件夹权限',
   primary key (privilegeId)
);

alter table doc_privilege comment '文档或目录的权限，只要是针对公共目录下的文档，个人的文档则不需要在这里进行权限的设置

某个目录';

/*==============================================================*/
/* Table: document                                              */
/*==============================================================*/
create table document
(
   docId                bigint not null auto_increment,
   docName              varchar(100) not null,
   content              text comment '内容',
   createtime           datetime not null comment '创建时间',
   updatetime           datetime,
   folderId             bigint,
   userId               bigint comment '主键',
   fullname             varchar(32) not null,
   haveAttach           smallint,
   sharedUserIds        varchar(1000) comment '共享员工ID',
   sharedUserNames      varchar(1000),
   sharedDepIds         varchar(1000) comment '共享部门ID',
   sharedDepNames       varchar(1000),
   sharedRoleIds        varchar(1000) comment '共享角色ID',
   sharedRoleNames      varchar(1000),
   isShared             smallint not null comment '是否共享',
   author               varchar(64),
   keywords             varchar(256),
   docType              varchar(64),
   swfPath              varchar(256),
   primary key (docId)
);

alter table document comment '文档';

/*==============================================================*/
/* Table: download_log                                          */
/*==============================================================*/
create table download_log
(
   logId                bigint not null auto_increment,
   username             varchar(64) not null,
   userId               bigint not null,
   fileId               bigint not null,
   downloadTIme         datetime not null,
   notes                varchar(1024),
   primary key (logId)
);

/*==============================================================*/
/* Table: duty                                                  */
/*==============================================================*/
create table duty
(
   dutyId               bigint not null auto_increment,
   userId               bigint not null comment '员工ID',
   fullname             varchar(32) not null comment '员工姓名',
   systemId             bigint not null comment '班制ID',
   startTime            datetime not null comment '开始时间',
   endTime              datetime comment '结束时间',
   primary key (dutyId)
);

alter table duty comment '排班';

/*==============================================================*/
/* Table: duty_register                                         */
/*==============================================================*/
create table duty_register
(
   registerId           bigint not null auto_increment,
   registerDate         datetime not null comment '登记时间',
   userId               bigint not null comment '登记人',
   fullname             varchar(32) not null,
   regFlag              smallint not null comment '登记标识
            1=正常登记（上班，下班）
            2＝迟到
            3=早退
            4＝休息
            5＝旷工
            6=放假
            
            ',
   regMins              bigint not null comment '迟到或早退分钟
            正常上班时为0',
   reason               varchar(128) comment '迟到原因',
   dayOfWeek            bigint not null comment '周几
            1=星期日
            ..
            7=日期六',
   inOffFlag            smallint not null comment '上下班标识
            1=签到
            2=签退',
   sectionId            bigint not null,
   primary key (registerId)
);

/*==============================================================*/
/* Table: duty_section                                          */
/*==============================================================*/
create table duty_section
(
   sectionId            bigint not null auto_increment,
   sectionName          varchar(64) not null,
   startSignin          datetime not null comment '开始签到',
   dutyStartTime        datetime not null comment '上班时间',
   endSignin            datetime not null comment '签到结束时间',
   earlyOffTime         datetime not null comment '早退计时',
   dutyEndTime          datetime not null comment '下班时间',
   signOutTime          datetime not null comment '签退结束',
   primary key (sectionId)
);

alter table duty_section comment '班次
也称为班段，一天可以分为两个或三个班段
';

/*==============================================================*/
/* Table: duty_system                                           */
/*==============================================================*/
create table duty_system
(
   systemId             bigint not null auto_increment,
   systemName           varchar(128) not null comment '班制名称',
   systemSetting        varchar(128) not null comment '班次
            班次的数据结构为：
            如一员工周一至周五上班，上午9：00- 12：00 ，下午 13：30 -18:00
            
            基数据结构为：
            1|2,1|2,1|2,1|2,1|2,-,-
            -代表正常休息日
            1|2代表为一天上两个班次，1代表为班次表1的记录
            ',
   systemDesc           varchar(256) not null comment '班次描述',
   isDefault            smallint not null comment '是否缺省
            1＝缺省
            0＝非缺省',
   primary key (systemId)
);

alter table duty_system comment '轮班制
为公司设置上班的时间规定
如规定周一至周五上班，周六周日休息';

/*==============================================================*/
/* Table: emp_profile                                           */
/*==============================================================*/
create table emp_profile
(
   profileId            bigint not null auto_increment,
   profileNo            varchar(100) not null comment '档案编号',
   userId               bigint not null,
   fullname             varchar(64) not null comment '员工姓名',
   address              varchar(128) comment '家庭地址',
   birthday             datetime comment '出生日期',
   homeZip              varchar(32) comment '家庭邮编',
   sex                  varchar(32),
   marriage             varchar(32) comment '婚姻状况
            已婚
            未婚',
   designation          varchar(64),
   position             varchar(128) not null,
   phone                varchar(64) comment '电话号码',
   mobile               varchar(64) comment '手机号码',
   openBank             varchar(100) comment '开户银行',
   bankNo               varchar(100) comment '银行账号',
   qq                   varchar(64) comment 'QQ号码',
   email                varchar(128) comment '电子邮箱',
   hobby                varchar(300) comment '爱好',
   religion             varchar(100) comment '宗教信仰',
   party                varchar(100) comment '政治面貌',
   nationality          varchar(100) comment '国籍',
   race                 varchar(100) comment '民族',
   birthPlace           varchar(128) comment '出生地',
   eduDegree            varchar(100) comment '学历',
   eduMajor             varchar(100) comment '专业',
   eduCollege           varchar(128) comment '毕业院校',
   startWorkDate        datetime comment '参加工作时间',
   eduCase              varchar(2048) comment '教育背景',
   awardPunishCase      varchar(2048) comment '奖惩情况',
   trainingCase         varchar(2048) comment '培训情况',
   workCase             varchar(2048) comment '工作经历',
   idCard               varchar(64) comment '身份证号',
   photo                varchar(128) comment '照片',
   standardMiNo         varchar(100) comment '薪酬标准编号',
   standardMoney        numeric(18,2) comment '薪酬标准金额',
   standardName         varchar(128) comment '薪酬标准单名称',
   standardId           bigint comment '薪酬标准单编号',
   jobId                bigint,
   creator              varchar(64) comment '建档人',
   createtime           datetime comment '建档时间',
   checkName            varchar(128) comment '审核人',
   checktime            datetime comment '审核时间',
   opprovalOpinion      varchar(1024),
   approvalStatus       smallint default 0 comment '核审状态
            0=未审批
            1=通过审核
            2=未通过审核',
   memo                 varchar(1024) comment '备注',
   depName              varchar(100) comment '所属部门或公司',
   depId                bigint comment '所属部门Id',
   delFlag              smallint not null default 0 comment '删除状态
            0=未删除
            1=删除',
   primary key (profileId)
);

alter table emp_profile comment '员工档案';

/*==============================================================*/
/* Table: errands_register                                      */
/*==============================================================*/
create table errands_register
(
   dateId               bigint not null auto_increment,
   userId               bigint not null comment '外出/加班人员',
   descp                varchar(500) not null comment '描述',
   startTime            datetime not null comment '开始日期',
   endTime              datetime not null comment '结束日期',
   approvalId           bigint comment '审批人ID',
   approvalName         varchar(128) comment '审批人',
   status               smallint not null comment '审批状态
            0=未审批
            1=通过审批
            2=不通过审批',
   approvalOption       varchar(500) comment '审批意见',
   flag                 smallint comment '标识
            0=加班
            1=请假
            2=外出',
   runId                bigint,
   primary key (dateId)
);

alter table errands_register comment '请假、加班、外出登记';

/*==============================================================*/
/* Table: field_rights                                          */
/*==============================================================*/
create table field_rights
(
   rightId              bigint not null auto_increment,
   mappingId            bigint not null,
   fieldId              bigint not null,
   taskName             varchar(128) not null,
   readWrite            smallint not null default 0 comment '隐藏读写权限
            0=隐藏
            1=读
            2=写',
   primary key (rightId)
);

/*==============================================================*/
/* Table: file_attach                                           */
/*==============================================================*/
create table file_attach
(
   fileId               bigint not null auto_increment,
   fileName             varchar(128) not null comment '文件名',
   filePath             varchar(128) not null comment '文件路径',
   createtime           datetime not null comment '创建时间',
   ext                  varchar(32) comment '扩展名',
   fileType             varchar(128) not null comment '附件类型
            如：邮件附件',
   note                 varchar(1024) comment '说明',
   creatorId            bigint,
   creator              varchar(32) not null comment '上传者',
   totalBytes           bigint default 0,
   delFlag              smallint comment '1=已删除
            0=删除',
   proTypeId            bigint,
   primary key (fileId)
);

alter table file_attach comment '附件';

/*==============================================================*/
/* Table: fixed_assets                                          */
/*==============================================================*/
create table fixed_assets
(
   assetsId             bigint not null auto_increment,
   assetsNo             varchar(128),
   assetsName           varchar(128) not null,
   model                varchar(64),
   assetsTypeId         bigint not null,
   manufacturer         varchar(64),
   manuDate             datetime,
   buyDate              datetime not null,
   beDep                varchar(64) not null,
   custodian            varchar(32),
   notes                varchar(500),
   remainValRate        numeric(18,6) not null,
   depreTypeId          bigint not null,
   startDepre           datetime,
   intendTerm           numeric(18,2),
   intendWorkGross      numeric(18,2) comment '当折旧的方法选择用工作量法进行计算时，才需要填写',
   workGrossUnit        varchar(128),
   assetValue           numeric(18,4) not null,
   assetCurValue        numeric(18,4) not null,
   depreRate            numeric(18,2),
   defPerWorkGross      numeric(18,2),
   primary key (assetsId)
);

/*==============================================================*/
/* Table: form_def                                              */
/*==============================================================*/
create table form_def
(
   formDefId            bigint not null auto_increment comment '表单ID',
   formTitle            varchar(128) not null comment '表单标题',
   formDesp             text,
   defHtml              text,
   status               smallint not null comment '0=草稿状态
            1=正式状态',
   formType             smallint comment '表单类型
            1=单表
            2=主从表
            3=多表',
   isDefault            smallint comment '是否缺省',
   isGen                smallint default 0 comment '1=已生成
            0=未生成',
   primary key (formDefId),
   unique key AK_FD_FormName (formTitle)
);

/*==============================================================*/
/* Table: form_rule                                              */
/*==============================================================*/
create table form_rule
(
  ruleId  bigint not null auto_increment  comment '主键',
  name    VARCHAR(128) not null comment '规则名称',
  rule    VARCHAR(128) not null comment '规则表达式',
  tipInfo VARCHAR(128) not null comment '提示信息',
  memo    VARCHAR(256) comment '备注',
  primary key (ruleId)
);

alter table file_attach comment '表单验证规则';


/*==============================================================*/
/* Table: form_def_mapping                                      */
/*==============================================================*/
create table form_def_mapping
(
   mappingId            bigint not null auto_increment,
   formDefId            bigint comment '表单ID',
   defId                bigint,
   versionNo            bigint not null default 0,
   deployId             varchar(128) not null comment '发布ID',
   useTemplate          smallint default 0 comment '1=使用模板表单
            0=使用普通在线表单',
   primary key (mappingId),
   unique key AK_UK_DeployId (deployId)
);

/*==============================================================*/
/* Table: form_field                                            */
/*==============================================================*/
create table form_field
(
   fieldId              bigint not null auto_increment,
   tableId              bigint,
   fieldName            varchar(128) not null,
   fieldLabel           varchar(128),
   fieldType            varchar(128) not null,
   isRequired           smallint,
   fieldSize            bigint,
   decimalLen			bigint comment '小数位',
   fieldDscp            varchar(1024),
   isPrimary            smallint,
   foreignKey           varchar(64),
   foreignTable         varchar(64),
   isList               smallint default 1,
   isQuery              smallint default 1,
   showFormat           varchar(256) comment '显示格式
            如日期显示yyyy-MM-dd
            数字 如 000.00',
   isFlowTitle          smallint comment '1=是
            0=否',
   isDesignShow         smallint comment '1=设计的可视化
            2=设计的不可视化
            3=手工加上',
   status               smallint comment '1=删除
            0=未删除',
   controlType 			smallint comment '控件类型',
   primary key (fieldId)
);

/*==============================================================*/
/* Table: form_table                                            */
/*==============================================================*/
create table form_table
(
   tableId              bigint not null auto_increment,
   formDefId            bigint comment '表单ID',
   tableName            varchar(128) not null,
   tableKey             varchar(128) not null,
   isMain               smallint,
   primary key (tableId)
);

/*==============================================================*/
/* Table: form_template                                         */
/*==============================================================*/
create table form_template
(
   templateId           bigint not null auto_increment,
   mappingId            bigint comment '映射ID',
   nodeName             varchar(128) not null comment '节点名称',
   tempContent          text comment '模板内容',
   extDef               text,
   formUrl              varchar(256),
   tempType             smallint comment '1=EXT模板
            2=URL模板',
   primary key (templateId)
);

/*==============================================================*/
/* Table: fun_url                                               */
/*==============================================================*/
create table fun_url
(
   urlId                bigint not null auto_increment,
   functionId           bigint not null,
   urlPath              varchar(128) not null,
   primary key (urlId)
);

/*==============================================================*/
/* Table: global_type                                           */
/*==============================================================*/
create table global_type
(
   proTypeId            bigint not null auto_increment,
   typeName             varchar(128) not null comment '名称',
   path                 varchar(64) comment '路径',
   depth                bigint not null comment '层次',
   parentId             bigint comment '父节点',
   nodeKey              varchar(64) not null comment '节点的分类Key',
   catKey               varchar(64) not null comment '节点分类的Key，如产品分类Key为PT',
   sn                   bigint not null comment '序号',
   userId               bigint comment '所属用户
            当为空则代表为公共分类',
   depId                bigint,
   primary key (proTypeId)
);

alter table global_type comment '总分类表

用于显示树层次结构的分类
可以允许任何层次结构';

/*==============================================================*/
/* Table: sys_identity   流水号                                      					*/
/*==============================================================*/
create table serial_number
(
  numberId     bigint not null auto_increment,
  name        varchar(50),
  alias       varchar(20),
  regulation  varchar(100),
  genType     smallint,
  noLength    bigint,
  curDate     varchar(10),
  initValue   bigint,
  curValue    bigint,
  step        smallint,
  primary key (numberId),
  unique key AK_SN_ALIAS (alias)
);

/*==============================================================*/
/* Table: goods_apply                                           */
/*==============================================================*/
create table goods_apply
(
   applyId              bigint not null auto_increment,
   goodsId              bigint not null,
   applyDate            datetime not null,
   applyNo              varchar(128) not null comment '申请号,按系统时间产生，如GA20091002-0001',
   useCounts            bigint not null,
   userId               bigint not null,
   proposer             varchar(32) not null,
   notes                varchar(500),
   approvalStatus       smallint not null comment '审批状态
            1=通过审批
            0=未审批
            ',
   primary key (applyId)
);

alter table goods_apply comment '办公用品出库';

/*==============================================================*/
/* Table: hire_issue                                            */
/*==============================================================*/
create table hire_issue
(
   hireId               bigint not null auto_increment,
   title                varchar(128) not null comment '招聘信息标题',
   startDate            datetime not null comment '开始时间',
   endDate              datetime not null comment '结束时间',
   hireCount            bigint not null comment '招聘人数',
   jobName              varchar(128) not null comment '职位名称',
   jobCondition         varchar(1024) comment '招聘要求(条件)',
   regFullname          varchar(128) not null comment '登记人姓名',
   regDate              datetime not null comment '登记时间',
   modifyFullname       varchar(32) comment '变更人姓名',
   modifyDate           datetime comment '变更时间',
   checkFullname        varchar(32) comment '审核人姓名',
   checkOpinion         varchar(512) comment '审核意见',
   checkDate            datetime comment '审批时间',
   status               smallint not null comment '状态
            1=通过审核
            0=未审核
            2=审核不通过',
   memo                 varchar(1024) comment '备注',
   jobId                bigint,
   primary key (hireId)
);

alter table hire_issue comment '招聘发布';

/*==============================================================*/
/* Table: holiday_record                                        */
/*==============================================================*/
create table holiday_record
(
   recordId             bigint not null auto_increment,
   startTime            datetime not null comment '开始日期',
   endTime              datetime not null comment '结束日期',
   descp                varchar(512) comment '假期描述',
   fullname             varchar(32) comment '用户名',
   userId               bigint comment '所属用户
            若为某员工的假期，则为员工自己的id',
   isAll                smallint not null comment '是否为全公司假期
            1=是
            0=否',
   primary key (recordId)
);

alter table holiday_record comment '放假记录';

/*==============================================================*/
/* Table: in_message                                            */
/*==============================================================*/
create table in_message
(
   receiveId            bigint not null auto_increment,
   messageId            bigint,
   userId               bigint comment '主键',
   readFlag             smallint not null comment '1=has red
            0=unread',
   delFlag              smallint not null,
   userFullname         varchar(32) not null,
   primary key (receiveId)
);

/*==============================================================*/
/* Table: in_stock                                              */
/*==============================================================*/
create table in_stock
(
   buyId                bigint not null auto_increment,
   goodsId              bigint not null,
   providerName         varchar(128),
   stockNo              varchar(128) not null,
   price                numeric(18,2),
   inCounts             bigint,
   amount               numeric(18,2) not null,
   inDate               datetime not null,
   buyer                varchar(128),
   primary key (buyId)
);

alter table in_stock comment '办公用品入库需要同时更新办公用品表的库存';

/*==============================================================*/
/* Table: index_display                                         */
/*==============================================================*/
create table index_display
(
   indexId              bigint not null auto_increment,
   portalId             varchar(64) not null comment 'Portal ID',
   userId               bigint not null comment '用户ID',
   colNum               bigint not null comment '列号',
   rowNo                bigint not null comment '行号',
   primary key (indexId)
);

alter table index_display comment '每个员工可以设置自己的显示方式';

/*==============================================================*/
/* Table: job_change                                            */
/*==============================================================*/
create table job_change
(
   changeId             bigint not null auto_increment,
   profileId            bigint not null,
   profileNo            varchar(64) not null,
   userName             varchar(64),
   orgJobName           varchar(64) not null,
   newJobName           varchar(64) not null,
   orgStandardId        bigint comment '原薪酬标准单ID',
   orgStandardNo        varchar(64),
   orgStandardName      varchar(64),
   orgDepId             bigint,
   orgDepName           varchar(128),
   orgTotalMoney        numeric(18,2),
   newStandardId        bigint,
   newStandardNo        varchar(64),
   newStandardName      varchar(64),
   newDepId             bigint,
   newDepName           varchar(128),
   newTotalMoney        numeric(18,2),
   changeReason         varchar(1024),
   regTime              datetime,
   regName              varchar(64),
   checkName            varchar(64),
   checkTime            datetime,
   checkOpinion         varchar(512),
   status               smallint comment '状态
            
            -1=草稿
            0=提交审批
            1=通过审批
            2=未通过审批
            ',
   memo                 varchar(1024),
   orgJobId             bigint,
   newJobId             bigint,
   primary key (changeId)
);

/*==============================================================*/
/* Table: mail                                                  */
/*==============================================================*/
create table mail
(
   mailId               bigint not null auto_increment,
   sender               varchar(32) not null,
   senderId             bigint not null,
   importantFlag        smallint not null comment '1=一般
            2=重要
            3=非常重要',
   sendTime             datetime not null,
   content              text not null comment '邮件内容',
   subject              varchar(256) not null comment '邮件标题',
   copyToNames          varchar(2000) comment '抄送人姓名列表',
   copyToIDs            varchar(2000) comment '抄送人ID列表
            用'',''分开',
   recipientNames       varchar(2000) not null comment '收件人姓名列表',
   recipientIDs         varchar(2000) not null comment '收件人ID列表
            用'',''分隔',
   mailStatus           smallint not null comment '邮件状态
            1=正式邮件
            0=草稿邮件',
   fileIds              varchar(500) comment '附件Ids，多个附件的ID，通过,分割',
   filenames            varchar(500) comment '附件名称列表，通过,进行分割',
   primary key (mailId)
);

alter table mail comment '邮件';

/*==============================================================*/
/* Table: mail_attach                                           */
/*==============================================================*/
create table mail_attach
(
   mailId               bigint not null,
   fileId               bigint not null,
   primary key (mailId, fileId)
);

/*==============================================================*/
/* Table: mail_box                                              */
/*==============================================================*/
create table mail_box
(
   boxId                bigint not null auto_increment,
   mailId               bigint not null,
   folderId             bigint not null,
   userId               bigint comment '主键',
   sendTime             datetime not null,
   delFlag              smallint not null comment 'del=1则代表删除',
   readFlag             smallint not null,
   replyFlag            smallint not null,
   note                 varchar(256) comment 'note',
   primary key (boxId)
);

alter table mail_box comment '收件箱';

/*==============================================================*/
/* Table: mail_folder                                           */
/*==============================================================*/
create table mail_folder
(
   folderId             bigint not null auto_increment comment '文件夹编号',
   userId               bigint comment '主键',
   folderName           varchar(128) not null comment '文件夹名称',
   parentId             bigint comment '父目录',
   depLevel             bigint not null comment '目录层',
   path                 varchar(256),
   isPublic             smallint not null comment '1=表示共享，则所有的员工均可以使用该文件夹
            0=私人文件夹',
   folderType           smallint not null comment '文件夹类型
            1=收信箱
            2=发信箱
            3=草稿箱
            4=删除箱
            10=其他',
   primary key (folderId)
);

/*==============================================================*/
/* Table: meeting                                               */
/*==============================================================*/
create table meeting
(
   mettingId            bigint not null auto_increment,
   holdTime             datetime,
   holdLocation         varchar(128),
   meetingName          varchar(128),
   attendUsers          varchar(128),
   holdDep              varchar(128),
   holdDepId            bigint,
   shortDesc            varchar(256),
   isFeedback           smallint not null,
   summary              text,
   primary key (mettingId)
);

/*==============================================================*/
/* Table: meeting_attend                                        */
/*==============================================================*/
create table meeting_attend
(
   attendId             bigint not null auto_increment,
   mettingId            bigint not null,
   userName             varchar(64),
   userId               bigint,
   depName              varchar(100),
   depId                bigint,
   attendType           smallint not null default 0 comment '参与类型
            0=user
            1=department',
   feedback             varchar(1024),
   signTime             datetime,
   signName             varchar(32) not null,
   primary key (attendId)
);

alter table meeting_attend comment '会议参与部门或人员';

/*==============================================================*/
/* Table: meeting_file                                          */
/*==============================================================*/
create table meeting_file
(
   mettingId            bigint not null,
   fileId               bigint not null,
   primary key (mettingId, fileId)
);

/*==============================================================*/
/* Table: news                                                  */
/*==============================================================*/
create table news
(
   newsId               bigint not null auto_increment comment 'ID',
   sectionId            bigint,
   subjectIcon          varchar(128),
   subject              varchar(128) not null comment '新闻标题',
   author               varchar(32) not null comment '作者',
   createtime           datetime not null comment '创建时间',
   expTime              datetime,
   replyCounts          bigint,
   viewCounts           bigint comment '浏览数',
   issuer               varchar(32) not null,
   content              text not null comment '内容',
   updateTime           datetime,
   status               smallint not null comment '
            0=待审核
            1=审核通过',
   isDeskImage          smallint comment '是否为桌面新闻',
   isNotice             smallint,
   sn                   bigint,
   orgIds               varchar(200) comment '组织id',
   orgNames             varchar(1000) comment '组织名',
   primary key (newsId)
);

alter table news comment '新闻';

/*==============================================================*/
/* Table: news_comment                                          */
/*==============================================================*/
create table news_comment
(
   commentId            bigint not null auto_increment,
   content              varchar(500) not null,
   createtime           datetime not null,
   fullname             varchar(32) not null,
   userId               bigint not null,
   newsId               bigint comment 'ID',
   primary key (commentId)
);

/*==============================================================*/
/* Table: office_goods                                          */
/*==============================================================*/
create table office_goods
(
   goodsId              bigint not null auto_increment,
   typeId               bigint not null comment '所属分类',
   goodsName            varchar(128) not null comment '物品名称',
   goodsNo              varchar(128) not null comment '编号',
   specifications       varchar(256) not null comment '规格',
   unit                 varchar(64) not null comment '计量单位',
   isWarning            smallint not null comment '是否启用库存警示',
   notes                varchar(500) comment '备注',
   stockCounts          bigint not null comment '库存总数',
   warnCounts           bigint not null comment '最低库存数',
   primary key (goodsId)
);

alter table office_goods comment '办公用品';

/*==============================================================*/
/* Table: office_goods_type                                     */
/*==============================================================*/
create table office_goods_type
(
   typeId               bigint not null auto_increment,
   typeName             varchar(128) not null comment '分类名称',
   primary key (typeId)
);

alter table office_goods_type comment '办公用品类型';

/*==============================================================*/
/* Table: organization                                          */
/*==============================================================*/
create table organization
(
   org_id               bigint not null auto_increment,
   dem_id               bigint,
   org_name             varchar(128) not null,
   org_desc             varchar(500),
   org_sup_id           bigint,
   path                 varchar(128),
   depth                int,
   org_type             smallint comment '1=公司
            2=部门
            3=其他组织',
   creator_id           bigint,
   createtime           datetime,
   update_id            bigint,
   updatetime           datetime,
   sn                   int,
   primary key (org_id)
);

/*==============================================================*/
/* Table: out_mail                                              */
/*==============================================================*/
create table out_mail
(
   mailId               bigint not null auto_increment,
   uidNo                varchar(512),
   userId               bigint,
   setId				bigint not null comment '邮箱设置ID',
   folderId             bigint comment '文件夹编号',
   title                varchar(512) comment '主题',
   content              text comment '内容',
   senderAddresses      varchar(128) not null comment '发件人地址',
   senderName           varchar(128) comment '发件人地址别名',
   receiverAddresses    text not null comment '收件人地址',
   receiverNames        text comment '收件人地址别名',
   cCAddresses          text comment '抄送人地址',
   cCNames              text comment '抄送人地址别名',
   bCCAddresses         text comment '暗送人地址',
   bCCAnames            text comment '暗送人地址别名',
   mailDate             datetime not null,
   fileIds              varchar(512),
   fileNames            varchar(512),
   readFlag             smallint not null default 0 comment '0:未阅
            1:已阅',
   replyFlag            smallint not null default 0 comment '0:未回复
            1;已回复',
   primary key (mailId)
);

/*==============================================================*/
/* Table: out_mail_file                                         */
/*==============================================================*/
create table out_mail_file
(
   mailId               bigint not null,
   fileId               bigint not null,
   primary key (mailId, fileId)
);

/*==============================================================*/
/* Table: out_mail_folder                                       */
/*==============================================================*/
create table out_mail_folder
(
   folderId             bigint not null auto_increment comment '文件夹编号',
   userId               bigint comment '主键',
   setId				bigint not null comment '邮箱设置ID',
   folderName           varchar(128) not null comment '文件夹名称',
   parentId             bigint comment '父目录',
   depLevel             bigint not null comment '目录层',
   path                 varchar(256),
   folderType           smallint not null comment '文件夹类型
            1=收信箱
            2=发信箱
            3=草稿箱
            4=删除箱
            10=其他',
   primary key (folderId)
);

/*==============================================================*/
/* Table: out_mail_user_seting                                  */
/*==============================================================*/
create table out_mail_user_seting
(
   setId                bigint not null auto_increment,
   userId               bigint comment '用户ID',
   userName             varchar(128) comment '用户名称',
   accountName         varchar(128) comment '帐号名称',
   mailAddress          varchar(128) not null comment '外部邮件地址',
   mailPass             varchar(128) not null comment '外部邮件密码',
   smtpHost             varchar(128) not null comment 'smt主机',
   smtpPort             varchar(64) not null comment 'smt端口',
   popHost              varchar(128) not null comment 'pop主机',
   popPort              varchar(64) not null comment 'pop端口',
   isDefault            smallint not null comment '是否默认：1表示默认，0表示非默认',
   primary key (setId)
);


/*==============================================================*/
/* Table: paint_template                                        */
/*==============================================================*/
create table paint_template
(
   ptemplateId          bigint not null auto_increment comment '主键',
   fileId               bigint,
   templateKey          varchar(64),
   templateName         varchar(64) not null comment '模板名称',
   path                 varchar(128) comment '路径',
   isActivate           smallint not null comment '是否激活
            1=是
            0=否',
   primary key (ptemplateId)
);

/*==============================================================*/
/* Table: phone_book                                            */
/*==============================================================*/
create table phone_book
(
   phoneId              bigint not null auto_increment,
   fullname             varchar(128) not null,
   title                varchar(32) not null comment '先生
            女士
            小姐',
   birthday             datetime,
   nickName             varchar(32),
   duty                 varchar(50),
   spouse               varchar(32),
   childs               varchar(40),
   companyName          varchar(100),
   companyAddress       varchar(128),
   companyPhone         varchar(32),
   companyFax           varchar(32),
   homeAddress          varchar(128),
   homeZip              varchar(12),
   mobile               varchar(32),
   phone                varchar(32),
   email                varchar(32),
   QQ                   varchar(64),
   MSN                  varchar(128),
   note                 varchar(500),
   userId               bigint not null,
   groupId              bigint,
   isShared             smallint not null,
   primary key (phoneId)
);

alter table phone_book comment '通讯簿';

/*==============================================================*/
/* Table: phone_group                                           */
/*==============================================================*/
create table phone_group
(
   groupId              bigint not null auto_increment,
   groupName            varchar(128) not null comment '分组名称',
   isShared             smallint not null comment '1=共享
            0=私有',
   SN                   bigint not null,
   userId               bigint not null,
   isPublic             smallint default 0 comment '是否公共
            0=私有
            1=公共',
   primary key (groupId)
);

/*==============================================================*/
/* Table: plan_attend                                           */
/*==============================================================*/
create table plan_attend
(
   attendId             bigint not null auto_increment,
   userId               bigint,
   depId                bigint comment '部门ID',
   planId               bigint not null,
   isDep                smallint not null comment '是否为部门',
   isPrimary            smallint comment '是否负责人',
   primary key (attendId)
);

/*==============================================================*/
/* Table: plan_file                                             */
/*==============================================================*/
create table plan_file
(
   fileId               bigint not null,
   planId               bigint not null,
   primary key (fileId, planId)
);

/*==============================================================*/
/* Table: position                                              */
/*==============================================================*/
create table position
(
   pos_id               bigint not null auto_increment,
   org_id               bigint,
   pos_name             varchar(128) not null,
   pos_desc             varchar(1024),
   pos_sup_id           bigint,
   path                 varchar(256),
   depth                int,
   sn                	int,
   primary key (pos_id)
);

/*==============================================================*/
/* Table: position_sub                                          */
/*==============================================================*/
create table position_sub
(
   mainPositionId       bigint not null,
   subPositionId        bigint not null,
   primary key (mainPositionId, subPositionId)
);

alter table position_sub comment '同级岗位是用于管理同级用户的下级，如一经理岗位同时有正与副，正副也同时管理下面的职位人员的话，
可以把这两职';

/*==============================================================*/
/* Table: pro_def_rights                                        */
/*==============================================================*/
create table pro_def_rights
(
   rightsId             bigint not null auto_increment,
   proTypeId            bigint,
   defId                bigint,
   roleNames            varchar(2000),
   depNames             varchar(2000),
   userNames            varchar(2000),
   userIds              varchar(2000) comment '用户IDS
            格式如下，以方便使用like操作
            ,1,2,',
   roleIds              varchar(2000) comment '角色IDS
            格式如下，以方便使用like操作
            ,1,2,',
   depIds               varchar(2000) comment '部门IDS
            格式如下，以方便使用like操作
            ,1,2,',
   primary key (rightsId)
);

/*==============================================================*/
/* Table: pro_definition                                        */
/*==============================================================*/
create table pro_definition
(
   defId                bigint not null auto_increment,
   proTypeId            bigint,
   name                 varchar(256) not null comment '流程的名称',
   description          varchar(1024) comment '描述',
   createtime           datetime comment '创建时间',
   updatetime           datetime comment '更新时间',
   deployId             varchar(64) comment 'Jbpm 工作流id',
   pdId                 varchar(64),
   defKey               varchar(64),
   defXml               text comment '流程定义XML',
   drawDefXml           text,
   isDefault            smallint not null default 0 comment '是否缺省
            1=是
            0=否',
   processName          varchar(128) comment '来自jbpm的流程定义jpdl中的name值',
   newVersion           bigint,
   status               smallint comment '1=激活
            0=禁用',         
   parentId            bigint comment '主版本ID',
   isMain               smallint  comment '0=非主版本 1=主版本 ',
   skipFirstNode        smallint comment '跳过第一个节点',
   primary key (defId)
);

alter table pro_definition comment '流程定义';

/*==============================================================*/
/* Table: pro_node_set                                          */
/*==============================================================*/
create table pro_node_set  (
   setId                bigint 	not null auto_increment,
   defId                bigint                      	not null,
   deployId             varchar(128)                   not null,
   jbpmDefId            varchar(64)                    not null,
   nodeName             varchar(256)                   not null,
   nodeType             smallint comment '1=普通任务节点;2=会签任务节点;3=分发任务节点',
   joinNodeName         varchar(256),
   isAllowBack          smallint,
  primary key (setId)
);

alter table pro_node_set comment '流程节点设置';


/*==============================================================*/
/* Table: pro_user_set                                          */
/*==============================================================*/
create table pro_user_set  (
   id                  bigint not null auto_increment,
   defId               bigint,
   deployId            varchar(64)  not null ,                   
   jbpmDefId           varchar(64)  not null comment 'Jbpm 工作流id',
   demId			   bigint comment '若选择上下级时对应的维度',
   nodeName            varchar(256) not null,
   userType            smallint     not null comment '1=发起人;2=user;3=role;4=岗位;5=部门、组织;6=部门、组织负责人;7=上下级',
   uids                text,
   unames              text,
   compType            smallint 	comment'1=or;2=and;3=exclude',
   sn 				   bigint 		default 1 comment '序号',
   strategy            smallint     comment '级数',
   primary key (id)
);

alter table pro_user_set comment '流程节点人员设置';



/*==============================================================*/
/* Table: pro_handle_comp                                       */
/*==============================================================*/
create table pro_handle_comp
(
   handleId             bigint not null auto_increment,
   deployId             varchar(128) not null comment 'JBPM流程DeployId',
   activityName         varchar(128) comment '节点名称',
   tranName             varchar(128) comment '若事件为某个transition中的事件的话，则该字段存储该值',
   eventName            varchar(128) comment '事件名，有值为：
            start
            end',
   eventLevel           smallint comment '事件级别为三值：
            1=process  代表为流程的事件
            2=node     代表为流程节点的事件
            3=transition 代表为跳转的事件',
   exeCode              varchar(4000) comment '事件中动态执行的代码',
   handleType           smallint comment '1=监听类 实现listener之类的接口
            2=处理类  实现handler之类的接口',
   primary key (handleId)
);

/*==============================================================*/
/* Table: pro_user_assign                                       */
/*==============================================================*/
create table pro_user_assign
(
   assignId             bigint not null auto_increment comment '授权ID',
   deployId             varchar(128) not null comment 'jbpm流程定义的id',
   activityName         varchar(128) not null comment '流程节点名称',
   roleId               varchar(128) comment '角色Id',
   roleName             varchar(256),
   userId               varchar(128) comment '用户ID',
   username             varchar(256),
   isSigned             smallint default 0 comment '1=是会签任务
            0=非会签任务
            
            若为会签任务，则需要为该会签添加会签的决策方式的设置
            ',
   jobId                varchar(128),
   jobName              varchar(128),
   reJobId              varchar(128),
   reJobName            varchar(128),
   depIds               varchar(512),
   depNames             varchar(512),
   posUserFlag          smallint,
   depPosIds            varchar(128),
   depPosNames          varchar(128),
   primary key (assignId)
);

alter table pro_user_assign comment '流程过程中各个任务节点及启动流程时的角色及用户';

/*==============================================================*/
/* Table: process_form                                          */
/*==============================================================*/
create table process_form
(
   formId               bigint not null auto_increment,
   runId                bigint not null comment '所属运行流程',
   activityName         varchar(256) not null comment '活动或任务名称',
   createtime           datetime not null,
   endtime              datetime,
   durtimes             bigint,
   creatorId            bigint,
   creatorName          varchar(64),
   fromTaskId           varchar(64) comment '该任务来自由哪一任务跳转过来，目的是为了查到该任务的上一任务，方便任务驳回。存储Jbpm 的任务ID',
   fromTask             varchar(256) comment '该任务来自由哪一任务跳转过来，目的是为了查到该任务的上一任务，方便任务驳回。',
   taskId               varchar(64) comment '当前任务ID',
   transTo              varchar(256) comment '跳转节点
            跳转至下一任务',
   status               smallint default 0 comment '0=进入任务
            1=完成
            2=取消',
   preFormId            bigint,
   comments             varchar(2000),
   primary key (formId)
);

alter table process_form comment '流程表单
存储保存在运行中的流程表单数据';

/*==============================================================*/
/* Table: process_module                                        */
/*==============================================================*/
create table process_module
(
   moduleId             bigint not null auto_increment,
   moduleName           varchar(256) not null,
   moduleKey            varchar(128) not null,
   descp                varchar(4000),
   defId                bigint,
   processKey           varchar(256),
   creator              varchar(64),
   createtime           datetime,
   primary key (moduleId)
);

/*==============================================================*/
/* Table: process_run                                           */
/*==============================================================*/
create table process_run
(
   runId                bigint not null auto_increment,
   subject              varchar(256) not null comment '标题
            一般为流程名称＋格式化的时间',
   creator              varchar(128) comment '创建人',
   userId               bigint not null comment '所属用户',
   defId                bigint not null comment '所属流程定义',
   piId                 varchar(64) comment '流程实例ID',
   piDbid               bigint,
   pdId                 varchar(64),
   processName          varchar(128),
   createtime           datetime not null comment '创建时间',
   runStatus            smallint not null comment '0=尚未启动
            1=已经启动流程
            2=运行结束',
   busDesc              varchar(1024),
   entityName           varchar(128),
   entityId             bigint,
   formDefId            bigint comment '存储正在运行的表单定义id',
   defHtml              text,
   primary key (runId)
);

alter table process_run comment '运行中的流程';

/*==============================================================*/
/* Table: product                                               */
/*==============================================================*/
create table product
(
   productId            bigint not null auto_increment,
   productName          varchar(128) not null comment '产品名称',
   productModel         varchar(128) comment '产品型号',
   unit                 varchar(128) comment '计量单位',
   costPrice            numeric(18,2) comment '成本价',
   salesPrice           numeric(18,2) comment '出售价',
   productDesc          varchar(512) comment '产品描述',
   providerId           bigint not null comment '所属供应商',
   createtime           datetime not null comment '收录时间',
   updatetime           datetime not null,
   primary key (productId)
);

alter table product comment '供应商产品';

/*==============================================================*/
/* Table: project                                               */
/*==============================================================*/
create table project
(
   projectId            bigint not null auto_increment,
   projectName          varchar(128) not null comment '项目名称',
   projectNo            varchar(64) not null comment '项目编号',
   reqDesc              text comment '需求描述',
   isContract           smallint not null comment '是否签订合同',
   fullname             varchar(32) not null comment '联系人姓名',
   mobile               varchar(32) comment '手机',
   phone                varchar(32) comment '电话',
   fax                  varchar(32) comment '传真',
   otherContacts        varchar(128) comment '其他联系方式',
   customerId           bigint not null comment '所属客户',
   userId               bigint not null comment '业务人员',
   primary key (projectId)
);

alter table project comment '项目信息';

/*==============================================================*/
/* Table: project_file                                          */
/*==============================================================*/
create table project_file
(
   fileId               bigint not null,
   projectId            bigint not null,
   primary key (fileId, projectId)
);

alter table project_file comment '项目附件';

/*==============================================================*/
/* Table: provider                                              */
/*==============================================================*/
create table provider
(
   providerId           bigint not null auto_increment,
   providerName         varchar(128) not null comment '供应商名称',
   contactor            varchar(128) not null comment '联系人',
   phone                varchar(32) not null comment '电话',
   fax                  varchar(32) comment '传真',
   site                 varchar(128) comment '网址',
   email                varchar(128) comment '邮件',
   address              varchar(128) not null comment '地址',
   zip                  varchar(32) comment '邮编',
   openBank             varchar(128) comment '开户行',
   account              varchar(64) comment '帐号',
   notes                varchar(500) comment '备注',
   rank                 bigint comment '供应商等级
            1=一级供应商
            2＝二级供应商
            3＝三级供应商
            4＝四级供应商
            ',
   primary key (providerId)
);

alter table provider comment '供应商';

/*==============================================================*/
/* Table: reg_attach                                            */
/*==============================================================*/
create table reg_attach
(
   fileId               bigint not null,
   regId                bigint not null,
   primary key (fileId, regId)
);

/*==============================================================*/
/* Table: region                                                */
/*==============================================================*/
create table region
(
   regionId             bigint not null auto_increment,
   regionName           varchar(128) not null comment '地区名称',
   regionType           smallint not null comment '地区类型
            1=省份
            2=市',
   parentId             bigint comment '上级地区',
   primary key (regionId)
);

alter table region comment '地区管理';

/*==============================================================*/
/* Table: regulation                                            */
/*==============================================================*/
create table regulation
(
   regId                bigint not null auto_increment,
   proTypeId            bigint,
   subject              varchar(256) not null comment '标题',
   issueDate            datetime comment '发布日期',
   issueUserId          bigint comment '发布人ID',
   issueFullname        varchar(64) comment '发布人',
   issueDepId           bigint comment '发布部门ID',
   issueDep             varchar(64) comment '发布部门',
   recDeps              varchar(1024) comment '接收部门范围',
   recDepIds            varchar(1024) comment '接收部门范围ID',
   recUsers             varchar(1024) comment '接收人范围',
   recUserIds           varchar(1024) comment '接收人范围ID',
   content              text comment '内容',
   keywords             varchar(256) comment '关键字',
   status               smallint comment '状态',
   primary key (regId)
);

/*==============================================================*/
/* Table: relative_job                                          */
/*==============================================================*/
create table relative_job
(
   reJobId              bigint not null auto_increment,
   jobName              varchar(128) not null comment '岗位名称',
   jobCode              varchar(256) comment '编码',
   parent               bigint comment '父岗位',
   path                 varchar(128) comment '路径',
   depath               bigint default 0 comment '深度',
   primary key (reJobId)
);

/*==============================================================*/
/* Table: relative_user                                         */
/*==============================================================*/
create table relative_user
(
   relativeUserId       bigint not null auto_increment comment 'ID',
   reJobId              bigint comment '所属相对岗位',
   userId               bigint comment '所属员工',
   jobUserId            bigint,
   isSuper              smallint comment '上下级标识
            1=上级
            0=下级',
   primary key (relativeUserId)
);

/*==============================================================*/
/* Table: report_param                                          */
/*==============================================================*/
create table report_param
(
   paramId              bigint not null auto_increment,
   reportId             bigint not null comment '所属报表',
   paramName            varchar(64) not null comment '参数名称',
   paramKey             varchar(64) not null comment '参数Key',
   defaultVal           varchar(128) comment '缺省值',
   paramType            varchar(32) not null comment '类型
            字符类型--varchar
            整型--int
            精度型--decimal
            日期型--date
            日期时间型--datetime
            ',
   sn                   bigint not null comment '系列号',
   paramTypeStr         varchar(1024),
   primary key (paramId)
);

alter table report_param comment '报表参数';

/*==============================================================*/
/* Table: report_template                                       */
/*==============================================================*/
create table report_template
(
   reportId             bigint not null auto_increment,
   title                varchar(128) not null comment '标题',
   descp                varchar(500) not null comment '描述',
   reportLocation       varchar(128) not null comment '报表模块的jasper文件的路径',
   createtime           datetime not null comment '创建时间',
   updatetime           datetime not null comment '修改时间',
   reportKey            varchar(128) comment '标识key',
   isDefaultIn          smallint comment '是否缺省
            1=缺省
            0=非缺省',
   primary key (reportId)
);

alter table report_template comment '报表模板
report_template';

/*==============================================================*/
/* Table: resume                                                */
/*==============================================================*/
create table resume
(
   resumeId             bigint not null auto_increment,
   fullname             varchar(64) not null,
   age                  bigint,
   birthday             datetime,
   address              varchar(128),
   zip                  varchar(32),
   sex                  varchar(32),
   position             varchar(64),
   phone                varchar(64),
   mobile               varchar(64),
   email                varchar(128),
   hobby                varchar(256),
   religion             varchar(128),
   party                varchar(128),
   nationality          varchar(32),
   race                 varchar(32),
   birthPlace           varchar(128),
   eduCollege           varchar(128),
   eduDegree            varchar(128),
   eduMajor             varchar(128),
   startWorkDate        datetime,
   idNo                 varchar(64),
   photo                varchar(128),
   status               varchar(64) comment '状态
            
            通过
            未通过
            准备安排面试
            面试通过
            
            ',
   memo                 varchar(1024),
   registor             varchar(64),
   regTime              datetime,
   workCase             text,
   trainCase            text,
   projectCase          text,
   primary key (resumeId)
);

alter table resume comment '简历管理';

/*==============================================================*/
/* Table: resume_file                                           */
/*==============================================================*/
create table resume_file
(
   fileId               bigint not null,
   resumeId             bigint not null,
   primary key (fileId, resumeId)
);

/*==============================================================*/
/* Table: role_fun                                              */
/*==============================================================*/
create table role_fun
(
   roleId               bigint not null,
   functionId           bigint not null,
   primary key (roleId, functionId)
);

/*==============================================================*/
/* Table: role_position                                         */
/*==============================================================*/
create table role_position
(
   pos_id               bigint not null,
   roleId               bigint not null,
   primary key (pos_id, roleId)
);

/*==============================================================*/
/* Table: roll_file                                             */
/*==============================================================*/
create table roll_file
(
   rollFileId           bigint not null auto_increment,
   typeName             varchar(128) comment '分类名称',
   rollId               bigint comment '案卷ID',
   proTypeId            bigint,
   fileName             varchar(128) not null comment '文件题名',
   fileNo               varchar(64) not null comment '文件编号',
   dutyPerson           varchar(32) comment '责任者',
   afNo                 varchar(64) comment '全宗号',
   catNo                varchar(64) comment '目录号',
   rollNo               varchar(64) comment '案卷号',
   seqNo                bigint comment '顺序号',
   pageNo               bigint comment '页号',
   pageNums             bigint comment '页数',
   secretLevel          varchar(64) comment '密级
            
            普通
            秘密
            机密
            绝密',
   timeLimit            varchar(64) comment '保管期限
            长久
            长期
            短期
            10年
            15年
            20年
            ',
   openStyle            varchar(64) comment '开放形式
            开放
            待定
            私密',
   keyWords             varchar(512) comment '主题词',
   notes                varchar(4000) comment '附注',
   content              text comment '内容',
   fileTime             datetime comment '文件时间',
   creatorName          varchar(128) comment '录入人',
   createTime           datetime  comment '录入时间',
   archStatus           smallint default 0 comment '归档状态
            1=归档
            0=未归档',
   tidyTime             datetime comment '归档时间',
   tidyName             varchar(128) comment '归档人',
   primary key (rollFileId)
);

/*==============================================================*/
/* Table: roll_file_list                                        */
/*==============================================================*/
create table roll_file_list
(
   listId               bigint not null auto_increment,
   rollFileId           bigint not null,
   fileId               bigint,
   downLoads            bigint,
   sn                   int,
   shortDesc            varchar(500),
   primary key (listId)
);

/*==============================================================*/
/* Table: run_data                                              */
/*==============================================================*/
create table run_data
(
   dataId               bigint not null auto_increment,
   runId                bigint,
   fieldLabel           varchar(128) comment '字段标签',
   fieldName            varchar(64) not null comment '字段名称',
   intValue             bigint comment '整数值',
   longValue            bigint comment '长整值',
   decValue             numeric(18,4) comment '精度值',
   dateValue            datetime comment '日期值',
   strValue             varchar(4000) comment '字符值',
   boolValue            smallint comment '布尔值',
   blobValue            longblob comment '对象值',
   isShowed             smallint comment '是否显示
            1=显示
            0=不显示',
   textValue            text,
   fieldType            varchar(32),
   primary key (dataId)
);

/*==============================================================*/
/* Table: salary_item                                           */
/*==============================================================*/
create table salary_item
(
   salaryItemId         bigint not null auto_increment,
   itemName             varchar(128) not null comment '项目名称',
   defaultVal           numeric(18,2) not null comment '缺省值',
   primary key (salaryItemId)
);

alter table salary_item comment '薪酬组成项目';

/*==============================================================*/
/* Table: salary_payoff                                         */
/*==============================================================*/
create table salary_payoff
(
   recordId             bigint not null auto_increment,
   fullname             varchar(64) not null comment '员工姓名',
   userId               bigint not null comment '所属员工',
   profileNo            varchar(128) comment '档案编号',
   standardId           bigint not null,
   idNo                 varchar(128) comment '身份证号',
   standAmount          numeric(18,2) not null default 0 comment '薪标金额',
   encourageAmount      numeric(18,2) not null default 0 comment '奖励金额',
   deductAmount         numeric(18,2) not null default 0 comment '扣除工资',
   achieveAmount        numeric(18,2) default 0 comment '效绩工资',
   encourageDesc        varchar(512) comment '奖励描述',
   deductDesc           varchar(512) comment '扣除描述',
   memo                 varchar(512) comment '备注描述',
   acutalAmount         numeric(18,2) comment '实发金额',
   regTime              datetime not null comment '登记时间',
   register             varchar(64) comment '登记人',
   checkOpinion         varchar(1024),
   checkName            varchar(64) comment '审批人',
   checkTime            datetime comment '审批时间',
   checkStatus          smallint comment '审批状态
            0=草稿
            1=通过审批
            2=未通过审批
            ',
   startTime            datetime not null,
   endTime              datetime not null,
   primary key (recordId)
);

/*==============================================================*/
/* Table: seal                                                  */
/*==============================================================*/
create table seal
(
   sealId               bigint not null auto_increment,
   fileId               bigint,
   sealName             varchar(64) not null comment '印章名称',
   sealPath             varchar(128) comment '印章文件路径',
   belongId             bigint not null comment '所属人ID',
   belongName           varchar(64) not null comment '所属人',
   primary key (sealId)
);

/*==============================================================*/
/* Table: section                                               */
/*==============================================================*/
create table section
(
   sectionId            bigint not null auto_increment,
   sectionName          varchar(256) not null,
   sectionDesc          varchar(1024),
   createtime           datetime not null,
   sectionType          smallint not null,
   username             varchar(256),
   userId               bigint,
   colNumber            bigint,
   rowNumber            bigint,
   status               smallint not null,
   primary key (sectionId)
);

/*==============================================================*/
/* Table: short_message                                         */
/*==============================================================*/
create table short_message
(
   messageId            bigint not null auto_increment,
   senderId             bigint comment '主键',
   content              varchar(256) not null,
   sender               varchar(64) not null,
   msgType              smallint not null comment '1=个人信息
            2=日程安排
            3=计划任务
            ',
   sendTime             datetime not null,
   primary key (messageId)
);

alter table short_message comment '短信消息';

/*==============================================================*/
/* Table: sms_history                                           */
/*==============================================================*/
create table sms_history
(
   smsId                bigint not null auto_increment,
   sendTime             datetime not null,
   recipients           varchar(128),
   phoneNumber          varchar(128) not null,
   userId               bigint,
   userName             varchar(128),
   smsContent           varchar(1024) not null,
   status               smallint not null comment '0=未发送
            1=发送失败
            
            发送成功后，该记录会直接存在另一张发送历史的表中
            ',
   primary key (smsId)
);

/*==============================================================*/
/* Table: sms_mobile                                            */
/*==============================================================*/
create table sms_mobile
(
   smsId                bigint not null auto_increment,
   sendTime             datetime not null,
   recipients           varchar(128),
   phoneNumber          varchar(128) not null,
   userId               bigint,
   userName             varchar(128),
   smsContent           varchar(1024) not null,
   status               smallint not null comment '0=未发送
            1=发送失败
            
            发送成功后，该记录会直接存在另一张发送历史的表中
            ',
   primary key (smsId)
);

/*==============================================================*/
/* Table: stand_salary                                          */
/*==============================================================*/
create table stand_salary
(
   standardId           bigint not null auto_increment,
   standardNo           varchar(128) not null comment '薪酬标准编号
            惟一',
   standardName         varchar(128) not null comment '标准名称',
   totalMoney           numeric(18,2) not null default 0.00,
   framer               varchar(64),
   setdownTime          datetime,
   checkName            varchar(64),
   checkTime            datetime,
   modifyName           varchar(64),
   modifyTime           datetime,
   checkOpinion         varchar(512),
   status               smallint not null comment '0=草稿
            1=审批
            2=未通过审批',
   memo                 varchar(512),
   primary key (standardId)
);

/*==============================================================*/
/* Table: stand_salary_item                                     */
/*==============================================================*/
create table stand_salary_item
(
   itemId               bigint not null auto_increment,
   standardId           bigint not null,
   itemName             varchar(64) not null,
   amount               numeric(18,2) not null,
   salaryItemId         bigint comment '所属工资组成ID
            外键，但不需要在数据库层建立外键',
   primary key (itemId)
);

alter table stand_salary_item comment '薪酬标准明细';

/*==============================================================*/
/* Table: suggest_box                                           */
/*==============================================================*/
create table suggest_box
(
   boxId                bigint not null auto_increment,
   subject              varchar(256) not null comment '意见标题',
   content              varchar(4000) not null comment '意见内容',
   createtime           datetime comment '创建日期',
   recUid               bigint comment '接收人ID',
   recFullname          varchar(32) comment '接收人名',
   senderId             bigint comment '发送人ID',
   senderFullname       varchar(32) comment '发送人名',
   senderIp             varchar(64) comment '发送人IP',
   phone                varchar(64) comment '联系电话',
   email                varchar(100) comment 'Email',
   isOpen               smallint comment '是否公开',
   replyContent         varchar(4000) comment '回复内容',
   replyTime            datetime comment '回复时间',
   replyId              bigint comment '回复人ID',
   replyFullname        varchar(32) comment '回复人名',
   status               smallint comment '状态',
   queryPwd             varchar(128),
   primary key (boxId)
);

/*==============================================================*/
/* Table: sys_config                                            */
/*==============================================================*/
create table sys_config
(
   configId             bigint not null auto_increment,
   configKey            varchar(64) not null comment 'Key',
   configName           varchar(64) not null comment '配置名称',
   configDesc           varchar(256) comment '配置描述',
   typeName             varchar(32) not null comment '所属分类名称',
   dataType             smallint not null comment '数据类型
            1=varchar
            2=intger
            3=decimal
            4=datetime
            5=time
            ',
   dataValue            varchar(64),
   typeKey              varchar(64),
   primary key (configId)
);

alter table sys_config comment '系统配置

用于系统的全局配置
如邮件服务器的配置';

/*==============================================================*/
/* Table: task_sign                                             */
/*==============================================================*/
create table task_sign
(
   signId               bigint not null auto_increment,
   setId             	bigint not null comment '所属节点设置',
   voteCounts           bigint comment '绝对票数',
   votePercents         bigint comment '百分比票数',
   decideType           smallint not null comment '1=pass 通过
            2=reject 拒绝',
   signtype 			smallint  not null comment '1=绝对票数 2=百分比票数',
   primary key (signId)
);

/*==============================================================*/
/* Table: task_sign_data                                        */
/*==============================================================*/
create table task_sign_data
(
   dataId               bigint not null auto_increment,
   voteId               bigint not null comment '投票人',
   voteName             varchar(64) comment '投票人名',
   voteTime             datetime not null comment '投票时间',
   taskId               varchar(64) not null comment '任务Id',
   isAgree              smallint not null comment '是否同意
            1=同意
            2=拒绝
            
            跟task_sign中的decideType是一样',
   primary key (dataId)
);

/*==============================================================*/
/* Table: type_key                                              */
/*==============================================================*/
create table type_key
(
   typeKeyId            bigint not null auto_increment,
   typeKey              varchar(64) not null,
   typeName             varchar(64) not null,
   sn                   bigint,
   primary key (typeKeyId)
);

/*==============================================================*/
/* Table: user_org                                              */
/*==============================================================*/
create table user_org
(
   user_org_id          bigint not null auto_increment,
   userId               bigint comment '主键',
   org_id               bigint,
   is_primary           smallint not null comment '1=主要
            0=非主要',
   is_charge            bigint,
   primary key (user_org_id)
);

/*==============================================================*/
/* Table: user_position                                         */
/*==============================================================*/
create table user_position
(
   user_pos_id          bigint not null auto_increment,
   pos_id               bigint,
   userId               bigint comment '主键',
   isPrimary            smallint,
   primary key (user_pos_id)
);

/*==============================================================*/
/* Table: user_role                                             */
/*==============================================================*/
create table user_role
(
   userId               bigint not null comment '主键',
   roleId               bigint not null,
   primary key (userId, roleId)
);

/*==============================================================*/
/* Table: wf_general                                            */
/*==============================================================*/
create table wf_general
(
   entityId             bigint not null auto_increment comment 'ID',
   itemSubject          varchar(128) not null,
   itemDescp            text not null comment '申请描述',
   runId                bigint comment 'process_run表的主键，通过它可以取到相关的流程运行及审批信息',
   createtime           datetime,
   primary key (entityId)
);

/*==============================================================*/
/* Table: work_plan                                             */
/*==============================================================*/
create table work_plan
(
   planId               bigint not null auto_increment,
   planName             varchar(128) not null comment '计划名称',
   planContent          text comment '计划内容',
   startTime            datetime not null comment '开始日期',
   endTime              datetime not null comment '结束日期',
   typeName             varchar(64) comment '类型名称',
   userId               bigint comment '员工ID',
   proTypeId            bigint,
   issueScope           varchar(2000) comment '发布范围
            0则代表全部部门
            存放所有的参与部门ID
            ',
   participants         varchar(2000) comment '参与人
            0则代表全部参与
            参与人,即员工ID列表',
   principal            varchar(256) not null comment '负责人',
   note                 varchar(500) comment '备注',
   status               smallint not null comment '状态
            1=激活
            0=禁用',
   isPersonal           smallint not null comment '是否为个人计划
            1=则为个人工作计划，这时发布范围，参与人均为空，负责人为当前用户
            0=则代表为其他任务',
   icon                 varchar(128) comment '图标',
   primary key (planId)
);

alter table work_plan comment '工作计划';

CREATE TABLE form_button_right (
	buttonId  		bigint(20) NOT NULL auto_increment ,
	mappingId 		bigint(20) NOT NULL ,
	tableId 		bigint(20) NOT NULL ,
	tableName  		varchar(256) NOT NULL COMMENT '子表名称',
	buttonRight  	smallint(6) NOT NULL COMMENT '隐藏/显示权限  0 隐藏 1 显示' ,
	userType  		smallint(6) NULL COMMENT '1=发起人;2=user;3=role;4=岗位;5=部门、组织;6=部门、组织负责人;7=上下级' ,
	uids  			text,
	unames  		text,
	taskName 		varchar(128) NOT NULL,
	buttonType  	smallint(6) NOT NULL COMMENT '按钮类型 1：增加按钮，2:删除按钮',
	PRIMARY KEY (buttonId)
);

alter table form_button_right comment '子表添加权限表';

ALTER TABLE form_button_right ADD CONSTRAINT form_button_right_ibfk_1 FOREIGN KEY (mappingId) 
REFERENCES form_def_mapping (mappingId) ON DELETE CASCADE;

alter table app_tips add constraint FK_AT_R_AP foreign key (userId)
      references app_user (userId) on delete restrict on update restrict;

alter table appointment add constraint FK_AP_R_AU foreign key (userId)
      references app_user (userId) on delete restrict on update restrict;

alter table arch_dispatch add constraint FK_AVDH_R_ARV foreign key (archivesId)
      references archives (archivesId) on delete cascade on update restrict;

alter table arch_flow_conf add constraint FK_AFC_R_PDN foreign key (defId)
      references pro_definition (defId) on delete cascade on update restrict;

alter table arch_fond add constraint FK_AF_R_GT foreign key (proTypeId)
      references global_type (proTypeId) on delete set null on update restrict;

alter table arch_hasten add constraint FK_ARHN_R_ARV foreign key (archivesId)
      references archives (archivesId) on delete restrict on update restrict;

alter table arch_roll add constraint FK_AR_R_AF foreign key (archFondId)
      references arch_fond (archFondId) on delete set null on update restrict;

alter table arch_roll add constraint FK_AR_R_GT foreign key (proTypeId)
      references global_type (proTypeId) on delete set null on update restrict;

alter table arch_template add constraint FK_AHT_R_FA foreign key (fileId)
      references file_attach (fileId) on delete restrict on update restrict;

alter table arch_template add constraint FK_ART_R_ARVT foreign key (proTypeId)
      references global_type (proTypeId) on delete restrict on update restrict;

alter table archives add constraint FK_ARV_R_ART foreign key (glo_proTypeId)
      references global_type (proTypeId) on delete restrict on update restrict;

alter table archives add constraint FK_ARV_R_ARVT foreign key (proTypeId)
      references global_type (proTypeId) on delete restrict on update restrict;

alter table archives_dep add constraint FK_AVD_R_ARV foreign key (archivesId)
      references archives (archivesId) on delete cascade on update restrict;

alter table archives_doc add constraint FK_ARHD_R_FA foreign key (fileId)
      references file_attach (fileId) on delete set null on update restrict;

alter table archives_doc add constraint FK_ARVD_R_ARV foreign key (archivesId)
      references archives (archivesId) on delete restrict on update restrict;

alter table book add constraint FK_BK_R_BT foreign key (typeId)
      references book_type (typeId) on delete restrict on update restrict;

alter table book_bor_ret add constraint FK_BBR_R_BS foreign key (bookSnId)
      references book_sn (bookSnId) on delete restrict on update restrict;

alter table book_sn add constraint FK_BS_R_BK foreign key (bookId)
      references book (bookId) on delete restrict on update restrict;

alter table borrow_file_list add constraint FK_BFL_AR foreign key (rollId)
      references arch_roll (rollId) on delete restrict on update restrict;

alter table borrow_file_list add constraint FK_BFL_R_AF foreign key (archFondId)
      references arch_fond (archFondId) on delete restrict on update restrict;

alter table borrow_file_list add constraint FK_BFL_R_BR foreign key (recordId)
      references borrow_record (recordId) on delete restrict on update restrict;

alter table borrow_file_list add constraint FK_BFL_R_RF foreign key (rollFileId)
      references roll_file (rollFileId) on delete restrict on update restrict;

alter table borrow_record add constraint FK_BORROW_R_BR_R_AU1_APP_USER foreign key (checkUserId)
      references app_user (userId) on delete set null on update restrict;

alter table cal_file add constraint FK_CF_R_CP foreign key (planId)
      references calendar_plan (planId) on delete restrict on update restrict;

alter table cal_file add constraint FK_CF_R_FA foreign key (fileId)
      references file_attach (fileId) on delete restrict on update restrict;

alter table calendar_plan add constraint FK_CA_R_AU foreign key (userId)
      references app_user (userId) on delete restrict on update restrict;

alter table calendar_plan add constraint FK_CP_R_AUAS foreign key (assignerId)
      references app_user (userId) on delete restrict on update restrict;

alter table car_apply add constraint FK_CRA_R_CR foreign key (carId)
      references car (carId) on delete restrict on update restrict;

alter table cart_repair add constraint FK_CRR_R_CR foreign key (carId)
      references car (carId) on delete restrict on update restrict;

alter table conf_attach add constraint FK_CFA_R_CFC foreign key (confId)
      references conference (confId) on delete cascade on update restrict;

alter table conf_attach add constraint FK_CCFA_R_FA foreign key (fileId)
      references file_attach (fileId) on delete cascade on update restrict;

alter table conf_attend add constraint FK_CA_R_CFC foreign key (confId)
      references conference (confId) on delete cascade on update restrict;

alter table conf_privilege add constraint FK_CP_R_CFC foreign key (confId)
      references conference (confId) on delete cascade on update restrict;

alter table conf_sum_attach add constraint FK_CSA_R_CS foreign key (sumId)
      references conf_summary (sumId) on delete cascade on update restrict;

alter table conf_sum_attach add constraint FK_CSA_R_FA foreign key (fileId)
      references file_attach (fileId) on delete restrict on update restrict;

alter table conf_summary add constraint FK_CS_R_CFC foreign key (confId)
      references conference (confId) on delete restrict on update restrict;

alter table conference add constraint FK_CF_R_BT_BD foreign key (typeId)
      references board_type (typeId) on delete set null on update restrict;

alter table conference add constraint FK_CFC_R_BDM foreign key (roomId)
      references boardroo (roomId) on delete set null on update restrict;

alter table contract add constraint FK_CT_R_PT foreign key (projectId)
      references project (projectId) on delete restrict on update restrict;

alter table contract_config add constraint FK_CC_R_CT foreign key (contractId)
      references contract (contractId) on delete restrict on update restrict;

alter table contract_file add constraint FK_CTF_R_CT foreign key (contractId)
      references contract (contractId) on delete restrict on update restrict;

alter table contract_file add constraint FK_CTF_R_FA foreign key (fileId)
      references file_attach (fileId) on delete restrict on update restrict;

alter table cus_connection add constraint FK_CC_R_CS foreign key (customerId)
      references customer (customerId) on delete restrict on update restrict;

alter table cus_linkman add constraint FK_CLM_R_CS foreign key (customerId)
      references customer (customerId) on delete restrict on update restrict;

alter table dep_pos add constraint FK_Reference_157 foreign key (org_id)
      references organization (org_id) on delete restrict on update restrict;

alter table dep_pos add constraint FK_Reference_158 foreign key (pos_id)
      references position (pos_id) on delete restrict on update restrict;

alter table depre_record add constraint FK_DR_R_FA foreign key (assetsId)
      references fixed_assets (assetsId) on delete restrict on update restrict;

alter table diary add constraint FK_DY_R_AU foreign key (userId)
      references app_user (userId) on delete restrict on update restrict;

alter table dictionary add constraint DTY_R_GT foreign key (proTypeId)
      references global_type (proTypeId) on delete set null on update restrict;

alter table doc_file add constraint FK_DF_F_DT foreign key (docId)
      references document (docId) on delete restrict on update restrict;

alter table doc_file add constraint FK_DF_R_FA foreign key (fileId)
      references file_attach (fileId) on delete restrict on update restrict;

alter table doc_folder add constraint FK_DF_R_AU foreign key (userId)
      references app_user (userId) on delete restrict on update restrict;

alter table doc_history add constraint FK_DHY_R_ARVD foreign key (docId)
      references archives_doc (docId) on delete restrict on update restrict;

alter table doc_history add constraint FK_DHY_R_FA foreign key (fileId)
      references file_attach (fileId) on delete set null on update restrict;

alter table doc_privilege add constraint FK_DP_R_DF foreign key (folderId)
      references doc_folder (folderId) on delete cascade on update restrict;

alter table doc_privilege add constraint FK_DP_R_DT foreign key (docId)
      references document (docId) on delete cascade on update restrict;

alter table document add constraint FK_DT_R_AU foreign key (userId)
      references app_user (userId) on delete restrict on update restrict;

alter table document add constraint FK_DT_R_DF foreign key (folderId)
      references doc_folder (folderId) on delete set null on update restrict;

alter table duty add constraint FK_DUY_R_AU foreign key (userId)
      references app_user (userId) on delete restrict on update restrict;

alter table duty add constraint FK_DUY_R_DS foreign key (systemId)
      references duty_system (systemId) on delete restrict on update restrict;

alter table duty_register add constraint FK_DR_R_AU foreign key (userId)
      references app_user (userId) on delete restrict on update restrict;

alter table duty_register add constraint FK_DR_R_DS foreign key (sectionId)
      references duty_section (sectionId) on delete restrict on update restrict;

alter table emp_profile add constraint FK_EPF_R_AU foreign key (userId)
      references app_user (userId) on delete restrict on update restrict;

alter table emp_profile add constraint FK_PT_R_EPF foreign key (jobId)
      references position (pos_id) on delete restrict on update restrict;

alter table emp_profile add constraint FK_SD_R_SY foreign key (standardId)
      references stand_salary (standardId) on delete restrict on update restrict;

alter table errands_register add constraint FK_ERP_R_AU foreign key (approvalId)
      references app_user (userId) on delete restrict on update restrict;

alter table errands_register add constraint FK_ER_R_AU foreign key (userId)
      references app_user (userId) on delete restrict on update restrict;

alter table field_rights add constraint FK_FR_R_FDM foreign key (mappingId)
      references form_def_mapping (mappingId) on delete cascade on update restrict;

alter table field_rights add constraint FK_FR_R_FF foreign key (fieldId)
      references form_field (fieldId) on delete cascade on update restrict;

alter table file_attach add constraint FK_FLE_AT_R_GT foreign key (proTypeId)
      references global_type (proTypeId) on delete set null on update restrict;

alter table fixed_assets add constraint FK_FA_R_AT foreign key (assetsTypeId)
      references assets_type (assetsTypeId) on delete restrict on update restrict;

alter table fixed_assets add constraint FK_FA_R_DT foreign key (depreTypeId)
      references depre_type (depreTypeId) on delete restrict on update restrict;

alter table form_def_mapping add constraint FK_FDM_R_FD foreign key (formDefId)
      references form_def (formDefId) on delete cascade on update restrict;

alter table form_def_mapping add constraint FK_FDM_R_PD foreign key (defId)
      references pro_definition (defId) on delete cascade on update restrict;

alter table form_field add constraint FK_FF_R_FD foreign key (tableId)
      references form_table (tableId) on delete cascade on update restrict;

alter table form_table add constraint FK_FT_R_FD foreign key (formDefId)
      references form_def (formDefId) on delete cascade on update restrict;

alter table form_template add constraint FK_FT_R_FDM foreign key (mappingId)
      references form_def_mapping (mappingId) on delete set null on update restrict;

alter table fun_url add constraint FK_FU_R_AFN foreign key (functionId)
      references app_function (functionId) on delete restrict on update restrict;

alter table goods_apply add constraint FK_GA_R_OG foreign key (goodsId)
      references office_goods (goodsId) on delete restrict on update restrict;

alter table in_message add constraint FK_IM_R_AU foreign key (userId)
      references app_user (userId) on delete restrict on update restrict;

alter table in_message add constraint FK_IM_R_SM foreign key (messageId)
      references short_message (messageId) on delete restrict on update restrict;

alter table in_stock add constraint FK_IS_R_OG foreign key (goodsId)
      references office_goods (goodsId) on delete restrict on update restrict;

alter table index_display add constraint FK_ID_R_AU foreign key (userId)
      references app_user (userId) on delete restrict on update restrict;

alter table job_change add constraint FK_JOB_CH_R_POS_NEW foreign key (newJobId)
      references position (pos_id) on delete cascade on update restrict;

alter table job_change add constraint FK_JOB_CH_R_POS foreign key (orgJobId)
      references position (pos_id) on delete cascade on update restrict;

alter table mail add constraint FK_ML_R_AU foreign key (senderId)
      references app_user (userId) on delete restrict on update restrict;

alter table mail_attach add constraint FK_MA_R_FA foreign key (fileId)
      references file_attach (fileId) on delete restrict on update restrict;

alter table mail_attach add constraint FK_MA_R_ML foreign key (mailId)
      references mail (mailId) on delete restrict on update restrict;

alter table mail_box add constraint FK_MB_R_AU foreign key (userId)
      references app_user (userId) on delete restrict on update restrict;

alter table mail_box add constraint FK_MB_R_FD foreign key (folderId)
      references mail_folder (folderId) on delete restrict on update restrict;

alter table mail_box add constraint FK_MB_R_ML foreign key (mailId)
      references mail (mailId) on delete restrict on update restrict;

alter table mail_folder add constraint FK_FD_R_AU foreign key (userId)
      references app_user (userId) on delete restrict on update restrict;

alter table meeting_attend add constraint FK_MTA_R_MT foreign key (mettingId)
      references meeting (mettingId) on delete restrict on update restrict;

alter table meeting_file add constraint FK_MF_R_FA foreign key (fileId)
      references file_attach (fileId) on delete restrict on update restrict;

alter table meeting_file add constraint FK_MF_R_MT foreign key (mettingId)
      references meeting (mettingId) on delete restrict on update restrict;

alter table news add constraint FK_NEWS_R_SCT foreign key (sectionId)
      references section (sectionId) on delete set null on update restrict;

alter table news_comment add constraint FK_NC_R_AU foreign key (userId)
      references app_user (userId) on delete restrict on update restrict;

alter table news_comment add constraint FK_NC_R_NS foreign key (newsId)
      references news (newsId) on delete restrict on update restrict;

alter table office_goods add constraint FK_OG_R_OGT foreign key (typeId)
      references office_goods_type (typeId) on delete restrict on update restrict;

alter table organization add constraint FK_ORG_R_DMS foreign key (dem_id)
      references demension (dem_id) on delete cascade on update restrict;

alter table out_mail add constraint FK_OM_R_OMF foreign key (folderId)
      references out_mail_folder (folderId) on delete restrict on update restrict;

alter table out_mail_file add constraint FK_OMF_R_FA foreign key (fileId)
      references file_attach (fileId) on delete restrict on update restrict;

alter table out_mail_file add constraint FK_OMF_R_OM foreign key (mailId)
      references out_mail (mailId) on delete restrict on update restrict;

alter table out_mail_folder add constraint FK_OMF_RAU foreign key (userId)
      references app_user (userId) on delete restrict on update restrict;

alter table out_mail_user_seting add constraint FK_OMU_R_AU foreign key (userId)
      references app_user (userId) on delete set null on update restrict;

alter table paint_template add constraint FK_PTE_R_FA foreign key (fileId)
      references file_attach (fileId) on delete cascade on update restrict;

alter table phone_book add constraint FK_PB_R_AU foreign key (userId)
      references app_user (userId) on delete restrict on update restrict;

alter table phone_book add constraint FK_PB_R_PG foreign key (groupId)
      references phone_group (groupId) on delete restrict on update restrict;

alter table phone_group add constraint FK_PG_R_AU foreign key (userId)
      references app_user (userId) on delete restrict on update restrict;

alter table plan_attend add constraint FK_PAD_R_UA foreign key (userId)
      references app_user (userId) on delete restrict on update restrict;

alter table plan_attend add constraint FK_PAD_R_WP foreign key (planId)
      references work_plan (planId) on delete restrict on update restrict;

alter table plan_file add constraint FK_PA_R_FA foreign key (fileId)
      references file_attach (fileId) on delete restrict on update restrict;

alter table plan_file add constraint FK_PA_R_WP foreign key (planId)
      references work_plan (planId) on delete restrict on update restrict;

alter table position_sub add constraint FK_POS_SUB_R_POS foreign key (mainPositionId)
      references position (pos_id) on delete cascade on update restrict;

alter table position_sub add constraint FK_POS_SUB_SUB_R_POS foreign key (subPositionId)
      references position (pos_id) on delete cascade on update restrict;

alter table pro_def_rights add constraint FK_PDR_R_GT foreign key (proTypeId)
      references global_type (proTypeId) on delete set null on update restrict;

alter table pro_def_rights add constraint FK_PDR_R_PD foreign key (defId)
      references pro_definition (defId) on delete cascade on update restrict;

alter table pro_definition add constraint FK_PD_R_GT foreign key (proTypeId)
      references global_type (proTypeId) on delete set null on update restrict;

alter table process_form add constraint FK_PF_R_PR foreign key (runId)
      references process_run (runId) on delete restrict on update restrict;

alter table process_module add constraint FK_PM_R_PDI foreign key (defId)
      references pro_definition (defId) on delete cascade on update restrict;

alter table process_run add constraint FK_PRORN__R_FORM_DEF foreign key (formDefId)
      references form_def (formDefId) on delete set null on update restrict;

alter table process_run add constraint FK_PR_R_AU foreign key (userId)
      references app_user (userId) on delete restrict on update restrict;

alter table process_run add constraint FK_PR_R_PD foreign key (defId)
      references pro_definition (defId) on delete restrict on update restrict;

alter table product add constraint FK_PD_R_PUT foreign key (providerId)
      references provider (providerId) on delete restrict on update restrict;

alter table project add constraint FK_PR_R_CS foreign key (customerId)
      references customer (customerId) on delete restrict on update restrict;

alter table project add constraint FK_PT_R_AU foreign key (userId)
      references app_user (userId) on delete restrict on update restrict;

alter table project_file add constraint FK_PF_R_FA foreign key (fileId)
      references file_attach (fileId) on delete restrict on update restrict;

alter table project_file add constraint FK_PF_R_PT foreign key (projectId)
      references project (projectId) on delete restrict on update restrict;

alter table reg_attach add constraint FK_RA_R_FA foreign key (fileId)
      references file_attach (fileId) on delete cascade on update restrict;

alter table reg_attach add constraint FK_RA_R_GT foreign key (regId)
      references regulation (regId) on delete cascade on update restrict;

alter table regulation add constraint FK_RG_R_GT foreign key (proTypeId)
      references global_type (proTypeId) on delete set null on update restrict;

alter table relative_user add constraint FK_RELATIVE_RU_R_AU_APP_USER foreign key (userId)
      references app_user (userId) on delete cascade on update restrict;

alter table relative_user add constraint FK_RELATIVE_RU_R_RJ_RELATIVE foreign key (reJobId)
      references relative_job (reJobId) on delete cascade on update restrict;

alter table report_param add constraint FK_RP_R_RPT foreign key (reportId)
      references report_template (reportId) on delete restrict on update restrict;

alter table resume_file add constraint FK_RMF_R_FA foreign key (fileId)
      references file_attach (fileId) on delete restrict on update restrict;

alter table resume_file add constraint FK_RMF_R_RM foreign key (resumeId)
      references resume (resumeId) on delete restrict on update restrict;

alter table role_fun add constraint FK_RF_R_AFN foreign key (functionId)
      references app_function (functionId) on delete restrict on update restrict;

alter table role_fun add constraint FK_RF_R_AR foreign key (roleId)
      references app_role (roleId) on delete restrict on update restrict;

alter table role_position add constraint FK_ROL_POS_R_POS foreign key (pos_id)
      references position (pos_id) on delete cascade on update restrict;

alter table role_position add constraint FK_RPOS_R_APPROLE foreign key (roleId)
      references app_role (roleId) on delete restrict on update restrict;

alter table roll_file add constraint FK_RF_R_AR_R foreign key (rollId)
      references arch_roll (rollId) on delete set null on update restrict;

alter table roll_file add constraint FK_RF_R_GT foreign key (proTypeId)
      references global_type (proTypeId) on delete set null on update restrict;

alter table roll_file_list add constraint FK_RFL_R_F_A foreign key (fileId)
      references file_attach (fileId) on delete cascade on update restrict;

alter table roll_file_list add constraint FK_RFL_R_RF foreign key (rollFileId)
      references roll_file (rollFileId) on delete cascade on update restrict;

alter table run_data add constraint FK_FD_R_PR foreign key (runId)
      references process_run (runId) on delete cascade on update restrict;

alter table seal add constraint FK_SE_R_FA foreign key (fileId)
      references file_attach (fileId) on delete cascade on update restrict;

alter table short_message add constraint FK_SM_R_AU foreign key (senderId)
      references app_user (userId) on delete restrict on update restrict;

alter table stand_salary_item add constraint FK_SSI_R_SSY foreign key (standardId)
      references stand_salary (standardId) on delete restrict on update restrict;

alter table task_sign add constraint FK_TASK_SIG_TS_R_PRO_NODE_SET
	foreign key (setId) references pro_node_set (setId) on delete restrict on update restrict;

alter table user_org add constraint FK_USER_ORG_O_APP_USER foreign key (userId)
      references app_user (userId) on delete restrict on update restrict;

alter table user_org add constraint FK_USER_ORG_R_ORG foreign key (org_id)
      references organization (org_id) on delete cascade on update restrict;

alter table user_position add constraint FK_USER_POS_R_POSITION foreign key (pos_id)
      references position (pos_id) on delete cascade on update restrict;

alter table user_position add constraint FK_USER_POS_R_APP_USER foreign key (userId)
      references app_user (userId) on delete restrict on update restrict;

alter table user_role add constraint FK_UR_R_AR foreign key (roleId)
      references app_role (roleId) on delete restrict on update restrict;

alter table user_role add constraint FK_UR_R_AU foreign key (userId)
      references app_user (userId) on delete restrict on update restrict;

alter table work_plan add constraint FK_WP_R_GT foreign key (proTypeId)
      references global_type (proTypeId) on delete restrict on update restrict;

alter table work_plan add constraint FK_WP_R_AU foreign key (userId)
      references app_user (userId) on delete restrict on update restrict;

alter table pro_node_set add constraint FK_PRO_NODE_R_PRO_DEF foreign key (defId)
      references pro_definition (defId) on delete cascade;
      
alter table pro_user_set add constraint FK_PRO_USR_R_PRO_DEF foreign key (defId)
      references pro_definition (defId) on delete cascade on update restrict;
      
alter table subordinate add constraint FK_SUBORDINATE_D_DEM foreign key (dem_id)
	  references demension (dem_id) on delete restrict on update restrict;

alter table subordinate add constraint FK_SUBORDINATE_U_APPUSER foreign key (userId)
	  references app_user (userId) on delete restrict on update restrict;
	  
alter table subordinate add constraint FK_SUBORDINATE_J_APPUSER foreign key (jobuserId)
	  references app_user (userId) on delete restrict on update restrict;

alter table pro_user_set add constraint FK_PRO_USR_D_DEM foreign key (demId)
	references demension (dem_id) on delete restrict on update restrict;
