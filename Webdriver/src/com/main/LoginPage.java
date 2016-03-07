package com.main;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;



public class LoginPage {
	
	   public static void performLogin(){

	        //opening a browser
	        WebDriver  driver = new FirefoxDriver();
	       
	        //get the website - whiteboxqa
	        driver.get("https://facebook.com/");
	       
	        //click on login button
	        WebElement email = driver.findElement(By.id("email"));
	       
	        //enter email
	        email.sendKeys("bharati@whp.com");
	       
	        WebElement pwd = driver.findElement(By.name("pass"));
	       
	        pwd.sendKeys("bharti");
	       
	        WebElement loginBtn = driver.findElement(By.id("u_0_n"));
	        loginBtn.click();
	       
	        driver.findElement(By.xpath(".//*[@id='u_0_1']"));
	        By.cssSelector("#u_0_1");
	       
	   
	       
	        driver.quit();   
	    }
	    //----------------------------------------------------------------------------------------------------------
	   
   
    public static void driverAPISample(){
       
        WebDriver  driver = new FirefoxDriver();
        driver.get("http://whiteboxqa.com/");
       
        /*List<WebElement> elements = driver.findElements(By.xpath("html/body/header/div[1]/div/ul/li/a"));
       
        for(WebElement e : elements)
        {
            e.click();
        }
        System.out.println(elements.size());*/
       
        System.out.println("the url is " + driver.getCurrentUrl());
        System.out.println("The page source " + driver.getPageSource());
        System.out.println("The page title" + driver.getTitle());
       
        driver.quit();   
    }
    //-----------------------------------------------------------------------------------------------------------
   
    public static void elementsSample(){
        WebDriver driver = new FirefoxDriver();
        driver.get("http://whiteboxqa.com/login.php");
   
        WebElement uname = driver.findElement(By.id("username"));
        //uname.clear();       // if the username and pwd is cached then this function can be used to clear
        uname.sendKeys("xnbsns");
        //uname.equals(obj);
        System.out.println("The class is " +uname.getAttribute("class"));
        System.out.println("The location is "+uname.getLocation());
        Point p = uname.getLocation();
        p.getX();
        p.getY();
       
        if(uname.isDisplayed());   // to know if a particular element is present
        uname.isEnabled();        // for eg to know if a particular link is enabled or a button is enabled
        uname.isSelected();        // to check for eg a rdio button or check box is selected
        uname.submit();  // this is used in case of a form to submit a form , it acts like click but is for a form
 
        // when you type uname.(), it will also show u the return type , try it
       
        System.out.println("The size is "+ uname.getSize()); // to get the size of the element
        System.out.println("The tag is "+ uname.getTagName());// to get the name of the tag<>
        System.out.println(driver.findElement(By.id("login")).getCssValue("padding"));
        driver.findElement(By.id("password")).sendKeys("xnbsns");
        driver.findElement(By.id("login")).click();
        driver.close();
           
    }
    //----------------------------------------------------------------------------------------------------------------
   
    /*Navigation Api is a standalone interface, so to access that we have a navigate function in
     * WebDriver to access it */
   
    public static void navigateSample(){
        WebDriver driver = new FirefoxDriver();
        //driver.get("http://whiteboxqa.com"); // same as 2 statements below
       
       
        driver.navigate().to("http://whiteboxqa.com");
        /*try {
            driver.navigate().to(new URL("http://whiteboxqa.com"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }*/
       
        /*Diff b/w to() and get()??? to() is similar as get*
         *             GET                                                TO /
         *Get belongs to WebDriver interface.            To belongs to Navigation interface.
         *No Overloaded function,() takes only string.   2 versions of to(),takes string or URL as parameter.
         */
       
        WebElement element = driver.findElement(By.xpath(".//*[@id='navbar-collapse']/ul/li[3]/a"));
        element.click();                // element and functions you can access by creating an obj of WebElement
        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();   
    }
    //--------------------------------------------------------------------------------------------------------------
    /*WEBDRIVER TIMEOUTS, WAITS
     * Implicit wait - when you scripts take longer to run, you ask your webdriver to wait
     * until the element or condition is found/satisfied. With Implicit wait once the browser is
     * closed the wait session is also closed.
     * Explicit wait - It may be that only one element in the script may be taking more time, in
     * that scenario you can mention explicitly that particular element with wait
     * Fluent wait - time taken by the script run each time may vary, it may take 10ms the first
     * time 50 the second, in such cases you can have a range of wait specified(not so much in use)*/
   
    public static void waitExample(){
        WebDriver driver = new FirefoxDriver();
        driver.get("http://whiteboxqa.com");
       
        //There are 3 kinds of Timeouts - ImplicitlyWait,setScriptTimeout, pageLoadTimeout.
        //Implicit wait
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS); // TimeUnit is an enum class
        //driver.manage().timeouts().setScriptTimeout(100, TimeUnit.MILLISECONDS);
        /*if suppose your JS is taking to load, then you can put setScriptTimeout instead of elements you are
         * asking wait-time for Scripts to load,if not throw an exception*/
        //driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.MILLISECONDS);
        /*This is asking to wait for Page load, if not throw an exception*/
       
        //Explicit wait (first create an instance of webdriver wait)
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='navbar-collapse']/ul/li[3]/a")));   
               
        //WebElement element = driver.findElement(By.xpath(".//*[@id='navbar-collapse']/ul/li[3]/a"));
        element.click();
       
        //Fluent wait
        Wait w = new FluentWait(driver)
        .withTimeout(100, TimeUnit.SECONDS)
        .pollingEvery(10, TimeUnit.SECONDS)
        .ignoring(NoSuchElementException.class);
       
        /*WebElement foo = (WebElement) w.until(new Function(){
            public WebElement apply(WebDriver driver){
                return driver.findElement(By.id("foo"));
            }
            @Override
            public Object apply(Object paramF){
                return null;  
            }
        });*/
      //Better to surround with try catch block just in case the condition is not satisfied.
       
    }
    
    
   
    //---------------------------------------------------------------------------------------------------------------
 
    public static void main(String[] args) {
        //LoginPage.performLogin();
       
        //LoginPage.driverAPISample();
       
        //LoginPage.elementsSample();
       
        //LoginPage.navigateSample();
       
        LoginPage.waitExample();
    }
       
   

}
