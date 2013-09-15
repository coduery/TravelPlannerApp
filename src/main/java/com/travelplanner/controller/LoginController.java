package com.travelplanner.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.travelplanner.model.business.LoginManager;
import com.travelplanner.model.domain.TravelPlannerAppUser;
import com.travelplanner.model.domain.UserInformation;

/**
 * Servlet Class for controlling Travel Planner Application User authentication.
 * Design Pattern: Controller portion of MVC Architecture
 * @author Gavin Rouleau
 */
@SuppressWarnings("serial")
public class LoginController extends HttpServlet {

    /**
     * Method for handling HTTP Post Requests for User Login.
     * @param request Incoming HttpServletRequest object from web container.
     * @param response Incoming HttpServletResponse object from web container.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        TravelPlannerAppUser user = new TravelPlannerAppUser();
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
               
        RequestDispatcher dispatcher = null;
        LoginManager loginManager = new LoginManager();
        UserInformation userInformation = loginManager.authenticate(user);
        
        if (userInformation != null) {
            HttpSession session = request.getSession();
            session.setAttribute("userInformation", userInformation);
            dispatcher = getServletContext().getRequestDispatcher("/home");
        }
        else {
            String[] errorMessages = { "Error: Login failed!", "User credentials are not valid." };
            request.setAttribute("error", errorMessages);
            dispatcher = getServletContext().getRequestDispatcher("/error");
        }

        dispatcher.forward(request, response);
    }
}
