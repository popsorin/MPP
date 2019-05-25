package Tema.repository;


import Tema.Dommain.Artisti;
import Tema.Dommain.Spectacol;
import Tema.Dommain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.AnnotatedType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LogerJdbcRepository implements ILogin<User> {
    private JDBCUtils jdbcUtils;
    private static final Logger logger = LogManager.getLogger();

    public LogerJdbcRepository() {
        logger.info("Initializing LogerJdbcRepository");
        this.jdbcUtils = new JDBCUtils();
    }

    @Override
    public int size() {
        logger.traceEntry();
        Connection connection = jdbcUtils.getConnection();
        try (PreparedStatement prepStm = connection.prepareStatement("SELECT COUNT (*) AS [SIZE] FROM User ")) {
            try (ResultSet resultSet = prepStm.executeQuery()) {
                if (resultSet.next()) {
                    logger.traceExit(resultSet.getInt("SIZE"));
                    return resultSet.getInt("SIZE");
                }
            }
        } catch (SQLException exception) {
            logger.error(exception);
            System.out.println(exception.getMessage());
        }
        return 0;
    }

    @Override
    public void save(User entity) {
        logger.traceEntry("Saving Artisti {}", entity);
        Connection connection = jdbcUtils.getConnection();
        try (PreparedStatement prepStm = connection.prepareStatement("INSERT INTO User(user,pasword) VALUES (?,?)")) {
            prepStm.setString(1, entity.getUser());
            prepStm.setString(1, entity.getPasword());
            prepStm.executeUpdate();
        } catch (SQLException exception) {
            logger.error(exception);
            System.out.println(exception.getMessage());
        }
        logger.traceExit();
    }

    @Override
    public void delete(int integer) {
        logger.traceEntry("Deleting Artisti with id {}", integer);
        Connection connection = jdbcUtils.getConnection();
        try (PreparedStatement prepStm = connection.prepareStatement("DELETE FROM Artist WHERE id_user=?")) {
            prepStm.setInt(1, integer);
            prepStm.executeUpdate();
        } catch (SQLException exception) {
            logger.error(exception);
            System.out.println(exception.getMessage());
        }
        logger.traceExit();

    }

    @Override
    public void update(int integer, User entity) {
        logger.traceEntry("Updating Artisti with id {} in {}", integer, entity);
        Connection connection = jdbcUtils.getConnection();
        try (PreparedStatement prepStm = connection.prepareStatement("UPDATE User SET user=?,password=? where id_artist=?")) {
            prepStm.setString(1, entity.getUser());
            prepStm.setString(2, entity.getPasword());
            prepStm.setInt(3, integer);
            prepStm.executeUpdate();
        } catch (SQLException exception) {
            logger.error(exception);
            System.out.println(exception.getMessage());
        }
        logger.traceExit();
    }



    @Override
    public boolean CheckUser(String user,String password) {
        logger.traceEntry("checking if we have a user in the database");
        Connection connection = jdbcUtils.getConnection();
        try (PreparedStatement prepStm = connection.prepareStatement("SELECT COUNT(*) AS nr FROM User WHERE user=? AND password=? ")) {
            prepStm.setString(1, user);
            prepStm.setString(2, password);
            ResultSet set = prepStm.executeQuery();
            int count = set.getInt("nr");
            if(count==1)
                return true;
            else
                return false;

        } catch (SQLException exception) {
            logger.error(exception);
            System.out.println(exception.getMessage());
            return true;
        }

    }





}
