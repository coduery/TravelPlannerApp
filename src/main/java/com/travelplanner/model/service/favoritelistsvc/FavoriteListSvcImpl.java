package com.travelplanner.model.service.favoritelistsvc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.postgresql.ds.PGConnectionPoolDataSource;

import com.travelplanner.model.domain.FavoriteItem;
import com.travelplanner.model.domain.FavoriteList;
import com.travelplanner.model.domain.TravelPlannerAppUser;

/**Implementation of Interface to define a Favorite List Service for the Travel Planner Application.
 * Design Pattern: Plugin - a service implementation that plugs into a service interface.
 * @author Gavin Rouleau
 */
public class FavoriteListSvcImpl implements IFavoriteListSvc {

    /** Field to store a connection for connecting to the database. */
    private Connection connection = null;
    
    /** Field to store a prepared statement for database execution. */    
    private PreparedStatement prepStatement = null;
    
    /** Field to store the SQL command to execute with the database. */ 
    private String sqlCommand;
    
    /**Method implementation to store a Favorite List.
     * @param list FavoriteList is specified by incoming list parameter.
     * @return Returns boolean value indicating whether operation was successful or not.
     */
    public boolean storeFavList(TravelPlannerAppUser user, FavoriteList list) {
        
        boolean isSuccessful = false;
        
        try
        {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            PGConnectionPoolDataSource connectionPool = (PGConnectionPoolDataSource) envContext.lookup("jdbc/postgresqlconnector");
            
            connection = connectionPool.getConnection();
            
            if(connection != null && user != null && list != null){
                
                if(list.getFavList() == null){
                    
                    int userId = getUserId(user.getUsername());
                    sqlCommand = "INSERT INTO favorite_lists(fav_list_name, fav_list_type, user_id) VALUES (?, ?, ?)";
                    prepStatement = connection.prepareStatement(sqlCommand);
                    prepStatement.setString(1, list.getNameFavList());
                    prepStatement.setString(2, list.getFavListType());
                    prepStatement.setInt(3, userId);            
                    prepStatement.executeUpdate();
                }
                else if(list.getFavList() != null){
                
                    List<FavoriteItem> listFavItems = list.getFavList();
                    FavoriteItem lastFavItem = listFavItems.get(listFavItems.size() - 1);

                    int listId = getListId(list.getNameFavList());

                    sqlCommand = "INSERT INTO favorite_items(fav_item_name, fav_item_type, location_description, address, " +
                                 "city, state, country, zip, phone, notes, list_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    prepStatement = connection.prepareStatement(sqlCommand);
                    prepStatement.setString(1, lastFavItem.getNameFavItem());
                    prepStatement.setString(2, lastFavItem.getFavItemType());
                    prepStatement.setString(3, lastFavItem.getLocationDescript().toString());
                    prepStatement.setString(4, lastFavItem.getAddress());
                    prepStatement.setString(5, lastFavItem.getCity());
                    prepStatement.setString(6, lastFavItem.getStateProvince());
                    prepStatement.setString(7, lastFavItem.getCountry());
                    prepStatement.setString(8, lastFavItem.getZip());
                    prepStatement.setString(9, lastFavItem.getPhone());
                    prepStatement.setString(10, lastFavItem.getNotes().toString());
                    prepStatement.setInt(11, listId); 
                    prepStatement.executeUpdate();
                }
                
                isSuccessful = true;
            }
            else if (connection == null)
            {
                System.err.println("FavoriteListSvcImpl::storeFavList Connection is Null");
            }
            else if (user == null) 
            {
                System.err.println("FavoriteListSvcImpl::storeFavList User is Null");
            }
            else if (list == null) 
            {
                System.err.println("FavoriteListSvcImpl::storeFavList List is Null");
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
    
    /**Method implementation to get a Favorite List.
     * @param username Travel Planner User name is specified by incoming username parameter.
     * @param listname Favorite List name is specified by incoming lastname parameter.
     * @return Method returns a FavoriteList object.
     */
    public FavoriteList getFavList(String username, String listname) {
        
        FavoriteList list = null;
        
        try
        {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            PGConnectionPoolDataSource connectionPool = (PGConnectionPoolDataSource) envContext.lookup("jdbc/postgresqlconnector");
            
            connection = connectionPool.getConnection();
            
            if(connection != null){
                int userId = getUserId(username);
                sqlCommand = "SELECT fav_list_name, fav_list_type FROM favorite_lists " +
                             "WHERE user_id = ? AND fav_list_name = ? ";
                prepStatement = connection.prepareStatement(sqlCommand);
                prepStatement.setInt(1, userId);
                prepStatement.setString(2, listname);
                ResultSet resultSet = prepStatement.executeQuery();
                
                while(resultSet.next()) {
                    list = new FavoriteList();
                    list.setNameFavList(resultSet.getString("fav_list_name"));
                    list.setFavListType(resultSet.getString("fav_list_type"));
                }
                
                int listId = getListId(listname);
                sqlCommand = "SELECT fav_item_name, fav_item_type, location_description, address, city, " +
                             "state, country, zip, phone, notes FROM favorite_items WHERE list_id = ? ";
                prepStatement = connection.prepareStatement(sqlCommand);
                prepStatement.setInt(1, listId);
                resultSet = prepStatement.executeQuery();
                
                FavoriteItem item = null;
                List<FavoriteItem> items = new ArrayList<FavoriteItem>();
                
                if(resultSet != null){
                    while(resultSet.next()) {
                        item = new FavoriteItem();
                        item.setNameFavItem(resultSet.getString("fav_item_name"));
                        item.setFavItemType(resultSet.getString("fav_item_type"));
                        item.setLocationDescript(resultSet.getString("location_description"));
                        item.setAddress(resultSet.getString("address"));
                        item.setCity(resultSet.getString("city"));
                        item.setStateProvince(resultSet.getString("state"));
                        item.setCountry(resultSet.getString("country"));
                        item.setZip(resultSet.getString("zip"));
                        item.setPhone(resultSet.getString("phone"));
                        item.setNotes(resultSet.getString("notes"));
                        items.add(item);
                    }

                    if (list != null) {
                        list.setFavList(items);
                    }
                }
            }
            else
            {
                System.err.println("FavoriteListSvcImpl::getFavList connection is null");
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
        
        return list;
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
            System.err.println("FavoriteListImpl::getUserId unable to get user ID for user: " + username);
        }

        return userId;
    }
    
    /**Method for getting a List Id from the database
     * @param listname Parameter that specifies favorite list name to receive the List ID for.
     * @return Returns an int value representing a List Id.
     */
    private int getListId(String listname){
        
        int listId = 0;
        
        sqlCommand = "SELECT list_id FROM favorite_lists " +
                     "WHERE fav_list_name = ?";
        try {
            prepStatement = connection.prepareStatement(sqlCommand);
            prepStatement.setString(1, listname);
            ResultSet resultSet = prepStatement.executeQuery();
            while(resultSet.next()) {
                listId = resultSet.getInt("list_id");
            }
        } catch (SQLException ex) {
            System.err.println("FavoriteListImpl::getListId unable to get list ID for list: " + listname);
        }

        return listId;
    }
}
