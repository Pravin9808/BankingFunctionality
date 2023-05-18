package testCases;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import BOBfunctionality.BaseClass;



public class SearchFunctionality extends BaseClass {
	
@Test
public void search() throws InterruptedException, IOException {
	driver.findElement(By.xpath("//div[@class='privacy-warning acceptonclose']/div[@class='close']")).click();
	driver.findElement(By.xpath("//a[@class='search-popup']")).click();
	driver.findElement(By.xpath("//input[@placeholder='Looking for something specific?']")).sendKeys("Home Loan");
    Thread.sleep(5000);
	List<WebElement> options=driver.findElements(By.xpath("//ul[@class='search-result-list']/li/h4"));
	System.out.println(options.size());
	ArrayList<String> ar=new ArrayList<String>();
	
	for (int i=0;i<options.size();i++) {
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", options.get(i) );
		 String text= options.get(i).getText();		
		ar.add(text);
		}
	    System.out.println(ar);	
	
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Links");
        int rowno = 0;
        for (int j=0;j<ar.size();j++) {
    	   HSSFRow row = sheet.createRow(rowno++);
    	   String data=ar.get(j);
    	   row.createCell(0).setCellValue(data);        
    	      
       }  
      
	
	FileOutputStream fos = new FileOutputStream(".//DataFiles//Search.xls");
	workbook.write(fos);
}
}
