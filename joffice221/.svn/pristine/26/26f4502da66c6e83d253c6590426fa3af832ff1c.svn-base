package com.htsoft.oa.service.flow.impl;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   -- http://www.Mendersoft.com
 *  2017/1/21
 */
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.dom4j.Element;
import org.jbpm.api.ProcessDefinition;
import org.springframework.beans.BeanUtils;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.core.util.BeanUtil;
import com.htsoft.core.util.XmlUtil;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.dao.flow.ProDefinitionDao;
import com.htsoft.oa.dao.flow.ProNodeSetDao;
import com.htsoft.oa.model.flow.FieldRights;
import com.htsoft.oa.model.flow.FormDefMapping;
import com.htsoft.oa.model.flow.ProDefinition;
import com.htsoft.oa.model.flow.ProNodeSet;
import com.htsoft.oa.model.flow.ProUserSet;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.system.GlobalType;
import com.htsoft.oa.service.bpm.ILog.factory.BpmFactory;
import com.htsoft.oa.service.flow.FieldRightsService;
import com.htsoft.oa.service.flow.FormDefMappingService;
import com.htsoft.oa.service.flow.JbpmService;
import com.htsoft.oa.service.flow.ProDefinitionService;
import com.htsoft.oa.service.flow.ProHandleCompService;
import com.htsoft.oa.service.flow.ProUserSetService;
import com.htsoft.oa.service.system.GlobalTypeService;

public class ProDefinitionServiceImpl extends BaseServiceImpl<ProDefinition>
		implements ProDefinitionService {

	@Resource
	private GlobalTypeService globalTypeService;
	@Resource
	private JbpmService jbpmService;
	@Resource
	private ProUserSetService proUserSetService;
	@Resource
	private ProHandleCompService proHandleCompService;
	@Resource
	private FormDefMappingService formDefMappingService;
	
	@Resource
	private FieldRightsService fieldRightsService;
	
	private ProDefinitionDao dao;
	@Resource
	private ProNodeSetDao proNodeSetDao;

	public ProDefinitionServiceImpl(ProDefinitionDao dao) {
		super(dao);
		this.dao = dao;
	}

	public ProDefinition getByDeployId(String deployId) {
		return dao.getByDeployId(deployId);
	}

	public ProDefinition getByName(String name) {
		return dao.getByName(name);
	}

	@Override
	public List<ProDefinition> getByRights(AppUser curUser,
			ProDefinition proDefinition, QueryFilter filter) {
		return dao.getByRights(curUser, proDefinition, filter);
	}

	@Override
	public boolean checkNameByVo(ProDefinition proDefinition) {
		return dao.checkNameByVo(proDefinition);
	}


	@Override
	public boolean checkDefKeyIfExist(String defKey) {
		return dao.checkDefKeyIfExist(defKey);
	}

	@Override
	public boolean checkProcessNameByVo(ProDefinition proDefinition) {
		return dao.checkProcessNameByVo(proDefinition);
	}

	@Override
	public List<ProDefinition> findRunningPro(ProDefinition proDefinition,
			Short runstate, PagingBean pb) {
		return dao.findRunningPro(proDefinition, runstate, pb);
	}

	/**
	 * 保存流程信息
	 */
	@Override
	public String defSave(ProDefinition proDefinition, Boolean deploy)
			throws Exception {
		logger.info("...eneter defSave......");
		String msg = "";
		// 转化xml文件格式
		if (proDefinition.getDrawDefXml() != null
				&& !proDefinition.getDrawDefXml().equals("")) {
			String text = change(proDefinition.getDrawDefXml(),
					proDefinition.getProcessName());
			proDefinition.setDefXml(text);
			logger.debug("解析的JBPM对应的xml文件：\n" + text);
		}
		// 流程类型
		Long proTypeId = proDefinition.getProTypeId();
		if (proTypeId != null) {
			GlobalType proType = globalTypeService.get(proTypeId);
			proDefinition.setProType(proType);
		}

		// 检查流程名称的唯一性
		if (!checkNameByVo(proDefinition)) {
			// 假如不唯一
			return "流程名称(系统中使用)已存在,请重新填写.";
		}
		if (!checkProcessNameByVo(proDefinition)) {
			// 假如不唯一
			return "流程名称(定义中使用)已存在,请重新填写.";
		}
		if (deploy) {
			saveOrUpdate(proDefinition, deploy);
			msg = "true";
			logger.debug("流程设计【保存并发布】操作返回结果：" + msg);
		} else {
			proDefinition.setCreatetime(new Date());
			save(proDefinition);
			msg = "true";
			logger.debug("流程设计【保存】操作返回结果：" + msg);
		}
		return msg;
	}

	/**
	 * @description 转变xml文件的格式
	 * @param str
	 * @param processName
	 *            JBPM流程Key
	 * @return
	 */
	private String change(String xml, String processName) {
		String text = "";
		if (xml != null && !xml.equals("")) {
			org.dom4j.Document document = XmlUtil.stringToDocument(xml);
			Element element = document.getRootElement();
			BpmFactory factory = new BpmFactory(document);
			@SuppressWarnings("unchecked")
			Iterator<Element> it = element.elements().iterator();
			text = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n <process name=\""
					+ processName + "\" xmlns=\"http://jbpm.org/4.4/jpdl\">";
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
	 * 更新从版本的parnentId及isMain属性
	 * 
	 * @param parentId
	 *            父ID
	 * @param defKey
	 *            定义Key
	 * @param maxVersionNo
	 *            最大版本号
	 */
	public void updateSubVersion(Long parentId, String defKey,
			Integer maxVersionNo) {
		dao.updateSubVersion(parentId, defKey, maxVersionNo);
	}

	/**
	 * 保存及更新流程定义 (non-Javadoc)
	 * 
	 * @see com.htsoft.oa.service.flow.ProDefinitionService#saveOrUpdate(com.htsoft.oa.model.flow.ProDefinition,
	 *      boolean)
	 */
	@Override
	public ProDefinition saveOrUpdate(ProDefinition proDefinition,
			boolean isDeploy) throws Exception {
		ProDefinition oldProDefinition = null;
		if (proDefinition.getDefId() == null) {// 新保存流程定义
			proDefinition.setCreatetime(new Date());
		} else {// 旧的流程进行流程信息保存
			oldProDefinition = get(proDefinition.getDefId());
			// BeanUtil.copyNotNullProperties(oldProDefinition,proDefinition);
			// this.save(oldProDefinition);
		}
		// 是否发布新版本
		if (isDeploy) {
			// 保存个历史版本
			if (oldProDefinition != null
					&& oldProDefinition.getDefId().longValue() > 0L) {
				ProDefinition newProDefinition = new ProDefinition();
				BeanUtil.copyNotNullProperties(newProDefinition,oldProDefinition);
				newProDefinition.setDefId(null);
				newProDefinition.setIsMain(ProDefinition.NOT_MAIN);
				// 保存个历史版本
				this.merge(newProDefinition);
				
			}
			ProcessDefinition processDefinition = jbpmService.deployDefinition(proDefinition.getDefXml());
			// 设置流程定义与jbpm的流程定义的关联
			proDefinition.setDeployId(processDefinition.getDeploymentId());
			proDefinition.setPdId(processDefinition.getId());
			proDefinition.setDefKey(processDefinition.getKey());

			proDefinition.setProcessName(processDefinition.getName());
			proDefinition.setNewVersion(processDefinition.getVersion());

			// 设置为主版本
			proDefinition.setIsMain(ProDefinition.MAIN);
			// 设置父节点为NULL
			proDefinition.setParentId(null);

		}
		proDefinition.setCreatetime(new Date());
		proDefinition.setUpdatetime(new Date());
		if (isDeploy && oldProDefinition != null) {
			String oldDeployId = oldProDefinition.getDeployId();
			String deployId = proDefinition.getDeployId();
			//保存用户设置信息
			List<ProUserSet> proUserSet = proUserSetService.findByDefIdDeployId(oldProDefinition.getDefId(), oldDeployId);
			if(proUserSet.size()>0){
				for(ProUserSet pUserSet : proUserSet){
					ProUserSet newProUserSet = new ProUserSet(); 
					BeanUtils.copyProperties(pUserSet,newProUserSet);
					newProUserSet.setDeployId(deployId);
					newProUserSet.setJbpmDefId(proDefinition.getPdId());
					newProUserSet.setId(null);
					proUserSetService.save(newProUserSet);
				}
			}
			//保存流程干预设置信息
			proHandleCompService.copyNewConfig(oldDeployId,deployId);
			this.merge(proDefinition);
			////保存表单设置信息
			FormDefMapping mapping = formDefMappingService.getByDeployId(oldDeployId);
			if(mapping!=null){
				FormDefMapping newMapping = new FormDefMapping(); 
				BeanUtils.copyProperties(mapping,newMapping);
				newMapping.setDeployId(deployId);
				newMapping.setVersionNo(proDefinition.getNewVersion());
				newMapping.setMappingId(null);
				newMapping.setProDefinition(proDefinition);
				newMapping.setFieldRightss(null);
				formDefMappingService.save(newMapping);
			}
			
		} else {
			this.save(proDefinition);
		}

		// 生成proNodeSet对应的任务节点设置记录 TODO
		List<String> taskNodeList = jbpmService
				.getTaskNodeFromXml(proDefinition.getDefXml());
		for (String taskName : taskNodeList) {
			ProNodeSet proNodeSet = new ProNodeSet();
			proNodeSet.setProDefinition(proDefinition);
			proNodeSet.setDeployId(proDefinition.getDeployId());
			proNodeSet.setJbpmDefId(proDefinition.getPdId());
			proNodeSet.setNodeName(taskName);
			proNodeSetDao.save(proNodeSet);
		}

		// 更新历史版本的父ID
		if (isDeploy) {
			this.updateSubVersion(proDefinition.getDefId(),proDefinition.getDefKey(), proDefinition.getNewVersion());
		}
		return proDefinition;
	}

	@Override
	public List<ProDefinition> findAllByParentId(Long parentId, PagingBean pb) {
		return dao.findAllByParentId(parentId, pb);
	}

	@Override
	public List<ProDefinition> findByRights(AppUser curUser,
			String processName, String description, PagingBean pb) {
		return dao.findByRights(curUser, processName, description, pb);
	}

	@Override
	public ProDefinition update(ProDefinition proDefinition, boolean isEdit)throws Exception {
		ProDefinition oldProDefinition = get(proDefinition.getDefId());
		ProcessDefinition processDefinition = jbpmService.deployDefinition(proDefinition.getDefXml());
		proDefinition.setDeployId(processDefinition.getDeploymentId());
		proDefinition.setPdId(processDefinition.getId());
		proDefinition.setDefKey(processDefinition.getKey());
		proDefinition.setProcessName(processDefinition.getName());
		proDefinition.setCreatetime(new Date());
		proDefinition.setIsMain(ProDefinition.MAIN);
		String oldDeployId = oldProDefinition.getDeployId();
		String deployId = proDefinition.getDeployId();
		//保存用户设置信息
		List<ProUserSet> proUserSet = proUserSetService.findByDefIdDeployId(oldProDefinition.getDefId(), oldDeployId);
		if(proUserSet.size()>0){
			for(ProUserSet pUserSet : proUserSet){
				ProUserSet newProUserSet = new ProUserSet(); 
				BeanUtils.copyProperties(pUserSet,newProUserSet);
				newProUserSet.setDeployId(deployId);
				newProUserSet.setJbpmDefId(proDefinition.getPdId());
				newProUserSet.setId(null);
				proUserSetService.save(newProUserSet);
			}
		}
		//保存流程干预设置信息
		proHandleCompService.copyNewConfig(oldDeployId,deployId);
		this.merge(proDefinition);
		////保存表单设置信息
		FormDefMapping mapping = formDefMappingService.getByDeployId(oldDeployId);
		FormDefMapping newMapping = new FormDefMapping(); 
		if(mapping!=null){
			BeanUtils.copyProperties(mapping,newMapping);
			newMapping.setDeployId(deployId);
			newMapping.setVersionNo(proDefinition.getNewVersion());
			newMapping.setMappingId(null);
			newMapping.setProDefinition(proDefinition);
			newMapping.setFieldRightss(null);
			formDefMappingService.save(newMapping);
		}
		//保存表单权限数据
		if(mapping!=null){
			List<FieldRights> fieldRights = fieldRightsService.getByMappingId(mapping.getMappingId());
			if(fieldRights.size()>0){
				for(FieldRights rights : fieldRights){
					FieldRights newFieldRights = new FieldRights();
					newFieldRights.setMappingId(newMapping.getMappingId());
					newFieldRights.setFieldId(rights.getFieldId());
					newFieldRights.setTaskName(rights.getTaskName());
					newFieldRights.setReadWrite(rights.getReadWrite());
					fieldRightsService.save(newFieldRights);
				}
			}
		}
		// 生成proNodeSet对应的任务节点设置记录 
		List<String> taskNodeList = jbpmService.getTaskNodeFromXml(proDefinition.getDefXml());
		for (String taskName : taskNodeList) {
			ProNodeSet proNodeSet = new ProNodeSet();
			proNodeSet.setProDefinition(proDefinition);
			proNodeSet.setDeployId(proDefinition.getDeployId());
			proNodeSet.setJbpmDefId(proDefinition.getPdId());
			proNodeSet.setNodeName(taskName);
			proNodeSetDao.save(proNodeSet);
		}
		return proDefinition;
	}

}