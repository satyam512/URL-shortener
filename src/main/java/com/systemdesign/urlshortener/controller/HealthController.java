package com.systemdesign.urlshortener.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HealthController {

    @Value("${server.name}")
    private String serverName;


    @GetMapping("/health")
    private String healthCheck() {
        return serverName + " is alive";
    }
}
