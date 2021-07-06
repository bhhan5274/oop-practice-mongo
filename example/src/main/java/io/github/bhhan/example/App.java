package io.github.bhhan.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = App.DEFAULT_BASE_PACKAGE)
public class App {
    static final String DEFAULT_BASE_PACKAGE = "io.github.bhhan.example";

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
