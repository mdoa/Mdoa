package com.htsoft.core.jbpm.jpdl;

import java.io.InputStream;

import javax.xml.transform.TransformerFactoryConfigurationError;

import org.junit.Test;

import com.htsoft.core.util.FileUtil;

public class JpdlUtilTest extends JpdlUtil {

	@Test
	public void testTransFromFlexXml()
			throws TransformerFactoryConfigurationError, Exception {
		InputStream is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("com/htsoft/core/jbpm/jpdl/jbpm4.xml");
		String xml = FileUtil.inputStream2String(is, "utf-8");
		String str = JpdlUtil.transFromFlexXml( "流程转换", xml);
		System.out.println(str);

	}

}
