package testCases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import BOBfunctionality.BaseClass;

public class DropdownItems extends BaseClass {
	@Test
	public void dropdownItem() throws IOException  {
		FileInputStream fs = new FileInputStream( System.getProperty("user.dir")+"//DataFiles//BOBURLList.xlsx");
		driver.findElement(By.xpath("//div[@class='privacy-warning acceptonclose']/div[@class='close']")).click();
		//Creating a workbook 
		XSSFWorkbook workbook = new XSSFWorkbook(fs);
		XSSFSheet sheet = workbook.getSheetAt(2);
		  int rowCount = sheet.getPhysicalNumberOfRows()-sheet.getFirstRowNum();			 
		 	//Create a loop over all the rows of excel file to read it
		  for (int i = 10; i < rowCount+1; i++) {
			XSSFRow currentrow = sheet.getRow(i);			  
		         //Create a loop to print cell values in a row
		        for (int j = 0; j < currentrow.getLastCellNum(); j++) {				       
			        if(currentrow.getCell(j)!= null) {
			        	
			        	ArrayList ar=new ArrayList();			 
			        	//Cell cell=currentrow.getCell(j);
			        	//Create a loop to print cell values in a row
			            //Print Excel data in console
			        	String value1= currentrow.getCell(j).getStringCellValue();
			        	driver.get(value1);
			        	 try {
			        	Boolean isDisplay=driver.findElement(By.xpath("//h6[normalize-space()='Request Callback']")).isDisplayed();
			        	if(isDisplay==true) {
			        		System.out.println(value1);				    		
				    		WebElement requestCallback = driver.findElement(By.xpath("//div[@class='bob-custom-dropdown mt-0']//span[@class='form-control']"));
				    		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",requestCallback);
				    		Actions action= new Actions(driver);
				    		action.moveToElement(requestCallback).perform();
				    		Thread.sleep(6000);
				    		requestCallback.click();
		    		
				    		List<WebElement> items1 = driver.findElements(By.xpath("//div[@class='bob-custom-dropdown mt-0']//ul[@id='loantype']//div///div//li"));
				    		/*WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(50));
				    		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath( "//ul[@id='loantype']/div/div/li")));*/
				    		
				    		System.out.println(items1.size());
				    		for (int k=0;k<items1.size();k++) {	
				    			Thread.sleep(3000);
				    			((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",items1.get(k) );				    		
				    			String liText = items1.get(k).getText();
				    			System.out.println(liText);
				    			ar.add(liText);					    			
				    			
				    		}
				    		System.out.println(ar);
			        	}
				    		else {
				    			continue;
				    			}
				    		
				    		}
				     
			        	 catch( Exception e) {
			        		 e.getMessage();
			        	 }
			    		
			        	
			    }  
			        
		  }
		
		  }       

	}

}
