package com.travelplanner.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.travelplanner.model.business.TravelPlanManager;
import com.travelplanner.model.domain.TravelPlan;
import com.travelplanner.model.domain.TravelPlannerAppUser;

/**
 * Servlet Class for controlling Travel Planner Application, Travel Plan creation.
 * Design Pattern: Controller portion of MVC Architecture
 * @author Gavin Rouleau
 */
@SuppressWarnings("serial")
public class TravelPlanController extends HttpServlet {

    /**
     * Method for handling HTTP Get Requests for creating a user's travel plan.
     * @param request Incoming HttpServletRequest object from web container.
     * @param response Incoming HttpServletResponse object from web container.
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/create_plan.jsp");
            dispatcher.forward(request, response);
        }
        else {
            response.sendRedirect("login.jsp");
        }
    }
    
    /**
     * Method for handling HTTP Post Requests for creating a user's travel plan.
     * @param request Incoming HttpServletRequest object from web container.
     * @param response Incoming HttpServletResponse object from web container.
     * @throws ServletException
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        TravelPlannerAppUser user = (TravelPlannerAppUser) session.getAttribute("user");
        
        TravelPlan plan = new TravelPlan();
        plan.setTravelPlanName(request.getParameter("plan_name"));
        plan.setTravelPlanDesc(request.getParameter("plan_description"));
        plan.setTravelPlanDetails(request.getParameter("travel_plan_details"));
        plan.setUser(user);
        
        RequestDispatcher dispatcher = null;
        
        if(plan.validate()){
            
            List<TravelPlan> tempAllTravelPlans = null;
            if (user.getAllTravelPlans() != null){
                tempAllTravelPlans = user.getAllTravelPlans();
            }
            else
            {
                tempAllTravelPlans = new ArrayList<TravelPlan>();
            }
            tempAllTravelPlans.add(plan);
            user.setAllTravelPlans(tempAllTravelPlans);

            TravelPlanManager travelPlanManager = new TravelPlanManager();
            boolean isSuccessful = travelPlanManager.performAction("CreatePlan", user, plan);
            
            if(isSuccessful == true){
                
                session.setAttribute("confirmation", "plan_saved");
                session.setAttribute("plan", plan);
                dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/success.jsp");
            }
            else {
                
                
                String[] errorMessages = { "Error!",
                                           "Travel Plan \"" + plan.getTravelPlanName() + "\" Not Saved.", 
                                           "Try Saving Plan Again!" };
                
                request.setAttribute("error", errorMessages);
                request.setAttribute("action", "create_plan");
                dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp");
            }
        }
        else
        {
            String[] errorMessages = { "Error!",
                                       "Travel Plan Creation Failed.", 
                                       "Travel Plan Name and Description are Required." };
            
            request.setAttribute("error", errorMessages);
            request.setAttribute("action", "create_plan");
            dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp");
        }
        
        dispatcher.forward(request, response);
    }
}
