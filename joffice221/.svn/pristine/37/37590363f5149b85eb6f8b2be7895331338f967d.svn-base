package com.htsoft.oa.action.admin;

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
import com.htsoft.core.json.tree.JsonTree;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.admin.Book;
import com.htsoft.oa.model.admin.BookType;
import com.htsoft.oa.service.admin.BookService;
import com.htsoft.oa.service.admin.BookTypeService;

/**
 * 图书类型管理Action
 * 
 * @description 图书类型管理
 * @author csx
 * 
 */
public class BookTypeAction extends BaseAction {
	@Resource
	private BookTypeService bookTypeService;
	private BookType bookType;
	@Resource
	private BookService bookService;

	private Long typeId;

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public BookType getBookType() {
		return bookType;
	}

	public void setBookType(BookType bookType) {
		this.bookType = bookType;
	}

	/**
	 * 显示列表
	 */
	public String list() {

		QueryFilter filter = new QueryFilter(getRequest());
		List<BookType> list = bookTypeService.getAll(filter);
		jsonString = mapper.toPageJson(list, filter.getPagingBean()
				.getTotalItems());
		return SUCCESS;
	}

	/**
	 * 显示图书类别树（下拉选框）
	 * 
	 * @return
	 */
	public String tree() {
		List<BookType> list = bookTypeService.getAll();
		String method = getRequest().getParameter("method");
		jsonString = JsonTree.generate(getResult(list, method), null, false);
		return SUCCESS;
	}

	/**
	 * 产生树的结果
	 * 
	 * @param list
	 * @param method
	 * @return
	 */
	private List<Object> getResult(List<BookType> list, String method) {

		List<Object> dataList = new ArrayList<Object>();
		String parentId = null;
		if (!StringUtils.isNotEmpty(method)) {
			parentId = "0";
			HashMap<String, Object> dataRecord = new HashMap<String, Object>();
			dataRecord.put("id", parentId);
			dataRecord.put("text", "图书类别");
			dataRecord.put("parentId", null);
			dataList.add(dataRecord);

		}
		for (BookType bookType : list) {
			HashMap<String, Object> dataRecord = new HashMap<String, Object>();
			dataRecord.put("id", bookType.getTypeId().toString());
			dataRecord.put("text", bookType.getTypeName());
			dataRecord.put("parentId", parentId);
			dataList.add(dataRecord);
		}

		return dataList;
	}

	/**
	 * 删除图书类别
	 * 
	 * @return
	 */
	public String remove() {
		Long typeId = Long.valueOf(getRequest().getParameter("typeId"));
		setBookType(bookTypeService.get(typeId));
		if (bookType != null) {
			QueryFilter filter = new QueryFilter(getRequest());
			filter.addFilter("Q_bookType.typeId_L_EQ", typeId.toString());
			List<Book> list = bookService.getAll(filter);
			if (list.size() > 0) {
				jsonString = "{success:false,message:'该类型下还有图书，请将图书移走后再删除！'}";
				return SUCCESS;
			}
			bookTypeService.remove(typeId);
		}
		jsonString = "{success:true}";
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
				QueryFilter filter = new QueryFilter(getRequest());
				filter.addFilter("Q_bookType.typeId_L_EQ", id);
				List<Book> list = bookService.getAll(filter);
				if (list.size() > 0) {
					jsonString = "{success:false,message:'该类型下还有图书，请将图书移走后再删除！'}";
					return SUCCESS;
				}
				bookTypeService.remove(new Long(id));
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
		BookType bookType = bookTypeService.get(typeId);
		jsonString = mapper.toDataJson(bookType);
		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		bookTypeService.save(bookType);
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
