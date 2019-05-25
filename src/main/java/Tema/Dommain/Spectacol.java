package Tema.Dommain;

import java.sql.Timestamp;

public class Spectacol {

    private int idS;
    private String data;
    private String locul;
    private  int nrVandute;
    private int nrDisponibile;
    private int idA;

   public Spectacol(int id,String d,String l,int nrV,int nrD,int idA){
        this.idS=id;
        this.nrDisponibile=nrD;
        this.nrVandute=nrV;
        this.data=d;
        this.locul=l;
        this.idA=idA;
    }
    public Spectacol(Spectacol s){
       if(this!=s){
           this.idS=s.idS;
           this.nrVandute=s.nrVandute;
           this.nrDisponibile=s.nrDisponibile;
           this.data=s.data;
           this.locul=s.locul;
           this.idA=s.idA;
       }
    }
    //accesorii

    public int getIdS() {
        return this.idS;
    }
    public void setLocul(int idS) {
        this.idS=idS;
    }

    public String getLocul() {
        return this.locul;
    }
    public void setLocul(String l) {
        this.locul=l;
    }


    public String getData(){
        return this.data;
    }
    public void setData(String d){
        this.data=d;
    }


    public int getNrDisponibile() {
        return this.nrDisponibile;
    }
    public void setnrDisponibile(int l) {
        this.nrDisponibile=l;
    }


    public int getNrVandute(){
        return this.nrVandute;
    }
    public void setNrVandute(int d){
        this.nrVandute=d;
    }

    public int getIdA(){return this.idA;}
    public void setIdA(int ida){this.idA=ida;}


    public String toString(){

        return this.idS + " " +this.data.toString()+ " " + this.locul + " " + this.nrVandute + " " + this.nrDisponibile + " " + this.idA + "\n";

    }

}
