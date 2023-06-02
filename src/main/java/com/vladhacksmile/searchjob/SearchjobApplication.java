package com.vladhacksmile.searchjob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SearchjobApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearchjobApplication.class, args);
    }

}
