package com.valkcastellani.site;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
//@EnableScheduling
//@ComponentScan(basePackageClasses = {HomeController.class, MailController.class})
//@EnableConfigurationProperties({FileStorageProperties.class, FotoStorageProperties.class})
public class SiteApplication extends SpringBootServletInitializer {

    private static ApplicationContext APPLICATION_CONTEXT;

    public static void main(String[] args) {
        APPLICATION_CONTEXT = SpringApplication.run(SiteApplication.class, args);
    }

    public static <T> T getBean(Class<T> requiredType) {
        return APPLICATION_CONTEXT.getBean(requiredType);
    }
}
