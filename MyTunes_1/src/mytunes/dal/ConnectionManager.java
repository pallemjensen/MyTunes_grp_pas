/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytunes.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;

/**
 *
 * @author pmj
 */
public class ConnectionManager {
    private SQLServerDataSource ds
                = new SQLServerDataSource();
    public ConnectionManager() {
        ds.setDatabaseName("MytunesAPS");
        ds.setUser("CS2017A_28");
        ds.setPassword("Ellap060174");
        ds.setServerName("EASV-DB2");
        ds.setPortNumber(1433);
    }
    
    public Connection getConnection() throws SQLServerException
    {
        return ds.getConnection();
    }
    
}