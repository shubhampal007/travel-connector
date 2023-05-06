package com.example.MiniProject.demo.controller;

import com.example.MiniProject.demo.service.HtmlToPdfImpl;
import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

@RestController
@Log4j2
public class HtmlToPdf {
    @Autowired
    HtmlToPdfImpl htmlToPdfImpl;
    @GetMapping("/convert")
    public String convertHtmlToPdf(@RequestBody Map<String,String> requestBody ,@RequestHeader Map<String,String> httpHeaders){
        log.info("HTML to PDF Process Started for:"+ requestBody.get("email"));
        JSONObject obj=htmlToPdfImpl.doApiImplementation(requestBody,httpHeaders);
        log.info("HTML to PDF Process Ended for:"+ requestBody.get("email"));
        return obj.toString();
    }
}
