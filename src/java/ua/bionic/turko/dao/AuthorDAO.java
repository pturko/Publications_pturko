package ua.bionic.turko.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import static ua.bionic.turko.dao.PublishDAO.QUERY;
import ua.bionic.turko.daointerfaces.IAuthorDAO;
import ua.bionic.turko.db.DBQuery;
//import ua.bionic.turko.db.Query;


public class AuthorDAO implements IAuthorDAO {
    public static final String QUERY = "SELECT * FROM AUTHOR ";
    public static final Logger LOG=Logger.getLogger(AuthorDAO.class.getName());

    @Override
    public String getAuthorName(String id) {
        ResultSet rSet = null;
        PreparedStatement statemen = null;
        
        try {
            LOG.info("AuthorDAO getAuthorName from id");
            //rSet = Query.getResultSetFromQuery("select * from author where auth_id='"+id+"'");
            
            statemen = DBQuery.getConnection().prepareStatement(QUERY + "WHERE AUTH_ID = ?");
            statemen.setString(1, id.toString());      
            rSet = DBQuery.getQueryResult(statemen);
            
            
            return rSet.getString("name");
        } catch (SQLException ex) {
            LOG.warn(ex.toString());
            return null;
        } finally {
            if (rSet != null)
                rSet = null;
        }
    }

    @Override
    public String getAuthorSurname(String id) {
        ResultSet rSet = null;
        PreparedStatement statemen = null;
        
        try {
            LOG.info("AuthorDAO getAuthorSurname from id");
            
            statemen = DBQuery.getConnection().prepareStatement(QUERY + "WHERE AUTH_ID = ?");
            statemen.setString(1, id.toString());
            rSet = DBQuery.getQueryResult(statemen);
            
            //rSet = Query.getResultSetFromQuery("select * from author where auth_id = ?'");
            return rSet.getString("surname");
        } catch (SQLException ex) {
            LOG.warn(ex.toString());
            return null;
       } finally {
            if (rSet != null)
                rSet = null;
        }
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
         if(!(obj instanceof AuthorDAO)) return false;
         
          AuthorDAO obj1 = (AuthorDAO) obj;
         
          return false;
    }   		
  
          
    public long getTime() {
          return System.currentTimeMillis();
    }

}