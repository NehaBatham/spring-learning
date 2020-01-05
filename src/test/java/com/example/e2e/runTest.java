package com.example.e2e;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="/spring-learning/src/test/java/com/example/e2e/feature", 
glue="/spring-learning/src/test/java/com/example/e2e/stepdef",
plugin = {"pretty", "html:/spring-learning/target/cucumber"})
public class runTest {

}
