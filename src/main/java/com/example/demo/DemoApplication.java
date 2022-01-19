package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.example.demo.coap.CaliforniumServerResource;
import com.example.demo.coap.CaliforniumServer;

@SpringBootApplication
public class DemoApplication {

    @Autowired
    CaliforniumServerResource resource;

    @Autowired
    CaliforniumServer server;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CaliforniumServerResource getServeresource() {
        return new CaliforniumServerResource("hello");
    }

    @Bean
    CaliforniumServer getServer() {
        server = new CaliforniumServer();
        server.addEndpoints();
        server.add(resource);
        server.start();
        return server;
    }
}
