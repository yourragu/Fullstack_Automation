package com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook; 

public class ReadExcel {

	public static String[][] readData(String path,String wbName,String sheetName) throws IOException{

		FileInputStream fis = new FileInputStream(new File(path+wbName));
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet ws = wb.getSheet(sheetName);
		 int rowCount = ws.getLastRowNum();
		//int rowCount = ws.getPhysicalNumberOfRows();
		System.out.println("Total No of rows : "+rowCount);
		//int ColCount = ws.getLastRowNum();
		short ColCount = ws.getRow(0).getLastCellNum();
		System.out.println("Total No of cols : "+ColCount);		
		String[][] data = new String[rowCount][ColCount];
		for (int i = 1; i <= rowCount; i++) {
			for (int j = 0; j < ColCount; j++) {

				String CellValue = ws.getRow(i).getCell(j).getStringCellValue();
				data[i-1][j] = CellValue;
				System.out.println(CellValue);				
			}
		}

		// XSSFRow row = ws.getRow(0);
		// int physicalNumberOfRows = ws.getPhysicalNumberOfRows();
		// XSSFCell cell = row.getCell(0);
		// String CellValue = cell.getStringCellValue();
		// System.out.println(CellValue);
		wb.close();		
		return data;

	}

}
