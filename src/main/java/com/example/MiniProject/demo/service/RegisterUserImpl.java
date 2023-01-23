package com.example.MiniProject.demo.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.sql.Date;


import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Map;

@Service
@Log4j2
public class RegisterUserImpl {
    @Autowired
    ObjectMapper objectMapper;
    @Value("${postgres.jdbc.url}")
    private String postgresjdbcUrl;
    @Value("${postgres.jdbc.username}")
    private String postgresjdbcUsername;
    @Value("${postgres.jdbc.password}")
    private String postgresjdbcPassword;

    public Object doApiImplementation(Map<String,String> requestBody, Map<String,String> httpHeaders){
        Object obj=null;
//        ResponseDTO resp=new ResponseDTO(responseDTO);
        try {
            log.info("Request Params: ", objectMapper.writeValueAsString(requestBody));
             obj=performDbOperation(requestBody,httpHeaders);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return obj;

    }

    private Object performDbOperation(Map<String, String> requestBody,Map<String, String> httpHeaders){
        Object object=null;
        String query="";

        query="Select COUNT(1) FROM TB_USER_MST WHERE EMAIL =?";
        int count=0;
        ResultSet resultSet;
        try (Connection conn = DriverManager.getConnection(postgresjdbcUrl, postgresjdbcUsername, postgresjdbcPassword);
             PreparedStatement stmt = conn.prepareStatement(query)) {
            log.info("Connected to the database!");
            stmt.setString(1, requestBody.get("email").toString());
            resultSet = stmt.executeQuery();
//          resultSet.next();
           count= resultSet.getRow();
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
        if(count==0)
        {
            object=RegisterUser(requestBody,httpHeaders);
        }
        else
        {
            object="Record Already Exist for username:"+requestBody.get("email");
        }

        return object;

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
