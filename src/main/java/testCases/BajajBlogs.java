package testCases;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import BOBfunctionality.BaseClass;

public class BajajBlogs extends BaseClass {
	@Test
     public void bajajBlogs() throws IOException {
		HashMap<String, String> ar = new HashMap<String, String>();
		Select drp = new Select(driver.findElement(By.id("select-filter0")));
		// Get a list of all the options in the dropdown
		List<WebElement> drpOptions = drp.getOptions();
		int numberOfDrpOptions = drpOptions.size();
		   System.out.println(numberOfDrpOptions);
		// Output the number of options in the dropdown
		//System.out.println("Total Categories in Dropdowns are :  " + numberOfDrpOptions );
		for(int i=0;i<numberOfDrpOptions;i++) {
			WebElement drpOption = drpOptions.get(i);
			String drpOptionName = drpOption.getText();
			drp.selectByVisibleText(drpOptionName);
			List<WebElement> blogs = driver.findElements(By.tagName("h2"));
			int numberOfBlogs = blogs.size();
			//System.out.println(numberOfBlogs);
			for (int j = 0; j < numberOfBlogs; j++){
				WebElement blogElement = blogs.get(j);
				String blogText = blogElement.getText();
				System.out.println(drpOptionName+" "+blogText);
				ar.put(drpOptionName, blogText);
			}
		}
		//System.out.println(ar);
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
		
	}}
//            WebElement selectedCategories = selectCategoriesDrp.getFirstSelectedOption();
		//            selectedCategories.getText();
		//            System.out.println(selectedCategories.getText());
		//            
		//          Check number of items displayed
		//            List<WebElement> blogs = driver.findElements(By.xpath("1"));
		//            blogs.size();
		//    
		//            System.out.println(blogs.size()); fbtproductitems.get(0);
		//         System.out.println(fbtproductitems.get(0));
		//
		//            for (int item = 0; item < fbtproductitems.size(); item++) {
		//
		//               Get the i-th anchor element from the list
		//                WebElement fbtproductitem = fbtproductitems.get(item);
		//
		//
		//               Perform a click operation on the anchor element
		//                fbtproductitem.click();
		//                 }
     //Loop through each option in the dropdown
		//            for (WebElement drpOption : drpOptions) {
		//             Get the text of the option
		//                String drpOptionText = drpOption.getText();
		// Select the drp option
		//                drp.selectByVisibleText(drpOptionText);
		// Get blog from the selected drp option
		//                WebElement blogElement = driver.findElement(By.tagName("h2"));
		// String blog = blogElement.getText();
		//             blog output
		//                System.out.println("selectCategoriesDrpoption: " + drpOptionText + ", Blog: " + blog);