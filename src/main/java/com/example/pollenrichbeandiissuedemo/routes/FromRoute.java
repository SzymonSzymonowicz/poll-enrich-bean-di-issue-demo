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
public class FromRoute extends RouteBuilder {

    private static final String ROUTE_ID = "sftp-from-route";
    private static final int SECOND = 1000;

    private final SftpConfigurationProperties sftpConfig;
    private final FilenameFilter filenameFilter;


    @Override
    public void configure() throws Exception {
        from(sftp(sftpConfig.getConnectionUrl())
                .username(sftpConfig.getUsername())
                .password(sftpConfig.getPassword())
                .filter(filenameFilter)
                .noop(true)
                .idempotent(true)
                // delay set to don't get overwhelmed by spam of filter logs
                .delay(60 * SECOND))
            .routeId(ROUTE_ID)
            .log("From route consumed file: " + resolveHeader(FtpConstants.FILE_NAME_ONLY));
    }
}
