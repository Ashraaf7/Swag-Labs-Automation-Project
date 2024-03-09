package Listeners;

import Utilities.LogsUtils;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ITestResultListenerClass implements ITestListener {
    public void onTestStart(ITestResult result) {
        LogsUtils.info("Test Case " + result.getName() + " started");
    }

    public void onTestSuccess(ITestResult result) {

    }

    public void onTestSkipped(ITestResult result) {
    }


}
