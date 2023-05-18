package testCases;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import BOBfunctionality.BaseClass;

public class BOBRDCalculator extends BaseClass {
	
	@Test(priority=0)
	public void bobIDBIRDCalTesting() throws InterruptedException, IOException, InvalidFormatException  {
		FileInputStream inpFiles = new FileInputStream(System.getProperty("user.dir")+"//InputFiles//InputFiles.xlsx");
		XSSFWorkbook inpDataWkb = new XSSFWorkbook(inpFiles);
		XSSFSheet inpSheet= inpDataWkb.getSheetAt(2);
		
		int inpRowCount = inpSheet.getPhysicalNumberOfRows()-inpSheet.getFirstRowNum();
		
		FileInputStream amrtFile=new FileInputStream(".//DataFiles//BOBAmrtSchd.xlsx");
		XSSFWorkbook wkb = new XSSFWorkbook(amrtFile); 
		for(int i=0;i<wkb.getNumberOfSheets();i++){
            //System.out.println(wkb.getSheetAt(i).getSheetName());
            if(wkb.getSheetAt(i).getSheetName().equals("CarLoanCalData")){
                wkb.removeSheetAt(i);
            }
        } 
		//wkb.removeSheetAt(1);
		wkb.createSheet("CarLoanCalData");
		
		for (int i = 2; i <=inpRowCount+1; i++) {
			XSSFRow currentrow = inpSheet.getRow(i);
						
			    DataFormatter formatter = new DataFormatter();
				String amount = formatter.formatCellValue(currentrow.getCell(0));			        			        	
	        	String rateOfInterest = formatter.formatCellValue(currentrow.getCell(1));
				String loanTermsInMonths = formatter.formatCellValue(currentrow.getCell(2));
				String loanTermsInYears = formatter.formatCellValue(currentrow.getCell(3));
				
				XSSFSheet sheet1 = wkb.getSheet("CarLoanCalData");
				int initialRow=sheet1.getLastRowNum()+2;
				
				driver.get("https://www.bankofbaroda.in/calculators/recurring-deposit-calculator");
				driver.findElement(By.xpath("//div[@class='privacy-warning acceptonclose']/div[@class='close']")).click();
				driver.manage().deleteAllCookies();
				Thread.sleep(5000);
				
				
				
			List<WebElement> typeCustomer= driver.findElements(By.xpath("//div[@class='bob-cal-bbutton-div']//input[@name='radioCust']"));
			for (int k=1;k<=typeCustomer.size();k++) {
				typeCustomer.get(k).click();						
				
				Actions slider=new Actions(driver);
				WebElement amountTextBoxElement = driver.findElement(By.xpath("//small[contains(text(),'₹')]"));
				// create an instance of the Actions class and perform double click
				
				slider.doubleClick(amountTextBoxElement).build().perform();
				slider.sendKeys(amount).build().perform();
				
				WebElement savingTermsYears  = driver.findElement(By.xpath("//em[normalize-space()='0']"));

				slider.doubleClick(savingTermsYears).build().perform();
				savingTermsYears.sendKeys(Keys.BACK_SPACE);
				slider.sendKeys(savingTermsYears).build().perform();
				
				WebElement savingTermsMonths = driver.findElement(By.xpath("//em[normalize-space()='6']"));
				slider.doubleClick(savingTermsMonths).build().perform();
				slider.sendKeys(savingTermsMonths).build().perform();
				
				String rdInterestRate = driver.findElement(By.xpath("//strong[@id='interestRate']")).getText();
			
				String maturityDateBOB  = driver.findElement(By.xpath("//strong[@id='maturityDate']")).getText();
				String totalSavingsAmountBOB  = driver.findElement(By.xpath("//span[@id='totalSavingsAmount']")).getText();
				String intersetEarnedBOB  = driver.findElement(By.xpath("//strong[@id='interestEarned']")).getText();
				
				
				ArrayList<String> bobRdData=new ArrayList<String>();
				bobRdData.add(amount);
				bobRdData.add(rateOfInterest);
				bobRdData.add(loanTermsInMonths);		
				bobRdData.add(rdInterestRate);
				bobRdData.add(maturityDateBOB);
				bobRdData.add(totalSavingsAmountBOB);
				bobRdData.add(intersetEarnedBOB);
				
				
				//3,2;3,3;3,4
				int rowno2=initialRow;
				int rowno3=initialRow+1;
				XSSFRow row2 = sheet1.createRow(rowno2);
				XSSFRow row3=sheet1.createRow(rowno3);
				
				row2.createCell(1).setCellValue("Input Amount");
				row2.createCell(2).setCellValue("InterestRate");
				row2.createCell(3).setCellValue("LoanTenureInMonths/Years");
				
				
				row2.createCell(4).setCellValue("Maturity Date");
				row2.createCell(5).setCellValue("Total Saving Amount");
				row2.createCell(6).setCellValue("Interest Earned");
				
				
							
				row3.createCell(0).setCellValue("For BOB Bank");
				
				 for (int a=0;a<bobRdData.size();a++) {	
					 
		            XSSFCell cell = row3.createCell(a+1);
		             cell.setCellType(CellType.STRING);
		             cell.setCellValue((String) bobRdData.get(a)); 
		         }	
				

				try (FileOutputStream fos = new FileOutputStream(".//DataFiles//BOBAmrtSchd.xlsx")) {
					
					//fos.flush();  
					wkb.write(fos);     
					//fos.close();
		        }
				
			}
				
				
				
				
		}
		
		
    }
}
