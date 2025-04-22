package dataaccesslayer;

/*
 * Version: 2
 * Author: Mayank Arora
 */

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
/**
 * DataSource is a class to connect to MySQL database.
 */
public class DataSource {
    private static Properties dbProperties = new Properties();
    private static Connection connection = null;
    private String url;
    private String username;
    private String password;

    public DataSource(ServletContext context) {
        loadDBProperties(context);
    }

    /**
     * Sets database properties using the db.properties file.
     * Allows users to edit the file based on their configuration.
     */

    private void loadDBProperties(ServletContext context) {
        try (InputStream input = context.getResourceAsStream("/WEB-INF/db.properties")) {

            dbProperties.load(input);
            String dbType = dbProperties.getProperty("db");
            String name = dbProperties.getProperty("name");
            String host = dbProperties.getProperty("host");
            String port = dbProperties.getProperty("port");

            url = "jdbc:" + dbType + "://" + host + ":" + port + "/" + name;
            username = dbProperties.getProperty("user");
            password = dbProperties.getProperty("pass");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Creates a database connection after loading the properties.
     * The method prints "DB connected as a confirmation" or "
     * "Cannot create new connection, one exists already" if the database
     * is already connected.
     */

    private synchronized void createConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
//        try {
//            if (connection == null || connection.isClosed()) {
//                loadDBProperties(context);
//                Class.forName("com.mysql.cj.jdbc.Driver");
//
//                connection = DriverManager.getConnection(url, username, password);
//                System.out.printf("DB connected");
//            } else {
//                System.out.println("Cannot create new connection, one exists already");
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            throw ex;
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        return connection;

    }

    /**
     * Returns the connection object.
     * @return connection object
     * @throws SQLException if a database access error occurs
     */
    public Connection getConnection() throws SQLException {
        if(connection == null || connection.isClosed()) {
            createConnection();
        }
        return connection;
    }


}
