package testCases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hpsf.Array;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JivoxIdVerification {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		//HashMap<String, String> sizePlacement = new HashMap<String, String>();
		WebDriverManager.firefoxdriver().setup();
		//WebDriverManager.chromedriver().setup();		
		FirefoxOptions opt = new FirefoxOptions();
		opt.addArguments("--headless");
		//opt.addArguments("--remote-allow-origins=*");
		//WebDriver driver = new ChromeDriver(opt);
		WebDriver driver = new FirefoxDriver(opt);
		 driver.manage().deleteAllCookies();
		driver.get("http://qa64.jivox.com/tags/tagrocket/");
		Long startTime=System.currentTimeMillis();
		//HashMap<String, String> links = new HashMap<String, String>();
			FileInputStream fs = new FileInputStream( System.getProperty("user.dir")+"//DataFiles//Tags_US_N_A_Lubriderm_2023_DM Addressable Display_US_Lubriderm_DCM Tracking_MTK-TTD_031423.xls");
			ArrayList sizePlacement=new ArrayList();
			//Creating a workbook 
			HSSFWorkbook workbook = new HSSFWorkbook(fs);
			HSSFSheet sheet = workbook.getSheetAt(0);
			  int rowCount = sheet.getPhysicalNumberOfRows()-sheet.getFirstRowNum();			 
			 	//Create a loop over all the rows of excel file to read it
				  int j=23;
				    for (int i = 11; i < rowCount+1; i++) {
				        HSSFRow currentrow = sheet.getRow(i);
				        if(currentrow.getCell(j)!= null) {
				        //Cell cell=currentrow.getCell(j);
				        	//Create a loop to print cell values in a row
				            //Print Excel data in console
				        	String value1= currentrow.getCell(j).getStringCellValue();
				        	driver.findElement(By.xpath("//textarea[@id='input']")).sendKeys(value1);
				        	//Thread.sleep(3000);			        
				        	String placementId=driver.findElement(By.xpath("//td[@id='placementId']")).getText();
				        	String Dimension=driver.findElement(By.xpath("//td[@id='unexpanded_dim']")).getText();
				        	String Securemsg=driver.findElement(By.xpath("//li[@class='message']")).getText();
				        	//String Securemsg=driver.findElement(By.xpath("//ul[@id='commonErrors']/li[@class='message'][2]")).getText();
				        	String DimSecMsg=Dimension.concat("|"+ Securemsg);
				        	String VerifiedPlacmentDim=placementId.concat("|"+DimSecMsg);				        	
				        	//System.out.println(placementId+ Dimension);
				        	//Thread.sleep(1000);
				        	sizePlacement.add(VerifiedPlacmentDim);
				        	System.out.println(VerifiedPlacmentDim);			        	 
				        	Actions action = new Actions(driver);	        	
				        	action.moveToElement(driver.findElement(By.xpath("//ul[@id='template_option']"))).click(driver.findElement(By.xpath("//li[normalize-space()='Blank Template']"))).build().perform();
				        }
				    }
			
			    //System.out.println(sizePlacement);		
			  		    
			    int rowno = 11;	
			    int cellid = 25;
			    HSSFRow row = sheet.getRow(rowno);
			    row.createCell(cellid).setCellValue("Jiv+Dim+Securemsg");
			    
			    for (Object obj : sizePlacement) {
			    	HSSFRow row2 = sheet.getRow(rowno++);
			    	
	                Cell cell = row2.createCell(cellid);
	                cell.setCellValue((String)obj);
	            }				
				FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir")+"//DataFiles//Tags_US_N_A_Lubriderm_2023_DM Addressable Display_US_Lubriderm_DCM Tracking_MTK-TTD_031423.xls");
				workbook.write(fos);		
				Long endTime=System.currentTimeMillis();
				System.out.println((endTime-startTime)/60000+"min");
				//driver.quit();
	}

}
