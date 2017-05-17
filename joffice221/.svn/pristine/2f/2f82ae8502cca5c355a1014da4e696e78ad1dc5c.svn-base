package com.htsoft.oa.action.flow;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
 */

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.jbpm.jpdl.JpdlUtil;
import com.htsoft.core.jbpm.jpdl.Node;
import com.htsoft.core.json.JacksonMapper;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.util.PinyinUtil;
import com.htsoft.core.util.XmlUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.flow.FormDefMapping;
import com.htsoft.oa.model.flow.FormTemplate;
import com.htsoft.oa.model.flow.ProDefinition;
import com.htsoft.oa.model.flow.ProcessRun;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.system.GlobalType;
import com.htsoft.oa.service.bpm.ILog.factory.BpmFactory;
import com.htsoft.oa.service.flow.FormDefMappingService;
import com.htsoft.oa.service.flow.FormTemplateService;
import com.htsoft.oa.service.flow.JbpmService;
import com.htsoft.oa.service.flow.ProDefinitionService;
import com.htsoft.oa.service.flow.ProcessRunService;
import com.htsoft.oa.service.system.GlobalTypeService;

/**
 * @description 流程定义
 * @class ProDefinitionAction
 * @author csx
 * @updater YHZ
 * @company www.jee-soft.cn
 * @data 2010-12-28PM
 */
public class ProDefinitionAction extends BaseAction {
	@Resource
	private ProDefinitionService proDefinitionService;
	@Resource
	private GlobalTypeService globalTypeService;
	@Resource
	private JbpmService jbpmService;
	@Resource
	private FormDefMappingService formDefMappingService;
	@Resource
	private FormTemplateService formTemplateService;
	@Resource
	private ProcessRunService processRunService; // 运行中的流程管理

	private ProDefinition proDefinition;

	private Long defId;

	public Long getDefId() {
		return defId;
	}

	public void setDefId(Long defId) {
		this.defId = defId;
	}

	public ProDefinition getProDefinition() {
		return proDefinition;
	}

	public void setProDefinition(ProDefinition proDefinition) {
		this.proDefinition = proDefinition;
	}

	/**
	 * 显示列表
	 */
	public String list() {

		QueryFilter filter = new QueryFilter(getRequest());

		String typeId = getRequest().getParameter("typeId");
		
		//获取查询中的流程名字
		String processName = getRequest().getParameter("Q_name_S_LK");
		//获取查询中的流程描述
		String description = getRequest().getParameter("Q_description_S_LK");
		
		PagingBean pb=getInitPagingBean();

		filter.addFilter("Q_isMain_SN_EQ", String.valueOf(ProDefinition.MAIN));
		filter.addSorted("defId", "desc");
		List<ProDefinition> list = null;
		int totalItems = 0;
		if (StringUtils.isNotEmpty(typeId) && !"0".equals(typeId)) {
			// 有类型ID,说明该用户有该类型的权限,查出该类型下的所有流程定义
			GlobalType proType = globalTypeService.get(new Long(typeId));
			if(proType!=null){
				filter.addFilter("Q_proType.path_S_LK", proType.getPath());
			}
			list = proDefinitionService.getAll(filter);
		} else {
			AppUser curUser = ContextUtil.getCurrentUser();
			if (curUser.isSupperManage()) {
				// 假如该用户为超级管理员角色,则该用户可看到所有流程定义
				list = proDefinitionService.getAll(filter);
				totalItems += filter.getPagingBean().getTotalItems();
			} else {
				// 非管理员角色的用户需要通过权限过虑流程
				list = proDefinitionService.findByRights(curUser,processName,description,pb);
				totalItems +=pb.getTotalItems();
			}
		}

		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd HH:mm:ss");
		jsonString = mapper.toPageJson(list, totalItems);

		return SUCCESS;
	}

	/**
	 * 在流程设计器中通过 流程分类ID或者流程标题查询流程列表
	 * @return
	 */
	public String getFlowList() {
		String typeId = getRequest().getParameter("typeId");
		String name = getRequest().getParameter("word");
		getRequest().setAttribute("searchAll", "true");
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addFilter("Q_isMain_SN_EQ", String.valueOf(proDefinition.MAIN));
		filter.addFilter("Q_status_SN_EQ", String.valueOf(proDefinition.STATUS_ENABLE));

		List<ProDefinition> list = null;
		if (StringUtils.isNotEmpty(name)) {
			filter.addFilter("Q_name_S_LK", name);
		} else if(!typeId.equals("0")){
			// 有类型ID,说明该用户有该类型的权限,查出该类型下的所有流程定义
			GlobalType proType = globalTypeService.get(new Long(typeId));
			filter.addFilter("Q_proType.path_S_LK", proType.getPath());
		}
		list = proDefinitionService.getAll(filter);
		StringBuffer msg = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?><Result>");
		for (ProDefinition proDefinition : list) {
			String typeName=proDefinition.getProType()==null?"未分类":proDefinition.getProType().getTypeName();
			msg.append("<item name=\"" + proDefinition.getName() + "\" key=\"" + proDefinition.getDefKey() + "\" type=\"" + typeName + "\"></item>");
		}
		msg.append("</Result>");
		setJsonString(msg.toString());
		return SUCCESS;
	}
	
	/**
	 * 通过流程名称获取流程key
	 * @return
	 */
	public String getDefKeyByName(){
		String name = getRequest().getParameter("name");
		if(StringUtils.isEmpty(name))return SUCCESS;
		
		String pingyin = PinyinUtil.getPinyinJianPinToUpperCase(name);
		String key = pingyin;
		int count=0;
		do{
			key = pingyin+(count==0?"":count);
			count++;
		}
		while(proDefinitionService.checkDefKeyIfExist(key));		
		
		setJsonString(key);
		return SUCCESS;
	}

	/**
	 * 批量删除
	 * 
	 * @return
	 */
	public String multiDel() {

		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				// 删除流程的定义，就会删除流程的实例，表单的数据及Jbpm的流程相关的内容
				// proDefinitionService.remove(new Long(id));
				jbpmService.doUnDeployProDefinition(new Long(id));
			}
		}

		jsonString = "{success:true}";

		return SUCCESS;
	}

	/**
	 * 显示详细信息
	 * 
	 * @return
	 */
	public String get() {

		if (defId != null) {
			proDefinition = proDefinitionService.get(defId);
		} else {
			proDefinition = new ProDefinition();
			String proTypeId = getRequest().getParameter("proTypeId");
			if (StringUtils.isNotEmpty(proTypeId)) {
				GlobalType proType = globalTypeService.get(new Long(proTypeId));
				proDefinition.setProType(proType);
			}
		}

		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd HH:mm:ss");
		jsonString = mapper.toDataJson(proDefinition);

		return SUCCESS;
	}

	/**
	 * 获取在线编辑器数据
	 * 
	 * @return
	 */
	public String flexGet() {
		if (defId != null) {
			proDefinition = proDefinitionService.get(defId);
		} else {
			proDefinition = new ProDefinition();
			String proTypeId = getRequest().getParameter("proTypeId");
			if (StringUtils.isNotEmpty(proTypeId)) {
				GlobalType proType = globalTypeService.get(new Long(proTypeId));
				proDefinition.setProType(proType);
			}
		}

		StringBuffer msg = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?><Result>");
		// defId
		msg.append("<defId>" + proDefinition.getDefId() + "</defId>");
		// drawDefXml
		msg.append("<drawDefXml>" + proDefinition.getDrawDefXml() + "</drawDefXml>");

		if (proDefinition.getProType() != null) {
			// proTypeName
			msg.append("<proTypeName>" + proDefinition.getProType().getTypeName() + "</proTypeName>");
			// proTypeId
			msg.append("<proTypeId>" + proDefinition.getProType().getProTypeId() + "</proTypeId>");
		}
		// name
		msg.append("<name>" + proDefinition.getName() + "</name>");

		// processName
		msg.append("<processName>" + proDefinition.getProcessName() + "</processName>");

		// status
		msg.append("<status>" + proDefinition.getStatus() + "</status>");

		// description
		msg.append("<description>" + proDefinition.getDescription() + "</description>");

		// newVersion
		msg.append("<newVersion>" + proDefinition.getNewVersion() + "</newVersion>");
		msg.append("</Result>");
		setJsonString(msg.toString());
		return SUCCESS;
	}

	/**
	 * flex提交
	 * 
	 * @throws Exception
	 */
	public String flexDefSave() throws Exception {
		boolean deploy = Boolean.parseBoolean(getRequest().getParameter("deploy"));
		boolean isEdit = Boolean.parseBoolean(getRequest().getParameter("isEdit"));
	//	boolean isEditCurrentVersion = Boolean.parseBoolean(getRequest().getParameter("isEditCurrentVersion"));
		// 转化xml文件格式
		if (proDefinition.getDrawDefXml() != null && !proDefinition.getDrawDefXml().equals("")) {
			String text = JpdlUtil.transFromFlexXml(proDefinition.getProcessName(), proDefinition.getDrawDefXml());
			proDefinition.setDefXml(text);
			logger.debug("解析的JBPM对应的xml文件：\n" + text);
		}
		// 检查流程名称的唯一性
		if (!isEdit&&!proDefinitionService.checkNameByVo(proDefinition)) {
			// 假如不唯一
			setJsonString("流程名称(系统中使用)已存在,请重新填写.");
			return SUCCESS;
		}
		if (!isEdit&&!proDefinitionService.checkProcessNameByVo(proDefinition)) {
			// 假如不唯一
			setJsonString("流程名称(定义中使用)已存在,请重新填写.");
			return SUCCESS;
		}
		if (deploy) {
			save();
		} else {
			Long proTypeId = proDefinition.getProTypeId();
			if (proTypeId != null) {
				GlobalType proType = globalTypeService.get(proTypeId);
				proDefinition.setProType(proType);
			}else{
				setJsonString("true");
			}
			try {
				if(isEdit){
					//调用编辑方法
					proDefinitionService.update(proDefinition,isEdit);
				}else{
					proDefinition.setCreatetime(new Date());
					proDefinition.setIsMain(ProDefinition.MAIN);
					proDefinitionService.save(proDefinition);
				}
			}catch (Exception ex) {
				ex.printStackTrace();
				logger.error(ex.getMessage());
				setJsonString("{success:编辑流程有错：设计流程定义中不能带有>或=等字符或mysql版第一次编辑时流程key需要修改一次!}");
				return SUCCESS;
			}
			setJsonString("true");
		}
		return SUCCESS;
	}

	public String defSave() {
		return custSave();
	}

	private String custSave() {
		logger.info("...eneter defSave......");
		boolean deploy = Boolean.parseBoolean(getRequest().getParameter("deploy"));
		// 转化xml文件格式
		if (proDefinition.getDrawDefXml() != null && !proDefinition.getDrawDefXml().equals("")) {
			String text = convertFlexXmlToJbpmXml(proDefinition.getDrawDefXml(), proDefinition.getProcessName());
			proDefinition.setDefXml(text);
			logger.debug("解析的JBPM对应的xml文件：\n" + text);
		}

		// 检查流程名称的唯一性
		if (!proDefinitionService.checkNameByVo(proDefinition)) {
			// 假如不唯一
			setJsonString("{success:false,msg:'流程名称(系统中使用)已存在,请重新填写.'}");
			return SUCCESS;
		}
		if (!proDefinitionService.checkProcessNameByVo(proDefinition)) {
			// 假如不唯一
			setJsonString("{success:false,msg:'流程名称(定义中使用)已存在,请重新填写.'}");
			return SUCCESS;
		}
		if (deploy) {// 保存并发布
			save();
		} else {
			Long proTypeId = proDefinition.getProTypeId();
			if (proTypeId != null) {
				GlobalType proType = globalTypeService.get(proTypeId);
				proDefinition.setProType(proType);
			}
			proDefinition.setCreatetime(new Date());
			proDefinition.setIsMain(ProDefinition.MAIN);
			proDefinitionService.save(proDefinition);
			setJsonString("{success:true}");
		}
		return SUCCESS;
	}

	/**
	 * @description 转变xml文件的格式
	 * @param str
	 * @param processName
	 *            JBPM流程Key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private String convertFlexXmlToJbpmXml(String xml, String processName) {
		String text = "";
		if (xml != null && !xml.equals("")) {
			org.dom4j.Document document = XmlUtil.stringToDocument(xml);
			Element element = document.getRootElement();
			BpmFactory factory = new BpmFactory(document);
			Iterator<Element> it = element.elements().iterator();
			text = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n <process name=\"" + processName + "\" xmlns=\"http://jbpm.org/4.4/jpdl\">";
			while (it.hasNext()) {
				Element el = it.next();
				String str = factory.getInfo(el, el.getName());
				text += str;
			}
			text += "</process>";
		}
		return text;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		// 设置流程分类
		Long proTypeId = proDefinition.getProTypeId();
		if (proTypeId != null) {
			GlobalType proType = globalTypeService.get(proTypeId);
			proDefinition.setProType(proType);
		}else{
			setJsonString("{success:当前流程类型是全部，请选择对应的流程类型!");
			return SUCCESS;
		}
		// 是否需要发布
		String deploy = getRequest().getParameter("deploy");

		boolean isDeploy = "true".equals(deploy) ? true : false;
		try {
			proDefinitionService.saveOrUpdate(proDefinition, isDeploy);
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex.getMessage());
			setJsonString("{success:编辑流程有错：设计流程定义中不能带有>或=等字符或mysql版在编辑时流程key需要修改一次!}");
			return SUCCESS;
		}
		setJsonString("{success:true}");
		return SUCCESS;
	}

	/**
	 * 通过defId取得某个流程是使用表单模板还是在线的定制模板
	 * 
	 * @return
	 */
	public String formTemp() {
		StringBuffer sb = new StringBuffer("{success:true");
		Short isUseTemplate = FormDefMapping.NOT_USE_TEMPLATE;

		ProDefinition proDefinition = proDefinitionService.get(defId);

		if (proDefinition != null && proDefinition.getDeployId() != null) {
			FormDefMapping fdm = formDefMappingService.getByDeployId(proDefinition.getDeployId());
			if (fdm != null) {
				isUseTemplate = fdm.getUseTemplate();
				sb.append(",mappingId:").append(fdm.getMappingId());
			}
		}
		sb.append(",isUseTemplate:" + isUseTemplate + "}");
		setJsonString(sb.toString());
		return SUCCESS;
	}

	/**
	 * 保存formMapping
	 * 
	 * @return
	 */
	public String saveFm() {

		ProDefinition pd = proDefinitionService.get(defId);
		String useTemplate = getRequest().getParameter("useTemplate");

		if (pd != null && pd.getDeployId() != null) {
			FormDefMapping mapping = formDefMappingService.getByDeployId(pd.getDeployId());
			Short isUseTemplate = FormDefMapping.NOT_USE_TEMPLATE;
			if ("true".equals(useTemplate)) {
				isUseTemplate = FormDefMapping.USE_TEMPLATE;
			}
			if (mapping != null) {
				mapping.setUseTemplate(isUseTemplate);
			} else {
				mapping = new FormDefMapping();
				mapping.setUseTemplate(isUseTemplate);
				mapping.setDefId(pd.getDefId());
				mapping.setDeployId(pd.getDeployId());
				mapping.setVersionNo(pd.getNewVersion());
			}
			formDefMappingService.save(mapping);

			if ("true".equals(useTemplate)) {
				List<FormTemplate> formTemplateList = formTemplateService.getByMappingId(mapping.getMappingId());
				if (formTemplateList.size() == 0) {
					// 为该流程映射添加所有表单定义
					List<Node> nodes = jbpmService.getFormNodesByDeployId(pd.getDeployId());
					List<String> nodeNames = new ArrayList<String>();
					for (Node node : nodes) {
						nodeNames.add(node.getName());
					}
					formTemplateService.batchAddDefault(nodeNames, mapping);
				}
			}
			setJsonString("{success:true,mappingId:" + mapping.getMappingId() + "}");
		}

		return SUCCESS;
	}

	/**
	 * 取得表单映射 TODO
	 * 
	 * @return
	 */
	public String getFormMapping() {
		ProDefinition pd = proDefinitionService.get(defId);
		if (pd != null && pd.getDeployId() != null) {
			FormDefMapping mapping = formDefMappingService.getByDeployId(pd.getDeployId());
			if (mapping != null) {

			}
		}

		return SUCCESS;
	}

	/**
	 * 修改流程状态
	 */
	public String update() {
		ProDefinition orgProDefinition = proDefinitionService.get(proDefinition.getDefId());
		orgProDefinition.setStatus(proDefinition.getStatus());

		proDefinitionService.save(orgProDefinition);

		setJsonString("{success:true}");
		return SUCCESS;
	}

	/**
	 * 保存其它参数 //TODO
	 * 
	 * @return
	 */
	public String saveParam() {
		ProDefinition orgProDefinition = proDefinitionService.get(proDefinition.getDefId());
		orgProDefinition.setSkipFirstNode(proDefinition.getSkipFirstNode());
		proDefinitionService.save(orgProDefinition);
		setJsonString("{success" + ":true}");
		return SUCCESS;
	}

	/**
	 * 判断该流程是否可以重新设置表单
	 */
	public String checkRun() {
		// 1.判断流程是否存为运行中的流程[process_run]
		boolean isRun = processRunService.checkRun(defId); // 存在
		String msg = "{success:true}";
		if (isRun)// 存在
			msg = "{failure:true,msg:'对不起，该流程正在运行不能设置，请谅解！'}";
		setJsonString(msg);
		return SUCCESS;
	}

	public String inList() {
		PagingBean pb =getInitPagingBean();
		List<ProDefinition> list=proDefinitionService.findRunningPro(proDefinition, ProcessRun.RUN_STATUS_RUNNING, pb);
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(pb.getTotalItems()).append(",result:[");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        for(ProDefinition pd:list){
        	 buff.append("{defId:'").append(pd.getDefId())
        	 .append("',subTotal:").append(processRunService.countRunningProcess(pd.getDefId()))
        	 .append(",typeName:'").append(pd.getProType()==null?"":pd.getProType().getTypeName())
        	 .append("',name:'").append(pd.getName())
        	 .append("',createtime:'").append(pd.getCreatetime()==null?"":sdf.format(pd.getCreatetime()))
        	 .append("',deployId:'").append(pd.getDeployId())
        	 .append("',status:").append(pd.getStatus()).append("},");
        }
        if(list.size()>0){
        	buff.deleteCharAt(buff.length()-1);
        }
		buff.append("]}");
		jsonString = buff.toString();
		return SUCCESS;
	}

	/**
	 * 历史版本
	 * 
	 * @return
	 */
	public String history() {
		Long parentId = new Long(getRequest().getParameter("parentId"));
		PagingBean pb = getInitPagingBean();
		List<ProDefinition> list = proDefinitionService.findAllByParentId(parentId, pb);

		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd HH:mm:ss");
		jsonString = mapper.toPageJson(list, pb.getTotalItems());
		return SUCCESS;
	}

}
