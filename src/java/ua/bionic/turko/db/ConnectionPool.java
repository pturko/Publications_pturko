/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.bionic.turko.db;

//import com.bionic.login.manager.Config;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import static ua.bionic.turko.commands.LoginLogic.LOG;


public class ConnectionPool {

    private static ConnectionPool instance = null;
    private static final String TOMCAT_JNDI_NAME="java:comp/env";
    private DataSource pool;
    private final String DATASOURCE;
    
    public ConnectionPool() {
     DATASOURCE = DBResources.getSource();
             //Config.getInstance().getProperty(Config.DATASOURCE);
     initialPool();     
    }

    public static synchronized ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }
    
    private void initialPool(){
        try{
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup(TOMCAT_JNDI_NAME);
            pool = (DataSource)envContext.lookup(DATASOURCE);
            LOG.info("pool : " + pool);
        }catch(NamingException e){
            e.printStackTrace();
        }
    }
    
    public synchronized Connection getConnection() throws SQLException{        
        return pool.getConnection();
    }
    
    public void closeConnection(Connection connection){
        try{
            if(connection != null){
                connection.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
