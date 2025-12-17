package com.saucedemo.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.saucedemo.utils.ConfigReader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	 private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	    public static WebDriver initDriver() {
	        String browser = ConfigReader.get("browser", "chrome");
	        if (browser.equalsIgnoreCase("chrome")) {
	           WebDriverManager.chromedriver().setup();
	            ChromeOptions options = new ChromeOptions();
	            if (ConfigReader.getBoolean("headless", false)) {
	                options.addArguments("--headless=new");
	            }
	            options.addArguments("--remote-allow-origins=*");
	            driver.set(new ChromeDriver(options));
	        } else {
	            throw new RuntimeException("Unsupported browser: " + browser);
	        }
	        driver.get().manage().window().maximize();
	        return driver.get();
	    }

	    public static WebDriver getDriver() {
	        return driver.get();
	    }

	    public static void quitDriver() {
	        if (driver.get() != null) {
	            driver.get().quit();
	            driver.remove();
	        }
	    }

}
