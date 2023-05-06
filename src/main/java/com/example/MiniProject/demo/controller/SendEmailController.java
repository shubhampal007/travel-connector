package com.example.MiniProject.demo.controller;

import com.example.MiniProject.demo.CommonController;
import com.example.MiniProject.demo.EmailRequestdto;
import com.example.MiniProject.demo.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("email")
public class SendEmailController extends CommonController {

    @Autowired
    private MailService mailService;

    @PostMapping("/sendEmail")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequestdto request) {
        String to = request.getTo();
        String subject = request.getSubject();
        String body = request.getBody();
        mailService.sendEmail(to, subject, body);
        return ResponseEntity.ok("Email sent successfully");
    }
}
