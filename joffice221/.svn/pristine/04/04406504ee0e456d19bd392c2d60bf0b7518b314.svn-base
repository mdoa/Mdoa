package com.htsoft.oa.action.admin;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
 */
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.admin.Book;
import com.htsoft.oa.model.admin.BookBorRet;
import com.htsoft.oa.model.admin.BookSn;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.service.admin.BookBorRetService;
import com.htsoft.oa.service.admin.BookService;
import com.htsoft.oa.service.admin.BookSnService;

/**
 * 
 * 图书借阅管理Action
 * 
 * @description 图书借阅管理
 * @author csx
 * 
 */
public class BookBorRetAction extends BaseAction {
	@Resource
	private BookBorRetService bookBorRetService;
	private BookBorRet bookBorRet;
	@Resource
	private BookSnService bookSnService;
	@Resource
	private BookService bookService;
	private Long recordId;
	private Long bookSnId;

	public Long getBookSnId() {
		return bookSnId;
	}

	public void setBookSnId(Long bookSnId) {
		this.bookSnId = bookSnId;
	}

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public BookBorRet getBookBorRet() {
		return bookBorRet;
	}

	public void setBookBorRet(BookBorRet bookBorRet) {
		this.bookBorRet = bookBorRet;
	}

	/**
	 * 显示列表
	 */
	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List<BookBorRet> list = bookBorRetService.getAll(filter);

		jsonString = mapper.toPageJson(list, filter.getPagingBean()
				.getTotalItems());
		return SUCCESS;
	}

	/**
	 * 显示已借出图书列表
	 */
	public String listBorrow() {
		QueryFilter filter = new QueryFilter(getRequest());
		List<BookBorRet> list = bookBorRetService.getAll(filter);

		jsonString = mapper.toPageJson(list, filter.getPagingBean()
				.getTotalItems());
		return SUCCESS;
	}

	/**
	 * 显示已归还图书列表
	 */
	public String listReturn() {
		QueryFilter filter = new QueryFilter(getRequest());
		List<BookBorRet> list = bookBorRetService.getAll(filter);

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
				bookBorRetService.remove(new Long(id));
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
		BookBorRet bookBorRet = bookBorRetService.get(recordId);
		jsonString = mapper.toDataJson(bookBorRet);
		return SUCCESS;

	}

	/**
	 * 添加及保存图书借阅操作
	 */
	public String saveBorrow() {
		bookBorRet.setBorrowTime(new Date());
		AppUser user = ContextUtil.getCurrentUser();
		bookBorRet.setRegisterName(user.getFullname());
		bookBorRetService.save(bookBorRet);
		BookSn bookSn = bookSnService.get(bookBorRet.getBookSnId());
		// 执行借出操作时将图书的状态改为1（表示借出状态）
		bookSn.setStatus(new Short((short) 1));
		bookSnService.save(bookSn);
		Book book = bookService.get(bookSn.getBookId());
		// 当图书借出操作成功后将未借出的图书数量-1
		book.setLeftAmount(book.getLeftAmount() - 1);
		bookService.save(book);
		setJsonString("{success:true}");
		return SUCCESS;
	}

	/**
	 * 添加及保存图书归还操作
	 */
	public String saveReturn() {
		// 用系统日期保存图书的归还日期
		bookBorRet.setLastReturnTime(new Date());
		AppUser user = ContextUtil.getCurrentUser();
		bookBorRet.setRegisterName(user.getFullname());
		bookBorRetService.save(bookBorRet);
		BookSn bookSn = bookSnService.get(bookBorRet.getBookSnId());
		// 执行借出操作时将图书的状态改为0（表示已归还）
		bookSn.setStatus(new Short((short) 0));
		bookSnService.save(bookSn);
		Book book = bookService.get(bookSn.getBookId());
		// 当图书归还操作成功后将未借出的图书数量+1
		book.setLeftAmount(book.getLeftAmount() + 1);
		bookService.save(book);
		setJsonString("{success:true}");
		return SUCCESS;
	}

	/**
	 * 根据bookSnId查出所借图书的借出时间和应还时间
	 */
	public String getBorRetTime() {
		// 得到传来的bookSnId
		Long bookSnId = Long.valueOf(getRequest().getParameter("bookSnId"));
		BookBorRet bookBorRet = bookBorRetService.getByBookSnId(bookSnId);
		jsonString = mapper.toDataJson(bookBorRet);
		return SUCCESS;
	}
}
