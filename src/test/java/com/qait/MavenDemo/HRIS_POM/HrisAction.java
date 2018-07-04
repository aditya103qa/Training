package com.qait.MavenDemo.HRIS_POM;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class HrisAction {
	
	WebDriver driver1;
	JavascriptExecutor js = (JavascriptExecutor)driver1;

	public WebDriver initiation()
	{
		
		System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
		driver1 = new ChromeDriver();
		driver1.get("https://hris.qainfotech.com/login.php");
		driver1.manage().window().maximize();
		return driver1;

	}
	public  WebElement hrislogo(WebDriver driver)
	{
		 return driver.findElement(By.cssSelector("a>img"));
	}
	public static WebElement loginpanel(WebDriver driver)
	{
		return driver.findElement(By.cssSelector("a[href*='panel1']"));
	}
	public static WebElement aboutpanel(WebDriver driver)
	{
		return driver.findElement(By.cssSelector("a[href*='panel2']"));
	}
	public static WebElement celebrationpanel(WebDriver driver)
	{
		return driver.findElement(By.cssSelector("a[href*='panel3']"));
	}
	public static WebElement updatepanel(WebDriver driver)
	{
		return driver.findElement(By.cssSelector("a[href*='panel4']"));
	}
	public static WebElement policypanel(WebDriver driver)
	{
		return driver.findElement(By.cssSelector("a[href*='panel5']"));
	}
	public static WebElement foodpanel(WebDriver driver)
	{
		return driver.findElement(By.cssSelector("a[href*='panel7']"));
	}
	public static WebElement knowpanel(WebDriver driver)
	{
		return driver.findElement(By.cssSelector("a[href*='panel6']"));
	}
	public static WebElement payroll(WebDriver driver)
	{
		return driver.findElement(By.cssSelector("a[href*='payroll']"));
	}
	public static WebElement bugreport(WebDriver driver)
	{
		return driver.findElement(By.cssSelector("a[href*='bug']"));
	}
	public static WebElement userid(WebDriver driver)
	{
		return driver.findElement(By.id("txtUserName"));
	}
	public static WebElement password(WebDriver driver)
	{
		return driver.findElement(By.id("txtPassword"));
	}
	public static WebElement signin(WebDriver driver)
	{
		return driver.findElement(By.cssSelector("input[class*='btn']"));
	}
public void logout()
{
	js.executeScript("document.querySelectorAll(\"a[class='profile-btn']\")[0].click()");
	js.executeScript("document.querySelectorAll(\"a[href*='logoff']\")[0].click()");
}
}
