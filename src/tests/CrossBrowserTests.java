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
import framework.driver.Device;

@RunWith(Parameterized.class)
public class CrossBrowserTests {
    private static WebDriver _driver;
    private static String _applicationPath ="http://automationpractice.com/index.php";
    private Browser _browser;
    private Device _device;
    
    public CrossBrowserTests(Browser browser, Device device)
    {
    	_browser = browser;
    	_device = device;
    }
    
    @Parameters
    public static Collection browsersStrings(){
      return Arrays.asList(new Object[][] 
    	  { 
	    	  {Browser.Chrome, Device.Desktop}, 
	    	  {Browser.Firefox, Device.Desktop}, 
	    	  {Browser.Edge, Device.Desktop}, 
	    	  {Browser.Chrome, Device.Tablet}, 
	    	  {Browser.Firefox, Device.Tablet}, 
	    	  {Browser.Edge, Device.Tablet}, 
	    	  {Browser.Phone, Device.Phone} 
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