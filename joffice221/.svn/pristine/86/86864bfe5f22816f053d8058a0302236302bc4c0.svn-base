
    create table JBPM4_DEPLOYMENT (
        DBID_ NUMERIC(18) not null,
        NAME_ lVARCHAR(32000),
        TIMESTAMP_ NUMERIC(18),
        STATE_ VARCHAR(254),
        primary key (DBID_)
    ) ;

    create table JBPM4_DEPLOYPROP (
        DBID_ NUMERIC(18) not null,
        DEPLOYMENT_ NUMERIC(18),
        OBJNAME_ VARCHAR(254),
        KEY_ VARCHAR(254),
        STRINGVAL_ VARCHAR(254),
        LONGVAL_ NUMERIC(18),
        primary key (DBID_)
    ) ;

    create table JBPM4_EXECUTION (
        DBID_ NUMERIC(18) not null,
        CLASS_ VARCHAR(254) not null,
        DBVERSION_ NUMERIC(18) not null,
        ACTIVITYNAME_ VARCHAR(254),
        PROCDEFID_ VARCHAR(254),
        HASVARS_ int8,
        NAME_ VARCHAR(254),
        KEY_ VARCHAR(254),
        ID_ VARCHAR(254) unique,
        STATE_ VARCHAR(254),
        SUSPHISTSTATE_ VARCHAR(254),
        PRIORITY_ NUMERIC(18),
        HISACTINST_ NUMERIC(18),
        PARENT_ NUMERIC(18),
        INSTANCE_ NUMERIC(18),
        SUPEREXEC_ NUMERIC(18),
        SUBPROCINST_ NUMERIC(18),
        PARENT_IDX_ NUMERIC(18),
        primary key (DBID_)
    ) ;

    create table JBPM4_HIST_ACTINST (
        DBID_ NUMERIC(18) not null,
        CLASS_ VARCHAR(254) not null,
        DBVERSION_ NUMERIC(18) not null,
        HPROCI_ NUMERIC(18),
        TYPE_ VARCHAR(254),
        EXECUTION_ VARCHAR(254),
        ACTIVITY_NAME_ VARCHAR(254),
        START_ DATETIME YEAR TO SECOND,
        END_ DATETIME YEAR TO SECOND,
        DURATION_ NUMERIC(18),
        TRANSITION_ VARCHAR(254),
        NEXTIDX_ NUMERIC(18),
        HTASK_ NUMERIC(18),
        primary key (DBID_)
    ) ;

    create table JBPM4_HIST_DETAIL (
        DBID_ NUMERIC(18) not null,
        CLASS_ VARCHAR(254) not null,
        DBVERSION_ NUMERIC(18) not null,
        USERID_ VARCHAR(254),
        TIME_ DATETIME YEAR TO SECOND,
        HPROCI_ NUMERIC(18),
        HPROCIIDX_ NUMERIC(18),
        HACTI_ NUMERIC(18),
        HACTIIDX_ NUMERIC(18),
        HTASK_ NUMERIC(18),
        HTASKIDX_ NUMERIC(18),
        HVAR_ NUMERIC(18),
        HVARIDX_ NUMERIC(18),
        MESSAGE_ text,
        OLD_STR_ VARCHAR(254),
        NEW_STR_ VARCHAR(254),
        OLD_INT_ NUMERIC(18),
        NEW_INT_ NUMERIC(18),
        OLD_TIME_ DATETIME YEAR TO SECOND,
        NEW_TIME_ DATETIME YEAR TO SECOND,
        PARENT_ NUMERIC(18),
        PARENT_IDX_ NUMERIC(18),
        primary key (DBID_)
    ) ;

    create table JBPM4_HIST_PROCINST (
        DBID_ NUMERIC(18) not null,
        DBVERSION_ NUMERIC(18) not null,
        ID_ VARCHAR(254),
        PROCDEFID_ VARCHAR(254),
        KEY_ VARCHAR(254),
        START_ DATETIME YEAR TO SECOND,
        END_ DATETIME YEAR TO SECOND,
        DURATION_ NUMERIC(18),
        STATE_ VARCHAR(254),
        ENDACTIVITY_ VARCHAR(254),
        NEXTIDX_ NUMERIC(18),
        primary key (DBID_)
    ) ;

    create table JBPM4_HIST_TASK (
        DBID_ NUMERIC(18) not null,
        DBVERSION_ NUMERIC(18) not null,
        EXECUTION_ VARCHAR(254),
        OUTCOME_ VARCHAR(254),
        ASSIGNEE_ VARCHAR(254),
        PRIORITY_ NUMERIC(18),
        STATE_ VARCHAR(254),
        CREATE_ DATETIME YEAR TO SECOND,
        END_ DATETIME YEAR TO SECOND,
        DURATION_ NUMERIC(18),
        NEXTIDX_ NUMERIC(18),
        SUPERTASK_ NUMERIC(18),
        primary key (DBID_)
    ) ;

    create table JBPM4_HIST_VAR (
        DBID_ NUMERIC(18) not null,
        DBVERSION_ NUMERIC(18) not null,
        PROCINSTID_ VARCHAR(254),
        EXECUTIONID_ VARCHAR(254),
        VARNAME_ VARCHAR(254),
        VALUE_ VARCHAR(254),
        HPROCI_ NUMERIC(18),
        HTASK_ NUMERIC(18),
        primary key (DBID_)
    ) ;

    create table JBPM4_ID_GROUP (
        DBID_ NUMERIC(18) not null,
        DBVERSION_ NUMERIC(18) not null,
        ID_ VARCHAR(254),
        NAME_ VARCHAR(254),
        TYPE_ VARCHAR(254),
        PARENT_ NUMERIC(18),
        primary key (DBID_)
    ) ;

    create table JBPM4_ID_MEMBERSHIP (
        DBID_ NUMERIC(18) not null,
        DBVERSION_ NUMERIC(18) not null,
        USER_ NUMERIC(18),
        GROUP_ NUMERIC(18),
        NAME_ VARCHAR(254),
        primary key (DBID_)
    ) ;

    create table JBPM4_ID_USER (
        DBID_ NUMERIC(18) not null,
        DBVERSION_ NUMERIC(18) not null,
        ID_ VARCHAR(254),
        PASSWORD_ VARCHAR(254),
        GIVENNAME_ VARCHAR(254),
        FAMILYNAME_ VARCHAR(254),
        BUSINESSEMAIL_ VARCHAR(254),
        primary key (DBID_)
    ) ;

    create table JBPM4_JOB (
        DBID_ NUMERIC(18) not null,
        CLASS_ VARCHAR(254) not null,
        DBVERSION_ NUMERIC(18) not null,
        DUEDATE_ DATETIME YEAR TO SECOND,
        STATE_ VARCHAR(254),
        ISEXCLUSIVE_ NUMERIC(18),
        LOCKOWNER_ VARCHAR(254),
        LOCKEXPTIME_ DATETIME YEAR TO SECOND,
        EXCEPTION_ text,
        RETRIES_ NUMERIC(18),
        PROCESSINSTANCE_ NUMERIC(18),
        EXECUTION_ NUMERIC(18),
        CFG_ NUMERIC(18),
        SIGNAL_ VARCHAR(254),
        EVENT_ VARCHAR(254),
        REPEAT_ VARCHAR(254),
        primary key (DBID_)
    ) ;

    create table JBPM4_LOB (
        DBID_ NUMERIC(18) not null,
        DBVERSION_ NUMERIC(18) not null,
        BLOB_VALUE_ blob,
        DEPLOYMENT_ NUMERIC(18),
        NAME_ VARCHAR(200),
        primary key (DBID_)
    ) ;

    create table JBPM4_PARTICIPATION (
        DBID_ NUMERIC(18) not null,
        DBVERSION_ NUMERIC(18) not null,
        GROUPID_ VARCHAR(254),
        USERID_ VARCHAR(254),
        TYPE_ VARCHAR(254),
        TASK_ NUMERIC(18),
        SWIMLANE_ NUMERIC(18),
        primary key (DBID_)
    ) ;

    create table JBPM4_PROPERTY (
        KEY_ VARCHAR(254) not null,
        VERSION_ NUMERIC(18) not null,
        VALUE_ VARCHAR(254),
        primary key (KEY_)
    ) ;

    create table JBPM4_SWIMLANE (
        DBID_ NUMERIC(18) not null,
        DBVERSION_ NUMERIC(18) not null,
        NAME_ VARCHAR(254),
        ASSIGNEE_ VARCHAR(254),
        EXECUTION_ NUMERIC(18),
        primary key (DBID_)
    ) ;

    create table JBPM4_TASK (
        DBID_ NUMERIC(18) not null,
        CLASS_ char(1) not null,
        DBVERSION_ NUMERIC(18) not null,
        NAME_ VARCHAR(254),
        DESCR_ lVARCHAR(512),
        STATE_ VARCHAR(254),
        SUSPHISTSTATE_ VARCHAR(254),
        ASSIGNEE_ VARCHAR(254),
        FORM_ VARCHAR(254),
        PRIORITY_ NUMERIC(18),
        CREATE_ DATETIME YEAR TO SECOND,
        DUEDATE_ DATETIME YEAR TO SECOND,
        PROGRESS_ NUMERIC(18),
        SIGNALLING_ int8,
        EXECUTION_ID_ VARCHAR(254),
        ACTIVITY_NAME_ VARCHAR(254),
        HASVARS_ int8,
        SUPERTASK_ NUMERIC(18),
        EXECUTION_ NUMERIC(18),
        PROCINST_ NUMERIC(18),
        SWIMLANE_ NUMERIC(18),
        TASKDEFNAME_ VARCHAR(254),
        primary key (DBID_)
    ) ;

    create table JBPM4_VARIABLE (
        DBID_ NUMERIC(18) not null,
        CLASS_ VARCHAR(254) not null,
        DBVERSION_ NUMERIC(18) not null,
        KEY_ VARCHAR(254),
        CONVERTER_ VARCHAR(254),
        HIST_ int8,
        EXECUTION_ NUMERIC(18),
        TASK_ NUMERIC(18),
        LOB_ NUMERIC(18),
        DATE_VALUE_ DATETIME YEAR TO SECOND,
        DOUBLE_VALUE_ double precision,
        CLASSNAME_ VARCHAR(254),
        LONG_VALUE_ NUMERIC(18),
        STRING_VALUE_ VARCHAR(254),
        TEXT_VALUE_ text,
        EXESYS_ NUMERIC(18),
        primary key (DBID_)
    ) ;


   create index IDX_DEPLPROP_DEPL on JBPM4_DEPLOYPROP (DEPLOYMENT_);

    alter table JBPM4_DEPLOYPROP 
        add constraint foreign key (DEPLOYMENT_) 
        references JBPM4_DEPLOYMENT (DBID_)
        constraint FK_DEPLPROP_DEPL;

    create index IDX_EXEC_SUPEREXEC on JBPM4_EXECUTION (SUPEREXEC_);

    create index IDX_EXEC_INSTANCE on JBPM4_EXECUTION (INSTANCE_);

    create index IDX_EXEC_SUBPI on JBPM4_EXECUTION (SUBPROCINST_);

    create index IDX_EXEC_PARENT on JBPM4_EXECUTION (PARENT_);

    alter table JBPM4_EXECUTION 
        add constraint foreign key (PARENT_) 
        references JBPM4_EXECUTION (DBID_)
        constraint FK_EXEC_PARENT;

    alter table JBPM4_EXECUTION 
        add constraint foreign key (SUBPROCINST_) 
        references JBPM4_EXECUTION (DBID_)
        constraint FK_EXEC_SUBPI;

    alter table JBPM4_EXECUTION 
        add constraint foreign key (INSTANCE_) 
        references JBPM4_EXECUTION (DBID_)
        constraint FK_EXEC_INSTANCE;

    alter table JBPM4_EXECUTION 
        add constraint foreign key (SUPEREXEC_) 
        references JBPM4_EXECUTION (DBID_)
        constraint FK_EXEC_SUPEREXEC;

    create index IDX_HACTI_HPROCI on JBPM4_HIST_ACTINST (HPROCI_);

    create index IDX_HTI_HTASK on JBPM4_HIST_ACTINST (HTASK_);

    alter table JBPM4_HIST_ACTINST 
        add constraint foreign key (HPROCI_) 
        references JBPM4_HIST_PROCINST (DBID_)
        constraint FK_HACTI_HPROCI;

    alter table JBPM4_HIST_ACTINST 
        add constraint foreign key (HTASK_) 
        references JBPM4_HIST_TASK (DBID_)
        constraint FK_HTI_HTASK;

    create index IDX_HDET_HACTI on JBPM4_HIST_DETAIL (HACTI_);

    create index IDX_HDET_HPROCI on JBPM4_HIST_DETAIL (HPROCI_);

    create index IDX_HDET_HVAR on JBPM4_HIST_DETAIL (HVAR_);

    create index IDX_HDET_HTASK on JBPM4_HIST_DETAIL (HTASK_);

    alter table JBPM4_HIST_DETAIL 
        add constraint foreign key (HPROCI_) 
        references JBPM4_HIST_PROCINST (DBID_)
        constraint FK_HDETAIL_HPROCI;

    alter table JBPM4_HIST_DETAIL 
        add constraint foreign key (HACTI_) 
        references JBPM4_HIST_ACTINST (DBID_)
        constraint FK_HDETAIL_HACTI;

    alter table JBPM4_HIST_DETAIL 
        add constraint foreign key (HTASK_) 
        references JBPM4_HIST_TASK (DBID_)
        constraint FK_HDETAIL_HTASK;

    alter table JBPM4_HIST_DETAIL 
        add constraint foreign key (HVAR_) 
        references JBPM4_HIST_VAR (DBID_)
        constraint FK_HDETAIL_HVAR;

    create index IDX_HSUPERT_SUB on JBPM4_HIST_TASK (SUPERTASK_);

    alter table JBPM4_HIST_TASK 
        add constraint foreign key (SUPERTASK_) 
        references JBPM4_HIST_TASK (DBID_)
        constraint FK_HSUPERT_SUB;

    create index IDX_HVAR_HPROCI on JBPM4_HIST_VAR (HPROCI_);

    create index IDX_HVAR_HTASK on JBPM4_HIST_VAR (HTASK_);

    alter table JBPM4_HIST_VAR 
        add constraint foreign key (HPROCI_) 
        references JBPM4_HIST_PROCINST (DBID_)
        constraint FK_HVAR_HPROCI;

    alter table JBPM4_HIST_VAR 
        add constraint foreign key (HTASK_) 
        references JBPM4_HIST_TASK (DBID_)
        constraint FK_HVAR_HTASK;

    create index IDX_GROUP_PARENT on JBPM4_ID_GROUP (PARENT_);

    alter table JBPM4_ID_GROUP 
        add constraint foreign key (PARENT_) 
        references JBPM4_ID_GROUP (DBID_)
        constraint FK_GROUP_PARENT;

    create index IDX_MEM_USER on JBPM4_ID_MEMBERSHIP (USER_);

    create index IDX_MEM_GROUP on JBPM4_ID_MEMBERSHIP (GROUP_);

    alter table JBPM4_ID_MEMBERSHIP 
        add constraint foreign key (GROUP_) 
        references JBPM4_ID_GROUP (DBID_)
        constraint FK_MEM_GROUP;

    alter table JBPM4_ID_MEMBERSHIP 
        add constraint foreign key (USER_) 
        references JBPM4_ID_USER (DBID_)
        constraint FK_MEM_USER;

    create index IDX_JOBRETRIES on JBPM4_JOB (RETRIES_);

    create index IDX_JOB_CFG on JBPM4_JOB (CFG_);

    create index IDX_JOB_PRINST on JBPM4_JOB (PROCESSINSTANCE_);

    create index IDX_JOB_EXE on JBPM4_JOB (EXECUTION_);

    create index IDX_JOBLOCKEXP on JBPM4_JOB (LOCKEXPTIME_);

    create index IDX_JOBDUEDATE on JBPM4_JOB (DUEDATE_);

    alter table JBPM4_JOB 
        add constraint foreign key (CFG_) 
        references JBPM4_LOB (DBID_)
        constraint FK_JOB_CFG;

    create index IDX_LOB_DEPLOYMENT on JBPM4_LOB (DEPLOYMENT_);

    alter table JBPM4_LOB 
        add constraint foreign key (DEPLOYMENT_) 
        references JBPM4_DEPLOYMENT (DBID_)
        constraint FK_LOB_DEPLOYMENT;

    create index IDX_PART_TASK on JBPM4_PARTICIPATION (TASK_);

    alter table JBPM4_PARTICIPATION 
        add constraint foreign key (SWIMLANE_) 
        references JBPM4_SWIMLANE (DBID_)
        constraint FK_PART_SWIMLANE;

    alter table JBPM4_PARTICIPATION 
        add constraint foreign key (TASK_) 
        references JBPM4_TASK (DBID_)
        constraint FK_PART_TASK;

    create index IDX_SWIMLANE_EXEC on JBPM4_SWIMLANE (EXECUTION_);

    alter table JBPM4_SWIMLANE 
        add constraint foreign key (EXECUTION_) 
        references JBPM4_EXECUTION (DBID_)
        constraint FK_SWIMLANE_EXEC;

    create index IDX_TASK_SUPERTASK on JBPM4_TASK (SUPERTASK_);

    alter table JBPM4_TASK 
        add constraint foreign key (SWIMLANE_) 
        references JBPM4_SWIMLANE (DBID_)
        constraint FK_TASK_SWIML;

    alter table JBPM4_TASK 
        add constraint foreign key (SUPERTASK_) 
        references JBPM4_TASK (DBID_)
        constraint FK_TASK_SUPERTASK;

    create index IDX_VAR_EXESYS on JBPM4_VARIABLE (EXESYS_);

    create index IDX_VAR_TASK on JBPM4_VARIABLE (TASK_);

    create index IDX_VAR_EXECUTION on JBPM4_VARIABLE (EXECUTION_);

    create index IDX_VAR_LOB on JBPM4_VARIABLE (LOB_);

    alter table JBPM4_VARIABLE 
        add constraint foreign key (LOB_) 
        references JBPM4_LOB (DBID_)
        constraint FK_VAR_LOB;

    alter table JBPM4_VARIABLE 
        add constraint foreign key (EXECUTION_) 
        references JBPM4_EXECUTION (DBID_)
        constraint FK_VAR_EXECUTION;

    alter table JBPM4_VARIABLE 
        add constraint foreign key (EXESYS_) 
        references JBPM4_EXECUTION (DBID_)
        constraint FK_VAR_EXESYS;

    alter table JBPM4_VARIABLE 
        add constraint foreign key (TASK_) 
        references JBPM4_TASK (DBID_)
        constraint FK_VAR_TASK;
        
create sequence hibernate_sequence;