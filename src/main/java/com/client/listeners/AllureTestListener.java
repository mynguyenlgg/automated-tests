package com.client.listeners;

import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.Attachment;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import io.qameta.allure.model.TestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.stream.Collectors;

public class AllureTestListener implements TestLifecycleListener {

    private static final Logger logger = LoggerFactory.getLogger(AllureTestListener.class);

    @Override
    public void beforeTestWrite(TestResult result) {
        logger.info("Before test write: {}", result.getName());
        if (result.getStatus() != null && result.getStatus().equals(Status.PASSED)) {
            try {
                List<StepResult> steps = result.getSteps();
                for (StepResult stepResult: steps) {
                    List<Attachment> attachmentList = stepResult.getAttachments().stream()
                            .filter(att -> (!att.getName().equals("Request")) && (!att.getName().contains("Response")))
                            .collect(Collectors.toList());
                    stepResult.setAttachments(attachmentList);
                }
            } catch (Exception e) {
                logger.error( e.getMessage());
            }
        }
    }
}
