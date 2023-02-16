package com.example.MiniProject.demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class CommonServiceClass {
    @Autowired
    ObjectMapper objectMapper;
    @Value("${postgres.jdbc.url}")
     String postgresjdbcUrl;
    @Value("${postgres.jdbc.username}")
     String postgresjdbcUsername;
    @Value("${postgres.jdbc.password}")
     String postgresjdbcPassword;
}
