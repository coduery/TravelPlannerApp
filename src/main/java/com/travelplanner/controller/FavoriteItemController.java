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
//import com.travelplanner.model.business.LoginManager; TODO:  Not sure if this is needed see below
import com.travelplanner.model.domain.FavoriteItem;
import com.travelplanner.model.domain.FavoriteList;
import com.travelplanner.model.domain.TravelPlannerAppUser;

/**
 * Servlet Class for controlling Travel Planner Application User's Favorite Item creation.
 * Design Pattern: Controller portion of MVC Architecture
 * @author Gavin Rouleau
 */
@SuppressWarnings("serial")
public class FavoriteItemController extends HttpServlet {

    /**
     * Method for handling HTTP Get Requests for creating a user's favorite item.
     * @param request Incoming HttpServletRequest object from web container.
     * @param response Incoming HttpServletResponse object from web container.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/add_item.jsp");
            dispatcher.forward(request, response);
        }
        else {
            response.sendRedirect("login.jsp");
        }
    }
    
    /**
     * Method for handling HTTP Post Requests for creating a user's favorite item.
     * @param request Incoming HttpServletRequest object from web container.
     * @param response Incoming HttpServletResponse object from web container.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        FavoriteItem item = new FavoriteItem();
        item.setNameFavItem(request.getParameter("item_name"));
        if (request.getParameter("list_type") != null  && !request.getParameter("list_type").isEmpty()){
            item.setFavItemType(request.getParameter("list_type"));
        }
        item.setLocationDescript(request.getParameter("location"));
        item.setAddress(request.getParameter("address"));
        item.setCity(request.getParameter("city"));
        item.setStateProvince(request.getParameter("state"));
        item.setCountry(request.getParameter("country"));
        item.setZip(request.getParameter("zip"));
        item.setPhone(request.getParameter("phone"));
        item.setNotes(request.getParameter("notes"));

        HttpSession session = request.getSession();
        TravelPlannerAppUser user = (TravelPlannerAppUser) session.getAttribute("user");
        
        FavoriteList list = null;
        String[] itemNames;
        
        FavoriteListManager listManager = null;
        
        if (item.getFavItemType() != null && !item.getFavItemType().isEmpty()){
            
            listManager = new FavoriteListManager();
            list = listManager.retrieveList(user.getUsername(), item.getFavItemType());
            
            if (list.getFavList().size() > 0){
                itemNames = new String[list.getFavList().size()];
                int i = 0;
                for (FavoriteItem favItem : list.getFavList()){
                    itemNames[i] = favItem.getNameFavItem();
                    i++;
                }
            }
            else
            {
                itemNames = new String[1];
                itemNames[0] = null;
            }
        }
        else
        {
            itemNames = new String[1];
            itemNames[0] = null;
        }
        
        RequestDispatcher dispatcher = null;
        
        if (!Arrays.asList(itemNames).contains(item.getNameFavItem())){
            
            if (item.getFavItemType() != null && !item.getFavItemType().isEmpty() && item.validate()){

                List<FavoriteItem> tempFavList;
                
                if (list.getFavList() != null){
                    tempFavList = list.getFavList();
                }
                else
                {
                    tempFavList = new ArrayList<FavoriteItem>();
                }
                
                tempFavList.add(item);
                list.setFavList(tempFavList);

                boolean isSuccessful = listManager.performAction("CreateList", user, list);
                
                if(isSuccessful == true){
                    
                    session.setAttribute("confirmation", "item_saved");
                    session.setAttribute("favoriteItem", item);
                    dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/success.jsp");

                    // TODO:  Determine whether below code is still needed for other use cases, and needs to be modified?
                    /*LoginManager loginManager = new LoginManager();
                    TravelPlannerAppUser tempUser = loginManager.retrieveUser(user);
                    user.setAllFavLists(tempUser.getAllFavLists());
                    appItemAddition.getUser().setAllFavListNames(user.getAllFavLists());
                    appItemAddition.getApp().formatTextArea(user);
                    appItemAddition.getApp().updateTravelPlannerApp();
                    restoreMainWindow(); */
                }
                else
                {
                    String[] errorMessages = { "Error!",
                                               "Favorite Item \"" + item.getNameFavItem() + "\" Not Saved.", 
                                               "Try Saving Item Again!" };
                    
                    request.setAttribute("error", errorMessages);
                    request.setAttribute("action", "add_item");
                    dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp");
                }                
            }
            else
            {
                String[] errorMessages = { "Error!",
                                           "Favorite Item Creation Failed.", 
                                           "Favorite Item Name and Type are Required." };
                
                request.setAttribute("error", errorMessages);
                request.setAttribute("action", "add_item");
                dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp");
            }
        }
        else
        {
            String[] errorMessages = { "Error!",
                                       "Favorite List Creation Failed.", 
                                       "Favorite List Name \"" + item.getNameFavItem() + "\" Already Exists." };
            
            request.setAttribute("error", errorMessages);
            request.setAttribute("action", "add_item");
            dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp");
        }
        
        dispatcher.forward(request, response);
    }
}
