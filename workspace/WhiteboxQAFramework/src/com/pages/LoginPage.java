/**
 * 
 */
package com.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.base.BasePage;

/**
 * @author vanith.sutrave
 *This class will contain all the methods and Elements belonging to Login page.
 */
public class LoginPage extends BasePage {
	
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	public String invalidLogin(String uName, String pwd){
		
		WebElement username = driver.findElement(By.xpath(".//*[@id='username']"));
		username.clear();
		username.sendKeys(uName);
		driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
		WebElement password= driver.findElement(By.xpath(".//*[@id='password']"));
		password.clear();
		password.sendKeys(pwd);
		driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
		driver.findElement(By.xpath(".//*[@id='login']")).click();
		driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
		return driver.findElement(By.cssSelector(".text-danger")).getText();
		
	}	
}
