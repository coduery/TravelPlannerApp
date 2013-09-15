package com.travelplanner.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.travelplanner.model.business.FavoriteListManager;
import com.travelplanner.model.domain.FavoriteList;

/**
 * Servlet Class for controlling Travel Planner Application User's Favorite List creation.
 * Design Pattern: Controller portion of MVC Architecture
 * @author Gavin Rouleau
 */
@SuppressWarnings("serial")
public class FavoriteListController extends HttpServlet {

    /**
     * Method for handling HTTP Post Requests for creating a user's favorite list.
     * @param request Incoming HttpServletRequest object from web container.
     * @param response Incoming HttpServletResponse object from web container.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        FavoriteList favoriteList = new FavoriteList();
        favoriteList.setNameFavList(request.getParameter("list_name"));
        favoriteList.setFavListType(request.getParameter("list_type"));
        
        RequestDispatcher dispatcher = null;
        FavoriteListManager listManager = new FavoriteListManager();
        boolean isValidList = listManager.validate(favoriteList);

        if (isValidList) {
            HttpSession session = request.getSession();
            session.setAttribute("favoriteList", favoriteList);
            dispatcher = getServletContext().getRequestDispatcher("/list_created");
        }
        else {
            String[] errorMessages = { "Error: Favorite list creation failed!", "Favorite list name is required." };
            request.setAttribute("error", errorMessages);
            dispatcher = getServletContext().getRequestDispatcher("/error");
        }
        
        dispatcher.forward(request, response);
    }
}
