package testCases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v111.network.Network;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.testng.Assert;
import org.testng.annotations.Test;



import BOBfunctionality.BaseClass;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ValidatingCookies {
	@Test
	public void getAllCookies() throws IOException, InterruptedException {
		//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+".//Driver//chromedriver.exe");
		WebDriverManager.chromedriver().setup();;
			
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(opt);	
		HashMap<String, String> mobileEmulation = new HashMap<String, String>();
		mobileEmulation.put("deviceName", "iPhone XR");
		
	    opt.setExperimentalOption("mobileEmulation", mobileEmulation);

		//driver.get("https://cm.bankofbaroda.in/personal-banking/accounts/saving-accounts/baroda-salary-classic");
			
		DevTools devTools = ((HasDevTools) driver).getDevTools();
		devTools.createSession();
		//send command to cdp-> cdp methods will invoke and get access to cdp
		devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
		devTools.addListener(Network.requestWillBeSent(),requestSent->{
		System.out.println(requestSent.getRequest().getUrl());
		});
		
		devTools.addListener(Network.responseReceived(),response->{
			System.out.println(response.getResponse().getStatus());
			});
		driver.get("https://mahindra.com/our-story");
		
		LogEntries entry = driver.manage().logs().get(LogType.BROWSER);//get logentry object
		List<LogEntry> logs =entry.getAll();//getall method returning all logs page
		for(LogEntry e:logs) {
			System.out.println(e.getMessage());
	
		}
		/*
        //Getting all cookies
        List<Cookie> cookies = devTools.send(Network.getAllCookies());
        cookies.forEach(cookie -> System.out.println(cookie.getName()));
        List<String> cookieName = cookies.stream().map(cookie -> cookie.getName()).sorted().collect(Collectors.toList());
        Set<org.openqa.selenium.Cookie> seleniumCookie = driver.manage().getCookies();
        List<String> selCookieName = seleniumCookie.stream().map(selCookie -> selCookie.getName()).sorted().collect(Collectors.toList());
        Assert.assertEquals(cookieName, selCookieName);

        //Clearing browser cookies
        devTools.send(Network.clearBrowserCookies());
        List<org.openqa.selenium.devtools.v106.network.model.Cookie> cookiesAfterClearing = devTools.send(Network.getAllCookies());
        Assert.assertTrue(cookiesAfterClearing.isEmpty());
       */
	}

}
