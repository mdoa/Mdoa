package com.mdoa.util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.mdoa.constant.FileConstant;

import sun.misc.BASE64Decoder;

public class FileUtil {

	/**
	 * 文件上传工具方法
	 * 
	 * @param file
	 *            所上传的文件对象
	 * @return 文件上传路径
	 */
	public static String uploadFile(MultipartFile file, String url) throws IllegalStateException, IOException {
		File fileFolder=new File(url.substring(0,url.lastIndexOf("/")+1));
		if(!fileFolder.exists()){
			fileFolder.mkdirs();
		}
		
		File newFile = new File(url);
		// 通过CommonsMultipartFile的方法直接写文件
		file.transferTo(newFile);
		return url;
	}

	/**
	 * 对上传的图片根据指定的URl进行保存，并将图片按比例保存小图
	 * @param file 上传的文件对象
	 * @param bigImgUrl 大图的url保存地址，包含文件名
	 * @param width 保存小图压缩后的宽度 单位px，高度则按照图片比例自动生成
	 * @return smallImgUrl 小图Url
	 */
	public static String uploadImage(MultipartFile file, String bigImgUrl, int width)
			throws IllegalStateException, IOException {
		//保存大图
		uploadFile(file, bigImgUrl);
		//保存小图
		String format = bigImgUrl.substring(bigImgUrl.lastIndexOf(".") + 1);
		String smallImgUrl = scaleImage(bigImgUrl, width, format);
		return smallImgUrl;
	}
	
	/**
	 * 按指定的比例对图片进行缩放处理
	 * @param file 上传的文件对象
	 * @param bigImgUrl 大图的url保存地址，包含文件名
	 * @param width 保存小图压缩后的宽度 单位px，高度则按照图片比例自动生成
	 * @return smallImgUrl 小图Url
	 */
	public static String scaleImage(String bigImgUrl, int width, String format) {

		File file = new File(bigImgUrl);
		
		int index = bigImgUrl.lastIndexOf(".");
		String smallImgUrl = bigImgUrl.substring(0, index) + "small" + bigImgUrl.substring(index);
		
		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(file);
			int imgWidth = bufferedImage.getWidth();
			int imgHeight = bufferedImage.getHeight();
			
			int height = imgHeight * width / imgWidth;

			Image image = bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics graphics = outputImage.getGraphics();
			graphics.drawImage(image, 0, 0, null);
			graphics.dispose();

			ImageIO.write(outputImage, format, new File(smallImgUrl));
			
		} catch (IOException e) {
			System.out.println("scaleImage方法压缩图片时出错了");
			e.printStackTrace();
		} finally {
			if(bufferedImage != null)
				bufferedImage.flush();
		}
		
		return smallImgUrl;
	}
	
	/**
	 * 获取Excel文件
	 * @param request
	 * @param response
	 * @param excelPath
	 * @throws IOException
	 */
	public static void downloadExcel(HttpServletRequest request,HttpServletResponse response, String excelPath) throws Exception{
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		// 获取文件的文件名
		String fileName = excelPath.substring(excelPath.lastIndexOf("/") + 1);
		System.out.println(fileName);
		String fileUrl = FileConstant.FILE_PATH + FileConstant.SHORT_TIME_EXCEL + "/" + excelPath;
		// 获取文件的文件名
		if(fileName == null){
			fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
		}
		try {
			long fileLength = new File(fileUrl).length();

			// 设置响应头为下载,并设置响应的文件名
			response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("UTF-8");
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition",
					"attachment; filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
			response.setHeader("Content-Length", String.valueOf(fileLength));

			bis = new BufferedInputStream(new FileInputStream(fileUrl));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
	}
	
	/**
	 * 文件下载工具方法 该方法没有返回值，通过对请求和响应的操作来实现对文件的下
	 * 
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @param fileUrl
	 *            文件下载的绝对路径
	 */
	public static void download(HttpServletRequest request, HttpServletResponse response, String fileUrl,
			String fileName) throws Exception {


		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		// 获取文件的文件名
		if(fileName == null){
			fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
		}
		try {
			long fileLength = new File(fileUrl).length();

			// 设置响应头为下载,并设置响应的文件名
			response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("UTF-8");
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition",
					"attachment; filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
			response.setHeader("Content-Length", String.valueOf(fileLength));

			bis = new BufferedInputStream(new FileInputStream(fileUrl));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (FileNotFoundException e) {
			System.out.println("未找到此文件");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
	}
	
	/**
	 * 静态的私有对象，用于对时间进行格式化
	 */
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSS");
	
	/**
	 * 根据传入的文件原始名称，文件分类，生成文件的Url存放路径
	 * @param fileName 文件原始名称
	 * @param type FileConstant 中的静态常量
	 * @return 生成的url
	 */
	public static String getUrl(String fileName, String type){
		String dateStr = sdf.format(new Date());
		String url = type + "/" + dateStr.substring(0, 6) + "/" + dateStr.substring(6) + 
				fileName.substring(fileName.lastIndexOf("."));
		return url;
	}
	
	/**
	 * 将一串base64编码按照指定的目录，以图片形式存入服务器
	 * 
	 */
	public static void saveBase64(String base, String imgUrl) throws Exception{
		if(StringUtil.isEmpty(base)){
			throw new RuntimeException("空的base64编码");
		}
		BufferedOutputStream bos = null;
		try{
			BASE64Decoder decoder = new BASE64Decoder();
			File fileFolder = new File(imgUrl.substring(0, imgUrl.lastIndexOf("/") + 1));
			if (!fileFolder.exists()) {
				fileFolder.mkdirs();
			}
			byte[] bytes = decoder.decodeBuffer(base);
			for (int i = 0; i < bytes.length; ++i) {
				if (bytes[i] < 0) {
					bytes[i] += 256;
				}
			}
			bos = new BufferedOutputStream(new FileOutputStream(imgUrl));
			bos.write(bytes);
		}catch(RuntimeException e){
			
		}finally{
			if(bos != null){
				bos.flush();
				bos.close();
			}
		}
	}
}
