package Tema.repository;


import Tema.Dommain.Artisti;
import Tema.Dommain.Spectacol;
import Tema.Dommain.Table;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.AnnotatedType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistiJdbcRepository implements IRepository<Integer, Artisti> {
    private JDBCUtils jdbcUtils;
    private static final Logger logger = LogManager.getLogger();

    public ArtistiJdbcRepository() {
        logger.info("Initializing ArtistiJdbcRepository");
        this.jdbcUtils = new JDBCUtils();
    }

    @Override
    public int size() {
        logger.traceEntry();
        Connection connection = jdbcUtils.getConnection();
        try (PreparedStatement prepStm = connection.prepareStatement("SELECT COUNT (*) AS [SIZE] FROM Artist")) {
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
    public void save(Artisti entity) {
        logger.traceEntry("Saving Artisti {}", entity);
        Connection connection = jdbcUtils.getConnection();
        try (PreparedStatement prepStm = connection.prepareStatement("INSERT INTO Artist VALUES (?)")) {
            prepStm.setString(1, entity.getNume());
            prepStm.executeUpdate();
        } catch (SQLException exception) {
            logger.error(exception);
            System.out.println(exception.getMessage());
        }
        logger.traceExit();
    }

    @Override
    public void delete(Integer integer) {
        logger.traceEntry("Deleting Artisti with id {}", integer);
        Connection connection = jdbcUtils.getConnection();
        try (PreparedStatement prepStm = connection.prepareStatement("DELETE FROM Artist WHERE id_artist=?")) {
            prepStm.setInt(1, integer);
            prepStm.executeUpdate();
        } catch (SQLException exception) {
            logger.error(exception);
            System.out.println(exception.getMessage());
        }
        logger.traceExit();

    }

    @Override
    public void update(Integer integer, Artisti entity) {
        logger.traceEntry("Updating Artisti with id {} in {}", integer, entity);
        Connection connection = jdbcUtils.getConnection();
        try (PreparedStatement prepStm = connection.prepareStatement("UPDATE Artist SET nume=? where id_artist=?")) {
            prepStm.setString(1, entity.getNume());
            prepStm.setInt(2, entity.getId());
            prepStm.executeUpdate();
        } catch (SQLException exception) {
            logger.error(exception);
            System.out.println(exception.getMessage());
        }
        logger.traceExit();
    }

    @Override
    public void updateBilete(Integer integer, Integer integer1) {}

    @Override
    public List<Artisti> findOne(Integer integer) {
        logger.traceEntry("Finding Artisti with nume {}", integer);
        Connection connection = jdbcUtils.getConnection();
        try (PreparedStatement prepStm = connection.prepareStatement("SELECT * FROM Artist WHERE id_artist=?")) {
            prepStm.setInt(1, integer);
            ResultSet set = prepStm.executeQuery();
            List<Artisti> art=new ArrayList<>();
            while (set.next()) {
                logger.traceExit(set.getInt(1) + " " + set.getString(2));
                 Artisti a = new Artisti(set.getString(2),set.getInt(1));
                 art.add(a);

            }
            return art;
        } catch (SQLException exception) {
            logger.error(exception);
            System.out.println(exception.getMessage());
        }
        return null;
    }
    @Override
    public Artisti findOneName(String n) {
        logger.traceEntry("Finding Artisti with nume {}", n);
        Connection connection = jdbcUtils.getConnection();
        try (PreparedStatement prepStm = connection.prepareStatement("SELECT * FROM Artist WHERE nume=?")) {
            prepStm.setString(1, n);
            ResultSet set = prepStm.executeQuery();
            if (set.next()) {

                logger.traceExit(set.getInt("id_artist") + " " + set.getString("nume"));
                return new Artisti(set.getString("nume"),set.getInt("id_artist"));
            }
        } catch (SQLException exception) {
            logger.error(exception);
            System.out.println(exception.getMessage()+"************************");
        }
        catch (Exception exp){
            System.out.println(exp.getMessage()+"************************************");
        }
        Artisti a =new Artisti("Smth",1);
        return a;
    }


    @Override
    public List<Table> findOneDate(String date){

        logger.traceEntry("Finding Spectacol with id {}", date.toString());
        Connection connection = jdbcUtils.getConnection();
        try (PreparedStatement prepStm = connection.prepareStatement("SELECT A.id_artist,A.nume,S.data,S.locul,S.nrDisponibile,S.Ora,S.nrVandute,S.idS FROM Artist A INNER JOIN Spectacol S ON S.idA = A.id_artist WHERE S.data = ?")) {
            prepStm.setString(1, date);
            ResultSet set = prepStm.executeQuery();
            List<Table> art=new ArrayList<>();

            while (set.next()) {
                logger.traceExit(set.getInt(1) + " " + set.getString(2) + " " + set.getInt(3));
                Table t= new Table(set.getString("nume"),set.getString("Data"),set.getString("locul"),set.getInt("nrVandute"),set.getInt("nrDisponibile"),set.getString("Ora"),set.getInt("idS"));
                art.add(t);

            }
            return art;
        } catch (SQLException exception) {
            logger.error(exception);
            System.out.println(exception.getMessage());
        }
        return null;
    }
    @Override
    public List<Table> findTable(int id_a){

        logger.traceEntry("Finding Spectacol with id {}",id_a);
        Connection connection = jdbcUtils.getConnection();
        try (PreparedStatement prepStm = connection.prepareStatement("SELECT A.nume,S.data,S.locul,S.nrDisponibile,S.nrVandute,S.ora,S.idS FROM Artist A INNER JOIN Spectacol S ON S.idA = A.id_artist WHERE A.id_artist = ?")) {
            prepStm.setInt(1, id_a);
            ResultSet set = prepStm.executeQuery();
            List<Table> art=new ArrayList<>();
            while (set.next()) {
                logger.traceExit(set.getInt(1) + " " + set.getString(2) + " " + set.getInt(3));
                Table t= new Table(set.getString("nume"),set.getString("Data"),set.getString("locul"),set.getInt("nrVandute"),set.getInt("nrDisponibile"),set.getString("Ora"),set.getInt("idS"));
                art.add(t);

            }
            return art;
        } catch (SQLException exception) {
            logger.error(exception);
            System.out.println(exception.getMessage());
        }
        return null;}

    @Override
    public Iterable<Artisti> findAll() {
        logger.traceEntry();
        Connection connection = jdbcUtils.getConnection();
        List<Artisti> Artistis = new ArrayList<>();
        try (PreparedStatement preStmt = connection.prepareStatement("select * from Artist")) {
            try (ResultSet result = preStmt.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt("id_artist");
                    String name = result.getString("nume");
                    Artistis.add(new Artisti(name, id));
                }
            }
        } catch (SQLException exception) {
            logger.error(exception);
            System.out.println("Error DB " + exception);
        }
        logger.traceExit(Artistis);
        return  Artistis;

    }
}
