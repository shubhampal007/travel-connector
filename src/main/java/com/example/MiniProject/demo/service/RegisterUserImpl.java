package com.example.MiniProject.demo.service;

import com.example.MiniProject.demo.ResponseDTO;
import com.example.MiniProject.demo.controller.LoginUserController;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.sql.Date;


import java.sql.*;
import java.util.HashMap;
import java.util.Map;

@Service
@Log4j2
public class RegisterUserImpl extends CommonServiceClass implements CommonService{



    public JSONObject doApiImplementation(Map<String,String> requestBody, Map<String,String> httpHeaders){
        Object obj=null;
        Boolean finalStatus=false;
//        ResponseDTO resp=new ResponseDTO(responseDTO);
        try {
            log.info("Request Params: ", objectMapper.writeValueAsString(requestBody));
             obj=performDbOperation(requestBody,httpHeaders);
             finalStatus=(Boolean) obj;
        }
        catch(Exception e)
        {   finalStatus=false;
            e.printStackTrace();
        }
        ResponseDTO responseDTO=new ResponseDTO();
        if(finalStatus)
        {
            responseDTO.setStatusSuccess();
        }
        else{
            responseDTO.setStatusFailure();
            responseDTO.setMessage("User Already Exist");

        }
        return responseDTO.toJsonResponse();

    }

    private Boolean performDbOperation(Map<String, String> requestBody,Map<String, String> httpHeaders){
        Object object=null;
        String query="";
        HashMap<String, String> map = new HashMap<>();

        query="Select COUNT(*) as value FROM TB_USER_MST WHERE EMAIL =?;  ";
        ResultSet resultSet;
        try (Connection conn = DriverManager.getConnection(postgresjdbcUrl, postgresjdbcUsername, postgresjdbcPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            log.info("Connected to the database!");
            stmt.setString(1, requestBody.get("email").toString());
            resultSet = stmt.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

            while (resultSet.next()) {
                for (int index = 1; index <= resultSetMetaData.getColumnCount(); index++) {
                    String columnName = resultSetMetaData.getColumnLabel(index).trim();
                    map.put(columnName,objectMapper.writeValueAsString(resultSet.getObject(columnName)));                }
            }
        }
        catch(SQLException e)
        {
            throw new RuntimeException("SQLException :"+e.getMessage());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        log.info("Database Connection Closed");
        if(Integer.parseInt(map.get("value"))==0)
        {
            object=RegisterUser(requestBody,httpHeaders);
            return true;

        }
        else {
            return false;
        }
}

    private Object RegisterUser(Map<String, String> requestBody, Map<String, String> httpHeaders) {
        String query="insert into tb_user_mst VALUES(?,?,?,?,?,?,?);";
        String date=requestBody.get("dob");

        try (Connection conn = DriverManager.getConnection(postgresjdbcUrl, postgresjdbcUsername, postgresjdbcPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            log.info("Connected to the database!");
            stmt.setString(1, requestBody.get("email"));
            stmt.setString(2, requestBody.get("password"));
            stmt.setString(3, requestBody.get("fname"));
            stmt.setString(4, requestBody.get("lname"));
            stmt.setString(5, requestBody.get("mobile"));
            stmt.setDate(6, Date.valueOf(date));
            stmt.setString(7, requestBody.get("gender"));
            int row = stmt.executeUpdate();
            log.info("Inserted row count = " + row);
    }
        catch (SQLException e)
        {
            throw new RuntimeException("SQLException:"+e);
        }
        log.info("Database Connection Closed");
        return "Data Inserted for username ="+requestBody.get("email");
}
}
