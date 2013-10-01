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
     * Method for handling HTTP Get Requests for User Login.
     * @param request Incoming HttpServletRequest object from web container.
     * @param response Incoming HttpServletResponse object from web container.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/welcome.jsp");
            dispatcher.forward(request, response);
        }
        else {
            response.sendRedirect("login.jsp");
        }
    }
    
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
        
        if (user.getUsername() != null && !user.getUsername().isEmpty() ) {
            user = loginManager.authenticate(user);
        }
        else {
            user = null;
        }
        
        UserInformation userInformation = null;
        
        if (user != null) {
            userInformation = user.getUserInformation();
        }
        
        if (userInformation != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("userInformation", userInformation);
            dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/welcome.jsp");
        }
        else {
            String[] errorMessages = { "Error!",
                                       "Login Failed.",
                                       "User credentials are not valid." };
            
            request.setAttribute("error", errorMessages);
            request.setAttribute("action", "welcome");
            dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp");
        }

        dispatcher.forward(request, response);
    }
}
