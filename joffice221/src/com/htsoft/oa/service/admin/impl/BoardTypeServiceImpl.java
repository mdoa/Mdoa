package com.htsoft.oa.service.admin.impl;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.admin.BoardTypeDao;
import com.htsoft.oa.model.admin.BoardType;
import com.htsoft.oa.service.admin.BoardTypeService;

/**
 * @description BoardTypeServiceImpl
 * @author YHZ
 * @date 2010-5-25 PM
 * 
 */
public class BoardTypeServiceImpl extends BaseServiceImpl<BoardType> implements
		BoardTypeService {

	@SuppressWarnings("unused")
	private BoardTypeDao dao;

	public BoardTypeServiceImpl(BoardTypeDao dao) {
		super(dao);
		this.dao = dao;
	}

}
