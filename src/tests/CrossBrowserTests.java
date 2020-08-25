package tests;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import framework.driver.Browser;
import framework.driver.DriverFactory;
import framework.driver.ViewPort;

@RunWith(Parameterized.class)
public class CrossBrowserTests {
    private static WebDriver _driver;
    private static String _applicationPath ="http://automationpractice.com/index.php";
    private Browser _browser;
    private ViewPort _device;
    
    public CrossBrowserTests(Browser browser, ViewPort device)
    {
    	_browser = browser;
    	_device = device;
    }
    
    @Parameters
    public static Collection browsersStrings(){
      return Arrays.asList(new Object[][] 
    	  { 
	    	  {Browser.Chrome, ViewPort.Desktop}, 
	    	  {Browser.Firefox, ViewPort.Desktop}, 
	    	  {Browser.Edge, ViewPort.Desktop}, 
	    	  {Browser.Chrome, ViewPort.Tablet}, 
	    	  {Browser.Firefox, ViewPort.Tablet}, 
	    	  {Browser.Edge, ViewPort.Tablet}, 
	    	  {Browser.Phone, ViewPort.Phone} 
    	  });
    }
    
    @After
    public void TearDown()
    {
        _driver.quit();
    }
    
    @Before
    public void Setup() {
        _driver = DriverFactory.WebDriver(_browser, _device);
        _driver.navigate().to(_applicationPath);
    }
    
    @Test
    public void VerifyLogo() {
        assertTrue(_driver.findElement(By.id("header_logo")).isDisplayed());   
    }
 }