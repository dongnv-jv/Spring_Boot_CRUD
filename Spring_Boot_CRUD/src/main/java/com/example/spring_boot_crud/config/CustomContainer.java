package com.example.spring_boot_crud.config;

import org.apache.tomcat.util.http.LegacyCookieProcessor;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomContainer {


    @Bean
    public WebServerFactoryCustomizer customizer() {
        return container -> {
            if (container instanceof TomcatServletWebServerFactory) {
                TomcatServletWebServerFactory tomcat = (TomcatServletWebServerFactory) container;
                tomcat.addContextCustomizers(context -> context.setCookieProcessor(new LegacyCookieProcessor()));
            }
        };
    }
}
