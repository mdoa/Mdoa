package com.htsoft.oa.action.admin;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
 */
import java.util.List;

import javax.annotation.Resource;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.json.JacksonMapper;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.admin.BoardRoo;
import com.htsoft.oa.service.admin.BoardRooService;

/**
 * 会议室管理Action
 * 
 * @company <a href="http://www.Mendersoft.com">杭州梦德软件有限公司</a>
 * @description 会议室
 * @data 2010-9-20 PM
 * 
 */
public class BoardRooAction extends BaseAction {

	@Resource
	private BoardRooService boardRooService;

	private BoardRoo boardRoo;
	private Long roomId;

	/**
	 * @return the boardRoo
	 */
	public BoardRoo getBoardRoo() {
		return boardRoo;
	}

	/**
	 * @param boardRoo
	 *            the boardRoo to set
	 */
	public void setBoardRoo(BoardRoo boardRoo) {
		this.boardRoo = boardRoo;
	}

	/**
	 * @return the roomId
	 */
	public Long getRoomId() {
		return roomId;
	}

	/**
	 * @param roomId
	 *            the roomId to set
	 */
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	/**
	 * @description 分页查询所有会议室信息
	 */
	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List<BoardRoo> list = boardRooService.getAll(filter);

		jsonString = mapper.toPageJson(list, filter.getPagingBean()
				.getTotalItems());
		return SUCCESS;
	}

	/**
	 * @description 批量删除数据操作
	 */
	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				boardRooService.remove(Long.valueOf(id));
			}
		}
		jsonString = "{success:true}";
		return SUCCESS;
	}

	/**
	 * @description 单条数据参数
	 */
	public String del() {
		boardRooService.remove(roomId);
		jsonString = "{success:true}";
		return SUCCESS;
	}

	/**
	 * @description 新增操作
	 */
	public String save() {
		boardRooService.save(boardRoo);
		jsonString = "{success:true,msg:'保存成功'}";
		return SUCCESS;
	}

	/**
	 * @description 根据RoomId获取对象的详细信息
	 */
	public String get() {
		BoardRoo boardRoo = boardRooService.get(roomId);

		JacksonMapper mapper = new JacksonMapper(true);
		jsonString = mapper.toDataJson(boardRoo);
		return SUCCESS;
	}
}
