package com.htsoft.oa.dao.admin.impl;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
*/

import java.util.List;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.dao.admin.BookSnDao;
import com.htsoft.oa.model.admin.BookSn;

public class BookSnDaoImpl extends BaseDaoImpl<BookSn> implements BookSnDao{

	public BookSnDaoImpl() {
		super(BookSn.class);
	}
	@Override
	public List<BookSn> findByBookId(final Long bookId) {
		// TODO Auto-generated method stub
		final String hql = "from BookSn b where b.book.bookId=? order by b.bookSnId";
		Object[] params ={bookId};
		return findByHql(hql, params);
	}
	@Override
	public List<BookSn> findBorrowByBookId(final Long bookId) {
		// TODO Auto-generated method stub
		final String hql = "from BookSn b where b.book.bookId=? and b.status=0 order by b.bookSnId";
		Object[] params ={bookId};
		return findByHql(hql, params);
	}
	@Override
	public List<BookSn> findReturnByBookId(final Long bookId) {
		// TODO Auto-generated method stub
		final String hql = "from BookSn b where b.book.bookId=? and b.status=1 order by b.bookSnId";
		Object[] params ={bookId};
		return findByHql(hql, params);
	}
	@Override
	public void updateBookSn(final String newBookIsbn,final Long bookSnId) {
		// TODO Auto-generated method stub
		final String hql = "update BookSn b set b.bookSN = ? where b.bookSnId=?";
		Object[] params ={newBookIsbn,bookSnId};
		update(hql, params);
	}
}