package com.htsoft.oa.action.admin;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
 */
import java.util.List;

import javax.annotation.Resource;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.admin.Book;
import com.htsoft.oa.model.admin.BookSn;
import com.htsoft.oa.model.admin.BookType;
import com.htsoft.oa.service.admin.BookService;
import com.htsoft.oa.service.admin.BookSnService;

/**
 * 图书管理Action
 * 
 * @description 图书管理
 * @author csx
 * 
 */
public class BookAction extends BaseAction {
	@Resource
	private BookService bookService;
	@Resource
	private BookSnService bookSnService;

	private Book book;

	private Long bookId;

	private Long typeId;
	private BookType bookType;

	/**
	 * @return the book
	 */
	public Book getBook() {
		return book;
	}

	/**
	 * @param book
	 *            the book to set
	 */
	public void setBook(Book book) {
		this.book = book;
	}

	/**
	 * @return the bookId
	 */
	public Long getBookId() {
		return bookId;
	}

	/**
	 * @param bookId
	 *            the bookId to set
	 */
	public void setBookId(Long bookId) {
		this.bookId = bookId;
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
	 * @return the bookType
	 */
	public BookType getBookType() {
		return bookType;
	}

	/**
	 * @param bookType
	 *            the bookType to set
	 */
	public void setBookType(BookType bookType) {
		this.bookType = bookType;
	}

	/**
	 * 显示列表
	 */
	public String list() {

		QueryFilter filter = new QueryFilter(getRequest());
		List<Book> list = bookService.getAll(filter);
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
				bookService.remove(new Long(id));
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
		Book book = bookService.get(bookId);
		jsonString = mapper.toDataJson(book);
		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		String bookSnNumber = "";
		// String newBookIsbn =
		// getRequest().getParameter("newBookIsbn").toString();
		// String oldBookIsbn =
		// getRequest().getParameter("oldBookIsbn").toString();
		// bookId为空，说明是新增加图书
		if (book.getBookId() == null) {
			// 添加图书时将未借出的数量设置为和图书数量一样
			book.setLeftAmount(book.getAmount());
			bookService.save(book);
			for (int i = 1; i <= book.getAmount(); i++) {
				// 添加图书成功后，根据ISBN和图书数量自动生成每本图书SN号
				BookSn bookSn = new BookSn();
				// 每本书的bookSn号根据书的ISBN号和数量自动生成,
				// 如书的ISBN是123,数量是3,则每本书的bookSn分别是：123-1,123-2,123-3
				bookSnNumber = book.getIsbn() + "-" + i;
				bookSn.setBookId(book.getBookId());
				bookSn.setBookSN(bookSnNumber);
				// 默认书的状态是0表示未借出
				bookSn.setStatus(new Short((short) 0));
				// 添加bookSn信息
				bookSnService.save(bookSn);
			}
		} else {

			bookService.save(book);
			List<BookSn> oldBookSns = bookSnService.findByBookId(book
					.getBookId());
			int i = 1;
			for (; i <= oldBookSns.size(); i++) {
				bookSnService.updateBookSn(book.getIsbn() + "-" + i, oldBookSns
						.get(i - 1).getBookSnId());// /(bookSn, );
			}
			for (; i <= book.getAmount(); i++) {
				BookSn bookSn = new BookSn();
				bookSnNumber = book.getIsbn() + "-" + i;
				bookSn.setBookId(book.getBookId());
				bookSn.setBookSN(bookSnNumber);
				bookSn.setStatus(new Short((short) 0));
				bookSnService.save(bookSn);
			}

		}
		setJsonString("{success:true,bookSnNumber:'" + bookSnNumber + "'}");
		return SUCCESS;
	}

	/**
	 * 增加图书数量时修改图书数量
	 */
	public String updateAmount() {
		Long bookId = Long.valueOf(getRequest().getParameter("bookId"));
		book = bookService.get(bookId);
		int addAmount = Integer
				.parseInt(getRequest().getParameter("addAmount"));
		int amount = book.getAmount() + addAmount;
		BookSn bookSn = null;
		String bookSnNumber = "";
		for (int i = book.getAmount() + 1; i <= book.getAmount() + addAmount; i++) {
			// 添加图书成功后，根据ISBN和图书数量自动生成每本图书SN号
			bookSn = new BookSn();
			// 每本书的bookSn号根据书的ISBN号和数量自动生成,
			// 如书的ISBN是123,数量是3,则每本书的bookSn分别是：123-1,123-2,123-3
			bookSnNumber = book.getIsbn() + "-" + i;
			bookSn.setBookId(book.getBookId());
			bookSn.setBookSN(bookSnNumber);
			// 默认书的状态是0表示未借出
			bookSn.setStatus(new Short((short) 0));
			// 添加bookSn信息
			bookSnService.save(bookSn);
		}
		book.setAmount(amount);
		book.setLeftAmount(book.getLeftAmount() + addAmount);
		bookService.save(book);
		return SUCCESS;
	}

	/**
	 * 删除图书标签后修改图书数量和未借出数量
	 */
	public String updateAmountAndLeftAmount() {
		Long bookId = Long.valueOf(getRequest().getParameter("bookId"));
		book = bookService.get(bookId);
		int amount = book.getAmount() - 1;
		int leftAmount = book.getLeftAmount() - 1;
		book.setAmount(amount);
		book.setLeftAmount(leftAmount);
		bookService.save(book);
		jsonString = mapper.toDataJson(book);
		return SUCCESS;
	}
}
