package com.travelplanner.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.travelplanner.model.business.FavoriteListManager;
import com.travelplanner.model.domain.FavoriteList;
import com.travelplanner.model.domain.TravelPlannerAppUser;

/**
 * Servlet Class for controlling Travel Planner Application User's Favorite List creation.
 * Design Pattern: Controller portion of MVC Architecture
 * @author Gavin Rouleau
 */
@SuppressWarnings("serial")
public class FavoriteListController extends HttpServlet {

    /**
     * Method for handling HTTP Get Requests for creating a user's favorite list.
     * @param request Incoming HttpServletRequest object from web container.
     * @param response Incoming HttpServletResponse object from web container.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/create_list.jsp");
            dispatcher.forward(request, response);
        }
        else {
            response.sendRedirect("login.jsp");
        }
    }
    
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
        
        HttpSession session = request.getSession();
        TravelPlannerAppUser user = (TravelPlannerAppUser) session.getAttribute("user");
        
        List<FavoriteList> tempAllFavLists;
        String[] allFavListNames = null; 
        
        if (user.getAllFavLists() != null) {
            tempAllFavLists = user.getAllFavLists();
            user.setAllFavListNames(tempAllFavLists);
            allFavListNames = user.getAllFavListNames();
        }
        else {
            tempAllFavLists = new ArrayList<FavoriteList>();
            allFavListNames = new String[1];
            allFavListNames[0] = "";
        }
        
        RequestDispatcher dispatcher = null;
        
        if (!Arrays.asList(allFavListNames).contains(favoriteList.getNameFavList())){
            
            if (favoriteList.validate()){
                
                tempAllFavLists.add(favoriteList);
                user.setAllFavLists(tempAllFavLists);
                
                FavoriteListManager listManager = new FavoriteListManager();
                boolean isActionSuccessful = listManager.performAction("CreateList", user, favoriteList);
                
                if (isActionSuccessful == true){
                    
                    session.setAttribute("confirmation", "list_saved");
                    session.setAttribute("favoriteList", favoriteList);
                    dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/success.jsp");
                }
                else
                {
                    String[] errorMessages = { "Error!",
                                               "Favorite List \"" + favoriteList.getNameFavList() + "\" Not Saved.", 
                                               "Try Saving List Again!" };
                    request.setAttribute("error", errorMessages);
                    request.setAttribute("action", "create_list");
                    
                    dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp");
                }
            }
            else
            {
                String[] errorMessages = { "Error!",
                                           "Favorite List Creation Failed.", 
                                           "Favorite List Name and Type are Required." };
                
                request.setAttribute("error", errorMessages);
                request.setAttribute("action", "create_list");
                dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp");
            }
        }
        else
        {
            String[] errorMessages = { "Error!",
                                       "Favorite List Creation Failed.", 
                                       "Favorite List Name \"" + favoriteList.getNameFavList() + "\" Already Exists." };
            
            request.setAttribute("error", errorMessages);
            request.setAttribute("action", "create_list");
            dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp");
        } 
        
        dispatcher.forward(request, response);
    }
}
