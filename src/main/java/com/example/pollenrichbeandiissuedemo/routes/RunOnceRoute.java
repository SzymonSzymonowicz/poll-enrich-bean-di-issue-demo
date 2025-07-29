package com.example.pollenrichbeandiissuedemo.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import static com.example.pollenrichbeandiissuedemo.routes.PollEnrichRoute.POLL_ENRICH_ROUTE;
import static org.apache.camel.builder.endpoint.StaticEndpointBuilders.timer;

@Component
public class RunOnceRoute extends RouteBuilder {

    private static final String ROUTE_ID = "run-once-route";

    @Override
    public void configure() throws Exception {
        from(timer(ROUTE_ID)
                .repeatCount(1))
            .routeId(ROUTE_ID)
            .to(POLL_ENRICH_ROUTE)
            .log("Run once route finished");
    }
}
