package com.mdoa.util;

import java.beans.PropertyDescriptor;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 处理Excel导入导出的工具类
 * 
 * @author Administrator
 *
 */
public class ExcelUtil {
	
	/**
	 * 导出数据到Excel
	 * @param fileUrl 文件路径
	 * @param modelList 数据集合
	 */
	@SuppressWarnings("resource")
	public static void writeListToExcel(String fileUrl, List<?> modelList ,List<ExcelModel> modelDetails) throws Exception{
		//工作薄
		Workbook workbook = null;
		if(fileUrl.endsWith("xls")){
			workbook = new XSSFWorkbook();
		}else if(fileUrl.endsWith("xlsx")){
			workbook = new HSSFWorkbook();
		}else {
			throw new RuntimeException("非法文件名,仅支持.xls与.xlsx文件");
		}
		if(modelList == null || modelList.size()==0)
			throw new RuntimeException("数据集合为空或大小为0");
		if(modelDetails.isEmpty())
			throw new RuntimeException("Excel详细设置为空");
		File file = new File(fileUrl);
		File fileFolder=new File(fileUrl.substring(0,fileUrl.lastIndexOf("/")+1));
		if(!fileFolder.exists()){
			fileFolder.mkdirs();
		}
		//创建工作表
		Sheet sheet = workbook.createSheet();
		//创建工作表样式
		CellStyle cellStyle = workbook.createCellStyle();
		//设置对齐方式居中
		cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		//行数计数器
		int rowIndex = 0;
		//创建第一行
		Row headRow = sheet.createRow(rowIndex++);
		//列号计数器
		int columnIndex = 0;
		//获取详细设置集合大小
		int detailsSize = modelDetails.size();
		//生成表头(第一行)的所有列 并将属性名添加进集合中
		for(int i=0;i<detailsSize;i++){
			//获取单列详细设置对象
			ExcelModel excelModel = modelDetails.get(i);
			//获取列宽
			Integer columnWidth = excelModel.getColumnWidth();
			//设置列宽
			if(StringUtil.isEmpty(columnWidth+"")){
				sheet.setColumnWidth(columnIndex, columnWidth*256);
			}else{
				sheet.setColumnWidth(columnIndex, 20*256);//默认值
			}
			//创建列
			Cell cell = headRow.createCell(columnIndex++);
			//获取表头
			String tableHeader = excelModel.getTableHeader();
			//设置表头
			cell.setCellValue(tableHeader);
			//设置表头列风格
			cell.setCellStyle(cellStyle);
		}
		//获取数据模板
		Class<?> classModel = modelList.get(0).getClass();
		//获取数据集合大小
		int modelsSize = modelList.size();
		//遍历数据集合 插入余下的所有行
		for(int i = 0;i<modelsSize;i++){
			//获取单个数据
			Object object = modelList.get(i);
			//生成单行
			Row row = sheet.createRow(rowIndex++);
			//遍历属性名 反射获取数据的对应属性值并填入对应列
			for(int j = 0;j<detailsSize;j++){	
				//获取单列详细设置对象
				ExcelModel excelModel = modelDetails.get(j);
				//获取单列属性名
				String propertyName = excelModel.getPropertyName();
				//获取单列属性值类型
				String propertyType = excelModel.getPropertyType();
				//获取单列数据格式
				String dateFormat = excelModel.getDateFormat();
				//获取存储器
				PropertyDescriptor propertyDescriptor = new PropertyDescriptor(propertyName, classModel);//获取存储器时可能抛出异常
				//获取属性的get方法
				Method getPropertyMethod = propertyDescriptor.getReadMethod();
				//属性值
				Object propertyValue = null;
				if(getPropertyMethod != null){
					propertyValue = getPropertyMethod.invoke(object);//object没有该属性的get方法时可能抛出异常
				}else {
					throw new RuntimeException("存储器内无该属性的get方法");
				}
				//生成单列
				Cell cell = row.createCell(j);
				//根据属性类型不同调用不同参数的设置列值的方法 
				switch (propertyType) {
				case "String":
					cell.setCellValue((String)propertyValue);
					break;
				case "Integer":
				case "Double":
					cell.setCellValue((Double)propertyValue);
					break;
				/*case "Date":
					if(!StringUtil.isEmpty(dateFormat)){
						//设置日期格式
						String dateStr = DateUtil.dateToStr((Date)propertyValue, dateFormat);
						cell.setCellValue(dateStr);
					}else{
						cell.setCellValue(DateUtil.dateToStr((Date)propertyValue));
					}
					break;*/
				default:
					cell.setCellValue((String)propertyValue);
					break;
				}
				cell.setCellStyle(cellStyle);
			}
		}
		//写入数据到Excel文件  文件输出流
		FileOutputStream fileOutputStream = new FileOutputStream(file);//可能抛出找不到文件的异常
		//输出缓冲流
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
		//写入数据
		workbook.write(bufferedOutputStream);//可能抛出IO异常
		if(fileOutputStream!=null){
			fileOutputStream.close();//可能抛出异常
		}
		workbook.close();//可能抛出异常
	}

	
	/**
	 * 从Excel中导入数据 未完成（禁用） 否则后果自负
	 * 
	 * @param fileName
	 * @param classModel
	 * @throws Exception
	 */
	public static void readListFromExcel(String fileName, Class<?> classModel) throws Exception {
		// 转为小写以校验文件名
		String testName = fileName.toLowerCase();
		if (!testName.endsWith("xls") || !testName.endsWith("xlsx"))
			throw new RuntimeException("不是合适的excel表格文件");
		File excel = new File(fileName);
		if (!excel.exists()) {
			throw new RuntimeException("文件不存在或路径有误");
		}
		// 文件输入流
		FileInputStream fileInputStream = null;
		// 缓冲输入流
		BufferedInputStream bufferedInputStream = null;
		// 工作薄
		Workbook workbook = null;

		fileInputStream = new FileInputStream(excel);
		bufferedInputStream = new BufferedInputStream(fileInputStream);

		if (testName.endsWith("xls")) {
			// xls对应HSSFWorkbook
			workbook = new HSSFWorkbook(bufferedInputStream);
		} else {
			// xlsx对应XSSFWorkbook
			workbook = new XSSFWorkbook(bufferedInputStream);
		}
		// 获得工作薄（Workbook）中工作表（Sheet）的个数
		int sheetsNumber = workbook.getNumberOfSheets();
		// 遍历每个Sheet
		for (int i = 0; i < sheetsNumber; i++) {
			// 获取单个Sheet
			Sheet sheet = workbook.getSheetAt(i);
			// 每个Sheet都有很多Row 获取Row迭代器
			Iterator<Row> rowIterator = sheet.iterator();
			// 遍历每一行Row
			while (rowIterator.hasNext()) {
				// 获取单个Row
				Row row = rowIterator.next();
				// 每个Row都有很多Column 获取Column迭代器
				Iterator<Cell> cellIterator = row.cellIterator();
				// 遍历每一列Cell
				while (cellIterator.hasNext()) {
					// 获取单个Cell
					Cell cell = cellIterator.next();
					
				}
			}
		}
	}
}
