package com.myecom.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.myecom.base.BaseClass;

public class ExcelSheetHandle extends BaseClass {
	
	public static FileInputStream fis;
	
	public FileInputStream getExcelFile() throws FileNotFoundException {
	
		fis = new FileInputStream(projectPath+"//src//test//resources//testdata//demodata.xlsx");
		return fis;
	}
	
	public Sheet getSheet(FileInputStream fileName,String sheetName) {
		Sheet sh = null;
		try {
			sh = WorkbookFactory.create(fileName).getSheet(sheetName);
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sh;
	}
	
	public Map<String, Object> getExcelSheetData(Sheet sh) {
		
		int getRow = sh.getLastRowNum();
				
		Map<String, Object> data = new HashMap<>();
		
		for(int i=0; i<getRow; i++) {
			
			int col = sh.getRow(i).getLastCellNum();
			
			for(int j=0; j<col; j++) {
				
				if(sh.getRow(i).getCell(j).getCellType().name().equals("STRING")) {
					data.put(sh.getRow(0).getCell(j).getStringCellValue(), 
							sh.getRow(i+1).getCell(j).getStringCellValue());
				}
				else if(sh.getRow(i).getCell(j).getCellType().name().equals("NUMERIC")) {
					data.put(sh.getRow(0).getCell(j).getStringCellValue(), 
							sh.getRow(i+1).getCell(j).getNumericCellValue());
				}
				
			}
		}
		
		return data;
	}
	
	public String getSingleStringVale(Sheet sh,int row, int col) {
		return sh.getRow(row).getCell(col).getStringCellValue();
	}
}
