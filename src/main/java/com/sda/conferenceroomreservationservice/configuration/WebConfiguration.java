package com.sda.conferenceroomreservationservice.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    // The Cross-Origin Resource Sharing (CORS) is a relevant specification with the emergence of HTML5 and JS clients
    // that consume data via REST APIs.
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("*");
    }
}
