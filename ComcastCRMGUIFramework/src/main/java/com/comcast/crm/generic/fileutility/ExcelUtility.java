package com.comcast.crm.generic.fileutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility 
{
	public String getDataFromExcel(String sheetName,int row,int cell) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis = new FileInputStream("testData/TestScriptData.xlsx");
		Workbook workbook=WorkbookFactory.create(fis);
		Sheet sheet=workbook.getSheet(sheetName);
		workbook.close();
		return sheet.getRow(row).getCell(cell).getStringCellValue();
	}
	
	public int getColumnCount(String sheet) throws IOException
	{
		FileInputStream fis = new FileInputStream("testData/TestScriptData.xlsx");
		Workbook workbook=WorkbookFactory.create(fis);
		workbook.close();
		return workbook.getSheet(sheet).getRow(0).getPhysicalNumberOfCells();		
	}
	
	public int getRowCount(String sheet) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis = new FileInputStream("testData/TestScriptData.xlsx");
		Workbook workbook=WorkbookFactory.create(fis);
		workbook.close();
		return workbook.getSheet(sheet).getLastRowNum();
	}
	
	public void setDataIntoExcel(String sheet,int row,int cell,String data) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis = new FileInputStream("testData/TestScriptData.xlsx");
		Workbook workbook=WorkbookFactory.create(fis);
		workbook.getSheet(sheet).getRow(row).createCell(cell).setCellValue(data);
		FileOutputStream fos = new FileOutputStream("testData/TestScriptData.xlsx");
		workbook.write(fos);
		workbook.close();
	}
}