package testCases;

import java.awt.Desktop.Action;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import BOBfunctionality.BaseClass;
import utilities.ReadConfig;
import utilities.XLUtility;

public class BOBPLCalculator extends BaseClass {
	ReadConfig readconfig=new ReadConfig();
	
	
	
	@Test(priority=0)
	public void axisPlCalAmortisation() throws InterruptedException, IOException, InvalidFormatException  {
		
		FileInputStream inpFiles = new FileInputStream(System.getProperty("user.dir")+"//InputFiles//InputFiles.xlsx");
		XSSFWorkbook inpDataWkb = new XSSFWorkbook(inpFiles);
		XSSFSheet inpSheet= inpDataWkb.getSheetAt(0);
		
		int inpRowCount = inpSheet.getPhysicalNumberOfRows()-inpSheet.getFirstRowNum();
		FileInputStream amrtFile=new FileInputStream(System.getProperty("user.dir")+"//DataFiles//bobamrtschd.xlsx");
		
		XSSFWorkbook wkb = new XSSFWorkbook(amrtFile);  
		wkb.removeSheetAt(0);
		wkb.createSheet("PersonalLoanCalData");
		
		
		 for (int i = 1; i <=inpRowCount+1; i++) {
				XSSFRow currentrow = inpSheet.getRow(i);
				FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir")+"//DataFiles//bobamrtschd.xlsx");
				driver.get("https://www.axisbank.com/retail/calculators/personal-loan-emi-calculator");
				driver.manage().deleteAllCookies();
				Thread.sleep(6000);
			         //Create a loop to print cell values in a row
					    DataFormatter formatter = new DataFormatter();
						String amount = formatter.formatCellValue(currentrow.getCell(0));			        			        	
			        	String rateOfInterest = formatter.formatCellValue(currentrow.getCell(1));
						String loanTermsInMonths = formatter.formatCellValue(currentrow.getCell(2));
						//String loanTermsInYears = currentrow.getCell(1).getStringCellValue();
						
					/*
						System.out.println(amount);
						System.out.println(rateOfInterest);
						System.out.println(loanTermsInMonths);
						System.out.println(inpRowCount);*/
						
						
						
						driver.findElement(By.xpath("//input[@id='loan_amount']")).sendKeys(Keys.CONTROL,"a");
						driver.findElement(By.xpath("//input[@id='loan_amount']")).sendKeys(Keys.BACK_SPACE);
					
						driver.findElement(By.xpath("//input[@id='loan_amount']")).sendKeys(amount);
						driver.findElement(By.xpath("//input[@id='interest_rate']")).sendKeys(Keys.CONTROL,"a");
						driver.findElement(By.xpath("//input[@id='interest_rate']")).sendKeys(Keys.BACK_SPACE);
						/*driver.findElement(By.xpath("//input[@id='interest_rate']")).sendKeys(Keys.CONTROL,"a");
						driver.findElement(By.xpath("//input[@id='interest_rate']")).sendKeys(Keys.BACK_SPACE);*/
						
						driver.findElement(By.xpath("//input[@id='interest_rate']")).sendKeys(rateOfInterest);
						driver.findElement(By.xpath("//input[@id='tenure']")).sendKeys(Keys.CONTROL,"a");
						driver.findElement(By.xpath("//input[@id='tenure']")).sendKeys(Keys.BACK_SPACE);
						
						driver.findElement(By.xpath("//input[@id='tenure']")).sendKeys(loanTermsInMonths);
						//driver.findElement(By.xpath("//strong[normalize-space()='Amortization']")).click();
						System.out.println("-----Axis Bank PL & Amortisation Data---------");
						
						String emi_axis=driver.findElement(By.xpath("//span[@id='lblEMIAmt']")).getText();
						String int_paym_axis=driver.findElement(By.xpath("//span[@id='intrAmt']")).getText();
						String totalAmt_paybleAxis = driver.findElement(By.xpath("//span[@id='totalPayAmt']")).getText();
						System.out.println("EMI :"+emi_axis);
						System.out.println("Interest Amount Payable :"+int_paym_axis);
						
					
						XSSFSheet sheet1 = wkb.getSheet("PersonalLoanCalData");
						int initialRow=sheet1.getLastRowNum()+2;
						System.out.println(initialRow);
						
						
						
						Thread.sleep(5000);
						
						ArrayList<String> axisPlData=new ArrayList<String>();
						axisPlData.add(amount);
						axisPlData.add(rateOfInterest);
						axisPlData.add(loanTermsInMonths);		
						axisPlData.add(emi_axis);
						axisPlData.add(int_paym_axis);
						
						//3,2;3,3;3,4
						int rowno=initialRow;
						int cellid=0;
						XSSFRow row2 = sheet1.createRow(rowno);
						
						row2.createCell(0).setCellValue("For Axis Bank");
						row2.createCell(11).setCellValue("Amortisation Axis Bank");
						row2.createCell(18).setCellValue("Amortisation BOB Bank");
						
						 for (int a=0;a<axisPlData.size();a++) {	
							 
				            XSSFCell cell = row2.createCell(a+1);
				             cell.setCellType(CellType.STRING);
				             cell.setCellValue((String) axisPlData.get(a)); 
				         }	
						
						List<WebElement> irows =   driver.findElements(By.xpath("//div[@id='trDynamic']//div[@class='tableRow totalCalc']|//div[@id='trDynamic']//div[@class='tableRow tableHead']"));
						List<WebElement> icols =   driver.findElements(By.xpath("//div[@id='trDynamic']//div[@class='tableRow totalCalc'][1]//div[@class='tableCell']"));
						System.out.println( irows.size());
						System.out.println(icols.size());
						
						
						for (int j=1;j<=irows.size();j++) {
							
							//List<WebElement> icols =   driver.findElements(By.xpath("//table[@id='personalLoanamortizationEligibility']//tbody["+i+"]/tr/td"));
							XSSFRow excelRow = sheet1.createRow(j+initialRow);//initialRow;//getRow+//
							System.out.println( icols.size());
							for (int k=1; k<=icols.size();k++) { 
							
								int offSetCl=16;
								if(j==1) {		
										WebElement val= driver.findElement(By.xpath("//div[@id='trDynamic']//div[@class='tableRow tableHead']//div[contains(@class,'tableCell ')]["+k+"]"));
										String  valhead = val.getText();            
										System.out.print(valhead);
										
										XSSFCell excelCell = excelRow.createCell(k+offSetCl,CellType.STRING);                  
										                
										excelCell.setCellValue(valhead);  
					      
										}
								else {
									int m=j-1;	
									WebElement valbody= driver.findElement(By.xpath("//div[@id='trDynamic']//div[@class='tableRow totalCalc']["+m+"]//div[@class='tableCell']["+k+"]"));
									String  valbodyText = valbody.getText();            
									System.out.print(valbodyText);
									XSSFCell excelCell = excelRow.createCell(k+offSetCl,CellType.STRING);                   
									//excelCell.setCellType(CellType.STRING);                 
									excelCell.setCellValue(valbodyText);  
												
									}
								}               
							  
							} 						
						
						  
						wkb.write(fos);
						
						
						driver.get("https://www.bankofbaroda.in/calculators/personal-loan-emi-calculator");
						driver.findElement(By.xpath("//div[@class='privacy-warning acceptonclose']/div[@class='close']")).click();
						Actions actions = new Actions(driver);
						WebElement amountTextBoxElement = driver.findElement(By.xpath("//small[contains(text(),'₹')]"));
						// create an instance of the Actions class and perform double click
						actions.doubleClick(amountTextBoxElement).perform();
						actions.sendKeys(amount).perform();
						Thread.sleep(1000);
		
						WebElement rateofInterestTextBoxElement = driver.findElement(By.xpath("//em[normalize-space()='10.90']"));
						actions.doubleClick(rateofInterestTextBoxElement).perform();
						//rateofInterestTextBoxElement.sendKeys(Keys.BACK_SPACE);
						actions.sendKeys(rateOfInterest).perform();
						
						
						WebElement loanTermsTextBoxElement = driver.findElement(By.xpath("//em[normalize-space()='18']"));
						actions.doubleClick(loanTermsTextBoxElement).perform();
						actions.sendKeys(loanTermsInMonths).perform();
		
		
						String intAmt_BOB=driver.findElement(By.xpath("//strong[@id='spn_interest_amt']")).getText();
						String emi_Amt_BOB=driver.findElement(By.xpath("//h6[@id='monthly-hl-emi']")).getText();
						System.out.print(intAmt_BOB);
						System.out.print(emi_Amt_BOB);
						WebElement amrtScheduleBOBtable=driver.findElement(By.xpath("//table[@id='personalLoanamortizationEligibility']"));
						List<WebElement> irowsBOB =   driver.findElements(By.xpath("//table[@id='personalLoanamortizationEligibility']//tr"));
						List<WebElement> icolsBOB =   driver.findElements(By.xpath("//table[@id='personalLoanamortizationEligibility']//tbody[1]/tr/td"));
						System.out.println( irows.size());
						int iRowsCount = irowsBOB.size();
																
						
						for (int j=1;j<=iRowsCount;j++) {
							
							//List<WebElement> icols =   driver.findElements(By.xpath("//table[@id='personalLoanamortizationEligibility']//tbody["+i+"]/tr/td"));
							XSSFRow excelRow = sheet1.createRow(j+initialRow);
							System.out.println( icols.size());
							for (int k=1; k<=icols.size();k++) { 
								int offSetCl=8;
								
								if(j==1) {		
										
											
										//WebElement val= driver.findElement(By.xpath("//table[@id='personalLoanamortizationEligibility']//thead["+i+"]//tr//th["+j+"]|//table[@id='personalLoanamortizationEligibility']//tbody["+i+"]//tr//td["+j+"]"));
									    //WebElement val= driver.findElement(By.xpath("//table[@id='personalLoanamortizationEligibility']//tbody["+i+"]//tr//td["+j+"]"));
										WebElement val= driver.findElement(By.xpath("//table[@id='personalLoanamortizationEligibility']//thead["+j+"]//tr//th["+k+"]"));
										String  valhead = val.getText();            
										System.out.print(valhead);
										
										XSSFCell excelCell = excelRow.createCell(k+offSetCl);                  
										excelCell.setCellType(CellType.STRING);                 
										excelCell.setCellValue(valhead);  
					      
											}
											else {
												int m=j-1;	
												
												WebElement valbody= driver.findElement(By.xpath("//table[@id='personalLoanamortizationEligibility']//tbody["+m+"]//tr//td["+k+"]"));
												String  valbodyText = valbody.getText();            
												System.out.print(valbodyText);
												XSSFCell excelCell = excelRow.createCell(k+offSetCl);                  
												excelCell.setCellType(CellType.STRING);                 
												excelCell.setCellValue(valbodyText);  
												
											}
								}               
						     
							} 					
						
						wkb.write(fos);
								
			}			
		
	}
	
}
	
	

