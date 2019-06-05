package Tema.ServerRMI;

import Tema.Dommain.Artisti;
import Tema.Dommain.Spectacol;
import Tema.Dommain.Table;
import Tema.Service.Service;
import Tema.Service.ServiceConfiguration;
import javafx.application.Application;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class ServerImpl implements IServices {

    private Service service;

    public ServerImpl(Service service)
    {
        ApplicationContext facotry = new AnnotationConfigApplicationContext(ServiceConfiguration.class);
        service = facotry.getBean(Service.class);
        this.service = service;

    }
    public List<Table> findArtist(String data){

        return  service.findArtist(data);
    }
    public  void Cumparare(int nrBilete,int idS){
        service.Cumparare(nrBilete,idS);
    }
    public boolean CheckUser(String user,String password){
        return service.CheckUser(user,password);
    }
    public Iterable<Artisti> findAllArtisti(){
        return service.findAllArtisti();
    }
    public Iterable<Spectacol> findAllSpectacol(){
        return service.findAllSpectacol();
    }
    public List<Table> findTable(int id_a){
        return service.findTable(id_a);
    }
}
