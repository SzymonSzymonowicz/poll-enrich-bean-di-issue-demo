package com.example.pollenrichbeandiissuedemo.configuration;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Value
@ConfigurationProperties(prefix = "sftp.server")
public class SftpConfigurationProperties {

    String host;
    Integer port;
    String username;
    String password;
    String path;

    public String getConnectionUrl() {
        return host + ":" + port + "/" + path;
    }

}
