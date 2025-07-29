package com.example.pollenrichbeandiissuedemo.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.component.file.GenericFile;
import org.apache.camel.component.file.GenericFileFilter;
import org.apache.camel.component.file.remote.SftpRemoteFile;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FilenameFilter implements GenericFileFilter<SftpRemoteFile> {

    @Override
    public boolean accept(GenericFile<SftpRemoteFile> file) {
        String fileName = file.getFileName();
        // in my real use case below condition is more complex
        boolean accepted = "test.txt".equals(fileName);
        log.info("Filter called for file: {}, accepted: {}", fileName, accepted);

        return accepted;
    }

}
