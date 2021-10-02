package DriverSetup;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import utils.ExtentReportManager;
import utils.Screenshot;

public class DriverSetup {
	public static WebDriver driver;
	public static Properties prop;

	public static int rowNo = 1;
	
	Screenshot screenshot = new Screenshot();

	//Method for Driver Setup
	public void driverSetup() 
	{
		ExtentReportManager.logger = ExtentReportManager.report.createTest("Loading Property File");
		ExtentReportManager.logger.log(Status.INFO, "Loading Property File");
		
		if (prop == null) {
			prop = new Properties();

			//Load Property File
			try {
				prop.load(new FileInputStream(
						System.getProperty("user.dir") + "\\src\\main\\resources\\Config.properties")); 																											 
																											
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		ExtentReportManager.logger.log(Status.PASS, "Config Properties loaded Successfully");		
		ExtentReportManager.logger = ExtentReportManager.report.createTest("Driver Setup");
		ExtentReportManager.logger.log(Status.INFO, "Setting up Driver");

		//Get Preferred Browser from Property File
		if (prop.getProperty("browserName").matches("chrome")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
			ChromeOptions ops = new ChromeOptions();
			ops.addArguments("--disable-infobars");
			ops.addArguments("--disable-gpu");
			ops.addArguments("--disable-notifications");
			// Initializing the new chrome driver
			driver = new ChromeDriver(ops); 
		}
		if (prop.getProperty("browserName").matches("firefox")) {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\drivers\\geckodriver.exe");
			// Initializing the new firefox driver
			driver = new FirefoxDriver(); 
		}
		if (prop.getProperty("browserName").matches("msedge")) {
			System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "\\drivers\\msedgedriver.exe");
			//Initializing the edge driver
			driver = new EdgeDriver();
		}
		// To maximize the window
		driver.manage().window().maximize(); 
		// Waiting time to page the load completely
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS); 
		// Adding driver waits to timeouts
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); 

		ExtentReportManager.logger.log(Status.PASS, "Driver Loaded Successfully");

	}

	// Method to open URL for smoke test
	public void openUrl() throws IOException, InterruptedException 
	{
		ExtentReportManager.logger = ExtentReportManager.report.createTest("Opening Url");
		
		try {
			ExtentReportManager.logger.log(Status.INFO, "Opening the Url");
			String s = prop.getProperty("url");
			driver.get(s);			
			
		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace();
		}
		try {
			Thread.sleep(3000);
			screenshot.takeScreenshot(driver,"url_opened");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		ExtentReportManager.reportPass("URL opened, URL is :" + prop.getProperty("url"));
		ExtentReportManager.reportInfo("Screenshot Taken");
	}

	// method to close the browser
	public void closeBrowser() 
	{
		// To close the browser
		driver.quit(); 
		// To save the reports
		ExtentReportManager.report.flush(); 
		try {
			Runtime.getRuntime().exec("taskkill /f /im chromedriver.exe");
			Runtime.getRuntime().exec("taskkill /f /im geckodriver.exe");
		} catch (Exception e) {
		}
	}
}
