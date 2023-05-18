package testCases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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

public class MobileNumberVerfication {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		//strong[normalize-space()='1800 5700']
		//WebDriverManager.firefoxdriver().setup();
		WebDriverManager.chromedriver().setup();		
		ChromeOptions opt = new ChromeOptions();
		//opt.addArguments("--headless");
		opt.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(opt);
		//WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();			
		driver.manage().deleteAllCookies();
		Long startTime=System.currentTimeMillis();
		driver.get("https://www.bankofbaroda.in");
     	driver.findElement(By.xpath("//div[@class='privacy-warning acceptonclose']/div[@class='close']")).click();
		//HashMap<String, String> links = new HashMap<String, String>();
			FileInputStream fs = new FileInputStream( System.getProperty("user.dir")+"//DataFiles//BOBURLList.xlsx");
			ArrayList sizePlacement=new ArrayList();
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
				        //Cell cell=currentrow.getCell(j);
				        	//Create a loop to print cell values in a row
				            //Print Excel data in console
				        	String value1= currentrow.getCell(j).getStringCellValue();
				        	driver.get(value1);
				        	//Thread.sleep(3000);	
				        	JavascriptExecutor js = (JavascriptExecutor) driver;
				            // Scrolling down the page till the element is found		
				        	js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
				            //Boolean tollFree = driver.findElement(By.xpath("//strong[normalize-space()='1800 5700']")).isDisplayed();
				        	if(driver.getPageSource().contains("Disclaimer")==true) {
				        		System.out.println(value1);
				        		
				        	}else {
				        		continue;
				        	}
				        	
				           	//Thread.sleep(3000);
				        	//WebElement tollFree=driver.findElement(By.xpath("//a/span[contains(text(),'Free Number')]"));	
				        	//String tollFreeNumber=tollFree.getText();
				        	//System.out.println(value1+"|"+ tollFreeNumber);
				        	//System.out.println(tollFreeNumber);
				        	//tollFree.click();
				    }
			  }
	}

	}}
