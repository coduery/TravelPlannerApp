package com.travelplanner.model.service.travelplansvc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.postgresql.ds.PGConnectionPoolDataSource;

import com.travelplanner.model.domain.TravelPlan;
import com.travelplanner.model.domain.TravelPlannerAppUser;

public class TravelPlanSvcImpl implements ITravelPlanSvc {

    /** Field to store a connection for connecting to the database. */
    private Connection connection = null;
    
    /** Field to store a prepared statement for database execution. */    
    private PreparedStatement prepStatement = null;
    
    /** Field to store the SQL command to execute with the database. */ 
    private String sqlCommand;
    
	/**Method abstraction to store a Travel Plan object.
	 * @param user TravelPlannerAppUser is specified by incoming user parameter.
	 * @param plan TravelPlan is specified by incoming user parameter.
     * @return Returns boolean value indicating whether operation was successful or not.
	 */
	public boolean storeTravelPlan(TravelPlannerAppUser user, TravelPlan plan) {
		
        boolean isSuccessful = false;
        
        try
        {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            PGConnectionPoolDataSource connectionPool = (PGConnectionPoolDataSource) envContext.lookup("jdbc/postgresqlconnector");
            
            connection = connectionPool.getConnection();
            
            if(connection != null && user != null && plan != null){
                
                int userId = getUserId(user.getUsername());
                sqlCommand = "INSERT INTO travel_plans(trav_plan_name, trav_plan_description, " +
                             "trav_plan_details, user_id) VALUES (?, ?, ?, ?)";
                prepStatement = connection.prepareStatement(sqlCommand);
                prepStatement.setString(1, plan.getTravelPlanName());
                prepStatement.setString(2, plan.getTravelPlanDesc().toString());
                prepStatement.setString(3, plan.getTravelPlanDetails().toString());
                prepStatement.setInt(4, userId);            
                prepStatement.executeUpdate();
                
                isSuccessful = true;
            }
            else if (connection == null)
            {
                System.err.println("TravelPlanSvcImpl::storeTravelPlan Connection is Null");
            }
            else if (user == null)
            {
                System.err.println("TravelPlanSvcImpl::storeTravelPlan User is Null");
            }
            else if (plan == null)
            {
                System.err.println("TravelPlanSvcImpl::storeTravelPlan Plan is Null");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(prepStatement != null){
                try {
                    prepStatement.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } 
        
        return isSuccessful; 
	}
	
	/**Method abstraction to get a Travel Plan object.
	 * @param uName TravelPlannerAppUser username is specified by incoming uName parameter.
	 * @param tName Travel Plan name is specified by incoming uName parameter.
	 * @return Returns a Travel Plan.
	 */
	public TravelPlan getTravelPlan(String uName, String tName) {
        
	    // TODO:  Add implementation for future use case
        
        //return plan;
	    return new TravelPlan();
	}
	
	
    /**Method for getting a User Id from the database
     * @param username Parameter that specifies username to receive the User ID for.
     * @return Returns an int value representing a User Id.
     */
    private int getUserId(String username){
        
        int userId = 0;
        
        sqlCommand = "SELECT user_id FROM app_users " +
                     "WHERE username = ?";
        try {
            prepStatement = connection.prepareStatement(sqlCommand);
            prepStatement.setString(1, username);
            ResultSet resultSet = prepStatement.executeQuery();
            while(resultSet.next()) {
                userId = resultSet.getInt("user_id");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return userId;
    }
}
