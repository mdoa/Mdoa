package com.htsoft.oa.action.admin;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
 */
import java.util.List;

import javax.annotation.Resource;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.admin.BoardType;
import com.htsoft.oa.service.admin.BoardTypeService;

/**
 * 会议类型管理Action
 * 
 * @company <a href="http://www.Mendersoft.com">杭州梦德软件有限公司</a>
 * @description 会议类型管理
 * @author YHZ
 * @date 2010-5-25 PM
 * 
 */
public class BoardTypeAction extends BaseAction {

	@Resource
	private BoardTypeService boardTypeService;

	private BoardType boardType;
	private Long typeId;

	/**
	 * @return the boardType
	 */
	public BoardType getBoardType() {
		return boardType;
	}

	/**
	 * @param boardType
	 *            the boardType to set
	 */
	public void setBoardType(BoardType boardType) {
		this.boardType = boardType;
	}

	/**
	 * @return the typeId
	 */
	public Long getTypeId() {
		return typeId;
	}

	/**
	 * @param typeId
	 *            the typeId to set
	 */
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	/**
	 * @description 分页查询
	 */
	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List<BoardType> list = boardTypeService.getAll(filter);
		jsonString = mapper.toPageJson(list, filter.getPagingBean()
				.getTotalItems());
		return SUCCESS;

	}

	/**
	 * @description 批量删除
	 */
	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		for (String id : ids) {
			boardTypeService.remove(new Long(id));
		}
		setJsonString("{success:true}");
		return SUCCESS;
	}

	/**
	 * @description 单条数据删除
	 */
	public String del() {
		boardTypeService.remove(typeId);
		setJsonString("{success:true}");
		return SUCCESS;
	}

	/**
	 * @description 保存
	 */
	public String save() {
		boardTypeService.save(boardType);
		setJsonString("{success:true}");
		return SUCCESS;
	}

	/**
	 * @description 详细信息
	 */
	public String get() {
		BoardType boardType = boardTypeService.get(typeId);
		jsonString = mapper.toDataJson(boardType);
		return SUCCESS;
	}

}
