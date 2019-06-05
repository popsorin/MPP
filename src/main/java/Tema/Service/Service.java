package Tema.Service;


import Tema.Dommain.Artisti;
import Tema.Dommain.Spectacol;
import Tema.Dommain.Table;
import Tema.Dommain.User;
import Tema.repository.ILogin;
import Tema.repository.IRepository;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Service implements Serializable {

    private IRepository<Integer, Artisti> repoArtist;
    private IRepository<Integer, Spectacol> repoSpectacol;
    private ILogin<User> repoLogin;

    public Service(){}

    public Service(IRepository<Integer,Artisti> repoA, IRepository<Integer,Spectacol> repoS,ILogin repoL){

        this.repoArtist=repoA;
        this.repoSpectacol=repoS;
        this.repoLogin=repoL;

    }

    public List<Table> findArtist(String data){

        try{
         return  this.repoArtist.findOneDate(data);
        }catch(Exception exp){
            System.out.println(exp.getMessage()+" HERE*************************************");
        }
        return null;
    }
    public void Cumparare(int nrBilete,int idS){

        this.repoSpectacol.updateBilete(idS,nrBilete);

    }
    public boolean CheckUser(String user,String password){
       return this.repoLogin.CheckUser(user,password);
    }
    public Iterable<Artisti> findAllArtisti(){
        return repoArtist.findAll();
    }
    public Iterable<Spectacol> findAllSpectacol(){
        return repoSpectacol.findAll();
    }
    public List<Table> findTable(int id_a){
        return repoArtist.findTable(id_a);
    }

}
