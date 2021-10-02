package Pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

import DriverSetup.DriverSetup;
import utils.ExtentReportManager;
import utils.Highlighter;
import utils.Screenshot;
import utils.excel;

public class ChennaiUsedCars extends DriverSetup
{
	
	Highlighter highlighter = new Highlighter();
	Screenshot screenshot = new Screenshot();
	
	public void clickUsedCars()  // Method to click used_cars
	{
		ExtentReportManager.logger = ExtentReportManager.report.createTest("Used Cars and Popular Model");
		try{
			ExtentReportManager.logger.log(Status.INFO, "Displaying Used Cars and Popular Model");
			
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(prop.getProperty("ucars"))));
			WebElement w1=driver.findElement(By.linkText(prop.getProperty("ucars")));
			
			highlighter.highlightElement(driver, w1);
			try {
				Thread.sleep(2000);
				screenshot.takeScreenshot(driver,"clickOnUsedCars");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			highlighter.removeHighlight(driver, w1);
			
		Actions act=new Actions(driver);
		act.moveToElement(w1).perform();
		
		highlighter.highlightElement(driver, driver.findElement(By.xpath("//*[@id=\"headerNewNavWrap\"]/nav/div[2]/ul/li[5]/ul/li/div[2]/ul/li[5]")));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(prop.getProperty("chennai"))));
		try {
			Thread.sleep(2000);
			screenshot.takeScreenshot(driver,"selectChennai");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		driver.findElement(By.linkText(prop.getProperty("chennai"))).click(); //Selecting Chennai City Successfully
		highlighter.removeHighlight(driver, driver.findElement(By.xpath("//*[@id=\"headerNewNavWrap\"]/nav/div[2]/ul/li[5]/ul/li/div[2]/ul/li[5]")));
		
		
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("window.scrollBy(0,400)", "");
		
//		try {
//
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		js.executeScript("window.scrollBy(0,500)", "");
//		try {
//
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		String usedCars = driver.findElement(By.xpath("//h1[@id='usedcarttlID']")).getText();
		if (usedCars.contains("Used Cars in Chennai")) 
			ExtentReportManager.reportPass("Used Cars in chennai are displayed");
		} catch (Exception e) {
			ExtentReportManager.reportFail(e.getMessage());
		}
		
	}
	public void clickPopularModels() // Method to click popular_models
	{
		ExtentReportManager.logger = ExtentReportManager.report.createTest("Obtaining Popular Models");
		try {
			ExtentReportManager.logger.log(Status.INFO, "printing the Popular Models");
			
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("popularmodels"))));
		WebElement w1=driver.findElement(By.xpath(prop.getProperty("popularmodels")));
		System.out.println("========================================================");
		System.out.println("#############  Popular Used Cars in Chennai:  ##########");
		System.out.println("========================================================");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName(prop.getProperty("list"))));
		List<WebElement> ls= w1.findElements(By.tagName(prop.getProperty("list")));
		excel.writeToExcel("Popular Used Cars", 0, 4);
		for(int i=0;i<10;i++)
		{
			System.out.println(ls.get(i).getText());
			excel.writeToExcel(ls.get(i).getText(), i + 1, 4);
		}
		ExtentReportManager.reportPass("Popular models are printed");
		} catch (Exception e) {
			ExtentReportManager.reportFail(e.getMessage());
		}
	}


}
