package com.selenium.test.testng.tests_runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Test Runner
 */
@RunWith(value = Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/",
        glue = { "com.selenium.test.stepDefs" },
        tags = { "@wip" },
        plugin = {"pretty", "html:target/cucumber", "json:target/cucumber.json" })
public class WipRunner {
}
