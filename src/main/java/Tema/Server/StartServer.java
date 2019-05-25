package Tema.Server;


public class StartServer {
    public static void main(String[] args) {

       AbstractServer server=new MyConcurrentServer(8888);
   //    AbstractServer server1=new MyConcurrentServer(55555);

        try {

            server.start();
        //s    server1.start();
        } catch (ServerException e) {
            System.out.println(e.getMessage());
        }
    }
}
