package Tema.Client;

import Tema.Dommain.Table;
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
import javafx.stage.WindowEvent;

import javax.swing.*;
import java.awt.event.WindowListener;
import java.io.*;
import java.net.Socket;


public class StartClient1 extends Application   {


    TableView<Table> table;
    TableView<Table> tableCautare;
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private Socket client;
    Stage window;
    Scene login, artisti, cautare;
    ObservableList<Table> table1FirstWindow = FXCollections.observableArrayList();
    String user;
    String password;
    String answer;

    public static void main(String[] args){

        launch(args);
    }



    public void start(Stage stage) throws Exception {


        try {
            client = new Socket("localhost", 55555);
            System.out.println("Connection obtained.");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        output = new ObjectOutputStream(client.getOutputStream());
        output.flush();

        input = new ObjectInputStream(client.getInputStream());


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
                String login = "Login";

                try {
                    output.writeObject(login);

                    user = userInput.getText();
                    password = passwordInput.getText();
                    System.out.println(user);
                    System.out.println(password);
                    output.writeObject(user);
                    output.flush();
                    output.writeObject(password);
                    output.flush();
                    answer = (String) input.readObject();
/**/
                    System.out.println(answer);
                } catch (Exception exc) {
                    System.out.println(exc);
                }
          /*      if (answer==null || answer.equals("no")) {
                    System.out.println("ERROR");
                    AlertBox.display("Error", "Error");

                } else {

                    try {
                        table1FirstWindow = getTable();
                        table.setItems(table1FirstWindow);
                        System.out.println("PASSED :D");
                        window.setScene(artisti);
                        window.setTitle("Artisti");
                    } catch (Exception exc) {
                        System.out.println(exc);
                    }
                }*/
            });
        }
        catch (Exception exc){System.out.println(exc);}


        login = new Scene(grid, 400, 400);

        window.setScene(login);
/*
        cumparare1.setOnAction(e ->{

            String cumparareFereastra1 = "cumparareFereastra1";

            try {
                output.writeObject(cumparareFereastra1);
                output.flush();
                output.writeObject(cumparareBttonClicked());
                output.flush();
                output.writeObject(ConfirmBox.display("Cumparare"));
                output.flush();
                table.setItems(getTable());
            }catch (Exception exc){
                System.out.println(exc);
            }

        });
*/
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

/*
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
                output.writeObject(searchString);
                output.flush();
                output.writeObject(giveTheDate.getText() + ".03.2011");
                output.flush();
                tableCautare.setItems(getTableDate());
                window.setScene(cautare);
                window.setTitle("Artisti gasiti");

            }
            catch (Exception exc){
                System.out.println(exc);
            }
        });


        cumparareSearched.setOnAction(e-> {

            String cumparareFereastra2 = "cumparareFereastra2";
            try {

                output.writeObject(cumparareFereastra2);
                output.flush();
                output.writeObject(ConfirmBox.display("Cumparare"));
                output.flush();
                output.writeObject(cumparareForSearcgedBttonClicked());
                output.flush();
                table.setItems(getTable());
                output.writeObject(giveTheDate.getText() + ".03.2011");
                output.flush();
                //for(Table t :getTableDate()){
                //    System.out.println(t.toString());
                //  }

                tableCautare.setItems(getTableDate());
            }catch (Exception exc){
                System.out.println(exc);
            }

        });

*/
        //I'm setting up the second scene

        window.show();


    }



    public int cumparareBttonClicked() throws Exception{
        Table tableSelected;

        tableSelected=table.getSelectionModel().getSelectedItem();
        return tableSelected.getIdS();

    }

    public int cumparareForSearcgedBttonClicked(){
        Table tableSelected;

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





    public  ObservableList<Table> getTableDate() throws Exception{

        ObservableList<Table> table = FXCollections.observableArrayList();

        int n = (int) input.readObject();

        System.out.println(n + "90000000000000000000000");

        for(int i=0;i<n;i++){
            Table t = (Table) input.readObject();
            table.add(t);
        }

        return table;
    }


}
