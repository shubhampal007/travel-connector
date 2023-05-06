package com.example.MiniProject.demo.service;

import com.example.MiniProject.demo.ResponseDTO;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.styledxmlparser.jsoup.nodes.Document;
import lombok.extern.log4j.Log4j2;
import org.apache.coyote.Response;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.Scanner;

@Service
@Log4j2
public class HtmlToPdfImpl extends CommonServiceClass implements CommonService{
    @Autowired
    TicketHtmlRequest ticketHtmlRequest;

    @Override
    public JSONObject doApiImplementation(Map<String, String> requestBody, Map<String, String> httpHeaders) {
//        String inputHtmlPath = "/home/decimal/Documents/ticketTemplate.html";
        String outputPdfPath = "/home/decimal/Documents/tickets/"+requestBody.get("name")+".pdf";
        ResponseDTO responseDTO=new ResponseDTO();
        boolean flag=false;
        try {
            String content=ticketHtmlRequest.getRequest(requestBody);
                String targetHtmlfile="/home/decimal/Documents/tickets/target.html";
            FileWriter file1=new FileWriter(targetHtmlfile);
            file1.write(content);
            file1.close();
            InputStream inputStream = new FileInputStream(targetHtmlfile);
            OutputStream outputStream = new FileOutputStream(outputPdfPath);
            HtmlConverter.convertToPdf(inputStream, outputStream);
//            URL url = new URL("file:///home/decimal/Documents/tickets/Shubham Pal.pdf");
//
//            // open the URL in the default browser
//            Desktop.getDesktop().browse(url.toURI());
            new File(targetHtmlfile).delete();
            flag=true;
            inputStream.close();
            outputStream.close();
        }
        catch (Exception e)
        {
            log.info("Error Creating Ticket:"+e.getMessage());
        }

        log.info("PDF generated successfully");
            if(flag)
            {
                responseDTO.setStatusSuccess();
                responseDTO.setMessage("PDF Downloaded at:"+outputPdfPath);
            }
            else{
                responseDTO.setStatusFailure();
            }

        return responseDTO.toJsonResponse();
    }
}
