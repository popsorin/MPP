package Tema.ServerRMI;

import Tema.Dommain.Artisti;
import Tema.Dommain.Spectacol;
import Tema.Dommain.Table;
import javafx.scene.control.Tab;

import java.util.List;

public interface IServices {
    List<Table> findArtist(String data);
    void Cumparare(int nrBilete,int idS);
    boolean CheckUser(String user,String password);
    Iterable<Artisti> findAllArtisti();
    Iterable<Spectacol> findAllSpectacol();
    List<Table> findTable(int id_a);
}
