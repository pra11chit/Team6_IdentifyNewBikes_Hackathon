package utils;

import java.io.File;


import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import DriverSetup.DriverSetup;


/*
 * Capturing the Screenshot of the Webpage
 */
public class Screenshot {
	public static ExtentReports report;
	public static ExtentTest logger;
	
	public void takeScreenshot(WebDriver driver, String name) throws IOException {

		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
		//Save the screenshot as Screenshot(i).jpg
		FileUtils.copyFile(src, new File(
				System.getProperty("user.dir") + "\\Screenshot\\" + "Screenshot_" + name + ".jpg"));		
	}
}
