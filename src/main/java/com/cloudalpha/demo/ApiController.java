package com.cloudalpha.demo;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    private final String version;

    public ApiController(@Value("${app.version:1.0.0}") String version) {
        this.version = version;
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "ok", "version", version);
    }

    @GetMapping("/api/greeting")
    public Map<String, String> greeting(@RequestParam(defaultValue = "world") String name) {
        String safeName = name.length() > 50 ? name.substring(0, 50) : name;
        return Map.of("message", "Hello, " + safeName + "!");
    }
}
