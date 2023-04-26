package com.example.MiniProject.demo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Component
public class EmailRequest {
    @JsonProperty("to")
    private String to;
    @JsonProperty("subject")
    private String subject;
    @JsonProperty("body")
    private String body;
}
