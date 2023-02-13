package com.example.MiniProject.demo;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.JsonNode;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
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

    private String errorDetails;

    public ResponseDTO() {

    }

    public ResponseDTO(ResponseDTO responseDto) {
        String responseType=responseDto.getStatus();

        if(responseType!=null) {
            this.status = responseDto.getStatus();
        }else
        {
            this.status = "FAILURE";
        }
        this.message = responseDto.getMessage();
        this.errorCode = responseDto.getErrorCode();
        this.responseData = responseDto.getResponseData();
        this.errorDetails=responseDto.getErrorDetails();

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public void setStatusSuccess() {
        this.status = "SUCCESS";
    }

    public void setStatusFailure() {
        this.status = "FAILURE";
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

    public String getErrorDetails() {
        return errorDetails;
    }

    public void setErrorDetails(String errorDetails) {
        this.errorDetails = errorDetails;
    }

    public JSONObject toJsonResponse()
    {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("response_status",this.status);
        jsonObject.put("response_message",(!(this.message==null))?this.message:"");
        jsonObject.put("response_code",(!(this.errorCode==null))?this.errorCode:"");
        jsonObject.put("responseData",(!(this.responseData==null))?this.responseData:"");
        return jsonObject;


    }
}
