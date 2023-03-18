package dal.connector;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

public class DataBaseConnector {

    private static final String file = "resources/sqlogin.properties";
    private SQLServerDataSource dataSource = null;

    public DataBaseConnector() {
        Properties properties = getConnectionDetails();
        dataSource = new SQLServerDataSource();
        dataSource.setDatabaseName(properties.getProperty("name"));
        dataSource.setUser(properties.getProperty("username"));
        dataSource.setPassword(properties.getProperty("password"));
        dataSource.setServerName(properties.getProperty("server"));
        dataSource.setPortNumber(Integer.parseInt(properties.getProperty("port")));
        dataSource.setTrustServerCertificate(true);
    }

    public Connection creatConnection()throws SQLServerException {
        return dataSource.getConnection();
    }

    private Properties getConnectionDetails() {
        Properties properties = new Properties();

        try {
            FileInputStream sr = new FileInputStream(file);
            properties.load(sr);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }
}
