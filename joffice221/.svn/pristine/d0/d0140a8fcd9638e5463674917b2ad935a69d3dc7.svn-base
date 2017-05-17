package com.htsoft.oa.action.flow;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
 */
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.htsoft.core.jbpm.jpdl.Node;
import com.htsoft.core.json.JacksonMapper;
import com.htsoft.core.web.action.BaseAction;

import com.htsoft.oa.model.flow.ProDefinition;
import com.htsoft.oa.model.flow.ProUserAssign;
import com.htsoft.oa.service.flow.JbpmService;
import com.htsoft.oa.service.flow.ProDefinitionService;
import com.htsoft.oa.service.flow.ProUserAssignService;

/**
 * 
 * @author csx
 * 
 */
public class ProUserAssignAction extends BaseAction {
	@Resource
	private ProUserAssignService proUserAssignService;
	@Resource
	private JbpmService jbpmService;
	@Resource
	private ProDefinitionService proDefinitionService;

	public void setJbpmService(JbpmService jbpmService) {
		this.jbpmService = jbpmService;
	}

	private ProUserAssign proUserAssign;

	private Long assignId;

	public Long getAssignId() {
		return assignId;
	}

	public void setAssignId(Long assignId) {
		this.assignId = assignId;
	}

	public ProUserAssign getProUserAssign() {
		return proUserAssign;
	}

	public void setProUserAssign(ProUserAssign proUserAssign) {
		this.proUserAssign = proUserAssign;
	}

	/**
	 * 显示授权的列表
	 */
	public String list() {
		String defId = getRequest().getParameter("defId");
		
		ProDefinition proDefinition = proDefinitionService.get(new Long(defId));

		List<Node> nodes = jbpmService.getTaskNodesByDefId(new Long(defId));

		List<ProUserAssign> nodesAssignList = proUserAssignService
				.getByDeployId(proDefinition.getDeployId());

		List<ProUserAssign> proUserAssignList = new ArrayList<ProUserAssign>();
		
		for (int i = 0; i < nodes.size(); i++) {
			String nodeName = nodes.get(i).getName();
			for (int j = 0; j < nodesAssignList.size(); j++) {
				ProUserAssign assign = nodesAssignList.get(j);
				if (nodeName.equals(assign.getActivityName())) {
					proUserAssignList.add(assign);
					break;
				}
			}
		}

		JacksonMapper mapper = new JacksonMapper(true);
		if (proUserAssignList.size() > 0) {	
			this.setJsonString(mapper.toResultJson(proUserAssignList));
		}

		return SUCCESS;
	}

	/**
	 * 显示详细信息
	 * 
	 * @return
	 */
	public String get() {
		ProUserAssign proUserAssign = proUserAssignService.get(assignId);
		JacksonMapper mapper = new JacksonMapper(true);
		this.setJsonString(mapper.toDataJson(proUserAssign));
		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		String data = getRequest().getParameter("data");
		// logger.info("data:"+data);
		if (StringUtils.isNotEmpty(data)) {
			Gson gson = new Gson();
			ProUserAssign[] assigns = gson
					.fromJson(data, ProUserAssign[].class);
			for (ProUserAssign assign : assigns) {
				if (assign.getAssignId() == -1) {// 若为-1，则代表尚未持久化,主要用于防止自动绑值（gson.fromJson(data,
													// ProUserAssign[].class)）出错;
					assign.setAssignId(null);
				}
				proUserAssignService.save(assign);
				// 添加用户自己的处理代码
				// jbpmService.addAssignHandler(assign);
			}
		}

		return SUCCESS;
	}
}
