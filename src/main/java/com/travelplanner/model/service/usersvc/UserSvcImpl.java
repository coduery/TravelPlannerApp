package com.travelplanner.model.service.usersvc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.postgresql.ds.PGConnectionPoolDataSource;

import com.travelplanner.model.domain.FavoriteList;
import com.travelplanner.model.domain.TravelPlannerAppUser;
import com.travelplanner.model.domain.UserInformation;

/**Implementation of Interface to define an User Service for Travel Planner Application.
 * Design Pattern: Plugin - a service implementation that plugs into a service interface.
 * @author Gavin Rouleau
 */
public class UserSvcImpl implements IUserSvc {
    
    /** Field to store a connection for connecting to the database. */
    private Connection connection = null;
    
    /** Field to store a prepared statement for database execution. */    
    private PreparedStatement prepStatement = null;
    
    /** Field to store the SQL command to execute with the database. */ 
    private String sqlCommand;
    
    /**Method implementation to store a Travel Planner Application User.
     * @param user TravelPlannerAppUser is specified by incoming user parameter.
     * @return Returns boolean value indicating whether operation was successful or not.
     */
    /*@Override // TODO - future method
    public boolean storeUser(TravelPlannerAppUser user) throws UserSvcException {

        boolean isSuccessful = false;
        
        UserPojo userPojo = new UserPojo();
        isSuccessful = userPojo.performAction("StoreUser", user, user);
        
        if(isSuccessful != true){
            System.err.println("UserSvcImpl::storeUser unable to store user: \"" + user.getUsername() + "\"");
        }
        
        return isSuccessful;
    }*/
    
    /**Method implementation to get a Travel Planner Application User.
     * @param user TravelPlannerAppUser is specified by incoming user parameter.
     * @return Method returns a TravelPlannerAppUser object.
     */
    public TravelPlannerAppUser getUser(TravelPlannerAppUser user) {

        UserInformation userInformation = null;
        ResultSet resultSet;
        
        try
        {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            PGConnectionPoolDataSource connectionPool = (PGConnectionPoolDataSource) envContext.lookup("jdbc/postgresqlconnector");
            
            connection = connectionPool.getConnection();
            
            if (connection != null && user != null) {
                sqlCommand = "SELECT user_id, username, password, firstname, middlename, lastname, address, phone, email " +
                             "FROM app_users WHERE username = ?";
                prepStatement = connection.prepareStatement(sqlCommand);
                prepStatement.setString(1, user.getUsername());
                resultSet = prepStatement.executeQuery();

                boolean isEmpty = true;
                
                while (resultSet.next()) {
                    user.setUserId(resultSet.getInt("user_id"));
                    
                    if (user.getPassword().equals(resultSet.getString("password"))) {
                        userInformation = new UserInformation();
                        userInformation.setFirstName(resultSet.getString("firstname"));
                        userInformation.setMiddleName(resultSet.getString("middlename"));
                        userInformation.setLastName(resultSet.getString("lastname"));
                        userInformation.setAddress(resultSet.getString("address"));
                        userInformation.setPhone(resultSet.getString("phone"));
                        userInformation.setEmail(resultSet.getString("email"));
                        user.setUserInformation(userInformation);
                    }
                    
                    isEmpty = false;
                }
                
                if (isEmpty) {
                    user = null;
                }
                
            }
            else if (connection == null)
            {
                System.err.println("UserSvcImpl::getUser Connection is Null");
            }
            else if (user == null) {
                System.err.println("UserSvcImpl::getUser User is Null");
            }

            if(user != null) {
                sqlCommand = "SELECT fav_list_name, fav_list_type FROM favorite_lists " +
                             "WHERE user_id = ?";
                prepStatement = connection.prepareStatement(sqlCommand);
                prepStatement.setInt(1, user.getUserId());
                resultSet = prepStatement.executeQuery();
                
                FavoriteList tempFavoriteList;
                List<FavoriteList> tempFavoriteLists = new ArrayList<FavoriteList>();
                while(resultSet.next()) {
                    tempFavoriteList = new FavoriteList();
                    tempFavoriteList.setNameFavList(resultSet.getString("fav_list_name"));
                    tempFavoriteList.setFavListType(resultSet.getString("fav_list_type"));
                    tempFavoriteLists.add(tempFavoriteList);
                }

                user.setAllFavLists(tempFavoriteLists);
                user.setAllFavListNames(tempFavoriteLists);
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
        
        return user;
    }
}
