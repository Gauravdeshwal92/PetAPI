package com.au.petstore.utils;

import org.testng.ISuite;
import org.testng.ISuiteListener;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Comparator;

public class Listeners implements ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
        clearAllureResults();
    }

    @Override
    public void onFinish(ISuite suite) {
        System.out.println("Suite " + suite.getName() + " has finished.");
    }

    private void clearAllureResults() {
        Path allureResultsPath = Paths.get("allure-results");
        if (Files.exists(allureResultsPath)) {
            try {
                Files.walk(allureResultsPath)
                        .sorted(Comparator.reverseOrder())
                        .map(Path::toFile)
                        .forEach(File::delete);
                System.out.println("Allure-results folder cleared.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
