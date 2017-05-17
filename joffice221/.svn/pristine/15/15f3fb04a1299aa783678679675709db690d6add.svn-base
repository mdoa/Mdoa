package com.htsoft.oa.action.system;

/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.json.JacksonMapper;
import com.htsoft.core.json.tree.JsonTree;
import com.htsoft.core.util.AppUtil;
import com.htsoft.core.util.BeanUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.system.RelativeJob;
import com.htsoft.oa.service.system.RelativeJobService;

/**
 * @description 相对岗位管理
 * @class RelativeJobAction
 * @author YHZ
 * @data 2010-12-13AM
 * @company www.jee-soft.cn
 */
public class RelativeJobAction extends BaseAction {
	@Resource
	private RelativeJobService relativeJobService;

	private RelativeJob relativeJob;

	private Long reJobId;

	public Long getReJobId() {
		return reJobId;
	}

	public void setReJobId(Long reJobId) {
		this.reJobId = reJobId;
	}

	public RelativeJob getRelativeJob() {
		return relativeJob;
	}

	public void setRelativeJob(RelativeJob relativeJob) {
		this.relativeJob = relativeJob;
	}

	/**
	 * 显示列表
	 */
	public String list() {

		QueryFilter filter = new QueryFilter(getRequest());
		List<RelativeJob> list = relativeJobService.getAll(filter);

		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd HH:mm:ss");
		jsonString = mapper.toPageJson(list, filter.getPagingBean()
				.getTotalItems());

		return SUCCESS;
	}

	/**
	 * 加载相对岗位信息
	 */
	public String treeLoad() {
		List<RelativeJob> list = relativeJobService.getAll();
		String method = getRequest().getParameter("method");
		jsonString = JsonTree.generate(getResult(list, method), null, false);
		logger.info("tree json:" + jsonString);
		return SUCCESS;
	}

	/**
	 * 产生树的结果
	 * 
	 * @param list
	 * @param method
	 * @return
	 */
	private List<Object> getResult(List<RelativeJob> list, String method) {
		List<Object> dataList = new ArrayList<Object>();

		if (!StringUtils.isNotEmpty(method)) {
			HashMap<String, Object> rootNode = new HashMap<String, Object>();
			rootNode.put("id", "0");
			rootNode.put("text", AppUtil.getCompanyName());
			rootNode.put("parentId", null);
			dataList.add(rootNode);
		}
		for (RelativeJob job : list) {
			HashMap<String, Object> dataRecord = new HashMap<String, Object>();
			dataRecord.put("id", job.getReJobId().toString());
			dataRecord.put("text", job.getJobName());
			String parentId = job.getParent().toString();
			if (StringUtils.isNotEmpty(method) && job.getParent() != null
					&& job.getParent().longValue() == 0L) {
				parentId = null;
			}
			dataRecord.put("parentId", parentId);
			dataList.add(dataRecord);
		}

		return dataList;
	}

	/**
	 * 批量删除
	 * 
	 * @return
	 */
	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null)
			for (String id : ids)
				relativeJobService.remove(new Long(id));
		jsonString = "{success:true}";
		return SUCCESS;
	}

	@Override
	public String delete() {
		relativeJobService.remove(reJobId);
		jsonString = "{success:true}";
		return SUCCESS;
	}

	/**
	 * 显示详细信息
	 * 
	 * @return
	 */
	public String get() {
		RelativeJob relativeJob = relativeJobService.get(reJobId);

		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd HH:mm:ss");
		jsonString = mapper.toDataJson(relativeJob);

		return SUCCESS;
	}

	/**
	 * 编辑操作
	 */
	public String save() {
		if (relativeJob.getReJobId() == null) {
			add();
		} else {
			RelativeJob orgRelativeJob = relativeJobService.get(relativeJob
					.getReJobId());
			try {
				BeanUtil.copyNotNullProperties(orgRelativeJob, relativeJob);
				relativeJobService.save(orgRelativeJob);
			} catch (Exception ex) {
				logger.error(ex.getMessage());
			}
		}
		setJsonString("{success:true}");
		return SUCCESS;

	}

	/**
	 * 添加操作
	 */
	private String add() {
		// 1.判断是否为顶级结点，设置深度
		boolean isParent = relativeJob.getParent() < 1;
		if (isParent)
			relativeJob.setDepath(2);
		else {
			Integer parentDepath = relativeJobService.get(
					relativeJob.getParent()).getDepath(); // 父节点深度
			relativeJob.setDepath(parentDepath + 1);
		}
		// 2.保存：jobName,parent,同时获取 :reJobId
		RelativeJob newRelativeJob = relativeJobService.save(relativeJob);
		// 3.设置：depath,path,jobCode
		if (isParent) { // 顶级节点
			newRelativeJob.setPath("0." + newRelativeJob.getReJobId() + ".");
		} else { // 有父节点
			// 获取父节点Path
			String parentPath = relativeJobService.get(
					newRelativeJob.getParent()).getPath();
			newRelativeJob.setPath(parentPath + newRelativeJob.getReJobId()
					+ ".");
		}
		newRelativeJob.setJobCode("0");
		// 4.修改数据
		relativeJobService.save(newRelativeJob);
		setJsonString("{success:true}");
		return SUCCESS;
	}

	// ------------------私有方法------------------//
	/**
	 * 根据父节点编号,查询对应的子节点信息
	 */
	private String findChildren(Long childId) {
		StringBuffer sb = new StringBuffer("");
		List<RelativeJob> list = relativeJobService.findByParentId(childId);
		if (list.isEmpty() || list.size() == 0) {
			sb.append("leaf:true},");
			return sb.toString();
		} else {
			sb.append("children:[");
			for (RelativeJob job : list) {
				sb.append("{id:'" + job.getReJobId() + "',text:'"
						+ job.getJobName() + "',");
				sb.append(findChildren(job.getReJobId()));
			}
			sb.deleteCharAt(sb.length() - 1);
			sb.append("]},");
			return sb.toString();
		}
	}
}
