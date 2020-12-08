package com.mytests;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserStackBaseTest {
	
	
	WebDriver driver;
	public static final String USERNAME = System.getenv("SAUCE_USERNAME");
	public static final String AUTOMATE_KEY = System.getenv("SAUCE_ACCESS_KEY");
	public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@ondemand.us-west-1.saucelabs.com:443/wd/hub";

	@Parameters({"browser", "browser_version", "os_name_version"})
	@BeforeMethod
	public void setUp(String browserName, String browser_version, String os_name_version,  Method name) {

		System.out.println("browser name is : " + browserName);
		System.out.println("URL  is : " + URL);
		String methodName = name.getName();
		
		DesiredCapabilities caps = null;
		if (browserName.equals("Chrome")) {
			caps = DesiredCapabilities.chrome();
			//WebDriverManager.chromedriver().setup();
			//caps.setCapability("browserName", "Chrome");
		} else if (browserName.equals("Firefox")) {
			caps = DesiredCapabilities.firefox();
			//WebDriverManager.firefoxdriver().setup();
			//caps.setCapability("browserName", "Firefox");
		}
		//caps.setCapability("os", os);
		//caps.setCapability("platformName", os_name_version);
		caps.setCapability("platform", os_name_version);
		//caps.setCapability("browserVersion", browser_version);
		caps.setCapability("version", browser_version);
		caps.setCapability("name",  System.getenv("JOB_NAME")  + " - " + System.getenv("BUILD_NUMBER"));
		caps.setCapability("build", System.getenv("JOB_NAME")+ " - " + System.getenv("STAGE_NAME") + " - " + System.getenv("BUILD_NUMBER"));

		
		try {
			driver = new RemoteWebDriver(new URL(URL), caps);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}
	
	
	

}
