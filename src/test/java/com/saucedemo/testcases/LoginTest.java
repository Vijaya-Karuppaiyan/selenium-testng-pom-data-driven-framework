package com.saucedemo.testcases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.utils.ConfigReader;
import com.saucedemo.utils.ExcelUtil;

public class LoginTest extends BaseTest {
	
	@DataProvider(name = "loginData")
    public Object[][] loginDataProvider() {
        // sheet name must match Excel sheet title
        return ExcelUtil.getTestData("LoginTestData");
    }

    // Column mapping: [0]=TestCaseID, [1]=Username, [2]=Password, [3]=ExpectedResult
    @Test(dataProvider = "loginData")
    public void loginWithExcelData(Object[] row) {
        String testCaseId = row[0].toString().trim();
        String username = row[1].toString().trim();
        String password = row[2].toString().trim();
        String expected = row[3].toString().trim(); // "Success" or "Fail"

        LoginPage lp = new LoginPage(driver);
        lp.doLogin(username, password);

        if ("Success".equalsIgnoreCase(expected)) {
            // assert login success
            Assert.assertTrue(lp.isLoginSuccessful(),
                    testCaseId + ": expected success but login failed. Error: " + lp.getErrorMessage());
        } else {
            // assert login failure
            Assert.assertFalse(lp.isLoginSuccessful(),
                    testCaseId + ": expected failure but login succeeded.");
            // optionally assert specific error messages exist (improve test coverage)
            String err = lp.getErrorMessage();
            Assert.assertFalse(err.isEmpty(), testCaseId + ": expected an error message but none shown.");
        }
}
}
