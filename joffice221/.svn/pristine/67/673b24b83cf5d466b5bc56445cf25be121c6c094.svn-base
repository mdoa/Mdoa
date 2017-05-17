package com.htsoft.oa.service.admin.impl;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   -- http://www.Mendersoft.com
 *  2017/1/21
*/
import java.util.List;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.dao.admin.BookSnDao;
import com.htsoft.oa.model.admin.BookSn;
import com.htsoft.oa.service.admin.BookSnService;

public class BookSnServiceImpl extends BaseServiceImpl<BookSn> implements BookSnService{
	private BookSnDao dao;
	
	public BookSnServiceImpl(BookSnDao dao) {
		super(dao);
		this.dao=dao;
	}
	@Override
	public List<BookSn> findByBookId(Long bookId) {
		// TODO Auto-generated method stub
		return dao.findByBookId(bookId);
	}
	@Override
	public List<BookSn> findBorrowByBookId(Long bookId) {
		// TODO Auto-generated method stub
		return dao.findBorrowByBookId(bookId);
	}
	@Override
	public List<BookSn> findReturnByBookId(Long bookId) {
		// TODO Auto-generated method stub
		return dao.findReturnByBookId(bookId);
	}
	@Override
	public void updateBookSn(String newBookIsbn,Long bookId) {
		// TODO Auto-generated method stub
		dao.updateBookSn(newBookIsbn,bookId);
	}
	
}