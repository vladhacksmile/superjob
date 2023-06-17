package com.vladhacksmile.searchjob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableKafka
@SpringBootApplication
public class SearchjobApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearchjobApplication.class, args);
    }

}
