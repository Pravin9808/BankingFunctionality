package testCases;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import BOBfunctionality.BaseClass;

public class DownloadInFolder extends BaseClass {

	@Test
	public void downloadInFolder() throws InterruptedException, IOException
	{	
		WebElement nextButton=driver.findElement(By.xpath("//a[@aria-label='Next']"));
		JavascriptExecutor js=(JavascriptExecutor)driver;
		List<WebElement> listBlogs= driver.findElements(By.xpath("//div[@class='product-1 list-group']//div[@class='productContentsection']/a"));
		List<String> ar=new ArrayList<String>();
		for (WebElement blogs:listBlogs) {
			ar.add(blogs.getText());
			System.out.println(blogs.getText());
			js.executeScript("arguments[0].scrollIntoView(true);",blogs );
			Thread.sleep(6000);
			while(true) {
				nextButton.click();
				Thread.sleep(6000);
				try{
					listBlogs= driver.findElements(By.xpath("//div[@class='product-1 list-group']//div[@class='productContentsection']/a"));
					for (WebElement blogs1:listBlogs) {
						ar.add(blogs1.getText());
						js.executeScript("arguments[0].scrollIntoView(true);",blogs1);
					}
				}
					catch(Exception e) {
						System.out.println("No More product Available");
					}
			}
		} 
		 	/*
			while(true) {
				nextButton.click();
				Thread.sleep(6000);
				try{
				listBlogs= driver.findElements(By.xpath("//div[@class='product-1 list-group']//div[@class='productContentsection']/a"));
				for (WebElement blogs:listBlogs) {
					ar.add(blogs.getText());
					js.executeScript("arguments[0].scrollIntoView(true);",blogs );
				}
				
									}
				catch(Exception e) {
					System.out.println("No More product Available");
				}
		}*/
	}	
	
	}
		
		/* 
		List<String> ar=new ArrayList<String>();
		int sizeofPagination=driver.findElements(By.xpath("//ul[@class='pagination']//a")).size();
		if(sizeofPagination>0) {
	    	    
		    do {
		    Thread.sleep(5000);
		    List<WebElement> listBlogs= driver.findElements(By.xpath("//div[@class='product-1 list-group']//div[@class='productContentsection']/a"));
		    for (WebElement blog:listBlogs) {
			ar.add(blog.getText());
			Thread.sleep(5000);
		    WebElement nextButton=driver.findElement(By.xpath("//li[@class='next-page']"));
		    String nextClassName=nextButton.getAttribute("class");
		    if(!nextClassName.contains("next-page disabled")) {
			   nextButton.click();
		   }else {}
		   break;
		}
			}  while(true) ; 
		}else {
			
		}
		for (String arName:ar) {
			System.out.println(arName);
		}*/
			//String resultSorted=item.get(i).getText();
			//ar.put(datayearName,categoryName);
		
		
			 
		
	/*
		System.out.println(ar);
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Links");
		int rowno = 0;

		for (Map.Entry entry :ar.entrySet()) {
			
			HSSFRow row = sheet.createRow(rowno++);
			row.createCell(0).setCellValue((String) entry.getKey());
			row.createCell(1).setCellValue((String) entry.getValue());
			
		}
		FileOutputStream fos = new FileOutputStream(".//DataFiles//SortedResult.xls");
		workbook.write(fos);
		*/
	
