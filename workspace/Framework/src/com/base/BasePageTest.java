package com.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public abstract class BasePageTest {
	
	public WebDriver driver;
	
	public void setup(){
		driver = new FirefoxDriver();
		driver.get("http://whiteboxqa.com/");
		driver.manage().window().maximize();
	}
}