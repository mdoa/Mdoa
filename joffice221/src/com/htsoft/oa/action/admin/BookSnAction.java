package com.htsoft.oa.action.admin;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
 */
import java.util.List;

import javax.annotation.Resource;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.admin.BookSn;
import com.htsoft.oa.service.admin.BookSnService;

/**
 * 图书索引管理Action
 * 
 * @description 图书索引管理
 * @author csx
 * 
 */
public class BookSnAction extends BaseAction {
	@Resource
	private BookSnService bookSnService;
	private BookSn bookSn;

	private Long bookSnId;

	public Long getBookSnId() {
		return bookSnId;
	}

	public void setBookSnId(Long bookSnId) {
		this.bookSnId = bookSnId;
	}

	public BookSn getBookSn() {
		return bookSn;
	}

	public void setBookSn(BookSn bookSn) {
		this.bookSn = bookSn;
	}

	/**
	 * 显示列表
	 */
	public String list() {

		QueryFilter filter = new QueryFilter(getRequest());
		List<BookSn> list = bookSnService.getAll(filter);
		jsonString = mapper.toPageJson(list, filter.getPagingBean()
				.getTotalItems());
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
				bookSnService.remove(new Long(id));
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
		BookSn bookSn = bookSnService.get(bookSnId);
		jsonString = mapper.toDataJson(bookSn);
		return SUCCESS;
	}

	/**
	 * 通过bookId找到图书的boonSn
	 * 
	 * @return
	 */
	public String getSn() {
		Long bookId = Long.valueOf(getRequest().getParameter("bookId"));
		List<BookSn> list = bookSnService.findByBookId(bookId);
		jsonString = mapper.toJson(list);
		return SUCCESS;
	}

	/**
	 * 通过bookId找到还没有借出去的图书的boonSn
	 * 
	 * @return
	 */
	public String getBorrowSn() {
		StringBuffer buff = new StringBuffer("[");
		Long bookId = 0L;
		bookId = Long.valueOf(getRequest().getParameter("bookId"));
		List<BookSn> list = bookSnService.findBorrowByBookId(bookId);
		for (BookSn bookSn : list) {
			buff.append("['" + bookSn.getBookSnId() + "','"
					+ bookSn.getBookSN() + "'],");
		}
		if (list.size() != 0) {
			buff.deleteCharAt(buff.length() - 1);
		}
		buff.append("]");
		setJsonString(buff.toString());
		return SUCCESS;
	}

	/**
	 * 通过bookId找到已借出去还未归还的图书的boonSn
	 * 
	 * @return
	 */
	public String getReturnSn() {
		StringBuffer buff = new StringBuffer("[");
		Long bookId = 0L;
		bookId = Long.valueOf(getRequest().getParameter("bookId"));
		List<BookSn> list = bookSnService.findReturnByBookId(bookId);
		for (BookSn bookSn : list) {
			buff.append("['" + bookSn.getBookSnId() + "','"
					+ bookSn.getBookSN() + "'],");
		}
		if (list.size() != 0) {
			buff.deleteCharAt(buff.length() - 1);
		}
		buff.append("]");
		setJsonString(buff.toString());
		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		bookSnService.save(bookSn);
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
