package com.htsoft.oa.action.bpm;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.flow.ProDefinition;
import com.htsoft.oa.service.flow.ProDefinitionService;
import com.htsoft.oa.service.system.GlobalTypeService;
import com.htsoft.oa.model.system.GlobalType;

/**
 * @description 在线流程设计
 * @class BpmDesignAction
 * @extends BaseAction
 * @author YHZ
 * @company www.jee-soft.cn
 * @createtime w2011-5-4AM
 * 
 */
public class BpmDesignAction extends BaseAction {

	@Resource
	private ProDefinitionService proDefinitionService;

	@Resource
	private GlobalTypeService globalTypeService;

	private Long defId; // defId流程定义Id

	private String name;

	public Long getDefId() {
		return defId;
	}

	public void setDefId(Long defId) {
		this.defId = defId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String execute() throws Exception {
		HttpServletRequest request  = getRequest();
		String defId=request.getParameter("defId");
		if(StringUtils.isNotEmpty(defId)){
			ProDefinition proDefintion=proDefinitionService.get(new Long(defId));
			request.setAttribute("proDefinition", proDefintion);
			request.setAttribute("title", proDefintion.getName());
		}else{
			request.setAttribute("title", "未命名");
		}
		Long uId = ContextUtil.getCurrentUserId();
		StringBuffer sb=new StringBuffer("<type id='0' label='全部'>");
		List<GlobalType> flowTypeList=globalTypeService.getByCatKey("FLOW");
		Long rootId=0L;//根节点ID
		if(flowTypeList.size()>0){
			for(GlobalType gt : flowTypeList){
				if(!rootId.equals(gt.getParentId())) continue;
				sb.append("<type id='" + gt.getProTypeId() + "' label='" + gt.getTypeName() + "'>");
				sb.append(getTypesXml(gt.getProTypeId(),flowTypeList));
				sb.append("</type>");
			}
		}
		sb.append("</type>");
		request.setAttribute("record", sb.toString());
		request.setAttribute("uId", uId);
		
		return SUCCESS;
	}
	
	/**
	 * 递归构建流程类型xml
	 * @param parentId
	 * @param list
	 * @return
	 */
	private String getTypesXml(Long parentId,List<GlobalType> list){
		StringBuffer sb = new StringBuffer("");
		if(list.size()>0){
			for(GlobalType gt : list){
				if(gt.getParentId().equals(parentId)){
					sb.append("<type id='" + gt.getProTypeId() + "' label='" + gt.getTypeName() + "'>");
					sb.append(getTypesXml(gt.getProTypeId(),list));
					sb.append("</type>");
				}
			}
		}
		return sb.toString();
	}

}
