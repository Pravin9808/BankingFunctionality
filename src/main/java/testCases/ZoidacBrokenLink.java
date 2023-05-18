package testCases;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.XLUtility;

public class ZoidacBrokenLink {
	
	private static WebDriver driver = null;

// public void urllinkstest()
public static void main(String[] args) throws IOException {

	{
		String homePage = "https://www.zodiaconline.com/";
		String url = "";
		HttpURLConnection huc = null;
		int respCode = 200;

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		// driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(homePage);

		try {
			// links present-------//a href <url>
			// images present------//img href <url>

			// 1. get the list of all the links and images:
			List<WebElement> linkslist = driver.findElements(By.tagName("a"));
			linkslist.addAll(driver.findElements(By.tagName("img")));

			System.out.println("size of full links and images---->" + linkslist.size());
			List<WebElement> activelinks = new ArrayList<WebElement>();

			// 2. iterate linkslist: exclude all the links/images which doesnt have any href attribute
		
			for (int i = 0; i < linkslist.size(); i++) {
				if (linkslist.get(i).getAttribute("href") != null) {
					activelinks.add(linkslist.get(i));
				}
			}
			// get the size of active links list:
			System.out.println("size of active links and images----->" + activelinks.size());

			String path = ".\\DataFiles\\zodiacurls.xls";
			XLUtility xlutils = new XLUtility(path);

			// write headers into excel sheet

			xlutils.setCellData("zodiac urls", "Sheet1", 0, 0, "URL is either not configured for anchor tag or it is empty");
			xlutils.setCellData("zodiac urls", "Sheet1", 0, 1, "URL belongs to another domain, skipping it");
			xlutils.setCellData("zodiac urls", "Sheet1", 0, 2, "Broken url links");
			xlutils.setCellData("zodiac urls", "Sheet1", 0, 3, "Valid url links");

			// to get actual links url list
			List<WebElement> links = driver.findElements(By.tagName("a"));

			Iterator<WebElement> iterator = links.iterator();

			int row1 = 1;
			int row2 = 1;
			int row3 = 1;
			int row4 = 1;
			
			while (iterator.hasNext()) {

				url = iterator.next().getAttribute("href");
				System.out.println(url);

				if (url == null || url.isEmpty()) {

					
//					for (int r = 1; r <= row; r++) {

						// xlutils.row.createCell(0);
						xlutils.setCellData("zodiac urls", "Sheet1", row1, 0, "null");

//					}
					System.out.println("URL is either not configured for anchor tag or it is empty");

					row1++;
					
					continue;

				}

				if (!url.startsWith(homePage)) {
					//int row = 1;
//					for (int r = 1; r <= row; r++) {

						xlutils.setCellData("zodiac urls", "Sheet1", row2, 1, url);
//					}
					System.out.println("URL belongs to another domain, skipping it.");
					row2++;
					continue;
				}

				huc = (HttpURLConnection) (new URL(url).openConnection());
				huc.setRequestMethod("HEAD");
				huc.connect();
				respCode = huc.getResponseCode();

				if (respCode >= 400) {
					//int row = 1;
//					for (int r = 1; r < row; r++) {
						xlutils.setCellData("zodiac urls", "Sheet1", row3, 2, url);
//					}

					System.out.println(url = " is a broken link");
					row3++;
				} else {
					//int row = 1;
//					for (int r = 1; r < row; r++) {
						xlutils.setCellData("zodiac urls", "Sheet1", row4, 3, url);

//					}

					System.out.println(url = " is a valid link");
					row4++;
				}

				//row++;
			}
			System.out.println("Links Catched successfully");

			
		} catch (MalformedURLException e) {

			// e.printStackTrace();
		} catch (IOException e) {

			// e.printStackTrace();
		}
	}
}

}
