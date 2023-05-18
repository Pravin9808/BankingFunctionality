package testCases;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import BOBfunctionality.BaseClass;

public class BrokenLinks extends BaseClass{
	@Test
	public void brokenLinks() throws InterruptedException, IOException {
	List<WebElement> links = driver.findElements(By.tagName("a"));
	/*
	String homePage="https://www.bajajauto.com/";
	
	Iterator<WebElement> it = links.iterator();

	while(it.hasNext()){

	String url = it.next().getAttribute("href");

	System.out.println(url);

	if(url == null || url.isEmpty()){
	System.out.println("URL is either not configured for anchor tag or it is empty");
	continue;
	}

	if(!url.startsWith(homePage)){
	System.out.println("URL belongs to another domain, skipping it.");
	continue;
	}*/
	for(WebElement link: links) {
		String url= link.getAttribute("href");
		HttpURLConnection conn=(HttpURLConnection) new URL(url).openConnection();
		Thread.sleep(5000);
		conn.setRequestMethod("HEAD");
		Thread.sleep(5000);
		conn.connect();
		Thread.sleep(5000);
		int rescode= conn.getResponseCode();
		System.out.println(rescode);
		System.out.println(url);
		/*
		if (rescode==404) {
			System.out.println(link.getText());
			System.out.println(url);
		}*/
	}
}
}