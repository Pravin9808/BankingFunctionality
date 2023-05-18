package testCases;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import BOBfunctionality.BaseClass;

public class OfferItemList extends BaseClass {
	@Test
	public void offerItemList() throws InterruptedException, IOException {
		Thread.sleep(6000);
		WebElement loadMore=driver.findElement(By.xpath("//button[normalize-space()='Load More']"));
		 ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", loadMore );
		 loadMore.click();
		
		// driver.findElement(By.cssSelector("div.popupCloseBtn > svg > path")).click();
		 driver.findElement(By.xpath("//a[contains(@data-card,'Rupay Card')]")).click();
		
		 List<WebElement> options=driver.findElements(By.xpath("//div[@class='bob-offer-card-div']/div[@class='bob-offer-card-btn-div']/a"));
		 ArrayList<String> ar=new ArrayList<String>();
		 for (int j = 0; j < options.size(); j++) {
			Thread.sleep(5000);
			String alinkName = options.get(j).getAttribute("href");
			ar.add(alinkName);			
		}
		
		HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("itemLinks");
        int rowno = 0;
        int k=0;

       for (int j=0;j<ar.size();j++) {
    	   HSSFRow row = sheet.createRow(rowno++);
    	   String data=ar.get(j);
    	   int i=0;
           row.createCell(0).setCellValue(data);        
    	      
       }  
       
      
	
	FileOutputStream fos = new FileOutputStream(".//DataFiles//OfferItem.xls");
	workbook.write(fos);
	
	}

}
