package com.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.assertEquals;
import com.base.BasePageTest;
import com.pages.HomePage;
import com.pages.LoginPage;

public class LoginPageTest extends BasePageTest{
	
	
	LoginPage lp;
	HomePage hp;
	
	@BeforeClass
	public void initialize(){
		setup();
		lp = new LoginPage(driver);
		hp = new HomePage(driver);
		hp.clickOnLogin();
	}
	
	@DataProvider(name = "userdata")
	public Object[][] getData(){
		Object[][] data = {{"Pete","34rai"},{"John","Cla23"},};
		return data;
	}
	
	@Test(dataProvider = "userdata")
	public void invalidLoginTest(String uName, String pwd){
		String actual = lp.invalidLogin(uName, pwd);
		assertEquals("Something went wrong...Please try again.", actual);
	}
	
	@AfterClass
	public void close(){
		driver.quit();
	}
	

}

