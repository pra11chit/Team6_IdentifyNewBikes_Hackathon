package TestSuite;

import java.io.IOException;


import org.testng.annotations.Test;

import DriverSetup.DriverSetup;
import Pages.ChennaiUsedCars;
import Pages.HomePage;
import Pages.HondaDetails;
import Pages.LoginPage;

public class RegressionTest {
	DriverSetup bs = new DriverSetup();
	
	@Test(priority=0)
	public void basic() throws InterruptedException, IOException {
		
		bs.driverSetup();
		bs.openUrl();
	}
	
//	@Test(priority=1)
//	public void testing() throws IOException, InterruptedException
//	{
//		HomePage hd= new HomePage();
////		hd.openUrl();
////		hd.clickUpcomingBikes();
////		hd.selectManufacturer();
//	}
	
	@Test(priority=1)
	public void testing1() throws InterruptedException, IOException
	{
		HondaDetails hd= new HondaDetails();   
		//hd.openUrl();
		//hd.closeLoginPopUp();
		hd.clickUpcomingBikes();
		hd.selectManufacturer();
		hd.viewMore();
		hd.printDetails();
	}
	
	@Test(priority=2)
	public void testingCars() throws IOException, InterruptedException
	{
		ChennaiUsedCars cu = new ChennaiUsedCars();
		cu.openUrl();
		cu.clickUsedCars();
		cu.clickPopularModels();
	}
	
	@Test(priority=3)
	public void  testingLogin() throws InterruptedException, IOException
	{
		LoginPage l= new LoginPage();
		l.openUrl();
		l.clickLogin();
		l.clickGoogleSignIn();
		l.captureErrorMessage();	
	}
	
	@Test(priority=4)
	public void lastStep() {
		bs.closeBrowser();
	}
}
