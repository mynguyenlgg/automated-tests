package com.client.tests.Board;

import com.client.config.AllureRestAssuredLogger;
import io.qameta.allure.Allure;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.util.List;

public class TestListener implements IInvokedMethodListener {

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult iTestResult) {
        if (iTestResult.getStatus() == ITestResult.FAILURE || iTestResult.getStatus() == ITestResult.SKIP) {
            List<String> testLogs = Reporter.getOutput(iTestResult);
            attachLogsInAllureReport(testLogs);
            Reporter.clear();
        }
    }

    private void attachLogsInAllureReport(List<String> testLogs) {
        for (String test : testLogs) {
            Allure.addAttachment("Logs", test);
        }
    }
}
