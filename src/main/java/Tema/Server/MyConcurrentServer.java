package Tema.Server;

import Tema.Dommain.Artisti;
import Tema.Dommain.Table;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class MyConcurrentServer extends ConcurrentServer {


    public MyConcurrentServer(int port) {
        super(port);
    }

    @Override
    protected Thread createWorker(Socket client) {
        Worker worker = new Worker(client);
        Thread tw = new Thread(worker);
        System.out.println(tw.getId());
        return tw;
    }

    class Worker implements Runnable {
        private Socket client;

        Worker(Socket client) {
            this.client = client;
        }


        public void run() {
            System.out.println("Starting to process request ...");


            try {
                output = new ObjectOutputStream(client.getOutputStream());
                output.flush();
                input = new ObjectInputStream(client.getInputStream());

                while (true) {


                    String option = (String) input.readObject();

                    System.out.println(option);

                    if (option.equals("Login")) {
                        String user = (String) input.readObject();
                        String password = (String) input.readObject();

                        String pass = "no";
                        if (service.CheckUser(user, password) == true)
                            pass = "yes";

                        output.writeObject(pass);
                        output.flush();

                        int n = 0;
                        for (Artisti a : service.findAllArtisti()) {
                            for (Table t : service.findTable(a.getId())) {
                                n++;
                            }
                        }
                        output.writeObject(n);
                        for (Artisti a : service.findAllArtisti()) {
                            for (Table t : service.findTable(a.getId())) {
                                output.writeObject(t);
                                output.flush();
                            }
                        }
                     //   Thread.currentThread().sleep(100);

                    }
                    if (option.equals("cumparareFereastra1")) {
                        int cumparareID = (int) input.readObject();
                        System.out.println(cumparareID);
                        int cumparareConfirmBox = (int) input.readObject();
                        System.out.println(cumparareConfirmBox);
                        System.out.println(option);

                        try {
                            service.Cumparare(cumparareConfirmBox, cumparareID);
                        } catch (Exception exc) {
                            System.out.println(exc);
                        }
                        for (Artisti a : service.findAllArtisti()) {
                            for (Table t : service.findTable(a.getId())) {
                                System.out.println(t.toString());
                            }
                        }

                        int n = 0;
                        for (Artisti a : service.findAllArtisti()) {
                            for (Table t : service.findTable(a.getId())) {
                                n++;
                            }
                        }
                        output.writeObject(n);
                        for (Artisti a : service.findAllArtisti()) {
                            for (Table t : service.findTable(a.getId())) {
                                output.writeObject(t);
                                output.flush();
                            }
                        }
                      //  Thread.currentThread().sleep(100);

                    }
                    if (option.equals("Search")) {
                        System.out.println("Reusit");
                        String data = (String) input.readObject();
                        int n = 0;
                        for (Table t : service.findArtist(data))
                            n++;

                        output.writeObject(n);
                        output.flush();

                        for (Table t : service.findArtist(data)) {
                            output.writeObject(t);
                            output.flush();
                        }
                    //    Thread.currentThread().sleep(100);
                    }
                    if (option.equals("cumparareFereastra2")) {

                        System.out.println("YAAAAAAAAAAAAY");
                        int cumparare = (int) input.readObject();
                        int cumparareID = (int) input.readObject();

                        //System.out.println(cumparare + "    "+cumparareID + "****************");
                        service.Cumparare(cumparare, cumparareID);

                        int n = 0;
                        for (Artisti a : service.findAllArtisti()) {
                            for (Table t : service.findTable(a.getId())) {
                                n++;
                            }
                        }
                        output.writeObject(n);
                        output.flush();
                        for (Artisti a : service.findAllArtisti()) {
                            for (Table t : service.findTable(a.getId())) {
                                output.writeObject(t);
                                output.flush();
                            }
                        }

                        String data = (String) input.readObject();

                        System.out.println(data + "***************************");

                        n = 0;

                        for (Table t : service.findArtist(data)) {
                            n++;
                        }

                        output.writeObject(n);
                        output.flush();

                        for (Table t : service.findArtist(data)) {
                            output.writeObject(t);
                            output.flush();
                        }
                   //     Thread.currentThread().sleep(100);
                    }

                    System.out.println("Finished  processing request ...");

                }




            } catch (Exception e) {
                System.out.println("Error in processing client request " + e);
            } finally {
                try {
                    client.close();
                    input.close();
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
