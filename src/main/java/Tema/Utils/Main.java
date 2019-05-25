package Tema.Utils;

import Tema.Client.AlertBox;
import Tema.Client.ConfirmBox;
import Tema.Dommain.Table;
import Tema.Dommain.Artisti;
import Tema.Service.Service;
import Tema.repository.ArtistiJdbcRepository;
import Tema.repository.LogerJdbcRepository;
import Tema.repository.SpectacolJdbcRepository;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import  javafx.scene.Scene;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Main extends Application {

    Service service;
    TableView<Table> table;
    TableView<Table> tableCautare;

    Stage window;
    Scene login,artisti,cautare;


    public static void main(String[] args){

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        //Repo+Service initialization

        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        ArtistiJdbcRepository repoArt = context.getBean(ArtistiJdbcRepository.class);
        SpectacolJdbcRepository repoSpec = context.getBean(SpectacolJdbcRepository.class);
        LogerJdbcRepository repoLoger=context.getBean(LogerJdbcRepository.class);
        service = new Service(repoArt,repoSpec,repoLoger);

        //stage denomination

        window=stage;
        window.setTitle("Login");

        //create button for purchase
        Button cumparareSearched=new Button("Cumparare");
        Button cumparare1=new Button("Cumparare");


         cumparare1.setOnAction(e ->{
             service.Cumparare(ConfirmBox.display("Cumparare"),cumparareBttonClicked());
             table.setItems(getTable());
         });

        //I'm making the table columns here

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
        table.setItems(getTable());
        table.getColumns().addAll(numeColumn,dataColumn,loculColumn,nrVanduteColumn,nrDisponibileColumn);


        //I'm setting up the first scene

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(5,5,5,5));
        grid.setVgap(4);
        grid.setHgap(5);


        Label labeluser = new Label("Username");
        GridPane.setConstraints(labeluser,0,0);

        TextField userInput = new TextField("");
        GridPane.setConstraints(userInput,1,0);

        Label labelpassword = new Label("Password");
        GridPane.setConstraints(labelpassword,0,1);

        TextField passwordInput = new TextField("");
        GridPane.setConstraints(passwordInput,1,1);

        Button loginButton=new Button("Login");

        GridPane.setConstraints(loginButton,1,2);
        grid.getChildren().addAll(labeluser, userInput,labelpassword,passwordInput,loginButton);



        //Here I'm making the search after a date

        Button search = new Button("Search       ");
        TextField giveTheDate = new TextField();
        giveTheDate.setPromptText("Give us the date");
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.getChildren().addAll(search,giveTheDate);

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


        cumparareSearched.setOnAction(e-> {

            service.Cumparare(ConfirmBox.display("Cumparare"),cumparareForSearcgedBttonClicked());
            table.setItems(getTable());
            tableCautare.setItems(getTableDate(giveTheDate.getText()+".03.2011"));
        });
        search.setOnAction(e ->{

            tableCautare = new TableView<>();
            tableCautare.setItems(getTableDate(giveTheDate.getText()+".03.2011"));
            tableCautare.getColumns().addAll(numeColumn1,loculColumn1,oraColumn,nrDisponibileColumn1);
            VBox cautareArtisti = new VBox();
            cautareArtisti.getChildren().addAll(tableCautare,back,cumparareSearched);
            cautare = new Scene(cautareArtisti,500,200);


            for(Table t:getTableDate(giveTheDate.getText())){

                System.out.println(t.toString());

            }

            window.setScene(cautare);
            window.setTitle("Artisti gasiti");
        });


        //I'm setting up the second scene

        VBox listaArtisti = new VBox();
        listaArtisti.getChildren().addAll(table,hBox,cumparare1);
        artisti = new Scene(listaArtisti,500,200);

        //I'm making the login button work
        loginButton.setOnAction(e->{
            String user = userInput.getText();
            String password = passwordInput.getText();
            if(service.CheckUser(user,password)==false)
                AlertBox.display("Login error","Wrong user or password");
            else
            {
                table.setItems(getTable());
                window.setScene(artisti);
                window.setTitle("Artisti");
            }

        });


        login = new Scene(grid,400,400);

        window.setScene(login);

        window.show();



    }





    public int cumparareBttonClicked(){
        Table tableSelected;

        tableSelected=table.getSelectionModel().getSelectedItem();
        return tableSelected.getIdS();

    }
    public int cumparareForSearcgedBttonClicked(){
        Table tableSelected;

        tableSelected=tableCautare.getSelectionModel().getSelectedItem();
        return tableSelected.getIdS();

    }


    public ObservableList<Table> getTable(){
        ObservableList<Table> table = FXCollections.observableArrayList();
        for(Artisti a : service.findAllArtisti()){
           for(Table t : service.findTable(a.getId())){
               table.add(t);
           }
        }
        return table;
    }

    public  ObservableList<Table> getTableDate(String data){
        ObservableList<Table> table = FXCollections.observableArrayList();

            for(Table t : service.findArtist(data)){
                table.add(t);
            }

        return table;
    }




}
