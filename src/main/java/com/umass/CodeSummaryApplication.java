package com.umass;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com"})
@MapperScan("com.umass.cs520.mapper")

public class CodeSummaryApplication {

  public static void main(String[] args) {
    SpringApplication.run(CodeSummaryApplication.class, args);
  }
}
