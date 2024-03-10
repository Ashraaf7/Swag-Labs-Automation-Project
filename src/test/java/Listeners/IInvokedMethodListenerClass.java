package Listeners;

import Utilities.LogsUtils;
import Utilities.Utility;
import io.qameta.allure.Allure;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static DriverFactory.DriverFactory.getDriver;

public class IInvokedMethodListenerClass implements IInvokedMethodListener {
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {

    }

    public void afterInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
        //Utility.takeFullScreenshot(getDriver(), new P02_LandingPage(getDriver()).getNumberOfSelectedProductsOnCart());
        switch (testResult.getStatus()) {
            case ITestResult.FAILURE:
                LogsUtils.info("Test Case " + testResult.getName() + " failed");
                Utility.takeScreenShot(getDriver(), testResult.getName()); //validLoginTC-2024-03-03-8-17pm
                break;
            case ITestResult.SUCCESS:
                LogsUtils.info("Test Case " + testResult.getName() + " passed");
                break;
            case ITestResult.SKIP:
                LogsUtils.info("Test Case " + testResult.getName() + " skipped");
                break;
        }
        try {
            File logFile = Utility.getLatestFile(LogsUtils.LOGS_PATH);
            assert logFile != null;
            Allure.addAttachment("logs.log", Files.readString(Path.of(logFile.getPath())));
        } catch (IOException e) {
            LogsUtils.error(e.getMessage());
        }

    }
}
