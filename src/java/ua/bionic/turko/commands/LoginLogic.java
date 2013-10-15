
package ua.bionic.turko.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import ua.bionic.turko.db.ConnectionPool;
import ua.bionic.turko.db.DBQuery;
import ua.bionic.turko.db.DBResources;
import ua.bionic.turko.db.DBTypes;
//import ua.bionic.turko.db.Database;
//import ua.bionic.turko.db.DBQuery;
//import ua.bionic.turko.driver.Driver;
//import ua.bionic.turko.driver.DriverFactory;


public class LoginLogic {

    public static final Logger LOG=Logger.getLogger(LoginLogic.class.getName());
    public static final String QUERY = "SELECT * FROM USERS WHERE LOGIN = ? AND PASSWORD = ?";
    
    
    public static boolean checkLogin(String login, String password) throws ClassNotFoundException, SQLException {
        
        LOG.info("checkLogin: " + login + " " + password);
       
//        ResultSet rs = null;
        
//       try {            
//           
//        String queryUsers = "select * from users where login='"+login+"' and password='"+password+"'";
//        

//        
    	
//    	Driver dbDriver = DriverFactory.createDriver(DBTypes.MYSQL);
    	    	
    	//Database database = new Database(dbDriver); //Connection
    	//database.connect(); //conn = getConnection(url, login, password)
    	

        ResultSet rSet = null;
        PreparedStatement statemen = null;
        
        DBResources.getInstance();
        
        try {
//            statemen = DBQuery.getConnection().prepareStatement(QUERY);
//            statemen.setString(1, login);
//            statemen.setString(2, password);    
//            rSet = DBQuery.getQueryResult(statemen);
//            LOG.info("1111111111111111");
            
            statemen = DBQuery.getConnection().prepareStatement(QUERY);
            statemen.setString(1, login);
            statemen.setString(2, password);
            LOG.info(" statemen " + statemen);
            rSet = statemen.executeQuery();

                    
            return rSet.next();
            
        } catch (SQLException ex) {
            LOG.warn(ex.toString());
            return false;
        } finally {
            //DBQuery.CloseConnection();
        }
    
        
//        ConnectionPool pool = ConnectionPool.getInstance();
//        LOG.info("pool: " + pool);
//        try {
//            
//            Connection cn = null;
//            try {
//                cn = pool.getConnection();
//                PreparedStatement st = null;
//                try {
//                    st = cn.prepareStatement("SELECT * FROM USERS WHERE LOGIN = ? AND PASSWORD = ?");
//                    st.setString(1, login);
//                    st.setString(2, password);
//                    ResultSet rs = null;
//                    try {
//                        rs = st.executeQuery();
//                        return rs.next();   
//                    // ==================================== 
//                    } catch (SQLException ex) {
//                        LOG.warn(ex.toString());
//                        return false;
//                    // ====================================     
//                    } finally {
//                        if (rs != null) {
//                            rs.close();
//                        }
//                    }
//                } finally {
//                    if (st != null) {
//                        st.close();
//                    }
//                }
//            } finally {
//                if (cn != null) {
//                    cn.close();
//                }
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
        
        
        
        
        
        
//    	Query query = new Query(database);
//    	query.createStatement(); //createStatement()
    	
//    	rs = query.executeQuery(queryUsers);
//    	
//         return rs.next();       
//            } catch (SQLException ex) {
//            LOG.warn(ex.toString());
//            return false;
//        } catch (ClassNotFoundException ex) {
//            LOG.warn(ex.toString());
//            return false;
//        } finally {
//            if (rs != null) {
//                   rs.close();
//                        }
//        }
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
         if(!(obj instanceof LoginLogic)) return false;
         
          LoginLogic obj1 = (LoginLogic) obj;
         
          return false;
    }   		
  
          
    public long getTime() {
          return System.currentTimeMillis();
    }
    
}
