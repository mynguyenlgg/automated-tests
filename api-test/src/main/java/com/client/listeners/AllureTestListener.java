package com.client.listeners;

import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.Attachment;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import io.qameta.allure.model.TestResult;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class AllureTestListener implements TestLifecycleListener {

    @Override
    public void beforeTestWrite(TestResult result) {
        log.info("Before test write: {}", result.getName());
        if (result.getStatus() != null && result.getStatus().equals(Status.PASSED)) {
            try {
                List<StepResult> steps = result.getSteps();
                for (StepResult stepResult : steps) {
                    List<Attachment> attachmentList = stepResult.getAttachments().stream()
                            .filter(att -> (!att.getName().equals("Request")) && (!att.getName().contains("Response")))
                            .collect(Collectors.toList());
                    stepResult.setAttachments(attachmentList);
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
    }
}
