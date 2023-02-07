package com.base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class BaseClass {
	
	public static WebDriver driver = null;
	
	public static void browserlaunch()	{
	driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		
	public static void Hotelurl(String url) {
		driver.get(url);
	}
	
	public static void click(WebElement element) {
	 element.click();
	}
	
	public static void clear(WebElement element) {
		 element.clear();
		}
		
	public static void close() {
		driver.close();
	}
	//WebElement element = driver.findElement(By.id(""));
	
	public static WebElement findingelement (String id) {
		return driver.findElement(By.id(id));

		}
	
	public static void senddata (WebElement element, String data) {
	element.sendKeys(data);
	}
	
	public static void selectByIndex(WebElement element,int index) {
		Select s=new Select(element);
		s.selectByIndex(index);
		}
	
	public static void selectByValue(WebElement element,String value) {
		Select s=new Select(element);
		s.selectByValue(value);
	}
	public static void selectByVisibleText(WebElement element,String text) {
		Select s=new Select(element);
		s.selectByVisibleText(text);
	}
	
	public static void getAttribute(WebElement element) {
		String data=element.getAttribute("Value");
		System.out.println(data);
	
}
}
