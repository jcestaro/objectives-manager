package com.github.jcestaro.objectivesmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ObjectivesManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ObjectivesManagerApplication.class, args);
    }

}
