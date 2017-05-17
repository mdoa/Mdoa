package com.htsoft.test.info;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.htsoft.core.json.JacksonMapper;
import com.htsoft.oa.dao.info.NewsCommentDao;
import com.htsoft.oa.dao.info.NewsDao;
import com.htsoft.oa.dao.info.SectionDao;
import com.htsoft.oa.model.info.News;
import com.htsoft.oa.model.info.NewsComment;
import com.htsoft.oa.model.info.Section;
import com.htsoft.test.BaseTestCase;

public class NewsDaoTestCase extends BaseTestCase{
	@Resource
	private NewsDao newsDao;
	@Resource
	private SectionDao  sectionDao;
	@Resource
	private  NewsCommentDao  newsCommentDao;

	@Test
	public void getAll() throws JsonGenerationException, JsonMappingException, IOException{
		List<News> list= newsDao.getAll();
		JacksonMapper mapper=new JacksonMapper(true,"yyyy-MM-dd HH:mm:ss");
		String jsonString = mapper.toPageJson(list, 3);
		System.err.println(jsonString);
	}	
	
	//@Test
	public void getAllSection() throws JsonGenerationException, JsonMappingException, IOException{
		List<Section> list= sectionDao.getAll();
		JacksonMapper mapper=new JacksonMapper(true,"yyyy-MM-dd HH:mm:ss");
		String jsonString = mapper.toPageJson(list, 3);
		System.err.println("Section"+jsonString);
	}	
	//@Test
	public void getAllNewsComment() throws JsonGenerationException, JsonMappingException, IOException{
		List<NewsComment> list= newsCommentDao.getAll();
		JacksonMapper mapper=new JacksonMapper(true,"yyyy-MM-dd HH:mm:ss");
		String jsonString = mapper.toPageJson(list, 3);
		System.err.println(jsonString);
	}	
}
