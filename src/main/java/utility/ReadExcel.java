package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

	public static String[][] excel(String sheetName) throws IOException {

		FileInputStream fis = new FileInputStream(new File("D:\\FullStackAutomation_Exercise\\PageObjectModel\\TestData\\test001.xlsx"));

		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet ws = wb.getSheet(sheetName);
		int RowCount = ws.getLastRowNum();
		short ColmCount = ws.getRow(0).getLastCellNum();
		String[][] data = new String[RowCount][ColmCount];
		String cellValue;
		for (int i = 1; i <= RowCount; i++) {
			for (int j = 0; j < ColmCount; j++) {
				cellValue = ws.getRow(i).getCell(j).getStringCellValue();
				System.out.println(cellValue);
				data[i - 1][j] = cellValue;
			}
		}
		wb.close();
		return data;

	}

}
