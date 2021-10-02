package Pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;

import DriverSetup.DriverSetup;
import utils.ExtentReportManager;
import utils.Highlighter;
import utils.Screenshot;

public class LoginPage extends DriverSetup {
	
	Screenshot screenshot = new Screenshot();
	Highlighter highlighter = new Highlighter();
	
	public void clickLogin() // Method to click Login
	{
		ExtentReportManager.logger = ExtentReportManager.report.createTest("Clicking on Login/Signup");
		try {
			
			ExtentReportManager.logger.log(Status.INFO, "Clicking on Login/Signup Button");
			highlighter.highlightElement(driver, driver.findElement(By.xpath("//*[@id=\"forum_login_div_lg\"]")));
			try {
				screenshot.takeScreenshot(driver,"click_LoginSignup");
				Thread.sleep(2000);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			driver.findElement(By.id(prop.getProperty("login"))).click();
			highlighter.removeHighlight(driver, driver.findElement(By.xpath("//*[@id=\"forum_login_div_lg\"]")));
			Thread.sleep(5000);
			String login1 = "Login/Register to";
			String ver = driver.findElement(By.xpath(
					"//span[@class='hd fnt-20 fnt-black fnt-m rel i-b ml-10 lh-24 txt-l login-title headingText default']"))
					.getText();
			ExtentReportManager.reportInfo("Clicking on Login/SignUp");
			if (ver.contains(login1))
				ExtentReportManager.reportPass("Clicked on Login Successfully");
		} catch (Exception e) {
			ExtentReportManager.reportFail(e.getMessage());
		}
	}

	public void clickGoogleSignIn() throws InterruptedException // Method to click Login
	{
		ExtentReportManager.logger = ExtentReportManager.report.createTest("Error Checking after signup");

		ExtentReportManager.reportInfo("Checking eror in google Signup");
		
		WebElement element =  driver.findElement(By.xpath("//*[@id='googleSignIn']"));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
	    jsExecutor.executeScript("arguments[0].setAttribute('style', 'border:3px solid red; background:yellow !important; color:black')", element);

		
		try {
			screenshot.takeScreenshot(driver,"click_GoogleSignIn");
			Thread.sleep(2000);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		driver.findElement(By.xpath(prop.getProperty("googleSignIn"))).click();
		for (String window : driver.getWindowHandles()) {
			driver.switchTo().window(window);
		}
		Thread.sleep(2000);
		driver.findElement(By.xpath(prop.getProperty("email"))).sendKeys("hackathon_team6_zig@gmail.com");
		WebElement element1 = driver.findElement(By.xpath(prop.getProperty("submit")));
	    jsExecutor.executeScript("arguments[0].setAttribute('style', 'border:3px solid red; background:yellow !important; color:black')", element1);
	    Thread.sleep(1000);
		
		element1.click();
		Thread.sleep(2000);
		
		ExtentReportManager.reportPass("Error is displayed in google signup");
			
	}

	public void captureErrorMessage() // Method to capture error message
	{
		System.out.println("==============================================================");
		System.out.println("################# Error Obtained during Signup: ##############");
		System.out.println("==============================================================");
		try {
			screenshot.takeScreenshot(driver,"Error_duringSignup");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ExtentReportManager.reportInfo("Checking for error message");
		String errorMessage = driver.findElement(By.xpath(prop.getProperty("error"))).getText();
		ExtentReportManager.reportPass("Error message captured");
		System.out.println("Error Message = " + errorMessage);
	}

}
