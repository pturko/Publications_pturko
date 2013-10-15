
package ua.bionic.turko.db;

import java.sql.*;
import java.util.logging.Level;
import org.apache.log4j.Logger;
//import ua.bionic.turko.driver.DriverFactory;


public final class DBQuery {

//    private Database database;
//    private Statement statement;
    private String lastError;
    private static final Logger LOG=Logger.getLogger(DBQuery.class.getName());    
    
    private static Connection connection = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultsSet = null;
    
    // NEW
    public static ResultSet getQueryResult(PreparedStatement statement) {
//        LOG.info("getQueryResult: "+queryString);
              
        ConnectionPool pool = getConnectionPool();
        try {            
            connection = pool.getConnection();
            
               try {
                   resultsSet = statement.executeQuery();
                   resultsSet.next();
                   return resultsSet;   
                  // ====================================
               } catch (SQLException ex) {
                   LOG.warn(">>>" + ex.toString());
                   return null;
                  // ====================================     
               } finally {
                   try {
                       CloseConnection();
                   } catch (SQLException ex) {
                       ex.printStackTrace();                       
                   }
               }                   

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    
     // NEW
     public static ResultSet getResult(PreparedStatement statement) {
//        LOG.info("getResult: "+queryString);
        
        ConnectionPool pool = getConnectionPool();
        try {            
            connection = pool.getConnection();
            
               try {
                   resultsSet = statement.executeQuery();
                   return resultsSet; 
                  // ==================================== 
               } catch (SQLException ex) {
                   LOG.warn(ex.toString());
                   return null;
                  // ====================================     
               } finally {
                   try {
                       CloseConnection();
                   } catch (SQLException ex) {
                       ex.printStackTrace();                       
                   }
               }                   

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    } 
    
     
     // NEW
     public static void queryUpdate(String queryString, PreparedStatement st) {
       LOG.info("queryUpdate: "+queryString);

    }     
     
     
    public static Connection getConnection() {
        ConnectionPool pool = getConnectionPool();
        try {
            connection = pool.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
            return connection;
    }
     
     
    public static ConnectionPool getConnectionPool() {
       return ConnectionPool.getInstance();
    }
    
    
    public static void CloseConnection() throws SQLException {
        //if (resultsSet != null) {
        //        resultsSet.close();
        //}        
        if (statement != null) {
                statement.close();
        }        
        if (connection != null) {
                connection.close();        
        }        
    }   
      
    
     
}