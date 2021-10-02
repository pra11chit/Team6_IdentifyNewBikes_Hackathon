package TestSuite;

import java.io.IOException;

import org.testng.annotations.Test;

import DriverSetup.DriverSetup;
import Pages.ChennaiUsedCars;
import Pages.HomePage;
import Pages.HondaDetails;
import Pages.LoginPage;


public class SmokeTest 
{
	DriverSetup stp = new DriverSetup();
	
	@Test(priority=0)
	public void setup() throws InterruptedException {
		stp.driverSetup();
	}
	
	@Test(priority=1)
	public void testing1() throws IOException, InterruptedException
	{
		stp.openUrl();
	}
	
	@Test(priority=2)
	public void newBikes() {
		HondaDetails hd= new HondaDetails();   
		hd.clickUpcomingBikes();
	}
	
	@Test(priority=3)
	public void usedCars() throws IOException, InterruptedException {
		ChennaiUsedCars cu = new ChennaiUsedCars();
		cu.openUrl();
		cu.clickUsedCars();
	}
	
	@Test(priority=4)
	public void login() throws IOException, InterruptedException {
		LoginPage l= new LoginPage();
		l.openUrl();
		l.clickLogin();
	}
	
	@Test(priority=5)
	public void lastStep() throws InterruptedException {
		stp.closeBrowser();
	} 

}
