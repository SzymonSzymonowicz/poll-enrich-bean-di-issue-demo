package com.example.pollenrichbeandiissuedemo.routes;

import com.example.pollenrichbeandiissuedemo.configuration.SftpConfigurationProperties;
import com.example.pollenrichbeandiissuedemo.filter.FilenameFilter;
import lombok.RequiredArgsConstructor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.file.remote.FtpConstants;
import org.springframework.stereotype.Component;

import static com.example.pollenrichbeandiissuedemo.util.RouteUtils.resolveHeader;
import static org.apache.camel.builder.endpoint.StaticEndpointBuilders.sftp;

@RequiredArgsConstructor
@Component
public class PollEnrichRoute extends RouteBuilder {

    private final static String ROUTE_ID = "sftp-poll-enrich-route";
    public final static String POLL_ENRICH_ROUTE = "direct:" + ROUTE_ID;

    private final SftpConfigurationProperties sftpConfig;
    private final FilenameFilter filenameFilter;

    @Override
    public void configure() throws Exception {
        from(POLL_ENRICH_ROUTE)
                .routeId(ROUTE_ID)
                .pollEnrich(sftp(sftpConfig.getConnectionUrl())
                        .username(sftpConfig.getUsername())
                        .password(sftpConfig.getPassword())
                        // binding filter by injected bean reference doesn't work
                        .filter(filenameFilter)
                        // binding filter by below hardcoded string works
//                         .filter("#filenameFilter")
                        .noop(true)
                        .idempotent(true))
                .log("Poll enrich route consumed file: " + resolveHeader(FtpConstants.FILE_NAME_ONLY))
                .process(exchange -> {log.info("Processing ...");});
    }
}
