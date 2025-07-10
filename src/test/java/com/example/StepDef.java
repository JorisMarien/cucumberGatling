package com.example;

import io.cucumber.java.en.Given;

import java.io.IOException;

public class StepDef {

    @Given("I start the Google load test")
    public void startGoogleLoadTest() throws IOException, InterruptedException {
        GatlingRunner.runSimulation();
    }
}
