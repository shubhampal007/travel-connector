package com.example.MiniProject.demo.controller;

import com.example.MiniProject.demo.CommonController;
import com.example.MiniProject.demo.service.RegisterUserImpl;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@Log4j2
public class RegisterUserController extends CommonController {

    @Autowired
    private RegisterUserImpl registerUserImpl;

    @PostMapping(value = "/registerUser",produces = "application/json")
    public Object registerUser(@RequestBody Map<String,String> requestBody, @RequestHeader Map<String,String> httpHeaders)
    {   JsonNode jsonNode=null;
        log.info("Started Register User");
        Object obj=registerUserImpl.doApiImplementation(requestBody,httpHeaders);
        log.info("Result:"+obj);
        HashMap<String,String> map=new HashMap<>();
        map.put("statusCode","SUCCESS");
        try {
            jsonNode = objectMapper.readTree(objectMapper.writeValueAsString(map));
        }catch (Exception e)
        {
            log.error("Error Message:",e);
        }
        return jsonNode;
    }
}
