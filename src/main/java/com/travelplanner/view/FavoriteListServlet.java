package com.travelplanner.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.travelplanner.model.domain.FavoriteList;

/**
 * Servlet Class for displaying favorite listed created with an HTML response.
 * Design Pattern: View portion of MVC Architecture.
 * @author Gavin Rouleau
 */
@SuppressWarnings("serial")
public class FavoriteListServlet extends HttpServlet {

    /**
     * Method for handling HTTP Post Requests for creating a user's favorite list.
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
        FavoriteList favoriteList = (FavoriteList) session.getAttribute("favoriteList");
        String htmlResponse = createResponse(favoriteList).toString();
        writer.print(htmlResponse);
    }
    
    /**
     * Method for building an HTML response.
     * @param favoriteList Incoming FavoriteList for which to create HTML Response. 
     * @return Returns a StringBuilder with HTML Response.
     */
    private StringBuilder createResponse(FavoriteList favoriteList) {
        
        StringBuilder htmlResponse = new StringBuilder();
        htmlResponse.append("<!DOCTYPE html><html><head>");
        htmlResponse.append("<title>Create Favorite List</title>");
        htmlResponse.append("<style> #page_layout { width: 800px; text-align: center } </style>");
        htmlResponse.append("</head><body><div id=\"page_layout\"><br><h2>" + "Favorite List Created" + "</h2><br>");
        htmlResponse.append("<p>" + "Favorite List Name: " + favoriteList.getNameFavList() + "</p>");
        htmlResponse.append("<p>" + "Favorite List Type: " + favoriteList.getFavListType() + "</p>");
        htmlResponse.append("</div></body></html>");
        
        return htmlResponse;
    }
}
