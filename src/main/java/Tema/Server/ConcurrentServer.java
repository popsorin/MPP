package Tema.Server;

import Tema.Client.StartClient;

import java.net.Socket;

/**
 * Created by IntelliJ IDEA.
 * User: grigo
 * Date: Mar 18, 2009
 * Time: 12:04:38 PM
 */
public abstract class ConcurrentServer extends AbstractServer {

    public ConcurrentServer(int port) {
        super(port);
         System.out.println("Concurrent Server");
    }

    protected void processRequest(Socket client) {
        Thread tw=createWorker(client);
        System.out.println(tw.getId());
        tw.start();
    }

     protected abstract Thread createWorker(Socket client);
}
