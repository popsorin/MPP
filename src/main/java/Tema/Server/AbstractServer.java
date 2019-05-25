package Tema.Server;



import Tema.Service.Service;
import Tema.repository.ArtistiJdbcRepository;
import Tema.repository.LogerJdbcRepository;
import Tema.repository.SpectacolJdbcRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: grigo
 * Date: Mar 18, 2009
 * Time: 11:41:16 AM
 */
public abstract class AbstractServer {
    private int port;
    private ServerSocket server=null;
    Service service;
    protected ObjectInputStream input;
    protected ObjectOutputStream output;


    public AbstractServer( int port){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        ArtistiJdbcRepository repoArt = context.getBean(ArtistiJdbcRepository.class);
        SpectacolJdbcRepository repoSpec = context.getBean(SpectacolJdbcRepository.class);
        LogerJdbcRepository repoLoger=context.getBean(LogerJdbcRepository.class);
        service = new Service(repoArt,repoSpec,repoLoger);
        this.port=port;
    }

    public void start() throws ServerException {
        try{
            server=new ServerSocket(port);
            while(true){
                System.out.println("Waiting for clients ...");
                Socket client=server.accept();
                output = new ObjectOutputStream(client.getOutputStream());
                input = new ObjectInputStream(client.getInputStream());
                System.out.println("Client connected ...");
                processRequest(client);


            }
        } catch (IOException e) {
            throw new ServerException("Starting server errror ",e);
        }finally {
            try{
                server.close();
            } catch (IOException e) {
                throw new ServerException("Closing server error ", e);
            }
        }
    }

    protected abstract  void processRequest(Socket client);
    public void stop() throws ServerException{
        try{
            server.close();
            input.close();
            output.close();
        } catch (IOException e) {
            throw new ServerException("Closing server error ", e);
        }
    }
}
