package com.qait.MavenDemo.HRIS_POM;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Practice {
	WebDriver driver;

	
	@BeforeTest
	public void browser()
	{
		System.setProperty("webdriver.chrome.driver","driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://toolsqa.com/automation-practice-switch-windows/");
	}
	
  @Test(priority=1)
  public void Test1() {
	  driver.findElement(By.cssSelector("button[id='button1']")).click();
	  Set<String> tabs = driver.getWindowHandles();
		Iterator<String> i = tabs.iterator();
		String main = i.next();
		String child = i.next();
		driver.switchTo().window(child);
		System.out.println("Switched to New Browser Window");
		driver.switchTo().window(main);
		System.out.println("Switched to Main Window");
  }
  @Test(priority=2)
  public void Test2() {
	  driver.findElement(By.xpath("//*[text()='New Message Window']")).click();
	  Set<String> tabs = driver.getWindowHandles();
		Iterator<String> i = tabs.iterator();
		String main = i.next();
		String child = i.next();
		driver.switchTo().window(child);
		System.out.println("Switched to New Message Window");
		driver.switchTo().window(main);
		System.out.println("Switched to Main Window");
  }
  @Test(priority=3)
  public void Test3() {
	  driver.findElement(By.xpath("//*[text()='New Browser Window']")).click();
	  Set<String> tabs = driver.getWindowHandles();
		Iterator<String> i = tabs.iterator();
		String main = i.next();
		String child = i.next();
		driver.switchTo().window(child);
		System.out.println("Switched to New Message Tab");
		driver.switchTo().window(main);
		System.out.println("Switched to Main Window Tab");
  }
  
  @Test(priority=4)
  public void Test4() {
	  driver.findElement(By.cssSelector("button[id='alert']")).click();
		Alert alert=driver.switchTo().alert();
		System.out.println("Switched to Alert Box having test as: ");
		System.out.println(alert.getText());
		alert.accept(); //to click on "ok" & .dismiss() to click on "" 
  }
  @Test(priority=5)
  public void Test5() throws InterruptedException {
	  
	  driver.findElement(By.cssSelector("button[id='timingAlert']")).click();

	  Alert alert=driver.switchTo().alert();
	  alert.accept();
  }
  
  @AfterClass
  public void close()
  {
	  driver.quit();
  }
}
