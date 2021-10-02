package Pages;

import java.io.IOException;
import java.util.Formatter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

import DriverSetup.DriverSetup;
import utils.ExtentReportManager;
import utils.Highlighter;
import utils.Screenshot;
import utils.excel;

public class HondaDetails extends DriverSetup {
	
	Highlighter highlighter = new Highlighter();
	Screenshot screenshot = new Screenshot();

	int count = 0, count1 = 0;

	// Method to click Upcoming_bikes
	public void clickUpcomingBikes() 
	{
		ExtentReportManager.logger = ExtentReportManager.report.createTest("Upcoming Bikes");
		try {
			ExtentReportManager.logger.log(Status.INFO, "Clicking on upcoming Bikes");
			
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(prop.getProperty("nbikes"))));
			WebElement w1 = driver.findElement(By.linkText(prop.getProperty("nbikes")));
			
			highlighter.highlightElement(driver, w1);
			try {
				Thread.sleep(3000);
				screenshot.takeScreenshot(driver,"click_newbikes");
				ExtentReportManager.reportInfo("Screenshot Taken : New Bikes");

				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			highlighter.removeHighlight(driver, w1);
			
			Actions act = new Actions(driver);
			act.moveToElement(w1).perform();
			
			highlighter.highlightElement(driver, driver.findElement(By.xpath("//*[@id=\"headerNewNavWrap\"]/nav/div[2]/ul/li[3]/ul/li[4]")));
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(prop.getProperty("ubikes"))));
			
			try {
				screenshot.takeScreenshot(driver,"upcoming_Bikes");
				ExtentReportManager.reportInfo("Screenshot Taken : Upcoming Bikes");
				Thread.sleep(3000);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			driver.findElement((By.linkText(prop.getProperty("ubikes")))).click();

			String str = driver.findElement(By.xpath("/html/body/div[10]/ol/li[2]/span")).getText();
			if (str.contains("Upcoming Bikes"))
				ExtentReportManager.reportPass("Upcoming bikes has been opened"); //Selecting Upcoming Bikes Successfully
		} catch (Exception e) {
			ExtentReportManager.reportFail(e.getMessage());
		}
		highlighter.removeHighlight(driver, driver.findElement(By.xpath("//*[@id=\"headerNewNavWrap\"]/nav/div[2]/ul/li[3]/ul/li[4]")));
	}

	// Method to select the Manufacturer
	public void selectManufacturer() 
	{
		ExtentReportManager.logger = ExtentReportManager.report.createTest("Honda Manufacturer");
		try {
			ExtentReportManager.logger.log(Status.INFO, "Selecting the Honda Manufacturer");
			
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prop.getProperty("smanuf"))));
			WebElement drop = driver.findElement(By.id(prop.getProperty("smanuf")));
			
			highlighter.highlightElement(driver, drop);
			
			Select select = new Select(drop);
			
			try {
				Thread.sleep(3000);
				select.selectByValue("53");
				Thread.sleep(2000);
				screenshot.takeScreenshot(driver,"select_Honda");	
				ExtentReportManager.reportInfo("Screenshot Taken : Select Honda");

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			highlighter.removeHighlight(driver, drop); //Selecting HONDA Brand successfully

			ExtentReportManager.reportPass("Manufacturer is HONDA");
		} catch(Exception e) {
			
		}
	}

	// Method to close the login-popup
	public void closeLoginPopUp() 
	{
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(prop.getProperty("lclose"))));
		driver.findElement(By.id(prop.getProperty("lclose"))).click();
	}

	// Method to click viewmore
	public void viewMore() 
	{
		ExtentReportManager.logger = ExtentReportManager.report.createTest("Accessing View More");
		try {
			ExtentReportManager.logger.log(Status.INFO, "Accessing the view More");

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,400)", "");
			
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("viewButton"))));
			
			WebElement element = driver.findElement(By.xpath(prop.getProperty("viewButton")));			
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);
			Thread.sleep(500);
			
			try {
				Thread.sleep(2000);
				screenshot.takeScreenshot(driver,"upcoming_HondaBikes");	
				ExtentReportManager.reportInfo("Screenshot Taken : Upcoming Honda Bikes");

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			//highlighter.highlightElement(driver, element);
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		    jsExecutor.executeScript("arguments[0].setAttribute('style', 'border:3px solid red; background:yellow !important; color:black')", element);
			
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			
			Thread.sleep(1000);
			try {
				screenshot.takeScreenshot(driver,"click_viewMore");
				ExtentReportManager.reportInfo("Screenshot Taken : Click View More");

				Thread.sleep(3000);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
//			highlighter.removeHighlight(driver, element);
			  
			jsExecutor.executeScript("arguments[0].setAttribute('style', 'border: solid 2px white')", element);
			
			executor.executeScript("arguments[0].click();", element);
			ExtentReportManager.reportPass("View More is clicked");
			
			
		} catch (Exception e) {
			ExtentReportManager.reportFail(e.getMessage());
		}
	}

	public void printDetails() throws IOException // Method to print details on the console
	{
		ExtentReportManager.logger = ExtentReportManager.report.createTest("Obtaining bike prices");
		
		ExtentReportManager.logger.log(Status.INFO, "Obtaining the Bike Prices");
		
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("BikeNames"))));
		List<WebElement> bikeNames = driver.findElements(By.xpath(prop.getProperty("BikeNames")));
		List<WebElement> bikePrices = driver.findElements(By.xpath(prop.getProperty("BikePrices")));
		List<WebElement> bikeLaunch = driver.findElements(By.xpath(prop.getProperty("BikeLaunch")));
		count = bikeNames.size();
		String priceTxt;
		System.out.println("==============================================================");
		System.out.println("######### Upcoming Honda Bike Details under 4 Lakhs: #########");
		System.out.println("==============================================================");
		String str;
		excel.writeToExcel("Bike Name", 0, 0);
		excel.writeToExcel("Bike Price", 0, 1);
		excel.writeToExcel("Lauch Date", 0, 2);
		
		Formatter fmt = new Formatter();
		fmt.format("%20s %20s %20s\n", "Bike Name", "Price", "Launch Date");  
		fmt.format("--------------------------------------------------------------\n");

		
		try {
			for (int i = 0; i < count; i++) {
				priceTxt = bikePrices.get(i).getText();
				float price = Float.parseFloat(priceTxt.replaceAll("Rs. ", "").replaceAll(" Lakh", ""));
				if (price < 4) {
					str = bikeNames.get(i).getText() + "|" + "\t" + bikePrices.get(i).getText() + "|" + "\t"
							+ bikeLaunch.get(i).getText().substring(14);
					fmt.format("%20s %20s %20s\n", bikeNames.get(i).getText(), bikePrices.get(i).getText(), bikeLaunch.get(i).getText().substring(14));  
					
					excel.writeToExcel(bikeNames.get(i).getText(), rowNo, 0);
					excel.writeToExcel(bikePrices.get(i).getText(), rowNo, 1);
					excel.writeToExcel(bikeLaunch.get(i).getText().substring(14), rowNo, 2);

					rowNo++;
				}
			}
			ExtentReportManager.reportPass("Bike Prices are Obtained");
		} catch (Exception e) {
			ExtentReportManager.reportFail(e.getMessage());
		}
		System.out.println(fmt);  
	}
}
