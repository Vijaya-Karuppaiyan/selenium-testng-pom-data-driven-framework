package com.saucedemo.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.saucedemo.factory.DriverFactory;
import com.saucedemo.utils.ConfigReader;

public class BaseTest {
	
	protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = DriverFactory.initDriver();
        int implicit = ConfigReader.getInt("implicitWait", 10);
        int pageLoad = ConfigReader.getInt("pageLoadTimeout", 30);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicit));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoad));
        driver.get(ConfigReader.get("baseUrl"));
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }

}
