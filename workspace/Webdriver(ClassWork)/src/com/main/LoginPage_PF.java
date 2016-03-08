package com.main;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/*PAGE OBJECT MODEL imp for interview
 * Which is a design pattern in Java which Selenium also follows
 * Designing pattern specific for java called Page object pattern/model
 * i.e how you will design your project or categorize it. It says what ever page you are 
 * trying to automate you create a separate class for each page like a class for homepage,
 * ( a class for login page etc..), and put all the functionalities of that page in that class
 * to test and then create a test class for it for eg HomepageTest class
 */	

public class LoginPage_PF {

	WebDriver driver; // creating a global variable of WebDriver, in order to use it for all browser types
	
	//In PageFactory , we find elements by @FindBy annotation rather than the function findElements(By.id())etc
	//which is more specific to one browser
	
	@FindBy(how = How.ID, using = "username")
	WebElement username;
	@FindBy(how= How.ID, using ="password")
	WebElement pswd;
	@FindBy(how = How.ID, using = "login")
	WebElement LoginBtn;
	
	//Creating a constructor
	public LoginPage_PF(WebDriver driver){
		this.driver = driver;	
	}
	//Writing the function to execute the task
	public void performLogic(String userName, String pwd){
		username.sendKeys(userName);
		pswd.sendKeys(pwd);LoginBtn.click();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
