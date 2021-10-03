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
	HomePage hp = new HomePage();
	HondaDetails hd= new HondaDetails();
	ChennaiUsedCars cu = new ChennaiUsedCars();
	LoginPage l= new LoginPage();


	@Test(priority=0)
	public void test1() throws InterruptedException, IOException {
		System.out.println("* Regression Test Started");
		bs.driverSetup();
	}
	
	@Test(priority=1)
	public void test2() throws InterruptedException, IOException {	
		bs.openUrl();
	}
	
	@Test(priority=2)
	public void test3() {
		hp.checkHomePageTitle();
	}
	
	@Test(priority=3)
	public void test4() throws InterruptedException, IOException {
		hd.clickUpcomingBikes();
	}
	
	@Test(priority=4)
	public void test5() throws InterruptedException, IOException {
		hd.selectManufacturer();
	}
	
	@Test(priority=5)
	public void test6() throws InterruptedException, IOException {
		hd.viewMore();
	}
	
	@Test(priority=6)
	public void test7() throws InterruptedException, IOException
	{
		hd.printDetails();
	}
	
	@Test(priority=7)
	public void test8() throws InterruptedException, IOException
	{
		cu.openUrl();
	}
	
	@Test(priority=8)
	public void test9() throws InterruptedException, IOException
	{
		cu.clickUsedCars();
	}
	
	@Test(priority=9)
	public void test10() throws IOException, InterruptedException
	{
		cu.clickPopularModels();
	}
	
	@Test(priority=10)
	public void test11() throws IOException, InterruptedException
	{
		l.openUrl();
	}
	
	@Test(priority=11)
	public void test12() throws IOException, InterruptedException
	{
		l.clickLogin();
	}
	
	@Test(priority=12)
	public void test13() throws IOException, InterruptedException
	{
		l.clickGoogleSignIn();
	}
	
	@Test(priority=13)
	public void  testing14() throws InterruptedException, IOException
	{
		l.captureErrorMessage();	
	}
	
	@Test(priority=14)
	public void test15() {
		bs.closeBrowser();
	}
}
