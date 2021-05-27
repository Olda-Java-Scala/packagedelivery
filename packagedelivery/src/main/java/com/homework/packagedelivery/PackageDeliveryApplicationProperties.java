package com.homework.packagedelivery;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
@Data
public class PackageDeliveryApplicationProperties {

    private String welcome;

    private String enterText;

    private String lineFormatInstructions;

    private String lineExample;

    private String quitAppText;

    private String invalidLineText;

    private String outputFromStorage;

    private String quitAppCommand;

    private String rulesLinePattern;
}
