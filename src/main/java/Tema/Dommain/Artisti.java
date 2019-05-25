package Tema.Dommain;


public class Artisti{

    private int id;
    private String nume;


    //constructor fara parametri
    public Artisti(){

    }
    //constructor cu parametri
    public Artisti(String n,int id){

        this.nume=n;

        this.id=id;

    }
    //constructor de copiere
    public Artisti(Artisti a){

        if(a!=this) {
            this.nume = a.nume;
            this.id = a.id;
        }
    }

    //accesorii
    public String getNume() {
        return this.nume;
    }
    public void setNume(String n) {
        this.nume=n;
    }


    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id=id;
    }


    public String toString(){

        return "ID:" + Integer.toString(this.id) + " Nume: " + this.nume;

    }


}
