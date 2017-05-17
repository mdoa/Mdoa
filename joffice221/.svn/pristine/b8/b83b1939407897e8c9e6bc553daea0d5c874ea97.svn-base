package com.htsoft.test.flow;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import com.htsoft.test.BaseTestCase;
import com.htsoft.core.json.JacksonMapper;
import com.htsoft.oa.dao.flow.ProDefinitionDao;
import com.htsoft.oa.dao.flow.ProTypeDao;
import com.htsoft.oa.model.flow.ProDefinition;
import com.htsoft.oa.model.flow.ProType;
import com.htsoft.oa.model.flow.ProUserSet;

public class ProDefinitionDaoTestCase extends BaseTestCase {
	@Resource
	private ProDefinitionDao proDefinitionDao;
	
	@Resource
	private ProTypeDao proTypeDao;
	
	//@Test
	//@Rollback(false)
	public void add(){
		
		ProDefinition pro=new ProDefinition();
		
		pro.setDefXml("xml");
		pro.setDescription("descriptin");
		pro.setName("vtest");
		pro.setDrawDefXml("drawXml");
		pro.setCreatetime(new Date());
		
//	    ProType proType=proTypeDao.get(new Long(1));
//	    
//		pro.setProType(proType);
//		pro.setDeployId("1");
		proDefinitionDao.save(pro);
		
//		ProDefinition proDefinition=new ProDefinition();
//		proDefinitionDao.save(proDefinition);
	}
	
	//@Test
	public void get(){
//		ProDefinition proDefinition=proDefinitionDao.get(2l);
//		ProType proType=proDefinition.getProType();
//		System.out.println("proType:" + proType.getTypeName());
		
		//Gson gson=new Gson();
		//JSONSerializer serializer=JsonUtil.getJSONSerializer("createtime");
		
//		JSONSerializer serializes=new JSONSerializer().exclude("proType.typeName");
//		
//		System.out.println("josn:" + serializer.prettyPrint(proDefinition));
		
		//serializer.transform(new DateTransformer("yyyy-MM-dd HH:mm:ss"), "createtime");

		List<ProDefinition> list=proDefinitionDao.getAll();
		
		//System.out.println("list:" + serializer.deepSerialize(list));
		
//		Type type=new TypeToken<List<ProDefinition>>(){}.getType();
//		StringBuffer buff = new StringBuffer();
//
//		Gson gson=new GsonBuilder().setPrettyPrinting().create();
//		buff.append(gson.toJson(list, type));
//	
//		System.out.println("josn:" + buff.toString());
	}
	
	@Test
	public void update(){
		String data = "{\"id\":22,\"defId\":10032,\"deployId\":\"30043\",\"nodeName\":\"办公室主任审批\",\"userType\":2,\"uids\":\"1,10000\",\"unames\":\"超级管理员,zxh\",\"compType\":1}]";
		JacksonMapper mapper = new JacksonMapper(true);
		ProUserSet[] proUserSets = mapper.toObject(data, ProUserSet[].class);
		System.out.println(proUserSets);
	}
}