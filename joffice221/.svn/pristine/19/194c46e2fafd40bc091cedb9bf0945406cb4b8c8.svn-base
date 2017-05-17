--添加字段的状态 1为删除状态
alter table form_field add status smallint;
--添加字段用于保存流程实例的表单HTML
alter table process_run add  defHtml  CLOB;
--add by lyy 2012-2-21 --

alter table process_run add  PDID  VARCHAR2(64);
alter table process_run add processName VARCHAR2(128);
alter table process_run add piDbid number(18);

alter table pro_definition add PDID VARCHAR2(64);
alter table pro_definition add DEFKEY VARCHAR2(64);
-- add by csx 2012-01-05 --



--用于mysql和SqlServer2005的脚本--
alter table process_run add  PDID  VARCHAR(64);
alter table process_run add processName VARCHAR(128);
alter table process_run add piDbid bigint;

alter table pro_definition add PDID VARCHAR(64);
alter table pro_definition add DEFKEY VARCHAR(64);
--add by hcy 2012-01-09


--add by 2012-01-17
alter table organization add sn int default 0;

--add by cjj 2012-07-17 mysql
alter table position add sn int default 0;
--add by xianggang 2013-07-25 oracle
alter table news add orgIds VARCHAR(128);
alter table news add orgNames VARCHAR(256);

--add by hcy 2014-01-04
alter table  pro_user_set add strategy smallint;