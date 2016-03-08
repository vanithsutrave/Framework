package com.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import com.gargoylesoftware.htmlunit.javascript.host.History;

public class APIExamples {
	
	public static void windowEx(){
		
		WebDriver driver = new FirefoxDriver();
		driver.get("http://whiteboxqa.com/");
		
		/*Sometimes there is a smaller window above the base window, so if want the control 
		 * on the smaller window in order to access the base window or since the small window is
		 * in the minimized version, you may have to scroll to look at all the elements, but if 
		 * you use the maximize function then you can avoid unneccessary errors*/
		driver.manage().window().maximize(); // will play with the browser to maximize.
		//driver.manage().window().fullscreen(); // same as maximize but will show on full screen of your computer
		System.out.println(driver.manage().window().getPosition());// where the element is located. Point(corresponding to element)
		System.out.println(driver.manage().window().getSize());// the size/dimension of that element. (corresponding to element)
		Point pt = new Point(300, 500);// Point is a class
		driver.manage().window().setPosition(pt);// since it takes a arg, make and obj and pass as arg like above
		// (corresponding to window)
		Dimension dim = new Dimension(40,40);
		driver.manage().window().setSize(dim);
	}
	//---------------------------------------------------------------------------------------------------
	
	/*ALERTS:Alert is an interface API. 3types;
	 * -Alerts
	 * -Confirmation Popups
	 * -Prompts
	 */
	
	public static void alertEx(){
		
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///C:/Users/vanith.sutrave/Desktop/hi.html"); // html page with alert
		
		driver.findElement(By.id("test")).click();
		Alert alert = driver.switchTo().alert();// u need switch to that tab/popup-window in order to do anything
		System.out.println("The Text "+ alert.getText());
		alert.accept();
		try {
			Thread.sleep(2000);// to make it wait to see the actions
		} catch (InterruptedException e) {
			e.printStackTrace(); // checked / compile-time exception
		}
		driver.findElement(By.id("test")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		alert.dismiss();
		
		//PROMPT
		WebDriver d = new FirefoxDriver();
		d.get("file:///C:/Users/vanith.sutrave/Desktop/prompt.html");
		d.findElement(By.id("myId")).click();
		Alert a = d.switchTo().alert();
		System.out.println("The Text "+ alert.getText());
		alert.sendKeys("Bharati");
		alert.accept();
		try {
			Thread.sleep(2000);// to make it wait to see the actions
		} catch (InterruptedException e) {
			e.printStackTrace(); // checked / compile-time exception
		}
		driver.findElement(By.id("test")).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		alert.dismiss();		
	}
	
//-----------------------------------------------------------------------------
	//OPTION(manage(), ... etc go through
	
	//WINDOWS/WINDOWHANDLE/S --- suppose different tab/ windows open when clicked(not alerts/popups)
	// eg: pinterest site, which does not have a alert/popup but has a smaller window
	
	public static void windowsSwitchEx(){
		WebDriver driver = new FirefoxDriver();
		driver.get("https://www.pinterest.com/");
		String win = driver.getWindowHandle();      // This api will give any child window opening on top of it as a string name
		driver.switchTo().window(win);
		driver.findElement(By.id("userEmail")).sendKeys("jhsdjkkdj");
		
		// when clicked many windows open , refer following;
		
		
		Set<String> s = driver.getWindowHandles();
		Iterator<String> it = s.iterator();
			while(it.hasNext()){
				String child = it.next();
				driver.switchTo().window(child);
				driver.close();
			}
		//IFRAMES	
		//driver.switchTo().frame(arg0);
		//driver.switchTo().parentFrame(); ----------study about iframes(html)
		}
	//-------------------------------------------------------------------------
	//FOR CHECKBOXES/DROPDOWNS : Select API
	public static void selectEx(){
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///C:/Users/vanith.sutrave/Desktop/select.html");
	
		WebElement dd = driver.findElement(By.name("Mobiles"));
		Select select = new Select(dd);
		select.selectByValue("samsung");
	
	}
	//-------------------------------------------------------------------------------
	/*COOKIE ,  is a small piece of data sent from a website and 
	 * stored in the user's web browser while the user is browsing it.
	 *  Every time the user loads the website, the browser sends the 
	 *  cookie back to the server to notify the user's previous activity.*/
	
	public static void cookiesEx(){
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.whiteboxqa.com");
		
		Set<Cookie> cookies = driver.manage().getCookies(); // Set is is key value pairs
		Iterator<Cookie> it = cookies.iterator();
		while(it.hasNext()){
			Cookie cookie = it.next();
			System.out.println("Name "+ cookie.getName()+"Value "+cookie.getValue());	
		}
		//OR, id u dont want to access all the cookies, just need one cookie then;
		
		System.out.println(driver.manage().getCookieNamed("PHPSESSID"));
		Cookie ck = new Cookie("Webdriver", "DecBatchcookies");
		driver.manage().addCookie(ck);
	}
	//---------------------------------------------------------------------------
	//TAKE SCREENSHOT API
	//<X> means it can be typecasted to anything, like * symbol is anything
	
	public static void screenshotEx(){
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.whiteboxqa.com");
		// there is no direct link of TakesScreenshot Api to WebDriver, so we typecaste driver instance
		File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); // Take it into a temporary file
		if(file.exists()){ // checking if there is any file
			System.out.println(file.getAbsolutePath()); // get the path of the temporary file
			try {
				FileUtils.copyFile(file, new File("C:\\Users\\vanith.sutrave\\+7-Desktop\\screen1.png")); // copy to the file you want for future access, check how to write absolute path
			} catch (IOException e) {
				e.printStackTrace();
			}
		}	
	}
	
	//-----------------------------------------------------------------------------------------------------
	//ACTIONS API: For mouse over events and keyboard
	public static void actionsEx(){
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.whiteboxqa.com");
		
		WebElement element = driver.findElement(By.cssSelector(".dropdown-toggle"));
		Actions actions = new Actions(driver);
		actions.moveToElement(element).build().perform(); // move to that element, build is like compile and perform is like run
		
		//drag and drop is imp - eg on dhtmlx.com
		dragDropEx();
		
		//actions.clickAndHold(element); -- check how to use these, just the names put here
		//actions.doubleClick(element);-- check how to use these, just the names put here
		//actions.keyDown(theKey);-- check how to use these, just the names put here
		//actions.keyUp(theKey);-- check how to use these, just the names put here	
	}
	/* void clickAndHold(Actions action){
		actions.clickAndHold(element).perform();}*/
	
	public static void dragDropEx(){
		WebDriver driver = new FirefoxDriver();
		driver.get("http://dhtmlx.com/docs/products/dhtmlxTree/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
		
		// choose the element which you want to drag
		WebElement from = driver.findElement(By.xpath(".//*[@id='treebox1']/div/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[2]/table/tbody/tr[6]/td[2]/table/tbody/tr/td[4]/span"));
		// choose the location where you want to drop
		WebElement to = driver.findElement(By.xpath(".//*[@id='treebox2']/div/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[2]/table/tbody/tr[1]/td[4]/span"));
		
		Actions actions = new Actions(driver);
		actions.clickAndHold(from).moveToElement(to).release(to).build().perform(); // know this 
		//OR
		//actions.dragAndDrop(from, to).build().perform();
	}
	//--------------------------------------------------------------------------------------------------
	/*JAVASCRIPT EXECUTOR (you need to LEARN JAVASCRIPT to use all functions)
		1) executeScript (it will execute the script first and then other tasks will continue)
		2) excuteAsyncScript(Asynchronous is multiple threads can be executed at the same time,
		it will keep performing parallel with other tasks 	
	*/
	
	public static void jsExecEx(){
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.whiteboxqa.com");
		
		JavascriptExecutor js = (JavascriptExecutor)driver; // typecasting the webdriver(instance) to JavascriptExecutor
		//js.executeScript("alert('hello');");
		
		WebElement element = driver.findElement(By.xpath(".//*[@id='loginButton']"));
		js.executeAsyncScript("arguments[0].click();", element);
		//History.go()--- func to be aware of
		//js.ready() -- if want your whole page to load---func to be aware of	
	}
	//-------------------------------------------------------------------------------------------------
	/*FOR CHROME AND INTERNET EXPLORER _ BROWSERS
	 * First download each of the browser drivers, extract it and copy it to the project
	 * Firefox came with default selenium and webdriver drivers, but for other browsers
	 * we need to install it
	 */
	public static void browserEx(){
		// for CHROME browser to open
		//System.setProperty("webdriver.chrome.driver", "chromedriver.exe"); // if the file is on your local you will provide
		// it like this, but if its external provide the entire path.
		//WebDriver driver = new ChromeDriver();
		
		// for InternetExplorer browser to open
		//System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
		//WebDriver driver = new InternetExplorerDriver();
		//driver.get("http://google.com");
	
	//HTML Unit Driver - this a native browser of webdriver, its a headless browser
	// meaning it has not GUI like others above.
	
		//WebDriver driver = new HtmlUnitDriver();
		//driver.get("http://google.com");
		
	/*Remote WebDriver - very rarely used, but know
	 * When you want to run scripts on a different browser of a different machine
	 *  not local then we can use Remote webdriver.
	 *  What are Capabilities (of browser)- for eg some browser do not support JS, 
	 *  some may block popups etc...
	 */
		
		//WebDriver driver = new RemoteWebDriver();	
	}
	
	public static void capEx(){
		DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
		//cap.getBrowserName();
		cap.setJavascriptEnabled(true);
		//REFER GitHub
		//eg; Questions they can ask: how do you enable your javascript ? or
		//if your browser doesnt support what do you so, then say set capabilities
	}
	/*MOBILE EMULATION - comes only with the chrome browser. In chrome, it has the capabilty
	 * to emulate any mobile or device
	 */
	
	public static void mobileEmulationEx(){
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		
		 Map<String, Object> chromeOptions = new HashMap<String, Object>();
         Map<String, String> mobileEmulation = new HashMap<String, String>();
         mobileEmulation.put("deviceName","Apple iPhone 6");
         chromeOptions.put("mobileEmulation", mobileEmulation);
         cap.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
         
         WebDriver driver = new ChromeDriver(cap);
		driver.get("http://google.com");
		// download Chrome
	}
	//------------------------------------------------------------------------------------------------------
	
	
	 
	public static void main(String[] args) {
		//windowEx();
		//alertEx();
		//windowsSwitchEx();
		//selectEx();
		//cookiesEx();
		//screenshotEx();
		//actionsEx();
		// jsExecEx();
		//browserEx();
		//mobileEmulationEx();
}
}
