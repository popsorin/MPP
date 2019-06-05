package Tema.ClientRMI;

import Tema.Client.AlertBox;
import Tema.Client.ConfirmBox;
import Tema.Dommain.Artisti;
import Tema.Dommain.Table;
import Tema.Server.Server;
import Tema.ServerRMI.IServices;
import Tema.ServerRMI.ServerImpl;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class StartClient extends Application   {


    TableView<Table> table;
    TableView<Table> tableCautare;
    Stage window;
    Scene login, artisti, cautare;
    ObservableList<Table> table1FirstWindow = FXCollections.observableArrayList();
    String user;
    String password;
    String answer;
    public IServices server;
    ObservableList<Table> tableElements = FXCollections.observableArrayList();

    public static void main(String[] args){

        launch(args);
    }



    public synchronized void start(Stage stage) throws Exception {

        ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:spring-client.xml");
        //server = (IServices)factory.getBean(IServices.class);
        server = (IServices)factory.getBean("chatService");
        System.out.println("Obtained reference to remote server");

        //stage denomination

        window = stage;
        window.setTitle("Login");

        //I'm setting up the first scene

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(5, 5, 5, 5));
        grid.setVgap(4);
        grid.setHgap(5);


        Label labeluser = new Label("Username");
        GridPane.setConstraints(labeluser, 0, 0);

        TextField userInput = new TextField("");
        GridPane.setConstraints(userInput, 1, 0);

        Label labelpassword = new Label("Password");
        GridPane.setConstraints(labelpassword, 0, 1);

        JPasswordField jPasswordField = new JPasswordField();
        TextField passwordInput = new TextField("");
        GridPane.setConstraints(passwordInput, 1, 1);

        Button loginButton = new Button("Login");

        GridPane.setConstraints(loginButton, 1, 2);
        grid.getChildren().addAll(labeluser, userInput, labelpassword, passwordInput, loginButton);


        TableColumn<Table, String> numeColumn = new TableColumn<>("Nume");
        numeColumn.setMinWidth(100);
        numeColumn.setCellValueFactory(new PropertyValueFactory<>("nume"));

        TableColumn<Table, String> dataColumn = new TableColumn<>("Data");
        dataColumn.setMinWidth(100);
        dataColumn.setCellValueFactory(new PropertyValueFactory<>("data"));

        TableColumn<Table, String> loculColumn = new TableColumn<>("Locul");
        loculColumn.setMinWidth(100);
        loculColumn.setCellValueFactory(new PropertyValueFactory<>("locul"));

        TableColumn<Table, Integer> nrVanduteColumn = new TableColumn<>("Vandute");
        nrVanduteColumn.setMinWidth(100);
        nrVanduteColumn.setCellValueFactory(new PropertyValueFactory<>("nrVandute"));

        TableColumn<Table, Integer> nrDisponibileColumn = new TableColumn<>("Disponibile");
        nrDisponibileColumn.setMinWidth(100);
        nrDisponibileColumn.setCellValueFactory(new PropertyValueFactory<>("nrDisponibile"));


        //I'm putting the values in the table
        table = new TableView<>();
        table.getColumns().addAll(numeColumn, dataColumn, loculColumn, nrVanduteColumn, nrDisponibileColumn);


        //create button for purchase
        Button cumparareSearched = new Button("Cumparare");
        Button cumparare1 = new Button("Cumparare");


        //Here I'm making the search after a date

        Button search = new Button("Search       ");
        TextField giveTheDate = new TextField();
        giveTheDate.setPromptText("Give us the date");
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.getChildren().addAll(search, giveTheDate);

        //I'm setting the first scene
        VBox listaArtisti = new VBox();
        listaArtisti.getChildren().addAll(table, hBox, cumparare1);
        artisti = new Scene(listaArtisti, 500, 200);


        //I'm making the login button work
          try{
        loginButton.setOnAction(e -> {
            user = userInput.getText();
            password = passwordInput.getText();
            Boolean answer = server.CheckUser(user,password);
            if(answer==true){
                window.setScene(artisti);
                window.setTitle("Artisti");
                try {

                    tableElements = getTable();
                }
                catch (Exception exc){
                    exc.printStackTrace();
                }
                table.setItems(tableElements);
            }

        });
    }
          catch (Exception exc){System.out.println(exc);}


        login = new Scene(grid, 400, 400);

        window.setScene(login);

      cumparare1.setOnAction(e ->{



          try {

              try {
                  server.Cumparare(ConfirmBox.display("Cumparare"), cumparareBttonClicked());
              } catch (Exception exc) {
                  System.out.println(exc);
              }

              tableElements = getTable();
              table.setItems(tableElements);
          }catch (Exception exc){
              System.out.println(exc);
          }

        });

        //I'm making the table columns here






        //The new window that displays the artist found at a given date

        TableColumn<Table, Integer> oraColumn = new TableColumn<>("Ora");
        oraColumn.setMinWidth(100);
        oraColumn.setCellValueFactory(new PropertyValueFactory<>("ora"));

        TableColumn<Table, String> numeColumn1 = new TableColumn<>("Nume");
        numeColumn1.setMinWidth(100);
        numeColumn1.setCellValueFactory(new PropertyValueFactory<>("nume"));

        TableColumn<Table, String> loculColumn1 = new TableColumn<>("Locul");
        loculColumn1.setMinWidth(100);
        loculColumn1.setCellValueFactory(new PropertyValueFactory<>("locul"));

        TableColumn<Table, Integer> nrDisponibileColumn1 = new TableColumn<>("Disponibile");
        nrDisponibileColumn1.setMinWidth(100);
        nrDisponibileColumn1.setCellValueFactory(new PropertyValueFactory<>("nrDisponibile"));


        //We will implement a back button
        Button back = new Button("<-Back");
        back.setOnAction(e -> {

            window.setScene(artisti);
            window.setTitle("Artisti");
        });

        tableCautare = new TableView<>();
        tableCautare.getColumns().addAll(numeColumn1, loculColumn1, oraColumn, nrDisponibileColumn1);
        VBox cautareArtisti = new VBox();
        cautareArtisti.getChildren().addAll(tableCautare, back, cumparareSearched);
        cautare = new Scene(cautareArtisti, 500, 200);

       search.setOnAction(e -> {

            String searchString = "Search";

            try {

                tableElements = getTableDate(giveTheDate.getText() + ".03.2011");
                tableCautare.setItems(tableElements);
                window.setScene(cautare);
                window.setTitle("Artisti gasiti");

            }
            catch (Exception exc){
                System.out.println(exc);
            }
        });


        cumparareSearched.setOnAction(e-> {


            try {

                server.Cumparare(ConfirmBox.display("Cumparare"), cumparareForSearchedBttonClicked());
                tableElements = getTable();
                table.setItems(tableElements);
                tableElements = getTableDate(giveTheDate.getText() + ".03.2011");
                tableCautare.setItems(tableElements);
            }catch (Exception exc){
                System.out.println(exc);
            }

        });


        //I'm setting up the second scene

        window.show();


    }
    public  ObservableList<Table> getTable() throws Exception{

        ObservableList<Table> table = FXCollections.observableArrayList();

        for (Artisti a : server.findAllArtisti()) {
            for (Table t : server.findTable(a.getId())) {
                table.add(t);
            }
        }

        return table;
    }

    public int cumparareBttonClicked() throws Exception{
        Table tableSelected;

        tableSelected=table.getSelectionModel().getSelectedItem();
        return tableSelected.getIdS();

    }

    public  ObservableList<Table> getTableDate(String data) throws Exception{

        ObservableList<Table> table = FXCollections.observableArrayList();


        for (Table t : server.findArtist(data)) {
            table.add(t);
        }

        return table;
    }
    public int cumparareForSearchedBttonClicked(){
        Table tableSelected;

        tableSelected=tableCautare.getSelectionModel().getSelectedItem();
        return tableSelected.getIdS();

    }

/*



    public int cumparareForSearcgedBttonClicked(){
        Table tableSelected;


                        for (Table t : service.findArtist(data)) {
                            output.writeObject(t);
                            output.flush();
                        }

        tableSelected=tableCautare.getSelectionModel().getSelectedItem();
        return tableSelected.getIdS();

    }


    public ObservableList<Table> getTable() throws Exception{
        int n = (int) input.readObject();

        ObservableList<Table> table1 = FXCollections.observableArrayList();
        for(int i=0;i<n;i++)
        {
            Table t = (Table) input.readObject();
            table1.add(t);
        }
        return table1;
    }






*/

}
