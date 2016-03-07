package com.test;
// As per design pattern we are making a test class(using testng) for each page(LoginPage_PF class.java)
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.main.LoginPage_PF;

public class LoginPage_PFTest {
	
	WebDriver driver;  
	LoginPage_PF lp;  // creating a global variable of the class
	
	@BeforeMethod
	public void intialize(){
		driver = new FirefoxDriver();
		driver.get("http://whiteboxqa.com/login.php");
		lp = PageFactory.initElements(driver, LoginPage_PF.class);
	}
	@Test
	public void method1(){
		lp.performLogic("jsak", "skjnskj");
		System.out.println("error");
	}
}

/*REFER SELENIUM PRESENTATION --- imp
 * Exceptions

– ElementNotVisibleException

– IllegalLocatorException

– InvalidSelectorException

– NoAlertPresentException

– NoSuchElementException

– NoSuchWindowException

– NotFoundException

– StaleElementReferenceException

– TimeoutException

– UnhandledAlertException

– UnsupportedCommandException

– WebDriverException

– XPathLookupException
*/
