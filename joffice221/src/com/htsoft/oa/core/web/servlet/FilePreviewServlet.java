package com.htsoft.oa.core.web.servlet;
import java.io.IOException;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import com.htsoft.core.util.AppUtil;
import com.htsoft.oa.model.system.FileAttach;
import com.htsoft.oa.service.system.FileAttachService;

public class FilePreviewServlet extends HttpServlet{
	/**
	 * 附件预览工具类
	 */
	private static final long serialVersionUID = 1L;
	private FileAttachService fileAttachService=(FileAttachService)AppUtil.getBean("fileAttachService");
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String fileId=req.getParameter("fileId");
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		if(StringUtils.isNotEmpty(fileId)){
			FileAttach fileAttach=fileAttachService.get(new Long(fileId));
			String ext=fileAttach.getExt();
			
			if(ext.toLowerCase().endsWith("doc")||ext.toLowerCase().endsWith("docx"))
			{
				resp.setContentType("application/msword");
			}
			else if(ext.toLowerCase().endsWith("xls") || ext.toLowerCase().endsWith("csv"))
			{
				resp.setContentType("application/ms-excel ");

			}else if (ext.toLowerCase().endsWith("pdf"))
			{
				resp.setContentType("application/pdf");
			}
			else if (ext.toLowerCase().endsWith("txt"))
			{
				resp.setContentType("text/plain");
			}
			else if (ext.toLowerCase().endsWith("jpg"))
			{
				resp.setContentType("image/jpg");
			}
			else if (ext.toLowerCase().endsWith("jpeg"))
			{
				resp.setContentType("image/jpeg");
			}
			else if (ext.toLowerCase().endsWith("gif"))
			{
				resp.setContentType("image/gif");
			}
			else if (ext.toLowerCase().endsWith("png"))
			{
				resp.setContentType("image/png");
			}
			else if (ext.toLowerCase().endsWith("bmp"))
			{
				resp.setContentType("image/bmp");
			}
			else{
				return;
			}
			
			ServletOutputStream out = null;
            try{
            	
    			java.io.FileInputStream fileIn =new java.io.FileInputStream(getServletContext().getRealPath("/")+"/attachFiles/"+fileAttach.getFilePath());
    			
                resp.setHeader("Content-Disposition", "inline;filename=" +URLEncoder.encode(fileAttach.getFileName(),"UTF-8")); 
                
                out = resp.getOutputStream();
                
                byte[] buff = new byte[1024];
                int leng = fileIn.read(buff);
                while(leng>0){
                	out.write(buff,0,leng);
                	leng = fileIn.read(buff);
                }
                
            }catch(Exception ex){
            	ex.printStackTrace();
            }finally{
            	if(out!=null){
            		try {
    					out.flush();
    				} catch (IOException e) {
    					e.printStackTrace();
    				}
            		try {
    					out.close();
    				} catch (IOException e) {
    					e.printStackTrace();
    				}
            	}

            }
			
			
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
