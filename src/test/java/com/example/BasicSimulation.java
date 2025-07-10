package com.example;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class BasicSimulation extends Simulation {

    HttpProtocolBuilder httpProtocol = http
            .baseUrl("https://www.google.com")
            .acceptHeader("application/json");

    ScenarioBuilder scenario = scenario("Basic Test")
            .exec(
                    http("Get Root")
                            .get("/")
                            .check(status().is(200))
            );

    {
        setUp(
                scenario.injectOpen(atOnceUsers(10))
        ).protocols(httpProtocol);
    }
}
