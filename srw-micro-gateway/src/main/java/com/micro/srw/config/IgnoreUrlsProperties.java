package com.micro.srw.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties("secure.ignore")
public class IgnoreUrlsProperties {
    private List<String> urls;
}
