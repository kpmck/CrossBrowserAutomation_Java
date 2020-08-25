package framework.driver;

import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	
    public static WebDriver WebDriver(Browser type, Device device)
    {
        WebDriver driver = null;

        switch (type)
        {
            case Edge:
                driver = EdgeDriver(device);
                break;
            case Firefox:
                driver = FirefoxDriver(device);
                break;
            case Chrome:
                driver = ChromeDriver(device);
                break;
            case Phone:
                driver = PhoneDriver();
                break;
        }
        return driver;
    }

    private static WebDriver PhoneDriver()
    {
        ChromeOptions options = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
    	Map<String, String> mobileEmulation = new HashMap<>();
    	mobileEmulation.put("deviceName", "iPhone X");
    	options.setExperimentalOption("mobileEmulation", mobileEmulation);
        return new ChromeDriver(options);
    }

    private static ChromeDriver ChromeDriver(Device device)
    {
        ChromeOptions options = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        switch (device)
        {
            case Desktop:
                options.addArguments("--window-size=1200,700");
                break;
            case Tablet:
            	Map<String, String> mobileEmulation = new HashMap<>();
            	mobileEmulation.put("deviceName", "iPad");
            	options.setExperimentalOption("mobileEmulation", mobileEmulation);
                options.addArguments("--window-size=768,700");
                break;
			default:
				break;
        }
        return new ChromeDriver(options);
    }

    private static FirefoxDriver FirefoxDriver(Device device)
    {
    	WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        switch (device)
        {
            case Desktop:
                options.addArguments("--width=1200");
                options.addArguments("--height=700");
                break;
            case Tablet:
                options.addArguments("--width=768");
                options.addArguments("--height=700");
                break;
			default:
				break;
        }
        return new FirefoxDriver(options);
    }


    private static EdgeDriver EdgeDriver(Device device)
    {
    	WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        EdgeDriver _driver = null;
        switch (device)
        {
            case Desktop:
                options.addArguments("--start-maximized");
                _driver = new EdgeDriver(options);
                _driver.manage().window().fullscreen();
                break;
            case Tablet:
                _driver = new EdgeDriver(options);
                _driver.manage().window().setSize(new Dimension(768, 700));
                break;
			default:
				break;
        }
        return _driver;
    }
}