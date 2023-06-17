package com.vladhacksmile.searchjob;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableScheduling
@EnableProcessApplication
public class SearchjobApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearchjobApplication.class, args);
    }

}
