package com.htsoft.oa.core.jbpm.jpdl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


import com.htsoft.core.util.Dom4jUtil;
import com.htsoft.core.util.FileUtil;

public class JpdlUtil {
	public static String transFromFlexXml(String name,String flexXml) throws Exception {
			Map<String,String> map=new HashMap<String, String>();
			map.put("name", name);
			String xlstPath=FileUtil.getClassesPath() + "jpdl/jpdl.xsl".replace("/", File.separator);
			flexXml=flexXml.trim();

			String str=Dom4jUtil.transXmlByXslt(flexXml, xlstPath, map);

			str=str.replace("&lt;", "<").replace("&gt;", ">").replace("xmlns=\"\"", "").replace("&amp;", "&");
			return str;
	}
}
