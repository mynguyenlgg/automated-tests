package com.client.listeners;

import org.testng.*;
public class TestListener implements ITestListener, IInvokedMethodListener{

//    public void onTestFailure(TestResult result) {
//        List<StepResult> steps = result.getSteps();
//        for (StepResult stepResult : steps) {
//            stepResult.setAttachments(Collections.emptyList());
//        }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult iTestResult) {
        if (iTestResult.getStatus() == ITestResult.SUCCESS) {
            System.out.println("Test case is pass");


//            String currentTestOrStep = (AllureTestNg) ((TestRunner) ((TestResult) iTestResult).getSteps()

//            ((AllureTestNg) ((TestRunner) ((TestResult) iTestResult).m_context).m_testListeners.get(1)).lifecycle.storage.getTestResult("4ab7df39-8c9a-4c5a-9c1f-d928465bf757").value.steps.get(1).setAttachments(Collections.emptyList())
    }
//        if (iTestResult.getStatus() == ITestResult.FAILURE || iTestResult.getStatus() == ITestResult.SKIP) {
//            List<String> testLogs = Reporter.getOutput(iTestResult);
//            attachLogsInAllureReport(testLogs);
//            Reporter.clear();
//        }
//    }

//    private void attachLogsInAllureReport(List<String> testLogs) {
//        for (String test : testLogs) {
//            Allure.addAttachment("Logs", test);
//        }
    }
}
