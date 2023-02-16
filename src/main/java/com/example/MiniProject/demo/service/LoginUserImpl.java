package com.example.MiniProject.demo.service;

import com.example.MiniProject.demo.ResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

@Service
@Log4j2
public class LoginUserImpl extends CommonServiceClass implements CommonService {
    @Override
    public JSONObject doApiImplementation(Map<String, String> requestBody, Map<String, String> httpHeaders) {
    boolean loginFlag=authenticateLogin(requestBody);
        ResponseDTO responseDTO=new ResponseDTO();

    if(loginFlag)
    {
        responseDTO.setStatusSuccess();
        responseDTO.setMessage("Authentication Success");
        responseDTO.setResponseData(requestBody);

    }
    else{
        responseDTO.setStatusFailure();
    }

        return responseDTO.toJsonResponse();
    }

    private boolean authenticateLogin(Map<String, String> requestBody) {
        String passwordFromDb=getPasswordFromDB(requestBody);

        if(passwordFromDb.equals(requestBody.get("password")))
        {
            return true;
        }
        else{
            return false;
        }
    }

    private String getPasswordFromDB(Map<String, String> requestBody) {
        String query="Select userpass from tb_user_mst where email=?";
        HashMap<String, String> map = new HashMap<>();
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
                    map.put(columnName,resultSet.getObject(columnName).toString());                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (map.get("userpass")==null)?"":map.get("userpass");
    }
}
