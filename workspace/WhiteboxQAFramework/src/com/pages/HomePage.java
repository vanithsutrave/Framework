/**
 * 
 */
package com.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.base.BasePage;


/**
 * @author vanith.sutrave
 *This class will contain all the methods and Elements belonging to Home page.
 */
public class HomePage extends BasePage {
	
	public HomePage(WebDriver driver) {
		super(driver);
	}

	public int countSocialIcons(){
		List<WebElement> elements = driver.findElements(By.cssSelector(".pull-right.social-icons>li"));
		return elements.size();
	}
	
	public String clickOnLogin(){
		driver.findElement(By.cssSelector("#loginButton")).click();
		return driver.getCurrentUrl();
	}

}
