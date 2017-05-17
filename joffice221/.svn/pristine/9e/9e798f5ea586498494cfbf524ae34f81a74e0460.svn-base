package com.htsoft.oa.action.communicate;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.json.JacksonMapper;
import com.htsoft.core.json.tree.JsonTree;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.communicate.PhoneBook;
import com.htsoft.oa.model.communicate.PhoneGroup;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.service.communicate.PhoneBookService;
import com.htsoft.oa.service.communicate.PhoneGroupService;

/**
 * 
 * @author csx
 * 
 */
public class PhoneGroupAction extends BaseAction {
	@Resource
	private PhoneGroupService phoneGroupService;
	private PhoneGroup phoneGroup;
	@Resource
	private PhoneBookService phoneBookService;
	private Long groupId;

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public PhoneGroup getPhoneGroup() {
		return phoneGroup;
	}

	public void setPhoneGroup(PhoneGroup phoneGroup) {
		this.phoneGroup = phoneGroup;
	}

	/**
	 * 显示列表
	 */
	public String list() {
		String isPublic = getRequest().getParameter("isPublic");
		List<PhoneGroup> list = new ArrayList<PhoneGroup>();
		if (StringUtils.isNotEmpty(isPublic) && "true".equals(isPublic)) {
			list = phoneGroupService.getPublicAll();
		} else {
			list = phoneGroupService.getAll(ContextUtil.getCurrentUserId());
		}
		String method = getRequest().getParameter("method");
		jsonString = JsonTree.generate(getResult(list, method), null, false);
		return SUCCESS;
	}

	/**
	 * 产生树的结果
	 * @param list
	 * @param method
	 * @return
	 */
	private List<Object> getResult(List<PhoneGroup> list, String method) {

		List<Object> dataList = new ArrayList<Object>();
		String parentId = null;
		if (!StringUtils.isNotEmpty(method)) {
			parentId = "0";
			HashMap<String, Object> dataRecord = new HashMap<String, Object>();
			dataRecord.put("id", parentId);
			dataRecord.put("text", "联系人分组");
			dataRecord.put("parentId", null);
			dataList.add(dataRecord);

		}
		for (PhoneGroup phoneGroup : list) {
			HashMap<String, Object> dataRecord = new HashMap<String, Object>();
			dataRecord.put("id", phoneGroup.getGroupId().toString());
			dataRecord.put("text", phoneGroup.getGroupName());
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
		if (ids != null) {
			for (String id : ids) {
				Long groupId = new Long(id);
				PhoneGroup phoneGroup = phoneGroupService.get(groupId);
				phoneGroupService.remove(groupId);
				List<PhoneGroup> list = phoneGroupService.findBySnDown(
						phoneGroup.getSn(),
						phoneGroup.getAppUser().getUserId(),
						phoneGroup.getIsPublic());
				for (PhoneGroup pg : list) {
					pg.setSn(pg.getSn() - 1);
					phoneGroupService.save(pg);
				}
			}
		}

		jsonString = "{success:true}";

		return SUCCESS;
	}

	public String count() {
		QueryFilter filter = new QueryFilter(getRequest());
		List<PhoneBook> pbList = phoneBookService.getAll(filter);
		setJsonString("{success:true,count:" + pbList.size() + "}");
		return SUCCESS;
	}

	/**
	 * 显示详细信息
	 * 
	 * @return
	 */
	public String get() {
		PhoneGroup phoneGroup = phoneGroupService.get(groupId);
		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd");
		jsonString = mapper.toDataJson(phoneGroup);
		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		String isPublic = getRequest().getParameter("phoneGroup.isPublic");

		AppUser appUser = ContextUtil.getCurrentUser();
		if (phoneGroup.getSn() == null || "null".equals(phoneGroup.getSn())) {
			Integer sn = 0;
			if (PhoneGroup.IS_PUBLIC == Short.parseShort(isPublic)) {
				sn = phoneGroupService.findPublicLastSn();
			} else {
				sn = phoneGroupService.findLastSn(appUser.getUserId());
			}
			if (sn == null)
				sn = 0;
			phoneGroup.setSn(sn + 1);
		}
		phoneGroup.setAppUser(appUser);
		phoneGroupService.save(phoneGroup);
		setJsonString("{success:true}");
		return SUCCESS;
	}

	public String move() {
		short isPublic = Short
				.parseShort(getRequest().getParameter("isPublic"));
		String strOpt = getRequest().getParameter("optId");
		String strGroupId = getRequest().getParameter("groupId");
		Long userId = ContextUtil.getCurrentUserId();
		if (StringUtils.isNotEmpty(strGroupId)) {
			Integer opt = Integer.parseInt(strOpt);
			Long groupId = Long.parseLong(strGroupId);
			phoneGroup = phoneGroupService.get(groupId);
			Integer sn = phoneGroup.getSn();
			if (opt == 1) {/* 上移 */
				if (sn > 1) {
					PhoneGroup pg = phoneGroupService.findBySn(sn - 1, userId,
							isPublic);
					pg.setSn(sn);
					phoneGroupService.save(pg);
					phoneGroup.setSn(sn - 1);
					phoneGroupService.save(phoneGroup);
				}
			}
			if (opt == 2) {// move down
				if (sn < phoneGroupService.findLastSn(userId)) {
					PhoneGroup pg = phoneGroupService.findBySn(sn + 1, userId,
							isPublic);
					pg.setSn(sn);
					phoneGroup.setSn(sn + 1);
					phoneGroupService.save(pg);
					phoneGroupService.save(phoneGroup);
				}
			}
			if (opt == 3) {// move top
				if (sn > 1) {
					List<PhoneGroup> list = phoneGroupService.findBySnUp(sn,
							userId, isPublic);
					for (PhoneGroup pg : list) {
						pg.setSn(pg.getSn() + 1);
						phoneGroupService.save(pg);
					}
					phoneGroup.setSn(1);
					phoneGroupService.save(phoneGroup);
				}
			}
			if (opt == 4) {
				if (sn < phoneGroupService.findLastSn(userId)) {
					List<PhoneGroup> list = phoneGroupService.findBySnDown(sn,
							userId, isPublic);
					for (PhoneGroup pg : list) {
						pg.setSn(pg.getSn() - 1);
						phoneGroupService.save(pg);
					}
					if (PhoneGroup.IS_PUBLIC == isPublic) {
						phoneGroup
								.setSn(phoneGroupService.findPublicLastSn() + 1);
					} else {
						phoneGroup
								.setSn(phoneGroupService.findLastSn(userId) + 1);
					}

					phoneGroupService.save(phoneGroup);
				}
			}
			setJsonString("{success:true}");
		} else {
			setJsonString("{success:false}");
		}
		return SUCCESS;
	}
}
