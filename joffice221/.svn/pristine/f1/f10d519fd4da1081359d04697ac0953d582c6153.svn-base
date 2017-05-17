/*==============================================================*/
/* DBMS name:      ORACLE Version 10gR2                         */
/* Created on:     2012-3-6 10:34:53                            */
/*==============================================================*/

/*==============================================================*/
/* Table: APP_FUNCTION                                          */
/*==============================================================*/
CREATE TABLE APP_FUNCTION  (
   FUNCTIONID           NUMBER(18)                      NOT NULL,
   FUNKEY               VARCHAR2(64)                    NOT NULL,
   FUNNAME              VARCHAR2(128)                   NOT NULL,
   CONSTRAINT PK_APP_FUNCTION PRIMARY KEY (FUNCTIONID),
   CONSTRAINT AK_UQ_RSKEY_APP_FUNC UNIQUE (FUNKEY)
);

COMMENT ON COLUMN APP_FUNCTION.FUNKEY IS
'权限Key';

COMMENT ON COLUMN APP_FUNCTION.FUNNAME IS
'权限名称';

/*==============================================================*/
/* Table: APP_ROLE                                              */
/*==============================================================*/
CREATE TABLE APP_ROLE  (
   ROLEID               NUMBER(18)                      NOT NULL,
   ROLENAME             VARCHAR2(128)                   NOT NULL,
   ROLEDESC             VARCHAR2(128),
   STATUS               SMALLINT                        NOT NULL,
   RIGHTS               CLOB,
   ISDEFAULTIN          SMALLINT                        NOT NULL,
   CONSTRAINT PK_APP_ROLE PRIMARY KEY (ROLEID)
);

COMMENT ON TABLE APP_ROLE IS
'角色表';

COMMENT ON COLUMN APP_ROLE.ROLENAME IS
'角色名称';

COMMENT ON COLUMN APP_ROLE.ROLEDESC IS
'角色描述';

COMMENT ON COLUMN APP_ROLE.STATUS IS
'状态';

/*==============================================================*/
/* Table: APP_TIPS                                              */
/*==============================================================*/
CREATE TABLE APP_TIPS  (
   TIPSID               NUMBER(18)                      NOT NULL,
   USERID               NUMBER(18)                      NOT NULL,
   TIPSNAME             VARCHAR2(128),
   CONTENT              VARCHAR2(2048),
   DISHEIGHT            NUMBER(18),
   DISWIDTH             NUMBER(18),
   DISLEFT              NUMBER(18),
   DISTOP               NUMBER(18),
   DISLEVEL             NUMBER(18),
   CREATETIME           DATE                            NOT NULL,
   CONSTRAINT PK_APP_TIPS PRIMARY KEY (TIPSID)
);

COMMENT ON TABLE APP_TIPS IS
'用户便签';

/*==============================================================*/
/* Table: APP_USER                                              */
/*==============================================================*/
CREATE TABLE APP_USER  (
   USERID               NUMBER(18)                      NOT NULL,
   USERNAME             VARCHAR2(128)                   NOT NULL,
   TITLE                SMALLINT                        NOT NULL,
   DEPID                NUMBER(18),
   PASSWORD             VARCHAR2(128)                   NOT NULL,
   EMAIL                VARCHAR2(128)                   NOT NULL,
   JOBID                NUMBER(18),
   PHONE                VARCHAR2(32),
   MOBILE               VARCHAR2(32),
   FAX                  VARCHAR2(32),
   ADDRESS              VARCHAR2(64),
   ZIP                  VARCHAR2(32),
   PHOTO                VARCHAR2(128),
   ACCESSIONTIME        DATE                            NOT NULL,
   STATUS               SMALLINT                        NOT NULL,
   EDUCATION            VARCHAR2(64),
   FULLNAME             VARCHAR2(50)                    NOT NULL,
   DELFLAG              SMALLINT                        NOT NULL,
   CONSTRAINT PK_APP_USER PRIMARY KEY (USERID)
);

COMMENT ON TABLE APP_USER IS
'app_user
用户表';

COMMENT ON COLUMN APP_USER.USERID IS
'主键';

COMMENT ON COLUMN APP_USER.USERNAME IS
'用户名';

COMMENT ON COLUMN APP_USER.TITLE IS
'1=先生
0=女士
小姐';

COMMENT ON COLUMN APP_USER.PASSWORD IS
'密码';

COMMENT ON COLUMN APP_USER.EMAIL IS
'邮件';

COMMENT ON COLUMN APP_USER.JOBID IS
'职位';

COMMENT ON COLUMN APP_USER.PHONE IS
'电话';

COMMENT ON COLUMN APP_USER.MOBILE IS
'手机';

COMMENT ON COLUMN APP_USER.FAX IS
'传真';

COMMENT ON COLUMN APP_USER.ADDRESS IS
'地址';

COMMENT ON COLUMN APP_USER.ZIP IS
'邮编';

COMMENT ON COLUMN APP_USER.PHOTO IS
'相片';

COMMENT ON COLUMN APP_USER.ACCESSIONTIME IS
'入职时间';

COMMENT ON COLUMN APP_USER.STATUS IS
'状态
1=激活
0=禁用
2=离职
';

COMMENT ON COLUMN APP_USER.DELFLAG IS
'0=未删除
1=删除';

/*==============================================================*/
/* Table: APPOINTMENT                                           */
/*==============================================================*/
CREATE TABLE APPOINTMENT  (
   APPOINTID            NUMBER(18)                      NOT NULL,
   USERID               NUMBER(18),
   SUBJECT              VARCHAR2(128)                   NOT NULL,
   STARTTIME            DATE                            NOT NULL,
   ENDTIME              DATE                            NOT NULL,
   CONTENT              CLOB                            NOT NULL,
   NOTES                VARCHAR2(1000),
   LOCATION             VARCHAR2(150)                   NOT NULL,
   INVITEEMAILS         VARCHAR2(1000),
   SENDMESSAGE          SMALLINT,
   SENDMAIL             SMALLINT,
   CONSTRAINT PK_APPOINTMENT PRIMARY KEY (APPOINTID)
);

COMMENT ON TABLE APPOINTMENT IS
'约会管理';

COMMENT ON COLUMN APPOINTMENT.USERID IS
'主键';

COMMENT ON COLUMN APPOINTMENT.SUBJECT IS
'主题';

COMMENT ON COLUMN APPOINTMENT.STARTTIME IS
'开始时间';

COMMENT ON COLUMN APPOINTMENT.ENDTIME IS
'结束时间';

COMMENT ON COLUMN APPOINTMENT.CONTENT IS
'约会内容';

COMMENT ON COLUMN APPOINTMENT.NOTES IS
'备注';

COMMENT ON COLUMN APPOINTMENT.LOCATION IS
'地点';

/*==============================================================*/
/* Table: ARCH_DISPATCH                                         */
/*==============================================================*/
CREATE TABLE ARCH_DISPATCH  (
   DISPATCHID           NUMBER(18)                      NOT NULL,
   ARCHIVESID           NUMBER(18),
   DISPATCHTIME         DATE                            NOT NULL,
   USERID               NUMBER(18)                      NOT NULL,
   FULLNAME             VARCHAR2(128),
   ISREAD               SMALLINT,
   SUBJECT              VARCHAR2(256),
   READFEEDBACK         VARCHAR2(1024),
   ARCHUSERTYPE         SMALLINT                       DEFAULT 0 NOT NULL,
   DISROLEID            NUMBER(18),
   DISROLENAME          VARCHAR2(64),
   CONSTRAINT PK_ARCH_DISPATCH PRIMARY KEY (DISPATCHID)
);

COMMENT ON COLUMN ARCH_DISPATCH.ARCHUSERTYPE IS
'0=阅读人员
1=承办人
2=分发负责人
';

/*==============================================================*/
/* Table: ARCH_FLOW_CONF                                        */
/*==============================================================*/
CREATE TABLE ARCH_FLOW_CONF  (
   CONFIGID             NUMBER(18)                      NOT NULL,
   DEFID                NUMBER(18),
   PROCESSNAME          VARCHAR2(128)                   NOT NULL,
   ARCHTYPE             SMALLINT                        NOT NULL,
   DEPID                NUMBER(18),
   CONSTRAINT PK_ARCH_FLOW_CONF PRIMARY KEY (CONFIGID)
);

COMMENT ON TABLE ARCH_FLOW_CONF IS
'公文流程设置';

COMMENT ON COLUMN ARCH_FLOW_CONF.ARCHTYPE IS
'0=发文
1=收文';

/*==============================================================*/
/* Table: ARCH_FOND                                             */
/*==============================================================*/
CREATE TABLE ARCH_FOND  (
   ARCHFONDID           NUMBER(18)                      NOT NULL,
   AFNO                 VARCHAR2(64)                    NOT NULL,
   AFNAME               VARCHAR2(128)                   NOT NULL,
   SHORTDESC            VARCHAR2(4000),
   DESCP                CLOB,
   CLEARUPDESC          VARCHAR2(4000),
   CREATETIME           DATE,
   UPDATETIME           DATE,
   CREATORNAME          VARCHAR2(32),
   CREATORID            NUMBER(18),
   CASENUMS             NUMBER(18),
   STATUS               SMALLINT,
   PROTYPEID            NUMBER(18),
   TYPENAME             VARCHAR2(128),
   OPENSTYLE            VARCHAR2(64),
   CONSTRAINT PK_ARCH_FOND PRIMARY KEY (ARCHFONDID)
);

COMMENT ON COLUMN ARCH_FOND.AFNO IS
'全宗号';

COMMENT ON COLUMN ARCH_FOND.AFNAME IS
'全宗名';

COMMENT ON COLUMN ARCH_FOND.SHORTDESC IS
'全宗概述';

COMMENT ON COLUMN ARCH_FOND.DESCP IS
'全宗描述';

COMMENT ON COLUMN ARCH_FOND.CLEARUPDESC IS
'全宗整理描述';

COMMENT ON COLUMN ARCH_FOND.CREATETIME IS
'创建时间';

COMMENT ON COLUMN ARCH_FOND.UPDATETIME IS
'最后更新时间';

COMMENT ON COLUMN ARCH_FOND.CREATORNAME IS
'创建人';

COMMENT ON COLUMN ARCH_FOND.CREATORID IS
'创建人ID';

COMMENT ON COLUMN ARCH_FOND.CASENUMS IS
'案件数';

COMMENT ON COLUMN ARCH_FOND.STATUS IS
'0草稿
1启用
-1禁用
';

COMMENT ON COLUMN ARCH_FOND.TYPENAME IS
'全宗分类名称';

COMMENT ON COLUMN ARCH_FOND.OPENSTYLE IS
'开放形式(来自数字字典)';

/*==============================================================*/
/* Table: ARCH_HASTEN                                           */
/*==============================================================*/
CREATE TABLE ARCH_HASTEN  (
   RECORDID             NUMBER(18)                      NOT NULL,
   ARCHIVESID           NUMBER(18),
   CONTENT              VARCHAR2(1024),
   CREATETIME           DATE,
   HASTENFULLNAME       VARCHAR2(64),
   HANDLERFULLNAME      VARCHAR2(64),
   HANDLERUSERID        NUMBER(18),
   CONSTRAINT PK_ARCH_HASTEN PRIMARY KEY (RECORDID)
);

COMMENT ON COLUMN ARCH_HASTEN.CONTENT IS
'催办内容';

COMMENT ON COLUMN ARCH_HASTEN.CREATETIME IS
'催办时间';

COMMENT ON COLUMN ARCH_HASTEN.HASTENFULLNAME IS
'催办人';

COMMENT ON COLUMN ARCH_HASTEN.HANDLERFULLNAME IS
'承办人';

COMMENT ON COLUMN ARCH_HASTEN.HANDLERUSERID IS
'承办人ID';

/*==============================================================*/
/* Table: ARCH_ROLL                                             */
/*==============================================================*/
CREATE TABLE ARCH_ROLL  (
   ROLLID               NUMBER(18)                      NOT NULL,
   ARCHFONDID           NUMBER(18),
   PROTYPEID            NUMBER(18),
   TYPENAME             VARCHAR2(32),
   ROLLLNAME            VARCHAR2(128)                   NOT NULL,
   AFNO                 VARCHAR2(64)                    NOT NULL,
   ROLLNO               VARCHAR2(64)                    NOT NULL,
   CATNO                VARCHAR2(64),
   TIMELIMIT            VARCHAR2(64),
   STARTTIME            DATE,
   ENDTIME              DATE,
   OPENSTYLE            VARCHAR2(64),
   AUTHOR               VARCHAR2(32),
   SETUPTIME            DATE,
   CHECKER              VARCHAR2(32),
   CREATORNAME          VARCHAR2(32),
   CREATETIME           DATE,
   KEYWORDS             VARCHAR2(512),
   EDITCOMPANY          VARCHAR2(128),
   EDITDEP              VARCHAR2(128),
   DECP                 CLOB,
   STATUS               SMALLINT,
   CONSTRAINT PK_ARCH_ROLL PRIMARY KEY (ROLLID)
);

COMMENT ON COLUMN ARCH_ROLL.TIMELIMIT IS
'保管期限
长久
长期
短期
10年
15年
20年

';

COMMENT ON COLUMN ARCH_ROLL.STARTTIME IS
'起始日期';

COMMENT ON COLUMN ARCH_ROLL.ENDTIME IS
'结束日期';

COMMENT ON COLUMN ARCH_ROLL.OPENSTYLE IS
'开放形式';

COMMENT ON COLUMN ARCH_ROLL.AUTHOR IS
'立卷人';

COMMENT ON COLUMN ARCH_ROLL.SETUPTIME IS
'立卷时间';

COMMENT ON COLUMN ARCH_ROLL.CHECKER IS
'检查人';

COMMENT ON COLUMN ARCH_ROLL.CREATORNAME IS
'录入人';

COMMENT ON COLUMN ARCH_ROLL.CREATETIME IS
'录入时间';

COMMENT ON COLUMN ARCH_ROLL.STATUS IS
'1=正常
0=销毁 ';

/*==============================================================*/
/* Table: ARCH_TEMPLATE                                         */
/*==============================================================*/
CREATE TABLE ARCH_TEMPLATE  (
   TEMPLATEID           NUMBER(18)                      NOT NULL,
   FILEID               NUMBER(18),
   PROTYPEID            NUMBER(18),
   TEMPNAME             VARCHAR2(128)                   NOT NULL,
   TEMPPATH             VARCHAR2(256)                   NOT NULL,
   CONSTRAINT PK_ARCH_TEMPLATE PRIMARY KEY (TEMPLATEID)
);

COMMENT ON TABLE ARCH_TEMPLATE IS
'公文模板';

COMMENT ON COLUMN ARCH_TEMPLATE.TEMPNAME IS
'模板名称';

COMMENT ON COLUMN ARCH_TEMPLATE.TEMPPATH IS
'路径';

/*==============================================================*/
/* Table: ARCHIVES                                              */
/*==============================================================*/
CREATE TABLE ARCHIVES  (
   ARCHIVESID           NUMBER(18)                      NOT NULL,
   TYPENAME             VARCHAR2(128),
   ARCHIVESNO           VARCHAR2(100)                   NOT NULL,
   ISSUEDEP             VARCHAR2(128),
   PROTYPEID            NUMBER(18),
   GLO_PROTYPEID        NUMBER(18),
   SUBJECT              VARCHAR2(256)                   NOT NULL,
   CREATETIME           DATE                            NOT NULL,
   ISSUEDATE            DATE                            NOT NULL,
   STATUS               VARCHAR2(256)                   NOT NULL,
   SHORTCONTENT         VARCHAR2(1024),
   FILECOUNTS           NUMBER(18)                     DEFAULT 0,
   PRIVACYLEVEL         VARCHAR2(50)                   DEFAULT '普通',
   URGENTLEVEL          VARCHAR2(50)                   DEFAULT '普通',
   ISSUER               VARCHAR2(50),
   ISSUERID             NUMBER(18),
   KEYWORDS             VARCHAR2(256),
   SOURCES              VARCHAR2(50),
   ARCHTYPE             SMALLINT                       DEFAULT 0 NOT NULL,
   RECDEPIDS            VARCHAR2(2000),
   RECDEPNAMES          VARCHAR2(2000),
   HANDLERUIDS          VARCHAR2(256),
   HANDLERUNAMES        VARCHAR2(256),
   ORGARCHIVESID        NUMBER(18),
   DEPSIGNNO            VARCHAR2(100),
   RUNID                NUMBER(18),
   ARCHSTATUS           SMALLINT,
   DEPID                NUMBER(18),
   CONSTRAINT PK_ARCHIVES PRIMARY KEY (ARCHIVESID)
);

COMMENT ON TABLE ARCHIVES IS
'收发公文';

COMMENT ON COLUMN ARCHIVES.TYPENAME IS
'公文类型名称';

COMMENT ON COLUMN ARCHIVES.ARCHIVESNO IS
'发文字号';

COMMENT ON COLUMN ARCHIVES.ISSUEDEP IS
'发文机关或部门';

COMMENT ON COLUMN ARCHIVES.SUBJECT IS
'文件标题';

COMMENT ON COLUMN ARCHIVES.ISSUEDATE IS
'发布日期';

COMMENT ON COLUMN ARCHIVES.STATUS IS
'公文状态
0=拟稿、修改状态
1=发文状态
2=归档状态';

COMMENT ON COLUMN ARCHIVES.SHORTCONTENT IS
'内容简介';

COMMENT ON COLUMN ARCHIVES.FILECOUNTS IS
'文件数';

COMMENT ON COLUMN ARCHIVES.PRIVACYLEVEL IS
'秘密等级
普通
秘密
机密
绝密';

COMMENT ON COLUMN ARCHIVES.URGENTLEVEL IS
'紧急程度
普通
紧急
特急
特提';

COMMENT ON COLUMN ARCHIVES.ISSUER IS
'发文人';

COMMENT ON COLUMN ARCHIVES.ISSUERID IS
'发文人ID';

COMMENT ON COLUMN ARCHIVES.KEYWORDS IS
'主题词';

COMMENT ON COLUMN ARCHIVES.SOURCES IS
'公文来源
仅在收文中指定，发公文不需要指定
上级公文
下级公文';

COMMENT ON COLUMN ARCHIVES.ARCHTYPE IS
'0=发文
1=收文';

COMMENT ON COLUMN ARCHIVES.RECDEPIDS IS
'用于存储接收公文的部门ID,使用,进行分开';

COMMENT ON COLUMN ARCHIVES.RECDEPNAMES IS
'用于存储接收公文的部门的名称，使用,进行分开';

COMMENT ON COLUMN ARCHIVES.HANDLERUIDS IS
'在收文中使用，多个用户ID用'',''分割';

COMMENT ON COLUMN ARCHIVES.HANDLERUNAMES IS
'用于收文，存储多个拟办用户名，用‘，’分割';

COMMENT ON COLUMN ARCHIVES.ORGARCHIVESID IS
'用于收文时使用，指向原公文ID';

COMMENT ON COLUMN ARCHIVES.DEPSIGNNO IS
'用于收文时，部门对自身的公文自编号';

COMMENT ON COLUMN ARCHIVES.RUNID IS
'流程运行id
通过该id可以查看该公文的审批历史';

COMMENT ON COLUMN ARCHIVES.ARCHSTATUS IS
'归档状态
1=已经归档
0=尚未归档';

/*==============================================================*/
/* Table: ARCHIVES_DEP                                          */
/*==============================================================*/
CREATE TABLE ARCHIVES_DEP  (
   ARCHDEPID            NUMBER(18)                      NOT NULL,
   SIGNNO               VARCHAR2(128),
   ARCHIVESID           NUMBER(18)                      NOT NULL,
   SUBJECT              VARCHAR2(256)                   NOT NULL,
   STATUS               SMALLINT                        NOT NULL,
   SIGNTIME             DATE,
   SIGNFULLNAME         VARCHAR2(64),
   SIGNUSERID           NUMBER(18),
   HANDLEFEEDBACK       VARCHAR2(4000),
   ISMAIN               SMALLINT                       DEFAULT 1 NOT NULL,
   DEPID                NUMBER(18),
   CONSTRAINT PK_ARCHIVES_DEP PRIMARY KEY (ARCHDEPID)
);

COMMENT ON COLUMN ARCHIVES_DEP.ARCHDEPID IS
'主键';

COMMENT ON COLUMN ARCHIVES_DEP.SIGNNO IS
'自编号';

COMMENT ON COLUMN ARCHIVES_DEP.ARCHIVESID IS
'所属公文';

COMMENT ON COLUMN ARCHIVES_DEP.SUBJECT IS
'公文标题';

COMMENT ON COLUMN ARCHIVES_DEP.STATUS IS
'签收状态
0=未签收
1=已签收';

COMMENT ON COLUMN ARCHIVES_DEP.SIGNTIME IS
'签收日期';

COMMENT ON COLUMN ARCHIVES_DEP.SIGNFULLNAME IS
'签收人';

COMMENT ON COLUMN ARCHIVES_DEP.HANDLEFEEDBACK IS
'办理结果反馈';

COMMENT ON COLUMN ARCHIVES_DEP.ISMAIN IS
'主送、抄送
1=主送
0=抄送';

/*==============================================================*/
/* Table: ARCHIVES_DOC                                          */
/*==============================================================*/
CREATE TABLE ARCHIVES_DOC  (
   DOCID                NUMBER(18)                      NOT NULL,
   ARCHIVESID           NUMBER(18),
   FILEID               NUMBER(18),
   CREATOR              VARCHAR2(64),
   CREATORID            NUMBER(18),
   MENDERID             NUMBER(18),
   MENDER               VARCHAR2(64),
   DOCNAME              VARCHAR2(128)                   NOT NULL,
   DOCSTATUS            SMALLINT                        NOT NULL,
   CURVERSION           NUMBER(18)                      NOT NULL,
   DOCPATH              VARCHAR2(128)                   NOT NULL,
   UPDATETIME           DATE                            NOT NULL,
   CREATETIME           DATE                            NOT NULL,
   CONSTRAINT PK_ARCHIVES_DOC PRIMARY KEY (DOCID)
);

COMMENT ON COLUMN ARCHIVES_DOC.CREATOR IS
'拟稿人';

COMMENT ON COLUMN ARCHIVES_DOC.CREATORID IS
'拟稿人ID';

COMMENT ON COLUMN ARCHIVES_DOC.MENDER IS
'修改人';

COMMENT ON COLUMN ARCHIVES_DOC.DOCNAME IS
'文档名称';

COMMENT ON COLUMN ARCHIVES_DOC.DOCSTATUS IS
'文档状态
0=修改中
1=修改完成';

COMMENT ON COLUMN ARCHIVES_DOC.CURVERSION IS
'当前版本
取当前最新的版本';

COMMENT ON COLUMN ARCHIVES_DOC.DOCPATH IS
'文档路径';

COMMENT ON COLUMN ARCHIVES_DOC.UPDATETIME IS
'更新时间';

COMMENT ON COLUMN ARCHIVES_DOC.CREATETIME IS
'创建时间';

/*==============================================================*/
/* Table: ASSETS_TYPE                                           */
/*==============================================================*/
CREATE TABLE ASSETS_TYPE  (
   ASSETSTYPEID         NUMBER(18)                      NOT NULL,
   TYPENAME             VARCHAR2(128)                   NOT NULL,
   CONSTRAINT PK_ASSETS_TYPE PRIMARY KEY (ASSETSTYPEID)
);

COMMENT ON COLUMN ASSETS_TYPE.TYPENAME IS
'分类名称';

/*==============================================================*/
/* Table: BOARD_TYPE                                            */
/*==============================================================*/
CREATE TABLE BOARD_TYPE  (
   TYPEID               NUMBER(18)                      NOT NULL,
   TYPENAME             VARCHAR2(128)                   NOT NULL,
   TYPEDESC             VARCHAR2(4000)                  NOT NULL,
   CONSTRAINT PK_BOARD_TYPE PRIMARY KEY (TYPEID)
);

/*==============================================================*/
/* Table: BOARDROO                                              */
/*==============================================================*/
CREATE TABLE BOARDROO  (
   ROOMID               NUMBER(18)                      NOT NULL,
   ROOMNAME             VARCHAR2(128)                   NOT NULL,
   ROOMDESC             VARCHAR2(4000),
   CONTAINNUM           NUMBER(18)                     DEFAULT 0,
   CONSTRAINT PK_BOARDROO PRIMARY KEY (ROOMID)
);

COMMENT ON COLUMN BOARDROO.ROOMNAME IS
'会议室名称';

COMMENT ON COLUMN BOARDROO.ROOMDESC IS
'会议室描述';

COMMENT ON COLUMN BOARDROO.CONTAINNUM IS
'容纳人数';

/*==============================================================*/
/* Table: BOOK                                                  */
/*==============================================================*/
CREATE TABLE BOOK  (
   BOOKID               NUMBER(18)                      NOT NULL,
   TYPEID               NUMBER(18),
   BOOKNAME             VARCHAR2(128)                   NOT NULL,
   AUTHOR               VARCHAR2(128)                   NOT NULL,
   ISBN                 VARCHAR2(64)                    NOT NULL,
   PUBLISHER            VARCHAR2(128),
   PRICE                NUMBER                          NOT NULL,
   LOCATION             VARCHAR2(128)                   NOT NULL,
   DEPARTMENT           VARCHAR2(64)                    NOT NULL,
   AMOUNT               NUMBER(18)                      NOT NULL,
   LEFTAMOUNT           NUMBER(18)                      NOT NULL,
   CONSTRAINT PK_BOOK PRIMARY KEY (BOOKID)
);

COMMENT ON TABLE BOOK IS
'图书';

/*==============================================================*/
/* Table: BOOK_BOR_RET                                          */
/*==============================================================*/
CREATE TABLE BOOK_BOR_RET  (
   RECORDID             NUMBER(18)                      NOT NULL,
   BOOKSNID             NUMBER(18),
   BORROWTIME           DATE                            NOT NULL,
   RETURNTIME           DATE                            NOT NULL,
   LASTRETURNTIME       DATE,
   BORROWISBN           VARCHAR2(128)                   NOT NULL,
   BOOKNAME             VARCHAR2(128)                   NOT NULL,
   REGISTERNAME         VARCHAR2(32)                    NOT NULL,
   FULLNAME             VARCHAR2(32)                    NOT NULL,
   CONSTRAINT PK_BOOK_BOR_RET PRIMARY KEY (RECORDID)
);

COMMENT ON TABLE BOOK_BOR_RET IS
'图书借还表';

/*==============================================================*/
/* Table: BOOK_SN                                               */
/*==============================================================*/
CREATE TABLE BOOK_SN  (
   BOOKSNID             NUMBER(18)                      NOT NULL,
   BOOKID               NUMBER(18)                      NOT NULL,
   BOOKSN               VARCHAR2(128)                   NOT NULL,
   STATUS               SMALLINT                        NOT NULL,
   CONSTRAINT PK_BOOK_SN PRIMARY KEY (BOOKSNID)
);

COMMENT ON COLUMN BOOK_SN.STATUS IS
'借阅状态
0=未借出
1=借出
2=预订
3=注销';

/*==============================================================*/
/* Table: BOOK_TYPE                                             */
/*==============================================================*/
CREATE TABLE BOOK_TYPE  (
   TYPEID               NUMBER(18)                      NOT NULL,
   TYPENAME             VARCHAR2(128)                   NOT NULL,
   CONSTRAINT PK_BOOK_TYPE PRIMARY KEY (TYPEID)
);

COMMENT ON TABLE BOOK_TYPE IS
'图书类别';

/*==============================================================*/
/* Table: BORROW_FILE_LIST                                      */
/*==============================================================*/
CREATE TABLE BORROW_FILE_LIST  (
   LISTID               NUMBER(18)                      NOT NULL,
   RECORDID             NUMBER(18)                      NOT NULL,
   LISTTYPE             VARCHAR2(64),
   ARCHFONDID           NUMBER(18),
   AFNO                 VARCHAR2(64),
   AFNAME               VARCHAR2(128),
   ROLLID               NUMBER(18),
   ROLLNO               VARCHAR2(64),
   ROLLLNAME            VARCHAR2(128),
   ROLLFILEID           NUMBER(18),
   FILENO               VARCHAR2(64),
   FILENAME             VARCHAR2(128),
   CONSTRAINT PK_BORROW_FILE_LIST PRIMARY KEY (LISTID)
);

COMMENT ON TABLE BORROW_FILE_LIST IS
'文件借阅清单';

COMMENT ON COLUMN BORROW_FILE_LIST.LISTTYPE IS
'0:全宗
1:案卷
2:文件';

COMMENT ON COLUMN BORROW_FILE_LIST.AFNO IS
'全宗号';

COMMENT ON COLUMN BORROW_FILE_LIST.AFNAME IS
'全宗名';

COMMENT ON COLUMN BORROW_FILE_LIST.FILENO IS
'文件编号';

COMMENT ON COLUMN BORROW_FILE_LIST.FILENAME IS
'文件题名';

/*==============================================================*/
/* Table: BORROW_RECORD                                         */
/*==============================================================*/
CREATE TABLE BORROW_RECORD  (
   RECORDID             NUMBER(18)                      NOT NULL,
   BORROWDATE           DATE,
   BORROWTYPE           VARCHAR2(64),
   BORROWREASON         VARCHAR2(64),
   CHECKUSERID          NUMBER(18),
   CHECKUSERNAME        VARCHAR2(64),
   CHECKDATE            DATE,
   RETURNDATE           DATE,
   BORROWNUM            VARCHAR2(128),
   BORROWREMARK         VARCHAR2(128),
   RETURNSTATUS         SMALLINT,
   CHECKID              NUMBER(18),
   CHECKNAME            VARCHAR2(64),
   CHECKCONTENT         VARCHAR2(128),
   CONSTRAINT PK_BORROW_RECORD PRIMARY KEY (RECORDID)
);

/*==============================================================*/
/* Table: CAL_FILE                                              */
/*==============================================================*/
CREATE TABLE CAL_FILE  (
   FILEID               NUMBER(18)                      NOT NULL,
   PLANID               NUMBER(18)                      NOT NULL,
   CONSTRAINT PK_CAL_FILE PRIMARY KEY (FILEID, PLANID)
);

/*==============================================================*/
/* Table: CALENDAR_PLAN                                         */
/*==============================================================*/
CREATE TABLE CALENDAR_PLAN  (
   PLANID               NUMBER(18)                      NOT NULL,
   STARTTIME            DATE,
   ENDTIME              DATE,
   URGENT               SMALLINT                        NOT NULL,
   SUMMARY              VARCHAR2(200),
   CONTENT              VARCHAR2(1200)                  NOT NULL,
   STATUS               SMALLINT                        NOT NULL,
   USERID               NUMBER(18)                      NOT NULL,
   FULLNAME             VARCHAR2(32),
   ASSIGNERID           NUMBER(18)                      NOT NULL,
   ASSIGNERNAME         VARCHAR2(32),
   FEEDBACK             VARCHAR2(500),
   SHOWSTYLE            SMALLINT                        NOT NULL,
   TASKTYPE             SMALLINT                        NOT NULL,
   CONSTRAINT PK_CALENDAR_PLAN PRIMARY KEY (PLANID)
);

COMMENT ON TABLE CALENDAR_PLAN IS
'日程安排';

COMMENT ON COLUMN CALENDAR_PLAN.STARTTIME IS
'开始时间';

COMMENT ON COLUMN CALENDAR_PLAN.ENDTIME IS
'结束时间';

COMMENT ON COLUMN CALENDAR_PLAN.URGENT IS
'紧急程度
0=一般
1=重要
2=紧急';

COMMENT ON COLUMN CALENDAR_PLAN.CONTENT IS
'内容';

COMMENT ON COLUMN CALENDAR_PLAN.STATUS IS
'状态
0=未完成
1=完成';

COMMENT ON COLUMN CALENDAR_PLAN.USERID IS
'员工ID';

COMMENT ON COLUMN CALENDAR_PLAN.FULLNAME IS
'员工名';

COMMENT ON COLUMN CALENDAR_PLAN.ASSIGNERID IS
'分配人';

COMMENT ON COLUMN CALENDAR_PLAN.ASSIGNERNAME IS
'分配人名';

COMMENT ON COLUMN CALENDAR_PLAN.FEEDBACK IS
'反馈意见';

COMMENT ON COLUMN CALENDAR_PLAN.SHOWSTYLE IS
'显示方式
1=仅在任务中显示
2=在日程与任务中显示';

COMMENT ON COLUMN CALENDAR_PLAN.TASKTYPE IS
'任务类型
1=限期任务
2=非限期任务';

/*==============================================================*/
/* Table: CAR                                                   */
/*==============================================================*/
CREATE TABLE CAR  (
   CARID                NUMBER(18)                      NOT NULL,
   CARNO                VARCHAR2(128)                   NOT NULL,
   CARTYPE              VARCHAR2(64)                    NOT NULL,
   ENGINENO             VARCHAR2(128),
   BUYINSURETIME        DATE,
   AUDITTIME            DATE,
   NOTES                VARCHAR2(500),
   FACTORYMODEL         VARCHAR2(64)                    NOT NULL,
   DRIVER               VARCHAR2(32)                    NOT NULL,
   BUYDATE              DATE                            NOT NULL,
   STATUS               SMALLINT                        NOT NULL,
   CARTIMAGE            VARCHAR2(128),
   CONSTRAINT PK_CAR PRIMARY KEY (CARID)
);

COMMENT ON TABLE CAR IS
'车辆信息';

COMMENT ON COLUMN CAR.CARTYPE IS
'轿车
货车
商务车
';

COMMENT ON COLUMN CAR.BUYINSURETIME IS
'购买保险时间';

COMMENT ON COLUMN CAR.AUDITTIME IS
'年审时间';

COMMENT ON COLUMN CAR.BUYDATE IS
'购置日期';

COMMENT ON COLUMN CAR.STATUS IS
'当前状态
1=可用
2=维修中
0=报废';

/*==============================================================*/
/* Table: CAR_APPLY                                             */
/*==============================================================*/
CREATE TABLE CAR_APPLY  (
   APPLYID              NUMBER(18)                      NOT NULL,
   CARID                NUMBER(18)                      NOT NULL,
   DEPARTMENT           VARCHAR2(64)                    NOT NULL,
   USERFULLNAME         VARCHAR2(32)                    NOT NULL,
   APPLYDATE            DATE                            NOT NULL,
   REASON               VARCHAR2(512)                   NOT NULL,
   STARTTIME            DATE                            NOT NULL,
   ENDTIME              DATE,
   USERID               NUMBER(18)                      NOT NULL,
   PROPOSER             VARCHAR2(32)                    NOT NULL,
   MILEAGE              NUMBER(18,2),
   OILUSE               NUMBER(18,2),
   NOTES                VARCHAR2(128),
   APPROVALSTATUS       SMALLINT                        NOT NULL,
   CONSTRAINT PK_CAR_APPLY PRIMARY KEY (APPLYID)
);

COMMENT ON TABLE CAR_APPLY IS
'车辆申请';

/*==============================================================*/
/* Table: CART_REPAIR                                           */
/*==============================================================*/
CREATE TABLE CART_REPAIR  (
   REPAIRID             NUMBER(18)                      NOT NULL,
   CARID                NUMBER(18),
   REPAIRDATE           DATE                            NOT NULL,
   REASON               VARCHAR2(128)                   NOT NULL,
   EXECUTANT            VARCHAR2(128)                   NOT NULL,
   NOTES                VARCHAR2(128),
   REPAIRTYPE           VARCHAR2(128)                   NOT NULL,
   FEE                  NUMBER(18,2),
   CONSTRAINT PK_CART_REPAIR PRIMARY KEY (REPAIRID)
);

COMMENT ON COLUMN CART_REPAIR.REPAIRDATE IS
'维护日期';

COMMENT ON COLUMN CART_REPAIR.REASON IS
'维护原因';

COMMENT ON COLUMN CART_REPAIR.EXECUTANT IS
'经办人';

COMMENT ON COLUMN CART_REPAIR.NOTES IS
'备注';

COMMENT ON COLUMN CART_REPAIR.REPAIRTYPE IS
'维修类型
保养
维修';

COMMENT ON COLUMN CART_REPAIR.FEE IS
'费用';

/*==============================================================*/
/* Table: COMPANY                                               */
/*==============================================================*/
CREATE TABLE COMPANY  (
   COMPANYID            NUMBER(18)                      NOT NULL,
   COMPANYNO            VARCHAR2(128),
   COMPANYNAME          VARCHAR2(128)                   NOT NULL,
   COMPANYDESC          VARCHAR2(4000),
   LEGALPERSON          VARCHAR2(32),
   SETUP                DATE,
   PHONE                VARCHAR2(32),
   FAX                  VARCHAR2(32),
   SITE                 VARCHAR2(128),
   LOGO                 VARCHAR2(128),
   CONSTRAINT PK_COMPANY PRIMARY KEY (COMPANYID)
);

COMMENT ON TABLE COMPANY IS
'公司信息';

/*==============================================================*/
/* Table: CONF_ATTACH                                           */
/*==============================================================*/
CREATE TABLE CONF_ATTACH  (
   CONFID               NUMBER(18),
   FILEID               NUMBER(18)
);

/*==============================================================*/
/* Table: CONF_ATTEND                                           */
/*==============================================================*/
CREATE TABLE CONF_ATTEND  (
   ATTENDID             NUMBER(18)                      NOT NULL,
   CONFID               NUMBER(18),
   USERID               NUMBER(18),
   USERTYPE             SMALLINT                       DEFAULT 3,
   FULLNAME             VARCHAR2(50),
   CONSTRAINT PK_CONF_ATTEND PRIMARY KEY (ATTENDID)
);

COMMENT ON COLUMN CONF_ATTEND.CONFID IS
'会议ID';

COMMENT ON COLUMN CONF_ATTEND.USERTYPE IS
'1=主持人
2=记录人
3=参与会议人';

/*==============================================================*/
/* Table: CONF_PRIVILEGE                                        */
/*==============================================================*/
CREATE TABLE CONF_PRIVILEGE  (
   PRIVILEGEID          NUMBER(18)                      NOT NULL,
   CONFID               NUMBER(18),
   USERID               NUMBER(18)                      NOT NULL,
   FULLNAME             VARCHAR2(64)                    NOT NULL,
   RIGHTS               SMALLINT                        NOT NULL,
   CONSTRAINT PK_CONF_PRIVILEGE PRIMARY KEY (PRIVILEGEID)
);

COMMENT ON COLUMN CONF_PRIVILEGE.RIGHTS IS
'权限
1=查看
2=修改
3=建立纪要';

/*==============================================================*/
/* Table: CONF_SUM_ATTACH                                       */
/*==============================================================*/
CREATE TABLE CONF_SUM_ATTACH  (
   SUMID                NUMBER(18),
   FILEID               NUMBER(18)
);

/*==============================================================*/
/* Table: CONF_SUMMARY                                          */
/*==============================================================*/
CREATE TABLE CONF_SUMMARY  (
   SUMID                NUMBER(18)                      NOT NULL,
   CONFID               NUMBER(18),
   CREATETIME           DATE                            NOT NULL,
   CREATOR              VARCHAR2(32)                    NOT NULL,
   SUMCONTENT           CLOB                            NOT NULL,
   STATUS               SMALLINT,
   CONSTRAINT PK_CONF_SUMMARY PRIMARY KEY (SUMID)
);

COMMENT ON COLUMN CONF_SUMMARY.STATUS IS
'状态
0=待发送
1=发送';

/*==============================================================*/
/* Table: CONFERENCE                                            */
/*==============================================================*/
CREATE TABLE CONFERENCE  (
   CONFID               NUMBER(18)                      NOT NULL,
   CONFTOPIC            VARCHAR2(128)                   NOT NULL,
   CONFPROPERTY         VARCHAR2(64),
   IMPORTLEVEL          SMALLINT                        NOT NULL,
   FEEBUDGET            DECIMAL(18,2),
   COMPERENAME          VARCHAR2(256),
   COMPERE              VARCHAR2(128),
   RECORDER             VARCHAR2(128),
   RECORDERNAME         VARCHAR2(256),
   ATTENDUSERS          VARCHAR2(500),
   ATTENDUSERSNAME      VARCHAR2(4000),
   STATUS               SMALLINT                        NOT NULL,
   ISEMAIL              SMALLINT,
   ISMOBILE             SMALLINT,
   STARTTIME            DATE                            NOT NULL,
   ENDTIME              DATE                            NOT NULL,
   ROOMID               NUMBER(18),
   TYPEID               NUMBER(18),
   ROOMNAME             VARCHAR2(64),
   ROOMLOCATION         VARCHAR2(128),
   CONFCONTENT          CLOB,
   CREATETIME           DATE,
   SENDTIME             DATE,
   CHECKREASON          VARCHAR2(512),
   CHECKUSERID          NUMBER(18),
   CHECKNAME            VARCHAR2(64),
   CONSTRAINT PK_CONFERENCE PRIMARY KEY (CONFID)
);

COMMENT ON COLUMN CONFERENCE.CONFTOPIC IS
'会议议题';

COMMENT ON COLUMN CONFERENCE.CONFPROPERTY IS
'会议性质';

COMMENT ON COLUMN CONFERENCE.IMPORTLEVEL IS
'重要级别
1=普通
2=重要
3=非常重要';

COMMENT ON COLUMN CONFERENCE.FEEBUDGET IS
'费用预算';

COMMENT ON COLUMN CONFERENCE.COMPERE IS
'主持人
(允许多人，用,分开)';

COMMENT ON COLUMN CONFERENCE.RECORDER IS
'记录人
(允许多人，用,分开)';

COMMENT ON COLUMN CONFERENCE.ATTENDUSERS IS
'参加人员列表';

COMMENT ON COLUMN CONFERENCE.STATUS IS
'状态
0=暂存
1=发布';

COMMENT ON COLUMN CONFERENCE.ISEMAIL IS
'邮件通知';

COMMENT ON COLUMN CONFERENCE.ISMOBILE IS
'短信通知';

COMMENT ON COLUMN CONFERENCE.STARTTIME IS
'开始时间';

COMMENT ON COLUMN CONFERENCE.ENDTIME IS
'结束时间';

COMMENT ON COLUMN CONFERENCE.ROOMID IS
'会议室ID';

COMMENT ON COLUMN CONFERENCE.ROOMNAME IS
'会议室';

COMMENT ON COLUMN CONFERENCE.ROOMLOCATION IS
'会议地点';

COMMENT ON COLUMN CONFERENCE.CONFCONTENT IS
'会议内容';

/*==============================================================*/
/* Table: CONTRACT                                              */
/*==============================================================*/
CREATE TABLE CONTRACT  (
   CONTRACTID           NUMBER(18)                      NOT NULL,
   CONTRACTNO           VARCHAR2(64)                    NOT NULL,
   SUBJECT              VARCHAR2(128)                   NOT NULL,
   CONTRACTAMOUNT       NUMBER                          NOT NULL,
   MAINITEM             VARCHAR2(4000),
   SALESAFTERITEM       VARCHAR2(4000),
   VALIDDATE            DATE                            NOT NULL,
   EXPIREDATE           DATE                            NOT NULL,
   SERVICEDEP           VARCHAR2(64),
   SERVICEMAN           VARCHAR2(64),
   SIGNUPUSER           VARCHAR2(64)                    NOT NULL,
   SIGNUPTIME           DATE                            NOT NULL,
   CREATOR              VARCHAR2(32)                    NOT NULL,
   CREATETIME           DATE                            NOT NULL,
   PROJECTID            NUMBER(18),
   CONSIGNADDRESS       VARCHAR2(128),
   CONSIGNEE            VARCHAR2(32),
   CONSTRAINT PK_CONTRACT PRIMARY KEY (CONTRACTID)
);

COMMENT ON COLUMN CONTRACT.CONTRACTNO IS
'合同编号';

COMMENT ON COLUMN CONTRACT.SUBJECT IS
'合同标题';

COMMENT ON COLUMN CONTRACT.CONTRACTAMOUNT IS
'合同金额';

COMMENT ON COLUMN CONTRACT.MAINITEM IS
'主要条款';

COMMENT ON COLUMN CONTRACT.SALESAFTERITEM IS
'售后条款';

COMMENT ON COLUMN CONTRACT.VALIDDATE IS
'生效日期';

COMMENT ON COLUMN CONTRACT.EXPIREDATE IS
'有效期';

COMMENT ON COLUMN CONTRACT.SERVICEDEP IS
'维修部门';

COMMENT ON COLUMN CONTRACT.SERVICEMAN IS
'维修负责人';

COMMENT ON COLUMN CONTRACT.SIGNUPUSER IS
'签约人';

COMMENT ON COLUMN CONTRACT.SIGNUPTIME IS
'签约时间';

COMMENT ON COLUMN CONTRACT.CREATOR IS
'录入人';

COMMENT ON COLUMN CONTRACT.CREATETIME IS
'录入时间';

COMMENT ON COLUMN CONTRACT.PROJECTID IS
'所属项目';

COMMENT ON COLUMN CONTRACT.CONSIGNADDRESS IS
'收货地址';

COMMENT ON COLUMN CONTRACT.CONSIGNEE IS
'收货人';

/*==============================================================*/
/* Table: CONTRACT_CONFIG                                       */
/*==============================================================*/
CREATE TABLE CONTRACT_CONFIG  (
   CONFIGID             NUMBER(18)                      NOT NULL,
   ITEMNAME             VARCHAR2(128)                   NOT NULL,
   CONTRACTID           NUMBER(18),
   ITEMSPEC             VARCHAR2(128)                   NOT NULL,
   AMOUNT               NUMBER(18,2)                    NOT NULL,
   NOTES                VARCHAR2(200),
   CONSTRAINT PK_CONTRACT_CONFIG PRIMARY KEY (CONFIGID)
);

COMMENT ON TABLE CONTRACT_CONFIG IS
'合同配置单';

COMMENT ON COLUMN CONTRACT_CONFIG.ITEMNAME IS
'设备名称';

COMMENT ON COLUMN CONTRACT_CONFIG.ITEMSPEC IS
'设置规格';

COMMENT ON COLUMN CONTRACT_CONFIG.AMOUNT IS
'数量';

COMMENT ON COLUMN CONTRACT_CONFIG.NOTES IS
'备注';

/*==============================================================*/
/* Table: CONTRACT_FILE                                         */
/*==============================================================*/
CREATE TABLE CONTRACT_FILE  (
   FILEID               NUMBER(18)                      NOT NULL,
   CONTRACTID           NUMBER(18)                      NOT NULL,
   CONSTRAINT PK_CONTRACT_FILE PRIMARY KEY (FILEID, CONTRACTID)
);

COMMENT ON TABLE CONTRACT_FILE IS
'合同附件';

/*==============================================================*/
/* Table: CUS_CONNECTION                                        */
/*==============================================================*/
CREATE TABLE CUS_CONNECTION  (
   CONNID               NUMBER(18)                      NOT NULL,
   CUSTOMERID           NUMBER(18)                      NOT NULL,
   CONTACTOR            VARCHAR2(32)                    NOT NULL,
   STARTDATE            DATE                            NOT NULL,
   ENDDATE              DATE                            NOT NULL,
   CONTENT              VARCHAR2(512)                   NOT NULL,
   NOTES                VARCHAR2(1000),
   CREATOR              VARCHAR2(32),
   CONSTRAINT PK_CUS_CONNECTION PRIMARY KEY (CONNID)
);

COMMENT ON TABLE CUS_CONNECTION IS
'business connection ';

/*==============================================================*/
/* Table: CUS_LINKMAN                                           */
/*==============================================================*/
CREATE TABLE CUS_LINKMAN  (
   LINKMANID            NUMBER(18)                      NOT NULL,
   CUSTOMERID           NUMBER(18)                      NOT NULL,
   FULLNAME             VARCHAR2(32)                    NOT NULL,
   SEX                  SMALLINT                        NOT NULL,
   POSITION             VARCHAR2(32),
   PHONE                VARCHAR2(32),
   MOBILE               VARCHAR2(32)                    NOT NULL,
   FAX                  VARCHAR2(32),
   EMAIL                VARCHAR2(100),
   MSN                  VARCHAR2(100),
   QQ                   VARCHAR2(64),
   BIRTHDAY             DATE,
   HOMEADDRESS          VARCHAR2(128),
   HOMEZIP              VARCHAR2(32),
   HOMEPHONE            VARCHAR2(32),
   HOBBY                VARCHAR2(100),
   ISPRIMARY            SMALLINT                        NOT NULL,
   NOTES                VARCHAR2(500),
   CONSTRAINT PK_CUS_LINKMAN PRIMARY KEY (LINKMANID)
);

COMMENT ON TABLE CUS_LINKMAN IS
'客户联系人';

COMMENT ON COLUMN CUS_LINKMAN.CUSTOMERID IS
'所属客户';

COMMENT ON COLUMN CUS_LINKMAN.FULLNAME IS
'姓名';

COMMENT ON COLUMN CUS_LINKMAN.SEX IS
'性别';

COMMENT ON COLUMN CUS_LINKMAN.POSITION IS
'职位';

COMMENT ON COLUMN CUS_LINKMAN.PHONE IS
'电话';

COMMENT ON COLUMN CUS_LINKMAN.MOBILE IS
'手机';

COMMENT ON COLUMN CUS_LINKMAN.EMAIL IS
'Email';

COMMENT ON COLUMN CUS_LINKMAN.MSN IS
'MSN';

COMMENT ON COLUMN CUS_LINKMAN.QQ IS
'QQ';

COMMENT ON COLUMN CUS_LINKMAN.BIRTHDAY IS
'生日';

COMMENT ON COLUMN CUS_LINKMAN.HOMEADDRESS IS
'家庭住址';

COMMENT ON COLUMN CUS_LINKMAN.HOMEZIP IS
'邮编';

COMMENT ON COLUMN CUS_LINKMAN.HOMEPHONE IS
'家庭电话';

COMMENT ON COLUMN CUS_LINKMAN.HOBBY IS
'爱好';

COMMENT ON COLUMN CUS_LINKMAN.ISPRIMARY IS
'是否为主要联系人';

COMMENT ON COLUMN CUS_LINKMAN.NOTES IS
'备注';

/*==============================================================*/
/* Table: CUSTOMER                                              */
/*==============================================================*/
CREATE TABLE CUSTOMER  (
   CUSTOMERID           NUMBER(18)                      NOT NULL,
   CUSTOMERNO           VARCHAR2(64)                    NOT NULL,
   INDUSTRYTYPE         VARCHAR2(64)                    NOT NULL,
   CUSTOMERSOURCE       VARCHAR2(64),
   CUSTOMERTYPE         SMALLINT                        NOT NULL,
   COMPANYSCALE         NUMBER(18),
   CUSTOMERNAME         VARCHAR2(128)                   NOT NULL,
   CUSTOMERMANAGER      VARCHAR2(32)                    NOT NULL,
   PHONE                VARCHAR2(32)                    NOT NULL,
   FAX                  VARCHAR2(32),
   SITE                 VARCHAR2(128),
   EMAIL                VARCHAR2(128),
   STATE                VARCHAR2(32),
   CITY                 VARCHAR2(32),
   ZIP                  VARCHAR2(32),
   ADDRESS              VARCHAR2(100),
   REGISTERFUN          NUMBER,
   TURNOVER             NUMBER,
   CURRENCYUNIT         VARCHAR2(32),
   OTHERDESC            VARCHAR2(800),
   PRINCIPAL            VARCHAR2(32),
   OPENBANK             VARCHAR2(64),
   ACCOUNTSNO           VARCHAR2(64),
   TAXNO                VARCHAR2(64),
   NOTES                VARCHAR2(500),
   RIGHTS               SMALLINT                        NOT NULL,
   CONSTRAINT PK_CUSTOMER PRIMARY KEY (CUSTOMERID)
);

COMMENT ON TABLE CUSTOMER IS
'客户信息';

COMMENT ON COLUMN CUSTOMER.CUSTOMERNO IS
'客户号
自动生成';

COMMENT ON COLUMN CUSTOMER.INDUSTRYTYPE IS
'所属行业
有缺省的选择，也可以输入';

COMMENT ON COLUMN CUSTOMER.CUSTOMERSOURCE IS
'客户来源
可编辑，可添加

电话访问
网络
客户或朋友介绍';

COMMENT ON COLUMN CUSTOMER.CUSTOMERTYPE IS
'1=正式客户
2=重要客户
3＝潜在客户
4＝无效客户';

COMMENT ON COLUMN CUSTOMER.COMPANYSCALE IS
'1=1-20人
2=20-50人
3=50-100人
4=100-200人
5=200-500人
6=500-1000 人
7=1000人以上
';

COMMENT ON COLUMN CUSTOMER.CUSTOMERNAME IS
'客户名称
一般为公司名称';

COMMENT ON COLUMN CUSTOMER.CUSTOMERMANAGER IS
'负责该客户的经理';

COMMENT ON COLUMN CUSTOMER.PHONE IS
'电话';

COMMENT ON COLUMN CUSTOMER.CURRENCYUNIT IS
'注册资金及年营业额的货币单位
可选可编辑
人民币（默认）
美元
';

COMMENT ON COLUMN CUSTOMER.RIGHTS IS
'1=客户经理及上级经理有权查看
2=公开
3=共享人员有权查看';

/*==============================================================*/
/* Table: DEMENSION                                             */
/*==============================================================*/
CREATE TABLE DEMENSION  (
   DEM_ID               NUMBER(18)                      NOT NULL,
   DEM_NAME             VARCHAR2(128)                   NOT NULL,
   DEM_DESC             VARCHAR2(1024),
   DEM_TYPE             SMALLINT                        NOT NULL,
   CONSTRAINT PK_DEMENSION PRIMARY KEY (DEM_ID)
);

COMMENT ON COLUMN DEMENSION.DEM_NAME IS
'维度名称';

COMMENT ON COLUMN DEMENSION.DEM_DESC IS
'维度描述';

COMMENT ON COLUMN DEMENSION.DEM_TYPE IS
'类型
1=行政(为公司的整个组织结构，用户所属的部门即隶属于该维度)
2=其他
';

/*==============================================================*/
/* Table: SUBORDINATE    用户组织关系改造相关修改 2012-10-22  				*/
/*==============================================================*/
CREATE TABLE SUBORDINATE 
(
	SUBORDINATEID NUMBER(18) NOT NULL,
	DEM_ID NUMBER(18) NOT NULL,
	USERID NUMBER(18) NOT NULL,
	JOBUSERID NUMBER(18) NOT NULL,
	RELATIVE NUMBER(2) NOT NULL,
	CONSTRAINT PK_SUBORDINATE PRIMARY KEY (SUBORDINATEID)
);

COMMENT ON COLUMN SUBORDINATE.RELATIVE IS '上下级标识 1=上级 ，2同级，0下级';
COMMENT ON COLUMN SUBORDINATE.JOBUSERID IS '相对用户标示';
COMMENT ON COLUMN SUBORDINATE.USERID IS '主用户标示';
COMMENT ON COLUMN SUBORDINATE.DEM_ID IS '关系所属维度';

/*==============================================================*/
/* Table: DEP_POS                                               */
/*==============================================================*/
CREATE TABLE DEP_POS  (
   ORG_ID               NUMBER(18)                      NOT NULL,
   POS_ID               NUMBER(18)                      NOT NULL,
   CONSTRAINT PK_DEP_POS PRIMARY KEY (ORG_ID, POS_ID)
);

/*==============================================================*/
/* Table: DEPRE_RECORD                                          */
/*==============================================================*/
CREATE TABLE DEPRE_RECORD  (
   RECORDID             NUMBER(18)                      NOT NULL,
   ASSETSID             NUMBER(18)                      NOT NULL,
   WORKCAPACITY         NUMBER(18,2),
   WORKGROSSUNIT        VARCHAR2(128),
   DEPREAMOUNT          NUMBER(18,4)                    NOT NULL,
   CALTIME              DATE                            NOT NULL,
   CONSTRAINT PK_DEPRE_RECORD PRIMARY KEY (RECORDID)
);

/*==============================================================*/
/* Table: DEPRE_TYPE                                            */
/*==============================================================*/
CREATE TABLE DEPRE_TYPE  (
   DEPRETYPEID          NUMBER(18)                      NOT NULL,
   TYPENAME             VARCHAR2(64)                    NOT NULL,
   DEPREPERIOD          NUMBER(18)                      NOT NULL,
   TYPEDESC             VARCHAR2(500),
   CALMETHOD            SMALLINT                        NOT NULL,
   CONSTRAINT PK_DEPRE_TYPE PRIMARY KEY (DEPRETYPEID)
);

COMMENT ON TABLE DEPRE_TYPE IS
'depreciation type';

COMMENT ON COLUMN DEPRE_TYPE.DEPREPERIOD IS
'单位为月';

COMMENT ON COLUMN DEPRE_TYPE.CALMETHOD IS
'1=平均年限法
2=工作量法
加速折旧法
3=双倍余额递减法
4=年数总和法';

/*==============================================================*/
/* Table: DIARY                                                 */
/*==============================================================*/
CREATE TABLE DIARY  (
   DIARYID              NUMBER(18)                      NOT NULL,
   USERID               NUMBER(18),
   DAYTIME              DATE                            NOT NULL,
   CONTENT              CLOB                            NOT NULL,
   DIARYTYPE            SMALLINT                        NOT NULL,
   CONSTRAINT PK_DIARY PRIMARY KEY (DIARYID)
);

COMMENT ON COLUMN DIARY.USERID IS
'主键';

COMMENT ON COLUMN DIARY.DIARYTYPE IS
'1=工作日志
0=个人日志';

/*==============================================================*/
/* Table: DICTIONARY                                            */
/*==============================================================*/
CREATE TABLE DICTIONARY  (
   DICID                NUMBER(18)                      NOT NULL,
   PROTYPEID            NUMBER(18)  					NOT NULL,
   ITEMNAME             VARCHAR2(64)                    NOT NULL,
   ITEMVALUE            VARCHAR2(128)                   NOT NULL,
   DESCP                VARCHAR2(256),
   SN                   NUMBER(18),
   CONSTRAINT PK_DICTIONARY PRIMARY KEY (DICID)
);

COMMENT ON TABLE DICTIONARY IS
'数据字典';

/*==============================================================*/
/* Table: DOC_FILE                                              */
/*==============================================================*/
CREATE TABLE DOC_FILE  (
   FILEID               NUMBER(18)                      NOT NULL,
   DOCID                NUMBER(18)                      NOT NULL,
   CONSTRAINT PK_DOC_FILE PRIMARY KEY (FILEID, DOCID)
);

/*==============================================================*/
/* Table: DOC_FOLDER                                            */
/*==============================================================*/
CREATE TABLE DOC_FOLDER  (
   FOLDERID             NUMBER(18)                      NOT NULL,
   USERID               NUMBER(18),
   FOLDERNAME           VARCHAR2(128)                   NOT NULL,
   PARENTID             NUMBER(18),
   PATH                 VARCHAR2(128),
   ISSHARED             SMALLINT                        NOT NULL,
   DESCP                VARCHAR2(256),
   CONSTRAINT PK_DOC_FOLDER PRIMARY KEY (FOLDERID)
);

COMMENT ON COLUMN DOC_FOLDER.USERID IS
'主键';

COMMENT ON COLUMN DOC_FOLDER.FOLDERNAME IS
'目录名称';

COMMENT ON COLUMN DOC_FOLDER.PARENTID IS
'父目录';

COMMENT ON COLUMN DOC_FOLDER.PATH IS
'路径
为当前路径的＋上级路径
如当前ID为3，上级目录的路径为1.2，
则当前的路径为1.2.3.';

/*==============================================================*/
/* Table: DOC_HISTORY                                           */
/*==============================================================*/
CREATE TABLE DOC_HISTORY  (
   HISTORYID            NUMBER(18)                      NOT NULL,
   DOCID                NUMBER(18)                      NOT NULL,
   FILEID               NUMBER(18),
   DOCNAME              VARCHAR2(128)                   NOT NULL,
   PATH                 VARCHAR2(128)                   NOT NULL,
   VERSION              NUMBER(18)                      NOT NULL,
   UPDATETIME           DATE                            NOT NULL,
   MENDER               VARCHAR2(64)                    NOT NULL,
   CONSTRAINT PK_DOC_HISTORY PRIMARY KEY (HISTORYID)
);

COMMENT ON COLUMN DOC_HISTORY.DOCNAME IS
'文档名称';

COMMENT ON COLUMN DOC_HISTORY.PATH IS
'路径';

COMMENT ON COLUMN DOC_HISTORY.VERSION IS
'版本';

COMMENT ON COLUMN DOC_HISTORY.UPDATETIME IS
'更新时间';

COMMENT ON COLUMN DOC_HISTORY.MENDER IS
'修改人';

/*==============================================================*/
/* Table: DOC_PRIVILEGE                                         */
/*==============================================================*/
CREATE TABLE DOC_PRIVILEGE  (
   PRIVILEGEID          NUMBER(18)                      NOT NULL,
   FOLDERID             NUMBER(18),
   DOCID                NUMBER(18),
   RIGHTS               NUMBER(18)                      NOT NULL,
   UDRID                NUMBER(18),
   UDRNAME              VARCHAR2(128),
   FLAG                 SMALLINT                        NOT NULL,
   FDFLAG               SMALLINT                        NOT NULL,
   CONSTRAINT PK_DOC_PRIVILEGE PRIMARY KEY (PRIVILEGEID)
);

COMMENT ON TABLE DOC_PRIVILEGE IS
'文档或目录的权限，只要是针对公共目录下的文档，个人的文档则不需要在这里进行权限的设置

某个目录或文档若没有指定某部门或某个人，即在本表中没有记录，
则表示可以进行所有的操作';

COMMENT ON COLUMN DOC_PRIVILEGE.RIGHTS IS
'权限
文档或目录的读写修改权限
1=读
2=修改
4=删除

权限值可以为上面的值之和
如：3则代表进行读，修改的操作


';

COMMENT ON COLUMN DOC_PRIVILEGE.FLAG IS
'1=user
2=deparment
3=role';

COMMENT ON COLUMN DOC_PRIVILEGE.FDFLAG IS
'缺省为文件夹权限
1=文档权限
0=文件夹权限';

/*==============================================================*/
/* Table: DOCUMENT                                              */
/*==============================================================*/
CREATE TABLE DOCUMENT  (
   DOCID                NUMBER(18)                      NOT NULL,
   DOCNAME              VARCHAR2(100)                   NOT NULL,
   CONTENT              CLOB,
   CREATETIME           DATE                            NOT NULL,
   UPDATETIME           DATE,
   FOLDERID             NUMBER(18),
   USERID               NUMBER(18),
   FULLNAME             VARCHAR2(32)                    NOT NULL,
   HAVEATTACH           SMALLINT,
   SHAREDUSERIDS        VARCHAR2(1000),
   SHAREDUSERNAMES      VARCHAR2(1000),
   SHAREDDEPIDS         VARCHAR2(1000),
   SHAREDDEPNAMES       VARCHAR2(1000),
   SHAREDROLEIDS        VARCHAR2(1000),
   SHAREDROLENAMES      VARCHAR2(1000),
   ISSHARED             SMALLINT                        NOT NULL,
   AUTHOR               VARCHAR2(64),
   KEYWORDS             VARCHAR2(256),
   DOCTYPE              VARCHAR2(64),
   SWFPATH              VARCHAR2(256),
   CONSTRAINT PK_DOCUMENT PRIMARY KEY (DOCID)
);

COMMENT ON TABLE DOCUMENT IS
'文档';

COMMENT ON COLUMN DOCUMENT.CONTENT IS
'内容';

COMMENT ON COLUMN DOCUMENT.CREATETIME IS
'创建时间';

COMMENT ON COLUMN DOCUMENT.USERID IS
'主键';

COMMENT ON COLUMN DOCUMENT.SHAREDUSERIDS IS
'共享员工ID';

COMMENT ON COLUMN DOCUMENT.SHAREDDEPIDS IS
'共享部门ID';

COMMENT ON COLUMN DOCUMENT.SHAREDROLEIDS IS
'共享角色ID';

COMMENT ON COLUMN DOCUMENT.ISSHARED IS
'是否共享';

/*==============================================================*/
/* Table: DOWNLOAD_LOG                                          */
/*==============================================================*/
CREATE TABLE DOWNLOAD_LOG  (
   LOGID                NUMBER(18)                      NOT NULL,
   USERNAME             VARCHAR2(64)                    NOT NULL,
   USERID               NUMBER(18)                      NOT NULL,
   FILEID               NUMBER(18)                      NOT NULL,
   DOWNLOADTIME         DATE                            NOT NULL,
   NOTES                VARCHAR2(1024),
   CONSTRAINT PK_DOWNLOAD_LOG PRIMARY KEY (LOGID)
);

/*==============================================================*/
/* Table: DUTY                                                  */
/*==============================================================*/
CREATE TABLE DUTY  (
   DUTYID               NUMBER(18)                      NOT NULL,
   USERID               NUMBER(18)                      NOT NULL,
   FULLNAME             VARCHAR2(32)                    NOT NULL,
   SYSTEMID             NUMBER(18)                      NOT NULL,
   STARTTIME            DATE                            NOT NULL,
   ENDTIME              DATE,
   CONSTRAINT PK_DUTY PRIMARY KEY (DUTYID)
);

COMMENT ON TABLE DUTY IS
'排班';

COMMENT ON COLUMN DUTY.USERID IS
'员工ID';

COMMENT ON COLUMN DUTY.FULLNAME IS
'员工姓名';

COMMENT ON COLUMN DUTY.SYSTEMID IS
'班制ID';

COMMENT ON COLUMN DUTY.STARTTIME IS
'开始时间';

COMMENT ON COLUMN DUTY.ENDTIME IS
'结束时间';

/*==============================================================*/
/* Table: DUTY_REGISTER                                         */
/*==============================================================*/
CREATE TABLE DUTY_REGISTER  (
   REGISTERID           NUMBER(18)                      NOT NULL,
   REGISTERDATE         DATE                            NOT NULL,
   USERID               NUMBER(18)                      NOT NULL,
   FULLNAME             VARCHAR2(32)                    NOT NULL,
   REGFLAG              SMALLINT                        NOT NULL,
   REGMINS              NUMBER(18)                      NOT NULL,
   REASON               VARCHAR2(128),
   DAYOFWEEK            NUMBER(18)                      NOT NULL,
   INOFFFLAG            SMALLINT                        NOT NULL,
   SECTIONID            NUMBER(18)                      NOT NULL,
   CONSTRAINT PK_DUTY_REGISTER PRIMARY KEY (REGISTERID)
);

COMMENT ON COLUMN DUTY_REGISTER.REGISTERDATE IS
'登记时间';

COMMENT ON COLUMN DUTY_REGISTER.USERID IS
'登记人';

COMMENT ON COLUMN DUTY_REGISTER.REGFLAG IS
'登记标识
1=正常登记（上班，下班）
2＝迟到
3=早退
4＝休息
5＝旷工
6=放假

';

COMMENT ON COLUMN DUTY_REGISTER.REGMINS IS
'迟到或早退分钟
正常上班时为0';

COMMENT ON COLUMN DUTY_REGISTER.REASON IS
'迟到原因';

COMMENT ON COLUMN DUTY_REGISTER.DAYOFWEEK IS
'周几
1=星期日
..
7=日期六';

COMMENT ON COLUMN DUTY_REGISTER.INOFFFLAG IS
'上下班标识
1=签到
2=签退';

/*==============================================================*/
/* Table: DUTY_SECTION                                          */
/*==============================================================*/
CREATE TABLE DUTY_SECTION  (
   SECTIONID            NUMBER(18)                      NOT NULL,
   SECTIONNAME          VARCHAR2(64)                    NOT NULL,
   STARTSIGNIN          DATE                            NOT NULL,
   DUTYSTARTTIME        DATE                            NOT NULL,
   ENDSIGNIN            DATE                            NOT NULL,
   EARLYOFFTIME         DATE                            NOT NULL,
   DUTYENDTIME          DATE                            NOT NULL,
   SIGNOUTTIME          DATE                            NOT NULL,
   CONSTRAINT PK_DUTY_SECTION PRIMARY KEY (SECTIONID)
);

COMMENT ON TABLE DUTY_SECTION IS
'班次
也称为班段，一天可以分为两个或三个班段
';

COMMENT ON COLUMN DUTY_SECTION.STARTSIGNIN IS
'开始签到';

COMMENT ON COLUMN DUTY_SECTION.DUTYSTARTTIME IS
'上班时间';

COMMENT ON COLUMN DUTY_SECTION.ENDSIGNIN IS
'签到结束时间';

COMMENT ON COLUMN DUTY_SECTION.EARLYOFFTIME IS
'早退计时';

COMMENT ON COLUMN DUTY_SECTION.DUTYENDTIME IS
'下班时间';

COMMENT ON COLUMN DUTY_SECTION.SIGNOUTTIME IS
'签退结束';

/*==============================================================*/
/* Table: DUTY_SYSTEM                                           */
/*==============================================================*/
CREATE TABLE DUTY_SYSTEM  (
   SYSTEMID             NUMBER(18)                      NOT NULL,
   SYSTEMNAME           VARCHAR2(128)                   NOT NULL,
   SYSTEMSETTING        VARCHAR2(128)                   NOT NULL,
   SYSTEMDESC           VARCHAR2(256)                   NOT NULL,
   ISDEFAULT            SMALLINT                        NOT NULL,
   CONSTRAINT PK_DUTY_SYSTEM PRIMARY KEY (SYSTEMID)
);

COMMENT ON TABLE DUTY_SYSTEM IS
'轮班制
为公司设置上班的时间规定
如规定周一至周五上班，周六周日休息';

COMMENT ON COLUMN DUTY_SYSTEM.SYSTEMNAME IS
'班制名称';

COMMENT ON COLUMN DUTY_SYSTEM.SYSTEMSETTING IS
'班次
班次的数据结构为：
如一员工周一至周五上班，上午9：00- 12：00 ，下午 13：30 -18:00

基数据结构为：
1|2,1|2,1|2,1|2,1|2,-,-
-代表正常休息日
1|2代表为一天上两个班次，1代表为班次表1的记录
';

COMMENT ON COLUMN DUTY_SYSTEM.SYSTEMDESC IS
'班次描述';

COMMENT ON COLUMN DUTY_SYSTEM.ISDEFAULT IS
'是否缺省
1＝缺省
0＝非缺省';

/*==============================================================*/
/* Table: EMP_PROFILE                                           */
/*==============================================================*/
CREATE TABLE EMP_PROFILE  (
   PROFILEID            NUMBER(18)                      NOT NULL,
   PROFILENO            VARCHAR2(100)                   NOT NULL,
   USERID               NUMBER(18)                      NOT NULL,
   FULLNAME             VARCHAR2(64)                    NOT NULL,
   ADDRESS              VARCHAR2(128),
   BIRTHDAY             DATE,
   HOMEZIP              VARCHAR2(32),
   SEX                  VARCHAR2(32),
   MARRIAGE             VARCHAR2(32),
   DESIGNATION          VARCHAR2(64),
   POSITION             VARCHAR2(128)                   NOT NULL,
   PHONE                VARCHAR2(64),
   MOBILE               VARCHAR2(64),
   OPENBANK             VARCHAR2(100),
   BANKNO               VARCHAR2(100),
   QQ                   VARCHAR2(64),
   EMAIL                VARCHAR2(128),
   HOBBY                VARCHAR2(300),
   RELIGION             VARCHAR2(100),
   PARTY                VARCHAR2(100),
   NATIONALITY          VARCHAR2(100),
   RACE                 VARCHAR2(100),
   BIRTHPLACE           VARCHAR2(128),
   EDUDEGREE            VARCHAR2(100),
   EDUMAJOR             VARCHAR2(100),
   EDUCOLLEGE           VARCHAR2(128),
   STARTWORKDATE        DATE,
   EDUCASE              VARCHAR2(2048),
   AWARDPUNISHCASE      VARCHAR2(2048),
   TRAININGCASE         VARCHAR2(2048),
   WORKCASE             VARCHAR2(2048),
   IDCARD               VARCHAR2(64),
   PHOTO                VARCHAR2(128),
   STANDARDMINO         VARCHAR2(100),
   STANDARDMONEY        NUMBER(18,2),
   STANDARDNAME         VARCHAR2(128),
   STANDARDID           NUMBER(18),
   JOBID                NUMBER(18),
   CREATOR              VARCHAR2(64),
   CREATETIME           DATE,
   CHECKNAME            VARCHAR2(128),
   CHECKTIME            DATE,
   OPPROVALOPINION      VARCHAR2(1024),
   APPROVALSTATUS       SMALLINT                       DEFAULT 0,
   MEMO                 VARCHAR2(1024),
   DEPNAME              VARCHAR2(100),
   DEPID                NUMBER(18),
   DELFLAG              SMALLINT                       DEFAULT 0 NOT NULL,
   CONSTRAINT PK_EMP_PROFILE PRIMARY KEY (PROFILEID)
);

COMMENT ON TABLE EMP_PROFILE IS
'员工档案';

COMMENT ON COLUMN EMP_PROFILE.PROFILENO IS
'档案编号';

COMMENT ON COLUMN EMP_PROFILE.FULLNAME IS
'员工姓名';

COMMENT ON COLUMN EMP_PROFILE.ADDRESS IS
'家庭地址';

COMMENT ON COLUMN EMP_PROFILE.BIRTHDAY IS
'出生日期';

COMMENT ON COLUMN EMP_PROFILE.HOMEZIP IS
'家庭邮编';

COMMENT ON COLUMN EMP_PROFILE.MARRIAGE IS
'婚姻状况
已婚
未婚';

COMMENT ON COLUMN EMP_PROFILE.PHONE IS
'电话号码';

COMMENT ON COLUMN EMP_PROFILE.MOBILE IS
'手机号码';

COMMENT ON COLUMN EMP_PROFILE.OPENBANK IS
'开户银行';

COMMENT ON COLUMN EMP_PROFILE.BANKNO IS
'银行账号';

COMMENT ON COLUMN EMP_PROFILE.QQ IS
'QQ号码';

COMMENT ON COLUMN EMP_PROFILE.EMAIL IS
'电子邮箱';

COMMENT ON COLUMN EMP_PROFILE.HOBBY IS
'爱好';

COMMENT ON COLUMN EMP_PROFILE.RELIGION IS
'宗教信仰';

COMMENT ON COLUMN EMP_PROFILE.PARTY IS
'政治面貌';

COMMENT ON COLUMN EMP_PROFILE.NATIONALITY IS
'国籍';

COMMENT ON COLUMN EMP_PROFILE.RACE IS
'民族';

COMMENT ON COLUMN EMP_PROFILE.BIRTHPLACE IS
'出生地';

COMMENT ON COLUMN EMP_PROFILE.EDUDEGREE IS
'学历';

COMMENT ON COLUMN EMP_PROFILE.EDUMAJOR IS
'专业';

COMMENT ON COLUMN EMP_PROFILE.EDUCOLLEGE IS
'毕业院校';

COMMENT ON COLUMN EMP_PROFILE.STARTWORKDATE IS
'参加工作时间';

COMMENT ON COLUMN EMP_PROFILE.EDUCASE IS
'教育背景';

COMMENT ON COLUMN EMP_PROFILE.AWARDPUNISHCASE IS
'奖惩情况';

COMMENT ON COLUMN EMP_PROFILE.TRAININGCASE IS
'培训情况';

COMMENT ON COLUMN EMP_PROFILE.WORKCASE IS
'工作经历';

COMMENT ON COLUMN EMP_PROFILE.IDCARD IS
'身份证号';

COMMENT ON COLUMN EMP_PROFILE.PHOTO IS
'照片';

COMMENT ON COLUMN EMP_PROFILE.STANDARDMINO IS
'薪酬标准编号';

COMMENT ON COLUMN EMP_PROFILE.STANDARDMONEY IS
'薪酬标准金额';

COMMENT ON COLUMN EMP_PROFILE.STANDARDNAME IS
'薪酬标准单名称';

COMMENT ON COLUMN EMP_PROFILE.STANDARDID IS
'薪酬标准单编号';

COMMENT ON COLUMN EMP_PROFILE.CREATOR IS
'建档人';

COMMENT ON COLUMN EMP_PROFILE.CREATETIME IS
'建档时间';

COMMENT ON COLUMN EMP_PROFILE.CHECKNAME IS
'审核人';

COMMENT ON COLUMN EMP_PROFILE.CHECKTIME IS
'审核时间';

COMMENT ON COLUMN EMP_PROFILE.APPROVALSTATUS IS
'核审状态
0=未审批
1=通过审核
2=未通过审核';

COMMENT ON COLUMN EMP_PROFILE.MEMO IS
'备注';

COMMENT ON COLUMN EMP_PROFILE.DEPNAME IS
'所属部门或公司';

COMMENT ON COLUMN EMP_PROFILE.DEPID IS
'所属部门Id';

COMMENT ON COLUMN EMP_PROFILE.DELFLAG IS
'删除状态
0=未删除
1=删除';

/*==============================================================*/
/* Table: ERRANDS_REGISTER                                      */
/*==============================================================*/
CREATE TABLE ERRANDS_REGISTER  (
   DATEID               NUMBER(18)                      NOT NULL,
   USERID               NUMBER(18)                      NOT NULL,
   DESCP                VARCHAR2(500)                   NOT NULL,
   STARTTIME            DATE                            NOT NULL,
   ENDTIME              DATE                            NOT NULL,
   APPROVALID           NUMBER(18),
   APPROVALNAME         VARCHAR2(128),
   STATUS               SMALLINT                        NOT NULL,
   APPROVALOPTION       VARCHAR2(500),
   FLAG                 SMALLINT,
   RUNID                NUMBER(18),
   CONSTRAINT PK_ERRANDS_REGISTER PRIMARY KEY (DATEID)
);

COMMENT ON TABLE ERRANDS_REGISTER IS
'请假、加班、外出登记';

COMMENT ON COLUMN ERRANDS_REGISTER.USERID IS
'外出/加班人员';

COMMENT ON COLUMN ERRANDS_REGISTER.DESCP IS
'描述';

COMMENT ON COLUMN ERRANDS_REGISTER.STARTTIME IS
'开始日期';

COMMENT ON COLUMN ERRANDS_REGISTER.ENDTIME IS
'结束日期';

COMMENT ON COLUMN ERRANDS_REGISTER.APPROVALID IS
'审批人ID';

COMMENT ON COLUMN ERRANDS_REGISTER.APPROVALNAME IS
'审批人';

COMMENT ON COLUMN ERRANDS_REGISTER.STATUS IS
'审批状态
0=未审批
1=通过审批
2=不通过审批';

COMMENT ON COLUMN ERRANDS_REGISTER.APPROVALOPTION IS
'审批意见';

COMMENT ON COLUMN ERRANDS_REGISTER.FLAG IS
'标识
0=加班
1=请假
2=外出';

/*==============================================================*/
/* Table: FIELD_RIGHTS                                          */
/*==============================================================*/
CREATE TABLE FIELD_RIGHTS  (
   RIGHTID              NUMBER(18)                      NOT NULL,
   MAPPINGID            NUMBER(18)                      NOT NULL,
   FIELDID              NUMBER(18)                      NOT NULL,
   TASKNAME             VARCHAR2(128)                   NOT NULL,
   READWRITE            SMALLINT                       DEFAULT 0 NOT NULL,
   CONSTRAINT PK_FIELD_RIGHTS PRIMARY KEY (RIGHTID)
);

COMMENT ON COLUMN FIELD_RIGHTS.READWRITE IS
'隐藏读写权限
0=隐藏
1=读
2=写';

/*==============================================================*/
/* Table: FILE_ATTACH                                           */
/*==============================================================*/
CREATE TABLE FILE_ATTACH  (
   FILEID               NUMBER(18)                      NOT NULL,
   FILENAME             VARCHAR2(128)                   NOT NULL,
   FILEPATH             VARCHAR2(128)                   NOT NULL,
   CREATETIME           DATE                            NOT NULL,
   EXT                  VARCHAR2(32),
   FILETYPE             VARCHAR2(128)                    NOT NULL,
   NOTE                 VARCHAR2(1024),
   CREATORID            NUMBER(18),
   CREATOR              VARCHAR2(32)                    NOT NULL,
   TOTALBYTES           NUMBER(18)                     DEFAULT 0,
   DELFLAG              SMALLINT,
   PROTYPEID            NUMBER(18),
   CONSTRAINT PK_FILE_ATTACH PRIMARY KEY (FILEID)
);

COMMENT ON TABLE FILE_ATTACH IS
'附件';

COMMENT ON COLUMN FILE_ATTACH.FILENAME IS
'文件名';

COMMENT ON COLUMN FILE_ATTACH.FILEPATH IS
'文件路径';

COMMENT ON COLUMN FILE_ATTACH.CREATETIME IS
'创建时间';

COMMENT ON COLUMN FILE_ATTACH.EXT IS
'扩展名';

COMMENT ON COLUMN FILE_ATTACH.FILETYPE IS
'附件类型
如：邮件附件';

COMMENT ON COLUMN FILE_ATTACH.NOTE IS
'说明';

COMMENT ON COLUMN FILE_ATTACH.CREATOR IS
'上传者';

COMMENT ON COLUMN FILE_ATTACH.DELFLAG IS
'1=已删除
0=删除';

/*==============================================================*/
/* Table: FIXED_ASSETS                                          */
/*==============================================================*/
CREATE TABLE FIXED_ASSETS  (
   ASSETSID             NUMBER(18)                      NOT NULL,
   ASSETSNO             VARCHAR2(128),
   ASSETSNAME           VARCHAR2(128)                   NOT NULL,
   MODEL                VARCHAR2(64),
   ASSETSTYPEID         NUMBER(18)                      NOT NULL,
   MANUFACTURER         VARCHAR2(64),
   MANUDATE             DATE,
   BUYDATE              DATE                            NOT NULL,
   BEDEP                VARCHAR2(64)                    NOT NULL,
   CUSTODIAN            VARCHAR2(32),
   NOTES                VARCHAR2(500),
   REMAINVALRATE        NUMBER(18,6)                    NOT NULL,
   DEPRETYPEID          NUMBER(18)                      NOT NULL,
   STARTDEPRE           DATE,
   INTENDTERM           NUMBER(18,2),
   INTENDWORKGROSS      NUMBER(18,2),
   WORKGROSSUNIT        VARCHAR2(128),
   ASSETVALUE           NUMBER(18,4)                    NOT NULL,
   ASSETCURVALUE        NUMBER(18,4)                    NOT NULL,
   DEPRERATE            NUMBER(18,2),
   DEFPERWORKGROSS      NUMBER(18,2),
   CONSTRAINT PK_FIXED_ASSETS PRIMARY KEY (ASSETSID)
);

COMMENT ON COLUMN FIXED_ASSETS.INTENDWORKGROSS IS
'当折旧的方法选择用工作量法进行计算时，才需要填写';

/*==============================================================*/
/* Table: FORM_DEF                                              */
/*==============================================================*/
CREATE TABLE FORM_DEF  (
   FORMDEFID            NUMBER(18)                      NOT NULL,
   FORMTITLE            VARCHAR2(128)                   NOT NULL,
   FORMDESP             CLOB,
   DEFHTML              CLOB,
   STATUS               SMALLINT                        NOT NULL,
   FORMTYPE             SMALLINT,
   ISDEFAULT            SMALLINT,
   ISGEN                SMALLINT                       DEFAULT 0,
   CONSTRAINT PK_FORM_DEF PRIMARY KEY (FORMDEFID),
   CONSTRAINT AK_FD_FORMNAME_FORM_DEF UNIQUE (FORMTITLE)
);

COMMENT ON COLUMN FORM_DEF.FORMDEFID IS
'表单ID';

COMMENT ON COLUMN FORM_DEF.FORMTITLE IS
'表单标题';

COMMENT ON COLUMN FORM_DEF.STATUS IS
'0=草稿状态
1=正式状态';

COMMENT ON COLUMN FORM_DEF.FORMTYPE IS
'表单类型
1=单表
2=主从表
3=多表';

COMMENT ON COLUMN FORM_DEF.ISDEFAULT IS
'是否缺省';

COMMENT ON COLUMN FORM_DEF.ISGEN IS
'1=已生成
0=未生成';

/*==============================================================*/
/* Table: FORM_RULE                                             */
/*==============================================================*/
create table FORM_RULE
(
  RULEID  NUMBER(18) NOT NULL,
  NAME    VARCHAR2(128) NOT NULL,
  RULE    VARCHAR2(128) NOT NULL,
  TIPINFO VARCHAR2(128) NOT NULL,
  MEMO    VARCHAR2(256)
);

COMMENT ON TABLE FORM_RULE
  IS '表单验证规则';
COMMENT ON COLUMN FORM_RULE.RULEID
  IS '主键';
COMMENT ON COLUMN FORM_RULE.NAME
  IS '规则名称';
COMMENT ON COLUMN FORM_RULE.RULE
  IS '规则表达式';
COMMENT ON COLUMN FORM_RULE.TIPINFO
  IS '提示信息';
COMMENT ON COLUMN FORM_RULE.MEMO
  IS '备注';



/*==============================================================*/
/* Table: FORM_DEF_MAPPING                                      */
/*==============================================================*/
CREATE TABLE FORM_DEF_MAPPING  (
   MAPPINGID            NUMBER(18)                      NOT NULL,
   FORMDEFID            NUMBER(18),
   DEFID                NUMBER(18),
   VERSIONNO            NUMBER(18)                     DEFAULT 0 NOT NULL,
   DEPLOYID             VARCHAR2(128)                   NOT NULL,
   USETEMPLATE          SMALLINT                       DEFAULT 0,
   CONSTRAINT PK_FORM_DEF_MAPPING PRIMARY KEY (MAPPINGID),
   CONSTRAINT AK_UK_DEPLOYID_FORM_DEF UNIQUE (DEPLOYID)
);

COMMENT ON COLUMN FORM_DEF_MAPPING.FORMDEFID IS
'表单ID';

COMMENT ON COLUMN FORM_DEF_MAPPING.DEPLOYID IS
'发布ID';

COMMENT ON COLUMN FORM_DEF_MAPPING.USETEMPLATE IS
'1=使用模板表单
0=使用普通在线表单';

/*==============================================================*/
/* Table: FORM_FIELD                                            */
/*==============================================================*/
CREATE TABLE FORM_FIELD  (
   FIELDID              NUMBER(18)                      NOT NULL,
   TABLEID              NUMBER(18),
   FIELDNAME            VARCHAR2(128)                   NOT NULL,
   FIELDLABEL           VARCHAR2(128),
   FIELDTYPE            VARCHAR2(128)                   NOT NULL,
   ISREQUIRED           SMALLINT,
   FIELDSIZE            NUMBER(18),
   FIELDDSCP            VARCHAR2(1024),
   ISPRIMARY            SMALLINT,
   FOREIGNKEY           VARCHAR2(64),
   FOREIGNTABLE         VARCHAR2(64),
   ISLIST               SMALLINT                       DEFAULT 1,
   ISQUERY              SMALLINT                       DEFAULT 1,
   SHOWFORMAT           VARCHAR2(256),
   ISFLOWTITLE          SMALLINT,
   ISDESIGNSHOW         SMALLINT,
   STATUS               SMALLINT,
   DECIMALLEN 			NUMBER(18),
   CONTROLTYPE 			NUMBER,
   CONSTRAINT PK_FORM_FIELD PRIMARY KEY (FIELDID)
);

COMMENT ON COLUMN FORM_FIELD.SHOWFORMAT IS
'显示格式
如日期显示yyyy-MM-dd
数字 如 000.00';

COMMENT ON COLUMN FORM_FIELD.ISFLOWTITLE IS
'1=是
0=否';

COMMENT ON COLUMN FORM_FIELD.ISDESIGNSHOW IS
'1=设计的可视化
2=设计的不可视化
3=手工加上';

COMMENT ON COLUMN FORM_FIELD.STATUS IS
'1=删除
0=未删除';

COMMENT ON COLUMN FORM_FIELD.CONTROLTYPE IS
'控件类型';

/*==============================================================*/
/* Table: FORM_TABLE                                            */
/*==============================================================*/
CREATE TABLE FORM_TABLE  (
   TABLEID              NUMBER(18)                      NOT NULL,
   FORMDEFID            NUMBER(18),
   TABLENAME            VARCHAR2(128)                   NOT NULL,
   TABLEKEY             VARCHAR2(128)                   NOT NULL,
   ISMAIN               SMALLINT,
   CONSTRAINT PK_FORM_TABLE PRIMARY KEY (TABLEID)
);

COMMENT ON COLUMN FORM_TABLE.FORMDEFID IS
'表单ID';

/*==============================================================*/
/* Table: FORM_TEMPLATE                                         */
/*==============================================================*/
CREATE TABLE FORM_TEMPLATE  (
   TEMPLATEID           NUMBER(18)                      NOT NULL,
   MAPPINGID            NUMBER(18),
   NODENAME             VARCHAR2(128)                   NOT NULL,
   TEMPCONTENT          CLOB,
   EXTDEF               CLOB,
   FORMURL              VARCHAR2(256),
   TEMPTYPE             SMALLINT,
   CONSTRAINT PK_FORM_TEMPLATE PRIMARY KEY (TEMPLATEID)
);

COMMENT ON COLUMN FORM_TEMPLATE.MAPPINGID IS
'映射ID';

COMMENT ON COLUMN FORM_TEMPLATE.NODENAME IS
'节点名称';

COMMENT ON COLUMN FORM_TEMPLATE.TEMPCONTENT IS
'模板内容';

COMMENT ON COLUMN FORM_TEMPLATE.TEMPTYPE IS
'1=EXT模板
2=URL模板';

/*==============================================================*/
/* Table: FUN_URL                                               */
/*==============================================================*/
CREATE TABLE FUN_URL  (
   URLID                NUMBER(18)                      NOT NULL,
   FUNCTIONID           NUMBER(18)                      NOT NULL,
   URLPATH              VARCHAR2(128)                   NOT NULL,
   CONSTRAINT PK_FUN_URL PRIMARY KEY (URLID)
);

/*==============================================================*/
/* Table: GLOBAL_TYPE                                           */
/*==============================================================*/
CREATE TABLE GLOBAL_TYPE  (
   PROTYPEID            NUMBER(18)                      NOT NULL,
   TYPENAME             VARCHAR2(128)                   NOT NULL,
   PATH                 VARCHAR2(64),
   DEPTH                NUMBER(18)                      NOT NULL,
   PARENTID             NUMBER(18),
   NODEKEY              VARCHAR2(64)                    NOT NULL,
   CATKEY               VARCHAR2(64)                    NOT NULL,
   SN                   NUMBER(18)                      NOT NULL,
   USERID               NUMBER(18),
   DEPID                NUMBER(18),
   CONSTRAINT PK_GLOBAL_TYPE PRIMARY KEY (PROTYPEID)
);

COMMENT ON TABLE GLOBAL_TYPE IS
'总分类表

用于显示树层次结构的分类
可以允许任何层次结构';

COMMENT ON COLUMN GLOBAL_TYPE.TYPENAME IS
'名称';

COMMENT ON COLUMN GLOBAL_TYPE.PATH IS
'路径';

COMMENT ON COLUMN GLOBAL_TYPE.DEPTH IS
'层次';

COMMENT ON COLUMN GLOBAL_TYPE.PARENTID IS
'父节点';

COMMENT ON COLUMN GLOBAL_TYPE.NODEKEY IS
'节点的分类Key';

COMMENT ON COLUMN GLOBAL_TYPE.CATKEY IS
'节点分类的Key，如产品分类Key为PT';

COMMENT ON COLUMN GLOBAL_TYPE.SN IS
'序号';

COMMENT ON COLUMN GLOBAL_TYPE.USERID IS
'所属用户
当为空则代表为公共分类';

/*==============================================================*/
/* Table: SERIAL_NUMBER  流水号                                       					*/
/*==============================================================*/
CREATE TABLE SERIAL_NUMBER
(
  NUMBERID    NUMBER(18) NOT NULL,
  NAME        VARCHAR2(50),
  ALIAS       VARCHAR2(20),
  REGULATION  VARCHAR2(100),
  GENTYPE     SMALLINT,
  NOLENGTH    INTEGER,
  CURDATE     VARCHAR2(10),
  INITVALUE   INTEGER,
  CURVALUE    INTEGER,
  STEP        SMALLINT,
  CONSTRAINT PK_SERIAL_NUMBER PRIMARY KEY (NUMBERID)
);

/*==============================================================*/
/* Table: GOODS_APPLY                                           */
/*==============================================================*/
CREATE TABLE GOODS_APPLY  (
   APPLYID              NUMBER(18)                      NOT NULL,
   GOODSID              NUMBER(18)                      NOT NULL,
   APPLYDATE            DATE                            NOT NULL,
   APPLYNO              VARCHAR2(128)                   NOT NULL,
   USECOUNTS            NUMBER(18)                      NOT NULL,
   USERID               NUMBER(18)                      NOT NULL,
   PROPOSER             VARCHAR2(32)                    NOT NULL,
   NOTES                VARCHAR2(500),
   APPROVALSTATUS       SMALLINT                        NOT NULL,
   CONSTRAINT PK_GOODS_APPLY PRIMARY KEY (APPLYID)
);

COMMENT ON TABLE GOODS_APPLY IS
'办公用品出库';

COMMENT ON COLUMN GOODS_APPLY.APPLYNO IS
'申请号,按系统时间产生，如GA20091002-0001';

COMMENT ON COLUMN GOODS_APPLY.APPROVALSTATUS IS
'审批状态
1=通过审批
0=未审批
';

/*==============================================================*/
/* Table: HIRE_ISSUE                                            */
/*==============================================================*/
CREATE TABLE HIRE_ISSUE  (
   HIREID               NUMBER(18)                      NOT NULL,
   TITLE                VARCHAR2(128)                   NOT NULL,
   STARTDATE            DATE                            NOT NULL,
   ENDDATE              DATE                            NOT NULL,
   HIRECOUNT            NUMBER(18)                      NOT NULL,
   JOBNAME              VARCHAR2(128)                   NOT NULL,
   JOBCONDITION         VARCHAR2(1024),
   REGFULLNAME          VARCHAR2(128)                   NOT NULL,
   REGDATE              DATE                            NOT NULL,
   MODIFYFULLNAME       VARCHAR2(32),
   MODIFYDATE           DATE,
   CHECKFULLNAME        VARCHAR2(32),
   CHECKOPINION         VARCHAR2(512),
   CHECKDATE            DATE,
   STATUS               SMALLINT                        NOT NULL,
   MEMO                 VARCHAR2(1024),
   JOBID                NUMBER(18),
   CONSTRAINT PK_HIRE_ISSUE PRIMARY KEY (HIREID)
);

COMMENT ON TABLE HIRE_ISSUE IS
'招聘发布';

COMMENT ON COLUMN HIRE_ISSUE.TITLE IS
'招聘信息标题';

COMMENT ON COLUMN HIRE_ISSUE.STARTDATE IS
'开始时间';

COMMENT ON COLUMN HIRE_ISSUE.ENDDATE IS
'结束时间';

COMMENT ON COLUMN HIRE_ISSUE.HIRECOUNT IS
'招聘人数';

COMMENT ON COLUMN HIRE_ISSUE.JOBNAME IS
'职位名称';

COMMENT ON COLUMN HIRE_ISSUE.JOBCONDITION IS
'招聘要求(条件)';

COMMENT ON COLUMN HIRE_ISSUE.REGFULLNAME IS
'登记人姓名';

COMMENT ON COLUMN HIRE_ISSUE.REGDATE IS
'登记时间';

COMMENT ON COLUMN HIRE_ISSUE.MODIFYFULLNAME IS
'变更人姓名';

COMMENT ON COLUMN HIRE_ISSUE.MODIFYDATE IS
'变更时间';

COMMENT ON COLUMN HIRE_ISSUE.CHECKFULLNAME IS
'审核人姓名';

COMMENT ON COLUMN HIRE_ISSUE.CHECKOPINION IS
'审核意见';

COMMENT ON COLUMN HIRE_ISSUE.CHECKDATE IS
'审批时间';

COMMENT ON COLUMN HIRE_ISSUE.STATUS IS
'状态
1=通过审核
0=未审核
2=审核不通过';

COMMENT ON COLUMN HIRE_ISSUE.MEMO IS
'备注';

/*==============================================================*/
/* Table: HOLIDAY_RECORD                                        */
/*==============================================================*/
CREATE TABLE HOLIDAY_RECORD  (
   RECORDID             NUMBER(18)                      NOT NULL,
   STARTTIME            DATE                            NOT NULL,
   ENDTIME              DATE                            NOT NULL,
   DESCP                VARCHAR2(512),
   FULLNAME             VARCHAR2(32),
   USERID               NUMBER(18),
   ISALL                SMALLINT                        NOT NULL,
   CONSTRAINT PK_HOLIDAY_RECORD PRIMARY KEY (RECORDID)
);

COMMENT ON TABLE HOLIDAY_RECORD IS
'放假记录';

COMMENT ON COLUMN HOLIDAY_RECORD.STARTTIME IS
'开始日期';

COMMENT ON COLUMN HOLIDAY_RECORD.ENDTIME IS
'结束日期';

COMMENT ON COLUMN HOLIDAY_RECORD.DESCP IS
'假期描述';

COMMENT ON COLUMN HOLIDAY_RECORD.FULLNAME IS
'用户名';

COMMENT ON COLUMN HOLIDAY_RECORD.USERID IS
'所属用户
若为某员工的假期，则为员工自己的id';

COMMENT ON COLUMN HOLIDAY_RECORD.ISALL IS
'是否为全公司假期
1=是
0=否';

/*==============================================================*/
/* Table: IN_MESSAGE                                            */
/*==============================================================*/
CREATE TABLE IN_MESSAGE  (
   RECEIVEID            NUMBER(18)                      NOT NULL,
   MESSAGEID            NUMBER(18),
   USERID               NUMBER(18),
   READFLAG             SMALLINT                        NOT NULL,
   DELFLAG              SMALLINT                        NOT NULL,
   USERFULLNAME         VARCHAR2(32)                    NOT NULL,
   CONSTRAINT PK_IN_MESSAGE PRIMARY KEY (RECEIVEID)
);

COMMENT ON COLUMN IN_MESSAGE.USERID IS
'主键';

COMMENT ON COLUMN IN_MESSAGE.READFLAG IS
'1=has red
0=unread';

/*==============================================================*/
/* Table: IN_STOCK                                              */
/*==============================================================*/
CREATE TABLE IN_STOCK  (
   BUYID                NUMBER(18)                      NOT NULL,
   GOODSID              NUMBER(18)                      NOT NULL,
   PROVIDERNAME         VARCHAR2(128),
   STOCKNO              VARCHAR2(128)                   NOT NULL,
   PRICE                NUMBER(18,2),
   INCOUNTS             NUMBER(18),
   AMOUNT               NUMBER(18,2)                    NOT NULL,
   INDATE               DATE                            NOT NULL,
   BUYER                VARCHAR2(128),
   CONSTRAINT PK_IN_STOCK PRIMARY KEY (BUYID)
);

COMMENT ON TABLE IN_STOCK IS
'办公用品入库需要同时更新办公用品表的库存';

/*==============================================================*/
/* Table: INDEX_DISPLAY                                         */
/*==============================================================*/
CREATE TABLE INDEX_DISPLAY  (
   INDEXID              NUMBER(18)                      NOT NULL,
   PORTALID             VARCHAR2(64)                    NOT NULL,
   USERID               NUMBER(18)                      NOT NULL,
   COLNUM               NUMBER(18)                      NOT NULL,
   ROWNO                NUMBER(18)                      NOT NULL,
   CONSTRAINT PK_INDEX_DISPLAY PRIMARY KEY (INDEXID)
);

COMMENT ON TABLE INDEX_DISPLAY IS
'每个员工可以设置自己的显示方式';

COMMENT ON COLUMN INDEX_DISPLAY.PORTALID IS
'Portal ID';

COMMENT ON COLUMN INDEX_DISPLAY.USERID IS
'用户ID';

COMMENT ON COLUMN INDEX_DISPLAY.COLNUM IS
'列号';

COMMENT ON COLUMN INDEX_DISPLAY.ROWNO IS
'行号';

/*==============================================================*/
/* Table: JOB_CHANGE                                            */
/*==============================================================*/
CREATE TABLE JOB_CHANGE  (
   CHANGEID             NUMBER(18)                      NOT NULL,
   PROFILEID            NUMBER(18)                      NOT NULL,
   PROFILENO            VARCHAR2(64)                    NOT NULL,
   USERNAME             VARCHAR2(64),
   ORGJOBNAME           VARCHAR2(64)                    NOT NULL,
   NEWJOBNAME           VARCHAR2(64)                    NOT NULL,
   ORGSTANDARDID        NUMBER(18),
   ORGSTANDARDNO        VARCHAR2(64),
   ORGSTANDARDNAME      VARCHAR2(64),
   ORGDEPID             NUMBER(18),
   ORGDEPNAME           VARCHAR2(128),
   ORGTOTALMONEY        NUMBER(18,2),
   NEWSTANDARDID        NUMBER(18),
   NEWSTANDARDNO        VARCHAR2(64),
   NEWSTANDARDNAME      VARCHAR2(64),
   NEWDEPID             NUMBER(18),
   NEWDEPNAME           VARCHAR2(128),
   NEWTOTALMONEY        NUMBER(18,2),
   CHANGEREASON         VARCHAR2(1024),
   REGTIME              DATE,
   REGNAME              VARCHAR2(64),
   CHECKNAME            VARCHAR2(64),
   CHECKTIME            DATE,
   CHECKOPINION         VARCHAR2(512),
   STATUS               SMALLINT,
   MEMO                 VARCHAR2(1024),
   ORGJOBID             NUMBER(18),
   NEWJOBID             NUMBER(18),
   CONSTRAINT PK_JOB_CHANGE PRIMARY KEY (CHANGEID)
);

COMMENT ON COLUMN JOB_CHANGE.ORGSTANDARDID IS
'原薪酬标准单ID';

COMMENT ON COLUMN JOB_CHANGE.STATUS IS
'状态

-1=草稿
0=提交审批
1=通过审批
2=未通过审批
';

/*==============================================================*/
/* Table: MAIL                                                  */
/*==============================================================*/
CREATE TABLE MAIL  (
   MAILID               NUMBER(18)                      NOT NULL,
   SENDER               VARCHAR2(32)                    NOT NULL,
   SENDERID             NUMBER(18)                      NOT NULL,
   IMPORTANTFLAG        SMALLINT                        NOT NULL,
   SENDTIME             DATE                            NOT NULL,
   CONTENT              CLOB                            NOT NULL,
   SUBJECT              VARCHAR2(256)                   NOT NULL,
   COPYTONAMES          VARCHAR2(2000),
   COPYTOIDS            VARCHAR2(2000),
   RECIPIENTNAMES       VARCHAR2(2000)                  NOT NULL,
   RECIPIENTIDS         VARCHAR2(2000)                  NOT NULL,
   MAILSTATUS           SMALLINT                        NOT NULL,
   FILEIDS              VARCHAR2(500),
   FILENAMES            VARCHAR2(500),
   CONSTRAINT PK_MAIL PRIMARY KEY (MAILID)
);

COMMENT ON TABLE MAIL IS
'邮件';

COMMENT ON COLUMN MAIL.IMPORTANTFLAG IS
'1=一般
2=重要
3=非常重要';

COMMENT ON COLUMN MAIL.CONTENT IS
'邮件内容';

COMMENT ON COLUMN MAIL.SUBJECT IS
'邮件标题';

COMMENT ON COLUMN MAIL.COPYTONAMES IS
'抄送人姓名列表';

COMMENT ON COLUMN MAIL.COPYTOIDS IS
'抄送人ID列表
用'',''分开';

COMMENT ON COLUMN MAIL.RECIPIENTNAMES IS
'收件人姓名列表';

COMMENT ON COLUMN MAIL.RECIPIENTIDS IS
'收件人ID列表
用'',''分隔';

COMMENT ON COLUMN MAIL.MAILSTATUS IS
'邮件状态
1=正式邮件
0=草稿邮件';

COMMENT ON COLUMN MAIL.FILEIDS IS
'附件Ids，多个附件的ID，通过,分割';

COMMENT ON COLUMN MAIL.FILENAMES IS
'附件名称列表，通过,进行分割';

/*==============================================================*/
/* Table: MAIL_ATTACH                                           */
/*==============================================================*/
CREATE TABLE MAIL_ATTACH  (
   MAILID               NUMBER(18)                      NOT NULL,
   FILEID               NUMBER(18)                      NOT NULL,
   CONSTRAINT PK_MAIL_ATTACH PRIMARY KEY (MAILID, FILEID)
);

/*==============================================================*/
/* Table: MAIL_BOX                                              */
/*==============================================================*/
CREATE TABLE MAIL_BOX  (
   BOXID                NUMBER(18)                      NOT NULL,
   MAILID               NUMBER(18)                      NOT NULL,
   FOLDERID             NUMBER(18)                      NOT NULL,
   USERID               NUMBER(18),
   SENDTIME             DATE                            NOT NULL,
   DELFLAG              SMALLINT                        NOT NULL,
   READFLAG             SMALLINT                        NOT NULL,
   REPLYFLAG            SMALLINT                        NOT NULL,
   NOTE                 VARCHAR2(256),
   CONSTRAINT PK_MAIL_BOX PRIMARY KEY (BOXID)
);

COMMENT ON TABLE MAIL_BOX IS
'收件箱';

COMMENT ON COLUMN MAIL_BOX.USERID IS
'主键';

COMMENT ON COLUMN MAIL_BOX.DELFLAG IS
'del=1则代表删除';

COMMENT ON COLUMN MAIL_BOX.NOTE IS
'note';

/*==============================================================*/
/* Table: MAIL_FOLDER                                           */
/*==============================================================*/
CREATE TABLE MAIL_FOLDER  (
   FOLDERID             NUMBER(18)                      NOT NULL,
   USERID               NUMBER(18),
   FOLDERNAME           VARCHAR2(128)                   NOT NULL,
   PARENTID             NUMBER(18),
   DEPLEVEL             NUMBER(18)                      NOT NULL,
   PATH                 VARCHAR2(256),
   ISPUBLIC             SMALLINT                        NOT NULL,
   FOLDERTYPE           SMALLINT                        NOT NULL,
   CONSTRAINT PK_MAIL_FOLDER PRIMARY KEY (FOLDERID)
);

COMMENT ON COLUMN MAIL_FOLDER.FOLDERID IS
'文件夹编号';

COMMENT ON COLUMN MAIL_FOLDER.USERID IS
'主键';

COMMENT ON COLUMN MAIL_FOLDER.FOLDERNAME IS
'文件夹名称';

COMMENT ON COLUMN MAIL_FOLDER.PARENTID IS
'父目录';

COMMENT ON COLUMN MAIL_FOLDER.DEPLEVEL IS
'目录层';

COMMENT ON COLUMN MAIL_FOLDER.ISPUBLIC IS
'1=表示共享，则所有的员工均可以使用该文件夹
0=私人文件夹';

COMMENT ON COLUMN MAIL_FOLDER.FOLDERTYPE IS
'文件夹类型
1=收信箱
2=发信箱
3=草稿箱
4=删除箱
10=其他';

/*==============================================================*/
/* Table: MEETING                                               */
/*==============================================================*/
CREATE TABLE MEETING  (
   METTINGID            NUMBER(18)                      NOT NULL,
   HOLDTIME             DATE,
   HOLDLOCATION         VARCHAR2(128),
   MEETINGNAME          VARCHAR2(128),
   ATTENDUSERS          VARCHAR2(128),
   HOLDDEP              VARCHAR2(128),
   HOLDDEPID            NUMBER(18),
   SHORTDESC            VARCHAR2(256),
   ISFEEDBACK           SMALLINT                        NOT NULL,
   SUMMARY              CLOB,
   CONSTRAINT PK_MEETING PRIMARY KEY (METTINGID)
);

/*==============================================================*/
/* Table: MEETING_ATTEND                                        */
/*==============================================================*/
CREATE TABLE MEETING_ATTEND  (
   ATTENDID             NUMBER(18)                      NOT NULL,
   METTINGID            NUMBER(18)                      NOT NULL,
   USERNAME             VARCHAR2(64),
   USERID               NUMBER(18),
   DEPNAME              VARCHAR2(100),
   DEPID                NUMBER(18),
   ATTENDTYPE           SMALLINT                       DEFAULT 0 NOT NULL,
   FEEDBACK             VARCHAR2(1024),
   SIGNTIME             DATE,
   SIGNNAME             VARCHAR2(32)                    NOT NULL,
   CONSTRAINT PK_MEETING_ATTEND PRIMARY KEY (ATTENDID)
);

COMMENT ON TABLE MEETING_ATTEND IS
'会议参与部门或人员';

COMMENT ON COLUMN MEETING_ATTEND.ATTENDTYPE IS
'参与类型
0=user
1=department';

/*==============================================================*/
/* Table: MEETING_FILE                                          */
/*==============================================================*/
CREATE TABLE MEETING_FILE  (
   METTINGID            NUMBER(18)                      NOT NULL,
   FILEID               NUMBER(18)                      NOT NULL,
   CONSTRAINT PK_MEETING_FILE PRIMARY KEY (METTINGID, FILEID)
);

/*==============================================================*/
/* Table: NEWS                                                  */
/*==============================================================*/
CREATE TABLE NEWS  (
   NEWSID               NUMBER(18)                      NOT NULL,
   SECTIONID            NUMBER(18),
   SUBJECTICON          VARCHAR2(128),
   SUBJECT              VARCHAR2(128)                   NOT NULL,
   AUTHOR               VARCHAR2(32)                    NOT NULL,
   CREATETIME           DATE                            NOT NULL,
   EXPTIME              DATE,
   REPLYCOUNTS          NUMBER(18),
   VIEWCOUNTS           NUMBER(18),
   ISSUER               VARCHAR2(32)                    NOT NULL,
   CONTENT              CLOB                            NOT NULL,
   UPDATETIME           DATE,
   STATUS               SMALLINT                        NOT NULL,
   ISDESKIMAGE          SMALLINT,
   ISNOTICE             SMALLINT,
   SN                   NUMBER(18),
   ORGIDS				VARCHAR2(248),
   ORGNAMES             VARCHAR2(1000),
   CONSTRAINT PK_NEWS PRIMARY KEY (NEWSID)
);

COMMENT ON TABLE NEWS IS
'新闻';

COMMENT ON COLUMN NEWS.NEWSID IS
'ID';

COMMENT ON COLUMN NEWS.SUBJECT IS
'新闻标题';

COMMENT ON COLUMN NEWS.AUTHOR IS
'作者';

COMMENT ON COLUMN NEWS.CREATETIME IS
'创建时间';

COMMENT ON COLUMN NEWS.VIEWCOUNTS IS
'浏览数';

COMMENT ON COLUMN NEWS.CONTENT IS
'内容';

COMMENT ON COLUMN NEWS.STATUS IS
'
0=待审核
1=审核通过';

COMMENT ON COLUMN NEWS.ISDESKIMAGE IS
'是否为桌面新闻';

COMMENT ON COLUMN NEWS.ORGIDS IS
'组织ID';

COMMENT ON COLUMN NEWS.ORGNAMES IS
'组织名';

/*==============================================================*/
/* Table: NEWS_COMMENT                                          */
/*==============================================================*/
CREATE TABLE NEWS_COMMENT  (
   COMMENTID            NUMBER(18)                      NOT NULL,
   CONTENT              VARCHAR2(500)                   NOT NULL,
   CREATETIME           DATE                            NOT NULL,
   FULLNAME             VARCHAR2(32)                    NOT NULL,
   USERID               NUMBER(18)                      NOT NULL,
   NEWSID               NUMBER(18),
   CONSTRAINT PK_NEWS_COMMENT PRIMARY KEY (COMMENTID)
);

COMMENT ON COLUMN NEWS_COMMENT.NEWSID IS
'ID';

/*==============================================================*/
/* Table: OFFICE_GOODS                                          */
/*==============================================================*/
CREATE TABLE OFFICE_GOODS  (
   GOODSID              NUMBER(18)                      NOT NULL,
   TYPEID               NUMBER(18)                      NOT NULL,
   GOODSNAME            VARCHAR2(128)                   NOT NULL,
   GOODSNO              VARCHAR2(128)                   NOT NULL,
   SPECIFICATIONS       VARCHAR2(256)                   NOT NULL,
   UNIT                 VARCHAR2(64)                    NOT NULL,
   ISWARNING            SMALLINT                        NOT NULL,
   NOTES                VARCHAR2(500),
   STOCKCOUNTS          NUMBER(18)                      NOT NULL,
   WARNCOUNTS           NUMBER(18)                      NOT NULL,
   CONSTRAINT PK_OFFICE_GOODS PRIMARY KEY (GOODSID)
);

COMMENT ON TABLE OFFICE_GOODS IS
'办公用品';

COMMENT ON COLUMN OFFICE_GOODS.TYPEID IS
'所属分类';

COMMENT ON COLUMN OFFICE_GOODS.GOODSNAME IS
'物品名称';

COMMENT ON COLUMN OFFICE_GOODS.GOODSNO IS
'编号';

COMMENT ON COLUMN OFFICE_GOODS.SPECIFICATIONS IS
'规格';

COMMENT ON COLUMN OFFICE_GOODS.UNIT IS
'计量单位';

COMMENT ON COLUMN OFFICE_GOODS.ISWARNING IS
'是否启用库存警示';

COMMENT ON COLUMN OFFICE_GOODS.NOTES IS
'备注';

COMMENT ON COLUMN OFFICE_GOODS.STOCKCOUNTS IS
'库存总数';

COMMENT ON COLUMN OFFICE_GOODS.WARNCOUNTS IS
'最低库存数';

/*==============================================================*/
/* Table: OFFICE_GOODS_TYPE                                     */
/*==============================================================*/
CREATE TABLE OFFICE_GOODS_TYPE  (
   TYPEID               NUMBER(18)                      NOT NULL,
   TYPENAME             VARCHAR2(128)                   NOT NULL,
   CONSTRAINT PK_OFFICE_GOODS_TYPE PRIMARY KEY (TYPEID)
);

COMMENT ON TABLE OFFICE_GOODS_TYPE IS
'办公用品类型';

COMMENT ON COLUMN OFFICE_GOODS_TYPE.TYPENAME IS
'分类名称';

/*==============================================================*/
/* Table: ORGANIZATION                                          */
/*==============================================================*/
CREATE TABLE ORGANIZATION  (
   ORG_ID               NUMBER(18)                      NOT NULL,
   DEM_ID               NUMBER(18),
   ORG_NAME             VARCHAR2(128)                   NOT NULL,
   ORG_DESC             VARCHAR2(500),
   ORG_SUP_ID           NUMBER(18),
   PATH                 VARCHAR2(128),
   DEPTH                INT,
   ORG_TYPE             SMALLINT,
   CREATOR_ID           NUMBER(18),
   CREATETIME           DATE,
   UPDATE_ID            NUMBER(18),
   UPDATETIME           DATE,
   SN                   INT,
   CONSTRAINT PK_ORGANIZATION PRIMARY KEY (ORG_ID)
);

COMMENT ON COLUMN ORGANIZATION.ORG_TYPE IS
'1=公司
2=部门
3=其他组织';

/*==============================================================*/
/* Table: OUT_MAIL                                              */
/*==============================================================*/
CREATE TABLE OUT_MAIL  (
   MAILID               NUMBER(18)                      NOT NULL,
   UIDNO                VARCHAR2(512),
   USERID               NUMBER(18),
   FOLDERID             NUMBER(18),
   SETID  				NUMBER(18)						NOT NULL,
   TITLE                VARCHAR2(512),
   CONTENT              CLOB,
   SENDERADDRESSES      VARCHAR2(128)                   NOT NULL,
   SENDERNAME           VARCHAR2(128),
   RECEIVERADDRESSES    CLOB                            NOT NULL,
   RECEIVERNAMES        CLOB,
   CCADDRESSES          CLOB,
   CCNAMES              CLOB,
   BCCADDRESSES         CLOB,
   BCCANAMES            CLOB,
   MAILDATE             DATE                            NOT NULL,
   FILEIDS              VARCHAR2(512),
   FILENAMES            VARCHAR2(512),
   READFLAG             SMALLINT                       DEFAULT 0 NOT NULL,
   REPLYFLAG            SMALLINT                       DEFAULT 0 NOT NULL,
   
   CONSTRAINT PK_OUT_MAIL PRIMARY KEY (MAILID)
);

COMMENT ON COLUMN OUT_MAIL.FOLDERID IS
'文件夹编号';

COMMENT ON COLUMN OUT_MAIL.TITLE IS
'主题';

COMMENT ON COLUMN OUT_MAIL.CONTENT IS
'内容';

COMMENT ON COLUMN OUT_MAIL.SENDERADDRESSES IS
'发件人地址';

COMMENT ON COLUMN OUT_MAIL.SENDERNAME IS
'发件人地址别名';

COMMENT ON COLUMN OUT_MAIL.RECEIVERADDRESSES IS
'收件人地址';

COMMENT ON COLUMN OUT_MAIL.RECEIVERNAMES IS
'收件人地址别名';

COMMENT ON COLUMN OUT_MAIL.CCADDRESSES IS
'抄送人地址';

COMMENT ON COLUMN OUT_MAIL.CCNAMES IS
'抄送人地址别名';

COMMENT ON COLUMN OUT_MAIL.BCCADDRESSES IS
'暗送人地址';

COMMENT ON COLUMN OUT_MAIL.BCCANAMES IS
'暗送人地址别名';

COMMENT ON COLUMN OUT_MAIL.READFLAG IS
'0:未阅
1:已阅';

COMMENT ON COLUMN OUT_MAIL.REPLYFLAG IS
'0:未回复
1;已回复';

COMMENT ON COLUMN OUT_MAIL.SETID IS
'外部邮箱设置ID';

/*==============================================================*/
/* Table: OUT_MAIL_FILE                                         */
/*==============================================================*/
CREATE TABLE OUT_MAIL_FILE  (
   MAILID               NUMBER(18)                      NOT NULL,
   FILEID               NUMBER(18)                      NOT NULL,
   CONSTRAINT PK_OUT_MAIL_FILE PRIMARY KEY (MAILID, FILEID)
);

/*==============================================================*/
/* Table: OUT_MAIL_FOLDER                                       */
/*==============================================================*/
CREATE TABLE OUT_MAIL_FOLDER  (
   FOLDERID             NUMBER(18)                      NOT NULL,
   USERID               NUMBER(18),
   SETID  				NUMBER(18)						NOT NULL,
   FOLDERNAME           VARCHAR2(128)                   NOT NULL,
   PARENTID             NUMBER(18),
   DEPLEVEL             NUMBER(18)                      NOT NULL,
   PATH                 VARCHAR2(256),
   FOLDERTYPE           SMALLINT                        NOT NULL,
   CONSTRAINT PK_OUT_MAIL_FOLDER PRIMARY KEY (FOLDERID)
);

COMMENT ON COLUMN OUT_MAIL_FOLDER.FOLDERID IS
'文件夹编号';

COMMENT ON COLUMN OUT_MAIL_FOLDER.USERID IS
'主键';

COMMENT ON COLUMN OUT_MAIL_FOLDER.FOLDERNAME IS
'文件夹名称';

COMMENT ON COLUMN OUT_MAIL_FOLDER.PARENTID IS
'父目录';

COMMENT ON COLUMN OUT_MAIL_FOLDER.DEPLEVEL IS
'目录层';

COMMENT ON COLUMN OUT_MAIL_FOLDER.FOLDERTYPE IS
'文件夹类型
1=收信箱
2=发信箱
3=草稿箱
4=删除箱
10=其他';

COMMENT ON COLUMN OUT_MAIL_FOLDER.SETID IS
'外部邮箱设置ID';


/*==============================================================*/
/* Table: OUT_MAIL_USER_SETING                                  */
/*==============================================================*/
CREATE TABLE OUT_MAIL_USER_SETING  (
   SETID                   NUMBER(18)                   NOT NULL,
   USERID               NUMBER(18),
   USERNAME             VARCHAR2(128),
   ACCOUNTNAME			    VARCHAR2(128) 					NOT NULL,
   MAILADDRESS          VARCHAR2(128)                   NOT NULL,
   MAILPASS             VARCHAR2(128)                   NOT NULL,
   SMTPHOST             VARCHAR2(128)                   NOT NULL,
   SMTPPORT             VARCHAR2(64)                    NOT NULL,
   POPHOST              VARCHAR2(128)                   NOT NULL,
   POPPORT              VARCHAR2(64)                    NOT NULL,
   ISDEfAULT            NUMBER(18)						NOT NULL,
   CONSTRAINT PK_OUT_MAIL_USER_SETING PRIMARY KEY (SETID)
);

COMMENT ON COLUMN OUT_MAIL_USER_SETING.SETID IS
'邮箱设置ID';

COMMENT ON COLUMN OUT_MAIL_USER_SETING.USERID IS
'用户ID';

COMMENT ON COLUMN OUT_MAIL_USER_SETING.ACCOUNTNAME IS
'帐号名称';

COMMENT ON COLUMN OUT_MAIL_USER_SETING.USERNAME IS
'用户名称';

COMMENT ON COLUMN OUT_MAIL_USER_SETING.MAILADDRESS IS
'外部邮件地址';

COMMENT ON COLUMN OUT_MAIL_USER_SETING.MAILPASS IS
'外部邮件密码';

COMMENT ON COLUMN OUT_MAIL_USER_SETING.SMTPHOST IS
'smt主机';

COMMENT ON COLUMN OUT_MAIL_USER_SETING.SMTPPORT IS
'smt端口';

COMMENT ON COLUMN OUT_MAIL_USER_SETING.POPHOST IS
'pop主机';

COMMENT ON COLUMN OUT_MAIL_USER_SETING.POPPORT IS
'pop端口';

COMMENT ON COLUMN OUT_MAIL_USER_SETING.ISDEfAULT IS
'是否默认：1表示默认，0表示非默认';


/*==============================================================*/
/* Table: PAINT_TEMPLATE                                        */
/*==============================================================*/
CREATE TABLE PAINT_TEMPLATE  (
   PTEMPLATEID          NUMBER(18)                      NOT NULL,
   FILEID               NUMBER(18),
   TEMPLATEKEY          VARCHAR2(64),
   TEMPLATENAME         VARCHAR2(64)                    NOT NULL,
   PATH                 VARCHAR2(128),
   ISACTIVATE           SMALLINT                        NOT NULL,
   CONSTRAINT PK_PAINT_TEMPLATE PRIMARY KEY (PTEMPLATEID)
);

COMMENT ON COLUMN PAINT_TEMPLATE.PTEMPLATEID IS
'主键';

COMMENT ON COLUMN PAINT_TEMPLATE.TEMPLATENAME IS
'模板名称';

COMMENT ON COLUMN PAINT_TEMPLATE.PATH IS
'路径';

COMMENT ON COLUMN PAINT_TEMPLATE.ISACTIVATE IS
'是否激活
1=是
0=否';

/*==============================================================*/
/* Table: PHONE_BOOK                                            */
/*==============================================================*/
CREATE TABLE PHONE_BOOK  (
   PHONEID              NUMBER(18)                      NOT NULL,
   FULLNAME             VARCHAR2(128)                   NOT NULL,
   TITLE                VARCHAR2(32)                    NOT NULL,
   BIRTHDAY             DATE,
   NICKNAME             VARCHAR2(32),
   DUTY                 VARCHAR2(50),
   SPOUSE               VARCHAR2(32),
   CHILDS               VARCHAR2(40),
   COMPANYNAME          VARCHAR2(100),
   COMPANYADDRESS       VARCHAR2(128),
   COMPANYPHONE         VARCHAR2(32),
   COMPANYFAX           VARCHAR2(32),
   HOMEADDRESS          VARCHAR2(128),
   HOMEZIP              VARCHAR2(12),
   MOBILE               VARCHAR2(32),
   PHONE                VARCHAR2(32),
   EMAIL                VARCHAR2(32),
   QQ                   VARCHAR2(64),
   MSN                  VARCHAR2(128),
   NOTE                 VARCHAR2(500),
   USERID               NUMBER(18)                      NOT NULL,
   GROUPID              NUMBER(18),
   ISSHARED             SMALLINT                        NOT NULL,
   CONSTRAINT PK_PHONE_BOOK PRIMARY KEY (PHONEID)
);

COMMENT ON TABLE PHONE_BOOK IS
'通讯簿';

COMMENT ON COLUMN PHONE_BOOK.TITLE IS
'先生
女士
小姐';

/*==============================================================*/
/* Table: PHONE_GROUP                                           */
/*==============================================================*/
CREATE TABLE PHONE_GROUP  (
   GROUPID              NUMBER(18)                      NOT NULL,
   GROUPNAME            VARCHAR2(128)                   NOT NULL,
   ISSHARED             SMALLINT                        NOT NULL,
   SN                   NUMBER(18)                      NOT NULL,
   USERID               NUMBER(18)                      NOT NULL,
   ISPUBLIC             SMALLINT                       DEFAULT 0,
   CONSTRAINT PK_PHONE_GROUP PRIMARY KEY (GROUPID)
);

COMMENT ON COLUMN PHONE_GROUP.GROUPNAME IS
'分组名称';

COMMENT ON COLUMN PHONE_GROUP.ISSHARED IS
'1=共享
0=私有';

COMMENT ON COLUMN PHONE_GROUP.ISPUBLIC IS
'是否公共
0=私有
1=公共';

/*==============================================================*/
/* Table: PLAN_ATTEND                                           */
/*==============================================================*/
CREATE TABLE PLAN_ATTEND  (
   ATTENDID             NUMBER(18)                      NOT NULL,
   USERID               NUMBER(18),
   DEPID                NUMBER(18),
   PLANID               NUMBER(18)                      NOT NULL,
   ISDEP                SMALLINT                        NOT NULL,
   ISPRIMARY            SMALLINT,
   CONSTRAINT PK_PLAN_ATTEND PRIMARY KEY (ATTENDID)
);

COMMENT ON COLUMN PLAN_ATTEND.DEPID IS
'部门ID';

COMMENT ON COLUMN PLAN_ATTEND.ISDEP IS
'是否为部门';

COMMENT ON COLUMN PLAN_ATTEND.ISPRIMARY IS
'是否负责人';

/*==============================================================*/
/* Table: PLAN_FILE                                             */
/*==============================================================*/
CREATE TABLE PLAN_FILE  (
   FILEID               NUMBER(18)                      NOT NULL,
   PLANID               NUMBER(18)                      NOT NULL,
   CONSTRAINT PK_PLAN_FILE PRIMARY KEY (FILEID, PLANID)
);

/*==============================================================*/
/* Table: POSITION                                              */
/*==============================================================*/
CREATE TABLE POSITION  (
   POS_ID               NUMBER(18)                      NOT NULL,
   ORG_ID               NUMBER(18,0),
   POS_NAME             VARCHAR2(128)                   NOT NULL,
   POS_DESC             VARCHAR2(1024),
   POS_SUP_ID           NUMBER(18),
   PATH                 VARCHAR2(256),
   DEPTH                INTEGER,
   SN                	INTEGER,
   CONSTRAINT PK_POSITION PRIMARY KEY (POS_ID)
);

/*==============================================================*/
/* Table: POSITION_SUB                                          */
/*==============================================================*/
CREATE TABLE POSITION_SUB  (
   MAINPOSITIONID       NUMBER(18)                      NOT NULL,
   SUBPOSITIONID        NUMBER(18)                      NOT NULL,
   CONSTRAINT PK_POSITION_SUB PRIMARY KEY (MAINPOSITIONID, SUBPOSITIONID)
);

COMMENT ON TABLE POSITION_SUB IS
'同级岗位是用于管理同级用户的下级，如一经理岗位同时有正与副，正副也同时管理下面的职位人员的话，
可以把这两职位在这里设置了映射关系，则清楚他们是同时管理下面的岗位';

/*==============================================================*/
/* Table: PRO_DEF_RIGHTS                                        */
/*==============================================================*/
CREATE TABLE PRO_DEF_RIGHTS  (
   RIGHTSID             NUMBER(18)                      NOT NULL,
   PROTYPEID            NUMBER(18),
   DEFID                NUMBER(18),
   ROLENAMES            VARCHAR2(2000),
   DEPNAMES             VARCHAR2(2000),
   USERNAMES            VARCHAR2(2000),
   USERIDS              VARCHAR2(2000),
   ROLEIDS              VARCHAR2(2000),
   DEPIDS               VARCHAR2(2000),
   CONSTRAINT PK_PRO_DEF_RIGHTS PRIMARY KEY (RIGHTSID)
);

COMMENT ON COLUMN PRO_DEF_RIGHTS.USERIDS IS
'用户IDS
格式如下，以方便使用like操作
,1,2,';

COMMENT ON COLUMN PRO_DEF_RIGHTS.ROLEIDS IS
'角色IDS
格式如下，以方便使用like操作
,1,2,';

COMMENT ON COLUMN PRO_DEF_RIGHTS.DEPIDS IS
'部门IDS
格式如下，以方便使用like操作
,1,2,';

/*==============================================================*/
/* Table: PRO_DEFINITION                                        */
/*==============================================================*/
CREATE TABLE PRO_DEFINITION  (
   DEFID                NUMBER(18)                      NOT NULL,
   PROTYPEID            NUMBER(18),
   NAME                 VARCHAR2(256)                   NOT NULL,
   DESCRIPTION          VARCHAR2(1024),
   CREATETIME           DATE,
   DEPLOYID             VARCHAR2(64),
   PDID                 VARCHAR2(64),
   DEFKEY               VARCHAR2(64),
   DEFXML               CLOB,
   DRAWDEFXML           CLOB,
   ISDEFAULT            SMALLINT                       DEFAULT 0 NOT NULL,
   PROCESSNAME          VARCHAR2(128),
   NEWVERSION           NUMBER(18),
   STATUS               SMALLINT,
   PARENTID             NUMBER(18),
   ISMAIN               SMALLINT,
   UPDATETIME           DATE,
   SKIPFIRSTNODE        SMALLINT,
   CONSTRAINT PK_PRO_DEFINITION PRIMARY KEY (DEFID)
);

COMMENT ON TABLE PRO_DEFINITION IS
'流程定义';

COMMENT ON COLUMN PRO_DEFINITION.NAME IS
'流程的名称';

COMMENT ON COLUMN PRO_DEFINITION.DESCRIPTION IS
'描述';

COMMENT ON COLUMN PRO_DEFINITION.CREATETIME IS
'创建时间';

COMMENT ON COLUMN PRO_DEFINITION.DEPLOYID IS
'Jbpm 工作流id';

COMMENT ON COLUMN PRO_DEFINITION.DEFXML IS
'流程定义XML';

COMMENT ON COLUMN PRO_DEFINITION.ISDEFAULT IS
'是否缺省
1=是
0=否';

COMMENT ON COLUMN PRO_DEFINITION.PROCESSNAME IS
'来自jbpm的流程定义jpdl中的name值';

COMMENT ON COLUMN PRO_DEFINITION.STATUS IS
'1=激活
0=禁用';

COMMENT ON COLUMN PRO_DEFINITION.SKIPFIRSTNODE IS
'跳过第一个节点';

COMMENT ON COLUMN PRO_DEFINITION.ISMAIN IS
'0=非主版本
1=主版本 ';

/*==============================================================*/
/* Table: PRO_HANDLE_COMP                                       */
/*==============================================================*/
CREATE TABLE PRO_HANDLE_COMP  (
   HANDLEID             NUMBER(18)                      NOT NULL,
   DEPLOYID             VARCHAR2(128)                   NOT NULL,
   ACTIVITYNAME         VARCHAR2(128),
   TRANNAME             VARCHAR2(128),
   EVENTNAME            VARCHAR2(128),
   EVENTLEVEL           SMALLINT,
   EXECODE              VARCHAR2(4000),
   HANDLETYPE           SMALLINT,
   CONSTRAINT PK_PRO_HANDLE_COMP PRIMARY KEY (HANDLEID)
);

COMMENT ON COLUMN PRO_HANDLE_COMP.DEPLOYID IS
'JBPM流程DeployId';

COMMENT ON COLUMN PRO_HANDLE_COMP.ACTIVITYNAME IS
'节点名称';

COMMENT ON COLUMN PRO_HANDLE_COMP.TRANNAME IS
'若事件为某个transition中的事件的话，则该字段存储该值';

COMMENT ON COLUMN PRO_HANDLE_COMP.EVENTNAME IS
'事件名，有值为：
start
end';

COMMENT ON COLUMN PRO_HANDLE_COMP.EVENTLEVEL IS
'事件级别为三值：
1=process  代表为流程的事件
2=node     代表为流程节点的事件
3=transition 代表为跳转的事件';

COMMENT ON COLUMN PRO_HANDLE_COMP.EXECODE IS
'事件中动态执行的代码';

COMMENT ON COLUMN PRO_HANDLE_COMP.HANDLETYPE IS
'1=监听类 实现listener之类的接口
2=处理类  实现handler之类的接口';

/*==============================================================*/
/* Table: PRO_USER_ASSIGN                                       */
/*==============================================================*/
CREATE TABLE PRO_USER_ASSIGN  (
   ASSIGNID             NUMBER(18)                      NOT NULL,
   DEPLOYID             VARCHAR2(128)                   NOT NULL,
   ACTIVITYNAME         VARCHAR2(128)                   NOT NULL,
   ROLEID               VARCHAR2(128),
   ROLENAME             VARCHAR2(256),
   USERID               VARCHAR2(128),
   USERNAME             VARCHAR2(256),
   ISSIGNED             SMALLINT                       DEFAULT 0,
   JOBID                VARCHAR2(128),
   JOBNAME              VARCHAR2(128),
   REJOBID              VARCHAR2(128),
   REJOBNAME            VARCHAR2(128),
   DEPIDS               VARCHAR2(512),
   DEPNAMES             VARCHAR2(512),
   POSUSERFLAG          SMALLINT,
   DEPPOSIDS            VARCHAR2(128),
   DEPPOSNAMES          VARCHAR2(128),
   CONSTRAINT PK_PRO_USER_ASSIGN PRIMARY KEY (ASSIGNID)
);

COMMENT ON TABLE PRO_USER_ASSIGN IS
'流程过程中各个任务节点及启动流程时的角色及用户';

COMMENT ON COLUMN PRO_USER_ASSIGN.ASSIGNID IS
'授权ID';

COMMENT ON COLUMN PRO_USER_ASSIGN.DEPLOYID IS
'jbpm流程定义的id';

COMMENT ON COLUMN PRO_USER_ASSIGN.ACTIVITYNAME IS
'流程节点名称';

COMMENT ON COLUMN PRO_USER_ASSIGN.ROLEID IS
'角色Id';

COMMENT ON COLUMN PRO_USER_ASSIGN.USERID IS
'用户ID';

COMMENT ON COLUMN PRO_USER_ASSIGN.ISSIGNED IS
'1=是会签任务
0=非会签任务

若为会签任务，则需要为该会签添加会签的决策方式的设置
';

/*==============================================================*/
/* Table: PROCESS_FORM                                          */
/*==============================================================*/
CREATE TABLE PROCESS_FORM  (
   FORMID               NUMBER(18)                      NOT NULL,
   RUNID                NUMBER(18)                      NOT NULL,
   ACTIVITYNAME         VARCHAR2(256)                   NOT NULL,
   CREATETIME           DATE                            NOT NULL,
   ENDTIME              DATE,
   DURTIMES             NUMBER(18),
   CREATORID            NUMBER(18),
   CREATORNAME          VARCHAR2(64),
   FROMTASKID           VARCHAR2(64),
   FROMTASK             VARCHAR2(256),
   TASKID               VARCHAR2(64),
   TRANSTO              VARCHAR2(256),
   STATUS               SMALLINT                       DEFAULT 0,
   PREFORMID            NUMBER(18),
   COMMENTS             VARCHAR2(2000),
   CONSTRAINT PK_PROCESS_FORM PRIMARY KEY (FORMID)
);

COMMENT ON TABLE PROCESS_FORM IS
'流程表单
存储保存在运行中的流程表单数据';

COMMENT ON COLUMN PROCESS_FORM.RUNID IS
'所属运行流程';

COMMENT ON COLUMN PROCESS_FORM.ACTIVITYNAME IS
'活动或任务名称';

COMMENT ON COLUMN PROCESS_FORM.FROMTASKID IS
'该任务来自由哪一任务跳转过来，目的是为了查到该任务的上一任务，方便任务驳回。存储Jbpm 的任务ID';

COMMENT ON COLUMN PROCESS_FORM.FROMTASK IS
'该任务来自由哪一任务跳转过来，目的是为了查到该任务的上一任务，方便任务驳回。';

COMMENT ON COLUMN PROCESS_FORM.TASKID IS
'当前任务ID';

COMMENT ON COLUMN PROCESS_FORM.TRANSTO IS
'跳转节点
跳转至下一任务';

COMMENT ON COLUMN PROCESS_FORM.STATUS IS
'0=进入任务
1=完成
2=取消';

/*==============================================================*/
/* Table: PROCESS_MODULE                                        */
/*==============================================================*/
CREATE TABLE PROCESS_MODULE  (
   MODULEID             NUMBER(18)                      NOT NULL,
   MODULENAME           VARCHAR2(256)                   NOT NULL,
   MODULEKEY            VARCHAR2(128)                   NOT NULL,
   DESCP                VARCHAR2(4000),
   DEFID                NUMBER(18),
   PROCESSKEY           VARCHAR2(256),
   CREATOR              VARCHAR2(64),
   CREATETIME           DATE,
   CONSTRAINT PK_PROCESS_MODULE PRIMARY KEY (MODULEID)
);

/*==============================================================*/
/* Table: PROCESS_RUN                                           */
/*==============================================================*/
CREATE TABLE PROCESS_RUN  (
   RUNID                NUMBER(18)                      NOT NULL,
   SUBJECT              VARCHAR2(256)                   NOT NULL,
   CREATOR              VARCHAR2(128),
   USERID               NUMBER(18)                      NOT NULL,
   DEFID                NUMBER(18)                      NOT NULL,
   PIID                 VARCHAR2(64),
   PIDBID               NUMBER(18),
   PDID                 VARCHAR2(64),
   PROCESSNAME          VARCHAR2(128),
   CREATETIME           DATE                            NOT NULL,
   RUNSTATUS            SMALLINT                        NOT NULL,
   BUSDESC              VARCHAR2(1024),
   ENTITYNAME           VARCHAR2(128),
   ENTITYID             NUMBER(18),
   FORMDEFID            NUMBER(18),
   DEFHTML              CLOB,
   CONSTRAINT PK_PROCESS_RUN PRIMARY KEY (RUNID)
);

COMMENT ON TABLE PROCESS_RUN IS
'运行中的流程';

COMMENT ON COLUMN PROCESS_RUN.SUBJECT IS
'标题
一般为流程名称＋格式化的时间';

COMMENT ON COLUMN PROCESS_RUN.CREATOR IS
'创建人';

COMMENT ON COLUMN PROCESS_RUN.USERID IS
'所属用户';

COMMENT ON COLUMN PROCESS_RUN.DEFID IS
'所属流程定义';

COMMENT ON COLUMN PROCESS_RUN.PIID IS
'流程实例ID';

COMMENT ON COLUMN PROCESS_RUN.CREATETIME IS
'创建时间';

COMMENT ON COLUMN PROCESS_RUN.RUNSTATUS IS
'0=尚未启动
1=已经启动流程
2=运行结束';

COMMENT ON COLUMN PROCESS_RUN.FORMDEFID IS
'存储正在运行的表单定义id';

/*==============================================================*/
/* Table: PRODUCT                                               */
/*==============================================================*/
CREATE TABLE PRODUCT  (
   PRODUCTID            NUMBER(18)                      NOT NULL,
   PRODUCTNAME          VARCHAR2(128)                   NOT NULL,
   PRODUCTMODEL         VARCHAR2(128),
   UNIT                 VARCHAR2(128),
   COSTPRICE            NUMBER(18,2),
   SALESPRICE           NUMBER(18,2),
   PRODUCTDESC          VARCHAR2(512),
   PROVIDERID           NUMBER(18)                      NOT NULL,
   CREATETIME           DATE                            NOT NULL,
   UPDATETIME           DATE                            NOT NULL,
   CONSTRAINT PK_PRODUCT PRIMARY KEY (PRODUCTID)
);

COMMENT ON TABLE PRODUCT IS
'供应商产品';

COMMENT ON COLUMN PRODUCT.PRODUCTNAME IS
'产品名称';

COMMENT ON COLUMN PRODUCT.PRODUCTMODEL IS
'产品型号';

COMMENT ON COLUMN PRODUCT.UNIT IS
'计量单位';

COMMENT ON COLUMN PRODUCT.COSTPRICE IS
'成本价';

COMMENT ON COLUMN PRODUCT.SALESPRICE IS
'出售价';

COMMENT ON COLUMN PRODUCT.PRODUCTDESC IS
'产品描述';

COMMENT ON COLUMN PRODUCT.PROVIDERID IS
'所属供应商';

COMMENT ON COLUMN PRODUCT.CREATETIME IS
'收录时间';

/*==============================================================*/
/* Table: PROJECT                                               */
/*==============================================================*/
CREATE TABLE PROJECT  (
   PROJECTID            NUMBER(18)                      NOT NULL,
   PROJECTNAME          VARCHAR2(128)                   NOT NULL,
   PROJECTNO            VARCHAR2(64)                    NOT NULL,
   REQDESC              CLOB,
   ISCONTRACT           SMALLINT                        NOT NULL,
   FULLNAME             VARCHAR2(32)                    NOT NULL,
   MOBILE               VARCHAR2(32),
   PHONE                VARCHAR2(32),
   FAX                  VARCHAR2(32),
   OTHERCONTACTS        VARCHAR2(128),
   CUSTOMERID           NUMBER(18)                      NOT NULL,
   USERID               NUMBER(18)                      NOT NULL,
   CONSTRAINT PK_PROJECT PRIMARY KEY (PROJECTID)
);

COMMENT ON TABLE PROJECT IS
'项目信息';

COMMENT ON COLUMN PROJECT.PROJECTNAME IS
'项目名称';

COMMENT ON COLUMN PROJECT.PROJECTNO IS
'项目编号';

COMMENT ON COLUMN PROJECT.REQDESC IS
'需求描述';

COMMENT ON COLUMN PROJECT.ISCONTRACT IS
'是否签订合同';

COMMENT ON COLUMN PROJECT.FULLNAME IS
'联系人姓名';

COMMENT ON COLUMN PROJECT.MOBILE IS
'手机';

COMMENT ON COLUMN PROJECT.PHONE IS
'电话';

COMMENT ON COLUMN PROJECT.FAX IS
'传真';

COMMENT ON COLUMN PROJECT.OTHERCONTACTS IS
'其他联系方式';

COMMENT ON COLUMN PROJECT.CUSTOMERID IS
'所属客户';

COMMENT ON COLUMN PROJECT.USERID IS
'业务人员';

/*==============================================================*/
/* Table: PROJECT_FILE                                          */
/*==============================================================*/
CREATE TABLE PROJECT_FILE  (
   FILEID               NUMBER(18)                      NOT NULL,
   PROJECTID            NUMBER(18)                      NOT NULL,
   CONSTRAINT PK_PROJECT_FILE PRIMARY KEY (FILEID, PROJECTID)
);

COMMENT ON TABLE PROJECT_FILE IS
'项目附件';

/*==============================================================*/
/* Table: PROVIDER                                              */
/*==============================================================*/
CREATE TABLE PROVIDER  (
   PROVIDERID           NUMBER(18)                      NOT NULL,
   PROVIDERNAME         VARCHAR2(128)                   NOT NULL,
   CONTACTOR            VARCHAR2(128)                   NOT NULL,
   PHONE                VARCHAR2(32)                    NOT NULL,
   FAX                  VARCHAR2(32),
   SITE                 VARCHAR2(128),
   EMAIL                VARCHAR2(128),
   ADDRESS              VARCHAR2(128)                   NOT NULL,
   ZIP                  VARCHAR2(32),
   OPENBANK             VARCHAR2(128),
   ACCOUNT              VARCHAR2(64),
   NOTES                VARCHAR2(500),
   RANK                 NUMBER(18),
   CONSTRAINT PK_PROVIDER PRIMARY KEY (PROVIDERID)
);

COMMENT ON TABLE PROVIDER IS
'供应商';

COMMENT ON COLUMN PROVIDER.PROVIDERNAME IS
'供应商名称';

COMMENT ON COLUMN PROVIDER.CONTACTOR IS
'联系人';

COMMENT ON COLUMN PROVIDER.PHONE IS
'电话';

COMMENT ON COLUMN PROVIDER.FAX IS
'传真';

COMMENT ON COLUMN PROVIDER.SITE IS
'网址';

COMMENT ON COLUMN PROVIDER.EMAIL IS
'邮件';

COMMENT ON COLUMN PROVIDER.ADDRESS IS
'地址';

COMMENT ON COLUMN PROVIDER.ZIP IS
'邮编';

COMMENT ON COLUMN PROVIDER.OPENBANK IS
'开户行';

COMMENT ON COLUMN PROVIDER.ACCOUNT IS
'帐号';

COMMENT ON COLUMN PROVIDER.NOTES IS
'备注';

COMMENT ON COLUMN PROVIDER.RANK IS
'供应商等级
1=一级供应商
2＝二级供应商
3＝三级供应商
4＝四级供应商
';

/*==============================================================*/
/* Table: REG_ATTACH                                            */
/*==============================================================*/
CREATE TABLE REG_ATTACH  (
   FILEID               NUMBER(18)                      NOT NULL,
   REGID                NUMBER(18)                      NOT NULL,
   CONSTRAINT PK_REG_ATTACH PRIMARY KEY (FILEID, REGID)
);

/*==============================================================*/
/* Table: REGION                                                */
/*==============================================================*/
CREATE TABLE REGION  (
   REGIONID             NUMBER(18)                      NOT NULL,
   REGIONNAME           VARCHAR2(128)                   NOT NULL,
   REGIONTYPE           SMALLINT                        NOT NULL,
   PARENTID             NUMBER(18),
   CONSTRAINT PK_REGION PRIMARY KEY (REGIONID)
);

COMMENT ON TABLE REGION IS
'地区管理';

COMMENT ON COLUMN REGION.REGIONNAME IS
'地区名称';

COMMENT ON COLUMN REGION.REGIONTYPE IS
'地区类型
1=省份
2=市';

COMMENT ON COLUMN REGION.PARENTID IS
'上级地区';

/*==============================================================*/
/* Table: REGULATION                                            */
/*==============================================================*/
CREATE TABLE REGULATION  (
   REGID                NUMBER(18)                      NOT NULL,
   PROTYPEID            NUMBER(18),
   SUBJECT              VARCHAR2(256)                   NOT NULL,
   ISSUEDATE            DATE,
   ISSUEUSERID          NUMBER(18),
   ISSUEFULLNAME        VARCHAR2(64),
   ISSUEDEPID           NUMBER(18),
   ISSUEDEP             VARCHAR2(64),
   RECDEPS              VARCHAR2(1024),
   RECDEPIDS            VARCHAR2(1024),
   RECUSERS             VARCHAR2(1024),
   RECUSERIDS           VARCHAR2(1024),
   CONTENT              CLOB,
   KEYWORDS             VARCHAR2(256),
   STATUS               SMALLINT,
   CONSTRAINT PK_REGULATION PRIMARY KEY (REGID)
);

COMMENT ON COLUMN REGULATION.SUBJECT IS
'标题';

COMMENT ON COLUMN REGULATION.ISSUEDATE IS
'发布日期';

COMMENT ON COLUMN REGULATION.ISSUEUSERID IS
'发布人ID';

COMMENT ON COLUMN REGULATION.ISSUEFULLNAME IS
'发布人';

COMMENT ON COLUMN REGULATION.ISSUEDEPID IS
'发布部门ID';

COMMENT ON COLUMN REGULATION.ISSUEDEP IS
'发布部门';

COMMENT ON COLUMN REGULATION.RECDEPS IS
'接收部门范围';

COMMENT ON COLUMN REGULATION.RECDEPIDS IS
'接收部门范围ID';

COMMENT ON COLUMN REGULATION.RECUSERS IS
'接收人范围';

COMMENT ON COLUMN REGULATION.RECUSERIDS IS
'接收人范围ID';

COMMENT ON COLUMN REGULATION.CONTENT IS
'内容';

COMMENT ON COLUMN REGULATION.KEYWORDS IS
'关键字';

COMMENT ON COLUMN REGULATION.STATUS IS
'状态';

/*==============================================================*/
/* Table: RELATIVE_JOB                                          */
/*==============================================================*/
CREATE TABLE RELATIVE_JOB  (
   REJOBID              NUMBER(18)                      NOT NULL,
   JOBNAME              VARCHAR2(128)                   NOT NULL,
   JOBCODE              VARCHAR2(256),
   PARENT               NUMBER(18),
   PATH                 VARCHAR2(128),
   DEPATH               NUMBER(18)                     DEFAULT 0,
   CONSTRAINT PK_RELATIVE_JOB PRIMARY KEY (REJOBID)
);

COMMENT ON COLUMN RELATIVE_JOB.JOBNAME IS
'岗位名称';

COMMENT ON COLUMN RELATIVE_JOB.JOBCODE IS
'编码';

COMMENT ON COLUMN RELATIVE_JOB.PARENT IS
'父岗位';

COMMENT ON COLUMN RELATIVE_JOB.PATH IS
'路径';

COMMENT ON COLUMN RELATIVE_JOB.DEPATH IS
'深度';

/*==============================================================*/
/* Table: RELATIVE_USER                                         */
/*==============================================================*/
CREATE TABLE RELATIVE_USER  (
   RELATIVEUSERID       NUMBER(18)                      NOT NULL,
   REJOBID              NUMBER(18),
   USERID               NUMBER(18),
   JOBUSERID            NUMBER(18),
   ISSUPER              SMALLINT,
   CONSTRAINT PK_RELATIVE_USER PRIMARY KEY (RELATIVEUSERID)
);

COMMENT ON COLUMN RELATIVE_USER.RELATIVEUSERID IS
'ID';

COMMENT ON COLUMN RELATIVE_USER.REJOBID IS
'所属相对岗位';

COMMENT ON COLUMN RELATIVE_USER.USERID IS
'所属员工';

COMMENT ON COLUMN RELATIVE_USER.ISSUPER IS
'上下级标识
1=上级
0=下级';

/*==============================================================*/
/* Table: REPORT_PARAM                                          */
/*==============================================================*/
CREATE TABLE REPORT_PARAM  (
   PARAMID              NUMBER(18)                      NOT NULL,
   REPORTID             NUMBER(18)                      NOT NULL,
   PARAMNAME            VARCHAR2(64)                    NOT NULL,
   PARAMKEY             VARCHAR2(64)                    NOT NULL,
   DEFAULTVAL           VARCHAR2(128),
   PARAMTYPE            VARCHAR2(32)                    NOT NULL,
   SN                   NUMBER(18)                      NOT NULL,
   PARAMTYPESTR         VARCHAR2(1024),
   CONSTRAINT PK_REPORT_PARAM PRIMARY KEY (PARAMID)
);

COMMENT ON TABLE REPORT_PARAM IS
'报表参数';

COMMENT ON COLUMN REPORT_PARAM.REPORTID IS
'所属报表';

COMMENT ON COLUMN REPORT_PARAM.PARAMNAME IS
'参数名称';

COMMENT ON COLUMN REPORT_PARAM.PARAMKEY IS
'参数Key';

COMMENT ON COLUMN REPORT_PARAM.DEFAULTVAL IS
'缺省值';

COMMENT ON COLUMN REPORT_PARAM.PARAMTYPE IS
'类型
字符类型--varchar
整型--int
精度型--decimal
日期型--date
日期时间型--datetime
';

COMMENT ON COLUMN REPORT_PARAM.SN IS
'系列号';

/*==============================================================*/
/* Table: REPORT_TEMPLATE                                       */
/*==============================================================*/
CREATE TABLE REPORT_TEMPLATE  (
   REPORTID             NUMBER(18)                      NOT NULL,
   TITLE                VARCHAR2(128)                   NOT NULL,
   DESCP                VARCHAR2(500)                   NOT NULL,
   REPORTLOCATION       VARCHAR2(128)                   NOT NULL,
   CREATETIME           DATE                            NOT NULL,
   UPDATETIME           DATE                            NOT NULL,
   REPORTKEY            VARCHAR2(128),
   ISDEFAULTIN          SMALLINT,
   CONSTRAINT PK_REPORT_TEMPLATE PRIMARY KEY (REPORTID)
);

COMMENT ON TABLE REPORT_TEMPLATE IS
'报表模板
report_template';

COMMENT ON COLUMN REPORT_TEMPLATE.TITLE IS
'标题';

COMMENT ON COLUMN REPORT_TEMPLATE.DESCP IS
'描述';

COMMENT ON COLUMN REPORT_TEMPLATE.REPORTLOCATION IS
'报表模块的jasper文件的路径';

COMMENT ON COLUMN REPORT_TEMPLATE.CREATETIME IS
'创建时间';

COMMENT ON COLUMN REPORT_TEMPLATE.UPDATETIME IS
'修改时间';

COMMENT ON COLUMN REPORT_TEMPLATE.REPORTKEY IS
'标识key';

COMMENT ON COLUMN REPORT_TEMPLATE.ISDEFAULTIN IS
'是否缺省
1=缺省
0=非缺省';

/*==============================================================*/
/* Table: RESUME                                                */
/*==============================================================*/
CREATE TABLE RESUME  (
   RESUMEID             NUMBER(18)                      NOT NULL,
   FULLNAME             VARCHAR2(64)                    NOT NULL,
   AGE                  NUMBER(18),
   BIRTHDAY             DATE,
   ADDRESS              VARCHAR2(128),
   ZIP                  VARCHAR2(32),
   SEX                  VARCHAR2(32),
   POSITION             VARCHAR2(64),
   PHONE                VARCHAR2(64),
   MOBILE               VARCHAR2(64),
   EMAIL                VARCHAR2(128),
   HOBBY                VARCHAR2(256),
   RELIGION             VARCHAR2(128),
   PARTY                VARCHAR2(128),
   NATIONALITY          VARCHAR2(32),
   RACE                 VARCHAR2(32),
   BIRTHPLACE           VARCHAR2(128),
   EDUCOLLEGE           VARCHAR2(128),
   EDUDEGREE            VARCHAR2(128),
   EDUMAJOR             VARCHAR2(128),
   STARTWORKDATE        DATE,
   IDNO                 VARCHAR2(64),
   PHOTO                VARCHAR2(128),
   STATUS               VARCHAR2(64),
   MEMO                 VARCHAR2(1024),
   REGISTOR             VARCHAR2(64),
   REGTIME              DATE,
   WORKCASE             CLOB,
   TRAINCASE            CLOB,
   PROJECTCASE          CLOB,
   CONSTRAINT PK_RESUME PRIMARY KEY (RESUMEID)
);

COMMENT ON TABLE RESUME IS
'简历管理';

COMMENT ON COLUMN RESUME.STATUS IS
'状态

通过
未通过
准备安排面试
面试通过

';

/*==============================================================*/
/* Table: RESUME_FILE                                           */
/*==============================================================*/
CREATE TABLE RESUME_FILE  (
   FILEID               NUMBER(18)                      NOT NULL,
   RESUMEID             NUMBER(18)                      NOT NULL,
   CONSTRAINT PK_RESUME_FILE PRIMARY KEY (FILEID, RESUMEID)
);

/*==============================================================*/
/* Table: ROLE_FUN                                              */
/*==============================================================*/
CREATE TABLE ROLE_FUN  (
   ROLEID               NUMBER(18)                      NOT NULL,
   FUNCTIONID           NUMBER(18)                      NOT NULL,
   CONSTRAINT PK_ROLE_FUN PRIMARY KEY (ROLEID, FUNCTIONID)
);

/*==============================================================*/
/* Table: ROLE_POSITION                                         */
/*==============================================================*/
CREATE TABLE ROLE_POSITION  (
   POS_ID               NUMBER(18)                      NOT NULL,
   ROLEID               NUMBER(18)                      NOT NULL,
   CONSTRAINT PK_ROLE_POSITION PRIMARY KEY (POS_ID, ROLEID)
);

/*==============================================================*/
/* Table: ROLL_FILE                                             */
/*==============================================================*/
CREATE TABLE ROLL_FILE  (
   ROLLFILEID           NUMBER(18)                      NOT NULL,
   TYPENAME             VARCHAR2(128),
   ROLLID               NUMBER(18),
   PROTYPEID            NUMBER(18),
   FILENAME             VARCHAR2(128)                   NOT NULL,
   FILENO               VARCHAR2(64)                    NOT NULL,
   DUTYPERSON           VARCHAR2(32),
   AFNO                 VARCHAR2(64),
   CATNO                VARCHAR2(64),
   ROLLNO               VARCHAR2(64),
   SEQNO                NUMBER(18),
   PAGENO               NUMBER(18),
   PAGENUMS             NUMBER(18),
   SECRETLEVEL          VARCHAR2(64),
   TIMELIMIT            VARCHAR2(64),
   OPENSTYLE            VARCHAR2(64),
   KEYWORDS             VARCHAR(512),
   NOTES                VARCHAR2(4000),
   CONTENT              CLOB,
   FILETIME             DATE,
   CREATORNAME          VARCHAR2(128),
   CREATETIME           DATE                           DEFAULT SYSDATE,
   ARCHSTATUS           SMALLINT                       DEFAULT 0,
   TIDYTIME             DATE,
   TIDYNAME             VARCHAR2(128),
   CONSTRAINT PK_ROLL_FILE PRIMARY KEY (ROLLFILEID)
);

COMMENT ON COLUMN ROLL_FILE.TYPENAME IS
'分类名称';

COMMENT ON COLUMN ROLL_FILE.ROLLID IS
'案卷ID';

COMMENT ON COLUMN ROLL_FILE.FILENAME IS
'文件题名';

COMMENT ON COLUMN ROLL_FILE.FILENO IS
'文件编号';

COMMENT ON COLUMN ROLL_FILE.DUTYPERSON IS
'责任者';

COMMENT ON COLUMN ROLL_FILE.AFNO IS
'全宗号';

COMMENT ON COLUMN ROLL_FILE.CATNO IS
'目录号';

COMMENT ON COLUMN ROLL_FILE.ROLLNO IS
'案卷号';

COMMENT ON COLUMN ROLL_FILE.SEQNO IS
'顺序号';

COMMENT ON COLUMN ROLL_FILE.PAGENO IS
'页号';

COMMENT ON COLUMN ROLL_FILE.PAGENUMS IS
'页数';

COMMENT ON COLUMN ROLL_FILE.SECRETLEVEL IS
'密级

普通
秘密
机密
绝密';

COMMENT ON COLUMN ROLL_FILE.TIMELIMIT IS
'保管期限
长久
长期
短期
10年
15年
20年
';

COMMENT ON COLUMN ROLL_FILE.OPENSTYLE IS
'开放形式
开放
待定
私密';

COMMENT ON COLUMN ROLL_FILE.KEYWORDS IS
'主题词';

COMMENT ON COLUMN ROLL_FILE.NOTES IS
'附注';

COMMENT ON COLUMN ROLL_FILE.CONTENT IS
'内容';

COMMENT ON COLUMN ROLL_FILE.FILETIME IS
'文件时间';

COMMENT ON COLUMN ROLL_FILE.CREATORNAME IS
'录入人';

COMMENT ON COLUMN ROLL_FILE.CREATETIME IS
'录入时间';

COMMENT ON COLUMN ROLL_FILE.ARCHSTATUS IS
'归档状态
1=归档
0=未归档';

COMMENT ON COLUMN ROLL_FILE.TIDYTIME IS
'归档时间';

COMMENT ON COLUMN ROLL_FILE.TIDYNAME IS
'归档人';

/*==============================================================*/
/* Table: ROLL_FILE_LIST                                        */
/*==============================================================*/
CREATE TABLE ROLL_FILE_LIST  (
   LISTID               NUMBER(18)                      NOT NULL,
   ROLLFILEID           NUMBER(18)                      NOT NULL,
   FILEID               NUMBER(18),
   DOWNLOADS            NUMBER(18),
   SN                   INT,
   SHORTDESC            VARCHAR2(500),
   CONSTRAINT PK_ROLL_FILE_LIST PRIMARY KEY (LISTID)
);

/*==============================================================*/
/* Table: RUN_DATA                                              */
/*==============================================================*/
CREATE TABLE RUN_DATA  (
   DATAID               NUMBER(18)                      NOT NULL,
   RUNID                NUMBER(18),
   FIELDLABEL           VARCHAR2(128),
   FIELDNAME            VARCHAR2(64)                    NOT NULL,
   INTVALUE             NUMBER(18),
   LONGVALUE            NUMBER(18),
   DECVALUE             NUMBER(18,4),
   DATEVALUE            DATE,
   STRVALUE             VARCHAR2(4000),
   BOOLVALUE            SMALLINT,
   BLOBVALUE            BLOB,
   ISSHOWED             SMALLINT,
   TEXTVALUE            CLOB,
   FIELDTYPE            VARCHAR2(32),
   CONSTRAINT PK_RUN_DATA PRIMARY KEY (DATAID)
);

COMMENT ON COLUMN RUN_DATA.FIELDLABEL IS
'字段标签';

COMMENT ON COLUMN RUN_DATA.FIELDNAME IS
'字段名称';

COMMENT ON COLUMN RUN_DATA.INTVALUE IS
'整数值';

COMMENT ON COLUMN RUN_DATA.LONGVALUE IS
'长整值';

COMMENT ON COLUMN RUN_DATA.DECVALUE IS
'精度值';

COMMENT ON COLUMN RUN_DATA.DATEVALUE IS
'日期值';

COMMENT ON COLUMN RUN_DATA.STRVALUE IS
'字符值';

COMMENT ON COLUMN RUN_DATA.BOOLVALUE IS
'布尔值';

COMMENT ON COLUMN RUN_DATA.BLOBVALUE IS
'对象值';

COMMENT ON COLUMN RUN_DATA.ISSHOWED IS
'是否显示
1=显示
0=不显示';

/*==============================================================*/
/* Table: SALARY_ITEM                                           */
/*==============================================================*/
CREATE TABLE SALARY_ITEM  (
   SALARYITEMID         NUMBER(18)                      NOT NULL,
   ITEMNAME             VARCHAR2(128)                   NOT NULL,
   DEFAULTVAL           NUMBER(18,2)                    NOT NULL,
   CONSTRAINT PK_SALARY_ITEM PRIMARY KEY (SALARYITEMID)
);

COMMENT ON TABLE SALARY_ITEM IS
'薪酬组成项目';

COMMENT ON COLUMN SALARY_ITEM.ITEMNAME IS
'项目名称';

COMMENT ON COLUMN SALARY_ITEM.DEFAULTVAL IS
'缺省值';

/*==============================================================*/
/* Table: SALARY_PAYOFF                                         */
/*==============================================================*/
CREATE TABLE SALARY_PAYOFF  (
   RECORDID             NUMBER(18)                      NOT NULL,
   FULLNAME             VARCHAR2(64)                    NOT NULL,
   USERID               NUMBER(18)                      NOT NULL,
   PROFILENO            VARCHAR2(128),
   STANDARDID           NUMBER(18)                      NOT NULL,
   IDNO                 VARCHAR2(128),
   STANDAMOUNT          NUMBER(18,2)                   DEFAULT 0 NOT NULL,
   ENCOURAGEAMOUNT      NUMBER(18,2)                   DEFAULT 0 NOT NULL,
   DEDUCTAMOUNT         NUMBER(18,2)                   DEFAULT 0 NOT NULL,
   ACHIEVEAMOUNT        NUMBER(18,2)                   DEFAULT 0,
   ENCOURAGEDESC        VARCHAR2(512),
   DEDUCTDESC           VARCHAR2(512),
   MEMO                 VARCHAR2(512),
   ACUTALAMOUNT         NUMBER(18,2),
   REGTIME              DATE                            NOT NULL,
   REGISTER             VARCHAR2(64),
   CHECKOPINION         VARCHAR2(1024),
   CHECKNAME            VARCHAR2(64),
   CHECKTIME            DATE,
   CHECKSTATUS          SMALLINT,
   STARTTIME            DATE                            NOT NULL,
   ENDTIME              DATE                            NOT NULL,
   CONSTRAINT PK_SALARY_PAYOFF PRIMARY KEY (RECORDID)
);

COMMENT ON COLUMN SALARY_PAYOFF.FULLNAME IS
'员工姓名';

COMMENT ON COLUMN SALARY_PAYOFF.USERID IS
'所属员工';

COMMENT ON COLUMN SALARY_PAYOFF.PROFILENO IS
'档案编号';

COMMENT ON COLUMN SALARY_PAYOFF.IDNO IS
'身份证号';

COMMENT ON COLUMN SALARY_PAYOFF.STANDAMOUNT IS
'薪标金额';

COMMENT ON COLUMN SALARY_PAYOFF.ENCOURAGEAMOUNT IS
'奖励金额';

COMMENT ON COLUMN SALARY_PAYOFF.DEDUCTAMOUNT IS
'扣除工资';

COMMENT ON COLUMN SALARY_PAYOFF.ACHIEVEAMOUNT IS
'效绩工资';

COMMENT ON COLUMN SALARY_PAYOFF.ENCOURAGEDESC IS
'奖励描述';

COMMENT ON COLUMN SALARY_PAYOFF.DEDUCTDESC IS
'扣除描述';

COMMENT ON COLUMN SALARY_PAYOFF.MEMO IS
'备注描述';

COMMENT ON COLUMN SALARY_PAYOFF.ACUTALAMOUNT IS
'实发金额';

COMMENT ON COLUMN SALARY_PAYOFF.REGTIME IS
'登记时间';

COMMENT ON COLUMN SALARY_PAYOFF.REGISTER IS
'登记人';

COMMENT ON COLUMN SALARY_PAYOFF.CHECKNAME IS
'审批人';

COMMENT ON COLUMN SALARY_PAYOFF.CHECKTIME IS
'审批时间';

COMMENT ON COLUMN SALARY_PAYOFF.CHECKSTATUS IS
'审批状态
0=草稿
1=通过审批
2=未通过审批
';

/*==============================================================*/
/* Table: SEAL                                                  */
/*==============================================================*/
CREATE TABLE SEAL  (
   SEALID               NUMBER(18)                      NOT NULL,
   FILEID               NUMBER(18),
   SEALNAME             VARCHAR2(64)                    NOT NULL,
   SEALPATH             VARCHAR2(128),
   BELONGID             NUMBER(18)                      NOT NULL,
   BELONGNAME           VARCHAR2(64)                    NOT NULL,
   CONSTRAINT PK_SEAL PRIMARY KEY (SEALID)
);

COMMENT ON COLUMN SEAL.SEALNAME IS
'印章名称';

COMMENT ON COLUMN SEAL.SEALPATH IS
'印章文件路径';

COMMENT ON COLUMN SEAL.BELONGID IS
'所属人ID';

COMMENT ON COLUMN SEAL.BELONGNAME IS
'所属人';

/*==============================================================*/
/* Table: SECTION                                               */
/*==============================================================*/
CREATE TABLE SECTION  (
   SECTIONID            NUMBER(18)                      NOT NULL,
   SECTIONNAME          VARCHAR2(256)                   NOT NULL,
   SECTIONDESC          VARCHAR2(1024),
   CREATETIME           DATE                            NOT NULL,
   SECTIONTYPE          SMALLINT                        NOT NULL,
   USERNAME             VARCHAR2(256),
   USERID               NUMBER(18),
   COLNUMBER            NUMBER(18),
   ROWNUMBER            NUMBER(18),
   STATUS               SMALLINT                        NOT NULL,
   CONSTRAINT PK_SECTION PRIMARY KEY (SECTIONID)
);

/*==============================================================*/
/* Table: SHORT_MESSAGE                                         */
/*==============================================================*/
CREATE TABLE SHORT_MESSAGE  (
   MESSAGEID            NUMBER(18)                      NOT NULL,
   SENDERID             NUMBER(18),
   CONTENT              VARCHAR2(256)                   NOT NULL,
   SENDER               VARCHAR2(64)                    NOT NULL,
   MSGTYPE              SMALLINT                        NOT NULL,
   SENDTIME             DATE                            NOT NULL,
   CONSTRAINT PK_SHORT_MESSAGE PRIMARY KEY (MESSAGEID)
);

COMMENT ON TABLE SHORT_MESSAGE IS
'短信消息';

COMMENT ON COLUMN SHORT_MESSAGE.SENDERID IS
'主键';

COMMENT ON COLUMN SHORT_MESSAGE.MSGTYPE IS
'1=个人信息
2=日程安排
3=计划任务
';

/*==============================================================*/
/* Table: SMS_HISTORY                                           */
/*==============================================================*/
CREATE TABLE SMS_HISTORY  (
   SMSID                NUMBER(18)                      NOT NULL,
   SENDTIME             DATE                            NOT NULL,
   RECIPIENTS           VARCHAR2(128),
   PHONENUMBER          VARCHAR2(128)                   NOT NULL,
   USERID               NUMBER(18),
   USERNAME             VARCHAR2(128),
   SMSCONTENT           VARCHAR2(1024)                  NOT NULL,
   STATUS               SMALLINT                        NOT NULL,
   CONSTRAINT PK_SMS_HISTORY PRIMARY KEY (SMSID)
);

COMMENT ON COLUMN SMS_HISTORY.STATUS IS
'0=未发送
1=发送失败

发送成功后，该记录会直接存在另一张发送历史的表中
';

/*==============================================================*/
/* Table: SMS_MOBILE                                            */
/*==============================================================*/
CREATE TABLE SMS_MOBILE  (
   SMSID                NUMBER(18)                      NOT NULL,
   SENDTIME             DATE                            NOT NULL,
   RECIPIENTS           VARCHAR2(128),
   PHONENUMBER          VARCHAR2(128)                   NOT NULL,
   USERID               NUMBER(18),
   USERNAME             VARCHAR2(128),
   SMSCONTENT           VARCHAR2(1024)                  NOT NULL,
   STATUS               SMALLINT                        NOT NULL,
   CONSTRAINT PK_SMS_MOBILE PRIMARY KEY (SMSID)
);

COMMENT ON COLUMN SMS_MOBILE.STATUS IS
'0=未发送
1=发送失败

发送成功后，该记录会直接存在另一张发送历史的表中
';

/*==============================================================*/
/* Table: STAND_SALARY                                          */
/*==============================================================*/
CREATE TABLE STAND_SALARY  (
   STANDARDID           NUMBER(18)                      NOT NULL,
   STANDARDNO           VARCHAR2(128)                   NOT NULL,
   STANDARDNAME         VARCHAR2(128)                   NOT NULL,
   TOTALMONEY           NUMBER(18,2)                   DEFAULT 0.00 NOT NULL,
   FRAMER               VARCHAR2(64),
   SETDOWNTIME          DATE,
   CHECKNAME            VARCHAR2(64),
   CHECKTIME            DATE,
   MODIFYNAME           VARCHAR2(64),
   MODIFYTIME           DATE,
   CHECKOPINION         VARCHAR2(512),
   STATUS               SMALLINT                        NOT NULL,
   MEMO                 VARCHAR2(512),
   CONSTRAINT PK_STAND_SALARY PRIMARY KEY (STANDARDID)
);

COMMENT ON COLUMN STAND_SALARY.STANDARDNO IS
'薪酬标准编号
惟一';

COMMENT ON COLUMN STAND_SALARY.STANDARDNAME IS
'标准名称';

COMMENT ON COLUMN STAND_SALARY.STATUS IS
'0=草稿
1=审批
2=未通过审批';

/*==============================================================*/
/* Table: STAND_SALARY_ITEM                                     */
/*==============================================================*/
CREATE TABLE STAND_SALARY_ITEM  (
   ITEMID               NUMBER(18)                      NOT NULL,
   STANDARDID           NUMBER(18)                      NOT NULL,
   ITEMNAME             VARCHAR2(64)                    NOT NULL,
   AMOUNT               NUMBER(18,2)                    NOT NULL,
   SALARYITEMID         NUMBER(18),
   CONSTRAINT PK_STAND_SALARY_ITEM PRIMARY KEY (ITEMID)
);

COMMENT ON TABLE STAND_SALARY_ITEM IS
'薪酬标准明细';

COMMENT ON COLUMN STAND_SALARY_ITEM.SALARYITEMID IS
'所属工资组成ID
外键，但不需要在数据库层建立外键';

/*==============================================================*/
/* Table: SUGGEST_BOX                                           */
/*==============================================================*/
CREATE TABLE SUGGEST_BOX  (
   BOXID                NUMBER(18)                      NOT NULL,
   SUBJECT              VARCHAR2(256)                   NOT NULL,
   CONTENT              VARCHAR2(4000)                  NOT NULL,
   CREATETIME           DATE,
   RECUID               NUMBER(18),
   RECFULLNAME          VARCHAR2(32),
   SENDERID             NUMBER(18),
   SENDERFULLNAME       VARCHAR2(32),
   SENDERIP             VARCHAR2(64),
   PHONE                VARCHAR2(64),
   EMAIL                VARCHAR2(100),
   ISOPEN               SMALLINT,
   REPLYCONTENT         VARCHAR2(4000),
   REPLYTIME            DATE,
   REPLYID              NUMBER(18),
   REPLYFULLNAME        VARCHAR2(32),
   STATUS               SMALLINT,
   QUERYPWD             VARCHAR2(128),
   CONSTRAINT PK_SUGGEST_BOX PRIMARY KEY (BOXID)
);

COMMENT ON COLUMN SUGGEST_BOX.SUBJECT IS
'意见标题';

COMMENT ON COLUMN SUGGEST_BOX.CONTENT IS
'意见内容';

COMMENT ON COLUMN SUGGEST_BOX.CREATETIME IS
'创建日期';

COMMENT ON COLUMN SUGGEST_BOX.RECUID IS
'接收人ID';

COMMENT ON COLUMN SUGGEST_BOX.RECFULLNAME IS
'接收人名';

COMMENT ON COLUMN SUGGEST_BOX.SENDERID IS
'发送人ID';

COMMENT ON COLUMN SUGGEST_BOX.SENDERFULLNAME IS
'发送人名';

COMMENT ON COLUMN SUGGEST_BOX.SENDERIP IS
'发送人IP';

COMMENT ON COLUMN SUGGEST_BOX.PHONE IS
'联系电话';

COMMENT ON COLUMN SUGGEST_BOX.EMAIL IS
'Email';

COMMENT ON COLUMN SUGGEST_BOX.ISOPEN IS
'是否公开';

COMMENT ON COLUMN SUGGEST_BOX.REPLYCONTENT IS
'回复内容';

COMMENT ON COLUMN SUGGEST_BOX.REPLYTIME IS
'回复时间';

COMMENT ON COLUMN SUGGEST_BOX.REPLYID IS
'回复人ID';

COMMENT ON COLUMN SUGGEST_BOX.REPLYFULLNAME IS
'回复人名';

COMMENT ON COLUMN SUGGEST_BOX.STATUS IS
'状态';

/*==============================================================*/
/* Table: SYS_CONFIG                                            */
/*==============================================================*/
CREATE TABLE SYS_CONFIG  (
   CONFIGID             NUMBER(18)                      NOT NULL,
   CONFIGKEY            VARCHAR2(64)                    NOT NULL,
   CONFIGNAME           VARCHAR2(64)                    NOT NULL,
   CONFIGDESC           VARCHAR2(256),
   TYPENAME             VARCHAR2(32)                    NOT NULL,
   DATATYPE             SMALLINT                        NOT NULL,
   DATAVALUE            VARCHAR2(64),
   TYPEKEY              VARCHAR2(64),
   CONSTRAINT PK_SYS_CONFIG PRIMARY KEY (CONFIGID)
);

COMMENT ON TABLE SYS_CONFIG IS
'系统配置

用于系统的全局配置
如邮件服务器的配置';

COMMENT ON COLUMN SYS_CONFIG.CONFIGKEY IS
'Key';

COMMENT ON COLUMN SYS_CONFIG.CONFIGNAME IS
'配置名称';

COMMENT ON COLUMN SYS_CONFIG.CONFIGDESC IS
'配置描述';

COMMENT ON COLUMN SYS_CONFIG.TYPENAME IS
'所属分类名称';

COMMENT ON COLUMN SYS_CONFIG.DATATYPE IS
'数据类型
1=varchar
2=intger
3=decimal
4=datetime
5=time
';

/*==============================================================*/
/* Table: TASK_SIGN                                             */
/*==============================================================*/
CREATE TABLE TASK_SIGN  (
   SIGNID               NUMBER(18)                      NOT NULL,
   SETID                NUMBER(18)                      NOT NULL,
   VOTECOUNTS           NUMBER(18),
   VOTEPERCENTS         NUMBER(18),
   DECIDETYPE           SMALLINT                        NOT NULL,
   signtype             INTEGER,
   CONSTRAINT PK_TASK_SIGN PRIMARY KEY (SIGNID)
);

COMMENT ON COLUMN TASK_SIGN.SETID IS
'所属设置流程节点ID';

COMMENT ON COLUMN TASK_SIGN.VOTECOUNTS IS
'绝对票数';

COMMENT ON COLUMN TASK_SIGN.VOTEPERCENTS IS
'百分比票数';

COMMENT ON COLUMN TASK_SIGN.DECIDETYPE IS
'1=pass 通过
2=reject 拒绝';

COMMENT on column TASK_SIGN.signtype is 
'1=绝对票数 2=百分比票数';

/*==============================================================*/
/* Table: TASK_SIGN_DATA                                        */
/*==============================================================*/
CREATE TABLE TASK_SIGN_DATA  (
   DATAID               NUMBER(18)                      NOT NULL,
   VOTEID               NUMBER(18)                      NOT NULL,
   VOTENAME             VARCHAR2(64),
   VOTETIME             DATE                            NOT NULL,
   TASKID               VARCHAR2(64)                    NOT NULL,
   ISAGREE              SMALLINT                        NOT NULL,
   CONSTRAINT PK_TASK_SIGN_DATA PRIMARY KEY (DATAID)
);

COMMENT ON COLUMN TASK_SIGN_DATA.VOTEID IS
'投票人';

COMMENT ON COLUMN TASK_SIGN_DATA.VOTENAME IS
'投票人名';

COMMENT ON COLUMN TASK_SIGN_DATA.VOTETIME IS
'投票时间';

COMMENT ON COLUMN TASK_SIGN_DATA.TASKID IS
'任务Id';

COMMENT ON COLUMN TASK_SIGN_DATA.ISAGREE IS
'是否同意
1=同意
2=拒绝

跟task_sign中的decideType是一样';

/*==============================================================*/
/* Table: TYPE_KEY                                              */
/*==============================================================*/
CREATE TABLE TYPE_KEY  (
   TYPEKEYID            NUMBER(18)                      NOT NULL,
   TYPEKEY              VARCHAR2(64)                    NOT NULL,
   TYPENAME             VARCHAR2(64)                    NOT NULL,
   SN                   NUMBER(18),
   CONSTRAINT PK_TYPE_KEY PRIMARY KEY (TYPEKEYID)
);

/*==============================================================*/
/* Table: USER_ORG                                              */
/*==============================================================*/
CREATE TABLE USER_ORG  (
   USER_ORG_ID          NUMBER(18)                      NOT NULL,
   USERID               NUMBER(18),
   ORG_ID               NUMBER(18),
   IS_PRIMARY           SMALLINT                        NOT NULL,
   IS_CHARGE            NUMBER(18),
   CONSTRAINT PK_USER_ORG PRIMARY KEY (USER_ORG_ID)
);

COMMENT ON COLUMN USER_ORG.USERID IS
'主键';

COMMENT ON COLUMN USER_ORG.IS_PRIMARY IS
'1=主要
0=非主要';

/*==============================================================*/
/* Table: USER_POSITION                                         */
/*==============================================================*/
CREATE TABLE USER_POSITION  (
   USER_POS_ID          NUMBER(18)                      NOT NULL,
   POS_ID               NUMBER(18),
   USERID               NUMBER(18),
   ISPRIMARY            SMALLINT,
   CONSTRAINT PK_USER_POSITION PRIMARY KEY (USER_POS_ID)
);

COMMENT ON COLUMN USER_POSITION.USERID IS
'主键';

/*==============================================================*/
/* Table: USER_ROLE                                             */
/*==============================================================*/
CREATE TABLE USER_ROLE  (
   USERID               NUMBER(18)                      NOT NULL,
   ROLEID               NUMBER(18)                      NOT NULL,
   CONSTRAINT PK_USER_ROLE PRIMARY KEY (USERID, ROLEID)
);

COMMENT ON COLUMN USER_ROLE.USERID IS
'主键';

/*==============================================================*/
/* Table: WF_GENERAL                                            */
/*==============================================================*/
CREATE TABLE WF_GENERAL  (
   ENTITYID             NUMBER(18)                      NOT NULL,
   ITEMSUBJECT          VARCHAR2(128)                   NOT NULL,
   ITEMDESCP            CLOB                            NOT NULL,
   RUNID                NUMBER(18),
   CREATETIME           DATE,
   CONSTRAINT PK_WF_GENERAL PRIMARY KEY (ENTITYID)
);

COMMENT ON COLUMN WF_GENERAL.ENTITYID IS
'ID';

COMMENT ON COLUMN WF_GENERAL.ITEMDESCP IS
'申请描述';

COMMENT ON COLUMN WF_GENERAL.RUNID IS
'process_run表的主键，通过它可以取到相关的流程运行及审批信息';

/*==============================================================*/
/* Table: WORK_PLAN                                             */
/*==============================================================*/
CREATE TABLE WORK_PLAN  (
   PLANID               NUMBER(18)                      NOT NULL,
   PLANNAME             VARCHAR2(128)                   NOT NULL,
   PLANCONTENT          CLOB,
   STARTTIME            DATE                            NOT NULL,
   ENDTIME              DATE                            NOT NULL,
   TYPENAME             VARCHAR2(64),
   USERID               NUMBER(18),
   PROTYPEID            NUMBER(18),
   ISSUESCOPE           VARCHAR2(2000),
   PARTICIPANTS         VARCHAR2(2000),
   PRINCIPAL            VARCHAR2(256)                   NOT NULL,
   NOTE                 VARCHAR2(500),
   STATUS               SMALLINT                        NOT NULL,
   ISPERSONAL           SMALLINT                        NOT NULL,
   ICON                 VARCHAR2(128),
   CONSTRAINT PK_WORK_PLAN PRIMARY KEY (PLANID)
);

COMMENT ON TABLE WORK_PLAN IS
'工作计划';

COMMENT ON COLUMN WORK_PLAN.PLANNAME IS
'计划名称';

COMMENT ON COLUMN WORK_PLAN.PLANCONTENT IS
'计划内容';

COMMENT ON COLUMN WORK_PLAN.STARTTIME IS
'开始日期';

COMMENT ON COLUMN WORK_PLAN.ENDTIME IS
'结束日期';

COMMENT ON COLUMN WORK_PLAN.TYPENAME IS
'类型名称';

COMMENT ON COLUMN WORK_PLAN.USERID IS
'员工ID';

COMMENT ON COLUMN WORK_PLAN.ISSUESCOPE IS
'发布范围
0则代表全部部门
存放所有的参与部门ID
';

COMMENT ON COLUMN WORK_PLAN.PARTICIPANTS IS
'参与人
0则代表全部参与
参与人,即员工ID列表';

COMMENT ON COLUMN WORK_PLAN.PRINCIPAL IS
'负责人';

COMMENT ON COLUMN WORK_PLAN.NOTE IS
'备注';

COMMENT ON COLUMN WORK_PLAN.STATUS IS
'状态
1=激活
0=禁用';

COMMENT ON COLUMN WORK_PLAN.ISPERSONAL IS
'是否为个人计划
1=则为个人工作计划，这时发布范围，参与人均为空，负责人为当前用户
0=则代表为其他任务';

COMMENT ON COLUMN WORK_PLAN.ICON IS
'图标';

/*==============================================================*/
/* Table: PRO_USER_SET                                          */
/*==============================================================*/
CREATE TABLE PRO_USER_SET  (
   ID                   NUMBER(18)                      NOT NULL,
   DEFID                NUMBER(18),
   DEPLOYID             VARCHAR2(64)                    NOT NULL,
   JBPMDEFID            VARCHAR2(64)                    NOT NULL,
   NODENAME             VARCHAR2(256)                   NOT NULL,
   USERTYPE             SMALLINT                        NOT NULL,
   UIDS                 CLOB,
   UNAMES               CLOB,
   COMPTYPE             SMALLINT,
   DEMID 				NUMBER(18),
   SN 					NUMBER(18) 						default 1,
   STRATEGY              SMALLINT,
   CONSTRAINT PK_PRO_USER_SET PRIMARY KEY (ID)
);

COMMENT ON COLUMN PRO_USER_SET.USERTYPE IS '1=发起人 2=user 3=role 4=岗位 5=部门、组织 6=部门、组织负责人 7=上下级';

COMMENT ON COLUMN PRO_USER_SET.COMPTYPE IS '1=or 2=and 3=exclude';

COMMENT ON COLUMN PRO_USER_SET.DEMID IS '若选择上下级时对应的维度';

COMMENT ON COLUMN PRO_USER_SET.SN IS '序号';

COMMENT ON COLUMN PRO_USER_SET.STRATEGY IS '级数';

/*==============================================================*/
/* Table: PRO_NODE_SET                                          */
/*==============================================================*/
CREATE TABLE PRO_NODE_SET  (
   SETID                NUMBER(18)                      NOT NULL,
   DEFID                NUMBER(18)                      NOT NULL,
   DEPLOYID             VARCHAR2(128)                   NOT NULL,
   JBPMDEFID            VARCHAR2(64)                    NOT NULL,
   NODENAME             VARCHAR2(256)                   NOT NULL,
   NODETYPE             SMALLINT,
   JOINNODENAME         VARCHAR2(256),
   ISALLOWBACK          SMALLINT,
   CONSTRAINT PK_PRO_NODE_SET PRIMARY KEY (SETID)
);

COMMENT ON COLUMN PRO_NODE_SET.NODETYPE IS '1=普通任务节点 2=会签任务节点 3=分发任务节点';


CREATE TABLE FORM_BUTTON_RIGHT (
	BUTTONID  		NUMBER(20) 		NOT NULL,
	MAPPINGID 		NUMBER(20) 		NOT NULL,
	TABLEID 		NUMBER(20) 		NOT NULL,
	TABLENAME  		VARCHAR2(256)	NOT NULL,
	BUTTONRIGHT  	SMALLINT 		NOT NULL,
	USERTYPE  		SMALLINT 		NULL,
	UIDS  			CLOB,
	UNAMES  		CLOB,
	TASKNAME 		VARCHAR2(128) 	NOT NULL,
	BUTTONTYPE  	SMALLINT	 	NOT NULL,
	CONSTRAINT PK_FORM_BUTTON_RIGHT PRIMARY KEY (BUTTONID)
);

COMMENT ON TABLE FORM_BUTTON_RIGHT IS '子表添加权限表';
COMMENT ON COLUMN FORM_BUTTON_RIGHT.TABLENAME IS '子表名称';
COMMENT ON COLUMN FORM_BUTTON_RIGHT.BUTTONRIGHT IS '隐藏/显示权限  0 隐藏 1 显示';
COMMENT ON COLUMN FORM_BUTTON_RIGHT.USERTYPE IS '1=发起人;2=USER;3=ROLE;4=岗位;5=部门、组织;6=部门、组织负责人;7=上下级';
COMMENT ON COLUMN FORM_BUTTON_RIGHT.BUTTONTYPE IS '按钮类型 1：增加按钮，2:删除按钮';


ALTER TABLE FORM_BUTTON_RIGHT ADD CONSTRAINT FORM_BUTTON_RIGHT_IBFK_1 FOREIGN KEY (MAPPINGID) 
REFERENCES FORM_DEF_MAPPING (MAPPINGID) ON DELETE CASCADE;


ALTER TABLE PRO_NODE_SET
   ADD CONSTRAINT FK_PRO_NODE_R_PRO_DEF FOREIGN KEY (DEFID)
      REFERENCES PRO_DEFINITION (DEFID)
      ON DELETE CASCADE;

ALTER TABLE APP_TIPS
   ADD CONSTRAINT FK_APP_TIPS_AT_R_AP_APP_USER FOREIGN KEY (USERID)
      REFERENCES APP_USER (USERID);

--ALTER TABLE APP_USER
--   ADD CONSTRAINT FK_APP_USER_AU_R_ORG_ORGANIZA FOREIGN KEY (ORG_ID)
--      REFERENCES ORGANIZATION (ORG_ID);

ALTER TABLE APPOINTMENT
   ADD CONSTRAINT FK_APPOINTM_AP_R_AU_APP_USER FOREIGN KEY (USERID)
      REFERENCES APP_USER (USERID);

ALTER TABLE ARCH_DISPATCH
   ADD CONSTRAINT FK_AVDH_R_ARV FOREIGN KEY (ARCHIVESID)
      REFERENCES ARCHIVES (ARCHIVESID)
      ON DELETE CASCADE;

ALTER TABLE ARCH_FLOW_CONF
   ADD CONSTRAINT FK_AFC_R_PDN FOREIGN KEY (DEFID)
      REFERENCES PRO_DEFINITION (DEFID)
      ON DELETE CASCADE;

ALTER TABLE ARCH_FOND
   ADD CONSTRAINT FK_AF_R_GT FOREIGN KEY (PROTYPEID)
      REFERENCES GLOBAL_TYPE (PROTYPEID)
      ON DELETE SET NULL;

ALTER TABLE ARCH_HASTEN
   ADD CONSTRAINT FK_ARHN_R_ARV FOREIGN KEY (ARCHIVESID)
      REFERENCES ARCHIVES (ARCHIVESID);

ALTER TABLE ARCH_ROLL
   ADD CONSTRAINT FK_AR_R_AF FOREIGN KEY (ARCHFONDID)
      REFERENCES ARCH_FOND (ARCHFONDID)
      ON DELETE SET NULL;

ALTER TABLE ARCH_ROLL
   ADD CONSTRAINT FK_AR_R_GT FOREIGN KEY (PROTYPEID)
      REFERENCES GLOBAL_TYPE (PROTYPEID)
      ON DELETE SET NULL;

ALTER TABLE ARCH_TEMPLATE
   ADD CONSTRAINT FK_ARCH_TEM_AHT_R_FA_FILE_ATT FOREIGN KEY (FILEID)
      REFERENCES FILE_ATTACH (FILEID);

ALTER TABLE ARCH_TEMPLATE
   ADD CONSTRAINT FK_ARCH_TEM_ART_R_ARV_GLOBAL_T FOREIGN KEY (PROTYPEID)
      REFERENCES GLOBAL_TYPE (PROTYPEID);

ALTER TABLE ARCHIVES
   ADD CONSTRAINT FK_ARCHIVES_ARV_R_ART_GLOBAL_T FOREIGN KEY (GLO_PROTYPEID)
      REFERENCES GLOBAL_TYPE (PROTYPEID);

ALTER TABLE ARCHIVES
   ADD CONSTRAINT FK_ARCHIVES_ARV_R_ARV_GLOBAL_T FOREIGN KEY (PROTYPEID)
      REFERENCES GLOBAL_TYPE (PROTYPEID);

ALTER TABLE ARCHIVES_DEP
   ADD CONSTRAINT FK_ARCHIVES_AVD_R_ARV_ARCHIVES FOREIGN KEY (ARCHIVESID)
      REFERENCES ARCHIVES (ARCHIVESID)
      ON DELETE CASCADE;

ALTER TABLE ARCHIVES_DOC
   ADD CONSTRAINT FK_ARHD_R_FA FOREIGN KEY (FILEID)
      REFERENCES FILE_ATTACH (FILEID)
      ON DELETE SET NULL;

ALTER TABLE ARCHIVES_DOC
   ADD CONSTRAINT FK_ARCHIVES_ARVD_R_AR_ARCHIVES FOREIGN KEY (ARCHIVESID)
      REFERENCES ARCHIVES (ARCHIVESID);

ALTER TABLE BOOK
   ADD CONSTRAINT FK_BOOK_BK_R_BT_BOOK_TYP FOREIGN KEY (TYPEID)
      REFERENCES BOOK_TYPE (TYPEID);

ALTER TABLE BOOK_BOR_RET
   ADD CONSTRAINT FK_BOOK_BOR_BBR_R_BS_BOOK_SN FOREIGN KEY (BOOKSNID)
      REFERENCES BOOK_SN (BOOKSNID);

ALTER TABLE BOOK_SN
   ADD CONSTRAINT FK_BOOK_SN_BS_R_BK_BOOK FOREIGN KEY (BOOKID)
      REFERENCES BOOK (BOOKID);

ALTER TABLE BORROW_FILE_LIST
   ADD CONSTRAINT FK_BORROW_F_BFL_AR_ARCH_ROL FOREIGN KEY (ROLLID)
      REFERENCES ARCH_ROLL (ROLLID);

ALTER TABLE BORROW_FILE_LIST
   ADD CONSTRAINT FK_BORROW_F_BFL_R_AF_ARCH_FON FOREIGN KEY (ARCHFONDID)
      REFERENCES ARCH_FOND (ARCHFONDID);

ALTER TABLE BORROW_FILE_LIST
   ADD CONSTRAINT FK_BORROW_F_BFL_R_BR_BORROW_R FOREIGN KEY (RECORDID)
      REFERENCES BORROW_RECORD (RECORDID);

ALTER TABLE BORROW_FILE_LIST
   ADD CONSTRAINT FK_BORROW_F_BFL_R_RF_ROLL_FIL FOREIGN KEY (ROLLFILEID)
      REFERENCES ROLL_FILE (ROLLFILEID);

ALTER TABLE BORROW_RECORD
   ADD CONSTRAINT FK_BORROW_R_BR_R_AU1_APP_USER FOREIGN KEY (CHECKUSERID)
      REFERENCES APP_USER (USERID)
      ON DELETE SET NULL;

ALTER TABLE CAL_FILE
   ADD CONSTRAINT FK_CAL_FILE_CF_R_CP_CALENDAR FOREIGN KEY (PLANID)
      REFERENCES CALENDAR_PLAN (PLANID);

ALTER TABLE CAL_FILE
   ADD CONSTRAINT FK_CAL_FILE_CF_R_FA_FILE_ATT FOREIGN KEY (FILEID)
      REFERENCES FILE_ATTACH (FILEID);

ALTER TABLE CALENDAR_PLAN
   ADD CONSTRAINT FK_CALENDAR_CA_R_AU_APP_USER FOREIGN KEY (USERID)
      REFERENCES APP_USER (USERID);

ALTER TABLE CALENDAR_PLAN
   ADD CONSTRAINT FK_CALENDAR_CP_R_AUAS_APP_USER FOREIGN KEY (ASSIGNERID)
      REFERENCES APP_USER (USERID);

ALTER TABLE CAR_APPLY
   ADD CONSTRAINT FK_CAR_APPL_CRA_R_CR_CAR FOREIGN KEY (CARID)
      REFERENCES CAR (CARID);

ALTER TABLE CART_REPAIR
   ADD CONSTRAINT FK_CART_REP_CRR_R_CR_CAR FOREIGN KEY (CARID)
      REFERENCES CAR (CARID);

ALTER TABLE CONF_ATTACH
   ADD CONSTRAINT FK_CFA_R_CFC FOREIGN KEY (CONFID)
      REFERENCES CONFERENCE (CONFID)
      ON DELETE CASCADE;

ALTER TABLE CONF_ATTACH
   ADD CONSTRAINT FK_CCFA_R_FA FOREIGN KEY (FILEID)
      REFERENCES FILE_ATTACH (FILEID)
      ON DELETE CASCADE;

ALTER TABLE CONF_ATTEND
   ADD CONSTRAINT FK_CA_R_CFC FOREIGN KEY (CONFID)
      REFERENCES CONFERENCE (CONFID)
      ON DELETE CASCADE;

ALTER TABLE CONF_PRIVILEGE
   ADD CONSTRAINT FK_CP_R_CFC FOREIGN KEY (CONFID)
      REFERENCES CONFERENCE (CONFID)
      ON DELETE CASCADE;

ALTER TABLE CONF_SUM_ATTACH
   ADD CONSTRAINT FK_CSA_R_CS FOREIGN KEY (SUMID)
      REFERENCES CONF_SUMMARY (SUMID)
      ON DELETE CASCADE;

ALTER TABLE CONF_SUM_ATTACH
   ADD CONSTRAINT FK_CSA_R_FA FOREIGN KEY (FILEID)
      REFERENCES FILE_ATTACH (FILEID);

ALTER TABLE CONF_SUMMARY
   ADD CONSTRAINT FK_CS_R_CFC FOREIGN KEY (CONFID)
      REFERENCES CONFERENCE (CONFID);

ALTER TABLE CONFERENCE
   ADD CONSTRAINT FK_CF_R_BT_BD FOREIGN KEY (TYPEID)
      REFERENCES BOARD_TYPE (TYPEID)
      ON DELETE SET NULL;

ALTER TABLE CONFERENCE
   ADD CONSTRAINT FK_CFC_R_BDM FOREIGN KEY (ROOMID)
      REFERENCES BOARDROO (ROOMID)
      ON DELETE SET NULL;

ALTER TABLE CONTRACT
   ADD CONSTRAINT FK_CONTRACT_CT_R_PT_PROJECT FOREIGN KEY (PROJECTID)
      REFERENCES PROJECT (PROJECTID);

ALTER TABLE CONTRACT_CONFIG
   ADD CONSTRAINT FK_CONTRACT_CC_R_CT_CONTRACT FOREIGN KEY (CONTRACTID)
      REFERENCES CONTRACT (CONTRACTID);

ALTER TABLE CONTRACT_FILE
   ADD CONSTRAINT FK_CONTRACT_CTF_R_CT_CONTRACT FOREIGN KEY (CONTRACTID)
      REFERENCES CONTRACT (CONTRACTID);

ALTER TABLE CONTRACT_FILE
   ADD CONSTRAINT FK_CONTRACT_CTF_R_FA_FILE_ATT FOREIGN KEY (FILEID)
      REFERENCES FILE_ATTACH (FILEID);

ALTER TABLE CUS_CONNECTION
   ADD CONSTRAINT FK_CUS_CONN_CC_R_CS_CUSTOMER FOREIGN KEY (CUSTOMERID)
      REFERENCES CUSTOMER (CUSTOMERID);

ALTER TABLE CUS_LINKMAN
   ADD CONSTRAINT FK_CUS_LINK_CLM_R_CS_CUSTOMER FOREIGN KEY (CUSTOMERID)
      REFERENCES CUSTOMER (CUSTOMERID);

ALTER TABLE DEP_POS
   ADD CONSTRAINT FK_DEP_POS_REFERENCE_ORGANIZA FOREIGN KEY (ORG_ID)
      REFERENCES ORGANIZATION (ORG_ID);

ALTER TABLE DEP_POS
   ADD CONSTRAINT FK_DEP_POS_REFERENCE_POSITION FOREIGN KEY (POS_ID)
      REFERENCES POSITION (POS_ID);

ALTER TABLE DEPRE_RECORD
   ADD CONSTRAINT FK_DEPRE_RE_DR_R_FA_FIXED_AS FOREIGN KEY (ASSETSID)
      REFERENCES FIXED_ASSETS (ASSETSID);

ALTER TABLE DIARY
   ADD CONSTRAINT FK_DIARY_DY_R_AU_APP_USER FOREIGN KEY (USERID)
      REFERENCES APP_USER (USERID);

ALTER TABLE DICTIONARY
   ADD CONSTRAINT DTY_R_GT FOREIGN KEY (PROTYPEID)
      REFERENCES GLOBAL_TYPE (PROTYPEID)
      ON DELETE SET NULL;

ALTER TABLE DOC_FILE
   ADD CONSTRAINT FK_DOC_FILE_DF_F_DT_DOCUMENT FOREIGN KEY (DOCID)
      REFERENCES DOCUMENT (DOCID);

ALTER TABLE DOC_FILE
   ADD CONSTRAINT FK_DF_R_FA FOREIGN KEY (FILEID)
      REFERENCES FILE_ATTACH (FILEID);

ALTER TABLE DOC_FOLDER
   ADD CONSTRAINT FK_DOC_FOLD_DF_R_AU_APP_USER FOREIGN KEY (USERID)
      REFERENCES APP_USER (USERID);

ALTER TABLE DOC_HISTORY
   ADD CONSTRAINT FK_DOC_HIST_DHY_R_ARV_ARCHIVES FOREIGN KEY (DOCID)
      REFERENCES ARCHIVES_DOC (DOCID);

ALTER TABLE DOC_HISTORY
   ADD CONSTRAINT FK_DHY_R_FA FOREIGN KEY (FILEID)
      REFERENCES FILE_ATTACH (FILEID)
      ON DELETE SET NULL;

ALTER TABLE DOC_PRIVILEGE
   ADD CONSTRAINT FK_DOC_PRIV_DP_R_DF_DOC_FOLD FOREIGN KEY (FOLDERID)
      REFERENCES DOC_FOLDER (FOLDERID)
      ON DELETE CASCADE;

ALTER TABLE DOC_PRIVILEGE
   ADD CONSTRAINT FK_DOC_PRIV_DP_R_DT_DOCUMENT FOREIGN KEY (DOCID)
      REFERENCES DOCUMENT (DOCID)
      ON DELETE CASCADE;

ALTER TABLE DOCUMENT
   ADD CONSTRAINT FK_DOCUMENT_DT_R_AU_APP_USER FOREIGN KEY (USERID)
      REFERENCES APP_USER (USERID);

ALTER TABLE DOCUMENT
   ADD CONSTRAINT FK_DOCUMENT_DT_R_DF_DOC_FOLD FOREIGN KEY (FOLDERID)
      REFERENCES DOC_FOLDER (FOLDERID)
      ON DELETE SET NULL;

ALTER TABLE DUTY
   ADD CONSTRAINT FK_DUTY_DUY_R_AU_APP_USER FOREIGN KEY (USERID)
      REFERENCES APP_USER (USERID);

ALTER TABLE DUTY
   ADD CONSTRAINT FK_DUTY_DUY_R_DS_DUTY_SYS FOREIGN KEY (SYSTEMID)
      REFERENCES DUTY_SYSTEM (SYSTEMID);

ALTER TABLE DUTY_REGISTER
   ADD CONSTRAINT FK_DUTY_REG_DR_R_AU_APP_USER FOREIGN KEY (USERID)
      REFERENCES APP_USER (USERID);

ALTER TABLE DUTY_REGISTER
   ADD CONSTRAINT FK_DUTY_REG_DR_R_DS_DUTY_SEC FOREIGN KEY (SECTIONID)
      REFERENCES DUTY_SECTION (SECTIONID);

ALTER TABLE EMP_PROFILE
   ADD CONSTRAINT FK_EMP_PROF_EPF_R_AU_APP_USER FOREIGN KEY (USERID)
      REFERENCES APP_USER (USERID);

ALTER TABLE EMP_PROFILE
   ADD CONSTRAINT FK_EMP_PROF_PT_R_EPF_POSITION FOREIGN KEY (JOBID)
      REFERENCES POSITION (POS_ID);

ALTER TABLE EMP_PROFILE
   ADD CONSTRAINT FK_EMP_PROF_SD_R_SY_STAND_SA FOREIGN KEY (STANDARDID)
      REFERENCES STAND_SALARY (STANDARDID);

ALTER TABLE ERRANDS_REGISTER
   ADD CONSTRAINT FK_ERRANDS__ERP_R_AU_APP_USER FOREIGN KEY (APPROVALID)
      REFERENCES APP_USER (USERID);

ALTER TABLE ERRANDS_REGISTER
   ADD CONSTRAINT FK_ERRANDS__ER_R_AU_APP_USER FOREIGN KEY (USERID)
      REFERENCES APP_USER (USERID);

ALTER TABLE FIELD_RIGHTS
   ADD CONSTRAINT FK_FIELD_RI_FR_R_FDM_FORM_DEF FOREIGN KEY (MAPPINGID)
      REFERENCES FORM_DEF_MAPPING (MAPPINGID)
      ON DELETE CASCADE;

ALTER TABLE FIELD_RIGHTS
   ADD CONSTRAINT FK_FIELD_RI_FR_R_FF_FORM_FIE FOREIGN KEY (FIELDID)
      REFERENCES FORM_FIELD (FIELDID)
      ON DELETE CASCADE;

ALTER TABLE FILE_ATTACH
   ADD CONSTRAINT FK_FLE_AT_R_GT FOREIGN KEY (PROTYPEID)
      REFERENCES GLOBAL_TYPE (PROTYPEID)
      ON DELETE SET NULL;

ALTER TABLE FIXED_ASSETS
   ADD CONSTRAINT FK_FIXED_AS_FA_R_AT_ASSETS_T FOREIGN KEY (ASSETSTYPEID)
      REFERENCES ASSETS_TYPE (ASSETSTYPEID);

ALTER TABLE FIXED_ASSETS
   ADD CONSTRAINT FK_FIXED_AS_FA_R_DT_DEPRE_TY FOREIGN KEY (DEPRETYPEID)
      REFERENCES DEPRE_TYPE (DEPRETYPEID);
      
ALTER TABLE FORM_RULE
  ADD CONSTRAINT PK_FORM_RULE PRIMARY KEY (RULEID);     
      
ALTER TABLE FORM_DEF_MAPPING
   ADD CONSTRAINT FK_FORM_DEF_FDM_R_FD_FORM_DEF FOREIGN KEY (FORMDEFID)
      REFERENCES FORM_DEF (FORMDEFID)
      ON DELETE CASCADE;

ALTER TABLE FORM_DEF_MAPPING
   ADD CONSTRAINT FK_FORM_DEF_FDM_R_PD_PRO_DEFI FOREIGN KEY (DEFID)
      REFERENCES PRO_DEFINITION (DEFID)
      ON DELETE CASCADE;

ALTER TABLE FORM_FIELD
   ADD CONSTRAINT FK_FORM_FIE_FF_R_FD_FORM_TAB FOREIGN KEY (TABLEID)
      REFERENCES FORM_TABLE (TABLEID)
      ON DELETE CASCADE;

ALTER TABLE FORM_TABLE
   ADD CONSTRAINT FK_FORM_TAB_FT_R_FD_FORM_DEF FOREIGN KEY (FORMDEFID)
      REFERENCES FORM_DEF (FORMDEFID)
      ON DELETE CASCADE;

ALTER TABLE FORM_TEMPLATE
   ADD CONSTRAINT FK_FORM_TEM_FT_R_FDM_FORM_DEF FOREIGN KEY (MAPPINGID)
      REFERENCES FORM_DEF_MAPPING (MAPPINGID)
      ON DELETE SET NULL;

ALTER TABLE FUN_URL
   ADD CONSTRAINT FK_FUN_URL_FU_R_AFN_APP_FUNC FOREIGN KEY (FUNCTIONID)
      REFERENCES APP_FUNCTION (FUNCTIONID);

ALTER TABLE GOODS_APPLY
   ADD CONSTRAINT FK_GOODS_AP_GA_R_OG_OFFICE_G FOREIGN KEY (GOODSID)
      REFERENCES OFFICE_GOODS (GOODSID);

ALTER TABLE IN_MESSAGE
   ADD CONSTRAINT FK_IN_MESSA_IM_R_AU_APP_USER FOREIGN KEY (USERID)
      REFERENCES APP_USER (USERID);

ALTER TABLE IN_MESSAGE
   ADD CONSTRAINT FK_IN_MESSA_IM_R_SM_SHORT_ME FOREIGN KEY (MESSAGEID)
      REFERENCES SHORT_MESSAGE (MESSAGEID);

ALTER TABLE IN_STOCK
   ADD CONSTRAINT FK_IN_STOCK_IS_R_OG_OFFICE_G FOREIGN KEY (GOODSID)
      REFERENCES OFFICE_GOODS (GOODSID);

ALTER TABLE INDEX_DISPLAY
   ADD CONSTRAINT FK_INDEX_DI_ID_R_AU_APP_USER FOREIGN KEY (USERID)
      REFERENCES APP_USER (USERID);

ALTER TABLE JOB_CHANGE
   ADD CONSTRAINT FK_JOB_CH_R_POS_NEW FOREIGN KEY (NEWJOBID)
      REFERENCES POSITION (POS_ID)
      ON DELETE CASCADE;

ALTER TABLE JOB_CHANGE
   ADD CONSTRAINT FK_JOB_CH_R_POS FOREIGN KEY (ORGJOBID)
      REFERENCES POSITION (POS_ID)
      ON DELETE CASCADE;

ALTER TABLE MAIL
   ADD CONSTRAINT FK_MAIL_ML_R_AU_APP_USER FOREIGN KEY (SENDERID)
      REFERENCES APP_USER (USERID);

ALTER TABLE MAIL_ATTACH
   ADD CONSTRAINT FK_MAIL_ATT_MA_R_FA_FILE_ATT FOREIGN KEY (FILEID)
      REFERENCES FILE_ATTACH (FILEID);

ALTER TABLE MAIL_ATTACH
   ADD CONSTRAINT FK_MAIL_ATT_MA_R_ML_MAIL FOREIGN KEY (MAILID)
      REFERENCES MAIL (MAILID);

ALTER TABLE MAIL_BOX
   ADD CONSTRAINT FK_MAIL_BOX_MB_R_AU_APP_USER FOREIGN KEY (USERID)
      REFERENCES APP_USER (USERID);

ALTER TABLE MAIL_BOX
   ADD CONSTRAINT FK_MAIL_BOX_MB_R_FD_MAIL_FOL FOREIGN KEY (FOLDERID)
      REFERENCES MAIL_FOLDER (FOLDERID);

ALTER TABLE MAIL_BOX
   ADD CONSTRAINT FK_MAIL_BOX_MB_R_ML_MAIL FOREIGN KEY (MAILID)
      REFERENCES MAIL (MAILID);

ALTER TABLE MAIL_FOLDER
   ADD CONSTRAINT FK_MAIL_FOL_FD_R_AU_APP_USER FOREIGN KEY (USERID)
      REFERENCES APP_USER (USERID);

ALTER TABLE MEETING_ATTEND
   ADD CONSTRAINT FK_MEETING__MTA_R_MT_MEETING FOREIGN KEY (METTINGID)
      REFERENCES MEETING (METTINGID);

ALTER TABLE MEETING_FILE
   ADD CONSTRAINT FK_MEETING__MF_R_FA_FILE_ATT FOREIGN KEY (FILEID)
      REFERENCES FILE_ATTACH (FILEID);

ALTER TABLE MEETING_FILE
   ADD CONSTRAINT FK_MEETING__MF_R_MT_MEETING FOREIGN KEY (METTINGID)
      REFERENCES MEETING (METTINGID);

ALTER TABLE NEWS
   ADD CONSTRAINT FK_NEWS_NEWS_R_SC_SECTION FOREIGN KEY (SECTIONID)
      REFERENCES SECTION (SECTIONID)
      ON DELETE SET NULL;

ALTER TABLE NEWS_COMMENT
   ADD CONSTRAINT FK_NEWS_COM_NC_R_AU_APP_USER FOREIGN KEY (USERID)
      REFERENCES APP_USER (USERID);

ALTER TABLE NEWS_COMMENT
   ADD CONSTRAINT FK_NEWS_COM_NC_R_NS_NEWS FOREIGN KEY (NEWSID)
      REFERENCES NEWS (NEWSID);

ALTER TABLE OFFICE_GOODS
   ADD CONSTRAINT FK_OFFICE_G_OG_R_OGT_OFFICE_G FOREIGN KEY (TYPEID)
      REFERENCES OFFICE_GOODS_TYPE (TYPEID);

ALTER TABLE ORGANIZATION
   ADD CONSTRAINT FK_ORG_R_DMS FOREIGN KEY (DEM_ID)
      REFERENCES DEMENSION (DEM_ID)
      ON DELETE CASCADE;

ALTER TABLE OUT_MAIL
   ADD CONSTRAINT FK_OUT_MAIL_OM_R_OMF_OUT_MAIL FOREIGN KEY (FOLDERID)
      REFERENCES OUT_MAIL_FOLDER (FOLDERID);

ALTER TABLE OUT_MAIL_FILE
   ADD CONSTRAINT FK_OUT_MAIL_OMF_R_FA_FILE_ATT FOREIGN KEY (FILEID)
      REFERENCES FILE_ATTACH (FILEID);

ALTER TABLE OUT_MAIL_FILE
   ADD CONSTRAINT FK_OUT_MAIL_OMF_R_OM_OUT_MAIL FOREIGN KEY (MAILID)
      REFERENCES OUT_MAIL (MAILID);

ALTER TABLE OUT_MAIL_FOLDER
   ADD CONSTRAINT FK_OUT_MAIL_OMF_RAU_APP_USER FOREIGN KEY (USERID)
      REFERENCES APP_USER (USERID);

ALTER TABLE OUT_MAIL_USER_SETING
   ADD CONSTRAINT FK_OUT_MAIL_OMU_R_AU_APP_USER FOREIGN KEY (USERID)
      REFERENCES APP_USER (USERID)
      ON DELETE SET NULL;

ALTER TABLE PAINT_TEMPLATE
   ADD CONSTRAINT FK_PTE_R_FA FOREIGN KEY (FILEID)
      REFERENCES FILE_ATTACH (FILEID)
      ON DELETE CASCADE;

ALTER TABLE PHONE_BOOK
   ADD CONSTRAINT FK_PHONE_BO_PB_R_AU_APP_USER FOREIGN KEY (USERID)
      REFERENCES APP_USER (USERID);

ALTER TABLE PHONE_BOOK
   ADD CONSTRAINT FK_PHONE_BO_PB_R_PG_PHONE_GR FOREIGN KEY (GROUPID)
      REFERENCES PHONE_GROUP (GROUPID);

ALTER TABLE PHONE_GROUP
   ADD CONSTRAINT FK_PHONE_GR_PG_R_AU_APP_USER FOREIGN KEY (USERID)
      REFERENCES APP_USER (USERID);

ALTER TABLE PLAN_ATTEND
   ADD CONSTRAINT FK_PLAN_ATT_PAD_R_UA_APP_USER FOREIGN KEY (USERID)
      REFERENCES APP_USER (USERID);

ALTER TABLE PLAN_ATTEND
   ADD CONSTRAINT FK_PLAN_ATT_PAD_R_WP_WORK_PLA FOREIGN KEY (PLANID)
      REFERENCES WORK_PLAN (PLANID);

ALTER TABLE PLAN_FILE
   ADD CONSTRAINT FK_PLAN_FIL_PA_R_FA_FILE_ATT FOREIGN KEY (FILEID)
      REFERENCES FILE_ATTACH (FILEID);

ALTER TABLE PLAN_FILE
   ADD CONSTRAINT FK_PLAN_FIL_PA_R_WP_WORK_PLA FOREIGN KEY (PLANID)
      REFERENCES WORK_PLAN (PLANID);

ALTER TABLE POSITION_SUB
   ADD CONSTRAINT FK_POS_SUB_R_POS FOREIGN KEY (MAINPOSITIONID)
      REFERENCES POSITION (POS_ID)
      ON DELETE CASCADE;

ALTER TABLE POSITION_SUB
   ADD CONSTRAINT FK_POS_SUB_SUB_R_POS FOREIGN KEY (SUBPOSITIONID)
      REFERENCES POSITION (POS_ID)
      ON DELETE CASCADE;

ALTER TABLE PRO_DEF_RIGHTS
   ADD CONSTRAINT FK_PRO_DEF__PDR_R_GT_GLOBAL_T FOREIGN KEY (PROTYPEID)
      REFERENCES GLOBAL_TYPE (PROTYPEID)
      ON DELETE SET NULL;

ALTER TABLE PRO_DEF_RIGHTS
   ADD CONSTRAINT FK_PRO_DEF__PDR_R_PD_PRO_DEFI FOREIGN KEY (DEFID)
      REFERENCES PRO_DEFINITION (DEFID)
      ON DELETE CASCADE;

ALTER TABLE PRO_DEFINITION
   ADD CONSTRAINT FK_PRO_DEFI_PD_R_GT_GLOBAL_T FOREIGN KEY (PROTYPEID)
      REFERENCES GLOBAL_TYPE (PROTYPEID)
      ON DELETE SET NULL;

ALTER TABLE PROCESS_FORM
   ADD CONSTRAINT FK_PROCESS__PF_R_PR_PROCESS_ FOREIGN KEY (RUNID)
      REFERENCES PROCESS_RUN (RUNID);

ALTER TABLE PROCESS_MODULE
   ADD CONSTRAINT FK_PM_R_PDI FOREIGN KEY (DEFID)
      REFERENCES PRO_DEFINITION (DEFID)
      ON DELETE CASCADE;

ALTER TABLE PROCESS_RUN
   ADD CONSTRAINT FK_PRORN__R_FORM_DEF FOREIGN KEY (FORMDEFID)
      REFERENCES FORM_DEF (FORMDEFID)
      ON DELETE SET NULL;

ALTER TABLE PROCESS_RUN
   ADD CONSTRAINT FK_PROCESS__PR_R_AU_APP_USER FOREIGN KEY (USERID)
      REFERENCES APP_USER (USERID);

ALTER TABLE PROCESS_RUN
   ADD CONSTRAINT FK_PROCESS__PR_R_PD_PRO_DEFI FOREIGN KEY (DEFID)
      REFERENCES PRO_DEFINITION (DEFID);

ALTER TABLE PRODUCT
   ADD CONSTRAINT FK_PRODUCT_PD_R_PUT_PROVIDER FOREIGN KEY (PROVIDERID)
      REFERENCES PROVIDER (PROVIDERID);

ALTER TABLE PROJECT
   ADD CONSTRAINT FK_PROJECT_PR_R_CS_CUSTOMER FOREIGN KEY (CUSTOMERID)
      REFERENCES CUSTOMER (CUSTOMERID);

ALTER TABLE PROJECT
   ADD CONSTRAINT FK_PROJECT_PT_R_AU_APP_USER FOREIGN KEY (USERID)
      REFERENCES APP_USER (USERID);

ALTER TABLE PROJECT_FILE
   ADD CONSTRAINT FK_PROJECT__PF_R_FA_FILE_ATT FOREIGN KEY (FILEID)
      REFERENCES FILE_ATTACH (FILEID);

ALTER TABLE PROJECT_FILE
   ADD CONSTRAINT FK_PROJECT__PF_R_PT_PROJECT FOREIGN KEY (PROJECTID)
      REFERENCES PROJECT (PROJECTID);

ALTER TABLE REG_ATTACH
   ADD CONSTRAINT FK_REG_ATTA_RA_R_FA_FILE_ATT FOREIGN KEY (FILEID)
      REFERENCES FILE_ATTACH (FILEID)
      ON DELETE CASCADE;

ALTER TABLE REG_ATTACH
   ADD CONSTRAINT FK_REG_ATTA_RA_R_GT_REGULATI FOREIGN KEY (REGID)
      REFERENCES REGULATION (REGID)
      ON DELETE CASCADE;

ALTER TABLE REGULATION
   ADD CONSTRAINT FK_RG_R_GT FOREIGN KEY (PROTYPEID)
      REFERENCES GLOBAL_TYPE (PROTYPEID)
      ON DELETE SET NULL;

ALTER TABLE RELATIVE_USER
   ADD CONSTRAINT FK_RELATIVE_RU_R_AU_APP_USER FOREIGN KEY (USERID)
      REFERENCES APP_USER (USERID)
      ON DELETE CASCADE;

ALTER TABLE RELATIVE_USER
   ADD CONSTRAINT FK_RELATIVE_RU_R_RJ_RELATIVE FOREIGN KEY (REJOBID)
      REFERENCES RELATIVE_JOB (REJOBID)
      ON DELETE CASCADE;

ALTER TABLE REPORT_PARAM
   ADD CONSTRAINT FK_REPORT_P_RP_R_RPT_REPORT_T FOREIGN KEY (REPORTID)
      REFERENCES REPORT_TEMPLATE (REPORTID);

ALTER TABLE RESUME_FILE
   ADD CONSTRAINT FK_RESUME_F_RMF_R_FA_FILE_ATT FOREIGN KEY (FILEID)
      REFERENCES FILE_ATTACH (FILEID);

ALTER TABLE RESUME_FILE
   ADD CONSTRAINT FK_RESUME_F_RMF_R_RM_RESUME FOREIGN KEY (RESUMEID)
      REFERENCES RESUME (RESUMEID);

ALTER TABLE ROLE_FUN
   ADD CONSTRAINT FK_ROLE_FUN_RF_R_AFN_APP_FUNC FOREIGN KEY (FUNCTIONID)
      REFERENCES APP_FUNCTION (FUNCTIONID);

ALTER TABLE ROLE_FUN
   ADD CONSTRAINT FK_ROLE_FUN_RF_R_AR_APP_ROLE FOREIGN KEY (ROLEID)
      REFERENCES APP_ROLE (ROLEID);

ALTER TABLE ROLE_POSITION
   ADD CONSTRAINT FK_ROL_POS_R_POS FOREIGN KEY (POS_ID)
      REFERENCES POSITION (POS_ID)
      ON DELETE CASCADE;

ALTER TABLE ROLE_POSITION
   ADD CONSTRAINT FK_ROLE_POS_RPOS_R_AP_APP_ROLE FOREIGN KEY (ROLEID)
      REFERENCES APP_ROLE (ROLEID);

ALTER TABLE ROLL_FILE
   ADD CONSTRAINT FK_RF_R_AR FOREIGN KEY (ROLLID)
      REFERENCES ARCH_ROLL (ROLLID)
      ON DELETE SET NULL;

ALTER TABLE ROLL_FILE
   ADD CONSTRAINT FK_RF_R_GT FOREIGN KEY (PROTYPEID)
      REFERENCES GLOBAL_TYPE (PROTYPEID)
      ON DELETE SET NULL;

ALTER TABLE ROLL_FILE_LIST
   ADD CONSTRAINT FK_ROLL_FIL_RFL_R_F_A_FILE_ATT FOREIGN KEY (FILEID)
      REFERENCES FILE_ATTACH (FILEID)
      ON DELETE CASCADE;

ALTER TABLE ROLL_FILE_LIST
   ADD CONSTRAINT FK_RFL_R_RF FOREIGN KEY (ROLLFILEID)
      REFERENCES ROLL_FILE (ROLLFILEID)
      ON DELETE CASCADE;

ALTER TABLE RUN_DATA
   ADD CONSTRAINT FK_FD_R_PR FOREIGN KEY (RUNID)
      REFERENCES PROCESS_RUN (RUNID)
      ON DELETE CASCADE;

ALTER TABLE SEAL
   ADD CONSTRAINT FK_SE_R_FA FOREIGN KEY (FILEID)
      REFERENCES FILE_ATTACH (FILEID)
      ON DELETE CASCADE;

ALTER TABLE SHORT_MESSAGE
   ADD CONSTRAINT FK_SHORT_ME_SM_R_AU_APP_USER FOREIGN KEY (SENDERID)
      REFERENCES APP_USER (USERID);

ALTER TABLE STAND_SALARY_ITEM
   ADD CONSTRAINT FK_STAND_SA_SSI_R_SSY_STAND_SA FOREIGN KEY (STANDARDID)
      REFERENCES STAND_SALARY (STANDARDID);
      
ALTER TABLE TASK_SIGN
   ADD CONSTRAINT FK_TASK_SIG_TS_R_PRO_NODE_SET foreign key (SETID)
      REFERENCES PRO_NODE_SET (SETID);

ALTER TABLE USER_ORG
   ADD CONSTRAINT FK_USER_ORG_USER_ORG__APP_USER FOREIGN KEY (USERID)
      REFERENCES APP_USER (USERID);

ALTER TABLE USER_ORG
   ADD CONSTRAINT FK_USER_ORG_R_ORG FOREIGN KEY (ORG_ID)
      REFERENCES ORGANIZATION (ORG_ID)
      ON DELETE CASCADE;

ALTER TABLE USER_POSITION
   ADD CONSTRAINT FK_USER_POS_R_POSITION FOREIGN KEY (POS_ID)
      REFERENCES POSITION (POS_ID)
      ON DELETE CASCADE;

ALTER TABLE USER_POSITION
   ADD CONSTRAINT FK_USER_POS_USER_POS__APP_USER FOREIGN KEY (USERID)
      REFERENCES APP_USER (USERID);

ALTER TABLE USER_ROLE
   ADD CONSTRAINT FK_UR_R_AR FOREIGN KEY (ROLEID)
      REFERENCES APP_ROLE (ROLEID);

ALTER TABLE USER_ROLE
   ADD CONSTRAINT FK_UR_R_AU FOREIGN KEY (USERID)
      REFERENCES APP_USER (USERID);

ALTER TABLE WORK_PLAN
   ADD CONSTRAINT FK_WP_R_GT FOREIGN KEY (PROTYPEID)
      REFERENCES GLOBAL_TYPE (PROTYPEID);

ALTER TABLE WORK_PLAN
   ADD CONSTRAINT FK_WORK_PLA_WP_R_AU_APP_USER FOREIGN KEY (USERID)
      REFERENCES APP_USER (USERID);

 ALTER TABLE PRO_USER_SET
   ADD CONSTRAINT FK_PRO_USR_R_PRO_DEF FOREIGN KEY (DEFID)
      REFERENCES PRO_DEFINITION (DEFID)
      ON DELETE CASCADE;
      
ALTER TABLE PRO_USER_SET 
	ADD CONSTRAINT FK_PRO_USR_D_DEM FOREIGN KEY (DEMID) 
		REFERENCES DEMENSION (DEM_ID) ON DELETE CASCADE;      
      
ALTER TABLE SUBORDINATE 
	ADD CONSTRAINT FK_SUBORDINATE_D_DEM FOREIGN KEY (DEM_ID) 
		REFERENCES DEMENSION (DEM_ID) ON DELETE CASCADE;

ALTER TABLE SUBORDINATE
	ADD CONSTRAINT FK_SUBORDINATE_U_APPUSER FOREIGN KEY (USERID) 
		REFERENCES APP_USER (USERID) ON DELETE CASCADE;

ALTER TABLE SUBORDINATE 
	ADD CONSTRAINT FK_SUBORDINATE_J_APPUSER FOREIGN KEY (JOBUSERID)
	REFERENCES APP_USER (USERID) ON DELETE CASCADE;

      
/**提交*/
commit;