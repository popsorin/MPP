package Tema.repository;


import Tema.Dommain.Spectacol;
import Tema.Dommain.Table;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SpectacolJdbcRepository implements IRepository<Integer, Spectacol> {
    private JDBCUtils jdbcUtils;
    private static final Logger logger = LogManager.getLogger();

    public SpectacolJdbcRepository() {
        logger.info("Initializing SpectacolJdbcRepository");
        this.jdbcUtils = new JDBCUtils();
    }

    @Override
    public int size() {
        logger.traceEntry();
        Connection connection = jdbcUtils.getConnection();
        try (PreparedStatement prepStm = connection.prepareStatement("SELECT COUNT (*) AS [SIZE] FROM Spectacol")) {
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
    public void save(Spectacol entity) {
        logger.traceEntry("Saving Spectacol {}", entity);
        Connection connection = jdbcUtils.getConnection();
        try (PreparedStatement prepStm = connection.prepareStatement("INSERT INTO Spectacol VALUES (?,?,?,?,?)")) {
            prepStm.setString(1, entity.getData());
            prepStm.setString(2, entity.getLocul());
            prepStm.setInt(3, entity.getNrVandute());
            prepStm.setInt(4, entity.getNrDisponibile());
            prepStm.setInt(5, entity.getNrVandute());
            prepStm.executeUpdate();
        } catch (SQLException exception) {
            logger.error(exception);
            System.out.println(exception.getMessage());
        }
        logger.traceExit();
    }

    @Override
    public void delete(Integer integer) {
        logger.traceEntry("Deleting Spectacol with id {}", integer);
        Connection connection = jdbcUtils.getConnection();
        try (PreparedStatement prepStm = connection.prepareStatement("DELETE FROM Spectacol WHERE id=?")) {
            prepStm.setInt(1, integer);
            prepStm.executeUpdate();
        } catch (SQLException exception) {
            logger.error(exception);
            System.out.println(exception.getMessage());
        }
        logger.traceExit();

    }

    @Override
    public void update(Integer integer, Spectacol entity) {
        logger.traceEntry("Updating Spectacol with id {} in {}", integer, entity);
        Connection connection = jdbcUtils.getConnection();
        try (PreparedStatement prepStm = connection.prepareStatement("UPDATE Spectacol SET date=?,locul=?,nrVandute=?,nrDisponibile=?,idA=? where id=?")) {
            prepStm.setString(1, entity.getData());
            prepStm.setString(2, entity.getLocul());
            prepStm.setInt(3, entity.getNrVandute());
            prepStm.setInt(4, entity.getNrDisponibile());
            prepStm.setInt(5, entity.getIdA());
            prepStm.setInt(5, entity.getIdS());
            prepStm.executeUpdate();
        } catch (SQLException exception) {
            logger.error(exception);
            System.out.println(exception.getMessage());
        }
        logger.traceExit();
    }

    @Override
    public void updateBilete(Integer integer, Integer nrBilete) {
        logger.traceEntry("Updating Spectacol with id {} in {}", integer, nrBilete);
        Connection connection = jdbcUtils.getConnection();
        try (PreparedStatement prepStm = connection.prepareStatement("SELECT nrDisponibile,nrVandute FROM Spectacol WHERE idS=?")) {
            prepStm.setInt(1, integer);
            ResultSet set = prepStm.executeQuery();


           if(nrBilete<=set.getInt("nrDisponibile"))
            {

                try(PreparedStatement prepStm0 = connection.prepareStatement("UPDATE Spectacol SET nrVandute=?,nrDisponibile=? where idS=?")) {
                    int nrV = set.getInt("nrVandute") + nrBilete;
                    int nrD = set.getInt("nrDisponibile") - nrBilete;
                    System.out.println(nrV);
                    prepStm0.setInt(1, nrV);
                    prepStm0.setInt(2, nrD);
                    prepStm0.setInt(3, integer);
                    prepStm0.executeUpdate();
                }
            }
            else{
                System.out.println("Nu mai sunt atatea bilete");
            }

        } catch (SQLException exception) {
            logger.error(exception);
            System.out.println(exception.getMessage() + "REPOOOOOOOOOOOOOOOOOOOO Spectacoooooool");
        }
        logger.traceExit();
    }

    @Override
    public List<Spectacol> findOne(Integer integer) {
        logger.traceEntry("Finding Spectacol with id {}", integer);
        Connection connection = jdbcUtils.getConnection();
        try (PreparedStatement prepStm = connection.prepareStatement("SELECT * FROM Spectacol WHERE idS=?")) {
            prepStm.setInt(1, integer);
            ResultSet set = prepStm.executeQuery();
            List<Spectacol> spec=new ArrayList<>();
            while (set.next()) {
                logger.traceExit(set.getInt(1) + " " + set.getString(2) + " " + set.getInt(3));
                String d = set.getString(2);

                Spectacol s= new Spectacol(set.getInt(1),set.getString("Data"),set.getString(3),set.getInt(4),set.getInt(5),set.getInt(6));
                spec.add(s);
            }
            return spec;
        } catch (SQLException exception) {
            logger.error(exception);
            System.out.println(exception.getMessage());
        }
        return null;
    }
    @Override
    public Spectacol findOneName(String name) {
        return null;
    }

    @Override
    public List<Table> findTable(int id_a){return null;}

    @Override
    public List<Table> findOneDate(String date) {
        return null;
    }/*
        logger.traceEntry("Finding Spectacol with id {}", date.toString());
        Connection connection = jdbcUtils.getConnection();
        try (PreparedStatement prepStm = connection.prepareStatement("SELECT * FROM Spectacol WHERE Data=?")) {
            prepStm.setString(1, date);
            ResultSet set = prepStm.executeQuery();
            List<Spectacol> spec=new ArrayList<>();
            while (set.next()) {
                logger.traceExit(set.getInt(1) + " " + set.getString(2) + " " + set.getInt(3));
                Spectacol s= new Spectacol(set.getInt(1),set.getString(2),set.getString(3),set.getInt(4),set.getInt(5),set.getInt(6));
                spec.add(s);
            }
            return spec;
        } catch (SQLException exception) {
            logger.error(exception);
            System.out.println(exception.getMessage());
        }
        return null;
    }*/


    @Override
    public Iterable<Spectacol> findAll() {
        logger.traceEntry();
        Connection connection = jdbcUtils.getConnection();
        List<Spectacol> Spectacols = new ArrayList<>();
        try (PreparedStatement preStmt = connection.prepareStatement("select * from Spectacol")) {
            try (ResultSet result = preStmt.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt("idS");
                    String data=result.getString("Data");
                    String loc = result.getString("locul");
                    int nrV = result.getInt("nrVandute");
                    int nrD = result.getInt("nrDisponibile");
                    int ida = result.getInt("idA");
                    Spectacols.add(new Spectacol(id,data,loc,nrV,nrD,ida));
                }
            }
        } catch (SQLException exception) {
            logger.error(exception);
            System.out.println("Error DB " + exception);
        }
        logger.traceExit(Spectacols);
        return  Spectacols;
    }
}
