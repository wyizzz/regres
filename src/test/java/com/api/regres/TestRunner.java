package com.api.regres;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

import java.util.stream.Stream;

//@RunWith(Cucumber.class)
//@CucumberOptions(plugin = {"pretty" , "html:target/Reports"}, features = "src/test/resources/com.api.regres.features")
public final class TestRunner {
//    private static String[] defaultOptions = {
//            "-g", "stepDefs",
//            "-p", "pretty",
//            "-p", "html:target/Reports",
//            "src/test/resources/com.api.regres.features"
//    };
    public static void main(String[] args) throws Throwable {
//        Stream<String> cucumberOptions;
//        cucumberOptions = Stream.concat(Stream.of(defaultOptions), Stream.of(args));
//        System.out.println();
        cucumber.api.cli.Main.main(new String[]{"--glue", "com.api.regres.stepDefs", "-p", "pretty", "-p", "html:Reports", "com.api.regres.features"});
    }
}
