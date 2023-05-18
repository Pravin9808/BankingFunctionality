package testCases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.XLUtility;

public class PersonalLoanEMICalculatorTest2 {
	
	
	static WebDriver driver;

	@Test
	public void verifyPersonalLoanEMICalculatorFunctionality() throws IOException, InterruptedException {

		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		DesiredCapabilities cp = new DesiredCapabilities();
		cp.setCapability(ChromeOptions.CAPABILITY, options);
		options.merge(cp);
		driver = new ChromeDriver(options);

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

		String xlfileNameoutput = "bobcalculatoramortization-qc.xlsx";
		String xlsheetNameoutput = "bobcalculatoramortization-qc-outputdata";

		String outputpath = ".//DataFiles//bobcalculatoramortization-qc.xlsx" ;
		XLUtility xlutilsoutput = new XLUtility(outputpath);

		driver.get("https://www.bankofbaroda.in/calculators/personal-loan-emi-calculator");
		driver.findElement(By.xpath("//div[@class='privacy-warning acceptonclose']/div[@class='close']")).click();
		String amount = "600000";
		String rateOfInterest = "15";
		String loanTerms = "12";

		System.out.println("Input Values as Test Data");
		System.out.println("Amount is: " + amount);
		System.out.println("Rate of Interest is: " + rateOfInterest);
		System.out.println("Loan Terms(monthly) is: " + loanTerms);

		// inputs into Bank of Baroda Calculator
		// 1 Find Amount text box element and enter input
		WebElement amountTextBoxElement = driver.findElement(By.xpath("//small[contains(text(),'₹')]"));
		// create an instance of the Actions class and perform double click
		Actions actions = new Actions(driver);
		actions.doubleClick(amountTextBoxElement).perform();
		actions.sendKeys(amount).perform();
		Thread.sleep(5000);
		// 2 find rate of interest textbox element and enter inputs
		WebElement rateofInterestTextBoxElement = driver.findElement(By.xpath("//em[normalize-space()='10.90']"));

		actions.doubleClick(rateofInterestTextBoxElement).perform();
		rateofInterestTextBoxElement.sendKeys(Keys.BACK_SPACE);
		actions.sendKeys(rateOfInterest).perform();

		// 3 Find loan terms(monthly) textbox element and enter inputs
		WebElement loanTermsTextBoxElement = driver.findElement(By.xpath("//em[normalize-space()='18']"));
		actions.doubleClick(loanTermsTextBoxElement).perform();

		actions.sendKeys(loanTerms).perform();

		// ouputs
		System.out.println("EMI Details Output from Bank of Baroda as per test data ");
		// 1 monthly payment
		WebElement monthlyPaymentElement = driver.findElement(By.xpath("//h6[@id='monthly-hl-emi']"));
		String monthlyPaymentText = monthlyPaymentElement.getText();

		// remove the rupees symbol and comma from the text
		monthlyPaymentText = monthlyPaymentText.replace("₹", "");
		monthlyPaymentText = monthlyPaymentText.replace(",", "");

		// convert the text to a number format
		int monthlyPayment = Integer.parseInt(monthlyPaymentText);
		// print the monthly payment
		System.out.println("Monthly Payment is: " + monthlyPayment);

		// 2 Principal Amount
		WebElement pricipalAmountElement = driver.findElement(By.xpath("//strong[@id='spn_principal_amt']"));
		String pricipalAmountText = pricipalAmountElement.getText();
		// remove the rupees symbol and comma from the text
		pricipalAmountText = pricipalAmountText.replace("₹", "");
		pricipalAmountText = pricipalAmountText.replace(",", "");

		// convert the text to a number format
		int pricipalAmount = Integer.parseInt(pricipalAmountText);
		// print the monthly payment
		System.out.println("Principal Amount is: " + pricipalAmount);

		// 3 Interest Amount
		WebElement interestAmountElement = driver.findElement(By.xpath("//strong[@id='spn_interest_amt']"));
		String interestAmountText = interestAmountElement.getText();

		// remove the rupees symbol and comma from the text
		interestAmountText = interestAmountText.replace("₹", "");
		interestAmountText = interestAmountText.replace(",", "");

		// convert the text to a number format
		int interestAmount = Integer.parseInt(interestAmountText);
		// print the monthly payment
		System.out.println("Interest Amount is: " + interestAmount);

		// 4 Total Amount Payable
		WebElement totalAmountPayableElement = driver.findElement(By.xpath("//strong[@id='spn_total_amt']"));
		String totalAmountPayableText = totalAmountPayableElement.getText();

		// remove the rupees symbol and comma from the text
		totalAmountPayableText = totalAmountPayableText.replace("₹", "");
		totalAmountPayableText = totalAmountPayableText.replace(",", "");

		// convert the text to a number format
		int totalAmountPayable = Integer.parseInt(totalAmountPayableText);
		// print the monthly payment
		System.out.println("Total Amount Payable is: " + totalAmountPayable);

		// driver.close();

		// bob personal loan amortization

		WebElement tableBOB = driver.findElement(By.id("personalLoanamortizationEligibility"));
		//List<String> tableDataBOB = extractTableDataBOB(tableBOB);

		// System.out.println(tableDataBOB);

		driver.get("https://www.axisbank.com/retail/calculators/personal-loan-emi-calculator ");

		// inputs into Axis Bank Calculator
		// 1 Find loan amount textbox and enter inputs
		WebElement loanAmountTextBoxElement = driver.findElement(By.xpath("//input[@id='loan_amount']"));
		actions.doubleClick(loanAmountTextBoxElement).perform();
		loanAmountTextBoxElement.sendKeys(amount);

		// 2 find interest rate element and enter inputs
		WebElement interestRateTextBoxElement = driver.findElement(By.xpath("//input[@id='interest_rate']"));
		actions.doubleClick(interestRateTextBoxElement).perform();
		interestRateTextBoxElement.sendKeys(Keys.BACK_SPACE);
		actions.doubleClick(interestRateTextBoxElement).perform();
		interestRateTextBoxElement.sendKeys(Keys.BACK_SPACE);
		interestRateTextBoxElement.sendKeys(rateOfInterest);

		// 3 find tenure(months) element and enter inputs

		WebElement tenureTextBoxElement = driver.findElement(By.xpath("//input[@id='tenure']"));
		tenureTextBoxElement.sendKeys(Keys.CONTROL, "a");
		// actions.doubleClick(tenureTextBoxElement).perform();
		tenureTextBoxElement.sendKeys(Keys.BACK_SPACE);
		tenureTextBoxElement.sendKeys(loanTerms);

		// ouputs
		System.out.println("EMI Details Output from Axis Bank Calcultor as per test data ");

		// 1 monthly payment EMI
		WebElement monthlyInstallmentEMIElement = driver.findElement(By.xpath("//span[@id='lblEMIAmt']"));
		String monthlyInstallmentEMIText = monthlyInstallmentEMIElement.getText();

		// remove the rupees symbol and comma from the text
		monthlyInstallmentEMIText = monthlyInstallmentEMIText.replace("₹", "");
		monthlyInstallmentEMIText = monthlyInstallmentEMIText.replace(",", "");

		// convert the text to a number format
		int monthlyInstallmentEMI = Integer.parseInt(monthlyInstallmentEMIText);
		// print the monthly payment
		System.out.println("Monthly Payment is: " + monthlyInstallmentEMI);

		// 2 Principal Amt
		WebElement pricipalAmtElement = driver.findElement(By.xpath("//span[@id='princAmt']"));
		String pricipalAmtText = pricipalAmtElement.getText();
		// remove the rupees symbol and comma from the text
		pricipalAmtText = pricipalAmtText.replace("₹", "");
		pricipalAmtText = pricipalAmtText.replace(",", "");

		// convert the text to a number format
		int pricipalAmt = Integer.parseInt(pricipalAmtText);
		// print the monthly payment
		System.out.println("Principal Amount is: " + pricipalAmt);

		// 3 Interest Amount
		WebElement interestAmtElement = driver.findElement(By.xpath("//span[@id='intrAmt']"));
		String interestAmtText = interestAmtElement.getText();

		// remove the rupees symbol and comma from the text
		interestAmtText = interestAmtText.replace("₹", "");
		interestAmtText = interestAmtText.replace(",", "");

		// convert the text to a number format
		int interestAmt = Integer.parseInt(interestAmtText);
		// print the monthly payment
		System.out.println("Interest Amount is: " + interestAmt);

		// 4 Total Amount Payable
		WebElement totalAmtPayableElement = driver.findElement(By.xpath("//span[@id='totalPayAmt']"));
		String totalAmtPayableText = totalAmtPayableElement.getText();

		// remove the rupees symbol and comma from the text
		totalAmtPayableText = totalAmtPayableText.replace("₹", "");
		totalAmtPayableText = totalAmtPayableText.replace(",", "");

		// convert the text to a number format
		int totalAmtPayable = Integer.parseInt(totalAmtPayableText);
		// print the Total Payable Amount
		System.out.println("Total Amount Payable is: " + totalAmtPayable);

//		// axis bank personal loan amortization
//
//		WebElement tableAxis = driver.findElement(By.id("trDynamic"));
//		
//		List<String> tableDataAxis = extractTableDataAxis(tableAxis);
//
//		// Loop through the table data and print each element to the console
//		for (String cellText : tableDataAxis) {
//		    System.out.println(cellText);
//		    
//		    xlutilsoutput.setCellData(xlfileNameoutput, xlsheetNameoutput, row, cell, cellText)  
//		    
//		}
//		System.out.print(tableDataAxis);

		// axis bank personal loan amortization
		Thread.sleep(5000);

		driver.get("https://www.bankofbaroda.in/calculators/personal-loan-emi-calculator");
		WebElement amrtScheduleBOBtable=driver.findElement(By.xpath("//table[@id='personalLoanamortizationEligibility']//tbody"));
		int rows=amrtScheduleBOBtable.findElements(By.xpath("tr")).size();
		
		// Extract the table data as a list of lists
		//List<List<String>> tableDataAxis = extractTableDataAxis(tableAxis);

		// Write the table data to the sheet

		//int rowIndex = 17;
		for (int i=17;i<=rows-17;i++) {
			
			xlutilsoutput.setCellData(xlfileNameoutput,xlsheetNameoutput, 17, 0, "month");
			xlutilsoutput.setCellData(xlfileNameoutput,xlsheetNameoutput, 17, 1,"opening_balance");
			xlutilsoutput.setCellData(xlfileNameoutput,xlsheetNameoutput, 17, 2, "prnc_repaid_dur_month");
			xlutilsoutput.setCellData(xlfileNameoutput,xlsheetNameoutput, 17, 3, "closing_balance");
			
			String month=amrtScheduleBOBtable.findElement(By.xpath("tr["+i+"]/td[1]")).getText();
			String opening_balance=amrtScheduleBOBtable.findElement(By.xpath("tr["+i+"]/td[2]")).getText();
			String prnc_repaid_dur_month=amrtScheduleBOBtable.findElement(By.xpath("tr["+i+"]/td[3]")).getText();
			String closing_balance =amrtScheduleBOBtable.findElement(By.xpath("tr["+i+"]/td[4]")).getText();
			
			xlutilsoutput.setCellData(xlfileNameoutput,xlsheetNameoutput, i, 0, month);
			xlutilsoutput.setCellData(xlfileNameoutput,xlsheetNameoutput, i, 1, opening_balance);
			xlutilsoutput.setCellData(xlfileNameoutput,xlsheetNameoutput, i, 2, prnc_repaid_dur_month);
			xlutilsoutput.setCellData(xlfileNameoutput,xlsheetNameoutput, i, 3, closing_balance);
		}
	}
/*
	private List<String> extractTableDataBOB(WebElement tableBOB) {

		List<String> tableDataBOB = new ArrayList<String>();

		// Find the header row and get the column headers
		List<WebElement> headerrows = tableBOB.findElements(By.tagName("thead"));

		for (WebElement headerrow : headerrows) {

			List<WebElement> headercells = headerrow.findElements(By.tagName("th"));
			for (WebElement headercell : headercells) {
				tableDataBOB.add(headercell.getText());
			}
		}
		// Find the data
		List<WebElement> rows = tableBOB.findElements(By.tagName("tbody"));
		for (WebElement row : rows) {
			List<WebElement> cells = row.findElements(By.tagName("td"));
			for (WebElement cell : cells) {
				tableDataBOB.add(cell.getText());
			}
		}
		return tableDataBOB;
	}



	private List<List<String>> extractTableDataAxis(WebElement tableAxis) {
		List<List<String>> tableDataAxis = new ArrayList<List<String>>();
		List<WebElement> rows = tableAxis.findElements(By.className("tableRow"));
		for (WebElement row : rows) {
			List<String> rowData = new ArrayList<String>();
			List<WebElement> cells = row.findElements(By.className("tableCell"));
			for (WebElement cell : cells) {
				rowData.add(cell.getText());
			}
			tableDataAxis.add(rowData);
		}
		return tableDataAxis;
	}

	private static List<String> compareTables(List<String> tableBOB, List<String> tableAxis) {
		List<String> differences = new ArrayList<String>();
		for (int i = 0; i < tableBOB.size(); i++) {
			String cell1 = tableBOB.get(i);
			String cell2 = tableAxis.get(i);
			if (!cell1.equals(cell2)) {
				differences.add(String.format("Cell %d is different: '%s' vs '%s'", i, cell1, cell2));

			}
		}
		return differences;
	}*/

}
