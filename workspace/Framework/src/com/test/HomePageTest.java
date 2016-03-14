/**
 * 
 */
package com.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import com.base.BasePageTest;
import com.pages.HomePage;
import static org.testng.AssertJUnit.assertEquals;

/**
 * @author vanith.sutrave
 *This class will test the Homepage using TestNG framework
 */

public class HomePageTest extends BasePageTest {
	
	HomePage hp;
	
	@BeforeClass
	private void initialize() {
		setup();
		hp = new HomePage(driver);	
	}
	
	@Test
	public void countSocialIconsTest() {
		int actual = hp.countSocialIcons();
		assertEquals(4, actual);
	}
	
	@Test
	public void clickOnLoginTest() {
		String actual = hp.clickOnLogin();
		assertEquals("http://whiteboxqa.com/login.php", actual);	
	}
	
	@AfterClass
	public void close(){
		driver.quit();
	}
	

}
