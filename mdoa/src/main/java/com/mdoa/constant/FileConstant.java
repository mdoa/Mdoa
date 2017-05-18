package com.mdoa.constant;

import java.io.InputStream;
import java.util.Properties;

public class FileConstant {
	
	static{  
        Properties prop =  new  Properties();      
        InputStream in;
		try {
			in =  FileConstant.class.getClassLoader().getResourceAsStream("config.properties");
            prop.load(in);  
            FILE_PATH = prop.getProperty("FilePath").trim();  
            PERSONNEL_PACK = prop.getProperty("PersonnelPack").trim(); 
            USER_IDCARD = prop.getProperty("UserIdcard").trim(); 
            SHORT_TIME_EXCEL = prop.getProperty("ShortTimeExcel").trim();
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
          
    }  
	
	
	public static void main(String[] args) {
		System.out.println(FileConstant.FILE_PATH);
	}
	/**
	 * 全部文件的基础存储路径
	 */
	public static String FILE_PATH;
	
	/**
	 * 人事合同 扫描件 存储文件夹名
	 */
	public static String PERSONNEL_PACK;
	/**
	 * 身份证 正反面图片 存储文件夹名
	 */
	public static String USER_IDCARD;
	/**
	 * 临时导出的excale文件的文件夹
	 */
	public static String SHORT_TIME_EXCEL;
}