package com.htsoft.oa.core.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

/**
 * 解析html
 * @author cjj
 *
 */
public class HtmlParseUtil{
	
	/**
	 * 递归调用，解析html嵌套标签
	 * @param praMap
	 * @param praElm
	 * @return
	 */
	public LinkedHashMap expandNode(LinkedHashMap parMap, Element parElm){
		
		int idx = 0;
		List<TextNode> tnlist = null;
		
		// 对html文本解析
		if(parElm.textNodes().size()>0)
		{
			String parTag = parElm.tagName();
			if(!parTag.equals("span")){
				List<Node> nodes = parElm.childNodes();
				if(nodes.size()>0){
					Document doc = Jsoup.parse("");
					Element parEl = doc.body().appendElement(parTag);
					for(Attribute attr:parElm.attributes()){
						parEl.attr(attr.getKey(), attr.getValue());
					}
					parElm = doc.body().children().first();
					int nidx=nodes.size();
					int nidx0=0;
					Node node = null;
					for(;nidx>0;){
						node = nodes.get(nidx0);
						Node parNode = node.parent();
						if(((Element)parNode).tagName().equals(parTag)){
							if(node instanceof TextNode){
								TextNode tn = (TextNode)node;
								parElm.append("<span>"+tn+"</span>");
								nidx0++;
							}else{
								parElm.appendChild(node);
							}
						}
						nidx--;
					}
				}
			}
		}
		
		// 嵌套标签处理
		if(parElm.children().size()>0)
		{
			for(Element elm:parElm.children()){
				
				LinkedHashMap chdMap = null;
				String tag = elm.nodeName();
				// 当表格是 table 的解析
				if(tag.equals("table")){
					parMap.put("tag", "table");
					expandNode(parMap, elm);
					continue;
				}
				// 当为以下标签时跳过
				else if(tag.equals("input")||tag.equals("textarea")||tag.equals("label")||tag.equals("select")){
					continue;
				}
				else
				{
					chdMap = new LinkedHashMap();
				}
				
				// html换行处理
				if(tag.toLowerCase().equals("br"))
				{
					chdMap.put("tag", "br");
					chdMap.put("html", "");
				}
				// 如果标签为th，变为td
				else if(tag.equals("th"))
				{
					chdMap.put("tag", "td");
				}
				else
				{
					chdMap.put("tag", tag);
				}
				
				// 标签所有属性解析
				Attributes attrs = elm.attributes();
				// 解析attribute
				parseAttribute(chdMap, attrs, parMap, idx, elm);
				
				// label
				if(tag.equals("span")&&!chdMap.containsKey("class"))
				{
					chdMap.put("tag", "label");
					chdMap.put("text", elm.text());
				}
				
				// 如果td 属于合并单元格跳过
				if(tag.equals("td")&&chdMap.containsValue("DISPLAY: none")){
					continue;
				}
				
				// 如果控件为 checkbox 或 radioinput
				if(chdMap.containsKey("class")&&
						(chdMap.get("class").equals("checkbox")
								||chdMap.get("class").equals("radioinput"))){
					continue;
				}
				
				parMap.put("chd"+idx, chdMap);
				idx++;
				expandNode(chdMap, elm);
				
			}
		}

		return parMap;
	}
	
	/**
	 * 父标签为body，定义容器装载
	 * @param html
	 * @return
	 * @throws Exception
	 */
	public List parseHtml(String html) throws Exception
	{  
		List<Map> ctlist = new ArrayList();
		html = html.replaceAll("\r", "").replaceAll("\n", "").replaceAll("\t", "")
				.replaceAll("&nbsp;", " ");
//				.replaceAll("<BR>", "");
		if(StringUtils.isNotEmpty(html))
		{
			Document doc = Jsoup.parse(html);
			Element body = doc.body();
			
			int ctIdx = 0; // 容器下标
			
			List<TextNode> textNodes = null;
			LinkedHashMap praMap = new LinkedHashMap();
			
			for(Element elm:body.getAllElements())
			{
				
				if(elm.children().size()>0) // 取body下面内容
				{
					// 解析父标签为 body 的子标签
					if(elm.parent().nodeName().equals("body"))
					{
						LinkedHashMap ctMap = new LinkedHashMap();
						LinkedHashMap chdMap = new LinkedHashMap();
						
						Attributes attrs = elm.attributes();
						for(Attribute attr:attrs){
							
							// 解析external属性
							if(attr.getKey().equals("external")){
								
								String[] strAttrs = attr.getValue().substring(1, attr.getValue().length()-1).split(",");
								for(String strAtt:strAttrs){
									String[] att = strAtt.split(":");
									chdMap.put(att[0], att[1]);
								}
								
							}else{
								chdMap.put(attr.getKey(), attr.getValue());
							}
							
						}
						
						if(chdMap.containsValue("formTable")&&!chdMap.containsKey("colspan")){
							Element td = elm.getElementsByTag("td").first();
							Attributes tdAttrs = td.attributes();
							chdMap.put("tablerows", tdAttrs.get("colspan"));
						}
						
						String tag = elm.nodeName();
						if(tag.equals("p")||tag.equals("table")){
							
							chdMap.put("tag", tag);
						}
						else if(tag.equals("div")&&chdMap.containsKey("class")&&
								chdMap.get("class").equals("subtable")){
							
							chdMap.put("tag", "table");
							tag = "table";
						}
						else{
							
							chdMap.put("tag", "p");
							tag = "p";
							Element p = Jsoup.parse("<p></p>").body().select("p").first();
							elm = p.append(elm.outerHtml());
						}
						
						Map expandMap = expandNode(chdMap,elm);
						// 解析普通table
						if(expandMap.containsKey("tag")&&expandMap.get("tag").equals("table")&&!expandMap.containsKey("class")){
							parseCommonTable(chdMap,expandMap,elm);
						}
						
						ctMap.put(tag+ctIdx, expandMap);
						ctlist.add(ctMap);
						ctIdx++;
					}
				}
				
			}
		}
		return ctlist;
	}
	
	/**
	 * 计算普通table列数
	 * @param chdMap
	 * @param expandMap
	 * @param elm
	 */
	private void parseCommonTable(LinkedHashMap chdMap, Map expandMap, Element elm){
		
		if(expandMap.get("tag").equals("table")&&!expandMap.containsKey("class")){

			int cols = 0;
			Element tr = elm.getElementsByTag("tr").first();
			if(tr.attributes().size()==0){
				
				Elements tds = tr.getElementsByTag("td");
				for(Element td:tds){
					
					Element tr0 = td.parent();
					if(tr0.attributes().size()==0){
						Attributes taAtts = td.attributes();
						if(taAtts.hasKey("colspan")){
							cols+=Integer.parseInt(taAtts.get("colspan"));
						}else{
							cols++;
						}
					}
				}
			}
			
			chdMap.put("tablerows", cols);
		}
	}
	
	/**
	 * 解析 attribute
	 * @param chdMap
	 * @param attrs
	 * @param parMap
	 * @param idx
	 */
	private void parseAttribute(LinkedHashMap chdMap, Attributes attrs, LinkedHashMap parMap, int idx, Element elm){
		
		for(Attribute attr:attrs){
			
			// external 属性解析
			if(attr.getKey().equals("external")){
				
				String attrValue = attr.getValue().replaceAll("'", "");
				int len = attrValue.length();
				int dbtsta = attrValue.indexOf("dbType");
				int dbtend = attrValue.indexOf("},");
				
				// 存在 dbType 属性
				if(dbtsta!=-1){
					String[] strAttrs = (attrValue.substring(1, dbtsta)+attrValue.substring(dbtend+2, len-1)).split(",");
					
					String[] dbTypes = attrValue.substring(dbtsta+8,dbtend).split(",");
					for(String dbType:dbTypes){
						if(chdMap.get("class").equals("datepicker")&&dbType.indexOf("format")!=-1){
							chdMap.put(dbType.substring(0,6), dbType.substring(8,dbType.length()-1));
						}else{
							String[] att = dbType.split(":");
							chdMap.put(att[0], att[1]);
						}
					}
					
					for(String strAtt:strAttrs){
						String[] att = strAtt.split(":");
						
						// 解析 checkbox 选项
						if(chdMap.containsKey("class")
								&&(chdMap.get("class").equals("checkbox")||chdMap.get("class").equals("radioinput"))
								&&att[0].equals("options")){
							
							String[] chbs = att[1].split("#newline#");
							for(int chbidx=0;chbidx<chbs.length;chbidx++){
								if(chbidx==0){
									chdMap.put("boxLabel", chbs[0]);
									parMap.put("chd"+idx, chdMap);
								}else{
									LinkedHashMap chbChdMap = (LinkedHashMap)chdMap.clone();
									chbChdMap.put("boxLabel", chbs[chbidx]);
									parMap.put("chd"+idx, chbChdMap);
								}
								idx++;
							}
						}
						else{
							
							chdMap.put(att[0], att[1]);
						}
					}
					
				}else{
				
					String[] strAttrs = attrValue.substring(1,attrValue.length()-1).split(",");
					for(String strAtt:strAttrs){
						String[] att = strAtt.split(":");
						chdMap.put(att[0], att[1]);
					}
				}
				
			}
			// 解析不在external里的属性
			else if(!attr.getKey().equals("name")){
				chdMap.put(attr.getKey(), attr.getValue());
			}
			
		}
		
	}
	
}   
