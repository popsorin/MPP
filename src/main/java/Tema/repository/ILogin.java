package Tema.repository;

import java.util.List;
import java.util.Vector;

public interface ILogin<U> {
    int size();
    void save(U entity);
    void delete(int id);
    void update(int id, U entity);

    boolean CheckUser(String user,String password);

}
