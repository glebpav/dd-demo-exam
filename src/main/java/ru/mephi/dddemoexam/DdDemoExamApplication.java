package ru.mephi.dddemoexam;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class DdDemoExamApplication {

    public static void main(String[] args) {
        log.info("Starting DdDemoExamApplication");
        SpringApplication.run(DdDemoExamApplication.class, args);
    }

}
