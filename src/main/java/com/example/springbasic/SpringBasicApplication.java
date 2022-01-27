package com.example.springbasic;

import com.example.springbasic.configuration.BasicCustomProp;
import com.example.springbasic.configuration.ConfigCustomProp;
import com.example.springbasic.configuration.ConstructCustomProp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class SpringBasicApplication{

    public static void main(String[] args) {
        SpringApplication.run(SpringBasicApplication.class, args);
    }

}
