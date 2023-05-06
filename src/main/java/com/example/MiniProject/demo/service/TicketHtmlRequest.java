package com.example.MiniProject.demo.service;


import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class TicketHtmlRequest {
    public String getRequest(Map<String,String> map)
    {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Document</title>\n" +
                "    <style>\n" +
                "        #ticket-all{\n" +
                "            border: 1px solid gray;\n" +
                "                margin-left: auto;\n" +
                "                margin-right: auto;\n" +
                "                width: 90%;\n" +
                "                background-color: #dbdbdb;\n" +
                "                text-align: left;\n" +
                "                padding: 8px;\n" +
                "        }\n" +
                "        .ticket-head{\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div id=\"ticket-all\">\n" +
                "        <div id=\"ticket-head\">\n" +
                "    <h2>Trip to "+map.get("place")+" </h2>\n" +
                "</div>\n" +
                "<div id=\"ticket-body\">\n" +
                "    <h3>Traveller Detail</h3>\n" +
                "    <h4>Name:</h4>\n" +
                "    <p>"+map.get("name")+"</p>\n" +
                "    <h4>Age</h4>\n" +
                "    <p>"+map.get("age")+"</p>\n" +
                "    <h4>email</h4>\n" +
                "    <p>"+map.get("email")+"</p>\n" +
                "    <h4>From:</h4>\n" +
                "    <p>"+map.get("from")+"</p>\n" +
                "    <h4>To:</h4>\n" +
                "    <p>"+map.get("place")+"</p>\n" +
                "    <h4>Departure</h4>\n" +
                "    <p>"+map.get("departureTime")+"</p>\n" +
                "    <h4>Arrival</h4>\n" +
                "    <p>"+map.get("arrivalTime")+"</p>\n" +
                "</div>\n" +
                "</div>\n" +
                "\n" +
                "\n" +
                "</body>\n" +
                "</html>";
    }
}
