package ua.bionic.turko.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import ua.bionic.turko.daointerfaces.IUserDAO;
import ua.bionic.turko.db.DBQuery;

public class UserDAO implements IUserDAO {
    public static final String QUERY = "SELECT * FROM USERS ";
    public static final Logger LOG=Logger.getLogger(UserDAO.class.getName());
    
    @Override
    public String getLogin(String id) {
        ResultSet rSet = null;
        PreparedStatement statemen = null;
        
        try {
            LOG.info("UserDAO getLogin from id");            
            
            statemen = DBQuery.getConnection().prepareStatement(QUERY + "WHERE USER_ID = ?");
            statemen.setString(1, id.toString());            
            rSet = DBQuery.getQueryResult(statemen);
            
            return rSet.getString("login");
        } catch (SQLException ex) {
            LOG.warn(ex.toString());
            return null;
        }
    }

    @Override
    public String getName(String id) {
        ResultSet rSet = null;
        PreparedStatement statemen = null;
        
        try {
            LOG.info("UserDAO getName from id");
            
            statemen = DBQuery.getConnection().prepareStatement(QUERY + "WHERE USER_ID = ?");
            statemen.setString(1, id.toString());            
            rSet = DBQuery.getQueryResult(statemen);
            
            return rSet.getString("name");
        } catch (SQLException ex) {
            LOG.warn(ex.toString());
            return null;
        }
    }

    @Override
    public String getSurname(String id) {
        ResultSet rSet = null;
        PreparedStatement statemen = null;
        
        try {
            LOG.info("UserDAO getSurname from id");
            //rSet = DBQuery.getResultSetFromQuery("select * from users where user_id='"+id+"'");
            
            statemen = DBQuery.getConnection().prepareStatement(QUERY + "WHERE USER_ID = ?");
            statemen.setString(1, id.toString());            
            rSet = DBQuery.getQueryResult(statemen);
            
            return rSet.getString("Surname");
        } catch (SQLException ex) {
            LOG.warn(ex.toString());
            return null;
        }
    }


    @Override
    public String getLastLogin(String id) {
        ResultSet rSet = null;
        PreparedStatement statemen = null;
        
        try {
            LOG.info("UserDAO getLastLogin from id");
            //rSet = DBQuery.getResultSetFromQuery("select * from users where user_id='"+id+"'");
            
            statemen = DBQuery.getConnection().prepareStatement(QUERY + "WHERE USER_ID = ?");
            statemen.setString(1, id.toString());            
            rSet = DBQuery.getQueryResult(statemen);
            
            return rSet.getString("Last_Login");
        } catch (SQLException ex) {
            LOG.warn(ex.toString());
            return null;
        }
    }

    @Override
    public String getIdByLogin(String login) {
        ResultSet rSet = null;
        PreparedStatement statemen = null;
        
        try {
            LOG.info("UserDAO getIdByLogin from login");
            //rSet = DBQuery.getResultSetFromQuery("select * from users where login='"+login+"'");
            
            statemen = DBQuery.getConnection().prepareStatement(QUERY + "WHERE LOGIN = ?");
            statemen.setString(1, login);            
            rSet = DBQuery.getQueryResult(statemen);
            
            return rSet.getString("user_id");
        } catch (SQLException ex) {
            LOG.warn(ex.toString());
            return null;
        }
    }

    @Override
    public UserType getUserType(String id) {
        ResultSet rSet = null;
        PreparedStatement statemen = null;
        
        try {
            LOG.info("UserDAO getUserType from id");
            //rSet = DBQuery.getResultSetFromQuery("select * from users where user_id='"+id+"'");
            
            statemen = DBQuery.getConnection().prepareStatement(QUERY + "WHERE USER_ID = ?");
            LOG.info("id " + id);
            statemen.setString(1, id.toString());            
            rSet = DBQuery.getQueryResult(statemen);
            
            String userType = rSet.getString("User_Type");
            
            if (userType.equals("0")) {
                return UserType.ADMIN;
            }
            else {
                return UserType.USER;
            }
            
        } catch (SQLException ex) {
            LOG.warn(ex.toString());
            return null;
        }
    }

    @Override
    public String getBalance(String id) {
        ResultSet rSet = null;
        PreparedStatement statemen = null;
        
        try {
            LOG.info("UserDAO getBalance from id");
            //rSet = DBQuery.getResultSetFromQuery("select * from users where user_id='"+id+"'");
            
            statemen = DBQuery.getConnection().prepareStatement(QUERY + "WHERE USER_ID = ?");
            statemen.setString(1, id.toString());            
            rSet = DBQuery.getQueryResult(statemen);
            
            return rSet.getString("balance");
        } catch (SQLException ex) {
            LOG.warn(ex.toString());
            return null;
        }
    }

    @Override
    public void withdraw(String id, int balance) {
        PreparedStatement statemen = null;
            LOG.info("USERDAO withdraw from id and balance");
//            DBQuery.queryUpdate("update users set balance='"+balance+"' where user_id='"+id+"'");

    }    

    public int hashCode() {
        long ht = this.getTime();
        return (int) ht ^ (int) (ht >> 32);
    }
	
	
    public String toString() {
	return getClass().getName();
    }
	

    public boolean equals(Object obj) {
         if (this == obj) return true;
		            
         if(obj == null) return false;

         //проверяет является ли obj объектом App
         if(!(obj instanceof UserDAO)) return false;
         
          UserDAO obj1 = (UserDAO) obj;
         
          return false;
    }   		
  
          
    public long getTime() {
          return System.currentTimeMillis();
    }
    
}