package com.travelplanner.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.travelplanner.model.domain.UserInformation;

/**
 * Servlet Class for greeting the user with an HTML response.
 * Design Pattern: View portion of MVC Architecture.
 * @author Gavin Rouleau
 */
@SuppressWarnings("serial")
public class HomeServlet extends HttpServlet {

    /**
     * Method for handling HTTP Post Requests for User Login Confirmation.
     * @param request Incoming HttpServletRequest object from web container.
     * @param response Incoming HttpServletResponse object from web container.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        HttpSession session = request.getSession();
        UserInformation userInformation = ((UserInformation) session.getAttribute("userInformation"));
        String htmlResponse = createResponse(userInformation).toString();
        writer.print(htmlResponse);
    }
    
    /**
     * Method for building an HTML response.
     * @param userInformation Incoming UserInformation object used for greeting user.
     * @return Returns a StringBuilder with HTML Response.
     */
    private StringBuilder createResponse(UserInformation userInformation) {
        
        StringBuilder htmlResponse = new StringBuilder();
        htmlResponse.append("<!DOCTYPE html><html><head>");
        htmlResponse.append("<title>Welcome</title>");
        htmlResponse.append("<style type=\"text/css\"> #page_layout { width: 800px; text-align: center } </style>");
        htmlResponse.append("</head><body><div id=\"page_layout\"><br>");
        htmlResponse.append("<h2>Login successful.</h2>");        
        htmlResponse.append("<h2>Hello " + userInformation.getFirstName() + " " + userInformation.getMiddleName());
        htmlResponse.append(" " + userInformation.getLastName() + "!</h2><p>Your address, phone, and email are: </p>");
        htmlResponse.append("<p>" + userInformation.getAddress() + "; " + userInformation.getPhone() + "; ");
        htmlResponse.append(userInformation.getEmail() + "</p></div></body></html>");        
        
        return htmlResponse;
    }
}
