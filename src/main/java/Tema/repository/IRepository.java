package Tema.repository;

import Tema.Dommain.Spectacol;
import Tema.Dommain.Table;

import java.sql.Date;
import java.util.List;

/**
 * Created by grigo on 11/14/16.
 */
public interface IRepository<ID, T> {
    int size();
    void save(T entity);
    void delete(ID id);
    void update(ID id, T entity);
    void updateBilete(ID id, ID numar);
    List<T> findOne(ID integer);
    T findOneName(String name);
    List<Table> findOneDate(String date);
    List<Table> findTable(int id_a);
    Iterable<T> findAll();
}