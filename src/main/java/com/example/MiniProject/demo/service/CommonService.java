package com.example.MiniProject.demo.service;

import org.json.JSONObject;

import java.util.Map;

public interface CommonService {
    public JSONObject doApiImplementation(Map<String,String> requestBody, Map<String,String> httpHeaders);

    }
