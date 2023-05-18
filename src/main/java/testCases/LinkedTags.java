package testCases;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import BOBfunctionality.BaseClass;

public class LinkedTags extends BaseClass {
	@Test
	public void tags() throws InterruptedException, IOException {
		// long startTime= System.currentTimeMillis();
		List<WebElement> atags = driver.findElements(By.tagName("a"));
		//List<WebElement> imgtags= driver.findElements(By.tagName("img"));
		// ArrayList<String> ar= new ArrayList();
		HashMap<String, String> links = new HashMap<String, String>();
		for (int i = 0; i < atags.size(); i++) {
			Thread.sleep(5000);
			String alinkName = atags.get(i).getAttribute("href");
			// System.out.println(atags.get(i).getText());
			String atagsName = atags.get(i).getText();
			// ar.add(atagsName);
			try {
				links.put(alinkName, atagsName);
			} catch (Exception e) {
				e.getMessage();
			}
		}

		/*
		  for(int i=0;i<imgtags.size();i++) { 
			  Thread.sleep(5000); 
		  String imglinkName= imgtags.get(i).getAttribute("src");
		 //System.out.println(imgtags.get(i).getText()); 
		  String imgName=imgtags.get(i).getText(); //ar.add(imgName); 
		  try {
			  links.put(imglinkName, imgName);
		
		  } catch(Exception e) { e.getMessage(); } }
		
*/
		System.out.println(links);
		// long endTime= System.currentTimeMillis();
		// System.out.println(endTime-startTime);

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Links");
		int rowno = 0;

		for (Map.Entry entry : links.entrySet()) {
			
			HSSFRow row = sheet.createRow(rowno++);
			row.createCell(0).setCellValue((String) entry.getKey());
			row.createCell(1).setCellValue((String) entry.getValue());
			
		}
		FileOutputStream fos = new FileOutputStream(".//DataFiles//MahindraSitemapLink.xls");
		workbook.write(fos);
				

	}

}
