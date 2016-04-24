package com.ccloomi.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ccloomi.core.constant.Constant;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：ExcelUtil
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年3月27日-上午9:09:08
 */
public class ExcelUtil {
	private static final Logger log=LoggerFactory.getLogger(ExcelUtil.class);
	/**
	 * 描述：读取所有的Sheet
	 * 作者：Chenxj
	 * 日期：2016年3月27日 - 上午9:38:59
	 * @param excelFile
	 * @return
	 */
	public static Map<String, List<List<Object>>>readAllSheetFromExcel(File excelFile){
		Map<String, List<List<Object>>> dataMap = new HashMap<>();
		InputStream in = null;
		Workbook wb = null;
		Sheet sheet;
		try {
			in=new FileInputStream(excelFile);
			wb=WorkbookFactory.create(in);
		} catch (InvalidFormatException e) {
			log.error("文件格式错误", e);
		} catch (IOException e) {
			log.error("读取失败", e);
		}
		
		int sheetNum = wb.getNumberOfSheets();
		for (int i = 0; i < sheetNum; i++) {
			sheet = wb.getSheetAt(i);
			String sheetName = sheet.getSheetName();
			dataMap.put(sheetName, readSheet(sheet, 0));
		}
		return dataMap;
	}
	/**
	 * 描述：读取第一个sheet
	 * 作者：Chenxj
	 * 日期：2016年3月27日 - 上午9:39:41
	 * @param excelFile
	 * @return
	 */
	public static List<List<Object>>readOneSheetFromExcel(File excelFile){
		return readSheetBySheetIndexFromExcel(excelFile, 0, 0);
	}
	/**
	 * 描述：读取指定Sheet名称，返回数据集
	 * 作者：Chenxj
	 * 日期：2016年3月27日 - 上午9:32:04
	 * @param excelFile Excel文件
	 * @param sheetName Sheet名称
	 * @param startIndex 开始行号
	 * @return CSV list
	 */
	public static List<List<Object>>readSheetBySheetNameFromExcel(File excelFile,String sheetName,int startIndex){
		InputStream in = null;
		Workbook wb = null;
		Sheet sheet;
		try {
			in=new FileInputStream(excelFile);
			wb=WorkbookFactory.create(in);
		} catch (InvalidFormatException e) {
			log.error("文件格式错误", e);
		} catch (IOException e) {
			log.error("读取失败", e);
		}
		sheet = wb.getSheet(sheetName);
		return readSheet(sheet, startIndex);
	}
	/**
	 * 描述：读取指定索引Sheet，返回数据集
	 * 作者：Chenxj
	 * 日期：2016年3月27日 - 上午9:30:49
	 * @param excelFile Excel文件
	 * @param sheetIndex Sheet索引号
	 * @param startIndex 开始行号
	 * @return CSV list
	 */
	public static List<List<Object>>readSheetBySheetIndexFromExcel(File excelFile,int sheetIndex,int startIndex){
		InputStream in = null;
		Workbook wb = null;
		Sheet sheet;
		try {
			in=new FileInputStream(excelFile);
			wb=WorkbookFactory.create(in);
		} catch (InvalidFormatException e) {
			log.error("文件格式错误", e);
		} catch (IOException e) {
			log.error("读取失败", e);
		}
		sheet = wb.getSheetAt(sheetIndex);
		return readSheet(sheet, startIndex);
	}
	/**
	 * 读取指定sheet
	 * @param sheet
	 * @param startIndex 开始行号
	 * @return list
	 */
	private  static List<List<Object>> readSheet(Sheet sheet,int startIndex){
		List<List<Object>> dataList = new ArrayList<List<Object>>();
		Row row;
		int rowNum = sheet.getPhysicalNumberOfRows();
		int firstRowColNum=-1;
		row=sheet.getRow(0);
		if(row!=null){
			firstRowColNum=row.getPhysicalNumberOfCells();
			if(startIndex==0){
				for(int i=startIndex;i<rowNum;i++){
					row=sheet.getRow(i);
					List<Object> rowDataList = new ArrayList<Object>();
					for(int j=0;j<firstRowColNum;j++){
						rowDataList.add(readCellValu(row.getCell(j)));
					}
					dataList.add(rowDataList);
				}
			}else if(startIndex>0&&startIndex<=rowNum){
				//读取第一行数据
				row = sheet.getRow(0);
				if(row!=null){
					List<Object> rowDataList = new ArrayList<Object>();
					for(int j=0;j<firstRowColNum;j++){
						rowDataList.add(readCellValu(row.getCell(j)));
					}
					dataList.add(rowDataList);
				}
				//从startIndex处开始读取数据
				for(int i=startIndex;i<rowNum;i++){
					row=sheet.getRow(i);
					List<Object> rowDataList = new ArrayList<Object>();
					for(int j=0;j<firstRowColNum;j++){
						rowDataList.add(readCellValu(row.getCell(j)));
					}
					dataList.add(rowDataList);
				}
			}
		}
		return dataList;
	}
	/**
	 * 描述：读取Excel格子中的值
	 * 作者：Chenxj
	 * 日期：2016年3月27日 - 上午9:16:31
	 * @param c Excel中的格子对象
	 * @return Object
	 */
	private  static Object readCellValu(Cell c) {
		if(c==null){
			return "";
		}else{
			switch (c.getCellType()) {
			case Cell.CELL_TYPE_BLANK:
				return "";
			case Cell.CELL_TYPE_BOOLEAN:
				return c.getBooleanCellValue();
			case Cell.CELL_TYPE_ERROR:
				return c.getErrorCellValue();
			case Cell.CELL_TYPE_FORMULA:
				return c.getCellFormula().replaceAll("\"", "");
			case Cell.CELL_TYPE_NUMERIC:
				if (DateUtil.isCellDateFormatted(c)) {
					return c.getDateCellValue();
				}
				return c.getNumericCellValue();
			case Cell.CELL_TYPE_STRING:
				return c.getStringCellValue();
			}
			return "Unknown Cell Type:" + c.getCellType();
		}
	}
	/**
	 * 描述：
	 * 作者：Chenxj
	 * 日期：2016年4月24日 - 下午8:15:27
	 * @param dataMap 要写入excel的数据
	 * @param cloName 自定义头
	 * @param out 要写入的Excel文件流
	 * @param excelType 要创建excel的版本，1是xls，2是xlsx
	 */
	public  static void createExcel(Map<String, List<Map<String, Object>>> dataMap,Map<String, String> cloNames,OutputStream out,int excelType){
		Workbook wb=null;
		switch (excelType) {
		case 1:
			wb=new HSSFWorkbook();
			break;
		case 2:
			wb=new XSSFWorkbook();
			break;
		default:
			wb=new HSSFWorkbook();
			break;
		}
		//创建一个日期格式style
		CellStyle cellStyle=wb.createCellStyle();
		DataFormat dataFormat=wb.createDataFormat();
		cellStyle.setDataFormat(dataFormat.getFormat("yyyy/MM/dd"));
		for(Entry<String, List<Map<String, Object>>> entry:dataMap.entrySet()){
			Sheet sheet=wb.createSheet(entry.getKey());
			List<Map<String, Object>> mapList=entry.getValue();
			int rowNum=mapList.size();
			if(rowNum>0){
				Row firstRow=sheet.createRow(0);
				Map<String,Object>m=mapList.get(0);
				List<String>titleList=new ArrayList<String>();
				if(cloNames==null){
					//获取title
					for(String title:m.keySet()){
						titleList.add(title);
					}
					//设置title
					for(int i=0;i<titleList.size();i++){
						firstRow.createCell(i).setCellValue(titleList.get(i));
					}
				}else{
					//获取title
					for(String title:cloNames.keySet()){
						titleList.add(title);
					}
					//设置title
					for(int i=0;i<titleList.size();i++){
						firstRow.createCell(i).setCellValue(cloNames.get(titleList.get(i)));
					}
				}
				//设置值
				for(int i=0;i<rowNum;i++){
					Row row=sheet.createRow(i+1);
					m=mapList.get(i);
					for(int j=0;j<m.size();j++){
						Cell c=row.createCell(j);
						Object o=m.get(titleList.get(j));
						if(o instanceof String){
							c.setCellValue(o+"");
						}else if(o instanceof Date){
							c.setCellStyle(cellStyle);
							c.setCellValue((Date)o);
						}else if(o instanceof Calendar){
							c.setCellValue((Calendar)o);
						}else if(o instanceof Boolean){
							c.setCellValue(((Boolean) o).booleanValue());
						}else if(o instanceof Double){
							c.setCellValue(((Double) o).doubleValue());
						}else if(o instanceof Integer){
							c.setCellValue(((Integer) o).doubleValue());
						}else if(o instanceof Float){
							c.setCellValue(((Float) o).floatValue());
						}else if(o instanceof Long){
							c.setCellValue(((Long) o).longValue());
						}else if(o instanceof Byte){
							c.setCellErrorValue(((Byte) o).byteValue());
						}else if(o==null){
							c.setCellValue("");
						}else{
							c.setCellValue("未知数据类型！");
						}
					}
				}
			}//
		}
		//写入Excel流
		try {
			wb.write(out);
		} catch (IOException e1) {
			log.error("写入Excel流失败", e1);
		}
		//关闭workbook对象
		try {
			wb.close();
		} catch (IOException e) {
			log.error("关闭workbook对象失败", e);
		}
	}
	/**
	 * 描述：
	 * 作者：Chenxj
	 * 日期：2016年4月24日 - 下午8:17:47
	 * @param dataMap 要写入excel的数据
	 * @param cloNames 自定义头
	 * @param out 输出
	 * @param overtake65535RenderNewSheet 超过65535新建sheet
	 * @param excelType 文件类型
	 */
	public static void createExcel(Map<String, List<Map<String, Object>>> dataMap,Map<String, String> cloNames,OutputStream out,boolean overtake65535RenderNewSheet,int excelType){
		Workbook wb=null;
		switch (excelType) {
		case 1:
			wb=new HSSFWorkbook();
			break;
		case 2:
			wb=new XSSFWorkbook();
			break;
		default:
			wb=new HSSFWorkbook();
			break;
		}
		//创建一个日期格式style
		CellStyle cellStyle=wb.createCellStyle();
		DataFormat dataFormat=wb.createDataFormat();
		cellStyle.setDataFormat(dataFormat.getFormat("yyyy/MM/dd"));
		//用于超过最大row是新sheet的名称中
		int flag=1;
		for(Entry<String, List<Map<String, Object>>> entry:dataMap.entrySet()){
			Sheet sheet=wb.createSheet(entry.getKey());
			List<Map<String, Object>> mapList=entry.getValue();
			int rowNum=mapList.size();
			if(rowNum>0){
				Row firstRow=sheet.createRow(0);
				Map<String,Object>m=mapList.get(0);
				List<String>titleList=new ArrayList<String>();
				if(cloNames==null){
					//获取title
					for(String title:m.keySet()){
						titleList.add(title);
					}
					//设置title
					for(int i=0;i<titleList.size();i++){
						firstRow.createCell(i).setCellValue(titleList.get(i));
					}
				}else{
					//获取title
					for(String title:cloNames.keySet()){
						titleList.add(title);
					}
					//设置title
					for(int i=0;i<titleList.size();i++){
						firstRow.createCell(i).setCellValue(cloNames.get(titleList.get(i)));
					}
				}
				//设置值
				for(int i=0,f=0;i<rowNum;i++,f++){
					//如果超过65535行数据则新建一个Sheet
					if(i!=0&&i%Constant.EXCEL_MAX_ROW==0){
						i=0;rowNum=rowNum-Constant.EXCEL_MAX_ROW;
						sheet=wb.createSheet(entry.getKey()+(flag++));
						firstRow=sheet.createRow(0);
						if(cloNames==null){
							//设置title
							for(int j=0;j<titleList.size();j++){
								firstRow.createCell(j).setCellValue(titleList.get(j));
							}
						}else{
							//设置title
							for(int j=0;j<titleList.size();j++){
								firstRow.createCell(j).setCellValue(cloNames.get(titleList.get(j)));
							}
						}
					}
					Row row=sheet.createRow(i+1);
					m=mapList.get(f);
					for(int j=0;j<m.size();j++){
						Cell c=row.createCell(j);
						Object o=m.get(titleList.get(j));
						if(o instanceof String){
							c.setCellValue(o+"");
						}else if(o instanceof Date){
							c.setCellStyle(cellStyle);
							c.setCellValue((Date)o);
						}else if(o instanceof Calendar){
							c.setCellValue((Calendar)o);
						}else if(o instanceof Boolean){
							c.setCellValue(((Boolean) o).booleanValue());
						}else if(o instanceof Double){
							c.setCellValue(((Double) o).doubleValue());
						}else if(o instanceof Integer){
							c.setCellValue(((Integer) o).doubleValue());
						}else if(o instanceof Float){
							c.setCellValue(((Float) o).floatValue());
						}else if(o instanceof Long){
							c.setCellValue(((Long) o).longValue());
						}else if(o instanceof Byte){
							c.setCellErrorValue(((Byte) o).byteValue());
						}else if(o==null){
							c.setCellValue("");
						}else{
							c.setCellValue("未知数据类型！");
						}
					}
				}
			}
		}
		//写入Excel流
		try {
			wb.write(out);
		} catch (IOException e1) {
			log.error("写入Excel流失败", e1);
		}
		//关闭workbook对象
		try {
			wb.close();
		} catch (IOException e) {
			log.error("关闭workbook对象失败", e);
		}
	}
}
