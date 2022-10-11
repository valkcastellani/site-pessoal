package com.valkcastellani.site;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = {"com.valkcastellani.site.controller"})
@Configuration
@EnableAutoConfiguration
@EnableConfigurationProperties
@EnableScheduling
@ComponentScan
public class SitePessoalApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SitePessoalApplication.class);
    }

    public static void main(String... args) {
        System.setProperty("spring.profiles.default", System.getProperty("spring.profiles.default", "dev"));
        final ApplicationContext applicationContext = SpringApplication.run(SitePessoalApplication.class, args);
    }

//    public static void main(String[] args) {
//        SpringApplication.run(SitePessoalApplication.class, args);
//    }
//
//    @Bean
//    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//        return args -> {
//
//            System.out.println("Let's inspect the beans provided by Spring Boot:");
//
//            String[] beanNames = ctx.getBeanDefinitionNames();
//            Arrays.sort(beanNames);
//            for (String beanName : beanNames) {
//                System.out.println(beanName);
//            }
//
//        };
//    }
}
