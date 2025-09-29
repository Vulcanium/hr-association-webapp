package com.vulcanium.hrassociationv2.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "com.vulcanium.hrassociationv2")
public class CustomProperties {

    private String apiUrl;
}