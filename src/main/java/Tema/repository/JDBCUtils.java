package Tema.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {
    private static final Logger logger = LogManager.getLogger();
    private Connection connection = null;

    private Connection getNewConnection(){
        logger.traceEntry();
        String url ="jdbc:sqlite:F:\\Info\\MPP\\mydatabase.sqlite";
        String driver = "org.sqlite.JDBC";
        logger.info("trying to connect to the database ... {}", url);
        Connection conn = null;
        try{
            Class.forName(driver);
            logger.info("Loader driver ... {}", driver);
            conn = DriverManager.getConnection(url);
        } catch (SQLException exception){
            logger.error(exception);
            System.out.println("Error get connection" + exception);
        } catch (ClassNotFoundException exception) {
            logger.error(exception);
            System.out.println("Error loading driver" + exception);
        }
        return conn;
    }

    public Connection getConnection(){
        logger.traceEntry();
        try {
            if (connection==null || connection.isClosed())
                connection=getNewConnection();

        } catch (SQLException e) {
            logger.error(e);
            System.out.println("Error DB "+e);
        }
        logger.traceExit(connection);
        return connection;
    }
}
