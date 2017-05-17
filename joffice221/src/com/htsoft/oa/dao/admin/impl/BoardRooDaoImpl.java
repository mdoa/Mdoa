package com.htsoft.oa.dao.admin.impl;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.admin.BoardRooDao;
import com.htsoft.oa.model.admin.BoardRoo;

/**
 * @description BoardRoo数据操作实现接口类
 * @author YHZ
 * @data 2010-9-20 PM
 * 
 */
@SuppressWarnings("unchecked")
public class BoardRooDaoImpl extends BaseDaoImpl<BoardRoo> implements
		BoardRooDao {

	public BoardRooDaoImpl() {
		super(BoardRoo.class);
	}

}
