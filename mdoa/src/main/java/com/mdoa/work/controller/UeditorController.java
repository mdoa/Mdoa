package com.mdoa.work.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mdoa.util.FileUtil;
import com.mdoa.util.StringUtil;
import com.mdoa.work.ueditor.ActionEnter;






@RestController
@RequestMapping("/ueditor")
public class UeditorController {
	  @RequestMapping("/upload.do")
      public void exec(@RequestParam(value="action",required=false)String action,
              @RequestParam(value="callback",required=false)String callback,
              HttpServletRequest req, HttpServletResponse res) {
          try {
              req.setCharacterEncoding("utf-8");
              res.setHeader("Content-Type", "text/html");
              String rootPath =req.getSession().getServletContext().getRealPath("/");
              res.getWriter().write(new ActionEnter(req, rootPath).exec());
         } catch (Exception e) {
              e.printStackTrace();
          }
      }
	  /**
	   * 显示图片
	   * @return
	   */
	  @RequestMapping("/loadImage.do")
	  public void loadImage(String imageName,HttpServletRequest request,HttpServletResponse response){
		  String imagePath = null;
		  if (imageName == null || imageName == "") {
			imageName = "";
		  }else{
			imagePath = "D:/workUeditor/image/"+imageName;
		  }
		  System.out.println("imagePath="+imagePath);
	      File file = new File(imagePath);
	      try {
			DataOutputStream temps = new DataOutputStream(response.getOutputStream());
			DataInputStream in = new DataInputStream(new FileInputStream(imagePath));
			byte[] b = new byte[2048];
			while ((in.read(b)) != -1) {
				temps.write(b);
				temps.flush();
			}

			in.close();
			temps.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		  
	  }
	  /**
	   * 显示图片(list)
	   * @return
	   */
	  @RequestMapping("/loadImages.do")
	  public void loadImages(String imagePath,HttpServletRequest request,HttpServletResponse response){
			  File file = new File(imagePath);
			  System.out.println("dada="+imagePath);
			  int a = imagePath.lastIndexOf("?");
			  if (a != -1) {
				  imagePath = imagePath.substring(0, a);
			  }
			  try {
				  DataOutputStream temps = new DataOutputStream(response.getOutputStream());
				  DataInputStream in = new DataInputStream(new FileInputStream(imagePath));
				  byte[] b = new byte[2048];
				  while ((in.read(b)) != -1) {
					  temps.write(b);
					  temps.flush();
				  }
				  in.close();
				  temps.close();
			  } catch (IOException e) {
				  e.printStackTrace();
			  }
	  }
	  /**
	   * 显示文件
	   */
	  @RequestMapping("/loadFile.do")
	  public void loadFile(String fileName,HttpServletRequest request,HttpServletResponse response){
		  try {
			  String fileUrl = "D:/workUeditor/file/"+fileName;
			  System.out.println("fileUrl="+fileUrl+",fileName="+fileName);
			  FileUtil.download(request, response, fileUrl, fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	  }
	  /**
	   * 显示文件
	   */
	  @RequestMapping("/loadFiles.do")
	  public void loadFiles(String fileUrl,HttpServletRequest request,HttpServletResponse response){
		  try {
			  int lastIndex = fileUrl.lastIndexOf("/");
			  String fileName = fileUrl.substring(lastIndex+1, fileUrl.length());
			  System.out.println("fileUrl="+fileUrl+",fileName="+fileName);
			  FileUtil.download(request, response, fileUrl, fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	  }
}
