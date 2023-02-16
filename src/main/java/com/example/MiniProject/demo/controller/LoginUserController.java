package com.example.MiniProject.demo.controller;

import com.example.MiniProject.demo.CommonController;
import com.example.MiniProject.demo.service.LoginUserImpl;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Log4j2
public class LoginUserController extends CommonController {

    @Autowired
    LoginUserImpl loginUserImpl;

    @PostMapping(value = "/loginUser",produces = "application/json")
    public String userLogin(@RequestBody Map<String,String> requestBody, @RequestHeader Map<String,String> httpHeaders)
    {
        log.info("Login Process Started for:"+ requestBody.get("email"));
        JSONObject obj=loginUserImpl.doApiImplementation(requestBody,httpHeaders);
        log.info("Login Process Ended for:"+ requestBody.get("email"));
            return obj.toString();

    }
}
