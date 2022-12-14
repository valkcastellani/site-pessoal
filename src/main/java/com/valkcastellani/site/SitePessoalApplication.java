package com.valkcastellani.site;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class SitePessoalApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SitePessoalApplication.class, args);
    }
}
