package com.htsoft.oa.service.document;

/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
 */
import java.util.List;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.document.PaintTemplate;

public interface PaintTemplateService extends BaseService<PaintTemplate> {
    /**
     * 按Key查找模板
     * 
     * @param templateKey
     * @return
     */
    public List<PaintTemplate> getByKey(String templateKey);

    /**
     * 该Key是否在存
     * 
     * @param templateKey
     * @return
     */
    public boolean isKeyExist(String templateKey);

    /**
     * 查找某个模板，除去id为eptTemplateId对应的记录
     * 
     * @param templateKey
     * @param eptTemplateId
     * @return
     */
    public List<PaintTemplate> getByKeyExceptId(String templateKey,
	    Long eptTemplateId);
}
