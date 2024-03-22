package com.client.listeners;

import io.qameta.allure.ConfigurationBuilder;
import io.qameta.allure.ReportGenerator;
import io.qameta.allure.core.Configuration;
import lombok.extern.slf4j.Slf4j;
import org.testng.ITestContext;
import org.testng.ITestListener;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

import org.apache.commons.io.FileUtils;

import static java.util.Collections.singletonList;

@Slf4j
public class TestListener implements ITestListener {

    private final String outDir = "allure-report";
    private final String resultDir = "allure-results";

    public void onStart(ITestContext context) {
        log.info(String.format("Delete all files in %s folder", resultDir));
        try {
            FileUtils.deleteDirectory(new File(resultDir));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public void onFinish(ITestContext context) {
        final Configuration configuration = new ConfigurationBuilder().useDefault().build();
        final ReportGenerator generator = new ReportGenerator(configuration);
        generator.generateSingleFile(Paths.get(outDir), new ArrayList<>(singletonList(Paths.get(resultDir))));
        log.info(String.format("Successfully generated html report to folder: %s", outDir));
    }
}
