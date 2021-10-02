package TestSuite;

import java.io.IOException;

import org.testng.annotations.Test;

import DriverSetup.DriverSetup;
import Pages.HomePage;


public class SmokeTest 
{
	DriverSetup stp = new DriverSetup();
	
	@Test(priority=0)
	public void setup() throws InterruptedException {
		stp.driverSetup();
	}
	
	@Test(priority=1)
	public void testing() throws IOException, InterruptedException
	{
		stp.openUrl();
//		hd.clickUpcomingBikes();
//		hd.selectManufacturer();
	}
	
	@Test(priority=2)
	public void lastStep() throws InterruptedException {
		stp.closeBrowser();
	} 

}
