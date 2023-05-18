package testCases;


import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import BOBfunctionality.BaseClass;

public class PlacementList extends BaseClass {
	@Test
	public void listItem() throws InterruptedException{
		driver.findElement(By.xpath("//li/a[@data-g-action='sign in cta']")).click();
		 Set<String> w = driver.getWindowHandles();
	      // window handles iterate
	      Iterator <String> t = w.iterator();
	      String parentId =  t.next();
	      String childId =  t.next();
	      // switching child window
	      driver.switchTo().window(childId);
		Thread.sleep(7000);
		driver.findElement(By.xpath("//input[@aria-label='Email or phone']")).sendKeys("pravininteractive25@gmail.com");
		driver.findElement(By.xpath("//span[normalize-space()='Next']")).click();
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Pk24interactive@");		
		
		List<WebElement> itemList=driver.findElements(By.xpath("//li[@id='md-option-31-1']"));
		for(int i=0;i<itemList.size();i++) {
		System.out.println(itemList.get(i).getText());
		
		}
	}

}
