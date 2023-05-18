package testCases;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.Command;
import org.openqa.selenium.devtools.DevTools;

import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;


import org.testng.annotations.Test;

import BOBfunctionality.BaseClass;

public class SessionsCookies {
	@Test
	public void devTools() {
	System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+".//Driver//chromedriver.exe");
	ChromeDriver driver = new ChromeDriver();
	//driver.get("https://www.bajajauto.com/");
	DevTools devTools = ((ChromeDriver)driver).getDevTools();	
    //devTools.createSession();
	long startTime= System.currentTimeMillis();

	driver.get("https://chetakdev.bajajauto.com/");
	long endTime= System.currentTimeMillis();
	
	System.out.println(endTime-startTime);
	/*
	 * LogEntries entry=driver.manage().logs().get(LogType.BROWSER); List<LogEntry>
	 * logs= entry.getAll();
	 * 
	 * for (LogEntry e:logs) { System.out.println(e.getMessage()); }
	 */
	
    
    
    //devTools.send(Network.enable(Optional.empty(),Optional.empty(), Optional.empty()));
	/*
	 * Predicate<URI> uriPredicate =
	 * uri->uri.getHost().contains("https://www.bajajauto.com/");
	 * ((HasAuthentication)driver).register(uriPredicate,UsernameAndPassword.of(
	 * "bob", "bob"));
	 */
    
    
 // iPhone 11 Pro dimensions
	/*
	 * devTools.send(Emulation.setDeviceMetricsOverride(375, 812, 50, true,
	 * Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
	 * Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
	 * Optional.empty()));
	 */
    
	/*
	 * devTools.send(Performance.enable(Optional.empty()));
	 * devTools.addListener(Performance.metrics(), metric -> {
	 * System.out.println("Metrics are: "+ metric.getTitle());
	 * 
	 * }); List<Metric> metrics =devTools.send(Performance.getMetrics());
	 * //System.out.println("Performace Response: "+((Command<Void>)
	 * metrics).getSendsResponse()); for(Metric m : metrics) {
	 * System.out.println(m.getName() + " = " + m.getValue()); }
	 */
    
    
 driver.quit();

}
}