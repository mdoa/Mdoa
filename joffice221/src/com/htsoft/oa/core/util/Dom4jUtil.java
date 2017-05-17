package com.htsoft.oa.core.util;

import java.io.File;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.DocumentResult;
import org.dom4j.io.DocumentSource;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.SAXValidator;
import org.dom4j.io.XMLWriter;
import org.dom4j.util.XMLErrorHandler;

/**
 * xml操作类。<br>
 * 包括xml的读取，xml的转换等。
 * 
 * @author hotent
 * 
 */
public class Dom4jUtil {
	private static final Log logger = LogFactory.getLog(Dom4jUtil.class);

	/**
	 * 将符合格式的xml字符串 转化成 Document
	 * 
	 * @param s
	 * @return
	 */
	public static Document loadXml(String s) {
		Document document = null;
		try {
			document = DocumentHelper.parseText(s);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return document;
	}

	/**
	 * 加载一个XML文件转成Document对象
	 * 
	 * @param filename
	 * @return
	 */
	public static Document load(String filename, String encode) {
		Document document = null;
		try {
			SAXReader saxReader = new SAXReader();
			saxReader.setEncoding("encode");
			document = saxReader.read(new File(filename));
		} catch (Exception ex) {
			// logger.error("load XML File error:"+ex.getMessage());
		}
		return document;
	}

	/**
	 * 按指定编码转化字符串为Document
	 * 
	 * @param xml
	 * @param encode
	 * @return
	 */
	public static Document loadXml(String xml, String encode) {
		ByteArrayInputStream inputStream = new ByteArrayInputStream(
				xml.getBytes());
		return loadXml(inputStream, encode);
	}

	/**
	 * 根据输入流返回Document
	 * 
	 * @param is
	 * @return
	 */
	public static Document loadXml(InputStream is) {
		return loadXml(is, "utf-8");
	}

	public static Document loadXml(InputStream is, String charset) {
		Document document = null;
		try {
			SAXReader reader = new SAXReader();
			reader.setEncoding(charset);
			document = reader.read(is);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return document;
	}

	/**
	 * 将DOM写入到文件
	 * 
	 * 
	 * @param document
	 * @param fileName
	 * @throws IOException
	 */
	public static void write(Document document, String fileName)
			throws IOException {
		String xml = document.asXML();
		FileUtil.writeFile(fileName, xml);
	}

	/**
	 * 将XML写入文件
	 * 
	 * @param str
	 * @param fileName
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static void write(String str, String fileName) throws IOException,
			DocumentException {
		Document document = DocumentHelper.parseText(str);
		write(document, fileName);
	}

	/**
	 * 根据URL取得DOM
	 * 
	 * @param url
	 * @return
	 * @throws DocumentException
	 */
	public Document load(URL url) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(url);
		return document;
	}

	/**
	 * 载入一个xml文档
	 * 
	 * @param filename
	 * @return 成功返回Document对象，失败返回null
	 */
	public static Document load(String filename) {
		Document document = null;
		try {
			SAXReader reader = new SAXReader();
			document = reader.read(new File(filename));
			document.normalize();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return document;
	}

	/**
	 * 根据xsl转换xmldom.
	 * 
	 * @param document
	 * @param stylesheet
	 * @return
	 * @throws Exception
	 */
	public static String transFormXsl(String xml, String xsl,
			Map<String, String> map) throws Exception {
		StringReader xmlReader = new StringReader(xml);
		StringReader xslReader = new StringReader(xsl);

		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer(new StreamSource(
				xslReader));
		if (map != null) {
			// 添加参数
			Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, String> obj = it.next();
				transformer.setParameter(obj.getKey(), obj.getValue());
			}
		}
		StreamSource xmlSource = new StreamSource(xmlReader);

		StringWriter writer = new StringWriter();
		Result result = new StreamResult(writer);
		transformer.transform(xmlSource, result);

		return writer.toString();
	}

	public static String transXmlByXslt(String xml, String xslPath,
			Map<String, String> map) throws Exception {
		Document document = loadXml(xml);

		Document result = styleDocument(document, xslPath, map);

		return docToString(result);
	}

	/**
	 * 把Document对象转成XML String
	 * 
	 * @param document
	 * @return
	 */
	public static String docToString(Document document) {
		String s = "";
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();

			OutputFormat format = new OutputFormat("  ", true, "UTF-8");
			XMLWriter writer = new XMLWriter(out, format);
			writer.write(document);
			s = out.toString("UTF-8");
		} catch (Exception ex) {
			logger.error("docToString error:" + ex.getMessage());
		}
		return s;
	}

	public static Document styleDocument(Document document, String stylesheet,
			Map<String, String> map) throws Exception {

		// load the transformer using JAXP
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer(new StreamSource(
				stylesheet));
		if (map != null) {
			// 添加参数
			Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, String> obj = it.next();
				transformer.setParameter(obj.getKey(), obj.getValue());
			}
		}
		// now lets style the given document
		DocumentSource source = new DocumentSource(document);
		DocumentResult result = new DocumentResult();
		transformer.transform(source, result);

		// return the transformed document
		Document transformedDoc = result.getDocument();
		return transformedDoc;
	}

	/**
	 * 根据给定的schema校验xml,并返回是否校验成功.
	 * 
	 * @param xml
	 * @param schema
	 * @return
	 */
	public static String validXmlBySchema(String xml, String schema) {
		String result = "";
		try {
			// 创建默认的XML错误处理器

			XMLErrorHandler errorHandler = new XMLErrorHandler();
			// 获取基于 SAX 的解析器的实例

			SAXParserFactory factory = SAXParserFactory.newInstance();
			// 解析器在解析时验证 XML 内容。

			factory.setValidating(true);
			// 指定由此代码生成的解析器将提供对 XML 名称空间的支持。

			factory.setNamespaceAware(true);
			// 使用当前配置的工厂参数创建 SAXParser 的一个新实例。

			SAXParser parser = factory.newSAXParser();
			// 创建一个读取工具

			SAXReader xmlReader = new SAXReader();
			// 获取要校验xml文档实例
			org.dom4j.Document xmlDocument = (org.dom4j.Document) xmlReader
					.read(new File(xml));
			// 设置 XMLReader 的基础实现中的特定属性。核心功能和属性列表可以在
			// [url]http://sax.sourceforge.net/?selected=get-set[/url] 中找到。

			parser.setProperty(
					"http://java.sun.com/xml/jaxp/properties/schemaLanguage",
					"http://www.w3.org/2001/XMLSchema");
			parser.setProperty(
					"http://java.sun.com/xml/jaxp/properties/schemaSource",
					"file:" + schema);
			// 创建一个SAXValidator校验工具，并设置校验工具的属性

			SAXValidator validator = new SAXValidator(parser.getXMLReader());
			// 设置校验工具的错误处理器，当发生错误时，可以从处理器对象中得到错误信息。

			validator.setErrorHandler(errorHandler);
			// 校验
			validator.validate(xmlDocument);
			// XMLWriter writer =
			new XMLWriter(OutputFormat.createPrettyPrint());
			// 如果错误信息不为空，说明校验失败，打印错误信息

			if (errorHandler.getErrors().hasContent()) {
				result = "<result success='0'>XML文件通过XSD文件校验失败,请检查xml是否符合指定格式!</result>";
			} else {
				result = "<result success='1'>XML文件通过XSD文件校验成功!</result>";
			}
		} catch (Exception ex) {
			result = "<result success='0'>XML文件通过XSD文件校验失败:" + ex.getMessage()
					+ "</result>";
		}
		return result;
	}

}
