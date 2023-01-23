package com.example.MiniProject.demo;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import org.springframework.stereotype.Component;

@Component
public class ResponseDTO {
    private String status;
    @JsonProperty("response_message")
    private String message;

    @JsonIgnore
    private String apiName;
    @JsonProperty("response_code")
    private String errorCode;
    @JsonProperty("responseData")
    private Object responseData;
    @JsonProperty("response_type")
    private String responseType;
    private String errorDetails;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Object getResponseData() {
        return responseData;
    }

    public void setResponseData(Object responseData) {
        this.responseData = responseData;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public String getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(String errorDetails) {
        this.errorDetails = errorDetails;
    }

//    public ResponseDTO(ResponseDTO responseDto) {
//        String responseType = responseDto.getStatus();
//
//        if (responseType != null) {
//            this.status = responseDto.getStatus();
//            this.responseType = responseType.equalsIgnoreCase("SUCCESS") ? "I" : "E";
//        } else {
//            this.status = "FAILURE";
//            this.responseType = "E";
//        }
//        this.message = responseDto.getMessage();
//        // this.apiName = responseDto.getApiName();
//        this.errorCode = responseDto.getErrorCode();
//        this.responseData = responseDto.getResponseData();
//        this.errorDetails = responseDto.getErrorDetails();
//    }

}
