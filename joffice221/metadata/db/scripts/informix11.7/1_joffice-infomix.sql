create table app_function  (
  functionId           NUMERIC(18)                          not null,
  funKey               VARCHAR(64)                     not null,
  funName              VARCHAR(128)                    not null,
primary key (functionId)
      constraint PK_APP_FUNCTION,
unique (funKey)
      constraint AK_UQ_RSK_APP_FUNC
);

create table app_role  (
  roleId               NUMERIC(18)                          not null,
  roleName             VARCHAR(128)                    not null,
  roleDesc             VARCHAR(128),
  status               SMALLINT                        not null,
  rights               lvarchar(9000),
  isDefaultIn          SMALLINT                        not null,
primary key (roleId)
      constraint PK_APP_ROLE
);

create table app_tips  (
  tipsId               NUMERIC(18)                          not null,
  userId               NUMERIC(18)                     not null,
  tipsName             VARCHAR(128),
  content              TEXT,
  disheight            decimal(18,0),
  diswidth             decimal(18,0),
  disleft              decimal(18,0),
  distop               decimal(18,0),
  dislevel             decimal(18,0),
  createtime           DATETIME YEAR TO SECOND         not null,
primary key (tipsId)
      constraint PK_APP_TIPS
);

create table app_user  (
  userId               NUMERIC(18)                          not null,
  username             VARCHAR(128)                    not null,
  title                SMALLINT                        not null,
  depId                NUMERIC(18),
  password             VARCHAR(128)                    not null,
  email                VARCHAR(128)                    not null,
  jobId                decimal(18,0),
  phone                VARCHAR(32),
  mobile               VARCHAR(32),
  fax                  VARCHAR(32),
  address              VARCHAR(64),
  zip                  VARCHAR(32),
  photo                VARCHAR(128),
  accessionTime        DATETIME YEAR TO SECOND         not null,
  status               SMALLINT                        not null,
  education            VARCHAR(64),
  fullname             VARCHAR(50)                     not null,
  delFlag              SMALLINT                        not null,
primary key (userId)
      constraint PK_APP_USER
);

create table appointment  (
  appointId            NUMERIC(18)                          not null,
  userId               NUMERIC(18),
  subject              VARCHAR(128)                    not null,
  startTime            DATETIME YEAR TO SECOND         not null,
  endTime              DATETIME YEAR TO SECOND         not null,
  content              TEXT                            not null,
  notes                TEXT,
  location             VARCHAR(150)                    not null,
  inviteEmails         TEXT,
  sendMessage          SMALLINT,
  sendMail             SMALLINT,
primary key (appointId)
      constraint PK_APPOINTMENT
);

create table arch_dispatch  (
  dispatchId           NUMERIC(18)                          not null,
  archivesId           NUMERIC(18),
  dispatchTime         DATETIME YEAR TO SECOND         not null,
  userId               decimal(18,0)                     not null,
  fullname             VARCHAR(128),
  isRead               SMALLINT,
  subject              VARCHAR(254),
  readFeedback         TEXT,
  archUserType         SMALLINT                       default 0 not null,
  disRoleId            decimal(18,0),
  disRoleName          VARCHAR(64),
primary key (dispatchId)
      constraint PK_ARCH_DISPATCH
);

create table arch_flow_conf  (
  configId             NUMERIC(18)                          not null,
  defId                NUMERIC(18),
  processName          VARCHAR(128)                    not null,
  archType             SMALLINT                        not null,
  depId                decimal(18,0),
primary key (configId)
      constraint PK_ARCH_FLOW_CONF
);

create table arch_fond  (
  archFondId           NUMERIC(18)                          not null,
  afNo                 VARCHAR(64)                     not null,
  afName               VARCHAR(128)                    not null,
  shortDesc            TEXT,
  descp                TEXT,
  clearupDesc          TEXT,
  createTime           DATETIME YEAR TO SECOND,
  updateTime           DATETIME YEAR TO SECOND,
  creatorName          VARCHAR(32),
  creatorId            NUMERIC(18),
  caseNums             bigint,
  status               SMALLINT,
  proTypeId            NUMERIC(18),
  typeName             VARCHAR(128),
  openStyle            VARCHAR(64),
primary key (archFondId)
      constraint PK_ARCH_FOND
);

create table arch_hasten  (
  recordId             NUMERIC(18)                          not null,
  archivesId           NUMERIC(18),
  content              TEXT,
  createtime           DATETIME YEAR TO SECOND,
  hastenFullname       VARCHAR(64),
  handlerFullname      VARCHAR(64),
  handlerUserId        NUMERIC(18),
primary key (recordId)
      constraint PK_ARCH_HASTEN
);

create table arch_roll  (
  rollId               NUMERIC(18)                          not null,
  archFondId           NUMERIC(18),
  proTypeId            NUMERIC(18),
  typeName             VARCHAR(32),
  rolllName            VARCHAR(128)                    not null,
  afNo                 VARCHAR(64)                     not null,
  rollNo               VARCHAR(64)                     not null,
  catNo                VARCHAR(64),
  timeLimit            VARCHAR(64),
  startTime            DATETIME YEAR TO SECOND,
  endTime              DATETIME YEAR TO SECOND,
  openStyle            VARCHAR(64),
  author               VARCHAR(32),
  setupTime            DATETIME YEAR TO SECOND,
  checker              VARCHAR(32),
  creatorName          VARCHAR(32),
  createTime           DATETIME YEAR TO SECOND,
  keyWords             TEXT,
  editCompany          VARCHAR(128),
  editDep              VARCHAR(128),
  decp                 TEXT,
  status               SMALLINT,
primary key (rollId)
      constraint PK_ARCH_ROLL
);

create table arch_template  (
  templateId           NUMERIC(18)                          not null,
  fileId               NUMERIC(18),
  proTypeId            NUMERIC(18),
  tempName             VARCHAR(128)                    not null,
  tempPath             VARCHAR(254)                    not null,
primary key (templateId)
      constraint PK_ARCH_TEMPLATE
);

create table archives  (
  archivesId           NUMERIC(18)                          not null,
  typeName             VARCHAR(128),
  archivesNo           VARCHAR(100)                    not null,
  issueDep             VARCHAR(128),
  proTypeId            NUMERIC(18),
  glo_proTypeId        NUMERIC(18),
  subject              VARCHAR(254)                    not null,
  createtime           DATETIME YEAR TO SECOND         not null,
  issueDate            DATETIME YEAR TO SECOND         not null,
  status               VARCHAR(254)                    not null,
  shortContent         TEXT,
  fileCounts           bigint                         default 0,
  privacyLevel         VARCHAR(50)                    ,
  urgentLevel          VARCHAR(50)                   ,
  issuer               VARCHAR(50),
  issuerId             NUMERIC(18),
  keywords             VARCHAR(254),
  sources              VARCHAR(50),
  archType             SMALLINT                       default 0 not null,
  recDepIds            TEXT,
  recDepNames          TEXT,
  handlerUids          VARCHAR(254),
  handlerUnames        VARCHAR(254),
  orgArchivesId        bigint,
  depSignNo            VARCHAR(100),
  runId                bigint,
  archStatus           SMALLINT,
  depId                bigint,
primary key (archivesId)
      constraint PK_ARCHIVES
);

create table archives_dep  (
  archDepId            NUMERIC(18)                          not null,
  signNo               VARCHAR(128),
  archivesId           NUMERIC(18)                     not null,
  subject              VARCHAR(254)                    not null,
  status               SMALLINT                        not null,
  signTime             DATETIME YEAR TO SECOND,
  signFullname         VARCHAR(64),
  signUserID           NUMERIC(18),
  handleFeedback       lvarchar(32000),
  isMain               SMALLINT                       default 1 not null,
  depId                NUMERIC(18),
primary key (archDepId)
      constraint PK_ARCHIVES_DEP
);

create table archives_doc  (
  docId                NUMERIC(18)                          not null,
  archivesId           NUMERIC(18),
  fileId               NUMERIC(18),
  creator              VARCHAR(64),
  creatorId            NUMERIC(18),
  menderId             NUMERIC(18),
  mender               VARCHAR(64),
  docName              VARCHAR(128)                    not null,
  docStatus            SMALLINT                        not null,
  curVersion           bigint                     not null,
  docPath              VARCHAR(128)                    not null,
  updatetime           DATETIME YEAR TO SECOND         not null,
  createtime           DATETIME YEAR TO SECOND         not null,
primary key (docId)
      constraint PK_ARCHIVES_DOC
);


create table assets_type  (
  assetsTypeId         NUMERIC(18)                          not null,
  typeName             VARCHAR(128)                    not null,
primary key (assetsTypeId)
      constraint PK_ASSETS_TYPE
);

create table board_type  (
  typeId               NUMERIC(18)                          not null,
  typeName             VARCHAR(128)                    not null,
  typeDesc             lvarchar(32000)                   not null,
primary key (typeId)
      constraint PK_BOARD_TYPE
);

create table boardroo  (
  roomId               NUMERIC(18)                          not null,
  roomName             VARCHAR(128)                    not null,
  roomDesc             lvarchar(32000),
  containNum           decimal(18,0)                    default 0,
primary key (roomId)
      constraint PK_BOARDROO
);

create table book  (
  bookId               NUMERIC(18)                          not null,
  typeId               NUMERIC(18),
  bookName             VARCHAR(128)                    not null,
  author               VARCHAR(128)                    not null,
  isbn                 VARCHAR(64)                     not null,
  publisher            VARCHAR(128),
  price                NUMERIC                         not null,
  location             VARCHAR(128)                    not null,
  department           VARCHAR(64)                     not null,
  amount               NUMERIC(18)                     not null,
  leftAmount           NUMERIC(18)                     not null,
primary key (bookId)
      constraint PK_BOOK
);

create table book_bor_ret  (
  recordId             NUMERIC(18)                          not null,
  bookSnId             NUMERIC(18),
  borrowTime           DATETIME YEAR TO SECOND         not null,
  returnTime           DATETIME YEAR TO SECOND         not null,
  lastReturnTime       DATETIME YEAR TO SECOND,
  borrowIsbn           VARCHAR(128)                    not null,
  bookName             VARCHAR(128)                    not null,
  registerName         VARCHAR(32)                     not null,
  fullname             VARCHAR(32)                     not null,
primary key (recordId)
      constraint PK_BOOK_BOR_RET
);

create table book_sn  (
  bookSnId             NUMERIC(18)                          not null,
  bookId               NUMERIC(18)                     not null,
  bookSN               VARCHAR(128)                    not null,
  status               SMALLINT                        not null,
primary key (bookSnId)
      constraint PK_BOOK_SN
);


create table book_type  (
  typeId               NUMERIC(18)                          not null,
  typeName             VARCHAR(128)                    not null,
primary key (typeId)
      constraint PK_BOOK_TYPE
);

create table borrow_file_list  (
  listId               NUMERIC(18)                          not null,
  recordId             NUMERIC(18)                     not null,
  listType             VARCHAR(64),
  archFondId           NUMERIC(18),
  afNo                 VARCHAR(64),
  afName               VARCHAR(128),
  rollId               NUMERIC(18),
  rollNo               VARCHAR(64),
  rolllName            VARCHAR(128),
  rollFileId           NUMERIC(18),
  fileNo               VARCHAR(64),
  fileName             VARCHAR(128),
primary key (listId)
      constraint PK_BORROW_FILE_LIS
);

create table borrow_record  (
  recordId             NUMERIC(18)                          not null,
  borrowDate           DATETIME YEAR TO SECOND,
  borrowType           VARCHAR(64),
  borrowReason         VARCHAR(64),
  checkUserId          NUMERIC(18),
  checkUserName        VARCHAR(64),
  checkDate            DATETIME YEAR TO SECOND,
  returnDate           DATETIME YEAR TO SECOND,
  borrowNum            VARCHAR(128),
  borrowRemark         VARCHAR(128),
  returnStatus         SMALLINT,
  checkId              NUMERIC(18),
  checkName            VARCHAR(64),
  checkContent         VARCHAR(128),
primary key (recordId)
      constraint PK_BORROW_RECORD
);


create table cal_file  (
  fileId               NUMERIC(18)                     not null,
  planId               NUMERIC(18)                     not null,
primary key (fileId, planId)
      constraint PK_CAL_FILE
);


create table calendar_plan  (
  planId               NUMERIC(18)                          not null,
  startTime            DATETIME YEAR TO SECOND,
  endTime              DATETIME YEAR TO SECOND,
  urgent               SMALLINT                        not null,
  summary              VARCHAR(200),
  content              TEXT                            not null,
  status               SMALLINT                        not null,
  userId               NUMERIC(18)                     not null,
  fullname             VARCHAR(32),
  assignerId           NUMERIC(18)                     not null,
  assignerName         VARCHAR(32),
  feedback             lvarchar(32000),
  showStyle            SMALLINT                        not null,
  taskType             SMALLINT                        not null,
primary key (planId)
      constraint PK_CALENDAR_PLAN
);


create table car  (
  carId                NUMERIC(18)                          not null,
  carNo                VARCHAR(128)                    not null,
  carType              VARCHAR(64)                     not null,
  engineNo             VARCHAR(128),
  buyInsureTime        DATETIME YEAR TO SECOND,
  auditTime            DATETIME YEAR TO SECOND,
  notes                TEXT,
  factoryModel         VARCHAR(64)                     not null,
  driver               VARCHAR(32)                     not null,
  buyDate              DATETIME YEAR TO SECOND         not null,
  status               SMALLINT                        not null,
  cartImage            VARCHAR(128),
primary key (carId)
      constraint PK_CAR
);


create table car_apply  (
  applyId              NUMERIC(18)                          not null,
  carId                NUMERIC(18)                     not null,
  department           VARCHAR(64)                     not null,
  userFullname         VARCHAR(32)                     not null,
  applyDate            DATETIME YEAR TO SECOND         not null,
  reason               TEXT                    not null,
  startTime            DATETIME YEAR TO SECOND         not null,
  endTime              DATETIME YEAR TO SECOND,
  userId               NUMERIC(18)                     not null,
  proposer             VARCHAR(32)                     not null,
  mileage              NUMERIC(18,2),
  oilUse               NUMERIC(18,2),
  notes                VARCHAR(128),
  approvalStatus       SMALLINT                        not null,
primary key (applyId)
      constraint PK_CAR_APPLY
);

create table cart_repair  (
  repairId             NUMERIC(18)                          not null,
  carId                NUMERIC(18),
  repairDate           DATETIME YEAR TO SECOND         not null,
  reason               VARCHAR(128)                    not null,
  executant            VARCHAR(128)                    not null,
  notes                VARCHAR(128),
  repairType           VARCHAR(128)                    not null,
  fee                  NUMERIC(18,2),
primary key (repairId)
      constraint PK_CART_REPAIR
);

create table company  (
  companyId            NUMERIC(18)                          not null,
  companyNo            VARCHAR(128),
  companyName          VARCHAR(128)                    not null,
  companyDesc          TEXT,
  legalPerson          VARCHAR(32),
  setup                DATETIME YEAR TO SECOND,
  phone                VARCHAR(32),
  fax                  VARCHAR(32),
  site                 VARCHAR(128),
  logo                 VARCHAR(128),
primary key (companyId)
      constraint PK_COMPANY
);


create table conf_attach  (
  confId               NUMERIC(18),
  fileId               NUMERIC(18)
);

create table conf_attend  (
  attendId             NUMERIC(18)                          not null,
  confId               NUMERIC(18),
  userId               NUMERIC(18),
  userType             SMALLINT                       default 3,
  fullname             VARCHAR(50),
primary key (attendId)
      constraint PK_CONF_ATTEND
);

create table conf_privilege  (
  privilegeId          NUMERIC(18)                          not null,
  confId               NUMERIC(18),
  userId               NUMERIC(18)                     not null,
  fullname             VARCHAR(64)                     not null,
  rights               SMALLINT                        not null,
primary key (privilegeId)
      constraint PK_CONF_PRIVILEGE
);

create table conf_sum_attach  (
  sumId                NUMERIC(18),
  fileId               NUMERIC(18)
);

create table conf_summary  (
  sumId                NUMERIC(18)                          not null,
  confId               NUMERIC(18),
  createtime           DATETIME YEAR TO SECOND         not null,
  creator              VARCHAR(32)                     not null,
  sumContent           TEXT                            not null,
  status               SMALLINT,
primary key (sumId)
      constraint PK_CONF_SUMMARY
);


create table conference  (
  confId               NUMERIC(18)                          not null,
  confTopic            VARCHAR(128)                    not null,
  confProperty         VARCHAR(64),
  importLevel          SMALLINT                        not null,
  feeBudget            NUMERIC(18,2),
  compereName          VARCHAR(254),
  compere              VARCHAR(128),
  recorder             VARCHAR(128),
  recorderName         VARCHAR(254),
  attendUsers          text,
  attendUsersName      TEXT,
  status               SMALLINT                        not null,
  isEmail              SMALLINT,
  isMobile             SMALLINT,
  startTime            DATETIME YEAR TO SECOND         not null,
  endTime              DATETIME YEAR TO SECOND         not null,
  roomId               NUMERIC(18),
  typeId               NUMERIC(18),
  roomName             VARCHAR(64),
  roomLocation         VARCHAR(128),
  confContent          TEXT,
  createtime           DATETIME YEAR TO SECOND,
  sendtime             DATETIME YEAR TO SECOND,
  checkReason          TEXT,
  checkUserId          NUMERIC(18),
  checkName            VARCHAR(64),
primary key (confId)
      constraint PK_CONFERENCE
);


create table contract  (
  contractId           NUMERIC(18)                          not null,
  contractNo           VARCHAR(64)                     not null,
  subject              VARCHAR(128)                    not null,
  contractAmount       NUMERIC                         not null,
  mainItem             TEXT,
  salesAfterItem       TEXT,
  validDate            DATETIME YEAR TO SECOND         not null,
  expireDate           DATETIME YEAR TO SECOND         not null,
  serviceDep           VARCHAR(64),
  serviceMan           VARCHAR(64),
  signupUser           VARCHAR(64)                     not null,
  signupTime           DATETIME YEAR TO SECOND         not null,
  creator              VARCHAR(32)                     not null,
  createtime           DATETIME YEAR TO SECOND         not null,
  projectId            NUMERIC(18),
  consignAddress       VARCHAR(128),
  consignee            VARCHAR(32),
primary key (contractId)
      constraint PK_CONTRACT
);


create table contract_config  (
  configId             NUMERIC(18)                          not null,
  itemName             VARCHAR(128)                    not null,
  contractId           NUMERIC(18),
  itemSpec             VARCHAR(128)                    not null,
  amount               NUMERIC(18,2)                   not null,
  notes                VARCHAR(200),
primary key (configId)
      constraint PK_CONTRACT_CONFIG
);


create table contract_file  (
  fileId               NUMERIC(18)                     not null,
  contractId           NUMERIC(18)                     not null,
primary key (fileId, contractId)
      constraint PK_CONTRACT_FILE
);



create table cus_connection  (
  connId               NUMERIC(18)                          not null,
  customerId           NUMERIC(18)                     not null,
  contactor            VARCHAR(32)                     not null,
  startDate            DATETIME YEAR TO SECOND         not null,
  endDate              DATETIME YEAR TO SECOND         not null,
  content              TEXT                    not null,
  notes                TEXT,
  creator              VARCHAR(32),
primary key (connId)
      constraint PK_CUS_CONNECTION
);


create table cus_linkman  (
  linkmanId            NUMERIC(18)                          not null,
  customerId           NUMERIC(18)                     not null,
  fullname             VARCHAR(32)                     not null,
  sex                  SMALLINT                        not null,
  position             VARCHAR(32),
  phone                VARCHAR(32),
  mobile               VARCHAR(32)                     not null,
  fax                  VARCHAR(32),
  email                VARCHAR(100),
  msn                  VARCHAR(100),
  qq                   VARCHAR(64),
  birthday             DATETIME YEAR TO SECOND,
  homeAddress          VARCHAR(128),
  homeZip              VARCHAR(32),
  homePhone            VARCHAR(32),
  hobby                VARCHAR(100),
  isPrimary            SMALLINT                        not null,
  notes                TEXT,
primary key (linkmanId)
      constraint PK_CUS_LINKMAN
);


create table customer  (
  customerId           NUMERIC(18)                          not null,
  customerNo           VARCHAR(64)                     not null,
  industryType         VARCHAR(64)                     not null,
  customerSource       VARCHAR(64),
  customerType         SMALLINT                        not null,
  companyScale         NUMERIC(18),
  customerName         VARCHAR(128)                    not null,
  customerManager      VARCHAR(32)                     not null,
  phone                VARCHAR(32)                     not null,
  fax                  VARCHAR(32),
  site                 VARCHAR(128),
  email                VARCHAR(128),
  state                VARCHAR(32),
  city                 VARCHAR(32),
  zip                  VARCHAR(32),
  address              VARCHAR(100),
  registerFun          NUMERIC,
  turnOver             NUMERIC,
  currencyUnit         VARCHAR(32),
  otherDesc            TEXT,
  principal            VARCHAR(32),
  openBank             VARCHAR(64),
  accountsNo           VARCHAR(64),
  taxNo                VARCHAR(64),
  notes                TEXT,
  rights               SMALLINT                        not null,
primary key (customerId)
      constraint PK_CUSTOMER
);


create table demension  (
  dem_id               NUMERIC(18)                          not null,
  dem_name             VARCHAR(128)                    not null,
  dem_desc             lvarchar(9000),
  dem_type             SMALLINT                        not null,
primary key (dem_id)
      constraint PK_DEMENSION
);


create table dep_pos  (
  org_id               NUMERIC(18)                     not null,
  pos_id               NUMERIC(18)                     not null,
primary key (org_id, pos_id)
      constraint PK_DEP_POS
);

create table depre_record  (
  recordId             NUMERIC(18)                          not null,
  assetsId             NUMERIC(18)                     not null,
  workCapacity         NUMERIC(18,2),
  workGrossUnit        VARCHAR(128),
  depreAmount          NUMERIC(18,4)                   not null,
  calTime              DATETIME YEAR TO SECOND         not null,
primary key (recordId)
      constraint PK_DEPRE_RECORD
);


create table depre_type  (
  depreTypeId          NUMERIC(18)                          not null,
  typeName             VARCHAR(64)                     not null,
  deprePeriod          NUMERIC(18)                     not null,
  typeDesc             TEXT,
  calMethod            SMALLINT                        not null,
primary key (depreTypeId)
      constraint PK_DEPRE_TYPE
);


create table diary  (
  diaryId              NUMERIC(18)                          not null,
  userId               NUMERIC(18),
  dayTime              DATETIME YEAR TO SECOND         not null,
  content              TEXT                            not null,
  diaryType            SMALLINT                        not null,
primary key (diaryId)
      constraint PK_DIARY
);


create table dictionary  (
  dicId                NUMERIC(18)                          not null,
  proTypeId            NUMERIC(18),
  itemName             VARCHAR(64)                     not null,
  itemValue            VARCHAR(128)                    not null,
  descp                VARCHAR(254),
  sn                   NUMERIC(18),
primary key (dicId)
      constraint PK_DICTIONARY
);



create table doc_file  (
  fileId               NUMERIC(18)                     not null,
  docId                NUMERIC(18)                     not null,
primary key (fileId, docId)
      constraint PK_DOC_FILE
);



create table doc_folder  (
  folderId             NUMERIC(18)                          not null,
  userId               NUMERIC(18),
  folderName           VARCHAR(128)                    not null,
  parentId             NUMERIC(18),
  path                 VARCHAR(128),
  isShared             SMALLINT                        not null,
  descp                VARCHAR(254),
primary key (folderId)
      constraint PK_DOC_FOLDER
);


create table doc_history  (
  historyId            NUMERIC(18)                          not null,
  docId                NUMERIC(18)                     not null,
  fileId               NUMERIC(18),
  docName              VARCHAR(128)                    not null,
  path                 VARCHAR(128)                    not null,
  version              NUMERIC(18)                     not null,
  updatetime           DATETIME YEAR TO SECOND         not null,
  mender               VARCHAR(64)                     not null,
primary key (historyId)
      constraint PK_DOC_HISTORY
);



create table doc_privilege  (
  privilegeId          NUMERIC(18)                          not null,
  folderId             NUMERIC(18),
  docId                NUMERIC(18),
  rights               NUMERIC(18)                     not null,
  udrId                NUMERIC(18),
  udrName              VARCHAR(128),
  flag                 SMALLINT                        not null,
  fdFlag               SMALLINT                        not null,
primary key (privilegeId)
      constraint PK_DOC_PRIVILEGE
);



create table document  (
  docId                NUMERIC(18)                          not null,
  docName              VARCHAR(100)                    not null,
  content              TEXT,
  createtime           DATETIME YEAR TO SECOND         not null,
  updatetime           DATETIME YEAR TO SECOND,
  folderId             NUMERIC(18),
  userId               NUMERIC(18),
  fullname             VARCHAR(32)                     not null,
  haveAttach           SMALLINT,
  sharedUserIds        TEXT,
  sharedUserNames      TEXT,
  sharedDepIds         TEXT,
  sharedDepNames       TEXT,
  sharedRoleIds        TEXT,
  sharedRoleNames      TEXT,
  isShared             SMALLINT                        not null,
  author               VARCHAR(64),
  keywords             VARCHAR(254),
  docType              VARCHAR(64),
  swfPath              VARCHAR(254),
primary key (docId)
      constraint PK_DOCUMENT
);



create table download_log  (
  logId                NUMERIC(18)                          not null,
  username             VARCHAR(64)                     not null,
  userId               NUMERIC(18)                     not null,
  fileId               NUMERIC(18)                     not null,
  downloadTIme         DATETIME YEAR TO SECOND         not null,
  notes                TEXT,
primary key (logId)
      constraint PK_DOWNLOAD_LOG
);


create table duty  (
  dutyId               NUMERIC(18)                          not null,
  userId               NUMERIC(18)                     not null,
  fullname             VARCHAR(32)                     not null,
  systemId             NUMERIC(18)                     not null,
  startTime            DATETIME YEAR TO SECOND         not null,
  endTime              DATETIME YEAR TO SECOND,
primary key (dutyId)
      constraint PK_DUTY
);


create table duty_register  (
  registerId           NUMERIC(18)                          not null,
  registerDate         DATETIME YEAR TO SECOND         not null,
  userId               NUMERIC(18)                     not null,
  fullname             VARCHAR(32)                     not null,
  regFlag              SMALLINT                        not null,
  regMins              NUMERIC(18)                     not null,
  reason               VARCHAR(128),
  dayOfWeek            NUMERIC(18)                     not null,
  inOffFlag            SMALLINT                        not null,
  sectionId            NUMERIC(18)                     not null,
primary key (registerId)
      constraint PK_DUTY_REGISTER
);


create table duty_section  (
  sectionId            NUMERIC(18)                          not null,
  sectionName          VARCHAR(64)                     not null,
  startSignin          DATETIME YEAR TO SECOND         not null,
  dutyStartTime        DATETIME YEAR TO SECOND         not null,
  endSignin            DATETIME YEAR TO SECOND         not null,
  earlyOffTime         DATETIME YEAR TO SECOND         not null,
  dutyEndTime          DATETIME YEAR TO SECOND         not null,
  signOutTime          DATETIME YEAR TO SECOND         not null,
primary key (sectionId)
      constraint PK_DUTY_SECTION
);


create table duty_system  (
  systemId             NUMERIC(18)                          not null,
  systemName           VARCHAR(128)                    not null,
  systemSetting        VARCHAR(128)                    not null,
  systemDesc           VARCHAR(254)                    not null,
  isDefault            SMALLINT                        not null,
primary key (systemId)
      constraint PK_DUTY_SYSTEM
);


create table emp_profile  (
  profileId            NUMERIC(18)                          not null,
  profileNo            VARCHAR(100)                    not null,
  userId               NUMERIC(18)                     not null,
  fullname             VARCHAR(64)                     not null,
  address              VARCHAR(128),
  birthday             DATETIME YEAR TO SECOND,
  homeZip              VARCHAR(32),
  sex                  VARCHAR(32),
  marriage             VARCHAR(32),
  designation          VARCHAR(64),
  position             VARCHAR(128)                    not null,
  phone                VARCHAR(64),
  mobile               VARCHAR(64),
  openBank             VARCHAR(100),
  bankNo               VARCHAR(100),
  qq                   VARCHAR(64),
  email                VARCHAR(128),
  hobby                VARCHAR(254),
  religion             VARCHAR(100),
  party                VARCHAR(100),
  nationality          VARCHAR(100),
  race                 VARCHAR(100),
  birthPlace           VARCHAR(128),
  eduDegree            VARCHAR(100),
  eduMajor             VARCHAR(100),
  eduCollege           VARCHAR(128),
  startWorkDate        DATETIME YEAR TO SECOND,
  eduCase              TEXT,
  awardPunishCase      TEXT,
  trainingCase         TEXT,
  workCase             TEXT,
  idCard               VARCHAR(64),
  photo                VARCHAR(128),
  standardMiNo         VARCHAR(100),
  standardMoney        NUMERIC(18,2),
  standardName         VARCHAR(128),
  standardId           NUMERIC(18),
  jobId                NUMERIC(18),
  creator              VARCHAR(64),
  createtime           DATETIME YEAR TO SECOND,
  checkName            VARCHAR(128),
  checktime            DATETIME YEAR TO SECOND,
  opprovalOpinion      TEXT,
  approvalStatus       SMALLINT                       default 0,
  memo                 TEXT,
  depName              VARCHAR(100),
  depId                NUMERIC(18),
  delFlag              SMALLINT                       default 0 not null,
primary key (profileId)
      constraint PK_EMP_PROFILE
);


create table errands_register  (
  dateId               NUMERIC(18)                          not null,
  userId               NUMERIC(18)                     not null,
  descp                TEXT                            not null,
  startTime            DATETIME YEAR TO SECOND         not null,
  endTime              DATETIME YEAR TO SECOND         not null,
  approvalId           NUMERIC(18),
  approvalName         VARCHAR(128),
  status               SMALLINT                        not null,
  approvalOption       TEXT,
  flag                 SMALLINT,
  runId                NUMERIC(18),
primary key (dateId)
      constraint PK_ERRANDS_REGISTE
);


create table field_rights  (
  rightId              NUMERIC(18)                          not null,
  mappingId            NUMERIC(18)                     not null,
  fieldId              NUMERIC(18)                     not null,
  taskName             VARCHAR(128)                    not null,
  readWrite            SMALLINT                       default 0 not null,
primary key (rightId)
      constraint PK_FIELD_RIGHTS
);


create table file_attach  (
  fileId               NUMERIC(18)                          not null,
  fileName             VARCHAR(128)                    not null,
  filePath             VARCHAR(128)                    not null,
  createtime           DATETIME YEAR TO SECOND         not null,
  ext                  VARCHAR(32),
  fileType             VARCHAR(128)                    not null,
  note                 TEXT,
  creatorId            NUMERIC(18),
  creator              VARCHAR(32)                     not null,
  totalBytes           NUMERIC(18)                    default 0,
  delFlag              SMALLINT,
  proTypeId            NUMERIC(18),
primary key (fileId)
      constraint PK_FILE_ATTACH
);


create table fixed_assets  (
  assetsId             NUMERIC(18)                          not null,
  assetsNo             VARCHAR(128),
  assetsName           VARCHAR(128)                    not null,
  model                VARCHAR(64),
  assetsTypeId         NUMERIC(18)                     not null,
  manufacturer         VARCHAR(64),
  manuDate             DATETIME YEAR TO SECOND,
  buyDate              DATETIME YEAR TO SECOND         not null,
  beDep                VARCHAR(64)                     not null,
  custodian            VARCHAR(32),
  notes                TEXT,
  remainValRate        NUMERIC(18,6)                   not null,
  depreTypeId          NUMERIC(18)                     not null,
  startDepre           DATETIME YEAR TO SECOND,
  intendTerm           NUMERIC(18,2),
  intendWorkGross      NUMERIC(18,2),
  workGrossUnit        VARCHAR(128),
  assetValue           NUMERIC(18,4)                   not null,
  assetCurValue        NUMERIC(18,4)                   not null,
  depreRate            NUMERIC(18,2),
  defPerWorkGross      NUMERIC(18,2),
primary key (assetsId)
      constraint PK_FIXED_ASSETS
);


create table form_def  (
  formDefId            NUMERIC(18)                          not null,
  formTitle            VARCHAR(128)                    not null,
  formDesp             varchar(254),
  defHtml              lvarchar(32000),
  status               SMALLINT                        not null,
  formType             SMALLINT,
  isDefault            SMALLINT,
  isGen                SMALLINT                       default 0,
primary key (formDefId)
      constraint PK_FORM_DEF,
unique (formTitle)
      constraint AK_FD_FOR_FORM_DEF
);


create table form_def_mapping  (
  mappingId            NUMERIC(18)                          not null,
  formDefId            NUMERIC(18),
  defId                NUMERIC(18),
  versionNo            NUMERIC(18)                    default 0 not null,
  deployId             VARCHAR(128)                    not null,
  useTemplate          SMALLINT                       default 0,
primary key (mappingId)
      constraint PK_FORM_DEF_MAPPIN,
unique (deployId)
      constraint AK_UK_DEP_FORM_DEF
);


create table form_field  (
  fieldId              NUMERIC(18)                          not null,
  tableId              NUMERIC(18),
  fieldName            VARCHAR(128)                    not null,
  fieldLabel           VARCHAR(128),
  fieldType            VARCHAR(128)                    not null,
  isRequired           SMALLINT,
  fieldSize            NUMERIC(18),
  fieldDscp            lvarchar(30000),
  isPrimary            SMALLINT,
  foreignKey           VARCHAR(64),
  foreignTable         VARCHAR(64),
  isList               SMALLINT                       default 1,
  isQuery              SMALLINT                       default 1,
  showFormat           VARCHAR(254),
  isFlowTitle          SMALLINT,
  isDesignShow         SMALLINT,
  status               SMALLINT,
  decimalLen           NUMERIC(18),
  controlType          NUMERIC(18),
primary key (fieldId)
      constraint PK_FORM_FIELD
);


create table form_rule  (
  ruleId               NUMERIC(18)                          not null,
  name                 VARCHAR(128)                    not null,
  rule                 VARCHAR(128)                    not null,
  tipInfo              VARCHAR(128)                    not null,
  memo                 VARCHAR(254),
primary key (ruleId)
      constraint PK_FORM_RULE
);


create table form_table  (
  tableId              NUMERIC(18)                          not null,
  formDefId            NUMERIC(18),
  tableName            VARCHAR(128)                    not null,
  tableKey             VARCHAR(128)                    not null,
  isMain               SMALLINT,
primary key (tableId)
      constraint PK_FORM_TABLE
);


create table form_template  (
  templateId           NUMERIC(18)                          not null,
  mappingId            NUMERIC(18),
  nodeName             VARCHAR(128)                    not null,
  tempContent          TEXT,
  extDef               TEXT,
  formUrl              VARCHAR(254),
  tempType             SMALLINT,
primary key (templateId)
      constraint PK_FORM_TEMPLATE
);


create table fun_url  (
  urlId                NUMERIC(18)                          not null,
  functionId           NUMERIC(18)                     not null,
  urlPath              VARCHAR(128)                    not null,
primary key (urlId)
      constraint PK_FUN_URL
);


create table global_type  (
  proTypeId            NUMERIC(18)                          not null,
  typeName             VARCHAR(128)                    not null,
  path                 VARCHAR(64),
  depth                NUMERIC(18)                     not null,
  parentId             NUMERIC(18),
  nodeKey              VARCHAR(64)                     not null,
  catKey               VARCHAR(64)                     not null,
  sn                   NUMERIC(18)                     not null,
  userId               NUMERIC(18),
  depId                NUMERIC(18),
primary key (proTypeId)
      constraint PK_GLOBAL_TYPE
);


create table goods_apply  (
  applyId              NUMERIC(18)                          not null,
  goodsId              NUMERIC(18)                     not null,
  applyDate            DATETIME YEAR TO SECOND         not null,
  applyNo              VARCHAR(128)                    not null,
  useCounts            NUMERIC(18)                     not null,
  userId               NUMERIC(18)                     not null,
  proposer             VARCHAR(32)                     not null,
  notes                TEXT,
  approvalStatus       SMALLINT                        not null,
primary key (applyId)
      constraint PK_GOODS_APPLY
);


create table hire_issue  (
  hireId               NUMERIC(18)                          not null,
  title                VARCHAR(128)                    not null,
  startDate            DATETIME YEAR TO SECOND         not null,
  endDate              DATETIME YEAR TO SECOND         not null,
  hireCount            NUMERIC(18)                     not null,
  jobName              VARCHAR(128)                    not null,
  jobCondition         TEXT,
  regFullname          VARCHAR(128)                    not null,
  regDate              DATETIME YEAR TO SECOND         not null,
  modifyFullname       VARCHAR(32),
  modifyDate           DATETIME YEAR TO SECOND,
  checkFullname        VARCHAR(32),
  checkOpinion         TEXT,
  checkDate            DATETIME YEAR TO SECOND,
  status               SMALLINT                        not null,
  memo                 TEXT,
  jobId                NUMERIC(18),
primary key (hireId)
      constraint PK_HIRE_ISSUE
);


create table holiday_record  (
  recordId             NUMERIC(18)                          not null,
  startTime            DATETIME YEAR TO SECOND         not null,
  endTime              DATETIME YEAR TO SECOND         not null,
  descp                TEXT,
  fullname             VARCHAR(32),
  userId               NUMERIC(18),
  isAll                SMALLINT                        not null,
primary key (recordId)
      constraint PK_HOLIDAY_RECORD
);


create table in_message  (
  receiveId            NUMERIC(18)                          not null,
  messageId            NUMERIC(18),
  userId               NUMERIC(18),
  readFlag             SMALLINT                        not null,
  delFlag              SMALLINT                        not null,
  userFullname         VARCHAR(32)                     not null,
primary key (receiveId)
      constraint PK_IN_MESSAGE
);


create table in_stock  (
  buyId                NUMERIC(18)                          not null,
  goodsId              NUMERIC(18)                     not null,
  providerName         VARCHAR(128),
  stockNo              VARCHAR(128)                    not null,
  price                NUMERIC(18,2),
  inCounts             NUMERIC(18),
  amount               NUMERIC(18,2)                   not null,
  inDate               DATETIME YEAR TO SECOND         not null,
  buyer                VARCHAR(128),
primary key (buyId)
      constraint PK_IN_STOCK
);


create table index_display  (
  indexId              NUMERIC(18)                          not null,
  portalId             VARCHAR(64)                     not null,
  userId               NUMERIC(18)                     not null,
  colNum               NUMERIC(18)                     not null,
  rowNo                NUMERIC(18)                     not null,
primary key (indexId)
      constraint PK_INDEX_DISPLAY
);


create table job_change  (
  changeId             NUMERIC(18)                          not null,
  profileId            NUMERIC(18)                     not null,
  profileNo            VARCHAR(64)                     not null,
  userName             VARCHAR(64),
  orgJobName           VARCHAR(64)                     not null,
  newJobName           VARCHAR(64)                     not null,
  orgStandardId        NUMERIC(18),
  orgStandardNo        VARCHAR(64),
  orgStandardName      VARCHAR(64),
  orgDepId             NUMERIC(18),
  orgDepName           VARCHAR(128),
  orgTotalMoney        NUMERIC(18,2),
  newStandardId        NUMERIC(18),
  newStandardNo        VARCHAR(64),
  newStandardName      VARCHAR(64),
  newDepId             NUMERIC(18),
  newDepName           VARCHAR(128),
  newTotalMoney        NUMERIC(18,2),
  changeReason         TEXT,
  regTime              DATETIME YEAR TO SECOND,
  regName              VARCHAR(64),
  checkName            VARCHAR(64),
  checkTime            DATETIME YEAR TO SECOND,
  checkOpinion         TEXT,
  status               SMALLINT,
  memo                 TEXT,
  orgJobId             NUMERIC(18),
  newJobId             NUMERIC(18),
primary key (changeId)
      constraint PK_JOB_CHANGE
);


create table mail  (
  mailId               NUMERIC(18)                          not null,
  sender               VARCHAR(32)                     not null,
  senderId             NUMERIC(18)                     not null,
  importantFlag        SMALLINT                        not null,
  sendTime             DATETIME YEAR TO SECOND         not null,
  content              TEXT                            not null,
  subject              VARCHAR(254)                    not null,
  copyToNames          TEXT,
  copyToIDs            TEXT,
  recipientNames       TEXT                   not null,
  recipientIDs         TEXT                   not null,
  mailStatus           SMALLINT                        not null,
  fileIds              TEXT,
  filenames            TEXT,
primary key (mailId)
      constraint PK_MAIL
);


create table mail_attach  (
  mailId               NUMERIC(18)                     not null,
  fileId               NUMERIC(18)                     not null,
primary key (mailId, fileId)
      constraint PK_MAIL_ATTACH
);


create table mail_box  (
  boxId                NUMERIC(18)                          not null,
  mailId               NUMERIC(18)                     not null,
  folderId             NUMERIC(18)                     not null,
  userId               NUMERIC(18),
  sendTime             DATETIME YEAR TO SECOND         not null,
  delFlag              SMALLINT                        not null,
  readFlag             SMALLINT                        not null,
  replyFlag            SMALLINT                        not null,
  note                 VARCHAR(254),
primary key (boxId)
      constraint PK_MAIL_BOX
);


create table mail_folder  (
  folderId             NUMERIC(18)                          not null,
  userId               NUMERIC(18),
  folderName           VARCHAR(128)                    not null,
  parentId             NUMERIC(18),
  depLevel             NUMERIC(18)                     not null,
  path                 VARCHAR(254),
  isPublic             SMALLINT                        not null,
  folderType           SMALLINT                        not null,
primary key (folderId)
      constraint PK_MAIL_FOLDER
);


create table meeting  (
  mettingId            NUMERIC(18)                          not null,
  holdTime             DATETIME YEAR TO SECOND,
  holdLocation         VARCHAR(128),
  meetingName          VARCHAR(128),
  attendUsers          VARCHAR(128),
  holdDep              VARCHAR(128),
  holdDepId            NUMERIC(18),
  shortDesc            VARCHAR(254),
  isFeedback           SMALLINT                        not null,
  summary              TEXT,
primary key (mettingId)
      constraint PK_MEETING
);


create table meeting_attend  (
  attendId             NUMERIC(18)                          not null,
  mettingId            NUMERIC(18)                     not null,
  userName             VARCHAR(64),
  userId               NUMERIC(18),
  depName              VARCHAR(100),
  depId                NUMERIC(18),
  attendType           SMALLINT                       default 0 not null,
  feedback             TEXT,
  signTime             DATETIME YEAR TO SECOND,
  signName             VARCHAR(32)                     not null,
primary key (attendId)
      constraint PK_MEETING_ATTEND
);


create table meeting_file  (
  mettingId            NUMERIC(18)                     not null,
  fileId               NUMERIC(18)                     not null,
primary key (mettingId, fileId)
      constraint PK_MEETING_FILE
);


create table news  (
  newsId               NUMERIC(18)                          not null,
  sectionId            NUMERIC(18),
  subjectIcon          VARCHAR(128),
  subject              VARCHAR(128)                    not null,
  author               VARCHAR(32)                     not null,
  createtime           DATETIME YEAR TO SECOND         not null,
  expTime              DATETIME YEAR TO SECOND,
  replyCounts          NUMERIC(18),
  viewCounts           NUMERIC(18),
  issuer               VARCHAR(32)                     not null,
  content              TEXT                            not null,
  updateTime           DATETIME YEAR TO SECOND,
  status               SMALLINT                        not null,
  isDeskImage          SMALLINT,
  isNotice             SMALLINT,
  sn                   NUMERIC(18),
  orgIds               VARCHAR(200),
  orgNames             VARCHAR(1000),
primary key (newsId)
      constraint PK_NEWS
);


create table news_comment  (
  commentId            NUMERIC(18)                          not null,
  content              TEXT                    not null,
  createtime           DATETIME YEAR TO SECOND         not null,
  fullname             VARCHAR(32)                     not null,
  userId               NUMERIC(18)                     not null,
  newsId               NUMERIC(18),
primary key (commentId)
      constraint PK_NEWS_COMMENT
);


create table office_goods  (
  goodsId              NUMERIC(18)                          not null,
  typeId               NUMERIC(18)                     not null,
  goodsName            VARCHAR(128)                    not null,
  goodsNo              VARCHAR(128)                    not null,
  specifications       VARCHAR(254)                    not null,
  unit                 VARCHAR(64)                     not null,
  isWarning            SMALLINT                        not null,
  notes                TEXT,
  stockCounts          NUMERIC(18)                     not null,
  warnCounts           NUMERIC(18)                     not null,
primary key (goodsId)
      constraint PK_OFFICE_GOODS
);


create table office_goods_type  (
  typeId               NUMERIC(18)                          not null,
  typeName             VARCHAR(128)                    not null,
primary key (typeId)
      constraint PK_OFFICE_GOODS_TY
);


create table organization  (
  org_id               NUMERIC(18)                          not null,
  dem_id               NUMERIC(18),
  org_name             VARCHAR(128)                    not null,
  org_desc             lvarchar(9000),
  org_sup_id           NUMERIC(18),
  path                 VARCHAR(128),
  depth                NUMERIC(18),
  org_type             SMALLINT,
  creator_id           NUMERIC(18),
  createtime           DATETIME YEAR TO SECOND,
  update_id            NUMERIC(18),
  updatetime           DATETIME YEAR TO SECOND,
  sn                   NUMERIC(18),
primary key (org_id)
      constraint PK_ORGANIZATION
);

create table out_mail  (
  mailId               NUMERIC(18)                          not null,
  uidNo                TEXT,
  setId                NUMERIC(18)                     not null,
  userId               NUMERIC(18),
  folderId             NUMERIC(18),
  title                TEXT,
  content              TEXT,
  senderAddresses      VARCHAR(128)                    not null,
  senderName           VARCHAR(128),
  receiverAddresses    TEXT                            not null,
  receiverNames        TEXT,
  cCAddresses          TEXT,
  cCNames              TEXT,
  bCCAddresses         TEXT,
  bCCAnames            TEXT,
  mailDate             DATETIME YEAR TO SECOND         not null,
  fileIds              TEXT,
  fileNames            TEXT,
  readFlag             SMALLINT                       default 0 not null,
  replyFlag            SMALLINT                       default 0 not null,
primary key (mailId)
      constraint PK_OUT_MAIL
);

create table out_mail_file  (
  mailId               NUMERIC(18)                     not null,
  fileId               NUMERIC(18)                     not null,
primary key (mailId, fileId)
      constraint PK_OUT_MAIL_FILE
);


create table out_mail_folder  (
  folderId             NUMERIC(18)                          not null,
  userId               NUMERIC(18),
  folderName           VARCHAR(128)                    not null,
  parentId             NUMERIC(18),
  depLevel             NUMERIC(18)                     not null,
  path                 VARCHAR(254),
  folderType           SMALLINT                        not null,
  setId                NUMERIC(18),
primary key (folderId)
      constraint PK_OUT_MAIL_FOLDER
);



create table out_mail_user_seti  (
  setId                NUMERIC(18)                          not null,
  userId               NUMERIC(18),
  userName             VARCHAR(128),
  accountname          VARCHAR(128)                    not null,
  mailAddress          VARCHAR(128)                    not null,
  mailPass             VARCHAR(128)                    not null,
  smtpHost             VARCHAR(128)                    not null,
  smtpPort             VARCHAR(64)                     not null,
  popHost              VARCHAR(128)                    not null,
  popPort              VARCHAR(64)                     not null,
  isDefault            SMALLINT                       default 1,
primary key (setId)
      constraint PK_OUT_MAIL_USER_S
);



create table paint_template  (
  ptemplateId          NUMERIC(18)                          not null,
  fileId               NUMERIC(18),
  templateKey          VARCHAR(64),
  templateName         VARCHAR(64)                     not null,
  path                 VARCHAR(128),
  isActivate           SMALLINT                        not null,
primary key (ptemplateId)
      constraint PK_PAINT_TEMPLATE
);


create table phone_book  (
  phoneId              NUMERIC(18)                          not null,
  fullname             VARCHAR(128)                    not null,
  title                VARCHAR(32)                     not null,
  birthday             DATETIME YEAR TO SECOND,
  nickName             VARCHAR(32),
  duty                 VARCHAR(50),
  spouse               VARCHAR(32),
  childs               VARCHAR(40),
  companyName          VARCHAR(100),
  companyAddress       VARCHAR(128),
  companyPhone         VARCHAR(32),
  companyFax           VARCHAR(32),
  homeAddress          VARCHAR(128),
  homeZip              VARCHAR(12),
  mobile               VARCHAR(32),
  phone                VARCHAR(32),
  email                VARCHAR(32),
  QQ                   VARCHAR(64),
  MSN                  VARCHAR(128),
  note                 TEXT,
  userId               NUMERIC(18)                     not null,
  groupId              NUMERIC(18),
  isShared             SMALLINT                        not null,
primary key (phoneId)
      constraint PK_PHONE_BOOK
);



create table phone_group  (
  groupId              NUMERIC(18)                          not null,
  groupName            VARCHAR(128)                    not null,
  isShared             SMALLINT                        not null,
  SN                   NUMERIC(18)                     not null,
  userId               NUMERIC(18)                     not null,
  isPublic             SMALLINT                       default 0,
primary key (groupId)
      constraint PK_PHONE_GROUP
);


create table plan_attend  (
  attendId             NUMERIC(18)                          not null,
  userId               NUMERIC(18),
  depId                NUMERIC(18),
  planId               NUMERIC(18)                     not null,
  isDep                SMALLINT                        not null,
  isPrimary            SMALLINT,
primary key (attendId)
      constraint PK_PLAN_ATTEND
);


create table plan_file  (
  fileId               NUMERIC(18)                     not null,
  planId               NUMERIC(18)                     not null,
primary key (fileId, planId)
      constraint PK_PLAN_FILE
);


create table position  (
  pos_id               NUMERIC(18)                          not null,
  org_id               NUMERIC(18,0),
  pos_name             VARCHAR(128)                    not null,
  pos_desc             TEXT,
  pos_sup_id           NUMERIC(18),
  path                 VARCHAR(254),
  depth                NUMERIC(18),
  sn                   NUMERIC(18),
primary key (pos_id)
      constraint PK_POSITION
);


create table position_sub  (
  mainPositionId       NUMERIC(18)                     not null,
  subPositionId        NUMERIC(18)                     not null,
primary key (mainPositionId, subPositionId)
      constraint PK_POSITION_SUB
);


create table pro_def_rights  (
  rightsId             NUMERIC(18)                          not null,
  proTypeId            NUMERIC(18),
  defId                NUMERIC(18),
  roleNames            TEXT,
  depNames             TEXT,
  userNames            TEXT,
  userIds              TEXT,
  roleIds              TEXT,
  depIds               TEXT,
primary key (rightsId)
      constraint PK_PRO_DEF_RIGHTS
);


create table pro_definition  (
  defId                NUMERIC(18)                          not null,
  proTypeId            NUMERIC(18),
  name               VARCHAR(254)                    not null,
  description          TEXT,
  createtime           DATETIME YEAR TO SECOND,
  updatetime           DATETIME YEAR TO SECOND,
  deployId             VARCHAR(64),
  pdId                 VARCHAR(64),
  defKey               VARCHAR(64),
  defXml               TEXT,
  drawDefXml           TEXT,
  isDefault            SMALLINT                       default 0 not null,
  processName          VARCHAR(128),
  newVersion           NUMERIC(18),
  status               SMALLINT,
  parentId             NUMERIC(18),
  isMain               SMALLINT                       default 0,
  skipFirstNode        SMALLINT,
primary key (defId)
      constraint PK_PRO_DEFINITION
);


create table pro_handle_comp  (
  handleId             NUMERIC(18)                          not null,
  deployId             VARCHAR(128)                    not null,
  activityName         VARCHAR(128),
  tranName             VARCHAR(128),
  eventName            VARCHAR(128),
  eventLevel           SMALLINT,
  exeCode              TEXT,
  handleType           SMALLINT,
primary key (handleId)
      constraint PK_PRO_HANDLE_COMP
);


create table pro_node_set  (
  setId                NUMERIC(18)                          not null,
  defId                NUMERIC(18),
  deployId             VARCHAR(64)                     not null,
  jbpmDefId            VARCHAR(64)                     not null,
  nodeName             VARCHAR(254),
  nodeType             SMALLINT,
  joinNodeName         VARCHAR(254),
  isAllowBack          SMALLINT,
primary key (setId)
      constraint PK_PRO_NODE_SET
);


create table pro_user_assign  (
  assignId             NUMERIC(18)                          not null,
  deployId             VARCHAR(128)                    not null,
  activityName         VARCHAR(128)                    not null,
  roleId               VARCHAR(128),
  roleName             VARCHAR(254),
  userId               VARCHAR(128),
  username             VARCHAR(254),
  isSigned             SMALLINT                       default 0,
  jobId                VARCHAR(128),
  jobName              VARCHAR(128),
  reJobId              VARCHAR(128),
  reJobName            VARCHAR(128),
  depIds               TEXT,
  depNames             TEXT,
  posUserFlag          SMALLINT,
  depPosIds            VARCHAR(128),
  depPosNames          VARCHAR(128),
primary key (assignId)
      constraint PK_PRO_USER_ASSIGN
);



create table pro_user_set  (
  id                   NUMERIC(18)                          not null,
  defId                NUMERIC(18),
  deployId             VARCHAR(64)                     not null,
  jbpmDefId            VARCHAR(64)                     not null,
  nodeName             VARCHAR(254)                    not null,
  userType             SMALLINT                        not null,
  uids                 TEXT,
  unames               TEXT,
  compType             SMALLINT,
  demId                NUMERIC(18),
  sn                   NUMERIC(18),
primary key (id)
      constraint PK_PRO_USER_SET
);


create table process_form  (
  formId               NUMERIC(18)                          not null,
  runId                NUMERIC(18)                     not null,
  activityName         VARCHAR(254)                    not null,
  createtime           DATETIME YEAR TO SECOND         not null,
  endtime              DATETIME YEAR TO SECOND,
  durtimes             NUMERIC(18),
  creatorId            NUMERIC(18),
  creatorName          VARCHAR(64),
  fromTaskId           VARCHAR(64),
  fromTask             VARCHAR(254),
  taskId               VARCHAR(64),
  transTo              VARCHAR(254),
  status               SMALLINT                       default 0,
  preFormId            NUMERIC(18),
  comments             TEXT,
primary key (formId)
      constraint PK_PROCESS_FORM
);


create table process_module  (
  moduleId             NUMERIC(18)                          not null,
  moduleName           VARCHAR(254)                    not null,
  moduleKey            VARCHAR(128)                    not null,
  descp                TEXT,
  defId                NUMERIC(18),
  processKey           VARCHAR(254),
  creator              VARCHAR(64),
  createtime           DATETIME YEAR TO SECOND,
primary key (moduleId)
      constraint PK_PROCESS_MODULE
);



create table process_run  (
  runId                NUMERIC(18)                          not null,
  subject              VARCHAR(254)                    not null,
  creator              VARCHAR(128),
  userId               NUMERIC(18)                     not null,
  defId                NUMERIC(18)                     not null,
  piId                 VARCHAR(64),
  piDbid               NUMERIC(18),
  pdId                 VARCHAR(64),
  processName          VARCHAR(128),
  createtime           DATETIME YEAR TO SECOND         not null,
  runStatus            SMALLINT                        not null,
  busDesc              TEXT,
  entityName           VARCHAR(128),
  entityId             NUMERIC(18),
  formDefId            NUMERIC(18),
  defHtml              TEXT,
primary key (runId)
      constraint PK_PROCESS_RUN
);



create table product  (
  productId            NUMERIC(18)                          not null,
  productName          VARCHAR(128)                    not null,
  productModel         VARCHAR(128),
  unit                 VARCHAR(128),
  costPrice            NUMERIC(18,2),
  salesPrice           NUMERIC(18,2),
  productDesc          TEXT,
  providerId           NUMERIC(18)                     not null,
  createtime           DATETIME YEAR TO SECOND         not null,
  updatetime           DATETIME YEAR TO SECOND         not null,
primary key (productId)
      constraint PK_PRODUCT
);


create table project  (
  projectId            NUMERIC(18)                          not null,
  projectName          VARCHAR(128)                    not null,
  projectNo            VARCHAR(64)                     not null,
  reqDesc              TEXT,
  isContract           SMALLINT                        not null,
  fullname             VARCHAR(32)                     not null,
  mobile               VARCHAR(32),
  phone                VARCHAR(32),
  fax                  VARCHAR(32),
  otherContacts        VARCHAR(128),
  customerId           NUMERIC(18)                     not null,
  userId               NUMERIC(18)                     not null,
primary key (projectId)
      constraint PK_PROJECT
);



create table project_file  (
  fileId               NUMERIC(18)                     not null,
  projectId            NUMERIC(18)                     not null,
primary key (fileId, projectId)
      constraint PK_PROJECT_FILE
);



create table provider  (
  providerId           NUMERIC(18)                          not null,
  providerName         VARCHAR(128)                    not null,
  contactor            VARCHAR(128)                    not null,
  phone                VARCHAR(32)                     not null,
  fax                  VARCHAR(32),
  site                 VARCHAR(128),
  email                VARCHAR(128),
  address              VARCHAR(128)                    not null,
  zip                  VARCHAR(32),
  openBank             VARCHAR(128),
  account              VARCHAR(64),
  notes                TEXT,
  rank                 NUMERIC(18),
primary key (providerId)
      constraint PK_PROVIDER
);


create table reg_attach  (
  fileId               NUMERIC(18)                     not null,
  regId                NUMERIC(18)                     not null,
primary key (fileId, regId)
      constraint PK_REG_ATTACH
);


create table region  (
  regionId             NUMERIC(18)                          not null,
  regionName           VARCHAR(128)                    not null,
  regionType           SMALLINT                        not null,
  parentId             NUMERIC(18),
primary key (regionId)
      constraint PK_REGION
);

create table regulation  (
  regId                NUMERIC(18)                          not null,
  proTypeId            NUMERIC(18),
  subject              VARCHAR(254)                    not null,
  issueDate            DATETIME YEAR TO SECOND,
  issueUserId          NUMERIC(18),
  issueFullname        VARCHAR(64),
  issueDepId           NUMERIC(18),
  issueDep             VARCHAR(64),
  recDeps              TEXT,
  recDepIds            TEXT,
  recUsers             TEXT,
  recUserIds           TEXT,
  content              TEXT,
  keywords             VARCHAR(254),
  status               SMALLINT,
primary key (regId)
      constraint PK_REGULATION
);


create table relative_job  (
  reJobId              NUMERIC(18)                          not null,
  jobName              VARCHAR(128)                    not null,
  jobCode              VARCHAR(254),
  parent               NUMERIC(18),
  path                 VARCHAR(128),
  depath               NUMERIC(18)                    default 0,
primary key (reJobId)
      constraint PK_RELATIVE_JOB
);



create table relative_user  (
  relativeUserId       NUMERIC(18)                          not null,
  reJobId              NUMERIC(18),
  userId               NUMERIC(18),
  jobUserId            NUMERIC(18),
  isSuper              SMALLINT,
primary key (relativeUserId)
      constraint PK_RELATIVE_USER
);

create table report_param  (
  paramId              NUMERIC(18)                          not null,
  reportId             NUMERIC(18)                     not null,
  paramName            VARCHAR(64)                     not null,
  paramKey             VARCHAR(64)                     not null,
  defaultVal           VARCHAR(128),
  paramType            VARCHAR(32)                     not null,
  sn                   NUMERIC(18)                     not null,
  paramTypeStr         TEXT,
primary key (paramId)
      constraint PK_REPORT_PARAM
);

create table report_template  (
  reportId             NUMERIC(18)                          not null,
  title                VARCHAR(128)                    not null,
  descp                TEXT                    not null,
  reportLocation       VARCHAR(128)                    not null,
  createtime           DATETIME YEAR TO SECOND         not null,
  updatetime           DATETIME YEAR TO SECOND         not null,
  reportKey            VARCHAR(128),
  isDefaultIn          SMALLINT,
primary key (reportId)
      constraint PK_REPORT_TEMPLATE
);

create table resume  (
  resumeId             NUMERIC(18)                          not null,
  fullname             VARCHAR(64)                     not null,
  age                  NUMERIC(18),
  birthday             DATETIME YEAR TO SECOND,
  address              VARCHAR(128),
  zip                  VARCHAR(32),
  sex                  VARCHAR(32),
  position             VARCHAR(64),
  phone                VARCHAR(64),
  mobile               VARCHAR(64),
  email                VARCHAR(128),
  hobby                VARCHAR(254),
  religion             VARCHAR(128),
  party                VARCHAR(128),
  nationality          VARCHAR(32),
  race                 VARCHAR(32),
  birthPlace           VARCHAR(128),
  eduCollege           VARCHAR(128),
  eduDegree            VARCHAR(128),
  eduMajor             VARCHAR(128),
  startWorkDate        DATETIME YEAR TO SECOND,
  idNo                 VARCHAR(64),
  photo                VARCHAR(128),
  status               VARCHAR(64),
  memo                 TEXT,
  registor             VARCHAR(64),
  regTime              DATETIME YEAR TO SECOND,
  workCase             TEXT,
  trainCase            TEXT,
  projectCase          TEXT,
primary key (resumeId)
      constraint PK_RESUME
);

create table resume_file  (
  fileId               NUMERIC(18)                     not null,
  resumeId             NUMERIC(18)                     not null,
primary key (fileId, resumeId)
      constraint PK_RESUME_FILE
);

create table role_fun  (
  roleId               NUMERIC(18)                     not null,
  functionId           NUMERIC(18)                     not null,
primary key (roleId, functionId)
      constraint PK_ROLE_FUN
);

create table role_position  (
  pos_id               NUMERIC(18)                     not null,
  roleId               NUMERIC(18)                     not null,
primary key (pos_id, roleId)
      constraint PK_ROLE_POSITION
);

create table roll_file  (
  rollFileId           NUMERIC(18)                          not null,
  typeName             VARCHAR(128),
  rollId               NUMERIC(18),
  proTypeId            NUMERIC(18),
  fileName             VARCHAR(128)                    not null,
  fileNo               VARCHAR(64)                     not null,
  dutyPerson           VARCHAR(32),
  afNo                 VARCHAR(64),
  catNo                VARCHAR(64),
  rollNo               VARCHAR(64),
  seqNo                NUMERIC(18),
  pageNo               NUMERIC(18),
  pageNums             NUMERIC(18),
  secretLevel          VARCHAR(64),
  timeLimit            VARCHAR(64),
  openStyle            VARCHAR(64),
  keyWords             TEXT,
  notes                TEXT,
  content              TEXT,
  fileTime             DATETIME YEAR TO SECOND,
  creatorName          VARCHAR(128),
  createTime           DATETIME YEAR TO SECOND,
  archStatus           SMALLINT                       default 0,
  tidyTime             DATETIME YEAR TO SECOND,
  tidyName             VARCHAR(128),
primary key (rollFileId)
      constraint PK_ROLL_FILE
);

create table roll_file_list  (
  listId               NUMERIC(18)                          not null,
  rollFileId           NUMERIC(18)                     not null,
  fileId               NUMERIC(18),
  downLoads            NUMERIC(18),
  sn                   NUMERIC(18),
  shortDesc            TEXT,
primary key (listId)
      constraint PK_ROLL_FILE_LIST
);

create table run_data  (
  dataId               NUMERIC(18)                          not null,
  runId                NUMERIC(18),
  fieldLabel           VARCHAR(128),
  fieldName            VARCHAR(64)                     not null,
  intValue             NUMERIC(18),
  longValue            NUMERIC(18),
  decValue             NUMERIC(18,4),
  dateValue            DATETIME YEAR TO SECOND,
  strValue             TEXT,
  boolValue            SMALLINT,
  blobValue            BYTE,
  isShowed             SMALLINT,
  textValue            TEXT,
  fieldType            VARCHAR(32),
primary key (dataId)
      constraint PK_RUN_DATA
);


create table salary_item  (
  salaryItemId         NUMERIC(18)                          not null,
  itemName             VARCHAR(128)                    not null,
  defaultVal           NUMERIC(18,2)                   not null,
primary key (salaryItemId)
      constraint PK_SALARY_ITEM
);

create table salary_payoff  (
  recordId             NUMERIC(18)                          not null,
  fullname             VARCHAR(64)                     not null,
  userId               NUMERIC(18)                     not null,
  profileNo            VARCHAR(128),
  standardId           NUMERIC(18)                     not null,
  idNo                 VARCHAR(128),
  standAmount          NUMERIC(18,2)                  default 0 not null,
  encourageAmount      NUMERIC(18,2)                  default 0 not null,
  deductAmount         NUMERIC(18,2)                  default 0 not null,
  achieveAmount        NUMERIC(18,2)                  default 0,
  encourageDesc        TEXT,
  deductDesc           TEXT,
  memo                 TEXT,
  acutalAmount         NUMERIC(18,2),
  regTime              DATETIME YEAR TO SECOND         not null,
  register             VARCHAR(64),
  checkOpinion         TEXT,
  checkName            VARCHAR(64),
  checkTime            DATETIME YEAR TO SECOND,
  checkStatus          SMALLINT,
  startTime            DATETIME YEAR TO SECOND         not null,
  endTime              DATETIME YEAR TO SECOND         not null,
primary key (recordId)
      constraint PK_SALARY_PAYOFF
);

create table seal  (
  sealId               NUMERIC(18)                          not null,
  fileId               NUMERIC(18),
  sealName             VARCHAR(64)                     not null,
  sealPath             VARCHAR(128),
  belongId             NUMERIC(18)                     not null,
  belongName           VARCHAR(64)                     not null,
primary key (sealId)
      constraint PK_SEAL
);

create table section  (
  sectionId            NUMERIC(18)                          not null,
  sectionName          VARCHAR(254)                    not null,
  sectionDesc          TEXT,
  createtime           DATETIME YEAR TO SECOND         not null,
  sectionType          SMALLINT                        not null,
  username             VARCHAR(254),
  userId               NUMERIC(18),
  colNumber            NUMERIC(18),
  rowNumber            NUMERIC(18),
  status               SMALLINT                        not null,
primary key (sectionId)
      constraint PK_SECTION
);

create table integer_number  (
  numberId             NUMERIC(18)                          not null,
  name                 VARCHAR(50),
  alias                VARCHAR(20),
  regulation           VARCHAR(100),
  genType              SMALLINT,
  noLength             NUMERIC(18),
  curDate              VARCHAR(10),
  initValue            NUMERIC(18),
  curValue             NUMERIC(18),
  step                 SMALLINT,
primary key (numberId)
      constraint PK_INTEGER_NUMBER
);

create table short_message  (
  messageId            NUMERIC(18)                          not null,
  senderId             NUMERIC(18),
  content              VARCHAR(254)                    not null,
  sender               VARCHAR(64)                     not null,
  msgType              SMALLINT                        not null,
  sendTime             DATETIME YEAR TO SECOND         not null,
primary key (messageId)
      constraint PK_SHORT_MESSAGE
);

create table sms_history  (
  smsId                NUMERIC(18)                          not null,
  sendTime             DATETIME YEAR TO SECOND         not null,
  recipients           VARCHAR(128),
  phoneNumber          VARCHAR(128)                    not null,
  userId               NUMERIC(18),
  userName             VARCHAR(128),
  smsContent           TEXT                   not null,
  status               SMALLINT                        not null,
primary key (smsId)
      constraint PK_SMS_HISTORY
);

create table sms_mobile  (
  smsId                NUMERIC(18)                          not null,
  sendTime             DATETIME YEAR TO SECOND         not null,
  recipients           VARCHAR(128),
  phoneNumber          VARCHAR(128)                    not null,
  userId               NUMERIC(18),
  userName             VARCHAR(128),
  smsContent           TEXT                   not null,
  status               SMALLINT                        not null,
primary key (smsId)
      constraint PK_SMS_MOBILE
);

create table stand_salary  (
  standardId           NUMERIC(18)                          not null,
  standardNo           VARCHAR(128)                    not null,
  standardName         VARCHAR(128)                    not null,
  totalMoney           NUMERIC(18,2)                  default 0.00 not null,
  framer               VARCHAR(64),
  setdownTime          DATETIME YEAR TO SECOND,
  checkName            VARCHAR(64),
  checkTime            DATETIME YEAR TO SECOND,
  modifyName           VARCHAR(64),
  modifyTime           DATETIME YEAR TO SECOND,
  checkOpinion         TEXT,
  status               SMALLINT                        not null,
  memo                 TEXT,
primary key (standardId)
      constraint PK_STAND_SALARY
);

create table stand_salary_item  (
  itemId               NUMERIC(18)                          not null,
  standardId           NUMERIC(18)                     not null,
  itemName             VARCHAR(64)                     not null,
  amount               NUMERIC(18,2)                   not null,
  salaryItemId         NUMERIC(18),
primary key (itemId)
      constraint PK_STAND_SALARY_IT
);

create table subordinate  (
  subordinateId        NUMERIC(18)                          not null,
  dem_id               NUMERIC(18)                     not null,
  userId               NUMERIC(18)                     not null,
  jobuserId            NUMERIC(18)                     not null,
  relative             NUMERIC(2)                      not null,
primary key (subordinateId)
      constraint PK_SUBORDINATE
);

create table suggest_box  (
  boxId                NUMERIC(18)                          not null,
  subject              VARCHAR(254)                    not null,
  content              TEXT                   not null,
  createtime           DATETIME YEAR TO SECOND,
  recUid               NUMERIC(18),
  recFullname          VARCHAR(32),
  senderId             NUMERIC(18),
  senderFullname       VARCHAR(32),
  senderIp             VARCHAR(64),
  phone                VARCHAR(64),
  email                VARCHAR(100),
  isOpen               SMALLINT,
  replyContent         TEXT,
  replyTime            DATETIME YEAR TO SECOND,
  replyId              NUMERIC(18),
  replyFullname        VARCHAR(32),
  status               SMALLINT,
  queryPwd             VARCHAR(128),
primary key (boxId)
      constraint PK_SUGGEST_BOX
);

create table sys_config  (
  configId             NUMERIC(18)                          not null,
  configKey            VARCHAR(64)                     not null,
  configName           VARCHAR(64)                     not null,
  configDesc           VARCHAR(254),
  typeName             VARCHAR(32)                     not null,
  dataType             SMALLINT                        not null,
  dataValue            VARCHAR(64),
  typeKey              VARCHAR(64),
primary key (configId)
      constraint PK_SYS_CONFIG
);

create table task_sign  (
  signId               NUMERIC(18)                          not null,
  setId                NUMERIC(18)                     not null,
  voteCounts           NUMERIC(18),
  votePercents         NUMERIC(18),
  decideType           SMALLINT                        not null,
  signType             NUMERIC(18),
primary key (signId)
      constraint PK_TASK_SIGN
);

create table task_sign_data  (
  dataId               NUMERIC(18)                          not null,
  voteId               NUMERIC(18)                     not null,
  voteName             VARCHAR(64),
  voteTime             DATETIME YEAR TO SECOND         not null,
  taskId               VARCHAR(64)                     not null,
  isAgree              SMALLINT                        not null,
primary key (dataId)
      constraint PK_TASK_SIGN_DATA
);

create table type_key  (
  typeKeyId            NUMERIC(18)                          not null,
  typeKey              VARCHAR(64)                     not null,
  typeName             VARCHAR(64)                     not null,
  sn                   NUMERIC(18),
primary key (typeKeyId)
      constraint PK_TYPE_KEY
);

create table user_org  (
  user_org_id          NUMERIC(18)                          not null,
  userId               NUMERIC(18),
  org_id               NUMERIC(18),
  is_primary           SMALLINT                        not null,
  is_charge            NUMERIC(18),
primary key (user_org_id)
      constraint PK_USER_ORG
);


create table user_position  (
  user_pos_id          NUMERIC(18)                          not null,
  pos_id               NUMERIC(18),
  userId               NUMERIC(18),
  isPrimary            SMALLINT,
primary key (user_pos_id)
      constraint PK_USER_POSITION
);


create table user_role  (
  userId               NUMERIC(18)                     not null,
  roleId               NUMERIC(18)                     not null,
primary key (userId, roleId)
      constraint PK_USER_ROLE
);


create table wf_general  (
  entityId             NUMERIC(18)                          not null,
  itemSubject          VARCHAR(128)                    not null,
  itemDescp            TEXT                            not null,
  runId                NUMERIC(18),
  createtime           DATETIME YEAR TO SECOND,
primary key (entityId)
      constraint PK_WF_GENERAL
);


create table work_plan  (
  planId               NUMERIC(18)                          not null,
  planName             VARCHAR(128)                    not null,
  planContent          TEXT,
  startTime            DATETIME YEAR TO SECOND         not null,
  endTime              DATETIME YEAR TO SECOND         not null,
  typeName             VARCHAR(64),
  userId               NUMERIC(18),
  proTypeId            NUMERIC(18),
  issueScope           TEXT,
  participants         TEXT,
  principal            VARCHAR(254)                    not null,
  note                 TEXT,
  status               SMALLINT                        not null,
  isPersonal           SMALLINT                        not null,
  icon                 VARCHAR(128),
primary key (planId)
      constraint PK_WORK_PLAN
);

alter table app_tips
   add constraint foreign key (userId)
      references app_user (userId) 
      constraint FK_AT_R_AP_APP_USE;

alter table appointment
   add constraint foreign key (userId)
      references app_user (userId) 
      constraint FK_APPOINTM_AP_R_AU;

alter table arch_dispatch
   add constraint foreign key (archivesId)
      references archives (archivesId) on delete cascade
      constraint FK_AVDH_R_ARV;

alter table arch_flow_conf
   add constraint foreign key (defId)
      references pro_definition (defId) on delete cascade
      constraint FK_AFC_R_PDN;

alter table arch_fond
   add constraint foreign key (proTypeId)
      references global_type (proTypeId) on delete cascade
      constraint FK_AF_R_GT;

alter table arch_hasten
   add constraint foreign key (archivesId)
      references archives (archivesId) 
      constraint FK_ARHN_R_ARV;

alter table arch_roll
   add constraint foreign key (archFondId)
      references arch_fond (archFondId) on delete cascade
      constraint FK_AR_R_AF;

alter table arch_roll
   add constraint foreign key (proTypeId)
      references global_type (proTypeId) on delete cascade
      constraint FK_AR_R_GT;

alter table arch_template
   add constraint foreign key (fileId)
      references file_attach (fileId) 
      constraint FK_ARCH_TE_FILE_AT;

alter table arch_template
   add constraint foreign key (proTypeId)
      references global_type (proTypeId) 
      constraint FK_ART_R_A_GLOBAL_;

alter table archives
   add constraint foreign key (glo_proTypeId)
      references global_type (proTypeId) 
      constraint FK_ARV_R_A_GLO_GLOBAL_;

alter table archives
   add constraint foreign key (proTypeId)
      references global_type (proTypeId) 
      constraint FK_ARCHIVE_R_A_GLOBAL_;

alter table archives_dep
   add constraint foreign key (archivesId)
      references archives (archivesId) on delete cascade
      constraint FK_AVD_R_A_ARCHIVE;

alter table archives_doc
   add constraint foreign key (fileId)
      references file_attach (fileId) on delete cascade
      constraint FK_ARHD_R_FA;

alter table archives_doc
   add constraint foreign key (archivesId)
      references archives (archivesId) 
      constraint FK_ARVD_R__ARCHIVE;

alter table book
   add constraint foreign key (typeId)
      references book_type (typeId) 
      constraint FK_BK_R_BT_BOOK_TY;

alter table book_bor_ret
   add constraint foreign key (bookSnId)
      references book_sn (bookSnId) 
      constraint FK_BBR_R_B_BOOK_SN;

alter table book_sn
   add constraint foreign key (bookId)
      references book (bookId) 
      constraint FK_BS_R_BK_BOOK;

alter table borrow_file_list
   add constraint foreign key (rollId)
      references arch_roll (rollId) 
      constraint FK_BFL_AR_ARCH_RO;

alter table borrow_file_list
   add constraint foreign key (archFondId)
      references arch_fond (archFondId) 
      constraint FK_BFL_R_A_ARCH_FO;

alter table borrow_file_list
   add constraint foreign key (recordId)
      references borrow_record (recordId) 
      constraint FK_BFL_R_B_BORROW_;

alter table borrow_file_list
   add constraint foreign key (rollFileId)
      references roll_file (rollFileId) 
      constraint FK_BFL_R_R_ROLL_FI;

alter table borrow_record
   add constraint foreign key (checkUserId)
      references app_user (userId) on delete cascade
      constraint FK_BORROW_R_BR_R_AU1_APP_USER;

alter table cal_file
   add constraint foreign key (planId)
      references calendar_plan (planId) 
      constraint FK_CF_R_CP_CALENDA;

alter table cal_file
   add constraint foreign key (fileId)
      references file_attach (fileId) 
      constraint FK_CF_R_FA_FILE_AT;

alter table calendar_plan
   add constraint foreign key (userId)
      references app_user (userId) 
      constraint FK_CA_R_AU_APP_USE;

alter table calendar_plan
   add constraint foreign key (assignerId)
      references app_user (userId) 
      constraint FK_CP_R_AU_APP_USE;

alter table car_apply
   add constraint foreign key (carId)
      references car (carId) 
      constraint FK_CRA_R_C_CAR;

alter table cart_repair
   add constraint foreign key (carId)
      references car (carId) 
      constraint FK_CRR_R_C_CAR;

alter table conf_attach
   add constraint foreign key (confId)
      references conference (confId) on delete cascade
      constraint FK_CFA_R_CFC;

alter table conf_attach
   add constraint foreign key (fileId)
      references file_attach (fileId) on delete cascade
      constraint FK_CCFA_R_FA;

alter table conf_attend
   add constraint foreign key (confId)
      references conference (confId) on delete cascade
      constraint FK_CA_R_CFC;

alter table conf_privilege
   add constraint foreign key (confId)
      references conference (confId) on delete cascade
      constraint FK_CP_R_CFC;

alter table conf_sum_attach
   add constraint foreign key (sumId)
      references conf_summary (sumId) on delete cascade
      constraint FK_CSA_R_CS;

alter table conf_sum_attach
   add constraint foreign key (fileId)
      references file_attach (fileId) 
      constraint FK_CSA_R_FA;

alter table conf_summary
   add constraint foreign key (confId)
      references conference (confId) 
      constraint FK_CS_R_CFC;

alter table conference
   add constraint foreign key (typeId)
      references board_type (typeId) on delete cascade
      constraint FK_CF_R_BT_BD;

alter table conference
   add constraint foreign key (roomId)
      references boardroo (roomId) on delete cascade
      constraint FK_CFC_R_BDM;

alter table contract
   add constraint foreign key (projectId)
      references project (projectId) 
      constraint FK_CT_R_PT_PROJECT;

alter table contract_config
   add constraint foreign key (contractId)
      references contract (contractId) 
      constraint FK_CC_R_CT_CONTRAC;

alter table contract_file
   add constraint foreign key (contractId)
      references contract (contractId) 
      constraint FK_CTF_R_C_CONTRAC;

alter table contract_file
   add constraint foreign key (fileId)
      references file_attach (fileId) 
      constraint FK_CTF_R_F_FILE_AT;

alter table cus_connection
   add constraint foreign key (customerId)
      references customer (customerId) 
      constraint FK_CC_R_CS_CUSTOME;

alter table cus_linkman
   add constraint foreign key (customerId)
      references customer (customerId) 
      constraint FK_CLM_R_C_CUSTOME;

alter table dep_pos
   add constraint foreign key (org_id)
      references organization (org_id) 
      constraint FK_DEP_POS_ORGANIZ;

alter table dep_pos
   add constraint foreign key (pos_id)
      references position (pos_id) 
      constraint FK_DEP_POS_POSITIO;

alter table depre_record
   add constraint foreign key (assetsId)
      references fixed_assets (assetsId) 
      constraint FK_DR_R_FA_FIXED_A;

alter table diary
   add constraint foreign key (userId)
      references app_user (userId) 
      constraint FK_DY_R_AU_APP_USE;

alter table dictionary
   add constraint foreign key (proTypeId)
      references global_type (proTypeId) on delete cascade
      constraint DTY_R_GT;

alter table doc_file
   add constraint foreign key (docId)
      references document (docId) 
      constraint FK_DF_F_DT_DOCUMEN;

alter table doc_file
   add constraint foreign key (fileId)
      references file_attach (fileId) 
      constraint FK_DF_R_FA;

alter table doc_folder
   add constraint foreign key (userId)
      references app_user (userId) 
      constraint FK_DF_R_AU_APP_USE;

alter table doc_history
   add constraint foreign key (docId)
      references archives_doc (docId) 
      constraint FK_DHY_R_A_ARCHIVE;

alter table doc_history
   add constraint foreign key (fileId)
      references file_attach (fileId) on delete cascade
      constraint FK_DHY_R_FA;

alter table doc_privilege
   add constraint foreign key (folderId)
      references doc_folder (folderId) on delete cascade
      constraint FK_DP_R_DF_DOC_FOL;

alter table doc_privilege
   add constraint foreign key (docId)
      references document (docId) on delete cascade
      constraint FK_DP_R_DT_DOCUMEN;

alter table document
   add constraint foreign key (userId)
      references app_user (userId) 
      constraint FK_DT_R_AU_APP_USE;

alter table document
   add constraint foreign key (folderId)
      references doc_folder (folderId) on delete cascade
      constraint FK_DT_R_DF_DOC_FOL;

alter table duty
   add constraint foreign key (userId)
      references app_user (userId) 
      constraint FK_DUY_R_A_APP_USE;

alter table duty
   add constraint foreign key (systemId)
      references duty_system (systemId) 
      constraint FK_DUY_R_D_DUTY_SY;

alter table duty_register
   add constraint foreign key (userId)
      references app_user (userId) 
      constraint FK_DR_R_AU_APP_USE;

alter table duty_register
   add constraint foreign key (sectionId)
      references duty_section (sectionId) 
      constraint FK_DR_R_DS_DUTY_SE;

alter table emp_profile
   add constraint foreign key (userId)
      references app_user (userId) 
      constraint FK_EPF_R_A_APP_USE;

alter table emp_profile
   add constraint foreign key (jobId)
      references position (pos_id) 
      constraint FK_PT_R_EP_POSITIO;

alter table emp_profile
   add constraint foreign key (standardId)
      references stand_salary (standardId) 
      constraint FK_SD_R_SY_STAND_S;

alter table errands_register
   add constraint foreign key (approvalId)
      references app_user (userId) 
      constraint FK_ERP_R_A_APP_USE;

alter table errands_register
   add constraint foreign key (userId)
      references app_user (userId) 
      constraint FK_ER_R_AU_APP_USE;

alter table field_rights
   add constraint foreign key (mappingId)
      references form_def_mapping (mappingId) on delete cascade
      constraint FK_FR_R_FD_FORM_DE;

alter table field_rights
   add constraint foreign key (fieldId)
      references form_field (fieldId) on delete cascade
      constraint FK_FR_R_FF_FORM_FI;

alter table file_attach
   add constraint foreign key (proTypeId)
      references global_type (proTypeId) on delete cascade
      constraint FK_FLE_AT_R_GT;

alter table fixed_assets
   add constraint foreign key (assetsTypeId)
      references assets_type (assetsTypeId) 
      constraint FK_FA_R_AT_ASSETS_;

alter table fixed_assets
   add constraint foreign key (depreTypeId)
      references depre_type (depreTypeId) 
      constraint FK_FA_R_DT_DEPRE_T;

alter table form_def_mapping
   add constraint foreign key (formDefId)
      references form_def (formDefId) on delete cascade
      constraint FK_FDM_R_F_FORM_DE;

alter table form_def_mapping
   add constraint foreign key (defId)
      references pro_definition (defId) on delete cascade
      constraint FK_FDM_R_P_PRO_DEF;

alter table form_field
   add constraint foreign key (tableId)
      references form_table (tableId) on delete cascade
      constraint FK_FORM_FI_FORM_TA;

alter table form_table
   add constraint foreign key (formDefId)
      references form_def (formDefId) on delete cascade
      constraint FK_FT_R_FD_FORM_DE;

alter table form_template
   add constraint foreign key (mappingId)
      references form_def_mapping (mappingId) on delete cascade
      constraint FK_FT_R_FD_FORM_DEF_MAP;

alter table fun_url
   add constraint foreign key (functionId)
      references app_function (functionId) 
      constraint FK_FU_R_AF_APP_FUN;

alter table goods_apply
   add constraint foreign key (goodsId)
      references office_goods (goodsId) 
      constraint FK_GA_R_OG_OFFICE_;

alter table in_message
   add constraint foreign key (userId)
      references app_user (userId) 
      constraint FK_IM_R_AU_APP_USE;

alter table in_message
   add constraint foreign key (messageId)
      references short_message (messageId) 
      constraint FK_IN_MESS_SHORT_M;

alter table in_stock
   add constraint foreign key (goodsId)
      references office_goods (goodsId) 
      constraint FK_IS_R_OG_OFFICE_;

alter table index_display
   add constraint foreign key (userId)
      references app_user (userId) 
      constraint FK_ID_R_AU_APP_USE;

alter table job_change
   add constraint foreign key (orgJobId)
      references position (pos_id) on delete cascade
      constraint FK_JOB_CH_R_POS;

alter table job_change
   add constraint foreign key (newJobId)
      references position (pos_id) on delete cascade
      constraint FK_JOB_CH_R_POS_NEW;

alter table mail
   add constraint foreign key (senderId)
      references app_user (userId) 
      constraint FK_ML_R_AU_APP_USE;

alter table mail_attach
   add constraint foreign key (fileId)
      references file_attach (fileId) 
      constraint FK_MA_R_FA_FILE_AT;

alter table mail_attach
   add constraint foreign key (mailId)
      references mail (mailId) 
      constraint FK_MA_R_ML_MAIL;

alter table mail_box
   add constraint foreign key (userId)
      references app_user (userId) 
      constraint FK_MB_R_AU_APP_USE;

alter table mail_box
   add constraint foreign key (folderId)
      references mail_folder (folderId) 
      constraint FK_MB_R_FD_MAIL_FO;

alter table mail_box
   add constraint foreign key (mailId)
      references mail (mailId) 
      constraint FK_MB_R_ML_MAIL;

alter table mail_folder
   add constraint foreign key (userId)
      references app_user (userId) 
      constraint FK_FD_R_AU_APP_USE;

alter table meeting_attend
   add constraint foreign key (mettingId)
      references meeting (mettingId) 
      constraint FK_MTA_R_M_MEETING;

alter table meeting_file
   add constraint foreign key (fileId)
      references file_attach (fileId) 
      constraint FK_MF_R_FA_FILE_AT;

alter table meeting_file
   add constraint foreign key (mettingId)
      references meeting (mettingId) 
      constraint FK_MF_R_MT_MEETING;

alter table news
   add constraint foreign key (sectionId)
      references section (sectionId) on delete cascade
      constraint FK_NEWS_R__SECTION;

alter table news_comment
   add constraint foreign key (userId)
      references app_user (userId) 
      constraint FK_NC_R_AU_APP_USE;

alter table news_comment
   add constraint foreign key (newsId)
      references news (newsId) 
      constraint FK_NC_R_NS_NEWS;

alter table office_goods
   add constraint foreign key (typeId)
      references office_goods_type (typeId) 
      constraint FK_OG_R_OG_OFFICE_;

alter table organization
   add constraint foreign key (dem_id)
      references demension (dem_id) on delete cascade
      constraint FK_ORG_R_DMS;

alter table out_mail
   add constraint foreign key (folderId)
      references out_mail_folder (folderId) 
      constraint FK_OM_R_OM_OUT_MAI;

alter table out_mail
   add constraint foreign key (setId)
      references out_mail_user_seti (setId) 
      constraint FK_O_M_R_O_M_S;

alter table out_mail_file
   add constraint foreign key (fileId)
      references file_attach (fileId) 
      constraint FK_OMF_R_F_FILE_AT;

alter table out_mail_file
   add constraint foreign key (mailId)
      references out_mail (mailId) 
      constraint FK_OMF_R_O_OUT_MAI;

alter table out_mail_folder
   add constraint foreign key (userId)
      references app_user (userId) 
      constraint FK_OMF_RAU_APP_USE;

alter table out_mail_folder
   add constraint foreign key (setId)
      references out_mail_user_seti (setId) 
      constraint FK_O_M_F_R_O_M_S;

alter table out_mail_user_seti
   add constraint foreign key (userId)
      references app_user (userId) on delete cascade
      constraint FK_OMU_R_A_APP_USE;

alter table paint_template
   add constraint foreign key (fileId)
      references file_attach (fileId) on delete cascade
      constraint FK_PTE_R_FA;

alter table phone_book
   add constraint foreign key (userId)
      references app_user (userId) 
      constraint FK_PB_R_AU_APP_USE;

alter table phone_book
   add constraint foreign key (groupId)
      references phone_group (groupId) 
      constraint FK_PB_R_PG_PHONE_G;

alter table phone_group
   add constraint foreign key (userId)
      references app_user (userId) 
      constraint FK_PG_R_AU_APP_USE;

alter table plan_attend
   add constraint foreign key (userId)
      references app_user (userId) 
      constraint FK_PAD_R_U_APP_USE;

alter table plan_attend
   add constraint foreign key (planId)
      references work_plan (planId) 
      constraint FK_PAD_R_W_WORK_PL;

alter table plan_file
   add constraint foreign key (fileId)
      references file_attach (fileId) 
      constraint FK_PA_R_FA_FILE_AT;

alter table plan_file
   add constraint foreign key (planId)
      references work_plan (planId) 
      constraint FK_PA_R_WP_WORK_PL;

alter table position_sub
   add constraint foreign key (mainPositionId)
      references position (pos_id) on delete cascade
      constraint FK_POS_SUB_R_POS;

alter table position_sub
   add constraint foreign key (subPositionId)
      references position (pos_id) on delete cascade
      constraint FK_POS_SUB_SUB_R_POS;

alter table pro_def_rights
   add constraint foreign key (proTypeId)
      references global_type (proTypeId) on delete cascade
      constraint FK_PDR_R_G_GLOBAL_;

alter table pro_def_rights
   add constraint foreign key (defId)
      references pro_definition (defId) on delete cascade
      constraint FK_PDR_R_P_PRO_DEF;

alter table pro_definition
   add constraint foreign key (proTypeId)
      references global_type (proTypeId) on delete cascade
      constraint FK_PD_R_GT_GLOBAL_;

alter table pro_node_set
   add constraint foreign key (defId)
      references pro_definition (defId) on delete cascade
      constraint FK_PRO_NOD_PRO_DEF;

alter table pro_user_set
   add constraint foreign key (defId)
      references pro_definition (defId) on delete cascade
      constraint FK_FK_PRO_PRO_DEF;

alter table pro_user_set
   add constraint foreign key (demId)
      references demension (dem_id) 
      constraint FK_PRO_USR_DEMENSI;

alter table process_form
   add constraint foreign key (runId)
      references process_run (runId) 
      constraint FK_PF_R_PR_PROCESS;

alter table process_module
   add constraint foreign key (defId)
      references pro_definition (defId) on delete cascade
      constraint FK_PM_R_PDI;

alter table process_run
   add constraint foreign key (userId)
      references app_user (userId) 
      constraint FK_PROCESS_APP_USE;

alter table process_run
   add constraint foreign key (formDefId)
      references form_def (formDefId) on delete cascade
      constraint FK_PRORN__R_FORM_DEF;

alter table process_run
   add constraint foreign key (defId)
      references pro_definition (defId)
      constraint FK_PR_R_PD_PRO_DEF;

alter table product
   add constraint foreign key (providerId)
      references provider (providerId) 
      constraint FK_PD_R_PU_PROVIDE;

alter table project
   add constraint foreign key (customerId)
      references customer (customerId) 
      constraint FK_PR_R_CS_CUSTOME;

alter table project
   add constraint foreign key (userId)
      references app_user (userId) 
      constraint FK_PT_R_AU_APP_USE;

alter table project_file
   add constraint foreign key (fileId)
      references file_attach (fileId) 
      constraint FK_PF_R_FA_FILE_AT;

alter table project_file
   add constraint foreign key (projectId)
      references project (projectId) 
      constraint FK_PF_R_PT_PROJECT;

alter table reg_attach
   add constraint foreign key (fileId)
      references file_attach (fileId) on delete cascade
      constraint FK_RA_R_FA_FILE_AT;

alter table reg_attach
   add constraint foreign key (regId)
      references regulation (regId) on delete cascade
      constraint FK_RA_R_GT_REGULAT;

alter table regulation
   add constraint foreign key (proTypeId)
      references global_type (proTypeId) on delete cascade
      constraint FK_RG_R_GT;

alter table relative_user
   add constraint foreign key (userId)
      references app_user (userId) on delete cascade
      constraint FK_RELATIVE_RU_R_AU_APP_USER;

alter table relative_user
   add constraint foreign key (reJobId)
      references relative_job (reJobId) on delete cascade
      constraint FK_RELATIVE_RU_R_RJ_RELATIVE;

alter table report_param
   add constraint foreign key (reportId)
      references report_template (reportId) 
      constraint FK_RP_R_RP_REPORT_;

alter table resume_file
   add constraint foreign key (fileId)
      references file_attach (fileId) 
      constraint FK_RMF_R_F_FILE_AT;

alter table resume_file
   add constraint foreign key (resumeId)
      references resume (resumeId) 
      constraint FK_RMF_R_R_RESUME;

alter table role_fun
   add constraint foreign key (functionId)
      references app_function (functionId) 
      constraint FK_RF_R_AF_APP_FUN;

alter table role_fun
   add constraint foreign key (roleId)
      references app_role (roleId) 
      constraint FK_RF_R_AR_APP_ROL;

alter table role_position
   add constraint foreign key (pos_id)
      references position (pos_id) on delete cascade
      constraint FK_ROL_POS_R_POS;

alter table role_position
   add constraint foreign key (roleId)
      references app_role (roleId) 
      constraint FK_RPOS_R__APP_ROL;

alter table roll_file
   add constraint foreign key (rollId)
      references arch_roll (rollId) on delete cascade
      constraint FK_RF_R_AR;

alter table roll_file
   add constraint foreign key (proTypeId)
      references global_type (proTypeId) on delete cascade
      constraint FK_RF_R_GT;

alter table roll_file_list
   add constraint foreign key (fileId)
      references file_attach (fileId) on delete cascade
      constraint FK_RFL_R_F_FILE_AT;

alter table roll_file_list
   add constraint foreign key (rollFileId)
      references roll_file (rollFileId) on delete cascade
      constraint FK_RFL_R_RF;

alter table run_data
   add constraint foreign key (runId)
      references process_run (runId) on delete cascade
      constraint FK_FD_R_PR;

alter table seal
   add constraint foreign key (fileId)
      references file_attach (fileId) on delete cascade
      constraint FK_SE_R_FA;

alter table short_message
   add constraint foreign key (senderId)
      references app_user (userId) 
      constraint FK_SM_R_AU_APP_USE;

alter table stand_salary_item
   add constraint foreign key (standardId)
      references stand_salary (standardId) 
      constraint FK_SSI_R_S_STAND_S;

alter table subordinate
   add constraint foreign key (dem_id)
      references demension (dem_id) 
      constraint FK_SUBORDINATE_D_DEM;

alter table subordinate
   add constraint foreign key (jobuserId)
      references app_user (userId) 
      constraint FK_SUBORDINATE_J_APPUSER;

alter table subordinate
   add constraint foreign key (userId)
      references app_user (userId) 
      constraint FK_SUBORDINATE_U_APPUSER;

alter table task_sign
   add constraint foreign key (setId)
      references pro_user_set (id) 
      constraint FK_TASK_SIG_TS_R_PRO_NODE_SET;

alter table user_org
   add constraint foreign key (userId)
      references app_user (userId) 
      constraint FK_USER_OR_APP_USE;

alter table user_org
   add constraint foreign key (org_id)
      references organization (org_id) on delete cascade
      constraint FK_USER_ORG_R_ORG;

alter table user_position
   add constraint foreign key (userId)
      references app_user (userId) 
      constraint FK_USER_PO_APP_USE;

alter table user_position
   add constraint foreign key (pos_id)
      references position (pos_id) on delete cascade
      constraint FK_USER_POS_R_POSITION;

alter table user_role
   add constraint foreign key (roleId)
      references app_role (roleId) 
      constraint FK_UR_R_AR;

alter table user_role
   add constraint foreign key (userId)
      references app_user (userId) 
      constraint FK_UR_R_AU;

alter table work_plan
   add constraint foreign key (proTypeId)
      references global_type (proTypeId) 
      constraint FK_WP_R_GT;

alter table work_plan
   add constraint foreign key (userId)
      references app_user (userId) 
      constraint FK_WP_R_AU_APP_USE;

