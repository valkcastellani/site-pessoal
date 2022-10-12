package com.valkcastellani.site;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
//(scanBasePackages = {"com.valkcastellani.site.controller"})
//@Configuration
@EnableAutoConfiguration
//@EnableConfigurationProperties
//@EnableScheduling
//@ComponentScan
public class SitePessoalApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SitePessoalApplication.class, args);
    }

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
