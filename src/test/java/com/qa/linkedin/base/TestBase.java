package com.qa.linkedin.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	
	private static Logger log = Logger.getLogger(TestBase.class);
	public static WebDriver driver = null;
	public static WebDriverWait wait = null;
	

	public static String readProperty(String key) throws IOException {
		log.debug("create obj to properties class");
		Properties prop = new Properties();
		log.debug("read the data from properties file");
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\java\\com\\qa\\linkedin\\config\\config.properties");
		log.debug("load the properties");
		prop.load(fis);
		return prop.getProperty(key);

	}

	@BeforeSuite
	public void setup() throws IOException {

		String browser = readProperty("browser");
		log.debug("fetch the browser value&launch" + browser);
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}

		log.debug("open application url: " + readProperty("url"));
		driver.get(readProperty("url"));
		driver.manage().window().maximize();
		log.debug("add implicit wait");
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(readProperty("implicitWait")), TimeUnit.SECONDS);
		log.debug("create object for WebDriverWait");
		wait = new WebDriverWait(driver, Long.parseLong(readProperty("explicitWait")));

	}

	@AfterSuite
	public void tearDown() {
		try {
		log.debug("closing the browser");
		}finally {
		if (driver != null) {
			driver.close();
		}
		}

	}

}
