package com.htsoft.oa.action.system;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;

import com.google.gson.Gson;
import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.json.JacksonMapper;
import com.htsoft.core.util.StringUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.system.FileAttach;
import com.htsoft.oa.service.system.FileAttachService;

/**
 * @description 附件管理
 * @class FileAttachAction
 * @author csx,yhz
 * @company www.jee-soft.cn
 * @createtime 2011-1-24PM
 * 
 */
public class FileAttachAction extends BaseAction {
	@Resource
	private FileAttachService fileAttachService;
	private FileAttach fileAttach;

	private Long fileId;

	private String filePath;

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public FileAttach getFileAttach() {
		return fileAttach;
	}

	public void setFileAttach(FileAttach fileAttach) {
		this.fileAttach = fileAttach;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * 查询我上传附件信息
	 */
	public String list() {
		int start = new QueryFilter(getRequest()).getPagingBean().getStart();
		PagingBean pb = new PagingBean(start, 20);
		String imageOrOthersFile = getRequest().getParameter("type");
		boolean bo = true;// 默认file
		if (imageOrOthersFile != null
				&& imageOrOthersFile.toLowerCase().equals("image")) {
			bo = false; // 图片
			pb = new PagingBean(start, 16);
		}
		String fileType = getRequest().getParameter("fileType");
		List<FileAttach> list = fileAttachService.fileList(pb, fileType, bo);

		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd HH:mm:ss");
		jsonString = mapper.toPageJson(list, pb.getTotalItems());
		return SUCCESS;
	};

	public String listAll() {
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addSorted("fileType", "DESC");
		List<FileAttach> list = fileAttachService.getAll(filter);
		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd HH:mm:ss");
		jsonString = mapper.toPageJson(list, filter.getPagingBean()
				.getTotalItems());
		return SUCCESS;
	}

	/**
	 * 批量删除
	 */
	public String multiDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) 
			{
				if(StringUtil.isNotEmpty(id)){
					fileAttachService.remove(new Long(id));
				}else{
					jsonString="{success:false,message:'删除数据失败，请联系管理员!'}";
				}
			}
		}
		String isFlex = getRequest().getParameter("isFlex");
		if (StringUtils.isNotEmpty(isFlex)) {
			jsonString = "{\"success\":\"true\"}";
		} else {
			jsonString = "{success:true}";
		}
		return SUCCESS;
	}

	/**
	 * 在flex中删除附件信息
	 */
	public String flexDel() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				fileAttachService.remove(new Long(id));
			}
		}
		jsonString = "{\"success\":\"true\"}";
		return SUCCESS;
	}

	/**
	 * 显示详细信息
	 */
	public String get() {
		FileAttach fileAttach = fileAttachService.get(fileId);
		
		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd HH:mm:ss");
		jsonString = mapper.toDataJson(fileAttach);
		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		fileAttachService.save(fileAttach);
		setJsonString("{success:true}");
		return SUCCESS;
	}

	/**
	 * 根据附件的路径删除附件,用于重复上传图片,或删除图片,已用于员工管理模块
	 */
	public String delete() {
		fileAttachService.removeByPath(filePath);
		return SUCCESS;
	}

	/**
	 * 根据IDS取得附件
	 */
	public String loadByIds() {
		List<FileAttach> list = new ArrayList<FileAttach>();
		String ids = getRequest().getParameter("ids");
		if (StringUtils.isNotEmpty(ids)) {
			String[] idsArr = ids.split(",");
			for (String fileId : idsArr) {
				if (StringUtils.isNotEmpty(fileId)) {
					FileAttach fileAttach = fileAttachService.get(new Long(
							fileId));
					list.add(fileAttach);
				}
			}
		}
		Gson gson = new Gson();
		StringBuffer results = new StringBuffer("{success :true,fileAttachs:");
		results.append(gson.toJson(list));
		results.append("}");
		setJsonString(results.toString());
		return SUCCESS;
	}

	/**
	 * @description 将List集合中的数据转化为JSON格式
	 * @param pb
	 *            PagingBean
	 */
	private String listToJson(List<FileAttach> list, PagingBean pb) {
		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd HH:mm:ss");
		jsonString = mapper.toPageJson(list, pb.getTotalItems());
		return SUCCESS;
	}
	

	/**
	 * 2. * 将Office文档转换为PDF. 运行该函数需要用到OpenOffice, OpenOffice 3. * 4. * @param
	 * sourceFile 5. * 源文件,绝对路径. 可以是Office2003-2007全部格式的文档, Office2010的没测试.
	 * 包括.doc, .docx, .xls, .xlsx, .ppt, .pptx等. 6. * 7. * @param destFile 8. *
	 * inputFilePath 目标文件.绝对路径. 成功时返回SWF文件的绝对路径，否则为“”！
	 */
	public String convertOfficeToPdf(String inputFilePath)
	{
		int num = inputFilePath.lastIndexOf(".");
		String type = inputFilePath.substring(num + 1, inputFilePath.length())
				.toLowerCase();
		System.out.println("officeToPdf文件的格式：" + type);
		if ("doc".equals(type) || "docx".equals(type) || "ppt".equals(type)
				|| "pptx".equals(type) || "xls".equals(type)
				|| "xlsx".equals(type))
		{
			File inputFile = new File(inputFilePath);
			if (inputFile.exists()) // 是否找到源文件
			{
				DefaultOfficeManagerConfiguration config = new DefaultOfficeManagerConfiguration();

				String officeHome = getOfficeHome();
				config.setOfficeHome(officeHome);

				OfficeManager officeManager = config.buildOfficeManager();
				officeManager.start();

				OfficeDocumentConverter converter = new OfficeDocumentConverter(
						officeManager);
				String outputFilePath = getOutputFilePath(inputFilePath, "pdf");

				File outputFile = new File(outputFilePath);
				if (!outputFile.getParentFile().exists())
				{ // 假如目标路径不存在, 则新建该路径
					outputFile.getParentFile().mkdirs();
				}
				converter.convert(inputFile, outputFile);
				officeManager.stop(); // 关闭 转换器
				return outputFilePath;
			} else
			{
				System.out.println("对不起，源文件不存在，OpenOffice转换失败！");
				return "";
			}
		} else
		{
			System.out.println("对不起，OpenOffice只能把Office相关文件格式转换成Pdf格式，转换失败！");
			return "";
		}
	}

	/**
	 * swftools 将pdf文件转换成swf文件
	 * 
	 * @param sourceFile pdf文件绝对路径
	 * 成功时返回SWF文件的绝对路径，否则为“”
	 */
	private String convertPdfToSwf(String sourceFile)
	{
		int num = sourceFile.lastIndexOf(".");
		String type = sourceFile.substring(num + 1, sourceFile.length())
				.toLowerCase();
		System.out.println("convertPdf2Swf文件的格式：" + type);
		if ("pdf".equals(type))
		{
			File file = new File(sourceFile);
			if (file.exists())
			{ // 是否找到源文件
				String pdfToSwfHome = getPdfToSwfHome();
				String outFile = getOutputFilePath(sourceFile, "swf");
				String command = pdfToSwfHome + "/pdf2swf.exe \"" + sourceFile + "\" -o  \"" + outFile + "\" -s flashversion=9 ";
				try
				{
					System.out.println(command);
					Process process = Runtime.getRuntime().exec(command);
					// Thread.sleep(5000);
					// process.destroy();
					// 非要读取一遍cmd的输出，要不不会flush生成文件（多线程）
					StreamGobbler sg1 = new StreamGobbler(process.getInputStream(), "Console");
					StreamGobbler sg2 = new StreamGobbler(process.getErrorStream(), "Error");
					sg1.start();
					sg2.start();
					try
					{
						process.waitFor();
					} catch (InterruptedException e)
					{
						e.printStackTrace();
						System.out.println("###--Msg: swf 转换过程出错！");
					}
					File swfFile = new File(outFile); 
					if(swfFile.exists()){ 
						 if(process!=null){
							 process.destroy();
						 }
						 System.out.println("###--Msg: swf 转换成功:" + outFile);
						 return outFile;
					}
					return "";
				} catch (Exception e)
				{
					e.printStackTrace();
					System.out.println("###--Msg: swf 转换失败");
				}
				return "";
			} else
			{
				System.out.println("对不起，源文件不存在，swftools转换失败！");
				return "";
			}
		} else
		{
			System.out.println("对不起，swftools只能把Pdf格式转换成Swf格式，转换失败！");
			return "";
		}
	}

	/**  
	 * 读取转换时cmd进程的标准输出流和错误输出流，这样做是因为如果不读取流， 进程将死锁
	 * @author xucx
	 */
	public class StreamGobbler extends Thread
	{
		InputStream is;
		String type;

		StreamGobbler(InputStream is, String type)
		{
			this.is = is;
			this.type = type;
		}

		public void run()
		{
			BufferedReader br = null;
			try
			{
				InputStreamReader isr = new InputStreamReader(is);
				br = new BufferedReader(isr);
				String line = null;
				while ((line = br.readLine()) != null) // 这里并没有对流的内容进行处理，只是读了一遍
				{
					// line 可以做一些写入文件的内容  比如日志等
				}
			} catch (IOException ioe)
			{
				ioe.printStackTrace();
			} finally
			{
				if (br != null)
				{
					try
					{
						br.close();
					} catch (IOException e)
					{
						e.printStackTrace();
					}
				}
			}
		}

	}

	/** 
	 * 转换格式文件的名称，inputFilePath 为文件全称，type 为要变成的类型
	 * @author xucx
	 */
	public String getOutputFilePath(String inputFilePath, String type)
	{
		String outputFilePath = "";
		int num = inputFilePath.lastIndexOf(".");
		if (num > 0)
		{
			outputFilePath = inputFilePath.substring(0, num) + "." + type;
		} else
		{
			outputFilePath = inputFilePath + "." + type;
		}
		return outputFilePath;
	}


	/**
	 * OpenOffice的安装路径可以存放在一个自定义的属性文件或者保存数据库中存取！ 在启动时自动读取相关内容
	 */
	public  String getOfficeHome()
	{
		String osName = System.getProperty("os.name").toLowerCase();
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("conf/jdbc.properties");
		Properties p = new  Properties();
		try {
			p.load(in);
			String name = p.getProperty("windows.officeToPdf");
			System.out.println("windowspdf:"+name);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (Pattern.matches("linux.*", osName))
		{
			return "/opt/openoffice.org3";
		} else if (Pattern.matches("windows.*", osName))
		{
			return p.getProperty("windows.officeToPdf");
			//return "C:/Program Files/OpenOffice.org 3";
		} else if (Pattern.matches("mac.*", osName))
		{
			return "/Application/OpenOffice.org.app/Contents";
		}
		return null;
	}
	
	
	/**
	 *  swftools的安装路径可以存放在一个自定义的属性文件或者保存数据库中存取！ 在启动时自动读取相关内容
	 */
	public String getPdfToSwfHome()
	{
		String osName = System.getProperty("os.name").toLowerCase();
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("conf/jdbc.properties");
		Properties p = new  Properties();
		try {
			p.load(in);
			String name = p.getProperty("windows.pdfToSwf");
			System.out.println("windowsSwf:"+name);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (Pattern.matches("linux.*", osName))
		{
			return "/opt/SWFTools";   //待确定
		} else if (Pattern.matches("windows.*", osName))
		{
			
			return p.getProperty("windows.pdfToSwf");
			//return "C:/Program Files/SWFTools";
		} else if (Pattern.matches("mac.*", osName))
		{
			return "/Application/SWFTools.app/Contents";  //待确定
		}
		return null;
	}

	/**
	 * Office文件转成 PDF，PDF再转成 SWF的逻辑控制方法
	 */
	public String docToSwf()
	{
		String fileId = getRequest().getParameter("fileId");
		String mark = "no";
		if (StringUtils.isNotEmpty(fileId))
		{
			FileAttach fileAttach = fileAttachService.get(new Long(fileId));
			String swfUrl = getOutputFilePath("attachFiles/" + fileAttach.getFilePath(), "swf");
			getRequest().setAttribute("swfPath", swfUrl);
			getRequest().setAttribute("fileId", fileId);
			String path = getRequest().getSession().getServletContext().getRealPath("/") + "attachFiles/" + fileAttach.getFilePath();
			path = path.replace("\\", "/");
			String swfPath = getOutputFilePath(path, "swf");
			File file = new File(swfPath); 
			if (file.exists())           //源文件对应的 SWF文件存在就直接返回，不用转换
			{
				mark = "yes";
			}else{
				file = new File(path);
				if (file.exists())  //源文件存在就去转换
				{
					int num = path.lastIndexOf(".");
					String pdfPath = "";
					if("pdf".equals(path.substring(num + 1, path.length()).toLowerCase())){   //如果是PDF的附件，就不用再转换成PDF文件
						pdfPath = path;    
					}else{
						pdfPath = getOutputFilePath(path, "pdf");  //替换成pdf的文件名称
						file = new File(pdfPath);
						if (!file.exists())  //对应的PDF文件存在就不用把源文件转成PDF文件
						{
							pdfPath = convertOfficeToPdf(path);   //把 PDF 转换成 SWF 文件
						}
					}
					swfPath = convertPdfToSwf(pdfPath);  //把 PDF文件 转换成 SWF文件，  成功时返回SWF的路径，否则返回“”；
					if (StringUtils.isNotEmpty(swfPath))
					{
						if(!"pdf".equals(path.substring(num + 1, path.length()).toLowerCase())){   //如果是源文件是PDF的附件就不用删除
							File filePdf = new File(pdfPath);   
						    filePdf.delete();   //有生成SWF文件后，可以删除PDF文件，避免文件过多 （可以不删除） 
						}
						mark = "yes";
					}
				}else{
					System.out.println("对不起，源文件不存在！"+ path);
				}	
			}
		}
		getRequest().setAttribute("mark", mark);
		return "fileShow";
	}
}
