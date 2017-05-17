package com.htsoft.oa.core.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.htsoft.core.util.AppUtil;
import com.htsoft.core.util.RequestUtil;
import com.htsoft.core.util.StringUtil;
import com.htsoft.oa.model.system.FileAttach;
import com.htsoft.oa.service.system.FileAttachService;

/**
 * 附件文件下载
 * @author zxh
 *
 */
public class FileDownloadServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FileAttachService fileAttachService = (FileAttachService) AppUtil
			.getBean("fileAttachService");

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String fileId = req.getParameter("fileId");
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		if (StringUtils.isNotEmpty(fileId)) {
			FileAttach fileAttach = fileAttachService.get(new Long(fileId));
			resp.setContentType(RequestUtil.getContentType(fileAttach.getExt()));
			ServletOutputStream out = null;
			try {

				java.io.FileInputStream fileIn = new java.io.FileInputStream(
						getServletContext().getRealPath("/") + "/attachFiles/"
								+ fileAttach.getFilePath());
				resp.addHeader(
						"Content-Disposition",
						"attachment;filename="
								+ StringUtil.encodingString(
										fileAttach.getFileName(), "GBK",
										"ISO-8859-1"));

				out = resp.getOutputStream();

				byte[] buff = new byte[1024];
				int leng = fileIn.read(buff);
				while (leng > 0) {
					out.write(buff, 0, leng);
					leng = fileIn.read(buff);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				if (out != null) {
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
