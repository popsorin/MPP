package Tema.Dommain;


import java.io.Serializable;

public class Table implements Serializable {

    private int idS;
    private String nume;
    private String data;
    private String locul;
    private  int nrVandute;
    private int nrDisponibile;
    private String ora;


    public Table(String nume, String data, String locul, int nrVandute, int nrDisponibile,String ora,int idS){
        this.nume = nume;
        this.data = data;
        this.locul = locul;
        this.nrVandute = nrVandute;
        this.nrDisponibile = nrDisponibile;
        this.ora=ora;
        this.idS=idS;
    }
    public Table() {

    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getLocul() {
        return locul;
    }

    public void setLocul(String locul) {
        this.locul = locul;
    }

    public int getNrVandute() {
        return nrVandute;
    }

    public void setNrVandute(int nrVandute) {
        this.nrVandute = nrVandute;
    }

    public int getNrDisponibile() {
        return nrDisponibile;
    }

    public void setNrDisponibile(int nrDisponibile) {
        this.nrDisponibile = nrDisponibile;
    }

    public String getOra() {
        return ora;
    }

    public void setOra(String ora) {
        this.ora = ora;
    }

    public int getIdS() {
        return idS;
    }

    public void setIdS(int idS) {
        this.idS = idS;
    }

    @Override
    public String toString() {
        return "Table{" +
                "idS=" + idS +
                ", nume='" + nume + '\'' +
                ", data='" + data + '\'' +
                ", locul='" + locul + '\'' +
                ", nrVandute=" + nrVandute +
                ", nrDisponibile=" + nrDisponibile +
                ", ora='" + ora + '\'' +
                '}';
    }
}
