package com.travelplanner.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Class for displaying an error to the user with an HTML response.
 * Design Pattern: View portion of MVC Architecture.
 * @author Gavin Rouleau
 */
@SuppressWarnings("serial")
public class ErrorServlet extends HttpServlet {

    /**
     * Method for handling HTTP Post Requests for user entry errors.
     * @param request Incoming HttpServletRequest object from web container.
     * @param response Incoming HttpServletResponse object from web container.
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        String htmlResponse = createResponse(request).toString();
        writer.print(htmlResponse);
    }
    
    /**
     * Method for building an HTML response.
     * @param request Incoming HttpServletRequest object from web container.
     * @return Returns a StringBuilder with HTML Response.
     */
    private StringBuilder createResponse(HttpServletRequest request) {
        
        String[] errorMessages = (String[]) request.getAttribute("error");
        
        StringBuilder htmlResponse = new StringBuilder();
        htmlResponse.append("<!DOCTYPE html><html><head>");
        htmlResponse.append("<title>Error Page</title>");
        htmlResponse.append("<style type=\"text/css\"> #page_layout { width: 800px; text-align: center } </style>");
        htmlResponse.append("</head><body><div id=\"page_layout\"><br><h2>");
        htmlResponse.append(errorMessages[0] + "</h2><h2>");
        htmlResponse.append(errorMessages[1] + "</h2>");
        htmlResponse.append("</h2></p></div></body></html>");        
        
        return htmlResponse;
    }
}
