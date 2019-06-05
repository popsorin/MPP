package Tema.ClientRMI;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {

    static int a;

    public static int display(String title){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        window.setMinHeight(150);
        Button cumpara = new Button("Cumpar");



        GridPane grid = new GridPane();
        grid.setPadding(new Insets(5,5,5,5));
        grid.setVgap(4);
        grid.setHgap(5);


        Label labelcumparator = new Label("Nume cumparator");
        GridPane.setConstraints(labelcumparator,0,0);

        TextField numeCumparator = new TextField("");
        GridPane.setConstraints(numeCumparator,1,0);

        Label labelBilete = new Label("Numar de bilete cumparate");
        GridPane.setConstraints(labelBilete,0,1);

        TextField numarDeBilete = new TextField("");
        GridPane.setConstraints(numarDeBilete,1,1);
        GridPane.setConstraints(cumpara,0,2);

        cumpara.setOnAction(e ->{

            a= Integer.parseInt(numarDeBilete.getText());
            window.close();

        });
        grid.getChildren().addAll(labelcumparator, numeCumparator,labelBilete,numarDeBilete,cumpara);

        Scene scene = new Scene(grid);
        window.setScene(scene);
        window.showAndWait();

        return a;
    }
}
