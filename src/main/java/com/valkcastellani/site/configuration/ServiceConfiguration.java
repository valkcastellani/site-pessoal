package com.valkcastellani.site.configuration;

import com.valkcastellani.site.rest.mail.service.JavaMailService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Valk Castellani
 * @since 1.0
 * @date 2021-10-16
 * @version 1.0
 */
@Configuration
@ComponentScan(basePackageClasses = JavaMailService.class)
public class ServiceConfiguration {

}
