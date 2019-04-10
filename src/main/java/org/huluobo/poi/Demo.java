package org.huluobo.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Demo {
	public static void main(String[] args) throws EncryptedDocumentException, FileNotFoundException, IOException {
		String absoluteFilePath="e:\\2018120213.xlsx";
		Workbook workbook = WorkbookFactory.create(new FileInputStream(absoluteFilePath)); 
		Sheet sheet = workbook.createSheet("2018-12-02"); 
		
		Row row=sheet.createRow(0);
		Cell cell_0=row.createCell(0);
		cell_0.setCellValue("编号");
		Cell cell_1=row.createCell(1);
		cell_1.setCellValue("名称");
		
		/*计划工期
		计划开始时间
		计划结束时间
		实际开始时间
		实际结束时间*/
		FileOutputStream fileOutputStream = new FileOutputStream(new File(absoluteFilePath));
		workbook.write(fileOutputStream);
	}
}
