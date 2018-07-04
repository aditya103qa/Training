package com.qait.MavenDemo.HRIS_POM;
import com.qait.MavenDemo.HRIS_POM.HrisAction;

import java.util.Set;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
public class HrisTestPom{

	HrisAction action;

	WebDriver driver;

	Properties prop = new Properties();
	FileInputStream fs;
	String uname;
	String incorrectpwd;
	String correctpwd;
	String mail;

	@BeforeTest
	public void browser() throws IOException
	{
		action = new HrisAction();
		driver = action.initiation();
		
		fs = new FileInputStream("datafile.properties");
		prop.load(fs);
		uname= prop.getProperty("username");
		System.out.println(uname);
		incorrectpwd= prop.getProperty("incorrectpassword");
		correctpwd= prop.getProperty("correctpassword");
		mail= prop.getProperty("mail");

	}

	@Test(priority=1)  //To check the HRIS logo on the top of the page/web-site which will navigate to the home-page.
	public void Test1HRISLogo()
	{
		action.hrislogo(driver).click();
		Assert.assertTrue(
				driver.findElement(By.cssSelector("#demo-2 > div > div.span12.text-center.margin-10 > a > img")).isDisplayed());
	}

	@Test(priority=2) //To check the Login Panel Tab is opening on clicking.
	public void Test2LoginPanelTab()
	{
		HrisAction.loginpanel(driver).click();
		Assert.assertTrue(
				HrisAction.userid(driver).isEnabled());
		Assert.assertTrue(
				HrisAction.password(driver).isEnabled());

	}
	@Test(priority=3) //To check the "About HRIS" tab.
	public void Test3AboutHRISTab()
	{
		HrisAction.aboutpanel(driver).click();
		Assert.assertTrue(
				driver.findElement(By.cssSelector("div[class='box li-spacing']")).isDisplayed());
	} 
	@Test(priority=4) //To check the "Celebration" tab.
	public void Test4CelebrationTab()
	{
		HrisAction.celebrationpanel(driver).click();
		Assert.assertTrue(
				driver.findElement(By.cssSelector("div#panel3>div.row-fluid>div.span4>h4>i.icon-star:nth-child(1)")).isDisplayed());
	}  
	@Test(priority=5) //To check the "QAIT Update" tab.
	public void Test5QaitUpdate()
	{
		HrisAction.updatepanel(driver).click();

	}
	@Test(priority=6) //To check the "HR Policy" tab.
	public void Test6HRPolicyTab()
	{
		HrisAction.policypanel(driver).click();
		Assert.assertTrue(
				driver.findElement(By.cssSelector("#demo-2 > div > div.tabbable.custom-tabs.tabs-left.tabs-animated."
						+ "flat.flat-all.hide-label-980.shadow.track-url.auto-scroll > div")).isDisplayed());
	} 
	@Test(priority=7) //To check the "Food Menu" tab.
	public void Test7foodMenuTab()
	{
		HrisAction.foodpanel(driver).click();
		Assert.assertTrue(
				driver.findElement(By.cssSelector("#panel5 > div:nth-child(1) > div > h4")).isDisplayed());
	} 
	@Test(priority=8) //To check the "Did you know?" tab.
	public void Test8DidYouKnowTab()
	{
		HrisAction.knowpanel(driver).click();
		Assert.assertTrue(
				driver.findElement(By.cssSelector("#panel7 > div > div > h4")).isDisplayed());
	} 
	@Test(priority=9) //To check the "Access Payroll Online" link at the bottom of the page.
	public void Test9AccessPayrollOnline()
	{
		HrisAction.payroll(driver).click();

		Set<String> tabs = driver.getWindowHandles();
		Iterator<String> i = tabs.iterator();
		String main = i.next();
		String child = i.next();
		driver.switchTo().window(child);
		driver.switchTo().window(main);

		Assert.assertTrue(
				driver.findElement(By.cssSelector("#panel6 > div > div > h4")).isDisplayed());
	} 
	@Test(priority=10) //To check the "Report a Bug" link at the bottom of the page.
	public void Test10ReportBug()
	{
		HrisAction.bugreport(driver).click();

		Set<String> tabs = this.driver.getWindowHandles();
		Iterator<String> i = tabs.iterator();
		String main = i.next();
		String child = i.next();
		driver.switchTo().window(child);
		driver.switchTo().window(main);
	}
	@Test(priority=11) //Test Login Panel with incorrect password
	public void Test11IncorrectPassword()
	{
		HrisAction.loginpanel(driver).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		HrisAction.userid(driver).sendKeys(uname);
		HrisAction.password(driver).sendKeys(incorrectpwd);
		HrisAction.signin(driver).click();

		Assert.assertTrue(
				driver.findElement(By.cssSelector("#login > form > div.loginTxt.txtHideDiv.alert.alert-error > div"))
				.getText().equals("Invalid Login"));
	}
	@Test(priority=12) //Test Login Panel with blank password
	public void Test12BlankPassword()
	{	
		driver.findElement(By.id("txtUserName")).clear();
		driver.findElement(By.id("txtPassword")).clear();
		
		HrisAction.userid(driver).sendKeys(uname);
		HrisAction.password(driver).sendKeys("");
		HrisAction.signin(driver).click();

		String border=driver.findElement(By.cssSelector("#txtPassword")).getAttribute("style");
		Assert.assertTrue(border.contains("red"));

	}
	@Test(priority=13) //Test Login Panel with correct password
	public void Test13CorrectPassword()
	{		
		HrisAction.loginpanel(driver).click();

		HrisAction.userid(driver).sendKeys(uname);
		HrisAction.password(driver).sendKeys(correctpwd);
		HrisAction.signin(driver).click();

		boolean status = false;
		if(driver.findElements(By.cssSelector("#txtUserName")).size()>0)
		{
			status = false ;
		}
		Assert.assertFalse(status);


	}
	@Test(priority=14) //to logout
	public void Test14LogOut()
	{		
		//action.logout();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("document.querySelectorAll(\"a[class='profile-btn']\")[0].click()");
		js.executeScript("document.querySelectorAll(\"a[href*='logoff']\")[0].click()");
	}

	@Test(priority=15) //to test the forgot password option
	public void Test15ForgotPwd()
	{	
		HrisAction.loginpanel(driver).click();

		driver.findElement(By.cssSelector("a[href*='send']")).click();
		
		Set<String> tabs = this.driver.getWindowHandles();
		Iterator<String> i = tabs.iterator();
		String main = i.next();
		String child = i.next();
		driver.switchTo().window(child);
		
		driver.findElement(By.cssSelector("input[name='login']")).sendKeys(uname);
		driver.findElement(By.cssSelector("input[name='mail']")).sendKeys(mail);
		
		driver.switchTo().window(main);
	}

	@AfterClass
	public void close()
	{
	driver.quit();
	}
}
