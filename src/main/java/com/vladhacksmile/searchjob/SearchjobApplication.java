package com.vladhacksmile.searchjob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SearchjobApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearchjobApplication.class, args);
    }

}
