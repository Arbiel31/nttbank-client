package com.nttdata.nttbankclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class NttbankClientApplication {
  public static void main(String[] args) {
    SpringApplication.run(NttbankClientApplication.class, args);
  }

}
